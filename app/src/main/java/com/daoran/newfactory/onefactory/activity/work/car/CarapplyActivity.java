package com.daoran.newfactory.onefactory.activity.work.car;

import android.content.Context;
import android.os.Bundle;
import android.os.StatFs;
import android.support.v7.widget.Toolbar;
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
import com.daoran.newfactory.onefactory.bean.CarDetailBean;
import com.daoran.newfactory.onefactory.bean.CarNumberBindBean;
import com.daoran.newfactory.onefactory.bean.DriverBindBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.JsonUtil;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 出车单展示
 * Created by lizhipeng on 2017/3/24.
 */

public class CarapplyActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private EditText etStartDataClick, etEndDataClick;
    private int id;
    private Spinner spinnerdriver, spinnerNumberBind;
    private List<DriverBindBean> bindBeen = new ArrayList<DriverBindBean>();
    private List<CarNumberBindBean> bindBeencar = new ArrayList<CarNumberBindBean>();
    private CarDetailBean carDetailBean;
    private ImageView ivBack;
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
        setListener();
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

    private void setListener() {}

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
                    System.out.print(content);
                    carDetailBean = new Gson().fromJson(content, CarDetailBean.class);
                    System.out.print(carDetailBean);
                    tvCarcode.setText(carDetailBean.getCode());
                    tvCarrecorder.setText(carDetailBean.getRecorder());
                    tvCarroad.setText(carDetailBean.getRoad());
                    tvCarrecordt.setText(carDetailBean.getRecordt());
                    tvCarreason.setText(carDetailBean.getReason());
                    tvCardepartureBdt.setText(carDetailBean.getDepartureBdt());
                    tvCardepartureEdt.setText(carDetailBean.getDepartureEdt());
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
            ToastUtils.ShowToastMessage("当前网络不可用，请稍后再试", CarapplyActivity.this);
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
                    System.out.print(content);
                    String ress = content.replace("\\", "");
                    System.out.print(ress);
                    String ression = StringUtil.sideTrim(ress, "\"");
                    System.out.print(ression);
                    bindBeen = JsonUtil.stringToList(ression, DriverBindBean.class);
                    System.out.print(bindBeen);
                    String[] spinnerr = getResources().getStringArray(R.array.driver);
                    ArrayAdapter<String> adapter = new
                            ArrayAdapter<String>(CarapplyActivity.this, android.R.layout.simple_spinner_item, spinnerr);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerdriver.setAdapter(adapter);
                    spinnerdriver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String[] languages = getResources().getStringArray(R.array.driver);
//                                    ToastUtils.ShowToastMessage("点击的是"+languages[position],CarapplyActivity.this);
//                                    .put(CarapplyActivity.this, "languages", languages[position]);
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
            ToastUtils.ShowToastMessage(R.string.noHttp, CarapplyActivity.this);
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
                    System.out.print(content);
                    String ress = content.replace("\\", "");
                    System.out.print(ress);
                    String ression = StringUtil.sideTrim(ress, "\"");
                    System.out.print(ression);
                    bindBeencar = JsonUtil.stringToList(ression, CarNumberBindBean.class);
                    System.out.print(bindBeencar);
                    String[] spinnerr = getResources().getStringArray(R.array.CarNumberBind);
                    ArrayAdapter<String> adapter = new
                            ArrayAdapter<String>(CarapplyActivity.this, android.R.layout.simple_spinner_item, spinnerr);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerNumberBind.setAdapter(adapter);
                    spinnerNumberBind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String[] languages = getResources().getStringArray(R.array.CarNumberBind);
//                                    ToastUtils.ShowToastMessage("点击的是"+languages[position],CarapplyActivity.this);
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
            ToastUtils.ShowToastMessage(R.string.noHttp, CarapplyActivity.this);
        }
    }

    /**
     * 获取SD可用容量
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
            case R.id.ivBack:
                finish();
                break;
        }
    }
}