package com.daoran.newfactory.onefactory.activity.work.commo;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import com.daoran.newfactory.onefactory.adapter.CommoditySqlAdapter;
import com.daoran.newfactory.onefactory.adapter.CommoditySqlLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ClumnsResultBean;
import com.daoran.newfactory.onefactory.bean.CommodityPostBean;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.save.CommodityExcelUtil;
import com.daoran.newfactory.onefactory.view.dialog.CommoDialog;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 查货跟踪单
 * Created by lizhipeng on 2017/3/29.
 */
public class CommoditySqlActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private NoscrollListView mData;//列表
    private NoscrollListView lv_cleft;//左侧款号列表
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack;//返回按钮
    private CommoDialog commoDialog;//查货条件查询弹出框
    private ImageView ivSearch;//条件查询

    private List<CommoditydetailBean.DataBean> dataBeen
            = new ArrayList<CommoditydetailBean.DataBean>();//查货信息实体集合
    private CommoditydetailBean commoditydetailBean;//列表实体bean
    private CommoditySqlLeftAdapter leftAdapter;
    private CommoditySqlAdapter sqlAdapter;//列表适配
    private ClumnsResultBean resultBean;

    private TextView tvSignPage;//显示的总页数
    private EditText etSqlDetail;//输入的页数
    private Button btnSignPage;//翻页确认
    private Button btnCommoSave;//保存
    private Button spinnermenu;
    private LinearLayout ll_visibi;
    private TextView tv_visibi;
    private ScrollView scroll_content;
    private Spinner spinnCommoPageClumns;
    private ImageView ivUpLeftPage, ivDownRightPage;

    private SharedPreferences sp;//轻量级存储本地数据
    private SPUtils spUtils;
    private int pageCount;//查询获取的总页数
    private int pageIndex = 0;//初始显示的页数
    private String configid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);//加载主页面
        getViews();
        initView();
        setData();
        setListener();
        setColumnRight();
    }

    /**
     * 初始化控件
     */
    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivCommoditySql);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        lv_cleft = (NoscrollListView) findViewById(R.id.lv_cleft);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        tvSignPage = (TextView) findViewById(R.id.tvSignPage);
        btnSignPage = (Button) findViewById(R.id.btnSignPage);
        etSqlDetail = (EditText) findViewById(R.id.etSqlDetail);
        btnCommoSave = (Button) findViewById(R.id.btnCommoSave);
        ll_visibi = (LinearLayout) findViewById(R.id.ll_visibi);
        tv_visibi = (TextView) findViewById(R.id.tv_visibi);
        scroll_content = (ScrollView) findViewById(R.id.scroll_content);
        spinnCommoPageClumns = (Spinner) findViewById(R.id.spinnCommoPageClumns);
        spinnermenu = (Button) findViewById(R.id.spinnermenu);
        ivUpLeftPage = (ImageView) findViewById(R.id.ivUpLeftPage);
        ivDownRightPage = (ImageView) findViewById(R.id.ivDownRightPage);
        setEditTextInhibitInputSpeChat(etSqlDetail);
        getClumnsSpinner();
    }

    /**
     * 填充查货跟踪单每页显示条目spinner数据
     */
    private void getClumnsSpinner() {
        String[] spinner = getResources().getStringArray(R.array.clumnsCommon);
        ArrayAdapter<String> adapterclumns = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner);
        adapterclumns.setDropDownViewResource(R.layout.dropdown_stytle);
        spinnCommoPageClumns.setAdapter(adapterclumns);
        spinnCommoPageClumns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().
                        getStringArray(R.array.clumnsCommon);
                spUtils.put(CommoditySqlActivity.this,
                        "clumnsspinner", languages[position]);
                setData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 控件操作
     */
    private void initView() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
        etSqlDetail.setSelection(etSqlDetail.length());
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("info", "landscape"); // 横屏
            configid = String.valueOf(1);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("info", "portrait"); // 竖屏
            configid = String.valueOf(2);
        }
    }

    /**
     * 实例化点击事件
     */
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);
        btnCommoSave.setOnClickListener(this);
        spinnermenu.setOnClickListener(this);
        ivUpLeftPage.setOnClickListener(this);
        ivDownRightPage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回按钮*/
            case R.id.ivCommoditySql:
                finish();
                break;
            /*查询*/
            case R.id.ivSearch:
                ShowDialog(v);
                break;
            /*翻页确认按钮*/
            case R.id.btnSignPage:
                String txt = etSqlDetail.getText().toString();
                String txtcount = tvSignPage.getText().toString();
                if (txt.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", CommoditySqlActivity.this);
                } else {
                    int txtindex = Integer.parseInt(txt);
                    int txtcountindex = Integer.parseInt(txtcount);
                    if (txtindex > txtcountindex) {
                        ToastUtils.ShowToastMessage("已经是最后一页", CommoditySqlActivity.this);
                    } else if (txtindex < 1) {
                        ToastUtils.ShowToastMessage("已经是第一页", CommoditySqlActivity.this);
                    } else if (txt.length() == 0) {
                        ToastUtils.ShowToastMessage("页码不能为空", CommoditySqlActivity.this);
                    } else if (txt.length() > txtcount.length()) {
                        ToastUtils.ShowToastMessage("页码超出输入范围", CommoditySqlActivity.this);
                    } else {
                        setPageDetail();
                    }
                }
                break;
            /*保存*/
            case R.id.btnCommoSave:
                setCommoSave();
                break;
            /*弹出菜单*/
            case R.id.spinnermenu:
                showPopupMenu(spinnermenu);
                break;
            /*上一页*/
            case R.id.ivUpLeftPage:
                String stredit = etSqlDetail.getText().toString();
                if (stredit.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", CommoditySqlActivity.this);
                } else {
                    int pageindex = Integer.parseInt(stredit);
                    int index = pageindex - 2;
                    if (index < 0) {
                        ToastUtils.ShowToastMessage("已经是第一页", CommoditySqlActivity.this);
                    } else {
                        String indexstr = String.valueOf(index + 1);
                        etSqlDetail.setText(indexstr);
                        etSqlDetail.setSelection(indexstr.length());
                        setPageDate(index);
                    }
                }
                break;
            /*下一页*/
            case R.id.ivDownRightPage:
                String stredit2 = etSqlDetail.getText().toString();
                if (stredit2.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", CommoditySqlActivity.this);
                } else {
                    int pageIndexx = Integer.parseInt(stredit2);
                    int index2 = pageIndexx + 1;
                    String maxpageindex = tvSignPage.getText().toString();
                    int indexmax = Integer.parseInt(maxpageindex);
                    if (index2 > indexmax) {
                        ToastUtils.ShowToastMessage("已经是最后一页", CommoditySqlActivity.this);
                    } else {
                        String index2str = String.valueOf(index2);
                        etSqlDetail.setText(index2str);
                        etSqlDetail.setSelection(index2str.length());
                        setPageDate(index2);
                    }
                }
                break;
        }
    }

    /**
     * 弹出选择菜单
     *
     * @param view
     */
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(CommoditySqlActivity.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_commo, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                switch (title) {
                    case "刷新":
                        setData();
                        break;
                    case "横竖屏切换":
                        if (configid.equals("1")) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        } else if (configid.equals("2")) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        } else {

                        }
                        break;
                    case "保存为Excel":
                        final ProgressDialog progressDialog = ProgressDialog.show(CommoditySqlActivity.this,
                                "请稍候...", "正在生成Excel中...", false, true);
                        final Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    if (dataBeen.size() != 0) {
                                        Looper.prepare();
                                        ToastUtils.ShowToastMessage("写入成功",
                                                CommoditySqlActivity.this);
                                        CommodityExcelUtil.writeExcel(CommoditySqlActivity.this,
                                                dataBeen,
                                                "dfCommoExcel+" + new Date().toString());
                                        progressDialog.dismiss();
                                        Looper.loop();
                                    } else {
                                        Looper.prepare();
                                        ToastUtils.ShowToastMessage("没有数据",
                                                CommoditySqlActivity.this);
                                        progressDialog.dismiss();
                                        Looper.loop();
                                    }
                                } catch (Exception e) {
                                    Looper.prepare();
                                    ToastUtils.ShowToastMessage("写入失败",
                                            CommoditySqlActivity.this);
                                    e.printStackTrace();
                                    progressDialog.dismiss();
                                    Looper.loop();
                                }
                            }
                        });
                        thread.start();
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

    /**
     * 查询按钮弹出框
     *
     * @param view
     */
    private void ShowDialog(View view) {
        commoDialog = new CommoDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        commoDialog.show();
    }

    /**
     * 确认
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
                    String etsql2 = etSqlDetail.getText().toString();
                    if (etsql2.equals("")) {
                        ToastUtils.ShowToastMessage("页码不能为空", CommoditySqlActivity.this);
                    } else {
                        setPageDetail();
                    }
                    commoDialog.dismiss();
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
                    commoDialog.dismiss();
                    break;
            }
        }
    };

    /**
     * 初始化查询数据
     */
    private void setData() {
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        sp = this.getSharedPreferences("my_sp", 0);
        String recodename = sp.getString("commoname", "");//跟单
        String Style = sp.getString("commoStyle", "");//款号
        String pagesize = sp.getString("clumnsspinner", "");
        if (pagesize.equals("")) {
            pagesize = String.valueOf(10);
        }
        String Recode = sp.getString("commoRecode", "");//巡检
        String etprodialogProcedure = sp.getString("etprodialogProcedure", "");//生产主管
        String ischeck = sp.getString("ischeckedd", "");//是否可为空
        boolean stris = Boolean.parseBoolean(ischeck);
        Gson gson = new Gson();
        CommodityPostBean postBean = new CommodityPostBean();
        CommodityPostBean.Conditions conditions = postBean.new Conditions();
        conditions.setItem(Style);
        conditions.setPrddocumentary(recodename);
        conditions.setPrdmaster(etprodialogProcedure);
        conditions.setIPQC(Recode);
        conditions.setPrdmasterisnull(stris);
        postBean.setConditions(conditions);
        postBean.setPageNum(0);
        postBean.setPageSize(Integer.parseInt(pagesize));
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            final ProgressDialog progressDialog = ProgressDialog.show(this,
                    "请稍候...", "正在查询中...", false, true);
            final int finalGetsize = Integer.parseInt(pagesize);
            OkHttpUtils.postString()
                    .url(str)
                    .content(stringpost)
                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                            thread.start();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                System.out.print(response);
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                commoditydetailBean = new Gson().fromJson(ression, CommoditydetailBean.class);
                                dataBeen = commoditydetailBean.getData();
                                if (commoditydetailBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    System.out.print(dataBeen);
                                    pageCount = commoditydetailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    tvSignPage.setText(count);
                                    sqlAdapter = new CommoditySqlAdapter(CommoditySqlActivity.this, dataBeen);
                                    mData.setAdapter(sqlAdapter);
                                    leftAdapter = new CommoditySqlLeftAdapter(CommoditySqlActivity.this, dataBeen);
                                    lv_cleft.setAdapter(leftAdapter);
                                } else {
                                    ll_visibi.setVisibility(View.VISIBLE);
                                    scroll_content.setVisibility(View.GONE);
                                    tv_visibi.setText("没有更多数据");
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", CommoditySqlActivity.this);
        }
    }

    /**
     * 指定页码查询数据
     */
    private void setPageDetail() {
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        sp = CommoditySqlActivity.this.getSharedPreferences("my_sp", 0);
        String recodename = sp.getString("commoname", "");//跟单
        if (recodename.contains("\n")) {
            recodename = "";
        }
        String Style = sp.getString("commoStyle", "");//款号
        if (Style.contains("\n")) {
            Style = "";
        }
        String pagesize = sp.getString("clumnsspinner", "");
        String Recode = sp.getString("commoRecode", "");//巡检
        if (Recode.contains("\n")) {
            Recode = "";
        }
        String etprodialogProcedure = sp.getString("etproProcedure", "");//生产主管
        if (etprodialogProcedure.contains("\n")) {
            etprodialogProcedure = "";
        }
        String ischeck = sp.getString("ischeckedd", "");//是否可为空
        boolean stris = Boolean.parseBoolean(ischeck);
        pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
        int Index = pageIndex - 1;
        Gson gson = new Gson();
        CommodityPostBean postBean = new CommodityPostBean();
        CommodityPostBean.Conditions conditions = postBean.new Conditions();
        conditions.setItem(Style);
        conditions.setPrddocumentary(recodename);
        conditions.setPrdmaster(etprodialogProcedure);
        conditions.setIPQC(Recode);
        conditions.setPrdmasterisnull(stris);
        postBean.setConditions(conditions);
        postBean.setPageNum(Index);
        postBean.setPageSize(Integer.parseInt(pagesize));
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            final ProgressDialog progressDialog = ProgressDialog.show(this,
                    "请稍候...", "正在查询中...", false, true);
            final int finalGetsize = Integer.parseInt(pagesize);
            OkHttpUtils.postString()
                    .url(str)
                    .content(stringpost)
                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                            thread.start();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                System.out.print(response);
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                commoditydetailBean = new Gson().fromJson(ression, CommoditydetailBean.class);
                                dataBeen = commoditydetailBean.getData();
                                if (commoditydetailBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    System.out.print(dataBeen);
                                    pageCount = commoditydetailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    tvSignPage.setText(count);
                                    sqlAdapter = new CommoditySqlAdapter(CommoditySqlActivity.this, dataBeen);
                                    mData.setAdapter(sqlAdapter);
                                    leftAdapter = new CommoditySqlLeftAdapter(CommoditySqlActivity.this, dataBeen);
                                    lv_cleft.setAdapter(leftAdapter);
                                } else {
                                    ll_visibi.setVisibility(View.VISIBLE);
                                    scroll_content.setVisibility(View.GONE);
                                    tv_visibi.setText("没有更多信息");
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", CommoditySqlActivity.this);
        }
    }

    /**
     * 上一页下一页
     *
     * @param pageIndexin
     */
    private void setPageDate(int pageIndexin) {
        ResponseDialog.showLoading(this);
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        sp = CommoditySqlActivity.this.getSharedPreferences("my_sp", 0);
        String recodename = sp.getString("commoname", "");//跟单
        String Style = sp.getString("commoStyle", "");//款号
        String pagesize = sp.getString("clumnsspinner", "");
        String Recode = sp.getString("commoRecode", "");//巡检
        String etprodialogProcedure = sp.getString("etproProcedure", "");//生产主管
        String ischeck = sp.getString("ischeckedd", "");//是否可为空
        boolean stris = Boolean.parseBoolean(ischeck);
        Gson gson = new Gson();
        CommodityPostBean postBean = new CommodityPostBean();
        CommodityPostBean.Conditions conditions = postBean.new Conditions();
        conditions.setItem(Style);
        conditions.setPrddocumentary(recodename);
        conditions.setPrdmaster(etprodialogProcedure);
        conditions.setIPQC(Recode);
        conditions.setPrdmasterisnull(stris);
        postBean.setConditions(conditions);
        postBean.setPageNum(pageIndexin);
        postBean.setPageSize(Integer.parseInt(pagesize));
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this);
            final int finalGetsize = Integer.parseInt(pagesize);
            OkHttpUtils.postString()
                    .url(str)
                    .content(stringpost)
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
                                commoditydetailBean = new Gson().fromJson(ression, CommoditydetailBean.class);
                                dataBeen = commoditydetailBean.getData();
                                if (commoditydetailBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    System.out.print(dataBeen);
                                    pageCount = commoditydetailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    tvSignPage.setText(count);
                                    sqlAdapter = new CommoditySqlAdapter(CommoditySqlActivity.this, dataBeen);
                                    mData.setAdapter(sqlAdapter);
                                    leftAdapter = new CommoditySqlLeftAdapter(CommoditySqlActivity.this, dataBeen);
                                    lv_cleft.setAdapter(leftAdapter);
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
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", CommoditySqlActivity.this);
        }
    }

    /**
     * 分配列权限
     */
    private void setColumnRight() {
        String strloginid = sp.getString("commologinid", "");
        String args = "pd_saleslist,查货跟踪表," + strloginid;
        String columnSql = HttpUrl.debugoneUrl + "Common/GetClumns/?id=" +
                escape(args);
        OkHttpUtils.get()
                .url(columnSql)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.print(response);//得到的是pd_saleslist里面的所有数据，
                        // 一般都是null，0678账号中JsonText字段有json数组
                        resultBean = new Gson().fromJson(response, ClumnsResultBean.class);
                        System.out.print(resultBean);
                        if (resultBean.getJsonText() != null) {//如果JsonText不为空的话，就转义，然后json解析到实体类
                            String resultJsontext = resultBean.getJsonText().replace("\\", "");
                            System.out.print(resultJsontext);
                        }
                    }
                });
    }

    /**
     * 保存
     */
    private void setCommoSave() {
        if (NetWork.isNetWorkAvailable(this)) {
            sp = getSharedPreferences("my_sp", 0);
            String saveurl = HttpUrl.debugoneUrl + "QACwork/SaveQACwork/";
            System.out.print(dataBeen);
            /*判断修改前和修改后的值是否相同*/
            String commonulltitle = sp.getString("commonulltitle", "");//后道
            if (commonulltitle.equals("")) {
                commonulltitle = "1";
            }
            String commonullitem = sp.getString("commonullitem", "");//主管评分
            if (commonullitem.equals("")) {
                commonullitem = "1";
            }
            String commonullsearledrev = sp.getString("commonullsearledrev", "");//封样资料接收时间
            if (commonullsearledrev.equals("")) {
                commonullsearledrev = "1";
            }
            String commonulldocback = sp.getString("commonulldocback", "");//大货资料接收时间
            if (commonulldocback.equals("")) {
                commonulldocback = "1";
            }
            String commonullmemo = sp.getString("commonullmemo", "");//需要特别备注的情况
            if (commonullmemo.equals("")) {
                commonullmemo = "1";
            }
            String commonullpreducdt = sp.getString("commonullpreducdt", "");//预计产前报告时间
            if (commonullpreducdt.equals("")) {
                commonullpreducdt = "1";
            }
            String commonullpred = sp.getString("commonullpred", "");//开产前会时间
            if (commonullpred.equals("")) {
                commonullpred = "1";
            }
            String commonullpredoc = sp.getString("commonullpredoc", "");//产前会报告
            if (commonullpredoc.equals("")) {
                commonullpredoc = "1";
            }
            String commonullfabricsok = sp.getString("commonullfabricsok", "");//大货面料情况
            if (commonullfabricsok.equals("")) {
                commonullfabricsok = "1";
            }
            String commonullaccessori = sp.getString("commonullaccessori", "");//大货辅料情况
            if (commonullaccessori.equals("")) {
                commonullaccessori = "1";
            }
            String commonullspcprodec = sp.getString("commonullspcprodec", "");//大货特殊工艺情况
            if (commonullspcprodec.equals("")) {
                commonullspcprodec = "1";
            }
            String commonullspcpromemo = sp.getString("commonullspcpromemo", "");//特殊工艺特别备注
            if (commonullspcpromemo.equals("")) {
                commonullspcpromemo = "1";
            }
            String commonullcutqty = sp.getString("commonullcutqty", "");//实裁数
            if (commonullcutqty.equals("")) {
                commonullcutqty = "1";
            }
            String commonullsewfdt = sp.getString("commonullsewfdt", "");//上线日期
            if (commonullsewfdt.equals("")) {
                commonullsewfdt = "1";
            }
            String commonullsewmdt = sp.getString("commonullsewmdt", "");//下线日期
            if (commonullsewmdt.equals("")) {
                commonullsewmdt = "1";
            }
            String commonullprebdt = sp.getString("commonullprebdt", "");//预计早期时间
            if (commonullprebdt.equals("")) {
                commonullprebdt = "1";
            }
            String commonullqcbdt = sp.getString("commonullqcbdt", "");//自查早期时间
            if (commonullqcbdt.equals("")) {
                commonullqcbdt = "1";
            }
            String commonullqcbdtdoc = sp.getString("commonullqcbdtdoc", "");//早期报告
            if (commonullqcbdtdoc.equals("")) {
                commonullqcbdtdoc = "1";
            }
            String commonullpremdt = sp.getString("commonullpremdt", "");//预计中期时间
            if (commonullpremdt.equals("")) {
                commonullpremdt = "1";
            }
            String commonullqcmdt = sp.getString("commonullqcmdt", "");//自查中期时间
            if (commonullqcmdt.equals("")) {
                commonullqcmdt = "1";
            }
            String commonullqcmdtdoc = sp.getString("commonullqcmdtdoc", "");//中期报告
            if (commonullqcmdtdoc.equals("")) {
                commonullqcmdtdoc = "1";
            }
            String commonullpreedt = sp.getString("commonullpreedt", "");//预计尾期时间
            if (commonullpreedt.equals("")) {
                commonullpreedt = "1";
            }
            String commonullqcmedt = sp.getString("commonullqcmedt", "");//自查尾期时间
            if (commonullqcmedt.equals("")) {
                commonullqcmedt = "1";
            }
            String commonullqcedtdoc = sp.getString("commonullqcedtdoc", "");//尾查报告
            if (commonullqcedtdoc.equals("")) {
                commonullqcedtdoc = "1";
            }
            String commonullfctmdt = sp.getString("commonullfctmdt", "");//客查中期时间
            if (commonullfctmdt.equals("")) {
                commonullfctmdt = "1";
            }
            String commonullfctedt = sp.getString("commonullfctedt", "");//客查尾期时间
            if (commonullfctedt.equals("")) {
                commonullfctedt = "1";
            }
            String commonullpackbdat = sp.getString("commonullpackbdat", "");//成品包装开始时间
            if (commonullpackbdat.equals("")) {
                commonullpackbdat = "1";
            }
            String commonullpackqty2 = sp.getString("commonullpackqty2", "");//装箱数量
            if (commonullpackqty2.equals("")) {
                commonullpackqty2 = "1";
            }
            String commonullqcmemo = sp.getString("commonullqcmemo", "");//qc特别备注
            if (commonullqcmemo.equals("")) {
                commonullqcmemo = "1";
            }
            String commonullfactlcdat = sp.getString("commonullfactlcdat", "");//离厂日期
            if (commonullfactlcdat.equals("")) {
                commonullfactlcdat = "1";
            }
            String commonullBatchid = sp.getString("commonullBatchid", "");//查货批次
            if (commonullBatchid.equals("")) {
                commonullBatchid = "1";
            }
            String commonullCtmchkdt = sp.getString("commonullCtmchkdt", "");//业务员确认客查日期
            if (commonullCtmchkdt.equals("")) {
                commonullCtmchkdt = "1";
            }
            String commonullipqcpedt = sp.getString("commonullipqcpedt", "");//尾查预查
            if (commonullipqcpedt.equals("")) {
                commonullipqcpedt = "1";
            }
            String commonullipqcmdt = sp.getString("commonullipqcmdt", "");//巡检中查
            if (commonullipqcmdt.equals("")) {
                commonullipqcmdt = "1";
            }
            String commonullqaname = sp.getString("commonullqaname", "");//QA首扎
            if (commonullqaname.equals("")) {
                commonullqaname = "1";
            }
            String commonullqascore = sp.getString("commonullqascore", "");//QA首扎件数
            if (commonullqascore.equals("")) {
                commonullqascore = "1";
            }
            String commonullqamemo = sp.getString("commonullqamemo", "");//QA首扎日
            if (commonullqamemo.equals("")) {
                commonullqamemo = "1";
            }
            Gson gson = new Gson();
            String commjson = gson.toJson(dataBeen);
            String dateee = commjson.replace("\"\"", "null");
            final ProgressDialog progressDialog = ProgressDialog.show(this,
                    "请稍候...", "正在保存中...", false, true);
            if (commonulltitle.equals("1") && commonullitem.equals("1") && commonullsearledrev.equals("1")
                    && commonulldocback.equals("1") && commonullmemo.equals("1") && commonullpreducdt.equals("1")
                    && commonullpred.equals("1") && commonullpredoc.equals("1") && commonullfabricsok.equals("1")
                    && commonullaccessori.equals("1") && commonullspcprodec.equals("1") && commonullspcpromemo.equals("1")
                    && commonullcutqty.equals("1") && commonullsewfdt.equals("1") && commonullsewmdt.equals("1")
                    && commonullprebdt.equals("1") && commonullqcbdt.equals("1") && commonullqcbdtdoc.equals("1")
                    && commonullpremdt.equals("1") && commonullqcmdt.equals("1") && commonullqcmdtdoc.equals("1")
                    && commonullpreedt.equals("1") && commonullqcmedt.equals("1") && commonullqcedtdoc.equals("1")
                    && commonullfctmdt.equals("1") && commonullfctedt.equals("1") && commonullpackbdat.equals("1")
                    && commonullpackqty2.equals("1") && commonullqcmemo.equals("1") && commonullfactlcdat.equals("1")
                    && commonullBatchid.equals("1") && commonullCtmchkdt.equals("1") && commonullipqcpedt.equals("1")
                    && commonullipqcmdt.equals("1") && commonullqaname.equals("1") && commonullqascore.equals("1")
                    && commonullqamemo.equals("1")) {
                ToastUtils.ShowToastMessage("未修改表中数据", CommoditySqlActivity.this);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                });
                thread.start();
            } else {
                OkHttpUtils.postString()
                        .url(saveurl)
                        .content(dateee)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
                                ToastUtils.ShowToastMessage("数据错误，请重新输入", CommoditySqlActivity.this);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                System.out.print(response);
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
                                response = response.replace("\\", "");
                                String ression = StringUtil.sideTrim(response, "\"");
                                System.out.print(ression);
                                if (ression.equals("true")) {
                                    ToastUtils.ShowToastMessage("保存成功", CommoditySqlActivity.this);
                                    setData();
                                } else {
                                    ToastUtils.ShowToastMessage("保存失败", CommoditySqlActivity.this);
                                }
                                deletesp();
                            }
                        });
            }
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, CommoditySqlActivity.this);
        }
    }

    /**
     * escape编解码
     *
     * @param src
     * @return
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * 启动
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 退出界面后删除轻量级存储my_sp中的数据
     */
    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("commoproid");
        editor.remove("CommodityQCMasterScore");//主管评分
        editor.remove("dateSealedrewtimesign");//封样资料接收时间
        editor.remove("dateDocbacktimesign");//大货资料接收时间
        editor.remove("CommodityPreMemo");//需要特别备注的情况
        editor.remove("datePredocdttimesign");//预计产前报告时间
        editor.remove("datePredtimesign");//开产前会时间
        editor.remove("CommodityPredoc");//产前会报告
        editor.remove("CommodityFabricsok");//大货面料情况
        editor.remove("CommodityAccessoriesok");//大货辅料情况
        editor.remove("CommoditySpcproDec");//大货特殊工艺情况
        editor.remove("CommoditySpcproMemo");//特殊工艺特别备注
        editor.remove("CommodityCutqty");//实裁数
        editor.remove("dateSewFdttimesign");//上线日期
        editor.remove("dateSewMdttimesign");//下线日期
        editor.remove("datePrebdttimesign");//预计早期时间
        editor.remove("dateQCbdttimesign");//自查早期时间
        editor.remove("CommodityQCbdtDoc");//早期报告
        editor.remove("datePremdttimesign");//预计中期时间
        editor.remove("dateQCmdttimesign");//自查中期时间
        editor.remove("CommodityQCmdtDoc");//中期报告
        editor.remove("datePreedttimesign");//预计尾期时间
        editor.remove("dateQCMedttimesign");//自查尾期时间
        editor.remove("CommodityQCedtDoc");//尾期报告
        editor.remove("dateFctmdttimesign");//客查中期时间
        editor.remove("dateFctedttimesign");//客查尾期时间
        editor.remove("datePackbdattimesign");//成品包装开始日期
        editor.remove("CommodityPackqty2");//装箱数量
        editor.remove("CommodityQCMemo");//QC特别备注
        editor.remove("dateFactlcdattimesign");//离厂日期
        editor.remove("CommodityBatchid");//查货批次
        editor.remove("commohdTitle");//后道
        editor.remove("dateCtmchkdttimesign");//业务员确认客查日期
        editor.remove("CommodityIPQCPedt");//尾查预查
        editor.remove("CommodityIPQCmdt");//巡检中查
        editor.remove("CommodityQAname");//QA首扎
        editor.remove("CommodityQAScore");//QA首扎件数
        editor.remove("dateQAMemotimesign");//QA首扎日

        editor.remove("commonulltitle");//后道
        editor.remove("commonullitem");
        editor.remove("commonullsearledrev");
        editor.remove("commonulldocback");
        editor.remove("commonullmemo");
        editor.remove("commonullpreducdt");
        editor.remove("commonullpred");
        editor.remove("commonullpredoc");
        editor.remove("commonullfabricsok");
        editor.remove("commonullaccessori");
        editor.remove("commonullspcprodec");
        editor.remove("commonullspcpromemo");
        editor.remove("commonullcutqty");
        editor.remove("commonullsewfdt");
        editor.remove("commonullsewmdt");
        editor.remove("commonullprebdt");
        editor.remove("commonullqcbdt");
        editor.remove("commonullqcbdtdoc");
        editor.remove("commonullpremdt");
        editor.remove("commonullqcmdt");
        editor.remove("commonullqcmdtdoc");
        editor.remove("commonullpreedt");
        editor.remove("commonullqcmedt");
        editor.remove("commonullqcedtdoc");
        editor.remove("commonullfctmdt");
        editor.remove("commonullfctedt");
        editor.remove("commonullpackbdat");
        editor.remove("commonullpackqty2");
        editor.remove("commonullqcmemo");
        editor.remove("commonullfactlcdat");
        editor.remove("commonullBatchid");
        editor.remove("commonullCtmchkdt");
        editor.remove("commonullipqcpedt");
        editor.remove("commonullipqcmdt");
        editor.remove("commonullqaname");
        editor.remove("commonullqascore");
        editor.remove("commonullqamemo");
        editor.commit();
        super.onDestroy();
    }

    /**
     * 完成后删除保存的临时信息
     */
    private void deletesp() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("commoproid");
        editor.remove("CommodityQCMasterScore");//主管评分
        editor.remove("dateSealedrewtimesign");//封样资料接收时间
        editor.remove("dateDocbacktimesign");//大货资料接收时间
        editor.remove("CommodityPreMemo");//需要特别备注的情况
        editor.remove("datePredocdttimesign");//预计产前报告时间
        editor.remove("datePredtimesign");//开产前会时间
        editor.remove("CommodityPredoc");//产前会报告
        editor.remove("CommodityFabricsok");//大货面料情况
        editor.remove("CommodityAccessoriesok");//大货辅料情况
        editor.remove("CommoditySpcproDec");//大货特殊工艺情况
        editor.remove("CommoditySpcproMemo");//特殊工艺特别备注
        editor.remove("CommodityCutqty");//实裁数
        editor.remove("dateSewFdttimesign");//上线日期
        editor.remove("dateSewMdttimesign");//下线日期
        editor.remove("datePrebdttimesign");//预计早期时间
        editor.remove("dateQCbdttimesign");//自查早期时间
        editor.remove("CommodityQCbdtDoc");//早期报告
        editor.remove("datePremdttimesign");//预计中期时间
        editor.remove("dateQCmdttimesign");//自查中期时间
        editor.remove("CommodityQCmdtDoc");//中期报告
        editor.remove("datePreedttimesign");//预计尾期时间
        editor.remove("dateQCMedttimesign");//自查尾期时间
        editor.remove("CommodityQCedtDoc");//尾期报告
        editor.remove("dateFctmdttimesign");//客查中期时间
        editor.remove("dateFctedttimesign");//客查尾期时间
        editor.remove("datePackbdattimesign");//成品包装开始日期
        editor.remove("CommodityPackqty2");//装箱数量
        editor.remove("CommodityQCMemo");//QC特别备注
        editor.remove("dateFactlcdattimesign");//离厂日期
        editor.remove("CommodityBatchid");//查货批次
        editor.remove("commohdTitle");//后道
        editor.remove("dateCtmchkdttimesign");//业务员确认客查日期
        editor.remove("CommodityIPQCPedt");//尾查预查
        editor.remove("CommodityIPQCmdt");//巡检中查
        editor.remove("CommodityQAname");//QA首扎
        editor.remove("CommodityQAScore");//QA首扎件数
        editor.remove("dateQAMemotimesign");//QA首扎日

        editor.remove("commonulltitle");//后道
        editor.remove("commonullitem");
        editor.remove("commonullsearledrev");
        editor.remove("commonulldocback");
        editor.remove("commonullmemo");
        editor.remove("commonullpreducdt");
        editor.remove("commonullpred");
        editor.remove("commonullpredoc");
        editor.remove("commonullfabricsok");
        editor.remove("commonullaccessori");
        editor.remove("commonullspcprodec");
        editor.remove("commonullspcpromemo");
        editor.remove("commonullcutqty");
        editor.remove("commonullsewfdt");
        editor.remove("commonullsewmdt");
        editor.remove("commonullprebdt");
        editor.remove("commonullqcbdt");
        editor.remove("commonullqcbdtdoc");
        editor.remove("commonullpremdt");
        editor.remove("commonullqcmdt");
        editor.remove("commonullqcmdtdoc");
        editor.remove("commonullpreedt");
        editor.remove("commonullqcmedt");
        editor.remove("commonullqcedtdoc");
        editor.remove("commonullfctmdt");
        editor.remove("commonullfctedt");
        editor.remove("commonullpackbdat");
        editor.remove("commonullpackqty2");
        editor.remove("commonullqcmemo");
        editor.remove("commonullfactlcdat");
        editor.remove("commonullBatchid");
        editor.remove("commonullCtmchkdt");
        editor.remove("commonullipqcpedt");
        editor.remove("commonullipqcmdt");
        editor.remove("commonullqaname");
        editor.remove("commonullqascore");
        editor.remove("commonullqamemo");
        editor.commit();
    }
    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (source.equals(" ")||source.equals("\n")||matcher.find())
                    return "";
                else
                    return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}