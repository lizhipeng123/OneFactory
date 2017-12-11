package com.daoran.newfactory.onefactory.activity.work.ftydl;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ftydladapter.FTYDLDetailAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDetailColorBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDetailCuttingBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDetailDetailBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDetailSearchBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLFillColSltBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDetailSaveBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLColCountBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLMonthBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.utils.ProductionUtil;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 创建时间：2017/11/20
 * 编写人：lizhipeng
 * 功能描述：修改保存
 */

public class FTYDLSearchDetailActivity extends BaseFrangmentActivity implements
        View.OnClickListener {
    private static String TAG = "FTYDLSearchDetailActivity";
    private SharedPreferences sp;//轻量级存储
    private SPUtils spUtils;
    private TextView tvProTitle;//标题
    private ImageView ivProductionBack;//返回按钮

    private TextView tv_config_Item, tv_config_ctmtxt, tv_config_subfactory,
            tv_config_pqty, tv_config_documentary, tv_config_department,
            tv_config_procedure, tv_config_size, tv_config_clippingnumber,
            tv_config_totalcompletion, tv_config_balanceamount, tv_config_state,
            tv_config_year, tv_config_recorder, tv_config_recordat, tv_config_cutdate;
    private EditText et_config_others, et_config_singularsystem, et_config_color,
            et_config_completedlastmonth, et_config_month, et_config_oneday,
            et_config_twoday, et_config_threeday, et_config_foreday,
            et_config_fiveday, et_config_sixday, et_config_servenday,
            et_config_eightday, et_config_nineday, et_config_tenday,
            et_config_elevenday, et_config_twelveday, et_config_thirteenday,
            et_config_fourteenday, et_config_fifteenday, et_config_sixteenday,
            et_config_seventeenday, et_config_eighteenday, et_config_nineteenday,
            et_config_twentyday, et_config_TwentyOneDay, et_config_twentytwoday,
            et_config_twentyThreeday, et_config_twentyforeday,
            et_config_twentyfiveday, et_config_twentysixday,
            et_config_twentysevenday, et_config_twentyeightday,
            et_config_twentynineday, et_config_thirtyday, et_config_thirtyoneday,
            et_config_remarks;

    private LinearLayout ll_config_singularsystem//任务数
            , ll_config_completedlast//上月完工数
            , ll_config_balanceamount//结余数量
            , ll_pro_config_one, ll_pro_config_two_visi,
            ll_pro_config_three_visi, ll_pro_config_fore_visi,
            ll_pro_config_five_visi, ll_pro_config_six_visi,
            ll_pro_config_serven_visi, ll_pro_config_eight_visi,
            ll_pro_config_nine_visi, ll_pro_config_ten_visi,
            ll_pro_config_eleven_visi, ll_list_config_pro_vertical;

    private Button btnProSave;//保存按钮
    private int year, month, datetime, hour, minute, second;//年,月,日,时,分,秒
    private ListView list_pro_config_vertical;//实裁数下面的各花色产量集合
    private boolean flagblack;
    private AlertDialog noticeDialog;

    private String pronullpartment, saveothers, saveremarks, savestate,
            savetasknunber, savelastmonth, save1, save2, save3, save4, save5, save6, save7, save8,
            save9, save10, save11, save12, save13, save14, save15, save16, save17, save18, save19, save20, save21,
            save22, save23, save24, save25, save26, save27, save28, save29, save30, save31,
            saveSubDepartment, saveboolean;
    //上个页面传过来的所点击的数据
    private String tvnewlyItem, tvnewlyDepartment, isprodure, tvnewlyctmtxt, tvnewlyDocumentary,
            tvnewlyFactory, tvnewlyOthers, tvnewlyProcedure, tvnyear, tvnmonth,
            tvnewpqty, tvTaskqty, tvnewMdl, tvnewlyProdcol, tvnewlyClippingNumber,
            tvnewlyCompletedLastMonth, tvnewlyTotal, tvnewlyrecorder, tvnewlyrecordat, tvnewlyday1,
            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday5, tvnewlyday6, tvnewlyday7,
            tvnewlyday8, tvnewlyday9, tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17, tvnewlyday18, tvnewlyday19,
            tvnewlyday20, tvnewlyday21, tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29, tvnewlyday30, tvnewlyday31,
            tvnewlySalid, id, tvnewlyplanid, tvnewlysn, tvnewlycontractno, tvnewlyinbill,
            tvnewlyarea, tvnewlycompanytxt, tvnewlypo, tvnewlyoitem, tvnewlyctmid, tvnewlyctmcompanytxt, tvnewlyprdtyp, tvnewlylcdat, tvnewlylbdat, tvnewlystyp, tvnewlyfsaler,
            tvnewlypsaler, tvnewlymemo, tvnewlyunit, tvnewlymegitem, tvnewlyteamname,
            tvnewlyrecordid, tvnewlycutbdt, tvnewlysewbdt, tvnewlysewedt, tvnewlysewDays, tvnewlyperqty, tvnewlycutamount, tvnewlysewamount, tvnewlypackamount, tvnewlyamount, tvnewlyperMachineQty, tvnewlysumMachineQty, tvnewlyprdmaster, tvnewlyleftQty, tvnewlylastMonQty, tvnewlyprddocumentaryid, tvnewlyisdiffc;

    private String usernamerecoder, CountMonthstr;

    private FTYDLDetailAdapter verticalAdatper;//花色list适配
    private List<FTYDLColCountBean.Data> procalbeanlist =
            new ArrayList<FTYDLColCountBean.Data>();//裁床情况下，各个花色对应的每日产量以及总产量集合

    private FTYDLDetailColorBean.DataBean postdataBean;
    private FTYDLDetailColorBean colorBean;
    private List<FTYDLDetailColorBean.DataBean> newdataBeans =
            new ArrayList<FTYDLDetailColorBean.DataBean>();//查询需要分色的相同款号的数据

    private FTYDLFillColSltBean.Data fillcolBean;
    private List<FTYDLFillColSltBean.Data> listfillcolBean =
            new ArrayList<FTYDLFillColSltBean.Data>();//根据searid查询的花色数据集合

    private FTYDLDetailSaveBean saveBean;//保存数据实体

    private List<FTYDLMonthBean.DataBean> monthlistBean =
            new ArrayList<FTYDLMonthBean.DataBean>();//同款号，部门，工序的数据集合
    private FTYDLMonthBean ftydlMonthBean;

    private FTYDLDetailCuttingBean.DataBean cuttingBean;
    private List<FTYDLDetailCuttingBean.DataBean> cuttinglistBean =
            new ArrayList<FTYDLDetailCuttingBean.DataBean>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_config_vertical);
        setSpUtils();//获取变量
        getView();
        initView();
        setListener();
    }

    /*实例化控件*/
    private void getView() {
        tvProTitle = (TextView) findViewById(R.id.tvProTitle);
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        tv_config_Item = (TextView) findViewById(R.id.tv_config_data);
        tv_config_ctmtxt = (TextView) findViewById(R.id.tv_config_ctmtxt);
        tv_config_subfactory = (TextView) findViewById(R.id.tv_config_subfactory);
        tv_config_pqty = (TextView) findViewById(R.id.tv_config_pqty);
        tv_config_documentary = (TextView) findViewById(R.id.tv_config_documentary);
        tv_config_department = (TextView) findViewById(R.id.tv_config_department);
        tv_config_procedure = (TextView) findViewById(R.id.tv_config_procedure);
        tv_config_size = (TextView) findViewById(R.id.tv_config_size);
        tv_config_clippingnumber = (TextView) findViewById(R.id.tv_config_clippingnumber);
        tv_config_totalcompletion = (TextView) findViewById(R.id.tv_config_totalcompletion);
        tv_config_balanceamount = (TextView) findViewById(R.id.tv_config_balanceamount);
        tv_config_year = (TextView) findViewById(R.id.tv_config_year);
        tv_config_recorder = (TextView) findViewById(R.id.tv_config_recorder);
        tv_config_recordat = (TextView) findViewById(R.id.tv_config_recordat);
        tv_config_state = (TextView) findViewById(R.id.tv_config_state);
        tv_config_cutdate = (TextView) findViewById(R.id.tv_config_cutdate);

        et_config_others = (EditText) findViewById(R.id.et_config_others);
        et_config_singularsystem = (EditText) findViewById(R.id.et_config_singularsystem);
        et_config_color = (EditText) findViewById(R.id.et_config_color);
        et_config_completedlastmonth = (EditText) findViewById(R.id.et_config_completedlastmonth);
        et_config_month = (EditText) findViewById(R.id.et_config_month);
        et_config_oneday = (EditText) findViewById(R.id.et_config_oneday);
        et_config_twoday = (EditText) findViewById(R.id.et_config_twoday);
        et_config_threeday = (EditText) findViewById(R.id.et_config_threeday);
        et_config_foreday = (EditText) findViewById(R.id.et_config_foreday);
        et_config_fiveday = (EditText) findViewById(R.id.et_config_fiveday);
        et_config_sixday = (EditText) findViewById(R.id.et_config_sixday);
        et_config_servenday = (EditText) findViewById(R.id.et_config_servenday);
        et_config_eightday = (EditText) findViewById(R.id.et_config_eightday);
        et_config_nineday = (EditText) findViewById(R.id.et_config_nineday);
        et_config_tenday = (EditText) findViewById(R.id.et_config_tenday);
        et_config_elevenday = (EditText) findViewById(R.id.et_config_elevenday);
        et_config_twelveday = (EditText) findViewById(R.id.et_config_twelveday);
        et_config_thirteenday = (EditText) findViewById(R.id.et_config_thirteenday);
        et_config_fourteenday = (EditText) findViewById(R.id.et_config_fourteenday);
        et_config_fifteenday = (EditText) findViewById(R.id.et_config_fifteenday);
        et_config_sixteenday = (EditText) findViewById(R.id.et_config_sixteenday);
        et_config_seventeenday = (EditText) findViewById(R.id.et_config_seventeenday);
        et_config_eighteenday = (EditText) findViewById(R.id.et_config_eighteenday);
        et_config_nineteenday = (EditText) findViewById(R.id.et_config_nineteenday);
        et_config_twentyday = (EditText) findViewById(R.id.et_config_twentyday);
        et_config_TwentyOneDay = (EditText) findViewById(R.id.et_config_TwentyOneDay);
        et_config_twentytwoday = (EditText) findViewById(R.id.et_config_twentytwoday);
        et_config_twentyThreeday = (EditText) findViewById(R.id.et_config_twentyThreeday);
        et_config_twentyforeday = (EditText) findViewById(R.id.et_config_twentyforeday);
        et_config_twentyfiveday = (EditText) findViewById(R.id.et_config_twentyfiveday);
        et_config_twentysixday = (EditText) findViewById(R.id.et_config_twentysixday);
        et_config_twentysevenday = (EditText) findViewById(R.id.et_config_twentysevenday);
        et_config_twentyeightday = (EditText) findViewById(R.id.et_config_twentyeightday);
        et_config_twentynineday = (EditText) findViewById(R.id.et_config_twentynineday);
        et_config_thirtyday = (EditText) findViewById(R.id.et_config_thirtyday);
        et_config_thirtyoneday = (EditText) findViewById(R.id.et_config_thirtyoneday);
        et_config_remarks = (EditText) findViewById(R.id.et_config_remarks);

        ll_config_singularsystem = (LinearLayout) findViewById(R.id.ll_config_singularsystem);
        ll_config_completedlast = (LinearLayout) findViewById(R.id.ll_config_completedlast);
        ll_config_balanceamount = (LinearLayout) findViewById(R.id.ll_config_balanceamount);
        ll_pro_config_one = (LinearLayout) findViewById(R.id.ll_pro_config_one);
        ll_pro_config_two_visi = (LinearLayout) findViewById(R.id.ll_pro_config_two_visi);
        ll_pro_config_three_visi = (LinearLayout) findViewById(R.id.ll_pro_config_three_visi);
        ll_pro_config_fore_visi = (LinearLayout) findViewById(R.id.ll_pro_config_fore_visi);
        ll_pro_config_five_visi = (LinearLayout) findViewById(R.id.ll_pro_config_five_visi);
        ll_pro_config_six_visi = (LinearLayout) findViewById(R.id.ll_pro_config_six_visi);
        ll_pro_config_serven_visi = (LinearLayout) findViewById(R.id.ll_pro_config_serven_visi);
        ll_pro_config_eight_visi = (LinearLayout) findViewById(R.id.ll_pro_config_eight_visi);
        ll_pro_config_nine_visi = (LinearLayout) findViewById(R.id.ll_pro_config_nine_visi);
        ll_pro_config_ten_visi = (LinearLayout) findViewById(R.id.ll_pro_config_ten_visi);
        ll_pro_config_eleven_visi = (LinearLayout) findViewById(R.id.ll_pro_config_eleven_visi);
        list_pro_config_vertical = (ListView) findViewById(R.id.list_pro_config_vertical);
        ll_list_config_pro_vertical = (LinearLayout) findViewById(R.id.ll_list_config_pro_vertical);

        btnProSave = (Button) findViewById(R.id.btnProSave);
    }

    private void initView() {
        tvProTitle.setText("修改保存");
        Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
        t.setToNow(); // 取得系统时间。
        year = t.year;
        month = t.month;
        datetime = t.monthDay;
        hour = t.hour; // 0-23
        minute = t.minute;
        second = t.second;
        month = month + 1;
        tv_config_Item.setText(tvnewlyItem);//款号
        tv_config_ctmtxt.setText(tvnewlyctmtxt);//客户
        tv_config_documentary.setText(tvnewlyDocumentary);//跟单
        tv_config_subfactory.setText(tvnewlyFactory);//工厂
        tv_config_department.setText(tvnewlyDepartment);//部门组别
        tv_config_procedure.setText(tvnewlyProcedure);//工序
        et_config_others.setText(tvnewlyOthers);//组别人数
        tv_config_pqty.setText(tvnewpqty);//制单数
        et_config_singularsystem.setText(tvTaskqty);//任务数
        tv_config_size.setText(tvnewMdl);//尺码
        et_config_color.setText(tvnewlyProdcol);//花色
        tv_config_clippingnumber.setText(tvnewlyClippingNumber);//实裁数
        tv_config_totalcompletion.setText(tvnewlyCompletedLastMonth);//总完工数
        et_config_completedlastmonth.setText(tvnewlyCompletedLastMonth);//上月完工数
        tv_config_state.setText(tvnewlyTotal);//状态
        tv_config_recorder.setText(tvnewlyrecorder);//制单人
        tv_config_recordat.setText(tvnewlyrecordat);//制单时间
        tv_config_cutdate.setText(year + "/" + month + "/" + datetime);//裁床时间
        tv_config_year.setText(tvnyear);//年
        et_config_month.setText(tvnmonth);//月
        et_config_oneday.setText(tvnewlyday1);
        et_config_twoday.setText(tvnewlyday2);
        et_config_threeday.setText(tvnewlyday3);
        et_config_foreday.setText(tvnewlyday4);
        et_config_fiveday.setText(tvnewlyday5);
        et_config_sixday.setText(tvnewlyday6);
        et_config_servenday.setText(tvnewlyday7);
        et_config_eightday.setText(tvnewlyday8);
        et_config_nineday.setText(tvnewlyday9);
        et_config_tenday.setText(tvnewlyday10);
        et_config_elevenday.setText(tvnewlyday11);
        et_config_twelveday.setText(tvnewlyday12);
        et_config_thirteenday.setText(tvnewlyday13);
        et_config_fourteenday.setText(tvnewlyday14);
        et_config_fifteenday.setText(tvnewlyday15);
        et_config_sixteenday.setText(tvnewlyday16);
        et_config_seventeenday.setText(tvnewlyday17);
        et_config_eighteenday.setText(tvnewlyday18);
        et_config_nineteenday.setText(tvnewlyday19);
        et_config_twentyday.setText(tvnewlyday20);
        et_config_TwentyOneDay.setText(tvnewlyday21);
        et_config_twentytwoday.setText(tvnewlyday22);
        et_config_twentyThreeday.setText(tvnewlyday23);
        et_config_twentyforeday.setText(tvnewlyday24);
        et_config_twentyfiveday.setText(tvnewlyday25);
        et_config_twentysixday.setText(tvnewlyday26);
        et_config_twentysevenday.setText(tvnewlyday27);
        et_config_twentyeightday.setText(tvnewlyday28);
        et_config_twentynineday.setText(tvnewlyday29);
        et_config_thirtyday.setText(tvnewlyday30);
        et_config_thirtyoneday.setText(tvnewlyday31);
        String prodetailPredocdt = (year + "/" + month + "/" + datetime);
        spUtils.put(getApplicationContext(), "prodetailPredocdt", prodetailPredocdt);
        if (isprodure.equals("0")) {
            ll_config_singularsystem.setVisibility(View.VISIBLE);
            ll_config_completedlast.setVisibility(View.VISIBLE);
            ll_config_balanceamount.setVisibility(View.VISIBLE);
            ll_pro_config_one.setVisibility(View.VISIBLE);
            ll_pro_config_two_visi.setVisibility(View.VISIBLE);
            ll_pro_config_three_visi.setVisibility(View.VISIBLE);
            ll_pro_config_fore_visi.setVisibility(View.VISIBLE);
            ll_pro_config_five_visi.setVisibility(View.VISIBLE);
            ll_pro_config_six_visi.setVisibility(View.VISIBLE);
            ll_pro_config_serven_visi.setVisibility(View.VISIBLE);
            ll_pro_config_eight_visi.setVisibility(View.VISIBLE);
            ll_pro_config_nine_visi.setVisibility(View.VISIBLE);
            ll_pro_config_ten_visi.setVisibility(View.VISIBLE);
            ll_pro_config_eleven_visi.setVisibility(View.VISIBLE);
            ll_list_config_pro_vertical.setVisibility(View.GONE);
            setMonthSearch();
        } else if (isprodure.equals("1")) {
            ll_config_singularsystem.setVisibility(View.GONE);
            ll_config_completedlast.setVisibility(View.GONE);
            ll_config_balanceamount.setVisibility(View.GONE);
            ll_pro_config_one.setVisibility(View.GONE);
            ll_pro_config_two_visi.setVisibility(View.GONE);
            ll_pro_config_three_visi.setVisibility(View.GONE);
            ll_pro_config_fore_visi.setVisibility(View.GONE);
            ll_pro_config_five_visi.setVisibility(View.GONE);
            ll_pro_config_six_visi.setVisibility(View.GONE);
            ll_pro_config_serven_visi.setVisibility(View.GONE);
            ll_pro_config_eight_visi.setVisibility(View.GONE);
            ll_pro_config_nine_visi.setVisibility(View.GONE);
            ll_pro_config_ten_visi.setVisibility(View.GONE);
            ll_pro_config_eleven_visi.setVisibility(View.GONE);
            ll_list_config_pro_vertical.setVisibility(View.VISIBLE);
            setFillColSlt(tvnewlySalid);//根据id查询数据
            setMonthSearch();//查询同款，同部门，同工序,同制单人的数据
        }
    }

    /*接收上一个页面传过来的点击的数据*/
    private void setSpUtils() {
        sp = getSharedPreferences("my_sp", 0);
        isprodure = sp.getString("FTYDLDetailISProdure", "");
        usernamerecoder = sp.getString("usernamerecoder", "");//登录人
        tvnewlyItem = sp.getString("tvFTYDLDetailItem", "");//款号
        tvnewlyctmtxt = sp.getString("tvFTYDLDetailCtmtxt", "");//客户
        tvnewlyDocumentary = sp.getString("tvFTYDLDetailDocumentary", "");//跟单
        tvnewlyFactory = sp.getString("tvFTYDLDetailFactory", "");//工厂
        tvnewlyDepartment = sp.getString("tvFTYDLDetailFactoryTeams", "");//部门/组别
        tvnewlyProcedure = sp.getString("tvFTYDLDetailProcedure", "");//工序
        tvnewlyOthers = sp.getString("tvFTYDLDetailWorkers", "");//组别人数
        tvnewpqty = sp.getString("tvFTYDLDetailPqty", "");//制单数
        tvTaskqty = sp.getString("tvFTYDLDetailTaskqty", "");//任务数
        tvnewMdl = sp.getString("tvFTYDLDetailMdl", "");//尺码
        tvnewlyProdcol = sp.getString("tvFTYDLDetailProdcol", "");//花色
        tvnewlyClippingNumber = sp.getString("tvFTYDLDetailFactcutqty",
                "");//实裁数
        tvnewlyCompletedLastMonth = sp.getString("tvFTYDLDetailSumCompletedQty",
                "");//总完工数
        tvnewlyTotal = sp.getString("tvFTYDLDetailPrdstatus", "");//状态
        tvnewlyrecorder = sp.getString("tvFTYDLDetailRecorder", "");//制单人
        tvnewlyrecordat = sp.getString("tvFTYDLDetailRecordat", "");//制单时间
        tvnyear = sp.getString("tvFTYDLDetailYear", "");//年
        tvnmonth = sp.getString("tvFTYDLDetailMonth", "");//月
        tvnewlyday1 = sp.getString("tvFTYDLDetailDay1", "");//1
        tvnewlyday2 = sp.getString("tvFTYDLDetailDay2", "");//2
        tvnewlyday3 = sp.getString("tvFTYDLDetailDay3", "");//3
        tvnewlyday4 = sp.getString("tvFTYDLDetailDay4", "");//4
        tvnewlyday5 = sp.getString("tvFTYDLDetailDay5", "");//5
        tvnewlyday6 = sp.getString("tvFTYDLDetailDay6", "");//6
        tvnewlyday7 = sp.getString("tvFTYDLDetailDay7", "");//7
        tvnewlyday8 = sp.getString("tvFTYDLDetailDay8", "");//8
        tvnewlyday9 = sp.getString("tvFTYDLDetailDay9", "");//9
        tvnewlyday10 = sp.getString("tvFTYDLDetailDay10", "");//10
        tvnewlyday11 = sp.getString("tvFTYDLDetailDay11", "");//11
        tvnewlyday12 = sp.getString("tvFTYDLDetailDay12", "");//12
        tvnewlyday13 = sp.getString("tvFTYDLDetailDay13", "");//13
        tvnewlyday14 = sp.getString("tvFTYDLDetailDay14", "");//14
        tvnewlyday15 = sp.getString("tvFTYDLDetailDay15", "");//15
        tvnewlyday16 = sp.getString("tvFTYDLDetailDay16", "");//16
        tvnewlyday17 = sp.getString("tvFTYDLDetailDay17", "");//17
        tvnewlyday18 = sp.getString("tvFTYDLDetailDay18", "");//18
        tvnewlyday19 = sp.getString("tvFTYDLDetailDay19", "");//19
        tvnewlyday20 = sp.getString("tvFTYDLDetailDay20", "");//20
        tvnewlyday21 = sp.getString("tvFTYDLDetailDay21", "");//21
        tvnewlyday22 = sp.getString("tvFTYDLDetailDay22", "");//22
        tvnewlyday23 = sp.getString("tvFTYDLDetailDay23", "");//23
        tvnewlyday24 = sp.getString("tvFTYDLDetailDay24", "");//24
        tvnewlyday25 = sp.getString("tvFTYDLDetailDay25", "");//25
        tvnewlyday26 = sp.getString("tvFTYDLDetailDay26", "");//26
        tvnewlyday27 = sp.getString("tvFTYDLDetailDay27", "");//27
        tvnewlyday28 = sp.getString("tvFTYDLDetailDay28", "");//28
        tvnewlyday29 = sp.getString("tvFTYDLDetailDay29", "");//29
        tvnewlyday30 = sp.getString("tvFTYDLDetailDay30", "");//30
        tvnewlyday31 = sp.getString("tvFTYDLDetailDay31", "");//31
        tvnewlySalid = sp.getString("tvFTYDLDetailSalesID", "");
        id = sp.getString("tvFTYDLDetailID", "");//id
        tvnewlyplanid = sp.getString("tvFTYDLDetailPlanId", "");//引用的工厂计划id
        tvnewlysn = sp.getString("tvFTYDLDetailSn", "");//序列号
        tvnewlycontractno = sp.getString("tvFTYDLDetailContractno", "");//销售合同号
        tvnewlyinbill = sp.getString("tvFTYDLDetailInbill", "");//内部id
        tvnewlyarea = sp.getString("tvFTYDLDetailArea", "");//片区号
        tvnewlycompanytxt = sp.getString("tvFTYDLDetailCompanytxt", "");//公司名称
        tvnewlypo = sp.getString("tvFTYDLDetailPo", "");//po
        tvnewlyoitem = sp.getString("tvFTYDLDetailOitem", "");//原款号
        tvnewlyctmid = sp.getString("tvFTYDLDetailCtmid", "");//客户id
        tvnewlyctmcompanytxt = sp.getString("tvFTYDLDetailCtmcompanytxt",
                "");//客户归属公司
        tvnewlyprdtyp = sp.getString("tvFTYDLDetailPrdtyp", "");//产品大类
        tvnewlylcdat = sp.getString("tvFTYDLDetailLcdat", "");//计划离厂日期
        tvnewlylbdat = sp.getString("tvFTYDLDetailLbdat", "");//计划离岸日期
        tvnewlystyp = sp.getString("tvFTYDLDetailStyp", "");//po类型
        tvnewlyfsaler = sp.getString("tvFTYDLDetailFsaler", "");//外贸业务员
        tvnewlypsaler = sp.getString("tvFTYDLDetailPsaler", "");//生产业务员
        tvnewlymemo = sp.getString("tvFTYDLDetailMemo", "");//备注
        tvnewlyunit = sp.getString("tvFTYDLDetailUnit", "");//单位
        tvnewlymegitem = sp.getString("tvFTYDLDetailMegitem", "");//合并款号
        tvnewlyteamname = sp.getString("tvFTYDLDetailTeamname", "");//外贸组别
        tvnewlyrecordid = sp.getString("tvFTYDLDetailRecordid", "");//制单人id
        tvnewlycutbdt = sp.getString("tvFTYDLDetailCutbdt", "");//开采日期
        tvnewlysewbdt = sp.getString("tvFTYDLDetailSewbdt", "");//上线日期
        tvnewlysewedt = sp.getString("tvFTYDLDetailSewedt", "");//完工日期
        tvnewlysewDays = sp.getString("tvFTYDLDetailSewDays", "");//天数
        tvnewlyperqty = sp.getString("tvFTYDLDetailPerqty", "");//人均件数
        tvnewlycutamount = sp.getString("tvFTYDLDetailCutamount", "");//裁剪金额
        tvnewlysewamount = sp.getString("tvFTYDLDetailSewamount", "");
        tvnewlypackamount = sp.getString("tvFTYDLDetailPackamount", "");
        tvnewlyamount = sp.getString("tvFTYDLDetailAmount", "");//总价
        tvnewlyperMachineQty = sp.getString("tvFTYDLDetailPerMachineQty", "");//车间人均台产
        tvnewlysumMachineQty = sp.getString("tvFTYDLDetailSumMachineQty", "");//台总产
        tvnewlyprdmaster = sp.getString("tvFTYDLDetailPrdmaster", "");//生产主管
        tvnewlyleftQty = sp.getString("tvFTYDLDetailLeftQty", "");//结余数量
        tvnewlylastMonQty = sp.getString("tvFTYDLDetailLastMonQty", "");//上月结余数量
        tvnewlyprddocumentaryid = sp.getString("tvFTYDLDetailPrdDocumentaryId", "");//跟单id
        tvnewlyisdiffc = sp.getString("tvFTYDLDetailIsdiffc", "");//是否分色
    }

    /*判断是否可输入*/
    private void setEnabled() {
        if (tvnewlyrecorder == null) {
            tvnewlyrecorder = "";
        }
        //判断登录人是否是制单人
        if (tvnewlyrecorder.equals(usernamerecoder)) {
            for (int i = 0; i < monthlistBean.size(); i++) {
                int intmonth = Integer.parseInt(tvnmonth);//当前月
                int getmonth =
                        Integer.parseInt(monthlistBean.get(i).getMonth());//集合中的月
                int intyear = Integer.parseInt(tvnyear);
                int getyear = Integer.parseInt(monthlistBean.get(i).getYear());
                //如果集合中的月大于当前月,则不可修改当前月
                if (getmonth > intmonth) {
                    //当前年份和集合中的年份是否相同,如果不同，则通过
                    if (getyear > intyear) {
                        setEnableFalse();
                        return;
                    } else if (getyear == intyear) {
                        setEnableFalse();
                        break;
                    } else {
                        setEnableTrue();
                        break;
                    }
                } else if (getyear == intyear) {
                    if (getyear > intyear) {
                        setEnableFalse();
                        return;
                    } else if (getyear == intyear) {
                        setEnableTrue();
                        break;
                    } else {
                        setEnableTrue();
                        break;
                    }
                } else {
                    if (getyear > intyear) {
                        setEnableFalse();
                        return;
                    } else if (getyear == intyear) {
                        setEnableTrue();
                        break;
                    } else {
                        setEnableTrue();
                        break;
                    }
                }
            }
        } else {
            setEnableFalse();
        }
    }

    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        btnProSave.setOnClickListener(this);
    }

    /*填充颜色list*/
    private void setProcol() {
        try {
            for (int j = 0; j < newdataBeans.size(); j++) {
                //循环当前人的的所有单子，如果颜色不相同的话,则添加到集合中，
                //如果相同，则删除这条数据
                String procol = newdataBeans.get(j).getProdcol();
                String completedLastMonth = String.valueOf(newdataBeans.get(j).getSumCompletedQty());
                if (completedLastMonth == null) {
                    completedLastMonth = "";
                }
                for (int y = 0; y < listfillcolBean.size(); y++) {
                    FTYDLColCountBean.Data procalbean =
                            new FTYDLColCountBean.Data();
                    if (procol.equals(listfillcolBean.get(y).getText())) {
                        procalbean.setProText(newdataBeans.get(j).getProdcol());
                        procalbean.setBalanceAmount(String.valueOf(newdataBeans.get(j).getTaskqty()));
                        procalbean.setProCount(completedLastMonth);
                        procalbean.setProNum("");
                        procalbeanlist.add(procalbean);
                    }
                }
            }
            System.out.println(newdataBeans);
            System.out.println(procalbeanlist);
            int tvsum = 0;
            for (int i = 0; i < newdataBeans.size(); i++) {
                String sumcom = String.valueOf(newdataBeans.get(i).getSumCompletedQty());
                if (sumcom == null||sumcom.equals("")) {
                    sumcom = String.valueOf(0);
                }
                tvsum = tvsum + Integer.parseInt(sumcom);
            }
            tv_config_clippingnumber.setText(String.valueOf(tvsum));
            verticalAdatper = new FTYDLDetailAdapter(
                    FTYDLSearchDetailActivity.this,
                    newdataBeans, procalbeanlist, monthlistBean);
            list_pro_config_vertical.setAdapter(verticalAdatper);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*计算总数*/
    private void setCount() {
        String lastmonth = et_config_completedlastmonth.getText().toString();
        String dayone = et_config_oneday.getText().toString();
        String daytwo = et_config_twoday.getText().toString();
        String dayThree = et_config_threeday.getText().toString();
        String dayfore = et_config_foreday.getText().toString();
        String dayfive = et_config_fiveday.getText().toString();
        String daysix = et_config_sixday.getText().toString();
        String daySeven = et_config_servenday.getText().toString();
        String dayEight = et_config_eightday.getText().toString();
        String dayNine = et_config_nineday.getText().toString();
        String dayTen = et_config_tenday.getText().toString();
        String dayEleven = et_config_elevenday.getText().toString();
        String dayTwelve = et_config_twelveday.getText().toString();
        String dayThirteen = et_config_thirteenday.getText().toString();
        String dayFourteen = et_config_fourteenday.getText().toString();
        String dayFifteen = et_config_fifteenday.getText().toString();
        String daySixteen = et_config_sixteenday.getText().toString();
        String daySeventeen = et_config_seventeenday.getText().toString();
        String dayEighteen = et_config_eighteenday.getText().toString();
        String dayNineteen = et_config_nineteenday.getText().toString();
        String dayTwenty = et_config_twentyday.getText().toString();
        String dayTwentyOne = et_config_TwentyOneDay.getText().toString();
        String dayTwentyTwo = et_config_twentytwoday.getText().toString();
        String dayTwentyThree = et_config_twentyThreeday.getText().toString();
        String dayTwentyFore = et_config_twentyforeday.getText().toString();
        String dayTwentyFive = et_config_twentyfiveday.getText().toString();
        String dayTwentySix = et_config_twentysixday.getText().toString();
        String dayTwentySeven = et_config_twentysevenday.getText().toString();
        String dayTwentyEight = et_config_twentyeightday.getText().toString();
        String dayTwentyNine = et_config_twentynineday.getText().toString();
        String dayThirty = et_config_thirtyday.getText().toString();
        String dayThirtyOne = et_config_thirtyoneday.getText().toString();
        ProductionUtil productionUtil = new ProductionUtil();
        String countmonth = productionUtil.CountMonth(lastmonth, dayone, daytwo
                , dayThree, dayfore, dayfive, daysix, daySeven, dayEight, dayNine, dayTen,
                dayEleven, dayTwelve, dayThirteen, dayFourteen, dayFifteen, daySixteen,
                daySeventeen, dayEighteen, dayNineteen, dayTwenty, dayTwentyOne,
                dayTwentyTwo, dayTwentyThree, dayTwentyFore, dayTwentyFive,
                dayTwentySix, dayTwentySeven, dayTwentyEight, dayTwentyNine,
                dayThirty, dayThirtyOne);
        tv_config_totalcompletion.setText(countmonth);
    }

    /*裁床情况的精确查询*/
    private void setDateCutting() {
        String urlDaily = HttpUrl.debugoneUrl + "FactoryPlan/SearchDailyData/";
        final Gson gson = new Gson();
        FTYDLDetailSaveBean saveBean = new FTYDLDetailSaveBean();
        saveBean.setID(Integer.parseInt(id));
        saveBean.setSalesid(Integer.parseInt(tvnewlySalid));
        saveBean.setItem(tvnewlyItem);
        saveBean.setPlanid(tvnewlyplanid);
        saveBean.setSn(Integer.parseInt(tvnewlysn));
        saveBean.setContractno(tvnewlycontractno);
        saveBean.setInbill(tvnewlyinbill);
        saveBean.setArea(tvnewlyarea);
        saveBean.setCompanytxt(tvnewlycompanytxt);
        saveBean.setPo(tvnewlypo);
        saveBean.setOitem(tvnewlyoitem);
        saveBean.setMdl(tvnewMdl);
        saveBean.setCtmid(tvnewlyctmid);
        saveBean.setCtmtxt(tvnewlyctmtxt);
        saveBean.setCtmcompanytxt(tvnewlyctmcompanytxt);
        saveBean.setPrdtyp(tvnewlyprdtyp);
        saveBean.setLcdat(tvnewlylcdat);
        saveBean.setLbdat(tvnewlylbdat);
        saveBean.setStyp(tvnewlystyp);
        saveBean.setFsaler(tvnewlyfsaler);
        saveBean.setPsaler(tvnewlypsaler);
        saveBean.setMemo(tvnewlymemo);
        saveBean.setPqty(tvnewpqty);
        saveBean.setUnit(tvnewlyunit);
        saveBean.setProdcol(tvnewlyProdcol);
        saveBean.setMegitem(tvnewlymegitem);
        saveBean.setTeamname(tvnewlyteamname);
        saveBean.setRecordat(tvnewlyrecordat);
        saveBean.setRecordid(tvnewlyrecordid);
        saveBean.setRecorder(tvnewlyrecorder);
        saveBean.setSubfactory(tvnewlyFactory);
        saveBean.setWorkingProcedure(tvnewlyProcedure);
        saveBean.setSubfactoryTeams(tvnewlyDepartment);
        saveBean.setWorkers(tvnewlyOthers);
        saveBean.setFactcutqty(tvnewlyClippingNumber);
        saveBean.setCutbdt(tvnewlycutbdt);
        saveBean.setSewbdt(tvnewlysewbdt);
        saveBean.setSewedt(tvnewlysewedt);
        saveBean.setSewDays(tvnewlysewDays);
        saveBean.setPerqty(tvnewlyperqty);
        saveBean.setCutamount(tvnewlycutamount);
        saveBean.setSewamount(tvnewlysewamount);
        saveBean.setPackamount(tvnewlypackamount);
        saveBean.setAmount(tvnewlyamount);
        saveBean.setPerMachineQty(tvnewlyperMachineQty);
        saveBean.setSumMachineQty(tvnewlysumMachineQty);
        saveBean.setPrdstatus(tvnewlyTotal);
        saveBean.setPrdmaster(tvnewlyprdmaster);
        saveBean.setPrddocumentary(tvnewlyDocumentary);
        saveBean.setTaskqty(tvTaskqty);
        saveBean.setSumCompletedQty(tvnewlyCompletedLastMonth);
        saveBean.setLeftQty(tvnewlyleftQty);
        saveBean.setLastMonQty(tvnewlylastMonQty);
        saveBean.setYear(tvnyear);
        saveBean.setMonth(tvnmonth);
        saveBean.setDay1(tvnewlyday1);
        saveBean.setDay2(tvnewlyday2);
        saveBean.setDay3(tvnewlyday3);
        saveBean.setDay4(tvnewlyday4);
        saveBean.setDay5(tvnewlyday5);
        saveBean.setDay6(tvnewlyday6);
        saveBean.setDay7(tvnewlyday7);
        saveBean.setDay8(tvnewlyday8);
        saveBean.setDay9(tvnewlyday9);
        saveBean.setDay10(tvnewlyday10);
        saveBean.setDay11(tvnewlyday11);
        saveBean.setDay12(tvnewlyday12);
        saveBean.setDay13(tvnewlyday13);
        saveBean.setDay14(tvnewlyday14);
        saveBean.setDay15(tvnewlyday15);
        saveBean.setDay16(tvnewlyday16);
        saveBean.setDay17(tvnewlyday17);
        saveBean.setDay18(tvnewlyday18);
        saveBean.setDay19(tvnewlyday19);
        saveBean.setDay20(tvnewlyday20);
        saveBean.setDay21(tvnewlyday21);
        saveBean.setDay22(tvnewlyday22);
        saveBean.setDay23(tvnewlyday23);
        saveBean.setDay24(tvnewlyday24);
        saveBean.setDay25(tvnewlyday25);
        saveBean.setDay26(tvnewlyday26);
        saveBean.setDay27(tvnewlyday27);
        saveBean.setDay28(tvnewlyday28);
        saveBean.setDay29(tvnewlyday29);
        saveBean.setDay30(tvnewlyday30);
        saveBean.setDay31(tvnewlyday31);
        saveBean.setPrddocumentaryid(tvnewlyprddocumentaryid);
        saveBean.setIsdiffc(Boolean.parseBoolean(tvnewlyisdiffc));
        final String bean = gson.toJson(saveBean);
//        String dateee = bean.replace("\"\"", "null");
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.postString()
                    .url(urlDaily)
                    .content(bean)
                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            String ress = response.replace("\\", "");
                            String ression = StringUtil.sideTrim(ress, "\"");
                            String ressi = ression.replace(":null", ":\"\"");
                            JsonParser jsonParser = new JsonParser();
                            JsonElement element = jsonParser.parse(ressi);
                            Gson gson1 = new Gson();
                            JsonArray jsonarray = null;
                            if (element.isJsonArray()) {
                                jsonarray = element.getAsJsonArray();
                            }
                            Iterator iterator = jsonarray.iterator();
                            while (iterator.hasNext()) {
                                JsonElement e = (JsonElement) iterator.next();
                                postdataBean = gson1.fromJson(e, FTYDLDetailColorBean.DataBean.class);
                                newdataBeans.add(postdataBean);
                            }
                            setProcol();
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLSearchDetailActivity.this);
        }
    }

    /*精确查询除了裁床之外*/
    private void setDateNewly() {
        String urlDaily = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        final Gson gson = new Gson();
        final FTYDLDetailDetailBean detailDetailBean = new FTYDLDetailDetailBean();
        FTYDLDetailDetailBean.Conditions conditions =
                detailDetailBean.new Conditions();
        conditions.setID(Integer.parseInt(id));
        conditions.setItem(tvnewlyItem);//款号
        conditions.setSalesid(Integer.parseInt(tvnewlySalid));
        conditions.setWorkingProcedure(tvnewlyProcedure);//工序
        conditions.setPrddocumentary(tvnewlyDocumentary);//跟单人
        conditions.setRecordat(tvnewlyrecordat);//时间
        conditions.setRecorder(tvnewlyrecorder);//制单人
        conditions.setRecordid(tvnewlyrecordid);//制单人id
        conditions.setProdcol(tvnewlyProdcol);//花色
        conditions.setYear(tvnyear);//年
        conditions.setMonth(tvnmonth);//月
        conditions.setSubfactoryTeams(tvnewlyDepartment);//组别
        conditions.setPrddocumentaryisnull(false);//制单人是否可空
        detailDetailBean.setConditions(conditions);
        detailDetailBean.setPageNum(0);
        detailDetailBean.setPageSize(15);
        final String bean = gson.toJson(detailDetailBean);
        String dateee = bean.replace("\"\"", "null");
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.postString()
                    .url(urlDaily)
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
                            System.out.println(response);
                            String ress = response.replace("\\", "");
                            System.out.print(ress);
                            String ression = StringUtil.sideTrim(ress, "\"");
                            System.out.print(ression);
                            colorBean = new Gson().fromJson(ression, FTYDLDetailColorBean.class);
                            newdataBeans = colorBean.getData();
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLSearchDetailActivity.this);
        }
    }

    /*获取相同款号相同部门相同工序的全部数据*/
    private void setMonthSearch() {
        String strMonth = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        FTYDLDetailSearchBean searchBean = new FTYDLDetailSearchBean();
        FTYDLDetailSearchBean.Conditions conditions = searchBean.new Conditions();
        conditions.setItem(tvnewlyItem);
        conditions.setWorkingProcedure(tvnewlyProcedure);
        conditions.setSubfactoryTeams(tvnewlyDepartment);
        conditions.setPrddocumentary(usernamerecoder);
        conditions.setPrddocumentaryisnull(false);
        searchBean.setConditions(conditions);
        searchBean.setPageNum(0);
        searchBean.setPageSize(500);
        Gson gson = new Gson();
        String gsonBean = gson.toJson(searchBean);
        OkHttpUtils.postString()
                .url(strMonth)
                .content(gsonBean)
                .mediaType(MediaType.parse("application/json;charset=utf-8"))
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
                        System.out.print(ress);
                        String ression = StringUtil.sideTrim(ress, "\"");
                        System.out.print(ression);
                        ftydlMonthBean = new Gson().fromJson(ression, FTYDLMonthBean.class);
                        monthlistBean = ftydlMonthBean.getData();
                        System.out.println(monthlistBean);
                        setEnabled();
                        if(tvnewlyProcedure.equals("裁床")){
                            setDateCutting();
                        }else{
                            setDateNewly();
                        }
                    }
                });
    }

    /*不可输入状态*/
    private void setEnableFalse() {
        spUtils.put(FTYDLSearchDetailActivity.this,
                "procedureboolean", "2");
        btnProSave.setVisibility(View.GONE);
        tv_config_cutdate.setEnabled(false);
        et_config_others.setEnabled(false);
        et_config_singularsystem.setEnabled(false);
        et_config_color.setEnabled(false);
        et_config_completedlastmonth.setEnabled(false);
        et_config_month.setEnabled(false);
        et_config_oneday.setEnabled(false);
        et_config_twoday.setEnabled(false);
        et_config_threeday.setEnabled(false);
        et_config_foreday.setEnabled(false);
        et_config_fiveday.setEnabled(false);
        et_config_sixday.setEnabled(false);
        et_config_servenday.setEnabled(false);
        et_config_eightday.setEnabled(false);
        et_config_nineday.setEnabled(false);
        et_config_tenday.setEnabled(false);
        et_config_elevenday.setEnabled(false);
        et_config_twelveday.setEnabled(false);
        et_config_thirteenday.setEnabled(false);
        et_config_fourteenday.setEnabled(false);
        et_config_fifteenday.setEnabled(false);
        et_config_sixteenday.setEnabled(false);
        et_config_seventeenday.setEnabled(false);
        et_config_eighteenday.setEnabled(false);
        et_config_nineteenday.setEnabled(false);
        et_config_twentyday.setEnabled(false);
        et_config_TwentyOneDay.setEnabled(false);
        et_config_twentytwoday.setEnabled(false);
        et_config_twentyThreeday.setEnabled(false);
        et_config_twentyforeday.setEnabled(false);
        et_config_twentyfiveday.setEnabled(false);
        et_config_twentysixday.setEnabled(false);
        et_config_twentysevenday.setEnabled(false);
        et_config_twentyeightday.setEnabled(false);
        et_config_twentynineday.setEnabled(false);
        et_config_thirtyday.setEnabled(false);
        et_config_thirtyoneday.setEnabled(false);
        et_config_remarks.setEnabled(false);
        tv_config_department.setEnabled(false);
        tv_config_state.setEnabled(false);
    }

    /*可输入状态*/
    private void setEnableTrue() {
        sp = getSharedPreferences("my_sp", 0);
        btnProSave.setVisibility(View.VISIBLE);
        final ProductionUtil productionUtil = new ProductionUtil();
        spUtils.put(FTYDLSearchDetailActivity.this, "procedureboolean", "1");
        saveBean = new FTYDLDetailSaveBean();
        postdataBean = new FTYDLDetailColorBean.DataBean();
        et_config_others.setEnabled(true);//组别人数
        final int MIN_MARK_OTHER = 0;
        final int MAX_MARK_OTHER = 999999;
        et_config_others.addTextChangedListener(new TextWatcher() {
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
                if (s != null || s.equals("")) {
                    if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                        int markVal = 0;
                        try {
                            markVal = Integer.parseInt(s.toString());
                        } catch (NumberFormatException e) {
                            markVal = 0;
                        }
                        if (markVal > 999999) {
                            ToastUtils.ShowToastMessage("数值太大",
                                    getApplicationContext());
                            et_config_others.setText(String.valueOf(MAX_MARK_OTHER));
                            et_config_others.setSelection(et_config_others.length());
                        }
                        String proitem = et_config_others.getText().toString();
                        String tvprodetailOthers = sp.getString("tvFTYDLDetailWorkers", "");
                        if (tvprodetailOthers == null) {
                            tvprodetailOthers = "";
                        }
                        String nullmemo;
                        if (tvprodetailOthers.equals(proitem)) {
                            nullmemo = "1";
                        } else {
                            nullmemo = "2";
                        }
                        postdataBean.setWorkers(proitem);
                        saveBean = new FTYDLDetailSaveBean();
                        saveBean.setWorkers(proitem);
                        spUtils.put(getApplicationContext(), "pronullothers", nullmemo);
                        spUtils.put(getApplicationContext(), "prosaveothers", proitem);
                        return;
                    }
                }
            }
        });
        et_config_others.setSelection(et_config_others.getText().length());

        et_config_singularsystem.setEnabled(true);//任务数
        final int singular = Integer.parseInt(et_config_singularsystem.getText().toString());
        final int MIN_MARK = 0;
        et_config_singularsystem.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_singularsystem.setText(singular);
                        et_config_singularsystem.setSelection(et_config_singularsystem.length());
                    }
                    String proitem = et_config_singularsystem.getText().toString();
                    String progettasknumber = sp.getString("tvFTYDLDetailTaskqty", "");
                    if (progettasknumber == null) {
                        progettasknumber = "";
                    }
                    String nulltasknumber;
                    if (progettasknumber.equals(proitem)) {
                        nulltasknumber = "1";
                    } else {
                        nulltasknumber = "2";
                    }
                    saveBean.setTaskqty(proitem);
                    spUtils.put(getApplicationContext(), "pronulltasknumber", nulltasknumber);
                    spUtils.put(getApplicationContext(), "prosavetasknunber", proitem);
                    return;
                }
            }
        });
        et_config_singularsystem.setSelection(et_config_singularsystem.getText().length());

        et_config_color.setEnabled(true);
        et_config_completedlastmonth.setEnabled(true);//上月完工数
        et_config_completedlastmonth.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        et_config_completedlastmonth.setText(String.valueOf(999999));
                        et_config_completedlastmonth.setSelection(et_config_completedlastmonth.length());
                    }
                    String proitem = et_config_completedlastmonth.getText().toString();
                    String progetlastmon = sp.getString("tvFTYDLDetailLastMonQty", "");
                    if (progetlastmon == null) {
                        progetlastmon = "";
                    }
                    String nulllastmon;
                    if (progetlastmon.equals(proitem)) {
                        nulllastmon = "1";
                    } else {
                        nulllastmon = "2";
                    }
                    saveBean.setLastMonQty(proitem);
                    spUtils.put(getApplicationContext(), "pronulllastmon", nulllastmon);
                    spUtils.put(getApplicationContext(), "prosavecompletedlastmonth", proitem);
                    CountMonthstr = productionUtil.CountMonth(proitem,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday5,
                            tvnewlyday6, tvnewlyday7, tvnewlyday8, tvnewlyday9,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);//总完工数
                    tv_config_clippingnumber.setText(CountMonthstr);//实裁数
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_completedlastmonth.setSelection(et_config_completedlastmonth.getText().length());

        et_config_month.setEnabled(false);//月份

        et_config_oneday.setEnabled(true);//1
        et_config_oneday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_oneday.setText(String.valueOf(999999));
                        et_config_oneday.setSelection(et_config_oneday.length());
                    }
                    String proitem = et_config_oneday.getText().toString();
                    String progetlastmon = sp.getString("", "");
                    if (progetlastmon == null) {
                        progetlastmon = "";
                    }
                    String nulllastmon;
                    if (progetlastmon.equals(proitem)) {
                        nulllastmon = "1";
                    } else {
                        nulllastmon = "2";
                    }
                    saveBean.setDay1(proitem);
                    spUtils.put(getApplicationContext(), "pronullday1", nulllastmon);
                    spUtils.put(getApplicationContext(), "prosaveoneday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            proitem,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday5,
                            tvnewlyday6, tvnewlyday7, tvnewlyday8, tvnewlyday9,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_oneday.setSelection(et_config_oneday.getText().length());

        et_config_twoday.setEnabled(true);//2
        et_config_twoday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twoday.setText(String.valueOf(999999));
                        et_config_twoday.setSelection(et_config_twoday.length());
                    }
                    String proitem = et_config_twoday.getText().toString();
                    String progetday2 = sp.getString("", "");
                    if (progetday2 == null) {
                        progetday2 = "";
                    }
                    String nullday2;
                    if (progetday2.equals(proitem)) {
                        nullday2 = "1";
                    } else {
                        nullday2 = "2";
                        if (proitem.equals("")) {
                            proitem = String.valueOf(0);
                        }

                    }
                    saveBean.setDay2(proitem);
                    spUtils.put(getApplicationContext(), "pronullday2", nullday2);
                    spUtils.put(getApplicationContext(), "prosavetwoday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            proitem, tvnewlyday3, tvnewlyday4, tvnewlyday5,
                            tvnewlyday6, tvnewlyday7, tvnewlyday8, tvnewlyday9,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twoday.setSelection(et_config_twoday.getText().length());

        et_config_threeday.setEnabled(true);//3
        et_config_threeday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_threeday.setText(String.valueOf(999999));
                        et_config_threeday.setSelection(et_config_threeday.length());
                    }
                    String proitem = et_config_threeday.getText().toString();
                    String progetday3 = sp.getString("tvFTYDLDetailDay3", "");
                    if (progetday3 == null) {
                        progetday3 = "";
                    }
                    String nullday3;
                    if (progetday3.equals(proitem)) {
                        nullday3 = "1";
                    } else {
                        nullday3 = "2";
                        if (proitem.equals("")) {
                            proitem = String.valueOf(0);
                        }
                    }
                    saveBean.setDay3(proitem);
                    spUtils.put(getApplicationContext(), "pronullday3", nullday3);
                    spUtils.put(getApplicationContext(), "prothreeday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, proitem, tvnewlyday4, tvnewlyday5,
                            tvnewlyday6, tvnewlyday7, tvnewlyday8, tvnewlyday9,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_threeday.setSelection(et_config_threeday.getText().length());

        et_config_foreday.setEnabled(true);//4
        et_config_foreday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_foreday.setText(String.valueOf(999999));
                        et_config_foreday.setSelection(et_config_foreday.length());
                    }
                    String proitem = et_config_foreday.getText().toString();
                    String progetday4 = sp.getString("tvFTYDLDetailDay4", "");
                    if (progetday4 == null) {
                        progetday4 = "";
                    }
                    String nullday4;
                    if (progetday4.equals(proitem)) {
                        nullday4 = "1";
                    } else {
                        nullday4 = "2";
                        if (proitem.equals("")) {
                            proitem = String.valueOf(0);
                        }
                    }
                    saveBean.setDay4(proitem);
                    spUtils.put(getApplicationContext(), "pronullday4", nullday4);
                    spUtils.put(getApplicationContext(), "prosaveforeday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, proitem, tvnewlyday5,
                            tvnewlyday6, tvnewlyday7, tvnewlyday8, tvnewlyday9,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_foreday.setSelection(et_config_foreday.getText().length());

        et_config_fiveday.setEnabled(true);//5
        et_config_fiveday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_fiveday.setText(String.valueOf(999999));
                        et_config_fiveday.setSelection(et_config_fiveday.length());
                    }
                    String proitem = et_config_fiveday.getText().toString();
                    String progetday5 = sp.getString("tvFTYDLDetailDay5", "");
                    if (progetday5 == null) {
                        progetday5 = "";
                    }
                    String nullday5;
                    if (progetday5.equals(proitem)) {
                        nullday5 = "1";
                    } else {
                        nullday5 = "2";
                        if (proitem.equals("")) {
                            proitem = String.valueOf(0);
                        }
                    }
                    saveBean.setDay5(proitem);
                    spUtils.put(getApplicationContext(), "pronullday5", nullday5);
                    spUtils.put(getApplicationContext(), "prosavefiveday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, proitem,
                            tvnewlyday6, tvnewlyday7, tvnewlyday8, tvnewlyday9,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_fiveday.setSelection(et_config_fiveday.getText().length());

        et_config_sixday.setEnabled(true);//6
        et_config_sixday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_sixday.setText(String.valueOf(999999));
                        et_config_sixday.setSelection(et_config_sixday.length());
                    }
                    String proitem = et_config_sixday.getText().toString();
                    String progetday6 = sp.getString("tvFTYDLDetailDay6", "");
                    if (progetday6 == null) {
                        progetday6 = "";
                    }
                    String nullday6;
                    if (progetday6.equals(proitem)) {
                        nullday6 = "1";
                    } else {
                        nullday6 = "2";
                    }
                    saveBean.setDay6(proitem);
                    spUtils.put(getApplicationContext(), "pronullday6", nullday6);
                    spUtils.put(getApplicationContext(), "prosavesixday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, proitem,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday9,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_sixday.setSelection(et_config_sixday.getText().length());

        et_config_servenday.setEnabled(true);//7
        et_config_servenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_servenday.setText(String.valueOf(999999));
                        et_config_servenday.setSelection(et_config_servenday.length());
                    }
                    String proitem = et_config_servenday.getText().toString();
                    String progetday7 = sp.getString("tvFTYDLDetailDay7", "");
                    if (progetday7 == null) {
                        progetday7 = "";
                    }
                    String nullday7;
                    if (progetday7.equals(proitem)) {
                        nullday7 = "1";
                    } else {
                        nullday7 = "2";
                    }
                    saveBean.setDay7(proitem);
                    spUtils.put(getApplicationContext(), "pronullday7", nullday7);
                    spUtils.put(getApplicationContext(), "prosavesevenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, proitem, tvnewlyday8, tvnewlyday9,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_servenday.setSelection(et_config_servenday.getText().length());

        et_config_eightday.setEnabled(true);//8
        et_config_eightday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_eightday.setText(String.valueOf(999999));
                        et_config_eightday.setSelection(et_config_eightday.length());
                    }
                    String proitem = et_config_eightday.getText().toString();
                    String progetday8 = sp.getString("tvFTYDLDetailDay8", "");
                    if (progetday8 == null) {
                        progetday8 = "";
                    }
                    String nullday8;
                    if (progetday8.equals(proitem)) {
                        nullday8 = "1";
                    } else {
                        nullday8 = "2";
                    }
                    saveBean.setDay8(proitem);
                    spUtils.put(getApplicationContext(), "pronullday8", nullday8);
                    spUtils.put(getApplicationContext(), "prosaveeightday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, proitem, tvnewlyday9,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_eightday.setSelection(et_config_eightday.getText().length());

        et_config_nineday.setEnabled(true);//9
        et_config_nineday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_nineday.setText(String.valueOf(999999));
                        et_config_nineday.setSelection(et_config_nineday.length());
                    }
                    String proitem = et_config_nineday.getText().toString();
                    String progetday9 = sp.getString("tvFTYDLDetailDay9", "");
                    if (progetday9 == null) {
                        progetday9 = "";
                    }
                    String nullday9;
                    if (progetday9.equals(proitem)) {
                        nullday9 = "1";
                    } else {
                        nullday9 = "2";
                    }
                    saveBean.setDay9(proitem);
                    spUtils.put(getApplicationContext(), "pronullday9", nullday9);
                    spUtils.put(getApplicationContext(), "prosavenineday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, proitem,
                            tvnewlyday10, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_nineday.setSelection(et_config_nineday.getText().length());

        et_config_tenday.setEnabled(true);//10
        et_config_tenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_tenday.setText(String.valueOf(999999));
                        et_config_tenday.setSelection(et_config_tenday.length());
                    }
                    String proitem = et_config_tenday.getText().toString();
                    String progetday10 = sp.getString("tvFTYDLDetailDay10", "");
                    if (progetday10 == null) {
                        progetday10 = "";
                    }
                    String nullday10;
                    if (progetday10.equals(proitem)) {
                        nullday10 = "1";
                    } else {
                        nullday10 = "2";
                    }
                    saveBean.setDay10(proitem);
                    spUtils.put(getApplicationContext(), "pronullday10", nullday10);
                    spUtils.put(getApplicationContext(), "prosavetenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday9,
                            proitem, tvnewlyday11, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_tenday.setSelection(et_config_tenday.getText().length());

        et_config_elevenday.setEnabled(true);//11
        et_config_elevenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_elevenday.setText(String.valueOf(999999));
                        et_config_elevenday.setSelection(et_config_elevenday.length());
                    }
                    String proitem = et_config_elevenday.getText().toString();
                    String progetday11 = sp.getString("tvFTYDLDetailDay11", "");
                    if (progetday11 == null) {
                        progetday11 = "";
                    }
                    String nullday11;
                    if (progetday11.equals(proitem)) {
                        nullday11 = "1";
                    } else {
                        nullday11 = "2";
                    }
                    saveBean.setDay11(proitem);
                    spUtils.put(getApplicationContext(), "pronullday11", nullday11);
                    spUtils.put(getApplicationContext(), "prosaveelevenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday9, proitem, tvnewlyday12, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_elevenday.setSelection(et_config_elevenday.getText().length());

        et_config_twelveday.setEnabled(true);//12
        et_config_twelveday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twelveday.setText(String.valueOf(999999));
                        et_config_twelveday.setSelection(et_config_twelveday.length());
                    }
                    String proitem = et_config_twelveday.getText().toString();
                    String progetday12 = sp.getString("tvFTYDLDetailDay12", "");
                    if (progetday12 == null) {
                        progetday12 = "";
                    }
                    String nullday12;
                    if (progetday12.equals(proitem)) {
                        nullday12 = "1";
                    } else {
                        nullday12 = "2";
                    }
                    saveBean.setDay12(proitem);
                    spUtils.put(getApplicationContext(), "pronullday12", nullday12);
                    spUtils.put(getApplicationContext(), "prosavetwelveday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday9, proitem, tvnewlyday13,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twelveday.setSelection(et_config_twelveday.getText().length());

        et_config_thirteenday.setEnabled(true);//13
        et_config_thirteenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_thirteenday.setText(String.valueOf(999999));
                        et_config_thirteenday.setSelection(et_config_thirteenday.length());
                    }
                    String proitem = et_config_thirteenday.getText().toString();
                    String progetday13 = sp.getString("tvFTYDLDetailDay13", "");
                    if (progetday13 == null) {
                        progetday13 = "";
                    }
                    String nullday13;
                    if (progetday13.equals(proitem)) {
                        nullday13 = "1";
                    } else {
                        nullday13 = "2";
                    }
                    saveBean.setDay13(proitem);
                    spUtils.put(getApplicationContext(), "pronullday13", nullday13);
                    spUtils.put(getApplicationContext(), "prosavethirteenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday9, proitem,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_thirteenday.setSelection(et_config_thirteenday.getText().length());

        et_config_fourteenday.setEnabled(true);//14
        et_config_fourteenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_fourteenday.setText(String.valueOf(999999));
                        et_config_fourteenday.setSelection(et_config_fourteenday.length());
                    }
                    String proitem = et_config_fourteenday.getText().toString();
                    String progetday14 = sp.getString("tvFTYDLDetailDay14", "");
                    if (progetday14 == null) {
                        progetday14 = "";
                    }
                    String nullday14;
                    if (progetday14.equals(proitem)) {
                        nullday14 = "1";
                    } else {
                        nullday14 = "2";
                    }
                    saveBean.setDay14(proitem);
                    spUtils.put(getApplicationContext(), "pronullday14", nullday14);
                    spUtils.put(getApplicationContext(), "prosavefourteenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            proitem, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_fourteenday.setSelection(et_config_fourteenday.getText().length());

        et_config_fifteenday.setEnabled(true);//15
        et_config_fifteenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_fifteenday.setText(String.valueOf(999999));
                        et_config_fifteenday.setSelection(et_config_fifteenday.length());
                    }
                    String proitem = et_config_fifteenday.getText().toString();
                    String progetday15 = sp.getString("tvFTYDLDetailDay15", "");
                    if (progetday15 == null) {
                        progetday15 = "";
                    }
                    String nullday15;
                    if (progetday15.equals(proitem)) {
                        nullday15 = "1";
                    } else {
                        nullday15 = "2";
                    }
                    saveBean.setDay15(proitem);
                    spUtils.put(getApplicationContext(), "pronullday15", nullday15);
                    spUtils.put(getApplicationContext(), "prosavefifteenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, proitem, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_fifteenday.setSelection(et_config_fifteenday.getText().length());

        et_config_sixteenday.setEnabled(true);//16
        et_config_sixteenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_sixteenday.setText(String.valueOf(999999));
                        et_config_sixteenday.setSelection(et_config_sixteenday.length());
                    }
                    String proitem = et_config_sixteenday.getText().toString();
                    String progetday16 = sp.getString("tvFTYDLDetailDay16", "");
                    if (progetday16 == null) {
                        progetday16 = "";
                    }
                    String nullday16;
                    if (progetday16.equals(proitem)) {
                        nullday16 = "1";
                    } else {
                        nullday16 = "2";
                    }
                    saveBean.setDay16(proitem);
                    spUtils.put(getApplicationContext(), "pronullday16", nullday16);
                    spUtils.put(getApplicationContext(), "prosavesixteenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, proitem, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_sixteenday.setSelection(et_config_sixteenday.getText().length());

        et_config_seventeenday.setEnabled(true);//17
        et_config_seventeenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_seventeenday.setText(String.valueOf(999999));
                        et_config_seventeenday.setSelection(et_config_seventeenday.length());
                    }
                    String proitem = et_config_seventeenday.getText().toString();
                    String progetday17 = sp.getString("tvFTYDLDetailDay17", "");
                    if (progetday17 == null) {
                        progetday17 = "";
                    }
                    String nullday17;
                    if (progetday17.equals(proitem)) {
                        nullday17 = "1";
                    } else {
                        nullday17 = "2";
                    }
                    saveBean.setDay17(proitem);
                    spUtils.put(getApplicationContext(), "pronullday17", nullday17);
                    spUtils.put(getApplicationContext(), "prosaveserventeenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, proitem,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_seventeenday.setSelection(et_config_seventeenday.getText().length());

        et_config_eighteenday.setEnabled(true);//18
        et_config_eighteenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_eighteenday.setText(String.valueOf(999999));
                        et_config_eighteenday.setSelection(et_config_eighteenday.length());
                    }
                    String proitem = et_config_eighteenday.getText().toString();
                    String progetday18 = sp.getString("tvFTYDLDetailDay18", "");
                    if (progetday18 == null) {
                        progetday18 = "";
                    }
                    String nullday18;
                    if (progetday18.equals(proitem)) {
                        nullday18 = "1";
                    } else {
                        nullday18 = "2";
                    }
                    saveBean.setDay18(proitem);
                    spUtils.put(getApplicationContext(), "pronullday18", nullday18);
                    spUtils.put(getApplicationContext(), "prosaveeighteenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            proitem, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_eighteenday.setSelection(et_config_eighteenday.getText().length());

        et_config_nineteenday.setEnabled(true);//19
        et_config_nineteenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_nineteenday.setText(String.valueOf(999999));
                        et_config_nineteenday.setSelection(et_config_nineteenday.length());
                    }
                    String proitem = et_config_nineteenday.getText().toString();
                    String progetday19 = sp.getString("tvFTYDLDetailDay19", "");
                    if (progetday19 == null) {
                        progetday19 = "";
                    }
                    String nullday19;
                    if (progetday19.equals(proitem)) {
                        nullday19 = "1";
                    } else {
                        nullday19 = "2";
                    }
                    saveBean.setDay19(proitem);
                    spUtils.put(getApplicationContext(), "pronullday19", nullday19);
                    spUtils.put(getApplicationContext(), "prosavenineteenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, proitem, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_nineteenday.setSelection(et_config_nineteenday.getText().length());

        et_config_twentyday.setEnabled(true);//20
        et_config_twentyday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twentyday.setText(String.valueOf(999999));
                        et_config_twentyday.setSelection(et_config_twentyday.length());
                    }
                    String proitem = et_config_twentyday.getText().toString();
                    String progetday20 = sp.getString("tvFTYDLDetailDay20", "");
                    if (progetday20 == null) {
                        progetday20 = "";
                    }
                    String nullday20;
                    if (progetday20.equals(proitem)) {
                        nullday20 = "1";
                    } else {
                        nullday20 = "2";
                    }
                    saveBean.setDay20(proitem);
                    spUtils.put(getApplicationContext(), "pronullday20", nullday20);
                    spUtils.put(getApplicationContext(), "prosavetwentyday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, proitem, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twentyday.setSelection(et_config_twentyday.getText().length());

        et_config_TwentyOneDay.setEnabled(true);//21
        et_config_TwentyOneDay.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_TwentyOneDay.setText(String.valueOf(999999));
                        et_config_TwentyOneDay.setSelection(et_config_TwentyOneDay.length());
                    }
                    String proitem = et_config_TwentyOneDay.getText().toString();
                    String progetday21 = sp.getString("tvFTYDLDetailDay21", "");
                    if (progetday21 == null) {
                        progetday21 = "";
                    }
                    String nullday21;
                    if (progetday21.equals(proitem)) {
                        nullday21 = "1";
                    } else {
                        nullday21 = "2";
                    }
                    saveBean.setDay21(proitem);
                    spUtils.put(getApplicationContext(), "pronullday21", nullday21);
                    spUtils.put(getApplicationContext(), "prosavetwentyoneday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, proitem,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_TwentyOneDay.setSelection(et_config_TwentyOneDay.getText().length());

        et_config_twentytwoday.setEnabled(true);//22
        et_config_twentytwoday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twentytwoday.setText(String.valueOf(999999));
                        et_config_twentytwoday.setSelection(et_config_twentytwoday.length());
                    }
                    String proitem = et_config_twentytwoday.getText().toString();
                    String progetday22 = sp.getString("tvFTYDLDetailDay22", "");
                    if (progetday22 == null) {
                        progetday22 = "";
                    }
                    String nullday22;
                    if (progetday22.equals(proitem)) {
                        nullday22 = "1";
                    } else {
                        nullday22 = "2";
                    }
                    saveBean.setDay22(proitem);
                    spUtils.put(getApplicationContext(), "pronullday22", nullday22);
                    spUtils.put(getApplicationContext(), "prosavetwentytwoday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            proitem, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twentytwoday.setSelection(et_config_twentytwoday.getText().length());

        et_config_twentyThreeday.setEnabled(true);//23
        et_config_twentyThreeday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twentyThreeday.setText(String.valueOf(999999));
                        et_config_twentyThreeday.setSelection(et_config_twentyThreeday.length());
                    }
                    String proitem = et_config_twentyThreeday.getText().toString();
                    String progetday23 = sp.getString("tvFTYDLDetailDay23", "");
                    if (progetday23 == null) {
                        progetday23 = "";
                    }
                    String nullday23;
                    if (progetday23.equals(proitem)) {
                        nullday23 = "1";
                    } else {
                        nullday23 = "2";
                    }
                    saveBean.setDay23(proitem);
                    spUtils.put(getApplicationContext(), "pronullday23", nullday23);
                    spUtils.put(getApplicationContext(), "prosavetwentythreeday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, proitem, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twentyThreeday.setSelection(et_config_twentyThreeday.getText().length());

        et_config_twentyforeday.setEnabled(true);//24
        et_config_twentyforeday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twentyforeday.setText(String.valueOf(999999));
                        et_config_twentyforeday.setSelection(et_config_twentyforeday.length());
                    }
                    String proitem = et_config_twentyforeday.getText().toString();
                    String progetday24 = sp.getString("tvFTYDLDetailDay24", "");
                    if (progetday24 == null) {
                        progetday24 = "";
                    }
                    String nullday24;
                    if (progetday24.equals(proitem)) {
                        nullday24 = "1";
                    } else {
                        nullday24 = "2";
                    }
                    saveBean.setDay24(proitem);
                    spUtils.put(getApplicationContext(), "pronullday24", nullday24);
                    spUtils.put(getApplicationContext(), "prosavetwentyforeday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, proitem, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twentyforeday.setSelection(et_config_twentyforeday.getText().length());

        et_config_twentyfiveday.setEnabled(true);//25
        et_config_twentyfiveday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twentyfiveday.setText(String.valueOf(999999));
                        et_config_twentyfiveday.setSelection(et_config_twentyfiveday.length());
                    }
                    String proitem = et_config_twentyfiveday.getText().toString();
                    String progetday25 = sp.getString("tvFTYDLDetailDay25", "");
                    if (progetday25 == null) {
                        progetday25 = "";
                    }
                    String nullday25;
                    if (progetday25.equals(proitem)) {
                        nullday25 = "1";
                    } else {
                        nullday25 = "2";
                    }
                    saveBean.setDay25(proitem);
                    spUtils.put(getApplicationContext(), "pronullday25", nullday25);
                    spUtils.put(getApplicationContext(), "prosavetwentyfiveday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, proitem,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twentyfiveday.setSelection(et_config_twentyfiveday.getText().length());

        et_config_twentysixday.setEnabled(true);//26
        et_config_twentysixday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twentysixday.setText(String.valueOf(999999));
                        et_config_twentysixday.setSelection(et_config_twentysixday.length());
                    }
                    String proitem = et_config_twentysixday.getText().toString();
                    String progetday26 = sp.getString("tvFTYDLDetailDay26", "");
                    if (progetday26 == null) {
                        progetday26 = "";
                    }
                    String nullday26;
                    if (progetday26.equals(proitem)) {
                        nullday26 = "1";
                    } else {
                        nullday26 = "2";
                    }
                    saveBean.setDay26(proitem);
                    spUtils.put(getApplicationContext(), "pronullday26", nullday26);
                    spUtils.put(getApplicationContext(), "prosavetwentysixday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            proitem, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twentysixday.setSelection(et_config_twentysixday.getText().length());

        et_config_twentysevenday.setEnabled(true);//27
        et_config_twentysevenday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twentysevenday.setText(String.valueOf(999999));
                        et_config_twentysevenday.setSelection(et_config_twentysevenday.length());
                    }
                    String proitem = et_config_twentysevenday.getText().toString();
                    String progetday27 = sp.getString("tvFTYDLDetailDay27", "");
                    if (progetday27 == null) {
                        progetday27 = "";
                    }
                    String nullday27;
                    if (progetday27.equals(proitem)) {
                        nullday27 = "1";
                    } else {
                        nullday27 = "2";
                    }
                    saveBean.setDay27(proitem);
                    spUtils.put(getApplicationContext(), "pronullday27", nullday27);
                    spUtils.put(getApplicationContext(), "prosavetwentysevenday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, proitem, tvnewlyday28, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twentysevenday.setSelection(et_config_twentysevenday.getText().length());

        et_config_twentyeightday.setEnabled(true);//28
        et_config_twentyeightday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twentyeightday.setText(String.valueOf(999999));
                        et_config_twentyeightday.setSelection(et_config_twentyeightday.length());
                    }
                    String proitem = et_config_twentyeightday.getText().toString();
                    String progetday28 = sp.getString("tvFTYDLDetailDay28", "");
                    if (progetday28 == null) {
                        progetday28 = "";
                    }
                    String nullday28;
                    if (progetday28.equals(proitem)) {
                        nullday28 = "1";
                    } else {
                        nullday28 = "2";
                    }
                    saveBean.setDay28(proitem);
                    spUtils.put(getApplicationContext(), "pronullday28", nullday28);
                    spUtils.put(getApplicationContext(), "prosavetwentyeightday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, proitem, tvnewlyday29,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twentyeightday.setSelection(et_config_twentyeightday.getText().length());

        et_config_twentynineday.setEnabled(true);//29
        et_config_twentynineday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_twentynineday.setText(String.valueOf(999999));
                        et_config_twentynineday.setSelection(et_config_twentynineday.length());
                    }
                    String proitem = et_config_twentynineday.getText().toString();
                    String progetday29 = sp.getString("tvFTYDLDetailDay29", "");
                    if (progetday29 == null) {
                        progetday29 = "";
                    }
                    String nullday29;
                    if (progetday29.equals(proitem)) {
                        nullday29 = "1";
                    } else {
                        nullday29 = "2";
                    }
                    saveBean.setDay29(proitem);
                    spUtils.put(getApplicationContext(), "pronullday29", nullday29);
                    spUtils.put(getApplicationContext(), "prosavetwentynineday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, proitem,
                            tvnewlyday30, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_twentynineday.setSelection(et_config_twentynineday.getText().length());

        et_config_thirtyday.setEnabled(true);//30
        et_config_thirtyday.addTextChangedListener(new TextWatcher() {
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大",
                                getApplicationContext());
                        et_config_thirtyday.setText(String.valueOf(999999));
                        et_config_thirtyday.setSelection(et_config_thirtyday.length());
                    }
                    String proitem = et_config_thirtyday.getText().toString();
                    String progetday30 = sp.getString("tvFTYDLDetailDay30", "");
                    if (progetday30 == null) {
                        progetday30 = "";
                    }
                    String nullday30;
                    if (progetday30.equals(proitem)) {
                        nullday30 = "1";
                    } else {
                        nullday30 = "2";
                    }
                    saveBean.setDay30(proitem);
                    spUtils.put(getApplicationContext(), "pronullday30", nullday30);
                    spUtils.put(getApplicationContext(), "prosavethirtyday", proitem);
                    CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                            tvnewlyday1,
                            tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                            tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                            tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                            tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                            tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                            tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                            tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                            proitem, tvnewlyday31);
                    tv_config_totalcompletion.setText(CountMonthstr);
                    tv_config_clippingnumber.setText(CountMonthstr);
                    saveBean.setSumCompletedQty(CountMonthstr);
                    saveBean.setFactcutqty(CountMonthstr);
                }
                return;
            }
        });
        et_config_thirtyday.setSelection(et_config_thirtyday.getText().length());

        et_config_thirtyoneday.setEnabled(true);//31
        et_config_thirtyoneday.addTextChangedListener(new TextWatcher() {
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
                CharSequence[] month = {"2", "4", "6", "9", "11"};
                String monthstr = sp.getString("tvFTYDLDetailYear", "");
                if (monthstr.equals("2") || monthstr.equals("4")
                        || monthstr.equals("6") || monthstr.equals("9")
                        || monthstr.equals("11")) {
                    ToastUtils.ShowToastMessage("当前" + monthstr + "月没" +
                            "有31日", getApplicationContext());
                } else {
                    if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                        int markVal = 0;
                        try {
                            markVal = Integer.parseInt(s.toString());
                        } catch (NumberFormatException e) {
                            markVal = 0;
                        }
                        if (markVal > 999999) {
                            ToastUtils.ShowToastMessage("数值太大",
                                    getApplicationContext());
                            et_config_thirtyoneday.setText(String.valueOf(999999));
                            et_config_thirtyoneday.setSelection(et_config_thirtyoneday.length());
                        }
                        String proitem = et_config_thirtyoneday.getText().toString();
                        String progetday31 = sp.getString("tvFTYDLDetailDay31", "");
                        if (progetday31 == null) {
                            progetday31 = "";
                        }
                        String nullday31;
                        if (progetday31.equals(proitem)) {
                            nullday31 = "1";
                        } else {
                            nullday31 = "2";
                        }
                        saveBean.setDay31(proitem);
                        spUtils.put(getApplicationContext(), "pronullday31", nullday31);
                        spUtils.put(getApplicationContext(), "prosavethirtyoneday", proitem);
                        CountMonthstr = productionUtil.CountMonth(tvnewlyCompletedLastMonth,
                                tvnewlyday1,
                                tvnewlyday2, tvnewlyday3, tvnewlyday4, tvnewlyday6,
                                tvnewlyday5, tvnewlyday7, tvnewlyday8, tvnewlyday10,
                                tvnewlyday11, tvnewlyday12, tvnewlyday13, tvnewlyday9,
                                tvnewlyday14, tvnewlyday15, tvnewlyday16, tvnewlyday17,
                                tvnewlyday18, tvnewlyday19, tvnewlyday20, tvnewlyday21,
                                tvnewlyday22, tvnewlyday23, tvnewlyday24, tvnewlyday25,
                                tvnewlyday26, tvnewlyday27, tvnewlyday28, tvnewlyday29,
                                tvnewlyday30, proitem);
                        tv_config_totalcompletion.setText(CountMonthstr);
                        tv_config_clippingnumber.setText(CountMonthstr);
                        saveBean.setSumCompletedQty(CountMonthstr);
                        saveBean.setFactcutqty(CountMonthstr);
                    }
                    return;
                }
            }
        });
        et_config_thirtyoneday.setSelection(et_config_thirtyoneday.getText().length());

        et_config_remarks.setEnabled(true);//备注
        et_config_remarks.addTextChangedListener(new TextWatcher() {
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
                String proitem = et_config_remarks.getText().toString();
                String progetmemo = sp.getString("tvFTYDLDetailMemo", "");
                if (progetmemo == null) {
                    progetmemo = "";
                }
                String nullmemo;
                if (progetmemo.equals(proitem)) {
                    nullmemo = "1";
                } else {
                    nullmemo = "2";
                }
                saveBean.setMemo(proitem);
                spUtils.put(getApplicationContext(), "pronullmemo", nullmemo);
                spUtils.put(getApplicationContext(), "prosaveremarks", proitem);
            }
        });
        et_config_remarks.setSelection(et_config_remarks.getText().length());

        tv_config_cutdate.setEnabled(true); //裁床时间
        tv_config_cutdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FTYDLSearchDetailActivity.this, null,
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
                                tv_config_cutdate.setText(datetime);
                                String commopreducdt = sp.getString("prodetailPredocdt", "");
                                if (commopreducdt == null) {
                                    commopreducdt = "";
                                }
                                String nullpreducdt;
                                if (commopreducdt.equals(datetime)) {
                                    nullpreducdt = "1";
                                } else {
                                    nullpreducdt = "2";
                                }
                                spUtils.put(getApplicationContext(), "pronullpreducdt", nullpreducdt);
                                spUtils.put(getApplicationContext(), "prodatePredocdttimesign", datetime);//预计产前报告时间
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                        , "清除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tv_config_cutdate.setText("");
                                String commopreducdt = sp.getString("prodetailPredocdt", "");
                                if (commopreducdt == null) {
                                    commopreducdt = "";
                                }
                                String nullpreducdt;
                                if (commopreducdt.equals("")) {
                                    nullpreducdt = "1";
                                } else {
                                    nullpreducdt = "2";
                                }
                                spUtils.put(getApplicationContext(), "pronullpreducdt", nullpreducdt);
                                spUtils.put(getApplicationContext(), "prodatePredocdttimesign", "");//预计产前报告时间
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

        tv_config_department.setEnabled(true);//部门/组别
        tv_config_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_column, popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        pronullpartment = sp.getString("tvFTYDLDetailFactoryTeams",
                                "");
                        if (pronullpartment == null) {
                            pronullpartment = "";
                        }
                        String nullpartment;
                        if (pronullpartment.equals(title)) {
                            nullpartment = "1";
                        } else {
                            nullpartment = "2";
                        }
                        saveBean.setSubfactoryTeams(title);
                        spUtils.put(getApplicationContext(), "pronullpartment", nullpartment);
                        spUtils.put(getApplicationContext(), "prosavedepartment", title);
                        tv_config_department.setText(title);
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

        tv_config_state.setEnabled(true);//状态
        tv_config_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_prdstatus, popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        String pronullstate = sp.getString("tvFTYDLDetailPrdstatus",
                                "");
                        if (pronullstate == null) {
                            pronullstate = "";
                        }
                        String nullstate;
                        if (pronullstate.equals(title)) {
                            nullstate = "1";
                        } else {
                            nullstate = "2";
                        }
                        saveBean.setPrdstatus(title);
                        spUtils.put(getApplicationContext(), "pronullstate", nullstate);
                        spUtils.put(getApplicationContext(), "prosavestate", title);
                        tv_config_state.setText(title);
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
    }

    /*根据salesid查询花色*/
    private List<FTYDLFillColSltBean.Data> setFillColSlt(String id) {
        if (NetWork.isNetWorkAvailable(this)) {
            String strColSelect = HttpUrl.debugoneUrl + "FactoryPlan/FTYDLColSelect/" + id;
            OkHttpUtils.post()
                    .url(strColSelect)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.println(response);
                            Gson gson1 = new Gson();
                            JsonParser jsonParser = new JsonParser();
                            JsonElement element = jsonParser.parse(response);
                            JsonObject jsonObject = null;
                            if (element.isJsonObject()) {
                                jsonObject = element.getAsJsonObject();
                            }
                            JsonArray jsonarray = null;
                            if (element.isJsonArray()) {
                                jsonarray = element.getAsJsonArray();
                            }
                            Iterator iterator = jsonarray.iterator();
                            while (iterator.hasNext()) {
                                JsonElement e = (JsonElement) iterator.next();
                                fillcolBean = gson1.fromJson(e, FTYDLFillColSltBean.Data.class);
                                listfillcolBean.add(fillcolBean);
                            }
                            System.out.println(listfillcolBean);
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp,
                    FTYDLSearchDetailActivity.this);
        }
        return listfillcolBean;
    }

    /*修改保存*/
    private void setSaveDate() {
        sp = getSharedPreferences("my_sp", 0);
        if (NetWork.isNetWorkAvailable(this)) {
            String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
            setVariable();
            ResponseDialog.showLoading(this, "正在保存");
            //如果没有修改部门或者月份，则不需要复制
            if (pronullpartment.equals("1")) {
                if (tvnewlyProcedure.equals("裁床")) {
                    for (int i = 0; i < newdataBeans.size(); i++) {
                        newdataBeans.get(i).setWorkers(saveothers);//组别人数
                        newdataBeans.get(i).setMemo(saveremarks);//备注
                        int balan = Integer.parseInt(procalbeanlist.get(i).getBalanceAmount());
                        int clippingint;
                        int cli;
                        String total;
                        if (procalbeanlist.get(i).getProClippingnumber() == null) {
                            String tvprodetailClippingNumber = sp.getString("tvFTYDLDetailFactcutqty",
                                    "");
                            cli = Integer.parseInt(tvprodetailClippingNumber);
                            clippingint = cli;
                            String tvprodetailCompletedLastMonth = sp.getString(
                                    "tvFTYDLDetailSumCompletedQty", "");
                            total = tvprodetailCompletedLastMonth;
                        } else {
                            cli = Integer.parseInt(procalbeanlist.get(i).getProClippingnumber());
                            clippingint = cli;
                            total = procalbeanlist.get(i).getProTotalCompletion();
                        }
                        newdataBeans.get(i).setLeftQty(String.valueOf(balan - clippingint));//结余数量
                        newdataBeans.get(i).setSumCompletedQty(
                                total);//总完工数
                        newdataBeans.get(i).setFactcutqty(String.valueOf(cli));//实裁数
                        if (datetime == 1) {
                            newdataBeans.get(i).setDay1(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 2) {
                            newdataBeans.get(i).setDay2(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 3) {
                            newdataBeans.get(i).setDay3(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 4) {
                            newdataBeans.get(i).setDay4(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 5) {
                            newdataBeans.get(i).setDay5(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 6) {
                            newdataBeans.get(i).setDay6(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 7) {
                            newdataBeans.get(i).setDay7(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 8) {
                            newdataBeans.get(i).setDay8(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 9) {
                            newdataBeans.get(i).setDay9(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 10) {
                            newdataBeans.get(i).setDay10(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 11) {
                            newdataBeans.get(i).setDay11(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 12) {
                            newdataBeans.get(i).setDay12(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 13) {
                            newdataBeans.get(i).setDay13(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 14) {
                            newdataBeans.get(i).setDay14(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 15) {
                            newdataBeans.get(i).setDay15(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 16) {
                            newdataBeans.get(i).setDay16(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 17) {
                            newdataBeans.get(i).setDay17(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 18) {
                            newdataBeans.get(i).setDay18(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 19) {
                            newdataBeans.get(i).setDay19(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 20) {
                            newdataBeans.get(i).setDay20(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 21) {
                            newdataBeans.get(i).setDay21(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 22) {
                            newdataBeans.get(i).setDay22(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 23) {
                            newdataBeans.get(i).setDay23(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 24) {
                            newdataBeans.get(i).setDay24(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 25) {
                            newdataBeans.get(i).setDay25(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 26) {
                            newdataBeans.get(i).setDay26(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 27) {
                            newdataBeans.get(i).setDay27(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 28) {
                            newdataBeans.get(i).setDay28(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 29) {
                            newdataBeans.get(i).setDay29(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 30) {
                            newdataBeans.get(i).setDay30(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 31) {
                            newdataBeans.get(i).setDay31(procalbeanlist.get(i).getProNum());
                        }
                        newdataBeans.get(i).setPrdstatus(savestate);//状态
                        newdataBeans.get(i).setSubfactoryTeams(saveSubDepartment);//部门组别
                    }
                } else {//除了裁床之外的保存方式
                    for (int i = 0; i < newdataBeans.size(); i++) {
                        String completion = tv_config_totalcompletion.getText().toString();
                        newdataBeans.get(i).setWorkers(saveothers);//组别人数
                        newdataBeans.get(i).setMemo(saveremarks);//备注
                        newdataBeans.get(i).setSubfactoryTeams(saveSubDepartment);//部门组别
                        newdataBeans.get(i).setPrdstatus(savestate);//状态
                        newdataBeans.get(i).setTaskqty(savetasknunber);//任务数
                        newdataBeans.get(i).setLastMonQty(savelastmonth);//上月完工数
                        newdataBeans.get(i).setSumCompletedQty(completion);//总完工数
                        newdataBeans.get(i).setLeftQty(
                                String.valueOf(Integer.parseInt(savetasknunber) - Integer.parseInt(completion)));//结余数量
                        String cli = tv_config_clippingnumber.getText().toString();
                        newdataBeans.get(i).setFactcutqty(cli);
                        newdataBeans.get(i).setDay1(save1);//1
                        newdataBeans.get(i).setDay2(save2);//2
                        newdataBeans.get(i).setDay3(save3);//3
                        newdataBeans.get(i).setDay4(save4);//4
                        newdataBeans.get(i).setDay5(save5);//5
                        newdataBeans.get(i).setDay6(save6);//6
                        newdataBeans.get(i).setDay7(save7);//7
                        newdataBeans.get(i).setDay8(save8);//8
                        newdataBeans.get(i).setDay9(save9);//9
                        newdataBeans.get(i).setDay10(save10);//10
                        newdataBeans.get(i).setDay11(save11);//11
                        newdataBeans.get(i).setDay12(save12);//12
                        newdataBeans.get(i).setDay13(save13);//13
                        newdataBeans.get(i).setDay14(save14);//14
                        newdataBeans.get(i).setDay15(save15);//15
                        newdataBeans.get(i).setDay16(save16);//16
                        newdataBeans.get(i).setDay17(save17);//17
                        newdataBeans.get(i).setDay18(save18);//18
                        newdataBeans.get(i).setDay19(save19);//19
                        newdataBeans.get(i).setDay20(save20);//20
                        newdataBeans.get(i).setDay21(save21);//21
                        newdataBeans.get(i).setDay22(save22);//22
                        newdataBeans.get(i).setDay23(save23);//23
                        newdataBeans.get(i).setDay24(save24);//24
                        newdataBeans.get(i).setDay25(save25);//25
                        newdataBeans.get(i).setDay26(save26);//26
                        newdataBeans.get(i).setDay27(save27);//27
                        newdataBeans.get(i).setDay28(save28);//28
                        newdataBeans.get(i).setDay29(save29);//29
                        newdataBeans.get(i).setDay30(save30);//30
                        newdataBeans.get(i).setDay31(save31);//31
                    }
                }
            } else {//如果修改了部门，则复制该条数据
                if (tvnewlyProcedure.equals("裁床")) {
                    for (int i = 0; i < newdataBeans.size(); i++) {
                        int balan = Integer.parseInt(procalbeanlist.get(i).getBalanceAmount());
                        int clippingint;
                        int cli;
                        String total;
                        if (procalbeanlist.get(i).getProClippingnumber() == null) {
                            String tvprodetailClippingNumber = sp.getString("tvFTYDLDetailFactcutqty",
                                    "");
                            cli = Integer.parseInt(tvprodetailClippingNumber);
                            clippingint = cli;
                            String tvprodetailCompletedLastMonth = sp.getString(
                                    "tvFTYDLDetailSumCompletedQty", "");
                            total = tvprodetailCompletedLastMonth;
                        } else {
                            cli = Integer.parseInt(procalbeanlist.get(i).getProClippingnumber());
                            clippingint = cli;
                            total = procalbeanlist.get(i).getProTotalCompletion();
                        }
                        Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
                        t.setToNow(); // 取得系统时间。
                        year = t.year;
                        month = t.month;
                        datetime = t.monthDay;
                        hour = t.hour; // 0-23
                        minute = t.minute;
                        second = t.second;
                        month = month + 1;
                        newdataBeans.get(i).setID(String.valueOf(0));//id
                        newdataBeans.get(i).setSalesid(id);//排单id
                        newdataBeans.get(i).setWorkers(saveothers);//组别人数
                        newdataBeans.get(i).setLeftQty(String.valueOf(balan - clippingint));//结余数量
                        newdataBeans.get(i).setSumCompletedQty(total);//总完工数
                        newdataBeans.get(i).setSubfactoryTeams(saveSubDepartment);//部门组别
                        newdataBeans.get(i).setFactcutqty(String.valueOf(cli));//实裁数
                        newdataBeans.get(i).setPrdstatus(savestate);//状态
                        newdataBeans.get(i).setMemo(saveremarks);//备注
                        if (datetime == 1) {
                            newdataBeans.get(i).setDay1(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 2) {
                            newdataBeans.get(i).setDay2(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 3) {
                            newdataBeans.get(i).setDay3(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 4) {
                            newdataBeans.get(i).setDay4(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 5) {
                            newdataBeans.get(i).setDay5(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 6) {
                            newdataBeans.get(i).setDay6(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 7) {
                            newdataBeans.get(i).setDay7(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 8) {
                            newdataBeans.get(i).setDay8(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 9) {
                            newdataBeans.get(i).setDay9(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 10) {
                            newdataBeans.get(i).setDay10(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 11) {
                            newdataBeans.get(i).setDay11(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 12) {
                            newdataBeans.get(i).setDay12(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 13) {
                            newdataBeans.get(i).setDay13(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 14) {
                            newdataBeans.get(i).setDay14(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 15) {
                            newdataBeans.get(i).setDay15(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 16) {
                            newdataBeans.get(i).setDay16(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 17) {
                            newdataBeans.get(i).setDay17(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 18) {
                            newdataBeans.get(i).setDay18(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 19) {
                            newdataBeans.get(i).setDay19(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 20) {
                            newdataBeans.get(i).setDay20(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 21) {
                            newdataBeans.get(i).setDay21(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 22) {
                            newdataBeans.get(i).setDay22(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 23) {
                            newdataBeans.get(i).setDay23(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 24) {
                            newdataBeans.get(i).setDay24(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 25) {
                            newdataBeans.get(i).setDay25(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 26) {
                            newdataBeans.get(i).setDay26(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 27) {
                            newdataBeans.get(i).setDay27(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 28) {
                            newdataBeans.get(i).setDay28(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 29) {
                            newdataBeans.get(i).setDay29(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 30) {
                            newdataBeans.get(i).setDay30(procalbeanlist.get(i).getProNum());
                        } else if (datetime == 31) {
                            newdataBeans.get(i).setDay31(procalbeanlist.get(i).getProNum());
                        }
                    }
                } else {
                    for (int i = 0; i < newdataBeans.size(); i++) {
                        String completion = tv_config_totalcompletion.getText().toString();
                        newdataBeans.get(i).setWorkers(saveothers);//组别人数
                        newdataBeans.get(i).setMemo(saveremarks);//备注
                        newdataBeans.get(i).setPrdstatus(savestate);//状态
                        newdataBeans.get(i).setTaskqty(savetasknunber);//任务数
                        newdataBeans.get(i).setLastMonQty(savelastmonth);//上月完工数
                        newdataBeans.get(i).setSumCompletedQty(completion);//总完工数
                        newdataBeans.get(i).setSubfactoryTeams(saveSubDepartment);//部门组别
                        newdataBeans.get(i).setLeftQty(
                                String.valueOf(Integer.parseInt(savetasknunber) -
                                        Integer.parseInt(completion)));//结余数量
                        String cli = tv_config_clippingnumber.getText().toString();
                        newdataBeans.get(i).setFactcutqty(cli);//实裁数
                        newdataBeans.get(i).setDay1(save1);//1
                        newdataBeans.get(i).setDay2(save2);//2
                        newdataBeans.get(i).setDay3(save3);//3
                        newdataBeans.get(i).setDay4(save4);//4
                        newdataBeans.get(i).setDay5(save5);//5
                        newdataBeans.get(i).setDay6(save6);//6
                        newdataBeans.get(i).setDay7(save7);//7
                        newdataBeans.get(i).setDay8(save8);//8
                        newdataBeans.get(i).setDay9(save9);//9
                        newdataBeans.get(i).setDay10(save10);//10
                        newdataBeans.get(i).setDay11(save11);//11
                        newdataBeans.get(i).setDay12(save12);//12
                        newdataBeans.get(i).setDay13(save13);//13
                        newdataBeans.get(i).setDay14(save14);//14
                        newdataBeans.get(i).setDay15(save15);//15
                        newdataBeans.get(i).setDay16(save16);//16
                        newdataBeans.get(i).setDay17(save17);//17
                        newdataBeans.get(i).setDay18(save18);//18
                        newdataBeans.get(i).setDay19(save19);//19
                        newdataBeans.get(i).setDay20(save20);//20
                        newdataBeans.get(i).setDay21(save21);//21
                        newdataBeans.get(i).setDay22(save22);//22
                        newdataBeans.get(i).setDay23(save23);//23
                        newdataBeans.get(i).setDay24(save24);//24
                        newdataBeans.get(i).setDay25(save25);//25
                        newdataBeans.get(i).setDay26(save26);//26
                        newdataBeans.get(i).setDay27(save27);//27
                        newdataBeans.get(i).setDay28(save28);//28
                        newdataBeans.get(i).setDay29(save29);//29
                        newdataBeans.get(i).setDay30(save30);//30
                        newdataBeans.get(i).setDay31(save31);//31
                    }
                }
            }
            setBlackpro();
            DeleteSp();
            //ture为没有修改，false是修改过
            if (flagblack == true) {
                ToastUtils.ShowToastMessage("未修改表中数据",
                        FTYDLSearchDetailActivity.this);
                ResponseDialog.closeLoading();
            } else {
                Gson gson = new Gson();
                String detailb = gson.toJson(newdataBeans);
                OkHttpUtils.postString().
                        url(saveurl)
                        .content(detailb)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, final Exception e, int id) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }

                            @Override
                            public void onResponse(final String response, int id) {
                                System.out.print(response);
                                String ression = StringUtil.sideTrim(response, "\"");
                                System.out.print(ression);
                                int resindex = Integer.parseInt(ression);
                                if (resindex > 3) {
                                    ToastUtils.ShowToastMessage("保存成功",
                                            FTYDLSearchDetailActivity.this);
                                } else if (ression == "3" || ression.equals("3")) {
                                    ToastUtils.ShowToastMessage("保存失败",
                                            FTYDLSearchDetailActivity.this);
                                } else if (ression == "4" || ression.equals("4")) {
                                    ToastUtils.ShowToastMessage("数据错误，请重试",
                                            FTYDLSearchDetailActivity.this);
                                } else if (ression == "2" || ression.equals("2")) {
                                    ToastUtils.ShowToastMessage("该单已存在无法新建！",
                                            FTYDLSearchDetailActivity.this);
                                } else {
                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                            FTYDLSearchDetailActivity.this);
                                }
                                ResponseDialog.closeLoading();
                            }
                        });
            }
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp,
                    FTYDLSearchDetailActivity.this);
        }
    }

    /*退出时判断是否保存修改过的数据*/
    private void setBlackpro() {
        sp = getSharedPreferences("my_sp", 0);
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
        String prosavecount = sp.getString("prosavecount", "");
        if (prosavecount.equals("") && prosaveothers.equals("") && prosavetasknunber.equals("") &&
                prosavecompletedlastmonth.equals("") && prosaveoneday.equals("")
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
            flagblack = true;//数值为空可以退出
        } else {
            flagblack = false;//数值不为空，弹框询问保存
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回*/
            case R.id.ivProductionBack:
                getBooleanSave();
                if (saveboolean.equals("未修改")) {
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("退出提示");
                    builder.setMessage("有修改的数据,退出是否保存");
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
                                    procalbeanlist.clear();
                                    finish();
                                }
                            });
                    noticeDialog = builder.create();
                    noticeDialog.setCanceledOnTouchOutside(false);
                    noticeDialog.show();
                }
                break;
            /*保存*/
            case R.id.btnProSave:
                getBooleanSave();
                if (saveboolean.equals("未修改")) {
                    ToastUtils.ShowToastMessage("未修改表中数据", FTYDLSearchDetailActivity.this);
                } else {
                    setSaveDate();
                }
                break;
        }
    }

    @Override//物理返回
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            getBooleanSave();
            if (saveboolean.equals("未修改")) {
                finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("退出提示");
                builder.setMessage("有修改的数据,退出是否保存");
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
                                procalbeanlist.clear();
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

    /*判断是否修改过数据*/
    private void getBooleanSave() {
        sp = getSharedPreferences("my_sp", 0);
                /*判断全部可填的数据是否都为空,空则转换成1，空则显示未修改数据*/
        String pronullothers = sp.getString("pronullothers", "");//组别人数是否相同
        if (pronullothers.equals("")) {
            pronullothers = "1";
        }
        String pronulltasknumber = sp.getString("pronulltasknumber", "");//任务数是否相同
        if (pronulltasknumber.equals("")) {
            pronulltasknumber = "1";
        }
        String pronulllastmon = sp.getString("pronulllastmon", "");//上月完工数是否相同
        if (pronulllastmon.equals("")) {
            pronulllastmon = "1";
        }
        String pronullprocedure = sp.getString("pronullprocedure", "");//工序是否相同
        if (pronullprocedure.equals("")) {
            pronullprocedure = "1";
        }
        String pronullpartment = sp.getString("pronullpartment", "");//部门组别是否相同
        if (pronullpartment.equals("")) {
            pronullpartment = "1";
        }
        String pronullstate = sp.getString("pronullstate", "");//状态是否相同
        if (pronullstate.equals("")) {
            pronullstate = "1";
        }
        String pronullmonth = sp.getString("pronullmonth", "");//月份是否相同
        if (pronullmonth.equals("")) {
            pronullmonth = "1";
        }
        String pronullday1 = sp.getString("pronullday1", "");//一日是否相同
        if (pronullday1.equals("")) {
            pronullday1 = "1";
        }
        String pronullday2 = sp.getString("pronullday2", "");//二日
        if (pronullday2.equals("")) {
            pronullday2 = "1";
        }
        String pronullday3 = sp.getString("pronullday3", "");
        if (pronullday3.equals("")) {
            pronullday3 = "1";
        }
        String pronullday4 = sp.getString("pronullday4", "");
        if (pronullday4.equals("")) {
            pronullday4 = "1";
        }
        String pronullday5 = sp.getString("pronullday5", "");
        if (pronullday5.equals("")) {
            pronullday5 = "1";
        }
        String pronullday6 = sp.getString("pronullday6", "");
        if (pronullday6.equals("")) {
            pronullday6 = "1";
        }
        String pronullday7 = sp.getString("pronullday7", "");
        if (pronullday7.equals("")) {
            pronullday7 = "1";
        }
        String pronullday8 = sp.getString("pronullday8", "");
        if (pronullday8.equals("")) {
            pronullday8 = "1";
        }
        String pronullday9 = sp.getString("pronullday9", "");
        if (pronullday9.equals("")) {
            pronullday9 = "1";
        }
        String pronullday10 = sp.getString("pronullday10", "");
        if (pronullday10.equals("")) {
            pronullday10 = "1";
        }
        String pronullday11 = sp.getString("pronullday11", "");
        if (pronullday11.equals("")) {
            pronullday11 = "1";
        }
        String pronullday12 = sp.getString("pronullday12", "");
        if (pronullday12.equals("")) {
            pronullday12 = "1";
        }
        String pronullday13 = sp.getString("pronullday13", "");
        if (pronullday13.equals("")) {
            pronullday13 = "1";
        }
        String pronullday14 = sp.getString("pronullday14", "");
        if (pronullday14.equals("")) {
            pronullday14 = "1";
        }
        String pronullday15 = sp.getString("pronullday15", "");
        if (pronullday15.equals("")) {
            pronullday15 = "1";
        }
        String pronullday16 = sp.getString("pronullday16", "");
        if (pronullday16.equals("")) {
            pronullday16 = "1";
        }
        String pronullday17 = sp.getString("pronullday17", "");
        if (pronullday17.equals("")) {
            pronullday17 = "1";
        }
        String pronullday18 = sp.getString("pronullday18", "");
        if (pronullday18.equals("")) {
            pronullday18 = "1";
        }
        String pronullday19 = sp.getString("pronullday19", "");
        if (pronullday19.equals("")) {
            pronullday19 = "1";
        }
        String pronullday20 = sp.getString("pronullday20", "");
        if (pronullday20.equals("")) {
            pronullday20 = "1";
        }
        String pronullday21 = sp.getString("pronullday21", "");
        if (pronullday21.equals("")) {
            pronullday21 = "1";
        }
        String pronullday22 = sp.getString("pronullday22", "");
        if (pronullday22.equals("")) {
            pronullday22 = "1";
        }
        String pronullday23 = sp.getString("pronullday23", "");
        if (pronullday23.equals("")) {
            pronullday23 = "1";
        }
        String pronullday24 = sp.getString("pronullday24", "");
        if (pronullday24.equals("")) {
            pronullday24 = "1";
        }
        String pronullday25 = sp.getString("pronullday25", "");
        if (pronullday25.equals("")) {
            pronullday25 = "1";
        }
        String pronullday26 = sp.getString("pronullday26", "");
        if (pronullday26.equals("")) {
            pronullday26 = "1";
        }
        String pronullday27 = sp.getString("pronullday27", "");
        if (pronullday27.equals("")) {
            pronullday27 = "1";
        }
        String pronullday28 = sp.getString("pronullday28", "");
        if (pronullday28.equals("")) {
            pronullday28 = "1";
        }
        String pronullday29 = sp.getString("pronullday29", "");
        if (pronullday29.equals("")) {
            pronullday29 = "1";
        }
        String pronullday30 = sp.getString("pronullday30", "");
        if (pronullday30.equals("")) {
            pronullday30 = "1";
        }
        String pronullday31 = sp.getString("pronullday31", "");
        if (pronullday31.equals("")) {
            pronullday31 = "1";
        }
        String pronullmemo = sp.getString("pronullmemo", "");
        if (pronullmemo.equals("")) {
            pronullmemo = "1";
        }
        String prosavecount = sp.getString("prosavecount", "");
        if (prosavecount.equals("")) {
            prosavecount = "1";
        }
        if (prosavecount.equals("1") && pronullothers.equals("1") && pronulltasknumber.equals("1")
                && pronulllastmon.equals("1")
                && pronullday1.equals("1") && pronullday2.equals("1") && pronullday3.equals("1")
                && pronullday4.equals("1") && pronullday5.equals("1") && pronullday6.equals("1")
                && pronullday7.equals("1") && pronullday8.equals("1") && pronullday9.equals("1")
                && pronullday10.equals("1") && pronullday11.equals("1") && pronullday12.equals("1")
                && pronullday13.equals("1") && pronullday14.equals("1") && pronullday15.equals("1")
                && pronullday16.equals("1") && pronullday17.equals("1") && pronullday18.equals("1")
                && pronullday19.equals("1") && pronullday20.equals("1") && pronullday21.equals("1")
                && pronullday22.equals("1") && pronullday23.equals("1") && pronullday24.equals("1")
                && pronullday25.equals("1") && pronullday26.equals("1") && pronullday27.equals("1")
                && pronullday28.equals("1") && pronullday29.equals("1") && pronullday30.equals("1")
                && pronullmemo.equals("1") && pronullpartment.equals("1") && pronullmonth.equals("1")
                && pronullprocedure.equals("1") && pronullstate.equals("1") && pronullday31.equals("1")) {
            saveboolean = "未修改";
        } else {
            saveboolean = "已修改";
        }
    }

    /*获取数据的变量*/
    private void setVariable() {
        sp = getSharedPreferences("my_sp", 0);
        pronullpartment = sp.getString("pronullpartment", "");//部门组别是否相同
        if (pronullpartment.equals("")) {
            pronullpartment = "1";
        }
        Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
        t.setToNow(); // 取得系统时间。
        year = t.year;
        month = t.month;
        datetime = t.monthDay;
        hour = t.hour; // 0-23
        minute = t.minute;
        second = t.second;
        month = month + 1;

        String prosaveothers = sp.getString("prosaveothers", "");//修改的组别人数
        if (prosaveothers.isEmpty() || prosaveothers.equals(tvnewlyOthers)) {
            saveothers = tvnewlyOthers;
        } else {
            saveothers = prosaveothers;
        }

        String prosaveremarks = sp.getString("prosaveremarks", "");//修改的备注
        if (prosaveremarks.isEmpty() || prosaveremarks.equals(tvnewlymemo)) {
            saveremarks = tvnewlymemo;
        } else {
            saveremarks = prosaveremarks;
        }

        String prosavestate = sp.getString("prosavestate", "");//修改的状态
        if (prosavestate.isEmpty() || prosavestate.equals(tvnewlyTotal)) {
            savestate = tvnewlyTotal;
        } else {
            savestate = prosavestate;
        }

        String prosavedepartment = sp.getString("prosavedepartment",
                "");//修改的部门组别
        if (prosavedepartment.isEmpty() || prosavedepartment.equals(tvnewlyDepartment)) {
            saveSubDepartment = tvnewlyDepartment;
        } else {
            saveSubDepartment = prosavedepartment;
        }

        String prosavetasknunber = sp.
                getString("prosavetasknunber", "");//修改的任务数
        if (prosavetasknunber.isEmpty() || prosavetasknunber.equals(tvTaskqty)) {
            savetasknunber = tvTaskqty;
        } else {
            savetasknunber = prosavetasknunber;
        }

        String prosavecompletedlastmonth = sp.
                getString("prosavecompletedlastmonth", "");//修改的上月完工数
        if (prosavecompletedlastmonth.isEmpty() || prosavecompletedlastmonth.equals(tvnewlylastMonQty)) {
            savelastmonth = tvnewlylastMonQty;
        } else {
            savelastmonth = prosavecompletedlastmonth;
        }

        String prosaveoneday = sp.getString("prosaveoneday", "");//修改的1日
        if (prosaveoneday.isEmpty() || prosaveoneday.equals(tvnewlyday1)) {
            save1 = tvnewlyday1;
        } else {
            save1 = prosaveoneday;
        }

        String prosavetwoday = sp.getString("prosavetwoday", "");//修改的2日
        if (prosavetwoday.isEmpty() || prosavetwoday.equals(tvnewlyday2)) {
            save2 = tvnewlyday2;
        } else {
            save2 = prosavetwoday;
        }

        String prothreeday = sp.getString("prothreeday", "");//修改的3日
        if (prothreeday.isEmpty() || prothreeday.equals(tvnewlyday3)) {
            save3 = tvnewlyday3;
        } else {
            save3 = prothreeday;
        }

        String prosaveforeday = sp.getString("prosaveforeday", "");//修改的4日
        if (prosaveforeday.isEmpty() || prosaveforeday.equals(tvnewlyday4)) {
            save4 = tvnewlyday4;
        } else {
            save4 = prosaveforeday;
        }

        String prosavefiveday = sp.getString("prosavefiveday", "");//修改的5日
        if (prosavefiveday.isEmpty() || prosavefiveday.equals(tvnewlyday5)) {
            save5 = tvnewlyday5;
        } else {
            save5 = prosavefiveday;
        }

        String prosavesixday = sp.getString("prosavesixday", "");//修改的6日
        if (prosavesixday.isEmpty() || prosavesixday.equals(tvnewlyday6)) {
            save6 = tvnewlyday6;
        } else {
            save6 = prosavesixday;
        }

        String prosavesevenday = sp.getString("prosavesevenday", "");//修改的7日
        if (prosavesevenday.isEmpty() || prosavesevenday.equals(tvnewlyday7)) {
            save7 = tvnewlyday7;
        } else {
            save7 = prosavesevenday;
        }

        String prosaveeightday = sp.getString("prosaveeightday", "");//修改的8日
        if (prosaveeightday.isEmpty() || prosaveeightday.equals(tvnewlyday8)) {
            save8 = tvnewlyday8;
        } else {
            save8 = prosaveeightday;
        }

        String prosavenineday = sp.getString("prosavenineday", "");//修改的9日
        if (prosavenineday.isEmpty() || prosavenineday.equals(tvnewlyday9)) {
            save9 = tvnewlyday9;
        } else {
            save9 = prosavenineday;
        }

        String prosavetenday = sp.getString("prosavetenday", "");//修改的10日
        if (prosavetenday.isEmpty() || prosavetenday.equals(tvnewlyday10)) {
            save10 = tvnewlyday10;
        } else {
            save10 = prosavetenday;
        }

        String prosaveelevenday = sp.getString("prosaveelevenday", "");//修改的11日
        if (prosaveelevenday.isEmpty() || prosaveelevenday.equals(tvnewlyday11)) {
            save11 = tvnewlyday11;
        } else {
            save11 = prosaveelevenday;
        }

        String prosavetwelveday = sp.getString("prosavetwelveday", "");//修改的12日
        if (prosavetwelveday.isEmpty() || prosavetwelveday.equals(tvnewlyday12)) {
            save12 = tvnewlyday12;
        } else {
            save12 = prosavetwelveday;
        }

        String prosavethirteenday = sp.getString("prosavethirteenday", "");//修改的13日
        if (prosavethirteenday.isEmpty() || prosavethirteenday.equals(tvnewlyday13)) {
            save13 = tvnewlyday13;
        } else {
            save13 = prosavethirteenday;
        }

        String prosavefourteenday = sp.getString("prosavefourteenday", "");//修改的14日
        if (prosavefourteenday.isEmpty() || prosavefourteenday.equals(tvnewlyday14)) {
            save14 = tvnewlyday14;
        } else {
            save14 = prosavefourteenday;
        }

        String prosavefifteenday = sp.getString("prosavefifteenday", "");//修改的15日
        if (prosavefifteenday.isEmpty() || prosavefifteenday.equals(tvnewlyday15)) {
            save15 = tvnewlyday15;
        } else {
            save15 = prosavefifteenday;
        }

        String prosavesixteenday = sp.getString("prosavesixteenday", "");//修改的16日
        if (prosavesixteenday.isEmpty() || prosavesixteenday.equals(tvnewlyday16)) {
            save16 = tvnewlyday16;
        } else {
            save16 = prosavesixteenday;
        }

        String prosaveserventeenday = sp.getString("prosaveserventeenday", "");//修改的17日
        if (prosaveserventeenday.isEmpty() || prosaveserventeenday.equals(tvnewlyday17)) {
            save17 = tvnewlyday17;
        } else {
            save17 = prosaveserventeenday;
        }
        String prosaveeighteenday = sp.getString("prosaveeighteenday", "");//修改的18日
        if (prosaveeighteenday.isEmpty() || prosaveeighteenday.equals(tvnewlyday18)) {
            save18 = tvnewlyday18;
        } else {
            save18 = prosaveeighteenday;
        }

        String prosavenineteenday = sp.getString("prosavenineteenday", "");//修改的19日
        if (prosavenineteenday.isEmpty() || prosavenineteenday.equals(tvnewlyday19)) {
            save19 = tvnewlyday19;
        } else {
            save19 = prosavenineteenday;
        }

        String prosavetwentyday = sp.getString("prosavetwentyday", "");//修改的20日
        if (prosavetwentyday.isEmpty() || prosavetwentyday.equals(tvnewlyday20)) {
            save20 = tvnewlyday20;
        } else {
            save20 = prosavetwentyday;
        }

        String prosavetwentyoneday = sp.getString("prosavetwentyoneday", "");//修改的21日
        if (prosavetwentyoneday.isEmpty() || prosavetwentyoneday.equals(tvnewlyday21)) {
            save21 = tvnewlyday21;
        } else {
            save21 = prosavetwentyoneday;
        }

        String prosavetwentytwoday = sp.getString("prosavetwentytwoday", "");//修改的22日
        if (prosavetwentytwoday.isEmpty() || prosavetwentytwoday.equals(tvnewlyday22)) {
            save22 = tvnewlyday22;
        } else {
            save22 = prosavetwentytwoday;
        }

        String prosavetwentythreeday = sp.getString("prosavetwentythreeday", "");//修改的23日
        if (prosavetwentythreeday.isEmpty() || prosavetwentythreeday.equals(tvnewlyday23)) {
            save23 = tvnewlyday23;
        } else {
            save23 = prosavetwentythreeday;
        }

        String prosavetwentyforeday = sp.getString("prosavetwentyforeday", "");//修改的24日
        if (prosavetwentyforeday.isEmpty() || prosavetwentyforeday.equals(tvnewlyday24)) {
            save24 = tvnewlyday24;
        } else {
            save24 = prosavetwentyforeday;
        }

        String prosavetwentyfiveday = sp.getString("prosavetwentyfiveday", "");//修改的25日
        if (prosavetwentyfiveday.isEmpty() || prosavetwentyfiveday.equals(tvnewlyday25)) {
            save25 = tvnewlyday25;
        } else {
            save25 = prosavetwentyfiveday;
        }

        String prosavetwentysixday = sp.getString("prosavetwentysixday", "");//修改的26日
        if (prosavetwentysixday.isEmpty() || prosavetwentysixday.equals(tvnewlyday26)) {
            save26 = tvnewlyday26;
        } else {
            save26 = prosavetwentysixday;
        }

        String prosavetwentysevenday = sp.getString("prosavetwentysevenday", "");//修改的27日
        if (prosavetwentysevenday.isEmpty() || prosavetwentysevenday.equals(tvnewlyday27)) {
            save27 = tvnewlyday27;
        } else {
            save27 = prosavetwentysevenday;
        }

        String prosavetwentyeightday = sp.getString("prosavetwentyeightday", "");//修改的28日
        if (prosavetwentyeightday.isEmpty() || prosavetwentyeightday.equals(tvnewlyday28)) {
            save28 = tvnewlyday28;
        } else {
            save28 = prosavetwentyeightday;
        }

        String prosavetwentynineday = sp.getString("prosavetwentynineday", "");//修改的29日
        if (prosavetwentynineday.isEmpty() || prosavetwentynineday.equals(tvnewlyday29)) {
            save29 = tvnewlyday29;
        } else {
            save29 = prosavetwentynineday;
        }

        String prosavethirtyday = sp.getString("prosavethirtyday", "");//修改的30日
        if (prosavethirtyday.isEmpty() || prosavethirtyday.equals(tvnewlyday30)) {
            save30 = tvnewlyday30;
        } else {
            save30 = prosavethirtyday;
        }

        String prosavethirtyoneday = sp.getString("prosavethirtyoneday", "");//修改的31日
        if (prosavethirtyoneday.isEmpty() || prosavethirtyoneday.equals(tvnewlyday31)) {
            save31 = tvnewlyday31;
        } else {
            save31 = prosavethirtyoneday;
        }
    }

    /*删除数据*/
    private void DeleteSp() {
        SharedPreferences.Editor editor = sp.edit();
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
        editor.remove("prosavetenday");
        editor.remove("prosaveelevenday");
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
        editor.remove("prosavestate");
        editor.remove("prosavecount");
        editor.remove("pronullothers");
        editor.remove("pronulltasknumber");
        editor.remove("pronulllastmon");
        editor.remove("pronullprocedure");
        editor.remove("pronullpartment");
        editor.remove("pronullstate");
        editor.remove("pronullmonth");
        editor.remove("pronullday1");
        editor.remove("pronullday2");
        editor.remove("pronullday3");
        editor.remove("pronullday4");
        editor.remove("pronullday5");
        editor.remove("pronullday6");
        editor.remove("pronullday7");
        editor.remove("pronullday8");
        editor.remove("pronullday9");
        editor.remove("pronullday10");
        editor.remove("pronullday11");
        editor.remove("pronullday12");
        editor.remove("pronullday13");
        editor.remove("pronullday14");
        editor.remove("pronullday15");
        editor.remove("pronullday16");
        editor.remove("pronullday17");
        editor.remove("pronullday18");
        editor.remove("pronullday19");
        editor.remove("pronullday20");
        editor.remove("pronullday21");
        editor.remove("pronullday22");
        editor.remove("pronullday23");
        editor.remove("pronullday24");
        editor.remove("pronullday25");
        editor.remove("pronullday26");
        editor.remove("pronullday27");
        editor.remove("pronullday28");
        editor.remove("pronullday29");
        editor.remove("pronullday30");
        editor.remove("pronullday31");
        editor.remove("pronullmemo");

        editor.remove("tvFTYDLDetailID");
        editor.remove("tvFTYDLDetailSalesID");
        editor.remove("tvFTYDLDetailPlanId");
        editor.remove("tvFTYDLDetailSn");
        editor.remove("tvFTYDLDetailContractno");
        editor.remove("tvFTYDLDetailInbill");
        editor.remove("tvFTYDLDetailArea");
        editor.remove("tvFTYDLDetailCompanytxt");
        editor.remove("tvFTYDLDetailPo");
        editor.remove("tvFTYDLDetailItem");
        editor.remove("tvFTYDLDetailOitem");
        editor.remove("tvFTYDLDetailMdl");
        editor.remove("tvFTYDLDetailCtmid");
        editor.remove("tvFTYDLDetailCtmtxt");
        editor.remove("tvFTYDLDetailCtmcompanytxt");
        editor.remove("tvFTYDLDetailPrdtyp");
        editor.remove("tvFTYDLDetailLcdat");
        editor.remove("tvFTYDLDetailLbdat");
        editor.remove("tvFTYDLDetailStyp");
        editor.remove("tvFTYDLDetailFsaler");
        editor.remove("tvFTYDLDetailPsaler");
        editor.remove("tvFTYDLDetailMemo");
        editor.remove("tvFTYDLDetailPqty");
        editor.remove("tvFTYDLDetailUnit");
        editor.remove("tvFTYDLDetailMegitem");
        editor.remove("tvFTYDLDetailTeamname");
        editor.remove("tvFTYDLDetailRecordat");
        editor.remove("tvFTYDLDetailRecordid");
        editor.remove("tvFTYDLDetailRecorder");
        editor.remove("tvFTYDLDetailFactory");
        editor.remove("tvFTYDLDetailProcedure");
        editor.remove("tvFTYDLDetailFactoryTeams");
        editor.remove("tvFTYDLDetailWorkers");
        editor.remove("tvFTYDLDetailFactcutqty");
        editor.remove("tvFTYDLDetailCutbdt");
        editor.remove("tvFTYDLDetailSewbdt");
        editor.remove("tvFTYDLDetailSewedt");
        editor.remove("tvFTYDLDetailSewDays");
        editor.remove("tvFTYDLDetailPerqty");
        editor.remove("tvFTYDLDetailCutamount");
        editor.remove("tvFTYDLDetailSewamount");
        editor.remove("tvFTYDLDetailPackamount");
        editor.remove("tvFTYDLDetailAmount");
        editor.remove("tvFTYDLDetailPerMachineQty");
        editor.remove("tvFTYDLDetailSumMachineQty");
        editor.remove("tvFTYDLDetailPrdstatus");
        editor.remove("tvFTYDLDetailPrdmaster");
        editor.remove("tvFTYDLDetailDocumentary");
        editor.remove("tvFTYDLDetailTaskqty");
        editor.remove("tvFTYDLDetailSumCompletedQty");
        editor.remove("tvFTYDLDetailLeftQty");
        editor.remove("tvFTYDLDetailLastMonQty");
        editor.remove("tvFTYDLDetailYear");
        editor.remove("tvFTYDLDetailMonth");
        editor.remove("tvFTYDLDetailDay1");
        editor.remove("tvFTYDLDetailDay2");
        editor.remove("tvFTYDLDetailDay3");
        editor.remove("tvFTYDLDetailDay4");
        editor.remove("tvFTYDLDetailDay5");
        editor.remove("tvFTYDLDetailDay6");
        editor.remove("tvFTYDLDetailDay7");
        editor.remove("tvFTYDLDetailDay8");
        editor.remove("tvFTYDLDetailDay9");
        editor.remove("tvFTYDLDetailDay10");
        editor.remove("tvFTYDLDetailDay11");
        editor.remove("tvFTYDLDetailDay12");
        editor.remove("tvFTYDLDetailDay13");
        editor.remove("tvFTYDLDetailDay14");
        editor.remove("tvFTYDLDetailDay15");
        editor.remove("tvFTYDLDetailDay16");
        editor.remove("tvFTYDLDetailDay17");
        editor.remove("tvFTYDLDetailDay18");
        editor.remove("tvFTYDLDetailDay19");
        editor.remove("tvFTYDLDetailDay20");
        editor.remove("tvFTYDLDetailDay21");
        editor.remove("tvFTYDLDetailDay22");
        editor.remove("tvFTYDLDetailDay23");
        editor.remove("tvFTYDLDetailDay24");
        editor.remove("tvFTYDLDetailDay25");
        editor.remove("tvFTYDLDetailDay26");
        editor.remove("tvFTYDLDetailDay27");
        editor.remove("tvFTYDLDetailDay28");
        editor.remove("tvFTYDLDetailDay29");
        editor.remove("tvFTYDLDetailDay30");
        editor.remove("tvFTYDLDetailDay31");
        editor.remove("tvFTYDLDetailPrdDocumentaryId");
        editor.remove("tvFTYDLDetailIsdiffc");
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("tvFTYDLDetailID");
        editor.remove("tvFTYDLDetailSalesID");
        editor.remove("tvFTYDLDetailItem");
        editor.remove("tvFTYDLDetailPlanId");
        editor.remove("tvFTYDLDetailSn");
        editor.remove("tvFTYDLDetailContractno");
        editor.remove("tvFTYDLDetailInbill");
        editor.remove("tvFTYDLDetailArea");
        editor.remove("tvFTYDLDetailCompanytxt");
        editor.remove("tvFTYDLDetailPo");
        editor.remove("tvFTYDLDetailOitem");
        editor.remove("tvFTYDLDetailMdl");
        editor.remove("tvFTYDLDetailCtmid");
        editor.remove("tvFTYDLDetailCtmtxt");
        editor.remove("tvFTYDLDetailCtmcompanytxt");
        editor.remove("tvFTYDLDetailPrdtyp");
        editor.remove("tvFTYDLDetailLcdat");
        editor.remove("tvFTYDLDetailLbdat");
        editor.remove("tvFTYDLDetailStyp");
        editor.remove("tvFTYDLDetailFsaler");
        editor.remove("tvFTYDLDetailPsaler");
        editor.remove("tvFTYDLDetailMemo");
        editor.remove("tvFTYDLDetailPqty");
        editor.remove("tvFTYDLDetailUnit");
        editor.remove("tvFTYDLDetailProdcol");
        editor.remove("tvFTYDLDetailMegitem");
        editor.remove("tvFTYDLDetailTeamname");
        editor.remove("tvFTYDLDetailRecordat");
        editor.remove("tvFTYDLDetailRecordid");
        editor.remove("tvFTYDLDetailRecorder");
        editor.remove("tvFTYDLDetailFactory");
        editor.remove("tvFTYDLDetailProcedure");
        editor.remove("tvFTYDLDetailFactoryTeams");
        editor.remove("tvFTYDLDetailWorkers");
        editor.remove("tvFTYDLDetailFactcutqty");
        editor.remove("tvFTYDLDetailCutbdt");
        editor.remove("tvFTYDLDetailSewbdt");
        editor.remove("tvFTYDLDetailSewedt");
        editor.remove("tvFTYDLDetailSewDays");
        editor.remove("tvFTYDLDetailPerqty");
        editor.remove("tvFTYDLDetailCutamount");
        editor.remove("tvFTYDLDetailSewamount");
        editor.remove("tvFTYDLDetailPackamount");
        editor.remove("tvFTYDLDetailAmount");
        editor.remove("tvFTYDLDetailPerMachineQty");
        editor.remove("tvFTYDLDetailSumMachineQty");
        editor.remove("tvFTYDLDetailPrdstatus");
        editor.remove("tvFTYDLDetailPrdmaster");
        editor.remove("tvFTYDLDetailDocumentary");
        editor.remove("tvFTYDLDetailTaskqty");
        editor.remove("tvFTYDLDetailSumCompletedQty");
        editor.remove("tvFTYDLDetailLeftQty");
        editor.remove("tvFTYDLDetailLastMonQty");
        editor.remove("tvFTYDLDetailYear");
        editor.remove("tvFTYDLDetailMonth");
        editor.remove("tvFTYDLDetailDay1");
        editor.remove("tvFTYDLDetailDay2");
        editor.remove("tvFTYDLDetailDay3");
        editor.remove("tvFTYDLDetailDay4");
        editor.remove("tvFTYDLDetailDay5");
        editor.remove("tvFTYDLDetailDay6");
        editor.remove("tvFTYDLDetailDay7");
        editor.remove("tvFTYDLDetailDay8");
        editor.remove("tvFTYDLDetailDay9");
        editor.remove("tvFTYDLDetailDay10");
        editor.remove("tvFTYDLDetailDay11");
        editor.remove("tvFTYDLDetailDay12");
        editor.remove("tvFTYDLDetailDay13");
        editor.remove("tvFTYDLDetailDay14");
        editor.remove("tvFTYDLDetailDay15");
        editor.remove("tvFTYDLDetailDay16");
        editor.remove("tvFTYDLDetailDay17");
        editor.remove("tvFTYDLDetailDay18");
        editor.remove("tvFTYDLDetailDay19");
        editor.remove("tvFTYDLDetailDay20");
        editor.remove("tvFTYDLDetailDay21");
        editor.remove("tvFTYDLDetailDay22");
        editor.remove("tvFTYDLDetailDay23");
        editor.remove("tvFTYDLDetailDay24");
        editor.remove("tvFTYDLDetailDay25");
        editor.remove("tvFTYDLDetailDay26");
        editor.remove("tvFTYDLDetailDay27");
        editor.remove("tvFTYDLDetailDay28");
        editor.remove("tvFTYDLDetailDay29");
        editor.remove("tvFTYDLDetailDay30");
        editor.remove("tvFTYDLDetailDay31");
        editor.remove("tvFTYDLDetailPrdDocumentaryId");
        editor.remove("tvFTYDLDetailIsdiffc");
        editor.remove("tvFTYDLDetailBoolIsdiffc");
        editor.remove("tvFTYDLDetailProdcol");

        editor.remove("prosavecount");
        editor.remove("pronullothers");
        editor.remove("pronulltasknumber");
        editor.remove("pronulllastmon");
        editor.remove("pronullprocedure");
        editor.remove("pronullpartment");
        editor.remove("pronullstate");
        editor.remove("pronullmonth");
        editor.remove("pronullday1");
        editor.remove("pronullday2");
        editor.remove("pronullday3");
        editor.remove("pronullday4");
        editor.remove("pronullday5");
        editor.remove("pronullday6");
        editor.remove("pronullday7");
        editor.remove("pronullday8");
        editor.remove("pronullday9");
        editor.remove("pronullday10");
        editor.remove("pronullday11");
        editor.remove("pronullday12");
        editor.remove("pronullday13");
        editor.remove("pronullday14");
        editor.remove("pronullday15");
        editor.remove("pronullday16");
        editor.remove("pronullday17");
        editor.remove("pronullday18");
        editor.remove("pronullday19");
        editor.remove("pronullday20");
        editor.remove("pronullday21");
        editor.remove("pronullday22");
        editor.remove("pronullday23");
        editor.remove("pronullday24");
        editor.remove("pronullday25");
        editor.remove("pronullday26");
        editor.remove("pronullday27");
        editor.remove("pronullday28");
        editor.remove("pronullday29");
        editor.remove("pronullday30");
        editor.remove("pronullday31");
        editor.remove("pronullmemo");
        editor.commit();
        super.onDestroy();
    }
}