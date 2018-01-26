package com.daoran.newfactory.onefactory.activity.work;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.image.BitmapTools;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 外勤签到
 * Created by lizhipeng on 2017/6/1.
 */
@RuntimePermissions
public class SignOpenActivity extends BaseFrangmentActivity
        implements View.OnClickListener, PoiSearch.OnPoiSearchListener,
        LocationSource, AMapLocationListener, AMap.OnCameraChangeListener
        , ActivityCompat.OnRequestPermissionsResultCallback {
    private MapView mapView;//地图控件
    private AMap aMap;//初始化地图控制器对象
    private AMapLocationClient mapLocationClient;//声明aMapLocationClient对象
    private AMapLocationClientOption mapLocationClientOption;//声明AMapLocationClientOption对象
    private LocationSource.OnLocationChangedListener mListener;
    private PoiSearch.Query query;//poi查询类
    private PoiSearch poiSearch;//搜索
    private PoiResult poiResult;//poi返回的结果
    private String deepType;//搜索类型
    private Spinner spinnnerfileTune;
    private MyLocationStyle myLocationStyle;
    private TextView tvSqltexttime;
    private LinearLayout btnSignOk;//声明签到按钮
    private Button btnCount;//签到统计按钮
    private TextView tvSignAddress, tvSignDate;//声明显示的签到地址与签到日期控件
    private Spinner SpinnerSign, spinnerfineTune;//声明spinner地址控件
    private EditText etRemark;//声明备注控件
    private ImageView ivSignBack;//声明返回控件
    private ImageView topBg;
    private ScrollView scrollviewSign;
    private ProgressDialog progressDialog;

    private static final String TAG = "TAG";
    //线程定位状态
    public static final String KEY_LAT = "lat";
    public static final String KEY_LNG = "lng";
    public static final String KEY_DES = "des";

    private boolean isFirstLoc = true;
    private double longitude;//初始化经纬度
    private double latitude;
    private String cityCode;//城市名
    private int year, month, date, hour, minute, second;
    String strprolong1;
    private MarkerOptions mMarkerOptions;//初始化可标记的点
    private ArrayAdapter<String> adapter;
    private ArrayList<String> locationList = new ArrayList<>();
    private SharedPreferences sp;//轻量级存储框架
    private SPUtils spUtils;

    /*判断是否需要检测，防止不停的弹框*/
//    private boolean isNeedCheck = true;
    private static final int msgKey1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        sp = this.getSharedPreferences("my_sp", 0);
        progressDialog = ProgressDialog.show(this,
                "请稍候...", "初始化中...", false, true);
        tvSqltexttime = (TextView) findViewById(R.id.tvSqltexttime);
        new TimeThread().start();//初始化启动时间线程
        //获取地图控件的引用
        mapView = (MapView) findViewById(R.id.mapSigngaode);
        //activity执行oncreate的时候执行mapview。创建地图
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
            UiSettings settings = aMap.getUiSettings();//设置显示定位按钮 并且可以点击
            aMap.setLocationSource(this);//设置了定位的监听,这里要实现LocationSource接口
            settings.setMyLocationButtonEnabled(true);// 是否显示定位按钮
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
            myLocationStyle = new MyLocationStyle();
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
            aMap.setMyLocationStyle(myLocationStyle);
        }
        setUp();//设置显示的地图模式
        mMarkerOptions = new MarkerOptions();
        mMarkerOptions.draggable(false);//可拖放性
        mMarkerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.guidepoint_red));
        getViews();//初始化控件
        initViews();
        initSpinner();//初始化下拉控件并且加载数据
        SignOpenActivityPermissionsDispatcher.startLocationWithCheck(this);
    }

    public class TimeThread extends Thread {
        @Override
        public void run() {
            super.run();
            do {
                try {
                    Thread.sleep(500);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    /*线程计时（时分秒）*/
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    tvSqltexttime.setText(format.format(date));
                    break;
                default:
                    break;
            }
        }
    };

    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    void startLocation() {
        //启动定位
        mapLocationClient.startLocation();
    }

    /*初始化控件*/
    private void initViews() {
        ivSignBack.setOnClickListener(this);
        btnCount.setOnClickListener(this);
        month = month + 1;
        tvSignDate.setText(String.valueOf(year + "/" + month + "/" + date));
        View v = mapView.getChildAt(0);
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
        etRemark.setOnClickListener(this);
        btnSignOk.setOnClickListener(this);
        topBg.setOnClickListener(this);
    }

    /*实例化控件*/
    private void getViews() {
        scrollviewSign = (ScrollView) findViewById(R.id.scrollviewSign);
        tvSignAddress = (TextView) findViewById(R.id.tvSignAddress);
        ivSignBack = (ImageView) findViewById(R.id.ivSignBack);
        btnCount = (Button) findViewById(R.id.btnCount);
        btnSignOk = (LinearLayout) findViewById(R.id.btnSignOk);
        tvSignDate = (TextView) findViewById(R.id.tvSignDate);
        etRemark = (EditText) findViewById(R.id.etRemark);
        SpinnerSign = (Spinner) findViewById(R.id.SpinnerSign);
        topBg = (ImageView) findViewById(R.id.topBg);
        spinnerfineTune = (Spinner) findViewById(R.id.spinnerfineTune);
        spinnnerfileTune = (Spinner) findViewById(R.id.spinnnerfileTune);
        getSpinner();
        getDate();
    }

    /*设置显示的地图模式*/
    private void setUp() {
        aMap.setLocationSource(this);
        aMap.setOnCameraChangeListener(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17.5f));
    }

    /*遍历查询周边地址特征*/
    private void initSpinner() {
        final String[] str = new String[]{"默认的地址类型", "汽车服务", "汽车销售", "汽车维修",
                "摩托车服务", "餐饮服务", "购物服务", "生活服务", "体育休闲服务", "医疗保健服务",
                "住宿服务", "风景名胜", "商务住宅", "政府机构及社会团体", "科教文化服务", "交通设施服务",
                "金融保险服务", "公司企业", "道路附属设施", "地名地址信息", "公共设施"};

        spinnnerfileTune.setAdapter(new ArrayAdapter<>(SignOpenActivity.this, android.R.layout.simple_spinner_dropdown_item, str));
        spinnnerfileTune.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            deepType = str[17];
                        } else {
                            deepType = (String) spinnnerfileTune.getSelectedItem();
                        }
                        doSearch();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );
    }

    /*根据poi搜索出的结果填充周边地址*/
    private void initSign() {
        spinnerfineTune.setAdapter(new ArrayAdapter<>(SignOpenActivity.this, android.R.layout.simple_spinner_dropdown_item, locationList));
        spinnerfineTune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) spinnerfineTune.getSelectedItem();
                spUtils.put(SignOpenActivity.this, "addressItem", str);
                tvSignAddress.setText(str);
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                });
                thread1.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*poi搜索周边*/
    private void doSearch() {
        aMap.setOnMapClickListener(null);//进行poi搜索时清除掉地图点击事件
        query = new PoiSearch.Query("", deepType, cityCode);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        query.setPageSize(30);// 设置每页最多返回多少条数据
        query.setPageNum(0);//设置查询页码
        LatLonPoint lp = new LatLonPoint(latitude, longitude);
        poiSearch.setBound(new PoiSearch.SearchBound(lp, 1000, true));//设置周边搜索的中心点以及半径
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SignOpenActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /*显示提示信息*/
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);
        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*启动应用的设置*/
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /*签到类型适配*/
    private void getSpinner() {
        String[] spinner = getResources().getStringArray(R.array.signSpinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerSign.setAdapter(adapter);
        SpinnerSign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.signSpinner);
                spUtils.put(SignOpenActivity.this, "languages", languages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*截屏保存图片之后上传签到信息*/
    private void getpicture() {
        aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {
            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int status) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                if (null == bitmap) {
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + "/test_"
                                    + sdf.format(new Date()) + ".png");
                    boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
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
                    StringBuffer buffer = new StringBuffer();
                    if (b)
                        buffer.append("截屏成功 ");
                    else {
                        buffer.append("截屏失败 ");
                    }
                    if (status != 0)
                        buffer.append("地图渲染完成，截屏无网格");
                    else {
                        buffer.append("地图未渲染完成，截屏有网格");
                    }
                    System.out.print(buffer.toString());
                    topBg.setImageBitmap(bitmap);
                    String picurl = BitmapTools.convertIconToString(bitmap);
                    spUtils.put(SignOpenActivity.this, "picurl", picurl);
                    setSignSave();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回*/
            case R.id.ivSignBack:
                sethideSoft(v);
                finish();
                break;
            /*跳转到签到查询*/
            case R.id.btnCount:
                sethideSoft(v);
                startActivity(new Intent(SignOpenActivity.this, SignDetailActivity.class));
                break;
            /*备注输入框*/
            case R.id.etRemark:
                etRemark.setFocusableInTouchMode(true);
                break;
            /*签到按钮，目前十分钟之内不能重复签到*/
            case R.id.btnSignOk:
                sethideSoft(v);
                if (TextUtils.isEmpty(tvSignAddress.getText())) {
                    ToastUtils.ShowToastMessage("签到失败，请确认定位成功后再进行签到", this);
                    break;
                } else {
                    long prolongtime = sp.getLong("prolongtime", 0);
                    int prolong = (int) prolongtime;
                    if (prolong == 0) {//第一次点击，初始化为本次单机的时间
                        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
                        strprolong1 = formatData("yyyy-MM-dd HH:mm:ss", time);
                        spUtils.put(SignOpenActivity.this, "prolongtime", time);
                        getpicture();
                        break;
                    } else {
                        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
                        String strprolong = formatData("yyyy-MM-dd HH:mm:ss", time);
                        String strpro = formatData("yyyy-MM-dd HH:mm:ss", prolongtime);
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            Date df1 = df.parse(strprolong);
                            Date df2 = df.parse(strpro);
                            long fiff = df1.getTime() - df2.getTime();
                            long days = fiff / (1000 * 60 * 60 * 24);//日
                            long hours = (fiff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);//时
                            long minutes = (fiff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);//分
                            int minu = (int) minutes;
                            if (minu < 5) {
                                ToastUtils.ShowToastMessage("五分钟之内不能重复签到", SignOpenActivity.this);
                                ResponseDialog.closeLoading();
                            } else {
                                getpicture();
                                spUtils.put(SignOpenActivity.this, "prolongtime", time);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            case R.id.topBg:
                break;
        }
    }

    /*判断软键盘是否弹出*/
    private void sethideSoft(View v) {
        //判断软件盘是否弹出
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                        0);
            } else {
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                        0);
            }
        }
    }

    /*转换格式*/
    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();//重新绘制加载地图
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();//暂停地图的绘制
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();//销毁地图
            mapView = null;
        }
        mapLocationClient.onDestroy();//退出本页面时销毁定位客户端。
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);//保存地图的当前状态
    }

    /*保存签到信息*/
    private void setSignSave() {
        String url = HttpUrl.debugoneUrl + "OutRegister/SaveBill/";
        sp = this.getSharedPreferences("my_sp", 0);
        String recodername = sp.getString("name", "");
        String userna = sp.getString("username", "");
        String Latitude = sp.getString("latitude", "");
        String languages = sp.getString("languages", "");
        String picstr = sp.getString("picurl", "");
        String address = sp.getString("addressItem", "");
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this, "正在签到");
            OkHttpUtils.post()
                    .url(url)
                    .addParams("id", "")//id
                    .addParams("pic", picstr)//地图截图
                    .addParams("code", "")//
                    .addParams("memo", etRemark.getText().toString())///备注
                    .addParams("recorder", recodername)//签到人(制单人)
                    .addParams("recorderID", userna)//签到人id
                    .addParams("recordat", tvSignDate.getText().toString())//签到时间
                    .addParams("recordplace", address)//签到地址
                    .addParams("LNGLAT", Latitude)//经纬度
                    .addParams("regedittyp", languages)//签到类型
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            ToastUtils.ShowToastMessage("上传错误" + e, SignOpenActivity.this);
                            ResponseDialog.closeLoading();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                String strresponse = String.valueOf(response.charAt(1));
                                if (strresponse.equals("1")) {
                                    ToastUtils.ShowToastMessage(R.string.signloadsuccess, SignOpenActivity.this);
                                } else {
                                    ToastUtils.ShowToastMessage(R.string.signloadfailed, SignOpenActivity.this);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ResponseDialog.closeLoading();
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignOpenActivity.this);
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mapLocationClient == null) {
            mapLocationClient = new AMapLocationClient(getApplicationContext());
            mapLocationClient.setLocationListener(this);
            //初始化定位参数
            mapLocationClientOption = new AMapLocationClientOption();
            //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置是否返回地址信息（默认返回地址信息）
            mapLocationClientOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            mapLocationClientOption.setOnceLocation(false);
            //设置是否强制刷新WIFI，默认为强制刷新
            mapLocationClientOption.setWifiActiveScan(true);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            mapLocationClientOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,默认为2000ms
            mapLocationClientOption.setInterval(1000);
            //设置定位回调监听
            mapLocationClient.setLocationListener(new AMapLocationListener() {

                @Override
                public void onLocationChanged(AMapLocation amapLocation) {
                    if (amapLocation != null) {
                        if (amapLocation.getErrorCode() == 0) {
                            //解析定位结果
                            Log.i(TAG, amapLocation.getPoiName());
                            latitude = amapLocation.getLatitude();
                            longitude = amapLocation.getLongitude();
                            cityCode = amapLocation.getCity();
                            doSearch();
                            mapLocationClient.stopLocation();
                        } else if (
                                amapLocation.getErrorCode() == 12) {
                            Toast.makeText(getApplicationContext(), "权限不足，请在设置中授予相应权限", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "error code = " + amapLocation.getErrorCode());
                        }
                    }
                }
            });
            //给定位客户端对象设置定位参数
            mapLocationClient.setLocationOption(mapLocationClientOption);
            //启动定位
            mapLocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /*获取系统时间*/
    private void getDate() {
        Time t = new Time(); // or Time t=new Time("GMT+8");
        t.setToNow(); // 取得系统时间。
        year = t.year;
        month = t.month;
        date = t.monthDay;
        hour = t.hour; // 0-23
        minute = t.minute;
        second = t.second;
        tvSqltexttime = (TextView) findViewById(R.id.tvSqltexttime);
        tvSqltexttime.setText(hour + ":" + minute + ":" + second);
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
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new
                            LatLng(aMapLocation.getLatitude(),
                            aMapLocation.getLongitude())));
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
                    spUtils.put(SignOpenActivity.this, "latitude", buffer1.toString());
                    isFirstLoc = false;
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                ToastUtils.ShowToastMessage("定位失败", SignOpenActivity.this);
            }
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        locationList.clear();
        if (rCode == 1000) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    ArrayList<PoiItem> poiItems = poiResult.getPois();
                    if (poiItems != null && poiItems.size() > 0) {
                        for (PoiItem p : poiItems) {
                            Log.i(TAG, "getTitle = " + p.getTitle());
                            locationList.add(p.getTitle() + "(" + p.getSnippet() + ")");
                            String snippet = p.getSnippet();
                            spUtils.put(SignOpenActivity.this, "snippet", snippet);
                            initSign();
                        }
                    }
                } else {
                    Log.e(TAG, "无结果");
                }
            }
        } else if (rCode == 27) {
            Log.e(TAG, "error_network");
        } else if (rCode == 32) {
            Log.e(TAG, "error_key");
        } else {
            Log.e(TAG, "error_other：" + rCode);
        }
    }
}