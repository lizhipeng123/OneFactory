package com.daoran.newfactory.onefactory.activity.work;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.CarNumberBindBean;
import com.daoran.newfactory.onefactory.bean.DriverBindBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * 出车单展示
 * Created by lizhipeng on 2017/3/24.
 */

public class CarapplyActivity extends BaseFrangmentActivity implements View.OnClickListener {

    private Toolbar tbarCarapply;
    private EditText etStartDataClick, etEndDataClick;
    private int id;
    private Spinner spinnerdriver;

    private ArrayList<DriverBindBean> bindBeen = new ArrayList<DriverBindBean>();
    private ArrayList<CarNumberBindBean> bindBeencar = new ArrayList<CarNumberBindBean>();
    private DriverBindBean driverBindBean;
    private List<CarNumberBindBean> numberBindBeen;
    private CarNumberBindBean carNumberBindBean;
    private String[] attr;

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

    private void getViews() {
        id = getIntent().getIntExtra("id", 0);
        tbarCarapply = (Toolbar) findViewById(R.id.tbarCarapply);
        etStartDataClick = (EditText) findViewById(R.id.etStartDataClick);
        etEndDataClick = (EditText) findViewById(R.id.etEndDataClick);
        spinnerdriver = (Spinner) findViewById(R.id.spinnerdriver);
    }

    private void initViews() {
        tbarCarapply.setNavigationIcon(R.mipmap.button_cross);
        tbarCarapply.setTitle("");
        etStartDataClick.setOnClickListener(this);
        etEndDataClick.setOnClickListener(this);
    }

    private void setListener() {
        tbarCarapply.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        String str = HttpUrl.debugoneUrl + "UCarsApply/GetUCarsApplyModel/"+id;

        if (NetWork.isNetWorkAvailable(this)) {
            NetUtil.getAsyncHttpClient().get(str,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    System.out.print(content);
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
        }
    else {
            ToastUtils.ShowToastMessage("当前网络不可用，请稍后再试", CarapplyActivity.this);
        }
    }

    /**
     * 查询司机
     */
    private void setDriverBind() {
        String strDriver = HttpUrl.debugoneUrl + "UCarsExamine/DriverBind/";
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.post()
                    .url(strDriver)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.print(response);
                            String ress = response.replace("\\", "");
                            System.out.print(ress);
                            String ression = StringUtil.sideTrim(ress, "\"");
                            System.out.print(ression);
                            bindBeen = JsonUtil.stringToList(ression, DriverBindBean.class);
                            System.out.print(bindBeen);
                            /*
                            填充有问题
                             */
//                            spinnerdriver.setAdapter(
//                                    new ArrayAdapter<>(CarapplyActivity.this,
//                                            android.R.layout.simple_spinner_dropdown_item
//                                            , bindBeen));
//                            spinnerdriver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                @Override
//                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                    ToastUtils.ShowToastMessage("点击的是：" + position, CarapplyActivity.this);
//                                    System.out.print(position);
//                                }
//
//                                @Override
//                                public void onNothingSelected(AdapterView<?> parent) {
//
//                                }
//                            });
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
            OkHttpUtils.post()
                    .url(strCarNumber)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.print(response);
                            String ress = response.replace("\\", "");
                            System.out.print(ress);
                            String ression = StringUtil.sideTrim(ress, "\"");
                            System.out.print(ression);
                            bindBeencar = JsonUtil.stringToList(ression,CarNumberBindBean.class);
                            System.out.print(bindBeencar);
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, CarapplyActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etStartDataClick:
                showEditClickPopupWindow();
                break;
        }
    }
}
