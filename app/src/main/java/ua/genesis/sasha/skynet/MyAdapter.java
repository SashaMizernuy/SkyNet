package ua.genesis.sasha.skynet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<MyOpenWeather> mWeather;
    private LayoutInflater mInflater;
    private Context context;
    private MyAdapterListener myAdapterListener;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private MyAdapterListener myAdapterListener;
        @BindView(R.id.imagePicasso) ImageView image;
        @BindView(R.id.city) TextView titleCity;
        @BindView(R.id.temp) TextView titleTemp;
        @BindView(R.id.tempMin) TextView titleTempMin;
        @BindView(R.id.tempMax) TextView titleTempMax;


        public ViewHolder(@NonNull View itemView, MyAdapterListener myAdapterListener){
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
            this.myAdapterListener=myAdapterListener;
        }

        @Override
        public void onClick(View v) {
            myAdapterListener.myAdapterClick(getAdapterPosition());
        }
    }

    public MyAdapter(Context context, ArrayList<MyOpenWeather> myWeather,MyAdapterListener mAdapterListener){
        mInflater = LayoutInflater.from(context);
        mWeather=myWeather;
        this.context=context;
        this.myAdapterListener=mAdapterListener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view,myAdapterListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){

        MyOpenWeather weather=mWeather.get(position);

        holder.titleCity.setText(weather.city);
        holder.titleTemp.setText(String.valueOf(weather.temp.shortValue()+" \u2103"));
        holder.titleTempMin.setText(String.valueOf(weather.tempMin.intValue()+"\u00b0"));
        holder.titleTempMax.setText(String.valueOf(weather.tempMax.intValue()+"\u00b0"));
        Picasso.with(context).load("http://openweathermap.org/img/w/"+weather.imagePath+".png").fit().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mWeather.size();
    }

    public interface MyAdapterListener{
        void myAdapterClick(int position);

    }
}