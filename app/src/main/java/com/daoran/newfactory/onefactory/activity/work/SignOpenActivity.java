package com.daoran.newfactory.onefactory.activity.work;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
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
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.SignDetailBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.util.image.BitmapTools;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by lizhipeng on 2017/6/1.
 */
@RuntimePermissions
public class SignOpenActivity extends BaseFrangmentActivity
        implements View.OnClickListener, PoiSearch.OnPoiSearchListener,
        LocationSource, AMapLocationListener, AMap.OnCameraChangeListener
        ,ActivityCompat.OnRequestPermissionsResultCallback{
    private MapView mapView;
    private AMap aMap;
    private AMapLocationClient mapLocationClient;
    private AMapLocationClientOption mapLocationClientOption;
    private boolean isFirstLoc = true;
    private double longitude;
    private double latitude;
    private LocationSource.OnLocationChangedListener mListener;
    private ProgressDialog progressDialog = null;//搜索时进度条
    private PoiSearch.Query query;//poi查询类
    private PoiSearch poiSearch;//搜索
    private PoiSearch.SearchBound searchBound;
    private List<PoiItem> poiItems;
    private int currentPage;//当前页面，从0开始
    private PoiResult poiResult;//poi返回的结果
    private String city = "";//搜索城市
    private String deepType;//搜索类型
    private int juli = 1000;
    private LatLonPoint latLonPoint;
    private GeocodeSearch geocodeSearch;
    private Spinner spinner, spinnnerfileTune;

    private static final String TAG = "TAG";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LNG = "lng";
    public static final String KEY_DES = "des";

    private String cityCode;

    private TextView tvSqltexttime;
    private LinearLayout btnSignOk;
    private Button btnCount
//            ,btnSignCancle
//            , btnSignOk
            ;
    private TextView tvSignAddress, tvSignDate;
    private Spinner SpinnerSign, spinnerfineTune;
    private EditText etRemark;
    private ImageView ivSignBack;
    private ImageView topBg;
    private ScrollView scrollviewSign;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> locationList = new ArrayList<>();
    private List<SignDetailBean.DataBean> signBean = new ArrayList<SignDetailBean.DataBean>();
    private SignDetailBean dataBean;

    private int year, month, date, hour, minute, second;
    private SharedPreferences sp;
    private SPUtils spUtils;
    private MarkerOptions mMarkerOptions;
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
    private static final int PERMISSON_REQUESTCODE = 0;
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    private static final int msgKey1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        tvSqltexttime = (TextView) findViewById(R.id.tvSqltexttime);
        new TimeThread().start();
        mapView = (MapView) findViewById(R.id.mapSigngaode);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
            UiSettings settings = aMap.getUiSettings();//设置显示定位按钮 并且可以点击
            aMap.setLocationSource(this);//设置了定位的监听,这里要实现LocationSource接口
            settings.setMyLocationButtonEnabled(true);// 是否显示定位按钮
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        setUp();
        mMarkerOptions = new MarkerOptions();
        mMarkerOptions.draggable(false);//可拖放性
        mMarkerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.guidepoint_red));
        getViews();
        initViews();
        initSpinner();
        init();
        sp = this.getSharedPreferences("my_sp", 0);
        SignOpenActivityPermissionsDispatcher.startLocationWithCheck(this);
    }

    public class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
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

    /**
     * 给予控件属性及方法
     */
    private void initViews() {
        ivSignBack.setOnClickListener(this);
        btnCount.setOnClickListener(this);
        month =month+1;
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
//        btnSignCancle.setOnClickListener(this);
        btnSignOk.setOnClickListener(this);
        topBg.setOnClickListener(this);
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        scrollviewSign = (ScrollView) findViewById(R.id.scrollviewSign);
        ivSignBack = (ImageView) findViewById(R.id.ivSignBack);
        btnCount = (Button) findViewById(R.id.btnCount);
//        btnSignCancle = (Button) findViewById(R.id.btnSignCancle);
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

    /**
     * 定位处理
     */
    private void init() {
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
            mapLocationClientOption.setInterval(2000);
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

    /**
     * 设置显示的地图模式
     */
    private void setUp() {
        aMap.setLocationSource(this);
        aMap.setOnCameraChangeListener(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17.5f));
    }

    /**
     * 遍历查询周边地址特征
     */
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
                            deepType = str[5] + "|" + str[7] + "|" + str[12] + "|" + str[17];
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

    /**
     * 根据poi搜索出的结果填充周边地址
     */
    private void initSign() {
        spinnerfineTune.setAdapter(new ArrayAdapter<>(SignOpenActivity.this, android.R.layout.simple_spinner_dropdown_item, locationList));
        spinnerfineTune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) spinnerfineTune.getSelectedItem();
                spUtils.put(SignOpenActivity.this, "addressItem", str);
                tvSignAddress = (TextView) findViewById(R.id.tvSignAddress);
                tvSignAddress.setText(str);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * poi搜索周边
     */
    private void doSearch() {
        aMap.setOnMapClickListener(null);//进行poi搜索时清除掉地图点击事件
        query = new PoiSearch.Query("", deepType, cityCode);
        PoiSearch poiSearch = new PoiSearch(this, query);
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
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(grantResults)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     * @since 2.5.0
     */
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
        if(keyCode == KeyEvent.KEYCODE_BACK){
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 签到类型适配
     */
    private void getSpinner() {
        String[] spinner = getResources().getStringArray(R.array.signSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner);
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

    /**
     * 截屏保存图片
     */
    private void getpicture(){
        aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {

            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int status) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                if(null == bitmap){
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
                        buffer.append( "地图未渲染完成，截屏有网格");
                    }
                    System.out.print(buffer.toString());
//                    ToastUtils.ShowToastMessage(buffer.toString(),getApplicationContext());
                    topBg.setImageBitmap(bitmap);
                    String picurl = BitmapTools.convertIconToString(bitmap);
                    spUtils.put(SignOpenActivity.this, "picurl", picurl);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSignBack:
                finish();
                break;
            case R.id.btnCount:
                startActivity(new Intent(SignOpenActivity.this, SignDetailActivity.class));
                break;
            case R.id.etRemark:
                etRemark.setFocusableInTouchMode(true);
                break;
//            case R.id.btnSignCancle:
//                finish();
//                break;
            case R.id.btnSignOk:
                ResponseDialog.showLoading(this, "请稍后");
                getpicture();
                setSignDebug();
                break;
            case R.id.topBg:
                break;
        }
    }

    /**
     * 适配7.0权限
     * @param
     * @since 2.5.0
     *
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     * @param permissions
     * @return
     * @since 2.5.0
     *
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
        if(isNeedCheck){
            checkPermissions(needPermissions);
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
        mapLocationClient.onDestroy();//销毁定位客户端。
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 保存签到信息
     */
    private void setSignDebug() {
        String url = HttpUrl.debugoneUrl + "OutRegister/SaveBill/";
        ResponseDialog.showLoading(this, "请稍后");
        sp = this.getSharedPreferences("my_sp", 0);
        String recodername = sp.getString("u_name", "");
        String userna = sp.getString("username", "");
        String Latitude = sp.getString("latitude", "");
        String languages = sp.getString("languages", "");
        String picstr = sp.getString("picurl", "");
        String address = sp.getString("addressItem", "");
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.post()
                    .url(url)
                    .addParams("id", "")
                    .addParams("pic", picstr)
                    .addParams("code", "")
                    .addParams("memo", etRemark.getText().toString())
                    .addParams("recorder", recodername)
                    .addParams("recorderID", userna)
                    .addParams("recordat", tvSignDate.getText().toString())
                    .addParams("recordplace", address)
                    .addParams("LNGLAT", Latitude)
                    .addParams("regedittyp", languages)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            ToastUtils.ShowToastMessage("上传错误" + e, SignOpenActivity.this);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                System.out.print(response);
                                String strresponse = String.valueOf(response.charAt(1));
                                System.out.print(strresponse);
                                if (strresponse.equals("1")) {
                                    ToastUtils.ShowToastMessage(R.string.Uploadsuccess, SignOpenActivity.this);
                                } else {
                                    ToastUtils.ShowToastMessage(R.string.Uploadfailed, SignOpenActivity.this);
                                    ResponseDialog.closeLoading();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignOpenActivity.this);
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 获取系统时间
     */
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
        tvSqltexttime.setText(hour+":"+minute+":"+second);
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
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(19));
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
                    PoiResult poiResult = result;
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
        adapter.notifyDataSetChanged();
    }
}
