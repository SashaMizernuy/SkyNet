package ua.genesis.sasha.skynet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenWeather {

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("visibility")
    @Expose
    private long visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private long cod;

    /**
     * No args constructor for use in serialization
     */
    public OpenWeather() {
    }

    /**
     * @param id
     * @param dt
     * @param clouds
     * @param coord
     * @param wind
     * @param cod
     * @param visibility
     * @param sys
     * @param name
     * @param base
     * @param weather
     * @param main
     */
    public OpenWeather(Coord coord, List<Weather> weather, String base, Main main, long visibility, Wind wind, Clouds clouds, long dt, Sys sys, long id, String name, long cod) {
        super();
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }


    public class Clouds {

        @SerializedName("all")
        @Expose
        private long all;

        /**
         * No args constructor for use in serialization
         */
        public Clouds() {
        }

        /**
         * @param all
         */
        public Clouds(long all) {
            super();
            this.all = all;
        }

        public long getAll() {
            return all;
        }

        public void setAll(long all) {
            this.all = all;
        }

    }


    public class Coord {

        @SerializedName("lon")
        @Expose
        private Double lon;
        @SerializedName("lat")
        @Expose
        private Double lat;

        /**
         * No args constructor for use in serialization
         */
        public Coord() {
        }

        /**
         * @param lon
         * @param lat
         */
        public Coord(Double lon, Double lat) {
            super();
            this.lon = lon;
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

    }


    public class Main {

        @SerializedName("temp")
        @Expose
        private Double temp;
        @SerializedName("pressure")
        @Expose
        private Double pressure;
        @SerializedName("humidity")
        @Expose
        private Double humidity;
        @SerializedName("temp_min")
        @Expose
        private Double tempMin;
        @SerializedName("temp_max")
        @Expose
        private Double tempMax;

        /**
         * No args constructor for use in serialization
         */
        public Main() {
        }

        /**
         * @param humidity
         * @param pressure
         * @param tempMax
         * @param temp
         * @param tempMin
         */
        public Main(Double temp, Double pressure, Double humidity, Double tempMin, Double tempMax) {
            super();
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
            this.tempMin = tempMin;
            this.tempMax = tempMax;
        }

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }

        public Double getPressure() {
            return pressure;
        }

        public void setPressure(Double pressure) {
            this.pressure = pressure;
        }

        public Double getHumidity() {
            return humidity;
        }

        public void setHumidity(Double humidity) {
            this.humidity = humidity;
        }

        public Double getTempMin() {
            return tempMin;
        }

        public void setTempMin(Double tempMin) {
            this.tempMin = tempMin;
        }

        public Double getTempMax() {
            return tempMax;
        }

        public void setTempMax(Double tempMax) {
            this.tempMax = tempMax;
        }

    }




    public class Sys {

        @SerializedName("type")
        @Expose
        private long type;
        @SerializedName("id")
        @Expose
        private long id;
        @SerializedName("message")
        @Expose
        private double message;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("sunrise")
        @Expose
        private long sunrise;
        @SerializedName("sunset")
        @Expose
        private long sunset;

        /**
         * No args constructor for use in serialization
         */
        public Sys() {
        }

        /**
         * @param message
         * @param id
         * @param sunset
         * @param sunrise
         * @param type
         * @param country
         */
        public Sys(long type, long id, double message, String country, long sunrise, long sunset) {
            super();
            this.type = type;
            this.id = id;
            this.message = message;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public long getType() {
            return type;
        }

        public void setType(long type) {
            this.type = type;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public double getMessage() {
            return message;
        }

        public void setMessage(double message) {
            this.message = message;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }

    }


    public class Weather {

        @SerializedName("id")
        @Expose
        private long id;
        @SerializedName("main")
        @Expose
        private String main;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("icon")
        @Expose
        private String icon;

        /**
         * No args constructor for use in serialization
         */
        public Weather() {
        }

        /**
         * @param id
         * @param icon
         * @param description
         * @param main
         */
        public Weather(long id, String main, String description, String icon) {
            super();
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }


    public class Wind {

        @SerializedName("speed")
        @Expose
        private Double speed;
        @SerializedName("deg")
        @Expose
        private Double deg;

        /**
         * No args constructor for use in serialization
         */
        public Wind() {
        }

        /**
         * @param speed
         * @param deg
         */
        public Wind(Double speed, Double deg) {
            super();
            this.speed = speed;
            this.deg = deg;
        }

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }

        public Double getDeg() {
            return deg;
        }

        public void setDeg(Double deg) {
            this.deg = deg;
        }

    }
}
