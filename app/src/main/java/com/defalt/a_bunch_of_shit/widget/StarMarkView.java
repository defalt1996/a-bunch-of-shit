package com.defalt.a_bunch_of_shit.widget;
/*
 *  author: Derrick
 *  Time: 2019/8/26 0:50
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class StarMarkView extends View {

    private int mark;

    public StarMarkView(Context context) {
        super(context);
    }

    public StarMarkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StarMarkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }



}
