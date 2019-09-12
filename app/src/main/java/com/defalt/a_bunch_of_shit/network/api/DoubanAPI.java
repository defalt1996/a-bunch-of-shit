package com.defalt.a_bunch_of_shit.network.api;
/*
 *  author: Derrick
 *  Time: 2019/8/14 16:50
 */

import com.defalt.a_bunch_of_shit.bean.douban.film.Root;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.top250details.Top250Details;
import com.defalt.a_bunch_of_shit.bean.douban.film.us_box.RootUsBox;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DoubanAPI {

    String apikey = "0df993c66c0c636e29ecbb5344252a4a";

    @GET("v2/movie/in_theaters")
    Observable<Root> getMovieInTheaters(@Query("city")String city, @Query("apikey") String apikey, @Query("start") int start, @Query("count") int count);

    @GET("v2/movie/coming_soon")
    Observable<Root> getMovieComingSoon(@Query("apikey") String apikey, @Query("start") int start, @Query("count") int count);

    //北美榜单
    @GET("v2/movie/us_box")
    Observable<RootUsBox> getMovieUSBox (@Query("apikey") String apikey,@Query("start") int start, @Query("count") int count);

    @GET("v2/movie/weekly")
    Observable<Root> getMovieWeekly(@Query("apikey") String apikey);

    @GET("v2/movie/top250")
    Observable<Root> getMovieTop250(@Query("apikey") String apikey,@Query("start") int start, @Query("count") int count);

    @GET("v2/movie/subject/{id}")
    Observable<Top250Details> getMovieTop250Detail(@Path("id") String id, @Query("apikey") String apikey );
}
