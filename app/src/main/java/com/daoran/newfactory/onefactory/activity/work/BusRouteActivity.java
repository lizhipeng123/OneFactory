package com.daoran.newfactory.onefactory.activity.work;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.overlayutil.DrivingRouteOverlay;
import com.daoran.newfactory.onefactory.util.overlayutil.TransitRouteOverlay;
import com.daoran.newfactory.onefactory.util.overlayutil.WalkingRouteOverlay;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.ExpandLayout;


/**
 * Created by lizhipeng on 2017/3/30.
 */

public class BusRouteActivity extends BaseFrangmentActivity implements View.OnClickListener, BDLocationListener {

    private MapView mapView = null;
    LocationClient mLocationClient = null;
    Button requestLocButton;
    private TextView tv_location;
    private Button clear_location;
    private BaiduMap mBaiduMap;
    private LocationClientOption locOption;
    private Marker mMarker;
    private boolean isFirstLoc = true;
    private static int LOCATION_COUTNS = 0;

    private String startPlace;// 开始地点
    private String endPlace;// 结束地点

    private Button driveBtn;// 驾车
    private Button walkBtn;// 步行
    private Button transitBtn;// 换成 （公交）
    private Button nextLineBtn;

    private RoutePlanSearch routePlanSearch;// 路径规划搜索接口

    private int index = -1;
    private int totalLine = 0;// 记录某种搜索出的方案数量
    private int drivintResultIndex = 0;// 驾车路线方案index
    private int transitResultIndex = 0;// 换乘路线方案index

    private ExpandLayout expandLayout;
    private Button btnOpen;
    private ImageView ivBusroute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);
        setContentView(R.layout.activity_busroute);
        getViews();
        setListener();
    }

    private void getViews() {
//        tv_location = (TextView) findViewById(R.id.tv_location);
        ivBusroute = (ImageView) findViewById(R.id.ivBusroute);
        mapView = (MapView) findViewById(R.id.bMapView);
        clear_location = (Button) findViewById(R.id.clear_location);
        initViews();
        mBaiduMap = mapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        clear_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLocationClient.isStarted()) {
                    mLocationClient.stop();
                }
                mLocationClient.start();
            }

        });
//        routePlanSearch = RoutePlanSearch.newInstance();
//        routePlanSearch.setOnGetRoutePlanResultListener(routePlanResultListener);
        btnOpen = (Button) findViewById(R.id.btnOpen);
//        initExpandView();

    }

    private void initExpandView() {
        expandLayout = (ExpandLayout) findViewById(R.id.expandLayout);
        expandLayout.initExpand(false);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandLayout.toggleExpand();
            }
        });
    }

    private void setListener() {

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
            mLocationClient.unRegisterLocationListener(BusRouteActivity.this);
        }
    }

    private void initViews() {
        ivBusroute.setOnClickListener(this);
        LocationClientOption locOption = new LocationClientOption();
        locOption.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
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
        mBaiduMap.setMyLocationData(locData);
        if (isFirstLoc) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(ll, 20);//设置地图中心及缩放级别
            mBaiduMap.animateMapStatus(update);
            isFirstLoc = false;
//            StringBuffer stringBuffer = new StringBuffer(256);
//            stringBuffer.append("\nAddress");
//            stringBuffer.append(location.getAddrStr());
//            stringBuffer.append(String.valueOf(LOCATION_COUTNS));
//            tv_location.setText(stringBuffer.toString());
//            isFirstLoc = false;
            Toast.makeText(getApplicationContext(), location.getAddrStr(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    /**
     * 路线规划结果回调
     */
    OnGetRoutePlanResultListener routePlanResultListener = new OnGetRoutePlanResultListener() {

        /**
         * 步行路线结果回调
         */
        @Override
        public void onGetWalkingRouteResult(
                WalkingRouteResult walkingRouteResult) {
            mBaiduMap.clear();
            if (walkingRouteResult == null
                    || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(BusRouteActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (walkingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // TODO
                return;
            }
            if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                WalkingRouteOverlay walkingRouteOverlay = new WalkingRouteOverlay(
                        mBaiduMap);
                walkingRouteOverlay.setData(walkingRouteResult.getRouteLines()
                        .get(drivintResultIndex));
                mBaiduMap.setOnMarkerClickListener(walkingRouteOverlay);
                walkingRouteOverlay.addToMap();
                walkingRouteOverlay.zoomToSpan();
                totalLine = walkingRouteResult.getRouteLines().size();
                ToastUtils.ShowToastMessage("共查询出" + totalLine + "条符合条件的线路", BusRouteActivity.this);
                if (totalLine > 1) {
                    nextLineBtn.setEnabled(true);
                }
            }
        }

        /**
         * 换成路线结果回调
         */
        @Override
        public void onGetTransitRouteResult(
                TransitRouteResult transitRouteResult) {
            mBaiduMap.clear();
            if (transitRouteResult == null
                    || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(BusRouteActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (transitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                // drivingRouteResult.getSuggestAddrInfo()
                return;
            }
            if (transitRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                TransitRouteOverlay transitRouteOverlay = new TransitRouteOverlay(
                        mBaiduMap);
                transitRouteOverlay.setData(transitRouteResult.getRouteLines()
                        .get(drivintResultIndex));// 设置一条驾车路线方案

                mBaiduMap.setOnMarkerClickListener(transitRouteOverlay);
                transitRouteOverlay.addToMap();
                transitRouteOverlay.zoomToSpan();
                totalLine = transitRouteResult.getRouteLines().size();
                ToastUtils.ShowToastMessage("共查询出" + totalLine + "条符合条件的线路", BusRouteActivity.this);
                if (totalLine > 1) {
                    nextLineBtn.setEnabled(true);
                } else if (totalLine <= drivintResultIndex) {
                    nextLineBtn.setEnabled(false);
                }
                // 通过getTaxiInfo()可以得到很多关于打车的信息
                ToastUtils.ShowToastMessage("该路线打车总路程"
                        + transitRouteResult.getTaxiInfo()
                        .getDistance(), BusRouteActivity.this);
                drivintResultIndex++;  //下一条按钮的点击次数不能超过这个数字.否则会抛出异常.
            }
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        /**
         * 驾车路线结果回调 查询的结果可能包括多条驾车路线方案
         */
        @Override
        public void onGetDrivingRouteResult(
                DrivingRouteResult drivingRouteResult) {
            mBaiduMap.clear();
            if (drivingRouteResult == null
                    || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(BusRouteActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                // drivingRouteResult.getSuggestAddrInfo()
                return;
            }
            if (drivingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                        mBaiduMap);
                drivingRouteOverlay.setData(drivingRouteResult.getRouteLines()
                        .get(drivintResultIndex));// 设置一条驾车路线方案

                mBaiduMap.setOnMarkerClickListener(drivingRouteOverlay);
                drivingRouteOverlay.addToMap();
                drivingRouteOverlay.zoomToSpan();
                totalLine = drivingRouteResult.getRouteLines().size();
                ToastUtils.ShowToastMessage("共查询出" + totalLine + "条符合条件的线路", BusRouteActivity.this);
                if (totalLine > 1) {
                    nextLineBtn.setEnabled(true);
                } else if (totalLine <= drivintResultIndex) {
                    nextLineBtn.setEnabled(false);
                }

                // 通过getTaxiInfo()可以得到很多关于打车的信息
                ToastUtils.ShowToastMessage("该路线打车总路程" + drivingRouteResult.getTaxiInfo()
                        .getDistance(), BusRouteActivity.this);
                drivintResultIndex++;
            }
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBusroute:
                finish();
                break;
        }
    }
}
