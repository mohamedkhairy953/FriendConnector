package com.example.moham.mvp_rx_dagg_ret1.retrofit;

import com.example.moham.mvp_rx_dagg_ret1.retrofit.RetrofitUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moham on 9/28/2017.
 */

public class ProvideRetrofit {
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        if(retrofit==null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitUtil.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
