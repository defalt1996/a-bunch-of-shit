package com.defalt.a_bunch_of_shit.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class BaseActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        //因为需要在setContentView之后bind Butterknife，所以重写加入bind
        ButterKnife.bind(this);
    }

}
