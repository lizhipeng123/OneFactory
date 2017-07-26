package com.daoran.newfactory.onefactory.util.application;

import android.app.Application;

import com.daoran.newfactory.onefactory.util.CrashHandler;

/**
 * 初始化操作
 * Created by lizhipeng on 2017/7/18.
 */

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
