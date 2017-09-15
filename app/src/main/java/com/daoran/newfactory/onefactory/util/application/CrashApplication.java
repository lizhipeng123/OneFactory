package com.daoran.newfactory.onefactory.util.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.daoran.newfactory.onefactory.util.application.settings.CrashHandler;

/**
 * 初始化操作
 * Created by lizhipeng on 2017/7/18.
 */

public class CrashApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
