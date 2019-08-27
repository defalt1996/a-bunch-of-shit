package com.defalt.a_bunch_of_shit.home.recommend;
/*
 *  author: Derrick
 *  Time: 2019/6/13 14:57
 */

import androidx.annotation.NonNull;

import com.defalt.a_bunch_of_shit.bean.douban.film.BannerHomePage;
import com.defalt.a_bunch_of_shit.bean.douban.film.Root;
import com.defalt.a_bunch_of_shit.bean.douban.film.CategoryTitle;
import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.BannerHomePageAll;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.CategoryTitleAll;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.EmptyValue;
import com.defalt.a_bunch_of_shit.network.Network;
import com.defalt.a_bunch_of_shit.network.api.DoubanAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;

public class RecommendPresenter implements RecommendContract.Presenter {

    private RecommendContract.View mHomePageView;
    private boolean isInTheater;
    private  Items items;
    private List<Subjects> inTheaterItemList;


    public RecommendPresenter(@NonNull RecommendContract.View homepage) {
        mHomePageView = homepage;
    }

    @Override
    public void loadPage() {
        Observable<Root> observable1 = Network.getDoubanAPI().getMovieInTheaters("北京", DoubanAPI.apikey, 0, 6);
//        Observable<Root> observable2 = Network.getDoubanAPI().getMovieComingSoon(DoubanAPI.apikey, 0, 6);
        Observable<Root> observable3 = Network.getDoubanAPI().getMovieWeekly(DoubanAPI.apikey);

        Disposable disposable = Observable.zip(observable1, observable3, new BiFunction<Root, Root, Items>() {
            @Override
            public Items apply(Root root, Root root3) throws Exception {
                items = new Items();
                //Generate category title and Banner
                BannerHomePageAll bannerHomePageAll = new BannerHomePageAll();
                List<BannerHomePage> bannerHomePageList = new ArrayList<>();
                bannerHomePageList.add(new BannerHomePage("找电影", "http://img.defalt.top/img/home_banner.jpg"));
                bannerHomePageList.add(new BannerHomePage("影", "http://img.defalt.top/img/home_banner.jpg"));
                bannerHomePageAll.setBannerHomePageList(bannerHomePageList);

                CategoryTitleAll categoryTitleAll = new CategoryTitleAll();

                items.add(bannerHomePageAll);
                items.add(categoryTitleAll);
                items.add(new EmptyValue(EmptyValue.TAB_TITLE, root.getTotal()));

                //TODO: 重构缓存inTheater
                inTheaterItemList = root.getSubjects();
                items.addAll(root.getSubjects());
//                items.add(new EmptyValue(EmptyValue.TAB_TITLE, root2.getTotal()));
//                items.addAll(root2.getSubjects());
//                items.addAll(root3.getRankSubjects());

                return items;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Items>() {
                    @Override
                    public void accept(Items items) throws Exception {
                        mHomePageView.loadPageSuccessful(items);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void tabInTheaterComingSoon(boolean isInTheater) {
        if (!isInTheater){
            Observable<Root> observable2 = Network.getDoubanAPI().getMovieComingSoon(DoubanAPI.apikey, 0, 6);

            Disposable disposable = observable2.map(new Function<Root, List<Subjects>>() {
                @Override
                public List<Subjects> apply(Root root) throws Exception {
                    return root.getSubjects();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Subjects>>() {
                        @Override
                        public void accept(List<Subjects> subjects) throws Exception {
                            mHomePageView.tabInTheaterComingSoon(subjects);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });
        }else {
            mHomePageView.tabInTheaterComingSoon(inTheaterItemList);
        }

    }


    @Override
    public void openAllInTheaterMovie() {

    }

    @Override
    public void openAllCommingSoonMovie() {

    }

    @Override
    public void openBanner() {

    }

    @Override
    public void openMovieCategory() {

    }

    @Override
    public void openMovieList() {

    }

    @Override
    public void openMovieDetails() {

    }

    @Override
    public void openRecommend() {

    }
}
