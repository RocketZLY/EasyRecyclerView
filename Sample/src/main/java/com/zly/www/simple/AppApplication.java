package com.zly.www.simple;

import android.app.Application;

/**
 * Created by zly on 2016/12/2 0002.
 */

public class AppApplication extends Application {

    private static AppApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static AppApplication getInstance(){
        return mApplication;
    }
}
