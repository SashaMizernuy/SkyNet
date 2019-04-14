package ua.genesis.sasha.skynet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenWeatherForecast {
    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public Double message;
    @SerializedName("cnt")
    @Expose
    public Integer cnt;
    @SerializedName("list")
    @Expose
    public List<DailyForecast> list = null;
    @SerializedName("city")
    @Expose
    public City city;

    public class City {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("coord")
        @Expose
        public Coord coord;
        @SerializedName("country")
        @Expose
        public String country;

    }


    public class Clouds {

        @SerializedName("all")
        @Expose
        public Integer all;

    }


    public class Coord {

        @SerializedName("lat")
        @Expose
        public Double lat;
        @SerializedName("lon")
        @Expose
        public Double lon;

    }

    public class DailyForecast {

        @SerializedName("dt")
        @Expose
        public Integer dt;
        @SerializedName("main")
        @Expose
        public Main main;
        @SerializedName("weather")
        @Expose
        public List<Weather> weather = null;
        @SerializedName("clouds")
        @Expose
        public Clouds clouds;
        @SerializedName("wind")
        @Expose
        public Wind wind;
        @SerializedName("sys")
        @Expose
        public Sys sys;
        @SerializedName("dt_txt")
        @Expose
        public String dtTxt;
        @SerializedName("rain")
        @Expose
        public Rain rain;

    }

    public class Main {

        @SerializedName("temp")
        @Expose
        public Double temp;
        @SerializedName("temp_min")
        @Expose
        public Double tempMin;
        @SerializedName("temp_max")
        @Expose
        public Double tempMax;
        @SerializedName("pressure")
        @Expose
        public Double pressure;
        @SerializedName("sea_level")
        @Expose
        public Double seaLevel;
        @SerializedName("grnd_level")
        @Expose
        public Double grndLevel;
        @SerializedName("humidity")
        @Expose
        public Integer humidity;
        @SerializedName("temp_kf")
        @Expose
        public Float tempKf;

    }


    public class Rain {

        @SerializedName("3h")
        @Expose
        public Double _3h;

    }


    public class Sys {

        @SerializedName("pod")
        @Expose
        public String pod;

    }

    public class Weather {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("main")
        @Expose
        public String main;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("icon")
        @Expose
        public String icon;

    }

    public class Wind {

        @SerializedName("speed")
        @Expose
        public Double speed;
        @SerializedName("deg")
        @Expose
        public Double deg;

    }
}
