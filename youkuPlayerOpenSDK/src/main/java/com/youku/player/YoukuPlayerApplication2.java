package com.youku.player;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.*;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;

import com.baseproject.utils.Logger;
import com.baseproject.utils.Profile;
import com.youku.analytics.data.Device;
import com.youku.analytics.utils.Tools;
import com.youku.player.module.ApiServiceParamsInfo;
import com.youku.player.service.VersionUpdateTask;
import com.youku.player.util.MediaPlayerProxyUtil;
import com.youku.player.util.PlayerUiUtile;
import com.youku.player.util.PlayerUtil;
import com.youku.vo.UserProfile;

/**
 * Created by Steven on 15/5/26.
 */
public abstract class YoukuPlayerApplication2 extends  android.support.multidex.MultiDexApplication  {
    private static final String TAG = YoukuPlayerApplication2.class.getSimpleName();
    private static SharedPreferences s;
    private static SharedPreferences.Editor e;
    public static final int FORMAT_MP4 = 1;
    public static final int FORMAT_3GPHD = 4;
    public static final int FORMAT_FLV = 5;
    public static final int FORMAT_HD2 = 7;
    public static final int TIMEOUT = 30000;
    public static boolean isHighEnd;
    public static boolean isMyYoukuNeedRefresh = false;
    public static ApiServiceParamsInfo apiServiceParamsInfo;
    public static Context context;
    public static String User_Agent;
    public static String versionName;
    public static String COOKIE = null;
    public static String userName = "";
    public static boolean isLogined = false;
    public static String loginAccount = null;
    public static String userId = "";
    public static UserProfile userprofile;
    public static Activity loginActivity;
    public static boolean isTablet;
    public static YoukuPlayerApplication2 instance;
    public static String client_secret = "";
    private static String DOWNLOAD_PATH = "";
    private static final int VERSION_CODE = 15011220;
    private static BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("login_broadcast")) {
                Logger.d(YoukuPlayerApplication2.TAG, "login_broadcast");
                YoukuPlayerApplication2.COOKIE = YoukuPlayerApplication2.s.getString("cookie", "");
                YoukuPlayerApplication2.isLogined = YoukuPlayerApplication2.s.getBoolean("isLogined", false);
                YoukuPlayerApplication2.userName = YoukuPlayerApplication2.s.getString("userName", "");
                YoukuPlayerApplication2.userId = YoukuPlayerApplication2.s.getString("uid", "");
                YoukuPlayerApplication2.apiServiceParamsInfo.setCookie(YoukuPlayerApplication2.COOKIE);
                YoukuPlayerApplication2.apiServiceParamsInfo.setLogin(YoukuPlayerApplication2.isLogined);
                YoukuPlayerApplication2.apiServiceParamsInfo.setUserId(YoukuPlayerApplication2.userId);
            } else if(action.equals("logout_broadcast")) {
                Logger.d(YoukuPlayerApplication2.TAG, "logout_broadcast");
                YoukuPlayerApplication2.COOKIE = YoukuPlayerApplication2.s.getString("cookie", "");
                YoukuPlayerApplication2.isLogined = YoukuPlayerApplication2.s.getBoolean("isLogined", false);
                YoukuPlayerApplication2.userName = YoukuPlayerApplication2.s.getString("userName", "");
                YoukuPlayerApplication2.apiServiceParamsInfo.setCookie("");
                YoukuPlayerApplication2.apiServiceParamsInfo.setLogin(false);
                YoukuPlayerApplication2.apiServiceParamsInfo.setUserId("");
            }
        }
    };

    public YoukuPlayerApplication2() {
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this.getApplicationContext();
        PlayerUiUtile.initDex(context);
        Profile.DEBUG = false;
        Profile.LOG = false;
        Device.imei = Tools.getIMEI(context);
        Device.mac = Tools.getMacAddress(context);
        Device.guid = Tools.getGUID(context);
        Device.gdid = Tools.getGDID(context);
        Profile.mContext = context;
        s = context.getSharedPreferences(context.getPackageName() + "_preferences", 0);
        e = s.edit();
        DOWNLOAD_PATH = this.configDownloadPath();

        try {
            versionName = this.getPackageManager().getPackageInfo(this.getPackageName(), 128).versionName;
        } catch (PackageManager.NameNotFoundException var3) {
            var3.printStackTrace();
        }

        if(MediaPlayerProxyUtil.isUplayerSupported()) {
            isHighEnd = true;
            savePreference("isSoftwareDecode", Boolean.valueOf(true));
            com.youku.player.goplay.Profile.setVideoType_and_PlayerType(5, context);
        } else {
            com.youku.player.goplay.Profile.setVideoType_and_PlayerType(4, context);
        }

        User_Agent = "Youku;" + versionName + ";Android;" + Build.VERSION.RELEASE + ";" + Build.MODEL;
        isTablet = (context.getResources().getConfiguration().screenLayout & 15) >= 3;
        COOKIE = s.getString("cookie", "");
        isLogined = s.getBoolean("isLogined", false);
        userName = s.getString("userName", "");
        userId = s.getString("uid", "");
        apiServiceParamsInfo = new ApiServiceParamsInfo(isLogined, COOKIE, User_Agent);
        apiServiceParamsInfo.setUserId(userId);
        String client_id = PlayerUtil.getClientId(context);
        apiServiceParamsInfo.setClientId(client_id);
        apiServiceParamsInfo.setClientSecret(PlayerUtil.getClientSecret(context));
        apiServiceParamsInfo.setOpenSdkVersion(String.valueOf(15011220));
        if(PlayerUtil.hasInternet(context)) {
            VersionUpdateTask filter = new VersionUpdateTask(client_id, 15011220);
            filter.execute(new Void[0]);
        }

        IntentFilter filter1 = new IntentFilter();
        filter1.addAction("login_broadcast");
        filter1.addAction("logout_broadcast");
        context.registerReceiver(receiver, filter1);
    }

    public static void exit() {
        android.os.Process.killProcess(Process.myPid());
        context.unregisterReceiver(receiver);
    }

    public static String getDownloadPath() {
        if(!TextUtils.isEmpty(DOWNLOAD_PATH)) {
            if(!DOWNLOAD_PATH.startsWith("/")) {
                DOWNLOAD_PATH = "/" + DOWNLOAD_PATH;
            }

            if(DOWNLOAD_PATH.endsWith("/")) {
                DOWNLOAD_PATH = DOWNLOAD_PATH + "/";
            }
        } else {
            String pkg = context.getPackageName();
            DOWNLOAD_PATH = "/" + pkg + "/videocache/";
        }

        Logger.d(TAG, "download_path: " + DOWNLOAD_PATH);
        return DOWNLOAD_PATH;
    }

    public static String getPreference(String key) {
        return s.getString(key, "");
    }

    public static void savePreference(String key, String value) {
        e.putString(key, value).commit();
    }

    public static void savePreference(String key, int value) {
        e.putInt(key, value).commit();
    }

    public static void savePreference(String key, Boolean value) {
        e.putBoolean(key, value.booleanValue()).commit();
    }

    public static boolean getPreferenceBoolean(String key) {
        return s.getBoolean(key, false);
    }

    public static String getPreference(String key, String def) {
        return s.getString(key, def);
    }

    public static int getPreferenceInt(String key) {
        return s.getInt(key, 0);
    }

    public static int getPreferenceInt(String key, int def) {
        return s.getInt(key, def);
    }

    public static boolean getPreferenceBoolean(String key, boolean def) {
        return s.getBoolean(key, def);
    }

    public static void showTips(String tips) {
        Toast.makeText(context, tips, Toast.LENGTH_SHORT).show();
    }

    public static void showTips(int id) {
        Toast.makeText(context, context.getResources().getString(id), Toast.LENGTH_SHORT).show();
    }

    public static void saveClientIdBase64Format(String client_id) {
        String encodedClientId = Base64.encodeToString(client_id.getBytes(), 2);
        Logger.d(TAG, "encoded client_id: " + encodedClientId);
        savePreference("encoded_client_id", encodedClientId);
    }

    public static String getBase64FormatClientId() {
        return getPreference("encoded_client_id");
    }

    public abstract int getNotifyLayoutId();

    public abstract Class<? extends Activity> getCachingActivityClass();

    public abstract Class<? extends Activity> getCachedActivityClass();

    public abstract String configDownloadPath();
}
