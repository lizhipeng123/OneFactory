package com.daoran.newfactory.onefactory.activity.work.production;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ProductionNewlyVerticalAdatper;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProNewlyBuildDateBean;
import com.daoran.newfactory.onefactory.bean.ProductionProcalListBean;
import com.daoran.newfactory.onefactory.bean.ProductionVerticalProcalBean;
import com.daoran.newfactory.onefactory.bean.PropostNewlyBuildVerticalBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PhoneSaveUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 创建时间：2017/11/20
 * 编写人：lizhipeng
 * 功能描述：
 */

public class ProductionDetailActivity extends BaseFrangmentActivity implements
        View.OnClickListener {
    private static final String TAG = "ProductionDetailActivity";
    private SharedPreferences sp;
    private SPUtils spUtils;
    private TextView tvProTitle;
    private ImageView ivProductionBack;

    private TextView tv_config_data, tv_config_ctmtxt, tv_config_subfactory,
            tv_config_pqty, tv_config_documentary, tv_config_department,
            tv_config_procedure, tv_config_size, tv_config_clippingnumber,
            tv_config_totalcompletion, tv_config_balanceamount, tv_config_state,
            tv_config_year, tv_config_recorder, tv_config_recordat;
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
    private int year, month, datetime, hour, minute, second;
    int lastmonth, day1, day2, day3, day4, day5, day6, day7, day8, day9,
            day10, day11, day12, day13, day14, day15, day16, day17, day18,
            day19, day20, day21, day22, day23, day24, day25, day26, day27,
            day28, day29, day30, day31;
    private ListView list_pro_config_vertical;

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
        setListener();
    }

    private void getView() {
        tvProTitle = (TextView) findViewById(R.id.tvProTitle);
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
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
    }

    private void initView() {
        tvProTitle.setText("修改保存");
        sp = getSharedPreferences("my_sp", 0);
        String isprodure = sp.getString("isprodetailprodure", "");

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
            setProcol();
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
        String tvnewlydate = sp.getString("tvprodetaildate", "");//款号
        tv_config_data.setText(tvnewlydate);
        String tvnewlyctmtxt = sp.getString("tvprodetailCtmtxt", "");//客户
        tv_config_ctmtxt.setText(tvnewlyctmtxt);
        String tvnewlyDocumentary = sp.getString("tvprodetailDocumentary", "");//跟单
        tv_config_documentary.setText(tvnewlyDocumentary);
        String tvnewlyFactory = sp.getString("tvprodetailFactory", "");//工厂
        tv_config_subfactory.setText(tvnewlyFactory);
        String tvnewlyDepartment = sp.getString("tvprodetailDepartment", "");//部门/组别
        tv_config_department.setText(tvnewlyDepartment);
        String tvnewlyProcedure = sp.getString("tvprodetailProcedure", "");//工序
        tv_config_procedure.setText(tvnewlyProcedure);
        String tvnewlyOthers = sp.getString("tvprodetailOthers", "");//组别人数
        et_config_others.setText(tvnewlyOthers);
        String tvnewSingularSystem = sp.getString("tvprodetailSingularSystem", "");//制单数
        tv_config_pqty.setText(tvnewSingularSystem);
        String tvdate = sp.getString("tvprodetailColorTaskqty", "");//任务数
        et_config_singularsystem.setText(tvdate);
        String tvnewTaskNumber = sp.getString("tvprodetailTaskNumber", "");//尺码
        tv_config_size.setText(tvnewTaskNumber);
        String tvnewlySize = sp.getString("tvprodetailSize", "");//花色
        et_config_color.setText(tvnewlySize);
        String tvnewlyClippingNumber = sp.getString("tvprodetailClippingNumber", "");//实裁数
        tv_config_clippingnumber.setText(tvnewlyClippingNumber);
        String tvnewlyCompletedLastMonth = sp.getString("tvprodetailCompletedLastMonth", "");//总完工数
        tv_config_totalcompletion.setText(tvnewlyCompletedLastMonth);
        String tvnewlyTotalCompletion = sp.getString("tvprodetailTotalCompletion", "");//状态
        tv_config_state.setText(tvnewlyTotalCompletion);
    }

    private void setListener() {
        ivProductionBack.setOnClickListener(this);
    }

    /*填充颜色list*/
    private void setProcol() {
        SharedPreferences spes = getSharedPreferences("my_sp", 0);
        String liststr = spes.getString("myprodetaillistStr", "");//花色集合
        try {
            if (liststr.equals("") || liststr.isEmpty()) {
                String procol = spes.getString("tvprodetailSize", "");//花色
                ProductionProcalListBean.Data procalbean =
                        new ProductionProcalListBean.Data();
                procalbean.setProText(procol);
                procalbean.setProCount("");
                procalbean.setProNum("");
                procalbeanlist.add(procalbean);
                System.out.println(procalbeanlist);
                verticalAdatper = new ProductionNewlyVerticalAdatper(
                        ProductionDetailActivity.this,
                        newdataBeans,procalbeanlist);
                list_pro_config_vertical.setAdapter(verticalAdatper);
            } else {
                List<String> listpro = PhoneSaveUtil.String2SceneList(liststr);
                for (int j = 0; j < listpro.size(); j++) {
                    ProductionProcalListBean.Data procalbean =
                            new ProductionProcalListBean.Data();
                    procalbean.setProText(listpro.get(j));
                    procalbean.setProCount("");
                    procalbean.setProNum("");
                    procalbeanlist.add(procalbean);
                }
                verticalAdatper = new ProductionNewlyVerticalAdatper(
                        ProductionDetailActivity.this,
                        newdataBeans,procalbeanlist);
                list_pro_config_vertical.setAdapter(verticalAdatper);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setSaveDate() {

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
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ProductionDetailActivity.this);
        }
        return newdataBeans;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivProductionBack:
                finish();
                break;
        }
    }
}
