package com.example.sah.lesson_8;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String weatherMain;
    String weatherDescr;
    String cityName;
    String humidity;
    String wind_speed;
    String wind_direction;
    String temp;




    public MyAdapter(String city_Name,
                     String weather_main,
                     String weather_descr,
                     String weather_temp,
                     String weather_humidity,
                     String weather_wind_speed,
                     String weather_wind_dir) {
        cityName = city_Name;
        weatherMain = weather_main;
        weatherDescr = weather_descr;
        temp = weather_temp;
        humidity = weather_humidity;
        wind_speed = weather_wind_speed;
        wind_direction = weather_wind_dir;


    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(cityName);
        holder.tv_weather_info.setText(weatherMain);
        holder.tv_weather_description.setText(weatherDescr);
        holder.tv_temp.setText(temp);
        holder.tv_humidity.setText(humidity);
        holder.tv_speed.setText(wind_speed);
        holder.tv_dir.setText(wind_direction);


        if (weatherMain.equals("Snow")) {
            holder.iv_main.setImageResource(R.drawable.snow);
        }
        else if (weatherMain.equals("Clouds")){
            holder.iv_main.setImageResource(R.drawable.clouds);
        }
         else if (weatherMain.equals("Rain")){
            holder.iv_main.setImageResource(R.drawable.rain);
        }
        else if (weatherMain.equals("Clear")){
            holder.iv_main.setImageResource(R.drawable.clear);
        }
        else if (weatherMain.equals("Mist")){
            holder.iv_main.setImageResource(R.drawable.mist);
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_weather_info;
        private TextView tv_weather_description;
        private ImageView iv_main;
        private TextView tv_temp;
        private TextView tv_humidity;
        private TextView tv_speed;
        private TextView tv_dir;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_weather_info = (TextView) itemView.findViewById(R.id.tv_weather_info);
            tv_weather_description = (TextView) itemView.findViewById(R.id.tv_weather_description);
            iv_main = (ImageView) itemView.findViewById(R.id.iv_main);
            tv_temp = (TextView) itemView.findViewById(R.id.tv_temp);
            tv_dir = (TextView) itemView.findViewById(R.id.tv_wind_dir);
            tv_humidity = (TextView) itemView.findViewById(R.id.tv_humidity);
            tv_speed= (TextView) itemView.findViewById(R.id.tv_wind_speed);
        }

    }
}