package com.defalt.a_bunch_of_shit.home.recommend;
/*
 *  author: Derrick
 *  Time: 2019/6/13 14:58
 */

import android.view.View;

import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.top250details.Top250Details;

import java.util.List;

import me.drakeet.multitype.Items;

public interface RecommendContract {

    int SEE_ALL_IN_THEATER = 1;
    int SEE_ALL_COMING_SOON = 2;
    int SEE_ALL_USBOX = 3;
    int SEE_ALL_RANK = 4;

    interface View {

        void loadPageSuccessful(Items items);

        void loadTop250InMainPage(List<Subjects> top250List);

        void loadTop250AdditionInMainPage(Top250Details top250Details);

        void tabInTheaterComingSoon(List<Subjects> subjectsList);

        void loadPageFailed();

        void showAllInTheaterMovie();

        void showAllCommingSoonMovie();

        void showBanner();

        void showMovieCategory();

        void showMovieList();

        void showMovieDetails();

        void showRecommend();
    }

    interface Presenter {

        void loadPage();

        void tabInTheaterComingSoon(boolean isInTheater);

        void loadTop250Movie(int page);

        void openAllCinemaMovie( int type);

        void openBanner();

        void openMovieCategory();

        void openMovieList();

        void openMovieDetails();

        void openRecommend();




    }


}
