package com.daoran.newfactory.onefactory.activity.work;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.CommoditySqlAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.CommodityPostBean;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.CommoDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 查货跟踪单
 * Created by lizhipeng on 2017/3/29.
 */

public class CommoditySqlActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private NoscrollListView mData;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack;
    private boolean prdmasterisnull = false;
    private CommoDialog commoDialog;
    private ImageView ivSearch;

    private List<CommoditydetailBean.DataBean> dataBeen
            = new ArrayList<CommoditydetailBean.DataBean>();
    private CommoditydetailBean commoditydetailBean;
    private CommoditySqlAdapter sqlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);
        getViews();
        initView();
        setData();
        setListener();
    }

    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivCommoditySql);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
    }

    private void initView() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
    }

    private void setListener(){
        ivProductionBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivCommoditySql:
                finish();
                break;
            case R.id.ivSearch:
                ShowDialog(v);
                break;
        }
    }

    private void ShowDialog(View view){
        commoDialog = new CommoDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        commoDialog.show();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnComfirm:
                    commoDialog.dismiss();
                    break;
            }
        }
    };

    private View.OnClickListener onCancleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnCancle:
                    commoDialog.dismiss();
                    break;
            }
        }
    };

    private void setData() {
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        Gson gson = new Gson();
        CommodityPostBean postBean = new CommodityPostBean();
        CommodityPostBean.Conditions conditions = postBean.new Conditions();
        conditions.setItem("");
        conditions.setPrddocumentary("");
        conditions.setPrdmaster("");
        conditions.setPrdmasterisnull(true);
        postBean.setConditions(conditions);
        postBean.setPageNum(0);
        postBean.setPageSize(10);
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.postString()
                    .url(str)
                    .content(stringpost)
                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
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
                            commoditydetailBean = new Gson().fromJson(ression,CommoditydetailBean.class);
                            dataBeen = commoditydetailBean.getData();
                            System.out.print(dataBeen);
                            sqlAdapter = new CommoditySqlAdapter(CommoditySqlActivity.this,dataBeen);
                            mData.setAdapter(sqlAdapter);
                        }
                    });

        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", CommoditySqlActivity.this);
        }
    }

    private void setPageDetail(){
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        Gson gson = new Gson();
        CommodityPostBean postBean = new CommodityPostBean();
        CommodityPostBean.Conditions conditions = postBean.new Conditions();
        conditions.setItem("");
        conditions.setPrddocumentary("");
        conditions.setPrdmaster("");
        conditions.setPrdmasterisnull(true);
        postBean.setConditions(conditions);
        postBean.setPageNum(0);
        postBean.setPageSize(10);
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.postString()
                    .url(str)
                    .content(stringpost)
                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
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
                            commoditydetailBean = new Gson().fromJson(ression,CommoditydetailBean.class);
                            dataBeen = commoditydetailBean.getData();
                            System.out.print(dataBeen);
                            sqlAdapter = new CommoditySqlAdapter(CommoditySqlActivity.this,dataBeen);
                            mData.setAdapter(sqlAdapter);
                        }
                    });

        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", CommoditySqlActivity.this);
        }
    }

}
