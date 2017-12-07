package com.daoran.newfactory.onefactory.activity.work.qacwork;

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
import com.daoran.newfactory.onefactory.adapter.qacworkadapter.QACworkSearchAdapter;
import com.daoran.newfactory.onefactory.adapter.qacworkadapter.QACworkSearchLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkRightsTableBean;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkSearchBean;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkPageDataBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 查货跟踪单
 * Created by lizhipeng on 2017/3/29.
 */
public class QACworkSearchActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private NoscrollListView mData;//列表
    private NoscrollListView lv_cleft;//左侧款号列表
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack;//返回按钮
    private QACworkSearchDialog QACworkSearchDialog;//查货条件查询弹出框
    private ImageView ivSearch;//条件查询

    private List<QACworkPageDataBean.DataBean> dataBeen
            = new ArrayList<QACworkPageDataBean.DataBean>();//查货信息实体集合
    private QACworkPageDataBean QACworkPageDataBean;//列表实体bean
    private QACworkSearchLeftAdapter leftAdapter;//左侧编号列表适配
    private QACworkSearchAdapter sqlAdapter;//列表适配

    private List<QACworkRightsTableBean.JsonTextBean> jsonTextBeanlist =
            new ArrayList<QACworkRightsTableBean.JsonTextBean>();
    private QACworkRightsTableBean QACworkRightsTableBean;

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
    public static QACworkSearchActivity QACworkinstance = null;

    private String[] columns = new String[]{"ID", "subfactory", "item", "sealedrev",
            "docback", "predt", "lcdat", "sewFdt", "sewMdt", "taskqty",
            "cutqty", "preMemo", "predoc", "fabricsok", "accessoriesok",
            "spcproDec", "spcproMemo", "QCbdt", "QCmdt", "QCMedt", "QCbdtDoc",
            "QCmdtDoc", "QCedtDoc", "fctmdt", "fctedt", "prddocumentary",
            "packbdat", "packqty2", "QCMemo", "factlcdat", "ourAfter", "prdmaster",
            "QCMasterScore", "batchid", "QAname", "QAScore", "QAMemo", "ctmtxt",
            "ctmchkdt", "IPQCmdt", "IPQCPedt", "predocdt", "prebdt", "premdt",
            "preedt"};
    private List<String> columnlist = Arrays.asList(columns);

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

        tvCommodetailCtmtxt = (TextView) findViewById(R.id.tv_commodetail_ctmtxt);
        tvviewctmtxt = (View) findViewById(R.id.tvviewctmtxt);
        tvCommodetailPrddocumentary = (TextView) findViewById(R.id.tv_commodetail_prddocumentary);
        tvviewprddocumentary = (View) findViewById(R.id.tvviewprddocumentary);
        tvCommodetailPrdmaster = (TextView) findViewById(R.id.tv_commodetail_prdmaster);
        tvviewprdmaster = (View) findViewById(R.id.tvviewprdmaster);
        tvCommodetailQCMasterScore = (TextView) findViewById(R.id.tv_commodetail_QCMasterScore);
        tvviewQCMasterScore = (View) findViewById(R.id.tvviewQCMasterScore);
        tvCommodetailSealedrev = (TextView) findViewById(R.id.tv_commodetail_sealedrev);
        tvviewsealedrev = (View) findViewById(R.id.tvviewsealedrev);
        tvCommodetailDocback = (TextView) findViewById(R.id.tv_commodetail_docback);
        tvviewdocback = (View) findViewById(R.id.tvviewdocback);
        tvCommodetailLcdat = (TextView) findViewById(R.id.tv_commodetail_lcdat);
        tvviewlcdat = (View) findViewById(R.id.tvviewlcdat);
        tvCommodetailCount = (TextView) findViewById(R.id.tv_commodetail_count);
        tvviewtaskqty = (View) findViewById(R.id.tvviewtaskqty);
        tvCommodetailPreMemo = (TextView) findViewById(R.id.tv_commodetail_preMemo);
        tvviewpreMemo = (View) findViewById(R.id.tvviewpreMemo);
        tvCommodetailPredocdt = (TextView) findViewById(R.id.tv_commodetail_predocdt);
        tvviewpredocdt = (View) findViewById(R.id.tvviewpredocdt);
        tvCommodetailPredt = (TextView) findViewById(R.id.tv_commodetail_predt);
        tvviewpredt = (View) findViewById(R.id.tvviewpredt);
        tvCommodetailPredoc = (TextView) findViewById(R.id.tv_commodetail_predoc);
        tvviewpredoc = (View) findViewById(R.id.tvviewpredoc);
        tvCommodetailFabricsok = (TextView) findViewById(R.id.tv_commodetail_fabricsok);
        tvviewfabricsok = (View) findViewById(R.id.tvviewfabricsok);
        tvCommodetailAccessoriesok = (TextView) findViewById(R.id.tv_commodetail_accessoriesok);
        tvviewaccessoriesok = (View) findViewById(R.id.tvviewaccessoriesok);
        tvCommodetailSpcproDec = (TextView) findViewById(R.id.tv_commodetail_spcproDec);
        tvviewspcproDec = (View) findViewById(R.id.tvviewspcproDec);
        tvCommodetailSpcproMemo = (TextView) findViewById(R.id.tv_commodetail_spcproMemo);
        tvviewspcproMemo = (View) findViewById(R.id.tvviewspcproMemo);
        tvCommodetailCutqty = (TextView) findViewById(R.id.tv_commodetail_cutqty);
        tvviewcutqty = (View) findViewById(R.id.tvviewcutqty);
        tvCommodetailSewFdt = (TextView) findViewById(R.id.tv_commodetail_sewFdt);
        tvviewsewFdt = (View) findViewById(R.id.tvviewsewFdt);
        tvCommodetailSewMdt = (TextView) findViewById(R.id.tv_commodetail_sewMdt);
        tvviewsewMdt = (View) findViewById(R.id.tvviewsewMdt);
        tvCommodetailSubfactory = (TextView) findViewById(R.id.tv_commodetail_subfactory);
        tvviewsubfactory = (View) findViewById(R.id.tvviewsubfactory);
        tvCommodetailPrebdt = (TextView) findViewById(R.id.tv_commodetail_prebdt);
        tvviewprebdt = (View) findViewById(R.id.tvviewprebdt);
        tvCommodetailQCbdt = (TextView) findViewById(R.id.tv_commodetail_QCbdt);
        tvviewQCbdt = (View) findViewById(R.id.tvviewQCbdt);
        llPPSDetailTxtQCbdtDoc = (TextView) findViewById(R.id.ll_PPSDetail_txt_QCbdtDoc);
        tvviewQCbdtDoc = (View) findViewById(R.id.tvviewQCbdtDoc);
        tvCommodetailPremdt = (TextView) findViewById(R.id.tv_commodetail_premdt);
        tvviewpremdt = (View) findViewById(R.id.tvviewpremdt);
        tvCommodetailQCmdt = (TextView) findViewById(R.id.tv_commodetail_QCmdt);
        tvviewQCmdt = (View) findViewById(R.id.tvviewQCmdt);
        tvCommodetailQCmdtDoc = (TextView) findViewById(R.id.tv_commodetail_QCmdtDoc);
        tvviewQCmdtDoc = (View) findViewById(R.id.tvviewQCmdtDoc);
        tvCommodetailPreedt = (TextView) findViewById(R.id.tv_commodetail_preedt);
        tvviewpreedt = (View) findViewById(R.id.tvviewpreedt);
        tvCommodetailQCMedt = (TextView) findViewById(R.id.tv_commodetail_QCMedt);
        tvviewQCMedt = (View) findViewById(R.id.tvviewQCMedt);
        tvCommodetailQCedtDoc = (TextView) findViewById(R.id.tv_commodetail_QCedtDoc);
        tvviewQCedtDoc = (View) findViewById(R.id.tvviewQCedtDoc);
        tvCommodetailFctmdt = (TextView) findViewById(R.id.tv_commodetail_fctmdt);
        tvviewfctmdt = (View) findViewById(R.id.tvviewfctmdt);
        tvCommodetailFctedt = (TextView) findViewById(R.id.tv_commodetail_fctedt);
        tvviewfctedt = (View) findViewById(R.id.tvviewfctedt);
        tvCommodetailPackbdat = (TextView) findViewById(R.id.tv_commodetail_packbdat);
        tvviewpackbdat = (View) findViewById(R.id.tvviewpackbdat);
        tvCommodetailPackqty2 = (TextView) findViewById(R.id.tv_commodetail_packqty2);
        tvviewpackqty2 = (View) findViewById(R.id.tvviewpackqty2);
        tvCommodetailQCMemo = (TextView) findViewById(R.id.tv_commodetail_QCMemo);
        tvviewQCMemo = (View) findViewById(R.id.tvviewQCMemo);
        tvCommodetailFactlcdat = (TextView) findViewById(R.id.tv_commodetail_factlcdat);
        tvviewfactlcdat = (View) findViewById(R.id.tvviewfactlcdat);
        tvCommodetailBatchid = (TextView) findViewById(R.id.tv_commodetail_batchid);
        tvviewbatchid = (View) findViewById(R.id.tvviewbatchid);
        tvCommodetailOurAfter = (TextView) findViewById(R.id.tv_commodetail_ourAfter);
        tvviewourAfter = (View) findViewById(R.id.tvviewourAfter);
        tvCommodetailCtmchkdt = (TextView) findViewById(R.id.tv_commodetail_ctmchkdt);
        tvviewctmchkdt = (View) findViewById(R.id.tvviewctmchkdt);
        tvCommodetailIPQCPedt = (TextView) findViewById(R.id.tv_commodetail_IPQCPedt);
        tvviewIPQCPedt = (View) findViewById(R.id.tvviewIPQCPedt);
        tvCommodetailIPQC = (TextView) findViewById(R.id.tv_commodetail_IPQC);
        tvviewIPQC = (View) findViewById(R.id.tvviewIPQC);
        tvCommodetailIPQCmdt = (TextView) findViewById(R.id.tv_commodetail_IPQCmdt);
        tvviewIPQCmdt = (View) findViewById(R.id.tvviewIPQCmdt);
        tvCommodetailQAname = (TextView) findViewById(R.id.tv_commodetail_QAname);
        tvviewQAname = (View) findViewById(R.id.tvviewQAname);
        tvCommodetailQAScore = (TextView) findViewById(R.id.tv_commodetail_QAScore);
        tvviewQAScore = (View) findViewById(R.id.tvviewQAScore);
        tvCommodetailQAMemo = (TextView) findViewById(R.id.tv_commodetail_QAMemo);
        tvviewQAMemo = (View) findViewById(R.id.tvviewQAMemo);
        tvCommodetailChker = (TextView) findViewById(R.id.tv_commodetail_chker);
        tvviewchker = (View) findViewById(R.id.tvviewchker);
        tvCommodetailChkpdt = (TextView) findViewById(R.id.tv_commodetail_chkpdt);
        tvviewchkpdt = (View) findViewById(R.id.tvviewchkpdt);
        tvCommodetailChkfctdt = (TextView) findViewById(R.id.tv_commodetail_chkfctdt);
        tvviewchkfctdt = (View) findViewById(R.id.tvviewchkfctdt);
        tvCommodetailChkplace = (TextView) findViewById(R.id.tv_commodetail_chkplace);
        tvviewchkplace = (View) findViewById(R.id.tvviewchkplace);

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
        spinnCommoPageClumns.setAdapter(adapterclumns);
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

    /*查询按钮弹出框*/
    private void ShowDialog(View view) {
        QACworkSearchDialog = new QACworkSearchDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        QACworkSearchDialog.show();
    }

    /*确认*/
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
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

    /*初始化查询数据*/
    private void setData() {
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        sp = getSharedPreferences("my_sp", 0);
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
        QACworkSearchBean postBean = new QACworkSearchBean();
        QACworkSearchBean.Conditions conditions = postBean.new Conditions();
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
                                System.out.print(response);
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                QACworkPageDataBean = new Gson().fromJson(ression, QACworkPageDataBean.class);
                                dataBeen = QACworkPageDataBean.getData();
                                //判断得到的数据是否为空,决定要显示的页面
                                if (QACworkPageDataBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    System.out.print(dataBeen);
                                    pageCount = QACworkPageDataBean.getTotalCount();
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    tvSignPage.setText(count);
                                    sqlAdapter = new QACworkSearchAdapter(QACworkSearchActivity.this, dataBeen,
                                            jsonTextBeanlist);
                                    mData.setAdapter(sqlAdapter);
                                    leftAdapter = new QACworkSearchLeftAdapter(QACworkSearchActivity.this, dataBeen);
                                    lv_cleft.setAdapter(leftAdapter);
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
        sp = QACworkSearchActivity.this.getSharedPreferences("my_sp", 0);
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
        QACworkSearchBean postBean = new QACworkSearchBean();
        QACworkSearchBean.Conditions conditions = postBean.new Conditions();
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
                                System.out.print(response);
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                QACworkPageDataBean = new Gson().fromJson(ression, QACworkPageDataBean.class);
                                dataBeen = QACworkPageDataBean.getData();
                                if (QACworkPageDataBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    System.out.print(dataBeen);
                                    pageCount = QACworkPageDataBean.getTotalCount();
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    tvSignPage.setText(count);
                                    sqlAdapter = new QACworkSearchAdapter(QACworkSearchActivity.this, dataBeen
                                            , jsonTextBeanlist);
                                    mData.setAdapter(sqlAdapter);
                                    leftAdapter = new QACworkSearchLeftAdapter(QACworkSearchActivity.this, dataBeen);
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
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", QACworkSearchActivity.this);
        }
    }

    /*上一页下一页*/
    private void setPageDate(String pageIndexin) {
        ResponseDialog.showLoading(this);
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        sp = QACworkSearchActivity.this.getSharedPreferences("my_sp", 0);
        String recodename = sp.getString("commoname", "");//跟单
        String Style = sp.getString("commoStyle", "");//款号
        String pagesize = sp.getString("clumnsspinner", "");
        String Recode = sp.getString("commoRecode", "");//巡检
        String etprodialogProcedure = sp.getString("etproProcedure", "");//生产主管
        String ischeck = sp.getString("ischeckedd", "");//是否可为空
        boolean stris = Boolean.parseBoolean(ischeck);
        Gson gson = new Gson();
        QACworkSearchBean postBean = new QACworkSearchBean();
        QACworkSearchBean.Conditions conditions = postBean.new Conditions();
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
                                QACworkPageDataBean = new Gson().fromJson(ression, QACworkPageDataBean.class);
                                dataBeen = QACworkPageDataBean.getData();
                                if (QACworkPageDataBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    System.out.print(dataBeen);
                                    pageCount = QACworkPageDataBean.getTotalCount();
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    tvSignPage.setText(count);
                                    sqlAdapter = new QACworkSearchAdapter(QACworkSearchActivity.this,
                                            dataBeen, jsonTextBeanlist);
                                    mData.setAdapter(sqlAdapter);
                                    leftAdapter = new QACworkSearchLeftAdapter(QACworkSearchActivity.this, dataBeen);
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
                        System.out.println(response);
                        String ress = response.replace("\\", "");
                        ress = ress.replace("\"[{", "[{");
                        ress = ress.replace("}]\"", "}]");
                        QACworkRightsTableBean = new Gson().
                                fromJson(ress, QACworkRightsTableBean.class);
                        if (QACworkRightsTableBean.getJsonText() != null) {
                            jsonTextBeanlist = QACworkRightsTableBean.getJsonText();
                        }
                        System.out.println(jsonTextBeanlist);
                        String jsontext = String.valueOf(QACworkRightsTableBean.getJsonText());
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

    /*启动*/
    @Override
    protected void onStart() {
        super.onStart();
    }

    /*退出界面后删除轻量级存储my_sp中的数据*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*禁止EditText输入特殊字符*/
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