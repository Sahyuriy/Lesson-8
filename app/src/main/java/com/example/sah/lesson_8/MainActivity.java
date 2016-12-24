package com.example.sah.lesson_8;


import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    String jsonUrlr;
    private EditText textField;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.btn);
        textField = (EditText) findViewById(R.id.et);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textField.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Текстове поле пусте", Toast.LENGTH_SHORT).show();
                } else {
                    jsonUrlr = textField.getText().toString();
                    Toast.makeText(getApplicationContext(), jsonUrlr, Toast.LENGTH_SHORT).show();

                    new ParseTask().execute();

                }
            }
        });
    }


    private class ParseTask extends AsyncTask<Void, Void, String> {


        JSONObject dataJsonObj = null;
        JSONObject dataWeatherJsonObj = null;
        JSONObject dataTemperatureJsonObj = null;
        JSONObject dataWindJsonObj = null;
        JSONArray weatherJsonObj = null;
        String data = "";
        String jsonWeather_main = "";
        String jsonWeather_descr = "";
        int jsonWeather_temp = 0;
        String jsonName = "";
        String humidity = "";
        String wind_speed = "";
        String wind_direction = "";

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";


        @Override
        protected String doInBackground(Void... params) {


            try {

                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + jsonUrlr + "&appid=9969e65c30d6a4788a27340c1ea4f3f6");


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);


            try {
                dataJsonObj = new JSONObject(strJson);
                jsonName = dataJsonObj.getString("name");

                dataTemperatureJsonObj = dataJsonObj.getJSONObject("main");
                dataWindJsonObj = dataJsonObj.getJSONObject("wind");


                weatherJsonObj = dataJsonObj.getJSONArray("weather");
                data = weatherJsonObj.getString(0);
                dataWeatherJsonObj = new JSONObject(data);


                jsonWeather_temp = dataTemperatureJsonObj.getInt("temp");
                jsonWeather_main = dataWeatherJsonObj.getString("main");
                jsonWeather_descr = dataWeatherJsonObj.getString("description");
                humidity = String.valueOf(dataTemperatureJsonObj.getInt("humidity"));
                wind_speed = String.valueOf(dataWindJsonObj.getString("speed"));
                wind_direction = String.valueOf(dataWindJsonObj.getString("deg"));


            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonWeather_temp = jsonWeather_temp - 273;
            String temp = String.valueOf(jsonWeather_temp);


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


            WeatherFragment fragment = new WeatherFragment("City: " + jsonName,
                    jsonWeather_main,
                    "Description: " + jsonWeather_descr,
                    "Temperature: " + temp + "ºC",
                    "Humidity " + humidity,
                    "Wind speed " + wind_speed + " m/s",
                    "Wind direction " + wind_direction + "º");
            fragmentTransaction.replace(R.id.fragm,fragment);
            fragmentTransaction.commit();




        }
    }

}
