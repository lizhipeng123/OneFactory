package com.daoran.newfactory.onefactory.activity.work.qacwork;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.daoran.newfactory.onefactory.adapter.qacworkadapter.QACworkSearchAdapter;
import com.daoran.newfactory.onefactory.adapter.qacworkadapter.QACworkSearchLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkRightsTableBean;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkSearchBean;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkPageDataBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.Listener.SetListener;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.utils.Util;
import com.daoran.newfactory.onefactory.view.dialog.qacworkdialog.QACworkSearchDialog;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 查货跟踪单
 * Created by lizhipeng on 2017/3/29.
 */
public class QACworkSearchActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private NoscrollListView lv_data;//列表
    private NoscrollListView lv_cleft;//左侧款号列表
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private QACworkSearchDialog QACworkSearchDialog;//查货条件查询弹出框
    private ImageView ivSearch,//条件查询
            ivUpLeftPage, ivDownRightPage,//上一页，下一页控件
            ivProductionBack;//返回按钮
    private List<QACworkPageDataBean.DataBean> dataBeen
            = new ArrayList<QACworkPageDataBean.DataBean>();//查货信息实体集合
    private QACworkPageDataBean QACworkPageDataBean;//列表实体bean
    private QACworkSearchLeftAdapter leftAdapter;//左侧编号列表适配
    private QACworkSearchAdapter sqlAdapter;//列表适配

    private List<QACworkRightsTableBean.JsonTextBean> jsonTextBeanlist =
            new ArrayList<QACworkRightsTableBean.JsonTextBean>();//列权限集合
    private QACworkRightsTableBean QACworkRightsTableBean;//列权限实体

    private TextView tvSignPage,//显示的总页数
            tv_visibi;//空数据显示的页面信息
    private TextView tvProTitle;//标题头
    private EditText etSqlDetail;//输入的页数
    private Button btnSignPage,//翻页确认
            spinnermenu;//最右侧菜单
    private LinearLayout ll_visibi;//空数据显示的页面
    private ScrollView scroll_content;//查货跟踪可上下滑动的视图
    private Spinner spinnCommoPageClumns;//选择每页显示的条目数

    private int pageCount;//查询获取的总页数
    private int pageIndex = 0;//初始显示的页数
    private String configid, Prddocumentary, QACworkDialogItem, pagesize,
            QACworkDialogIPQC, QACworkDialogprdmaster, QACworkCheckedd;

    public static QACworkSearchActivity QACworkinstance = null;
    private SharedPreferences sp;//轻量级存储本地数据
    private SPUtils spUtils;

    //拼接的列名数组
    private String[] columns = new String[]{"ID", "subfactory", "item", "sealedrev",
            "docback", "predt", "lcdat", "sewFdt", "sewMdt", "taskqty",
            "cutqty", "preMemo", "predoc", "fabricsok", "accessoriesok",
            "spcproDec", "spcproMemo", "QCbdt", "QCmdt", "QCMedt", "QCbdtDoc",
            "QCmdtDoc", "QCedtDoc", "fctmdt", "fctedt", "prddocumentary",
            "packbdat", "packqty2", "QCMemo", "factlcdat", "ourAfter", "prdmaster",
            "QCMasterScore", "batchid", "QAname", "QAScore", "QAMemo", "ctmtxt",
            "ctmchkdt", "IPQCmdt", "IPQCPedt", "predocdt", "prebdt", "premdt",
            "preedt"};
    private List<String> columnlist = Arrays.asList(columns);//数组转化为集合

    private TextView tvCommodetailCtmtxt, tvCommodetailPrddocumentary,
            tvCommodetailPrdmaster, tvCommodetailQCMasterScore,
            tvCommodetailSealedrev, tvCommodetailDocback, tvCommodetailLcdat,
            tvCommodetailCount, tvCommodetailPreMemo, tvCommodetailPredocdt,
            tvCommodetailPredt, tvCommodetailPredoc, tvCommodetailFabricsok,
            tvCommodetailAccessoriesok, tvCommodetailSpcproDec,
            tvCommodetailSpcproMemo, tvCommodetailCutqty, tvCommodetailSewFdt,
            tvCommodetailSewMdt, tvCommodetailSubfactory, tvCommodetailPrebdt,
            tvCommodetailQCbdt, llPPSDetailTxtQCbdtDoc, tvCommodetailPremdt,
            tvCommodetailQCmdt, tvCommodetailQCmdtDoc, tvCommodetailPreedt,
            tvCommodetailQCMedt, tvCommodetailQCedtDoc, tvCommodetailFctmdt,
            tvCommodetailFctedt, tvCommodetailPackbdat, tvCommodetailPackqty2,
            tvCommodetailQCMemo, tvCommodetailFactlcdat, tvCommodetailBatchid,
            tvCommodetailOurAfter, tvCommodetailCtmchkdt, tvCommodetailIPQCPedt,
            tvCommodetailIPQC, tvCommodetailIPQCmdt, tvCommodetailQAname,
            tvCommodetailQAScore, tvCommodetailQAMemo, tvCommodetailChker,
            tvCommodetailChkpdt, tvCommodetailChkfctdt, tvCommodetailChkplace;
    private View tvviewctmtxt, tvviewprddocumentary, tvviewprdmaster,
            tvviewQCMasterScore, tvviewsealedrev, tvviewdocback,
            tvviewlcdat, tvviewtaskqty, tvviewpreMemo, tvviewpredocdt,
            tvviewpredt, tvviewpredoc, tvviewfabricsok, tvviewaccessoriesok,
            tvviewspcproDec, tvviewspcproMemo, tvviewcutqty, tvviewsewFdt,
            tvviewsewMdt, tvviewsubfactory, tvviewprebdt, tvviewQCbdt,
            tvviewQCbdtDoc, tvviewpremdt, tvviewQCmdt, tvviewQCmdtDoc,
            tvviewpreedt, tvviewQCMedt, tvviewQCedtDoc, tvviewfctmdt,
            tvviewfctedt, tvviewpackbdat, tvviewpackqty2, tvviewQCMemo,
            tvviewfactlcdat, tvviewbatchid, tvviewourAfter, tvviewctmchkdt,
            tvviewIPQCPedt, tvviewIPQC, tvviewIPQCmdt, tvviewQAname, tvviewQAScore,
            tvviewQAMemo, tvviewchker, tvviewchkpdt, tvviewchkfctdt,
            tvviewchkplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qacwork_search);//加载主页面
        QACworkinstance = this;
        getViews();
        initView();
        setListener();
    }

    /*初始化控件*/
    private void getViews() {
        View viewButton = findViewById(R.id.button_filp_view);//引用include布局
        View rl_title_top = findViewById(R.id.rl_title_top);
        ivProductionBack = (ImageView) rl_title_top.findViewById(R.id.ivProductionBack);
        tvProTitle = (TextView) rl_title_top.findViewById(R.id.tvProTitle);
        ivSearch = (ImageView) rl_title_top.findViewById(R.id.ivSearch);
        spinnermenu = (Button) rl_title_top.findViewById(R.id.spinnermenu);

        lv_data = (NoscrollListView) findViewById(R.id.lv_data);
        lv_cleft = (NoscrollListView) findViewById(R.id.lv_cleft);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        ll_visibi = (LinearLayout) findViewById(R.id.ll_visibi);
        tv_visibi = (TextView) findViewById(R.id.tv_visibi);
        scroll_content = (ScrollView) findViewById(R.id.scroll_content);

        tvSignPage = (TextView) viewButton.findViewById(R.id.tvSignPage);
        btnSignPage = (Button) viewButton.findViewById(R.id.btnSignPage);
        etSqlDetail = (EditText) viewButton.findViewById(R.id.etSqlDetail);
        spinnCommoPageClumns = (Spinner) viewButton.findViewById(R.id.spinnCommoPageClumns);
        ivUpLeftPage = (ImageView) viewButton.findViewById(R.id.ivUpLeftPage);
        ivDownRightPage = (ImageView) viewButton.findViewById(R.id.ivDownRightPage);

        tvCommodetailCtmtxt = (TextView) findViewById(R.id.tv_commodetail_ctmtxt);
        tvviewctmtxt = findViewById(R.id.tvviewctmtxt);
        tvCommodetailPrddocumentary = (TextView) findViewById(R.id.tv_commodetail_prddocumentary);
        tvviewprddocumentary = findViewById(R.id.tvviewprddocumentary);
        tvCommodetailPrdmaster = (TextView) findViewById(R.id.tv_commodetail_prdmaster);
        tvviewprdmaster = findViewById(R.id.tvviewprdmaster);
        tvCommodetailQCMasterScore = (TextView) findViewById(R.id.tv_commodetail_QCMasterScore);
        tvviewQCMasterScore = findViewById(R.id.tvviewQCMasterScore);
        tvCommodetailSealedrev = (TextView) findViewById(R.id.tv_commodetail_sealedrev);
        tvviewsealedrev = findViewById(R.id.tvviewsealedrev);
        tvCommodetailDocback = (TextView) findViewById(R.id.tv_commodetail_docback);
        tvviewdocback = findViewById(R.id.tvviewdocback);
        tvCommodetailLcdat = (TextView) findViewById(R.id.tv_commodetail_lcdat);
        tvviewlcdat = findViewById(R.id.tvviewlcdat);
        tvCommodetailCount = (TextView) findViewById(R.id.tv_commodetail_count);
        tvviewtaskqty = findViewById(R.id.tvviewtaskqty);
        tvCommodetailPreMemo = (TextView) findViewById(R.id.tv_commodetail_preMemo);
        tvviewpreMemo = findViewById(R.id.tvviewpreMemo);
        tvCommodetailPredocdt = (TextView) findViewById(R.id.tv_commodetail_predocdt);
        tvviewpredocdt = findViewById(R.id.tvviewpredocdt);
        tvCommodetailPredt = (TextView) findViewById(R.id.tv_commodetail_predt);
        tvviewpredt = findViewById(R.id.tvviewpredt);
        tvCommodetailPredoc = (TextView) findViewById(R.id.tv_commodetail_predoc);
        tvviewpredoc = findViewById(R.id.tvviewpredoc);
        tvCommodetailFabricsok = (TextView) findViewById(R.id.tv_commodetail_fabricsok);
        tvviewfabricsok = findViewById(R.id.tvviewfabricsok);
        tvCommodetailAccessoriesok = (TextView) findViewById(R.id.tv_commodetail_accessoriesok);
        tvviewaccessoriesok = findViewById(R.id.tvviewaccessoriesok);
        tvCommodetailSpcproDec = (TextView) findViewById(R.id.tv_commodetail_spcproDec);
        tvviewspcproDec = findViewById(R.id.tvviewspcproDec);
        tvCommodetailSpcproMemo = (TextView) findViewById(R.id.tv_commodetail_spcproMemo);
        tvviewspcproMemo = findViewById(R.id.tvviewspcproMemo);
        tvCommodetailCutqty = (TextView) findViewById(R.id.tv_commodetail_cutqty);
        tvviewcutqty = findViewById(R.id.tvviewcutqty);
        tvCommodetailSewFdt = (TextView) findViewById(R.id.tv_commodetail_sewFdt);
        tvviewsewFdt = findViewById(R.id.tvviewsewFdt);
        tvCommodetailSewMdt = (TextView) findViewById(R.id.tv_commodetail_sewMdt);
        tvviewsewMdt = findViewById(R.id.tvviewsewMdt);
        tvCommodetailSubfactory = (TextView) findViewById(R.id.tv_commodetail_subfactory);
        tvviewsubfactory = findViewById(R.id.tvviewsubfactory);
        tvCommodetailPrebdt = (TextView) findViewById(R.id.tv_commodetail_prebdt);
        tvviewprebdt = findViewById(R.id.tvviewprebdt);
        tvCommodetailQCbdt = (TextView) findViewById(R.id.tv_commodetail_QCbdt);
        tvviewQCbdt = findViewById(R.id.tvviewQCbdt);
        llPPSDetailTxtQCbdtDoc = (TextView) findViewById(R.id.ll_PPSDetail_txt_QCbdtDoc);
        tvviewQCbdtDoc = findViewById(R.id.tvviewQCbdtDoc);
        tvCommodetailPremdt = (TextView) findViewById(R.id.tv_commodetail_premdt);
        tvviewpremdt = findViewById(R.id.tvviewpremdt);
        tvCommodetailQCmdt = (TextView) findViewById(R.id.tv_commodetail_QCmdt);
        tvviewQCmdt = findViewById(R.id.tvviewQCmdt);
        tvCommodetailQCmdtDoc = (TextView) findViewById(R.id.tv_commodetail_QCmdtDoc);
        tvviewQCmdtDoc = findViewById(R.id.tvviewQCmdtDoc);
        tvCommodetailPreedt = (TextView) findViewById(R.id.tv_commodetail_preedt);
        tvviewpreedt = findViewById(R.id.tvviewpreedt);
        tvCommodetailQCMedt = (TextView) findViewById(R.id.tv_commodetail_QCMedt);
        tvviewQCMedt = findViewById(R.id.tvviewQCMedt);
        tvCommodetailQCedtDoc = (TextView) findViewById(R.id.tv_commodetail_QCedtDoc);
        tvviewQCedtDoc = findViewById(R.id.tvviewQCedtDoc);
        tvCommodetailFctmdt = (TextView) findViewById(R.id.tv_commodetail_fctmdt);
        tvviewfctmdt = findViewById(R.id.tvviewfctmdt);
        tvCommodetailFctedt = (TextView) findViewById(R.id.tv_commodetail_fctedt);
        tvviewfctedt = findViewById(R.id.tvviewfctedt);
        tvCommodetailPackbdat = (TextView) findViewById(R.id.tv_commodetail_packbdat);
        tvviewpackbdat = findViewById(R.id.tvviewpackbdat);
        tvCommodetailPackqty2 = (TextView) findViewById(R.id.tv_commodetail_packqty2);
        tvviewpackqty2 = findViewById(R.id.tvviewpackqty2);
        tvCommodetailQCMemo = (TextView) findViewById(R.id.tv_commodetail_QCMemo);
        tvviewQCMemo = findViewById(R.id.tvviewQCMemo);
        tvCommodetailFactlcdat = (TextView) findViewById(R.id.tv_commodetail_factlcdat);
        tvviewfactlcdat = findViewById(R.id.tvviewfactlcdat);
        tvCommodetailBatchid = (TextView) findViewById(R.id.tv_commodetail_batchid);
        tvviewbatchid = findViewById(R.id.tvviewbatchid);
        tvCommodetailOurAfter = (TextView) findViewById(R.id.tv_commodetail_ourAfter);
        tvviewourAfter = findViewById(R.id.tvviewourAfter);
        tvCommodetailCtmchkdt = (TextView) findViewById(R.id.tv_commodetail_ctmchkdt);
        tvviewctmchkdt = findViewById(R.id.tvviewctmchkdt);
        tvCommodetailIPQCPedt = (TextView) findViewById(R.id.tv_commodetail_IPQCPedt);
        tvviewIPQCPedt = findViewById(R.id.tvviewIPQCPedt);
        tvCommodetailIPQC = (TextView) findViewById(R.id.tv_commodetail_IPQC);
        tvviewIPQC = findViewById(R.id.tvviewIPQC);
        tvCommodetailIPQCmdt = (TextView) findViewById(R.id.tv_commodetail_IPQCmdt);
        tvviewIPQCmdt = findViewById(R.id.tvviewIPQCmdt);
        tvCommodetailQAname = (TextView) findViewById(R.id.tv_commodetail_QAname);
        tvviewQAname = findViewById(R.id.tvviewQAname);
        tvCommodetailQAScore = (TextView) findViewById(R.id.tv_commodetail_QAScore);
        tvviewQAScore = findViewById(R.id.tvviewQAScore);
        tvCommodetailQAMemo = (TextView) findViewById(R.id.tv_commodetail_QAMemo);
        tvviewQAMemo = findViewById(R.id.tvviewQAMemo);
        tvCommodetailChker = (TextView) findViewById(R.id.tv_commodetail_chker);
        tvviewchker = findViewById(R.id.tvviewchker);
        tvCommodetailChkpdt = (TextView) findViewById(R.id.tv_commodetail_chkpdt);
        tvviewchkpdt = findViewById(R.id.tvviewchkpdt);
        tvCommodetailChkfctdt = (TextView) findViewById(R.id.tv_commodetail_chkfctdt);
        tvviewchkfctdt = findViewById(R.id.tvviewchkfctdt);
        tvCommodetailChkplace = (TextView) findViewById(R.id.tv_commodetail_chkplace);
        tvviewchkplace = findViewById(R.id.tvviewchkplace);

        Util.setEditTextInhibitInputSpeChat(etSqlDetail);
        getClumnsSpinner();
        setColumnRight();
    }

    /*填充查货跟踪单每页显示条目spinner数据*/
    private void getClumnsSpinner() {
        //将资源转化成string数组
        String[] spinner = getResources().getStringArray(R.array.clumnsCommon);
        //填充数据
        ArrayAdapter<String> adapterclumns = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner);
        adapterclumns.setDropDownViewResource(R.layout.dropdown_stytle);
        spinnCommoPageClumns.setAdapter(adapterclumns);//绑定数据到控件
        //列表点击事件选择每页显示的条目数
        spinnCommoPageClumns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().
                        getStringArray(R.array.clumnsCommon);
                spUtils.put(QACworkSearchActivity.this,
                        "clumnsspinner", languages[position]);//将选择的条目数保存到存储中
                setData();//查询
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*控件操作*/
    private void initView() {
        tvProTitle.setText("查货跟踪表");
        spinnermenu.setBackground(getResources().getDrawable(R.mipmap.spin_filp));
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

    /*实例化点击事件*/
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);
        spinnermenu.setOnClickListener(this);
        ivUpLeftPage.setOnClickListener(this);
        ivDownRightPage.setOnClickListener(this);
    }

    /*查询按钮弹出框*/
    private void ShowDialog(View view) {
        QACworkSearchDialog = new QACworkSearchDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        QACworkSearchDialog.show();
        QACworkSearchDialog.setCancelable(false);
    }

    /*确认*/
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
                    sethideSoft(v);
                    String etsql2 = etSqlDetail.getText().toString();
                    if (etsql2.equals("")) {
                        ToastUtils.ShowToastMessage("页码不能为空", QACworkSearchActivity.this);
                    } else {
                        setPageDetail();
                    }
                    QACworkSearchDialog.dismiss();
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
                    QACworkSearchDialog.dismiss();
                    break;
            }
        }
    };

    /*接收条件弹窗传过来的数值*/
    private void setSPUtils() {
        sp = getSharedPreferences("my_sp", 0);
        Prddocumentary = sp.getString("QACworkDialogPrddocumentary", "");//跟单
        QACworkDialogItem = sp.getString("QACworkDialogItem", "");//款号
        pagesize = sp.getString("clumnsspinner", "");//每页显示的条目数
        QACworkDialogIPQC = sp.getString("QACworkDialogIPQC", "");//巡检
        QACworkDialogprdmaster = sp.getString("QACworkDialogprdmaster",
                "");//生产主管
        QACworkCheckedd = sp.getString("QACworkCheckedd", "");//是否可为空
    }

    /*初始化查询数据*/
    private void setData() {
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        setSPUtils();
        //默认显示为10条
        if (pagesize.equals("")) {
            pagesize = String.valueOf(10);
        }
        boolean stris = Boolean.parseBoolean(QACworkCheckedd);//转换成boolean类型
        Gson gson = new Gson();
        //将获取到的数据放入bean中
        QACworkSearchBean postBean = new QACworkSearchBean();
        QACworkSearchBean.Conditions conditions = postBean.new Conditions();
        conditions.setItem(QACworkDialogItem);//填充款号
        conditions.setPrddocumentary(Prddocumentary);//填充跟单
        conditions.setPrdmaster(QACworkDialogprdmaster);//填充生产主管
        conditions.setIPQC(QACworkDialogIPQC);//填充巡检
        conditions.setPrdmasterisnull(stris);//填充生产主管是否可空
        postBean.setConditions(conditions);//填充父节点
        postBean.setPageNum(0);//起始页
        postBean.setPageSize(Integer.parseInt(pagesize));
        //将bean中的数据转成json数据
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            final int finalGetsize = Integer.parseInt(pagesize);
            ResponseDialog.showLoading(this, "正在查询");
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
                                String ress = response.replace("\\", "");
                                String ression = StringUtil.sideTrim(ress, "\"");
                                QACworkPageDataBean = new Gson().fromJson(ression, QACworkPageDataBean.class);
                                dataBeen = QACworkPageDataBean.getData();
                                //判断得到的数据是否为空,决定要显示的页面
                                if (QACworkPageDataBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    pageCount = QACworkPageDataBean.getTotalCount();
                                    tvSignPage.setText(String.valueOf(pageCount / finalGetsize + 1));
                                    leftAdapter = new QACworkSearchLeftAdapter(
                                            QACworkSearchActivity.this, dataBeen);
                                    lv_cleft.setAdapter(leftAdapter);
                                    sqlAdapter = new QACworkSearchAdapter(QACworkSearchActivity.this, dataBeen,
                                            jsonTextBeanlist);
                                    lv_data.setAdapter(sqlAdapter);//绑定数据源
                                    SetListener setListener = new SetListener();
                                    setListener.setQACworkDetailLister(
                                            QACworkSearchActivity.this,
                                            sqlAdapter, dataBeen);//回调进入详情修改页面
                                } else {
                                    ll_visibi.setVisibility(View.VISIBLE);
                                    scroll_content.setVisibility(View.GONE);
                                    tv_visibi.setText("没有更多数据");
                                }
                                ResponseDialog.closeLoading();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", QACworkSearchActivity.this);
        }
    }

    /*指定页码查询数据*/
    private void setPageDetail() {
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        setSPUtils();
        if (Prddocumentary.contains("\n")) {
            Prddocumentary = "";
        }
        if (QACworkDialogItem.contains("\n")) {
            QACworkDialogItem = "";
        }
        if (QACworkDialogIPQC.contains("\n")) {
            QACworkDialogIPQC = "";
        }
        if (QACworkDialogprdmaster.contains("\n")) {
            QACworkDialogprdmaster = "";
        }
        boolean stris = Boolean.parseBoolean(QACworkCheckedd);
        pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
        int Index = pageIndex - 1;
        Gson gson = new Gson();
        QACworkSearchBean postBean = new QACworkSearchBean();
        QACworkSearchBean.Conditions conditions = postBean.new Conditions();
        conditions.setItem(QACworkDialogItem);
        conditions.setPrddocumentary(Prddocumentary);
        conditions.setPrdmaster(QACworkDialogprdmaster);
        conditions.setIPQC(QACworkDialogIPQC);
        conditions.setPrdmasterisnull(stris);
        postBean.setConditions(conditions);
        postBean.setPageNum(Index);
        postBean.setPageSize(Integer.parseInt(pagesize));
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this, "正在查询");
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
                                String ress = response.replace("\\", "");
                                String ression = StringUtil.sideTrim(ress, "\"");
                                QACworkPageDataBean = new Gson().fromJson(ression, QACworkPageDataBean.class);
                                dataBeen = QACworkPageDataBean.getData();
                                if (QACworkPageDataBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    pageCount = QACworkPageDataBean.getTotalCount();
                                    tvSignPage.setText(String.valueOf(pageCount / finalGetsize + 1));//总页数
                                    sqlAdapter = new QACworkSearchAdapter(QACworkSearchActivity.this, dataBeen
                                            , jsonTextBeanlist);
                                    lv_data.setAdapter(sqlAdapter);//绑定数据源
                                    leftAdapter = new QACworkSearchLeftAdapter(
                                            QACworkSearchActivity.this, dataBeen);
                                    lv_cleft.setAdapter(leftAdapter);
                                    SetListener setListener = new SetListener();
                                    setListener.setQACworkDetailLister(
                                            QACworkSearchActivity.this,
                                            sqlAdapter, dataBeen);//回调进入详情修改页面
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
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", QACworkSearchActivity.this);
        }
    }

    /*上一页下一页*/
    private void setPageDate(String pageIndexin) {
        ResponseDialog.showLoading(this);
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        setSPUtils();
        boolean stris = Boolean.parseBoolean(QACworkCheckedd);
        Gson gson = new Gson();
        QACworkSearchBean postBean = new QACworkSearchBean();
        QACworkSearchBean.Conditions conditions = postBean.new Conditions();
        conditions.setItem(QACworkDialogItem);
        conditions.setPrddocumentary(Prddocumentary);
        conditions.setPrdmaster(QACworkDialogprdmaster);
        conditions.setIPQC(QACworkDialogIPQC);
        conditions.setPrdmasterisnull(stris);
        postBean.setConditions(conditions);
        postBean.setPageNum(Integer.parseInt(pageIndexin));
        postBean.setPageSize(Integer.parseInt(pagesize));
        String stringpost = gson.toJson(postBean);
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this, "正在查询");
            final int finalGetsize = Integer.parseInt(pagesize);
            OkHttpUtils.postString()//okhttputil
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
                                String ress = response.replace("\\", "");
                                String ression = StringUtil.sideTrim(ress, "\"");
                                QACworkPageDataBean = new Gson().fromJson(ression, QACworkPageDataBean.class);
                                dataBeen = QACworkPageDataBean.getData();
                                if (QACworkPageDataBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    pageCount = QACworkPageDataBean.getTotalCount();
                                    tvSignPage.setText(String.valueOf(pageCount / finalGetsize + 1));
                                    sqlAdapter = new QACworkSearchAdapter(QACworkSearchActivity.this,
                                            dataBeen, jsonTextBeanlist);
                                    lv_data.setAdapter(sqlAdapter);//绑定数据源
                                    leftAdapter = new QACworkSearchLeftAdapter(
                                            QACworkSearchActivity.this, dataBeen);
                                    lv_cleft.setAdapter(leftAdapter);
                                    SetListener setListener = new SetListener();
                                    setListener.setQACworkDetailLister(
                                            QACworkSearchActivity.this,
                                            sqlAdapter, dataBeen);//回调进入详情修改页面
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
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", QACworkSearchActivity.this);
        }
    }

    /*分配列权限*/
    private void setColumnRight() {
        sp = getSharedPreferences("my_sp", 0);
        String commologinid = sp.getString("commologinid", "");
        String args = "pd_saleslist,查货跟踪表," + commologinid;
        String idcolum = HttpUrl.debugoneUrl + "Common/GetClumns/?id=" + args;
        OkHttpUtils.get()
                .url(idcolum)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String ress = response.replace("\\", "");
                        ress = ress.replace("\"[{", "[{");
                        ress = ress.replace("}]\"", "}]");
                        QACworkRightsTableBean = new Gson().
                                fromJson(ress, QACworkRightsTableBean.class);
                        if (QACworkRightsTableBean.getJsonText() != null) {
                            jsonTextBeanlist = QACworkRightsTableBean.getJsonText();
                        }
                        String jsontext = String.valueOf(QACworkRightsTableBean.getJsonText());
                        //判断如果为null，则默认显示全部的数据
                        //如果不是null，则循环判断其中字段是否是修改或者是查看
                        if (jsontext.equals("null")) {
                            for (int i = 0; i < columnlist.size(); i++) {
                                String sfil = ("tv_commodetail_" + columnlist.get(i));
                                String tvview = ("tvview" + columnlist.get(i));
                                try {
                                    Field field = R.id.class.getField(sfil);
                                    int idd = field.getInt(new R.id());
                                    View view = findViewById(idd);
                                    view.setVisibility(View.VISIBLE);

                                    Field fieldtvview = R.id.class.getField(tvview);
                                    int iddtvview = fieldtvview.getInt(new R.id());
                                    View viewtvview = findViewById(iddtvview);
                                    viewtvview.setVisibility(View.VISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            for (int i = 0; i < jsonTextBeanlist.size(); i++) {
                                int pid = Integer.parseInt(jsonTextBeanlist.get(i).getPId());
                                if (pid > 0 && jsonTextBeanlist.get(i).isChecked() == true) {
                                    for (int j = 0; j < columnlist.size(); j++) {
                                        String columstr = columnlist.get(j);
                                        String columnname = jsonTextBeanlist.get(i).getColumnName();
                                        if (columstr == columnname || columstr.equals(columnname)) {
                                            if (jsonTextBeanlist.get(i).getName().equals("修改")) {
                                                String sfil = ("tv_commodetail_" + jsonTextBeanlist.get(i).getColumnName());
                                                String tvview = ("tvview" + jsonTextBeanlist.get(i).getColumnName());
                                                try {
                                                    Field field = R.id.class.getField(sfil);
                                                    int idd = field.getInt(new R.id());
                                                    View view = findViewById(idd);
                                                    view.setVisibility(View.VISIBLE);

                                                    Field fieldtvview = R.id.class.getField(tvview);
                                                    int iddtvview = fieldtvview.getInt(new R.id());
                                                    View viewtvview = findViewById(iddtvview);
                                                    viewtvview.setVisibility(View.VISIBLE);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            } else if (jsonTextBeanlist.get(i).getName().equals("查看")) {
                                                String sfil = ("tv_commodetail_" + jsonTextBeanlist.get(i).getColumnName());
                                                String tvview = ("tvview" + jsonTextBeanlist.get(i).getColumnName());
                                                try {
                                                    Field field = R.id.class.getField(sfil);
                                                    int idd = field.getInt(new R.id());
                                                    View view = findViewById(idd);
                                                    view.setVisibility(View.VISIBLE);

                                                    Field fieldtvview = R.id.class.getField(tvview);
                                                    int iddtvview = fieldtvview.getInt(new R.id());
                                                    View viewtvview = findViewById(iddtvview);
                                                    viewtvview.setVisibility(View.VISIBLE);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                String sfil = ("tv_commodetail_" + jsonTextBeanlist.get(i).getColumnName());
                                                String tvview = ("tvview" + jsonTextBeanlist.get(i).getColumnName());
                                                try {
                                                    Field field = R.id.class.getField(sfil);
                                                    int idd = field.getInt(new R.id());
                                                    View view = findViewById(idd);
                                                    view.setVisibility(View.GONE);

                                                    Field fieldtvview = R.id.class.getField(tvview);
                                                    int iddtvview = fieldtvview.getInt(new R.id());
                                                    View viewtvview = findViewById(iddtvview);
                                                    viewtvview.setVisibility(View.GONE);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            break;
                                        }
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                });
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

    /*启动*/
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回按钮*/
            case R.id.ivProductionBack:
                finish();
                break;
            /*查询*/
            case R.id.ivSearch:
                sethideSoft(v);
                ShowDialog(v);
                break;
            /*翻页确认按钮*/
            case R.id.btnSignPage:
                sethideSoft(v);
                String txt = etSqlDetail.getText().toString();
                String txtcount = tvSignPage.getText().toString();
                if (txt.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", QACworkSearchActivity.this);
                } else {
                    int txtindex = Integer.parseInt(txt);
                    int txtcountindex = Integer.parseInt(txtcount);
                    if (txtindex > txtcountindex) {
                        ToastUtils.ShowToastMessage("已经是最后一页", QACworkSearchActivity.this);
                    } else if (txtindex < 1) {
                        ToastUtils.ShowToastMessage("已经是第一页", QACworkSearchActivity.this);
                    } else if (txt.length() == 0) {
                        ToastUtils.ShowToastMessage("页码不能为空", QACworkSearchActivity.this);
                    } else if (txt.length() > txtcount.length()) {
                        ToastUtils.ShowToastMessage("页码超出输入范围", QACworkSearchActivity.this);
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
                sethideSoft(v);
                String stredit = etSqlDetail.getText().toString();
                if (stredit.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", QACworkSearchActivity.this);
                } else {
                    int pageindex = Integer.parseInt(stredit);
                    int index = pageindex - 2;
                    if (index < 0) {
                        ToastUtils.ShowToastMessage("已经是第一页", QACworkSearchActivity.this);
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
                sethideSoft(v);
                String stredit2 = etSqlDetail.getText().toString();
                if (stredit2.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", QACworkSearchActivity.this);
                } else {
                    int pageIndexx = Integer.parseInt(stredit2);
                    int index2 = pageIndexx;
                    String maxpageindex = tvSignPage.getText().toString();
                    int indexmax = Integer.parseInt(maxpageindex);
                    int index3 = index2 + 1;
                    if (index3 > indexmax) {
                        ToastUtils.ShowToastMessage("已经是最后一页", QACworkSearchActivity.this);
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

    /*退出界面后删除轻量级存储my_sp中的数据*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return false;
    }
}