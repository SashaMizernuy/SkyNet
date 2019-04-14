package ua.genesis.sasha.skynet;

public class MyOpenWeatherForecast {

    public Double temp;
    public String wind;
    public String icon;
    public Integer humidity;

    MyOpenWeatherForecast(Double temp, String wind,String icon,Integer humidity){

        this.temp=temp;
        this.wind=wind;
        this.icon=icon;
        this.humidity=humidity;
    }
}
