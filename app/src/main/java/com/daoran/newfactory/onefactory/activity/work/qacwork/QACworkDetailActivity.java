package com.daoran.newfactory.onefactory.activity.work.qacwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkRightsTableBean;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkDetailSaveBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 查货跟踪详情
 * Created by lizhipeng on 2017/8/30.
 */
public class QACworkDetailActivity extends Activity
        implements View.OnClickListener {
    private static final String TAG = "QACworkDetailActivity";
    private SPUtils spUtils;
    private SharedPreferences sp;

    private List<QACworkRightsTableBean.JsonTextBean> jsonTextBeanlist =
            new ArrayList<QACworkRightsTableBean.JsonTextBean>();
    private QACworkRightsTableBean QACworkRightsTableBean;

    private AlertDialog noticeDialog;//退出当前页提示弹窗
    private ImageView ivCommoditySql;//返回按钮
    private Button btnCommoSave;//保存按钮

    private TextView tv_commodetail_date, tv_commodetail_ctmtxt, tv_commodetail_factory,
            tv_commodetail_count, tv_commodetail_prddocumentary, tv_commodetail_prdmaster,
            tv_commodetail_sealedrev, tv_commodetail_docback, tv_commodetail_lcdat,
            tv_commodetail_predocdt, tv_commodetail_predt, tv_commodetail_sewFdt,
            tv_commodetail_sewMdt, tv_commodetail_prebdt, tv_commodetail_premdt,
            tv_commodetail_preedt, tv_commodetail_fctmdt, tv_commodetail_fctedt,
            tv_commodetail_packbdat, tv_commodetail_factlcdat, tv_commodetail_ourAfter,
            tv_commodetail_ctmchkdt, tv_commodetail_IPQCPedt, tv_commodetail_IPQC,
            tv_commodetail_IPQCmdt,
            tv_commodetail_QAMemo, tv_commodetail_chkpdt, tv_commodetail_chkfctdt;

    private EditText tv_commodetail_QCMasterScore, tv_commodetail_preMemo,
            tv_commodetail_predoc, tv_commodetail_fabricsok, tv_commodetail_accessoriesok,
            tv_commodetail_spcproDec, tv_commodetail_spcproMemo, tv_commodetail_cutqty,
            tv_commodetail_QCbdt, tv_commodetail_QCbdtDoc, tv_commodetail_QCmdt,
            tv_commodetail_QCmdtDoc, tv_commodetail_QCMedt, tv_commodetail_QCedtDoc,
            tv_commodetail_packqty2, tv_commodetail_QCMemo, tv_commodetail_batchid,
            tv_commodetail_chker, tv_commodetail_QAname, tv_commodetail_QAScore,
            tv_commodetail_chkplace;

    private String commodetailPrddocumentary, commodetailprdmaster, nameid, commodetailOurAfter;
    private String[] columns = new String[]{"ID", "subfactory", "item", "sealedrev",
            "docback", "predt", "lcdat", "sewFdt", "sewMdt", "taskqty",
            "cutqty", "preMemo", "predoc", "fabricsok", "accessoriesok",
            "spcproDec", "spcproMemo", "QCbdt", "QCmdt", "QCMedt", "QCbdtDoc",
            "QCmdtDoc", "QCedtDoc", "fctmdt", "fctedt", "prddocumentary",
            "packbdat", "packqty2", "QCMemo", "factlcdat", "ourAfter", "prdmaster",
            "QCMasterScore", "batchid", "QAname", "QAScore", "QAMemo", "ctmtxt",
            "ctmchkdt", "IPQCmdt", "IPQCPedt", "predocdt", "prebdt", "premdt",
            "preedt"};
    private List<String> columnlist = Arrays.asList(columns);//将列名数组添加到集合中

    private LinearLayout ll_PPSDetail_txt_item, ll_PPSDetail_txt_ctmtxt, ll_PPSDetail_txt_subfactory,
            ll_PPSDetail_txt_prdmaster, ll_PPSDetail_txt_prddocumentary, ll_PPSDetail_txt_QCMasterScore,
            ll_PPSDetail_txt_sealedrev, ll_PPSDetail_txt_docback, ll_PPSDetail_txt_lcdat,
            ll_PPSDetail_txt_preMemo, ll_PPSDetail_txt_predocdt, ll_PPSDetail_txt_predt, ll_PPSDetail_txt_predoc,
            ll_PPSDetail_txt_fabricsok, ll_PPSDetail_txt_accessoriesok, ll_PPSDetail_txt_spcproDec,
            ll_PPSDetail_txt_spcproMemo, ll_PPSDetail_txt_cutqty, ll_PPSDetail_txt_sewFdt,
            ll_PPSDetail_txt_sewMdt, ll_PPSDetail_txt_prebdt, ll_PPSDetail_txt_QCbdt, ll_PPSDetail_txt_QCbdtDoc,
            ll_PPSDetail_txt_premdt, ll_PPSDetail_txt_QCmdt, ll_PPSDetail_txt_QCmdtDoc, ll_PPSDetail_txt_preedt,
            ll_PPSDetail_txt_QCMedt, ll_PPSDetail_txt_QCedtDoc, ll_PPSDetail_txt_fctmdt, ll_PPSDetail_txt_fctedt,
            ll_PPSDetail_txt_packbdat, ll_PPSDetail_txt_packqty2, ll_PPSDetail_txt_QCMemo,
            ll_PPSDetail_txt_factlcdat, ll_PPSDetail_txt_batchid, ll_PPSDetail_txt_ourAfter,
            ll_PPSDetail_txt_ctmchkdt, ll_PPSDetail_txt_IPQCPedt, ll_PPSDetail_txt_IPQC, ll_PPSDetail_txt_IPQCmdt,
            ll_PPSDetail_txt_QAname, ll_PPSDetail_txt_QAScore, ll_PPSDetail_txt_QAMemo, ll_PPSDetail_txt_chker,
            ll_PPSDetail_txt_chkpdt, ll_PPSDetail_txt_chkfctdt, ll_PPSDetail_txt_chkplace;

    private boolean flagblackBoolean;//是否修改过数据

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qacwork_detail);
        getViews();
        setColumRight();
        setViews();
        setListener();
    }

    private void getViews() {
        ivCommoditySql = (ImageView) findViewById(R.id.ivCommoditySql);//返回按钮
        btnCommoSave = (Button) findViewById(R.id.btnCommoSave);//保存按钮
        tv_commodetail_date = (TextView) findViewById(R.id.tv_commodetail_date);//款号
        tv_commodetail_ctmtxt = (TextView) findViewById(R.id.tv_commodetail_ctmtxt);//客户
        tv_commodetail_factory = (TextView) findViewById(R.id.tv_commodetail_factory);//工厂
        tv_commodetail_count = (TextView) findViewById(R.id.tv_commodetail_count);//数量
        tv_commodetail_prddocumentary = (TextView) findViewById(R.id.tv_commodetail_prddocumentary);//跟单
        tv_commodetail_prdmaster = (TextView) findViewById(R.id.tv_commodetail_prdmaster);//生产主管
        tv_commodetail_QCMasterScore = (EditText) findViewById(R.id.tv_commodetail_QCMasterScore);//主管评分
        tv_commodetail_sealedrev = (TextView) findViewById(R.id.tv_commodetail_sealedrev);//封样资料接收时间
        tv_commodetail_docback = (TextView) findViewById(R.id.tv_commodetail_docback);//大货资料接收时间
        tv_commodetail_lcdat = (TextView) findViewById(R.id.tv_commodetail_lcdat);//出货时间
        tv_commodetail_preMemo = (EditText) findViewById(R.id.tv_commodetail_preMemo);//特别备注情况
        tv_commodetail_predocdt = (TextView) findViewById(R.id.tv_commodetail_predocdt);//预计产前会报告时间
        tv_commodetail_predt = (TextView) findViewById(R.id.tv_commodetail_predt);//开产前会时间
        tv_commodetail_predoc = (EditText) findViewById(R.id.tv_commodetail_predoc);//产前会报告
        tv_commodetail_fabricsok = (EditText) findViewById(R.id.tv_commodetail_fabricsok);//面料情况
        tv_commodetail_accessoriesok = (EditText) findViewById(R.id.tv_commodetail_accessoriesok);//辅料情况
        tv_commodetail_spcproDec = (EditText) findViewById(R.id.tv_commodetail_spcproDec);//特殊工艺情况
        tv_commodetail_spcproMemo = (EditText) findViewById(R.id.tv_commodetail_spcproMemo);//特殊工艺备注
        tv_commodetail_cutqty = (EditText) findViewById(R.id.tv_commodetail_cutqty);//实裁数
        tv_commodetail_sewFdt = (TextView) findViewById(R.id.tv_commodetail_sewFdt);//上线日期
        tv_commodetail_sewMdt = (TextView) findViewById(R.id.tv_commodetail_sewMdt);//下线日期
        tv_commodetail_prebdt = (TextView) findViewById(R.id.tv_commodetail_prebdt);//预计早期时间
        tv_commodetail_QCbdt = (EditText) findViewById(R.id.tv_commodetail_QCbdt);//自查早期时间
        tv_commodetail_QCbdtDoc = (EditText) findViewById(R.id.tv_commodetail_QCbdtDoc);//早期报告
        tv_commodetail_premdt = (TextView) findViewById(R.id.tv_commodetail_premdt);//预计中期时间
        tv_commodetail_QCmdt = (EditText) findViewById(R.id.tv_commodetail_QCmdt);//自查中期时间
        tv_commodetail_QCmdtDoc = (EditText) findViewById(R.id.tv_commodetail_QCmdtDoc);//中期报告
        tv_commodetail_preedt = (TextView) findViewById(R.id.tv_commodetail_preedt);//预计尾期时间
        tv_commodetail_QCMedt = (EditText) findViewById(R.id.tv_commodetail_QCMedt);//自查尾期时间
        tv_commodetail_QCedtDoc = (EditText) findViewById(R.id.tv_commodetail_QCedtDoc);//尾期报告
        tv_commodetail_fctmdt = (TextView) findViewById(R.id.tv_commodetail_fctmdt);//客查中期时间
        tv_commodetail_fctedt = (TextView) findViewById(R.id.tv_commodetail_fctedt);//客查尾期时间
        tv_commodetail_packbdat = (TextView) findViewById(R.id.tv_commodetail_packbdat);//成品包装开始日期
        tv_commodetail_packqty2 = (EditText) findViewById(R.id.tv_commodetail_packqty2);//装箱数量
        tv_commodetail_QCMemo = (EditText) findViewById(R.id.tv_commodetail_QCMemo);//QA特别备注
        tv_commodetail_factlcdat = (TextView) findViewById(R.id.tv_commodetail_factlcdat);//离厂日期
        tv_commodetail_batchid = (EditText) findViewById(R.id.tv_commodetail_batchid);//查货批次
        tv_commodetail_ourAfter = (TextView) findViewById(R.id.tv_commodetail_ourAfter);//后道
        tv_commodetail_ctmchkdt = (TextView) findViewById(R.id.tv_commodetail_ctmchkdt);//业务员确认客查日期
        tv_commodetail_IPQCPedt = (TextView) findViewById(R.id.tv_commodetail_IPQCPedt);//尾查预查
        tv_commodetail_IPQC = (TextView) findViewById(R.id.tv_commodetail_IPQC);//巡检
        tv_commodetail_IPQCmdt = (TextView) findViewById(R.id.tv_commodetail_IPQCmdt);//巡检中查
        tv_commodetail_QAname = (EditText) findViewById(R.id.tv_commodetail_QAname);//QA首扎
        tv_commodetail_QAScore = (EditText) findViewById(R.id.tv_commodetail_QAScore);//QA首扎件数
        tv_commodetail_QAMemo = (TextView) findViewById(R.id.tv_commodetail_QAMemo);//QA首扎日期
        tv_commodetail_chker = (EditText) findViewById(R.id.tv_commodetail_chker);//件查
        tv_commodetail_chkpdt = (TextView) findViewById(R.id.tv_commodetail_chkpdt);//预计件查时间
        tv_commodetail_chkfctdt = (TextView) findViewById(R.id.tv_commodetail_chkfctdt);//实际件查时间
        tv_commodetail_chkplace = (EditText) findViewById(R.id.tv_commodetail_chkplace);//查货地点(件查)

        ll_PPSDetail_txt_item = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_item);
        ll_PPSDetail_txt_ctmtxt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_ctmtxt);
        ll_PPSDetail_txt_subfactory = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_subfactory);
        ll_PPSDetail_txt_prdmaster = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_prdmaster);
        ll_PPSDetail_txt_prddocumentary = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_prddocumentary);
        ll_PPSDetail_txt_QCMasterScore = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QCMasterScore);
        ll_PPSDetail_txt_sealedrev = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_sealedrev);
        ll_PPSDetail_txt_docback = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_docback);
        ll_PPSDetail_txt_lcdat = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_lcdat);
        ll_PPSDetail_txt_preMemo = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_preMemo);
        ll_PPSDetail_txt_predocdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_predocdt);
        ll_PPSDetail_txt_predt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_predt);
        ll_PPSDetail_txt_predoc = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_predoc);
        ll_PPSDetail_txt_fabricsok = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_fabricsok);
        ll_PPSDetail_txt_accessoriesok = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_accessoriesok);
        ll_PPSDetail_txt_spcproDec = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_spcproDec);
        ll_PPSDetail_txt_spcproMemo = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_spcproMemo);
        ll_PPSDetail_txt_cutqty = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_cutqty);
        ll_PPSDetail_txt_sewFdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_sewFdt);
        ll_PPSDetail_txt_sewMdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_sewMdt);
        ll_PPSDetail_txt_prebdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_prebdt);
        ll_PPSDetail_txt_QCbdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QCbdt);
        ll_PPSDetail_txt_QCbdtDoc = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QCbdtDoc);
        ll_PPSDetail_txt_premdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_premdt);
        ll_PPSDetail_txt_QCmdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QCmdt);
        ll_PPSDetail_txt_QCmdtDoc = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QCmdtDoc);
        ll_PPSDetail_txt_preedt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_preedt);
        ll_PPSDetail_txt_QCMedt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QCMedt);
        ll_PPSDetail_txt_QCedtDoc = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QCedtDoc);
        ll_PPSDetail_txt_fctmdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_fctmdt);
        ll_PPSDetail_txt_fctedt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_fctedt);
        ll_PPSDetail_txt_packbdat = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_packbdat);
        ll_PPSDetail_txt_packqty2 = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_packqty2);
        ll_PPSDetail_txt_QCMemo = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QCMemo);
        ll_PPSDetail_txt_factlcdat = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_factlcdat);
        ll_PPSDetail_txt_batchid = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_batchid);
        ll_PPSDetail_txt_ourAfter = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_ourAfter);
        ll_PPSDetail_txt_ctmchkdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_ctmchkdt);
        ll_PPSDetail_txt_IPQCPedt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_IPQCPedt);
        ll_PPSDetail_txt_IPQC = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_IPQC);
        ll_PPSDetail_txt_IPQCmdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_IPQCmdt);
        ll_PPSDetail_txt_QAname = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QAname);
        ll_PPSDetail_txt_QAScore = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QAScore);
        ll_PPSDetail_txt_QAMemo = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_QAMemo);
        ll_PPSDetail_txt_chker = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_chker);
        ll_PPSDetail_txt_chkpdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_chkpdt);
        ll_PPSDetail_txt_chkfctdt = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_chkfctdt);
        ll_PPSDetail_txt_chkplace = (LinearLayout) findViewById(R.id.ll_PPSDetail_txt_chkplace);
    }

    private void setViews() {
        sp = getSharedPreferences("my_sp", 0);
        nameid = sp.getString("usernamerecoder", "");
        String commodetailproid = sp.getString("commodetailproid", "");//id
        String commoitem = sp.getString("commodetailitem", "");//款号
        String commodetailCtmtxt = sp.getString("commodetailCtmtxt", "");//客户
        commodetailPrddocumentary = sp.getString("commodetailPrddocumentary", "");//跟单
        String commodetailSubfactory = sp.getString("commodetailSubfactory", "");//工厂
        String commodetailTaskqty = sp.getString("commodetailTaskqty", "");//制单数量
        commodetailprdmaster = sp.getString("commodetailprdmaster", "");//生产主管
        String commodetailQCMasterScore = sp.getString("commodetailQCMasterScore", "");//主管评分
        String commodetailSealedrev = sp.getString("commodetailSealedrev", "");//封样资料接收时间
        String commodetailDocback = sp.getString("commodetailDocback", "");//大货资料接收时间
        String commodetailLcdat = sp.getString("commodetailLcdat", "");//出货时间
        String commodetailPreMemo = sp.getString("commodetailPreMemo", "");//特别备注情况
        String commodetailPredocdt = sp.getString("commodetailPredocdt", "");//预计产前会报告时间
        String commodetailPred = sp.getString("commodetailPred", "");//开产前会时间
        String commodetailPredoc = sp.getString("commodetailPredoc", "");//产前会报告
        String commodetailFabricsok = sp.getString("commodetailFabricsok", "");//大货面料情况
        String commodetailAccessoriesok = sp.getString("commodetailAccessoriesok", "");//大货辅料情况
        String commodetailSpcproDec = sp.getString("commodetailSpcproDec", "");//特殊工艺情况
        String commodetailSpcproMemo = sp.getString("commodetailSpcproMemo", "");//特殊工艺备注
        String commodetailCutqty = sp.getString("commodetailCutqty", "");//实裁数
        String commodetailSewFdt = sp.getString("commodetailSewFdt", "");//上线日期
        String commodetailSewMdt = sp.getString("commodetailSewMdt", "");//下线日期
        String commodetailPrebdt = sp.getString("commodetailPrebdt", "");//预计早期时间
        String commodetailQCbdt = sp.getString("commodetailQCbdt", "");//自查早期时间
        String commodetailQCbdtDoc = sp.getString("commodetailQCbdtDoc", "");//早期报告
        String commodetailPremdt = sp.getString("commodetailPremdt", "");//预计中期时间
        String commodetailQCmdt = sp.getString("commodetailQCmdt", "");//自查中期时间
        String commodetailQCmdtDoc = sp.getString("commodetailQCmdtDoc", "");//中期报告
        String commodetailPreedt = sp.getString("commodetailPreedt", "");//预计尾期时间
        String commodetailQCMedt = sp.getString("commodetailQCMedt", "");//自查尾期时间
        String commodetailQCedtDoc = sp.getString("commodetailQCedtDoc", "");//尾期报告
        String commodetailFctmdt = sp.getString("commodetailFctmdt", "");//客查中期时间
        String commodetailFctedt = sp.getString("commodetailFctedt", "");//客查尾期时间
        String commodetailPackbdat = sp.getString("commodetailPackbdat", "");//成品包装开始时间
        String commoPackqty2 = sp.getString("commoPackqty2", "");//装箱数量
        String commodetailQCMemo = sp.getString("commodetailQCMemo", "");//QC特别备注
        String commodetailFactlcdat = sp.getString("commodetailFactlcdat", "");//离厂日期
        String commodetailBatchid = sp.getString("commodetailBatchid", "");//查货批次
        commodetailOurAfter = sp.getString("commodetailOurAfter", "");//后道
        String commodetailCtmchkdt = sp.getString("commodetailCtmchkdt", "");//业务员确认客查日期
        String commodetailIPQCPedt = sp.getString("commodetailIPQCPedt", "");//尾查预查
        String commodetailIPQCmdt = sp.getString("commodetailIPQCmdt", "");//巡检中查
        String commodetailIPQC = sp.getString("commodetailIPQC", "");//巡检
        String commoQAname = sp.getString("commodetailfirstsamQA", "");//QA首扎
        String commodetailQAScore = sp.getString("commodetailQAScore", "");//QA首扎件数
        String commodetailQAMemo = sp.getString("commodetailQAMemo", "");//QA首扎日期
        String commodetailchker = sp.getString("commodetailchker", "");//件查
        String commodetailchkpdt = sp.getString("commodetailchkpdt", "");//预计件查时间
        String commodetailchkfctdt = sp.getString("commodetailchkfctdt", "");//实际件查时间
        String commodetailchkplace = sp.getString("commodetailchkplace", "");//件查地址

        tv_commodetail_date.setText(commoitem);
        tv_commodetail_ctmtxt.setText(commodetailCtmtxt);
        tv_commodetail_factory.setText(commodetailSubfactory);
        tv_commodetail_count.setText(commodetailTaskqty);
        tv_commodetail_prddocumentary.setText(commodetailPrddocumentary);
        tv_commodetail_prdmaster.setText(commodetailprdmaster);
        tv_commodetail_QCMasterScore.setText(commodetailQCMasterScore);
        tv_commodetail_sealedrev.setText(commodetailSealedrev);
        tv_commodetail_docback.setText(commodetailDocback);
        tv_commodetail_lcdat.setText(commodetailLcdat);
        tv_commodetail_preMemo.setText(commodetailPreMemo);
        tv_commodetail_predocdt.setText(commodetailPredocdt);
        tv_commodetail_predt.setText(commodetailPred);
        tv_commodetail_predoc.setText(commodetailPredoc);
        tv_commodetail_fabricsok.setText(commodetailFabricsok);
        tv_commodetail_accessoriesok.setText(commodetailAccessoriesok);
        tv_commodetail_spcproDec.setText(commodetailSpcproDec);
        tv_commodetail_spcproMemo.setText(commodetailSpcproMemo);
        tv_commodetail_cutqty.setText(commodetailCutqty);
        tv_commodetail_sewFdt.setText(commodetailSewFdt);
        tv_commodetail_sewMdt.setText(commodetailSewMdt);
        tv_commodetail_prebdt.setText(commodetailPrebdt);
        tv_commodetail_QCbdt.setText(commodetailQCbdt);
        tv_commodetail_QCbdtDoc.setText(commodetailQCbdtDoc);
        tv_commodetail_premdt.setText(commodetailPremdt);
        tv_commodetail_QCmdt.setText(commodetailQCmdt);
        tv_commodetail_QCmdtDoc.setText(commodetailQCmdtDoc);
        tv_commodetail_preedt.setText(commodetailPreedt);
        tv_commodetail_QCMedt.setText(commodetailQCMedt);
        tv_commodetail_QCedtDoc.setText(commodetailQCedtDoc);
        tv_commodetail_fctmdt.setText(commodetailFctmdt);
        tv_commodetail_fctedt.setText(commodetailFctedt);
        tv_commodetail_packbdat.setText(commodetailPackbdat);
        tv_commodetail_packqty2.setText(commoPackqty2);
        tv_commodetail_QCMemo.setText(commodetailQCMemo);
        tv_commodetail_factlcdat.setText(commodetailFactlcdat);
        tv_commodetail_batchid.setText(commodetailBatchid);
        tv_commodetail_ourAfter.setText(commodetailOurAfter);
        tv_commodetail_ctmchkdt.setText(commodetailCtmchkdt);
        tv_commodetail_IPQCPedt.setText(commodetailIPQCPedt);
        tv_commodetail_IPQC.setText(commodetailIPQC);
        tv_commodetail_IPQCmdt.setText(commodetailIPQCmdt);
        tv_commodetail_QAname.setText(commoQAname);
        tv_commodetail_QAScore.setText(commodetailQAScore);
        tv_commodetail_QAMemo.setText(commodetailQAMemo);
        tv_commodetail_chker.setText(commodetailchker);
        tv_commodetail_chkpdt.setText(commodetailchkpdt);
        tv_commodetail_chkfctdt.setText(commodetailchkfctdt);
        tv_commodetail_chkplace.setText(commodetailchkplace);
        setVisibility();
    }

    private void setListener() {
        ivCommoditySql.setOnClickListener(this);
        btnCommoSave.setOnClickListener(this);
    }

    /*判断是否修改过数据*/
    private void setBlacksp() {
        sp = getSharedPreferences("my_sp", 0);
        String CommodityQCMasterScore = sp.getString("CommodityQCMasterScore", "");
        String dateSealedrewtimesign = sp.getString("dateSealedrewtimesign", "");
        String dateDocbacktimesign = sp.getString("dateDocbacktimesign", "");
        String CommodityPreMemo = sp.getString("CommodityPreMemo", "");
        String datePredocdttimesign = sp.getString("datePredocdttimesign", "");
        String datePredtimesign = sp.getString("datePredtimesign", "");
        String CommodityPredoc = sp.getString("CommodityPredoc", "");
        String CommodityFabricsok = sp.getString("CommodityFabricsok", "");
        String CommodityAccessoriesok = sp.getString("CommodityAccessoriesok", "");
        String CommoditySpcproDec = sp.getString("CommoditySpcproDec", "");
        String CommoditySpcproMemo = sp.getString("CommoditySpcproMemo", "");
        String CommodityCutqty = sp.getString("CommodityCutqty", "");
        String dateSewFdttimesign = sp.getString("dateSewFdttimesign", "");
        String dateSewMdttimesign = sp.getString("dateSewMdttimesign", "");
        String datePrebdttimesign = sp.getString("datePrebdttimesign", "");
        String dateQCbdttimesign = sp.getString("dateQCbdttimesign", "");
        String CommodityQCbdtDoc = sp.getString("CommodityQCbdtDoc", "");
        String datePremdttimesign = sp.getString("datePremdttimesign", "");
        String dateQCmdttimesign = sp.getString("dateQCmdttimesign", "");
        String CommodityQCmdtDoc = sp.getString("CommodityQCmdtDoc", "");
        String datePreedttimesign = sp.getString("datePreedttimesign", "");
        String dateQCMedttimesign = sp.getString("dateQCMedttimesign", "");
        String CommodityQCedtDoc = sp.getString("CommodityQCedtDoc", "");
        String dateFctmdttimesign = sp.getString("dateFctmdttimesign", "");
        String dateFctedttimesign = sp.getString("dateFctedttimesign", "");
        String datePackbdattimesign = sp.getString("datePackbdattimesign", "");
        String CommodityPackqty2 = sp.getString("CommodityPackqty2", "");
        String CommodityQCMemo = sp.getString("CommodityQCMemo", "");
        String dateFactlcdattimesign = sp.getString("dateFactlcdattimesign", "");
        String CommodityBatchid = sp.getString("CommodityBatchid", "");
        String commohdTitle = sp.getString("commohdTitle", "");
        String dateCtmchkdttimesign = sp.getString("dateCtmchkdttimesign", "");
        String CommodityIPQCPedt = sp.getString("CommodityIPQCPedt", "");
        String CommodityIPQCmdt = sp.getString("CommodityIPQCmdt", "");
        String CommodityQAname = sp.getString("CommodityQAname", "");
        String CommodityQAScore = sp.getString("CommodityQAScore", "");
        String dateQAMemotimesign = sp.getString("dateQAMemotimesign", "");
        if (CommodityQCMasterScore.equals("") && dateSealedrewtimesign.equals("")
                && dateDocbacktimesign.equals("") && CommodityPreMemo.equals("")
                && datePredocdttimesign.equals("") && datePredtimesign.equals("")
                && CommodityPredoc.equals("") && CommodityFabricsok.equals("")
                && CommodityAccessoriesok.equals("") && CommoditySpcproDec.equals("")
                && CommoditySpcproMemo.equals("") && CommodityCutqty.equals("")
                && dateSewFdttimesign.equals("") && dateSewMdttimesign.equals("")
                && datePrebdttimesign.equals("") && dateQCbdttimesign.equals("")
                && CommodityQCbdtDoc.equals("") && datePremdttimesign.equals("")
                && dateQCmdttimesign.equals("") && CommodityQCmdtDoc.equals("")
                && datePreedttimesign.equals("") && dateQCMedttimesign.equals("")
                && CommodityQCedtDoc.equals("") && dateFctmdttimesign.equals("")
                && dateFctedttimesign.equals("") && datePackbdattimesign.equals("")
                && CommodityPackqty2.equals("") && CommodityQCMemo.equals("")
                && dateFactlcdattimesign.equals("") && CommodityBatchid.equals("")
                && commohdTitle.equals("") && dateCtmchkdttimesign.equals("")
                && CommodityIPQCPedt.equals("") && CommodityIPQCmdt.equals("")
                && CommodityQAname.equals("") && CommodityQAScore.equals("")
                && dateQAMemotimesign.equals("")) {
            flagblackBoolean = true;//都为空则直接退出
        } else {
            flagblackBoolean = false;//有数据则询问是否保存
        }
    }

    /*
     * 判断生产主管是否是当前登录用户
     * 如果是当前用户，则可以修改对应的字符
     * 如果不是，则判断跟单是否是当前用户
     * 可修改后道、主管评分、封样资料接收时间、大货资料接收时间、需要特别备注的情况、
     * 预计产前报告时间、开产前会时间、大货面料情况、大货辅料情况、大货特殊工艺情况
     * 特殊工艺特别备注、实裁数、上线日期、下线日期、预计早期时间、自查早期时间、
     * 预计中期时间、自查中期时间、预计尾期时间、自查尾期时间、客查尾期时间、
     * 客查中期时间、成品包装开始日期、装箱数量、离厂日期、业务员确认客查日期
     */
    private void setVisibility() {
        sp = getSharedPreferences("my_sp", 0);
        if (commodetailprdmaster.equals(nameid)) {//判断生产主管是否是当前用户
            tv_commodetail_ourAfter.setEnabled(true);//后道弹出选择菜单
            tv_commodetail_ourAfter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_commo_hd, popupMenu.getMenu());
                    // menu的item点击事件
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            String title = item.getTitle().toString();
                            if (commodetailOurAfter == null) {
                                commodetailOurAfter = "";
                            }
                            String nulltitle;
                            if (commodetailOurAfter.equals(title)) {
                                nulltitle = "1";//1是空的等于没有修改
                            } else {
                                nulltitle = "2";
                            }
                            spUtils.put(getApplicationContext(), "commonulltitle", nulltitle);
                            spUtils.put(getApplicationContext(), "commohdTitle", title);//后道
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
            });

            tv_commodetail_QCMasterScore.setEnabled(true);//生产主管频分
            tv_commodetail_QCMasterScore.setFilters(new
                    InputFilter[]{new InputFilter.LengthFilter(10)});
            tv_commodetail_QCMasterScore.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {//得到焦点
                        String qcmaster = sp.getString("CommodityQCMasterScore", "");
                        spUtils.put(getApplicationContext(), "debugQCMasterScore", qcmaster);
                        TextWatcher Tvqcmasterscore = new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                Log.d(TAG, "beforeTextChanged");
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                Log.d(TAG, "onTextChanged");
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                Log.d(TAG, "afterTextChanged");
                                String proitem = tv_commodetail_QCMasterScore.getText().toString();
                                String commoitem = sp.getString("commodetailQCMasterScore", "");
                                if (commoitem == null) {
                                    commoitem = "";
                                }
                                String nullitem;
                                if (commoitem.equals(proitem)) {
                                    nullitem = "1";
                                } else {
                                    nullitem = "2";

                                }
                                spUtils.put(getApplicationContext(), "commonullitem", nullitem);
                                spUtils.put(getApplicationContext(), "CommodityQCMasterScore", proitem);//主管评分
                            }
                        };
                        tv_commodetail_QCMasterScore.addTextChangedListener(Tvqcmasterscore);
                        tv_commodetail_QCMasterScore.setTag(Tvqcmasterscore);
                    } else {//失去焦点
                        String qcmastertwoo = tv_commodetail_QCMasterScore.getText().toString();
                        String commoitem = sp.getString("commodetailQCMasterScore", "");
                        if (commoitem == null) {
                            commoitem = "";
                        }
                        String nullitem;
                        if (commoitem.equals(qcmastertwoo)) {
                            nullitem = "1";
                        } else {
                            nullitem = "2";
                        }
                        spUtils.put(getApplicationContext(), "commonullitem", nullitem);
                    }
                }
            });
            /*光标放置在文本最后*/
            tv_commodetail_QCMasterScore.setSelection(tv_commodetail_QCMasterScore.length());

            tv_commodetail_sealedrev.setEnabled(true);//封样资料接收时间
            tv_commodetail_sealedrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_sealedrev.setText(datetime);
                                    String commosearledrev = sp.getString("commodetailSealedrev", "");
                                    if (commosearledrev == null) {
                                        commosearledrev = "";
                                    }
                                    String nullsearledrev;
                                    if (commosearledrev.equals(datetime)) {
                                        nullsearledrev = "1";
                                    } else {
                                        nullsearledrev = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsearledrev", nullsearledrev);
                                    spUtils.put(getApplicationContext(), "dateSealedrewtimesign", datetime);//封样资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                            "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_sealedrev.setText("");
                                    String commosearledrev = sp.getString("commodetailSealedrev", "");
                                    if (commosearledrev == null) {
                                        commosearledrev = "";
                                    }
                                    String nullsearledrev;
                                    if (commosearledrev.equals("")) {
                                        nullsearledrev = "1";
                                    } else {
                                        nullsearledrev = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsearledrev", nullsearledrev);
                                    spUtils.put(getApplicationContext(), "dateSealedrewtimesign", "");//封样资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_docback.setEnabled(true);//大货资料接收时间
            tv_commodetail_docback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_docback.setText(datetime);
                                    String commodocback = sp.getString("commodetailDocback", "");
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals(datetime)) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonulldocback", nulldocback);
                                    spUtils.put(getApplicationContext(), "dateDocbacktimesign", datetime);//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_docback.setText("");
                                    String commodocback = sp.getString("commodetailDocback", "");
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals("")) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonulldocback", nulldocback);
                                    spUtils.put(getApplicationContext(), "dateDocbacktimesign", "");//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_lcdat.setEnabled(true);//出货时间

            tv_commodetail_preMemo.setEnabled(true);//需要备注的特别情况
            tv_commodetail_preMemo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_preMemo.setText("");
                    }
                    String proitem = tv_commodetail_preMemo.getText().toString();
                    String commopromemo = sp.getString("commodetailPreMemo", "");
                    if (commopromemo == null) {
                        commopromemo = "";
                    }
                    String nullmemo;
                    if (commopromemo.equals(proitem)) {
                        nullmemo = "1";
                    } else {
                        nullmemo = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullmemo", nullmemo);
                    spUtils.put(getApplicationContext(), "CommodityPreMemo", proitem);//需要特别备注的情况
                }
            });
            tv_commodetail_preMemo.setSelection(tv_commodetail_preMemo.length());


            tv_commodetail_predocdt.setEnabled(true);//预计产前会报告时间
            tv_commodetail_predocdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_predocdt.setText(datetime);
                                    String commopreducdt = sp.getString("commodetailPredocdt", "");
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals(datetime)) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreducdt", nullpreducdt);
                                    spUtils.put(getApplicationContext(), "datePredocdttimesign", datetime);//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_predocdt.setText("");
                                    String commopreducdt = sp.getString("commodetailPredocdt", "");
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals("")) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreducdt", nullpreducdt);
                                    spUtils.put(getApplicationContext(), "datePredocdttimesign", "");//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_predt.setEnabled(true);//开产前会时间
            tv_commodetail_predt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_predt.setText(datetime);
                                    String commopred = sp.getString("commodetailPred", "");
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals(datetime)) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpred", nullpred);
                                    spUtils.put(getApplicationContext(), "datePredtimesign", datetime);//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_predt.setText("");
                                    String commopred = sp.getString("commodetailPred", "");
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals("")) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpred", nullpred);
                                    spUtils.put(getApplicationContext(), "datePredtimesign", "");//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_fabricsok.setEnabled(true);//大货面料情况
            tv_commodetail_fabricsok.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_fabricsok.setText("");
                    }
                    String proitem = tv_commodetail_fabricsok.getText().toString();
                    String commofabricsok = sp.getString("commodetailFabricsok", "");
                    if (commofabricsok == null) {
                        commofabricsok = "";
                    }
                    String nullfabricsok;
                    if (commofabricsok.equals(proitem)) {
                        nullfabricsok = "1";
                    } else {
                        nullfabricsok = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullfabricsok", nullfabricsok);
                    spUtils.put(getApplicationContext(), "CommodityFabricsok", proitem);//大货面料情况
                }
            });
            tv_commodetail_fabricsok.setSelection(tv_commodetail_fabricsok.length());

            tv_commodetail_accessoriesok.setEnabled(true);//大货辅料情况
            tv_commodetail_accessoriesok.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_accessoriesok.setText("");
                    }
                    String proitem = tv_commodetail_accessoriesok.getText().toString();
                    String commoaccessori = sp.getString("commodetailAccessoriesok", "");
                    if (commoaccessori == null) {
                        commoaccessori = "";
                    }
                    String nullaccessori;
                    if (commoaccessori.equals(proitem)) {
                        nullaccessori = "1";
                    } else {
                        nullaccessori = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullaccessori", nullaccessori);
                    spUtils.put(getApplicationContext(), "CommodityAccessoriesok", proitem);//大货辅料情况
                }
            });
            tv_commodetail_accessoriesok.setSelection(tv_commodetail_accessoriesok.length());

            tv_commodetail_spcproDec.setEnabled(true);//大货特殊工艺情况
            tv_commodetail_spcproDec.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_spcproDec.setText("");
                    }
                    String proitem = tv_commodetail_spcproDec.getText().toString();
                    String commospcprodec = sp.getString("commodetailSpcproDec", "");
                    if (commospcprodec == null) {
                        commospcprodec = "";
                    }
                    String nullspcprodec;
                    if (commospcprodec.equals(proitem)) {
                        nullspcprodec = "1";
                    } else {
                        nullspcprodec = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullspcprodec", nullspcprodec);
                    spUtils.put(getApplicationContext(), "CommoditySpcproDec", proitem);//大货特殊工艺情况
                }
            });
            tv_commodetail_spcproDec.setSelection(tv_commodetail_spcproDec.length());

            tv_commodetail_spcproMemo.setEnabled(true);//特殊工艺备注
            tv_commodetail_spcproMemo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_spcproMemo.setText("");
                    }
                    String proitem = tv_commodetail_spcproMemo.getText().toString();
                    String commospcpromemo = sp.getString("commodetailSpcproMemo", "");
                    if (commospcpromemo == null) {
                        commospcpromemo = "";
                    }
                    String nullspcpromemo;
                    if (commospcpromemo.equals(proitem)) {
                        nullspcpromemo = "1";
                    } else {
                        nullspcpromemo = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullspcpromemo", nullspcpromemo);
                    spUtils.put(getApplicationContext(), "CommoditySpcproMemo", proitem);//特殊工艺特别备注
                }
            });
            tv_commodetail_spcproMemo.setSelection(tv_commodetail_spcproMemo.length());

            tv_commodetail_cutqty.setEnabled(true);//实裁数
            tv_commodetail_cutqty.setFilters(new
                    InputFilter[]{new InputFilter.LengthFilter(10)});
            tv_commodetail_cutqty.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_cutqty.getText().toString();
                    String commocutqty = sp.getString("commodetailCutqty", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullcutqty;
                    if (commocutqty.equals(proitem)) {
                        nullcutqty = "1";
                    } else {
                        nullcutqty = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullcutqty", nullcutqty);
                    spUtils.put(getApplicationContext(), "CommodityCutqty", proitem);//实裁数
                }
            });
            tv_commodetail_cutqty.setSelection(tv_commodetail_cutqty.length());

            tv_commodetail_sewFdt.setEnabled(true);//上线日期
            tv_commodetail_sewFdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_sewFdt.setText(datetime);
                                    String commosewfdt = sp.getString("commodetailSewFdt", "");
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals(datetime)) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewfdt", nullsewfdt);
                                    spUtils.put(getApplicationContext(), "dateSewFdttimesign", datetime);//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_sewFdt.setText("");
                                    String commosewfdt = sp.getString("commodetailSewFdt", "");
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals("")) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewfdt", nullsewfdt);
                                    spUtils.put(getApplicationContext(), "dateSewFdttimesign", "");//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_sewMdt.setEnabled(true);//下线日期
            tv_commodetail_sewMdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_sewMdt.setText(datetime);
                                    String commosewmdt = sp.getString("commodetailSewMdt", "");
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals(datetime)) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewmdt", nullsewmdt);
                                    spUtils.put(getApplicationContext(), "dateSewMdttimesign", datetime);//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_sewMdt.setText("");
                                    String commosewmdt = sp.getString("commodetailSewMdt", "");
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals("")) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewmdt", nullsewmdt);
                                    spUtils.put(getApplicationContext(), "dateSewMdttimesign", "");//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_prebdt.setEnabled(true);//预计早期时间
            tv_commodetail_prebdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_prebdt.setText(datetime);
                                    String commoprebdt = sp.getString("commodetailPrebdt", "");
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals(datetime)) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullprebdt", nullprebdt);
                                    spUtils.put(getApplicationContext(), "datePrebdttimesign", datetime);//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_prebdt.setText("");
                                    String commoprebdt = sp.getString("commodetailPrebdt", "");
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals("")) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullprebdt", nullprebdt);
                                    spUtils.put(getApplicationContext(), "datePrebdttimesign", "");//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QCbdt.setEnabled(true);//自查早期时间
            tv_commodetail_QCbdt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_QCbdt.getText().toString();
                    String commocutqty = sp.getString("commodetailQCbdt", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullqcbdt;
                    if (commocutqty.equals(proitem)) {
                        nullqcbdt = "1";
                    } else {
                        nullqcbdt = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcbdt", nullqcbdt);
                    spUtils.put(getApplicationContext(), "dateQCbdttimesign", proitem);//自查早期时间
                }
            });
            tv_commodetail_QCbdt.setSelection(tv_commodetail_QCbdt.length());

            tv_commodetail_premdt.setEnabled(true);//预计中期时间
            tv_commodetail_premdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_premdt.setText(datetime);
                                    String commopremdt = sp.getString("commodetailPremdt", "");
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals(datetime)) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpremdt", nullpremdt);
                                    spUtils.put(getApplicationContext(), "datePremdttimesign", datetime);//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_premdt.setText("");
                                    String commopremdt = sp.getString("commodetailPremdt", "");
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals("")) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpremdt", nullpremdt);
                                    spUtils.put(getApplicationContext(), "datePremdttimesign", "");//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QCmdt.setEnabled(true);//自查中期时间
            tv_commodetail_QCmdt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_QCmdt.getText().toString();
                    String commocutqty = sp.getString("commodetailQCmdt", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullqcmdt;
                    if (commocutqty.equals(proitem)) {
                        nullqcmdt = "1";
                    } else {
                        nullqcmdt = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmdt", nullqcmdt);
                    spUtils.put(getApplicationContext(), "dateQCmdttimesign", proitem);//自查中期时间
                }
            });
            tv_commodetail_QCmdt.setSelection(tv_commodetail_QCmdt.length());

            tv_commodetail_preedt.setEnabled(true);//预计尾期时间
            tv_commodetail_preedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_preedt.setText(datetime);
                                    String commopreedt = sp.getString("commodetailPreedt", "");
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals(datetime)) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreedt", nullpreedt);
                                    spUtils.put(getApplicationContext(), "datePreedttimesign", datetime);//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_preedt.setText("");
                                    String commopreedt = sp.getString("commodetailPreedt", "");
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals("")) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreedt", nullpreedt);
                                    spUtils.put(getApplicationContext(), "datePreedttimesign", "");//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QCMedt.setEnabled(true);//自查尾期时间
            tv_commodetail_QCMedt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_QCmdt.getText().toString();
                    String commocutqty = sp.getString("commodetailQCmdt", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullqcmedt;
                    if (commocutqty.equals(proitem)) {
                        nullqcmedt = "1";
                    } else {
                        nullqcmedt = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmedt", nullqcmedt);
                    spUtils.put(getApplicationContext(), "dateQCMedttimesign", proitem);//自查尾期时间
                }
            });
            tv_commodetail_QCMedt.setSelection(tv_commodetail_QCMedt.length());

            tv_commodetail_fctmdt.setEnabled(true);//客查中期时间
            tv_commodetail_fctmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_fctmdt.setText(datetime);
                                    String commofctmdt = sp.getString("commodetailFctmdt", "");
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals(datetime)) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctmdt", nullfctmdt);
                                    spUtils.put(getApplicationContext(), "dateFctmdttimesign", datetime);//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_fctmdt.setText("");
                                    String commofctmdt = sp.getString("commodetailFctmdt", "");
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals("")) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctmdt", nullfctmdt);
                                    spUtils.put(getApplicationContext(), "dateFctmdttimesign", "");//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_fctedt.setEnabled(true);//客查尾期时间
            tv_commodetail_fctedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_fctedt.setText(datetime);
                                    String commofctedt = sp.getString("commodetailFctedt", "");
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals(datetime)) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctedt", nullfctedt);
                                    spUtils.put(getApplicationContext(), "dateFctedttimesign", datetime);//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_fctedt.setText("");
                                    String commofctedt = sp.getString("commodetailFctedt", "");
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals("")) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctedt", nullfctedt);
                                    spUtils.put(getApplicationContext(), "dateFctedttimesign", "");//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_packbdat.setEnabled(true);//成品包装开始时间
            tv_commodetail_packbdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_packbdat.setText(datetime);
                                    String commopackbdat = sp.getString("commodetailPackbdat", "");
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals(datetime)) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpackbdat", nullpackbdat);
                                    spUtils.put(getApplicationContext(), "datePackbdattimesign", datetime);//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_packbdat.setText("");
                                    String commopackbdat = sp.getString("commodetailPackbdat", "");
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals("")) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpackbdat", nullpackbdat);
                                    spUtils.put(getApplicationContext(), "datePackbdattimesign", "");//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_packqty2.setEnabled(true);//装箱数量
            tv_commodetail_packqty2.setFilters(new
                    InputFilter[]{new InputFilter.LengthFilter(10)});
            tv_commodetail_packqty2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_packqty2.getText().toString();
                    String commopackqty2 = sp.getString("commoPackqty2", "");
                    if (commopackqty2 == null) {
                        commopackqty2 = "";
                    }
                    String nullpackqty2;
                    if (commopackqty2.equals(proitem)) {
                        nullpackqty2 = "1";
                    } else {
                        nullpackqty2 = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullpackqty2", nullpackqty2);
                    spUtils.put(getApplicationContext(), "CommodityPackqty2", proitem);//装箱数量
                }
            });
            tv_commodetail_packqty2.setSelection(tv_commodetail_packqty2.length());

            tv_commodetail_QCMemo.setEnabled(true);//QC特别备注
            tv_commodetail_QCMemo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_QCMemo.setText("");
                    }
                    String proitem = tv_commodetail_QCMemo.getText().toString();
                    String commoqcmemo = sp.getString("commodetailQCMemo", "");
                    if (commoqcmemo == null) {
                        commoqcmemo = "";
                    }
                    String nullqcmemo;
                    if (commoqcmemo.equals(proitem)) {
                        nullqcmemo = "1";
                    } else {
                        nullqcmemo = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmemo", nullqcmemo);
                    spUtils.put(getApplicationContext(), "CommodityQCMemo", proitem);//QC特别备注
                }
            });
            tv_commodetail_QCMemo.setSelection(tv_commodetail_QCMemo.length());

            tv_commodetail_factlcdat.setEnabled(true);//离厂日期
            tv_commodetail_factlcdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_factlcdat.setText(datetime);
                                    String commofactlcdat = sp.getString("commodetailFactlcdat", "");
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals(datetime)) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(getApplicationContext(), "dateFactlcdattimesign", datetime);//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_factlcdat.setText("");
                                    String commofactlcdat = sp.getString("commodetailFactlcdat", "");
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals("")) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(getApplicationContext(), "dateFactlcdattimesign", "");//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_batchid.setEnabled(true);//查获批次
            tv_commodetail_batchid.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_batchid.getText().toString();
                    String commoBatchid = sp.getString("commodetailBatchid", "");
                    if (commoBatchid == null) {
                        commoBatchid = "";
                    }
                    String nullBatchid;
                    if (commoBatchid.equals(proitem)) {
                        nullBatchid = "1";
                    } else {
                        nullBatchid = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullBatchid", nullBatchid);
                    spUtils.put(getApplicationContext(), "CommodityBatchid", proitem);//查货批次
                }
            });
            tv_commodetail_batchid.setSelection(tv_commodetail_batchid.length());

            tv_commodetail_ctmchkdt.setEnabled(true);//业务员确认客查日期
            tv_commodetail_ctmchkdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_ctmchkdt.setText(datetime);
                                    String commoCtmchkdt = sp.getString("commodetailCtmchkdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(getApplicationContext(), "dateCtmchkdttimesign", datetime);//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_ctmchkdt.setText("");
                                    String commoCtmchkdt = sp.getString("commodetailCtmchkdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(getApplicationContext(), "dateCtmchkdttimesign", "");//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_IPQCPedt.setEnabled(true);//尾查预查
            tv_commodetail_IPQCPedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_IPQCPedt.setText(datetime);
                                    String commoCtmchkdt = sp.getString("commodetailIPQCPedt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcpedt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullipqcpedt = "1";
                                    } else {
                                        nullipqcpedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcpedt", nullipqcpedt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCPedt", datetime);//尾查预查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_IPQCPedt.setText("");
                                    String commoCtmchkdt = sp.getString("commodetailIPQCPedt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcpedt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullipqcpedt = "1";
                                    } else {
                                        nullipqcpedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcpedt", nullipqcpedt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCPedt", "");//尾查预查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_IPQCmdt.setEnabled(true);//巡检中查
            tv_commodetail_IPQCmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_IPQCmdt.setText(datetime);
                                    String commoCtmchkdt = sp.getString("commodetailIPQCmdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcmdt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullipqcmdt = "1";
                                    } else {
                                        nullipqcmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcmdt", nullipqcmdt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCmdt", datetime);//巡检中查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_IPQCmdt.setText("");
                                    String commoCtmchkdt = sp.getString("commodetailIPQCmdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcmdt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullipqcmdt = "1";
                                    } else {
                                        nullipqcmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcmdt", nullipqcmdt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCmdt", "");//巡检中查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_predoc.setEnabled(false);//产前会报告
            tv_commodetail_QCbdtDoc.setEnabled(false);//早期报告
            tv_commodetail_QCmdtDoc.setEnabled(false);//中期报告
            tv_commodetail_QCedtDoc.setEnabled(false);//尾期报告
            tv_commodetail_chker.setEnabled(false);//件查
            tv_commodetail_chkplace.setEnabled(false);//件查地址
            tv_commodetail_QAname.setEnabled(false);//QA首扎
            tv_commodetail_QAScore.setEnabled(false);//QA首扎件数
            btnCommoSave.setVisibility(View.VISIBLE);
        } else if (commodetailPrddocumentary.equals(nameid)) {//判断跟单是否是当前用户
            tv_commodetail_ourAfter.setEnabled(true);//后道弹出选择菜单
            tv_commodetail_ourAfter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_commo_hd, popupMenu.getMenu());
                    // menu的item点击事件
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            String title = item.getTitle().toString();
                            if (commodetailOurAfter == null) {
                                commodetailOurAfter = "";
                            }
                            String nulltitle;
                            if (commodetailOurAfter.equals(title)) {
                                nulltitle = "1";//1是空的等于没有修改
                            } else {
                                nulltitle = "2";
                            }
                            spUtils.put(getApplicationContext(), "commonulltitle", nulltitle);
                            spUtils.put(getApplicationContext(), "commohdTitle", title);//后道
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
            });

            tv_commodetail_QCMasterScore.setEnabled(false);//生产主管评分

            tv_commodetail_sealedrev.setEnabled(true);//封样资料接收时间
            tv_commodetail_sealedrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_sealedrev.setText(datetime);
                                    String commosearledrev = sp.getString("commodetailSealedrev", "");
                                    if (commosearledrev == null) {
                                        commosearledrev = "";
                                    }
                                    String nullsearledrev;
                                    if (commosearledrev.equals(datetime)) {
                                        nullsearledrev = "1";
                                    } else {
                                        nullsearledrev = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsearledrev", nullsearledrev);
                                    spUtils.put(getApplicationContext(), "dateSealedrewtimesign", datetime);//封样资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                            "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_sealedrev.setText("");
                                    String commosearledrev = sp.getString("commodetailSealedrev", "");
                                    if (commosearledrev == null) {
                                        commosearledrev = "";
                                    }
                                    String nullsearledrev;
                                    if (commosearledrev.equals("")) {
                                        nullsearledrev = "1";
                                    } else {
                                        nullsearledrev = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsearledrev", nullsearledrev);
                                    spUtils.put(getApplicationContext(), "dateSealedrewtimesign", "");//封样资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_docback.setEnabled(true);//大货资料接收时间
            tv_commodetail_docback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_docback.setText(datetime);
                                    String commodocback = sp.getString("commodetailDocback", "");
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals(datetime)) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonulldocback", nulldocback);
                                    spUtils.put(getApplicationContext(), "dateDocbacktimesign", datetime);//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_docback.setText("");
                                    String commodocback = sp.getString("commodetailDocback", "");
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals("")) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonulldocback", nulldocback);
                                    spUtils.put(getApplicationContext(), "dateDocbacktimesign", "");//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_lcdat.setEnabled(true);//出货时间

            tv_commodetail_preMemo.setEnabled(true);//需要备注的特别情况
            tv_commodetail_preMemo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_preMemo.setText("");
                    }
                    String proitem = tv_commodetail_preMemo.getText().toString();
                    String commopromemo = sp.getString("commodetailPreMemo", "");
                    if (commopromemo == null) {
                        commopromemo = "";
                    }
                    String nullmemo;
                    if (commopromemo.equals(proitem)) {
                        nullmemo = "1";
                    } else {
                        nullmemo = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullmemo", nullmemo);
                    spUtils.put(getApplicationContext(), "CommodityPreMemo", proitem);//需要特别备注的情况
                }
            });
            tv_commodetail_preMemo.setSelection(tv_commodetail_preMemo.length());


            tv_commodetail_predocdt.setEnabled(true);//预计产前会报告时间
            tv_commodetail_predocdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_predocdt.setText(datetime);
                                    String commopreducdt = sp.getString("commodetailPredocdt", "");
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals(datetime)) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreducdt", nullpreducdt);
                                    spUtils.put(getApplicationContext(), "datePredocdttimesign", datetime);//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_predocdt.setText("");
                                    String commopreducdt = sp.getString("commodetailPredocdt", "");
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals("")) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreducdt", nullpreducdt);
                                    spUtils.put(getApplicationContext(), "datePredocdttimesign", "");//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_predt.setEnabled(true);//开产前会时间
            tv_commodetail_predt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_predt.setText(datetime);
                                    String commopred = sp.getString("commodetailPred", "");
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals(datetime)) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpred", nullpred);
                                    spUtils.put(getApplicationContext(), "datePredtimesign", datetime);//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_predt.setText("");
                                    String commopred = sp.getString("commodetailPred", "");
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals("")) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpred", nullpred);
                                    spUtils.put(getApplicationContext(), "datePredtimesign", "");//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_fabricsok.setEnabled(true);//大货面料情况
            tv_commodetail_fabricsok.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_fabricsok.setText("");
                    }
                    String proitem = tv_commodetail_fabricsok.getText().toString();
                    String commofabricsok = sp.getString("commodetailFabricsok", "");
                    if (commofabricsok == null) {
                        commofabricsok = "";
                    }
                    String nullfabricsok;
                    if (commofabricsok.equals(proitem)) {
                        nullfabricsok = "1";
                    } else {
                        nullfabricsok = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullfabricsok", nullfabricsok);
                    spUtils.put(getApplicationContext(), "CommodityFabricsok", proitem);//大货面料情况
                }
            });
            tv_commodetail_fabricsok.setSelection(tv_commodetail_fabricsok.length());

            tv_commodetail_accessoriesok.setEnabled(true);//大货辅料情况
            tv_commodetail_accessoriesok.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_accessoriesok.setText("");
                    }
                    String proitem = tv_commodetail_accessoriesok.getText().toString();
                    String commoaccessori = sp.getString("commodetailAccessoriesok", "");
                    if (commoaccessori == null) {
                        commoaccessori = "";
                    }
                    String nullaccessori;
                    if (commoaccessori.equals(proitem)) {
                        nullaccessori = "1";
                    } else {
                        nullaccessori = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullaccessori", nullaccessori);
                    spUtils.put(getApplicationContext(), "CommodityAccessoriesok", proitem);//大货辅料情况
                }
            });
            tv_commodetail_accessoriesok.setSelection(tv_commodetail_accessoriesok.length());

            tv_commodetail_spcproDec.setEnabled(true);//大货特殊工艺情况
            tv_commodetail_spcproDec.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_spcproDec.setText("");
                    }
                    String proitem = tv_commodetail_spcproDec.getText().toString();
                    String commospcprodec = sp.getString("commodetailSpcproDec", "");
                    if (commospcprodec == null) {
                        commospcprodec = "";
                    }
                    String nullspcprodec;
                    if (commospcprodec.equals(proitem)) {
                        nullspcprodec = "1";
                    } else {
                        nullspcprodec = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullspcprodec", nullspcprodec);
                    spUtils.put(getApplicationContext(), "CommoditySpcproDec", proitem);//大货特殊工艺情况
                }
            });
            tv_commodetail_spcproDec.setSelection(tv_commodetail_spcproDec.length());

            tv_commodetail_spcproMemo.setEnabled(true);//特殊工艺备注
            tv_commodetail_spcproMemo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_spcproMemo.setText("");
                    }
                    String proitem = tv_commodetail_spcproMemo.getText().toString();
                    String commospcpromemo = sp.getString("commodetailSpcproMemo", "");
                    if (commospcpromemo == null) {
                        commospcpromemo = "";
                    }
                    String nullspcpromemo;
                    if (commospcpromemo.equals(proitem)) {
                        nullspcpromemo = "1";
                    } else {
                        nullspcpromemo = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullspcpromemo", nullspcpromemo);
                    spUtils.put(getApplicationContext(), "CommoditySpcproMemo", proitem);//特殊工艺特别备注
                }
            });
            tv_commodetail_spcproMemo.setSelection(tv_commodetail_spcproMemo.length());

            tv_commodetail_cutqty.setEnabled(true);//实裁数
            tv_commodetail_cutqty.setFilters(new
                    InputFilter[]{new InputFilter.LengthFilter(10)});
            tv_commodetail_cutqty.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_cutqty.getText().toString();
                    String commocutqty = sp.getString("commodetailCutqty", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullcutqty;
                    if (commocutqty.equals(proitem)) {
                        nullcutqty = "1";
                    } else {
                        nullcutqty = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullcutqty", nullcutqty);
                    spUtils.put(getApplicationContext(), "CommodityCutqty", proitem);//实裁数
                }
            });
            tv_commodetail_cutqty.setSelection(tv_commodetail_cutqty.length());

            tv_commodetail_sewFdt.setEnabled(true);//上线日期
            tv_commodetail_sewFdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_sewFdt.setText(datetime);
                                    String commosewfdt = sp.getString("commodetailSewFdt", "");
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals(datetime)) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewfdt", nullsewfdt);
                                    spUtils.put(getApplicationContext(), "dateSewFdttimesign", datetime);//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_sewFdt.setText("");
                                    String commosewfdt = sp.getString("commodetailSewFdt", "");
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals("")) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewfdt", nullsewfdt);
                                    spUtils.put(getApplicationContext(), "dateSewFdttimesign", "");//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_sewMdt.setEnabled(true);//下线日期
            tv_commodetail_sewMdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_sewMdt.setText(datetime);
                                    String commosewmdt = sp.getString("commodetailSewMdt", "");
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals(datetime)) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewmdt", nullsewmdt);
                                    spUtils.put(getApplicationContext(), "dateSewMdttimesign", datetime);//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_sewMdt.setText("");
                                    String commosewmdt = sp.getString("commodetailSewMdt", "");
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals("")) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewmdt", nullsewmdt);
                                    spUtils.put(getApplicationContext(), "dateSewMdttimesign", "");//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_prebdt.setEnabled(true);//预计早期时间
            tv_commodetail_prebdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_prebdt.setText(datetime);
                                    String commoprebdt = sp.getString("commodetailPrebdt", "");
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals(datetime)) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullprebdt", nullprebdt);
                                    spUtils.put(getApplicationContext(), "datePrebdttimesign", datetime);//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_prebdt.setText("");
                                    String commoprebdt = sp.getString("commodetailPrebdt", "");
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals("")) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullprebdt", nullprebdt);
                                    spUtils.put(getApplicationContext(), "datePrebdttimesign", "");//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QCbdt.setEnabled(true);//自查早期时间
            tv_commodetail_QCbdt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_QCbdt.getText().toString();
                    String commocutqty = sp.getString("commodetailQCbdt", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullqcbdt;
                    if (commocutqty.equals(proitem)) {
                        nullqcbdt = "1";
                    } else {
                        nullqcbdt = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcbdt", nullqcbdt);
                    spUtils.put(getApplicationContext(), "dateQCbdttimesign", proitem);//自查早期时间
                }
            });
            tv_commodetail_QCbdt.setSelection(tv_commodetail_QCbdt.length());

            tv_commodetail_premdt.setEnabled(true);//预计中期时间
            tv_commodetail_premdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_premdt.setText(datetime);
                                    String commopremdt = sp.getString("commodetailPremdt", "");
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals(datetime)) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpremdt", nullpremdt);
                                    spUtils.put(getApplicationContext(), "datePremdttimesign", datetime);//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_premdt.setText("");
                                    String commopremdt = sp.getString("commodetailPremdt", "");
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals("")) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpremdt", nullpremdt);
                                    spUtils.put(getApplicationContext(), "datePremdttimesign", "");//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QCmdt.setEnabled(true);//自查中期时间
            tv_commodetail_QCmdt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_QCmdt.getText().toString();
                    String commocutqty = sp.getString("commodetailQCmdt", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullqcmdt;
                    if (commocutqty.equals(proitem)) {
                        nullqcmdt = "1";
                    } else {
                        nullqcmdt = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmdt", nullqcmdt);
                    spUtils.put(getApplicationContext(), "dateQCmdttimesign", proitem);//自查中期时间
                }
            });
            tv_commodetail_QCmdt.setSelection(tv_commodetail_QCmdt.length());

            tv_commodetail_preedt.setEnabled(true);//预计尾期时间
            tv_commodetail_preedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_preedt.setText(datetime);
                                    String commopreedt = sp.getString("commodetailPreedt", "");
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals(datetime)) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreedt", nullpreedt);
                                    spUtils.put(getApplicationContext(), "datePreedttimesign", datetime);//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_preedt.setText("");
                                    String commopreedt = sp.getString("commodetailPreedt", "");
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals("")) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreedt", nullpreedt);
                                    spUtils.put(getApplicationContext(), "datePreedttimesign", "");//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QCMedt.setEnabled(true);//自查尾期时间
            tv_commodetail_QCMedt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_QCmdt.getText().toString();
                    String commocutqty = sp.getString("commodetailQCmdt", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullqcmedt;
                    if (commocutqty.equals(proitem)) {
                        nullqcmedt = "1";
                    } else {
                        nullqcmedt = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmedt", nullqcmedt);
                    spUtils.put(getApplicationContext(), "dateQCMedttimesign", proitem);//自查尾期时间
                }
            });
            tv_commodetail_QCMedt.setSelection(tv_commodetail_QCMedt.length());

            tv_commodetail_fctmdt.setEnabled(true);//客查中期时间
            tv_commodetail_fctmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_fctmdt.setText(datetime);
                                    String commofctmdt = sp.getString("commodetailFctmdt", "");
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals(datetime)) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctmdt", nullfctmdt);
                                    spUtils.put(getApplicationContext(), "dateFctmdttimesign", datetime);//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_fctmdt.setText("");
                                    String commofctmdt = sp.getString("commodetailFctmdt", "");
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals("")) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctmdt", nullfctmdt);
                                    spUtils.put(getApplicationContext(), "dateFctmdttimesign", "");//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_fctedt.setEnabled(true);//客查尾期时间
            tv_commodetail_fctedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_fctedt.setText(datetime);
                                    String commofctedt = sp.getString("commodetailFctedt", "");
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals(datetime)) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctedt", nullfctedt);
                                    spUtils.put(getApplicationContext(), "dateFctedttimesign", datetime);//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_fctedt.setText("");
                                    String commofctedt = sp.getString("commodetailFctedt", "");
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals("")) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctedt", nullfctedt);
                                    spUtils.put(getApplicationContext(), "dateFctedttimesign", "");//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_packbdat.setEnabled(true);//成品包装开始时间
            tv_commodetail_packbdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_packbdat.setText(datetime);
                                    String commopackbdat = sp.getString("commodetailPackbdat", "");
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals(datetime)) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpackbdat", nullpackbdat);
                                    spUtils.put(getApplicationContext(), "datePackbdattimesign", datetime);//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_packbdat.setText("");
                                    String commopackbdat = sp.getString("commodetailPackbdat", "");
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals("")) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpackbdat", nullpackbdat);
                                    spUtils.put(getApplicationContext(), "datePackbdattimesign", "");//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_packqty2.setEnabled(true);//装箱数量
            tv_commodetail_packqty2.setFilters(new
                    InputFilter[]{new InputFilter.LengthFilter(10)});
            tv_commodetail_packqty2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_packqty2.getText().toString();
                    String commopackqty2 = sp.getString("commoPackqty2", "");
                    if (commopackqty2 == null) {
                        commopackqty2 = "";
                    }
                    String nullpackqty2;
                    if (commopackqty2.equals(proitem)) {
                        nullpackqty2 = "1";
                    } else {
                        nullpackqty2 = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullpackqty2", nullpackqty2);
                    spUtils.put(getApplicationContext(), "CommodityPackqty2", proitem);//装箱数量
                }
            });
            tv_commodetail_packqty2.setSelection(tv_commodetail_packqty2.length());

            tv_commodetail_QCMemo.setEnabled(true);//QC特别备注
            tv_commodetail_QCMemo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_QCMemo.setText("");
                    }
                    String proitem = tv_commodetail_QCMemo.getText().toString();
                    String commoqcmemo = sp.getString("commodetailQCMemo", "");
                    if (commoqcmemo == null) {
                        commoqcmemo = "";
                    }
                    String nullqcmemo;
                    if (commoqcmemo.equals(proitem)) {
                        nullqcmemo = "1";
                    } else {
                        nullqcmemo = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmemo", nullqcmemo);
                    spUtils.put(getApplicationContext(), "CommodityQCMemo", proitem);//QC特别备注
                }
            });
            tv_commodetail_QCMemo.setSelection(tv_commodetail_QCMemo.length());

            tv_commodetail_factlcdat.setEnabled(true);//离厂日期
            tv_commodetail_factlcdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_factlcdat.setText(datetime);
                                    String commofactlcdat = sp.getString("commodetailFactlcdat", "");
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals(datetime)) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(getApplicationContext(), "dateFactlcdattimesign", datetime);//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_factlcdat.setText("");
                                    String commofactlcdat = sp.getString("commodetailFactlcdat", "");
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals("")) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(getApplicationContext(), "dateFactlcdattimesign", "");//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_batchid.setEnabled(true);//查获批次
            tv_commodetail_batchid.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_batchid.getText().toString();
                    String commoBatchid = sp.getString("commodetailBatchid", "");
                    if (commoBatchid == null) {
                        commoBatchid = "";
                    }
                    String nullBatchid;
                    if (commoBatchid.equals(proitem)) {
                        nullBatchid = "1";
                    } else {
                        nullBatchid = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullBatchid", nullBatchid);
                    spUtils.put(getApplicationContext(), "CommodityBatchid", proitem);//查货批次
                }
            });
            tv_commodetail_batchid.setSelection(tv_commodetail_batchid.length());

            tv_commodetail_ctmchkdt.setEnabled(true);//业务员确认客查日期
            tv_commodetail_ctmchkdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_ctmchkdt.setText(datetime);
                                    String commoCtmchkdt = sp.getString("commodetailCtmchkdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(getApplicationContext(), "dateCtmchkdttimesign", datetime);//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_ctmchkdt.setText("");
                                    String commoCtmchkdt = sp.getString("commodetailCtmchkdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(getApplicationContext(), "dateCtmchkdttimesign", "");//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_IPQCPedt.setEnabled(true);//尾查预查
            tv_commodetail_IPQCPedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_IPQCPedt.setText(datetime);
                                    String commoCtmchkdt = sp.getString("commodetailIPQCPedt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcpedt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullipqcpedt = "1";
                                    } else {
                                        nullipqcpedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcpedt", nullipqcpedt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCPedt", datetime);//尾查预查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_IPQCPedt.setText("");
                                    String commoCtmchkdt = sp.getString("commodetailIPQCPedt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcpedt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullipqcpedt = "1";
                                    } else {
                                        nullipqcpedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcpedt", nullipqcpedt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCPedt", "");//尾查预查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_IPQCmdt.setEnabled(true);//巡检中查
            tv_commodetail_IPQCmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_IPQCmdt.setText(datetime);
                                    String commoCtmchkdt = sp.getString("commodetailIPQCmdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcmdt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullipqcmdt = "1";
                                    } else {
                                        nullipqcmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcmdt", nullipqcmdt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCmdt", datetime);//巡检中查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_IPQCmdt.setText("");
                                    String commoCtmchkdt = sp.getString("commodetailIPQCmdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcmdt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullipqcmdt = "1";
                                    } else {
                                        nullipqcmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcmdt", nullipqcmdt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCmdt", "");//巡检中查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_predoc.setEnabled(false);//产前会报告
            tv_commodetail_QCbdtDoc.setEnabled(false);//早期报告
            tv_commodetail_QCmdtDoc.setEnabled(false);//中期报告
            tv_commodetail_QCedtDoc.setEnabled(false);//尾期报告
            tv_commodetail_chker.setEnabled(false);//件查
            tv_commodetail_chkplace.setEnabled(false);//件查地址
            tv_commodetail_QAname.setEnabled(false);//QA首扎
            tv_commodetail_QAScore.setEnabled(false);//QA首扎件数
            btnCommoSave.setVisibility(View.VISIBLE);
        } else if (nameid.equals("陈慧萍")) {//判断登录人是否是陈慧萍（跟单负责人）
            tv_commodetail_ourAfter.setEnabled(true);//后道弹出选择菜单
            tv_commodetail_ourAfter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_commo_hd, popupMenu.getMenu());
                    // menu的item点击事件
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            String title = item.getTitle().toString();
                            if (commodetailOurAfter == null) {
                                commodetailOurAfter = "";
                            }
                            String nulltitle;
                            if (commodetailOurAfter.equals(title)) {
                                nulltitle = "1";//1是空的等于没有修改
                            } else {
                                nulltitle = "2";
                            }
                            spUtils.put(getApplicationContext(), "commonulltitle", nulltitle);
                            spUtils.put(getApplicationContext(), "commohdTitle", title);//后道
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
            });

            tv_commodetail_QCMasterScore.setEnabled(false);//生产主管评分

            tv_commodetail_sealedrev.setEnabled(true);//封样资料接收时间
            tv_commodetail_sealedrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_sealedrev.setText(datetime);
                                    String commosearledrev = sp.getString("commodetailSealedrev", "");
                                    if (commosearledrev == null) {
                                        commosearledrev = "";
                                    }
                                    String nullsearledrev;
                                    if (commosearledrev.equals(datetime)) {
                                        nullsearledrev = "1";
                                    } else {
                                        nullsearledrev = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsearledrev", nullsearledrev);
                                    spUtils.put(getApplicationContext(), "dateSealedrewtimesign", datetime);//封样资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                            "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_sealedrev.setText("");
                                    String commosearledrev = sp.getString("commodetailSealedrev", "");
                                    if (commosearledrev == null) {
                                        commosearledrev = "";
                                    }
                                    String nullsearledrev;
                                    if (commosearledrev.equals("")) {
                                        nullsearledrev = "1";
                                    } else {
                                        nullsearledrev = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsearledrev", nullsearledrev);
                                    spUtils.put(getApplicationContext(), "dateSealedrewtimesign", "");//封样资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_docback.setEnabled(true);//大货资料接收时间
            tv_commodetail_docback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_docback.setText(datetime);
                                    String commodocback = sp.getString("commodetailDocback", "");
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals(datetime)) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonulldocback", nulldocback);
                                    spUtils.put(getApplicationContext(), "dateDocbacktimesign", datetime);//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_docback.setText("");
                                    String commodocback = sp.getString("commodetailDocback", "");
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals("")) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonulldocback", nulldocback);
                                    spUtils.put(getApplicationContext(), "dateDocbacktimesign", "");//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_preMemo.setEnabled(true);//需要备注的特别情况
            tv_commodetail_preMemo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_preMemo.setText("");
                    }
                    String proitem = tv_commodetail_preMemo.getText().toString();
                    String commopromemo = sp.getString("commodetailPreMemo", "");
                    if (commopromemo == null) {
                        commopromemo = "";
                    }
                    String nullmemo;
                    if (commopromemo.equals(proitem)) {
                        nullmemo = "1";
                    } else {
                        nullmemo = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullmemo", nullmemo);
                    spUtils.put(getApplicationContext(), "CommodityPreMemo", proitem);//需要特别备注的情况
                }
            });
            tv_commodetail_preMemo.setSelection(tv_commodetail_preMemo.length());


            tv_commodetail_predocdt.setEnabled(true);//预计产前会报告时间
            tv_commodetail_predocdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_predocdt.setText(datetime);
                                    String commopreducdt = sp.getString("commodetailPredocdt", "");
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals(datetime)) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreducdt", nullpreducdt);
                                    spUtils.put(getApplicationContext(), "datePredocdttimesign", datetime);//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_predocdt.setText("");
                                    String commopreducdt = sp.getString("commodetailPredocdt", "");
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals("")) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreducdt", nullpreducdt);
                                    spUtils.put(getApplicationContext(), "datePredocdttimesign", "");//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_predt.setEnabled(true);//开产前会时间
            tv_commodetail_predt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_predt.setText(datetime);
                                    String commopred = sp.getString("commodetailPred", "");
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals(datetime)) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpred", nullpred);
                                    spUtils.put(getApplicationContext(), "datePredtimesign", datetime);//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_predt.setText("");
                                    String commopred = sp.getString("commodetailPred", "");
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals("")) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpred", nullpred);
                                    spUtils.put(getApplicationContext(), "datePredtimesign", "");//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_fabricsok.setEnabled(true);//大货面料情况
            tv_commodetail_fabricsok.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_fabricsok.setText("");
                    }
                    String proitem = tv_commodetail_fabricsok.getText().toString();
                    String commofabricsok = sp.getString("commodetailFabricsok", "");
                    if (commofabricsok == null) {
                        commofabricsok = "";
                    }
                    String nullfabricsok;
                    if (commofabricsok.equals(proitem)) {
                        nullfabricsok = "1";
                    } else {
                        nullfabricsok = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullfabricsok", nullfabricsok);
                    spUtils.put(getApplicationContext(), "CommodityFabricsok", proitem);//大货面料情况
                }
            });
            tv_commodetail_fabricsok.setSelection(tv_commodetail_fabricsok.length());

            tv_commodetail_accessoriesok.setEnabled(true);//大货辅料情况
            tv_commodetail_accessoriesok.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_accessoriesok.setText("");
                    }
                    String proitem = tv_commodetail_accessoriesok.getText().toString();
                    String commoaccessori = sp.getString("commodetailAccessoriesok", "");
                    if (commoaccessori == null) {
                        commoaccessori = "";
                    }
                    String nullaccessori;
                    if (commoaccessori.equals(proitem)) {
                        nullaccessori = "1";
                    } else {
                        nullaccessori = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullaccessori", nullaccessori);
                    spUtils.put(getApplicationContext(), "CommodityAccessoriesok", proitem);//大货辅料情况
                }
            });
            tv_commodetail_accessoriesok.setSelection(tv_commodetail_accessoriesok.length());

            tv_commodetail_spcproDec.setEnabled(true);//大货特殊工艺情况
            tv_commodetail_spcproDec.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_spcproDec.setText("");
                    }
                    String proitem = tv_commodetail_spcproDec.getText().toString();
                    String commospcprodec = sp.getString("commodetailSpcproDec", "");
                    if (commospcprodec == null) {
                        commospcprodec = "";
                    }
                    String nullspcprodec;
                    if (commospcprodec.equals(proitem)) {
                        nullspcprodec = "1";
                    } else {
                        nullspcprodec = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullspcprodec", nullspcprodec);
                    spUtils.put(getApplicationContext(), "CommoditySpcproDec", proitem);//大货特殊工艺情况
                }
            });
            tv_commodetail_spcproDec.setSelection(tv_commodetail_spcproDec.length());

            tv_commodetail_spcproMemo.setEnabled(true);//特殊工艺备注
            tv_commodetail_spcproMemo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_spcproMemo.setText("");
                    }
                    String proitem = tv_commodetail_spcproMemo.getText().toString();
                    String commospcpromemo = sp.getString("commodetailSpcproMemo", "");
                    if (commospcpromemo == null) {
                        commospcpromemo = "";
                    }
                    String nullspcpromemo;
                    if (commospcpromemo.equals(proitem)) {
                        nullspcpromemo = "1";
                    } else {
                        nullspcpromemo = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullspcpromemo", nullspcpromemo);
                    spUtils.put(getApplicationContext(), "CommoditySpcproMemo", proitem);//特殊工艺特别备注
                }
            });
            tv_commodetail_spcproMemo.setSelection(tv_commodetail_spcproMemo.length());

            tv_commodetail_cutqty.setEnabled(true);//实裁数
            tv_commodetail_cutqty.setFilters(new
                    InputFilter[]{new InputFilter.LengthFilter(10)});
            tv_commodetail_cutqty.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_cutqty.getText().toString();
                    String commocutqty = sp.getString("commodetailCutqty", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullcutqty;
                    if (commocutqty.equals(proitem)) {
                        nullcutqty = "1";
                    } else {
                        nullcutqty = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullcutqty", nullcutqty);
                    spUtils.put(getApplicationContext(), "CommodityCutqty", proitem);//实裁数
                }
            });
            tv_commodetail_cutqty.setSelection(tv_commodetail_cutqty.length());

            tv_commodetail_sewFdt.setEnabled(true);//上线日期
            tv_commodetail_sewFdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_sewFdt.setText(datetime);
                                    String commosewfdt = sp.getString("commodetailSewFdt", "");
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals(datetime)) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewfdt", nullsewfdt);
                                    spUtils.put(getApplicationContext(), "dateSewFdttimesign", datetime);//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_sewFdt.setText("");
                                    String commosewfdt = sp.getString("commodetailSewFdt", "");
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals("")) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewfdt", nullsewfdt);
                                    spUtils.put(getApplicationContext(), "dateSewFdttimesign", "");//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_sewMdt.setEnabled(true);//下线日期
            tv_commodetail_sewMdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_sewMdt.setText(datetime);
                                    String commosewmdt = sp.getString("commodetailSewMdt", "");
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals(datetime)) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewmdt", nullsewmdt);
                                    spUtils.put(getApplicationContext(), "dateSewMdttimesign", datetime);//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_sewMdt.setText("");
                                    String commosewmdt = sp.getString("commodetailSewMdt", "");
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals("")) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullsewmdt", nullsewmdt);
                                    spUtils.put(getApplicationContext(), "dateSewMdttimesign", "");//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_prebdt.setEnabled(true);//预计早期时间
            tv_commodetail_prebdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_prebdt.setText(datetime);
                                    String commoprebdt = sp.getString("commodetailPrebdt", "");
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals(datetime)) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullprebdt", nullprebdt);
                                    spUtils.put(getApplicationContext(), "datePrebdttimesign", datetime);//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_prebdt.setText("");
                                    String commoprebdt = sp.getString("commodetailPrebdt", "");
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals("")) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullprebdt", nullprebdt);
                                    spUtils.put(getApplicationContext(), "datePrebdttimesign", "");//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QCbdt.setEnabled(true);//自查早期时间
            tv_commodetail_QCbdt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_QCbdt.getText().toString();
                    String commocutqty = sp.getString("commodetailQCbdt", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullqcbdt;
                    if (commocutqty.equals(proitem)) {
                        nullqcbdt = "1";
                    } else {
                        nullqcbdt = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcbdt", nullqcbdt);
                    spUtils.put(getApplicationContext(), "dateQCbdttimesign", proitem);//自查早期时间
                }
            });
            tv_commodetail_QCbdt.setSelection(tv_commodetail_QCbdt.length());

            tv_commodetail_premdt.setEnabled(true);//预计中期时间
            tv_commodetail_premdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_premdt.setText(datetime);
                                    String commopremdt = sp.getString("commodetailPremdt", "");
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals(datetime)) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpremdt", nullpremdt);
                                    spUtils.put(getApplicationContext(), "datePremdttimesign", datetime);//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_premdt.setText("");
                                    String commopremdt = sp.getString("commodetailPremdt", "");
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals("")) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpremdt", nullpremdt);
                                    spUtils.put(getApplicationContext(), "datePremdttimesign", "");//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QCmdt.setEnabled(true);//自查中期时间
            tv_commodetail_QCmdt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_QCmdt.getText().toString();
                    String commocutqty = sp.getString("commodetailQCmdt", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullqcmdt;
                    if (commocutqty.equals(proitem)) {
                        nullqcmdt = "1";
                    } else {
                        nullqcmdt = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmdt", nullqcmdt);
                    spUtils.put(getApplicationContext(), "dateQCmdttimesign", proitem);//自查中期时间
                }
            });
            tv_commodetail_QCmdt.setSelection(tv_commodetail_QCmdt.length());

            tv_commodetail_preedt.setEnabled(true);//预计尾期时间
            tv_commodetail_preedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_preedt.setText(datetime);
                                    String commopreedt = sp.getString("commodetailPreedt", "");
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals(datetime)) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreedt", nullpreedt);
                                    spUtils.put(getApplicationContext(), "datePreedttimesign", datetime);//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_preedt.setText("");
                                    String commopreedt = sp.getString("commodetailPreedt", "");
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals("")) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpreedt", nullpreedt);
                                    spUtils.put(getApplicationContext(), "datePreedttimesign", "");//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QCMedt.setEnabled(true);//自查尾期时间
            tv_commodetail_QCMedt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_QCmdt.getText().toString();
                    String commocutqty = sp.getString("commodetailQCmdt", "");
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullqcmedt;
                    if (commocutqty.equals(proitem)) {
                        nullqcmedt = "1";
                    } else {
                        nullqcmedt = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmedt", nullqcmedt);
                    spUtils.put(getApplicationContext(), "dateQCMedttimesign", proitem);//自查尾期时间
                }
            });
            tv_commodetail_QCMedt.setSelection(tv_commodetail_QCMedt.length());

            tv_commodetail_fctmdt.setEnabled(true);//客查中期时间
            tv_commodetail_fctmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_fctmdt.setText(datetime);
                                    String commofctmdt = sp.getString("commodetailFctmdt", "");
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals(datetime)) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctmdt", nullfctmdt);
                                    spUtils.put(getApplicationContext(), "dateFctmdttimesign", datetime);//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_fctmdt.setText("");
                                    String commofctmdt = sp.getString("commodetailFctmdt", "");
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals("")) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctmdt", nullfctmdt);
                                    spUtils.put(getApplicationContext(), "dateFctmdttimesign", "");//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_fctedt.setEnabled(true);//客查尾期时间
            tv_commodetail_fctedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_fctedt.setText(datetime);
                                    String commofctedt = sp.getString("commodetailFctedt", "");
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals(datetime)) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctedt", nullfctedt);
                                    spUtils.put(getApplicationContext(), "dateFctedttimesign", datetime);//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_fctedt.setText("");
                                    String commofctedt = sp.getString("commodetailFctedt", "");
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals("")) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfctedt", nullfctedt);
                                    spUtils.put(getApplicationContext(), "dateFctedttimesign", "");//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_packbdat.setEnabled(true);//成品包装开始时间
            tv_commodetail_packbdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_packbdat.setText(datetime);
                                    String commopackbdat = sp.getString("commodetailPackbdat", "");
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals(datetime)) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpackbdat", nullpackbdat);
                                    spUtils.put(getApplicationContext(), "datePackbdattimesign", datetime);//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_packbdat.setText("");
                                    String commopackbdat = sp.getString("commodetailPackbdat", "");
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals("")) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullpackbdat", nullpackbdat);
                                    spUtils.put(getApplicationContext(), "datePackbdattimesign", "");//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_packqty2.setEnabled(true);//装箱数量
            tv_commodetail_packqty2.setFilters(new
                    InputFilter[]{new InputFilter.LengthFilter(10)});
            tv_commodetail_packqty2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_packqty2.getText().toString();
                    String commopackqty2 = sp.getString("commoPackqty2", "");
                    if (commopackqty2 == null) {
                        commopackqty2 = "";
                    }
                    String nullpackqty2;
                    if (commopackqty2.equals(proitem)) {
                        nullpackqty2 = "1";
                    } else {
                        nullpackqty2 = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullpackqty2", nullpackqty2);
                    spUtils.put(getApplicationContext(), "CommodityPackqty2", proitem);//装箱数量
                }
            });
            tv_commodetail_packqty2.setSelection(tv_commodetail_packqty2.length());

            tv_commodetail_QCMemo.setEnabled(true);//QC特别备注
            tv_commodetail_QCMemo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_QCMemo.setText("");
                    }
                    String proitem = tv_commodetail_QCMemo.getText().toString();
                    String commoqcmemo = sp.getString("commodetailQCMemo", "");
                    if (commoqcmemo == null) {
                        commoqcmemo = "";
                    }
                    String nullqcmemo;
                    if (commoqcmemo.equals(proitem)) {
                        nullqcmemo = "1";
                    } else {
                        nullqcmemo = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmemo", nullqcmemo);
                    spUtils.put(getApplicationContext(), "CommodityQCMemo", proitem);//QC特别备注
                }
            });
            tv_commodetail_QCMemo.setSelection(tv_commodetail_QCMemo.length());

            tv_commodetail_factlcdat.setEnabled(true);//离厂日期
            tv_commodetail_factlcdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_factlcdat.setText(datetime);
                                    String commofactlcdat = sp.getString("commodetailFactlcdat", "");
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals(datetime)) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(getApplicationContext(), "dateFactlcdattimesign", datetime);//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_factlcdat.setText("");
                                    String commofactlcdat = sp.getString("commodetailFactlcdat", "");
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals("")) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(getApplicationContext(), "dateFactlcdattimesign", "");//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_batchid.setEnabled(true);//查获批次
            tv_commodetail_batchid.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    String proitem = tv_commodetail_batchid.getText().toString();
                    String commoBatchid = sp.getString("commodetailBatchid", "");
                    if (commoBatchid == null) {
                        commoBatchid = "";
                    }
                    String nullBatchid;
                    if (commoBatchid.equals(proitem)) {
                        nullBatchid = "1";
                    } else {
                        nullBatchid = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullBatchid", nullBatchid);
                    spUtils.put(getApplicationContext(), "CommodityBatchid", proitem);//查货批次
                }
            });
            tv_commodetail_batchid.setSelection(tv_commodetail_batchid.length());

            tv_commodetail_ctmchkdt.setEnabled(true);//业务员确认客查日期
            tv_commodetail_ctmchkdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_ctmchkdt.setText(datetime);
                                    String commoCtmchkdt = sp.getString("commodetailCtmchkdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(getApplicationContext(), "dateCtmchkdttimesign", datetime);//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_ctmchkdt.setText("");
                                    String commoCtmchkdt = sp.getString("commodetailCtmchkdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(getApplicationContext(), "dateCtmchkdttimesign", "");//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_IPQCPedt.setEnabled(true);//尾查预查
            tv_commodetail_IPQCPedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_IPQCPedt.setText(datetime);
                                    String commoCtmchkdt = sp.getString("commodetailIPQCPedt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcpedt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullipqcpedt = "1";
                                    } else {
                                        nullipqcpedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcpedt", nullipqcpedt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCPedt", datetime);//尾查预查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_IPQCPedt.setText("");
                                    String commoCtmchkdt = sp.getString("commodetailIPQCPedt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcpedt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullipqcpedt = "1";
                                    } else {
                                        nullipqcpedt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcpedt", nullipqcpedt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCPedt", "");//尾查预查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_IPQCmdt.setEnabled(true);//巡检中查
            tv_commodetail_IPQCmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_IPQCmdt.setText(datetime);
                                    String commoCtmchkdt = sp.getString("commodetailIPQCmdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcmdt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullipqcmdt = "1";
                                    } else {
                                        nullipqcmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcmdt", nullipqcmdt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCmdt", datetime);//巡检中查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_IPQCmdt.setText("");
                                    String commoCtmchkdt = sp.getString("commodetailIPQCmdt", "");
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullipqcmdt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullipqcmdt = "1";
                                    } else {
                                        nullipqcmdt = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullipqcmdt", nullipqcmdt);
                                    spUtils.put(getApplicationContext(), "CommodityIPQCmdt", "");//巡检中查
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_QAname.setEnabled(false);//QA首扎
            tv_commodetail_QAname.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_QAname.setText("");
                    }
                    String proitem = tv_commodetail_QAname.getText().toString();
                    String commoQaname = sp.getString("commoQAname", "");
                    if (commoQaname == null) {
                        commoQaname = "";
                    }
                    String nullqaname;
                    if (commoQaname.equals(proitem)) {
                        nullqaname = "1";
                    } else {
                        nullqaname = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqaname", nullqaname);
                    spUtils.put(getApplicationContext(), "CommodityQAname", proitem);//QA首扎
                }
            });
            tv_commodetail_QAname.setSelection(tv_commodetail_QAname.length());

            tv_commodetail_QAScore.setEnabled(false);//QA首扎件数
            tv_commodetail_QAScore.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_QAScore.setText("");
                    }
                    String proitem = tv_commodetail_QAScore.getText().toString();
                    String commoqascore = sp.getString("commodetailQAScore", "");//QA首扎件数
                    if (commoqascore == null) {
                        commoqascore = "";
                    }
                    String nullqascore;
                    if (commoqascore.equals(proitem)) {
                        nullqascore = "1";
                    } else {
                        nullqascore = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqascore", nullqascore);
                    spUtils.put(getApplicationContext(), "CommodityQAScore", proitem);//QA首扎件数
                }
            });
            tv_commodetail_QAScore.setSelection(tv_commodetail_QAScore.length());

            tv_commodetail_QAMemo.setEnabled(false);//QA首扎日期
            tv_commodetail_QAMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            QACworkDetailActivity.this, null,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    tv_commodetail_QAMemo.setText(datetime);
                                    String commoqamemo = sp.getString("commodetailQAMemo", "");//QA首扎日期
                                    if (commoqamemo == null) {
                                        commoqamemo = "";
                                    }
                                    String nullqamemo;
                                    if (commoqamemo.equals(datetime)) {
                                        nullqamemo = "1";
                                    } else {
                                        nullqamemo = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullqamemo", nullqamemo);
                                    spUtils.put(getApplicationContext(), "dateQAMemotimesign", datetime);//QA首扎日
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_commodetail_QAMemo.setText("");
                                    String commoqamemo = sp.getString("commodetailQAMemo", "");//QA首扎日期
                                    if (commoqamemo == null) {
                                        commoqamemo = "";
                                    }
                                    String nullqamemo;
                                    if (commoqamemo.equals("")) {
                                        nullqamemo = "1";
                                    } else {
                                        nullqamemo = "2";
                                    }
                                    spUtils.put(getApplicationContext(), "commonullqamemo", nullqamemo);
                                    spUtils.put(getApplicationContext(), "dateQAMemotimesign", "");//QA首扎日
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            tv_commodetail_predoc.setEnabled(true);//产前会报告
            tv_commodetail_predoc.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_predoc.setText("");
                    }
                    String proitem = tv_commodetail_predoc.getText().toString();
                    String commopredoc = sp.getString("commodetailPredoc", "");//产前会报告
                    if (commopredoc == null) {
                        commopredoc = "";
                    }
                    String nullpredoc;
                    if (commopredoc.equals(proitem)) {
                        nullpredoc = "1";
                    } else {
                        nullpredoc = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullpredoc", nullpredoc);
                    spUtils.put(getApplicationContext(), "CommodityPredoc", proitem);//产前会报告
                }
            });
            tv_commodetail_predoc.setSelection(tv_commodetail_predoc.length());

            tv_commodetail_QCbdtDoc.setEnabled(true);//早期报告
            tv_commodetail_QCbdtDoc.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_QCbdtDoc.setText("");
                    }
                    String proitem = tv_commodetail_QCbdtDoc.getText().toString();
                    String commoqcbdtdoc = sp.getString("commodetailQCbdtDoc", "");//早期报告
                    if (commoqcbdtdoc == null) {
                        commoqcbdtdoc = "";
                    }
                    String nullqcbdtdoc;
                    if (commoqcbdtdoc.equals(proitem)) {
                        nullqcbdtdoc = "1";
                    } else {
                        nullqcbdtdoc = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcbdtdoc", nullqcbdtdoc);
                    spUtils.put(getApplicationContext(), "Commodityqcbdtdoc", proitem);//早期报告
                }
            });
            tv_commodetail_QCbdtDoc.setSelection(tv_commodetail_QCbdtDoc.length());

            tv_commodetail_QCmdtDoc.setEnabled(true);//中期报告
            tv_commodetail_QCmdtDoc.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_QCmdtDoc.setText("");
                    }
                    String proitem = tv_commodetail_QCmdtDoc.getText().toString();
                    String commoqcmdtdoc = sp.getString("commodetailQCmdtDoc", "");//中期报告
                    if (commoqcmdtdoc == null) {
                        commoqcmdtdoc = "";
                    }
                    String nullqcmdtdoc;
                    if (commoqcmdtdoc.equals(proitem)) {
                        nullqcmdtdoc = "1";
                    } else {
                        nullqcmdtdoc = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcmdtdoc", nullqcmdtdoc);
                    spUtils.put(getApplicationContext(), "CommodityQCmdtDoc", proitem);//中期报告
                }
            });
            tv_commodetail_QCmdtDoc.setSelection(tv_commodetail_QCmdtDoc.length());

            tv_commodetail_QCedtDoc.setEnabled(true);//尾期报告
            tv_commodetail_QCedtDoc.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d(TAG, "beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged");
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d(TAG, "afterTextChanged");
                    if(s.toString().equals("\n")||s.toString().equals(" ")){
                        tv_commodetail_QCedtDoc.setText("");
                    }
                    String proitem = tv_commodetail_QCedtDoc.getText().toString();
                    String commoqcedtdoc = sp.getString("commodetailQCedtDoc", "");//尾期报告
                    if (commoqcedtdoc == null) {
                        commoqcedtdoc = "";
                    }
                    String nullqcedtdoc;
                    if (commoqcedtdoc.equals(proitem)) {
                        nullqcedtdoc = "1";
                    } else {
                        nullqcedtdoc = "2";
                    }
                    spUtils.put(getApplicationContext(), "commonullqcedtdoc", nullqcedtdoc);
                    spUtils.put(getApplicationContext(), "CommodityQCedtDoc", proitem);//尾查报告
                }
            });

            tv_commodetail_chker.setEnabled(false);//件查
            tv_commodetail_chkplace.setEnabled(false);//件查地址
            btnCommoSave.setVisibility(View.VISIBLE);
        } else {
            btnCommoSave.setVisibility(View.GONE);
            tv_commodetail_ourAfter.setEnabled(false);
            tv_commodetail_QCMasterScore.setEnabled(false);//生产主管评分
            tv_commodetail_sealedrev.setEnabled(false);//封样资料接收时间
            tv_commodetail_docback.setEnabled(false);//大货资料接收时间
            tv_commodetail_predoc.setEnabled(false);//产前会报告
            tv_commodetail_QCbdtDoc.setEnabled(false);//早期报告
            tv_commodetail_QCmdtDoc.setEnabled(false);//中期报告
            tv_commodetail_QCedtDoc.setEnabled(false);//尾期报告
            tv_commodetail_chker.setEnabled(false);//件查
            tv_commodetail_QAname.setEnabled(false);//QA首扎
            tv_commodetail_QAScore.setEnabled(false);//QA首扎件数
            tv_commodetail_chkplace.setEnabled(false);//件查地址
            tv_commodetail_batchid.setEnabled(false);//查获批次
            tv_commodetail_QCMemo.setEnabled(false);//QC特别备注
            tv_commodetail_packqty2.setEnabled(false);//装箱数量
            tv_commodetail_QCMedt.setEnabled(false);//自查尾期时间
            tv_commodetail_QCmdt.setEnabled(false);//自查中期时间
            tv_commodetail_QCbdt.setEnabled(false);//自查早期时间
            tv_commodetail_cutqty.setEnabled(false);//实裁数
            tv_commodetail_fabricsok.setEnabled(false);//大货面料情况
            tv_commodetail_accessoriesok.setEnabled(false);//大货辅料情况
            tv_commodetail_spcproDec.setEnabled(false);//大货特殊工艺情况
            tv_commodetail_spcproMemo.setEnabled(false);//特殊工艺备注
            tv_commodetail_preMemo.setEnabled(false);//需要备注的特别情况
        }
    }

    /*列权限请求分配*/
    private void setColumRight() {
        sp = getSharedPreferences("my_sp", 0);
        String commologinid = sp.getString("commologinid", "");
        String args = "pd_saleslist,查货跟踪表," + commologinid;
        String idcolum = HttpUrl.debugoneUrl + "Common/GetClumns/?id=" + args;
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils
                    .get()
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
                                    String sfil = ("ll_PPSDetail_txt_" + columnlist.get(i));
                                    try {
                                        Field field = R.id.class.getField(sfil);
                                        int idd = field.getInt(new R.id());
                                        View view = findViewById(idd);
                                        view.setVisibility(View.VISIBLE);
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
                                                    String sfil = ("ll_PPSDetail_txt_" + jsonTextBeanlist.get(i).getColumnName());
                                                    try {
                                                        Field field = R.id.class.getField(sfil);
                                                        int idd = field.getInt(new R.id());
                                                        View view = findViewById(idd);
                                                        view.setVisibility(View.VISIBLE);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                } else if (jsonTextBeanlist.get(i).getName().equals("查看")) {
                                                    String sfil = ("ll_PPSDetail_txt_" + jsonTextBeanlist.get(i).getColumnName());
                                                    try {
                                                        Field field = R.id.class.getField(sfil);
                                                        int idd = field.getInt(new R.id());
                                                        View view = findViewById(idd);
                                                        view.setVisibility(View.VISIBLE);
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    String sfil = ("ll_PPSDetail_txt_" + jsonTextBeanlist.get(i).getColumnName());
                                                    try {
                                                        Field field = R.id.class.getField(sfil);
                                                        int idd = field.getInt(new R.id());
                                                        View view = findViewById(idd);
                                                        view.setVisibility(View.GONE);
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
                            setVisityQA();
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp,
                    QACworkDetailActivity.this);
        }
    }

    /*qa控件操作*/
    private void setVisityQA() {
//        sp = getSharedPreferences("my_sp", 0);
//        String qaname = tv_commodetail_QAname.getText().toString();
//        String commoname = sp.getString("commoname", "");
//        if (qaname.equals("") || qaname.equals(commoname)) {
//            ll_PPSDetail_txt_QAname.setVisibility(View.VISIBLE);
//            ll_PPSDetail_txt_QAScore.setVisibility(View.VISIBLE);
//            ll_PPSDetail_txt_QAMemo.setVisibility(View.VISIBLE);
//        } else {
//            ll_PPSDetail_txt_QAname.setVisibility(View.GONE);
//            ll_PPSDetail_txt_QAScore.setVisibility(View.GONE);
//            ll_PPSDetail_txt_QAMemo.setVisibility(View.GONE);
//        }
    }

    /*保存上传*/
    private void setSaveDate() {
        if (NetWork.isNetWorkAvailable(this)) {
            sp = getSharedPreferences("my_sp", 0);
            String saveurl = HttpUrl.debugoneUrl + "QACwork/SaveQACwork/";
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
            String commodetailproid = sp.getString("commodetailproid", "");
            String commodetailPrddocumentary =
                    sp.getString("commodetailPrddocumentary", "");
            String commodetailitem = sp.getString("commodetailitem", "");
            String commodetailCtmtxt = sp.getString("commodetailCtmtxt", "");
            String dateSealedrewtimesign = sp.getString("dateSealedrewtimesign", "");//修改的封样资料接收时间
            String commodetailSealedrev = sp.getString("commodetailSealedrev", "");//原本的封样资料接收时间
            String sealedrevstr;
            if (dateSealedrewtimesign.equals(commodetailSealedrev)) {
                sealedrevstr = commodetailSealedrev;
            } else {
                sealedrevstr = dateSealedrewtimesign;
            }

            String dateDocbacktimesign = sp.getString("dateDocbacktimesign", "");//修改的大货资料接收时间
            String commodetailDocback = sp.getString("commodetailDocback", "");//原本的大货资料接收时间
            String Docbackstr;
            if (dateDocbacktimesign.equals(commodetailDocback)) {
                Docbackstr = commodetailDocback;
            } else {
                Docbackstr = dateDocbacktimesign;
            }

            String datePredtimesign = sp.getString("datePredtimesign", "");//修改的开产前会时间
            String commodetailPred = sp.getString("commodetailPred", "");//原本的开产前会时间
            String predtstr;
            if (datePredtimesign.equals(commodetailPred)) {
                predtstr = commodetailPred;
            } else {
                predtstr = datePredtimesign;
            }

            String commodetailLcdat = sp.getString("commodetailLcdat", "");//出货时间
            String dateSewFdttimesign = sp.getString("dateSewFdttimesign", "");//修改的上线日期
            String commodetailSewFdt = sp.getString("commodetailSewFdt", "");//原本的上线日期
            String sewfdtstr;
            if (dateSewFdttimesign.equals(commodetailSewFdt)) {
                sewfdtstr = commodetailSewFdt;
            } else {
                sewfdtstr = dateSewFdttimesign;
            }

            String dateSewMdttimesign = sp.getString("dateSewMdttimesign", "");//修改的下线日期
            String commodetailSewMdt = sp.getString("commodetailSewMdt", "");//原本的下线日期
            String sewmdtstr;
            if (dateSewMdttimesign.equals(commodetailSewMdt)) {
                sewmdtstr = commodetailSewMdt;
            } else {
                sewmdtstr = dateSewMdttimesign;
            }

            String commodetailTaskqty = sp.getString("commodetailTaskqty", "");//制单数量
            String CommodityCutqty = sp.getString("CommodityCutqty", "");//修改的实裁数
            String commodetailCutqty = sp.getString("commodetailCutqty", "");//原本的实裁数
            String cutqtystr;
            if (CommodityCutqty.equals(commodetailCutqty)) {
                cutqtystr = commodetailCutqty;
            } else {
                cutqtystr = CommodityCutqty;
            }

            String CommodityPreMemo = sp.getString("CommodityPreMemo", "");//修改的需要备注的特殊情况
            String commodetailPreMemo = sp.getString("commodetailPreMemo", "");//原本的需要备注的特殊情况
            String prememostr;
            if (CommodityPreMemo.equals(commodetailPreMemo)) {
                prememostr = commodetailPreMemo;
            } else {
                prememostr = CommodityPreMemo;
            }

            String CommodityPredoc = sp.getString("CommodityPredoc", "");//修改的产前会报告
            String commodetailPredoc = sp.getString("commodetailPredoc", "");//原本的产前会报告
            String predocstr;
            if (CommodityPredoc.equals(commodetailPredoc)) {
                predocstr = commodetailPredoc;
            } else {
                predocstr = CommodityPredoc;
            }

            String CommodityFabricsok = sp.getString("CommodityFabricsok", "");//修改的大货面料情况
            String commodetailFabricsok = sp.getString("commodetailFabricsok", "");//原本的大货面料情况
            String fabricsokstr;
            if (CommodityFabricsok.equals(commodetailFabricsok)) {
                fabricsokstr = commodetailFabricsok;
            } else {
                fabricsokstr = CommodityFabricsok;
            }

            String CommodityAccessoriesok = sp.getString("CommodityAccessoriesok", "");//修改的大货辅料情况
            String commodetailAccessoriesok = sp.getString("commodetailAccessoriesok", "");//原本的大货辅料情况
            String accessoriesokstr;
            if (CommodityAccessoriesok.equals(commodetailAccessoriesok)) {
                accessoriesokstr = commodetailAccessoriesok;
            } else {
                accessoriesokstr = CommodityAccessoriesok;
            }

            String CommoditySpcproDec = sp.getString("CommoditySpcproDec", "");//修改的大货特殊工艺情况
            String commodetailSpcproDec = sp.getString("commodetailSpcproDec", "");//原本的特殊工艺情况
            String spcprodecstr;
            if (CommoditySpcproDec.equals(commodetailSpcproDec)) {
                spcprodecstr = commodetailSpcproDec;
            } else {
                spcprodecstr = CommoditySpcproDec;
            }

            String CommoditySpcproMemo = sp.getString("CommoditySpcproMemo", "");//修改的特殊工艺备注
            String commodetailSpcproMemo = sp.getString("commodetailSpcproMemo", "");//原本的特殊工艺备注
            String spcpromemostr;
            if (CommoditySpcproMemo.equals(commodetailSpcproMemo)) {
                spcpromemostr = commodetailSpcproMemo;
            } else {
                spcpromemostr = CommoditySpcproMemo;
            }

            String dateQCbdttimesign = sp.getString("dateQCbdttimesign", "");//修改的自查早期时间
            String commodetailQCbdt = sp.getString("commodetailQCbdt", "");//原本的自查早期时间
            String qcbdtstr;
            if (dateQCbdttimesign.equals(commodetailQCbdt)) {
                qcbdtstr = commodetailQCbdt;
            } else {
                qcbdtstr = dateQCbdttimesign;
            }

            String dateQCmdttimesign = sp.getString("dateQCmdttimesign", "");//修改的自查中期时间
            String commodetailQCmdt = sp.getString("commodetailQCmdt", "");//原本的自查中期时间
            String qcmdtstr;
            if (dateQCmdttimesign.equals(commodetailQCmdt)) {
                qcmdtstr = commodetailQCmdt;
            } else {
                qcmdtstr = dateQCmdttimesign;
            }

            String dateQCMedttimesign = sp.getString("dateQCMedttimesign", "");//修改的自查尾期时间
            String commodetailQCMedt = sp.getString("commodetailQCMedt", "");//原本的自查尾期时间
            String qcmedtstr;
            if (dateQCMedttimesign.equals(commodetailQCMedt)) {
                qcmedtstr = commodetailQCMedt;
            } else {
                qcmedtstr = dateQCMedttimesign;
            }

            String Commodityqcbdtdoc = sp.getString("Commodityqcbdtdoc", "");//修改的早期报告
            String commodetailQCbdtDoc = sp.getString("commodetailQCbdtDoc", "");//原本的早期报告
            String qcbdtdocstr;
            if (Commodityqcbdtdoc.equals(commodetailQCbdtDoc)) {
                qcbdtdocstr = commodetailQCbdtDoc;
            } else {
                qcbdtdocstr = Commodityqcbdtdoc;
            }

            String CommodityQCmdtDoc = sp.getString("CommodityQCmdtDoc", "");//修改的中期报告
            String commodetailQCmdtDoc = sp.getString("commodetailQCmdtDoc", "");//原本的中期报告
            String qcmdtdocstr;
            if (CommodityQCmdtDoc.equals(commodetailQCmdtDoc)) {
                qcmdtdocstr = commodetailQCmdtDoc;
            } else {
                qcmdtdocstr = CommodityQCmdtDoc;
            }

            String CommodityQCedtDoc = sp.getString("CommodityQCedtDoc", "");//修改的尾期报告
            String commodetailQCedtDoc = sp.getString("commodetailQCedtDoc", "");//原本的尾期报告
            String qcedtdocstr;
            if (CommodityQCedtDoc.equals(commodetailQCedtDoc)) {
                qcedtdocstr = commodetailQCedtDoc;
            } else {
                qcedtdocstr = CommodityQCedtDoc;
            }

            String dateFctmdttimesign = sp.getString("dateFctmdttimesign", "");//修改的客查中期时间
            String commodetailFctmdt = sp.getString("commodetailFctmdt", "");//原本的客查中期时间
            String fctmdtstr;
            if (dateFctmdttimesign.equals(commodetailFctmdt)) {
                fctmdtstr = commodetailFctmdt;
            } else {
                fctmdtstr = dateFctmdttimesign;
            }

            String dateFctedttimesign = sp.getString("dateFctedttimesign", "");//修改的客查尾期时间
            String commodetailFctedt = sp.getString("commodetailFctedt", "");//原本的客查尾期时间
            String fctedtstr;
            if (dateFctedttimesign.equals(commodetailFctedt)) {
                fctedtstr = commodetailFctedt;
            } else {
                fctedtstr = dateFctedttimesign;
            }

            String CommodityQCMemo = sp.getString("CommodityQCMemo", "");//修改的QC特别备注
            String commodetailQCMemo = sp.getString("commodetailQCMemo", "");//原本的QC特别备注
            String qcmemostr;
            if (CommodityQCMemo.equals(commodetailQCMemo)) {
                qcmemostr = commodetailQCMemo;
            } else {
                qcmemostr = CommodityQCMemo;
            }

            String datePackbdattimesign = sp.getString("datePackbdattimesign", "");//修改的成品包装开始日期
            String commodetailPackbdat = sp.getString("commodetailPackbdat", "");//原本的成品包装开始日期
            String packbdatstr;
            if (datePackbdattimesign.equals(commodetailPackbdat)) {
                packbdatstr = commodetailPackbdat;
            } else {
                packbdatstr = datePackbdattimesign;
            }

            String CommodityPackqty2 = sp.getString("CommodityPackqty2", "");//修改的装箱数量
            String commoPackqty2 = sp.getString("commoPackqty2", "");//原本的装箱数量
            String packqty2str;
            if (CommodityPackqty2.equals(commoPackqty2)) {
                packqty2str = commoPackqty2;
            } else {
                packqty2str = CommodityPackqty2;
            }

            String dateFactlcdattimesign = sp.getString("dateFactlcdattimesign", "");//修改的离厂日期
            String commodetailFactlcdat = sp.getString("commodetailFactlcdat", "");//原本的离厂日期
            String factlcdatstr;
            if (dateFactlcdattimesign.equals(commodetailFactlcdat)) {
                factlcdatstr = commodetailFactlcdat;
            } else {
                factlcdatstr = dateFactlcdattimesign;
            }

            String commohdTitle = sp.getString("commohdTitle", "");//修改的后道
            String commodetailOurAfter = sp.getString("commodetailOurAfter", "");//原本的后道
            String ourafterstr;
            if (commohdTitle.equals(commodetailOurAfter)) {
                ourafterstr = commodetailOurAfter;
            } else {
                ourafterstr = commohdTitle;
            }

            String commodetailprdmasterid = sp.getString("commodetailprdmasterid", "");//生产主管id
            String CommodityQCMasterScore = sp.getString("CommodityQCMasterScore", "");//修改的主管评分
            String commodetailQCMasterScore = sp.getString("commodetailQCMasterScore", "");//原本的主管评分
            String qcmasterscorestr;
            if (CommodityQCMasterScore.equals(commodetailQCMasterScore)) {
                qcmasterscorestr = commodetailQCMasterScore;
            } else {
                qcmasterscorestr = CommodityQCMasterScore;
            }

            String CommodityBatchid = sp.getString("CommodityBatchid", "");//修改的查货批次
            String commodetailBatchid = sp.getString("commodetailBatchid", "");//原本的查获批次
            String batchidstr;
            if (CommodityBatchid.equals(commodetailBatchid)) {
                batchidstr = commodetailBatchid;
            } else {
                batchidstr = CommodityBatchid;
            }

            String commoQAname = sp.getString("commoQAname", "");//QA首扎
            String commodetailfirstsamQA = sp.getString("commodetailfirstsamQA", "");//QA首扎改后
            String commodetailQAScore = sp.getString("commodetailQAScore", "");//QA首扎件数
            String commodetailfirstsamQAid = sp.getString("commodetailfirstsamQAid", "");
            String commodetailQAMemo = sp.getString("commodetailQAMemo", "");//QA首扎日期

            String dateCtmchkdttimesign = sp.getString("dateCtmchkdttimesign", "");//修改的业务员确认客查日期
            String commodetailCtmchkdt = sp.getString("commodetailCtmchkdt", "");//原本的业务员确认客查日期
            String ctmchkdtstr;
            if (dateCtmchkdttimesign.equals(commodetailCtmchkdt)) {
                ctmchkdtstr = commodetailCtmchkdt;
            } else {
                ctmchkdtstr = dateCtmchkdttimesign;
            }

            String commodetailIPQC = sp.getString("commodetailIPQC", "");//巡检
            String commodetailIPQCid = sp.getString("commodetailIPQCid", "");//巡检id

            String CommodityIPQCmdt = sp.getString("CommodityIPQCmdt", "");//修改的巡检中查
            String commodetailIPQCmdt = sp.getString("commodetailIPQCmdt", "");//原本的巡检中查
            String ipqcmdtstr;
            if (CommodityIPQCmdt.equals(commodetailIPQCmdt)) {
                ipqcmdtstr = commodetailIPQCmdt;
            } else {
                ipqcmdtstr = CommodityIPQCmdt;
            }

            String CommodityIPQCPedt = sp.getString("CommodityIPQCPedt", "");//修改的尾查预查
            String commodetailIPQCPedt = sp.getString("commodetailIPQCPedt", "");//原本的尾查预查
            String ipqcpedtstr;
            if (CommodityIPQCPedt.equals(commodetailIPQCPedt)) {
                ipqcpedtstr = commodetailIPQCPedt;
            } else {
                ipqcpedtstr = CommodityIPQCPedt;
            }

            String datePredocdttimesign = sp.getString("datePredocdttimesign", "");//修改的预计产前会报告时间
            String commodetailPredocdt = sp.getString("commodetailPredocdt", "");//原本的预计产前会报告时间
            String predocdtstr;
            if (datePredocdttimesign.equals(commodetailPredocdt)) {
                predocdtstr = commodetailPredocdt;
            } else {
                predocdtstr = datePredocdttimesign;
            }

            String datePrebdttimesign = sp.getString("datePrebdttimesign", "");//修改的预计早期时间
            String commodetailPrebdt = sp.getString("commodetailPrebdt", "");//原本的预计早期时间
            String prebdtstr;
            if (datePrebdttimesign.equals(commodetailPrebdt)) {
                prebdtstr = commodetailPrebdt;
            } else {
                prebdtstr = datePrebdttimesign;
            }

            String datePremdttimesign = sp.getString("datePremdttimesign", "");//修改的预计中期时间
            String commodetailPremdt = sp.getString("commodetailPremdt", "");//原本的预计中期时间
            String premdtstr;
            if (datePremdttimesign.equals(commodetailPremdt)) {
                premdtstr = commodetailPremdt;
            } else {
                premdtstr = datePremdttimesign;
            }

            String datePreedttimesign = sp.getString("datePreedttimesign", "");//修改的预计尾期时间
            String commodetailPreedt = sp.getString("commodetailPreedt", "");//原本的预计尾期时间
            String preedtstr;
            if (datePreedttimesign.equals(commodetailPreedt)) {
                preedtstr = commodetailPreedt;
            } else {
                preedtstr = datePreedttimesign;
            }

            String commodetailchker = sp.getString("commodetailchker", "");//件查
            String commodetailchkpdt = sp.getString("commodetailchkpdt", "");//预计件查时间
            String commodetailchkfctdt = sp.getString("commodetailchkfctdt", "");//实际件查时间
            String commodetailchkplace = sp.getString("commodetailchkplace", "");//件查地址
            List<QACworkDetailSaveBean.DataBean> commoditySaveBean =
                    new ArrayList<QACworkDetailSaveBean.DataBean>();
            QACworkDetailSaveBean saveBean = new QACworkDetailSaveBean();
            QACworkDetailSaveBean.DataBean dataBean = new QACworkDetailSaveBean.DataBean();
            dataBean.setID(Integer.parseInt(commodetailproid));//id
            dataBean.setSubfactory(commodetailPrddocumentary);//跟单
            dataBean.setItem(commodetailitem);//款号
            dataBean.setCtmtxt(commodetailCtmtxt);//客户
            dataBean.setSealedrev(sealedrevstr);//封样资料接收时间
            dataBean.setDocback(Docbackstr);//大货资料接收时间
            dataBean.setPredt(predtstr);//开产前会时间
            dataBean.setLcdat(commodetailLcdat);//出货时间
            dataBean.setSewFdt(sewfdtstr);//上线日期
            dataBean.setSewMdt(sewmdtstr);//下线日期
            dataBean.setTaskqty(commodetailTaskqty);//制单数量
            dataBean.setCutqty(cutqtystr);//实裁数
            dataBean.setPreMemo(prememostr);//需要备注的特殊情况
            dataBean.setPredoc(predocstr);//产前会报告
            dataBean.setFabricsok(fabricsokstr);//大货面料情况
            dataBean.setAccessoriesok(accessoriesokstr);//大货辅料情况
            dataBean.setSpcproDec(spcprodecstr);//特殊工艺情况
            dataBean.setSpcproMemo(spcpromemostr);//特殊工艺备注
            dataBean.setQCbdt(qcbdtstr);//自查早期时间
            dataBean.setQCmdt(qcmdtstr);//自查中期时间
            dataBean.setQCMedt(qcmedtstr);//自查尾期时间
            dataBean.setQCbdtDoc(qcbdtdocstr);//早期报告
            dataBean.setQCmdtDoc(qcmdtdocstr);//中期报告
            dataBean.setQCedtDoc(qcedtdocstr);//尾期报告
            dataBean.setFctmdt(fctmdtstr);//客查中期时间
            dataBean.setFctedt(fctedtstr);//客查尾期时间
            dataBean.setQCMemo(qcmemostr);//QC特别备注
            dataBean.setPackbdat(packbdatstr);//成品包装开始日期
            dataBean.setPackqty2(packqty2str);//装箱数量
            dataBean.setFactlcdat(factlcdatstr);//离厂日期
            dataBean.setOurAfter(ourafterstr);//后道
            dataBean.setPrdmaster(commodetailprdmaster);//生产主管
            dataBean.setPrdmasterid(commodetailprdmasterid);//生产主管id
            dataBean.setQCMasterScore(qcmasterscorestr);//主管评分
            dataBean.setBatchid(batchidstr);//查货批次
            dataBean.setQAname(commoQAname);//QA首扎
            dataBean.setFirstsamQA(commodetailfirstsamQA);//QA首扎改后
            dataBean.setFirstsamQAid(commodetailfirstsamQAid);
            dataBean.setQAScore(commodetailQAScore);//QA首扎件数
            dataBean.setQAMemo(commodetailQAMemo);//QA首扎日期
            dataBean.setCtmchkdt(ctmchkdtstr);//业务员确认客查日期
            dataBean.setIPQC(commodetailIPQC);//巡检
            dataBean.setIPQCid(commodetailIPQCid);//巡检id
            dataBean.setIPQCmdt(ipqcmdtstr);//巡检中查
            dataBean.setIPQCPedt(ipqcpedtstr);//尾查预查
            dataBean.setPredocdt(predocdtstr);//预计产前会报告时间
            dataBean.setPrebdt(prebdtstr);//预计早期时间
            dataBean.setPremdt(premdtstr);//预计中期时间
            dataBean.setPreedt(preedtstr);//预计尾期时间
            dataBean.setChker(commodetailchker);//件查
            dataBean.setChkpdt(commodetailchkpdt);//预计件查时间
            dataBean.setChkfctdt(commodetailchkfctdt);//实际件查时间
            dataBean.setChkplace(commodetailchkplace);//件查地址
            commoditySaveBean.add(dataBean);
            Gson gson = new Gson();
            String commjson = gson.toJson(commoditySaveBean);
            String dateee = commjson.replace("\"\"", "null");
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
                ToastUtils.ShowToastMessage("未修改表中数据", QACworkDetailActivity.this);
            } else if (commodetailPrddocumentary.equals(nameid)) {
                OkHttpUtils.postString()
                        .url(saveurl)
                        .content(dateee)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                ToastUtils.ShowToastMessage("数据错误，请重新输入", QACworkDetailActivity.this);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                System.out.print(response);
                                response = response.replace("\\", "");
                                String ression = StringUtil.sideTrim(response, "\"");
                                System.out.print(ression);
                                if (ression.equals("true")) {
                                    QACworkSearchActivity.QACworkinstance.finish();
                                    ToastUtils.ShowToastMessage("保存成功",
                                            QACworkDetailActivity.this);
                                    startActivity(new Intent(QACworkDetailActivity.this,
                                            QACworkSearchActivity.class));
                                    finish();
                                } else {
                                    ToastUtils.ShowToastMessage("保存失败",
                                            QACworkDetailActivity.this);
                                    deletesp();
                                }
                            }
                        });
            } else if (commodetailprdmaster.equals(nameid)) {
                OkHttpUtils.postString()
                        .url(saveurl)
                        .content(dateee)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                ToastUtils.ShowToastMessage("数据错误，请重新输入", QACworkDetailActivity.this);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                System.out.print(response);
                                response = response.replace("\\", "");
                                String ression = StringUtil.sideTrim(response, "\"");
                                System.out.print(ression);
                                if (ression.equals("true")) {
                                    QACworkSearchActivity.QACworkinstance.finish();
                                    ToastUtils.ShowToastMessage("保存成功",
                                            QACworkDetailActivity.this);
                                    startActivity(new Intent(QACworkDetailActivity.this,
                                            QACworkSearchActivity.class));
                                } else {
                                    ToastUtils.ShowToastMessage("保存失败",
                                            QACworkDetailActivity.this);
                                }
                                deletesp();
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage("当前用户不能修改本款号",
                        QACworkDetailActivity.this);
            }
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, QACworkDetailActivity.this);
        }
    }

    /*完成后删除保存的临时信息*/
    private void deletesp() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("commodetailproid");
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
        editor.remove("QACworkDialogprdmaster");
        editor.remove("QACworkDialogIPQC");
        editor.remove("QACworkDialogItem");

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

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("commodetailproid");
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

        editor.remove("QACworkDialogprdmaster");
        editor.remove("QACworkDialogIPQC");
        editor.remove("QACworkDialogItem");

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivCommoditySql:
                setBlacksp();
                //如果没有修改过数据，就直接退出。如果修改过则弹出框提示是否保存
                if (flagblackBoolean = true) {
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("保存提示");
                    builder.setMessage("退出是否保存");
                    builder.setPositiveButton("保存后退出"
                            , new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setSaveDate();
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                    builder.setNegativeButton("不保存，直接退出",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                    noticeDialog = builder.create();
                    noticeDialog.setCanceledOnTouchOutside(false);
                    noticeDialog.show();
                }
                break;
            case R.id.btnCommoSave:
                setSaveDate();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setBlacksp();
            if (flagblackBoolean == true) {
                finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("保存提示");
                builder.setMessage("退出是否保存");
                builder.setPositiveButton("保存后退出"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setSaveDate();
                                dialog.dismiss();
                                finish();
                            }
                        });
                builder.setNegativeButton("不保存，直接退出",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                noticeDialog = builder.create();
                noticeDialog.setCanceledOnTouchOutside(false);
                noticeDialog.show();
            }
        }
        return false;
    }
}