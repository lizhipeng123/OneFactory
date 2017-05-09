package com.daoran.newfactory.onefactory.activity.work.production;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.commo.CommoditySqlActivity;
import com.daoran.newfactory.onefactory.adapter.CommoditySqlAdapter;
import com.daoran.newfactory.onefactory.adapter.ProductionAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.CommodityPostBean;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.bean.Propostbean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.CommoDialog;
import com.daoran.newfactory.onefactory.view.dialog.ProcationDialog;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by lizhipeng on 2017/5/9.
 */

public class ProductionNewActivity extends BaseFrangmentActivity
implements View.OnClickListener{

    private ImageView ivCommoditySql;//返回
    private ImageView ivSearch;//条件查询
    private Button btnCommoSave;//保存
    private Button btnCommoMenu;//菜单
    private EditText etSqlDetail;//输入页面
    private TextView tvSignPage;//总页数
    private Button btnSignPage;//翻页确定按钮
    private NoscrollListView lv_data;//列表展示
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ProcationDialog procationDialog;

    private List<ProducationDetailBean.DataBean> detailBeenList =
            new ArrayList<ProducationDetailBean.DataBean>();
    private ProducationDetailBean detailBean;
    private ProductionAdapter adapter;


    private SharedPreferences sp;
    private SPUtils spUtils;
    private int pageCount;
    private int pageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_debug);
        getViews();
        initViews();
        setDate();
        setListener();
    }

    private void getViews() {
        ivCommoditySql = (ImageView) findViewById(R.id.ivCommoditySql);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        btnCommoSave = (Button) findViewById(R.id.btnCommoSave);
        btnCommoMenu = (Button) findViewById(R.id.btnCommoMenu);
        etSqlDetail = (EditText) findViewById(R.id.etSqlDetail);
        tvSignPage = (TextView) findViewById(R.id.tvSignPage);
        btnSignPage = (Button) findViewById(R.id.btnSignPage);
        lv_data = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
    }

    private void initViews() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
    }

    private void setListener() {
        ivCommoditySql.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnCommoSave.setOnClickListener(this);
        btnCommoMenu.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);

    }

    private void setDate() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivCommoditySql:
                finish();
                break;
            case R.id.btnSignPage:
                String txt = etSqlDetail.getText().toString();
                String txtcount = tvSignPage.getText().toString();
                if (txt.length() == 0) {
                    ToastUtils.ShowToastMessage("页码不能为空", ProductionNewActivity.this);
                    return;
                } else if (txt.length() > txtcount.length()) {
                    ToastUtils.ShowToastMessage("页码超出输入范围", ProductionNewActivity.this);
                } else {
                    setPageDetail();
                }
                break;
        }
    }

    /**
     * 翻页查询
     */
    private void setPageDetail() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        sp = getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String Style = sp.getString("etprodialogStyle", "");
        String Factory = sp.getString("etprodialogFactory", "");
        String Recode = sp.getString("etprodialogRecode", "");
        String Procedure = sp.getString("Procedure", "");
        String stis = sp.getString("ischeckedd", "");
        if (Procedure.equals("全部")) {
            boolean stris = Boolean.parseBoolean(stis);
            pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
            int index = pageIndex - 1;
            Gson gson = new Gson();
            Propostbean propostbean = new Propostbean();
            Propostbean.Conditions conditions = propostbean.new Conditions();
            conditions.setItem(Style);
            conditions.setPrddocumentary(Recode);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure("");
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(index);
            propostbean.setPageSize(10);
            String gsonbeanStr = gson.toJson(propostbean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this);
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
                                try {
                                    System.out.print(response);
                                    String ress = response.replace("\\", "");
                                    System.out.print(ress);
                                    String ression = StringUtil.sideTrim(ress, "\"");
                                    System.out.print(ression);
                                    detailBean = new Gson().fromJson(ression, ProducationDetailBean.class);
                                    detailBeenList = detailBean.getData();
                                    System.out.print(detailBeenList);
                                    pageCount = detailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / 10);
                                    tvSignPage.setText(count);
                                    adapter = new ProductionAdapter(ProductionNewActivity.this, detailBeenList);
                                    lv_data.setAdapter(adapter);
                                    ResponseDialog.closeLoading();
                                    adapter.notifyDataSetChanged();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionNewActivity.this);
            }
        } else {
            boolean stris = Boolean.parseBoolean(stis);
            pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
            int index = pageIndex - 1;
            Gson gson = new Gson();
            Propostbean propostbean = new Propostbean();
            Propostbean.Conditions conditions = propostbean.new Conditions();
            conditions.setItem(Style);
            conditions.setPrddocumentary(Recode);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure(Procedure);
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(index);
            propostbean.setPageSize(10);
            String gsonbeanStr = gson.toJson(propostbean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this);
                OkHttpUtils.postString()
                        .url(str)
                        .content(gsonbeanStr)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                try {
                                    System.out.print(response);
                                    String ress = response.replace("\\", "");
                                    System.out.print(ress);
                                    String ression = StringUtil.sideTrim(ress, "\"");
                                    System.out.print(ression);
                                    detailBean = new Gson().fromJson(ression, ProducationDetailBean.class);
                                    detailBeenList = detailBean.getData();
                                    System.out.print(detailBeenList);
                                    pageCount = detailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / 10);
                                    tvSignPage.setText(count);
                                    adapter = new ProductionAdapter(ProductionNewActivity.this, detailBeenList);
                                    lv_data.setAdapter(adapter);
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionNewActivity.this);
            }
        }
    }


//    /**
//     * 指定页码查询数据
//     */
//    private void setPageDetaill() {
//        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
//        sp = getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
//        String Style = sp.getString("commoStyle", "");//款号
//        String Factory = sp.getString("commoFactory", "");//跟单
//        String Recode = sp.getString("commoRecode", "");//巡检
//        String etprodialogProcedure = sp.getString("etproProcedure", "");//生产主管
//        String ischeck = sp.getString("ischeckedd", "");//是否可为空
//        boolean stris = Boolean.parseBoolean(ischeck);
//        pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
//        int Index = pageIndex - 1;
//        Gson gson = new Gson();
//        CommodityPostBean postBean = new CommodityPostBean();
//        CommodityPostBean.Conditions conditions = postBean.new Conditions();
//        conditions.setItem(Style);
//        conditions.setPrddocumentary(Factory);
//        conditions.setPrdmaster(etprodialogProcedure);
//        conditions.setIPQC(Recode);
//        conditions.setPrdmasterisnull(stris);
//        postBean.setConditions(conditions);
//        postBean.setPageNum(Index);
//        postBean.setPageSize(10);
//        String stringpost = gson.toJson(postBean);
//        if (NetWork.isNetWorkAvailable(this)) {
//            OkHttpUtils.postString()
//                    .url(str)
//                    .content(stringpost)
//                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//                            e.printStackTrace();
//                        }
//
//                        @Override
//                        public void onResponse(String response, int id) {
//                            try {
//                                System.out.print(response);
//                                String ress = response.replace("\\", "");
//                                System.out.print(ress);
//                                String ression = StringUtil.sideTrim(ress, "\"");
//                                System.out.print(ression);
//                                commoditydetailBean = new Gson().fromJson(ression, CommoditydetailBean.class);
//                                dataBeen = commoditydetailBean.getData();
//                                System.out.print(dataBeen);
//                                pageCount = commoditydetailBean.getTotalCount();
//                                String count = String.valueOf(pageCount / 10);
//                                tvSignPage.setText(count);
//                                sqlAdapter = new CommoditySqlAdapter(ProductionNewActivity.this, dataBeen);
//                                lv_data.setAdapter(sqlAdapter);
//                            } catch (JsonSyntaxException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//
//        } else {
//            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", ProductionNewActivity.this);
//        }
//    }


    /**
     * 查询按钮弹出框
     *
     * @param view
     */
    private void ShowDialog(View view) {
        procationDialog = new ProcationDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        procationDialog.show();
    }

    /**
     * 确认
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
                    setPageDetail();
                    procationDialog.dismiss();
                    break;
            }
        }
    };

    /**
     * 取消
     */
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

    /**
     * 弹出选择菜单
     *
     * @param view
     */
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pro, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                switch (title) {
                    case "新建":
                        startActivity(new Intent(ProductionNewActivity.this,
                                ProductionNewlyBuildActivity.class));
                        break;
                    case "复制":

                        break;
                    case "刷新":
//                        setData();
                        break;
                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });

        popupMenu.show();
    }


//    private NoscrollListView mData;
//
//    private SyncHorizontalScrollView mHeaderHorizontal;
//    private SyncHorizontalScrollView mDataHorizontal;
//    private ImageView ivProductionBack;
//    private boolean prdmasterisnull = false;
//    private CommoDialog commoDialog;
//    private ImageView ivSearch;
//
//    private List<CommoditydetailBean.DataBean> dataBeen
//            = new ArrayList<CommoditydetailBean.DataBean>();
//    private CommoditydetailBean commoditydetailBean;
//    private CommoditySqlAdapter sqlAdapter;
//
//    private TextView tvSignPage;
//    private EditText etSqlDetail;
//    private Button btnSignPage;
//    private Button btnCommoRefresh, btnCommoSave;
//
//    private SharedPreferences sp;
//    private SPUtils spUtils;
//    private int pageCount;
//    private int pageIndex = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_commodity);
//        getViews();
//        initView();
//        setData();
//        setListener();
//    }
//
//    /**
//     * 初始化控件
//     */
//    private void getViews() {
//        ivProductionBack = (ImageView) findViewById(R.id.ivCommoditySql);
//        mData = (NoscrollListView) findViewById(R.id.lv_data);
//        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
//        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
//        ivSearch = (ImageView) findViewById(R.id.ivSearch);
//        tvSignPage = (TextView) findViewById(R.id.tvSignPage);
//        btnSignPage = (Button) findViewById(R.id.btnSignPage);
//        etSqlDetail = (EditText) findViewById(R.id.etSqlDetail);
//        btnCommoRefresh = (Button) findViewById(R.id.btnCommoRefresh);
//        btnCommoSave = (Button) findViewById(R.id.btnCommoSave);
//    }
//
//    /**
//     * 控件操作
//     */
//    private void initView() {
//        mDataHorizontal.setSrollView(mHeaderHorizontal);
//        mHeaderHorizontal.setSrollView(mDataHorizontal);
//        etSqlDetail.setSelection(etSqlDetail.length());
//    }
//
//    /**
//     * 实例化点击事件
//     */
//    private void setListener() {
//        ivProductionBack.setOnClickListener(this);
//        ivSearch.setOnClickListener(this);
//        btnSignPage.setOnClickListener(this);
//        btnCommoRefresh.setOnClickListener(this);
//        btnCommoSave.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            /*返回按钮*/
//            case R.id.ivCommoditySql:
//                finish();
//                break;
//            /*查询*/
//            case R.id.ivSearch:
//                ShowDialog(v);
//                break;
//            /*翻页确认按钮*/
//            case R.id.btnSignPage:
//                String txt = etSqlDetail.getText().toString();
//                String txtcount = tvSignPage.getText().toString();
//                if (txt.length() == 0) {
//                    ToastUtils.ShowToastMessage("页码不能为空", ProductionNewActivity.this);
//                } else if (txt.length() > txtcount.length()) {
//                    ToastUtils.ShowToastMessage("页码超出输入范围", ProductionNewActivity.this);
//                } else {
//                    setPageDetail();
//                }
//                break;
//            /*刷新*/
//            case R.id.btnCommoRefresh:
//
//                break;
//            /*保存*/
//            case R.id.btnCommoSave:
//
//                break;
//        }
//    }
//
//    /**
//     * 查询按钮弹出框
//     *
//     * @param view
//     */
//    private void ShowDialog(View view) {
//        commoDialog = new CommoDialog(this,
//                R.style.dialogstyle, onClickListener, onCancleListener);
//        commoDialog.show();
//    }
//
//    /**
//     * 确认
//     */
//    private View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.btnComfirm:
//                    setPageDetail();
//                    commoDialog.dismiss();
//                    break;
//            }
//        }
//    };
//
//    /**
//     * 取消
//     */
//    private View.OnClickListener onCancleListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.btnCancle:
//                    commoDialog.dismiss();
//                    break;
//            }
//        }
//    };
//
//    /**
//     * 初始化查询数据
//     */
//    private void setData() {
//        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
//        sp = getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
//        String Style = sp.getString("commoStyle", "");//款号
//        String Factory = sp.getString("commoFactory", "");//跟单
//        String Recode = sp.getString("commoRecode", "");//巡检
//        String etprodialogProcedure = sp.getString("etprodialogProcedure", "");//生产主管
//        String ischeck = sp.getString("ischeckedd", "");//是否可为空
//        boolean stris = Boolean.parseBoolean(ischeck);
//        Gson gson = new Gson();
//        CommodityPostBean postBean = new CommodityPostBean();
//        CommodityPostBean.Conditions conditions = postBean.new Conditions();
//        conditions.setItem(Style);
//        conditions.setPrddocumentary(Factory);
//        conditions.setPrdmaster(etprodialogProcedure);
//        conditions.setIPQC(Recode);
//        conditions.setPrdmasterisnull(stris);
//        postBean.setConditions(conditions);
//        postBean.setPageNum(0);
//        postBean.setPageSize(10);
//        String stringpost = gson.toJson(postBean);
//        if (NetWork.isNetWorkAvailable(this)) {
//            OkHttpUtils.postString()
//                    .url(str)
//                    .content(stringpost)
//                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//                            e.printStackTrace();
//                        }
//
//                        @Override
//                        public void onResponse(String response, int id) {
//                            try {
//                                System.out.print(response);
//                                String ress = response.replace("\\", "");
//                                System.out.print(ress);
//                                String ression = StringUtil.sideTrim(ress, "\"");
//                                System.out.print(ression);
//                                commoditydetailBean = new Gson().fromJson(ression, CommoditydetailBean.class);
//                                dataBeen = commoditydetailBean.getData();
//                                System.out.print(dataBeen);
//                                pageCount = commoditydetailBean.getTotalCount();
//                                String count = String.valueOf(pageCount / 10);
//                                tvSignPage.setText(count);
//                                sqlAdapter = new CommoditySqlAdapter(CommoditySqlActivity.this, dataBeen);
//                                mData.setAdapter(sqlAdapter);
//                            } catch (JsonSyntaxException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//
//        } else {
//            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", CommoditySqlActivity.this);
//        }
//    }
//
//    /**
//     * 指定页码查询数据
//     */
//    private void setPageDetail() {
//        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
//        sp = CommoditySqlActivity.this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
//        String Style = sp.getString("commoStyle", "");//款号
//        String Factory = sp.getString("commoFactory", "");//跟单
//        String Recode = sp.getString("commoRecode", "");//巡检
//        String etprodialogProcedure = sp.getString("etproProcedure", "");//生产主管
//        String ischeck = sp.getString("ischeckedd", "");//是否可为空
//        boolean stris = Boolean.parseBoolean(ischeck);
//        pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
//        int Index = pageIndex - 1;
//        Gson gson = new Gson();
//        CommodityPostBean postBean = new CommodityPostBean();
//        CommodityPostBean.Conditions conditions = postBean.new Conditions();
//        conditions.setItem(Style);
//        conditions.setPrddocumentary(Factory);
//        conditions.setPrdmaster(etprodialogProcedure);
//        conditions.setIPQC(Recode);
//        conditions.setPrdmasterisnull(stris);
//        postBean.setConditions(conditions);
//        postBean.setPageNum(Index);
//        postBean.setPageSize(10);
//        String stringpost = gson.toJson(postBean);
//        if (NetWork.isNetWorkAvailable(this)) {
//            OkHttpUtils.postString()
//                    .url(str)
//                    .content(stringpost)
//                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//                            e.printStackTrace();
//                        }
//
//                        @Override
//                        public void onResponse(String response, int id) {
//                            try {
//                                System.out.print(response);
//                                String ress = response.replace("\\", "");
//                                System.out.print(ress);
//                                String ression = StringUtil.sideTrim(ress, "\"");
//                                System.out.print(ression);
//                                commoditydetailBean = new Gson().fromJson(ression, CommoditydetailBean.class);
//                                dataBeen = commoditydetailBean.getData();
//                                System.out.print(dataBeen);
//                                pageCount = commoditydetailBean.getTotalCount();
//                                String count = String.valueOf(pageCount / 10);
//                                tvSignPage.setText(count);
//                                sqlAdapter = new CommoditySqlAdapter(CommoditySqlActivity.this, dataBeen);
//                                mData.setAdapter(sqlAdapter);
//                            } catch (JsonSyntaxException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//
//        } else {
//            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", CommoditySqlActivity.this);
//        }
//    }


}
