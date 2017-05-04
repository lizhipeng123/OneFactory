package com.daoran.newfactory.onefactory.activity.work.production;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
        etSqlDetail.setSelection(etSqlDetail.length());
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
                                    String count = String.valueOf(pageCount / 20);
                                    tvSignPage.setText(count);
                                    adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                    mData.setAdapter(adapter);
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
                                    String count = String.valueOf(pageCount / 20);
                                    tvSignPage.setText(count);
                                    adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                    mData.setAdapter(adapter);
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
                                    String count = String.valueOf(pageCount / 20);
                                    tvSignPage.setText(count);
                                    adapter = new ProductionAdapter(ProductionActivity.this, detailBeenList);
                                    mData.setAdapter(adapter);
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

    private void setSave() {
        String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
        sp = this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String proColumnTitle = sp.getString("proColumnTitle", "");//部门
        String proProcedureTitle = sp.getString("proProcedureTitle", "");//工序

        String proPrdstatusTitle = sp.getString("proadapterPrdstatusTitle", "");//状态//

        String productionItem = sp.getString("productionItem", "");//款号

        String productionDocumentary = sp.getString("productionadapterDocumentary", "");//跟单//

        String productionFactory = sp.getString("productionFactory", "");//工厂
        String productionOthers = sp.getString("productionOthers", "");//组别人

        String productionSingularSystem = sp.getString("productionadapterSingularSystem", "");//制单数//

        String productionColor = sp.getString("productionColor", "");//花色
        String productionTaskNumber = sp.getString("productionTaskNumber", "");//任务数
        String productionSize = sp.getString("productionSize", "");//尺码
        String productionClippingNumber = sp.getString("productionClippingNumber", "");//实裁数
        String productionCompletedLastMonth = sp.getString("productionCompletedLastMonth", "");//上月完工
        String productionTotalCompletion = sp.getString("productionTotalCompletion", "");//总完工数
        String productionBalanceAmount = sp.getString("productionBalanceAmount", "");//结余数量
        String productionYear = sp.getString("productionYear", "");//年
        String productionMonth = sp.getString("productionMonth", "");//月
        String productionOneDay = sp.getString("productionOneDay", "");//1
        String productionTwoDay = sp.getString("productionTwoDay", "");//2
        String productionThreeDay = sp.getString("productionThreeDay", "");//3
        String productionForeDay = sp.getString("productionForeDay", "");//4
        String productionFiveDay = sp.getString("productionFiveDay", "");//5
        String productionSixDay = sp.getString("productionSixDay", "");//6
        String productionSevenDay = sp.getString("productionSevenDay", "");//7
        String productionEightDay = sp.getString("productionEightDay", "");//8
        String productionNineDay = sp.getString("productionNineDay", "");//9
        String productionTenDay = sp.getString("productionTenDay", "");//10
        String productionElevenDay = sp.getString("productionElevenDay", "");//11
        String productionTwelveDay = sp.getString("productionTwelveDay", "");//12
        String productionThirteenDay = sp.getString("productionThirteenDay", "");//13
        String productionFourteenDay = sp.getString("productionFourteenDay", "");//14
        String productionFifteenDay = sp.getString("productionFifteenDay", "");//15
        String productionSixteenDay = sp.getString("productionSixteenDay", "");//16
        String productionSeventeenDay = sp.getString("productionSeventeenDay", "");//17
        String productionEighteenDay = sp.getString("productionEighteenDay", "");//18
        String productionNineteenDay = sp.getString("productionNineteenDay", "");//19
        String productionTwentyDay = sp.getString("productionTwentyDay", "");//20
        String productionTwentyOneDay = sp.getString("productionTwentyOneDay", "");//21
        String productionTwentyTwoDay = sp.getString("productionTwentyTwoDay", "");//22
        String productionTwentyThreeDay = sp.getString("productionTwentyThreeDay", "");//23
        String productionTwentyForeDay = sp.getString("productionTwentyForeDay", "");//24
        String productionTwentyFiveDay = sp.getString("productionTwentyFiveDay", "");//25
        String productionTwentySixDay = sp.getString("productionTwentySixDay", "");//26
        String productionTwentySevenDay = sp.getString("productionTwentySevenDay", "");//27
        String productionTwentyEightDay = sp.getString("productionTwentyEightDay", "");//28
        String productionTwentyNineDay = sp.getString("productionTwentyNineDay", "");//29
        String productionThirtyDay = sp.getString("productionThirtyDay", "");//30
        String productionThirtyOneDay = sp.getString("productionThirtyOneDay", "");//31
        String productionRemarks = sp.getString("productionRemarks", "");//备注
        String productionRecorder = sp.getString("productionRecorder", "");//制单人
        String productionRecordat = sp.getString("productionRecordat", "");//制单时间
        ProducationDetailBean.DataBean dataBean = new ProducationDetailBean.DataBean();
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
        System.out.print(detailBeenList);
        String beenlist = detailBeenList.toString();
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.postString().
                    url(saveurl)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.print(response);
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
