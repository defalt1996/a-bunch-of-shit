package com.defalt.a_bunch_of_shit.home.essential;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.defalt.a_bunch_of_shit.R;


public class EssentialFragment extends Fragment {

    public EssentialFragment() {
        // Required empty public constructor
    }


    public static EssentialFragment newInstance() {
        EssentialFragment fragment = new EssentialFragment();
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
        return inflater.inflate(R.layout.fragment_home_essential, container, false);
    }


}
