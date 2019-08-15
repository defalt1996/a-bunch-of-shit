package com.defalt.a_bunch_of_shit.network;
/*
 *  author: Derrick
 *  Time: 2019/8/15 15:27
 */

import com.defalt.a_bunch_of_shit.network.api.DoubanAPI;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    private static DoubanAPI doubanAPI;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    public static DoubanAPI getDoubanAPI() {
        if (doubanAPI == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.douban.com")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            doubanAPI = retrofit.create(DoubanAPI.class);
        }
        return doubanAPI;
    }


}
