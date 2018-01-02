package com.daoran.newfactory.onefactory.util.application;

import android.content.Context;
import android.os.Debug;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.daoran.newfactory.onefactory.util.exception.UEHandler;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import java.io.File;

import cn.jpush.android.api.JPushInterface;

/**
 * 初始化操作（使得分包情况下可以正常运行程序）
 * 进行分享操作之类
 * Created by lizhipeng on 2017/7/18.
 */

public class CrashApplication extends MultiDexApplication {
    private static final String TAG = "JIGUANG-Example";
    /** "/data/data/<app_package>/files/error.log" */
    public static final String PATH_ERROR_LOG = File.separator + "data" + File.separator + "data"
            + File.separator + "com.daoran.newfactory.onefactory" + File.separator + "files" + File.separator
            + "error.log";
    /** 标识是否需要退出。为true时表示当前的Activity要执行finish()。 */
    private boolean need2Exit;
    /** 异常处理类。 */
    private UEHandler ueHandler;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Debug.startMethodTracing("OneFactory");
        Bugly.init(this, "52c746d40d", false);//腾讯分享口令
        JPushInterface.setDebugMode(true);//设置开启日志，发布时关闭日志
        JPushInterface.init(this);//初始化JPush
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
        need2Exit = false;
        ueHandler = new UEHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(ueHandler);
        Debug.stopMethodTracing();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

        //安装tinker
        Beta.installTinker();
    }

    public void setNeed2Exit(boolean bool) {
        need2Exit = bool;
    }

    public boolean need2Exit() {
        return need2Exit;
    }
}
