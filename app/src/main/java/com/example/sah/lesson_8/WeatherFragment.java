package com.example.sah.lesson_8;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WeatherFragment extends Fragment {


    private RecyclerView rv_items;
    String weatherMain;
    String weatherDescr;
    String cityName;
    String humidity;
    String wind_speed;
    String wind_direction;
    String temp;


    public WeatherFragment(String city_Name,
                           String weather_main,
                           String weather_descr,
                           String weather_temp,
                           String weather_humidity,
                           String weather_wind_speed,
                           String weather_wind_direction) {
        cityName = city_Name;
        weatherMain = weather_main;
        weatherDescr = weather_descr;
        temp = weather_temp;
        humidity = weather_humidity;
        wind_speed = weather_wind_speed;
        wind_direction = weather_wind_direction;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rv_items = (RecyclerView)view.findViewById(R.id.rv_item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        MyAdapter adapter = new MyAdapter(cityName, weatherMain, weatherDescr, temp, humidity, wind_speed, wind_direction);
        rv_items.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_items.setAdapter(adapter);


    }

}
