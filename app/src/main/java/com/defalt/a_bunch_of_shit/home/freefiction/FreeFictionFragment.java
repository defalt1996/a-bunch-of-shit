package com.defalt.a_bunch_of_shit.home.freefiction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.defalt.a_bunch_of_shit.R;


public class FreeFictionFragment extends Fragment {


    public FreeFictionFragment() {
        // Required empty public constructor
    }


    public static FreeFictionFragment newInstance() {
        FreeFictionFragment fragment = new FreeFictionFragment();
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
        return inflater.inflate(R.layout.fragment_home_recommend, container, false);
    }

}
