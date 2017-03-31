package com.daoran.newfactory.onefactory.activity.work;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.ToastUtils;

import java.util.List;

/**
 * 外勤签到
 * Created by lizhipeng on 2017/3/31.
 */

public class SignActivity extends BaseFrangmentActivity implements View.OnClickListener, BDLocationListener {

    private MapView mapView = null;
    private BaiduMap mBaidumap;
    private boolean isFirstLoc = true;

    private Button btnCount, btnSignCancle, btnSignOk;
    private TextView tvSignAddress, tvSignDate;
    private Spinner SpinnerSign;
    private EditText etRemark;
    private ImageView ivSignBack;


    private LocationClient mLocationClient = null;

    private int year, month, date, hour, minute, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);
        setContentView(R.layout.activity_sign);
        getViews();
        initViews();
        mBaidumap = mapView.getMap();
        mBaidumap.setMyLocationEnabled(true);
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
        mLocationClient.start();
        setListener();
    }

    private void initViews() {
        ivSignBack.setOnClickListener(this);
        btnCount.setOnClickListener(this);
        LocationClientOption locOption = new LocationClientOption();
        locOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        locOption.setCoorType("bd09ll");// 设置定位结果类型
        int span = 1000;
        locOption.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        locOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        locOption.setOpenGps(true);//可选，默认false,设置是否使用gps
        locOption.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locOption.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        locOption.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(locOption);
        tvSignDate.setText(String.valueOf(year + "/" + month + "/" + date));
        etRemark.setOnClickListener(this);
//        tvSignAddress.setText();
    }

    private void getViews() {
        ivSignBack = (ImageView) findViewById(R.id.ivSignBack);
        mapView = (MapView) findViewById(R.id.mapSign);
        btnCount = (Button) findViewById(R.id.btnCount);
        btnSignCancle = (Button) findViewById(R.id.btnSignCancle);
        btnSignOk = (Button) findViewById(R.id.btnSignOk);
        tvSignAddress = (TextView) findViewById(R.id.tvSignAddress);
        tvSignDate = (TextView) findViewById(R.id.tvSignDate);
        etRemark = (EditText) findViewById(R.id.etRemark);
        getDate();

    }

    private void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSignBack:
                finish();
                break;
            case R.id.btnCount:
                startActivity(new Intent(SignActivity.this, DebugDetailActivity.class));
                break;
            case R.id.etRemark:
                etRemark.setFocusableInTouchMode(true);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onResume();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();
            mapView = null;
        }
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(SignActivity.this);
        }
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null) {

        }
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        // 设置定位数据
        mBaidumap.setMyLocationData(locData);
        if (isFirstLoc) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(ll, 20);//设置地图中心及缩放级别
            mBaidumap.animateMapStatus(update);
            isFirstLoc = false;
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
            ContentValues initialValues = new ContentValues();
            initialValues.put("shijian", location.getTime());
            initialValues.put("didian", location.getLatitude() + "--" + location.getLongitude());
            ToastUtils.ShowToastMessage(location.getAddrStr(), getApplicationContext());
        }
//        tvSignAddress.setText("radius:" + location.getRadius() + ";"
//                + "latitute:" + location.getLatitude() + ";"
//                + "longtitute:" + location.getLongitude() + ";"
//                + "address:" + location.getAddrStr());
    }

    private void getDate() {
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        year = t.year;
        month = t.month;
        date = t.monthDay;
        hour = t.hour; // 0-23
        minute = t.minute;
        second = t.second;
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

}
