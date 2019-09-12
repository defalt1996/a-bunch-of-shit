package com.defalt.a_bunch_of_shit.home.recommend;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;
import com.defalt.a_bunch_of_shit.bean.douban.film.recommend_subjects.RankSubjects;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.Top250;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.Top250Addition;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.top250details.Top250Details;
import com.defalt.a_bunch_of_shit.bean.douban.film.us_box.Subject;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.BannerHomePageAll;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.CategoryTitleAll;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.EmptyValue;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.BannerHomepageViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.CategoryTitleViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.RankMovieViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.RankTitleViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.TabTitleViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.TheaterMovieViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.TitleViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.Top250MovieViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.UsBoxViewBinder;
import com.defalt.a_bunch_of_shit.network.api.DoubanAPI;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public class RecommendFragment extends Fragment implements RecommendContract.View, SwipeRefreshLayout.OnRefreshListener {


    private static final String TAG = "RecommendFragment";
    private RecyclerView recyclerView;
    private MultiTypeAdapter adapter;
    private RecommendPresenter recommendPresenter;
    private Items mItems;
    private List<Top250> top250List;

    public RecommendFragment() {
        // Required empty public constructor
    }


    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recommendPresenter = new RecommendPresenter(this);
        recommendPresenter.loadPage();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        recyclerView = view.findViewById(R.id.rv_recommend);

        top250List = new ArrayList<>();

        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                recommendPresenter.loadTop250Movie(0);
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });

        return view;
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void loadPageSuccessful(final Items items) {

        mItems = items;

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 12);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (items.get(position) instanceof  BannerHomePageAll){
                    return 12/1;
                }
                if (items.get(position) instanceof CategoryTitleAll){
                    return 12/1;
                }
                if (items.get(position) instanceof Subjects){
                    return 12/3;
                }
                if (items.get(position) instanceof Subject){
                    return 12/3;
                }
                if (items.get(position) instanceof RankSubjects){
                    return 12/1;
                }
                if (items.get(position) instanceof EmptyValue){
                    return 12/1;
                }

                return 12;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MultiTypeAdapter();
        //register one "EmptyValue" for multiple tile
        TabTitleViewBinder tabTitleViewBinder = new TabTitleViewBinder();
        tabTitleViewBinder.setOntabChangedListener(new TabTitleViewBinder.OntabChangedListener() {
            @Override
            public void onChanged(boolean isIntheater) {
                recommendPresenter.tabInTheaterComingSoon(isIntheater);
            }

            @Override
            public void onClickSeeAll(int type) {
                recommendPresenter.openAllCinemaMovie(type);
            }
        });

        adapter.register(EmptyValue.class).to(
                tabTitleViewBinder,
                new TitleViewBinder(),
                new RankTitleViewBinder())
                .withClassLinker(new ClassLinker<EmptyValue>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<EmptyValue, ?>> index(int position, @NonNull EmptyValue emptyValue) {
                        if (emptyValue.type == EmptyValue.TAB_TITLE){
                            return TabTitleViewBinder.class;
                        }else if (emptyValue.type == EmptyValue.USBOX_TITLE){
                            return TitleViewBinder.class;
                        }
                        return RankTitleViewBinder.class;
                    }
                });
        adapter.register(BannerHomePageAll.class, new BannerHomepageViewBinder());
        adapter.register(CategoryTitleAll.class, new CategoryTitleViewBinder());
        adapter.register(Subjects.class, new TheaterMovieViewBinder());
        adapter.register(Subject.class, new UsBoxViewBinder());
        adapter.register(Top250.class, new Top250MovieViewBinder());
        adapter.register(RankSubjects.class, new RankMovieViewBinder());
        recyclerView.setAdapter(adapter);
        adapter.setItems(mItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadTop250InMainPage(List<Subjects> top250SubjectsList) {

        for (Subjects item : top250SubjectsList){
            Top250 top250 = new Top250();
            top250.setItem(item);
            top250.setAdded(false);
            top250List.add(top250);
        }

        mItems.addAll(top250List);
        adapter.setItems(mItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadTop250AdditionInMainPage(Top250Details top250Details) {

        for (Top250 top250 : top250List){
            if ( !top250.isAdded() && top250.getItem().getId().equals( top250Details.getId())){
                Log.d(TAG, "Set top250 movie addition, top250 id= "+ top250.getItem().getId()+ "  top250Details id = " +top250Details.getId() );
                //获取3个content img
                top250.setDetails(top250Details);
                top250.setAdded(true);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void tabInTheaterComingSoon(List<Subjects> subjectsList) {
        //Replace data
        int j = 0;
        for (int i=0; i< mItems.size(); i++){
            if (mItems.get(i) instanceof Subjects){
                mItems.set(i, subjectsList.get(j));
                j ++;
            }
        }
        adapter.setItems(mItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadPageFailed() {

    }

    @Override
    public void showAllInTheaterMovie() {

    }

    @Override
    public void showAllCommingSoonMovie() {

    }

    @Override
    public void showBanner() {

    }

    @Override
    public void showMovieCategory() {

    }

    @Override
    public void showMovieList() {

    }

    @Override
    public void showMovieDetails() {

    }

    @Override
    public void showRecommend() {

    }
}
