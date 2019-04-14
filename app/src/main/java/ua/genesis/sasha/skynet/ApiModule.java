package ua.genesis.sasha.skynet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
public class ApiModule {

    String mBaseUrl;

    ApiModule(String mBaseUrl){
        this.mBaseUrl=mBaseUrl;
    }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gson=new GsonBuilder();
        return gson.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        Retrofit retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(provideGson())).baseUrl(mBaseUrl).build();
        return retrofit;
    }

}
