package com.daoran.newfactory.onefactory.activity.work.commo;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.CommoditySqlAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.CommodityPostBean;
import com.daoran.newfactory.onefactory.bean.CommoditySaveBean;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
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

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 查货跟踪单
 * Created by lizhipeng on 2017/3/29.
 */
public class CommoditySqlActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private NoscrollListView mData;//列表

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack;//返回按钮
    private boolean prdmasterisnull = false;//判断是否选中
    private CommoDialog commoDialog;//查货条件查询弹出框
    private ImageView ivSearch;//条件查询

    private List<CommoditydetailBean.DataBean> dataBeen
            = new ArrayList<CommoditydetailBean.DataBean>();//查货信息实体集合
    private CommoditydetailBean commoditydetailBean;//列表实体bean
    private CommoditySqlAdapter sqlAdapter;//列表适配
    List<CommoditySaveBean> saveBeen = new ArrayList<CommoditySaveBean>();//实体list

    private TextView tvSignPage;//显示的总页数
    private EditText etSqlDetail;//输入的页数
    private Button btnSignPage;//翻页确认
    private Button btnCommoRefresh, btnCommoSave;//刷新，保存
    private LinearLayout ll_visibi;
    private TextView tv_visibi;
    private ScrollView scroll_content;

    private SharedPreferences sp;//轻量级存储本地数据
    private SPUtils spUtils;
    private int pageCount;//查询获取的总页数
    private int pageIndex = 0;//初始显示的页数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);//加载主页面
        getViews();
        initView();
        setData();
        setListener();
    }

    /**
     * 初始化控件
     */
    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivCommoditySql);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        tvSignPage = (TextView) findViewById(R.id.tvSignPage);
        btnSignPage = (Button) findViewById(R.id.btnSignPage);
        etSqlDetail = (EditText) findViewById(R.id.etSqlDetail);
        btnCommoRefresh = (Button) findViewById(R.id.btnCommoRefresh);
        btnCommoSave = (Button) findViewById(R.id.btnCommoSave);
        ll_visibi = (LinearLayout) findViewById(R.id.ll_visibi);
        tv_visibi = (TextView) findViewById(R.id.tv_visibi);
        scroll_content = (ScrollView) findViewById(R.id.scroll_content);
    }

    /**
     * 控件操作
     */
    private void initView() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
        etSqlDetail.setSelection(etSqlDetail.length());
    }

    /**
     * 实例化点击事件
     */
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);
        btnCommoRefresh.setOnClickListener(this);
        btnCommoSave.setOnClickListener(this);
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
                if (txt.length() == 0) {
                    ToastUtils.ShowToastMessage("页码不能为空", CommoditySqlActivity.this);
                } else if (txt.length() > txtcount.length()) {
                    ToastUtils.ShowToastMessage("页码超出输入范围", CommoditySqlActivity.this);
                } else {
                    setPageDetail();
                }
                break;
            /*刷新*/
            case R.id.btnCommoRefresh:
                setData();
                break;
            /*保存*/
            case R.id.btnCommoSave:
                setCommoSave();
                break;
        }
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
                    setPageDetail();
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
        sp = CommoditySqlActivity.this.getSharedPreferences("my_sp", 0);
        String recodename = sp.getString("commoname", "");//跟单
        String Style = sp.getString("commoStyle", "");//款号
//        String Factory = sp.getString("commoFactory", "");//跟单
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
        postBean.setPageSize(10);
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this);
            OkHttpUtils.postString()
                    .url(str)
                    .content(stringpost)
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
                                commoditydetailBean = new Gson().fromJson(ression, CommoditydetailBean.class);
                                dataBeen = commoditydetailBean.getData();
                                if (commoditydetailBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    System.out.print(dataBeen);
                                    pageCount = commoditydetailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / 10);
                                    tvSignPage.setText(count);
                                    sqlAdapter = new CommoditySqlAdapter(CommoditySqlActivity.this, dataBeen);
                                    mData.setAdapter(sqlAdapter);
                                } else {
                                    ll_visibi.setVisibility(View.VISIBLE);
                                    scroll_content.setVisibility(View.GONE);
                                    tv_visibi.setText("没有更多数据");
                                }
                                ResponseDialog.closeLoading();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
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
        ResponseDialog.showLoading(this);
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        sp = CommoditySqlActivity.this.getSharedPreferences("my_sp", 0);
        String recodename = sp.getString("commoname", "");//跟单
        String Style = sp.getString("commoStyle", "");//款号
//        String Factory = sp.getString("commoFactory", "");//跟单
        String Recode = sp.getString("commoRecode", "");//巡检
        String etprodialogProcedure = sp.getString("etproProcedure", "");//生产主管
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
        postBean.setPageSize(10);
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this);
            OkHttpUtils.postString()
                    .url(str)
                    .content(stringpost)
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
                                commoditydetailBean = new Gson().fromJson(ression, CommoditydetailBean.class);
                                dataBeen = commoditydetailBean.getData();
                                if (commoditydetailBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    System.out.print(dataBeen);
                                    pageCount = commoditydetailBean.getTotalCount();
                                    String count = String.valueOf(pageCount / 10);
                                    tvSignPage.setText(count);
                                    sqlAdapter = new CommoditySqlAdapter(CommoditySqlActivity.this, dataBeen);
                                    mData.setAdapter(sqlAdapter);
                                } else {
                                    ll_visibi.setVisibility(View.VISIBLE);
                                    scroll_content.setVisibility(View.GONE);
                                    tv_visibi.setText("没有更多信息");
                                }
                                ResponseDialog.closeLoading();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", CommoditySqlActivity.this);
        }
    }

    /**
     * 保存
     */
    private void setCommoSave() {
        if (NetWork.isNetWorkAvailable(this)) {
            String saveurl = HttpUrl.debugoneUrl + "QACwork/SaveQACwork/";
            sp = this.getSharedPreferences("my_sp", 0);
            SharedPreferences.Editor editorone = sp.edit();
            /*获取CommoditySqlAdapter中输入框或时间选择信息*/
            String commoproid = sp.getString("commoproid", "");//id
            String CommodityQCMasterScore = sp.getString("CommodityQCMasterScore", "");//主管评分
            if (CommodityQCMasterScore == "" || CommodityQCMasterScore.equals("")) {
                CommodityQCMasterScore = null;
            }
            String dateSealedrewtimesign = sp.getString("dateSealedrewtimesign", "");//封样接收时间
            if (dateSealedrewtimesign == "" || dateSealedrewtimesign.equals("")) {
                dateSealedrewtimesign = null;
            }
            String dateDocbacktimesign = sp.getString("dateDocbacktimesign", "");//大货接收时间
            if (dateDocbacktimesign == "" || dateDocbacktimesign.equals("")) {
                dateDocbacktimesign = null;
            }
            String CommodityPreMemo = sp.getString("CommodityPreMemo", "");//备注
            if (CommodityPreMemo == "" || CommodityPreMemo.equals("")) {
                CommodityPreMemo = null;
            }
            String datePredocdttimesign = sp.getString("datePredocdttimesign", "");//预计产前时间
            if (datePredocdttimesign == "" || datePredocdttimesign.equals("")) {
                datePredocdttimesign = null;
            }
            String datePredtimesign = sp.getString("datePredtimesign", "");//产前会时间
            if (datePredtimesign == "" || datePredtimesign.equals("")) {
                datePredtimesign = null;
            }
            String CommodityPredoc = sp.getString("CommodityPredoc", "");//产前会报告
            if (CommodityPredoc == "" || CommodityPredoc.equals("")) {
                CommodityPredoc = null;
            }
            String CommodityFabricsok = sp.getString("CommodityFabricsok", "");//大货面料情况
            if (CommodityFabricsok == "" || CommodityFabricsok.equals("")) {
                CommodityFabricsok = null;
            }
            String CommodityAccessoriesok = sp.getString("CommodityAccessoriesok", "");//大货辅料情况
            if (CommodityAccessoriesok == "" || CommodityAccessoriesok.equals("")) {
                CommodityAccessoriesok = null;
            }
            String CommoditySpcproDec = sp.getString("CommoditySpcproDec", "");//大货特殊工艺情况
            if (CommoditySpcproDec == "" || CommoditySpcproDec.equals("")) {
                CommoditySpcproDec = null;
            }
            String CommoditySpcproMemo = sp.getString("CommoditySpcproMemo", "");//特殊工艺备注
            if (CommoditySpcproMemo == "" || CommoditySpcproMemo.equals("")) {
                CommoditySpcproMemo = null;
            }
            String CommodityCutqty = sp.getString("CommodityCutqty", "");//实裁数
            if (CommodityCutqty == "" || CommodityCutqty.equals("")) {
                CommodityCutqty = null;
            }
            String dateSewFdttimesign = sp.getString("dateSewFdttimesign", "");//上线日期
            if (dateSewFdttimesign == "" || dateSewFdttimesign.equals("")) {
                dateSewFdttimesign = null;
            }
            String dateSewMdttimesign = sp.getString("dateSewMdttimesign", "");//下线日期
            if (dateSewMdttimesign == "" || dateSewMdttimesign.equals("")) {
                dateSewMdttimesign = null;
            }
            String datePrebdttimesign = sp.getString("datePrebdttimesign", "");//预计早期时间
            if (datePrebdttimesign == "" || datePrebdttimesign.equals("")) {
                datePrebdttimesign = null;
            }
            String dateQCbdttimesign = sp.getString("dateQCbdttimesign", "");//自查早期时间
            if (dateQCbdttimesign == "" || dateQCbdttimesign.equals("")) {
                dateQCbdttimesign = null;
            }
            String CommodityQCbdtDoc = sp.getString("CommodityQCbdtDoc", "");//早期报告
            if (CommodityQCbdtDoc == "" || CommodityQCbdtDoc.equals("")) {
                CommodityQCbdtDoc = null;
            }
            String datePremdttimesign = sp.getString("datePremdttimesign", "");//预计中期时间
            if (datePremdttimesign == "" || datePremdttimesign.equals("")) {
                datePremdttimesign = null;
            }
            String dateQCmdttimesign = sp.getString("dateQCmdttimesign", "");//自查中期时间
            if (dateQCmdttimesign == "" || dateQCmdttimesign.equals("")) {
                dateQCmdttimesign = null;
            }
            String CommodityQCmdtDoc = sp.getString("CommodityQCmdtDoc", "");//中期报告
            if (CommodityQCmdtDoc == "" || CommodityQCmdtDoc.equals("")) {
                CommodityQCmdtDoc = null;
            }
            String datePreedttimesign = sp.getString("datePreedttimesign", "");//预计尾查时间
            if (datePreedttimesign == "" || datePreedttimesign.equals("")) {
                datePreedttimesign = null;
            }
            String dateQCMedttimesign = sp.getString("dateQCMedttimesign", "");//自查尾期时间
            if (dateQCMedttimesign == "" || dateQCMedttimesign.equals("")) {
                dateQCMedttimesign = null;
            }
            String CommodityQCedtDoc = sp.getString("CommodityQCedtDoc", "");//尾期报告
            if (CommodityQCedtDoc == "" || CommodityQCedtDoc.equals("")) {
                CommodityQCedtDoc = null;
            }
            String dateFctmdttimesign = sp.getString("dateFctmdttimesign", "");//客查中期时间
            if (dateFctmdttimesign == "" || dateFctmdttimesign.equals("")) {
                dateFctmdttimesign = null;
            }
            String dateFctedttimesign = sp.getString("dateFctedttimesign", "");//客查尾期时间
            if (dateFctedttimesign == "" || dateFctedttimesign.equals("")) {
                dateFctedttimesign = null;
            }
            String datePackbdattimesign = sp.getString("datePackbdattimesign", "");//成品包装日期
            if (datePackbdattimesign == "" || datePackbdattimesign.equals("")) {
                datePackbdattimesign = null;
            }
            String CommodityPackqty2 = sp.getString("CommodityPackqty2", "");//装箱数量
            if (CommodityPackqty2 == "" || CommodityPackqty2.equals("")) {
                CommodityPackqty2 = null;
            }
            String CommodityQCMemo = sp.getString("CommodityQCMemo", "");//qc备注
            if (CommodityQCMemo == "" || CommodityQCMemo.equals("")) {
                CommodityQCMemo = null;
            }
            String dateFactlcdattimesign = sp.getString("dateFactlcdattimesign", "");//离厂日期
            if (dateFactlcdattimesign == "" || dateFactlcdattimesign.equals("")) {
                dateFactlcdattimesign = null;
            }
            String CommodityBatchid = sp.getString("CommodityBatchid", "");//查货批次
            if (CommodityBatchid == "" || CommodityBatchid.equals("")) {
                CommodityBatchid = null;
            }
            String tvCommoOurAfter = sp.getString("commohdTitle", "");//后道
            if (tvCommoOurAfter == "" || tvCommoOurAfter.equals("")) {
                tvCommoOurAfter = null;
            }
            String dateCtmchkdttimesign = sp.getString("dateCtmchkdttimesign", "");//业务员确认客查日期
            if (dateCtmchkdttimesign == "" || dateCtmchkdttimesign.equals("")) {
                dateCtmchkdttimesign = null;
            }
            String CommodityIPQCPedt = sp.getString("CommodityIPQCPedt", "");//尾查预查
            if (CommodityIPQCPedt == "" || CommodityIPQCPedt.equals("")) {
                CommodityIPQCPedt = null;
            }
            String CommodityIPQCmdt = sp.getString("CommodityIPQCmdt", "");//巡检中查
            if (CommodityIPQCmdt == "" || CommodityIPQCmdt.equals("")) {
                CommodityIPQCmdt = null;
            }
            String CommodityQAname = sp.getString("CommodityQAname", "");//qa首扎
            if (CommodityQAname == "" || CommodityQAname.equals("")) {
                CommodityQAname = null;
            }
            String CommodityQAScore = sp.getString("CommodityQAScore", "");//qa首扎件
            if (CommodityQAScore == "" || CommodityQAScore.equals("")) {
                CommodityQAScore = null;
            }
            String dateQAMemotimesign = sp.getString("dateQAMemotimesign", "");//qa首扎日
            if (dateQAMemotimesign == "" || dateQAMemotimesign.equals("")) {
                dateQAMemotimesign = null;
            }
            String uriid = sp.getString("uriid", "");
            Gson gson = new Gson();
            CommoditySaveBean saveBean = new CommoditySaveBean();
            if (uriid == commoproid || uriid.equals(commoproid)) {//判断选中的是否是同一行
                if (!commoproid.equals("")) {//选中当前行
                    ResponseDialog.showLoading(this);
                    saveBean.setID(Integer.parseInt(commoproid));
                    saveBean.setItem(null);
                    saveBean.setCtmtxt(null);
                    saveBean.setPrddocumentary(null);
                    saveBean.setPrdmaster(null);
                    saveBean.setQCMasterScore(CommodityQCMasterScore);
                    saveBean.setSealedrev(dateSealedrewtimesign);
                    saveBean.setDocback(dateDocbacktimesign);
                    saveBean.setLcdat(null);
                    saveBean.setTaskqty(null);
                    saveBean.setPreMemo(CommodityPreMemo);
                    saveBean.setPredocdt(datePredocdttimesign);
                    saveBean.setPredt(datePredtimesign);
                    saveBean.setPredoc(CommodityPredoc);
                    saveBean.setFabricsok(CommodityFabricsok);
                    saveBean.setAccessoriesok(CommodityAccessoriesok);
                    saveBean.setSpcproDec(CommoditySpcproDec);
                    saveBean.setSpcproMemo(CommoditySpcproMemo);
                    saveBean.setCutqty(CommodityCutqty);
                    saveBean.setSewFdt(dateSewFdttimesign);
                    saveBean.setSewMdt(dateSewMdttimesign);
                    saveBean.setSubfactory(null);
                    saveBean.setPrebdt(datePrebdttimesign);
                    saveBean.setQCbdt(dateQCbdttimesign);
                    saveBean.setQCbdtDoc(CommodityQCbdtDoc);
                    saveBean.setPremdt(datePremdttimesign);
                    saveBean.setQCmdt(dateQCmdttimesign);
                    saveBean.setQCmdtDoc(CommodityQCmdtDoc);
                    saveBean.setPreedt(datePreedttimesign);
                    saveBean.setQCMedt(dateQCMedttimesign);
                    saveBean.setQCedtDoc(CommodityQCedtDoc);
                    saveBean.setFctmdt(dateFctmdttimesign);
                    saveBean.setFctedt(dateFctedttimesign);
                    saveBean.setPackbdat(datePackbdattimesign);
                    saveBean.setPackqty2(CommodityPackqty2);
                    saveBean.setQCMemo(CommodityQCMemo);
                    saveBean.setFactlcdat(dateFactlcdattimesign);
                    saveBean.setBatchid(CommodityBatchid);
                    saveBean.setOurAfter(tvCommoOurAfter);
                    saveBean.setCtmchkdt(dateCtmchkdttimesign);
                    saveBean.setIPQCPedt(CommodityIPQCPedt);
                    saveBean.setIPQCmdt(CommodityIPQCmdt);
                    saveBean.setQAname(CommodityQAname);
                    saveBean.setQAScore(CommodityQAScore);
                    saveBean.setQAMemo(dateQAMemotimesign);
                    saveBeen.add(saveBean);
                    String commjson = gson.toJson(saveBeen);//转换json字符串
//                    String dateee = commjson.replace("\"\"", "null");
                    /*删除本地存储中的适配器中输入框的信息*/
                    editorone.remove("commoproid");
                    editorone.remove("CommodityQCMasterScore");
                    editorone.remove("dateSealedrewtimesign");
                    editorone.remove("dateDocbacktimesign");
                    editorone.remove("CommodityPreMemo");
                    editorone.remove("datePredocdttimesign");
                    editorone.remove("datePredtimesign");
                    editorone.remove("CommodityPredoc");
                    editorone.remove("CommodityFabricsok");
                    editorone.remove("CommodityAccessoriesok");
                    editorone.remove("CommoditySpcproDec");
                    editorone.remove("CommoditySpcproMemo");
                    editorone.remove("CommodityCutqty");
                    editorone.remove("dateSewFdttimesign");
                    editorone.remove("dateSewMdttimesign");
                    editorone.remove("datePrebdttimesign");
                    editorone.remove("dateQCbdttimesign");
                    editorone.remove("CommodityQCbdtDoc");
                    editorone.remove("datePremdttimesign");
                    editorone.remove("dateQCmdttimesign");
                    editorone.remove("CommodityQCmdtDoc");
                    editorone.remove("datePreedttimesign");
                    editorone.remove("dateQCMedttimesign");
                    editorone.remove("CommodityQCedtDoc");
                    editorone.remove("dateFctmdttimesign");
                    editorone.remove("dateFctedttimesign");
                    editorone.remove("datePackbdattimesign");
                    editorone.remove("CommodityPackqty2");
                    editorone.remove("CommodityQCMemo");
                    editorone.remove("dateFactlcdattimesign");
                    editorone.remove("CommodityBatchid");
                    editorone.remove("commohdTitle");
                    editorone.remove("dateCtmchkdttimesign");
                    editorone.remove("CommodityIPQCPedt");
                    editorone.remove("CommodityIPQCmdt");
                    editorone.remove("CommodityQAname");
                    editorone.remove("CommodityQAScore");
                    editorone.remove("dateQAMemotimesign");
                    editorone.commit();
                    OkHttpUtils.postString()
                            .url(saveurl)
                            .content(commjson)
                            .mediaType(MediaType.parse("application/json;charset=utf-8"))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                    ToastUtils.ShowToastMessage("数据错误，请重新输入", CommoditySqlActivity.this);
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    System.out.print(response);
                                    response = response.replace("\\", "");
                                    String ression = StringUtil.sideTrim(response, "\"");
                                    System.out.print(ression);
                                    if (ression.equals("true")) {
                                        ResponseDialog.closeLoading();
                                        ToastUtils.ShowToastMessage("保存成功", CommoditySqlActivity.this);
                                        setData();
                                    } else {
                                        ResponseDialog.closeLoading();
                                        ToastUtils.ShowToastMessage("保存失败", CommoditySqlActivity.this);
                                    }
                                }
                            });
                } else {
                    editorone.remove("commoproid");
                    editorone.remove("CommodityQCMasterScore");
                    editorone.remove("dateSealedrewtimesign");
                    editorone.remove("dateDocbacktimesign");
                    editorone.remove("CommodityPreMemo");
                    editorone.remove("datePredocdttimesign");
                    editorone.remove("datePredtimesign");
                    editorone.remove("CommodityPredoc");
                    editorone.remove("CommodityFabricsok");
                    editorone.remove("CommodityAccessoriesok");
                    editorone.remove("CommoditySpcproDec");
                    editorone.remove("CommoditySpcproMemo");
                    editorone.remove("CommodityCutqty");
                    editorone.remove("dateSewFdttimesign");
                    editorone.remove("dateSewMdttimesign");
                    editorone.remove("datePrebdttimesign");
                    editorone.remove("dateQCbdttimesign");
                    editorone.remove("CommodityQCbdtDoc");
                    editorone.remove("datePremdttimesign");
                    editorone.remove("dateQCmdttimesign");
                    editorone.remove("CommodityQCmdtDoc");
                    editorone.remove("datePreedttimesign");
                    editorone.remove("dateQCMedttimesign");
                    editorone.remove("CommodityQCedtDoc");
                    editorone.remove("dateFctmdttimesign");
                    editorone.remove("dateFctedttimesign");
                    editorone.remove("datePackbdattimesign");
                    editorone.remove("CommodityPackqty2");
                    editorone.remove("CommodityQCMemo");
                    editorone.remove("dateFactlcdattimesign");
                    editorone.remove("CommodityBatchid");
                    editorone.remove("commohdTitle");
                    editorone.remove("dateCtmchkdttimesign");
                    editorone.remove("CommodityIPQCPedt");
                    editorone.remove("CommodityIPQCmdt");
                    editorone.remove("CommodityQAname");
                    editorone.remove("CommodityQAScore");
                    editorone.remove("dateQAMemotimesign");
                    editorone.commit();
                    new AlertDialog.Builder(CommoditySqlActivity.this).setTitle("提示信息")
                            .setMessage("请选择当前行，再进行保存")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();//相应事件
                }
            } else {
                ToastUtils.ShowToastMessage("请选择当前行，再进行修改保存",
                        CommoditySqlActivity.this);
            }
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, CommoditySqlActivity.this);
        }
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
        editor.remove("CommodityQCMasterScore");
        editor.remove("dateSealedrewtimesign");
        editor.remove("dateDocbacktimesign");
        editor.remove("CommodityPreMemo");
        editor.remove("datePredocdttimesign");
        editor.remove("datePredtimesign");
        editor.remove("CommodityPredoc");
        editor.remove("CommodityFabricsok");
        editor.remove("CommodityAccessoriesok");
        editor.remove("CommoditySpcproDec");
        editor.remove("CommoditySpcproMemo");
        editor.remove("CommodityCutqty");
        editor.remove("dateSewFdttimesign");
        editor.remove("dateSewMdttimesign");
        editor.remove("datePrebdttimesign");
        editor.remove("dateQCbdttimesign");
        editor.remove("CommodityQCbdtDoc");
        editor.remove("datePremdttimesign");
        editor.remove("dateQCmdttimesign");
        editor.remove("CommodityQCmdtDoc");
        editor.remove("datePreedttimesign");
        editor.remove("dateQCMedttimesign");
        editor.remove("CommodityQCedtDoc");
        editor.remove("dateFctmdttimesign");
        editor.remove("dateFctedttimesign");
        editor.remove("datePackbdattimesign");
        editor.remove("CommodityPackqty2");
        editor.remove("CommodityQCMemo");
        editor.remove("dateFactlcdattimesign");
        editor.remove("CommodityBatchid");
        editor.remove("commohdTitle");
        editor.remove("dateCtmchkdttimesign");
        editor.remove("CommodityIPQCPedt");
        editor.remove("CommodityIPQCmdt");
        editor.remove("CommodityQAname");
        editor.remove("CommodityQAScore");
        editor.remove("dateQAMemotimesign");
        editor.commit();
        super.onDestroy();
    }
}
