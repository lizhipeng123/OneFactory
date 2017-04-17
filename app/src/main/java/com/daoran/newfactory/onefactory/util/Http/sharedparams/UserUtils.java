package com.daoran.newfactory.onefactory.util.Http.sharedparams;

import android.content.SharedPreferences;

import com.daoran.newfactory.onefactory.util.application.MyApplication;

import static com.daoran.newfactory.onefactory.util.application.MyApplication.getAppContext;

/**
 * Created by lizhipeng on 2017/4/10.
 */

public class UserUtils {

    public static String getUserInfo() {
        SharedPreferences pref = getAppContext().getSharedPreferences(
                "user_login", 0);
        return pref.getString("", "");
    }

    public static boolean saveUserInfo(String info) {
        SharedPreferences pref = getAppContext().getSharedPreferences(
                "user_login", 0);
        return pref.edit().putString("user_info", info).commit();
    }
}
