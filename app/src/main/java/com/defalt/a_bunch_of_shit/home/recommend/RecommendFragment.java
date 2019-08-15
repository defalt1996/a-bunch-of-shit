package com.defalt.a_bunch_of_shit.home.recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;
import com.defalt.a_bunch_of_shit.home.bean.AppBean;

import java.util.ArrayList;
import java.util.List;


public class RecommendFragment extends Fragment implements RecommendContract, SwipeRefreshLayout.OnRefreshListener {

    private RecommendRVAdapter recommendRVAdapter;
    private RecyclerView recyclerView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        recyclerView = view.findViewById(R.id.rv_recommend);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<AppBean> appBeanList = new ArrayList<>();
        for (int i=0; i<10; i++){
            AppBean bean = new AppBean();
            bean.setName("lallala");
            bean.setGrade("123");
            bean.setShortInfo("asdfasdfadfadsfasdf");
            appBeanList.add(bean);
        }

        recommendRVAdapter = new RecommendRVAdapter(getActivity(), appBeanList);
        recyclerView.setAdapter(recommendRVAdapter);

        return view;
    }

    @Override
    public void getMovieInTheaterSuccess(List<Subjects> subjects) {

    }

    @Override
    public void getMovieInTheaterFailed() {

    }

    @Override
    public void onRefresh() {

    }
}
