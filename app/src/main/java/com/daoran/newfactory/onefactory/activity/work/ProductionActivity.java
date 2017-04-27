package com.daoran.newfactory.onefactory.activity.work;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ProductionAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.bean.Propostbean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.ProcationDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 生产日报
 * Created by lizhipeng on 2017/3/29.
 */

public class ProductionActivity extends BaseFrangmentActivity
        implements View.OnClickListener {

    private NoscrollListView mData;
    private ProcationDialog procationDialog;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack, ivSearch;
    private List<ProducationDetailBean.DataBean> detailBeenList =
            new ArrayList<ProducationDetailBean.DataBean>();
    private ProducationDetailBean detailBean;
    private ProductionAdapter adapter;

    private EditText etSqlDetail;
    private TextView tvSignPage;
    private Button btnSignPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
        getViews();
        initView();
        setListener();
        setData();
    }

    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        etSqlDetail = (EditText) findViewById(R.id.etSqlDetail);
        tvSignPage = (TextView) findViewById(R.id.tvSignPage);
        btnSignPage = (Button) findViewById(R.id.btnSignPage);
    }

    private void initView() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
    }

    private void setListener() {

        ivProductionBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivProductionBack:
                finish();
                break;
            case R.id.ivSearch:
                ShowDialog(v);
                break;
            case R.id.btnSignPage:

                break;
        }
    }

    private void setData() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        Gson gson = new Gson();
        Propostbean propostbean = new Propostbean();
        Propostbean.Conditions conditions = propostbean.new Conditions();
        conditions.setItem("");
        conditions.setPrddocumentary("");
        conditions.setSubfactory("");
        conditions.setWorkingProcedure("");
        conditions.setPrddocumentaryisnull(true);
        propostbean.setConditions(conditions);
        propostbean.setPageNum(0);
        propostbean.setPageSize(10);
        String gsonbeanStr = gson.toJson(propostbean);
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.postString()
                    .url(str)
                    .content(gsonbeanStr)
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
                            detailBean = new Gson().fromJson(ression,ProducationDetailBean.class);
                            detailBeenList = detailBean.getData();
                            System.out.print(detailBeenList);
                            adapter = new ProductionAdapter(ProductionActivity.this,detailBeenList);
                            mData.setAdapter(adapter);
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionActivity.this);
        }

    }

    private void setPageDetail(){
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        Gson gson = new Gson();
        Propostbean propostbean = new Propostbean();
        Propostbean.Conditions conditions = propostbean.new Conditions();
        conditions.setItem("");
        conditions.setPrddocumentary("");
        conditions.setSubfactory("");
        conditions.setWorkingProcedure("");
        conditions.setPrddocumentaryisnull(true);
        propostbean.setConditions(conditions);
        propostbean.setPageNum(0);
        propostbean.setPageSize(10);
        String gsonbeanStr = gson.toJson(propostbean);
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.postString()
                    .url(str)
                    .content(gsonbeanStr)
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
                            detailBean = new Gson().fromJson(ression,ProducationDetailBean.class);
                            detailBeenList = detailBean.getData();
                            System.out.print(detailBeenList);
                            adapter = new ProductionAdapter(ProductionActivity.this,detailBeenList);
                            mData.setAdapter(adapter);
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionActivity.this);
        }
    }

    private void ShowDialog(View view) {
        procationDialog = new ProcationDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        procationDialog.show();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
                    procationDialog.dismiss();
                    break;
            }
        }
    };

    private View.OnClickListener onCancleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCancle:
                    procationDialog.dismiss();
                    break;
            }
        }
    };

}
