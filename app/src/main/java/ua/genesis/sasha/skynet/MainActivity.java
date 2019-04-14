package ua.genesis.sasha.skynet;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity implements PlaceSelectionListener,MyAdapter.MyAdapterListener{


    DBHelper dbHelper;
    SQLiteDatabase db;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Cursor c;
    ContentValues cv;
    ArrayList<MyOpenWeather> weather;
    OpenWeather open;

    @Inject
    public Retrofit retrofit;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private int position;


    @Override
    public void myAdapterClick(int position) {

        String city=weather.get(position).city;
        long id=weather.get(position).id;

        Bundle bundle=new Bundle();
        bundle.putLong("id",id);
        bundle.putString("city",city);
        android.app.FragmentManager fragmentManager=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        DetailsFragments detailsFragments = new DetailsFragments();

        fragmentTransaction.replace(R.id.fragment_switch,detailsFragments);
        detailsFragments.setArguments(bundle);
        fragmentTransaction.commit();

    }


    @Override
    public void onPlaceSelected(@NonNull Place place) {

        cv = new ContentValues();
        String name=place.getName();
        Double latitude = place.getLatLng().latitude;
        Double longitude =place.getLatLng().longitude;
        db = dbHelper.getWritableDatabase();
        cv.put("name",name);
        cv.put("latitude",latitude);
        cv.put("longitude",longitude);
        long rowID = db.insert("mytable", null, cv);
        getData();
        weather=new ArrayList<>();
        adapter();
    }

    @Override
    public void onError(@NonNull Status status) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MyApplication.getmMyComponent().inject(this);

        Places.initialize(getApplicationContext(), " AIzaSyBliH6h7ThlHt-xTkuIGDWZ8YRpnWHkZHo ");
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.searchBar);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.ID,Place.Field.LAT_LNG));
        autocompleteFragment.setOnPlaceSelectedListener(this);

        dbHelper = new DBHelper(this);
        getData();
        weather=new ArrayList<>();
        adapter();
    }


    public void adapter(){
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new MyAdapter(this,weather,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    private void getDailyForecast(String lat,String lon){
       WeatherApi api=retrofit.create(WeatherApi.class);
       api.getOpenWeather(lat,lon).enqueue(new Callback<OpenWeather>() {
                   @Override
                   public void onResponse(Call<OpenWeather> call, Response<OpenWeather> response) {
                       open=(OpenWeather)response.body();
                       OpenWeather.Main d=open.getMain();
                       OpenWeather.Coord f=open.getCoord();
                       OpenWeather name=open;
                       OpenWeather.Weather w=open.getWeather().get(0);
                        weather.add(new MyOpenWeather(d.getTemp(),d.getTempMax(),d.getTempMin(),name.getName(),f.getLat(),f.getLon(),w.getIcon(),name.getId()));
                        mAdapter.notifyDataSetChanged();
                        Log.i("Script","ArrayAdapter_Latitude= "+f.getLat());
                   }

                   @Override
                   public void onFailure(Call<OpenWeather> call, Throwable t) {
                       Log.i("Script","MESSEGE "+t.getMessage());

                   }
               });
    }


    public void getData(){
        db=dbHelper.getReadableDatabase();
        c = db.query("mytable", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            int latitudeColIndex = c.getColumnIndex("latitude");
            int longitudeColIndex = c.getColumnIndex("longitude");
            do {
                getDailyForecast(c.getString(latitudeColIndex),c.getString(longitudeColIndex));

            } while (c.moveToNext());
        } else
        if(c.getColumnIndex("id")<=1){
            putData();
        }
        c.close();
    }


    public void putData(){
        cv = new ContentValues();
        db = dbHelper.getWritableDatabase();
        cv.put("name","Donetsk");
        cv.put("latitude",48.023);
        cv.put("longitude",37.80224);
        db.insert("mytable", null, cv);
        cv.put("name","Vinnytsya");
        cv.put("latitude",49.2331);
        cv.put("longitude",28.4682);
        db.insert("mytable", null, cv);
        c.moveToFirst();
        getData();
    }
}

