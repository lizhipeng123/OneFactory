package com.daoran.newfactory.onefactory.util.application;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.daoran.newfactory.onefactory.bean.UsergetBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PreferencesUtils;
import com.daoran.newfactory.onefactory.util.settings.Comfig;
import com.google.gson.Gson;

import java.io.File;

/**
 * 初始化个人信息
 * Created by lizhipeng on 2017/3/24.
 */

public class CLApplication extends Application {

    public static Context applicationContext;
    public static CLApplication instantce;

    public static File cacheFile;
    private UsergetBean user;

    //相册路径
    public static final String photoPath = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera";

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instantce = this;
        cacheFile = new File(this.getExternalCacheDir().getPath());
    }

    //单独获取个人信息的存取
    public synchronized void setUser(UsergetBean userBean) {
        if (null == userBean) {
            PreferencesUtils.putString(this, Comfig.USERINFO, "");
        } else {
            user = userBean;
            PreferencesUtils.putString(this, Comfig.USERINFO, new Gson().toJson(user));
        }
    }

    public UsergetBean getUser() {
        if (null == user) {
            try {
                user = new Gson().fromJson(PreferencesUtils.getString(this, Comfig.USERINFO), UsergetBean.class);
            } catch (Exception e) {
                user = new UsergetBean();
            }
        }
        return user;
    }
}
