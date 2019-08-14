package com.defalt.a_bunch_of_shit.base.baseview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;


import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.base.baseview.bottombar.BottomBar;
import com.defalt.a_bunch_of_shit.base.baseview.bottombar.BottomBarTab;
import com.defalt.a_bunch_of_shit.billboard.BillboardMainFragment;
import com.defalt.a_bunch_of_shit.event.TabSelectedEvent;
import com.defalt.a_bunch_of_shit.game.GameMainFragment;
import com.defalt.a_bunch_of_shit.home.HomeMainFragment;

import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;


public class MainFragment extends SupportFragment {

    private static final int REQ_MSG = 10;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FORTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[3];
    private BottomBar mBottomBar;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment homeMainFragment = findChildFragment(HomeMainFragment.class);
        if (homeMainFragment == null){
            mFragments[FIRST] = HomeMainFragment.newInstance();
            mFragments[SECOND] = GameMainFragment.newInstance();
            mFragments[THIRD] = BillboardMainFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST, mFragments[FIRST], mFragments[SECOND], mFragments[THIRD]);
        }else {
            mFragments[FIRST] = homeMainFragment;
            mFragments[SECOND] = findChildFragment(GameMainFragment.class);
            mFragments[THIRD] = findChildFragment(BillboardMainFragment.class);
        }
    }

    private void initView(View view){
        mBottomBar = view.findViewById(R.id.bottom_bar);
        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_home, "home"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_game, "game"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_billboard, "billboard"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_planet, "plant"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_me, "me"));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }
            @Override
            public void onTabNotSelected(int position) {

            }
            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                EventBusActivityScope.getDefault(_mActivity).post(new TabSelectedEvent(position));
            }
        });
    }



    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MSG && resultCode == RESULT_OK){

        }
    }
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }

}
