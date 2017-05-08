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
import com.daoran.newfactory.onefactory.adapter.ProductionAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.bean.Propostbean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
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
 * 生产日报页面
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
    private Button btnSignPage, btnProSave, spinnermenu;

    private SharedPreferences sp;
    private SPUtils spUtils;
    private int pageCount;
    private int pageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
        getViews();
        initView();
        setListener();
        setData();
//        setItemClick();
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
        sp = ProductionActivity.this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
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
                                    System.out.print(detailBeenList);
                                    pageCount = detailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / 10);
                                    tvSignPage.setText(count);
                                    adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                    mData.setAdapter(adapter);
                                    ResponseDialog.closeLoading();
                                    adapter.notifyDataSetChanged();
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
                                    System.out.print(detailBeenList);
                                    pageCount = detailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / 10);
                                    tvSignPage.setText(count);
                                    adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                    mData.setAdapter(adapter);
                                    ResponseDialog.closeLoading();
                                    adapter.notifyDataSetChanged();
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
        sp = ProductionActivity.this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
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
                                    adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                    mData.setAdapter(adapter);
                                    ResponseDialog.closeLoading();
                                    adapter.notifyDataSetChanged();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
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
                                    System.out.print(detailBeenList);
                                    pageCount = detailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / 10);
                                    tvSignPage.setText(count);
                                    adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                    mData.setAdapter(adapter);
                                    ResponseDialog.closeLoading();
                                    adapter.notifyDataSetChanged();
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
        String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
        sp = this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String proid = sp.getString("proid", "");
        String salesid = sp.getString("salesid", "");
        String proColumnTitle = sp.getString("proColumnTitle", "");//部门
        if (proColumnTitle == "" || proColumnTitle.equals("")) {
            proColumnTitle = null;
            System.out.print(proColumnTitle);
        }
        String proProcedureTitle = sp.getString("proProcedureTitle", "");//工序
        if (proProcedureTitle == "" || proProcedureTitle.equals("")) {
            proProcedureTitle = null;
        }
        String proPrdstatusTitle = sp.getString("proadapterPrdstatusTitle", "");//状态//
        if (proPrdstatusTitle == "" || proPrdstatusTitle.equals("")) {
            proPrdstatusTitle = null;
        }
        String productionItem = sp.getString("productionItem", "");//款号
        if (productionItem == "" || productionItem.equals("")) {
            productionItem = null;
        }
        String productionDocumentary = sp.getString("productionadapterDocumentary", "");//跟单//
        if (productionDocumentary == "" || productionDocumentary.equals("")) {
            productionDocumentary = null;
        }
        String productionFactory = sp.getString("productionFactory", "");//工厂
        if (productionFactory == "" || productionFactory.equals("")) {
            productionFactory = null;
        }
        String productionOthers = sp.getString("productionOthers", "");//组别人
        if (productionOthers == "" || productionOthers.equals("")) {
            productionOthers = null;
        }
        String productionSingularSystem = sp.getString("productionadapterSingularSystem", "");//制单数//
        if (productionSingularSystem == "" || productionSingularSystem.equals("")) {
            productionSingularSystem = null;
        }
        String productionColor = sp.getString("productionColor", "");//花色
        if (productionColor == "" || productionColor.equals("")) {
            productionColor = null;
        }
        String productionTaskNumber = sp.getString("productionTaskNumber", "");//任务数
        if (productionTaskNumber == "" || productionTaskNumber.equals("")) {
            productionTaskNumber = null;
        }
        String productionSize = sp.getString("productionSize", "");//尺码
        if (productionSize == "" || productionSize.equals("")) {
            productionSize = null;
        }
        String productionClippingNumber = sp.getString("productionClippingNumber", "");//实裁数
        if (productionClippingNumber == "" || productionClippingNumber.equals("")) {
            productionClippingNumber = null;
        }
        String productionCompletedLastMonth = sp.getString("productionCompletedLastMonth", "");//上月完工
        if (productionCompletedLastMonth == "" || productionCompletedLastMonth.equals("")) {
            productionCompletedLastMonth = null;
        }
        String productionTotalCompletion = sp.getString("productionTotalCompletion", "");//总完工数
        if (productionTotalCompletion == "" || productionTotalCompletion.equals("")) {
            productionTotalCompletion = null;
        }
        String productionBalanceAmount = sp.getString("productionBalanceAmount", "");//结余数量
        if (productionBalanceAmount == "" || productionBalanceAmount.equals("")) {
            productionBalanceAmount = null;
        }
        String productionYear = sp.getString("productionYear", "");//年
        if (productionYear == "" || productionYear.equals("")) {
            productionYear = null;
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
        String productionRecorder = sp.getString("productionRecorder", "");//制单人
        if (productionRecorder == "" || productionRecorder.equals("")) {
            productionRecorder = null;
        }
        String productionRecordat = sp.getString("productionRecordat", "");//制单时间
        if (productionRecordat == "" || productionRecordat.equals("")) {
            productionRecordat = null;
        }
        Gson gson = new Gson();
        ProducationDetailBean.DataBean dataBean = new ProducationDetailBean.DataBean();
        dataBean.setID(Integer.parseInt(proid));
        dataBean.setSalesid(Integer.parseInt(salesid));
        dataBean.setItem(productionItem);
        dataBean.setPrddocumentary(productionDocumentary);
        dataBean.setSubfactory(productionFactory);
        dataBean.setSubfactoryTeams(proColumnTitle);
        dataBean.setWorkingProcedure(proProcedureTitle);
        dataBean.setWorkers(productionOthers);
        dataBean.setPqty(productionSingularSystem);
        dataBean.setProdcol(productionColor);
        dataBean.setTaskqty(productionTaskNumber);
        dataBean.setMdl(productionSize);
        dataBean.setFactcutqty(productionClippingNumber);
        dataBean.setLastMonQty(productionCompletedLastMonth);
        dataBean.setSumCompletedQty(productionTotalCompletion);
        dataBean.String(productionBalanceAmount);
        dataBean.setPrdstatus(proPrdstatusTitle);
        dataBean.setYear(productionYear);
        dataBean.setMonth(productionMonth);
        dataBean.setDay1(productionOneDay);
        dataBean.setDay2(productionTwoDay);
        dataBean.setDay3(productionThreeDay);
        dataBean.setDay4(productionForeDay);
        dataBean.setDay5(productionFiveDay);
        dataBean.setDay6(productionSixDay);
        dataBean.setDay7(productionSevenDay);
        dataBean.setDay8(productionEightDay);
        dataBean.setDay9(productionNineDay);
        dataBean.setDay10(productionTenDay);
        dataBean.setDay11(productionElevenDay);
        dataBean.setDay12(productionTwelveDay);
        dataBean.setDay13(productionThirteenDay);
        dataBean.setDay14(productionFourteenDay);
        dataBean.setDay15(productionFifteenDay);
        dataBean.setDay16(productionSixteenDay);
        dataBean.setDay17(productionSeventeenDay);
        dataBean.setDay18(productionEighteenDay);
        dataBean.setDay19(productionNineteenDay);
        dataBean.setDay20(productionTwentyDay);
        dataBean.setDay21(productionTwentyOneDay);
        dataBean.setDay22(productionTwentyTwoDay);
        dataBean.setDay23(productionTwentyThreeDay);
        dataBean.setDay24(productionTwentyForeDay);
        dataBean.setDay25(productionTwentyFiveDay);
        dataBean.setDay26(productionTwentySixDay);
        dataBean.setDay27(productionTwentySevenDay);
        dataBean.setDay28(productionTwentyEightDay);
        dataBean.setDay29(productionTwentyNineDay);
        dataBean.setDay30(productionThirtyDay);
        dataBean.setDay31(productionThirtyOneDay);
        dataBean.setMemo(productionRemarks);
        dataBean.setRecorder(productionRecorder);
        dataBean.setRecordat(productionRecordat);
        detailBeenList.add(dataBean);
        String detailb = gson.toJson(detailBeenList);
        if (NetWork.isNetWorkAvailable(this)) {
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
                            int resindex = Integer.parseInt(response);
                            if (resindex > 3) {
                                ToastUtils.ShowToastMessage("保存成功，请刷新页面", ProductionActivity.this);
                            } else if (ression == "3" || ression.equals("3")) {
                                ToastUtils.ShowToastMessage("保存失败", ProductionActivity.this);
                            } else if (ression == "4" || ression.equals("4")) {
                                ToastUtils.ShowToastMessage("数据错误，请重试", ProductionActivity.this);
                            } else {
                                ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionActivity.this);
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ProductionActivity.this);
        }
    }

    private void setNewlyBuild() {
        String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
        sp = this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String proid = sp.getString("proid", "");
        String salesid = sp.getString("salesid", "");
        String proColumnTitle = sp.getString("proColumnTitle", "");//部门
        if (proColumnTitle == "" || proColumnTitle.equals("")) {
            proColumnTitle = null;
//            String columntitle = proColumnTitle.replace("\0","null");
            System.out.print(proColumnTitle);
        }
        String proProcedureTitle = sp.getString("proProcedureTitle", "");//工序
        if (proProcedureTitle == "" || proProcedureTitle.equals("")) {
            proProcedureTitle = null;
        }
        String proPrdstatusTitle = sp.getString("proadapterPrdstatusTitle", "");//状态//
        if (proPrdstatusTitle == "" || proPrdstatusTitle.equals("")) {
            proPrdstatusTitle = null;
        }
        String productionItem = sp.getString("productionItem", "");//款号
        if (productionItem == "" || productionItem.equals("")) {
            productionItem = null;
        }
        String productionDocumentary = sp.getString("productionadapterDocumentary", "");//跟单//
        if (productionDocumentary == "" || productionDocumentary.equals("")) {
            productionDocumentary = null;
        }
        String productionFactory = sp.getString("productionFactory", "");//工厂
        if (productionFactory == "" || productionFactory.equals("")) {
            productionFactory = null;
        }
        String productionOthers = sp.getString("productionOthers", "");//组别人
        if (productionOthers == "" || productionOthers.equals("")) {
            productionOthers = null;
        }
        String productionSingularSystem = sp.getString("productionadapterSingularSystem", "");//制单数//
        if (productionSingularSystem == "" || productionSingularSystem.equals("")) {
            productionSingularSystem = null;
        }
        String productionColor = sp.getString("productionColor", "");//花色
        if (productionColor == "" || productionColor.equals("")) {
            productionColor = null;
        }
        String productionTaskNumber = sp.getString("productionTaskNumber", "");//任务数
        if (productionTaskNumber == "" || productionTaskNumber.equals("")) {
            productionTaskNumber = null;
        }
        String productionSize = sp.getString("productionSize", "");//尺码
        if (productionSize == "" || productionSize.equals("")) {
            productionSize = null;
        }
        String productionClippingNumber = sp.getString("productionClippingNumber", "");//实裁数
        if (productionClippingNumber == "" || productionClippingNumber.equals("")) {
            productionClippingNumber = null;
        }
        String productionCompletedLastMonth = sp.getString("productionCompletedLastMonth", "");//上月完工
        if (productionCompletedLastMonth == "" || productionCompletedLastMonth.equals("")) {
            productionCompletedLastMonth = null;
        }
        String productionTotalCompletion = sp.getString("productionTotalCompletion", "");//总完工数
        if (productionTotalCompletion == "" || productionTotalCompletion.equals("")) {
            productionTotalCompletion = null;
        }
        String productionBalanceAmount = sp.getString("productionBalanceAmount", "");//结余数量
        if (productionBalanceAmount == "" || productionBalanceAmount.equals("")) {
            productionBalanceAmount = null;
        }
        String productionYear = sp.getString("productionYear", "");//年
        if (productionYear == "" || productionYear.equals("")) {
            productionYear = null;
        }
        String productionMonth = sp.getString("productionMonth", "");//月
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
        String productionRecorder = sp.getString("productionRecorder", "");//制单人
        if (productionRecorder == "" || productionRecorder.equals("")) {
            productionRecorder = null;
        }
        String productionRecordat = sp.getString("productionRecordat", "");//制单时间
        if (productionRecordat == "" || productionRecordat.equals("")) {
            productionRecordat = null;
        }
        Gson gson = new Gson();
        ProducationDetailBean.DataBean dataBean = new ProducationDetailBean.DataBean();
        dataBean.setID(Integer.parseInt(proid));
        dataBean.setSalesid(Integer.parseInt(salesid));
        dataBean.setItem(productionItem);
        dataBean.setPrddocumentary(productionDocumentary);
        dataBean.setSubfactory(productionFactory);
        dataBean.setSubfactoryTeams(proColumnTitle);
        dataBean.setWorkingProcedure(proProcedureTitle);
        dataBean.setWorkers(productionOthers);
        dataBean.setPqty(productionSingularSystem);
        dataBean.setProdcol(productionColor);
        dataBean.setTaskqty(productionTaskNumber);
        dataBean.setMdl(productionSize);
        dataBean.setFactcutqty(productionClippingNumber);
        dataBean.setLastMonQty(productionCompletedLastMonth);
        dataBean.setSumCompletedQty(productionTotalCompletion);
        dataBean.String(productionBalanceAmount);
        dataBean.setPrdstatus(proPrdstatusTitle);
        dataBean.setYear(productionYear);
        dataBean.setMonth(productionMonth);
        dataBean.setDay1(productionOneDay);
        dataBean.setDay2(productionTwoDay);
        dataBean.setDay3(productionThreeDay);
        dataBean.setDay4(productionForeDay);
        dataBean.setDay5(productionFiveDay);
        dataBean.setDay6(productionSixDay);
        dataBean.setDay7(productionSevenDay);
        dataBean.setDay8(productionEightDay);
        dataBean.setDay9(productionNineDay);
        dataBean.setDay10(productionTenDay);
        dataBean.setDay11(productionElevenDay);
        dataBean.setDay12(productionTwelveDay);
        dataBean.setDay13(productionThirteenDay);
        dataBean.setDay14(productionFourteenDay);
        dataBean.setDay15(productionFifteenDay);
        dataBean.setDay16(productionSixteenDay);
        dataBean.setDay17(productionSeventeenDay);
        dataBean.setDay18(productionEighteenDay);
        dataBean.setDay19(productionNineteenDay);
        dataBean.setDay20(productionTwentyDay);
        dataBean.setDay21(productionTwentyOneDay);
        dataBean.setDay22(productionTwentyTwoDay);
        dataBean.setDay23(productionTwentyThreeDay);
        dataBean.setDay24(productionTwentyForeDay);
        dataBean.setDay25(productionTwentyFiveDay);
        dataBean.setDay26(productionTwentySixDay);
        dataBean.setDay27(productionTwentySevenDay);
        dataBean.setDay28(productionTwentyEightDay);
        dataBean.setDay29(productionTwentyNineDay);
        dataBean.setDay30(productionThirtyDay);
        dataBean.setDay31(productionThirtyOneDay);
        dataBean.setMemo(productionRemarks);
        dataBean.setRecorder(productionRecorder);
        dataBean.setRecordat(productionRecordat);
        detailBeenList.add(dataBean);
        String detailb = gson.toJson(detailBeenList);
        if (NetWork.isNetWorkAvailable(this)) {
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
                            int resindex = Integer.parseInt(response);
                            if (resindex > 3) {
                                ToastUtils.ShowToastMessage("保存成功，请刷新页面", ProductionActivity.this);
                            } else if (ression == "3" || ression.equals("3")) {
                                ToastUtils.ShowToastMessage("保存失败", ProductionActivity.this);
                            } else if (ression == "4" || ression.equals("4")) {
                                ToastUtils.ShowToastMessage("数据错误，请重试", ProductionActivity.this);
                            } else {
                                ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionActivity.this);
                            }
                        }
                    });
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
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });

        popupMenu.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
