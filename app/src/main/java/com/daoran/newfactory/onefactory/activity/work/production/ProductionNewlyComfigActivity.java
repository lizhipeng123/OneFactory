package com.daoran.newfactory.onefactory.activity.work.production;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProducationConfigSaveBean;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 新建保存
 * Created by lizhipeng on 2017/5/9.
 */

public class ProductionNewlyComfigActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private static final String TAG = "configtest";
    private NoscrollListView mData;
    private ProcationDialog procationDialog;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack;
    private List<ProducationDetailBean.DataBean> detailBeenList =
            new ArrayList<ProducationDetailBean.DataBean>();
    private ProducationDetailBean detailBean;
    private List<ProducationConfigSaveBean> saveBeen =
            new ArrayList<ProducationConfigSaveBean>();

    private Button btnProSave;
    private TextView spinnerNewbuild;
    private EditText etNewbuild;
    private MyAdatper comfigAdapter;

    private SharedPreferences sp;
    private SPUtils spUtils;
    private int pageCount;
    private int pageIndex = 0;
    private List<Map<String, Object>> mdate;
    private int year, month, datetime, hour, minute, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_config);
        getViews();
        initViews();
        setListener();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        btnProSave = (Button) findViewById(R.id.btnProSave);
    }

    /**
     * 控件操作
     */
    private void initViews() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mdate = getData();
        comfigAdapter = new MyAdatper(this);
        mData.setAdapter(comfigAdapter);
    }

    /**
     * 点击事件
     */
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        btnProSave.setOnClickListener(this);
    }

    /**
     * 保存list数据
     * @return
     */
    private List<Map<String, Object>> getData() {
        sp = getSharedPreferences("my_sp", 0);
        String tvnewlydate = sp.getString("tvnewlydate", "");//款号
        String tvnewlyDocumentary = sp.getString("tvnewlyDocumentary", "");//跟单
        String tvnewlyFactory = sp.getString("tvnewlyFactory", "");//工厂
        String tvnewlyDepartment = sp.getString("tvnewlyDepartment", "");//部门/组别
        String tvnewlyProcedure = sp.getString("tvnewlyProcedure", "");//工序
        String tvnewlyOthers = sp.getString("tvnewlyOthers", "");//组别人
        String tvnewSingularSystem = sp.getString("tvnewSingularSystem", "");//制单数
        String tvdate = sp.getString("tvColorTaskqty", "");//任务数
        String tvnewTaskNumber = sp.getString("tvnewTaskNumber", "");//尺码
        String tvnewlySize = sp.getString("tvnewlySize", "");//花色
        String tvnewlyClippingNumber = sp.getString("tvnewlyClippingNumber", "");//实裁数
        String tvnewlyCompletedLastMonth = sp.getString("tvnewlyCompletedLastMonth", "");//总完工数
        String tvnewlyTotalCompletion = sp.getString("tvnewlyTotalCompletion", "");//状态
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tvnewlydate", tvnewlydate);
        map.put("tvnewlyDocumentary", tvnewlyDocumentary);
        map.put("tvnewlyFactory", tvnewlyFactory);
        map.put("tvnewlyDepartment", tvnewlyDepartment);
        map.put("tvnewlyProcedure", tvnewlyProcedure);
        map.put("tvnewlyOthers", tvnewlyOthers);
        map.put("tvnewSingularSystem", tvnewSingularSystem);
        map.put("tvColorTaskqty", tvdate);
        map.put("tvnewTaskNumber", tvnewTaskNumber);
        map.put("tvnewlySize", tvnewlySize);
        map.put("tvnewlyClippingNumber", tvnewlyClippingNumber);
        map.put("tvnewlyCompletedLastMonth", tvnewlyCompletedLastMonth);
        map.put("tvnewlyTotalCompletion", tvnewlyTotalCompletion);
        list.add(map);
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivProductionBack:
                finish();
                break;
            case R.id.btnProSave:
                setSave();
                break;
        }
    }

    /**
     * 新建保存
     */
    private void setSave() {
        String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
        sp = this.getSharedPreferences("my_sp", 0);
        String proid = null;
        String salesid = sp.getString("tvnewlysalesid", "");
        String proColumnTitle = sp.getString("ComfigPrdstatus", "");//部门
        String proProcedureTitle = sp.getString("ConfigProcedure", "");//工序
        String proPrdstatusTitle = sp.getString("ComfigPrdstatus", "");//状态//
        String productionItem = sp.getString("comfigitem", "");//款号
        String productionDocumentary = sp.getString("configdocument", "");//跟单//
        String productionFactory = sp.getString("configfactory", "");//工厂
        String productionOthers = sp.getString("ConfigOthers", "");//组别人
        String productionSingularSystem = sp.getString("configsingular", "");//制单数//
        String productionColor = sp.getString("configcolor", "");//花色
        String productionTaskNumber = sp.getString("ConfigTaskNumber", "");//任务数
        String productionSize = sp.getString("configsize", "");//尺码
        String productionClippingNumber = sp.getString("configclipping", "");//实裁数
        String productionCompletedLastMonth = sp.getString("ConfigTaskNumber", "");//上月完工
        String productionTotalCompletion = sp.getString("configcompletion", "");//总完工数
        String productionBalanceAmount = sp.getString("configamount", "");//结余数量
        String productionYear = sp.getString("configyear", "");//年
        String productionMonth = sp.getString("ComfigMonth", "");//月
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
        String productionRecorder = sp.getString("configrecorder", "");//制单人
        String productionRecordat = sp.getString("configrecordat", "");//制单时间
        Gson gson = new Gson();
        ProducationConfigSaveBean saveBean = new ProducationConfigSaveBean();
        saveBean.setID("");
        saveBean.setSalesid(salesid);
        saveBean.setItem(productionItem);
        saveBean.setPrddocumentary(productionDocumentary);
        saveBean.setSubfactory(productionFactory);
        saveBean.setSubfactoryTeams(proColumnTitle);
        saveBean.setWorkingProcedure(proProcedureTitle);
        saveBean.setWorkers(productionOthers);
        saveBean.setPqty(productionSingularSystem);
        saveBean.setProdcol(productionColor);
        saveBean.setTaskqty(productionTaskNumber);
        saveBean.setMdl(productionSize);
        saveBean.setFactcutqty(productionClippingNumber);
        saveBean.setLastMonQty(productionCompletedLastMonth);
        saveBean.setSumCompletedQty(productionTotalCompletion);
        saveBean.setLeftQty(productionBalanceAmount);
        saveBean.setPrdstatus(proPrdstatusTitle);
        saveBean.setYear(productionYear);
        saveBean.setMonth(productionMonth);
        saveBean.setDay1(productionOneDay);
        saveBean.setDay2(productionTwoDay);
        saveBean.setDay3(productionThreeDay);
        saveBean.setDay4(productionForeDay);
        saveBean.setDay5(productionFiveDay);
        saveBean.setDay6(productionSixDay);
        saveBean.setDay7(productionSevenDay);
        saveBean.setDay8(productionEightDay);
        saveBean.setDay9(productionNineDay);
        saveBean.setDay10(productionTenDay);
        saveBean.setDay11(productionElevenDay);
        saveBean.setDay12(productionTwelveDay);
        saveBean.setDay13(productionThirteenDay);
        saveBean.setDay14(productionFourteenDay);
        saveBean.setDay15(productionFifteenDay);
        saveBean.setDay16(productionSixteenDay);
        saveBean.setDay17(productionSeventeenDay);
        saveBean.setDay18(productionEighteenDay);
        saveBean.setDay19(productionNineteenDay);
        saveBean.setDay20(productionTwentyDay);
        saveBean.setDay21(productionTwentyOneDay);
        saveBean.setDay22(productionTwentyTwoDay);
        saveBean.setDay23(productionTwentyThreeDay);
        saveBean.setDay24(productionTwentyForeDay);
        saveBean.setDay25(productionTwentyFiveDay);
        saveBean.setDay26(productionTwentySixDay);
        saveBean.setDay27(productionTwentySevenDay);
        saveBean.setDay28(productionTwentyEightDay);
        saveBean.setDay29(productionTwentyNineDay);
        saveBean.setDay30(productionThirtyDay);
        saveBean.setDay31(productionThirtyOneDay);
        saveBean.setMemo(productionRemarks);
        saveBean.setRecorder(productionRecorder);
        saveBean.setRecordat(productionRecordat);
        saveBeen.add(saveBean);
        String detailb = gson.toJson(saveBeen);
        String dateee = detailb.replace("\"\"", "null");
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this);
            OkHttpUtils.postString().
                    url(saveurl)
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
                            System.out.print(response);
                            String ression = StringUtil.sideTrim(response, "\"");
                            System.out.print(ression);
                            int resindex = Integer.parseInt(ression);
                            if (resindex > 4) {
                                ResponseDialog.closeLoading();
                                ToastUtils.ShowToastMessage("保存成功，请刷新页面",
                                        ProductionNewlyComfigActivity.this);
                                startActivity(new Intent(ProductionNewlyComfigActivity.this,
                                        ProductionActivity.class));
                            } else if (resindex == 3) {
                                ResponseDialog.closeLoading();
                                ToastUtils.ShowToastMessage("保存失败",
                                        ProductionNewlyComfigActivity.this);
                            } else if (resindex == 4) {
                                ResponseDialog.closeLoading();
                                ToastUtils.ShowToastMessage("数据错误，请重试",
                                        ProductionNewlyComfigActivity.this);
                            } else {
                                ResponseDialog.closeLoading();
                                ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                        ProductionNewlyComfigActivity.this);
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ProductionNewlyComfigActivity.this);
        }
    }

    public final class ViewHolder {
        TextView tv_data;//款号
        TextView tvProDocumentary,//跟单
                tvProFactory;//工厂
        TextView tvProDepartment,//部门/组别
                tvProState,//状态
                tvProProcedure;//工序
        EditText tvProOthers,//组别人
                tvProCompletedLastMonth,//上月完工
                tvProTaskNumber;//任务数;
        TextView tvProSingularSystem,//制单数
                tvProColor,//花色
                tvProSize,//尺码
                tvProMonth,//月
                tvProClippingNumber,//实裁数
                tvProTotalCompletion,//总完工数
                tvProBalanceAmount,//结余数量
                tvProYear;//年
        EditText tvProOneDay,//1日
                tvProTwoDay,//2
                tvProThreeDay,//3
                tvProForeDay,//4
                tvProFiveDay,//5
                tvProSixDay,//6
                tvProSevenDay,//7
                tvProEightDay,//8
                tvProNineDay,//9
                tvProTenDay,//10
                tvProElevenDay,//11
                tvProTwelveDay,//12
                tvProThirteenDay,//13
                tvProFourteenDay,//14
                tvProFifteenDay,//15
                tvProSixteenDay,//16
                tvProSeventeenDay,//17
                tvProEighteenDay,//18
                tvProNineteenDay,//19
                tvProTwentyDay,//20
                tvProTwentyOneDay,//21
                tvProTwentyTwoDay,//22
                tvProTwentyThreeDay,//23
                tvProTwentyForeDay,//24
                tvProTwentyFiveDay,//25
                tvProTwentySixDay,//26
                tvProTwentySevenDay,//27
                tvProTwentyEightDay,//28
                tvProTwentyNineDay,//29
                tvProThirtyDay,//30
                tvProThirtyOneDay,//31
                tvProRemarks;//备注
        TextView tvProRecorder,//制单人
                tvProRecordat;//制单时间
    }

    /**
     * 新建保存适配
     */
    public class MyAdatper extends BaseAdapter {
        private Context context;
        private int index = -1;

        public MyAdatper(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return mdate.size();
        }

        @Override
        public Object getItem(int position) {
            return mdate.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_production_data, null);
                viewHolder.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
                viewHolder.tvProDocumentary = (TextView) convertView.findViewById(R.id.tvProDocumentary);
                viewHolder.tvProFactory = (TextView) convertView.findViewById(R.id.tvProFactory);
                viewHolder.tvProDepartment = (TextView) convertView.findViewById(R.id.tvProDepartment);
                viewHolder.tvProProcedure = (TextView) convertView.findViewById(R.id.tvProProcedure);
                viewHolder.tvProOthers = (EditText) convertView.findViewById(R.id.tvProOthers);
                viewHolder.tvProSingularSystem = (TextView) convertView.findViewById(R.id.tvProSingularSystem);
                viewHolder.tvProColor = (TextView) convertView.findViewById(R.id.tvProColor);
                viewHolder.tvProTaskNumber = (EditText) convertView.findViewById(R.id.tvProTaskNumber);
                viewHolder.tvProSize = (TextView) convertView.findViewById(R.id.tvProSize);
                viewHolder.tvProClippingNumber = (TextView) convertView.findViewById(R.id.tvProClippingNumber);
                viewHolder.tvProCompletedLastMonth = (EditText) convertView.findViewById(R.id.tvProCompletedLastMonth);
                viewHolder.tvProTotalCompletion = (TextView) convertView.findViewById(R.id.tvProTotalCompletion);
                viewHolder.tvProBalanceAmount = (TextView) convertView.findViewById(R.id.tvProBalanceAmount);
                viewHolder.tvProState = (TextView) convertView.findViewById(R.id.tvProState);
                viewHolder.tvProYear = (TextView) convertView.findViewById(R.id.tvProYear);
                viewHolder.tvProMonth = (TextView) convertView.findViewById(R.id.tvProMonth);
                viewHolder.tvProOneDay = (EditText) convertView.findViewById(R.id.tvProOneDay);
                viewHolder.tvProTwoDay = (EditText) convertView.findViewById(R.id.tvProTwoDay);
                viewHolder.tvProThreeDay = (EditText) convertView.findViewById(R.id.tvProThreeDay);
                viewHolder.tvProForeDay = (EditText) convertView.findViewById(R.id.tvProForeDay);
                viewHolder.tvProFiveDay = (EditText) convertView.findViewById(R.id.tvProFiveDay);
                viewHolder.tvProSixDay = (EditText) convertView.findViewById(R.id.tvProSixDay);
                viewHolder.tvProSevenDay = (EditText) convertView.findViewById(R.id.tvProSevenDay);
                viewHolder.tvProEightDay = (EditText) convertView.findViewById(R.id.tvProEightDay);
                viewHolder.tvProNineDay = (EditText) convertView.findViewById(R.id.tvProNineDay);
                viewHolder.tvProTenDay = (EditText) convertView.findViewById(R.id.tvProTenDay);
                viewHolder.tvProElevenDay = (EditText) convertView.findViewById(R.id.tvProElevenDay);
                viewHolder.tvProTwelveDay = (EditText) convertView.findViewById(R.id.tvProTwelveDay);
                viewHolder.tvProThirteenDay = (EditText) convertView.findViewById(R.id.tvProThirteenDay);
                viewHolder.tvProFourteenDay = (EditText) convertView.findViewById(R.id.tvProFourteenDay);
                viewHolder.tvProFifteenDay = (EditText) convertView.findViewById(R.id.tvProFifteenDay);
                viewHolder.tvProSixteenDay = (EditText) convertView.findViewById(R.id.tvProSixteenDay);
                viewHolder.tvProSeventeenDay = (EditText) convertView.findViewById(R.id.tvProSeventeenDay);
                viewHolder.tvProEighteenDay = (EditText) convertView.findViewById(R.id.tvProEighteenDay);
                viewHolder.tvProNineteenDay = (EditText) convertView.findViewById(R.id.tvProNineteenDay);
                viewHolder.tvProTwentyDay = (EditText) convertView.findViewById(R.id.tvProTwentyDay);
                viewHolder.tvProTwentyOneDay = (EditText) convertView.findViewById(R.id.tvProTwentyOneDay);
                viewHolder.tvProTwentyTwoDay = (EditText) convertView.findViewById(R.id.tvProTwentyTwoDay);
                viewHolder.tvProTwentyThreeDay = (EditText) convertView.findViewById(R.id.tvProTwentyThreeDay);
                viewHolder.tvProTwentyForeDay = (EditText) convertView.findViewById(R.id.tvProTwentyForeDay);
                viewHolder.tvProTwentyFiveDay = (EditText) convertView.findViewById(R.id.tvProTwentyFiveDay);
                viewHolder.tvProTwentySixDay = (EditText) convertView.findViewById(R.id.tvProTwentySixDay);
                viewHolder.tvProTwentySevenDay = (EditText) convertView.findViewById(R.id.tvProTwentySevenDay);
                viewHolder.tvProTwentyEightDay = (EditText) convertView.findViewById(R.id.tvProTwentyEightDay);
                viewHolder.tvProTwentyNineDay = (EditText) convertView.findViewById(R.id.tvProTwentyNineDay);
                viewHolder.tvProThirtyDay = (EditText) convertView.findViewById(R.id.tvProThirtyDay);
                viewHolder.tvProThirtyOneDay = (EditText) convertView.findViewById(R.id.tvProThirtyOneDay);
                viewHolder.tvProRemarks = (EditText) convertView.findViewById(R.id.tvProRemarks);
                viewHolder.tvProRecorder = (TextView) convertView.findViewById(R.id.tvProRecorder);
                viewHolder.tvProRecordat = (TextView) convertView.findViewById(R.id.tvProRecordat);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String tvnewly = String.valueOf(mdate.get(position).get("tvnewlydate"));
            System.out.print(tvnewly);
            viewHolder.tv_data.setText(tvnewly);

            sp = getSharedPreferences("my_sp", 0);
            String nameid = sp.getString("usernamerecoder", "");
            String tvnewlyDocumen = String.valueOf(mdate.get(position).get("tvnewlyDocumentary"));
            String tvnewlyFactory = String.valueOf(mdate.get(position).get("tvnewlyFactory"));
            String tvnewlyDepartment = String.valueOf(mdate.get(position).get("tvnewlyDepartment"));
            String tvnewlyProcedure = String.valueOf(mdate.get(position).get("tvnewlyProcedure"));
            String tvnewlyOthers = String.valueOf(mdate.get(position).get("tvnewlyOthers"));
            String tvnewSingularSystem = String.valueOf(mdate.get(position).get("tvnewSingularSystem"));
            String tvdate = String.valueOf(mdate.get(position).get("tvColorTaskqty"));
            String tvnewTaskNumber = String.valueOf(mdate.get(position).get("tvnewTaskNumber"));
            String tvnewlySize = String.valueOf(mdate.get(position).get("tvnewlySize"));
            String tvnewlyClippingNumber = String.valueOf(mdate.get(position).get("tvnewlyClippingNumber"));
            String tvnewlyCompletedLastMonth = String.valueOf(mdate.get(position).get("tvnewlyCompletedLastMonth"));
            String tvnewlyTotalCompletion = String.valueOf(mdate.get(position).get("tvnewlyTotalCompletion"));

            Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
            t.setToNow(); // 取得系统时间。
            year = t.year;
            month = t.month;
            datetime = t.monthDay;
            hour = t.hour; // 0-23
            minute = t.minute;
            second = t.second;
            month = month + 1;

            if (nameid != null && !nameid.equals("")) {

                viewHolder.tvProDocumentary.setText(tvnewlyDocumen);
                viewHolder.tvProFactory.setText(tvnewlyFactory);
                viewHolder.tvProDepartment.setText(tvnewlyDepartment);
                viewHolder.tvProProcedure.setText(tvnewlyProcedure);
                viewHolder.tvProOthers.setText(tvnewlyOthers);
                viewHolder.tvProSingularSystem.setText(tvnewSingularSystem);
                viewHolder.tvProSize.setText(tvnewTaskNumber);
                viewHolder.tvProColor.setText(tvnewlySize);
                viewHolder.tvProClippingNumber.setText(tvnewlyClippingNumber);

                viewHolder.tvProRecordat.setText(year + "/" + month + "/" + datetime);

                String comfigitem = viewHolder.tv_data.getText().toString();
                spUtils.put(context, "comfigitem", comfigitem);

                String configdocument = viewHolder.tvProDocumentary.getText().toString();
                spUtils.put(context, "configdocument", configdocument);

                String configfactory = viewHolder.tvProFactory.getText().toString();
                spUtils.put(context, "configfactory", configfactory);

                viewHolder.tvProDepartment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(context, v);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_pro_column, popupMenu.getMenu());
                        // menu的item点击事件
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                String title = item.getTitle().toString();
                                spUtils.put(context, "Configdepartment", title);
                                viewHolder.tvProDepartment.setText(title);
                                ToastUtils.ShowToastMessage("点击的是：" + title, context);
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

                viewHolder.tvProProcedure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(context, v);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_pro_procedure, popupMenu.getMenu());
                        // menu的item点击事件
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                sp = context.getSharedPreferences("userInfo", 0);
                                String title = item.getTitle().toString();
                                spUtils.put(context, "ConfigProcedure", title);
                                viewHolder.tvProProcedure.setText(title);
                                ToastUtils.ShowToastMessage("点击的是：" + title, context);
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

                final EditText editTexOthers = viewHolder.tvProOthers;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexOthers.getTag() instanceof TextWatcher) {
                    editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
                }
                editTexOthers.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvOthers = new TextWatcher() {
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
                        String proitem = viewHolder.tvProOthers.getText().toString();
                        spUtils.put(context, "ConfigOthers", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexOthers.addTextChangedListener(TvOthers);
                editTexOthers.setTag(TvOthers);
            /*光标放置在文本最后*/
                viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.length());

                String configsingular = viewHolder.tvProSingularSystem.getText().toString();
                spUtils.put(context, "configsingular", configsingular);

                String configcolor = viewHolder.tvProColor.getText().toString();
                spUtils.put(context, "configcolor", configcolor);

                final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
//            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                    editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
                }
                viewHolder.tvProTaskNumber.setText(tvdate);
                editTexTaskNumber.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTaskNumber = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTaskNumber.getText().toString();
                        spUtils.put(context, "ConfigTaskNumber", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTaskNumber.addTextChangedListener(TvTaskNumber);
                editTexTaskNumber.setTag(TvTaskNumber);
            /*光标放置在文本最后*/
                viewHolder.tvProTaskNumber.setSelection(viewHolder.tvProTaskNumber.length());

                String configsize = viewHolder.tvProSize.getText().toString();
                spUtils.put(context, "configsize", configsize);

                String configclipping = viewHolder.tvProClippingNumber.getText().toString();
                spUtils.put(context, "configclipping", configclipping);

                final EditText editTexLastMonth = viewHolder.tvProCompletedLastMonth;
//            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexLastMonth.getTag() instanceof TextWatcher) {
                    editTexLastMonth.removeTextChangedListener((TextWatcher) editTexLastMonth.getTag());
                }
                viewHolder.tvProCompletedLastMonth.setText(tvnewlyCompletedLastMonth);
                editTexLastMonth.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvLastMonth = new TextWatcher() {
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
                        String proitem = viewHolder.tvProCompletedLastMonth.getText().toString();
                        spUtils.put(context, "ConfigTaskNumber", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexLastMonth.addTextChangedListener(TvLastMonth);
                editTexLastMonth.setTag(TvLastMonth);
            /*光标放置在文本最后*/
                viewHolder.tvProCompletedLastMonth.setSelection(viewHolder.tvProCompletedLastMonth.length());

                viewHolder.tvProTotalCompletion.setText(tvnewlyTotalCompletion);
                String configcompletion = viewHolder.tvProTotalCompletion.getText().toString();
                spUtils.put(context, "configcompletion", configcompletion);

                String configamount = viewHolder.tvProBalanceAmount.getText().toString();
                spUtils.put(context, "configamount", configamount);


                viewHolder.tvProState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(context, v);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_pro_prdstatus, popupMenu.getMenu());
                        // menu的item点击事件
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                String title = item.getTitle().toString();
                                spUtils.put(context, "ComfigPrdstatus", title);
                                viewHolder.tvProState.setText(title);
                                ToastUtils.ShowToastMessage("点击的是：" + title, context);
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

                viewHolder.tvProYear.setText(year + "");
                String configyear = viewHolder.tvProYear.getText().toString();
                spUtils.put(context, "configyear", configyear);


                viewHolder.tvProMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(context, v);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_pro_mouth, popupMenu.getMenu());
                        // menu的item点击事件
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                String title = item.getTitle().toString();
                                spUtils.put(context, "ComfigMonth", title);
                                viewHolder.tvProMonth.setText(title);
                                ToastUtils.ShowToastMessage("点击的是：" + title, context);
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


                final EditText editTexOneDay = viewHolder.tvProOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexOneDay.getTag() instanceof TextWatcher) {
                    editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
                }
                editTexOneDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvOneDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProOneDay.getText().toString();
                        spUtils.put(context, "configOneDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexOneDay.addTextChangedListener(TvOneDay);
                editTexOneDay.setTag(TvOneDay);
            /*光标放置在文本最后*/
                viewHolder.tvProOneDay.setSelection(viewHolder.tvProOneDay.length());


                viewHolder.tvProTwoDay.setEnabled(true);
                final EditText editTexTwoDay = viewHolder.tvProTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwoDay.getTag() instanceof TextWatcher) {
                    editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
                }
                editTexTwoDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwoDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwoDay.getText().toString();
                        spUtils.put(context, "configTwoDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwoDay.addTextChangedListener(TvTwoDay);
                editTexTwoDay.setTag(TvTwoDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwoDay.setSelection(viewHolder.tvProTwoDay.length());


                viewHolder.tvProThreeDay.setEnabled(true);
                final EditText editTexThreeDay = viewHolder.tvProThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThreeDay.getTag() instanceof TextWatcher) {
                    editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
                }
                editTexThreeDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvThreeDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProThreeDay.getText().toString();
                        spUtils.put(context, "configThreeDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexThreeDay.addTextChangedListener(TvThreeDay);
                editTexThreeDay.setTag(TvThreeDay);
            /*光标放置在文本最后*/
                viewHolder.tvProThreeDay.setSelection(viewHolder.tvProThreeDay.length());


                viewHolder.tvProForeDay.setEnabled(true);
                final EditText editTexForeDay = viewHolder.tvProForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexForeDay.getTag() instanceof TextWatcher) {
                    editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
                }
                editTexForeDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvForeDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProForeDay.getText().toString();
                        spUtils.put(context, "configForeDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexForeDay.addTextChangedListener(TvForeDay);
                editTexForeDay.setTag(TvForeDay);
            /*光标放置在文本最后*/
                viewHolder.tvProForeDay.setSelection(viewHolder.tvProForeDay.length());


                viewHolder.tvProFiveDay.setEnabled(true);
                final EditText editTexFiveDay = viewHolder.tvProFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFiveDay.getTag() instanceof TextWatcher) {
                    editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
                }
                editTexFiveDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvFiveDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProFiveDay.getText().toString();
                        spUtils.put(context, "configFiveDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexFiveDay.addTextChangedListener(TvFiveDay);
                editTexFiveDay.setTag(TvFiveDay);
            /*光标放置在文本最后*/
                viewHolder.tvProFiveDay.setSelection(viewHolder.tvProFiveDay.length());


                viewHolder.tvProSixDay.setEnabled(true);
                final EditText editTexSixDay = viewHolder.tvProSixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSixDay.getTag() instanceof TextWatcher) {
                    editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
                }
                editTexSixDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvSixDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProSixDay.getText().toString();
                        spUtils.put(context, "configSixDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexSixDay.addTextChangedListener(TvSixDay);
                editTexSixDay.setTag(TvSixDay);
            /*光标放置在文本最后*/
                viewHolder.tvProSixDay.setSelection(viewHolder.tvProSixDay.length());


                viewHolder.tvProSevenDay.setEnabled(true);
                final EditText editTexSevenDay = viewHolder.tvProSevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSevenDay.getTag() instanceof TextWatcher) {
                    editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
                }
                editTexSevenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvSevenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProSevenDay.getText().toString();
                        spUtils.put(context, "configSevenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexSevenDay.addTextChangedListener(TvSevenDay);
                editTexSevenDay.setTag(TvSevenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProSevenDay.setSelection(viewHolder.tvProSevenDay.length());


                viewHolder.tvProEightDay.setEnabled(true);
                final EditText editTexEightDay = viewHolder.tvProEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexEightDay.getTag() instanceof TextWatcher) {
                    editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
                }
                editTexEightDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvEightDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProEightDay.getText().toString();
                        spUtils.put(context, "configEightDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexEightDay.addTextChangedListener(TvEightDay);
                editTexEightDay.setTag(TvEightDay);
            /*光标放置在文本最后*/
                viewHolder.tvProEightDay.setSelection(viewHolder.tvProEightDay.length());


                viewHolder.tvProNineDay.setEnabled(true);
                final EditText editTexNineDay = viewHolder.tvProNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexNineDay.getTag() instanceof TextWatcher) {
                    editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
                }
                editTexNineDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvNineDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProNineDay.getText().toString();
                        spUtils.put(context, "configNineDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexNineDay.addTextChangedListener(TvNineDay);
                editTexNineDay.setTag(TvNineDay);
            /*光标放置在文本最后*/
                viewHolder.tvProNineDay.setSelection(viewHolder.tvProNineDay.length());


                viewHolder.tvProTenDay.setEnabled(true);
                final EditText editTexTenDay = viewHolder.tvProTenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTenDay.getTag() instanceof TextWatcher) {
                    editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
                }
                editTexTenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTenDay.getText().toString();
                        spUtils.put(context, "configTenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTenDay.addTextChangedListener(TvTenDay);
                editTexTenDay.setTag(TvTenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTenDay.setSelection(viewHolder.tvProTenDay.length());


                viewHolder.tvProElevenDay.setEnabled(true);
                final EditText editTexElevenDay = viewHolder.tvProElevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexElevenDay.getTag() instanceof TextWatcher) {
                    editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
                }
                editTexElevenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvElevenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProElevenDay.getText().toString();
                        spUtils.put(context, "configElevenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexElevenDay.addTextChangedListener(TvElevenDay);
                editTexElevenDay.setTag(TvElevenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProElevenDay.setSelection(viewHolder.tvProElevenDay.length());


                viewHolder.tvProTwelveDay.setEnabled(true);
                final EditText editTexTwelveDay = viewHolder.tvProTwelveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwelveDay.getTag() instanceof TextWatcher) {
                    editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
                }
                editTexTwelveDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwelveDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwelveDay.getText().toString();
                        spUtils.put(context, "configTwelveDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwelveDay.addTextChangedListener(TvTwelveDay);
                editTexTwelveDay.setTag(TvTwelveDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwelveDay.setSelection(viewHolder.tvProTwelveDay.length());


                viewHolder.tvProThirteenDay.setEnabled(true);
                final EditText editTexThirteenDay = viewHolder.tvProThirteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirteenDay.getTag() instanceof TextWatcher) {
                    editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
                }
                editTexThirteenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvThirteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProThirteenDay.getText().toString();
                        spUtils.put(context, "configThirteenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexThirteenDay.addTextChangedListener(TvThirteenDay);
                editTexThirteenDay.setTag(TvThirteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProThirteenDay.setSelection(viewHolder.tvProThirteenDay.length());


                viewHolder.tvProFourteenDay.setEnabled(true);
                final EditText editTexFourteenDay = viewHolder.tvProFourteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFourteenDay.getTag() instanceof TextWatcher) {
                    editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
                }
                editTexFourteenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvFourteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProFourteenDay.getText().toString();
                        spUtils.put(context, "configFourteenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexFourteenDay.addTextChangedListener(TvFourteenDay);
                editTexFourteenDay.setTag(TvFourteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProFourteenDay.setSelection(viewHolder.tvProFourteenDay.length());


                viewHolder.tvProFifteenDay.setEnabled(true);
                final EditText editTexFifteenDay = viewHolder.tvProFifteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFifteenDay.getTag() instanceof TextWatcher) {
                    editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
                }
                editTexFifteenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvFifteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProFifteenDay.getText().toString();
                        spUtils.put(context, "configFifteenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexFifteenDay.addTextChangedListener(TvFifteenDay);
                editTexFifteenDay.setTag(TvFifteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProFifteenDay.setSelection(viewHolder.tvProFifteenDay.length());


                viewHolder.tvProSixteenDay.setEnabled(true);
                final EditText editTexSixteenDay = viewHolder.tvProSixteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSixteenDay.getTag() instanceof TextWatcher) {
                    editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
                }
                editTexSixteenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvSixteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProSixteenDay.getText().toString();
                        spUtils.put(context, "configSixteenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexSixteenDay.addTextChangedListener(TvSixteenDay);
                editTexSixteenDay.setTag(TvSixteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProSixteenDay.setSelection(viewHolder.tvProSixteenDay.length());


                viewHolder.tvProSeventeenDay.setEnabled(true);
                final EditText editTexSeventeenDay = viewHolder.tvProSeventeenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
                    editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
                }
                editTexSeventeenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvSeventeenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProSeventeenDay.getText().toString();
                        spUtils.put(context, "configSeventeenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexSeventeenDay.addTextChangedListener(TvSeventeenDay);
                editTexSeventeenDay.setTag(TvSeventeenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProSeventeenDay.setSelection(viewHolder.tvProSeventeenDay.length());


                viewHolder.tvProEighteenDay.setEnabled(true);
                final EditText editTexEighteenDay = viewHolder.tvProEighteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexEighteenDay.getTag() instanceof TextWatcher) {
                    editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
                }
                editTexEighteenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvEighteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProEighteenDay.getText().toString();
                        spUtils.put(context, "configEighteenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexEighteenDay.addTextChangedListener(TvEighteenDay);
                editTexEighteenDay.setTag(TvEighteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProEighteenDay.setSelection(viewHolder.tvProEighteenDay.length());


                viewHolder.tvProNineteenDay.setEnabled(true);
                final EditText editTexNineteenDay = viewHolder.tvProNineteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexNineteenDay.getTag() instanceof TextWatcher) {
                    editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
                }
                editTexNineteenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvNineteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProNineteenDay.getText().toString();
                        spUtils.put(context, "configNineteenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexNineteenDay.addTextChangedListener(TvNineteenDay);
                editTexNineteenDay.setTag(TvNineteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProNineteenDay.setSelection(viewHolder.tvProNineteenDay.length());


                viewHolder.tvProTwentyDay.setEnabled(true);
                final EditText editTexTwentyDay = viewHolder.tvProTwentyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyDay.getTag() instanceof TextWatcher) {
                    editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
                }
                editTexTwentyDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentyDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyDay.getText().toString();
                        spUtils.put(context, "configTwentyDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentyDay.addTextChangedListener(TvTwentyDay);
                editTexTwentyDay.setTag(TvTwentyDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyDay.setSelection(viewHolder.tvProTwentyDay.length());


                viewHolder.tvProTwentyOneDay.setEnabled(true);
                final EditText editTexTwentyOneDay = viewHolder.tvProTwentyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
                    editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
                }
                editTexTwentyOneDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentyOneDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyOneDay.getText().toString();
                        spUtils.put(context, "configTwentyOneDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentyOneDay.addTextChangedListener(TvTwentyOneDay);
                editTexTwentyOneDay.setTag(TvTwentyOneDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyOneDay.setSelection(viewHolder.tvProTwentyOneDay.length());


                viewHolder.tvProTwentyTwoDay.setEnabled(true);
                final EditText editTexTwentyTwoDay = viewHolder.tvProTwentyTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
                    editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
                }
                editTexTwentyTwoDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentyTwoDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyTwoDay.getText().toString();
                        spUtils.put(context, "configTwentyTwoDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentyTwoDay.addTextChangedListener(TvTwentyTwoDay);
                editTexTwentyTwoDay.setTag(TvTwentyTwoDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyTwoDay.setSelection(viewHolder.tvProTwentyTwoDay.length());


                viewHolder.tvProTwentyThreeDay.setEnabled(true);
                final EditText editTexTwentyThreeDay = viewHolder.tvProTwentyThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
                    editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
                }
                editTexTwentyThreeDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentyThreeDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyThreeDay.getText().toString();
                        spUtils.put(context, "configTwentyThreeDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentyThreeDay.addTextChangedListener(TvTwentyThreeDay);
                editTexTwentyThreeDay.setTag(TvTwentyThreeDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyThreeDay.setSelection(viewHolder.tvProTwentyThreeDay.length());


                viewHolder.tvProTwentyForeDay.setEnabled(true);
                final EditText editTexTwentyForeDay = viewHolder.tvProTwentyForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
                    editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
                }
                editTexTwentyForeDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentyForeDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyForeDay.getText().toString();
                        spUtils.put(context, "configTwentyForeDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentyForeDay.addTextChangedListener(TvTwentyForeDay);
                editTexTwentyForeDay.setTag(TvTwentyForeDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyForeDay.setSelection(viewHolder.tvProTwentyForeDay.length());


                viewHolder.tvProTwentyFiveDay.setEnabled(true);
                final EditText editTexTwentyFiveDay = viewHolder.tvProTwentyFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
                    editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
                }
                editTexTwentyFiveDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentyFiveDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyFiveDay.getText().toString();
                        spUtils.put(context, "configTwentyFiveDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentyFiveDay.addTextChangedListener(TvTwentyFiveDay);
                editTexTwentyFiveDay.setTag(TvTwentyFiveDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyFiveDay.setSelection(viewHolder.tvProTwentyFiveDay.length());


                viewHolder.tvProTwentySixDay.setEnabled(true);
                final EditText editTexTwentySixDay = viewHolder.tvProTwentySixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
                    editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
                }
                editTexTwentySixDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentySixDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentySixDay.getText().toString();
                        spUtils.put(context, "configTwentySixDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentySixDay.addTextChangedListener(TvTwentySixDay);
                editTexTwentySixDay.setTag(TvTwentySixDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentySixDay.setSelection(viewHolder.tvProTwentySixDay.length());


                viewHolder.tvProTwentySevenDay.setEnabled(true);
                final EditText editTexTwentySevenDay = viewHolder.tvProTwentySevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
                    editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
                }
                editTexTwentySevenDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentySevenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentySevenDay.getText().toString();
                        spUtils.put(context, "configTwentySevenDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentySevenDay.addTextChangedListener(TvTwentySevenDay);
                editTexTwentySevenDay.setTag(TvTwentySevenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentySevenDay.setSelection(viewHolder.tvProTwentySevenDay.length());


                viewHolder.tvProTwentyEightDay.setEnabled(true);
                final EditText editTexTwentyEightDay = viewHolder.tvProTwentyEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
                    editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
                }
                editTexTwentyEightDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentyEightDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyEightDay.getText().toString();
                        spUtils.put(context, "configTwentyEightDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentyEightDay.addTextChangedListener(TvTwentyEightDay);
                editTexTwentyEightDay.setTag(TvTwentyEightDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyEightDay.setSelection(viewHolder.tvProTwentyEightDay.length());


                viewHolder.tvProTwentyNineDay.setEnabled(true);
                final EditText editTexTwentyNineDay = viewHolder.tvProTwentyNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
                    editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
                }
                editTexTwentyNineDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvTwentyNineDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyNineDay.getText().toString();
                        spUtils.put(context, "configTwentyNineDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexTwentyNineDay.addTextChangedListener(TvTwentyNineDay);
                editTexTwentyNineDay.setTag(TvTwentyNineDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyNineDay.setSelection(viewHolder.tvProTwentyNineDay.length());


                viewHolder.tvProThirtyDay.setEnabled(true);
                final EditText editTexThirtyDay = viewHolder.tvProThirtyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirtyDay.getTag() instanceof TextWatcher) {
                    editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
                }
                editTexThirtyDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvThirtyDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProThirtyDay.getText().toString();
                        spUtils.put(context, "configThirtyDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexThirtyDay.addTextChangedListener(TvThirtyDay);
                editTexThirtyDay.setTag(TvThirtyDay);
            /*光标放置在文本最后*/
                viewHolder.tvProThirtyDay.setSelection(viewHolder.tvProThirtyDay.length());


                viewHolder.tvProThirtyOneDay.setEnabled(true);
                final EditText editTexThirtyOneDay = viewHolder.tvProThirtyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
                    editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
                }
                editTexThirtyOneDay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvThirtyOneDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProThirtyOneDay.getText().toString();
                        spUtils.put(context, "configThirtyOneDay", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexThirtyOneDay.addTextChangedListener(TvThirtyOneDay);
                editTexThirtyOneDay.setTag(TvThirtyOneDay);
            /*光标放置在文本最后*/
                viewHolder.tvProThirtyOneDay.setSelection(viewHolder.tvProThirtyOneDay.length());


                final EditText editTexRemarks = viewHolder.tvProRemarks;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexRemarks.getTag() instanceof TextWatcher) {
                    editTexRemarks.removeTextChangedListener((TextWatcher) editTexRemarks.getTag());
                }
                editTexRemarks.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
                TextWatcher TvRemarks = new TextWatcher() {
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
                        String proitem = viewHolder.tvProRemarks.getText().toString();
                        spUtils.put(context, "configRemarks", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTexRemarks.addTextChangedListener(TvRemarks);
                editTexRemarks.setTag(TvRemarks);
            /*光标放置在文本最后*/
                viewHolder.tvProRemarks.setSelection(viewHolder.tvProRemarks.length());


                viewHolder.tvProRecorder.setText(nameid);
                String configrecorder = viewHolder.tvProRecorder.getText().toString();
                spUtils.put(context, "configrecorder", configrecorder);


                viewHolder.tvProRecordat.setText(year + "/" + month + "/" + datetime);
                String configrecordat = viewHolder.tvProRecordat.getText().toString();
                spUtils.put(context, "configrecordat", configrecordat);

            } else {
                final EditText editTexOthers = viewHolder.tvProOthers;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexOthers.getTag() instanceof TextWatcher) {
                    editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
                }
                editTexOthers.setText(tvnewlyOthers);

                final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
//            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                    editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
                }
                viewHolder.tvProTaskNumber.setText(tvdate);

                final EditText editTexLastMonth = viewHolder.tvProCompletedLastMonth;
//            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexLastMonth.getTag() instanceof TextWatcher) {
                    editTexLastMonth.removeTextChangedListener((TextWatcher) editTexLastMonth.getTag());
                }
                viewHolder.tvProCompletedLastMonth.setText(tvnewlyCompletedLastMonth);

                final EditText editTexOneDay = viewHolder.tvProOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexOneDay.getTag() instanceof TextWatcher) {
                    editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
                }

                final EditText editTexTwoDay = viewHolder.tvProTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwoDay.getTag() instanceof TextWatcher) {
                    editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
                }

                final EditText editTexThreeDay = viewHolder.tvProThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThreeDay.getTag() instanceof TextWatcher) {
                    editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
                }

                final EditText editTexForeDay = viewHolder.tvProForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexForeDay.getTag() instanceof TextWatcher) {
                    editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
                }

                final EditText editTexFiveDay = viewHolder.tvProFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFiveDay.getTag() instanceof TextWatcher) {
                    editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
                }

            }
            return convertView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
