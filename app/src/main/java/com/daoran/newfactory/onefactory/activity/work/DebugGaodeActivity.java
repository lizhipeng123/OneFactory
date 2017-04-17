package com.daoran.newfactory.onefactory.activity.work;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.listview.ListViewForScrollView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by lizhipeng on 2017/4/14.
 */
@RuntimePermissions
public class DebugGaodeActivity extends BaseFrangmentActivity
        implements PoiSearch.OnPoiSearchListener,
        AMap.OnCameraChangeListener, LocationSource, AMapLocationListener {

    private ScrollView scrollviewSign;
    public static final String TAG = "MainActivity";
    private TextView textView;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private OnLocationChangedListener mListener;
    private PoiSearch.Query query;
    private double longitude;
    private double latitude;
    private String cityCode;
    private ListViewForScrollView mList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> locationDataList = new ArrayList<>();

    private Spinner mSpinner;
    private String deepType;
    private ProgressBar mProgressBar;
    private MapView gaomapview;
    private AMap aMap;
    private MarkerOptions mMarkerOptions;
    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_gaode);
        gaomapview = (MapView) findViewById(R.id.gaomapview);
        gaomapview.onCreate(savedInstanceState);
        init();
        textView = (TextView) findViewById(R.id.location_text);
        mList = (ListViewForScrollView) findViewById(R.id.list);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mProgressBar = (ProgressBar) findViewById(R.id.load);
        scrollviewSign = (ScrollView) findViewById(R.id.scrollviewSign);
        View v = gaomapview.getChildAt(0);
        //解决scrollview与mapview滑动冲突
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scrollviewSign.requestDisallowInterceptTouchEvent(false);
                } else {
                    scrollviewSign.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        View emptyView = View.inflate(getApplicationContext(), R.layout.empty_view, null);
        ((ViewGroup) mList.getParent()).addView(emptyView);
        mList.setEmptyView(emptyView);


        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, locationDataList);
        mList.setAdapter(adapter);


        TextView refresh = (TextView) findViewById(R.id.refresh);

        if (refresh != null) {
            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doSearch();
                }
            });
        }
        initSpinner();
        initAMap();
        DebugGaodeActivityPermissionsDispatcher.startLocationWithCheck(this);

    }

    private void init() {
        if (aMap == null) {
            aMap = gaomapview.getMap();
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);
        }
        setUp();
        mMarkerOptions = new MarkerOptions();
        mMarkerOptions.draggable(false);//可拖放性
        mMarkerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.guidepoint_red));

    }

    private void setUp() {
        aMap.setLocationSource(this);
        aMap.setOnCameraChangeListener(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17.5f));
    }

    private void setTextWithColor(String text, int color) {
        textView.setText(text);
        textView.setTextColor(color);
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_COARSE_LOCATION)
    void showDeniedForLocation() {
        setTextWithColor("只有打开定位权限才能正常使用此应用", Color.RED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        DebugGaodeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private void initSpinner() {
        final String[] str = new String[]{"SDK默认的deepType", "汽车服务", "汽车销售", "汽车维修",
                "摩托车服务", "餐饮服务", "购物服务", "生活服务", "体育休闲服务", "医疗保健服务",
                "住宿服务", "风景名胜", "商务住宅", "政府机构及社会团体", "科教文化服务", "交通设施服务",
                "金融保险服务", "公司企业", "道路附属设施", "地名地址信息", "公共设施"};

        mSpinner.setAdapter(new ArrayAdapter<>(DebugGaodeActivity.this, android.R.layout.simple_spinner_dropdown_item, str));
        mSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            deepType = str[5] + "|" + str[7] + "|" + str[12];
                        } else {
                            deepType = (String) mSpinner.getSelectedItem();
                        }
                        doSearch();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );
    }

    private void loading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mList.setVisibility(View.GONE);
    }

    private void loadComplete() {
        mProgressBar.setVisibility(View.GONE);
        mList.setVisibility(View.VISIBLE);
    }

    private void initAMap() {
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(getApplicationContext());
            mLocationClient.setLocationListener(this);
            //初始化AMapLocationClientOption对象
            mLocationOption = new AMapLocationClientOption();

            //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

            //获取一次定位结果：
            //该方法默认为false。
            mLocationOption.setOnceLocation(true);
            mLocationOption.setInterval(20 * 1000);

            mLocationOption.setLocationCacheEnable(false);

            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);

            //设置定位回调监听
            mLocationClient.setLocationListener(new AMapLocationListener() {

                @Override
                public void onLocationChanged(AMapLocation amapLocation) {
                    if (amapLocation != null) {
                        if (amapLocation.getErrorCode() == 0) {
                            //解析定位结果
                            Log.i(TAG, amapLocation.getPoiName());
                            latitude = amapLocation.getLatitude();
                            longitude = amapLocation.getLongitude();
                            setTextWithColor("纬度 = " + latitude + "\n经度 = " + longitude, Color.BLACK);
                            cityCode = amapLocation.getCity();
                            doSearch();
                            mLocationClient.stopLocation();
                        } else if (amapLocation.getErrorCode() == 12) {
                            setTextWithColor(amapLocation.getErrorInfo(), Color.RED);
                            Toast.makeText(getApplicationContext(), "权限不足，请在设置中授予相应权限", Toast.LENGTH_SHORT).show();
                        } else {
                            setTextWithColor(amapLocation.getErrorInfo(), Color.RED);
                            Log.e(TAG, "error code = " + amapLocation.getErrorCode());
                        }
                    }
                }
            });

            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.stopLocation();
        }
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon
                (BitmapDescriptorFactory.
                        fromResource(R.drawable.location_landmark));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));//圆形填充颜色
        myLocationStyle.strokeWidth(1.0f);//圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);//设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

    }

    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    void startLocation() {
        //启动定位
        mLocationClient.startLocation();
    }

    private void doSearch() {
        loading();
        query = new PoiSearch.Query("", deepType, cityCode);
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        query.setPageSize(20);// 设置每页最多返回多少条数据
        query.setPageNum(0);//设置查询页码
        LatLonPoint lp = new LatLonPoint(latitude, longitude);
        poiSearch.setBound(new PoiSearch.SearchBound(lp, 2000, true));//设置周边搜索的中心点以及半径
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，
        //POI搜索类型共分为以下20种：汽车服务|汽车销售|
        //汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
        //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
        //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索

        poiSearch.searchPOIAsyn();
    }


    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        locationDataList.clear();
        if (rCode == 1000) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    PoiResult poiResult = result;
                    ArrayList<PoiItem> poiItems = poiResult.getPois();

                    if (poiItems != null && poiItems.size() > 0) {
                        for (PoiItem p : poiItems) {
                            Log.i(TAG, "getTitle = " + p.getTitle());
                            locationDataList.add(p.getTitle());

                        }

                    }
                } else {
                    Log.e(TAG, "无结果");
                }
            }

        } else if (rCode == 27) {
            Log.e(TAG, "error_network");
            setTextWithColor("网络异常", Color.RED);
        } else if (rCode == 32) {
            setTextWithColor("error key", Color.RED);
            Log.e(TAG, "error_key");
        } else {
            setTextWithColor("poi error code = " + rCode, Color.RED);
            Log.e(TAG, "error_other：" + rCode);
        }
        loadComplete();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        gaomapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gaomapview.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        gaomapview.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gaomapview.onDestroy();
        mLocationClient.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationClient.stopLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    StringBuffer buffer1 = new StringBuffer();
                    buffer1.append(aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());

//                    spUtils.put(SignActivity.this,"gmapaddress",buffer.toString());
//                    spUtils.put(SignActivity.this,"latitude",buffer1.toString());
                    ToastUtils.ShowToastMessage(buffer.toString(), DebugGaodeActivity.this);
                    isFirstLoc = false;
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                ToastUtils.ShowToastMessage("定位失败", DebugGaodeActivity.this);
            }
        }
    }
}
