package com.daoran.newfactory.onefactory.activity.work.ftydl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.daoran.newfactory.onefactory.adapter.ftydladapter.FTYDLSearchAdapter;
import com.daoran.newfactory.onefactory.adapter.ftydladapter.FTYDLSearchLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDailyBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLSearchBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.Listener.SelectItemInterface;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.utils.Util;
import com.daoran.newfactory.onefactory.view.dialog.ftydldialog.FTYDLSearchDialog;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
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
 * 生产日报页面
 * Created by lizhipeng on 2017/3/29.
 */
public class FTYDLSearchActivity extends BaseFrangmentActivity
        implements View.OnClickListener, SelectItemInterface {

    private NoscrollListView mData;//listview的列表
    private NoscrollListView lv_left;//左侧编号
    private FTYDLSearchDialog FTYDLSearchDialog;//条件查询的dialog
    private FTYDLSearchLeftAdapter mLeftAdapter;//左侧编号适配数据

    private SyncHorizontalScrollView mHeaderHorizontal;//标题scrollview
    private SyncHorizontalScrollView mDataHorizontal;//列表scrollview
    private ImageView ivProductionBack, ivSearch;//返回与条件查询
    private List<FTYDLDailyBean.DataBean> detailBeenList =
            new ArrayList<FTYDLDailyBean.DataBean>();//列表实体list
    private FTYDLDailyBean detailBean;//列表实体bean
    private FTYDLSearchAdapter adapter;//列表适配

    private EditText etSqlDetail;//底部页码输入框
    private TextView tvSignPage;//页数显示
    private Button btnSignPage, btnProSave, spinnermenu;//翻页确定、保存确定，菜单menu
    private ImageView ivUpLeftPage, ivDownRightPage;//上下翻页图片按钮

    private SharedPreferences sp;//存储
    private SPUtils spUtils;
    private int pageCount;//总页数int
    private int pageIndex = 0;//初始页数0
    private LinearLayout ll_visibi;//空数据显示的页面
    private TextView tv_visibi;//空数据显示的页面信息
    private ScrollView scroll_content;//生产日报可上下滑动的视图
    private Spinner spinnProPageClumns;//选择每页显示的条目数
    int keyHeight = 0;
    int screenHeight = 0;
    //本地变动的变量
    private String configid, FTYDLSearchName, FTYDLSearchItem,clumn;
    //接收的变量
    private String FTYDLName,FTYDLDialogItem,FTYDLDialogFactory,FTYDLDialogProcedure,
            productionleftItem,FTYDLStis;

    public static FTYDLSearchActivity FTYDLSearchinstance;//本页面实例

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);//显示主页面
        FTYDLSearchinstance = this;
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        keyHeight = screenHeight / 3;
        getViews();
        initView();
        setListener();
    }

    /*初始化控件*/
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
        ivUpLeftPage = (ImageView) findViewById(R.id.ivUpLeftPage);
        ivDownRightPage = (ImageView) findViewById(R.id.ivDownRightPage);
        Util.setEditTextInhibitInputSpeChat(etSqlDetail);
        getClumnsSpinner();
    }

    /*填充生产日报每页显示条目数spinner数据*/
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
                //将选择的条目数保存到轻量级存储中
                spUtils.put(FTYDLSearchActivity.this,
                        "clumnsprospinner", languages[position]);
                setData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*操作控件*/
    private void initView() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);//关联滑动SyncHorizontalScrollView适配
        etSqlDetail.setSelection(etSqlDetail.getText().length());//将光标移到文本最后
        //判断当前是横屏还是竖屏
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("info", "landscape"); // 横屏
            configid = String.valueOf(1);//赋值使横竖屏切换易于判断
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("info", "portrait"); // 竖屏
            configid = String.valueOf(2);
        }
    }

    /*点击事件*/
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);
        btnProSave.setOnClickListener(this);
        spinnermenu.setOnClickListener(this);
        ivUpLeftPage.setOnClickListener(this);
        ivDownRightPage.setOnClickListener(this);
    }

    /*接收数据*/
    private void setSPUtils(){
        sp = getSharedPreferences("my_sp", 0);
        FTYDLName = sp.getString("FTYDLName",
                "");//条件查询dialog中监听制单人的输入信息
        FTYDLDialogItem = sp.getString("FTYDLDialogItem",
                "");//条件查询dialog中监听款号的输入信息
        FTYDLDialogFactory = sp.getString("FTYDLDialogFactory",
                "");//条件查询dialog中监听工厂的输入信息
        FTYDLDialogProcedure = sp.getString("FTYDLDialogProcedure",
                "");//条件查询dialog中选择的工序
        productionleftItem = sp.getString("productionleftItem",
                "");//查货跟踪长按传过来的款号
        FTYDLStis = sp.getString("FTYDLCheckedd", "");//是否为空
        clumn = sp.getString("clumnsprospinner",
                "");//spinner中选择的条目数
    }

    /*初始化查询全部数据*/
    private void setData() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        setSPUtils();
        //如果查货跟踪传过来款号为空，则将dialog监听款号的信息传给变量，然后由变量进行查询
        if (productionleftItem.equals("")) {
            FTYDLSearchItem = FTYDLDialogItem;
            FTYDLSearchName = FTYDLName;
        } else {
            FTYDLSearchItem = productionleftItem;
            FTYDLSearchName = "";//如果不为空，则查询查货跟踪穿过来的款号
        }

        if (clumn.equals("")) {
            clumn = String.valueOf(10);
        }
        if (FTYDLDialogProcedure.equals("全部")) {
            boolean stris = Boolean.parseBoolean(FTYDLStis);
            Gson gson = new Gson();
            FTYDLSearchBean FTYDLSearchBean = new FTYDLSearchBean();
            FTYDLSearchBean.Conditions conditions = FTYDLSearchBean.new Conditions();
            conditions.setItem(FTYDLSearchItem);
            conditions.setPrddocumentary(FTYDLSearchName);
            conditions.setSubfactory(FTYDLDialogFactory);
            conditions.setWorkingProcedure("");
            conditions.setPrddocumentaryisnull(stris);
            FTYDLSearchBean.setConditions(conditions);
            FTYDLSearchBean.setPageNum(0);
            FTYDLSearchBean.setPageSize(Integer.parseInt(clumn));
            String gsonbeanStr = gson.toJson(FTYDLSearchBean);
            if (NetWork.isNetWorkAvailable(this)) {
                final int finalGetsize = Integer.parseInt(clumn);
                ResponseDialog.showLoading(this, "正在查询");
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
                                    String ress = response.replace("\\", "");
                                    String ression = StringUtil.sideTrim(ress, "\"");
                                    detailBean = new Gson().fromJson(ression, FTYDLDailyBean.class);
                                    detailBeenList = detailBean.getData();
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);
                                        mLeftAdapter = new FTYDLSearchLeftAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
                                        adapter = new FTYDLSearchAdapter(FTYDLSearchActivity.this, detailBeenList);
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
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", FTYDLSearchActivity.this);
            }
        } else {
            boolean stris = Boolean.parseBoolean(FTYDLStis);
            Gson gson = new Gson();
            FTYDLSearchBean FTYDLSearchBean = new FTYDLSearchBean();
            FTYDLSearchBean.Conditions conditions = FTYDLSearchBean.new Conditions();
            conditions.setItem(FTYDLSearchItem);
            conditions.setPrddocumentary(FTYDLSearchName);
            conditions.setSubfactory(FTYDLDialogFactory);
            conditions.setWorkingProcedure(FTYDLDialogProcedure);
            conditions.setPrddocumentaryisnull(stris);
            FTYDLSearchBean.setConditions(conditions);
            FTYDLSearchBean.setPageNum(0);
            FTYDLSearchBean.setPageSize(Integer.parseInt(clumn));
            String gsonbeanStr = gson.toJson(FTYDLSearchBean);/*字符串转为json字符串*/
            if (NetWork.isNetWorkAvailable(this)) {
                final int finalGetsize = Integer.parseInt(clumn);
                ResponseDialog.showLoading(this, "正在查询");
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
                                    detailBean = new Gson().fromJson(ression, FTYDLDailyBean.class);
                                    detailBeenList = detailBean.getData();
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        System.out.print(detailBeenList);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);
                                        mLeftAdapter = new FTYDLSearchLeftAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
                                        adapter = new FTYDLSearchAdapter(FTYDLSearchActivity.this, detailBeenList);
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
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", FTYDLSearchActivity.this);
            }
        }
    }

    /*翻页查询*/
    private void setPageDetail() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        setSPUtils();
        /*获取条件查询dialog中输入的信息字段*/
        if (productionleftItem.equals("")) {
            FTYDLSearchItem = FTYDLDialogItem;
            FTYDLSearchName = FTYDLName;
        } else {
            FTYDLSearchItem = productionleftItem;
            FTYDLSearchName = "";
        }
        if (clumn.equals("")) {
            clumn = String.valueOf(10);
        }
        if (FTYDLDialogProcedure.equals("全部")) {
            boolean stris = Boolean.parseBoolean(FTYDLStis);
            pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
            int index = pageIndex - 1;
            Gson gson = new Gson();
            FTYDLSearchBean FTYDLSearchBean = new FTYDLSearchBean();
            FTYDLSearchBean.Conditions conditions = FTYDLSearchBean.new Conditions();
            conditions.setItem(FTYDLSearchItem);
            conditions.setPrddocumentary(FTYDLSearchName);
            conditions.setSubfactory(FTYDLDialogFactory);
            conditions.setWorkingProcedure("");
            conditions.setPrddocumentaryisnull(stris);
            FTYDLSearchBean.setConditions(conditions);
            FTYDLSearchBean.setPageNum(index);
            FTYDLSearchBean.setPageSize(Integer.parseInt(clumn));
            String gsonbeanStr = gson.toJson(FTYDLSearchBean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this, "正在查询");
                final int finalGetsize = Integer.parseInt(clumn);
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
                                    detailBean = new Gson().fromJson(ression, FTYDLDailyBean.class);
                                    detailBeenList = detailBean.getData();
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        System.out.print(detailBeenList);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);

                                        adapter = new FTYDLSearchAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        mLeftAdapter = new FTYDLSearchLeftAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
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
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", FTYDLSearchActivity.this);
            }
        } else {
            boolean stris = Boolean.parseBoolean(FTYDLStis);
            pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
            int index = pageIndex - 1;
            Gson gson = new Gson();
            FTYDLSearchBean FTYDLSearchBean = new FTYDLSearchBean();
            FTYDLSearchBean.Conditions conditions = FTYDLSearchBean.new Conditions();
            conditions.setItem(FTYDLSearchItem);
            conditions.setPrddocumentary(FTYDLSearchName);
            conditions.setSubfactory(FTYDLDialogFactory);
            conditions.setWorkingProcedure(FTYDLDialogProcedure);
            conditions.setPrddocumentaryisnull(stris);
            FTYDLSearchBean.setConditions(conditions);
            FTYDLSearchBean.setPageNum(index);
            FTYDLSearchBean.setPageSize(Integer.parseInt(clumn));
            String gsonbeanStr = gson.toJson(FTYDLSearchBean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this, "正在查询");
                final int finalGetsize = Integer.parseInt(clumn);
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
                                    detailBean = new Gson().fromJson(ression, FTYDLDailyBean.class);
                                    detailBeenList = detailBean.getData();
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        System.out.print(detailBeenList);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);
                                        adapter = new FTYDLSearchAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        mLeftAdapter = new FTYDLSearchLeftAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
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
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", FTYDLSearchActivity.this);
            }
        }
    }

    /*上一页，下一页*/
    private void setPageUpDate(String pageupIndex) {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        setSPUtils();
        /*获取条件查询dialog中输入的信息字段*/
        if (productionleftItem.equals("")) {
            FTYDLSearchItem = FTYDLDialogItem;
            FTYDLSearchName = FTYDLName;
        } else {
            FTYDLSearchItem = productionleftItem;
            FTYDLSearchName = "";
        }
        if (clumn.equals("")) {
            clumn = String.valueOf(10);
        }
        if (FTYDLDialogProcedure.equals("全部")) {
            boolean stris = Boolean.parseBoolean(FTYDLStis);
            Gson gson = new Gson();
            FTYDLSearchBean FTYDLSearchBean = new FTYDLSearchBean();
            FTYDLSearchBean.Conditions conditions = FTYDLSearchBean.new Conditions();
            conditions.setItem(FTYDLSearchItem);
            conditions.setPrddocumentary(FTYDLSearchName);
            conditions.setSubfactory(FTYDLDialogFactory);
            conditions.setWorkingProcedure("");
            conditions.setPrddocumentaryisnull(stris);
            FTYDLSearchBean.setConditions(conditions);
            FTYDLSearchBean.setPageNum(Integer.parseInt(pageupIndex));
            FTYDLSearchBean.setPageSize(Integer.parseInt(clumn));
            String gsonbeanStr = gson.toJson(FTYDLSearchBean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this, "正在查询");
                final int finalGetsize = Integer.parseInt(clumn);
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
                                    detailBean = new Gson().fromJson(ression, FTYDLDailyBean.class);
                                    detailBeenList = detailBean.getData();
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        System.out.print(detailBeenList);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);

                                        adapter = new FTYDLSearchAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        mLeftAdapter = new FTYDLSearchLeftAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
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
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", FTYDLSearchActivity.this);
            }
        } else {
            boolean stris = Boolean.parseBoolean(FTYDLStis);
            pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
            int index = pageIndex - 1;
            Gson gson = new Gson();
            FTYDLSearchBean FTYDLSearchBean = new FTYDLSearchBean();
            FTYDLSearchBean.Conditions conditions = FTYDLSearchBean.new Conditions();
            conditions.setItem(FTYDLSearchItem);
            conditions.setPrddocumentary(FTYDLSearchName);
            conditions.setSubfactory(FTYDLDialogFactory);
            conditions.setWorkingProcedure(FTYDLDialogProcedure);
            conditions.setPrddocumentaryisnull(stris);
            FTYDLSearchBean.setConditions(conditions);
            FTYDLSearchBean.setPageNum(index);
            FTYDLSearchBean.setPageSize(Integer.parseInt(clumn));
            String gsonbeanStr = gson.toJson(FTYDLSearchBean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this, "正在查询");
                final int finalGetsize = Integer.parseInt(clumn);
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
                                    detailBean = new Gson().fromJson(ression, FTYDLDailyBean.class);
                                    detailBeenList = detailBean.getData();
                                    if (detailBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        System.out.print(detailBeenList);
                                        pageCount = detailBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvSignPage.setText(count);
                                        adapter = new FTYDLSearchAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        mData.setAdapter(adapter);
                                        mLeftAdapter = new FTYDLSearchLeftAdapter(FTYDLSearchActivity.this, detailBeenList);
                                        lv_left.setAdapter(mLeftAdapter);
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }
//                                    setNewlyComfig();
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", FTYDLSearchActivity.this);
            }
        }
    }

    /*弹出输入框*/
    private void ShowDialog(View view) {
        FTYDLSearchDialog = new FTYDLSearchDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        FTYDLSearchDialog.show();
    }

    /*确定*/
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
                    sethideSoft(v);
                    String etsql2 = etSqlDetail.getText().toString();
                    if (etsql2.equals("")) {
                        ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchActivity.this);
                    } else {
                        setPageDetail();
                    }
                    FTYDLSearchDialog.dismiss();
                    break;
            }
        }
    };

    /*取消*/
    private View.OnClickListener onCancleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCancle:
                    FTYDLSearchDialog.dismiss();
                    break;
            }
        }
    };

    /*弹出选择菜单*/
    private void showPopupMenu(final View view) {
        PopupMenu popupMenu = new PopupMenu(FTYDLSearchActivity.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pro, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                switch (title) {
                    case "新建":
                        sethideSoft(view);
                        startActivity(new Intent(FTYDLSearchActivity.this,
                                FTYDLSearchNewlyBuildActivity.class));
                        break;
                    case "横竖屏切换":
                        sethideSoft(view);
                        if (configid.equals("1")) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        } else if (configid.equals("2")) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        } else {

                        }
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

    /*判断软键盘是否弹出*/
    private void sethideSoft(View v) {
        //判断软件盘是否弹出
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                        0);
            } else {
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                        0);
            }
        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回按钮*/
            case R.id.ivProductionBack:
                finish();
                break;
            /*条件查询弹出框*/
            case R.id.ivSearch:
                sethideSoft(v);//判断软键盘是否弹出
                ShowDialog(v);//弹出
                break;
            /*按页查询*/
            case R.id.btnSignPage:
                sethideSoft(v);
                String txt = etSqlDetail.getText().toString();
                String txtcount = tvSignPage.getText().toString();
                //判断页数输入框是否为空，否则不能点击
                if (txt.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchActivity.this);
                } else {
                    int txtindex = Integer.parseInt(txt);
                    int txtcountindex = Integer.parseInt(txtcount);
                    if (txtindex > txtcountindex) {
                        ToastUtils.ShowToastMessage("已经是最后一页", FTYDLSearchActivity.this);
                    } else if (txtindex < 1) {
                        ToastUtils.ShowToastMessage("已经是第一页", FTYDLSearchActivity.this);
                    } else if (txt.length() == 0) {
                        ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchActivity.this);
                    } else if (etSqlDetail.getText().toString() == null) {
                        ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchActivity.this);
                    } else {
                        setPageDetail();
                    }
                }
                break;
            /*menu菜单*/
            case R.id.spinnermenu:
                sethideSoft(v);
                showPopupMenu(spinnermenu);
                break;
            /*保存按钮*/
            case R.id.btnProSave:
                break;
            /*上一页*/
            case R.id.ivUpLeftPage:
                sethideSoft(v);
                String etsql = etSqlDetail.getText().toString();
                //判断页码输入框是否为空
                if (etsql.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchActivity.this);
                } else {
                    pageIndex = Integer.parseInt(etsql);
                    int index = pageIndex - 2;
                    if (index < 0) {
                        ToastUtils.ShowToastMessage("已经是第一页", FTYDLSearchActivity.this);
                    } else {
                        String indexstr = String.valueOf(index);
                        int indexcount = index + 1;
                        etSqlDetail.setText(String.valueOf(indexcount));
                        etSqlDetail.setSelection(String.valueOf(indexcount).length());
                        setPageUpDate(indexstr);
                    }
                }
                break;
            /*下一页*/
            case R.id.ivDownRightPage:
                sethideSoft(v);
                String etsql2 = etSqlDetail.getText().toString();
                //判断页码输入框是否为空
                if (etsql2.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchActivity.this);
                } else {
                    int textedit2 = Integer.parseInt(etsql2);
                    int editindex = textedit2;
                    String maxpageindex = tvSignPage.getText().toString();
                    int indexmax = Integer.parseInt(maxpageindex);
                    int index2 = editindex + 1;
                    if (index2 > indexmax) {
                        ToastUtils.ShowToastMessage("已经是最后一页", FTYDLSearchActivity.this);
                    } else {
                        String index2str = String.valueOf(editindex);
                        int indexcount = editindex + 1;
                        etSqlDetail.setText(String.valueOf(indexcount));
                        etSqlDetail.setSelection(String.valueOf(indexcount).length());
                        setPageUpDate(index2str);
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = sp.edit();
        /*关闭界面后，删除本地存储的字段*/
        editor.remove("prouriid");
        editor.remove("prosalesid");
        editor.remove("proProcedureTitle");
        editor.remove("proadapterPrdstatusTitle");
        editor.remove("productionsaveOthers");
        editor.remove("productionOthers");
        editor.remove("productionTaskNumber");
        editor.remove("productionCompletedLastMonth");
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
        editor.remove("FTYDLDialogItem");
        editor.remove("prosaveothers");
        editor.remove("prosavetasknunber");
        editor.remove("prosavecompletedlastmonth");
        editor.remove("prosaveoneday");
        editor.remove("prosavetwoday");
        editor.remove("prothreeday");
        editor.remove("prosaveforeday");
        editor.remove("prosavefiveday");
        editor.remove("prosavesixday");
        editor.remove("prosavesevenday");
        editor.remove("prosaveeightday");
        editor.remove("prosavenineday");
        editor.remove("prosaveelevenday");
        editor.remove("prosavetenday");
        editor.remove("prosavetwelveday");
        editor.remove("prosavethirteenday");
        editor.remove("prosavefourteenday");
        editor.remove("prosavefifteenday");
        editor.remove("prosavesixteenday");
        editor.remove("prosaveserventeenday");
        editor.remove("prosaveeighteenday");
        editor.remove("prosavenineteenday");
        editor.remove("prosavetwentyday");
        editor.remove("prosavetwentyoneday");
        editor.remove("prosavetwentytwoday");
        editor.remove("prosavetwentythreeday");
        editor.remove("prosavetwentyforeday");
        editor.remove("prosavetwentyfiveday");
        editor.remove("prosavetwentysixday");
        editor.remove("prosavetwentysevenday");
        editor.remove("prosavetwentyeightday");
        editor.remove("prosavetwentynineday");
        editor.remove("prosavethirtyday");
        editor.remove("prosavethirtyoneday");
        editor.remove("prosaveremarks");
        editor.remove("prosavemonth");
        editor.remove("prosavedepartment");
        editor.remove("probooleanProcedureTitle");
        editor.remove("prosavestate");
        editor.remove("FTYDLDialogFactory");

        editor.remove("pronullothers");
        editor.remove("pronulltasknumber");
        editor.remove("pronulllastmon");
        editor.remove("pronullprocedure");
        editor.remove("pronullpartment");
        editor.remove("pronullstate");
        editor.remove("pronullmonth");
        editor.remove("pronullday1");
        editor.remove("pronullday2");
        editor.remove("pronullday3");
        editor.remove("pronullday4");
        editor.remove("pronullday5");
        editor.remove("pronullday6");
        editor.remove("pronullday7");
        editor.remove("pronullday8");
        editor.remove("pronullday9");
        editor.remove("pronullday10");
        editor.remove("pronullday11");
        editor.remove("pronullday12");
        editor.remove("pronullday13");
        editor.remove("pronullday14");
        editor.remove("pronullday15");
        editor.remove("pronullday16");
        editor.remove("pronullday17");
        editor.remove("pronullday18");
        editor.remove("pronullday19");
        editor.remove("pronullday20");
        editor.remove("pronullday21");
        editor.remove("pronullday22");
        editor.remove("pronullday23");
        editor.remove("pronullday24");
        editor.remove("pronullday25");
        editor.remove("pronullday26");
        editor.remove("pronullday27");
        editor.remove("pronullday28");
        editor.remove("pronullday29");
        editor.remove("pronullday30");
        editor.remove("pronullday31");
        editor.remove("pronullmemo");
        editor.commit();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }
}
