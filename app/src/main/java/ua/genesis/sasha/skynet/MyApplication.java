package ua.genesis.sasha.skynet;

import android.app.Application;




public class MyApplication extends Application {

    private static MyComponent mMyComponent;




    @Override
    public void onCreate(){
        super.onCreate();
        mMyComponent=createMyComponent();
    }

    public static MyComponent getmMyComponent(){
        return mMyComponent;
    }


    private MyComponent createMyComponent(){
        return DaggerMyComponent
                .builder()
                .apiModule(new ApiModule("https://api.openweathermap.org/"))
                .build();
    }

}
