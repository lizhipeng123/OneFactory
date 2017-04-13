package com.daoran.newfactory.onefactory.activity.work;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ResponseBean;
import com.daoran.newfactory.onefactory.bean.SignDebugBean;
import com.daoran.newfactory.onefactory.util.BitmapTools;
import com.daoran.newfactory.onefactory.util.CLApplication;
import com.daoran.newfactory.onefactory.util.CrameUtils;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.OkHttp;
import com.daoran.newfactory.onefactory.util.Http.RequestParams;
import com.daoran.newfactory.onefactory.util.ImageRes;
import com.daoran.newfactory.onefactory.util.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.util.xutil.HttpParams;
import com.daoran.newfactory.onefactory.util.xutil.HttpRequestCallBack;
import com.daoran.newfactory.onefactory.util.xutil.HttpUtils;
import com.daoran.newfactory.onefactory.view.CustomPhotoDialog;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.client.HttpRequest;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.NameValuePair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import okhttp3.Call;

/**
 * 外勤签到
 * Created by lizhipeng on 2017/3/31.
 */

public class SignActivity extends BaseFrangmentActivity implements View.OnClickListener, BDLocationListener {
    private View view;

    private static final String TAG = "TAG";
    private MapView mapView = null;
    private BaiduMap mBaidumap;
    private boolean isFirstLoc = true;

    private Button btnCount, btnSignCancle, btnSignOk;
    private TextView tvSignAddress, tvSignDate;
    private Spinner SpinnerSign;
    private EditText etRemark;
    private ImageView ivSignBack, ivClickMarke;
    private ImageView topBg;
    private List<SignDebugBean.DataBean> signBean = new ArrayList<SignDebugBean.DataBean>();
    private SignDebugBean dataBean;
    private ResponseBean responseBean;
    private MyLocationListener mMyLocationListener = null;
    private LocationClient mLocationClient = null;

    private int year, month, date, hour, minute, second;
    private SharedPreferences sp;
    private SPUtils spUtils;
    private CrameUtils crameUtils;
    public String path = "";
    private boolean isMe = false;
    private int id;


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
        mMyLocationListener = new MyLocationListener();
        location(mMyLocationListener);
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
        ivClickMarke.setOnClickListener(this);
        btnSignCancle.setOnClickListener(this);
        btnSignOk.setOnClickListener(this);
        topBg.setOnClickListener(this);
        crameUtils = new CrameUtils();
    }


    private void getViews() {
        ivSignBack = (ImageView) findViewById(R.id.ivSignBack);
        mapView = (MapView) findViewById(R.id.mapSign);
        btnCount = (Button) findViewById(R.id.btnCount);
        btnSignCancle = (Button) findViewById(R.id.btnSignCancle);
        btnSignOk = (Button) findViewById(R.id.btnSignOk);
        tvSignAddress = (TextView) findViewById(R.id.tvSignAddress);
        tvSignAddress.setText("杭州市下城区新天地街");
        tvSignDate = (TextView) findViewById(R.id.tvSignDate);
        etRemark = (EditText) findViewById(R.id.etRemark);
        SpinnerSign = (Spinner) findViewById(R.id.SpinnerSign);
        ivClickMarke = (ImageView) findViewById(R.id.ivClickMarke);
        topBg = (ImageView) findViewById(R.id.topBg);
        getSpinner();
        getDate();


    }

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

    public void location(BDLocationListener listener) {
        mLocationClient = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);// 设置定位模式
        option.setScanSpan(5 * 60000);//检查周期 小于1秒的按1秒
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(listener);
        mLocationClient.start();
    }

    private void setListener() {

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
            case R.id.ivClickMarke:
                if (mLocationClient.isStarted()) {
                    mLocationClient.stop();
                }
                mLocationClient.start();
                break;
            case R.id.btnSignCancle:
                finish();
                break;
            case R.id.btnSignOk:
                setSignDebug();
//                mBaidumap.snapshot(new BaiduMap.SnapshotReadyCallback() {
//                    @Override
//                    public void onSnapshotReady(Bitmap bitmap) {
//                        File file = new File("/mnt/sdcard/test.png");
//                        FileOutputStream out;
//                        try {
//                            out = new FileOutputStream(file);
//                            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
//                                out.flush();
//                                out.close();
//                            }
//                            ToastUtils.ShowToastMessage("屏幕截图成功，保存在：" + file.toString(), SignActivity.this);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
////                Rect rect = new Rect(0, 0, 300, 300);//左xy,右xy
////                mBaidumap.snapshotScope(rect, new BaiduMap.SnapshotReadyCallback() {
////                    @Override
////                    public void onSnapshotReady(Bitmap bitmap) {
////                        File file = new File("/mnt/sdcard/testall300.png");
////                        FileOutputStream out;
////                        try {
////                            out = new FileOutputStream(file);
////                            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
////                                out.flush();
////                                out.close();
////                            }
////                            ToastUtils.ShowToastMessage("屏幕截图成功，保存在：" + file.toString(), SignActivity.this);
////                        } catch (FileNotFoundException e) {
////                            e.printStackTrace();
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                });
//                ToastUtils.ShowToastMessage("正在截取屏幕图片....", SignActivity.this);
//                setSignDebug();
                break;
            case R.id.topBg:
                selectPic(view);
//                changebg();
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
        if (mapView != null) {
            mapView.onDestroy();
            mapView = null;
        }
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(SignActivity.this);
        }
    }

    private void setSignDebug() {
        String url = HttpUrl.debugoneUrl + "OutRegister/SaveBill/";
        sp = this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
//        Bundle bundle = this.getIntent().getExtras();
//        String recodername = sp.getString("u_name");
        String userna = sp.getString("username", "");
        String Latitude = sp.getString("Latitude", "");
        String Longitude = sp.getString("Longitude", "");
        String languages = sp.getString("languages", "");
        String picstr = sp.getString("picurl", "");

        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this, "请稍后");
            OkHttpUtils.post()
                    .url(url)
                    .addParams("id", "")
                    .addParams("pic", picstr)
                    .addParams("code", "")
                    .addParams("memo", etRemark.getText().toString())
                    .addParams("recorder", userna)
                    .addParams("recorderID", "")
                    .addParams("recordat", "2017/3/3 19:00:25")
                    .addParams("recordplace", tvSignAddress.getText().toString())
                    .addParams("LNGLAT", Latitude + "" + Longitude)
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
                            System.out.print(response);
                            JSONObject jsonObject = new JSONObject
                                    (Boolean.parseBoolean(response.toString()))
                                    .getJSONObject("response");
                            Gson gson = new Gson();
                            responseBean = gson.fromJson(response,ResponseBean.class);
                            if(responseBean.getResponse()=="1"){
                                ToastUtils.ShowToastMessage("上传成功",SignActivity.this);

                            }else{
                                ToastUtils.ShowToastMessage("失败",SignActivity.this);
                            }
//                            if(response.equals("1")){
//                                ToastUtils.ShowToastMessage("上传成功",SignActivity.this);
//                            }else{
//                                ToastUtils.ShowToastMessage("失败",SignActivity.this);
//                            }
                            ToastUtils.ShowToastMessage("上传进来了", SignActivity.this);
                        }
                    });

//            HttpParams httpParams = new HttpParams(url);
//            String urll = httpParams.toString();
//            HttpUtils httpUtils = HttpUtils.getInstance();
//            HttpParams hp = new HttpParams(url);
//            hp.addBodyParameter("id", "");
//            hp.addBodyParameter("pic", new File(pic));
//            hp.addBodyParameter("code", "");
//            hp.addBodyParameter("memo", etRemark.getText().toString());
//            hp.addBodyParameter("recorder", userna);
//            hp.addBodyParameter("recorderID", "");
//            hp.addBodyParameter("recordat", tvSignDate.getText().toString());
//            hp.addBodyParameter("recordplace", tvSignAddress.getText().toString());
//            hp.addBodyParameter("LNGLAT", Latitude + Longitude);
//            hp.addBodyParameter("regedittyp", languages);
//            httpUtils.send(HttpRequest.HttpMethod.POST, urll, hp, new HttpRequestCallBack<String>(String.class, this, "正在修改") {
//                @Override
//                protected void onSuccess(String s) {
//                    JSONObject jsonObject = JSONObject.parseObject(s);
//                    if (s.contains("1")) {
//                        if (jsonObject.containsKey("Data")) {
//                            String jsonStr = jsonObject.getString("Data");
//                            dataBean = JSON.parseObject(jsonStr, SignDebugBean.class);
//                            Picasso.with(SignActivity.this).load(signBean.get(0).getPicpath());
//                            ToastUtils.ShowToastMessage("更换成功", SignActivity.this);
//                        }
//
//                    } else {
//                        ToastUtils.ShowToastMessage("更换失败", SignActivity.this);
//                    }
//                }
//
//                @Override
//                public void onFailure(HttpException arg0, String arg1) {
//                    super.onFailure(arg0, arg1);
//                    ToastUtils.ShowToastMessage("更换失败", SignActivity.this);
//                }
//            });


//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(NetUtil.createParam("id", ""));
//            params.add(NetUtil.createParam("picpath",picstr));
//            params.add(NetUtil.createParam("code", ""));
//            params.add(NetUtil.createParam("memo", etRemark.getText().toString()));
//            params.add(NetUtil.createParam("recorder", userna));
//            params.add(NetUtil.createParam("recorderID", ""));
//            params.add(NetUtil.createParam("recordat", "2017/3/3 19:00:25"));
//            params.add(NetUtil.createParam("recordplace", tvSignAddress.getText().toString()));
//            params.add(NetUtil.createParam("LNGLAT", Latitude+""+Longitude));
//            params.add(NetUtil.createParam("regedittyp", languages));
//            RequestParams requestParams = new RequestParams(params);
//            NetUtil.getAsyncHttpClient().post(url, requestParams, new AsyncHttpResponseHandler() {
//                @Override
//                public void onSuccess(String content) {
//                    super.onSuccess(content);
//                    try {
//                        System.out.print(content);
//                        if (content == "1") {
//                            System.out.print(content);
//                            ToastUtils.ShowToastMessage("上传成功" + content, SignActivity.this);
//                        } else {
//                            ToastUtils.ShowToastMessage("上传失败", SignActivity.this);
//                            ResponseDialog.closeLoading();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Throwable error, String content) {
//                    super.onFailure(error, content);
//                    ToastUtils.ShowToastMessage("上传失败"+content,SignActivity.this);
//                    error.printStackTrace();
//                    ResponseDialog.closeLoading();
//                }
//
//                @Override
//                public void onFinish() {
//                    super.onFinish();
//                }
//            });

        } else {
            ToastUtils.ShowToastMessage("当前网络不可用，请重试", SignActivity.this);
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
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
                spUtils.put(SignActivity.this, "Latitude", location.getLatitude() + "");
                spUtils.put(SignActivity.this, "Longitude", location.getLongitude() + "");
                ToastUtils.ShowToastMessage(location.getAddrStr(), getApplicationContext());

            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null) {
            return;
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
            tvSignAddress.setText("guale");
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
