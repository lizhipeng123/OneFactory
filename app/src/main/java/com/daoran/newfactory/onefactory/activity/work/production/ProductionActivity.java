package com.daoran.newfactory.onefactory.activity.work.production;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ProductionAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProducationConfigSaveBean;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.bean.ProducationSaveBean;
import com.daoran.newfactory.onefactory.bean.Propostbean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.Listener.SelectItemInterface;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.ACache;
import com.daoran.newfactory.onefactory.util.file.NullStringToEmptyAdapterFactory;
import com.daoran.newfactory.onefactory.view.dialog.ProcationDialog;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 生产日报页面
 * Created by lizhipeng on 2017/3/29.
 */
public class ProductionActivity extends BaseFrangmentActivity
        implements View.OnClickListener, SelectItemInterface
        , View.OnLayoutChangeListener {

    private NoscrollListView mData;//listview的列表
    private ProcationDialog procationDialog;//条件查询的dialog

    private SyncHorizontalScrollView mHeaderHorizontal;//标题scrollview
    private SyncHorizontalScrollView mDataHorizontal;//列表scrollview
    private ImageView ivProductionBack, ivSearch;//返回与条件查询
    private List<ProducationDetailBean.DataBean> detailBeenList =
            new ArrayList<ProducationDetailBean.DataBean>();//列表实体list
    private ProducationDetailBean detailBean;//列表实体bean
    private ProductionAdapter adapter;//列表适配
    List<ProducationSaveBean> saveBeen =
            new ArrayList<ProducationSaveBean>();//保存
    List<ProducationConfigSaveBean> configSaveBeen =
            new ArrayList<ProducationConfigSaveBean>();//新建

    private EditText etSqlDetail;//底部页码输入框
    private TextView tvSignPage;//页数显示
    private Button btnSignPage, btnProSave, spinnermenu;//翻页确定、保存确定，菜单menu
    private TextView spinnerNewbuild;
    private EditText etNewbuild;

    private SharedPreferences sp;//存储
    private SPUtils spUtils;
    private int pageCount;//总页数int
    private int pageIndex = 0;//初始页数0
    private int last_item = -1;
    private TextView oldView;
    private LinearLayout ll_visibi;//
    private TextView tv_visibi;
    private ScrollView scroll_content;
    int keyHeight = 0;
    int screenHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);//显示主页面
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        keyHeight = screenHeight / 3;
        getViews();
        initView();
        setData();
        setListener();
    }

    /**
     * 初始化控件
     */
    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        etSqlDetail = (EditText) findViewById(R.id.etSqlDetail);
        tvSignPage = (TextView) findViewById(R.id.tvSignPage);
        btnSignPage = (Button) findViewById(R.id.btnSignPage);
        btnProSave = (Button) findViewById(R.id.btnProSave);
        spinnermenu = (Button) findViewById(R.id.spinnermenu);
        ll_visibi = (LinearLayout) findViewById(R.id.ll_visibi);
        tv_visibi = (TextView) findViewById(R.id.tv_visibi);
        scroll_content = (ScrollView) findViewById(R.id.scroll_content);
    }

    /**
     * 操作控件
     */
    private void initView() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);//横竖SyncHorizontalScrollView适配
        etSqlDetail.setSelection(etSqlDetail.getText().length());//将光标移到文本最后
    }

    /**
     * 点击事件
     */
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);
        btnProSave.setOnClickListener(this);
        spinnermenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回按钮*/
            case R.id.ivProductionBack:
                finish();
                break;
            /*条件查询弹出框*/
            case R.id.ivSearch:
                ShowDialog(v);
                break;
            /*按页查询*/
            case R.id.btnSignPage:
                String txt = etSqlDetail.getText().toString();
                String txtcount = tvSignPage.getText().toString();
                if (txt.length() == 0) {
                    ToastUtils.ShowToastMessage("页码不能为空", ProductionActivity.this);
                    return;
                } else if (txt.length() > txtcount.length()) {
                    ToastUtils.ShowToastMessage("页码超出输入范围", ProductionActivity.this);
                } else {
                    setPageDetail();
                }
                break;
            /*menu菜单*/
            case R.id.spinnermenu:
                showPopupMenu(spinnermenu);
                break;
            /*保存按钮*/
            case R.id.btnProSave:
                setSave();
                break;
        }
    }

    /**
     * 初始化查询全部数据
     */
    private void setData() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        sp = ProductionActivity.this.getSharedPreferences("my_sp", 0);
        String namedure = sp.getString("proname", "");//制单人
        String Style = sp.getString("etprodialogStyle", "");//款号
        String Factory = sp.getString("etprodialogFactory", "");//工厂
//        String Recode = sp.getString("etprodialogRecode", "");//制单人
        String Procedure = sp.getString("Procedure", "");//工序
        String stis = sp.getString("ischeckedd", "");//是否为空
        if (Procedure.equals("全部")) {
            boolean stris = Boolean.parseBoolean(stis);
            Gson gson = new Gson();
            Propostbean propostbean = new Propostbean();
            Propostbean.Conditions conditions = propostbean.new Conditions();
            conditions.setItem(Style);
            conditions.setPrddocumentary(namedure);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure("");
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(0);
            propostbean.setPageSize(10);

            String gsonbeanStr = gson.toJson(propostbean);
            if (NetWork.isNetWorkAvailable(this)) {
//                /*检测是否为可用WiFi*/
//                WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
//                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//                String infossid = wifiInfo.getSSID();
//                infossid = infossid.replace("\"", "");
//                if (!infossid.equals("taoxinxi")) {
//                    android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
//                    dialog.setTitle("系统提示");
//                    dialog.setMessage("当前WiFi为公共网络，运行请转到测试WiFi状态");
//                    dialog.setButton("确定", listenerwifi);
//                    dialog.show();
//                } else {
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
                                    /*成功返回的结果*/
                                    System.out.print(response);
                                    String ress = response.replace("\\", "");
                                    System.out.print(ress);
                                    String ression = StringUtil.sideTrim(ress, "\"");
                                    System.out.print(ression);
                                    detailBean = new Gson().fromJson(ression, ProducationDetailBean.class);
                                    detailBeenList = detailBean.getData();
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        System.out.print(detailBeenList);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / 10);
                                        tvSignPage.setText(count);
                                        adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
//                }
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionActivity.this);
            }
        } else {
            boolean stris = Boolean.parseBoolean(stis);
            Gson gson = new Gson();
            Propostbean propostbean = new Propostbean();
            Propostbean.Conditions conditions = propostbean.new Conditions();
            conditions.setItem(Style);
            conditions.setPrddocumentary(namedure);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure(Procedure);
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(0);
            propostbean.setPageSize(10);
            String gsonbeanStr = gson.toJson(propostbean);/*字符串转为json字符串*/
            if (NetWork.isNetWorkAvailable(this)) {
//                /*检测是否为可用WiFi*/
//                WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
//                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//                String infossid = wifiInfo.getSSID();
//                infossid = infossid.replace("\"", "");
//                if (!infossid.equals("taoxinxi")) {
//                    android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
//                    dialog.setTitle("系统提示");
//                    dialog.setMessage("当前WiFi为公共网络，运行请转到测试WiFi状态");
//                    dialog.setButton("确定", listenerwifi);
//                    dialog.show();
//                } else {
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
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        System.out.print(detailBeenList);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / 10);
                                        tvSignPage.setText(count);
                                        adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
//                }
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionActivity.this);
            }
        }
    }

    /**
     * 翻页查询
     */
    private void setPageDetail() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        sp = ProductionActivity.this.getSharedPreferences("my_sp", 0);
        /*获取条件查询dialog中输入的信息字段*/
        String namedure = sp.getString("proname", "");
        String Style = sp.getString("etprodialogStyle", "");
        String Factory = sp.getString("etprodialogFactory", "");
//        String Recode = sp.getString("etprodialogRecode", "");
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
            conditions.setPrddocumentary(namedure);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure("");
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(index);
            propostbean.setPageSize(10);
            String gsonbeanStr = gson.toJson(propostbean);
            Log.e("you wanted", "[" + gsonbeanStr + "," + gsonbeanStr + "+]");
            if (NetWork.isNetWorkAvailable(this)) {
//                 /*检测是否为可用WiFi*/
//                WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
//                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//                String infossid = wifiInfo.getSSID();
//                infossid = infossid.replace("\"", "");
//                if (!infossid.equals("taoxinxi")) {
//                    android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
//                    dialog.setTitle("系统提示");
//                    dialog.setMessage("当前WiFi为公共网络，运行请转到测试WiFi状态");
//                    dialog.setButton("确定", listenerwifi);
//                    dialog.show();
//                } else {
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
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        System.out.print(detailBeenList);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / 10);
                                        tvSignPage.setText(count);
                                        adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }

                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
//                }
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionActivity.this);
            }
        } else {
            boolean stris = Boolean.parseBoolean(stis);
            pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
            int index = pageIndex - 1;
            Gson gson = new Gson();
            Propostbean propostbean = new Propostbean();
            Propostbean.Conditions conditions = propostbean.new Conditions();
            conditions.setItem(Style);
            conditions.setPrddocumentary(namedure);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure(Procedure);
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(index);
            propostbean.setPageSize(10);
            String gsonbeanStr = gson.toJson(propostbean);
            if (NetWork.isNetWorkAvailable(this)) {
//                /*检测是否为可用WiFi*/
//                WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
//                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//                String infossid = wifiInfo.getSSID();
//                infossid = infossid.replace("\"", "");
//                if (!infossid.equals("taoxinxi")) {
//                    android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
//                    dialog.setTitle("系统提示");
//                    dialog.setMessage("当前WiFi为公共网络，运行请转到测试WiFi状态");
//                    dialog.setButton("确定", listenerwifi);
//                    dialog.show();
//                } else {
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
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        System.out.print(detailBeenList);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / 10);
                                        tvSignPage.setText(count);
                                        adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
//                }
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionActivity.this);
            }
        }
    }

    /**
     * 修改保存
     */
    private void setSave() {
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this);
            String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
            System.out.print(detailBeenList);
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
            String detailb = gson.toJson(detailBeenList);
            System.out.print(detailb);
            OkHttpUtils.postString().
                    url(saveurl)
                    .content(detailb)
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
                            String ression = StringUtil.sideTrim(response, "\"");
                            System.out.print(ression);
                            int resindex = Integer.parseInt(ression);
                            if (resindex > 3) {
                                ToastUtils.ShowToastMessage("保存成功", ProductionActivity.this);
                                setData();
                                ResponseDialog.closeLoading();
                            } else if (ression == "3" || ression.equals("3")) {
                                ToastUtils.ShowToastMessage("保存失败", ProductionActivity.this);
                                ResponseDialog.closeLoading();
                            } else if (ression == "4" || ression.equals("4")) {
                                ToastUtils.ShowToastMessage("数据错误，请重试", ProductionActivity.this);
                                ResponseDialog.closeLoading();
                            } else {
                                ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionActivity.this);
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ProductionActivity.this);
        }
    }

    /**
     * 复制
     */
    private void setCopy() {
        String strcopy = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
        if (NetWork.isNetWorkAvailable(this)) {
//            /*检测是否为可用WiFi*/
//            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
//            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//            String infossid = wifiInfo.getSSID();
//            infossid = infossid.replace("\"", "");
//            if (!infossid.equals("taoxinxi")) {
//                android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
//                dialog.setTitle("系统提示");
//                dialog.setMessage("当前WiFi为公共网络，运行请转到测试WiFi状态");
//                dialog.setButton("确定", listenerwifi);
//                dialog.show();
//            } else {
            sp = this.getSharedPreferences("my_sp", 0);
            SharedPreferences.Editor editor = sp.edit();
            /*调用ProducationAdapter中点击item后取得的字段*/
            String itemid = "";
            String prosalesid = sp.getString("prosalesid", "");
            String item = sp.getString("copyitem", "");
            String copyDocumentary = sp.getString("copyDocumentary", "");
            String copyFactory = sp.getString("copyFactory", "");
            String copyDepartment = sp.getString("copyDepartment", "");
            String copyProcedure = sp.getString("copyProcedure", "");
            String copyOthers = sp.getString("copyOthers", "");
            String copySingularSystem = sp.getString("copySingularSystem", "");
            String copyColor = sp.getString("copyColor", "");
            String copyTaskNumber = sp.getString("copyTaskNumber", "");
            String copySize = sp.getString("copySize", "");
            String copyClippingNumber = sp.getString("copyCompletedLastMonth", "");
            String copyCompletedLastMonth = sp.getString("copyCompletedLastMonth", "");
            String copyTotalCompletion = sp.getString("copyTotalCompletion", "");
            String copyBalanceAmount = sp.getString("copyBalanceAmount", "");
            String copyState = sp.getString("copyState", "");
            String copyProYear = sp.getString("copyProYear", "");
            String copyMonth = sp.getString("copyMonth", "");
            String copyRemarks = sp.getString("copyRemarks", "");
            String copyRecorder = sp.getString("copyRecorder", "");
            String copyRecordat = sp.getString("copyRecordat", "");
            Gson gson = new Gson();
            ProducationConfigSaveBean saveBean = new ProducationConfigSaveBean();
            saveBean.setID(itemid);
            saveBean.setSalesid(prosalesid);
            saveBean.setItem(item);
            saveBean.setPrddocumentary(copyDocumentary);
            saveBean.setSubfactory(copyFactory);
            saveBean.setSubfactoryTeams(copyDepartment);
            saveBean.setWorkingProcedure(copyProcedure);
            saveBean.setWorkers(copyOthers);
            saveBean.setPqty(copySingularSystem);
            saveBean.setProdcol(copyColor);
            saveBean.setTaskqty(copyTaskNumber);
            saveBean.setMdl(copySize);
            saveBean.setFactcutqty(copyClippingNumber);
            saveBean.setLastMonQty(copyCompletedLastMonth);
            saveBean.setSumCompletedQty(copyTotalCompletion);
            saveBean.setLeftQty(copyBalanceAmount);
            saveBean.setPrdstatus(copyState);
            saveBean.setYear(copyProYear);
            saveBean.setMonth(copyMonth);
            saveBean.setDay1("");
            saveBean.setDay2("");
            saveBean.setDay3("");
            saveBean.setDay4("");
            saveBean.setDay5("");
            saveBean.setDay6("");
            saveBean.setDay7("");
            saveBean.setDay8("");
            saveBean.setDay9("");
            saveBean.setDay10("");
            saveBean.setDay11("");
            saveBean.setDay12("");
            saveBean.setDay13("");
            saveBean.setDay14("");
            saveBean.setDay15("");
            saveBean.setDay16("");
            saveBean.setDay17("");
            saveBean.setDay18("");
            saveBean.setDay19("");
            saveBean.setDay20("");
            saveBean.setDay21("");
            saveBean.setDay22("");
            saveBean.setDay23("");
            saveBean.setDay24("");
            saveBean.setDay25("");
            saveBean.setDay26("");
            saveBean.setDay27("");
            saveBean.setDay28("");
            saveBean.setDay29("");
            saveBean.setDay30("");
            saveBean.setDay31("");
            saveBean.setMemo(copyRemarks);
            saveBean.setRecorder(copyRecorder);
            saveBean.setRecordat(copyRecordat);
            configSaveBeen.add(saveBean);
            String configsave = gson.toJson(configSaveBeen);
            String dateee = configsave.replace("\"\"", "null");
            /*删除本地存储的之前点击item获取的字段*/
            editor.remove("prosalesid");
            editor.remove("copyitem");
            editor.remove("copyDocumentary");
            editor.remove("copyFactory");
            editor.remove("copyDepartment");
            editor.remove("copyOthers");
            editor.remove("copySingularSystem");
            editor.remove("copyColor");
            editor.remove("copyTaskNumber");
            editor.remove("copySize");
            editor.remove("copyCompletedLastMonth");
            editor.remove("copyCompletedLastMonth");
            editor.remove("copyTotalCompletion");
            editor.remove("copyBalanceAmount");
            editor.remove("copyState");
            editor.remove("copyProYear");
            editor.remove("copyMonth");
            editor.remove("copyRemarks");
            editor.remove("copyRecorder");
            editor.remove("copyRecordat");
            editor.commit();
            if (!itemid.equals("")) {
                ResponseDialog.showLoading(this);
                OkHttpUtils.postString()
                        .url(strcopy)
                        .content(dateee)
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
                                String ression = StringUtil.sideTrim(response, "\"");
                                System.out.print(ression);
                                int resindex = Integer.parseInt(ression);
                                if (resindex > 4) {
                                    ToastUtils.ShowToastMessage("保存成功",
                                            ProductionActivity.this);
                                    setData();
                                    ResponseDialog.closeLoading();
                                } else if (resindex == 3) {
                                    ToastUtils.ShowToastMessage("保存失败",
                                            ProductionActivity.this);
                                    ResponseDialog.closeLoading();
                                } else if (resindex == 4) {
                                    ToastUtils.ShowToastMessage("数据错误，请重试",
                                            ProductionActivity.this);
                                    ResponseDialog.closeLoading();
                                } else {
                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                            ProductionActivity.this);
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
            } else {
                new AlertDialog.Builder(ProductionActivity.this).setTitle("提示信息")
                        .setMessage("请选择款号，再进行复制保存")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();//相应事件
            }
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ProductionActivity.this);
        }
    }

    /**
     *
     */
    DialogInterface.OnClickListener listenerwifi = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case android.app.AlertDialog.BUTTON_POSITIVE://确定
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 弹出输入框
     *
     * @param view
     */
    private void ShowDialog(View view) {
        procationDialog = new ProcationDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        procationDialog.show();
    }

    /**
     * 确定
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
        PopupMenu popupMenu = new PopupMenu(ProductionActivity.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pro, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                switch (title) {
                    case "新建":
                        startActivity(new Intent(ProductionActivity.this,
                                ProductionNewlyBuildActivity.class));
                        break;
                    case "复制":
                        setCopy();
                        break;
                    case "刷新":
                        setData();
                        break;
                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
            }
        });

        popupMenu.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void setSelectedItem(final int position) {
        mData.postDelayed(new Runnable() {
            @Override
            public void run() {
                mData.setSelection(position);
            }
        }, 1000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = sp.edit();
        /*关闭界面后，删除本地存储的字段*/
        editor.remove("prouriid");
        editor.remove("prosalesid");
        editor.remove("proColumnTitle");
        editor.remove("proProcedureTitle");
        editor.remove("proadapterPrdstatusTitle");
        editor.remove("productionsaveOthers");
        editor.remove("productionOthers");
        editor.remove("productionTaskNumber");
        editor.remove("productionCompletedLastMonth");
        editor.remove("proadapterMonthTitle");
        editor.remove("productionOneDay");
        editor.remove("productionTwoDay");
        editor.remove("productionThreeDay");
        editor.remove("productionForeDay");
        editor.remove("productionFiveDay");
        editor.remove("productionSixDay");
        editor.remove("productionSevenDay");
        editor.remove("productionEightDay");
        editor.remove("productionNineDay");
        editor.remove("productionTenDay");
        editor.remove("productionElevenDay");
        editor.remove("productionTwelveDay");
        editor.remove("productionThirteenDay");
        editor.remove("productionFourteenDay");
        editor.remove("productionFifteenDay");
        editor.remove("productionSixteenDay");
        editor.remove("productionSeventeenDay");
        editor.remove("productionEighteenDay");
        editor.remove("productionNineteenDay");
        editor.remove("productionTwentyDay");
        editor.remove("productionTwentyOneDay");
        editor.remove("productionTwentyTwoDay");
        editor.remove("productionTwentyThreeDay");
        editor.remove("productionTwentyForeDay");
        editor.remove("productionTwentyFiveDay");
        editor.remove("productionTwentySixDay");
        editor.remove("productionTwentySevenDay");
        editor.remove("productionTwentyEightDay");
        editor.remove("productionTwentyNineDay");
        editor.remove("productionThirtyDay");
        editor.remove("productionThirtyOneDay");
        editor.remove("productionRemarks");
        editor.commit();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
            ToastUtils.ShowToastMessage("弹出状态", ProductionActivity.this);
        } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            ToastUtils.ShowToastMessage("隐藏状态", ProductionActivity.this);
        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            ToastUtils.ShowToastMessage("软键盘弹起啦", ProductionActivity.this);
        } else {
            ToastUtils.ShowToastMessage("回去了", ProductionActivity.this);
        }
    }
}
