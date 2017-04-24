package com.daoran.newfactory.onefactory.activity.work;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Manager.PixelOrdpManager;
import com.daoran.newfactory.onefactory.util.kprogresshud.KProgressHUD;
import com.daoran.newfactory.onefactory.util.model.RouteModel;
import com.daoran.newfactory.onefactory.util.model.RoutePointModel;
import com.daoran.newfactory.onefactory.util.overlay.DrivingRouteOverlay;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizhipeng on 2017/4/14.
 */

public class DebugGaodeActivity extends
        BaseFrangmentActivity
        implements
        LocationSource,
        AMapLocationListener,
        GeocodeSearch.OnGeocodeSearchListener,
        RouteSearch.OnRouteSearchListener {

    /**
     * 高德地图
     */
    private MapView mapView;
    private AMap aMap;
    //定位点
    double latitude;// 纬度
    double longitude;// 经度
    //地图中心点
    Marker rallyMark;
    // 获取当前地图中心点的坐标
    LatLng mTarget;
    //定位
    private AMapLocationClient mLocationClient;
    private LocationSource.OnLocationChangedListener mListener;

    private EditText edt_search_via_point;
    //搜索
    private PoiSearch.Query query;// Poi查询条件类
    private PoiResult poiResult; // poi返回的结果
    private ArrayList<PoiItem> poiItemList = new ArrayList<PoiItem>();// poi数据
    int currentPage = 0;
    private GeocodeSearch geocodeSearch;
    private LatLonPoint latLonPoint;

    //移动到当前定位点
    private ImageView img_map_location_type_locate;
    //完成提交按钮
    private TextView txt_add_route;

    //结束点  红点
    private ImageView img_route_end;
    //结束点   位置上的添加按钮
    private LinearLayout layout_route_end_add;
    private ImageView img_route_end_add;

    //地图中显示的中心点的照片
    private ImageView img_current_position;
    //up  down
    private TextView txt_up;
    private TextView txt_down;
    //地图中心点当前的具体位置
    private String currentSpecificPosition;
    private DriveRouteResult mDriveRouteResult;
    //第三个参数表示途经点（最多支持16个）
    List<LatLonPoint> latLonPointList = new ArrayList<LatLonPoint>();
    RouteSearch routeSearch;
    KProgressHUD first_hud;
    //提交转圈的动画
    KProgressHUD hud;
    //完成提交的数据
    private RouteModel routeModel;
    long mileage;
    String imgUrl;

    //搜索途经点的回调
    private boolean isShowActivityForResult = false;
    private boolean isShowMoveLocationCenter = true;
    //显示开始点  结束点位置
    private boolean isShowVisibleArea = false;

    //点此设置集结点
    public static final int SEARCH_VIA_POINT = 16;
    public static final int SEARCH_VIA_POINT_FOR_RESULT = 165;

    private ProgressDialog progDialog = null;// 搜索时进度条
    private Intent intent;
    private boolean isEdit = false;
    private TextView txt_my_route;
    //标记
    private Marker startMark;
    private Marker viaOneMark;
    private Marker viaTwoMark;
    private Marker viaThereMark;
    private Marker viaFourMark;
    private Marker viaFiveMark;
    private Marker viaSixMark;
    private Marker viaSevenMark;
    private Marker viaEightMark;
    private Marker viaNineMark;
    private Marker viaTenMark;
    private Marker viaElevenMark;
    private Marker viaTwelveMark;
    private Marker viaThirteenMark;
    private Marker viaFourteenMark;
    private Marker viaFifteenMark;
    private Marker viaSixteenMark;
    private Marker endMark;
    private ListView listView;
    private ViaAdapter viaAdapter;
    //开始点  途径点  终点的集合
    ArrayList<RoutePointModel> routePointModelArrayList = new ArrayList<RoutePointModel>();
    //判断    开始点  途经点1   途经点2   途经点3   结束点
    private int currentShowPoint = 0;//0   1  2  3  4   5
    private Map<String, Marker> viaMakerMap = new HashMap<>();// 途经点
    //路书的编辑返回
    public static final int EDIT_ROUTE_FINISH_FOR_RESULT = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_gaode);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mapView = (MapView) findViewById(R.id.gaomapview);
        mapView.onCreate(savedInstanceState);
        routeModel = new RouteModel();
        initMap();
        initFindView();
        loadTheData();
        listenerView();
        initBroadcaseReciver();
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        UiSettings mUiSettings = aMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(false);// 是否显示放大缩小按钮
        mUiSettings.setMyLocationButtonEnabled(false);// 是否显示定位按钮
        mUiSettings.setCompassEnabled(false);// 是否显示指南针
        mUiSettings.setScaleControlsEnabled(false); // 是否显示比例尺
        mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);// logo位置
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        /**
         * 自定义定位图标
         */
        setMyLocationStyle();
        /**
         * 设置定位资源
         */
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        first_hud = KProgressHUD.create(DebugGaodeActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        /**
         * 设置地图加载完成回调
         */
        aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
//                aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
                /**
                 * 设置缩放级别为16
                 */
                aMap.moveCamera(CameraUpdateFactory.zoomTo(19));
                aMap.showMapText(true);
                aMap.showIndoorMap(false);
//                重新加载地图数据。
//                aMap.setLoadOfflineData(true);
            }
        });
        //移动地图
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

                if (currentShowPoint == 0) {
                    img_current_position.setImageResource(R.mipmap.select_position_g_up);
                    txt_down.setVisibility(View.GONE);
                    txt_up.setVisibility(View.GONE);
                } else if (0 < currentShowPoint && currentShowPoint < 17) {
                    img_current_position.setImageResource(R.mipmap.select_position_y_up);
                    txt_up.setVisibility(View.VISIBLE);
                    txt_up.setText(currentShowPoint + "");
                    txt_down.setVisibility(View.GONE);
                } else if (currentShowPoint == 17) {
                    img_current_position.setImageResource(R.mipmap.select_position_r_up);
                    txt_down.setVisibility(View.GONE);
                    txt_up.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                if (currentShowPoint == 0) {
                    img_current_position.setImageResource(R.mipmap.select_position_g_down);
                    txt_down.setVisibility(View.GONE);
                    txt_up.setVisibility(View.GONE);
                } else if (0 < currentShowPoint && currentShowPoint < 17) {
                    img_current_position.setImageResource(R.mipmap.select_position_y_down);
                    txt_down.setVisibility(View.VISIBLE);
                    txt_down.setText(currentShowPoint + "");
                    txt_up.setVisibility(View.GONE);
                } else if (currentShowPoint == 17) {
                    img_current_position.setImageResource(R.mipmap.select_position_r_down);
                    txt_down.setVisibility(View.GONE);
                    txt_up.setVisibility(View.GONE);
                }

                // 获取当前地图中心点的坐标
                mTarget = aMap.getCameraPosition().target;
                //准确位置获取
                geocodeSearch = new GeocodeSearch(DebugGaodeActivity.this);
                geocodeSearch.setOnGeocodeSearchListener(DebugGaodeActivity.this);
                latLonPoint = new LatLonPoint(mTarget.latitude, mTarget.longitude);
                getAddress(latLonPoint);
                //第一次定位移动到定位点作为地图的中心点
                if (isShowMoveLocationCenter == true) {
                    aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
                    isShowMoveLocationCenter = false;
                }
                if (isShowVisibleArea == true) {
                    //设置显示的范围
                    setShowVisibleArea();
                }
            }
        });
    }

    /**
     * 自定义定位图标
     */
    private void setMyLocationStyle() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();// 自定义系统定位小蓝点
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.o_map));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.TRANSPARENT);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);// 设置圆形的填充颜色
        myLocationStyle.anchor(0.5f, 0.5f);// 设置小蓝点的锚点
        myLocationStyle.strokeWidth(0);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
    }

    private void initFindView() {
        txt_add_route = (TextView) findViewById(R.id.txt_add_route);
        //移动到当前定位点
        img_map_location_type_locate = (ImageView) findViewById(R.id.img_map_location_type_locate);
        img_current_position = (ImageView) findViewById(R.id.img_current_position);
        img_current_position.setImageResource(R.mipmap.select_position_g_down);
        txt_down = (TextView) findViewById(R.id.txt_down);
        txt_up = (TextView) findViewById(R.id.txt_up);
        edt_search_via_point = (EditText) findViewById(R.id.edt_search_via_point);
        txt_my_route = (TextView) findViewById(R.id.txt_my_route);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void listenerView() {
        //点击选择   设置当前的位置给点
        img_current_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置起始位置
                if (currentShowPoint == 0) {
//                    currentShowPoint = 17;
                    if (routePointModelArrayList.size() >= 1) {
                        routePointModelArrayList.remove(0);
                        routePointModelArrayList.add(0, new RoutePointModel(mTarget.latitude, mTarget.longitude, currentSpecificPosition));
                        currentShowPoint = 1;
                    } else {
                        routePointModelArrayList.add(new
                                RoutePointModel(mTarget.latitude,
                                mTarget.longitude, currentSpecificPosition));
                        currentShowPoint = 17;
                    }
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                    if (startMark == null) {
                        startMark = aMap.addMarker(new MarkerOptions().
                                position(mTarget).icon(BitmapDescriptorFactory.
                                fromResource(R.mipmap.position_start_up)));
                        startMark.setAnchor(0.5f, 0.5f);
                    } else {
                        startMark.setPosition(mTarget);
                    }
                }
                //结束点
                else if (currentShowPoint == 17) {
                    if (routePointModelArrayList.size() == 18) {
                        currentShowPoint = 17;
                        routePointModelArrayList.remove(17);
                        routePointModelArrayList.add(17,
                                new RoutePointModel(mTarget.latitude,
                                        mTarget.longitude, currentSpecificPosition));
                        listView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listView.requestFocusFromTouch();
                                listView.setSelection(routePointModelArrayList.size() - 5);
                            }
                        }, 500);
                    } else if (routePointModelArrayList.size() >= 2) {
                        currentShowPoint = 1;
                        routePointModelArrayList.
                                remove(routePointModelArrayList.size() - 1);
                        routePointModelArrayList.add(new
                                RoutePointModel(mTarget.latitude,
                                mTarget.longitude, currentSpecificPosition));
                    } else {
                        currentShowPoint = 1;
                        routePointModelArrayList.add(new
                                RoutePointModel(mTarget.latitude,
                                mTarget.longitude, currentSpecificPosition));
                    }
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                    if (endMark == null) {
                        endMark = aMap.addMarker(new
                                MarkerOptions().position(mTarget).
                                icon(BitmapDescriptorFactory.fromResource
                                        (R.mipmap.position_end_up)));
                        endMark.setAnchor(0.5f, 0.5f);
                    } else {
                        endMark.setPosition(mTarget);
                    }
                }
                //途径点1
                else if (currentShowPoint == 1) {
                    currentShowPoint = 2;
                    if (routePointModelArrayList.size() >= 3) {
                        routePointModelArrayList.remove(1);
                    }
                    routePointModelArrayList.add(1,
                            new RoutePointModel(mTarget.latitude,
                                    mTarget.longitude, currentSpecificPosition));
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点2
                else if (currentShowPoint == 2) {
                    currentShowPoint = 3;
                    if (routePointModelArrayList.size() >= 4) {
                        routePointModelArrayList.remove(2);
                    }
                    routePointModelArrayList.add(2,
                            new RoutePointModel(mTarget.latitude,
                                    mTarget.longitude, currentSpecificPosition));
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点3
                else if (currentShowPoint == 3) {
                    currentShowPoint = 4;
                    if (routePointModelArrayList.size() >= 5) {
                        routePointModelArrayList.remove(3);
                    }
                    routePointModelArrayList.add(3, new
                            RoutePointModel(mTarget.latitude,
                            mTarget.longitude, currentSpecificPosition));
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点4
                else if (currentShowPoint == 4) {
                    currentShowPoint = 5;
                    if (routePointModelArrayList.size() >= 6) {
                        routePointModelArrayList.remove(4);
                    }
                    routePointModelArrayList.add(4,
                            new RoutePointModel(mTarget.latitude,
                                    mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点5
                else if (currentShowPoint == 5) {
                    currentShowPoint = 6;
                    if (routePointModelArrayList.size() >= 7) {
                        routePointModelArrayList.remove(5);
                    }
                    routePointModelArrayList.add(5, new
                            RoutePointModel(mTarget.latitude,
                            mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点6
                else if (currentShowPoint == 6) {
                    currentShowPoint = 7;
                    if (routePointModelArrayList.size() >= 8) {
                        routePointModelArrayList.remove(6);
                    }
                    routePointModelArrayList.add(6, new
                            RoutePointModel(mTarget.latitude, mTarget.longitude,
                            currentSpecificPosition));

                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点7
                else if (currentShowPoint == 7) {
                    currentShowPoint = 8;
                    if (routePointModelArrayList.size() >= 9) {
                        routePointModelArrayList.remove(7);
                    }
                    routePointModelArrayList.add(7, new
                            RoutePointModel(mTarget.latitude,
                            mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点8
                else if (currentShowPoint == 8) {
                    currentShowPoint = 9;
                    if (routePointModelArrayList.size() >= 10) {
                        routePointModelArrayList.remove(8);
                    }
                    routePointModelArrayList.add(8, new
                            RoutePointModel(mTarget.latitude,
                            mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点9
                else if (currentShowPoint == 9) {
                    currentShowPoint = 10;
                    if (routePointModelArrayList.size() >= 11) {
                        routePointModelArrayList.remove(9);
                    }
                    routePointModelArrayList.add(9, new
                            RoutePointModel(mTarget.latitude,
                            mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点10
                else if (currentShowPoint == 10) {
                    currentShowPoint = 11;
                    if (routePointModelArrayList.size() >= 12) {
                        routePointModelArrayList.remove(10);
                    }
                    routePointModelArrayList.add(10, new
                            RoutePointModel(mTarget.latitude,
                            mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点11
                else if (currentShowPoint == 11) {
                    currentShowPoint = 12;
                    if (routePointModelArrayList.size() >= 13) {
                        routePointModelArrayList.remove(11);
                    }
                    routePointModelArrayList.add(11, new
                            RoutePointModel(mTarget.latitude,
                            mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点12
                else if (currentShowPoint == 12) {
                    currentShowPoint = 13;
                    if (routePointModelArrayList.size() >= 14) {
                        routePointModelArrayList.remove(12);
                    }
                    routePointModelArrayList.add(12, new
                            RoutePointModel(mTarget.latitude,
                            mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点13
                else if (currentShowPoint == 13) {
                    currentShowPoint = 14;
                    if (routePointModelArrayList.size() >= 15) {
                        routePointModelArrayList.remove(13);
                    }
                    routePointModelArrayList.add(13, new
                            RoutePointModel(mTarget.latitude,
                            mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点14
                else if (currentShowPoint == 14) {
                    currentShowPoint = 15;
                    if (routePointModelArrayList.size() >= 16) {
                        routePointModelArrayList.remove(14);
                    }
                    routePointModelArrayList.add(14,
                            new RoutePointModel(mTarget.latitude,
                                    mTarget.longitude, currentSpecificPosition));

                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点15
                else if (currentShowPoint == 15) {
                    currentShowPoint = 16;
                    if (routePointModelArrayList.size() >= 17) {
                        routePointModelArrayList.remove(15);
                    }
                    routePointModelArrayList.add(15,
                            new RoutePointModel(mTarget.latitude,
                                    mTarget.longitude, currentSpecificPosition));

                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }
                // 途径点16
                else if (currentShowPoint == 16) {
                    currentShowPoint = 17;
                    if (routePointModelArrayList.size() >= 18) {
                        routePointModelArrayList.remove(16);
                    }
                    routePointModelArrayList.add(16,
                            new RoutePointModel(mTarget.latitude,
                                    mTarget.longitude, currentSpecificPosition));
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.requestFocusFromTouch();
                            listView.setSelection(routePointModelArrayList.size() - 5);
                        }
                    }, 500);
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();
                    //画点
                }

                //点击确认后设置新的中心点显示的图标
                if (currentShowPoint == 0) {
                    img_current_position.setImageResource(
                            R.mipmap.select_position_g_down);
                    txt_down.setVisibility(View.GONE);
                    txt_up.setVisibility(View.GONE);
                } else if (0 < currentShowPoint && currentShowPoint < 17) {
                    img_current_position.setImageResource(
                            R.mipmap.select_position_y_down);
                    txt_down.setVisibility(View.VISIBLE);
                    txt_down.setText(currentShowPoint + "");
                    txt_up.setVisibility(View.GONE);
                } else if (currentShowPoint == 17) {
                    img_current_position.setImageResource(
                            R.mipmap.select_position_r_down);
                    txt_down.setVisibility(View.GONE);
                    txt_up.setVisibility(View.GONE);
                }
//                //当结束点和开始点都有值得时候就执行划线的方法
                setLatLonPointToMap(routePointModelArrayList);
            }
        });

        //创建约跑的item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                //点击选中了那一项  那一项 就处于编辑状态
                if (routePointModelArrayList == null) {
                    currentShowPoint = position;
                } else if (0 < routePointModelArrayList.size() &&
                        routePointModelArrayList.size() < 18) {
                    if (position == routePointModelArrayList.size()) {
                        currentShowPoint = 17;
                    } else {
                        currentShowPoint = position;
                    }
                } else if (routePointModelArrayList.size() == 18) {
                    currentShowPoint = position;
                }
                //刷新   当前选中的颜色改变
                viaAdapter.notifyDataSetChanged();
                //移动到选中的点处
                if (routePointModelArrayList == null) {
                } else if (routePointModelArrayList.size() > 0) {
                    LatLng viaLatLng = null;
                    if (routePointModelArrayList.size() == 1) {
                        if (position == 0) {
                            viaLatLng = new LatLng(routePointModelArrayList.
                                    get(position)
                                    .getLatitude(),
                                    routePointModelArrayList.get(position).
                                            getLongitude());
                            //设置地图中心点
                            aMap.moveCamera(CameraUpdateFactory.changeLatLng(viaLatLng));
                            isShowVisibleArea = false;
                        } else {
                            //点击的另外
                        }
                    } else if (routePointModelArrayList.size() > 1
                            && routePointModelArrayList.size() < 18) {
                        if (position == routePointModelArrayList.size()) {// 0  1   2      2
                            viaLatLng = new LatLng(routePointModelArrayList.
                                    get(position - 1).getLatitude(),
                                    routePointModelArrayList.get(position - 1).
                                            getLongitude());
                            //设置地图中心点
                            aMap.moveCamera(CameraUpdateFactory.changeLatLng(viaLatLng));
                            isShowVisibleArea = false;
                        } else if (position == routePointModelArrayList.size() - 1) {

                        } else {
                            viaLatLng = new LatLng(routePointModelArrayList.
                                    get(position).getLatitude(),
                                    routePointModelArrayList.get(position).
                                            getLongitude());
                            //设置地图中心点
                            aMap.moveCamera(CameraUpdateFactory.changeLatLng(viaLatLng));
                            isShowVisibleArea = false;
                        }
                    } else if (routePointModelArrayList.size() == 18) {
                        viaLatLng = new LatLng(routePointModelArrayList.
                                get(position).getLatitude(),
                                routePointModelArrayList.get(position).getLongitude());
                        //设置地图中心点
                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(viaLatLng));
                        isShowVisibleArea = false;
                    }
                }
            }
        });
        //返回
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        DebugGaodeActivity.this);
                builder.setTitle("提示");
                builder.setMessage("退出此次编辑");
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isEdit == true) {
                            //返回上一界面   然后继续返回到我的路书界面
                            Intent intent = new Intent();
                            setResult(EDIT_ROUTE_FINISH_FOR_RESULT, intent);
                        }
                        finish();
                    }
                });
                builder.show();
            }
        });
        findViewById(R.id.txt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        DebugGaodeActivity.this);
                builder.setTitle("提示");
                builder.setMessage("退出此次编辑");
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isEdit == true) {
                            //返回上一界面   然后继续返回到我的路书界面
                            Intent intent = new Intent();
                            setResult(EDIT_ROUTE_FINISH_FOR_RESULT, intent);
                        }
                        finish();
                    }
                });
                builder.show();
            }
        });
        //移动到定位点
        img_map_location_type_locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
                isShowVisibleArea = false;
            }
        });
        /**
         *setOnMapTouchListener(BaiduMap.OnMapTouchListener listener):
         * 设置触摸地图事件监听者
         * */
        aMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                isShowVisibleArea = false;
                img_map_location_type_locate.setVisibility(View.VISIBLE);
            }
        });
        //搜索
        edt_search_via_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DebugGaodeActivity.this,
                        SearchViaPointActivity.class);
                intent.putExtra("currentShowPoint", currentShowPoint);
                startActivityForResult(intent, SEARCH_VIA_POINT);
            }
        });
        //完成提交
        txt_add_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (routePointModelArrayList != null
                        && routePointModelArrayList.size() >= 2) {
                    hud = KProgressHUD.create(DebugGaodeActivity.this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setCancellable(false)
                            .setAnimationSpeed(1)
                            .setDimAmount(0.5f)
                            .show();
//                    //当结束点和开始点都有值得时候就执行划线的方法
//                    setLatLonPointToMap(latLonPointList);
                    //设置显示区域
                    setShowVisibleArea();
                    //定位点  设置定位层是否显示。
                    aMap.setMyLocationEnabled(false);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(2000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getMapPhoto();
                                }
                            });
                        }
                    }).start();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DebugGaodeActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("请选择开始点和结束点");
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    //设置显示区域
    private void setShowVisibleArea() {
        //设置显示区域
        if (routePointModelArrayList != null && routePointModelArrayList.size() >= 2) {
            LatLngBounds bounds = null;
            for (int i = 0; i < routePointModelArrayList.size(); i++) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(i).getLatitude(),
                                routePointModelArrayList.get(i).getLongitude())).build();
            }
            if (routePointModelArrayList.size() == 2) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 3) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 4) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 5) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 6) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 7) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 8) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 9) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 10) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(9).getLatitude(),
                                routePointModelArrayList.get(9).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 11) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(9).getLatitude(),
                                routePointModelArrayList.get(9).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(10).getLatitude(),
                                routePointModelArrayList.get(10).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 12) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(9).getLatitude(),
                                routePointModelArrayList.get(9).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(10).getLatitude(),
                                routePointModelArrayList.get(10).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(11).getLatitude(),
                                routePointModelArrayList.get(11).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 13) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(9).getLatitude(),
                                routePointModelArrayList.get(9).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(10).getLatitude(),
                                routePointModelArrayList.get(10).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(11).getLatitude(),
                                routePointModelArrayList.get(11).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(12).getLatitude(),
                                routePointModelArrayList.get(12).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 14) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(9).getLatitude(),
                                routePointModelArrayList.get(9).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(10).getLatitude(),
                                routePointModelArrayList.get(10).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(11).getLatitude(),
                                routePointModelArrayList.get(11).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(12).getLatitude(),
                                routePointModelArrayList.get(12).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(13).getLatitude(),
                                routePointModelArrayList.get(13).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 15) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(9).getLatitude(),
                                routePointModelArrayList.get(9).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(10).getLatitude(),
                                routePointModelArrayList.get(10).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(11).getLatitude(),
                                routePointModelArrayList.get(11).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(12).getLatitude(),
                                routePointModelArrayList.get(12).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(13).getLatitude(),
                                routePointModelArrayList.get(13).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(14).getLatitude(),
                                routePointModelArrayList.get(14).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 16) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(9).getLatitude(),
                                routePointModelArrayList.get(9).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(10).getLatitude(),
                                routePointModelArrayList.get(10).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(11).getLatitude(),
                                routePointModelArrayList.get(11).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(12).getLatitude(),
                                routePointModelArrayList.get(12).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(13).getLatitude(),
                                routePointModelArrayList.get(13).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(14).getLatitude(),
                                routePointModelArrayList.get(14).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(15).getLatitude(),
                                routePointModelArrayList.get(15).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 17) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(9).getLatitude(),
                                routePointModelArrayList.get(9).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(10).getLatitude(),
                                routePointModelArrayList.get(10).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(11).getLatitude(),
                                routePointModelArrayList.get(11).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(12).getLatitude(),
                                routePointModelArrayList.get(12).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(13).getLatitude(),
                                routePointModelArrayList.get(13).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(14).getLatitude(),
                                routePointModelArrayList.get(14).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(15).getLatitude(),
                                routePointModelArrayList.get(15).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(16).getLatitude(),
                                routePointModelArrayList.get(16).getLongitude()))
                        .build();
            } else if (routePointModelArrayList.size() == 18) {
                bounds = new LatLngBounds.Builder()
                        .include(new LatLng(routePointModelArrayList.get(0).getLatitude(),
                                routePointModelArrayList.get(0).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(1).getLatitude(),
                                routePointModelArrayList.get(1).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(2).getLatitude(),
                                routePointModelArrayList.get(2).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(3).getLatitude(),
                                routePointModelArrayList.get(3).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(4).getLatitude(),
                                routePointModelArrayList.get(4).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(5).getLatitude(),
                                routePointModelArrayList.get(5).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(6).getLatitude(),
                                routePointModelArrayList.get(6).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(7).getLatitude(),
                                routePointModelArrayList.get(7).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(8).getLatitude(),
                                routePointModelArrayList.get(8).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(9).getLatitude(),
                                routePointModelArrayList.get(9).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(10).getLatitude(),
                                routePointModelArrayList.get(10).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(11).getLatitude(),
                                routePointModelArrayList.get(11).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(12).getLatitude(),
                                routePointModelArrayList.get(12).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(13).getLatitude(),
                                routePointModelArrayList.get(13).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(14).getLatitude(),
                                routePointModelArrayList.get(14).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(15).getLatitude(),
                                routePointModelArrayList.get(15).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(16).getLatitude(),
                                routePointModelArrayList.get(16).getLongitude()))
                        .include(new LatLng(routePointModelArrayList.get(17).getLatitude(),
                                routePointModelArrayList.get(17).getLongitude()))
                        .build();
            }
            aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,
                    mapView.getWidth(), mapView.getHeight(),
                    PixelOrdpManager.dip2px(getBaseContext(), 100)));
        }
    }

    //提交数据
    private void commitRouteData() {
        if (isEdit == false) {
            //添加新路书
        } else if (isEdit == true) {
            //修改路书
        }
    }

    //设置数据进行划线
    private void setLatLonPointToMap(ArrayList<RoutePointModel> routePointList) {
        if (routePointList != null) {
            LatLng startTarget = null;
            LatLng endTarget = null;
            if (latLonPointList != null) {
                latLonPointList.clear();
            }
            if (routePointList.size() == 1) {
                startTarget = new LatLng(routePointList.get(0).getLatitude(),
                        routePointList.get(0).getLongitude());
            } else if (routePointList.size() == 2) {
                startTarget = new LatLng(routePointList.get(0).getLatitude(),
                        routePointList.get(0).getLongitude());
                endTarget = new LatLng(routePointList.get(1).getLatitude(),
                        routePointList.get(1).getLongitude());
            } else if (routePointList.size() > 2) {
                startTarget = new LatLng(routePointList.get(0).getLatitude(),
                        routePointList.get(0).getLongitude());
                endTarget = new LatLng(routePointList.get(routePointList.size() - 1).
                        getLatitude(), routePointList.get(routePointList.size() - 1).
                        getLongitude());
                for (int i = 1; i < routePointList.size(); i++) {
                    latLonPointList.add(new LatLonPoint(routePointList.get(i).
                            getLatitude(), routePointList.get(i).getLongitude()));
                }
            }
            //        当结束点和开始点都有值得时候就执行划线的方法
            if (startTarget != null && endTarget != null) {
                showProgressDialog();
                routeSearch = new RouteSearch(DebugGaodeActivity.this);
                routeSearch.setRouteSearchListener(DebugGaodeActivity.this);
                // fromAndTo包含路径规划的起点和终点，drivingMode表示驾车模式
                // 第三个参数表示途经点（最多支持16个），第四个参数表示避让区域（最多支持32个），第五个参数表示避让道路
//                    RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, drivingMode, null, null, "");
                if (latLonPointList != null) {
                    RouteSearch.DriveRouteQuery query = new
                            RouteSearch.DriveRouteQuery(new RouteSearch.FromAndTo(
                            new LatLonPoint(startTarget.latitude,
                                    startTarget.longitude),
                            new LatLonPoint(endTarget.latitude, endTarget.longitude)),
                            RouteSearch.DrivingDefault, latLonPointList, null, "");
                    routeSearch.calculateDriveRouteAsyn(query);
                } else {
                    RouteSearch.DriveRouteQuery query = new
                            RouteSearch.DriveRouteQuery(new RouteSearch.FromAndTo(
                            new LatLonPoint(startTarget.latitude, startTarget.longitude),
                            new LatLonPoint(endTarget.latitude, endTarget.longitude)),
                            RouteSearch.DrivingDefault, null, null, "");
                    routeSearch.calculateDriveRouteAsyn(query);
                }
            } else {
                //当没有开始点和结束点时清除线路 重现画点
                aMap.clear();
                //画点
                if (startTarget != null) {
                    startMark = aMap.addMarker(new MarkerOptions().
                            position(startTarget).icon(
                            BitmapDescriptorFactory.fromResource(
                                    R.mipmap.position_start_up)));
                    startMark.setAnchor(0.5f, 0.5f);
                }
            }
        }
    }

    private void loadTheData() {
        viaAdapter = new ViaAdapter(DebugGaodeActivity.this);
        listView.setAdapter(viaAdapter);
        viaAdapter.notifyDataSetChanged();
    }

    private void initBroadcaseReciver() {

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(getApplicationContext());
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            mLocationClient.setLocationListener(this);
            mLocationOption.setLocationMode(
                    AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation();
        }
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
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统蓝点
                latitude = aMapLocation.getLatitude();// 纬度
                longitude = aMapLocation.getLongitude();// 经度
            } else {
                String errText = "定位失败," +
                        aMapLocation.getErrorCode() + ": " +
                        aMapLocation.getErrorInfo();
                System.out.println(errText);
            }
        } else {
        }
    }

    /**
     * 逆地理编码
     */
    public void getAddress(final LatLonPoint latLonPoint) {
        RegeocodeQuery regeocodeQuery = new
                RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(regeocodeQuery);
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null && result.getRegeocodeAddress() !=
                    null && result.getRegeocodeAddress().
                    getFormatAddress() != null) {
                first_hud.dismiss();
                //当前具体位置
                currentSpecificPosition = result.
                        getRegeocodeAddress().getFormatAddress();
//                如果是activityforresult来进行数据设置
                if (isShowActivityForResult == true) {

                    isShowActivityForResult = false;
                }
            } else {
                Toast.makeText(DebugGaodeActivity.this,
                        "定位失败，请重试", Toast.LENGTH_SHORT);
            }
        } else if (rCode == 27) {
            Toast.makeText(DebugGaodeActivity.this,
                    "定位失败，请重试", Toast.LENGTH_SHORT);
        } else if (rCode == 32) {
            Toast.makeText(DebugGaodeActivity.this,
                    "定位失败，请重试", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(DebugGaodeActivity.this,
                    "定位失败，请重试", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        isShowVisibleArea = true;
        aMap.clear();
        if (errorCode == 1000) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mDriveRouteResult = result;
                    final DrivePath drivePath = mDriveRouteResult.getPaths().get(0);
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                            DebugGaodeActivity.this, aMap, drivePath,
                            mDriveRouteResult.getStartPos(),
                            mDriveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();
                    //距离
                    double totalDistance = 0;
                    for (DrivePath drivePaths : result.getPaths()) {
                        if (drivePaths != null && drivePaths.getSteps() != null) {
                            for (DriveStep driveStep : drivePaths.getSteps()) {
                                totalDistance += driveStep.getDistance();
                            }
                        }
                    }
                    mileage = (long) totalDistance;
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.setNodeIconVisibility(false);//去除车图标
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();
                    if (routePointModelArrayList.size() > 2) {
                        for (int i = 1; i < routePointModelArrayList.size() - 1; i++) {
                            double latitude = routePointModelArrayList.get(i).
                                    getLatitude();
                            double longitude = routePointModelArrayList.get(i).
                                    getLongitude();
                            String title = routePointModelArrayList.get(i).getTitle();
                            //自定义设置显示的图标
                            View view = LayoutInflater.from(this).inflate(R.layout.
                                    inflate_route_maker, null);
                            TextView txt_maker_num = (TextView) view.findViewById(
                                    R.id.txt_maker_num);
                            if (latitude > 0 && longitude > 0) {
                                txt_maker_num.setText(i + "");
                                MarkerOptions markerOptions = new MarkerOptions();
                                BitmapDescriptor bitmapDescriptor =
                                        BitmapDescriptorFactory.fromView(view);
                                markerOptions.icon(bitmapDescriptor).position(new
                                        LatLng(latitude, longitude));
                                Marker marker = aMap.addMarker(markerOptions);
                                viaMakerMap.put(title, marker);
                            }
                        }
                    }
                    // 获取当前地图开始点坐标
                    LatLng startTarget = new LatLng(routePointModelArrayList.get(0)
                            .getLatitude(),
                            routePointModelArrayList.get(0).getLongitude()
                    );
                    // 获取当前地图结束点坐标
                    LatLng endTarget = new LatLng(routePointModelArrayList.
                            get(routePointModelArrayList.size() - 1).getLatitude(),
                            routePointModelArrayList.get(
                                    routePointModelArrayList.size() - 1).
                                    getLongitude()
                    );
                    LatLngBounds bounds = new LatLngBounds.Builder()
                            .include(startTarget).include(endTarget).build();
                    aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,
                            PixelOrdpManager.dip2px(getBaseContext(), 100)));

                } else if (result != null && result.getPaths() == null) {
                    Toast.makeText(this, "对不起，没有搜索到相关数据！",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "对不起，没有搜索到相关数据！",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "对不起，没有搜索到相关数据！",
                    Toast.LENGTH_SHORT).show();
        }
        dissmissProgressDialog();
    }

    /**
     * 处理图片
     *
     * @param bm        所要转换的bitmap
     * @param newWidth
     * @param newHeight
     * @return 指定宽高的bitmap
     */
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    //路书照片的存储
    private void getMapPhoto() {
//        img_current_position.setVisibility(View.GONE);
        img_map_location_type_locate.setVisibility(View.GONE);
        // 设置截屏监听接口，截取地图可视区域
        aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {

            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int arg1) {
                Bitmap currentBitmap = zoomImg(bitmap,
                        PixelOrdpManager.dip2px(getBaseContext(), 375),
                        PixelOrdpManager.dip2px(getBaseContext(), 300));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                imgUrl = Environment.getExternalStorageDirectory() +
                        "/motoband_" + sdf.format(new Date()) + ".png";
                try {
                    FileOutputStream fos = new FileOutputStream(imgUrl);
                    boolean b = currentBitmap.compress(Bitmap.
                            CompressFormat.PNG, 100, fos);
                    try {
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //得到照片URL在上传
                    commitRouteData();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在搜索");
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    private class ViaAdapter extends BaseAdapter {
        private ArrayList<RoutePointModel> routePointModelList;
        private Context mcontext;

        private ViaAdapter(Context context) {
            mcontext = context;
        }

        public void setData(ArrayList<RoutePointModel> data) {
            if (routePointModelList == null) {
                routePointModelList = new ArrayList<RoutePointModel>();
            }
            routePointModelList.clear();
            if (data != null) {
                routePointModelList.addAll(data);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (routePointModelList == null) {
                return 1;
            } else if (routePointModelList.size() >= 18) {
                return 18;
            } else {
                return routePointModelList.size() + 1;
            }

        }

        @Override
        public RoutePointModel getItem(int position) {
            return routePointModelList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViaViewHolder viaViewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.inflate_add_route, parent, false);
                viaViewHolder = new ViaViewHolder();
                viaViewHolder.layout_roate_via = (
                        LinearLayout) convertView.findViewById(R.id.layout_roate_via);
                viaViewHolder.txt_route_via =
                        (TextView) convertView.findViewById(R.id.txt_route_via);
                viaViewHolder.txt_via = (TextView)
                        convertView.findViewById(R.id.txt_via);
                convertView.setTag(viaViewHolder);
            } else {
                viaViewHolder = (ViaViewHolder) convertView.getTag();
            }
            //设置当前哪一个被选中
            if (currentShowPoint == position) {
                viaViewHolder.txt_route_via.setTextColor(0xFFFFFFFF);
            } else {
                if (currentShowPoint == 17 && position == routePointModelList.size()) {
                    viaViewHolder.txt_route_via.setTextColor(0xFFFFFFFF);
                } else {
                    viaViewHolder.txt_route_via.setTextColor(0xFF828282);
                }
            }
            //开始点 途经点  结束点显示的图标
            if (routePointModelList != null && routePointModelList.size() > 0) {
                if (position == 0) {//0
                    //绿色
                    viaViewHolder.txt_via.setTextColor(0xFF00FF00);
                    viaViewHolder.txt_via.setText("起");
                } else if (0 < position && position <
                        routePointModelList.size() && position < 17) {
                    //黄色   1 -  数据总数的
                    viaViewHolder.txt_via.setTextColor(0xFFFFCE00);
                    viaViewHolder.txt_via.setText(position + "");
                } else if (position == routePointModelList.size()) {
                    //红色
                    viaViewHolder.txt_via.setTextColor(0xFFDC143C);
                    viaViewHolder.txt_via.setText("终");
                    viaViewHolder.txt_route_via.setText("选择结束点");
                } else if (position == 17 && routePointModelList.size() == 18) {
                    //红色
                    viaViewHolder.txt_via.setTextColor(0xFFDC143C);
                    viaViewHolder.txt_via.setText("终");
                    viaViewHolder.txt_route_via.setText("选择结束点");
                }
            } else {
                //绿色
                viaViewHolder.txt_via.setTextColor(0xFF00FF00);
                viaViewHolder.txt_via.setText("起");
            }
            //设置数据
            //路书实体有数据时才设置数据，没有数据设置默认
            if (routePointModelList != null && routePointModelList.size() > 0) {
                //0   //开始点  途经点
                if (position < routePointModelList.size()) {  //0  1   2  3      3
                    RoutePointModel routePointModel = routePointModelList.get(position);
                    if (position == 0) {
                        viaViewHolder.txt_route_via.setText(routePointModel.getTitle());
                    } else if (position > 0 && position < routePointModelList.size() - 1) {//2    3
                        if (position < routePointModelList.size() - 1) {
                            viaViewHolder.txt_route_via.setText(routePointModel.getTitle());
                        } else if (position == routePointModelList.size() - 2) {
                            viaViewHolder.txt_route_via.setText("选择途经点" + position);
                        }
                    } else if (position == routePointModelList.size() - 1
                            && routePointModelList.size() < 18) {
                        viaViewHolder.txt_route_via.setText("选择途经点" + position);
                    } else if (position == routePointModelList.size() - 1
                            && routePointModelList.size() == 18) {
                        viaViewHolder.txt_route_via.setText(routePointModel.getTitle());
                    }
                } else {
                    //途经点设置默认显示的     终点设置显示数据
                    if (0 < position && position < routePointModelList.size()) {
                        viaViewHolder.txt_route_via.setText("选择途经点" + position);
                    } else if (position == routePointModelList.size()
                            && routePointModelList.size() >= 2) {
                        RoutePointModel routePointModel =
                                routePointModelList.get(position - 1);
                        viaViewHolder.txt_route_via.setText(routePointModel.getTitle());
                    }
                }
            } else {
                viaViewHolder.txt_route_via.setText("添加开始点");
            }

            //删除开始点  途经点   结束点的按钮
            viaViewHolder.layout_roate_via.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //删除点  要保证里面有可以删除的点
                    if (routePointModelArrayList != null
                            && routePointModelArrayList.size() > 0) {
                        if (position == 0) {
                            routePointModelArrayList.remove(position);
                            //重新绘制地图
                            setLatLonPointToMap(routePointModelArrayList);
                        } else if (0 < position && position <
                                routePointModelArrayList.size() - 1) {//0 1  2        2
                            routePointModelArrayList.remove(position);
                            //重新绘制地图
                            setLatLonPointToMap(routePointModelArrayList);
                        } else if (position == routePointModelArrayList.size() - 1) {
                            //当点的集合等于18时，集合满了，可以移除当前的一项，要不然这一项为空，不做处理
                            if (routePointModelArrayList.size() == 18) {
                                routePointModelArrayList.remove(position);
                                //重新绘制地图
                                setLatLonPointToMap(routePointModelArrayList);
                            }
                        } else if (position == routePointModelArrayList.size()) {
                            if (1 < routePointModelArrayList.size()
                                    && routePointModelArrayList.size() < 18) {
                                routePointModelArrayList.remove(position - 1);
                                //重新绘制地图
                                setLatLonPointToMap(routePointModelArrayList);
                            } else if (routePointModelArrayList.size() == 18) {
                                routePointModelArrayList.remove(position);
                                //重新绘制地图
                                setLatLonPointToMap(routePointModelArrayList);
                            }
                        }
                    }
                    //删除数据后，再次显示下一个谁被选中
                    if (routePointModelArrayList != null
                            && routePointModelArrayList.size() > 0) {
                        //只有开始点时/点都满的时候
                        if (routePointModelArrayList.size() == 18 ||
                                routePointModelArrayList.size() == 1) {
                            currentShowPoint = 17;
                        } else if (routePointModelArrayList.size() < 18) {
                            currentShowPoint = routePointModelArrayList.size() - 1;
                        }
                    } else {
                        currentShowPoint = 0;
                    }
                    viaAdapter.setData(routePointModelArrayList);
                    viaAdapter.notifyDataSetChanged();

                }
            });
            return convertView;
        }

        private class ViaViewHolder {
            LinearLayout layout_roate_via;
            TextView txt_via;
            TextView txt_route_via;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_VIA_POINT) {
            if (resultCode == SEARCH_VIA_POINT_FOR_RESULT) {
                Tip tip = data.getParcelableExtra("tip");
                int currentPosition = data.getIntExtra("currentShowPoint", 1);
                currentShowPoint = currentPosition;
                if (tip != null) {
                    if (tip.getPoint() != null) {
                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new
                                LatLng(tip.getPoint().getLatitude(),
                                tip.getPoint().getLongitude())));
                        if (startMark != null && endMark != null) {
                            //解决路书有起始点和终点时   搜索回来   跳转不到搜索点的问题
                            isShowVisibleArea = false;
                        }
                        isShowActivityForResult = true;
                    } else {
                        if (currentPosition == 1) {
                            Toast.makeText(this, "请选择准确开始点",
                                    Toast.LENGTH_SHORT).show();
                        } else if (currentPosition == 5) {
                            Toast.makeText(this, "请选择准确结束点",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "请选择准确途径点",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        deactivate();
    }
}
