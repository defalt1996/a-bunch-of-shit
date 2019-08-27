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

        void openAllInTheaterMovie();

        void openAllCommingSoonMovie();

        void openBanner();

        void openMovieCategory();

        void openMovieList();

        void openMovieDetails();

        void openRecommend();




    }


}
