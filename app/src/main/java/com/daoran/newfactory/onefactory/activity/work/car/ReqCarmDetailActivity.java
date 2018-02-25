package com.daoran.newfactory.onefactory.activity.work.car;

import android.content.Context;
import android.os.Bundle;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.reqcarbean.ReqCarDetailBean;
import com.daoran.newfactory.onefactory.bean.reqcarbean.ReqCarNumberBindBean;
import com.daoran.newfactory.onefactory.bean.reqcarbean.ReqCarDriverBindBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.JsonUtil;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 出车单展示
 * Created by lizhipeng on 2017/3/24.
 */

public class ReqCarmDetailActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private EditText etStartDataClick, etEndDataClick;
    private int id;
    private Spinner spinnerdriver, spinnerNumberBind;
    private List<ReqCarDriverBindBean> bindBeen = new ArrayList<ReqCarDriverBindBean>();//司机集合
    private List<ReqCarNumberBindBean> bindBeencar = new ArrayList<ReqCarNumberBindBean>();//车牌集合
    private ReqCarDetailBean reqCarDetailBean;
    private ImageView ivBack;//返回按钮
    private TextView tvCarcode,//编号
            tvCarrecorder,//申请人
            tvCarroad,//地点
            tvCarrecordt,//申请日期
            tvCarreason,//事由
            tvCardepartureBdt,//预计出车时间
            tvCardepartureEdt;//预计结束时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carapply);
        getViews();
        initViews();
        setCardetail();
        setDriverBind();
        setCarNumberBind();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        id = getIntent().getIntExtra("id", 0);
        etStartDataClick = (EditText) findViewById(R.id.etStartDataClick);
        etEndDataClick = (EditText) findViewById(R.id.etEndDataClick);
        spinnerdriver = (Spinner) findViewById(R.id.spinnerdriver);
        spinnerNumberBind = (Spinner) findViewById(R.id.spinnerNumberBind);
        tvCarcode = (TextView) findViewById(R.id.tvCarcode);
        tvCarrecorder = (TextView) findViewById(R.id.tvCarrecorder);
        tvCarroad = (TextView) findViewById(R.id.tvCarroad);
        tvCarrecordt = (TextView) findViewById(R.id.tvCarrecordt);
        tvCarreason = (TextView) findViewById(R.id.tvCarreason);
        tvCardepartureBdt = (TextView) findViewById(R.id.tvCardepartureBdt);
        tvCardepartureEdt = (TextView) findViewById(R.id.tvCardepartureEdt);
        ivBack = (ImageView) findViewById(R.id.ivBack);
    }

    /**
     * 操作控件
     */
    private void initViews() {
        etStartDataClick.setOnClickListener(this);
        etEndDataClick.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    private void showEditClickPopupWindow() {
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
    }

    /**
     * 根据id查询用车详细信息
     */
    private void setCardetail() {
        String str = HttpUrl.debugoneUrl + "UCarsApply/GetUCarsApplyModel/" + id;
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this);
            NetUtil.getAsyncHttpClient().post(str, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    reqCarDetailBean = new Gson().fromJson(content, ReqCarDetailBean.class);//json数据解析转换
                    tvCarcode.setText(reqCarDetailBean.getCode());//用车单编号
                    tvCarrecorder.setText(reqCarDetailBean.getRecorder());//制单人
                    tvCarroad.setText(reqCarDetailBean.getRoad());//地点
                    tvCarrecordt.setText(reqCarDetailBean.getRecordt());//制单时间
                    tvCarreason.setText(reqCarDetailBean.getReason());//事由
                    tvCardepartureBdt.setText(reqCarDetailBean.getDepartureBdt());//出发时间
                    tvCardepartureEdt.setText(reqCarDetailBean.getDepartureEdt());//回来时间
                    ResponseDialog.closeLoading();
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                    ResponseDialog.closeLoading();
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    ResponseDialog.closeLoading();
                }
            });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用，请稍后再试", ReqCarmDetailActivity.this);
        }
    }

    /**
     * 查询司机
     */
    private void setDriverBind() {
        String strDriver = HttpUrl.debugoneUrl + "UCarsExamine/DriverBind/";
        if (NetWork.isNetWorkAvailable(this)) {
            NetUtil.getAsyncHttpClient().post(strDriver, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    String ress = content.replace("\\", "");
                    String ression = StringUtil.sideTrim(ress, "\"");
                    bindBeen = JsonUtil.stringToList(ression, ReqCarDriverBindBean.class);
                    String[] spinnerr = getResources().getStringArray(R.array.driver);//加载数据数组
                    ArrayAdapter<String> adapter = new
                            ArrayAdapter<String>(ReqCarmDetailActivity.this,
                            android.R.layout.simple_spinner_item, spinnerr);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerdriver.setAdapter(adapter);//绑定数据源
                    spinnerdriver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String[] languages = getResources().getStringArray(R.array.driver);
//                                    ToastUtils.ShowToastMessage("点击的是"+languages[position],ReqCarmDetailActivity.this);
//                                    .put(ReqCarmDetailActivity.this, "languages", languages[position]);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                }
            });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ReqCarmDetailActivity.this);
        }
    }

    /**
     * 查询车牌
     */
    private void setCarNumberBind() {
        String strCarNumber = HttpUrl.debugoneUrl + "UCarsExamine/CarNumberBind/";
        if (NetWork.isNetWorkAvailable(this)) {
            NetUtil.getAsyncHttpClient().post(strCarNumber, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    String ress = content.replace("\\", "");
                    String ression = StringUtil.sideTrim(ress, "\"");
                    bindBeencar = JsonUtil.stringToList(ression, ReqCarNumberBindBean.class);
                    String[] spinnerr = getResources().getStringArray(R.array.CarNumberBind);
                    ArrayAdapter<String> adapter = new
                            ArrayAdapter<String>(ReqCarmDetailActivity.this, android.R.layout.simple_spinner_item, spinnerr);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerNumberBind.setAdapter(adapter);
                    spinnerNumberBind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String[] languages = getResources().getStringArray(R.array.CarNumberBind);
//                                    ToastUtils.ShowToastMessage("点击的是"+languages[position],ReqCarmDetailActivity.this);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }
            });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ReqCarmDetailActivity.this);
        }
    }

    /**
     * 获取SD可用容量
     *
     * @param context
     * @return
     */
    private static long getAvailableStorage(Context context) {
        String root = context.getExternalFilesDir(null).getPath();
        StatFs statFs = new StatFs(root);
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        long availableSize = blockSize * availableBlocks;
        return availableSize;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etStartDataClick:
                showEditClickPopupWindow();
                break;
            /*返回*/
            case R.id.ivBack:
                finish();
                break;
        }
    }
}