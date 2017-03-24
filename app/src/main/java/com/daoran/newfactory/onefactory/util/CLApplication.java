package com.daoran.newfactory.onefactory.util;

import android.app.Application;
import android.content.Context;

import com.daoran.newfactory.onefactory.bean.UsergetBean;

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

    public UsergetBean getUser() {

        return user;
    }

}
