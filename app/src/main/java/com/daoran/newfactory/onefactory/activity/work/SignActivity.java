package com.daoran.newfactory.onefactory.activity.work;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.SignDebugBean;
import com.daoran.newfactory.onefactory.util.BitmapTools;
import com.daoran.newfactory.onefactory.util.CLApplication;
import com.daoran.newfactory.onefactory.util.CrameUtils;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * 外勤签到
 * Created by lizhipeng on 2017/3/31.
 */

public class SignActivity extends BaseFrangmentActivity implements View.OnClickListener,LocationSource,AMapLocationListener {
    private View view;

    private MapView mapView;
    private AMap aMap;
    private AMapLocationClient mapLocationClient;
    private AMapLocationClientOption mapLocationClientOption;
    private boolean isFirstLoc = true;
    private LocationSource.OnLocationChangedListener mListener ;

    private ProgressDialog progressDialog = null;//搜索时进度条
    private PoiSearch.Query query;//poi查询类
    private PoiSearch poiSearch;//搜索
    private PoiSearch.SearchBound searchBound;
    private int currentPage;//当前页面，从0开始
    private PoiResult poiResult;//poi返回的结果
    private String city = "";//搜索城市
    private int juli = 1000;
    private LatLonPoint latLonPoint;
    private Spinner spinner;


    private static final String TAG = "TAG";
//    private MapView mapView = null;
//    private BaiduMap mBaidumap;
//    private boolean isFirstLoc = true;

    private Button btnCount, btnSignCancle, btnSignOk, btnfileTune;
    private TextView tvSignAddress, tvSignDate;
    private Spinner SpinnerSign, spinnerfineTune;
    private EditText etRemark;
    private ImageView ivSignBack;
    private ImageView topBg;
    private ScrollView scrollviewSign;

    private List<SignDebugBean.DataBean> signBean = new ArrayList<SignDebugBean.DataBean>();
    private SignDebugBean dataBean;


//    private MyLocationListener mMyLocationListener = null;
//    private LocationClient mLocationClient = null;

    private int year, month, date, hour, minute, second;
    private SharedPreferences sp;
    private SPUtils spUtils;
    private CrameUtils crameUtils;
    private boolean isMe = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
        mapView = (MapView) findViewById(R.id.mapSigngaode);
        mapView.onCreate(savedInstanceState);
        if(aMap==null){
            aMap = mapView.getMap();
            UiSettings settings = aMap.getUiSettings();//设置显示定位按钮 并且可以点击
            aMap.setLocationSource(this);//设置了定位的监听,这里要实现LocationSource接口
            settings.setMyLocationButtonEnabled(true);// 是否显示定位按钮
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        getViews();
        initViews();
        init();
        sp = this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        tvSignAddress = (TextView) findViewById(R.id.tvSignAddress);
        String tvaddress = sp.getString("gmapaddress", "");
        tvSignAddress.setText(tvaddress);
    }

    /**
     * 给予控件属性及方法
     */
    private void initViews() {
        ivSignBack.setOnClickListener(this);
        btnCount.setOnClickListener(this);

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
        btnSignCancle.setOnClickListener(this);
        btnSignOk.setOnClickListener(this);
        topBg.setOnClickListener(this);
        btnfileTune.setOnClickListener(this);
        crameUtils = new CrameUtils();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        scrollviewSign = (ScrollView) findViewById(R.id.scrollviewSign);
        ivSignBack = (ImageView) findViewById(R.id.ivSignBack);
        btnCount = (Button) findViewById(R.id.btnCount);
        btnSignCancle = (Button) findViewById(R.id.btnSignCancle);
        btnSignOk = (Button) findViewById(R.id.btnSignOk);
        tvSignDate = (TextView) findViewById(R.id.tvSignDate);
        etRemark = (EditText) findViewById(R.id.etRemark);
        SpinnerSign = (Spinner) findViewById(R.id.SpinnerSign);
        topBg = (ImageView) findViewById(R.id.topBg);
        spinnerfineTune = (Spinner) findViewById(R.id.spinnerfineTune);
        btnfileTune = (Button) findViewById(R.id.btnfileTune);
        getSpinner();
        getDate();


    }

    private void init() {
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
        //给定位客户端对象设置定位参数
        mapLocationClient.setLocationOption(mapLocationClientOption);
        //启动定位
        mapLocationClient.startLocation();
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
                spUtils.put(SignActivity.this, "languages", languages[position]);
                ToastUtils.ShowToastMessage("点击的是：" + languages[position], SignActivity.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 打开系统相册，并选择图片
     */
    public void selectPic(View view) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    /**
     * 给拍的照片命名
     */
    public String createPhotoName() {
        //以系统的当前时间给图片命名
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = format.format(date) + ".jpg";
        return fileName;
    }

    /**
     * 把拍的照片保存到SD卡
     */
    public void saveToSDCard(Bitmap bitmap) {
        //先要判断SD卡是否存在并且挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(CLApplication.photoPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(createPhotoName());
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);//把图片数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            ToastUtils.ShowToastMessage("SD卡不存在", SignActivity.this);
        }
    }


    /**
     * 选择拍照的图片
     */
    public void takePhoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 1);
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
            case R.id.btnSignCancle:
                finish();
                break;
            case R.id.btnSignOk:
                ResponseDialog.showLoading(this, "请稍后");
                setSignDebug();
                break;
            case R.id.topBg:
                selectPic(view);
                break;
            case R.id.btnfileTune:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;//当data为空的时候，不做任何处理
        }
        Bitmap bitmap = null;
        if (requestCode == 0) {
            //获取从相册界面返回的缩略图
            bitmap = data.getParcelableExtra("data");
            if (bitmap == null) {//如果返回的图片不够大，就不会执行缩略图的代码，因此需要判断是否为null,如果是小图，直接显示原图即可
                try {
                    //通过URI得到输入流
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    //通过输入流得到bitmap对象
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 1) {
            bitmap = (Bitmap) data.getExtras().get("data");
            saveToSDCard(bitmap);
        }
        //将选择的图片设置到控件上
        topBg.setImageBitmap(bitmap);
        String picurl = BitmapTools.convertIconToString(bitmap);
        spUtils.put(SignActivity.this, "picurl", picurl);
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
//        if (mapView != null) {
            mapView.onDestroy();
//            mapView = null;
//        }
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
        sp = this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String recodername = sp.getString("u_name", "");
        String userna = sp.getString("username", "");
        String Latitude = sp.getString("latitude", "");
        String languages = sp.getString("languages", "");
        String picstr = sp.getString("picurl", "");
        String address = sp.getString("gmapaddress", "");
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
                            ToastUtils.ShowToastMessage("上传错误" + e, SignActivity.this);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                System.out.print(response);
                                String strresponse = String.valueOf(response.charAt(1));
                                System.out.print(strresponse);
                                if (strresponse.equals("1")) {
                                    ToastUtils.ShowToastMessage(R.string.Uploadsuccess, SignActivity.this);
                                } else {
                                    ToastUtils.ShowToastMessage(R.string.Uploadfailed, SignActivity.this);
                                    ResponseDialog.closeLoading();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignActivity.this);
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
                    buffer1.append(aMapLocation.getLatitude()+","+aMapLocation.getLongitude());

                    spUtils.put(SignActivity.this,"gmapaddress",buffer.toString());
                    spUtils.put(SignActivity.this,"latitude",buffer1.toString());
                    ToastUtils.ShowToastMessage(buffer.toString(),SignActivity.this);
                    isFirstLoc = false;
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                ToastUtils.ShowToastMessage("定位失败",SignActivity.this);
            }
        }
    }
}
