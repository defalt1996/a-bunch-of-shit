package com.defalt.a_bunch_of_shit;

import android.os.Bundle;
import android.widget.FrameLayout;


import com.defalt.a_bunch_of_shit.base.BaseActivity;
import com.defalt.a_bunch_of_shit.base.baseview.MainFragment;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity {

    @BindView(R.id.content_layout)
    public FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.addLogAdapter(new AndroidLogAdapter());

        //加载fragment
        if (findFragment(MainFragment.class) == null){
            loadRootFragment(R.id.content_layout, MainFragment.newInstance());
        }
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
}
