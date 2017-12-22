package com.daoran.newfactory.onefactory.activity.work.ftydl;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ftydladapter.FTYDLNewlyVerticalAdatper;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLColSelectBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLFactoryDailyColBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDetailSaveBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLNewlybooleanBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLColCountBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLVerticalColBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLNewlyBuildColSearchBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLSearchBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PhoneSaveUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.utils.ProductionUtil;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 创建时间：2017/11/9
 * 编写人：lizhipeng
 * 功能描述：新建保存页面
 */

public class FTYDLNewlyComfigDetailActivity
        extends BaseFrangmentActivity implements View.OnClickListener {
    private static final String TAG = "FTYDLNewlyComfigDetail";
    private SharedPreferences sp;
    private SPUtils spUtils;
    private List<FTYDLNewlybooleanBean.DataBean> booleandatelist =
            new ArrayList<FTYDLNewlybooleanBean.DataBean>();
    private FTYDLNewlybooleanBean newlybooleanBean;
    private List<FTYDLDetailSaveBean> newlyComfigSaveBeen
            = new ArrayList<FTYDLDetailSaveBean>();

    private TextView tv_config_data, tv_config_ctmtxt, tv_config_subfactory,
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

    private ImageView ivProductionBack;//返回按钮
    private Button btnProSave;//保存按钮
    private ListView list_pro_config_vertical;
    private AlertDialog noticeDialog;
    //本类值判断变量
    private String smonth, somemonth, columntitle,
            proPrdstatusTitle, tvnewlyTotalCompletionn, someOther;
    //接收从上一个页面穿过来的数据变量
    private String nameid, isprodure, tvnewlyItem, tvnewlyctmtxt, tvnewlyDocumentary,
            tvnewlyFactory, tvnewlyDepartment, tvnewlyProcedure, tvnewlyOthers,
            tvnewSingularSystem, tvnewlyTaskqty, tvnewlyMdl, tvnewlyProcol,
            tvnewlyClippingNumber, tvnewlyCompletedLastMonth, tvnewlyPrdstatus, pagesize,
            ischeckedd, recordid, salesid, productionDocumentaryid, productionOthers;
    //内部数值变量赋值
    private String productionTaskNumber, totalBalanceamount, productionMonth,
            productionClippingNumber, productionCompletedLastMonth, productionTotalCompletion,
            proColumnTitle, tvColorTaskqtyss, productionBalanceAmount,
            productionOneDay, productionTwoDay, productionThreeDay, productionForeDay,
            productionFiveDay, productionSixDay, productionSevenDay, productionEightDay,
            productionNineDay, productionTenDay, productionElevenDay, productionTwelveDay,
            productionThirteenDay, productionFourteenDay, productionFifteenDay, productionSixteenDay,
            productionSeventeenDay, productionEighteenDay, productionNineteenDay,
            productionTwentyDay, productionTwentyOneDay, productionTwentyTwoDay,
            productionTwentyThreeDay, productionTwentyForeDay, productionTwentyFiveDay,
            productionTwentySixDay, productionTwentySevenDay, productionTwentyEightDay,
            productionTwentyNineDay, productionThirtyDay, productionThirtyOneDay,
            productionRemarks;
    private int year, month, datetime, hour, minute, second;
    int lastmonth, day1, day2, day3, day4, day5, day6, day7, day8, day9,
            day10, day11, day12, day13, day14, day15, day16, day17, day18,
            day19, day20, day21, day22, day23, day24, day25, day26, day27,
            day28, day29, day30, day31;
    private FTYDLNewlyVerticalAdatper verticalAdatper;
    private List<FTYDLColSelectBean.Data> procaldataList
            = new ArrayList<FTYDLColSelectBean.Data>();

    private List<FTYDLColCountBean.Data> procalbeanlist =
            new ArrayList<FTYDLColCountBean.Data>();

    private FTYDLFactoryDailyColBean newlyBuildDateBean;//初始化数据实体bean
    private List<FTYDLFactoryDailyColBean.DataBean> newdataBeans
            = new ArrayList<FTYDLFactoryDailyColBean.DataBean>();//初始化数据实体list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_config_vertical);
        setSpUtils();
        getView();
        initView();
        setListener();
        setOnclickEdit();
    }

    /*初始化控件*/
    private void getView() {
        tv_config_data = (TextView) findViewById(R.id.tv_config_data);
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

        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        btnProSave = (Button) findViewById(R.id.btnProSave);
    }

    /*赋值*/
    private void initView() {
        tv_config_recordat.setText(year + "/" + month + "/" + datetime);//制单时间
        tv_config_recorder.setText(nameid);//制单人
        tv_config_year.setText(year + "");//年
        et_config_month.setText(month + "");//月
        tv_config_data.setText(tvnewlyItem);//款号
        tv_config_ctmtxt.setText(tvnewlyctmtxt);//客户
        tv_config_documentary.setText(tvnewlyDocumentary);//部门组别
        tv_config_subfactory.setText(tvnewlyFactory);//工厂
        tv_config_department.setText(tvnewlyDepartment);//跟单
        tv_config_procedure.setText(tvnewlyProcedure);//工序
        et_config_others.setText(tvnewlyOthers);//组别人数
        tv_config_pqty.setText(tvnewSingularSystem);//制单数
        et_config_singularsystem.setText(tvnewlyTaskqty);//任务数
        tv_config_size.setText(tvnewlyMdl);//尺码
        et_config_color.setText(tvnewlyProcol);//花色
        tv_config_clippingnumber.setText(tvnewlyClippingNumber);//实裁数
        tv_config_totalcompletion.setText(tvnewlyCompletedLastMonth);//总完工数
        tv_config_state.setText(tvnewlyPrdstatus);//状态
        tv_config_cutdate.setText(year + "/" + month + "/" + datetime);//裁床时间
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
        }
        setDateNewly();
        setNewlyComfig();
    }

    /*接收上一个页面传过来的点击的数据*/
    private void setSpUtils() {
        sp = getSharedPreferences("my_sp", 0);
        Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
        t.setToNow(); // 取得系统时间。
        year = t.year;
        month = t.month;
        datetime = t.monthDay;
        hour = t.hour; // 0-23
        minute = t.minute;
        second = t.second;
        month = month + 1;
        nameid = sp.getString("usernamerecoder", "");//登录人
        isprodure = sp.getString("FTYDLNewlyIsProdure", "");//判断是否是裁床
        tvnewlyItem = sp.getString("tvFTYDLNewlyItem", "");//款号
        tvnewlyctmtxt = sp.getString("tvFTYDLNewlyCtmtxt", "");//客户
        tvnewlyDocumentary = sp.getString("tvFTYDLNewlyDocumentary", "");//跟单
        tvnewlyFactory = sp.getString("tvFTYDLNewlyFactory", "");//工厂
        tvnewlyDepartment = sp.getString("tvFTYDLNewlyDepartment",
                "");//部门/组别
        tvnewlyProcedure = sp.getString("tvFTYDLNewlyProcedure", "");//工序
        tvnewlyOthers = sp.getString("tvFTYDLNewlyWorkers", "");//组别人数
        tvnewSingularSystem = sp.getString("tvFTYDLNewlyPqty",
                "");//制单数
        tvnewlyTaskqty = sp.getString("tvFTYDLNewlyTaskqty", "");//任务数
        tvnewlyMdl = sp.getString("tvFTYDLNewlyMdl", "");//尺码
        tvnewlyProcol = sp.getString("tvFTYDLNewlyProcal", "");//花色
        tvnewlyClippingNumber = sp.getString("tvFTYDLNewlyFactcutqty",
                "");//实裁数
        tvnewlyCompletedLastMonth = sp.getString("tvFTYDLNewlySumCompletedQty",
                "");//总完工数
        tvnewlyPrdstatus = sp.getString("tvFTYDLNewlyPrdstatus", "");//状态
        pagesize = sp.getString("clumnsFTYDLpageSize", "");//每页数量
        ischeckedd = sp.getString("FTYDLCheckedd", "");//选择制单人是否为空
        recordid = sp.getString("username", "");//制单人id(登录名)
        salesid = sp.getString("tvFTYDLNewlyId", "");//款号行id
        productionDocumentaryid = sp.getString("tvFTYDLNewlyDocumentaryId", "");//跟单人id
    }

    //内部传值
    private void saveSpUtils() {
        sp = getSharedPreferences("my_sp", 0);
        //内部传值
        productionOthers = sp.getString("configOthers", "");//组别人数
        productionMonth = sp.getString("configMonth", "");//月
        productionTaskNumber = sp.getString("configTaskQty", "");//修改的任务数
        proColumnTitle = sp.getString("configdepartment", "");//部门
        proPrdstatusTitle = sp.getString("configPrdstatus", "");//状态//
        productionClippingNumber = sp.getString("configclipping", "");//实裁数
        productionCompletedLastMonth = sp.getString("configLastMonth", "");//上月完工
        productionTotalCompletion = sp.getString("configcompletion", "");//总完工数
        productionBalanceAmount = sp.getString("configBalanceAmount", "");//结余数量
        productionOneDay = sp.getString("configOneDay", "");//1
        productionTwoDay = sp.getString("configTwoDay", "");//2
        productionThreeDay = sp.getString("configThreeDay", "");//3
        productionForeDay = sp.getString("configForeDay", "");//4
        productionFiveDay = sp.getString("configFiveDay", "");//5
        productionSixDay = sp.getString("configSixDay", "");//6
        productionSevenDay = sp.getString("configSevenDay", "");//7
        productionEightDay = sp.getString("configEightDay", "");//8
        productionNineDay = sp.getString("configNineDay", "");//9
        productionTenDay = sp.getString("configTenDay", "");//10
        productionElevenDay = sp.getString("configElevenDay", "");//11
        productionTwelveDay = sp.getString("configTwelveDay", "");//12
        productionThirteenDay = sp.getString("configThirteenDay", "");//13
        productionFourteenDay = sp.getString("configFourteenDay", "");//14
        productionFifteenDay = sp.getString("configFifteenDay", "");//15
        productionSixteenDay = sp.getString("configSixteenDay", "");//16
        productionSeventeenDay = sp.getString("configSeventeenDay", "");//17
        productionEighteenDay = sp.getString("configEighteenDay", "");//18
        productionNineteenDay = sp.getString("configNineteenDay", "");//19
        productionTwentyDay = sp.getString("configTwentyDay", "");//20
        productionTwentyOneDay = sp.getString("configTwentyOneDay", "");//21
        productionTwentyTwoDay = sp.getString("configTwentyTwoDay", "");//22
        productionTwentyThreeDay = sp.getString("configTwentyThreeDay", "");//23
        productionTwentyForeDay = sp.getString("configTwentyForeDay", "");//24
        productionTwentyFiveDay = sp.getString("configTwentyFiveDay", "");//25
        productionTwentySixDay = sp.getString("configTwentySixDay", "");//26
        productionTwentySevenDay = sp.getString("configTwentySevenDay", "");//27
        productionTwentyEightDay = sp.getString("configTwentyEightDay", "");//28
        productionTwentyNineDay = sp.getString("configTwentyNineDay", "");//29
        productionThirtyDay = sp.getString("configThirtyDay", "");//30
        productionThirtyOneDay = sp.getString("configThirtyOneDay", "");//31
        productionRemarks = sp.getString("configRemarks", "");//备注
    }

    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        btnProSave.setOnClickListener(this);
        tv_config_department.setOnClickListener(this);
        tv_config_state.setOnClickListener(this);
    }

    /*填充颜色list*/
    private void setProcol() {
        try {
            for (int j = 0; j < newdataBeans.size(); j++) {
                FTYDLColCountBean.Data procalbean =
                        new FTYDLColCountBean.Data();
                procalbean.setProText(newdataBeans.get(j).getProdcol());
                procalbean.setBalanceAmount(newdataBeans.get(j).getTaskqty());
                procalbean.setProCount("");
                procalbean.setProNum("");
                procalbeanlist.add(procalbean);
            }
            verticalAdatper = new FTYDLNewlyVerticalAdatper(
                    FTYDLNewlyComfigDetailActivity.this,
                    newdataBeans, procalbeanlist);
            list_pro_config_vertical.setAdapter(verticalAdatper);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //监听edittext事件
    private void setOnclickEdit() {
        sp = getSharedPreferences("my_sp", 0);
        tv_config_cutdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FTYDLNewlyComfigDetailActivity.this,
                        null,
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
                                spUtils.put(getApplicationContext(),
                                        "proverPredocdttimesign", day);//预计产前报告时间
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                        , "清除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tv_config_cutdate.setText("");
                                spUtils.put(getApplicationContext(),
                                        "proverPredocdttimesign", "");//预计产前报告时间
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

        final int MIN_MARK_OTHER = 0;
        final int MAX_MARK_OTHER = 200;
        //组别人数监听
        et_config_others.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
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
                if (MIN_MARK_OTHER != -1 || 200 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 200) {
                        ToastUtils.ShowToastMessage("大小不能超过200",
                                getApplicationContext());
                        et_config_others.setText(String.valueOf(MAX_MARK_OTHER));
                        et_config_others.setSelection(et_config_others.length());
                    }
                    String proitem = et_config_others.getText().toString();
                    spUtils.put(getApplicationContext(), "configOthers", proitem);
                    return;
                }
            }
        });
        et_config_others.setSelection(et_config_others.getText().length());

        //任务数监听
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
                if (MIN_MARK_OTHER != -1 || 999999 != -1) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 999999) {
                        ToastUtils.ShowToastMessage("数值太大", getApplicationContext());
                        et_config_singularsystem.setText(tvnewlyTaskqty);
                    }
                    String prosingular = et_config_singularsystem.getText().toString();
                    spUtils.put(getApplicationContext(), "configTaskQty", prosingular);
                    et_config_singularsystem.setSelection(et_config_singularsystem.length());
                    return;
                }
            }
        });
        et_config_singularsystem.setSelection(et_config_singularsystem.getText().length());

        //上月完工数
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
                    if (proitem.equals("")) {
                        lastmonth = 0;
                        setCount(lastmonth);
                    } else {
                        lastmonth = Integer.parseInt(proitem);
                        setCount(lastmonth);
                    }
                    setCount(lastmonth);
                    spUtils.put(getApplicationContext(), "configTaskQty", proitem);
                    return;
                }
            }
        });
        et_config_completedlastmonth.setSelection(
                et_config_completedlastmonth.getText().length());

        //月份监听
        et_config_month.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        et_config_month.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String promonth = et_config_month.getText().toString();
                int markVal = 0;
                try {
                    markVal = Integer.parseInt(s.toString());
                } catch (NumberFormatException e) {
                    markVal = 0;
                }
                if (markVal > 12) {
                    et_config_month.setText("12");
                    et_config_month.setSelection(et_config_month.length());
                    spUtils.put(getApplicationContext(), "configMonth", promonth);
                }
                spUtils.put(getApplicationContext(), "configMonth", promonth);

            }
        });
        et_config_month.setSelection(et_config_month.getText().length());

        //1日
        et_config_oneday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_oneday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e(TAG, "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG, "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e(TAG, "afterTextChanged");
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
                            et_config_oneday.setText(String.valueOf(999999));
                            et_config_oneday.setSelection(et_config_oneday.length());
                        }
                        String proitem = et_config_oneday.getText().toString();
                        spUtils.put(getApplicationContext(), "configOneDay", proitem);
                        String prolastmonth = et_config_completedlastmonth.getText().toString();
                        if (prolastmonth.equals("")) {
                            lastmonth = 0;
                            setCount(lastmonth);
                        } else {
                            lastmonth = Integer.parseInt(prolastmonth);
                            setCount(lastmonth);
                        }
                        return;
                    }
                }
            }
        });
        //2日
        et_config_twoday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twoday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twoday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwoDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //3日
        et_config_threeday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_threeday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_threeday.getText().toString();
                spUtils.put(getApplicationContext(), "configThreeDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //4日
        et_config_foreday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_foreday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_foreday.getText().toString();
                spUtils.put(getApplicationContext(), "configForeDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //5日
        et_config_fiveday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_fiveday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_fiveday.getText().toString();
                spUtils.put(getApplicationContext(), "configFiveDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //6日
        et_config_sixday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_sixday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_sixday.getText().toString();
                spUtils.put(getApplicationContext(), "configSixDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //7日
        et_config_servenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_servenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_servenday.getText().toString();
                spUtils.put(getApplicationContext(), "configSevenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //8日
        et_config_eightday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_eightday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_eightday.getText().toString();
                spUtils.put(getApplicationContext(), "configEightDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //9日
        et_config_nineday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_nineday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_nineday.getText().toString();
                spUtils.put(getApplicationContext(), "configNineDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //10日
        et_config_tenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_tenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_tenday.getText().toString();
                spUtils.put(getApplicationContext(), "configTenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //11日
        et_config_elevenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_elevenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_elevenday.getText().toString();
                spUtils.put(getApplicationContext(), "configElevenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //12日
        et_config_twelveday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twelveday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twelveday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwelveDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //13日
        et_config_thirteenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_thirteenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_thirteenday.getText().toString();
                spUtils.put(getApplicationContext(), "configThirteenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //14日
        et_config_fourteenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_fourteenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_fourteenday.getText().toString();
                spUtils.put(getApplicationContext(), "configFourteenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //15日
        et_config_fifteenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_fifteenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_fifteenday.getText().toString();
                spUtils.put(getApplicationContext(), "configFifteenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //16日
        et_config_sixteenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_sixteenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_sixteenday.getText().toString();
                spUtils.put(getApplicationContext(), "configSixteenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //17日
        et_config_seventeenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_seventeenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_seventeenday.getText().toString();
                spUtils.put(getApplicationContext(), "configSeventeenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //18日
        et_config_eighteenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_eighteenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_eighteenday.getText().toString();
                spUtils.put(getApplicationContext(), "configEighteenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //19日
        et_config_nineteenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_nineteenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_nineteenday.getText().toString();
                spUtils.put(getApplicationContext(), "configNineteenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //20日
        et_config_twentyday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twentyday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twentyday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwentyDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //21日
        et_config_TwentyOneDay.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_TwentyOneDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_TwentyOneDay.getText().toString();
                spUtils.put(getApplicationContext(), "configTwentyOneDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //22日
        et_config_twentytwoday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twentytwoday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twentytwoday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwentyTwoDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //23日
        et_config_twentyThreeday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twentyThreeday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twentyThreeday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwentyThreeDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //24日
        et_config_twentyforeday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twentyforeday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twentyforeday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwentyForeDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //25日
        et_config_twentyfiveday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twentyfiveday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twentyfiveday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwentyFiveDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //26日
        et_config_twentysixday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twentysixday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twentysixday.getText().toString();
                spUtils.put(getApplicationContext(), "tvProTwentySixDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //27日
        et_config_twentysevenday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twentysevenday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twentysevenday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwentySevenDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //28日
        et_config_twentyeightday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twentyeightday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twentyeightday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwentyEightDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //29日
        et_config_twentynineday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_twentynineday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_twentynineday.getText().toString();
                spUtils.put(getApplicationContext(), "configTwentyNineDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //30日
        et_config_thirtyday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_thirtyday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_thirtyday.getText().toString();
                spUtils.put(getApplicationContext(), "configThirtyDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
        //31日
        et_config_thirtyoneday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_thirtyoneday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_thirtyoneday.getText().toString();
                spUtils.put(getApplicationContext(), "configThirtyOneDay", prooneday);
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                if (prolastmonth.equals("")) {
                    lastmonth = 0;
                    setCount(lastmonth);
                } else {
                    lastmonth = Integer.parseInt(prolastmonth);
                    setCount(lastmonth);
                }
            }
        });
    }

    /*计算总完工数*/
    private void setCount(int lastmont) {
        String dayone = et_config_oneday.getText().toString();
        if (dayone.equals("")) {
            day1 = 0;
        } else {
            day1 = Integer.parseInt(dayone);
        }
        String daytwo = et_config_twoday.getText().toString();
        if (daytwo.equals("")) {
            day2 = 0;
        } else {
            day2 = Integer.parseInt(daytwo);
        }
        String dayThree = et_config_threeday.getText().toString();
        if (dayThree.equals("")) {
            day3 = 0;
        } else {
            day3 = Integer.parseInt(dayThree);
        }
        String dayfore = et_config_foreday.getText().toString();
        if (dayfore.equals("")) {
            day4 = 0;
        } else {
            day4 = Integer.parseInt(dayfore);
        }
        String dayfive = et_config_fiveday.getText().toString();
        if (dayfive.equals("")) {
            day5 = 0;
        } else {
            day5 = Integer.parseInt(dayfive);
        }
        String daysix = et_config_sixday.getText().toString();
        if (daysix.equals("")) {
            day6 = 0;
        } else {
            day6 = Integer.parseInt(daysix);
        }
        String daySeven = et_config_servenday.getText().toString();
        if (daySeven.equals("")) {
            day7 = 0;
        } else {
            day7 = Integer.parseInt(daySeven);
        }
        String dayEight = et_config_eightday.getText().toString();
        if (dayEight.equals("")) {
            day8 = 0;
        } else {
            day8 = Integer.parseInt(dayEight);
        }
        String dayNine = et_config_nineday.getText().toString();
        if (dayNine.equals("")) {
            day9 = 0;
        } else {
            day9 = Integer.parseInt(dayNine);
        }
        String dayTen = et_config_tenday.getText().toString();
        if (dayTen.equals("")) {
            day10 = 0;
        } else {
            day10 = Integer.parseInt(dayTen);
        }
        String dayEleven = et_config_elevenday.getText().toString();
        if (dayEleven.equals("")) {
            day11 = 0;
        } else {
            day11 = Integer.parseInt(dayEleven);
        }
        String dayTwelve = et_config_twelveday.getText().toString();
        if (dayTwelve.equals("")) {
            day12 = 0;
        } else {
            day12 = Integer.parseInt(dayTwelve);
        }
        String dayThirteen = et_config_thirteenday.getText().toString();
        if (dayThirteen.equals("")) {
            day13 = 0;
        } else {
            day13 = Integer.parseInt(dayThirteen);
        }
        String dayFourteen = et_config_fourteenday.getText().toString();
        if (dayFourteen.equals("")) {
            day14 = 0;
        } else {
            day14 = Integer.parseInt(dayFourteen);
        }
        String dayFifteen = et_config_fifteenday.getText().toString();
        if (dayFifteen.equals("")) {
            day15 = 0;
        } else {
            day15 = Integer.parseInt(dayFifteen);
        }
        String daySixteen = et_config_sixteenday.getText().toString();
        if (daySixteen.equals("")) {
            day16 = 0;
        } else {
            day16 = Integer.parseInt(daySixteen);
        }
        String daySeventeen = et_config_seventeenday.getText().toString();
        if (daySeventeen.equals("")) {
            day17 = 0;
        } else {
            day17 = Integer.parseInt(daySeventeen);
        }
        String dayEighteen = et_config_eighteenday.getText().toString();
        if (dayEighteen.equals("")) {
            day18 = 0;
        } else {
            day18 = Integer.parseInt(dayEighteen);
        }
        String dayNineteen = et_config_nineteenday.getText().toString();
        if (dayNineteen.equals("")) {
            day19 = 0;
        } else {
            day19 = Integer.parseInt(dayNineteen);
        }
        String dayTwenty = et_config_twentyday.getText().toString();
        if (dayTwenty.equals("")) {
            day20 = 0;
        } else {
            day20 = Integer.parseInt(dayTwenty);
        }
        String dayTwentyOne = et_config_TwentyOneDay.getText().toString();
        if (dayTwentyOne.equals("")) {
            day21 = 0;
        } else {
            day21 = Integer.parseInt(dayTwentyOne);
        }
        String dayTwentyTwo = et_config_twentytwoday.getText().toString();
        if (dayTwentyTwo.equals("")) {
            day22 = 0;
        } else {
            day22 = Integer.parseInt(dayTwentyTwo);
        }
        String dayTwentyThree = et_config_twentyThreeday.getText().toString();
        if (dayTwentyThree.equals("")) {
            day23 = 0;
        } else {
            day23 = Integer.parseInt(dayTwentyThree);
        }
        String dayTwentyFore = et_config_twentyforeday.getText().toString();
        if (dayTwentyFore.equals("")) {
            day24 = 0;
        } else {
            day24 = Integer.parseInt(dayTwentyFore);
        }
        String dayTwentyFive = et_config_twentyfiveday.getText().toString();
        if (dayTwentyFive.equals("")) {
            day25 = 0;
        } else {
            day25 = Integer.parseInt(dayTwentyFive);
        }
        String dayTwentySix = et_config_twentysixday.getText().toString();
        if (dayTwentySix.equals("")) {
            day26 = 0;
        } else {
            day26 = Integer.parseInt(dayTwentySix);
        }
        String dayTwentySeven = et_config_twentysevenday.getText().toString();
        if (dayTwentySeven.equals("")) {
            day27 = 0;
        } else {
            day27 = Integer.parseInt(dayTwentySeven);
        }
        String dayTwentyEight = et_config_twentyeightday.getText().toString();
        if (dayTwentyEight.equals("")) {
            day28 = 0;
        } else {
            day28 = Integer.parseInt(dayTwentyEight);
        }
        String dayTwentyNine = et_config_twentynineday.getText().toString();
        if (dayTwentyNine.equals("")) {
            day29 = 0;
        } else {
            day29 = Integer.parseInt(dayTwentyNine);
        }
        String dayThirty = et_config_thirtyday.getText().toString();
        if (dayThirty.equals("")) {
            day30 = 0;
        } else {
            day30 = Integer.parseInt(dayThirty);
        }
        String dayThirtyOne = et_config_thirtyoneday.getText().toString();
        if (dayThirtyOne.equals("")) {
            day31 = 0;
        } else {
            day31 = Integer.parseInt(dayThirtyOne);
        }
        /**
         * 计算总完工数（每月数量相加）
         */
        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                + day29 + day30 + day31;
        String countmonth = String.valueOf(count);
        tv_config_totalcompletion.setText(countmonth);//总完工数
        productionTaskNumber = sp.getString("configTaskQty", "");//修改的任务数
        int tasknumber;
        if (productionTaskNumber.equals("") || productionTaskNumber == null) {
            tasknumber = 0;
        } else {
            tasknumber = Integer.parseInt(productionTaskNumber);
        }
        totalBalanceamount = String.valueOf(tasknumber - count);
        tv_config_balanceamount.setText(totalBalanceamount);//结余数量
        tv_config_clippingnumber.setText(countmonth);//实裁数
        spUtils.put(getApplicationContext(), "configBalanceAmount", totalBalanceamount);//结余数量
        spUtils.put(getApplicationContext(), "configcompletion", countmonth);//
        spUtils.put(getApplicationContext(), "procomfigcountmonth", countmonth);
    }

    private void setDateColor() {
        sp = getSharedPreferences("my_sp", 0);
        String tvnewlysalesid = sp.getString("tvFTYDLNewlyId", "");
        String strurl = HttpUrl.debugoneUrl + "FactoryPlan/FTYDLColSelect/" + tvnewlysalesid;
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.post()
                    .url(strurl)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.println(response);
                            Gson gson = new Gson();
                            String result = response;
                            Type listtype = new TypeToken<List<FTYDLColSelectBean.Data>>() {
                            }.getType();
                            procaldataList = gson.fromJson(result, listtype);
//                            procalBean = new Gson().fromJson(response, FTYDLColSelectBean.class);
//                            procaldataList = procalBean.getDatas();
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp,
                    FTYDLNewlyComfigDetailActivity.this);
        }
    }

    private void setCutDate() {
        if (NetWork.isNetWorkAvailable(this)) {
            String strcutdate = HttpUrl.debugoneUrl + "FactoryPlan/SearchDailyData/";
            sp = getSharedPreferences("my_sp", 0);
            String tvdatenewlyid = sp.getString("tvFTYDLNewlyId", "");
            String tvdatenewlySalid = sp.getString("tvFTYDLNewlyId", "");//排单id
            String tvdatenewlydate = sp.getString("tvFTYDLNewlyItem", "");//款号
            String tvdatenewlyear = year + "";//年
            String productionMonth = sp.getString("configMonth", "");//月
            if (productionMonth.equals("")) {
                somemonth = et_config_month.getText().toString();
            } else {
                somemonth = productionMonth;
            }
            String tvdatenewlyFactory = sp.getString(
                    "tvFTYDLNewlyFactory", "");//工厂

            String proColumnTitle = sp.getString("configdepartment", "");//部门
            String proadaptertitle = sp.getString("tvFTYDLNewlyDepartment", "");//款号选择传过来的部门组别
            String columntitle;//部门变量
            if (proColumnTitle.equals("")) {//如果没有修改部门组别，就把款号传过来的部门加进去
                columntitle = proadaptertitle;
            } else {
                columntitle = proColumnTitle;
            }

            String proProcedureadapterTitle = sp.getString("ConfigProcedure", "");//工序adapter中修改过的
            String procudureTitle;//工序变量
            //如果工序显示是空值或者为选择工序，则统一赋给变量为空值""，
            //如果不是的话就把修改的工序赋给变量
            if (tvnewlyProcedure.equals("选择工序") && proProcedureadapterTitle.equals("")) {
                procudureTitle = "";
            } else if (!proProcedureadapterTitle.equals("")) {
                procudureTitle = proProcedureadapterTitle;
            } else {
                procudureTitle = tvnewlyProcedure;
            }

            String tvdatenewlySize = sp.getString(
                    "tvFTYDLNewlyProcal", "");//花色
            String tvdateda = sp.getString("proverPredocdttimesign", "");//裁床日期
            String liststr = sp.getString("mylistStr", "");//花色集合
            String tvnewlyCtmtxt = sp.getString("tvFTYDLNewlyCtmtxt", "");//客户
            String tvnewSingularSystem = sp.getString("tvFTYDLNewlyPqty", "");//制单数
            String tvnewlyDocumentary = sp.getString("tvFTYDLNewlyDocumentary", "");//跟单
            String tvnewlyOthers = sp.getString("tvFTYDLNewlyWorkers", "");//组别人数
            String tvColorTaskqty = sp.getString("tvFTYDLNewlyTaskqty", "");//任务数
            String tvnewTaskNumber = sp.getString("tvFTYDLNewlyMdl", "");//尺码
            String tvnewlyClippingNumber = sp.getString("tvFTYDLNewlyFactcutqty", "");//实裁数
            String tvnewlyCompletedLastMonth = sp.getString("tvFTYDLNewlySumCompletedQty", "");//总完工数
            String tvnewlyTotalCompletion = sp.getString("tvFTYDLNewlyPrdstatus", "");//生产状态
            int salesidint = Integer.parseInt(tvdatenewlySalid);
            if (liststr.equals("") || liststr.isEmpty()) {
                Gson gson = new Gson();
                FTYDLDetailSaveBean saveBean = new FTYDLDetailSaveBean();
                saveBean.setID(salesidint);
                saveBean.setSalesid(salesidint);
                saveBean.setItem(tvdatenewlydate);
                saveBean.setCtmtxt(tvnewlyCtmtxt);
                saveBean.setYear(tvdatenewlyear);
                saveBean.setMonth(somemonth);
                saveBean.setSubfactory(tvdatenewlyFactory);
                saveBean.setSubfactoryTeams(columntitle);
                saveBean.setWorkingProcedure(procudureTitle);
                saveBean.setProdcol(tvdatenewlySize);
                saveBean.setPqty(tvnewSingularSystem);
                saveBean.setPrddocumentary(tvnewlyDocumentary);
                saveBean.setWorkers(tvnewlyOthers);
                saveBean.setTaskqty(tvColorTaskqty);
                saveBean.setMdl(tvnewTaskNumber);
                saveBean.setFactcutqty(tvnewlyClippingNumber);
                saveBean.setLastMonQty("");
                saveBean.setSumCompletedQty(tvnewlyCompletedLastMonth);
                saveBean.setLeftQty("");
                saveBean.setPrdstatus(tvnewlyTotalCompletion);
                String colcontent = gson.toJson(saveBean);
                OkHttpUtils
                        .postString()
                        .url(strcutdate)
                        .content(colcontent)
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
                            }
                        });
            } else {
                try {
                    Gson gson = new Gson();
                    List<String> list = PhoneSaveUtil.String2SceneList(liststr);
                    List<FTYDLColCountBean.Data> data =
                            new ArrayList<FTYDLColCountBean.Data>();
                    for (int i = 0; i < list.size(); i++) {
                        FTYDLVerticalColBean colBean = new FTYDLVerticalColBean();
                        colBean.setSalesid(Integer.parseInt(tvdatenewlySalid));
                        colBean.setItem(tvdatenewlydate);

                        String colcontent = gson.toJson(colBean);
                        OkHttpUtils
                                .postString()
                                .url(strcutdate)
                                .content(colcontent)
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
                                    }
                                });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp,
                    FTYDLNewlyComfigDetailActivity.this);
        }
    }

    /*查询需要分色的相同款号的单子*/
    public void setDateNewly() {
        String urlDaily = HttpUrl.debugoneUrl + "FactoryPlan/FactoryDailyAPP/";
        if (pagesize.equals("")) {
            pagesize = String.valueOf(15);
        }
        Gson gson = new Gson();
        final FTYDLNewlyBuildColSearchBean buildBean = new FTYDLNewlyBuildColSearchBean();
        FTYDLNewlyBuildColSearchBean.Conditions conditions = buildBean.new Conditions();
        conditions.setItem(tvnewlyItem);
        conditions.setWorkingProcedure(tvnewlyProcedure);
        conditions.setStyp("大货");
        buildBean.setConditions(conditions);
        buildBean.setPageNum(0);
        buildBean.setPageSize(Integer.parseInt(pagesize));
        final String bean = gson.toJson(buildBean);
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
                            try {
                                System.out.print(response);
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                newlyBuildDateBean = new Gson().fromJson(ression, FTYDLFactoryDailyColBean.class);
                                newdataBeans = newlyBuildDateBean.getData();
                                if (tvnewlyProcedure.equals("裁床")) {
                                    setProcol();
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLNewlyComfigDetailActivity.this);
        }
    }

    /*新建时点击款号后查询有关当前登录用户的数据*/
    private void setNewlyComfig() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        boolean stris = Boolean.parseBoolean(ischeckedd);
        Gson gson = new Gson();
        FTYDLSearchBean FTYDLSearchBean = new FTYDLSearchBean();
        FTYDLSearchBean.Conditions conditions = FTYDLSearchBean.new Conditions();
        conditions.setItem("");//款号
        conditions.setPrddocumentary(nameid);//制单人
        conditions.setSubfactory("");//工厂
        conditions.setWorkingProcedure("");//工序
        conditions.setPrddocumentaryisnull(stris);//是否为空
        FTYDLSearchBean.setConditions(conditions);//外部嵌套
        FTYDLSearchBean.setPageNum(0);//默认第几页
        FTYDLSearchBean.setPageSize(500);//默认每页多少条数据
        String gsonbeanStr = gson.toJson(FTYDLSearchBean);
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
                                newlybooleanBean = new Gson().fromJson(ression, FTYDLNewlybooleanBean.class);
                                booleandatelist = newlybooleanBean.getData();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请重新再试",
                    FTYDLNewlyComfigDetailActivity.this);
        }
    }

    /*保存上传*/
    private void setSave() {
        saveSpUtils();//获取内部变动的数值
        String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
        sp = getSharedPreferences("my_sp", 0);
        if (productionMonth.equals("")) {//月份判断数值
            smonth = et_config_month.getText().toString();
        } else {
            smonth = productionMonth;
        }

        if (proColumnTitle.equals("")) {//如果没有修改部门组别，就把款号传过来的部门加进去
            columntitle = tvnewlyDepartment;
        } else {
            columntitle = proColumnTitle;
        }

        if (productionOthers.equals("")) {//未修改组别人数
            someOther = tvnewlyOthers;
        } else {
            someOther = productionOthers;
        }

        if (productionTaskNumber.equals("")) {//如果没有修改任务数，就把款号传过来的任务数加进去
            tvColorTaskqtyss = tvnewlyTaskqty;
        } else {
            tvColorTaskqtyss = productionTaskNumber;
        }

        if (proPrdstatusTitle.equals("")) {//状态
            tvnewlyTotalCompletionn = tvnewlyPrdstatus;
        } else {
            tvnewlyTotalCompletionn = proPrdstatusTitle;
        }

        int listsize = booleandatelist.size();
        if (listsize == 0) {
            listsize = 1;
        } else {
            listsize = booleandatelist.size();
        }
        String[] arrsitem = tvnewlyItem.split(",");//修改的款号数组
        String[] arrsdatemonth = new String[listsize];
        String[] arrsdatepredure = new String[listsize];//符合条件的工序数组
        String[] arrsdateSubfactoryTeams = new String[listsize];//部门组别
        Gson gson = new Gson();
        if (tvColorTaskqtyss.equals("") || tvColorTaskqtyss == null) {
            ToastUtils.ShowToastMessage("任务数不能为空",
                    FTYDLNewlyComfigDetailActivity.this);
        } else {
            ResponseDialog.showLoading(this, "正在保存");
            if (booleandatelist.size() == 0) {
                /*判断工序是否是裁床*/
                if (tvnewlyProcedure.equals("裁床")) {
                    try {
                        /*如果花色为多条，则循环把这条数据分成多条数据*/
                        for (int j = 0; j < newdataBeans.size(); j++) {
                            FTYDLDetailSaveBean consaveBean =
                                    new FTYDLDetailSaveBean();
                            consaveBean.setID(0);//id
                            consaveBean.setRecordid(recordid);//制单人id
                            consaveBean.setSalesid(Integer.parseInt(salesid));//排单计划id
                            consaveBean.setProdcol(newdataBeans.get(j).getProdcol());//花色
                            consaveBean.setItem(tvnewlyItem);//款号
                            consaveBean.setPrddocumentary(tvnewlyDocumentary);//跟单
                            consaveBean.setPrddocumentaryid(productionDocumentaryid);//跟单id
                            consaveBean.setSubfactory(tvnewlyFactory);//工厂
                            consaveBean.setSubfactoryTeams(columntitle);//部门组别
                            consaveBean.setWorkingProcedure(tvnewlyProcedure);//工序
                            consaveBean.setWorkers(someOther);//组别人数
                            consaveBean.setPqty(tvnewSingularSystem);//制单数
                            consaveBean.setTaskqty(newdataBeans.get(j).getTaskqty());//任务数
                            consaveBean.setMdl(tvnewlyMdl);//尺码
                            consaveBean.setFactcutqty(procalbeanlist.get(j).getProClippingnumber());//实裁数/
                            consaveBean.setSumCompletedQty(procalbeanlist.get(j)
                                    .getProTotalCompletion());//总完工数
                            consaveBean.setLastMonQty(productionCompletedLastMonth);//上月完工数
                            int balan = Integer.parseInt(procalbeanlist.get(j).getBalanceAmount());
                            int cli = Integer.parseInt(procalbeanlist.get(j).getProClippingnumber());
                            consaveBean.setLeftQty(String.valueOf(balan - cli));//结余数量/
                            consaveBean.setPrdstatus(tvnewlyTotalCompletionn);//状态
                            consaveBean.setYear(String.valueOf(year));//年
                            if (datetime == 1) {//如果当前日期是某一天，则将产量填入当天的产量中
                                consaveBean.setDay1(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 2) {
                                consaveBean.setDay2(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 3) {
                                consaveBean.setDay3(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 4) {
                                consaveBean.setDay4(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 5) {
                                consaveBean.setDay5(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 6) {
                                consaveBean.setDay6(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 7) {
                                consaveBean.setDay7(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 8) {
                                consaveBean.setDay8(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 9) {
                                consaveBean.setDay9(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 10) {
                                consaveBean.setDay10(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 11) {
                                consaveBean.setDay11(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 12) {
                                consaveBean.setDay12(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 13) {
                                consaveBean.setDay13(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 14) {
                                consaveBean.setDay14(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 15) {
                                consaveBean.setDay15(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 16) {
                                consaveBean.setDay16(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 17) {
                                consaveBean.setDay17(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 18) {
                                consaveBean.setDay18(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 19) {
                                consaveBean.setDay19(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 20) {
                                consaveBean.setDay20(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 21) {
                                consaveBean.setDay21(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 22) {
                                consaveBean.setDay22(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 23) {
                                consaveBean.setDay23(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 24) {
                                consaveBean.setDay24(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 25) {
                                consaveBean.setDay25(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 26) {
                                consaveBean.setDay26(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 27) {
                                consaveBean.setDay27(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 28) {
                                consaveBean.setDay28(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 29) {
                                consaveBean.setDay29(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 30) {
                                consaveBean.setDay30(procalbeanlist.get(j).getProNum());
                            } else if (datetime == 31) {
                                consaveBean.setDay31(procalbeanlist.get(j).getProNum());
                            }
                            if (productionMonth.equals("")) {
                                consaveBean.setMonth(smonth);
                            } else {
                                consaveBean.setMonth(productionMonth);
                            }
                            consaveBean.setMemo(productionRemarks);
                            consaveBean.setRecorder(nameid);
                            consaveBean.setRecordat(year + "/" + month + "/" + datetime
                                    + "/" + hour + "/" + minute + "/" + second);
                            newlyComfigSaveBeen.add(consaveBean);
                        }
                        String detailb = gson.toJson(newlyComfigSaveBeen);
                        String dateee = detailb.replace("\"\"", "null");
                        if (NetWork.isNetWorkAvailable(this)) {
                            OkHttpUtils.postString().
                                    url(saveurl)
                                    .content(dateee)
                                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            e.printStackTrace();
                                            ResponseDialog.closeLoading();
                                        }

                                        @Override
                                        public void onResponse(final String response, int id) {
                                            String ression = StringUtil.sideTrim(response, "\"");
                                            int resindex = Integer.parseInt(ression);
                                            setNewlyComfig();
                                            if (resindex > 4) {
                                                FTYDLSearchActivity.FTYDLSearchinstance.finish();
                                                ToastUtils.ShowToastMessage("保存成功",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                                saveDestroy();//保存成功后删掉保存的数据
                                                startActivity(new Intent(FTYDLNewlyComfigDetailActivity.this,
                                                        FTYDLSearchActivity.class));
                                                procalbeanlist.clear();//清空集合
                                                finish();
                                            } else if (resindex == 3) {
                                                ToastUtils.ShowToastMessage("保存失败",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                            } else if (resindex == 4) {
                                                ToastUtils.ShowToastMessage("数据错误，请重试",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                            } else if (resindex == 2) {
                                                ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                            } else {
                                                ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                            }
                                            ResponseDialog.closeLoading();
                                        }
                                    });
                        } else {
                            ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLNewlyComfigDetailActivity.this);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        FTYDLDetailSaveBean consaveBean =
                                new FTYDLDetailSaveBean();
                        consaveBean.setID(0);//id
                        consaveBean.setSalesid(Integer.parseInt(salesid));//排单id
                        consaveBean.setRecordid(recordid);//制单人id
                        consaveBean.setProdcol(tvnewlyProcol);//花色
                        consaveBean.setItem(tvnewlyItem);//款号
                        consaveBean.setPrddocumentary(tvnewlyDocumentary);//跟单
                        consaveBean.setPrddocumentaryid(productionDocumentaryid);//跟单id
                        consaveBean.setSubfactory(tvnewlyFactory);//工厂
                        consaveBean.setSubfactoryTeams(columntitle);//部门组别
                        consaveBean.setWorkingProcedure(tvnewlyProcedure);//工序
                        consaveBean.setWorkers(someOther);//组别人数
                        consaveBean.setPqty(tvnewSingularSystem);//制单数
                        consaveBean.setTaskqty(tvColorTaskqtyss);//任务数
                        consaveBean.setMdl(tvnewlyMdl);//尺码
                        consaveBean.setFactcutqty(productionTaskNumber);//实裁数
                        consaveBean.setSumCompletedQty(productionTotalCompletion);//总完工数
                        consaveBean.setLastMonQty(productionCompletedLastMonth);//上月完工数
                        consaveBean.setLeftQty(productionBalanceAmount);//结余数量
                        consaveBean.setPrdstatus(tvnewlyTotalCompletionn);//状态
                        consaveBean.setYear(String.valueOf(year));//年
                        if (productionMonth.equals("")) {//月
                            consaveBean.setMonth(smonth);
                        } else {
                            consaveBean.setMonth(productionMonth);
                        }
                        consaveBean.setDay1(productionOneDay);
                        consaveBean.setDay2(productionTwoDay);
                        consaveBean.setDay3(productionThreeDay);
                        consaveBean.setDay4(productionForeDay);
                        consaveBean.setDay5(productionFiveDay);
                        consaveBean.setDay6(productionSixDay);
                        consaveBean.setDay7(productionSevenDay);
                        consaveBean.setDay8(productionEightDay);
                        consaveBean.setDay9(productionNineDay);
                        consaveBean.setDay10(productionTenDay);
                        consaveBean.setDay11(productionElevenDay);
                        consaveBean.setDay12(productionTwelveDay);
                        consaveBean.setDay13(productionThirteenDay);
                        consaveBean.setDay14(productionFourteenDay);
                        consaveBean.setDay15(productionFifteenDay);
                        consaveBean.setDay16(productionSixteenDay);
                        consaveBean.setDay17(productionSeventeenDay);
                        consaveBean.setDay18(productionEighteenDay);
                        consaveBean.setDay19(productionNineteenDay);
                        consaveBean.setDay20(productionTwentyDay);
                        consaveBean.setDay21(productionTwentyOneDay);
                        consaveBean.setDay22(productionTwentyTwoDay);
                        consaveBean.setDay23(productionTwentyThreeDay);
                        consaveBean.setDay24(productionTwentyForeDay);
                        consaveBean.setDay25(productionTwentyFiveDay);
                        consaveBean.setDay26(productionTwentySixDay);
                        consaveBean.setDay27(productionTwentySevenDay);
                        consaveBean.setDay28(productionTwentyEightDay);
                        consaveBean.setDay29(productionTwentyNineDay);
                        consaveBean.setDay30(productionThirtyDay);
                        consaveBean.setDay31(productionThirtyOneDay);
                        consaveBean.setMemo(productionRemarks);//备注
                        consaveBean.setRecorder(nameid);//制单人
                        consaveBean.setRecordat(year + "/" + month + "/" + datetime
                                + "/" + hour + "/" + minute + "/" + second);//制单时间
                        newlyComfigSaveBeen.add(consaveBean);
                        String detailb = gson.toJson(newlyComfigSaveBeen);
                        String dateee = detailb.replace("\"\"", "null");
                        if (newlyComfigSaveBeen.equals("")) {
                            ToastUtils.ShowToastMessage("没有数据可以保存", FTYDLNewlyComfigDetailActivity.this);
                            ResponseDialog.closeLoading();
                        } else {
                            if (NetWork.isNetWorkAvailable(this)) {
                                OkHttpUtils.postString().
                                        url(saveurl)
                                        .content(dateee)
                                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                e.printStackTrace();
                                                ResponseDialog.closeLoading();
                                            }

                                            @Override
                                            public void onResponse(final String response, int id) {
                                                String ression = StringUtil.sideTrim(response, "\"");
                                                int resindex = Integer.parseInt(ression);
                                                setNewlyComfig();
                                                if (resindex > 4) {
                                                    FTYDLSearchActivity.FTYDLSearchinstance.finish();
                                                    ToastUtils.ShowToastMessage("保存成功",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                    saveDestroy();
                                                    startActivity(new Intent(FTYDLNewlyComfigDetailActivity.this,
                                                            FTYDLSearchActivity.class));
                                                    procalbeanlist.clear();
                                                    finish();
                                                } else if (resindex == 3) {
                                                    ToastUtils.ShowToastMessage("保存失败",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                } else if (resindex == 4) {
                                                    ToastUtils.ShowToastMessage("数据错误，请重试",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                } else if (resindex == 2) {
                                                    ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                } else {
                                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                }
                                                ResponseDialog.closeLoading();
                                            }
                                        });
                            } else {
                                ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLNewlyComfigDetailActivity.this);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                for (int i = 0; i < booleandatelist.size(); i++) {
                    if (listsize != 0) {
                        if (booleandatelist.get(i).getItem() != null) {
                            String woritem = booleandatelist.get(i).getItem();
                            String[] workitempro = woritem.split(",");
                            boolean probool = containsAll(arrsitem, workitempro);
                            if (probool == true) {//得到一致的数据
                                arrsdatepredure[i] = booleandatelist.get(i).getWorkingProcedure();
                                arrsdatemonth[i] = booleandatelist.get(i).getMonth();
                                if (booleandatelist.get(i).getSubfactoryTeams() == null) {
                                    arrsdateSubfactoryTeams[i] = "";
                                } else {
                                    arrsdateSubfactoryTeams[i] = booleandatelist.get(i).getSubfactoryTeams();
                                }
                            } else {
                                arrsdatepredure[i] = "";
                                arrsdatemonth[i] = "";
                                arrsdateSubfactoryTeams[i] = "";
                            }
                        }
                    } else {
                        arrsdatepredure[i] = "";
                        arrsdatemonth[i] = "";
                        arrsdateSubfactoryTeams[i] = "";
                    }
                }
                StringBuffer sb = new StringBuffer();//循环去掉空的数据
                for (int i = 0; i < arrsdatepredure.length; i++) {
                    if ("".equals(arrsdatepredure[i])) {
                        continue;
                    }
                    sb.append(arrsdatepredure[i]);
                    if (i != arrsdatepredure.length - 1) {
                        sb.append(";");
                    }
                }

                arrsdatepredure = sb.toString().split(";");
                for (int i = 0; i < arrsdatepredure.length; i++) {
                    System.out.print(arrsdatepredure[i] + "");
                }
                System.out.print(arrsdatepredure + "");

                StringBuffer sb2 = new StringBuffer();
                for (int i = 0; i < arrsdatemonth.length; i++) {
                    if ("".equals(arrsdatemonth[i])) {
                        continue;
                    }
                    sb2.append(arrsdatemonth[i]);
                    if (i != arrsdatemonth.length - 1) {
                        sb2.append(";");
                    }
                }
                arrsdatemonth = sb2.toString().split(";");
                for (int i = 0; i < arrsdatemonth.length; i++) {
                    System.out.print(arrsdatemonth[i] + "");
                }
                System.out.print(arrsdatemonth + "");

                StringBuffer sb3 = new StringBuffer();
                for (int i = 0; i < arrsdateSubfactoryTeams.length; i++) {
                    if ("".equals(arrsdateSubfactoryTeams[i])) {
                        continue;
                    }
                    sb3.append(arrsdateSubfactoryTeams[i]);
                    if (i != arrsdateSubfactoryTeams.length - 1) {
                        sb3.append(";");
                    }
                }
                arrsdateSubfactoryTeams = sb3.toString().split(";");
                for (int i = 0; i < arrsdateSubfactoryTeams.length; i++) {
                    System.out.print(arrsdateSubfactoryTeams[i] + "");
                }
                System.out.print(arrsdateSubfactoryTeams + "");
                if (tvnewlyProcedure.equals("裁床")) {
                    try {
                        for (int j = 0; j < newdataBeans.size(); j++) {
                            FTYDLDetailSaveBean consaveBean =
                                    new FTYDLDetailSaveBean();
                            consaveBean.setID(0);//id
                            consaveBean.setRecordid(recordid);//制单人id
                            consaveBean.setSalesid(Integer.parseInt(salesid));//排单id
                            consaveBean.setProdcol(newdataBeans.get(j).getProdcol());//花色
                            consaveBean.setItem(tvnewlyItem);//款号
                            consaveBean.setPrddocumentary(tvnewlyDocumentary);//跟单
                            consaveBean.setPrddocumentaryid(productionDocumentaryid);//跟单id
                            consaveBean.setSubfactory(tvnewlyFactory);//工厂
                            consaveBean.setSubfactoryTeams(columntitle);//部门组别
                            consaveBean.setWorkingProcedure(tvnewlyProcedure);//工序
                            consaveBean.setWorkers(someOther);//组别人数
                            consaveBean.setPqty(tvnewSingularSystem);//制单数
                            consaveBean.setTaskqty(newdataBeans.get(j).getTaskqty());//任务数
                            consaveBean.setMdl(tvnewlyMdl);//尺码
                            consaveBean.setFactcutqty(procalbeanlist.get(j).getProClippingnumber());//实裁数/
                            consaveBean.setSumCompletedQty(procalbeanlist.get(j).getProTotalCompletion());//总完工数/
                            consaveBean.setLastMonQty(productionCompletedLastMonth);//上月完工数
                            int balan = Integer.parseInt(procalbeanlist.get(j).getBalanceAmount());
                            int cli;
                            if (procalbeanlist.get(j).getProClippingnumber() == null) {
                                cli = 0;
                            } else {
                                cli = Integer.parseInt(procalbeanlist.get(j).getProClippingnumber());
                            }
                            consaveBean.setLeftQty(String.valueOf(balan - cli));//结余数量/
                            consaveBean.setPrdstatus(tvnewlyTotalCompletionn);//状态
                            consaveBean.setYear(String.valueOf(year));//年
                            if (productionMonth.equals("")) {//月
                                consaveBean.setMonth(smonth);
                            } else {
                                consaveBean.setMonth(productionMonth);
                            }
                            consaveBean.setMemo(productionRemarks);//备注
                            consaveBean.setRecorder(nameid);//制单人
                            consaveBean.setRecordat(year + "/" + month + "/" + datetime
                                    + "/" + hour + "/" + minute + "/" + second);//制单时间
                            newlyComfigSaveBeen.add(consaveBean);
                        }
                        String detailb = gson.toJson(newlyComfigSaveBeen);
                        String dateee = detailb.replace("\"\"", "null");
                        if (NetWork.isNetWorkAvailable(this)) {
                            OkHttpUtils.postString().
                                    url(saveurl)
                                    .content(dateee)
                                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            e.printStackTrace();
                                            ResponseDialog.closeLoading();
                                        }

                                        @Override
                                        public void onResponse(final String response, int id) {
                                            String ression = StringUtil.sideTrim(response, "\"");
                                            int resindex = Integer.parseInt(ression);
                                            setNewlyComfig();
                                            if (resindex > 4) {
                                                FTYDLSearchActivity.FTYDLSearchinstance.finish();
                                                ToastUtils.ShowToastMessage("保存成功",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                                saveDestroy();
                                                startActivity(new Intent(FTYDLNewlyComfigDetailActivity.this,
                                                        FTYDLSearchActivity.class));
                                                procalbeanlist.clear();
                                                finish();
                                            } else if (resindex == 3) {
                                                ToastUtils.ShowToastMessage("保存失败",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                            } else if (resindex == 4) {
                                                ToastUtils.ShowToastMessage("数据错误，请重试",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                            } else if (resindex == 2) {
                                                ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                            } else {
                                                ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                        FTYDLNewlyComfigDetailActivity.this);
                                            }
                                            ResponseDialog.closeLoading();
                                        }
                                    });
                        } else {
                            ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLNewlyComfigDetailActivity.this);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        FTYDLDetailSaveBean consaveBean =
                                new FTYDLDetailSaveBean();
                        consaveBean.setID(0);//id
                        consaveBean.setSalesid(Integer.parseInt(salesid));//排单id
                        consaveBean.setRecordid(recordid);//制单人id
                        consaveBean.setProdcol(tvnewlyProcol);//花色
                        consaveBean.setItem(tvnewlyItem);//款号
                        consaveBean.setPrddocumentary(tvnewlyDocumentary);//跟单
                        consaveBean.setPrddocumentaryid(productionDocumentaryid);//跟单id
                        consaveBean.setSubfactory(tvnewlyFactory);//工厂
                        consaveBean.setSubfactoryTeams(columntitle);//部门组别
                        consaveBean.setWorkingProcedure(tvnewlyProcedure);//工序
                        consaveBean.setWorkers(someOther);//组别人数
                        consaveBean.setPqty(tvnewSingularSystem);//制单数
                        consaveBean.setTaskqty(tvColorTaskqtyss);//任务数
                        consaveBean.setMdl(tvnewlyMdl);//尺码
                        consaveBean.setFactcutqty(productionTaskNumber);//实裁数
                        consaveBean.setSumCompletedQty(productionTotalCompletion);//总完工数
                        consaveBean.setLastMonQty(productionCompletedLastMonth);//上月完工数
                        consaveBean.setLeftQty(productionBalanceAmount);//结余数量
                        consaveBean.setPrdstatus(tvnewlyTotalCompletionn);//状态
                        consaveBean.setYear(String.valueOf(year));//年
                        if (productionMonth.equals("")) {//月
                            consaveBean.setMonth(smonth);
                        } else {
                            consaveBean.setMonth(productionMonth);
                        }
                        consaveBean.setDay1(productionOneDay);
                        consaveBean.setDay2(productionTwoDay);
                        consaveBean.setDay3(productionThreeDay);
                        consaveBean.setDay4(productionForeDay);
                        consaveBean.setDay5(productionFiveDay);
                        consaveBean.setDay6(productionSixDay);
                        consaveBean.setDay7(productionSevenDay);
                        consaveBean.setDay8(productionEightDay);
                        consaveBean.setDay9(productionNineDay);
                        consaveBean.setDay10(productionTenDay);
                        consaveBean.setDay11(productionElevenDay);
                        consaveBean.setDay12(productionTwelveDay);
                        consaveBean.setDay13(productionThirteenDay);
                        consaveBean.setDay14(productionFourteenDay);
                        consaveBean.setDay15(productionFifteenDay);
                        consaveBean.setDay16(productionSixteenDay);
                        consaveBean.setDay17(productionSeventeenDay);
                        consaveBean.setDay18(productionEighteenDay);
                        consaveBean.setDay19(productionNineteenDay);
                        consaveBean.setDay20(productionTwentyDay);
                        consaveBean.setDay21(productionTwentyOneDay);
                        consaveBean.setDay22(productionTwentyTwoDay);
                        consaveBean.setDay23(productionTwentyThreeDay);
                        consaveBean.setDay24(productionTwentyForeDay);
                        consaveBean.setDay25(productionTwentyFiveDay);
                        consaveBean.setDay26(productionTwentySixDay);
                        consaveBean.setDay27(productionTwentySevenDay);
                        consaveBean.setDay28(productionTwentyEightDay);
                        consaveBean.setDay29(productionTwentyNineDay);
                        consaveBean.setDay30(productionThirtyDay);
                        consaveBean.setDay31(productionThirtyOneDay);
                        consaveBean.setMemo(productionRemarks);//备注
                        consaveBean.setRecorder(nameid);//制单人
                        consaveBean.setRecordat(year + "/" + month + "/" + datetime
                                + "/" + hour + "/" + minute + "/" + second);//制单时间
                        newlyComfigSaveBeen.add(consaveBean);
                        String detailb = gson.toJson(newlyComfigSaveBeen);
                        String dateee = detailb.replace("\"\"", "null");
                        if (newlyComfigSaveBeen.equals("")) {
                            ToastUtils.ShowToastMessage("没有数据可以保存", FTYDLNewlyComfigDetailActivity.this);
                            ResponseDialog.closeLoading();
                        } else {
                            if (NetWork.isNetWorkAvailable(this)) {
                                OkHttpUtils.postString().
                                        url(saveurl)
                                        .content(dateee)
                                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                e.printStackTrace();
                                                ResponseDialog.closeLoading();
                                            }

                                            @Override
                                            public void onResponse(final String response, int id) {
                                                String ression = StringUtil.sideTrim(response, "\"");
                                                int resindex = Integer.parseInt(ression);
                                                setNewlyComfig();
                                                if (resindex > 4) {
                                                    FTYDLSearchActivity.FTYDLSearchinstance.finish();
                                                    ToastUtils.ShowToastMessage("保存成功",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                    saveDestroy();
                                                    startActivity(new Intent(FTYDLNewlyComfigDetailActivity.this,
                                                            FTYDLSearchActivity.class));
                                                    procalbeanlist.clear();
                                                    finish();
                                                } else if (resindex == 3) {
                                                    ToastUtils.ShowToastMessage("保存失败",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                } else if (resindex == 4) {
                                                    ToastUtils.ShowToastMessage("数据错误，请重试",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                } else if (resindex == 2) {
                                                    ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                } else {
                                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                            FTYDLNewlyComfigDetailActivity.this);
                                                }
                                                ResponseDialog.closeLoading();
                                            }
                                        });
                            } else {
                                ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLNewlyComfigDetailActivity.this);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回事件
            case R.id.ivProductionBack:
                sethideSoft(v);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("保存提示");
                builder.setMessage("退出是否保存");
                builder.setPositiveButton("保存后退出"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setSave();
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
                break;
            //部门组别
            case R.id.tv_config_department:
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_column, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        spUtils.put(getApplicationContext(), "configdepartment", title);
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
                break;
            //状态
            case R.id.tv_config_state:
                PopupMenu popupMenustate = new PopupMenu(getApplicationContext(), v);
                popupMenustate.getMenuInflater().inflate(R.menu.menu_pro_prdstatus, popupMenustate.getMenu());
                // menu的item点击事件
                popupMenustate.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        spUtils.put(getApplicationContext(), "configPrdstatus", title);
                        tv_config_state.setText(title);
                        return false;
                    }
                });
                // PopupMenu关闭事件
                popupMenustate.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                    }
                });
                popupMenustate.show();
                break;
            //保存
            case R.id.btnProSave:
                System.out.println(procalbeanlist);
                setSave();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("保存提示");
            builder.setMessage("退出是否保存");
            builder.setPositiveButton("保存后退出"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setSave();
                            dialog.dismiss();
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
        return false;
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

    /* 判断array1是否包含所有的 array2*/
    private static boolean containsAll(String[] array1, String[] array2) {
        for (String str : array2) {
            if (!ArrayUtils.contains(array1, str)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*删除存储中的字段*/
    private void saveDestroy() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("mylistStr");//保存集合
        editor.remove("tvFTYDLNewlyProcedure");//工序
        editor.remove("configdepartment");//部门
        editor.remove("tvFTYDLNewlyDepartment");//部门
        editor.remove("configMonth");//月份
        editor.remove("ConfigProcedure");//修改的工序
        editor.remove("configOthers");//组别人数
        editor.remove("configOneDay");//1
        editor.remove("configTwoDay");//2
        editor.remove("configThreeDay");//3
        editor.remove("configForeDay");//4
        editor.remove("configFiveDay");//5
        editor.remove("configSixDay");//6
        editor.remove("configSevenDay");//7
        editor.remove("configEightDay");//8
        editor.remove("configNineDay");//9
        editor.remove("configTenDay");//10
        editor.remove("configElevenDay");//11
        editor.remove("configTwelveDay");//12
        editor.remove("configThirteenDay");//13
        editor.remove("configFourteenDay");//14
        editor.remove("configFifteenDay");//15
        editor.remove("configSixteenDay");//16
        editor.remove("configSeventeenDay");//17
        editor.remove("configEighteenDay");//18
        editor.remove("configNineteenDay");//19
        editor.remove("configTwentyDay");//20
        editor.remove("configTwentyOneDay");//21
        editor.remove("configTwentyTwoDay");//22
        editor.remove("configTwentyThreeDay");//23
        editor.remove("configTwentyForeDay");//24
        editor.remove("configTwentyFiveDay");//25
        editor.remove("configTwentySixDay");//26
        editor.remove("configTwentySevenDay");//27
        editor.remove("configTwentyEightDay");//28
        editor.remove("configTwentyNineDay");//29
        editor.remove("configThirtyDay");//30
        editor.remove("configThirtyOneDay");//31
        editor.remove("configRemarks");//备注
        editor.remove("configcompletion");//总完工数//修改的
        editor.remove("configclipping");//实裁数//修改的
        editor.remove("configBalanceAmount");//结余数量//修改的
        editor.remove("configLastMonth");//上月完工
        editor.remove("configTaskQty");//任务数
        editor.remove("tvFTYDLNewlyPrdstatus");//状态
        editor.remove("tvFTYDLNewlyItem");//款号
        editor.remove("tvFTYDLNewlyDocumentary");//跟单
        editor.remove("tvFTYDLNewlyFactory");//工厂
        editor.remove("tvFTYDLNewlyWorkers");//组别人数
        editor.remove("tvFTYDLNewlyPqty");//制单数
        editor.remove("tvFTYDLNewlyTaskqty");//任务数
        editor.remove("tvFTYDLNewlyMdl");//尺码
        editor.remove("tvFTYDLNewlyProcal");//花色
        editor.remove("tvFTYDLNewlyFactcutqty");//实裁数
        editor.remove("tvFTYDLNewlySumCompletedQty");//总完工数
        editor.remove("configPrdstatus");//状态
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        //关闭界面时清除缓存中可输入的数据
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("mylistStr");//保存集合
        editor.remove("tvFTYDLNewlyItem");//款号
        editor.remove("tvFTYDLNewlyProcedure");//工序
        editor.remove("tvFTYDLNewlyDepartment");//部门
        editor.remove("configdepartment");//部门
        editor.remove("configMonth");//月份
        editor.remove("ConfigProcedure");//工序
        editor.remove("configOthers");//组别人数
        editor.remove("tvFTYDLNewlyWorkers");//组别人数
        editor.remove("configOneDay");//1
        editor.remove("configTwoDay");//2
        editor.remove("configThreeDay");//3
        editor.remove("configForeDay");//4
        editor.remove("configFiveDay");//5
        editor.remove("configSixDay");//6
        editor.remove("configSevenDay");//7
        editor.remove("configEightDay");//8
        editor.remove("configNineDay");//9
        editor.remove("configTenDay");//10
        editor.remove("configElevenDay");//11
        editor.remove("configTwelveDay");//12
        editor.remove("configThirteenDay");//13
        editor.remove("configFourteenDay");//14
        editor.remove("configFifteenDay");//15
        editor.remove("configSixteenDay");//16
        editor.remove("configSeventeenDay");//17
        editor.remove("configEighteenDay");//18
        editor.remove("configNineteenDay");//19
        editor.remove("configTwentyDay");//20
        editor.remove("configTwentyOneDay");//21
        editor.remove("configTwentyTwoDay");//22
        editor.remove("configTwentyThreeDay");//23
        editor.remove("configTwentyForeDay");//24
        editor.remove("configTwentyFiveDay");//25
        editor.remove("configTwentySixDay");//26
        editor.remove("configTwentySevenDay");//27
        editor.remove("configTwentyEightDay");//28
        editor.remove("configTwentyNineDay");//29
        editor.remove("configThirtyDay");//30
        editor.remove("configThirtyOneDay");//31
        editor.remove("configRemarks");//备注
        editor.remove("configcompletion");//总完工数//修改的
        editor.remove("configclipping");//实裁数//修改的
        editor.remove("configBalanceAmount");//结余数量//修改的
        editor.remove("configLastMonth");//上月完工
        editor.remove("configTaskQty");//任务数
        editor.remove("tvFTYDLNewlyPrdstatus");//状态
        editor.remove("configPrdstatus");//状态
        editor.remove("tvFTYDLNewlyDocumentary");//跟单
        editor.remove("tvFTYDLNewlyFactory");//工厂
        editor.remove("tvFTYDLNewlyPqty");//制单数
        editor.remove("tvFTYDLNewlyTaskqty");//任务数
        editor.remove("tvFTYDLNewlyMdl");//尺码
        editor.remove("tvFTYDLNewlyProcal");//花色
        editor.remove("tvFTYDLNewlyFactcutqty");//实裁数
        editor.remove("tvFTYDLNewlySumCompletedQty");//总完工数
        editor.commit();
        super.onDestroy();
    }
}
