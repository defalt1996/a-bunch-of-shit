package com.defalt.a_bunch_of_shit.base;
/*
 *  author: Derrick
 *  Time: 2019/8/27 21:36
 */

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
