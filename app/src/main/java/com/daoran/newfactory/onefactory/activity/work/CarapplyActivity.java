package com.daoran.newfactory.onefactory.activity.work;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 出车单展示
 * Created by lizhipeng on 2017/3/24.
 */

public class CarapplyActivity extends BaseFrangmentActivity implements View.OnClickListener {


    private Toolbar tbarCarapply;
    private EditText etStartDataClick, etEndDataClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carapply);
        getViews();
        initViews();
        setListener();
        setCardetail();
    }

    private void getViews() {
        tbarCarapply = (Toolbar) findViewById(R.id.tbarCarapply);
        etStartDataClick = (EditText) findViewById(R.id.etStartDataClick);
        etEndDataClick = (EditText) findViewById(R.id.etEndDataClick);
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
        int width = outMetrics.widthPixels;
//        View menuView = LayoutInflater.from(this).inflate()
    }

    private void setCardetail() {
        String str = HttpUrl.debugoneUrl + "UCarsApply/UCarsApplySearch/";
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils
                    .post()
                    .url(str)
                    .addParams("pageNum", "1")
                    .addParams("pageSize","20")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.print(response);
                            Gson gson = new Gson();
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用，请稍后再试", CarapplyActivity.this);
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
