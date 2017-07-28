package com.daoran.newfactory.onefactory.activity.work.production;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
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
import com.daoran.newfactory.onefactory.adapter.ProductionAdapter;
import com.daoran.newfactory.onefactory.adapter.ProductionLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProducationDeleteBean;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.bean.ProducationSaveBean;
import com.daoran.newfactory.onefactory.bean.ProductionDetailBooleanBean;
import com.daoran.newfactory.onefactory.bean.Propostbean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.Listener.SelectItemInterface;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.NullStringToEmptyAdapterFactory;
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
    private ProducationSaveBean producationSaveBean;//修改后的保存
    private List<ProductionDetailBooleanBean.DataBean> detailbooleanDatabean
            = new ArrayList<ProductionDetailBooleanBean.DataBean>();
    private ProductionDetailBooleanBean detailBooleanBean;

    private EditText etSqlDetail;//底部页码输入框
    private TextView tvSignPage;//页数显示
    private Button btnSignPage, btnProSave, spinnermenu;//翻页确定、保存确定，菜单menu
    private ImageView ivUpLeftPage, ivDownRightPage;

    private SharedPreferences sp;//存储
    private SPUtils spUtils;
    private int pageCount;//总页数int
    private int pageIndex = 0;//初始页数0
    private LinearLayout ll_visibi;//
    private TextView tv_visibi;
    private ScrollView scroll_content;
    private Spinner spinnProPageClumns;
    int keyHeight = 0;
    int screenHeight = 0;
    private int year, month, datetime, hour, minute, second;
    private boolean flagmonthsize;
    private String configid ;

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
        ivUpLeftPage = (ImageView) findViewById(R.id.ivUpLeftPage);
        ivDownRightPage = (ImageView) findViewById(R.id.ivDownRightPage);
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
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("info", "landscape"); // 横屏
            configid= String.valueOf(1);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("info", "portrait"); // 竖屏
            configid= String.valueOf(2);
        }
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
        ivUpLeftPage.setOnClickListener(this);
        ivDownRightPage.setOnClickListener(this);
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
                if (txt.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", ProductionActivity.this);
                } else {
                    int txtindex = Integer.parseInt(txt);
                    int txtcountindex = Integer.parseInt(txtcount);
                    if (txtindex > txtcountindex) {
                        ToastUtils.ShowToastMessage("已经是最后一页", ProductionActivity.this);
                    } else if (txtindex < 1) {
                        ToastUtils.ShowToastMessage("已经是第一页", ProductionActivity.this);
                    } else if (txt.length() == 0) {
                        ToastUtils.ShowToastMessage("页码不能为空", ProductionActivity.this);
                    } else if (etSqlDetail.getText().toString() == null) {
                        ToastUtils.ShowToastMessage("页码不能为空", ProductionActivity.this);
                    } else {
                        setPageDetail();
                    }
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
            /*上一页*/
            case R.id.ivUpLeftPage:
                String etsql = etSqlDetail.getText().toString();
                if (etsql.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", ProductionActivity.this);
                } else {
                    pageIndex = Integer.parseInt(etsql);
                    int index = pageIndex - 2;
                    if (index < 0) {
                        ToastUtils.ShowToastMessage("已经是第一页", ProductionActivity.this);
                    } else {
                        String indexstr = String.valueOf(index + 1);
                        etSqlDetail.setText(indexstr);
                        etSqlDetail.setSelection(indexstr.length());
                        setPageUpDate(index);
                    }
                }
                break;
            /*下一页*/
            case R.id.ivDownRightPage:
                String etsql2 = etSqlDetail.getText().toString();
                if (etsql2.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", ProductionActivity.this);
                } else {
                    pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
                    int index2 = pageIndex + 1;
                    String maxpageindex = tvSignPage.getText().toString();
                    int indexmax = Integer.parseInt(maxpageindex);
                    if (index2 > indexmax) {
                        ToastUtils.ShowToastMessage("已经是最后一页", ProductionActivity.this);
                    } else {
                        String index2str = String.valueOf(index2);
                        etSqlDetail.setText(index2str);
                        etSqlDetail.setSelection(index2str.length());
                        setPageUpDate(index2);
                    }
                }
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
                final ProgressDialog progressDialog = ProgressDialog.show(this,
                        "请稍候...", "正在查询中...", false, true);
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
                                    /*成功返回的结果*/
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

                                    ;
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
                final ProgressDialog progressDialog = ProgressDialog.show(this,
                        "请稍候...", "正在查询中...", false, true);
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
                final ProgressDialog progressDialog = ProgressDialog.show(this,
                        "请稍候...", "正在查询中...", false, true);
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
                final ProgressDialog progressDialog = ProgressDialog.show(this,
                        "请稍候...", "正在查询中...", false, true);
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
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionActivity.this);
            }
        }
    }

    /**
     * 上一页，下一页
     */
    private void setPageUpDate(int pageupIndex) {
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
        String Procedure = sp.getString("Procedure", "");
        String stis = sp.getString("ischeckedd", "");
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
            propostbean.setPageNum(pageupIndex);
            propostbean.setPageSize(Integer.parseInt(getsize));
            String gsonbeanStr = gson.toJson(propostbean);
            Log.e("you wanted", "[" + gsonbeanStr + "," + gsonbeanStr + "+]");
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this, "正在查询");
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
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多信息");
                                    }
                                    ResponseDialog.closeLoading();
                                    setNewlyComfig();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
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
                ResponseDialog.showLoading(this, "正在查询");
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
     */
    private void setSave() {
        sp = getSharedPreferences("my_sp", 0);
        if (NetWork.isNetWorkAvailable(this)) {
            final ProgressDialog progressDialog = ProgressDialog.show(this,
                    "请稍候...", "正在保存中...", false, true);
            String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
            int beanlength = detailBeenList.size();//修改后的数据大小
            String[] arrsflag = new String[beanlength];//修改的工序数组
            String[] arrsItem = new String[beanlength];//修改的工序对应的款号数组
            String[] arrsmonth = new String[beanlength];//修改的工序款号对应的月份数组
            String[] arrcolor = new String[beanlength];//修改的工序款号对应的花色数组
            //循环遍历修改后的数据大小输出修改的工序和款号数组
            for (int i = 0; i < beanlength; i++) {
                if (beanlength != 0) {
                    arrsflag[i] = detailBeenList.get(i).getMemoprdure();
                    String memoprdure = detailBeenList.get(i).getMemoprdure();
                    String memomonth = detailBeenList.get(i).getMemomonth();
                    if (memoprdure != null) {//修改的工序不为空
                        arrsItem[i] = detailBeenList.get(i).getItem();
                        arrsflag[i] = detailBeenList.get(i).getWorkingProcedure();
                        arrsmonth[i] = detailBeenList.get(i).getMonth();
                        arrcolor[i] = detailBeenList.get(i).getProdcol();
                    } else {
                        if (memomonth != null) {
                            arrsItem[i] = detailBeenList.get(i).getItem();
                            arrsflag[i] = detailBeenList.get(i).getWorkingProcedure();
                            arrsmonth[i] = detailBeenList.get(i).getMonth();
                            arrcolor[i] = detailBeenList.get(i).getProdcol();
                        } else {
                            arrsItem[i] = "";
                            arrsflag[i] = "";
                            arrsmonth[i] = "";
                            arrcolor[i] = "";
                        }
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

            /*去掉符合款号的月份空值*/
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

            /*去掉符合款号的颜色的空值*/
            StringBuffer sbcolor = new StringBuffer();
            for (int i = 0; i < arrcolor.length; i++) {
                if ("".equals(arrcolor[i])) {
                    continue;
                }
                sbcolor.append(arrcolor[i]);
                if (i != arrcolor.length - 1) {
                    sbcolor.append(";");
                }
            }
            arrcolor = sbcolor.toString().split(";");
            for (int i = 0; i < arrcolor.length; i++) {
                System.out.print(arrcolor[i] + "");
            }
            System.out.print(arrcolor + "");

            int booleanlistlength = detailbooleanDatabean.size();//初始化查询的数据
            String[] arrsdata = new String[booleanlistlength];//初始化的工序数组
            String[] arrsdatamonth = new String[booleanlistlength];//初始化的月份数组
            String[] arrsdateitem = new String[booleanlistlength];//初始化款号
            String[] arrsdatecolor = new String[booleanlistlength];//初始化花色
            //循环遍历初始化集合大小，输出符合修改后款号的工序
            for (int i = 0; i < booleanlistlength; i++) {
                if (booleanlistlength != 0) {
                    String workitem = detailbooleanDatabean.get(i).getItem();//初始的款号
                    String[] workitempro = workitem.split(",");//将字符串转化成数组
                    boolean containsall = containsAll(arrsItem, workitempro);//判断当前循环第一条的款号是否在初始化款号中存在
                    if (containsall == true) {
                        arrsdata[i] = detailbooleanDatabean.get(i).getWorkingProcedure();
                        arrsdatamonth[i] = detailbooleanDatabean.get(i).getMonth();
                        arrsdateitem[i] = detailbooleanDatabean.get(i).getItem();
                        arrsdatecolor[i] = detailbooleanDatabean.get(i).getProdcol();
                    } else {
                        arrsdata[i] = "";
                        arrsdatamonth[i] = "";
                        arrsdateitem[i] = "";
                        arrsdatecolor[i] = "";
                    }
                } else {
                    arrsdata[i] = "";
                    arrsdatamonth[i] = "";
                    arrsdateitem[i] = "";
                    arrsdatecolor[i] = "";
                }
            }
            /*去掉月份中空值*/
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
            /*加入花色情况的判断*/
            String[] arrsprodurecolorlist = new String[booleanlistlength];//花色
            String[] arrsprodurelist = new String[booleanlistlength];//工序
            String[] arrsproduremonthlist = new String[booleanlistlength];//月份
            for (int i = 0; i < booleanlistlength; i++) {
                if (booleanlistlength != 0) {
                    String produrecolor = detailbooleanDatabean.get(i).getProdcol();
                    String[] produrecolorpro = produrecolor.split(",");//将字符串转化成数组
                    boolean colorpro = containsAll(arrcolor, produrecolorpro);
                    if (colorpro == true) {
                        arrsprodurecolorlist[i] = detailbooleanDatabean.get(i).getProdcol();
                        arrsprodurelist[i] = detailbooleanDatabean.get(i).getWorkingProcedure();
                        arrsproduremonthlist[i] = detailbooleanDatabean.get(i).getMonth();
                    } else {
                        arrsprodurecolorlist[i] = "";
                        arrsprodurelist[i] = "";
                        arrsproduremonthlist[i] = "";
                    }
                } else {
                    arrsprodurecolorlist[i] = "";
                    arrsprodurelist[i] = "";
                    arrsproduremonthlist[i] = "";
                }
            }
            /*去掉颜色中空值*/
            StringBuffer sbcolorpro = new StringBuffer();
            for (int i = 0; i < arrsprodurecolorlist.length; i++) {
                if ("".equals(arrsprodurecolorlist[i])) {
                    continue;
                }
                sbcolorpro.append(arrsprodurecolorlist[i]);
                if (i != arrsprodurecolorlist.length - 1) {
                    sbcolorpro.append(";");
                }
            }
            arrsprodurecolorlist = sbcolorpro.toString().split(";");
            for (int i = 0; i < arrsprodurecolorlist.length; i++) {
                System.out.print(arrsprodurecolorlist[i] + "");
            }

            /*去掉月份中空值*/
            StringBuffer sbproduremonth = new StringBuffer();
            for (int i = 0; i < arrsproduremonthlist.length; i++) {
                if ("".equals(arrsproduremonthlist[i])) {
                    continue;
                }
                sbproduremonth.append(arrsproduremonthlist[i]);
                if (i != arrsproduremonthlist.length - 1) {
                    sbproduremonth.append(";");
                }
            }
            arrsproduremonthlist = sbproduremonth.toString().split(";");
            for (int i = 0; i < arrsproduremonthlist.length; i++) {
                System.out.print(arrsproduremonthlist[i] + "");
            }

            /*循环判断相同花色情况下，月份是否相同*/
            for (int i = 0; i < arrsproduremonthlist.length; i++) {
                for (int j = i + 1; j < arrsproduremonthlist.length; j++) {
                    System.out.print(arrsproduremonthlist[i] + "==" + arrsproduremonthlist[j] + "--");
                    if (arrsproduremonthlist[i].equals(arrsproduremonthlist[j])) {
                        System.out.print(true);
                        flagmonthsize = true;
                        break;
                    } else {
                        System.out.print(false);
                        flagmonthsize = false;
                    }
                }
            }
            System.out.print(arrsdata + "");//输出的是循环出来的所有符合条件的初始化工序
            System.out.print(arrsflag + "");
            System.out.print(arrsdatamonth + "");
            System.out.print(arrsdateitem + "");
            System.out.print(arrsdatecolor + "");
            System.out.print(arrsprodurecolorlist + "");//相同的花色
            System.out.print(arrsprodurelist + "");//相同花色的工序
            System.out.print(arrsproduremonthlist + "");//相同花色的月份
            boolean flagmonth = containsAll(arrsdatamonth, arrsmonth);
            boolean flagdata = containsAll(arrsdata, arrsflag);
            boolean flagitem = containsAll(arrsdateitem, arrsItem);
            boolean flagcolor = containsAll(arrsdatecolor, arrcolor);//符合条件的花色
            boolean flagprodurecolro = containsAll(arrsprodurecolorlist, arrcolor);//相同花色
            boolean flagprodurelist = containsAll(arrsprodurelist, arrsflag);//
            boolean flagproduremonth = containsAll(arrsproduremonthlist, arrsmonth);
            int colorlistlength = arrsprodurecolorlist.length;
            /*将得到的实体类转变为json数据*/
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new
                    NullStringToEmptyAdapterFactory()).create();
            String detailb = gson.toJson(detailBeenList);
            System.out.print(detailb);

            String prosaveothers = sp.getString("prosaveothers", "");//组别人数
            String prosavetasknunber = sp.getString("prosavetasknunber", "");//任务数
            String prosavecompletedlastmonth = sp.getString("prosavecompletedlastmonth", "");//上月完工
            String prosaveoneday = sp.getString("prosaveoneday", "");//1日
            String prosavetwoday = sp.getString("prosavetwoday", "");//2日
            String prothreeday = sp.getString("prothreeday", "");//3日
            String prosaveforeday = sp.getString("prosaveforeday", "");//4日
            String prosavefiveday = sp.getString("prosavefiveday", "");//5日
            String prosavesixday = sp.getString("prosavesixday", "");//6日
            String prosavesevenday = sp.getString("prosavesevenday", "");//7日
            String prosaveeightday = sp.getString("prosaveeightday", "");//8日
            String prosavenineday = sp.getString("prosavenineday", "");//9日
            String prosavetenday = sp.getString("prosavetenday", "");//10日
            String prosaveelevenday = sp.getString("prosaveelevenday", "");//11日
            String prosavetwelveday = sp.getString("prosavetwelveday", "");//12日
            String prosavethirteenday = sp.getString("prosavethirteenday", "");//13日
            String prosavefourteenday = sp.getString("prosavefourteenday", "");//14日
            String prosavefifteenday = sp.getString("prosavefifteenday", "");//15日
            String prosavesixteenday = sp.getString("prosavesixteenday", "");//16日
            String prosaveserventeenday = sp.getString("prosaveserventeenday", "");//17日
            String prosaveeighteenday = sp.getString("prosaveeighteenday", "");//18日
            String prosavenineteenday = sp.getString("prosavenineteenday", "");//19日
            String prosavetwentyday = sp.getString("prosavetwentyday", "");//20日
            String prosavetwentyoneday = sp.getString("prosavetwentyoneday", "");//21日
            String prosavetwentytwoday = sp.getString("prosavetwentytwoday", "");//22日
            String prosavetwentythreeday = sp.getString("prosavetwentythreeday", "");//23日
            String prosavetwentyforeday = sp.getString("prosavetwentyforeday", "");//24日
            String prosavetwentyfiveday = sp.getString("prosavetwentyfiveday", "");//25日
            String prosavetwentysixday = sp.getString("prosavetwentysixday", "");//26日
            String prosavetwentysevenday = sp.getString("prosavetwentysevenday", "");//27日
            String prosavetwentyeightday = sp.getString("prosavetwentyeightday", "");//28日
            String prosavetwentynineday = sp.getString("prosavetwentynineday", "");//29日
            String prosavethirtyday = sp.getString("prosavethirtyday", "");//30日
            String prosavethirtyoneday = sp.getString("prosavethirtyoneday", "");//31日
            String prosaveremarks = sp.getString("prosaveremarks", "");//备注
            String prosavemonth = sp.getString("prosavemonth", "");//月份
            String prosavedepartment = sp.getString("prosavedepartment", "");//部门组别
            String probooleanProcedureTitle = sp.getString("probooleanProcedureTitle", "");//工序
            String prosavestate = sp.getString("prosavestate", "");//状态
            producationSaveBean = new ProducationSaveBean();
            /*判断全部可填的数据是否都为空，空则显示未修改数据*/
            if (prosaveothers.equals("") && prosavetasknunber.equals("")
                    && prosavecompletedlastmonth.equals("") && prosaveoneday.equals("")
                    && prosavetwoday.equals("") && prothreeday.equals("")
                    && prosaveforeday.equals("") && prosavefiveday.equals("")
                    && prosavesixday.equals("") && prosavesevenday.equals("")
                    && prosaveeightday.equals("") && prosavenineday.equals("")
                    && prosavetenday.equals("") && prosaveelevenday.equals("")
                    && prosavetwelveday.equals("") && prosavethirteenday.equals("")
                    && prosavefourteenday.equals("") && prosavefifteenday.equals("")
                    && prosavesixteenday.equals("") && prosaveserventeenday.equals("")
                    && prosaveeighteenday.equals("") && prosavenineteenday.equals("")
                    && prosavetwentyday.equals("") && prosavetwentyoneday.equals("")
                    && prosavetwentytwoday.equals("") && prosavetwentythreeday.equals("")
                    && prosavetwentyforeday.equals("") && prosavetwentyfiveday.equals("")
                    && prosavetwentysixday.equals("") && prosavetwentysevenday.equals("")
                    && prosavetwentyeightday.equals("") && prosavetwentynineday.equals("")
                    && prosavethirtyday.equals("") && prosavethirtyoneday.equals("")
                    && prosaveremarks.equals("") && prosavemonth.equals("")
                    && prosavedepartment.equals("") && probooleanProcedureTitle.equals("")
                    && prosavestate.equals("")) {
                ToastUtils.ShowToastMessage("未修改表中数据", ProductionActivity.this);
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
                /*如果有多个相同颜色的款号，则判断其符合条件的工序和月份是否存在修改的工序*/
                if (colorlistlength > 1) {
                    if (flagprodurelist == true && flagproduremonth == true) {
                        if (flagmonthsize == true) {
                            ToastUtils.ShowToastMessage("花色有多分，其中有存在相同的工序或者月份，请检查后再保存", ProductionActivity.this);
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
                            OkHttpUtils.postString().
                                    url(saveurl)
                                    .content(detailb)
                                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            e.printStackTrace();
                                            progressDialog.dismiss();
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
                                            String ression = StringUtil.sideTrim(response, "\"");
                                            System.out.print(ression);
                                            int resindex = Integer.parseInt(ression);
                                            if (resindex > 3) {
                                                ToastUtils.ShowToastMessage("保存成功", ProductionActivity.this);
                                                setData();
                                            } else if (ression == "3" || ression.equals("3")) {
                                                ToastUtils.ShowToastMessage("保存失败", ProductionActivity.this);
                                            } else if (ression == "4" || ression.equals("4")) {
                                                ToastUtils.ShowToastMessage("数据错误，请重试", ProductionActivity.this);
                                            } else {
                                                ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionActivity.this);
                                            }
                                        }
                                    });
                        }
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
                                        progressDialog.dismiss();
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
                                        String ression = StringUtil.sideTrim(response, "\"");
                                        System.out.print(ression);
                                        int resindex = Integer.parseInt(ression);
                                        if (resindex > 3) {
                                            ToastUtils.ShowToastMessage("保存成功", ProductionActivity.this);
                                            setData();
                                        } else if (ression == "3" || ression.equals("3")) {
                                            ToastUtils.ShowToastMessage("保存失败", ProductionActivity.this);
                                        } else if (ression == "4" || ression.equals("4")) {
                                            ToastUtils.ShowToastMessage("数据错误，请重试", ProductionActivity.this);
                                        } else {
                                            ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionActivity.this);
                                        }
                                    }
                                });
                    }
                } else {
                    if (arrsflag != null) {
                        //判断修改后的工序是否存在于修改前的工序之中，如果存在则不能保存
                        if (flagdata == true) {
                            System.out.print("true");
                            //如果修改后的工序不存在与修改前的工序之中，则可以保存
                            System.out.print("false");
                            if (flagdata == true) {
                                String arrsitemi = "";
                                for (int i = 0; i < arrsItem.length; i++) {
                                    System.out.print(arrsItem[i]);
                                    arrsitemi = arrsItem[i];
                                }
                                if (arrsitemi.equals("") && prosaveothers.equals("") && prosavetasknunber.equals("")
                                        && prosavecompletedlastmonth.equals("") && prosaveoneday.equals("")
                                        && prosavetwoday.equals("") && prothreeday.equals("")
                                        && prosaveforeday.equals("") && prosavefiveday.equals("")
                                        && prosavesixday.equals("") && prosavesevenday.equals("")
                                        && prosaveeightday.equals("") && prosavenineday.equals("")
                                        && prosavetenday.equals("") && prosaveelevenday.equals("")
                                        && prosavetwelveday.equals("") && prosavethirteenday.equals("")
                                        && prosavefourteenday.equals("") && prosavefifteenday.equals("")
                                        && prosavesixteenday.equals("") && prosaveserventeenday.equals("")
                                        && prosaveeighteenday.equals("") && prosavenineteenday.equals("")
                                        && prosavetwentyday.equals("") && prosavetwentyoneday.equals("")
                                        && prosavetwentytwoday.equals("") && prosavetwentythreeday.equals("")
                                        && prosavetwentyforeday.equals("") && prosavetwentyfiveday.equals("")
                                        && prosavetwentysixday.equals("") && prosavetwentysevenday.equals("")
                                        && prosavetwentyeightday.equals("") && prosavetwentynineday.equals("")
                                        && prosavethirtyday.equals("") && prosavethirtyoneday.equals("")
                                        && prosaveremarks.equals("") && prosavemonth.equals("")
                                        && prosavedepartment.equals("") && probooleanProcedureTitle.equals("")
                                        && prosavestate.equals("")) {
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
                                    ToastUtils.ShowToastMessage("未修改表中数据",
                                            ProductionActivity.this);
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
                                                    progressDialog.dismiss();
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
                                                    String ression = StringUtil.sideTrim(response, "\"");
                                                    System.out.print(ression);
                                                    int resindex = Integer.parseInt(ression);
                                                    if (resindex > 3) {
                                                        ToastUtils.ShowToastMessage("保存成功", ProductionActivity.this);
                                                        setData();
                                                    } else if (ression == "3" || ression.equals("3")) {
                                                        ToastUtils.ShowToastMessage("保存失败", ProductionActivity.this);
                                                    } else if (ression == "4" || ression.equals("4")) {
                                                        ToastUtils.ShowToastMessage("数据错误，请重试", ProductionActivity.this);
                                                    } else {
                                                        ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionActivity.this);
                                                    }
                                                }
                                            });
                                }
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
                                                progressDialog.dismiss();
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
                                                String ression = StringUtil.sideTrim(response, "\"");
                                                System.out.print(ression);
                                                int resindex = Integer.parseInt(ression);
                                                if (resindex > 3) {
                                                    ToastUtils.ShowToastMessage("保存成功", ProductionActivity.this);
                                                    setData();
                                                } else if (ression == "3" || ression.equals("3")) {
                                                    ToastUtils.ShowToastMessage("保存失败", ProductionActivity.this);
                                                    ;
                                                } else if (ression == "4" || ression.equals("4")) {
                                                    ToastUtils.ShowToastMessage("数据错误，请重试", ProductionActivity.this);
                                                } else {
                                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionActivity.this);
                                                }
                                            }
                                        });
                            }
                        } else {
                            //如果修改后的工序不存在与修改前的工序之中，则可以保存
                            System.out.print("false");
                            OkHttpUtils.postString().
                                    url(saveurl)
                                    .content(detailb)
                                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            e.printStackTrace();
                                            progressDialog.dismiss();
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
                                            String ression = StringUtil.sideTrim(response, "\"");
                                            System.out.print(ression);
                                            int resindex = Integer.parseInt(ression);
                                            if (resindex > 3) {
                                                ToastUtils.ShowToastMessage("保存成功", ProductionActivity.this);
                                                setData();
                                            } else if (ression == "3" || ression.equals("3")) {
                                                ToastUtils.ShowToastMessage("保存失败", ProductionActivity.this);
                                            } else if (ression == "4" || ression.equals("4")) {
                                                ToastUtils.ShowToastMessage("数据错误，请重试", ProductionActivity.this);
                                            } else {
                                                ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionActivity.this);
                                            }
                                        }
                                    });
                        }
                    } else {
                        if (flagitem == true) {
                            if (flagmonth == true) {
                                ToastUtils.ShowToastMessage("相同款号、用户下，月份不能相同", ProductionActivity.this);
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
                                //如果修改后的工序不存在与修改前的工序之中，则可以保存
                                System.out.print("false");
                                OkHttpUtils.postString().
                                        url(saveurl)
                                        .content(detailb)
                                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                e.printStackTrace();
                                                progressDialog.dismiss();
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
                                                String ression = StringUtil.sideTrim(response, "\"");
                                                System.out.print(ression);
                                                int resindex = Integer.parseInt(ression);
                                                if (resindex > 3) {
                                                    ToastUtils.ShowToastMessage("保存成功", ProductionActivity.this);
                                                    setData();
                                                } else if (ression == "3" || ression.equals("3")) {
                                                    ToastUtils.ShowToastMessage("保存失败", ProductionActivity.this);
                                                } else if (ression == "4" || ression.equals("4")) {
                                                    ToastUtils.ShowToastMessage("数据错误，请重试", ProductionActivity.this);
                                                } else {
                                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionActivity.this);
                                                }
                                            }
                                        });
                            }
                        } else {
                        }
                    }
                }
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
                                /*成功返回的结果*/
                                System.out.print(response);
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                detailBooleanBean = new Gson().fromJson(ression, ProductionDetailBooleanBean.class);
                                detailbooleanDatabean = detailBooleanBean.getData();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
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
                    String etsql2 = etSqlDetail.getText().toString();
                    if (etsql2.equals("")) {
                        ToastUtils.ShowToastMessage("页码不能为空", ProductionActivity.this);
                    } else {
                        setPageDetail();
                    }
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
                    case "横竖屏切换":
                        if (configid.equals("1")) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        } else if(configid.equals("2")) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        }else{

                        }
                        break;
                    case "刷新":
                        setData();
                        break;
                    case "保存为Excel":
                        final ProgressDialog progressDialog = ProgressDialog.show(ProductionActivity.this,
                                "请稍候...", "正在生成Excel中...", false, true);
                        final Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    if (detailBeenList.size() != 0) {
                                        Looper.prepare();
                                        ToastUtils.ShowToastMessage("写入成功",
                                                ProductionActivity.this);
                                        ProductionExcelUtil.writeExcel(ProductionActivity.this,
                                                detailBeenList,
                                                "dfProExcel+" + new Date().toString());
                                        progressDialog.dismiss();
                                        Looper.loop();

                                    } else {
                                        Looper.prepare();
                                        ToastUtils.ShowToastMessage("没有数据",
                                                ProductionActivity.this);
                                        progressDialog.dismiss();
                                        Looper.loop();
                                    }
                                } catch (Exception e) {
                                    Looper.prepare();
                                    ToastUtils.ShowToastMessage("写入失败",
                                            ProductionActivity.this);
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
        String message = newConfig.orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? "屏幕设置为：横屏" : "屏幕设置为：竖屏";
        ToastUtils.ShowToastMessage(message, this);
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
