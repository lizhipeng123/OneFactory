package com.daoran.newfactory.onefactory.activity.work.production;

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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Time;
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
import com.daoran.newfactory.onefactory.adapter.ProductionNewlyVerticalAdatper;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ComfigVerticalItemBean;
import com.daoran.newfactory.onefactory.bean.ProNewlyBuildDateBean;
import com.daoran.newfactory.onefactory.bean.ProducationNewlyComfigSaveBean;
import com.daoran.newfactory.onefactory.bean.ProducationSaveBean;
import com.daoran.newfactory.onefactory.bean.ProductionNewlybooleanBean;
import com.daoran.newfactory.onefactory.bean.ProductionProcalListBean;
import com.daoran.newfactory.onefactory.bean.ProductionVerticalColBean;
import com.daoran.newfactory.onefactory.bean.ProductionVerticalProcalBean;
import com.daoran.newfactory.onefactory.bean.PropostNewlyBuildBean;
import com.daoran.newfactory.onefactory.bean.PropostNewlyBuildVerticalBean;
import com.daoran.newfactory.onefactory.bean.Propostbean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.RequestParams;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PhoneSaveUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.NameValuePair;

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
 * 功能描述：
 */

public class ProductionNewlyComfigVerticalActivity
        extends BaseFrangmentActivity implements View.OnClickListener {
    private static final String TAG = "productionNewlycomfigvertical";
    private SharedPreferences sp;
    private SPUtils spUtils;
    private List<ProductionNewlybooleanBean.DataBean> booleandatelist =
            new ArrayList<ProductionNewlybooleanBean.DataBean>();
    private ProductionNewlybooleanBean newlybooleanBean;
    private List<ProducationNewlyComfigSaveBean> newlyComfigSaveBeen
            = new ArrayList<ProducationNewlyComfigSaveBean>();

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
    private String otherConfigtext;
    private String smonth, somemonth;
    private int year, month, datetime, hour, minute, second;
    int lastmonth, day1, day2, day3, day4, day5, day6, day7, day8, day9,
            day10, day11, day12, day13, day14, day15, day16, day17, day18,
            day19, day20, day21, day22, day23, day24, day25, day26, day27,
            day28, day29, day30, day31;

    private List<ComfigVerticalItemBean> itemlist =
            new ArrayList<ComfigVerticalItemBean>();

    private ProductionNewlyVerticalAdatper verticalAdatper;
    private ProductionVerticalProcalBean procalBean;
    private ProductionVerticalProcalBean.Data procaldata;
    private List<ProductionVerticalProcalBean.Data> procaldataList
            = new ArrayList<ProductionVerticalProcalBean.Data>();
    private List<ProductionProcalListBean.Data> procalbeanlist =
            new ArrayList<ProductionProcalListBean.Data>();

    private ProNewlyBuildDateBean newlyBuildDateBean;//初始化数据实体bean
    private List<ProNewlyBuildDateBean.DataBean> newdataBeans
            = new ArrayList<ProNewlyBuildDateBean.DataBean>();//初始化数据实体list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_config_vertical);
        getView();
        initView();
//        setDateColor();
        setListener();
        setOnclickEdit();

    }

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

    private void initView() {
        sp = getSharedPreferences("my_sp", 0);
        String isprodure = sp.getString("isprodure", "");

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
            sp = getSharedPreferences("my_sp", 0);
            String spitem = sp.getString("tvnewlydate", "");
            newdataBeans = setDateNewly(spitem);
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
        tv_config_recordat.setText(year + "/" + month + "/" + datetime);
        String nameid = sp.getString("usernamerecoder", "");
        tv_config_recorder.setText(nameid);
        tv_config_year.setText(year + "");
        et_config_month.setText(month + "");
        String tvnewlydate = sp.getString("tvnewlydate", "");//款号
        tv_config_data.setText(tvnewlydate);
        String tvnewlyctmtxt = sp.getString("tvnewlyCtmtxt", "");//客户
        tv_config_ctmtxt.setText(tvnewlyctmtxt);
        String tvnewlyDocumentary = sp.getString("tvnewlyDocumentary", "");//跟单
        tv_config_documentary.setText(tvnewlyDocumentary);
        String tvnewlyFactory = sp.getString("tvnewlyFactory", "");//工厂
        tv_config_subfactory.setText(tvnewlyFactory);
        String tvnewlyDepartment = sp.getString("tvnewlyDepartment", "");//部门/组别
        tv_config_department.setText(tvnewlyDepartment);
        String tvnewlyProcedure = sp.getString("tvnewlyProcedure", "");//工序
        tv_config_procedure.setText(tvnewlyProcedure);
        String tvnewlyOthers = sp.getString("tvnewlyOthers", "");//组别人数
        et_config_others.setText(tvnewlyOthers);
        String tvnewSingularSystem = sp.getString("tvnewSingularSystem", "");//制单数
        tv_config_pqty.setText(tvnewSingularSystem);
        String tvdate = sp.getString("tvColorTaskqty", "");//任务数
        et_config_singularsystem.setText(tvdate);
        String tvnewTaskNumber = sp.getString("tvnewTaskNumber", "");//尺码
        tv_config_size.setText(tvnewTaskNumber);
        String tvnewlySize = sp.getString("tvnewlySize", "");//花色
        et_config_color.setText(tvnewlySize);
        String tvnewlyClippingNumber = sp.getString("tvnewlyClippingNumber", "");//实裁数
        tv_config_clippingnumber.setText(tvnewlyClippingNumber);
        String tvnewlyCompletedLastMonth = sp.getString("tvnewlyCompletedLastMonth", "");//总完工数
        tv_config_totalcompletion.setText(tvnewlyCompletedLastMonth);
        String tvnewlyTotalCompletion = sp.getString("tvnewlyTotalCompletion", "");//状态
        tv_config_state.setText(tvnewlyTotalCompletion);
        tv_config_cutdate.setText(year + "/" + month + "/" + datetime);
    }

    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        btnProSave.setOnClickListener(this);
        tv_config_department.setOnClickListener(this);
        tv_config_state.setOnClickListener(this);
    }

    /*填充颜色list*/
    private void setProcol() {
        SharedPreferences spes = getSharedPreferences("my_sp", 0);
        try {

            for (int j = 0; j < newdataBeans.size(); j++) {
                ProductionProcalListBean.Data procalbean =
                        new ProductionProcalListBean.Data();
                procalbean.setProText(newdataBeans.get(j).getProdcol());
                procalbean.setBalanceAmount(newdataBeans.get(j).getTaskqty());
                procalbean.setProCount("");
                procalbean.setProNum("");
                procalbeanlist.add(procalbean);
            }
            verticalAdatper = new ProductionNewlyVerticalAdatper(
                    ProductionNewlyComfigVerticalActivity.this,
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
                        ProductionNewlyComfigVerticalActivity.this,
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
        final int MIN_MARK = 0;
        //组别人数监听
        et_config_others.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        et_config_others.addTextChangedListener(new TextWatcher() {
            @Override//第一个执行
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override //第二个执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override//第三个执行
            public void afterTextChanged(Editable s) {
                otherConfigtext = s.toString();
                String proothers = et_config_others.getText().toString();
                spUtils.put(getApplicationContext(), "ConfigOthers", proothers);
            }
        });

        final String tvdate = sp.getString("tvColorTaskqty", "");//任务数
        final int singular = Integer.parseInt(tv_config_pqty.getText().toString());
        //任务数监听
        et_config_singularsystem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString() != null || s.toString().equals("")) {
                    int markVal = 0;
                    markVal = Integer.parseInt(s.toString());
                    if (markVal > singular) {
                        ToastUtils.ShowToastMessage("大小不能超过制单数", getApplicationContext());
                        et_config_singularsystem.setText(tvdate);
                        et_config_singularsystem.setSelection(et_config_singularsystem.length());
                    }
                    return;
                }
                String prosingular = et_config_singularsystem.getText().toString();
                spUtils.put(getApplicationContext(), "ConfigTaskNumber", prosingular);
                et_config_singularsystem.setSelection(et_config_singularsystem.length());
            }
        });
        //监听各花色实裁数

        //上月完工数
        et_config_completedlastmonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prolastmonth = et_config_completedlastmonth.getText().toString();
                spUtils.put(getApplicationContext(), "ConfigTaskNumber", prolastmonth);
            }
        });
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
                if (s != null || s.equals("")) {
                    int markVal = 0;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > 12) {
                        et_config_month.setText("12");
                        et_config_month.setSelection(et_config_month.length());
                    }
                }
                String promonth = et_config_month.getText().toString();
                spUtils.put(getApplicationContext(), "ComfigMonth", promonth);
            }
        });
        //1日
        et_config_oneday.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        et_config_oneday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String prooneday = et_config_oneday.getText().toString();
                spUtils.put(getApplicationContext(), "configOneDay", prooneday);
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

    /**
     * 计算总完工数
     *
     * @param lastmont
     */
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
        tv_config_totalcompletion.setText(countmonth);
        spUtils.put(getApplicationContext(), "procomfigcountmonth", countmonth);
    }

    private void setDateColor() {
        sp = getSharedPreferences("my_sp", 0);
        String tvnewlysalesid = sp.getString("tvnewlysalesid", "");
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
                            Type listtype = new TypeToken<List<ProductionVerticalProcalBean.Data>>() {
                            }.getType();
                            procaldataList = gson.fromJson(result, listtype);
//                            procalBean = new Gson().fromJson(response, ProductionVerticalProcalBean.class);
//                            procaldataList = procalBean.getDatas();
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp,
                    ProductionNewlyComfigVerticalActivity.this);
        }
    }

    private void setCutDate() {
        if (NetWork.isNetWorkAvailable(this)) {
            String strcutdate = HttpUrl.debugoneUrl + "FactoryPlan/SearchDailyData/";
            sp = getSharedPreferences("my_sp", 0);
            String tvdatenewlyid = sp.getString("tvnewlysalesid", "");
            String tvdatenewlySalid = sp.getString("tvnewlysalesid", "");//排单id
            String tvdatenewlydate = sp.getString("tvnewlydate", "");//款号
            String tvdatenewlyear = year + "";//年
            String productionMonth = sp.getString("ComfigMonth", "");//月
            if (productionMonth.equals("")) {
                somemonth = et_config_month.getText().toString();
            } else {
                somemonth = productionMonth;
            }
            String tvdatenewlyFactory = sp.getString(
                    "tvnewlyFactory", "");//工厂

            String proColumnTitle = sp.getString("Configdepartment", "");//部门
            String proadaptertitle = sp.getString("tvnewlyDepartment", "");//款号选择传过来的部门组别
            String columntitle;//部门变量
            if (proColumnTitle.equals("")) {//如果没有修改部门组别，就把款号传过来的部门加进去
                columntitle = proadaptertitle;
            } else {
                columntitle = proColumnTitle;
            }

            String proProcedureadapterTitle = sp.getString("ConfigProcedure", "");//工序adapter中修改过的
            String proprocudureTitle = sp.getString("tvnewlyProcedure", "");//从款号选择传过来的工序
            String procudureTitle;//工序变量
            //如果工序显示是空值或者为选择工序，则统一赋给变量为空值""，
            //如果不是的话就把修改的工序赋给变量
            if (proprocudureTitle.equals("选择工序") && proProcedureadapterTitle.equals("")) {
                procudureTitle = "";
            } else if (!proProcedureadapterTitle.equals("")) {
                procudureTitle = proProcedureadapterTitle;
            } else {
                procudureTitle = proprocudureTitle;
            }

            String tvdatenewlySize = sp.getString(
                    "tvnewlySize", "");//花色
            String tvdateda = sp.getString("proverPredocdttimesign", "");//裁床日期
            String liststr = sp.getString("mylistStr", "");//花色集合
            String tvnewlyCtmtxt = sp.getString("tvnewlyCtmtxt", "");//客户
            String tvnewSingularSystem = sp.getString("tvnewSingularSystem", "");//制单数
            String tvnewlyDocumentary = sp.getString("tvnewlyDocumentary", "");//跟单
            String tvnewlyOthers = sp.getString("tvnewlyOthers", "");
            String tvColorTaskqty = sp.getString("tvColorTaskqty", "");//任务数
            String tvnewTaskNumber = sp.getString("tvnewTaskNumber", "");//尺码
            String tvnewlyClippingNumber = sp.getString("tvnewlyClippingNumber", "");//实裁数
            String tvnewlyCompletedLastMonth = sp.getString("tvnewlyCompletedLastMonth", "");//总完工数
            String tvnewlyTotalCompletion = sp.getString("tvnewlyTotalCompletion", "");//生产状态
            int salesidint = Integer.parseInt(tvdatenewlySalid);
            if (liststr.equals("") || liststr.isEmpty()) {
                Gson gson = new Gson();
//                ProductionVerticalColBean colBean = new ProductionVerticalColBean();
//                colBean.setID(salesidint);
//                colBean.setSalesid(salesidint);
//                colBean.setItem(tvdatenewlydate);
//                colBean.setCtmtxt(tvnewlyCtmtxt);
//                colBean.setYear(tvdatenewlyear);
//                colBean.setMonth(somemonth);
//                colBean.setSubfactory(tvdatenewlyFactory);
//                colBean.setSubfactoryTeams(columntitle);
//                colBean.setWorkingProcedure(procudureTitle);
//                colBean.setProdcol(tvdatenewlySize);
//                colBean.setPqty(tvnewSingularSystem);
//                colBean.setPrddocumentary(tvnewlyDocumentary);
//                colBean.setWorkers(tvnewlyOthers);
//                colBean.setTaskqty(tvColorTaskqty);
//                colBean.setMdl(tvnewTaskNumber);
//                colBean.setFactcutqty(tvnewlyClippingNumber);
//                colBean.setLastMonQty("");
//                colBean.setSumCompletedQty(tvnewlyCompletedLastMonth);
//                colBean.setLeftQty("");
//                colBean.setPrdstatus(tvnewlyTotalCompletion);

                ProducationSaveBean saveBean = new ProducationSaveBean();
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
                    List<ProductionProcalListBean.Data> data =
                            new ArrayList<ProductionProcalListBean.Data>();
                    for (int i = 0; i < list.size(); i++) {
                        ProductionVerticalColBean colBean = new ProductionVerticalColBean();
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
                    ProductionNewlyComfigVerticalActivity.this);
        }
    }

    /**
     * 保存上传
     */
    private void setSave() {
//        setCutDate();
        String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
        sp = getSharedPreferences("my_sp", 0);
        final ProgressDialog progressDialog = ProgressDialog.show(this,
                "请稍候...", "正在保存中...", false, true);
        String liststr = sp.getString("mylistStr", "");//花色集合
        String salesid = sp.getString("tvnewlysalesid", "");//款号行id
        String recordid = sp.getString("username", "");//制单人id
        String productionRecorder = sp.getString("usernamerecoder", "");//制单人
        String productionMonth = sp.getString("ComfigMonth", "");//月
        if (productionMonth.equals("")) {
            smonth = et_config_month.getText().toString();
        } else {
            smonth = productionMonth;
        }
        String proColumnTitle = sp.getString("Configdepartment", "");//部门
        String proadaptertitle = sp.getString("tvnewlyDepartment", "");//款号选择传过来的部门组别
        String columntitle;//部门变量
        if (proColumnTitle.equals("")) {//如果没有修改部门组别，就把款号传过来的部门加进去
            columntitle = proadaptertitle;
        } else {
            columntitle = proColumnTitle;
        }
        String productionTaskNumber = sp.getString("ConfigTaskNumber", "");//修改的任务数
        String productiontvColorTaskqty = sp.getString("tvColorTaskqty", "");//款号选择传过来的任务数
        String tvColorTaskqtyss;//任务数变量
        if (productionTaskNumber.equals("")) {//如果没有修改任务数，就把款号传过来的任务数加进去
            tvColorTaskqtyss = productiontvColorTaskqty;
        } else {
            tvColorTaskqtyss = productionTaskNumber;
        }
        String proProcedureadapterTitle = sp.getString("ConfigProcedure", "");//工序adapter中修改过的
        String proprocudureTitle = sp.getString("tvnewlyProcedure", "");//从款号选择传过来的工序
        String procudureTitle;//工序变量
        //如果工序显示是空值或者为选择工序，则统一赋给变量为空值""，
        //如果不是的话就把修改的工序赋给变量
        if (proprocudureTitle.equals("选择工序") && proProcedureadapterTitle.equals("")) {
            procudureTitle = "";
        } else if (!proProcedureadapterTitle.equals("")) {
            procudureTitle = proProcedureadapterTitle;
        } else {
            procudureTitle = proprocudureTitle;
        }
        String proPrdstatusTitle = sp.getString("ComfigPrdstatus", "");//状态//
        String protvnewlyTotalCompletionn = sp.getString("tvnewlyTotalCompletion", "");//款号传过来的状态
        String tvnewlyTotalCompletionn;
        if (proPrdstatusTitle.equals("")) {
            tvnewlyTotalCompletionn = protvnewlyTotalCompletionn;
        } else {
            tvnewlyTotalCompletionn = proPrdstatusTitle;
        }

        int listsize = booleandatelist.size();
        if (listsize == 0) {
            listsize = 1;
        } else {
            listsize = booleandatelist.size();
        }
        String procomfigcountmonth = sp.getString("procomfigcountmonth", "");//结算后的总完工数
        String tvnewlydate = sp.getString("tvnewlydate", "");
        String[] arrsitem = tvnewlydate.split(",");//修改的款号数组
        String[] arrspredure = procudureTitle.split(",");//修改的工序数组
        String[] arrsmonth = productionMonth.split(",");//修改的月份数组
        String[] arrsdatemonth = new String[listsize];
        String[] arrsdatepredure = new String[listsize];//符合条件的工序数组
        Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
        t.setToNow(); // 取得系统时间。
        year = t.year;
        month = t.month;
        datetime = t.monthDay;
        hour = t.hour; // 0-23
        minute = t.minute;
        second = t.second;
        month = month + 1;
        String productionItem = sp.getString("tvnewlydate", "");//款号
        String productionDocumentary = sp.getString("tvnewlyDocumentary", "");//跟单//
        String productionFactory = sp.getString("tvnewlyFactory", "");//工厂
        String productionOthers = sp.getString("ConfigOthers", "");//组别人数
        String productionSingularSystem = sp.getString("tvnewSingularSystem", "");//制单数//
        String productionColor = sp.getString("tvnewlySize", "");//花色
        String productionSize = sp.getString("tvnewTaskNumber", "");//尺码
        String productionClippingNumber = sp.getString("configclipping", "");//实裁数
        String productionCompletedLastMonth = sp.getString("ConfigLastMonth", "");//上月完工
        String productionTotalCompletion = sp.getString("configcompletion", "");//总完工数
        String productionBalanceAmount = sp.getString("configamount", "");//结余数量
        String productionYear = sp.getString("configyear", "");//年
        String productionOneDay = sp.getString("configOneDay", "");//1
        String productionTwoDay = sp.getString("configTwoDay", "");//2
        String productionThreeDay = sp.getString("configThreeDay", "");//3
        String productionForeDay = sp.getString("configForeDay", "");//4
        String productionFiveDay = sp.getString("configFiveDay", "");//5
        String productionSixDay = sp.getString("configSixDay", "");//6
        String productionSevenDay = sp.getString("configSevenDay", "");//7
        String productionEightDay = sp.getString("configEightDay", "");//8
        String productionNineDay = sp.getString("configNineDay", "");//9
        String productionTenDay = sp.getString("configTenDay", "");//10
        String productionElevenDay = sp.getString("configElevenDay", "");//11
        String productionTwelveDay = sp.getString("configTwelveDay", "");//12
        String productionThirteenDay = sp.getString("configThirteenDay", "");//13
        String productionFourteenDay = sp.getString("configFourteenDay", "");//14
        String productionFifteenDay = sp.getString("configFifteenDay", "");//15
        String productionSixteenDay = sp.getString("configSixteenDay", "");//16
        String productionSeventeenDay = sp.getString("configSeventeenDay", "");//17
        String productionEighteenDay = sp.getString("configEighteenDay", "");//18
        String productionNineteenDay = sp.getString("configNineteenDay", "");//19
        String productionTwentyDay = sp.getString("configTwentyDay", "");//20
        String productionTwentyOneDay = sp.getString("configTwentyOneDay", "");//21
        String productionTwentyTwoDay = sp.getString("configTwentyTwoDay", "");//22
        String productionTwentyThreeDay = sp.getString("configTwentyThreeDay", "");//23
        String productionTwentyForeDay = sp.getString("configTwentyForeDay", "");//24
        String productionTwentyFiveDay = sp.getString("configTwentyFiveDay", "");//25
        String productionTwentySixDay = sp.getString("configTwentySixDay", "");//26
        String productionTwentySevenDay = sp.getString("configTwentySevenDay", "");//27
        String productionTwentyEightDay = sp.getString("configTwentyEightDay", "");//28
        String productionTwentyNineDay = sp.getString("configTwentyNineDay", "");//29
        String productionThirtyDay = sp.getString("configThirtyDay", "");//30
        String productionThirtyOneDay = sp.getString("configThirtyOneDay", "");//31
        String productionRemarks = sp.getString("configRemarks", "");//备注
        String productionRecordat = sp.getString("configrecordat", "");//制单时间

        if (booleandatelist.size() == 0) {
            if (procudureTitle.equals("")) {
                ToastUtils.ShowToastMessage("请选择工序，再保存",
                        ProductionNewlyComfigVerticalActivity.this);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                });
                thread.start();
            } else {
                Gson gson = new Gson();
                booleandatelist.size();
                /*判断工序是否是裁床*/
                if (procudureTitle.equals("裁床")) {
                    try {
                            /*如果花色为多条，则循环把这条数据分成多条数据*/
                        for (int j = 0; j < newdataBeans.size(); j++) {
                            ProducationNewlyComfigSaveBean consaveBean =
                                    new ProducationNewlyComfigSaveBean();
                            consaveBean.setID("0");//id
                            consaveBean.setRecordid(recordid);//制单人id
                            consaveBean.setSalesid(salesid);//排单计划id
                            consaveBean.setProdcol(newdataBeans.get(j).getProdcol());//花色
                            consaveBean.setItem(productionItem);//款号
                            consaveBean.setPrddocumentary(productionDocumentary);//跟单
                            consaveBean.setSubfactory(productionFactory);//工厂
                            consaveBean.setSubfactoryTeams(columntitle);//部门组别
                            consaveBean.setWorkingProcedure(procudureTitle);//工序
                            consaveBean.setWorkers(productionOthers);//组别人数
                            consaveBean.setPqty(productionSingularSystem);//制单数
                            consaveBean.setTaskqty(newdataBeans.get(j).getTaskqty());//任务数
                            consaveBean.setMdl(productionSize);//尺码
                            consaveBean.setFactcutqty(procalbeanlist.get(j).getProClippingnumber());//实裁数/
                            consaveBean.setSumCompletedQty(procalbeanlist.get(j).getProTotalCompletion());//总完工数/
                            consaveBean.setLastMonQty(productionCompletedLastMonth);//上月完工数
                            int balan = Integer.parseInt(procalbeanlist.get(j).getBalanceAmount());
                            int cli = Integer.parseInt(procalbeanlist.get(j).getProClippingnumber());
                            consaveBean.setLeftQty(String.valueOf(balan - cli));//结余数量/
                            consaveBean.setPrdstatus(tvnewlyTotalCompletionn);//状态
                            consaveBean.setYear(productionYear);//年
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
                            consaveBean.setRecorder(productionRecorder);
                            consaveBean.setRecordat(productionRecordat);
                            newlyComfigSaveBeen.add(consaveBean);
                        }
                        System.out.print(newdataBeans);
                        System.out.print(newlyComfigSaveBeen);
                        String detailb = gson.toJson(newlyComfigSaveBeen);
                        String dateee = detailb.replace("\"\"", "null");
                        if (newlyComfigSaveBeen.equals("")) {
                            ToastUtils.ShowToastMessage("没有数据可以保存",
                                    ProductionNewlyComfigVerticalActivity.this);
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                            thread.start();
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
                                                Thread thread = new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(1000);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                                thread.start();
                                            }

                                            @Override
                                            public void onResponse(final String response, int id) {
                                                System.out.print(response);
                                                Thread thread = new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(1000);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        String ression = StringUtil.sideTrim(response, "\"");
                                                        System.out.print(ression);
                                                        int resindex = Integer.parseInt(ression);
                                                        setNewlyComfig();
                                                        if (resindex > 4) {
                                                            ToastUtils.ShowToastMessage("保存成功",
                                                                    ProductionNewlyComfigVerticalActivity.this);
                                                            saveDestroy();
                                                            startActivity(new Intent(ProductionNewlyComfigVerticalActivity.this,
                                                                    ProductionActivity.class));
                                                            ProductionActivity.instance.finish();
                                                            procalbeanlist.clear();
                                                            finish();
                                                        } else if (resindex == 3) {
                                                            ToastUtils.ShowToastMessage("保存失败",
                                                                    ProductionNewlyComfigVerticalActivity.this);
                                                        } else if (resindex == 4) {
                                                            ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                    ProductionNewlyComfigVerticalActivity.this);
                                                        } else if (resindex == 2) {
                                                            ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                    ProductionNewlyComfigVerticalActivity.this);
                                                        } else {
                                                            ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                    ProductionNewlyComfigVerticalActivity.this);
                                                        }
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                                thread.start();
                                            }
                                        });
                            } else {
                                ToastUtils.ShowToastMessage(R.string.noHttp, ProductionNewlyComfigVerticalActivity.this);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        ProducationNewlyComfigSaveBean consaveBean =
                                new ProducationNewlyComfigSaveBean();
                        consaveBean.setID("0");
                        consaveBean.setSalesid(salesid);
                        consaveBean.setRecordid(recordid);
                        consaveBean.setProdcol(productionColor);
                        consaveBean.setItem(productionItem);
                        consaveBean.setPrddocumentary(productionDocumentary);
                        consaveBean.setSubfactory(productionFactory);
                        consaveBean.setSubfactoryTeams(columntitle);
                        consaveBean.setWorkingProcedure(procudureTitle);
                        consaveBean.setWorkers(productionOthers);
                        consaveBean.setPqty(productionSingularSystem);
                        consaveBean.setTaskqty(tvColorTaskqtyss);
                        consaveBean.setMdl(productionSize);
                        consaveBean.setFactcutqty(productionClippingNumber);
                        consaveBean.setSumCompletedQty(productionTotalCompletion);
                        consaveBean.setLastMonQty(productionCompletedLastMonth);
                        consaveBean.setLeftQty(productionBalanceAmount);
                        consaveBean.setPrdstatus(tvnewlyTotalCompletionn);
                        consaveBean.setYear(productionYear);
                        if (productionMonth.equals("")) {
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
                        consaveBean.setMemo(productionRemarks);
                        consaveBean.setRecorder(productionRecorder);
                        consaveBean.setRecordat(productionRecordat);
                        newlyComfigSaveBeen.add(consaveBean);
                        System.out.print(newlyComfigSaveBeen);
                        String detailb = gson.toJson(newlyComfigSaveBeen);
                        String dateee = detailb.replace("\"\"", "null");
                        if (newlyComfigSaveBeen.equals("")) {
                            ToastUtils.ShowToastMessage("没有数据可以保存", ProductionNewlyComfigVerticalActivity.this);
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                            thread.start();
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
                                                Thread thread = new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(1000);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                                thread.start();
                                            }

                                            @Override
                                            public void onResponse(final String response, int id) {
                                                System.out.print(response);
                                                Thread thread = new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(1000);

                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        String ression = StringUtil.sideTrim(response, "\"");
                                                        System.out.print(ression);
                                                        int resindex = Integer.parseInt(ression);
                                                        setNewlyComfig();
                                                        if (resindex > 4) {
                                                            ToastUtils.ShowToastMessage("保存成功",
                                                                    ProductionNewlyComfigVerticalActivity.this);
                                                            saveDestroy();
                                                            startActivity(new Intent(ProductionNewlyComfigVerticalActivity.this,
                                                                    ProductionActivity.class));
                                                            ProductionActivity.instance.finish();
                                                            procalbeanlist.clear();
                                                            finish();
                                                        } else if (resindex == 3) {
                                                            ToastUtils.ShowToastMessage("保存失败",
                                                                    ProductionNewlyComfigVerticalActivity.this);
                                                        } else if (resindex == 4) {
                                                            ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                    ProductionNewlyComfigVerticalActivity.this);
                                                        } else {
                                                            ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                    ProductionNewlyComfigVerticalActivity.this);
                                                        }
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                                thread.start();
                                            }
                                        });
                            } else {
                                ToastUtils.ShowToastMessage(R.string.noHttp, ProductionNewlyComfigVerticalActivity.this);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            for (int i = 0; i < listsize; i++) {
                if (listsize != 0) {
                    if (booleandatelist.get(i).getItem() != null) {
                        String woritem = booleandatelist.get(i).getItem();
                        String[] workitempro = woritem.split(",");
                        boolean probool = containsAll(arrsitem, workitempro);
                        if (probool == true) {
                            arrsdatepredure[i] = booleandatelist.get(i).getWorkingProcedure();
                            arrsdatemonth[i] = booleandatelist.get(i).getMonth();
                        } else {
                            arrsdatepredure[i] = "";
                            arrsdatemonth[i] = "";
                        }
                    }
                } else {
                    arrsdatepredure[i] = "";
                    arrsdatemonth[i] = "";
                }
            }
            System.out.print(arrsdatepredure + "");//符合条件的工序
            System.out.print(arrsdatemonth + "");
            StringBuffer sb = new StringBuffer();
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
            boolean monthbool = containsAll(arrsdatemonth, arrsmonth);//判断月份是否存在
            boolean predurebool = containsAll(arrsdatepredure, arrspredure);//判断工序是否存在
            if (procudureTitle.equals("")) {
                ToastUtils.ShowToastMessage("请填写工序", ProductionNewlyComfigVerticalActivity.this);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                });
                thread.start();
            } else {
                if (predurebool == true) {
                    if (monthbool == true) {
                        ToastUtils.ShowToastMessage("已存在相同月份，工序的款号", ProductionNewlyComfigVerticalActivity.this);
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                            }
                        });
                        thread.start();
                    } else {
                        Gson gson = new Gson();
                        booleandatelist.size();
                        if (procudureTitle.equals("裁床")) {
                            try {
                                for (int j = 0; j < newdataBeans.size(); j++) {
                                    ProducationNewlyComfigSaveBean consaveBean =
                                            new ProducationNewlyComfigSaveBean();
                                    consaveBean.setID("0");
                                    consaveBean.setRecordid(recordid);
                                    consaveBean.setSalesid(salesid);
                                    consaveBean.setProdcol(newdataBeans.get(j).getProdcol());
                                    consaveBean.setItem(productionItem);
                                    consaveBean.setPrddocumentary(productionDocumentary);
                                    consaveBean.setSubfactory(productionFactory);
                                    consaveBean.setSubfactoryTeams(columntitle);
                                    consaveBean.setWorkingProcedure(procudureTitle);
                                    consaveBean.setWorkers(productionOthers);
                                    consaveBean.setPqty(productionSingularSystem);
                                    consaveBean.setTaskqty(newdataBeans.get(j).getTaskqty());
                                    consaveBean.setMdl(productionSize);
                                    consaveBean.setFactcutqty(procalbeanlist.get(j).getProClippingnumber());//实裁数/
                                    consaveBean.setSumCompletedQty(procalbeanlist.get(j).getProTotalCompletion());//总完工数/
                                    consaveBean.setLastMonQty(productionCompletedLastMonth);//上月完工数
                                    int balan = Integer.parseInt(procalbeanlist.get(j).getBalanceAmount());
                                    int cli = Integer.parseInt(procalbeanlist.get(j).getProClippingnumber());
                                    consaveBean.setLeftQty(String.valueOf(balan - cli));//结余数量/
                                    consaveBean.setPrdstatus(tvnewlyTotalCompletionn);
                                    consaveBean.setYear(productionYear);
                                    if (productionMonth.equals("")) {
                                        consaveBean.setMonth(smonth);
                                    } else {
                                        consaveBean.setMonth(productionMonth);
                                    }
                                    consaveBean.setMemo(productionRemarks);
                                    consaveBean.setRecorder(productionRecorder);
                                    consaveBean.setRecordat(productionRecordat);
                                    newlyComfigSaveBeen.add(consaveBean);
                                }
                                System.out.print(newdataBeans);
                                System.out.print(newlyComfigSaveBeen);
                                String detailb = gson.toJson(newlyComfigSaveBeen);
                                String dateee = detailb.replace("\"\"", "null");
                                if (newlyComfigSaveBeen.equals("")) {
                                    ToastUtils.ShowToastMessage("没有数据可以保存", ProductionNewlyComfigVerticalActivity.this);
                                    Thread thread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            progressDialog.dismiss();
                                        }
                                    });
                                    thread.start();
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
                                                        Thread thread = new Thread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                try {
                                                                    Thread.sleep(1000);
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                progressDialog.dismiss();
                                                            }
                                                        });
                                                        thread.start();
                                                    }

                                                    @Override
                                                    public void onResponse(final String response, int id) {
                                                        System.out.print(response);
                                                        Thread thread = new Thread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                try {
                                                                    Thread.sleep(1000);
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                String ression = StringUtil.sideTrim(response, "\"");
                                                                System.out.print(ression);
                                                                int resindex = Integer.parseInt(ression);
                                                                setNewlyComfig();
                                                                if (resindex > 4) {
                                                                    ToastUtils.ShowToastMessage("保存成功",
                                                                            ProductionNewlyComfigVerticalActivity.this);
                                                                    saveDestroy();
                                                                    startActivity(new Intent(ProductionNewlyComfigVerticalActivity.this,
                                                                            ProductionActivity.class));
                                                                    procalbeanlist.clear();
                                                                    ProductionActivity.instance.finish();
                                                                    finish();
                                                                } else if (resindex == 3) {
                                                                    ToastUtils.ShowToastMessage("保存失败",
                                                                            ProductionNewlyComfigVerticalActivity.this);
                                                                } else if (resindex == 4) {
                                                                    ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                            ProductionNewlyComfigVerticalActivity.this);
                                                                } else if (resindex == 2) {
                                                                    ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                            ProductionNewlyComfigVerticalActivity.this);
                                                                } else {
                                                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                            ProductionNewlyComfigVerticalActivity.this);
                                                                }
                                                                progressDialog.dismiss();
                                                            }
                                                        });
                                                        thread.start();
                                                    }
                                                });
                                    } else {
                                        ToastUtils.ShowToastMessage(R.string.noHttp, ProductionNewlyComfigVerticalActivity.this);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    Gson gson = new Gson();
                    booleandatelist.size();
                    if (procudureTitle.equals("裁床")) {
                        try {
                            for (int j = 0; j < newdataBeans.size(); j++) {
                                ProducationNewlyComfigSaveBean consaveBean =
                                        new ProducationNewlyComfigSaveBean();
                                consaveBean.setID("0");
                                consaveBean.setRecordid(recordid);
                                consaveBean.setSalesid(salesid);
                                consaveBean.setProdcol(newdataBeans.get(j).getProdcol());
                                consaveBean.setItem(productionItem);
                                consaveBean.setPrddocumentary(productionDocumentary);
                                consaveBean.setSubfactory(productionFactory);
                                consaveBean.setSubfactoryTeams(columntitle);
                                consaveBean.setWorkingProcedure(procudureTitle);
                                consaveBean.setWorkers(productionOthers);
                                consaveBean.setPqty(productionSingularSystem);
                                consaveBean.setTaskqty(newdataBeans.get(j).getTaskqty());
                                consaveBean.setMdl(productionSize);
                                consaveBean.setFactcutqty(procalbeanlist.get(j).getProClippingnumber());//实裁数/
                                consaveBean.setSumCompletedQty(procalbeanlist.get(j).getProTotalCompletion());//总完工数/
                                consaveBean.setLastMonQty(productionCompletedLastMonth);//上月完工数
                                consaveBean.setLeftQty(procalbeanlist.get(j).getBalanceAmount());//结余数量/
                                consaveBean.setPrdstatus(tvnewlyTotalCompletionn);
                                consaveBean.setYear(productionYear);
                                if (productionMonth.equals("")) {
                                    consaveBean.setMonth(smonth);
                                } else {
                                    consaveBean.setMonth(productionMonth);
                                }
                                consaveBean.setMemo(productionRemarks);
                                consaveBean.setRecorder(productionRecorder);
                                consaveBean.setRecordat(productionRecordat);
                                newlyComfigSaveBeen.add(consaveBean);
                            }
                            System.out.print(newdataBeans);
                            System.out.print(newlyComfigSaveBeen);
                            String detailb = gson.toJson(newlyComfigSaveBeen);
                            String dateee = detailb.replace("\"\"", "null");
                            if (newlyComfigSaveBeen.equals("")) {
                                ToastUtils.ShowToastMessage("没有数据可以保存", ProductionNewlyComfigVerticalActivity.this);
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
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
                                                    Thread thread = new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                Thread.sleep(1000);
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                                    thread.start();
                                                }

                                                @Override
                                                public void onResponse(final String response, int id) {
                                                    System.out.print(response);
                                                    Thread thread = new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                Thread.sleep(1000);
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            String ression = StringUtil.sideTrim(response, "\"");
                                                            System.out.print(ression);
                                                            int resindex = Integer.parseInt(ression);
                                                            setNewlyComfig();
                                                            if (resindex > 4) {
                                                                ToastUtils.ShowToastMessage("保存成功",
                                                                        ProductionNewlyComfigVerticalActivity.this);
                                                                saveDestroy();
                                                                startActivity(new Intent(ProductionNewlyComfigVerticalActivity.this,
                                                                        ProductionActivity.class));
                                                                ProductionActivity.instance.finish();
                                                                procalbeanlist.clear();
                                                                finish();
                                                            } else if (resindex == 3) {
                                                                ToastUtils.ShowToastMessage("保存失败",
                                                                        ProductionNewlyComfigVerticalActivity.this);
                                                            } else if (resindex == 4) {
                                                                ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                        ProductionNewlyComfigVerticalActivity.this);
                                                            } else if (resindex == 2) {
                                                                ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                        ProductionNewlyComfigVerticalActivity.this);
                                                            } else {
                                                                ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                        ProductionNewlyComfigVerticalActivity.this);
                                                            }
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                                    thread.start();
                                                }
                                            });
                                } else {
                                    ToastUtils.ShowToastMessage(R.string.noHttp, ProductionNewlyComfigVerticalActivity.this);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /*查询需要分色的相同款号的单子*/
    public List<ProNewlyBuildDateBean.DataBean> setDateNewly(String item) {
        SharedPreferences sp = getSharedPreferences("my_sp", 0);
        String urlDaily = HttpUrl.debugoneUrl + "FactoryPlan/FactoryDailyAPP/";
        String pagesize = sp.getString("clumnspronewspinner", "");
        if (pagesize.equals("")) {
            pagesize = String.valueOf(15);
        }
        Gson gson = new Gson();
        final PropostNewlyBuildVerticalBean buildBean = new PropostNewlyBuildVerticalBean();
        PropostNewlyBuildVerticalBean.Conditions conditions = buildBean.new Conditions();
        conditions.setItem(item);
        conditions.setWorkingProcedure("裁床");
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
                                newlyBuildDateBean = new Gson().fromJson(ression, ProNewlyBuildDateBean.class);
                                newdataBeans = newlyBuildDateBean.getData();
                                setProcol();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ProductionNewlyComfigVerticalActivity.this);
        }
        return newdataBeans;
    }

    /**
     * 新建时点击款号后查询有关当前登录用户的数据
     */
    private void setNewlyComfig() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        sp = getSharedPreferences("my_sp", 0);
        String namedure = sp.getString("usernamerecoder", "");//制单人
        String stis = sp.getString("ischeckedd", "");//是否为空
        boolean stris = Boolean.parseBoolean(stis);
        Gson gson = new Gson();
        Propostbean propostbean = new Propostbean();
        Propostbean.Conditions conditions = propostbean.new Conditions();
        conditions.setItem("");//款号
        conditions.setPrddocumentary(namedure);//制单人
        conditions.setSubfactory("");//工厂
        conditions.setWorkingProcedure("");//工序
        conditions.setPrddocumentaryisnull(stris);//是否为空
        propostbean.setConditions(conditions);//外部嵌套
        propostbean.setPageNum(0);//默认第几页
        propostbean.setPageSize(500);//默认每页多少条数据
        String gsonbeanStr = gson.toJson(propostbean);
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
                                newlybooleanBean = new Gson().fromJson(ression, ProductionNewlybooleanBean.class);
                                booleandatelist = newlybooleanBean.getData();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请重新再试",
                    ProductionNewlyComfigVerticalActivity.this);
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
                        spUtils.put(getApplicationContext(), "Configdepartment", title);
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
                        spUtils.put(getApplicationContext(), "ComfigPrdstatus", title);
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

    /**
     * 判断软键盘是否弹出
     *
     * @param v
     */
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

    /**
     * 判断 array1是否包含所有的 array2
     */
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

    /**
     * 删除存储中的字段
     */
    private void saveDestroy() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("mylistStr");//保存集合
        editor.remove("tvnewlyProcedure");//工序
        editor.remove("Configdepartment");//部门
        editor.remove("tvnewlyDepartment");//部门
        editor.remove("ComfigMonth");//月份
        editor.remove("ConfigProcedure");//修改的工序
        editor.remove("ConfigOthers");//组别人数
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
        editor.remove("ConfigLastMonth");//上月完工
        editor.remove("ConfigTaskNumber");//任务数
        editor.remove("tvnewlyTotalCompletion");//状态
        editor.remove("tvnewlydate");//款号
        editor.remove("tvnewlyDocumentary");//跟单
        editor.remove("tvnewlyFactory");//工厂
        editor.remove("tvnewlyOthers");//组别人数
        editor.remove("tvnewSingularSystem");//制单数
        editor.remove("tvColorTaskqty");//任务数
        editor.remove("tvnewTaskNumber");//尺码
        editor.remove("tvnewlySize");//花色
        editor.remove("tvnewlyClippingNumber");//实裁数
        editor.remove("tvnewlyCompletedLastMonth");//总完工数
        editor.remove("ComfigPrdstatus");//状态
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        //关闭界面时清除缓存中可输入的数据
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("mylistStr");//保存集合
        editor.remove("tvnewlydate");//款号
        editor.remove("tvnewlyProcedure");//工序
        editor.remove("tvnewlyDepartment");//部门
        editor.remove("Configdepartment");//部门
        editor.remove("ComfigMonth");//月份
        editor.remove("ConfigProcedure");//工序
        editor.remove("ConfigOthers");//组别人数
        editor.remove("tvnewlyOthers");//组别人数
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
        editor.remove("ConfigLastMonth");//上月完工
        editor.remove("ConfigTaskNumber");//任务数
        editor.remove("tvnewlyTotalCompletion");//状态
        editor.remove("ComfigPrdstatus");//状态
        editor.remove("tvnewlyDocumentary");//跟单
        editor.remove("tvnewlyFactory");//工厂
        editor.remove("tvnewSingularSystem");//制单数
        editor.remove("tvColorTaskqty");//任务数
        editor.remove("tvnewTaskNumber");//尺码
        editor.remove("tvnewlySize");//花色
        editor.remove("tvnewlyClippingNumber");//实裁数
        editor.remove("tvnewlyCompletedLastMonth");//总完工数
        editor.commit();
        super.onDestroy();
    }
}
