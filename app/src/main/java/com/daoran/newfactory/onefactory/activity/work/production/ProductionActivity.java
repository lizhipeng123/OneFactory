package com.daoran.newfactory.onefactory.activity.work.production;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
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
        implements View.OnClickListener, SelectItemInterface {

    private NoscrollListView mData;
    private ProcationDialog procationDialog;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack, ivSearch;
    private List<ProducationDetailBean.DataBean> detailBeenList =
            new ArrayList<ProducationDetailBean.DataBean>();
    private ProducationDetailBean detailBean;
    private ProductionAdapter adapter;
    List<ProducationSaveBean> saveBeen = new ArrayList<ProducationSaveBean>();
    List<ProducationConfigSaveBean> configSaveBeen = new ArrayList<ProducationConfigSaveBean>();

    private EditText etSqlDetail;
    private TextView tvSignPage;
    private Button btnSignPage, btnProSave, spinnermenu;
    private TextView spinnerNewbuild;
    private EditText etNewbuild;

    private SharedPreferences sp;
    private SPUtils spUtils;
    private int pageCount;
    private int pageIndex = 0;
    private int last_item = -1;
    private TextView oldView;
    private LinearLayout ll_visibi;
    private TextView tv_visibi;
    private ScrollView scroll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
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
        mHeaderHorizontal.setSrollView(mDataHorizontal);
        etSqlDetail.setSelection(etSqlDetail.getText().length());
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
            case R.id.ivProductionBack:
                finish();
                break;
            case R.id.ivSearch:
                ShowDialog(v);
                break;
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
            case R.id.spinnermenu:
                showPopupMenu(spinnermenu);
                break;
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
        String Style = sp.getString("etprodialogStyle", "");
        String Factory = sp.getString("etprodialogFactory", "");
        String Recode = sp.getString("etprodialogRecode", "");
        String Procedure = sp.getString("Procedure", "");
        String stis = sp.getString("ischeckedd", "");
        if (Procedure.equals("全部")) {
            boolean stris = Boolean.parseBoolean(stis);
            Gson gson = new Gson();
            Propostbean propostbean = new Propostbean();
            Propostbean.Conditions conditions = propostbean.new Conditions();
            conditions.setItem(Style);
            conditions.setPrddocumentary(Recode);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure("");
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(0);
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
            } else {
                ToastUtils.ShowToastMessage("当前网络不可用,请重新再试", ProductionActivity.this);
            }
        } else {
            boolean stris = Boolean.parseBoolean(stis);
            Gson gson = new Gson();
            Propostbean propostbean = new Propostbean();
            Propostbean.Conditions conditions = propostbean.new Conditions();
            conditions.setItem(Style);
            conditions.setPrddocumentary(Recode);
            conditions.setSubfactory(Factory);
            conditions.setWorkingProcedure(Procedure);
            conditions.setPrddocumentaryisnull(stris);
            propostbean.setConditions(conditions);
            propostbean.setPageNum(0);
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
            String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
            sp = this.getSharedPreferences("my_sp", 0);
            String proid = sp.getString("proadapterid", "");
            String salesid = sp.getString("prosalesid", "");
            String proColumnTitle = sp.getString("proColumnTitle", "");//部门
            if (proColumnTitle == "" || proColumnTitle.equals("")) {
                proColumnTitle = null;
            }
            String proProcedureTitle = sp.getString("proProcedureTitle", "");//工序
            if (proProcedureTitle == "" || proProcedureTitle.equals("")) {
                proProcedureTitle = null;
            }
            String proPrdstatusTitle = sp.getString("proadapterPrdstatusTitle", "");//状态//
            if (proPrdstatusTitle == "" || proPrdstatusTitle.equals("")) {
                proPrdstatusTitle = null;
            }
            String productionOthers = sp.getString("productionOthers", "");//组别人
            if (productionOthers == "" || productionOthers.equals("")) {
                productionOthers = null;
            }
            String productionTaskNumber = sp.getString("productionTaskNumber", "");//任务数
            if (productionTaskNumber == "" || productionTaskNumber.equals("")) {
                productionTaskNumber = null;
            }
            String productionCompletedLastMonth = sp.getString("productionCompletedLastMonth", "");//上月完工
            if (productionCompletedLastMonth == "" || productionCompletedLastMonth.equals("")) {
                productionCompletedLastMonth = null;
            }
            String productionMonth = sp.getString("proadapterMonthTitle", "");//月
            if (productionMonth == "" || productionMonth.equals("")) {
                productionMonth = null;
            }
            String productionOneDay = sp.getString("productionOneDay", "");//1
            if (productionOneDay == "" || productionOneDay.equals("")) {
                productionOneDay = null;
            }
            String productionTwoDay = sp.getString("productionTwoDay", "");//2
            if (productionTwoDay == "" || productionTwoDay.equals("")) {
                productionTwoDay = null;
            }
            String productionThreeDay = sp.getString("productionThreeDay", "");//3
            if (productionThreeDay == "" || productionThreeDay.equals("")) {
                productionThreeDay = null;
            }
            String productionForeDay = sp.getString("productionForeDay", "");//4
            if (productionForeDay == "" || productionForeDay.equals("")) {
                productionForeDay = null;
            }
            String productionFiveDay = sp.getString("productionFiveDay", "");//5
            if (productionFiveDay == "" || productionFiveDay.equals("")) {
                productionFiveDay = null;
            }
            String productionSixDay = sp.getString("productionSixDay", "");//6
            if (productionSixDay == "" || productionSixDay.equals("")) {
                productionSixDay = null;
            }
            String productionSevenDay = sp.getString("productionSevenDay", "");//7
            if (productionSevenDay == "" || productionSevenDay.equals("")) {
                productionSevenDay = null;
            }
            String productionEightDay = sp.getString("productionEightDay", "");//8
            if (productionEightDay == "" || productionEightDay.equals("")) {
                productionEightDay = null;
            }
            String productionNineDay = sp.getString("productionNineDay", "");//9
            if (productionNineDay == "" || productionNineDay.equals("")) {
                productionNineDay = null;
            }
            String productionTenDay = sp.getString("productionTenDay", "");//10
            if (productionTenDay == "" || productionTenDay.equals("")) {
                productionTenDay = null;
            }
            String productionElevenDay = sp.getString("productionElevenDay", "");//11
            if (productionElevenDay == "" || productionElevenDay.equals("")) {
                productionElevenDay = null;
            }
            String productionTwelveDay = sp.getString("productionTwelveDay", "");//12
            if (productionTwelveDay == "" || productionTwelveDay.equals("")) {
                productionTwelveDay = null;
            }
            String productionThirteenDay = sp.getString("productionThirteenDay", "");//13
            if (productionThirteenDay == "" || productionThirteenDay.equals("")) {
                productionThirteenDay = null;
            }
            String productionFourteenDay = sp.getString("productionFourteenDay", "");//14
            if (productionFourteenDay == "" || productionFourteenDay.equals("")) {
                productionFourteenDay = null;
            }
            String productionFifteenDay = sp.getString("productionFifteenDay", "");//15
            if (productionFifteenDay == "" || productionFifteenDay.equals("")) {
                productionFifteenDay = null;
            }
            String productionSixteenDay = sp.getString("productionSixteenDay", "");//16
            if (productionSixteenDay == "" || productionSixteenDay.equals("")) {
                productionSixteenDay = null;
            }
            String productionSeventeenDay = sp.getString("productionSeventeenDay", "");//17
            if (productionSeventeenDay == "" || productionSeventeenDay.equals("")) {
                productionSeventeenDay = null;
            }
            String productionEighteenDay = sp.getString("productionEighteenDay", "");//18
            if (productionEighteenDay == "" || productionEighteenDay.equals("")) {
                productionEighteenDay = null;
            }
            String productionNineteenDay = sp.getString("productionNineteenDay", "");//19
            if (productionNineteenDay == "" || productionNineteenDay.equals("")) {
                productionNineteenDay = null;
            }
            String productionTwentyDay = sp.getString("productionTwentyDay", "");//20
            if (productionTwentyDay == "" || productionTwentyDay.equals("")) {
                productionTwentyDay = null;
            }
            String productionTwentyOneDay = sp.getString("productionTwentyOneDay", "");//21
            if (productionTwentyOneDay == "" || productionTwentyOneDay.equals("")) {
                productionTwentyOneDay = null;
            }
            String productionTwentyTwoDay = sp.getString("productionTwentyTwoDay", "");//22
            if (productionTwentyTwoDay == "" || productionTwentyTwoDay.equals("")) {
                productionTwentyTwoDay = null;
            }
            String productionTwentyThreeDay = sp.getString("productionTwentyThreeDay", "");//23
            if (productionTwentyThreeDay == "" || productionTwentyThreeDay.equals("")) {
                productionTwentyThreeDay = null;
            }
            String productionTwentyForeDay = sp.getString("productionTwentyForeDay", "");//24
            if (productionTwentyForeDay == "" || productionTwentyForeDay.equals("")) {
                productionTwentyForeDay = null;
            }
            String productionTwentyFiveDay = sp.getString("productionTwentyFiveDay", "");//25
            if (productionTwentyFiveDay == "" || productionTwentyFiveDay.equals("")) {
                productionTwentyFiveDay = null;
            }
            String productionTwentySixDay = sp.getString("productionTwentySixDay", "");//26
            if (productionTwentySixDay == "" || productionTwentySixDay.equals("")) {
                productionTwentySixDay = null;
            }
            String productionTwentySevenDay = sp.getString("productionTwentySevenDay", "");//27
            if (productionTwentySevenDay == "" || productionTwentySevenDay.equals("")) {
                productionTwentySevenDay = null;
            }
            String productionTwentyEightDay = sp.getString("productionTwentyEightDay", "");//28
            if (productionTwentyEightDay == "" || productionTwentyEightDay.equals("")) {
                productionTwentyEightDay = null;
            }
            String productionTwentyNineDay = sp.getString("productionTwentyNineDay", "");//29
            if (productionTwentyNineDay == "" || productionTwentyNineDay.equals("")) {
                productionTwentyNineDay = null;
            }
            String productionThirtyDay = sp.getString("productionThirtyDay", "");//30
            if (productionThirtyDay == "" || productionThirtyDay.equals("")) {
                productionThirtyDay = null;
            }
            String productionThirtyOneDay = sp.getString("productionThirtyOneDay", "");//31
            if (productionThirtyOneDay == "" || productionThirtyOneDay.equals("")) {
                productionThirtyOneDay = null;
            }
            String productionRemarks = sp.getString("productionRemarks", "");//备注
            if (productionRemarks == "" || productionRemarks.equals("")) {
                productionRemarks = null;
            }
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
            String urlid = sp.getString("prouriid", "");
            ProducationSaveBean saveBean = new ProducationSaveBean();
            if (urlid == proid || urlid.equals(proid)) {
                if (!proid.equals("")) {
                    ResponseDialog.showLoading(this);
                    saveBean.setID(Integer.parseInt(proid));
                    saveBean.setSalesid(Integer.parseInt(salesid));
                    saveBean.setItem(null);
                    saveBean.setPrddocumentary(null);
                    saveBean.setSubfactory(null);
                    saveBean.setSubfactoryTeams(proColumnTitle);
                    saveBean.setWorkingProcedure(proProcedureTitle);
                    saveBean.setWorkers(productionOthers);
                    saveBean.setPqty(null);
                    saveBean.setProdcol(null);
                    saveBean.setTaskqty(productionTaskNumber);
                    saveBean.setMdl(null);
                    saveBean.setFactcutqty(null);
                    saveBean.setLastMonQty(productionCompletedLastMonth);
                    saveBean.setSumCompletedQty(null);
                    saveBean.setLeftQty(null);
                    saveBean.setPrdstatus(proPrdstatusTitle);
                    saveBean.setYear(null);
                    saveBean.setMonth(productionMonth);
                    saveBean.setDay1(productionOneDay);
                    saveBean.setDay2(productionTwoDay);
                    saveBean.setDay3(productionThreeDay);
                    saveBean.setDay4(productionForeDay);
                    saveBean.setDay5(productionFiveDay);
                    saveBean.setDay6(productionSixDay);
                    saveBean.setDay7(productionSevenDay);
                    saveBean.setDay8(productionEightDay);
                    saveBean.setDay9(productionNineDay);
                    saveBean.setDay10(productionTenDay);
                    saveBean.setDay11(productionElevenDay);
                    saveBean.setDay12(productionTwelveDay);
                    saveBean.setDay13(productionThirteenDay);
                    saveBean.setDay14(productionFourteenDay);
                    saveBean.setDay15(productionFifteenDay);
                    saveBean.setDay16(productionSixteenDay);
                    saveBean.setDay17(productionSeventeenDay);
                    saveBean.setDay18(productionEighteenDay);
                    saveBean.setDay19(productionNineteenDay);
                    saveBean.setDay20(productionTwentyDay);
                    saveBean.setDay21(productionTwentyOneDay);
                    saveBean.setDay22(productionTwentyTwoDay);
                    saveBean.setDay23(productionTwentyThreeDay);
                    saveBean.setDay24(productionTwentyForeDay);
                    saveBean.setDay25(productionTwentyFiveDay);
                    saveBean.setDay26(productionTwentySixDay);
                    saveBean.setDay27(productionTwentySevenDay);
                    saveBean.setDay28(productionTwentyEightDay);
                    saveBean.setDay29(productionTwentyNineDay);
                    saveBean.setDay30(productionThirtyDay);
                    saveBean.setDay31(productionThirtyOneDay);
                    saveBean.setMemo(productionRemarks);
                    saveBean.setRecorder(null);
                    saveBean.setRecordat(null);
                    saveBeen.add(saveBean);
                    String detailb = gson.toJson(saveBeen);
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
                    new AlertDialog.Builder(ProductionActivity.this).setTitle("提示信息")
                            .setMessage("请修改数据，再进行保存")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();//相应事件
                }

            } else {
                ToastUtils.ShowToastMessage("请选择当前行，再进行修改保存",
                        ProductionActivity.this);
            }
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
            sp = this.getSharedPreferences("my_sp", 0);
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
     * 弹出输入框
     *
     * @param view
     */
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
                    setPageDetail();
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
}
