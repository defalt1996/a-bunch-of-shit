package com.defalt.a_bunch_of_shit.home.recommend;
/*
 *  author: Derrick
 *  Time: 2019/6/13 14:57
 */

import com.defalt.a_bunch_of_shit.bean.douban.film.Root;
import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;
import com.defalt.a_bunch_of_shit.network.Network;
import com.defalt.a_bunch_of_shit.network.api.DoubanAPI;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RecommendPresenter {

    public void getMovieInTheater(int page){
        int count = 20;
        int start = page * count;
        Disposable disposable = Network.getDoubanAPI().getMovieInTheaters("北京", DoubanAPI.apikey, start, 20)
                .map(new Function<Root, List<Subjects>>() {
                    @Override
                    public List<Subjects> apply(Root root) throws Exception {
                        return root.getSubjects();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Subjects>>() {
                    @Override
                    public void accept(List<Subjects> subjects) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
