package com.daoran.newfactory.onefactory.activity.work.commo;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.view.dialog.CommoDialog;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
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
    private CommoditySqlLeftAdapter leftAdapter;//左侧编号列表适配
    private CommoditySqlAdapter sqlAdapter;//列表适配
    private ClumnsResultBean resultBean;//列权限实体

    private TextView tvSignPage;//显示的总页数
    private EditText etSqlDetail;//输入的页数
    private Button btnSignPage;//翻页确认
    private Button spinnermenu;//最右侧菜单
    private LinearLayout ll_visibi;//空数据显示的页面
    private TextView tv_visibi;//空数据显示的页面信息
    private ScrollView scroll_content;//查货跟踪可上下滑动的视图
    private Spinner spinnCommoPageClumns;//选择每页显示的条目数
    private ImageView ivUpLeftPage, ivDownRightPage;//上一页，下一页控件

    private SharedPreferences sp;//轻量级存储本地数据
    private SPUtils spUtils;
    private int pageCount;//查询获取的总页数
    private int pageIndex = 0;//初始显示的页数
    private String configid;
    public static CommoditySqlActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);//加载主页面
        instance = this;
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
        //将资源转化成string数组
        String[] spinner = getResources().getStringArray(R.array.clumnsCommon);
        //填充数据
        ArrayAdapter<String> adapterclumns = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner);
        adapterclumns.setDropDownViewResource(R.layout.dropdown_stytle);
        spinnCommoPageClumns.setAdapter(adapterclumns);
        //列表点击事件选择每页显示的条目数
        spinnCommoPageClumns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().
                        getStringArray(R.array.clumnsCommon);
                spUtils.put(CommoditySqlActivity.this,
                        "clumnsspinner", languages[position]);//将选择的条目数保存到存储中
                setData();//查询
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
        etSqlDetail.setSelection(etSqlDetail.getText().length());
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
            /*弹出菜单*/
            case R.id.spinnermenu:
                if (configid.equals("1")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (configid.equals("2")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {

                }
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
                        String indexstr = String.valueOf(index);
                        int indexcount = index + 1;
                        etSqlDetail.setText(String.valueOf(indexcount));
                        etSqlDetail.setSelection(String.valueOf(indexcount).length());
                        setPageDate(indexstr);
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
                    int index2 = pageIndexx;
                    String maxpageindex = tvSignPage.getText().toString();
                    int indexmax = Integer.parseInt(maxpageindex);
                    int index3 = index2+1;
                    if (index3 > indexmax) {
                        ToastUtils.ShowToastMessage("已经是最后一页", CommoditySqlActivity.this);
                    } else {
                        String index2str = String.valueOf(index2);
                        int indexcount = index2 + 1;
                        etSqlDetail.setText(String.valueOf(indexcount));
                        etSqlDetail.setSelection(String.valueOf(indexcount).length());
                        setPageDate(index2str);
                    }
                }
                break;
        }
    }

//    /**
//     * 判断软键盘是否弹出
//     *
//     * @param v
//     */
//    private void sethideSoft(View v) {
//        //判断软件盘是否弹出
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm != null) {
//            if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
//                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
//                        0);
//            } else {
//                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
//                        0);
//            }
//        }
//    }

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
        String pagesize = sp.getString("clumnsspinner", "");//每页显示的条目数
        //默认显示为10条
        if (pagesize.equals("")) {
            pagesize = String.valueOf(10);
        }
        String Recode = sp.getString("commoRecode", "");//巡检
        String etprodialogProcedure = sp.getString("etprodialogProcedure", "");//生产主管
        String ischeck = sp.getString("ischeckedd", "");//是否可为空
        boolean stris = Boolean.parseBoolean(ischeck);//转换成boolean类型
        Gson gson = new Gson();
        //将获取到的数据放入bean中
        CommodityPostBean postBean = new CommodityPostBean();
        CommodityPostBean.Conditions conditions = postBean.new Conditions();
        conditions.setItem(Style);//填充款号
        conditions.setPrddocumentary(recodename);//填充跟单
        conditions.setPrdmaster(etprodialogProcedure);//填充生产主管
        conditions.setIPQC(Recode);//填充巡检
        conditions.setPrdmasterisnull(stris);//填充生产主管是否可空
        postBean.setConditions(conditions);//填充父节点
        postBean.setPageNum(0);
        postBean.setPageSize(Integer.parseInt(pagesize));
        //将bean中的数据转成json数据
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
                                        Thread.sleep(1000);
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
                                            Thread.sleep(1000);
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
                                //判断得到的数据是否为空,决定要显示的页面
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
                                            Thread.sleep(1000);
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
        String pagesize = sp.getString("clumnsspinner", "");//每页显示的条目数
        String Recode = sp.getString("commoRecode", "");//巡检
        if (Recode.contains("\n")) {
            Recode = "";
        }
        String etprodialogProcedure = sp.getString("etproProcedure", "");//生产主管
        if (etprodialogProcedure.contains("\n")) {
            etprodialogProcedure = "";
        }
        String ischeck = sp.getString("ischeckedd", "");//生产主管是否可为空
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
                                        Thread.sleep(1000);
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
                                            Thread.sleep(1000);
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
                                            Thread.sleep(1000);
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
    private void setPageDate(String pageIndexin) {
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
        postBean.setPageNum(Integer.parseInt(pageIndexin));
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
        super.onDestroy();
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
                if (source.equals(" ") || source.equals("\n") || matcher.find())
                    return "";
                else
                    return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return false;
    }
}