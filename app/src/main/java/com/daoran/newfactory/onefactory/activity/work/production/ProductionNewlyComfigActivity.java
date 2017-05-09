package com.daoran.newfactory.onefactory.activity.work.production;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ProductionAdapter;
import com.daoran.newfactory.onefactory.adapter.ProductionNewlyComfigAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.ProcationDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by lizhipeng on 2017/5/9.
 */

public class ProductionNewlyComfigActivity extends BaseFrangmentActivity
        implements View.OnClickListener {

    private NoscrollListView mData;
    private ProcationDialog procationDialog;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack, ivSearch;
    private List<ProducationDetailBean.DataBean> detailBeenList =
            new ArrayList<ProducationDetailBean.DataBean>();
    private ProducationDetailBean detailBean;

    private EditText etSqlDetail;
    private TextView tvSignPage;
    private Button btnSignPage, btnProSave, spinnermenu;
    private TextView spinnerNewbuild;
    private EditText etNewbuild;
    private MyAdatper comfigAdapter;

    private SharedPreferences sp;
    private SPUtils spUtils;
    private int pageCount;
    private int pageIndex = 0;
    private List<Map<String, Object>> mdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
        getViews();
        initViews();
        setDate();
        setListener();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        etSqlDetail = (EditText) findViewById(R.id.etSqlDetail);
        tvSignPage = (TextView) findViewById(R.id.tvSignPage);
        btnSignPage = (Button) findViewById(R.id.btnSignPage);
        btnProSave = (Button) findViewById(R.id.btnProSave);
        spinnermenu = (Button) findViewById(R.id.spinnermenu);
    }

    /**
     * 控件操作
     */
    private void initViews() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
        etSqlDetail.setSelection(etSqlDetail.getText().length());

        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mdate = getData();
        comfigAdapter = new MyAdatper(this);
        mData.setAdapter(comfigAdapter);
//        comfigAdapter.notifyDataSetChanged();
    }

    /**
     * 点击事件
     */
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);
        btnProSave.setOnClickListener(this);
        spinnermenu.setOnClickListener(this);
    }

    /**
     * 查询数据且操作数据
     */
    private void setDate() {

    }

    private List<Map<String, Object>> getData() {
        sp = getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
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
            case R.id.ivSearch:
                ShowDialog(v);
                break;
            case R.id.btnSignPage:
                String txt = etSqlDetail.getText().toString();
                String txtcount = tvSignPage.getText().toString();
                if (txt.length() == 0) {
                    ToastUtils.ShowToastMessage("页码不能为空", ProductionNewlyComfigActivity.this);
                    return;
                } else if (txt.length() > txtcount.length()) {
                    ToastUtils.ShowToastMessage("页码超出输入范围", ProductionNewlyComfigActivity.this);
                } else {
//                    setPageDetail();
                }
                break;
            case R.id.spinnermenu:
                showPopupMenu(spinnermenu);
                break;
            case R.id.btnProSave:
//                setSave();
                break;
        }
    }

    /**
     * 弹出输入框
     *
     * @param view
     */
    private void ShowDialog(View view) {
        procationDialog = new ProcationDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        procationDialog.show();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
//                    setPageDetail();
                    procationDialog.dismiss();
                    break;
            }
        }
    };

    private View.OnClickListener onCancleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCancle:
                    procationDialog.dismiss();
                    break;
            }
        }
    };

    /**
     * 弹出选择菜单
     *
     * @param view
     */
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pro, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                switch (title) {
                    case "新建":
                        startActivity(new Intent(ProductionNewlyComfigActivity.this,
                                ProductionNewlyBuildActivity.class));
                        break;
                    case "复制":

                        break;
                    case "刷新":
//                        setData();
                        break;
                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });

        popupMenu.show();
    }

    private void setSave() {
        String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
        sp = this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String proid = sp.getString("proid", "");
        String salesid = sp.getString("salesid", "");
        String proColumnTitle = sp.getString("proColumnTitle", "");//部门
        if (proColumnTitle == "" || proColumnTitle.equals("")) {
            proColumnTitle = null;
            System.out.print(proColumnTitle);
        }
        String proProcedureTitle = sp.getString("proProcedureTitle", "");//工序
        if (proProcedureTitle == "" || proProcedureTitle.equals("")) {
            proProcedureTitle = null;
        }
        String proPrdstatusTitle = sp.getString("proadapterPrdstatusTitle", "");//状态//
        if (proPrdstatusTitle == "" || proPrdstatusTitle.equals("")) {
            proPrdstatusTitle = null;
        }
        String productionItem = sp.getString("productionItem", "");//款号
        if (productionItem == "" || productionItem.equals("")) {
            productionItem = null;
        }
        String productionDocumentary = sp.getString("productionadapterDocumentary", "");//跟单//
        if (productionDocumentary == "" || productionDocumentary.equals("")) {
            productionDocumentary = null;
        }
        String productionFactory = sp.getString("productionFactory", "");//工厂
        if (productionFactory == "" || productionFactory.equals("")) {
            productionFactory = null;
        }
        String productionOthers = sp.getString("productionOthers", "");//组别人
        if (productionOthers == "" || productionOthers.equals("")) {
            productionOthers = null;
        }
        String productionSingularSystem = sp.getString("productionadapterSingularSystem", "");//制单数//
        if (productionSingularSystem == "" || productionSingularSystem.equals("")) {
            productionSingularSystem = null;
        }
        String productionColor = sp.getString("productionColor", "");//花色
        if (productionColor == "" || productionColor.equals("")) {
            productionColor = null;
        }
        String productionTaskNumber = sp.getString("productionTaskNumber", "");//任务数
        if (productionTaskNumber == "" || productionTaskNumber.equals("")) {
            productionTaskNumber = null;
        }
        String productionSize = sp.getString("productionSize", "");//尺码
        if (productionSize == "" || productionSize.equals("")) {
            productionSize = null;
        }
        String productionClippingNumber = sp.getString("productionClippingNumber", "");//实裁数
        if (productionClippingNumber == "" || productionClippingNumber.equals("")) {
            productionClippingNumber = null;
        }
        String productionCompletedLastMonth = sp.getString("productionCompletedLastMonth", "");//上月完工
        if (productionCompletedLastMonth == "" || productionCompletedLastMonth.equals("")) {
            productionCompletedLastMonth = null;
        }
        String productionTotalCompletion = sp.getString("productionTotalCompletion", "");//总完工数
        if (productionTotalCompletion == "" || productionTotalCompletion.equals("")) {
            productionTotalCompletion = null;
        }
        String productionBalanceAmount = sp.getString("productionBalanceAmount", "");//结余数量
        if (productionBalanceAmount == "" || productionBalanceAmount.equals("")) {
            productionBalanceAmount = null;
        }
        String productionYear = sp.getString("productionYear", "");//年
        if (productionYear == "" || productionYear.equals("")) {
            productionYear = null;
        }
        String productionMonth = sp.getString("proadapterMonthTitle", "");//月
        if (productionMonth == "" || productionMonth.equals("")) {
            productionMonth = null;
        }
        String productionOneDay = sp.getString("productionOneDay", "");//1
        if (productionOneDay == "" || productionOneDay.equals("")) {
            productionOneDay = null;
        }
        String productionTwoDay = sp.getString("productionTwoDay", "");//2
        if (productionTwoDay == "" || productionTwoDay.equals("")) {
            productionTwoDay = null;
        }
        String productionThreeDay = sp.getString("productionThreeDay", "");//3
        if (productionThreeDay == "" || productionThreeDay.equals("")) {
            productionThreeDay = null;
        }
        String productionForeDay = sp.getString("productionForeDay", "");//4
        if (productionForeDay == "" || productionForeDay.equals("")) {
            productionForeDay = null;
        }
        String productionFiveDay = sp.getString("productionFiveDay", "");//5
        if (productionFiveDay == "" || productionFiveDay.equals("")) {
            productionFiveDay = null;
        }
        String productionSixDay = sp.getString("productionSixDay", "");//6
        if (productionSixDay == "" || productionSixDay.equals("")) {
            productionSixDay = null;
        }
        String productionSevenDay = sp.getString("productionSevenDay", "");//7
        if (productionSevenDay == "" || productionSevenDay.equals("")) {
            productionSevenDay = null;
        }
        String productionEightDay = sp.getString("productionEightDay", "");//8
        if (productionEightDay == "" || productionEightDay.equals("")) {
            productionEightDay = null;
        }
        String productionNineDay = sp.getString("productionNineDay", "");//9
        if (productionNineDay == "" || productionNineDay.equals("")) {
            productionNineDay = null;
        }
        String productionTenDay = sp.getString("productionTenDay", "");//10
        if (productionTenDay == "" || productionTenDay.equals("")) {
            productionTenDay = null;
        }
        String productionElevenDay = sp.getString("productionElevenDay", "");//11
        if (productionElevenDay == "" || productionElevenDay.equals("")) {
            productionElevenDay = null;
        }
        String productionTwelveDay = sp.getString("productionTwelveDay", "");//12
        if (productionTwelveDay == "" || productionTwelveDay.equals("")) {
            productionTwelveDay = null;
        }
        String productionThirteenDay = sp.getString("productionThirteenDay", "");//13
        if (productionThirteenDay == "" || productionThirteenDay.equals("")) {
            productionThirteenDay = null;
        }
        String productionFourteenDay = sp.getString("productionFourteenDay", "");//14
        if (productionFourteenDay == "" || productionFourteenDay.equals("")) {
            productionFourteenDay = null;
        }
        String productionFifteenDay = sp.getString("productionFifteenDay", "");//15
        if (productionFifteenDay == "" || productionFifteenDay.equals("")) {
            productionFifteenDay = null;
        }
        String productionSixteenDay = sp.getString("productionSixteenDay", "");//16
        if (productionSixteenDay == "" || productionSixteenDay.equals("")) {
            productionSixteenDay = null;
        }
        String productionSeventeenDay = sp.getString("productionSeventeenDay", "");//17
        if (productionSeventeenDay == "" || productionSeventeenDay.equals("")) {
            productionSeventeenDay = null;
        }
        String productionEighteenDay = sp.getString("productionEighteenDay", "");//18
        if (productionEighteenDay == "" || productionEighteenDay.equals("")) {
            productionEighteenDay = null;
        }
        String productionNineteenDay = sp.getString("productionNineteenDay", "");//19
        if (productionNineteenDay == "" || productionNineteenDay.equals("")) {
            productionNineteenDay = null;
        }
        String productionTwentyDay = sp.getString("productionTwentyDay", "");//20
        if (productionTwentyDay == "" || productionTwentyDay.equals("")) {
            productionTwentyDay = null;
        }
        String productionTwentyOneDay = sp.getString("productionTwentyOneDay", "");//21
        if (productionTwentyOneDay == "" || productionTwentyOneDay.equals("")) {
            productionTwentyOneDay = null;
        }
        String productionTwentyTwoDay = sp.getString("productionTwentyTwoDay", "");//22
        if (productionTwentyTwoDay == "" || productionTwentyTwoDay.equals("")) {
            productionTwentyTwoDay = null;
        }
        String productionTwentyThreeDay = sp.getString("productionTwentyThreeDay", "");//23
        if (productionTwentyThreeDay == "" || productionTwentyThreeDay.equals("")) {
            productionTwentyThreeDay = null;
        }
        String productionTwentyForeDay = sp.getString("productionTwentyForeDay", "");//24
        if (productionTwentyForeDay == "" || productionTwentyForeDay.equals("")) {
            productionTwentyForeDay = null;
        }
        String productionTwentyFiveDay = sp.getString("productionTwentyFiveDay", "");//25
        if (productionTwentyFiveDay == "" || productionTwentyFiveDay.equals("")) {
            productionTwentyFiveDay = null;
        }
        String productionTwentySixDay = sp.getString("productionTwentySixDay", "");//26
        if (productionTwentySixDay == "" || productionTwentySixDay.equals("")) {
            productionTwentySixDay = null;
        }
        String productionTwentySevenDay = sp.getString("productionTwentySevenDay", "");//27
        if (productionTwentySevenDay == "" || productionTwentySevenDay.equals("")) {
            productionTwentySevenDay = null;
        }
        String productionTwentyEightDay = sp.getString("productionTwentyEightDay", "");//28
        if (productionTwentyEightDay == "" || productionTwentyEightDay.equals("")) {
            productionTwentyEightDay = null;
        }
        String productionTwentyNineDay = sp.getString("productionTwentyNineDay", "");//29
        if (productionTwentyNineDay == "" || productionTwentyNineDay.equals("")) {
            productionTwentyNineDay = null;
        }
        String productionThirtyDay = sp.getString("productionThirtyDay", "");//30
        if (productionThirtyDay == "" || productionThirtyDay.equals("")) {
            productionThirtyDay = null;
        }
        String productionThirtyOneDay = sp.getString("productionThirtyOneDay", "");//31
        if (productionThirtyOneDay == "" || productionThirtyOneDay.equals("")) {
            productionThirtyOneDay = null;
        }
        String productionRemarks = sp.getString("productionRemarks", "");//备注
        if (productionRemarks == "" || productionRemarks.equals("")) {
            productionRemarks = null;
        }
        String productionRecorder = sp.getString("productionRecorder", "");//制单人
        if (productionRecorder == "" || productionRecorder.equals("")) {
            productionRecorder = null;
        }
        String productionRecordat = sp.getString("productionRecordat", "");//制单时间
        if (productionRecordat == "" || productionRecordat.equals("")) {
            productionRecordat = null;
        }
        Gson gson = new Gson();
        ProducationDetailBean.DataBean dataBean = new ProducationDetailBean.DataBean();
        dataBean.setID(Integer.parseInt(proid));
        dataBean.setSalesid(Integer.parseInt(salesid));
        dataBean.setItem(productionItem);
        dataBean.setPrddocumentary(productionDocumentary);
        dataBean.setSubfactory(productionFactory);
        dataBean.setSubfactoryTeams(proColumnTitle);
        dataBean.setWorkingProcedure(proProcedureTitle);
        dataBean.setWorkers(productionOthers);
        dataBean.setPqty(productionSingularSystem);
        dataBean.setProdcol(productionColor);
        dataBean.setTaskqty(productionTaskNumber);
        dataBean.setMdl(productionSize);
        dataBean.setFactcutqty(productionClippingNumber);
        dataBean.setLastMonQty(productionCompletedLastMonth);
        dataBean.setSumCompletedQty(productionTotalCompletion);
        dataBean.String(productionBalanceAmount);
        dataBean.setPrdstatus(proPrdstatusTitle);
        dataBean.setYear(productionYear);
        dataBean.setMonth(productionMonth);
        dataBean.setDay1(productionOneDay);
        dataBean.setDay2(productionTwoDay);
        dataBean.setDay3(productionThreeDay);
        dataBean.setDay4(productionForeDay);
        dataBean.setDay5(productionFiveDay);
        dataBean.setDay6(productionSixDay);
        dataBean.setDay7(productionSevenDay);
        dataBean.setDay8(productionEightDay);
        dataBean.setDay9(productionNineDay);
        dataBean.setDay10(productionTenDay);
        dataBean.setDay11(productionElevenDay);
        dataBean.setDay12(productionTwelveDay);
        dataBean.setDay13(productionThirteenDay);
        dataBean.setDay14(productionFourteenDay);
        dataBean.setDay15(productionFifteenDay);
        dataBean.setDay16(productionSixteenDay);
        dataBean.setDay17(productionSeventeenDay);
        dataBean.setDay18(productionEighteenDay);
        dataBean.setDay19(productionNineteenDay);
        dataBean.setDay20(productionTwentyDay);
        dataBean.setDay21(productionTwentyOneDay);
        dataBean.setDay22(productionTwentyTwoDay);
        dataBean.setDay23(productionTwentyThreeDay);
        dataBean.setDay24(productionTwentyForeDay);
        dataBean.setDay25(productionTwentyFiveDay);
        dataBean.setDay26(productionTwentySixDay);
        dataBean.setDay27(productionTwentySevenDay);
        dataBean.setDay28(productionTwentyEightDay);
        dataBean.setDay29(productionTwentyNineDay);
        dataBean.setDay30(productionThirtyDay);
        dataBean.setDay31(productionThirtyOneDay);
        dataBean.setMemo(productionRemarks);
        dataBean.setRecorder(productionRecorder);
        dataBean.setRecordat(productionRecordat);
        detailBeenList.add(dataBean);
        String detailb = gson.toJson(detailBeenList);
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils.postString().
                    url(saveurl)
                    .content(detailb)
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
                            int resindex = Integer.parseInt(response);
                            if (resindex > 3) {
                                ToastUtils.ShowToastMessage("保存成功，请刷新页面", ProductionNewlyComfigActivity.this);
                            } else if (ression == "3" || ression.equals("3")) {
                                ToastUtils.ShowToastMessage("保存失败", ProductionNewlyComfigActivity.this);
                            } else if (ression == "4" || ression.equals("4")) {
                                ToastUtils.ShowToastMessage("数据错误，请重试", ProductionNewlyComfigActivity.this);
                            } else {
                                ToastUtils.ShowToastMessage("未知错误，请联系管理员", ProductionNewlyComfigActivity.this);
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

    public class MyAdatper extends BaseAdapter {
        private LayoutInflater layoutInflater;

        public MyAdatper(Context context) {
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mdate.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.item_production_data, null);
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

            String tvnewlyDocumen = String.valueOf(mdate.get(position).get("tvnewlyDocumentary"));
            viewHolder.tvProDocumentary.setText(tvnewlyDocumen);

            String tvnewlyFactory = String.valueOf(mdate.get(position).get("tvnewlyFactory"));
            viewHolder.tvProFactory.setText(tvnewlyFactory);

            String tvnewlyDepartment = String.valueOf(mdate.get(position).get("tvnewlyDepartment"));
            viewHolder.tvProDepartment.setText(tvnewlyDepartment);

            String tvnewlyProcedure = String.valueOf(mdate.get(position).get("tvnewlyProcedure"));
            viewHolder.tvProProcedure.setText(tvnewlyProcedure);

            String tvnewlyOthers = String.valueOf(mdate.get(position).get("tvnewlyOthers"));
            viewHolder.tvProOthers.setText(tvnewlyOthers);

            String tvnewSingularSystem = String.valueOf(mdate.get(position).get("tvnewSingularSystem"));
            viewHolder.tvProSingularSystem.setText(tvnewSingularSystem);

            String tvdate = String.valueOf(mdate.get(position).get("tvColorTaskqty"));
            viewHolder.tvProTaskNumber.setText(tvdate);

            String tvnewTaskNumber = String.valueOf(mdate.get(position).get("tvnewTaskNumber"));
            viewHolder.tvProSize.setText(tvnewTaskNumber);

            String tvnewlySize = String.valueOf(mdate.get(position).get("tvnewlySize"));
            viewHolder.tvProColor.setText(tvnewlySize);

            String tvnewlyClippingNumber = String.valueOf(mdate.get(position).get("tvnewlyClippingNumber"));
            viewHolder.tvProClippingNumber.setText(tvnewlyClippingNumber);

            String tvnewlyCompletedLastMonth = String.valueOf(mdate.get(position).get("tvnewlyCompletedLastMonth"));
            viewHolder.tvProCompletedLastMonth.setText(tvnewlyCompletedLastMonth);

            String tvnewlyTotalCompletion = String.valueOf(mdate.get(position).get("tvnewlyTotalCompletion"));
            viewHolder.tvProTotalCompletion.setText(tvnewlyTotalCompletion);

            sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
            String nameid = sp.getString("username", "");
            viewHolder.tvProRecorder.setText(nameid);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss     ");
            Date date = new Date(System.currentTimeMillis());
            String str = formatter.format(date);
            System.out.print(str);
            viewHolder.tvProRecordat.setText(str);
            return convertView;
        }
    }
}
