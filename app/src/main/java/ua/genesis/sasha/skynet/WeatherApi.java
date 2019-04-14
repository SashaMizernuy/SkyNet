package ua.genesis.sasha.skynet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/data/2.5/weather?APPID=1326e3f912fb46859a937be21bf3f0c6&units=metric")
    Call <OpenWeather> getOpenWeather(@Query("lat") String lat, @Query("lon") String lon);


    @GET("/data/2.5/forecast?APPID=1326e3f912fb46859a937be21bf3f0c6&units=metric")
    Call <OpenWeatherForecast> getOpenWeatherForecast(@Query("id")long id, @Query("cnt")int countDays);

}
