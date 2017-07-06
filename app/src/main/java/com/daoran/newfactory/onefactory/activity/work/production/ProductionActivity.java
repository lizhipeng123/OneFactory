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
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ProductionAdapter;
import com.daoran.newfactory.onefactory.adapter.ProductionLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProducationConfigSaveBean;
import com.daoran.newfactory.onefactory.bean.ProducationDeleteBean;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.bean.ProducationSaveBean;
import com.daoran.newfactory.onefactory.bean.ProductionDetailBooleanBean;
import com.daoran.newfactory.onefactory.bean.Propostbean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.Listener.SelectItemInterface;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.NullStringToEmptyAdapterFactory;
import com.daoran.newfactory.onefactory.util.file.save.ProductionExcelUtil;
import com.daoran.newfactory.onefactory.view.dialog.ProcationDialog;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Date;
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
    private NoscrollListView lv_left;//左侧编号
    private ProcationDialog procationDialog;//条件查询的dialog
    private ProductionLeftAdapter mLeftAdapter;

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
    private List<ProductionDetailBooleanBean.DataBean> detailbooleanDatabean
            = new ArrayList<ProductionDetailBooleanBean.DataBean>();
    private ProductionDetailBooleanBean detailBooleanBean;


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
    private Spinner spinnProPageClumns;
    int keyHeight = 0;
    int screenHeight = 0;
    private int year, month, datetime, hour, minute, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);//显示主页面
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        keyHeight = screenHeight / 3;
        getViews();
        initView();
        setNewlyComfig();
        setData();
        setListener();
    }

    /**
     * 初始化控件
     */
    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        lv_left = (NoscrollListView) findViewById(R.id.lv_pleft);
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
        spinnProPageClumns = (Spinner) findViewById(R.id.spinnProPageClumns);
        getClumnsSpinner();
    }

    /**
     * 填充生产日报每页显示条目数spinner数据
     */
    private void getClumnsSpinner() {
        String[] spinner = getResources().getStringArray(R.array.clumnsCommon);
        ArrayAdapter<String> adapterclumns = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner);
        adapterclumns.setDropDownViewResource(R.layout.dropdown_stytle);
        spinnProPageClumns.setAdapter(adapterclumns);
        spinnProPageClumns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().
                        getStringArray(R.array.clumnsCommon);
                spUtils.put(ProductionActivity.this,
                        "clumnsprospinner", languages[position]);
                setData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        String commostyle = sp.getString("productionleftItem", "");
        String commonamedure;
        String itemstyle;
        if (commostyle.equals("")) {
            itemstyle = Style;
            commonamedure = namedure;
        } else {
            itemstyle = commostyle;
            commonamedure = "";
        }
        String Factory = sp.getString("etprodialogFactory", "");//工厂
//        String Recode = sp.getString("etprodialogRecode", "");//制单人
        String getsize = sp.getString("clumnsprospinner", "");
        if (getsize.equals("")) {
            getsize = String.valueOf(10);
        }
        String Procedure = sp.getString("Procedure", "");//工序
        String stis = sp.getString("ischeckedd", "");//是否为空
        if (Procedure.equals("全部")) {
            boolean stris = Boolean.parseBoolean(stis);
            Gson gson = new Gson();
            Propostbean propostbean = new Propostbean();
            Propostbean.Conditions conditions = propostbean.new Conditions();
            conditions.setItem(itemstyle);
            conditions.setPrddocumentary(commonamedure);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure("");
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(0);
            propostbean.setPageSize(Integer.parseInt(getsize));
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
                final int finalGetsize = Integer.parseInt(getsize);
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
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);
                                        mLeftAdapter = new ProductionLeftAdapter(ProductionActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
                                        adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }
                                    setNewlyComfig();
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
            conditions.setItem(itemstyle);
            conditions.setPrddocumentary(commonamedure);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure(Procedure);
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(0);
            propostbean.setPageSize(Integer.parseInt(getsize));
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
                final int finalGetsize = Integer.parseInt(getsize);
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
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);
                                        mLeftAdapter = new ProductionLeftAdapter(ProductionActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
                                        adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }
                                    setNewlyComfig();
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
        String commonamedure;
        String Style = sp.getString("etprodialogStyle", "");
        String commostyle = sp.getString("productionleftItem", "");
        String itemstyle;
        if (commostyle.equals("")) {
            itemstyle = Style;
            commonamedure = namedure;
        } else {
            itemstyle = commostyle;
            commonamedure = "";
        }
        String Factory = sp.getString("etprodialogFactory", "");
        String getsize = sp.getString("clumnsprospinner", "");
        if (getsize.equals("")) {
            getsize = String.valueOf(10);
        }
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
            conditions.setItem(itemstyle);
            conditions.setPrddocumentary(commonamedure);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure("");
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(index);
            propostbean.setPageSize(Integer.parseInt(getsize));
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
                final int finalGetsize = Integer.parseInt(getsize);
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
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);

                                        adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        mLeftAdapter = new ProductionLeftAdapter(ProductionActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }
                                    setNewlyComfig();
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
            conditions.setItem(itemstyle);
            conditions.setPrddocumentary(commonamedure);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure(Procedure);
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(index);
            propostbean.setPageSize(Integer.parseInt(getsize));
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
                final int finalGetsize = Integer.parseInt(getsize);
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
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);
                                        adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        mLeftAdapter = new ProductionLeftAdapter(ProductionActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
//                                        adapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }
                                    setNewlyComfig();
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
     * 判断 array1是否包含所有的 array2
     */
    private static boolean containsAll(String[] array1, String[] array2) {
        for (String str : array2) {
            if (!ArrayUtils.contains(array1, str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 修改保存
     * 待修改（循环判断）
     */
    private void setSave() {
        sp = getSharedPreferences("my_sp", 0);
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this);
            String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
            int beanlength = detailBeenList.size();//修改后的数据大小
            String[] arrsflag = new String[beanlength];//修改的工序数组
            String[] arrsItem = new String[beanlength];//修改的工序对应的款号数组
            String[] arrsmonth = new String[beanlength];//修改的工序款号对应的月份数组
            //循环遍历修改后的数据大小输出修改的工序和款号数组
            for (int i = 0; i < beanlength; i++) {
                if (beanlength != 0) {
                    arrsflag[i] = detailBeenList.get(i).getMemoprdure();
                    String memoprdure = detailBeenList.get(i).getMemoprdure();
                    String memomonth = detailBeenList.get(i).getMemomonth();
                    if (memoprdure != null || memomonth != null) {//修改的工序不为空
                        arrsItem[i] = detailBeenList.get(i).getItem();
                        arrsflag[i] = detailBeenList.get(i).getMemoprdure();
                        arrsmonth[i] = detailBeenList.get(i).getMemomonth();
                    } else {
                        arrsItem[i] = "";
                        arrsflag[i] = "";
                        arrsmonth[i] = "";
                    }
                } else {
                    arrsflag[i] = "";
                }
            }
            System.out.print(arrsflag + "");

            String strflag = "";
            for (int i = 0; i < arrsflag.length; i++) {
                strflag += arrsflag[i];
            }
            System.out.print(strflag);

            //去掉修改后的工序数组中的空值“”
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arrsItem.length; i++) {
                if ("".equals(arrsItem[i])) {
                    continue;
                }
                sb.append(arrsItem[i]);
                if (i != arrsItem.length - 1) {
                    sb.append(";");
                }
            }
            arrsItem = sb.toString().split(";");
            for (int i = 0; i < arrsItem.length; i++) {
                System.out.print(arrsItem[i] + "");
            }
            System.out.print(arrsItem + "");
            StringBuffer sb1 = new StringBuffer();
            for (int i = 0; i < arrsmonth.length; i++) {
                if ("".equals(arrsmonth[i])) {
                    continue;
                }
                sb1.append(arrsmonth[i]);
                if (i != arrsmonth.length - 1) {
                    sb1.append(";");
                }
            }
            arrsmonth = sb1.toString().split(";");
            for (int i = 0; i < arrsmonth.length; i++) {
                System.out.print(arrsmonth[i] + "");
            }
            System.out.print(arrsmonth + "");
            int booleanlistlength = detailbooleanDatabean.size();//初始化查询的数据
            String[] arrsdata = new String[booleanlistlength];//初始化的工序数组
            String[] arrsdatamonth = new String[booleanlistlength];//初始化的月份数组
            String[] arrsdateitem = new String[booleanlistlength];//初始化款号
            //循环遍历初始化集合大小，输出符合修改后款号的工序
            for (int i = 0; i < booleanlistlength; i++) {
                if (booleanlistlength != 0) {
                    String workitem = detailbooleanDatabean.get(i).getItem();
                    String[] workitempro = workitem.split(",");
                    boolean containsall = containsAll(arrsItem, workitempro);
                    if (containsall == true) {
                        arrsdata[i] = detailbooleanDatabean.get(i).getWorkingProcedure();
                        arrsdatamonth[i] = detailbooleanDatabean.get(i).getMonth();
                        arrsdateitem[i] = detailbooleanDatabean.get(i).getItem();
                    } else {
                        arrsdata[i] = "";
                        arrsdatamonth[i] = "";
                        arrsdateitem[i] = "";
                    }
                } else {
                    arrsdata[i] = "";
                    arrsdatamonth[i] = "";
                    arrsdateitem[i] = "";
                }
            }
            StringBuffer sb2 = new StringBuffer();
            for (int i = 0; i < arrsdatamonth.length; i++) {
                if ("".equals(arrsdatamonth[i])) {
                    continue;
                }
                sb2.append(arrsdatamonth[i]);
                if (i != arrsdatamonth.length - 1) {
                    sb2.append(";");
                }
            }
            arrsdatamonth = sb2.toString().split(";");
            for (int i = 0; i < arrsdatamonth.length; i++) {
                System.out.print(arrsdatamonth[i] + "");
            }
//            System.out.print(arrsdatamonth + "");
            System.out.print(arrsdata + "");//输出的是循环出来的所有符合条件的初始化工序
            System.out.print(arrsflag + "");
            System.out.print(arrsdatamonth + "");
            System.out.print(arrsdateitem + "");
            boolean flagmonth = containsAll(arrsdatamonth, arrsmonth);
            boolean flagdata = containsAll(arrsdata, arrsflag);
            boolean flagitem = containsAll(arrsdateitem, arrsItem);
            boolean booleanmonth = false;
            for (int i = 0; i < arrsmonth.length; i++) {
                booleanmonth = TextUtils.isEmpty(arrsmonth[i]);
            }
            boolean booleandata = false;
            for (int i = 0; i < arrsflag.length; i++) {
                booleandata = TextUtils.isEmpty(arrsflag[i]);
            }
            boolean booleanitem = false;
            for (int i = 0; i < arrsItem.length; i++) {
                booleanitem = TextUtils.isEmpty(arrsItem[i]);
            }

            if (booleanmonth == true && booleandata == true && booleanitem == true) {
                ToastUtils.ShowToastMessage("未修改表中数据",ProductionActivity.this);
                ResponseDialog.closeLoading();
            }else{
                if (arrsflag != null) {
//判断修改后的工序是否存在于修改前的工序之中，如果存在则不能保存
                    if (flagdata == true) {
                        System.out.print("true");
                        if (flagmonth == true) {
                            ToastUtils.ShowToastMessage("相同款号、用户下，月份不能相同", ProductionActivity.this);
                            ResponseDialog.closeLoading();
                        } else {
                            //如果修改后的工序不存在与修改前的工序之中，则可以保存
                            System.out.print("false");
                            if (flagdata == true) {
                                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                                String detailb = gson.toJson(detailBeenList);
                                System.out.print(detailb);
                                if (detailb.equals("[]")) {
                                    ToastUtils.ShowToastMessage("没有数据可以修改", ProductionActivity.this);
                                } else {
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
                                }
                            } else {
                                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                                String detailb = gson.toJson(detailBeenList);
                                System.out.print(detailb);
                                if (detailb.equals("[]")) {
                                    ToastUtils.ShowToastMessage("没有数据可以修改", ProductionActivity.this);
                                } else {
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
                                }
                            }

                        }
                    } else {
                        //如果修改后的工序不存在与修改前的工序之中，则可以保存
                        System.out.print("false");
                        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                        String detailb = gson.toJson(detailBeenList);
                        System.out.print(detailb);
                        if (detailb.equals("[]")) {
                            ToastUtils.ShowToastMessage("没有数据可以修改", ProductionActivity.this);
                        } else {
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
                        }
                    }
                } else {
                    if (flagitem == true) {
                        if (flagmonth == true) {
                            ToastUtils.ShowToastMessage("相同款号、用户下，月份不能相同", ProductionActivity.this);
                            ResponseDialog.closeLoading();
                        } else {
                            //如果修改后的工序不存在与修改前的工序之中，则可以保存
                            System.out.print("false");
                            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                            String detailb = gson.toJson(detailBeenList);
                            System.out.print(detailb);
                            if (detailb.equals("[]")) {
                                ToastUtils.ShowToastMessage("没有数据可以修改", ProductionActivity.this);
                            } else {
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
                            }
                        }
                    } else {

                    }
                }
            }
//            System.out.print(detailBeenList);//修改过的数据list
//            String probooleanProcedureTitle = sp.getString("probooleanProcedureTitle", "");
//            //在初始化的数据中循环遍历所有工序在款号item相同的情况下和修改的工序是否一致
//            for (int i = 0; i < booleanlistlength; i++) {
//                String beanitem = detailBeenList.get(i).getItem();//已修改的集合中的款号
//                String ProcedureTitle = detailBeenList.get(i).getMemoprdure();//已修改的集合中的工序(默认)(不修改则为空)
//                String booleanitem = detailbooleanDatabean.get(i).getItem();//初始化查询出的集合中的款号
//                String booleandure = detailbooleanDatabean.get(i).getWorkingProcedure();//初始化查询出的集合中的工序
//                //判断款号与工序，有和原来相同的，提示并跳出，如果没有相同的则可以保存
//                if (ProcedureTitle == null) {
//                    ProcedureTitle = "";
//                }
//                if (beanitem.equals(booleanitem) && ProcedureTitle.equals(booleandure)) {
//                    ResponseDialog.closeLoading();
//                    flag = false;
//                    continue;
//                } else {
//                    flag = true;
//                }
//            }
//            System.out.print(flag + "");
//            if (flag == true) {
//
//            } else {
//
//            }
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
            Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
            t.setToNow(); // 取得系统时间。
            year = t.year;
            month = t.month;
            datetime = t.monthDay;
            hour = t.hour; // 0-23
            minute = t.minute;
            second = t.second;
            month = month + 1;
            sp = this.getSharedPreferences("my_sp", 0);
            final SharedPreferences.Editor editor = sp.edit();
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
            String copyRecordid = sp.getString("username", "");
            Gson gson = new Gson();
            ProducationConfigSaveBean saveBean = new ProducationConfigSaveBean();
            saveBean.setID(itemid);
            saveBean.setSalesid(prosalesid);
            saveBean.setRecordid(copyRecordid);
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
            saveBean.setYear(String.valueOf(year));
            saveBean.setMonth(String.valueOf(month));
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
            saveBean.setMemo("");
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
            if (!item.equals("")) {
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
                                    setNewlyComfig();
                                    setData();
                                    configSaveBeen.clear();
                                    ResponseDialog.closeLoading();
                                } else if (resindex == 3) {
                                    ToastUtils.ShowToastMessage("保存失败",
                                            ProductionActivity.this);
                                    configSaveBeen.clear();
                                    ResponseDialog.closeLoading();
                                } else if (resindex == 4) {
                                    ToastUtils.ShowToastMessage("数据错误，请重试",
                                            ProductionActivity.this);
                                    configSaveBeen.clear();
                                    ResponseDialog.closeLoading();
                                } else {
                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                            ProductionActivity.this);
                                    configSaveBeen.clear();
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
                configSaveBeen.clear();
            }
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ProductionActivity.this);
        }
    }

    /**
     * 进入生产日报表时查询有关当前登录用户的数据，并在修改保存时调用其实体类
     * 判断当前用户是否创建过相同款号，相同工序的条目
     */
    private void setNewlyComfig() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        sp = getSharedPreferences("my_sp", 0);
        String namedure = sp.getString("usernamerecoder", "");//制单人
        String stis = sp.getString("ischeckedd", "");//是否为空
        boolean stris = Boolean.parseBoolean(stis);
        Gson gson = new Gson();
        Propostbean propostbean = new Propostbean();
        Propostbean.Conditions conditions = propostbean.new Conditions();
        conditions.setItem("");//款号
        conditions.setPrddocumentary(namedure);//制单人
        conditions.setSubfactory("");//工厂
        conditions.setWorkingProcedure("");//工序
        conditions.setPrddocumentaryisnull(stris);//是否为空
        propostbean.setConditions(conditions);//外部嵌套
        propostbean.setPageNum(0);//默认第几页
        propostbean.setPageSize(500);//默认每页多少条数据
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
                                    /*成功返回的结果*/
                                System.out.print(response);
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                detailBooleanBean = new Gson().fromJson(ression, ProductionDetailBooleanBean.class);
                                detailbooleanDatabean = detailBooleanBean.getData();
                                ResponseDialog.closeLoading();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请重新再试",
                    ProductionActivity.this);
        }
    }

    /**
     * 删除
     */
    private void DeleteDate() {
        sp = getSharedPreferences("my_sp", 0);
        String id = sp.getString("proadapterid", "");
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
        String copyRecordid = sp.getString("username", "");
        String prorecorid = sp.getString("prorecorid", "");
        if (id.equals("")) {
            ToastUtils.ShowToastMessage("未选择当前行", ProductionActivity.this);
        } else {
            Gson gson = new Gson();
            ProducationDeleteBean deleteBean = new ProducationDeleteBean();
            deleteBean.setID(Integer.parseInt(id));
            deleteBean.setItem(item);
            deleteBean.setSalesid(Integer.parseInt(prosalesid));
            deleteBean.setRecorder(copyRecorder);
            deleteBean.setCtmtxt("");
            deleteBean.setPqty("");
            deleteBean.setPrdtyp("");
            deleteBean.setPrddocumentary("");
            deleteBean.setSubfactory("");
            deleteBean.setWorkingProcedure("");
            deleteBean.setSubfactoryTeams("");
            deleteBean.setWorkers("");
            deleteBean.setMemo("");
            deleteBean.setCutamount("");
            deleteBean.setSewamount("");
            deleteBean.setPackamount("");
            deleteBean.setAmount("");
            deleteBean.setPrdstatus("");
            deleteBean.setProdcol("");
            deleteBean.setMdl("");
            deleteBean.setInbill("");
            deleteBean.setRecordid(prorecorid);
            String delete = gson.toJson(deleteBean);
//            String dateee = delete.replace("\"\"", "null");
            if (NetWork.isNetWorkAvailable(this)) {
                String strdelete = HttpUrl.debugoneUrl + "FactoryPlan/DeletePlanBill/";
                OkHttpUtils.postString()
                        .url(strdelete)
                        .content(delete)
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
                                String result = response;
                                if (result.equals("false")) {
                                    ToastUtils.ShowToastMessage("删除失败", ProductionActivity.this);
                                } else {
                                    ToastUtils.ShowToastMessage("删除成功，刷新页面", ProductionActivity.this);
                                }
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage(R.string.noHttp, ProductionActivity.this);
            }
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
//                    case "删除当前行":
//                        DeleteDate();
//                        break;
                    case "保存为Excel":
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ProductionExcelUtil.writeExcel(ProductionActivity.this,
                                            detailBeenList,
                                            "dfProExcel+" + new Date().toString());
                                    ToastUtils.ShowToastMessage("写入成功",
                                            ProductionActivity.this);
                                } catch (Exception e) {
                                    ToastUtils.ShowToastMessage("写入失败",
                                            ProductionActivity.this);
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        ToastUtils.ShowToastMessage("写入成功，请在设置中Excel文件中查看",
                                ProductionActivity.this);
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
        editor.remove("productionleftItem");
        editor.remove("etprodialogStyle");
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
