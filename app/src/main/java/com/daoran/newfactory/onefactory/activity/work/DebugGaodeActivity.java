package com.daoran.newfactory.onefactory.activity.work;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import java.io.File;
import java.net.URISyntaxException;

/**
 * 路线规划（调用高德地图）
 * Created by lizhipeng on 2017/4/14.
 */

public class DebugGaodeActivity extends
        BaseFrangmentActivity {
    private static final String TAG = "DebugGaodeActivity";
    //--------------以下都是高德坐标系的坐标-------30.324328,120.188428------------//
    private static final double LATITUDE_A = 28.1903;  //起点纬度
    private static final double LONGTITUDE_A = 113.031738;  //起点经度

    private static final double LATITUDE_B = 30.324328;  //终点纬度
    private static final double LONGTITUDE_B = 120.188428;  //终点经度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_gaode);
        setUpGaodeAppByMine();
    }

    /**
     * 调用高德地图
     */
    private void setUpGaodeAppByMine(){
        try {
            Intent intent = Intent.getIntent(
                    "androidamap://route?sourceApplication=softname&sname=我的位置&dlat="
                    +LATITUDE_B+"&dlon="+LONGTITUDE_B+"&dname="+"杭州道然进出口有限公司"+"&dev=0&m=0&t=1");
            if(isInstallByread("com.autonavi.minimap")){
                startActivity(intent);
                Log.e(TAG, "高德地图客户端已经安装") ;
                finish();
            }else {
                Log.e(TAG, "没有安装高德地图客户端") ;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否安装目标应用
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }
}
