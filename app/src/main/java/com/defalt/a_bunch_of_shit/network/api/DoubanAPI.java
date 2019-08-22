package com.defalt.a_bunch_of_shit.network.api;
/*
 *  author: Derrick
 *  Time: 2019/8/14 16:50
 */

import com.defalt.a_bunch_of_shit.bean.douban.film.Root;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DoubanAPI {

    String apikey = "0df993c66c0c636e29ecbb5344252a4a";

    @GET("v2/movie/in_theaters")
    Observable<Root> getMovieInTheaters(@Query("city")String city, @Query("apikey") String apikey, @Query("start") int start, @Query("count") int count);

    @GET("v2/movie/coming_soon")
    Observable<Root> getMovieComingSoon(@Query("apikey") String apikey, @Query("start") int start, @Query("count") int count);

    @GET("v2/movie/weekly")
    Observable<Root> getMovieWeekly(@Query("apikey") String apikey);

}
