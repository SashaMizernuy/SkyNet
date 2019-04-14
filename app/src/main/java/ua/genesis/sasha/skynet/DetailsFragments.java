package ua.genesis.sasha.skynet;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailsFragments extends Fragment {

    public static ArrayList<String> day = new ArrayList<>();
    public static ArrayList<MyOpenWeatherForecast> myWeathers=new ArrayList<MyOpenWeatherForecast>();
    String[] wDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    String city;


    @Inject
    Retrofit retrofit;

    @BindView(R.id.city_name)
    TextView cityName;

    @BindView(R.id.day_of_week)
    TextView dayOfWeek;

    @BindView(R.id.temp_info)
    TextView tempInfo;

    @BindView(R.id.clouds_icon)
    ImageView clouds;

    @BindView(R.id.image_hum)
    ImageView imageHum;

    @BindView(R.id.text_hum)
    TextView textHumidity;

    @BindView(R.id.image_wind)
    ImageView imageWind;

    @BindView(R.id.text_wind)
    TextView textWind;


    @BindView(R.id.tableRow1)
    TableRow row1;

    @BindView(R.id.tableRow2)
    TableRow row2;

    @BindView(R.id.tableRow3)
    TableRow row3;

    @BindView(R.id.tableRow4)
    TableRow row4;




    OpenWeatherForecast openWeatherForecast;
    Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         context=getActivity().getApplicationContext();

        View view = inflater.inflate(R.layout.custom_city, container, false);
        MyApplication.getmMyComponent().inject(this);
        long id=getArguments().getLong("id");
        city=getArguments().getString("city");
        day.clear();
        myWeathers.clear();

        getDailyForecast(id);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,getActivity());
    }


    @OnClick(R.id.closeView)
    public void closeFragment(View view){
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }



    String getWind(double degree,double speed){
        if(degree>360-22.5) return "nnw "+speed+" m/s";
        else if(degree>360-45)return"nw "+speed+" m/s";
        else if(degree>360-67.5)return"wnw "+speed+" m/s";
        else if(degree>270)return"w "+speed+" m/s";
        else if(degree>270-22.5)return"wsw "+speed+" m/s";
        else if(degree>270-45)return"sw "+speed+" m/s";
        else if(degree>270-67.5)return"ssw "+speed+" m/s";
        else if(degree>180)return"s "+speed+" m/s";
        else if(degree>180-22.5)return"sse "+speed+" m/s";
        else if(degree>180-45)return"se "+speed+" m/s";
        else if(degree>180-67.5)return"ese "+speed+" m/s";
        else if(degree>90)return"e "+speed+" m/s";
        else if(degree>67.5)return"ene "+speed+" m/s";
        else if(degree>45)return"ne "+speed+" m/s";
        else if(degree>22.5)return"nne "+speed+" m/s";
        else  return"n "+speed+" m/s";
    }


private void getDailyForecast(long id){

    WeatherApi api=retrofit.create(WeatherApi.class);
    api.getOpenWeatherForecast(id,7).enqueue(new Callback<OpenWeatherForecast>() {
        @Override
        public void onResponse(Call<OpenWeatherForecast> call, Response<OpenWeatherForecast> response) {
            openWeatherForecast = (OpenWeatherForecast) response.body();
                for (int i = 0; i < openWeatherForecast.list.size(); i++) {
                    OpenWeatherForecast.DailyForecast d = openWeatherForecast.list.get(i);
                    OpenWeatherForecast.Weather f = openWeatherForecast.list.get(i).weather.get(0);
                myWeathers.add(new MyOpenWeatherForecast( d.main.temp,getWind(d.wind.deg,d.wind.speed),f.icon,d.main.humidity));//,f.icon
            }
            addTable();

        }

        @Override
        public void onFailure(Call<OpenWeatherForecast> call, Throwable t) {
            Log.i("Script","MESSEGE "+t.getMessage());

        }
    });
}

public void addTable(){

    GregorianCalendar gCalendar = new GregorianCalendar();
    int stDay = gCalendar.get(Calendar.DAY_OF_WEEK);

    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();

    dayOfWeek.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
        for (int i=0;i<myWeathers.size();i++) {
            tempInfo.setText(myWeathers.get(i).temp.shortValue() + " \u2103");
            textHumidity.setText(myWeathers.get(i).humidity+"%");
            textWind.setText(myWeathers.get(i).wind);
            Picasso.with(context).load("http://openweathermap.org/img/w/"+myWeathers.get(i).icon+".png").resize(120,120).into( clouds);
        }

    cityName.setText(city);
    imageHum.setImageResource(R.drawable.humidity_icon);
    imageWind.setImageResource(R.drawable.cloud_wind);

        for (int i = stDay; i < stDay + 6; i++)
            day.add(wDays[i]);

    TableRow.LayoutParams paramsDate = new TableRow.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    TableRow.LayoutParams paramsHum = new TableRow.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    paramsHum.setMargins(0,0,0,0);
    paramsDate.setMargins(30, 0, 30, 5);

        for(int i=0;i<day.size();i++) {

            TextView txtDate = new TextView(context);
            row1.removeView(txtDate);
            txtDate.setLayoutParams(paramsDate);
            txtDate.setText(day.get(i));
            row1.addView(txtDate);

            TextView txtTemp = new TextView(context);
            txtTemp.setLayoutParams(paramsDate);
            txtTemp.setText(String.valueOf(myWeathers.get(i).temp.shortValue()+"\u00b0"));
            row2.addView(txtTemp);

            ImageView image=new ImageView(context);
            image.setLayoutParams(paramsDate);
            Picasso.with(context).load("http://openweathermap.org/img/w/"+myWeathers.get(i).icon+".png").resize(90,90).into( image);
            Log.i("Script","PICASSO = "+image);
            row3.addView(image);

            TextView txtHum = new TextView(context);
            txtHum.setLayoutParams(paramsDate);
            txtHum.setText(String.valueOf(myWeathers.get(i).humidity+"%"));
            row4.addView(txtHum);
        }

    }


}
