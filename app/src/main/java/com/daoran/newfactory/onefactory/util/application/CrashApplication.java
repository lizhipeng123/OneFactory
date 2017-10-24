package com.daoran.newfactory.onefactory.util.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.daoran.newfactory.onefactory.util.application.settings.CrashHandler;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * 初始化操作（使得分包情况下可以正常运行程序）
 * 进行分享操作之类
 * Created by lizhipeng on 2017/7/18.
 */

public class CrashApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Bugly.init(this, "52c746d40d", false);//腾讯分享口令
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

        //安装tinker
        Beta.installTinker();
    }
}
