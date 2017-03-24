package com.daoran.newfactory.onefactory.util.Http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by lizhipeng on 2017/3/23.
 */

public class NetWork {
    /**
     * 判断当前网络（WiFi，2G/3G）状态
     * @param context
     * @return true表示可用
     */
    public static boolean isNetWorkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null){
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if(info!=null&&info.isConnected()){
                if(info.getState()==NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }

        return false;
    }
}
