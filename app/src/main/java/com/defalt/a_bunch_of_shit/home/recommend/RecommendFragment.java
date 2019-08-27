package com.defalt.a_bunch_of_shit.home.recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;
import com.defalt.a_bunch_of_shit.bean.douban.film.recommend_subjects.RankSubjects;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.BannerHomePageAll;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.CategoryTitleAll;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.EmptyValue;
import com.defalt.a_bunch_of_shit.home.bean.AppBean;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.BannerHomepageViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.CategoryTitleViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.RankMovieViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.RankTitleViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.TabTitleViewBinder;
import com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder.TheaterMovieViewBinder;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public class RecommendFragment extends Fragment implements RecommendContract.View, SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView recyclerView;
    private MultiTypeAdapter adapter;
    private RecommendPresenter recommendPresenter;
    private Items mItems;

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
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
        });

        adapter.register(EmptyValue.class).to(
                tabTitleViewBinder,
                new RankTitleViewBinder())
                .withClassLinker(new ClassLinker<EmptyValue>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<EmptyValue, ?>> index(int position, @NonNull EmptyValue emptyValue) {
                        if (emptyValue.type == EmptyValue.TAB_TITLE){
                            return TabTitleViewBinder.class;
                        }
                        return RankTitleViewBinder.class;
                    }
                });
        adapter.register(BannerHomePageAll.class, new BannerHomepageViewBinder());
        adapter.register(CategoryTitleAll.class, new CategoryTitleViewBinder());
        adapter.register(Subjects.class, new TheaterMovieViewBinder());
        adapter.register(RankSubjects.class, new RankMovieViewBinder());
        recyclerView.setAdapter(adapter);
        adapter.setItems(mItems);
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
