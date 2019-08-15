package com.defalt.a_bunch_of_shit.home.recommend;
/*
 *  author: Derrick
 *  Time: 2019/6/13 14:58
 */

import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;

import java.util.List;

public interface RecommendContract {

    void getMovieInTheaterSuccess(List<Subjects> subjects);

    void getMovieInTheaterFailed();

}
