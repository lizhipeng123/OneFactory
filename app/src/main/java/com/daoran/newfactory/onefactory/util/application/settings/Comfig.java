package com.daoran.newfactory.onefactory.util.application.settings;


import com.daoran.newfactory.onefactory.R;

/**
 *  手动配置
 * Created by lizhipeng on 2017/3/21.
 */

public class Comfig {

    /**
     *  记录是否自动登录app
     */
    public static final String AUTO_LOGIN_WHRN_LAUNCH = "auto_login";
    /**
     * 记录是否记住密码
     */
    public static final String REMEMBER_PASSWORD = "remember_pws";
    /**
     * token值
     */
    public static final String TOKEN = "token";
    public static String USERINFO = "userinfo";
    public static final String USERNAME = "Login";//账号
    public static final String PASSWORD = "pwd";//密码

    /**
     * 是否开启打印输出 true 开启打印输出 false 关闭打印输出,发布设置为flase
     */
    public static final boolean isDebug = false;
    /*发布设置为true*/
    public static final boolean isLine = false;
    /**
     * 获取服务器
     *
     * @return 服务器地址的Id
     */
    public static final String getServer() {
        String serverIp = isLine ? "服务器地址" : "服务器地址ip";
        return serverIp;
    }

    public static String key = "token值";

    public static int[] getGuiderResIds() {//首次进入的引导页
        return new int[]{R.drawable.welcome_01, R.drawable.welcome_02, R.drawable.welcome_03};
    }

    public static final String OTHER_FIRST_LETTER = "#";
}
