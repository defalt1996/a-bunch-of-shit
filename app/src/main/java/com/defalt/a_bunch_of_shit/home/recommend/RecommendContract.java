package com.defalt.a_bunch_of_shit.home.recommend;
/*
 *  author: Derrick
 *  Time: 2019/6/13 14:58
 */

import android.view.View;

import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;

import java.util.List;

import me.drakeet.multitype.Items;

public interface RecommendContract {

    int SEE_ALL_IN_THEATER = 1;
    int SEE_ALL_COMING_SOON = 2;
    int SEE_ALL_USBOX = 3;
    int SEE_ALL_RANK = 4;

    interface View {

        void loadPageSuccessful(Items items);

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

//        void openAllInTheaterMovie();
        void openAllCinemaMovie( int type);
//        void openAllCommingSoonMovie();

        void openBanner();

        void openMovieCategory();

        void openMovieList();

        void openMovieDetails();

        void openRecommend();




    }


}
