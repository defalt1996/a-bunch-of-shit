package com.defalt.a_bunch_of_shit.home.recommend;
/*
 *  author: Derrick
 *  Time: 2019/6/13 14:57
 */

import android.util.Log;

import androidx.annotation.NonNull;

import com.defalt.a_bunch_of_shit.bean.douban.film.BannerHomePage;
import com.defalt.a_bunch_of_shit.bean.douban.film.Root;
import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.Top250Addition;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.top250details.Top250Details;
import com.defalt.a_bunch_of_shit.bean.douban.film.us_box.RootUsBox;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.BannerHomePageAll;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.CategoryTitleAll;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.EmptyValue;
import com.defalt.a_bunch_of_shit.network.Network;
import com.defalt.a_bunch_of_shit.network.api.DoubanAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.Items;

public class RecommendPresenter implements RecommendContract.Presenter {

    private static final String TAG = "RecommendPresenter";
    private RecommendContract.View mHomePageView;
    private boolean isInTheater;
    private  Items items;
    private List<Subjects> inTheaterItemList;

    public RecommendPresenter(@NonNull RecommendContract.View homepage) {
        mHomePageView = homepage;
    }

    //加载基本首页
    @Override
    public void loadPage() {
        Observable<Root> observable1 = Network.getDoubanAPI().getMovieInTheaters("北京", DoubanAPI.apikey, 0, 6);
//        Observable<RootUsBox> observable2 = Network.getDoubanAPI().getMovieComingSoon(DoubanAPI.apikey, 0, 6);
//        Observable<RootUsBox> observable3 = Network.getDoubanAPI().getMovieWeekly(DoubanAPI.apikey);
        Observable<RootUsBox> observable2 = Network.getDoubanAPI().getMovieUSBox(DoubanAPI.apikey, 0, 6);

        Disposable disposable = Observable.zip(observable1, observable2, new BiFunction<Root, RootUsBox, Items>() {
            @Override
            public Items apply(Root root, RootUsBox root2) throws Exception {
                items = new Items();
                //Generate category title and Banner
                BannerHomePageAll bannerHomePageAll = new BannerHomePageAll();
                List<BannerHomePage> bannerHomePageList = new ArrayList<>();
                bannerHomePageList.add(new BannerHomePage("找电影", "http://img.defalt.top/img/home_banner.jpg"));
                bannerHomePageList.add(new BannerHomePage("影", "http://img.defalt.top/img/home_banner.jpg"));
                bannerHomePageAll.setBannerHomePageList(bannerHomePageList);
                CategoryTitleAll categoryTitleAll = new CategoryTitleAll();
                inTheaterItemList = root.getSubjects();

                // Top Banner
                items.add(bannerHomePageAll);

                items.add(categoryTitleAll);
                items.add(new EmptyValue(EmptyValue.TAB_TITLE,"", root.getTotal()));
                //TODO: 重构缓存inTheater
                items.addAll(inTheaterItemList);
                items.add(new EmptyValue(EmptyValue.USBOX_TITLE, "北美榜单", root2.getSubjects().size()));
                for (int i = 0; i<6 ; i++){
                    items.add(root2.getSubjects().get(i).getSubject());
                }
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

    // tab --> 切换到即将上映
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

    //
    @Override
    public void loadTop250Movie(int page) {
        int count = 20;
        int start = page * count;
        Observable<Root> observableTop250List = Network.getDoubanAPI().getMovieTop250(DoubanAPI.apikey, start, count);

        Disposable disposable = observableTop250List.map(new Function<Root, List<Subjects>>() {
            @Override
            public List<Subjects> apply(Root root) throws Exception {
                return root.getSubjects();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Subjects>>() {
                    @Override
                    public void accept(List<Subjects> subjects) throws Exception {

                        mHomePageView.loadTop250InMainPage(subjects);
                        // 继续添加 内容图banner 以及 最热评论
                        for (Subjects item : subjects){
                            loadTop250AdditionsInMainPage(item.getId());

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }


    public void loadTop250AdditionsInMainPage(final String movieId){

        Observable<Top250Details> observableTop250 = Network.getDoubanAPI().getMovieTop250Detail(movieId, DoubanAPI.apikey);
        Disposable disposable = observableTop250
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Top250Details>() {
                    @Override
                    public void accept(Top250Details top250Details) throws Exception {
                        mHomePageView.loadTop250AdditionInMainPage(top250Details);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "exception id: " + movieId);
                        throwable.printStackTrace();
                    }
                });
    }


    // 查看全部影片 （正在上映/即将上映 type）
    @Override
    public void openAllCinemaMovie(int type) {

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
