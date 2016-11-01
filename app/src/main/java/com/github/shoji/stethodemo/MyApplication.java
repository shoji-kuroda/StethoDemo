package com.github.shoji.stethodemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by shoji.kuroda on 2016/11/01.
 */

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}