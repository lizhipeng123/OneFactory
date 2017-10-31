package com.daoran.newfactory.onefactory.activity.work;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * 路线规划（调用高德地图）
 * Created by lizhipeng on 2017/4/14.
 */

public class GaodeRouteActivity extends
        BaseFrangmentActivity {
    private static final String TAG = "GaodeRouteActivity";
    //--------------以下都是高德坐标系的坐标-------30.324328,120.188428------------//
    private static final double LATITUDE_A = 28.1903;  //起点纬度
    private static final double LONGTITUDE_A = 113.031738;  //起点经度
    /**
     * 当前位置
     */
    private static double[] START_LATLON = {120.11649, 30.272873};
    /**
     * 目的地
     */
    private static double[] DESTINATION_TA_LATLON = {120.156132, 30.237626};
    private static final double LATITUDE_B = 30.324328;  //终点纬度
    private static final double LONGTITUDE_B = 120.188428;  //终点经度

    private static String APP_NAME = "OneFactory";
    private String SNAME = "起点";
    private String DNAME = "终点";
    private String CITY = "杭州";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaodemap);
        if (isAvilible(this, "com.baidu.BaiduMap")) {
            ToastUtils.ShowToastMessage("即将用百度地图打开导航", this);
            Uri mUri = Uri.parse("geo:" + LATITUDE_A + "," + LONGTITUDE_A + "?q=" + "杭州道然进出口有限公司");
            Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
            startActivity(mIntent);
        } else if (isAvilible(this, "com.autonavi.minimap")) {
            ToastUtils.ShowToastMessage("即将用高德地图打开导航", this);
            try {
                Intent intent = Intent.getIntent(
                        "androidamap://route?sourceApplication=softname&sname=我的位置&dlat="
                                + LATITUDE_B + "&dlon=" + LONGTITUDE_B + "&dname="
                                + "杭州道然进出口有限公司" + "&dev=0&m=0&t=1");
                if (isInstallByread("com.autonavi.minimap")) {
                    startActivity(intent);
                    Log.e(TAG, "高德地图客户端已经安装");
                    finish();
                } else {
                    Log.e(TAG, "没有安装高德地图客户端");
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            String act = "android.intent.action.VIEW";
            Uri dat = Uri.parse("http://m.amap.com/navigation/walkmap/?k=杭州道然进出口有限公司");
            Intent intent = new Intent(act, dat);
            startActivity(intent);
            finish();
        }
    }

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    private boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
}