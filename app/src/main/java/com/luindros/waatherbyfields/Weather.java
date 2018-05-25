package com.luindros.waatherbyfields;

public class Weather {

    private int weather_id;

    public Weather() {
    }

    public Weather(int weather_id) {
        this.weather_id = weather_id;
    }

    public int getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(int weather_id) {
        this.weather_id = weather_id;
    }
}
