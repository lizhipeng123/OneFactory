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
import com.daoran.newfactory.onefactory.activity.work.ftydl.FTYDLSearchDetailActivity;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkPageDataBean;
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

    List<QACworkPageDataBean.DataBean> qaCworkDataBean =
            new ArrayList<QACworkPageDataBean.DataBean>();//查货跟踪数据

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

    //接收的数据变量
    private String commodetailproid, commoitem, commodetailCtmtxt, commodetailPrddocumentary,
            commodetailSubfactory, commodetailTaskqty, commodetailprdmaster, commodetailPrdmasterid,
            commodetailQCMasterScore, commodetailfirstsamQA, commodetailfirstsamQAid,
            commodetailSealedrev, commodetailDocback, commodetailLcdat, commodetailPreMemo,
            commodetailPredocdt, commodetailPred, commodetailPredoc, commodetailFabricsok,
            commodetailAccessoriesok, commodetailSpcproDec, commodetailSpcproMemo,
            commodetailCutqty, commodetailSewFdt, commodetailSewMdt, commodetailPrebdt,
            commodetailQCbdt, commodetailQCbdtDoc, commodetailPremdt, commodetailQCmdt,
            commodetailQCmdtDoc, commodetailPreedt, commodetailQCMedt,
            commodetailQCedtDoc, commodetailFctmdt, commodetailFctedt, commodetailPackbdat,
            commoPackqty2, commodetailQCMemo, commodetailFactlcdat, commodetailBatchid,
            commodetailCtmchkdt, commodetailIPQCPedt, commodetailIPQCmdt,
            commodetailIPQC, commoQAname, commodetailQAScore, commodetailQAMemo,
            commodetailchker, commodetailchkpdt, commodetailchkfctdt, commodetailchkplace,
            commodetailIPQCid;
    //本页面的存储变量
    private String nameid, commodetailOurAfter, saveboolean, dateSealedrewtimesign,
            dateDocbacktimesign, datePredtimesign, dateSewFdttimesign, dateSewMdttimesign,
            CommodityCutqty, CommodityPreMemo, CommodityPredoc, CommodityFabricsok,
            CommodityAccessoriesok, CommoditySpcproDec, CommoditySpcproMemo,
            dateQCbdttimesign, dateQCmdttimesign, dateQCMedttimesign, Commodityqcbdtdoc,
            CommodityQCmdtDoc, CommodityQCedtDoc,CommodityQCMemo,datePackbdattimesign,
            CommodityPackqty2,dateFactlcdattimesign,commohdTitle,CommodityQCMasterScore,
            CommodityBatchid,dateCtmchkdttimesign,CommodityIPQCmdt,
            CommodityIPQCPedt,datePredocdttimesign,datePrebdttimesign,
            datePremdttimesign,datePreedttimesign;
    //本地字符串变量
    private String sealedrevstr, Docbackstr, predtstr, sewfdtstr, sewmdtstr, cutqtystr,
            prememostr, predocstr, fabricsokstr, accessoriesokstr, spcprodecstr,
            spcpromemostr, qcbdtstr, qcmdtstr, qcmedtstr, qcbdtdocstr, qcmdtdocstr,
            qcedtdocstr, fctmdtstr, fctedtstr, dateFctmdttimesign, dateFctedttimesign,
            qcmemostr, packbdatstr, packqty2str, factlcdatstr, ourafterstr, qcmasterscorestr,
            batchidstr, ctmchkdtstr, ipqcmdtstr, ipqcpedtstr, predocdtstr, prebdtstr,
            premdtstr, preedtstr;
    private boolean flagblackBoolean;//是否修改过数据
    private int position;//数据位置索引

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qacwork_detail);
        setSpUtils(qaCworkDataBean);
        getViews();
        setColumRight();
        setViews();
        setListener();
    }

    /*获取传过来的数据*/
    private void setSpUtils(List<QACworkPageDataBean.DataBean> qaCworkDataBean) {
        sp = getSharedPreferences("my_sp", 0);
        nameid = sp.getString("usernamerecoder", "");
        position = Integer.parseInt(
                sp.getString("tvQACWorkDetailId", ""));//位置索引
        qaCworkDataBean = getIntent().getParcelableArrayListExtra("qaCworkDataBean");

        commodetailproid = String.valueOf(qaCworkDataBean.get(position).getID());//id
        commoitem = qaCworkDataBean.get(position).getItem();//款号
        if (qaCworkDataBean.get(position).getCtmtxt() == null) {//客户
            commodetailCtmtxt = "";
        } else {
            commodetailCtmtxt = qaCworkDataBean.get(position).getCtmtxt();
        }

        if (qaCworkDataBean.get(position).getPrddocumentary() == null) {//跟单
            commodetailPrddocumentary = "";
        } else {
            commodetailPrddocumentary = qaCworkDataBean.get(position).getPrddocumentary();
        }

        if (qaCworkDataBean.get(position).getSubfactory() == null) {//工厂
            commodetailSubfactory = "";
        } else {
            commodetailSubfactory = qaCworkDataBean.get(position).getSubfactory();
        }

        if (qaCworkDataBean.get(position).getTaskqty() == null) {//制单数量
            commodetailTaskqty = "";
        } else {
            commodetailTaskqty = qaCworkDataBean.get(position).getTaskqty();
        }

        if (qaCworkDataBean.get(position).getPrdmaster() == null) {//生产主管
            commodetailprdmaster = "";
        } else {
            commodetailprdmaster = qaCworkDataBean.get(position).getPrdmaster();
        }

        if (qaCworkDataBean.get(position).getPrdmasterid() == null) {//生产主管id
            commodetailPrdmasterid = "";
        } else {
            commodetailPrdmasterid = qaCworkDataBean.get(position).getPrdmasterid();
        }

        if (qaCworkDataBean.get(position).getQCMasterScore() == null) {//主管评分
            commodetailQCMasterScore = "";
        } else {
            commodetailQCMasterScore = qaCworkDataBean.get(position).getQCMasterScore();
        }

        if (qaCworkDataBean.get(position).getSealedrev() == null) {//封样资料接收时间
            commodetailSealedrev = "";
        } else {
            commodetailSealedrev = qaCworkDataBean.get(position).getSealedrev();
        }

        if (qaCworkDataBean.get(position).getDocback() == null) {//大货资料接收时间
            commodetailDocback = "";
        } else {
            commodetailDocback = qaCworkDataBean.get(position).getDocback();
        }

        if (qaCworkDataBean.get(position).getLcdat() == null) {//出货时间
            commodetailLcdat = "";
        } else {
            commodetailLcdat = qaCworkDataBean.get(position).getLcdat();
        }

        if (qaCworkDataBean.get(position).getPreMemo() == null) {//特别备注情况
            commodetailPreMemo = "";
        } else {
            commodetailPreMemo = qaCworkDataBean.get(position).getPreMemo();
        }

        if (qaCworkDataBean.get(position).getPredocdt() == null) {//预计产前会报告时间
            commodetailPredocdt = "";
        } else {
            commodetailPredocdt = qaCworkDataBean.get(position).getPredocdt();
        }

        if (qaCworkDataBean.get(position).getPredt() == null) {//开产前会时间
            commodetailPred = "";
        } else {
            commodetailPred = qaCworkDataBean.get(position).getPredt();
        }

        if (qaCworkDataBean.get(position).getPredoc() == null) {//产前会报告
            commodetailPredoc = "";
        } else {
            commodetailPredoc = qaCworkDataBean.get(position).getPredoc();
        }

        if (qaCworkDataBean.get(position).getFabricsok() == null) {//大货面料情况
            commodetailFabricsok = "";
        } else {
            commodetailFabricsok = qaCworkDataBean.get(position).getFabricsok();
        }

        if (qaCworkDataBean.get(position).getAccessoriesok() == null) {//大货辅料情况
            commodetailAccessoriesok = "";
        } else {
            commodetailAccessoriesok = qaCworkDataBean.get(position).getAccessoriesok();
        }

        if (qaCworkDataBean.get(position).getSpcproDec() == null) {//特殊工艺情况
            commodetailSpcproDec = "";
        } else {
            commodetailSpcproDec = qaCworkDataBean.get(position).getSpcproDec();
        }

        if (qaCworkDataBean.get(position).getSpcproMemo() == null) {//特殊工艺备注
            commodetailSpcproMemo = "";
        } else {
            commodetailSpcproMemo = qaCworkDataBean.get(position).getSpcproMemo();
        }

        if (qaCworkDataBean.get(position).getCutqty() == null) {//实裁数
            commodetailCutqty = "";
        } else {
            commodetailCutqty = qaCworkDataBean.get(position).getCutqty();
        }

        if (qaCworkDataBean.get(position).getSewFdt() == null) {//上线日期
            commodetailSewFdt = "";
        } else {
            commodetailSewFdt = qaCworkDataBean.get(position).getSewFdt();
        }

        if (qaCworkDataBean.get(position).getSewMdt() == null) {//下线日期
            commodetailSewMdt = "";
        } else {
            commodetailSewMdt = qaCworkDataBean.get(position).getSewMdt();
        }

        if (qaCworkDataBean.get(position).getPrebdt() == null) {//预计早期时间
            commodetailPrebdt = "";
        } else {
            commodetailPrebdt = qaCworkDataBean.get(position).getPrebdt();
        }

        if (qaCworkDataBean.get(position).getQCbdt() == null) {//自查早期时间
            commodetailQCbdt = "";
        } else {
            commodetailQCbdt = qaCworkDataBean.get(position).getQCbdt();
        }

        if (qaCworkDataBean.get(position).getQCbdtDoc() == null) {//早期报告
            commodetailQCbdtDoc = "";
        } else {
            commodetailQCbdtDoc = qaCworkDataBean.get(position).getQCbdtDoc();
        }

        if (qaCworkDataBean.get(position).getPremdt() == null) {//预计中期时间
            commodetailPremdt = "";
        } else {
            commodetailPremdt = qaCworkDataBean.get(position).getPremdt();
        }

        if (qaCworkDataBean.get(position).getQCmdt() == null) {//自查中期时间
            commodetailQCmdt = "";
        } else {
            commodetailQCmdt = qaCworkDataBean.get(position).getQCmdt();
        }

        if (qaCworkDataBean.get(position).getQCmdtDoc() == null) {//中期报告
            commodetailQCmdtDoc = "";
        } else {
            commodetailQCmdtDoc = qaCworkDataBean.get(position).getQCmdtDoc();
        }

        if (qaCworkDataBean.get(position).getPreedt() == null) {//预计尾期时间
            commodetailPreedt = "";
        } else {
            commodetailPreedt = qaCworkDataBean.get(position).getPreedt();
        }

        if (qaCworkDataBean.get(position).getQCMedt() == null) {//自查尾期时间
            commodetailQCMedt = "";
        } else {
            commodetailQCMedt = qaCworkDataBean.get(position).getQCMedt();
        }

        if (qaCworkDataBean.get(position).getQCedtDoc() == null) {//尾期报告
            commodetailQCedtDoc = "";
        } else {
            commodetailQCedtDoc = qaCworkDataBean.get(position).getQCedtDoc();
        }

        if (qaCworkDataBean.get(position).getFctmdt() == null) {//客查中期时间
            commodetailFctmdt = "";
        } else {
            commodetailFctmdt = qaCworkDataBean.get(position).getFctmdt();
        }

        if (qaCworkDataBean.get(position).getFctedt() == null) {//客查尾期时间
            commodetailFctedt = "";
        } else {
            commodetailFctedt = qaCworkDataBean.get(position).getFctedt();
        }

        if (qaCworkDataBean.get(position).getPackbdat() == null) {//成品包装开始时间
            commodetailPackbdat = "";
        } else {
            commodetailPackbdat = qaCworkDataBean.get(position).getPackbdat();
        }

        if (qaCworkDataBean.get(position).getPackqty2() == null) {//装箱数量
            commoPackqty2 = "";
        } else {
            commoPackqty2 = qaCworkDataBean.get(position).getPackqty2();
        }

        if (qaCworkDataBean.get(position).getQCMemo() == null) {//QC特别备注
            commodetailQCMemo = "";
        } else {
            commodetailQCMemo = qaCworkDataBean.get(position).getQCMemo();
        }

        if (qaCworkDataBean.get(position).getFactlcdat() == null) {//离厂日期
            commodetailFactlcdat = "";
        } else {
            commodetailFactlcdat = qaCworkDataBean.get(position).getFactlcdat();
        }

        if (qaCworkDataBean.get(position).getBatchid() == null) {//查货批次
            commodetailBatchid = "";
        } else {
            commodetailBatchid = qaCworkDataBean.get(position).getBatchid();
        }

        if (qaCworkDataBean.get(position).getOurAfter() == null) {//后道
            commodetailOurAfter = "";
        } else {
            commodetailOurAfter = qaCworkDataBean.get(position).getOurAfter();
        }

        if (qaCworkDataBean.get(position).getCtmchkdt() == null) {//业务员确认客查日期
            commodetailCtmchkdt = "";
        } else {
            commodetailCtmchkdt = qaCworkDataBean.get(position).getCtmchkdt();
        }

        if (qaCworkDataBean.get(position).getIPQCPedt() == null) {//尾查预查
            commodetailIPQCPedt = "";
        } else {
            commodetailIPQCPedt = qaCworkDataBean.get(position).getIPQCPedt();
        }

        if (qaCworkDataBean.get(position).getIPQCmdt() == null) {//巡检中查
            commodetailIPQCmdt = "";
        } else {
            commodetailIPQCmdt = qaCworkDataBean.get(position).getIPQCmdt();
        }

        if (qaCworkDataBean.get(position).getIPQC() == null) {//巡检
            commodetailIPQC = "";
        } else {
            commodetailIPQC = qaCworkDataBean.get(position).getIPQC();
        }

        if (qaCworkDataBean.get(position).getQAname() == null) {//QA首扎
            commoQAname = "";
        } else {
            commoQAname = qaCworkDataBean.get(position).getQAname();
        }

        if (qaCworkDataBean.get(position).getQAScore() == null) {//QA首扎件数
            commodetailQAScore = "";
        } else {
            commodetailQAScore = qaCworkDataBean.get(position).getQAScore();
        }

        if (qaCworkDataBean.get(position).getQAMemo() == null) {//QA首扎日期
            commodetailQAMemo = "";
        } else {
            commodetailQAMemo = qaCworkDataBean.get(position).getQAMemo();
        }

        if (qaCworkDataBean.get(position).getChker() == null) {//件查
            commodetailchker = "";
        } else {
            commodetailchker = qaCworkDataBean.get(position).getChker();
        }

        if (qaCworkDataBean.get(position).getChkpdt() == null) {//预计件查时间
            commodetailchkpdt = "";
        } else {
            commodetailchkpdt = qaCworkDataBean.get(position).getChkpdt();
        }

        if (qaCworkDataBean.get(position).getChkfctdt() == null) {//实际件查时间
            commodetailchkfctdt = "";
        } else {
            commodetailchkfctdt = qaCworkDataBean.get(position).getChkfctdt();
        }

        if (qaCworkDataBean.get(position).getChkplace() == null) {//件查地址
            commodetailchkplace = "";
        } else {
            commodetailchkplace = qaCworkDataBean.get(position).getChkplace();
        }

        if (qaCworkDataBean.get(position).getFirstsamQA() == null) {//
            commodetailfirstsamQA = "";
        } else {
            commodetailfirstsamQA = qaCworkDataBean.get(position).getFirstsamQA();
        }

        if (qaCworkDataBean.get(position).getFirstsamQAid() == null) {
            commodetailfirstsamQAid = "";
        } else {
            commodetailfirstsamQAid = qaCworkDataBean.get(position).getFirstsamQAid();
        }

        if (qaCworkDataBean.get(position).getIPQCid() == null) {
            commodetailIPQCid = "";
        } else {
            commodetailIPQCid = qaCworkDataBean.get(position).getIPQCid();
        }
    }

    /*本地修改的数据*/
    private void setLocalSpUtils() {
        sp = getSharedPreferences("my_sp", 0);
        dateSealedrewtimesign = sp.getString("dateSealedrewtimesign", "");//修改的封样资料接收时间
        dateDocbacktimesign = sp.getString("dateDocbacktimesign", "");//修改的大货资料接收时间
        datePredtimesign = sp.getString("datePredtimesign", "");//修改的开产前会时间
        dateSewFdttimesign = sp.getString("dateSewFdttimesign", "");//修改的上线日期
        dateSewMdttimesign = sp.getString("dateSewMdttimesign", "");//修改的下线日期
        CommodityCutqty = sp.getString("CommodityCutqty", "");//修改的实裁数
        CommodityPreMemo = sp.getString("CommodityPreMemo", "");//修改的需要备注的特殊情况
        CommodityPredoc = sp.getString("CommodityPredoc", "");//修改的产前会报告
        CommodityFabricsok = sp.getString("CommodityFabricsok", "");//修改的大货面料情况
        CommodityAccessoriesok = sp.getString("CommodityAccessoriesok", "");//修改的大货辅料情况
        CommoditySpcproDec = sp.getString("CommoditySpcproDec", "");//修改的大货特殊工艺情况
        CommoditySpcproMemo = sp.getString("CommoditySpcproMemo", "");//修改的特殊工艺备注
        dateQCbdttimesign = sp.getString("dateQCbdttimesign", "");//修改的自查早期时间
        dateQCmdttimesign = sp.getString("dateQCmdttimesign", "");//修改的自查中期时间
        dateQCMedttimesign = sp.getString("dateQCMedttimesign", "");//修改的自查尾期时间
        Commodityqcbdtdoc = sp.getString("Commodityqcbdtdoc", "");//修改的早期报告
        CommodityQCmdtDoc = sp.getString("CommodityQCmdtDoc", "");//修改的中期报告
        CommodityQCedtDoc = sp.getString("CommodityQCedtDoc", "");//修改的尾期报告
        dateFctmdttimesign = sp.getString("dateFctmdttimesign", "");//修改的客查中期时间
        dateFctedttimesign = sp.getString("dateFctedttimesign", "");//修改的客查尾期时间
        CommodityQCMemo = sp.getString("CommodityQCMemo", "");//修改的QC特别备注
        datePackbdattimesign = sp.getString("datePackbdattimesign", "");//修改的成品包装开始日期
        CommodityPackqty2 = sp.getString("CommodityPackqty2", "");//修改的装箱数量
        dateFactlcdattimesign = sp.getString("dateFactlcdattimesign", "");//修改的离厂日期
        commohdTitle = sp.getString("commohdTitle", "");//修改的后道
        CommodityQCMasterScore = sp.getString("CommodityQCMasterScore", "");//修改的主管评分
        CommodityBatchid = sp.getString("CommodityBatchid", "");//修改的查货批次
        dateCtmchkdttimesign = sp.getString("dateCtmchkdttimesign", "");//修改的业务员确认客查日期
        CommodityIPQCmdt = sp.getString("CommodityIPQCmdt", "");//修改的巡检中查
        CommodityIPQCPedt = sp.getString("CommodityIPQCPedt", "");//修改的尾查预查
        datePredocdttimesign = sp.getString("datePredocdttimesign", "");//修改的预计产前会报告时间
        datePrebdttimesign = sp.getString("datePrebdttimesign", "");//修改的预计早期时间
        datePremdttimesign = sp.getString("datePremdttimesign", "");//修改的预计中期时间
        datePreedttimesign = sp.getString("datePreedttimesign", "");//修改的预计尾期时间

    }

    /*实例化控件*/
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

    /*初始化控件显示的数据*/
    private void setViews() {
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
            tv_commodetail_QCMasterScore.setOnFocusChangeListener(
                    new View.OnFocusChangeListener() {
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
                                        String commoitem;
                                        if (qaCworkDataBean.get(position).getQCMasterScore() == null) {
                                            commoitem = "";
                                        } else {
                                            commoitem = qaCworkDataBean.get(position).getQCMasterScore();
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
                                String commoitem;
                                if (qaCworkDataBean.get(position).getQCMasterScore() == null) {
                                    commoitem = "";
                                } else {
                                    commoitem = qaCworkDataBean.get(position).getQCMasterScore();
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
                                    String commosearledrev;
                                    if (qaCworkDataBean.get(position).getSealedrev() == null) {
                                        commosearledrev = "";
                                    } else {
                                        commosearledrev = qaCworkDataBean.get(position).getSealedrev();
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
                                    String commosearledrev;
                                    if (qaCworkDataBean.get(position).getSealedrev() == null) {
                                        commosearledrev = "";
                                    } else {
                                        commosearledrev = qaCworkDataBean.get(position).getSealedrev();
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
                                    String commodocback;
                                    if (qaCworkDataBean.get(position).getDocback() == null) {
                                        commodocback = "";
                                    } else {
                                        commodocback = qaCworkDataBean.get(position).getDocback();
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
                                    String commodocback;
                                    if (qaCworkDataBean.get(position).getDocback() == null) {
                                        commodocback = "";
                                    } else {
                                        commodocback = qaCworkDataBean.get(position).getDocback();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_preMemo.setText("");
                    }
                    String proitem = tv_commodetail_preMemo.getText().toString();
                    String commopromemo;
                    if (qaCworkDataBean.get(position).getPreMemo() == null) {
                        commopromemo = "";
                    } else {
                        commopromemo = qaCworkDataBean.get(position).getPreMemo();
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
                                    String commopreducdt;
                                    if (qaCworkDataBean.get(position).getPredocdt() == null) {
                                        commopreducdt = "";
                                    } else {
                                        commopreducdt = qaCworkDataBean.get(position).getPredocdt();
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
                                    String commopreducdt;
                                    if (qaCworkDataBean.get(position).getPredocdt() == null) {
                                        commopreducdt = "";
                                    } else {
                                        commopreducdt = qaCworkDataBean.get(position).getPredocdt();
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
                                    String commopred;
                                    if (qaCworkDataBean.get(position).getPredt() == null) {
                                        commopred = "";
                                    } else {
                                        commopred = qaCworkDataBean.get(position).getPredt();
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
                                    String commopred;
                                    if (qaCworkDataBean.get(position).getPredt() == null) {
                                        commopred = "";
                                    } else {
                                        commopred = qaCworkDataBean.get(position).getPredt();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_fabricsok.setText("");
                    }
                    String proitem = tv_commodetail_fabricsok.getText().toString();
                    String commofabricsok;
                    if (qaCworkDataBean.get(position).getFabricsok() == null) {
                        commofabricsok = "";
                    } else {
                        commofabricsok = qaCworkDataBean.get(position).getFabricsok();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_accessoriesok.setText("");
                    }
                    String proitem = tv_commodetail_accessoriesok.getText().toString();
                    String commoaccessori;
                    if (qaCworkDataBean.get(position).getAccessoriesok() == null) {
                        commoaccessori = "";
                    } else {
                        commoaccessori = qaCworkDataBean.get(position).getAccessoriesok();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_spcproDec.setText("");
                    }
                    String proitem = tv_commodetail_spcproDec.getText().toString();
                    String commospcprodec;
                    if (qaCworkDataBean.get(position).getSpcproDec() == null) {
                        commospcprodec = "";
                    } else {
                        commospcprodec = qaCworkDataBean.get(position).getSpcproDec();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_spcproMemo.setText("");
                    }
                    String proitem = tv_commodetail_spcproMemo.getText().toString();
                    String commospcpromemo;
                    if (qaCworkDataBean.get(position).getSpcproMemo() == null) {
                        commospcpromemo = "";
                    } else {
                        commospcpromemo = qaCworkDataBean.get(position).getSpcproMemo();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getCutqty() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getCutqty();
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
                                    String commosewfdt;
                                    if (qaCworkDataBean.get(position).getSewFdt() == null) {
                                        commosewfdt = "";
                                    } else {
                                        commosewfdt = qaCworkDataBean.get(position).getSewFdt();
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
                                    String commosewfdt;
                                    if (qaCworkDataBean.get(position).getSewFdt() == null) {
                                        commosewfdt = "";
                                    } else {
                                        commosewfdt = qaCworkDataBean.get(position).getSewFdt();
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
                                    String commosewmdt;
                                    if (qaCworkDataBean.get(position).getSewMdt() == null) {
                                        commosewmdt = "";
                                    } else {
                                        commosewmdt = qaCworkDataBean.get(position).getSewMdt();
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
                                    String commosewmdt;
                                    if (qaCworkDataBean.get(position).getSewMdt() == null) {
                                        commosewmdt = "";
                                    } else {
                                        commosewmdt = qaCworkDataBean.get(position).getSewMdt();
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
                                    String commoprebdt;
                                    if (qaCworkDataBean.get(position).getPrebdt() == null) {
                                        commoprebdt = "";
                                    } else {
                                        commoprebdt = qaCworkDataBean.get(position).getPrebdt();
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
                                    String commoprebdt;
                                    if (qaCworkDataBean.get(position).getPrebdt() == null) {
                                        commoprebdt = "";
                                    } else {
                                        commoprebdt = qaCworkDataBean.get(position).getPrebdt();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getQCbdt() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getQCbdt();
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
                                    String commopremdt;
                                    if (qaCworkDataBean.get(position).getPremdt() == null) {
                                        commopremdt = "";
                                    } else {
                                        commopremdt = qaCworkDataBean.get(position).getPremdt();
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
                                    String commopremdt;
                                    if (qaCworkDataBean.get(position).getPremdt() == null) {
                                        commopremdt = "";
                                    } else {
                                        commopremdt = qaCworkDataBean.get(position).getPremdt();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getQCmdt() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getQCmdt();
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
                                    String commopreedt;
                                    if (qaCworkDataBean.get(position).getPreedt() == null) {
                                        commopreedt = "";
                                    } else {
                                        commopreedt = qaCworkDataBean.get(position).getPreedt();
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
                                    String commopreedt;
                                    if (qaCworkDataBean.get(position).getPreedt() == null) {
                                        commopreedt = "";
                                    } else {
                                        commopreedt = qaCworkDataBean.get(position).getPreedt();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getQCmdt() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getQCmdt();
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
                                    String commofctmdt;
                                    if (qaCworkDataBean.get(position).getFctmdt() == null) {
                                        commofctmdt = "";
                                    } else {
                                        commofctmdt = qaCworkDataBean.get(position).getFctmdt();
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
                                    String commofctmdt;
                                    if (qaCworkDataBean.get(position).getFctmdt() == null) {
                                        commofctmdt = "";
                                    } else {
                                        commofctmdt = qaCworkDataBean.get(position).getFctmdt();
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
                                    String commofctedt;
                                    if (qaCworkDataBean.get(position).getFctedt() == null) {
                                        commofctedt = "";
                                    } else {
                                        commofctedt = qaCworkDataBean.get(position).getFctedt();
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
                                    String commofctedt;
                                    if (qaCworkDataBean.get(position).getFctedt() == null) {
                                        commofctedt = "";
                                    } else {
                                        commofctedt = qaCworkDataBean.get(position).getFctedt();
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
                                    String commopackbdat;
                                    if (qaCworkDataBean.get(position).getPackbdat() == null) {
                                        commopackbdat = "";
                                    } else {
                                        commopackbdat = qaCworkDataBean.get(position).getPackbdat();
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
                                    String commopackbdat;
                                    if (qaCworkDataBean.get(position).getPackbdat() == null) {
                                        commopackbdat = "";
                                    } else {
                                        commopackbdat = qaCworkDataBean.get(position).getPackbdat();
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
                    String commopackqty2;
                    if (qaCworkDataBean.get(position).getPackqty2() == null) {
                        commopackqty2 = "";
                    } else {
                        commopackqty2 = qaCworkDataBean.get(position).getPackqty2();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_QCMemo.setText("");
                    }
                    String proitem = tv_commodetail_QCMemo.getText().toString();
                    String commoqcmemo;
                    if (qaCworkDataBean.get(position).getQCMemo() == null) {
                        commoqcmemo = "";
                    } else {
                        commoqcmemo = qaCworkDataBean.get(position).getQCMemo();
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
                                    String commofactlcdat;
                                    if (qaCworkDataBean.get(position).getFactlcdat() == null) {
                                        commofactlcdat = "";
                                    } else {
                                        commofactlcdat = qaCworkDataBean.get(position).getFactlcdat();
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
                                    String commofactlcdat;
                                    if (qaCworkDataBean.get(position).getFactlcdat() == null) {
                                        commofactlcdat = "";
                                    } else {
                                        commofactlcdat = qaCworkDataBean.get(position).getFactlcdat();
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
                    String commoBatchid;
                    if (qaCworkDataBean.get(position).getBatchid() == null) {
                        commoBatchid = "";
                    } else {
                        commoBatchid = qaCworkDataBean.get(position).getBatchid();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getCtmchkdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getCtmchkdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getCtmchkdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getCtmchkdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getCtmchkdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getCtmchkdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getCtmchkdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getCtmchkdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCmdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCmdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCmdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCmdt();
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
                                    String commosearledrev;
                                    if (qaCworkDataBean.get(position).getSealedrev() == null) {
                                        commosearledrev = "";
                                    } else {
                                        commosearledrev = qaCworkDataBean.get(position).getSealedrev();
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
                                    String commosearledrev;
                                    if (qaCworkDataBean.get(position).getSealedrev() == null) {
                                        commosearledrev = "";
                                    } else {
                                        commosearledrev = qaCworkDataBean.get(position).getSealedrev();
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
                                    String commodocback;
                                    if (qaCworkDataBean.get(position).getDocback() == null) {
                                        commodocback = "";
                                    } else {
                                        commodocback = qaCworkDataBean.get(position).getDocback();
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
                                    String commodocback;
                                    if (qaCworkDataBean.get(position).getDocback() == null) {
                                        commodocback = "";
                                    } else {
                                        commodocback = qaCworkDataBean.get(position).getDocback();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_preMemo.setText("");
                    }
                    String proitem = tv_commodetail_preMemo.getText().toString();
                    String commopromemo;
                    if (qaCworkDataBean.get(position).getPreMemo() == null) {
                        commopromemo = "";
                    } else {
                        commopromemo = qaCworkDataBean.get(position).getPreMemo();
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
                                    String commopreducdt;
                                    if (qaCworkDataBean.get(position).getPredocdt() == null) {
                                        commopreducdt = "";
                                    } else {
                                        commopreducdt = qaCworkDataBean.get(position).getPredocdt();
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
                                    String commopreducdt;
                                    if (qaCworkDataBean.get(position).getPredocdt() == null) {
                                        commopreducdt = "";
                                    } else {
                                        commopreducdt = qaCworkDataBean.get(position).getPredocdt();
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
                                    String commopred;
                                    if (qaCworkDataBean.get(position).getPredt() == null) {
                                        commopred = "";
                                    } else {
                                        commopred = qaCworkDataBean.get(position).getPredt();
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
                                    String commopred;
                                    if (qaCworkDataBean.get(position).getPredt() == null) {
                                        commopred = "";
                                    } else {
                                        commopred = qaCworkDataBean.get(position).getPredt();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_fabricsok.setText("");
                    }
                    String proitem = tv_commodetail_fabricsok.getText().toString();
                    String commofabricsok;
                    if (qaCworkDataBean.get(position).getFabricsok() == null) {
                        commofabricsok = "";
                    } else {
                        commofabricsok = qaCworkDataBean.get(position).getFabricsok();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_accessoriesok.setText("");
                    }
                    String proitem = tv_commodetail_accessoriesok.getText().toString();
                    String commoaccessori;
                    if (qaCworkDataBean.get(position).getAccessoriesok() == null) {
                        commoaccessori = "";
                    } else {
                        commoaccessori = qaCworkDataBean.get(position).getAccessoriesok();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_spcproDec.setText("");
                    }
                    String proitem = tv_commodetail_spcproDec.getText().toString();
                    String commospcprodec;
                    if (qaCworkDataBean.get(position).getSpcproDec() == null) {
                        commospcprodec = "";
                    } else {
                        commospcprodec = qaCworkDataBean.get(position).getSpcproDec();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_spcproMemo.setText("");
                    }
                    String proitem = tv_commodetail_spcproMemo.getText().toString();
                    String commospcpromemo;
                    if (qaCworkDataBean.get(position).getSpcproMemo() == null) {
                        commospcpromemo = "";
                    } else {
                        commospcpromemo = qaCworkDataBean.get(position).getSpcproMemo();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getCutqty() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getCutqty();
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
                                    String commosewfdt;
                                    if (qaCworkDataBean.get(position).getSewFdt() == null) {
                                        commosewfdt = "";
                                    } else {
                                        commosewfdt = qaCworkDataBean.get(position).getSewFdt();
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
                                    String commosewfdt;
                                    if (qaCworkDataBean.get(position).getSewFdt() == null) {
                                        commosewfdt = "";
                                    } else {
                                        commosewfdt = qaCworkDataBean.get(position).getSewFdt();
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
                                    String commosewmdt;
                                    if (qaCworkDataBean.get(position).getSewMdt() == null) {
                                        commosewmdt = "";
                                    } else {
                                        commosewmdt = qaCworkDataBean.get(position).getSewMdt();
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
                                    String commosewmdt;
                                    if (qaCworkDataBean.get(position).getSewMdt() == null) {
                                        commosewmdt = "";
                                    } else {
                                        commosewmdt = qaCworkDataBean.get(position).getSewMdt();
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
                                    String commoprebdt;
                                    if (qaCworkDataBean.get(position).getPrebdt() == null) {
                                        commoprebdt = "";
                                    } else {
                                        commoprebdt = qaCworkDataBean.get(position).getPrebdt();
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
                                    String commoprebdt;
                                    if (qaCworkDataBean.get(position).getPrebdt() == null) {
                                        commoprebdt = "";
                                    } else {
                                        commoprebdt = qaCworkDataBean.get(position).getPrebdt();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getQCbdt() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getQCbdt();
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
                                    String commopremdt;
                                    if (qaCworkDataBean.get(position).getPremdt() == null) {
                                        commopremdt = "";
                                    } else {
                                        commopremdt = qaCworkDataBean.get(position).getPremdt();
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
                                    String commopremdt;
                                    if (qaCworkDataBean.get(position).getPremdt() == null) {
                                        commopremdt = "";
                                    } else {
                                        commopremdt = qaCworkDataBean.get(position).getPremdt();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getQCmdt() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getQCmdt();
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
                                    String commopreedt;
                                    if (qaCworkDataBean.get(position).getPreedt() == null) {
                                        commopreedt = "";
                                    } else {
                                        commopreedt = qaCworkDataBean.get(position).getPreedt();
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
                                    String commopreedt;
                                    if (qaCworkDataBean.get(position).getPreedt() == null) {
                                        commopreedt = "";
                                    } else {
                                        commopreedt = qaCworkDataBean.get(position).getPreedt();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getQCmdt() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getQCmdt();
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
                                    String commofctmdt;
                                    if (qaCworkDataBean.get(position).getFctmdt() == null) {
                                        commofctmdt = "";
                                    } else {
                                        commofctmdt = qaCworkDataBean.get(position).getFctmdt();
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
                                    String commofctmdt;
                                    if (qaCworkDataBean.get(position).getFctmdt() == null) {
                                        commofctmdt = "";
                                    } else {
                                        commofctmdt = qaCworkDataBean.get(position).getFctmdt();
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
                                    String commofctedt;
                                    if (qaCworkDataBean.get(position).getFctedt() == null) {
                                        commofctedt = "";
                                    } else {
                                        commofctedt = qaCworkDataBean.get(position).getFctedt();
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
                                    String commofctedt;
                                    if (qaCworkDataBean.get(position).getFctedt() == null) {
                                        commofctedt = "";
                                    } else {
                                        commofctedt = qaCworkDataBean.get(position).getFctedt();
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
                                    String commopackbdat;
                                    if (qaCworkDataBean.get(position).getPackbdat() == null) {
                                        commopackbdat = "";
                                    } else {
                                        commopackbdat = qaCworkDataBean.get(position).getPackbdat();
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
                                    String commopackbdat;
                                    if (qaCworkDataBean.get(position).getPackbdat() == null) {
                                        commopackbdat = "";
                                    } else {
                                        commopackbdat = qaCworkDataBean.get(position).getPackbdat();
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
                    String commopackqty2;
                    if (qaCworkDataBean.get(position).getPackqty2() == null) {
                        commopackqty2 = "";
                    } else {
                        commopackqty2 = qaCworkDataBean.get(position).getPackqty2();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_QCMemo.setText("");
                    }
                    String proitem = tv_commodetail_QCMemo.getText().toString();
                    String commoqcmemo;
                    if (qaCworkDataBean.get(position).getQCMemo() == null) {
                        commoqcmemo = "";
                    } else {
                        commoqcmemo = qaCworkDataBean.get(position).getQCMemo();
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
                                    String commofactlcdat;
                                    if (qaCworkDataBean.get(position).getFactlcdat() == null) {
                                        commofactlcdat = "";
                                    } else {
                                        commofactlcdat = qaCworkDataBean.get(position).getFactlcdat();
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
                                    String commofactlcdat;
                                    if (qaCworkDataBean.get(position).getFactlcdat() == null) {
                                        commofactlcdat = "";
                                    } else {
                                        commofactlcdat = qaCworkDataBean.get(position).getFactlcdat();
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
                    String commoBatchid;
                    if (qaCworkDataBean.get(position).getBatchid() == null) {
                        commoBatchid = "";
                    } else {
                        commoBatchid = qaCworkDataBean.get(position).getBatchid();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getCtmchkdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getCtmchkdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getCtmchkdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getCtmchkdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCPedt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCPedt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCPedt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCPedt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCmdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCmdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCmdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCmdt();
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
                                    String commosearledrev;
                                    if (qaCworkDataBean.get(position).getSealedrev() == null) {
                                        commosearledrev = "";
                                    } else {
                                        commosearledrev = qaCworkDataBean.get(position).getSealedrev();
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
                                    String commosearledrev;
                                    if (qaCworkDataBean.get(position).getSealedrev() == null) {
                                        commosearledrev = "";
                                    } else {
                                        commosearledrev = qaCworkDataBean.get(position).getSealedrev();
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
                                    String commodocback;
                                    if (qaCworkDataBean.get(position).getDocback() == null) {
                                        commodocback = "";
                                    } else {
                                        commodocback = qaCworkDataBean.get(position).getDocback();
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
                                    String commodocback;
                                    if (qaCworkDataBean.get(position).getDocback() == null) {
                                        commodocback = "";
                                    } else {
                                        commodocback = qaCworkDataBean.get(position).getDocback();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_preMemo.setText("");
                    }
                    String proitem = tv_commodetail_preMemo.getText().toString();
                    String commopromemo;
                    if (qaCworkDataBean.get(position).getPreMemo() == null) {
                        commopromemo = "";
                    } else {
                        commopromemo = qaCworkDataBean.get(position).getPreMemo();
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
                                    String commopreducdt;
                                    if (qaCworkDataBean.get(position).getPredocdt() == null) {
                                        commopreducdt = "";
                                    } else {
                                        commopreducdt = qaCworkDataBean.get(position).getPredocdt();
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
                                    String commopreducdt;
                                    if (qaCworkDataBean.get(position).getPredocdt() == null) {
                                        commopreducdt = "";
                                    } else {
                                        commopreducdt = qaCworkDataBean.get(position).getPredocdt();
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
                                    String commopred;
                                    if (qaCworkDataBean.get(position).getPredt() == null) {
                                        commopred = "";
                                    } else {
                                        commopred = qaCworkDataBean.get(position).getPredt();
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
                                    String commopred;
                                    if (qaCworkDataBean.get(position).getPredt() == null) {
                                        commopred = "";
                                    } else {
                                        commopred = qaCworkDataBean.get(position).getPredt();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_fabricsok.setText("");
                    }
                    String proitem = tv_commodetail_fabricsok.getText().toString();
                    String commofabricsok;
                    if (qaCworkDataBean.get(position).getFabricsok() == null) {
                        commofabricsok = "";
                    } else {
                        commofabricsok = qaCworkDataBean.get(position).getFabricsok();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_accessoriesok.setText("");
                    }
                    String proitem = tv_commodetail_accessoriesok.getText().toString();
                    String commoaccessori;
                    if (qaCworkDataBean.get(position).getAccessoriesok() == null) {
                        commoaccessori = "";
                    } else {
                        commoaccessori = qaCworkDataBean.get(position).getAccessoriesok();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_spcproDec.setText("");
                    }
                    String proitem = tv_commodetail_spcproDec.getText().toString();
                    String commospcprodec;
                    if (qaCworkDataBean.get(position).getSpcproDec() == null) {
                        commospcprodec = "";
                    } else {
                        commospcprodec = qaCworkDataBean.get(position).getSpcproDec();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_spcproMemo.setText("");
                    }
                    String proitem = tv_commodetail_spcproMemo.getText().toString();
                    String commospcpromemo;
                    if (qaCworkDataBean.get(position).getSpcproMemo() == null) {
                        commospcpromemo = "";
                    } else {
                        commospcpromemo = qaCworkDataBean.get(position).getSpcproMemo();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getCutqty() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getCutqty();
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
                                    String commosewfdt;
                                    if (qaCworkDataBean.get(position).getSewFdt() == null) {
                                        commosewfdt = "";
                                    } else {
                                        commosewfdt = qaCworkDataBean.get(position).getSewFdt();
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
                                    String commosewfdt;
                                    if (qaCworkDataBean.get(position).getSewFdt() == null) {
                                        commosewfdt = "";
                                    } else {
                                        commosewfdt = qaCworkDataBean.get(position).getSewFdt();
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
                                    String commosewmdt;
                                    if (qaCworkDataBean.get(position).getSewMdt() == null) {
                                        commosewmdt = "";
                                    } else {
                                        commosewmdt = qaCworkDataBean.get(position).getSewMdt();
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
                                    String commosewmdt;
                                    if (qaCworkDataBean.get(position).getSewMdt() == null) {
                                        commosewmdt = "";
                                    } else {
                                        commosewmdt = qaCworkDataBean.get(position).getSewMdt();
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
                                    String commoprebdt;
                                    if (qaCworkDataBean.get(position).getPrebdt() == null) {
                                        commoprebdt = "";
                                    } else {
                                        commoprebdt = qaCworkDataBean.get(position).getPrebdt();
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
                                    String commoprebdt;
                                    if (qaCworkDataBean.get(position).getPrebdt() == null) {
                                        commoprebdt = "";
                                    } else {
                                        commoprebdt = qaCworkDataBean.get(position).getPrebdt();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getQCbdt() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getQCbdt();
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
                                    String commopremdt;
                                    if (qaCworkDataBean.get(position).getPremdt() == null) {
                                        commopremdt = "";
                                    } else {
                                        commopremdt = qaCworkDataBean.get(position).getPremdt();
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
                                    String commopremdt;
                                    if (qaCworkDataBean.get(position).getPremdt() == null) {
                                        commopremdt = "";
                                    } else {
                                        commopremdt = qaCworkDataBean.get(position).getPremdt();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getQCmdt() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getQCmdt();
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
                                    String commopreedt;
                                    if (qaCworkDataBean.get(position).getPreedt() == null) {
                                        commopreedt = "";
                                    } else {
                                        commopreedt = qaCworkDataBean.get(position).getPreedt();
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
                                    String commopreedt;
                                    if (qaCworkDataBean.get(position).getPreedt() == null) {
                                        commopreedt = "";
                                    } else {
                                        commopreedt = qaCworkDataBean.get(position).getPreedt();
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
                    String commocutqty;
                    if (qaCworkDataBean.get(position).getQCmdt() == null) {
                        commocutqty = "";
                    } else {
                        commocutqty = qaCworkDataBean.get(position).getQCmdt();
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
                                    String commofctmdt;
                                    if (qaCworkDataBean.get(position).getFctmdt() == null) {
                                        commofctmdt = "";
                                    } else {
                                        commofctmdt = qaCworkDataBean.get(position).getFctmdt();
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
                                    String commofctmdt;
                                    if (qaCworkDataBean.get(position).getFctmdt() == null) {
                                        commofctmdt = "";
                                    } else {
                                        commofctmdt = qaCworkDataBean.get(position).getFctmdt();
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
                                    String commofctedt;
                                    if (qaCworkDataBean.get(position).getFctedt() == null) {
                                        commofctedt = "";
                                    } else {
                                        commofctedt = qaCworkDataBean.get(position).getFctedt();
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
                                    String commofctedt;
                                    if (qaCworkDataBean.get(position).getFctedt() == null) {
                                        commofctedt = "";
                                    } else {
                                        commofctedt = qaCworkDataBean.get(position).getFctedt();
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
                                    String commopackbdat;
                                    if (qaCworkDataBean.get(position).getPackbdat() == null) {
                                        commopackbdat = "";
                                    } else {
                                        commopackbdat = qaCworkDataBean.get(position).getPackbdat();
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
                                    String commopackbdat;
                                    if (qaCworkDataBean.get(position).getPackbdat() == null) {
                                        commopackbdat = "";
                                    } else {
                                        commopackbdat = qaCworkDataBean.get(position).getPackbdat();
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
                    String commopackqty2;
                    if (qaCworkDataBean.get(position).getPackqty2() == null) {
                        commopackqty2 = "";
                    } else {
                        commopackqty2 = qaCworkDataBean.get(position).getPackqty2();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_QCMemo.setText("");
                    }
                    String proitem = tv_commodetail_QCMemo.getText().toString();
                    String commoqcmemo;
                    if (qaCworkDataBean.get(position).getQCMemo() == null) {
                        commoqcmemo = "";
                    } else {
                        commoqcmemo = qaCworkDataBean.get(position).getQCMemo();
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
                                    String commofactlcdat;
                                    if (qaCworkDataBean.get(position).getFactlcdat() == null) {
                                        commofactlcdat = "";
                                    } else {
                                        commofactlcdat = qaCworkDataBean.get(position).getFactlcdat();
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
                                    String commofactlcdat;
                                    if (qaCworkDataBean.get(position).getFactlcdat() == null) {
                                        commofactlcdat = "";
                                    } else {
                                        commofactlcdat = qaCworkDataBean.get(position).getFactlcdat();
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
                    String commoBatchid;
                    if (qaCworkDataBean.get(position).getBatchid() == null) {
                        commoBatchid = "";
                    } else {
                        commoBatchid = qaCworkDataBean.get(position).getBatchid();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getCtmchkdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getCtmchkdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getCtmchkdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getCtmchkdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCPedt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCPedt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCPedt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCPedt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCmdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCmdt();
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
                                    String commoCtmchkdt;
                                    if (qaCworkDataBean.get(position).getIPQCmdt() == null) {
                                        commoCtmchkdt = "";
                                    } else {
                                        commoCtmchkdt = qaCworkDataBean.get(position).getIPQCmdt();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_QAname.setText("");
                    }
                    String proitem = tv_commodetail_QAname.getText().toString();
                    String commoQaname;
                    if (qaCworkDataBean.get(position).getQAname() == null) {
                        commoQaname = "";
                    } else {
                        commoQaname = qaCworkDataBean.get(position).getQAname();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_QAScore.setText("");
                    }
                    String proitem = tv_commodetail_QAScore.getText().toString();
                    String commoqascore;//QA首扎件数
                    if (qaCworkDataBean.get(position).getQAScore() == null) {
                        commoqascore = "";
                    } else {
                        commoqascore = qaCworkDataBean.get(position).getQAScore();
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
                                    String commoqamemo;//QA首扎日期
                                    if (qaCworkDataBean.get(position).getQAMemo() == null) {
                                        commoqamemo = "";
                                    } else {
                                        commoqamemo = qaCworkDataBean.get(position).getQAMemo();
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
                                    String commoqamemo;//QA首扎日期
                                    if (qaCworkDataBean.get(position).getQAMemo() == null) {
                                        commoqamemo = "";
                                    } else {
                                        commoqamemo = qaCworkDataBean.get(position).getQAMemo();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_predoc.setText("");
                    }
                    String proitem = tv_commodetail_predoc.getText().toString();
                    String commopredoc;//产前会报告
                    if (qaCworkDataBean.get(position).getPredoc() == null) {
                        commopredoc = "";
                    } else {
                        commopredoc = qaCworkDataBean.get(position).getPredoc();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_QCbdtDoc.setText("");
                    }
                    String proitem = tv_commodetail_QCbdtDoc.getText().toString();
                    String commoqcbdtdoc;//早期报告
                    if (qaCworkDataBean.get(position).getQCbdtDoc() == null) {
                        commoqcbdtdoc = "";
                    } else {
                        commoqcbdtdoc = qaCworkDataBean.get(position).getQCbdtDoc();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_QCmdtDoc.setText("");
                    }
                    String proitem = tv_commodetail_QCmdtDoc.getText().toString();
                    String commoqcmdtdoc;//中期报告
                    if (qaCworkDataBean.get(position).getQCmdtDoc() == null) {
                        commoqcmdtdoc = "";
                    } else {
                        commoqcmdtdoc = qaCworkDataBean.get(position).getQCmdtDoc();
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
                    if (s.toString().equals("\n") || s.toString().equals(" ")) {
                        tv_commodetail_QCedtDoc.setText("");
                    }
                    String proitem = tv_commodetail_QCedtDoc.getText().toString();
                    String commoqcedtdoc;//尾期报告
                    if (qaCworkDataBean.get(position).getQCedtDoc() == null) {
                        commoqcedtdoc = "";
                    } else {
                        commoqcedtdoc = qaCworkDataBean.get(position).getQCedtDoc();
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

    /*本地变量判断是否修改，修改过则赋予修改的值，没有修改则赋予原本的数据*/
    private void setVariable() {
        if (dateSealedrewtimesign.equals(commodetailSealedrev)) {//封样时间
            sealedrevstr = commodetailSealedrev;
        } else {
            sealedrevstr = dateSealedrewtimesign;
        }

        if (dateDocbacktimesign.equals(commodetailDocback)) {//大货时间
            Docbackstr = commodetailDocback;
        } else {
            Docbackstr = dateDocbacktimesign;
        }

        if (datePredtimesign.equals(commodetailPred)) {//开产前会时间
            predtstr = commodetailPred;
        } else {
            predtstr = datePredtimesign;
        }

        if (dateSewFdttimesign.equals(commodetailSewFdt)) {//上线日期
            sewfdtstr = commodetailSewFdt;
        } else {
            sewfdtstr = dateSewFdttimesign;
        }

        if (dateSewMdttimesign.equals(commodetailSewMdt)) {//下线日期
            sewmdtstr = commodetailSewMdt;
        } else {
            sewmdtstr = dateSewMdttimesign;
        }

        if (CommodityCutqty.equals(commodetailCutqty)) {//实裁数
            cutqtystr = commodetailCutqty;
        } else {
            cutqtystr = CommodityCutqty;
        }

        if (CommodityPreMemo.equals(commodetailPreMemo)) {//需要备注的特殊情况
            prememostr = commodetailPreMemo;
        } else {
            prememostr = CommodityPreMemo;
        }

        if (CommodityPredoc.equals(commodetailPredoc)) {//产前会报告
            predocstr = commodetailPredoc;
        } else {
            predocstr = CommodityPredoc;
        }

        if (CommodityFabricsok.equals(commodetailFabricsok)) {//大货面料情况
            fabricsokstr = commodetailFabricsok;
        } else {
            fabricsokstr = CommodityFabricsok;
        }

        if (CommodityAccessoriesok.equals(commodetailAccessoriesok)) {//大货辅料情况
            accessoriesokstr = commodetailAccessoriesok;
        } else {
            accessoriesokstr = CommodityAccessoriesok;
        }

        if (CommoditySpcproDec.equals(commodetailSpcproDec)) {//大货特殊工艺情况
            spcprodecstr = commodetailSpcproDec;
        } else {
            spcprodecstr = CommoditySpcproDec;
        }

        if (CommoditySpcproMemo.equals(commodetailSpcproMemo)) {//特殊工艺备注
            spcpromemostr = commodetailSpcproMemo;
        } else {
            spcpromemostr = CommoditySpcproMemo;
        }

        if (dateQCbdttimesign.equals(commodetailQCbdt)) {//自查早期时间
            qcbdtstr = commodetailQCbdt;
        } else {
            qcbdtstr = dateQCbdttimesign;
        }

        if (dateQCmdttimesign.equals(commodetailQCmdt)) {//自查中期时间
            qcmdtstr = commodetailQCmdt;
        } else {
            qcmdtstr = dateQCmdttimesign;
        }

        if (dateQCMedttimesign.equals(commodetailQCMedt)) {//自查尾期时间
            qcmedtstr = commodetailQCMedt;
        } else {
            qcmedtstr = dateQCMedttimesign;
        }

        if (Commodityqcbdtdoc.equals(commodetailQCbdtDoc)) {//早期报告
            qcbdtdocstr = commodetailQCbdtDoc;
        } else {
            qcbdtdocstr = Commodityqcbdtdoc;
        }

        if (CommodityQCmdtDoc.equals(commodetailQCmdtDoc)) {//中期报告
            qcmdtdocstr = commodetailQCmdtDoc;
        } else {
            qcmdtdocstr = CommodityQCmdtDoc;
        }

        if (CommodityQCedtDoc.equals(commodetailQCedtDoc)) {//尾期报告
            qcedtdocstr = commodetailQCedtDoc;
        } else {
            qcedtdocstr = CommodityQCedtDoc;
        }

        if (dateFctmdttimesign.equals(commodetailFctmdt)) {//客查中期时间
            fctmdtstr = commodetailFctmdt;
        } else {
            fctmdtstr = dateFctmdttimesign;
        }

        if (dateFctedttimesign.equals(commodetailFctedt)) {//客查尾期时间
            fctedtstr = commodetailFctedt;
        } else {
            fctedtstr = dateFctedttimesign;
        }

        if (CommodityQCMemo.equals(commodetailQCMemo)) {//QC特别备注
            qcmemostr = commodetailQCMemo;
        } else {
            qcmemostr = CommodityQCMemo;
        }

        if (datePackbdattimesign.equals(commodetailPackbdat)) {//成品包装开始时间
            packbdatstr = commodetailPackbdat;
        } else {
            packbdatstr = datePackbdattimesign;
        }

        if (CommodityPackqty2.equals(commoPackqty2)) {//装箱数量
            packqty2str = commoPackqty2;
        } else {
            packqty2str = CommodityPackqty2;
        }

        if (dateFactlcdattimesign.equals(commodetailFactlcdat)) {//离厂日期
            factlcdatstr = commodetailFactlcdat;
        } else {
            factlcdatstr = dateFactlcdattimesign;
        }

        if (commohdTitle.equals(commodetailOurAfter)) {//后道
            ourafterstr = commodetailOurAfter;
        } else {
            ourafterstr = commohdTitle;
        }

        if (CommodityQCMasterScore.equals(commodetailQCMasterScore)) {//主管评分
            qcmasterscorestr = commodetailQCMasterScore;
        } else {
            qcmasterscorestr = CommodityQCMasterScore;
        }

        if (CommodityBatchid.equals(commodetailBatchid)) {//查货批次
            batchidstr = commodetailBatchid;
        } else {
            batchidstr = CommodityBatchid;
        }

        if (dateCtmchkdttimesign.equals(commodetailCtmchkdt)) {//业务员确认客查日期
            ctmchkdtstr = commodetailCtmchkdt;
        } else {
            ctmchkdtstr = dateCtmchkdttimesign;
        }

        if (CommodityIPQCmdt.equals(commodetailIPQCmdt)) {//巡检中查
            ipqcmdtstr = commodetailIPQCmdt;
        } else {
            ipqcmdtstr = CommodityIPQCmdt;
        }

        if (CommodityIPQCPedt.equals(commodetailIPQCPedt)) {//尾查预查
            ipqcpedtstr = commodetailIPQCPedt;
        } else {
            ipqcpedtstr = CommodityIPQCPedt;
        }

        if (datePredocdttimesign.equals(commodetailPredocdt)) {//预计产前会报告时间
            predocdtstr = commodetailPredocdt;
        } else {
            predocdtstr = datePredocdttimesign;
        }

        if (datePrebdttimesign.equals(commodetailPrebdt)) {//预计早起时间
            prebdtstr = commodetailPrebdt;
        } else {
            prebdtstr = datePrebdttimesign;
        }

        if (datePremdttimesign.equals(commodetailPremdt)) {//预计中期时间
            premdtstr = commodetailPremdt;
        } else {
            premdtstr = datePremdttimesign;
        }

        if (datePreedttimesign.equals(commodetailPreedt)) {//预计尾期时间
            preedtstr = commodetailPreedt;
        } else {
            preedtstr = datePreedttimesign;
        }
    }

    /*保存上传*/
    private void setSaveDate() {
        if (NetWork.isNetWorkAvailable(this)) {
            sp = getSharedPreferences("my_sp", 0);
            String saveurl = HttpUrl.debugoneUrl + "QACwork/SaveQACwork/";
            setLocalSpUtils();//修改的变量判断
            setVariable();//本地的变量
            List<QACworkDetailSaveBean.DataBean> commoditySaveBean =
                    new ArrayList<QACworkDetailSaveBean.DataBean>();
            QACworkDetailSaveBean.DataBean dataBean = new QACworkDetailSaveBean.DataBean();
            dataBean.setID(Integer.parseInt(commodetailproid));//id
            dataBean.setSubfactory(commodetailPrddocumentary);//跟单
            dataBean.setItem(commoitem);//款号
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
            dataBean.setPrdmasterid(commodetailPrdmasterid);//生产主管id
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
            if (commodetailPrddocumentary.equals(nameid)) {
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

    private void getBooleanSave() {
        sp = getSharedPreferences("my_sp", 0);
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
            saveboolean = "未修改";
        } else {
            saveboolean = "已修改";
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
        editor.remove("QACworkDialogprdmaster");//生产主管
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
                getBooleanSave();
                if (saveboolean.equals("未修改")) {
                    ToastUtils.ShowToastMessage("未修改表中数据", QACworkDetailActivity.this);
                } else {
                    setSaveDate();
                }
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