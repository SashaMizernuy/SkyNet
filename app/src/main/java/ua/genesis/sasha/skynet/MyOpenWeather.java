package ua.genesis.sasha.skynet;

public class MyOpenWeather {
    Double temp;
    Double tempMin;
    Double tempMax;
    String city;
    Double lat;
    Double lon;
    String imagePath;
    long id;



    MyOpenWeather(Double temp, Double tempMin, Double tempMax,String city,Double lat,Double lon,String imagePath,long id){
        this.temp=temp;
        this.tempMin=tempMin;
        this.tempMax=tempMax;
        this.city=city;
        this.lat=lat;
        this.lon=lon;
        this.imagePath=imagePath;
        this.id=id;


    }
}
