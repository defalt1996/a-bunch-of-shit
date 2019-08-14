package com.defalt.a_bunch_of_shit.game;
/*
 *  author: Derrick
 *  Time: 2019/7/10 18:01
 */

import android.os.Bundle;

import me.yokeyword.fragmentation.SupportFragment;

public class GameMainFragment extends SupportFragment {

    public static GameMainFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GameMainFragment fragment = new GameMainFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
