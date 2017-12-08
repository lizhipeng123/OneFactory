package com.daoran.newfactory.onefactory.adapter.ftydladapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.ftydl.FTYDLSearchDetailActivity;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDailyBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.util.List;

/**
 * 生产日报主列表适配
 * Created by lizhipeng on 2017/4/26.
 */

public class FTYDLSearchAdapter extends BaseAdapter {
    private Context context;
    private List<FTYDLDailyBean.DataBean> dataBeen;
    private SPUtils spUtils;
    private int isprodure;

    public FTYDLSearchAdapter(Context context, List<FTYDLDailyBean.DataBean> dataBeen
    ) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public FTYDLDailyBean.DataBean getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*填充item*/
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_production_data, null);
            viewHolder.lin_content = (LinearLayout) convertView.findViewById(R.id.data_ll_vertical);
            viewHolder.tvProDocumentary = (TextView) convertView.findViewById(R.id.tvProDocumentary);
            viewHolder.tvProFactory = (TextView) convertView.findViewById(R.id.tvProFactory);
            viewHolder.tvProDepartment = (TextView) convertView.findViewById(R.id.tvProDepartment);
            viewHolder.tvProProcedure = (TextView) convertView.findViewById(R.id.tvProProcedure);
            viewHolder.tvProOthers = (TextView) convertView.findViewById(R.id.tvProOthers);
            viewHolder.tvProSingularSystem = (TextView) convertView.findViewById(R.id.tvProSingularSystem);
            viewHolder.tvProColor = (TextView) convertView.findViewById(R.id.tvProColor);
            viewHolder.tvProTaskNumber = (TextView) convertView.findViewById(R.id.tvProTaskNumber);
            viewHolder.tvProSize = (TextView) convertView.findViewById(R.id.tvProSize);
            viewHolder.tvProClippingNumber = (TextView) convertView.findViewById(R.id.tvProClippingNumber);
            viewHolder.tvProCompletedLastMonth = (TextView) convertView.findViewById(R.id.tvProCompletedLastMonth);
            viewHolder.tvProTotalCompletion = (TextView) convertView.findViewById(R.id.tvProTotalCompletion);
            viewHolder.tvProBalanceAmount = (TextView) convertView.findViewById(R.id.tvProBalanceAmount);
            viewHolder.tvProState = (TextView) convertView.findViewById(R.id.tvProState);
            viewHolder.tvProYear = (TextView) convertView.findViewById(R.id.tvProYear);
            viewHolder.tvProMonth = (TextView) convertView.findViewById(R.id.tvProMonth);
            viewHolder.tvProOneDay = (TextView) convertView.findViewById(R.id.tvProOneDay);
            viewHolder.tvProTwoDay = (TextView) convertView.findViewById(R.id.tvProTwoDay);
            viewHolder.tvProThreeDay = (TextView) convertView.findViewById(R.id.tvProThreeDay);
            viewHolder.tvProForeDay = (TextView) convertView.findViewById(R.id.tvProForeDay);
            viewHolder.tvProFiveDay = (TextView) convertView.findViewById(R.id.tvProFiveDay);
            viewHolder.tvProSixDay = (TextView) convertView.findViewById(R.id.tvProSixDay);
            viewHolder.tvProSevenDay = (TextView) convertView.findViewById(R.id.tvProSevenDay);
            viewHolder.tvProEightDay = (TextView) convertView.findViewById(R.id.tvProEightDay);
            viewHolder.tvProNineDay = (TextView) convertView.findViewById(R.id.tvProNineDay);
            viewHolder.tvProTenDay = (TextView) convertView.findViewById(R.id.tvProTenDay);
            viewHolder.tvProElevenDay = (TextView) convertView.findViewById(R.id.tvProElevenDay);
            viewHolder.tvProTwelveDay = (TextView) convertView.findViewById(R.id.tvProTwelveDay);
            viewHolder.tvProThirteenDay = (TextView) convertView.findViewById(R.id.tvProThirteenDay);
            viewHolder.tvProFourteenDay = (TextView) convertView.findViewById(R.id.tvProFourteenDay);
            viewHolder.tvProFifteenDay = (TextView) convertView.findViewById(R.id.tvProFifteenDay);
            viewHolder.tvProSixteenDay = (TextView) convertView.findViewById(R.id.tvProSixteenDay);
            viewHolder.tvProSeventeenDay = (TextView) convertView.findViewById(R.id.tvProSeventeenDay);
            viewHolder.tvProEighteenDay = (TextView) convertView.findViewById(R.id.tvProEighteenDay);
            viewHolder.tvProNineteenDay = (TextView) convertView.findViewById(R.id.tvProNineteenDay);
            viewHolder.tvProTwentyDay = (TextView) convertView.findViewById(R.id.tvProTwentyDay);
            viewHolder.tvProTwentyOneDay = (TextView) convertView.findViewById(R.id.tvProTwentyOneDay);
            viewHolder.tvProTwentyTwoDay = (TextView) convertView.findViewById(R.id.tvProTwentyTwoDay);
            viewHolder.tvProTwentyThreeDay = (TextView) convertView.findViewById(R.id.tvProTwentyThreeDay);
            viewHolder.tvProTwentyForeDay = (TextView) convertView.findViewById(R.id.tvProTwentyForeDay);
            viewHolder.tvProTwentyFiveDay = (TextView) convertView.findViewById(R.id.tvProTwentyFiveDay);
            viewHolder.tvProTwentySixDay = (TextView) convertView.findViewById(R.id.tvProTwentySixDay);
            viewHolder.tvProTwentySevenDay = (TextView) convertView.findViewById(R.id.tvProTwentySevenDay);
            viewHolder.tvProTwentyEightDay = (TextView) convertView.findViewById(R.id.tvProTwentyEightDay);
            viewHolder.tvProTwentyNineDay = (TextView) convertView.findViewById(R.id.tvProTwentyNineDay);
            viewHolder.tvProThirtyDay = (TextView) convertView.findViewById(R.id.tvProThirtyDay);
            viewHolder.tvProThirtyOneDay = (TextView) convertView.findViewById(R.id.tvProThirtyOneDay);
            viewHolder.tvProRemarks = (TextView) convertView.findViewById(R.id.tvProRemarks);
            viewHolder.tvProRecorder = (TextView) convertView.findViewById(R.id.tvProRecorder);
            viewHolder.tvProRecordat = (TextView) convertView.findViewById(R.id.tvProRecordat);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
         /*判断item中制单人是否是登录用户，是为可改，否为不可改*/
        final TextView tvProDocumentary = viewHolder.tvProDocumentary;
        final TextView tvProFactory = viewHolder.tvProFactory;
        final TextView tvProDepartment = viewHolder.tvProDepartment;
        final TextView tvProProcedure = viewHolder.tvProProcedure;
        final TextView editTexOthers = viewHolder.tvProOthers;
        final TextView tvProSingularSystem = viewHolder.tvProSingularSystem;
        final TextView tvProColor = viewHolder.tvProColor;
        final TextView editTexTaskNumber = viewHolder.tvProTaskNumber;
        final TextView tvProSize = viewHolder.tvProSize;
        final TextView tvProClippingNumber = viewHolder.tvProClippingNumber;
        final TextView editTexCompletedLastMonth = viewHolder.tvProCompletedLastMonth;
        final TextView tvProTotalCompletion = viewHolder.tvProTotalCompletion;
        final TextView tvProBalanceAmount = viewHolder.tvProBalanceAmount;
        final TextView tvProState = viewHolder.tvProState;
        final TextView tvProYear = viewHolder.tvProYear;
        final TextView tvProMonth = viewHolder.tvProMonth;
        final TextView editTexOneDay = viewHolder.tvProOneDay;
        final TextView editTexTwoDay = viewHolder.tvProTwoDay;
        final TextView editTexThreeDay = viewHolder.tvProThreeDay;
        final TextView editTexForeDay = viewHolder.tvProForeDay;
        final TextView editTexFiveDay = viewHolder.tvProFiveDay;
        final TextView editTexSixDay = viewHolder.tvProSixDay;
        final TextView editTexSevenDay = viewHolder.tvProSevenDay;
        final TextView editTexEightDay = viewHolder.tvProEightDay;
        final TextView editTexNineDay = viewHolder.tvProNineDay;
        final TextView editTexTenDay = viewHolder.tvProTenDay;
        final TextView editTexElevenDay = viewHolder.tvProElevenDay;
        final TextView editTexTwelveDay = viewHolder.tvProTwelveDay;
        final TextView editTexThirteenDay = viewHolder.tvProThirteenDay;
        final TextView editTexFourteenDay = viewHolder.tvProFourteenDay;
        final TextView editTexFifteenDay = viewHolder.tvProFifteenDay;
        final TextView editTexSixteenDay = viewHolder.tvProSixteenDay;
        final TextView editTexSeventeenDay = viewHolder.tvProSeventeenDay;
        final TextView editTexEighteenDay = viewHolder.tvProEighteenDay;
        final TextView editTexNineteenDay = viewHolder.tvProNineteenDay;
        final TextView editTexTwentyDay = viewHolder.tvProTwentyDay;
        final TextView editTexTwentyOneDay = viewHolder.tvProTwentyOneDay;
        final TextView editTexTwentyTwoDay = viewHolder.tvProTwentyTwoDay;
        final TextView editTexTwentyThreeDay = viewHolder.tvProTwentyThreeDay;
        final TextView editTexTwentyForeDay = viewHolder.tvProTwentyForeDay;
        final TextView editTexTwentyFiveDay = viewHolder.tvProTwentyFiveDay;
        final TextView editTexTwentySixDay = viewHolder.tvProTwentySixDay;
        final TextView editTexTwentySevenDay = viewHolder.tvProTwentySevenDay;
        final TextView editTexTwentyEightDay = viewHolder.tvProTwentyEightDay;
        final TextView editTexTwentyNineDay = viewHolder.tvProTwentyNineDay;
        final TextView editTexThirtyDay = viewHolder.tvProThirtyDay;
        final TextView editTexThirtyOneDay = viewHolder.tvProThirtyOneDay;
        final TextView editTexRemarks = viewHolder.tvProRemarks;
        final TextView tvProRecorder = viewHolder.tvProRecorder;
        final TextView tvProRecordat = viewHolder.tvProRecordat;

        tvProDocumentary.setText(getItem(position).getPrddocumentary());
        tvProFactory.setText(getItem(position).getSubfactory());
        tvProDepartment.setText(getItem(position).getSubfactoryTeams());
        tvProProcedure.setText(getItem(position).getWorkingProcedure());
        editTexOthers.setText(getItem(position).getWorkers());
        tvProSingularSystem.setText(String.valueOf(getItem(position).getPqty()));
        tvProColor.setText(getItem(position).getProdcol());
        editTexTaskNumber.setText(String.valueOf(getItem(position).getTaskqty()));
        tvProSize.setText(getItem(position).getMdl());
        tvProClippingNumber.setText(String.valueOf(getItem(position).getFactcutqty()));
        editTexCompletedLastMonth.setText(String.valueOf(getItem(position).getLastMonQty()));
        tvProTotalCompletion.setText(String.valueOf(getItem(position).getSumCompletedQty()));
        tvProBalanceAmount.setText(String.valueOf(getItem(position).getLeftQty()));
        tvProState.setText(getItem(position).getPrdstatus());
        tvProYear.setText(String.valueOf(getItem(position).getYear()));
        tvProMonth.setText(String.valueOf(getItem(position).getMonth()));
        editTexOneDay.setText(getItem(position).getDay1());
        editTexTwoDay.setText(getItem(position).getDay2());
        editTexThreeDay.setText(getItem(position).getDay3());
        editTexForeDay.setText(getItem(position).getDay4());
        editTexFiveDay.setText(getItem(position).getDay5());
        editTexSixDay.setText(getItem(position).getDay6());
        editTexSevenDay.setText(getItem(position).getDay7());
        editTexEightDay.setText(getItem(position).getDay8());
        editTexNineDay.setText(getItem(position).getDay9());
        editTexTenDay.setText(getItem(position).getDay10());
        editTexElevenDay.setText(getItem(position).getDay11());
        editTexTwelveDay.setText(getItem(position).getDay12());
        editTexThirteenDay.setText(getItem(position).getDay13());
        editTexFourteenDay.setText(getItem(position).getDay14());
        editTexFifteenDay.setText(getItem(position).getDay15());
        editTexSixteenDay.setText(getItem(position).getDay16());
        editTexSeventeenDay.setText(getItem(position).getDay17());
        editTexEighteenDay.setText(getItem(position).getDay18());
        editTexNineteenDay.setText(getItem(position).getDay19());
        editTexTwentyDay.setText(getItem(position).getDay20());
        editTexTwentyOneDay.setText(getItem(position).getDay21());
        editTexTwentyTwoDay.setText(getItem(position).getDay22());
        editTexTwentyThreeDay.setText(getItem(position).getDay23());
        editTexTwentyForeDay.setText(getItem(position).getDay24());
        editTexTwentyFiveDay.setText(getItem(position).getDay25());
        editTexTwentySixDay.setText(getItem(position).getDay26());
        editTexTwentySevenDay.setText(getItem(position).getDay27());
        editTexTwentyEightDay.setText(getItem(position).getDay28());
        editTexTwentyNineDay.setText(getItem(position).getDay29());
        editTexThirtyDay.setText(getItem(position).getDay30());
        editTexThirtyOneDay.setText(getItem(position).getDay31());
        editTexRemarks.setText(getItem(position).getMemo());
        tvProRecorder.setText(getItem(position).getRecorder());
        tvProRecordat.setText(getItem(position).getRecordat());

        /*点击修改*/
        viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salesid = String.valueOf(getItem(position).getID());
                String said = String.valueOf(getItem(position).getSalesid());
                spUtils.put(context, "tvFTYDLDetailID", salesid);
                spUtils.put(context, "tvFTYDLDetailSalesID", said);
                String planid = getItem(position).getPlanid();//引用的工厂计划id
                if (planid == null) {
                    planid = "";
                }
                spUtils.put(context, "tvFTYDLDetailPlanId", planid);

                String sn = String.valueOf(getItem(position).getSn());//序列号
                if (sn == null) {
                    sn = "";
                }
                spUtils.put(context, "tvFTYDLDetailSn", sn);

                String contractno = getItem(position).getContractno();//销售合同号
                if (contractno == null) {
                    contractno = "";
                }
                spUtils.put(context, "tvFTYDLDetailContractno", contractno);

                String inbill = getItem(position).getInbill();//内部id
                if (inbill == null) {
                    inbill = "";
                }
                spUtils.put(context, "tvFTYDLDetailInbill", inbill);

                String area = getItem(position).getArea();//片区号
                if (area == null) {
                    area = "";
                }
                spUtils.put(context, "tvFTYDLDetailArea", area);

                String companytxt = getItem(position).getCompanytxt();//公司名称
                if (companytxt == null) {
                    companytxt = "";
                }
                spUtils.put(context, "tvFTYDLDetailCompanytxt", companytxt);

                String po = getItem(position).getPo();//po
                if (po == null) {
                    po = "";
                }
                spUtils.put(context, "tvFTYDLDetailPo", po);

                String tvdate = getItem(position).getItem();
                if (tvdate == null) {
                    tvdate = "";
                }
                spUtils.put(context, "tvFTYDLDetailItem", tvdate);//款号

                String oitem = getItem(position).getOitem();//原款号
                if (oitem == null) {
                    oitem = "";
                }
                spUtils.put(context, "tvFTYDLDetailOitem", oitem);

                String tvProMdl = getItem(position).getMdl();
                if (tvProMdl == null) {
                    tvProMdl = "";
                }
                spUtils.put(context, "tvFTYDLDetailMdl", tvProMdl);//尺码

                String ctmid = getItem(position).getCtmid();//客户id
                if (ctmid == null) {
                    ctmid = "";
                }
                spUtils.put(context, "tvFTYDLDetailCtmid", ctmid);

                String tvctmtxt = getItem(position).getCtmtxt();
                if (tvctmtxt == null) {
                    tvctmtxt = "";
                }
                spUtils.put(context, "tvFTYDLDetailCtmtxt", tvctmtxt);//客户

                String ctmcompanytxt = getItem(position).getCtmcompanytxt();//客户归属公司
                if (ctmcompanytxt == null) {
                    ctmcompanytxt = "";
                }
                spUtils.put(context, "tvFTYDLDetailCtmcompanytxt", ctmcompanytxt);

                String prdtyp = getItem(position).getPrdtyp();//产品大类
                if (prdtyp == null) {
                    prdtyp = "";
                }
                spUtils.put(context, "tvFTYDLDetailPrdtyp", prdtyp);

                String lcdat = getItem(position).getLcdat();//计划离厂日期
                if (lcdat == null) {
                    lcdat = "";
                }
                spUtils.put(context, "tvFTYDLDetailLcdat", lcdat);

                String lbdat = getItem(position).getLbdat();//计划离岸日期
                if (lbdat == null) {
                    lbdat = "";
                }
                spUtils.put(context, "tvFTYDLDetailLbdat", lbdat);

                String styp = getItem(position).getStyp();//po类型(分类)
                if (styp == null) {
                    styp = "";
                }
                spUtils.put(context, "tvFTYDLDetailStyp", styp);

                String fsaler = getItem(position).getFsaler();//外贸业务员
                if (fsaler == null) {
                    fsaler = "";
                }
                spUtils.put(context, "tvFTYDLDetailFsaler", fsaler);

                String psaler = getItem(position).getPsaler();//生产业务员
                if (psaler == null) {
                    psaler = "";
                }
                spUtils.put(context, "tvFTYDLDetailPsaler", psaler);

                String memo = getItem(position).getMemo();//备注
                if (memo == null) {
                    memo = "";
                }
                spUtils.put(context, "tvFTYDLDetailMemo", memo);

                String tvProPqty = String.valueOf(getItem(position).getPqty());
                if (tvProPqty == null) {
                    tvProPqty = "";
                }
                spUtils.put(context, "tvFTYDLDetailPqty", tvProPqty);//制单数

                String unit = getItem(position).getUnit();//单位
                if (unit == null) {
                    unit = "";
                }
                spUtils.put(context, "tvFTYDLDetailUnit", unit);

                String tvProdcol = getItem(position).getProdcol();
                if (tvProdcol == null) {
                    tvProdcol = "";
                }
                spUtils.put(context, "tvFTYDLDetailProdcol", tvProdcol);//花色

                String megitem = getItem(position).getMegitem();//合并款号
                if (megitem == null) {
                    megitem = "";
                }
                spUtils.put(context, "tvFTYDLDetailMegitem", megitem);

                String teamname = getItem(position).getTeamname();//外贸组别
                if (teamname == null) {
                    teamname = "";
                }
                spUtils.put(context, "tvFTYDLDetailTeamname", teamname);

                String recordat = getItem(position).getRecordat();//制单时间
                if (recordat == null) {
                    recordat = "";
                }
                spUtils.put(context, "tvFTYDLDetailRecordat", recordat);

                String recordid = getItem(position).getRecordid();//制单人id
                if (recordid == null) {
                    recordid = "";
                }
                spUtils.put(context, "tvFTYDLDetailRecordid", recordid);

                String recorder = getItem(position).getRecorder();//制单人
                if (recorder == null) {
                    recorder = "";
                }
                spUtils.put(context, "tvFTYDLDetailRecorder", recorder);

                String tvProFactory = getItem(position).getSubfactory();
                if (tvProFactory == null) {
                    tvProFactory = "";
                }
                spUtils.put(context, "tvFTYDLDetailFactory", tvProFactory);//工厂

                String tvProProcedure = getItem(position).getWorkingProcedure();
                if (tvProProcedure == null) {
                    tvProProcedure = "";
                }
                spUtils.put(context, "tvFTYDLDetailProcedure", tvProProcedure);//工序

                String tvProFactoryTeams = getItem(position).getSubfactoryTeams();
                if (tvProFactoryTeams == null) {
                    tvProFactoryTeams = "";
                }
                spUtils.put(context, "tvFTYDLDetailFactoryTeams", tvProFactoryTeams);//部门

                String tvProWorkers = getItem(position).getWorkers();
                if (tvProWorkers == null) {
                    tvProWorkers = "";
                }
                spUtils.put(context, "tvFTYDLDetailWorkers", tvProWorkers);//组别人数

                String tvProFactcutqty = String.valueOf(getItem(position).getFactcutqty());
                if (tvProFactcutqty == null) {
                    tvProFactcutqty = "";
                }
                spUtils.put(context, "tvFTYDLDetailFactcutqty", tvProFactcutqty);//实裁数

                String cutbdt = getItem(position).getCutbdt();//开裁日期
                if (cutbdt == null) {
                    cutbdt = "";
                }
                spUtils.put(context, "tvFTYDLDetailCutbdt", cutbdt);

                String sewbdt = getItem(position).getSewbdt();//上线日期
                if (sewbdt == null) {
                    sewbdt = "";
                }
                spUtils.put(context, "tvFTYDLDetailSewbdt", sewbdt);

                String sewedt = getItem(position).getSewedt();//完工日期
                if (sewedt == null) {
                    sewedt = "";
                }
                spUtils.put(context, "tvFTYDLDetailSewedt", sewedt);

                String sewDays = getItem(position).getSewDays();//天数
                if (sewDays == null) {
                    sewDays = "";
                }
                spUtils.put(context, "tvFTYDLDetailSewDays", sewDays);

                String perqty = getItem(position).getPerqty();//人均件数
                if (perqty == null) {
                    perqty = "";
                }
                spUtils.put(context, "tvFTYDLDetailPerqty", perqty);

                String cutamount = getItem(position).getCutamount();//裁剪金额
                if (cutamount == null) {
                    cutamount = "";
                }
                spUtils.put(context, "tvFTYDLDetailCutamount", cutamount);

                String sewamount = getItem(position).getSewamount();
                if (sewamount == null) {
                    sewamount = "";
                }
                spUtils.put(context, "tvFTYDLDetailSewamount", sewamount);

                String packamount = getItem(position).getPackamount();
                if (packamount == null) {
                    packamount = "";
                }
                spUtils.put(context, "tvFTYDLDetailPackamount", packamount);

                String amount = getItem(position).getAmount();//总价
                if (amount == null) {
                    amount = "";
                }
                spUtils.put(context, "tvFTYDLDetailAmount", amount);

                String perMachineQty = getItem(position).getPerMachineQty();//车间人均台产
                if (perMachineQty == null) {
                    perMachineQty = "";
                }
                spUtils.put(context, "tvFTYDLDetailPerMachineQty", perMachineQty);

                String sumMachineQty = getItem(position).getSumMachineQty();//台总产
                if (sumMachineQty == null) {
                    sumMachineQty = "";
                }
                spUtils.put(context, "tvFTYDLDetailSumMachineQty", sumMachineQty);

                String tvProPrdstatus = getItem(position).getPrdstatus();
                if (tvProPrdstatus == null) {
                    tvProPrdstatus = "";
                }
                spUtils.put(context, "tvFTYDLDetailPrdstatus", tvProPrdstatus);//状态

                String prdmaster = getItem(position).getPrdmaster();//生产主管
                if (prdmaster == null) {
                    prdmaster = "";
                }
                spUtils.put(context, "tvFTYDLDetailPrdmaster", prdmaster);

                String tvProDocumentary = getItem(position).getPrddocumentary();
                if (tvProDocumentary == null) {
                    tvProDocumentary = "";
                }
                spUtils.put(context, "tvFTYDLDetailDocumentary", tvProDocumentary);//跟单

                String tvProColor = String.valueOf(getItem(position).getTaskqty());
                if (tvProColor == null) {
                    tvProColor = "";
                }
                spUtils.put(context, "tvFTYDLDetailTaskqty", tvProColor);//任务数

                String tvProCompletedLastMonth = String.valueOf(getItem(position).getSumCompletedQty());
                if (tvProCompletedLastMonth == null) {
                    tvProCompletedLastMonth = "";
                }
                spUtils.put(context, "tvFTYDLDetailSumCompletedQty", tvProCompletedLastMonth);//总完工数

                String leftQty = String.valueOf(getItem(position).getLeftQty());//结余数量
                if (leftQty == null) {
                    leftQty = "";
                }
                spUtils.put(context, "tvFTYDLDetailLeftQty", leftQty);

                String lastMonQty = String.valueOf(getItem(position).getLastMonQty());//上月结余数量
                if (lastMonQty == null) {
                    lastMonQty = "";
                }
                spUtils.put(context, "tvFTYDLDetailLastMonQty", lastMonQty);

                String year = String.valueOf(getItem(position).getYear());//年
                if (year == null) {
                    year = "";
                }
                spUtils.put(context, "tvFTYDLDetailYear", year);

                String month = String.valueOf(getItem(position).getMonth());//月
                if (month == null) {
                    month = "";
                }
                spUtils.put(context, "tvFTYDLDetailMonth", month);

                String day1 = getItem(position).getDay1();
                if (day1 == null) {
                    day1 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay1", day1);

                String day2 = getItem(position).getDay2();
                if (day2 == null) {
                    day2 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay2", day2);

                String day3 = getItem(position).getDay3();
                if (day3 == null) {
                    day3 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay3", day3);

                String day4 = getItem(position).getDay4();
                if (day4 == null) {
                    day4 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay4", day4);

                String day5 = getItem(position).getDay5();
                if (day5 == null) {
                    day5 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay5", day5);

                String day6 = getItem(position).getDay6();
                if (day6 == null) {
                    day6 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay6", day6);

                String day7 = getItem(position).getDay7();
                if (day7 == null) {
                    day7 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay7", day7);

                String day8 = getItem(position).getDay8();
                if (day8 == null) {
                    day8 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay8", day8);

                String day9 = getItem(position).getDay9();
                if (day9 == null) {
                    day9 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay9", day9);

                String day10 = getItem(position).getDay10();
                if (day10 == null) {
                    day10 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay10", day10);

                String day11 = getItem(position).getDay11();
                if (day11 == null) {
                    day11 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay11", day11);

                String day12 = getItem(position).getDay12();
                if (day12 == null) {
                    day12 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay12", day12);

                String day13 = getItem(position).getDay13();
                if (day13 == null) {
                    day13 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay13", day13);

                String day14 = getItem(position).getDay14();
                if (day14 == null) {
                    day14 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay14", day14);

                String day15 = getItem(position).getDay15();
                if (day15 == null) {
                    day15 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay15", day15);

                String day16 = getItem(position).getDay16();
                if (day16 == null) {
                    day16 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay16", day16);

                String day17 = getItem(position).getDay17();
                if (day17 == null) {
                    day17 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay17", day17);

                String day18 = getItem(position).getDay18();
                if (day18 == null) {
                    day18 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay18", day18);

                String day19 = getItem(position).getDay19();
                if (day19 == null) {
                    day19 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay19", day19);

                String day20 = getItem(position).getDay20();
                if (day20 == null) {
                    day20 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay20", day20);

                String day21 = getItem(position).getDay21();
                if (day21 == null) {
                    day21 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay21", day21);

                String day22 = getItem(position).getDay22();
                if (day22 == null) {
                    day22 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay22", day22);

                String day23 = getItem(position).getDay23();
                if (day23 == null) {
                    day23 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay23", day23);

                String day24 = getItem(position).getDay24();
                if (day24 == null) {
                    day24 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay24", day24);

                String day25 = getItem(position).getDay25();
                if (day25 == null) {
                    day25 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay25", day25);

                String day26 = getItem(position).getDay26();
                if (day26 == null) {
                    day26 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay26", day26);

                String day27 = getItem(position).getDay27();
                if (day27 == null) {
                    day27 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay27", day27);

                String day28 = getItem(position).getDay28();
                if (day28 == null) {
                    day28 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay28", day28);

                String day29 = getItem(position).getDay29();
                if (day29 == null) {
                    day29 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay29", day29);

                String day30 = getItem(position).getDay30();
                if (day30 == null) {
                    day30 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay30", day30);

                String day31 = getItem(position).getDay31();
                if (day31 == null) {
                    day31 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay31", day31);

                String prddocumentaryid = getItem(position).getPrddocumentaryid();//跟单id
                if (prddocumentaryid == null) {
                    prddocumentaryid = "";
                }
                spUtils.put(context, "tvFTYDLDetailPrdDocumentaryId", prddocumentaryid);

                boolean isdiffc = Boolean.parseBoolean(getItem(position).getIsdiffc());
                String ssisdiffc = String.valueOf(getItem(position).getIsdiffc());
                if (ssisdiffc == null) {
                    spUtils.put(context, "tvFTYDLDetailIsdiffc", "");
                } else {
                    spUtils.put(context, "tvFTYDLDetailBoolIsdiffc", isdiffc);
                    spUtils.put(context, "tvFTYDLDetailIsdiffc", "");
                }

                if (tvProProcedure.equals("裁床")) {
                    isprodure = 1;
                    spUtils.put(context, "FTYDLDetailISProdure", String.valueOf(isprodure));
                } else if (tvProProcedure.equals("选择工序")) {
                    ToastUtils.ShowToastMessage("选择工序后再新建", context);
                    return;
                } else {
                    isprodure = 0;
                    spUtils.put(context, "FTYDLDetailISProdure", String.valueOf(isprodure));
                }

                viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_item);
                context.startActivity(new Intent(context,
                        FTYDLSearchDetailActivity.class));
            }
        });

        viewHolder.lin_content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String salesid = String.valueOf(getItem(position).getID());
                String said = String.valueOf(getItem(position).getSalesid());
                spUtils.put(context, "tvFTYDLDetailID", salesid);
                spUtils.put(context, "tvFTYDLDetailSalesID", said);
                String planid = getItem(position).getPlanid();//引用的工厂计划id
                if (planid == null) {
                    planid = "";
                }
                spUtils.put(context, "tvFTYDLDetailPlanId", planid);

                String sn = String.valueOf(getItem(position).getSn());//序列号
                if (sn == null) {
                    sn = "";
                }
                spUtils.put(context, "tvFTYDLDetailSn", sn);

                String contractno = getItem(position).getContractno();//销售合同号
                if (contractno == null) {
                    contractno = "";
                }
                spUtils.put(context, "tvFTYDLDetailContractno", contractno);

                String inbill = getItem(position).getInbill();//内部id
                if (inbill == null) {
                    inbill = "";
                }
                spUtils.put(context, "tvFTYDLDetailInbill", inbill);

                String area = getItem(position).getArea();//片区号
                if (area == null) {
                    area = "";
                }
                spUtils.put(context, "tvFTYDLDetailArea", area);

                String companytxt = getItem(position).getCompanytxt();//公司名称
                if (companytxt == null) {
                    companytxt = "";
                }
                spUtils.put(context, "tvFTYDLDetailCompanytxt", companytxt);

                String po = getItem(position).getPo();//po
                if (po == null) {
                    po = "";
                }
                spUtils.put(context, "tvFTYDLDetailPo", po);

                String tvdate = getItem(position).getItem();
                if (tvdate == null) {
                    tvdate = "";
                }
                spUtils.put(context, "tvFTYDLDetailItem", tvdate);//款号

                String oitem = getItem(position).getOitem();//原款号
                if (oitem == null) {
                    oitem = "";
                }
                spUtils.put(context, "tvFTYDLDetailOitem", oitem);

                String tvProMdl = getItem(position).getMdl();
                if (tvProMdl == null) {
                    tvProMdl = "";
                }
                spUtils.put(context, "tvFTYDLDetailMdl", tvProMdl);//尺码

                String ctmid = getItem(position).getCtmid();//客户id
                if (ctmid == null) {
                    ctmid = "";
                }
                spUtils.put(context, "tvFTYDLDetailCtmid", ctmid);

                String tvctmtxt = getItem(position).getCtmtxt();
                if (tvctmtxt == null) {
                    tvctmtxt = "";
                }
                spUtils.put(context, "tvFTYDLDetailCtmtxt", tvctmtxt);//客户

                String ctmcompanytxt = getItem(position).getCtmcompanytxt();//客户归属公司
                if (ctmcompanytxt == null) {
                    ctmcompanytxt = "";
                }
                spUtils.put(context, "tvFTYDLDetailCtmcompanytxt", ctmcompanytxt);

                String prdtyp = getItem(position).getPrdtyp();//产品大类
                if (prdtyp == null) {
                    prdtyp = "";
                }
                spUtils.put(context, "tvFTYDLDetailPrdtyp", prdtyp);

                String lcdat = getItem(position).getLcdat();//计划离厂日期
                if (lcdat == null) {
                    lcdat = "";
                }
                spUtils.put(context, "tvFTYDLDetailLcdat", lcdat);

                String lbdat = getItem(position).getLbdat();//计划离岸日期
                if (lbdat == null) {
                    lbdat = "";
                }
                spUtils.put(context, "tvFTYDLDetailLbdat", lbdat);

                String styp = getItem(position).getStyp();//po类型(分类)
                if (styp == null) {
                    styp = "";
                }
                spUtils.put(context, "tvFTYDLDetailStyp", styp);

                String fsaler = getItem(position).getFsaler();//外贸业务员
                if (fsaler == null) {
                    fsaler = "";
                }
                spUtils.put(context, "tvFTYDLDetailFsaler", fsaler);

                String psaler = getItem(position).getPsaler();//生产业务员
                if (psaler == null) {
                    psaler = "";
                }
                spUtils.put(context, "tvFTYDLDetailPsaler", psaler);

                String memo = getItem(position).getMemo();//备注
                if (memo == null) {
                    memo = "";
                }
                spUtils.put(context, "tvFTYDLDetailMemo", memo);

                String tvProPqty = String.valueOf(getItem(position).getPqty());
                if (tvProPqty == null) {
                    tvProPqty = "";
                }
                spUtils.put(context, "tvFTYDLDetailPqty", tvProPqty);//制单数

                String unit = getItem(position).getUnit();//单位
                if (unit == null) {
                    unit = "";
                }
                spUtils.put(context, "tvFTYDLDetailUnit", unit);

                String tvProdcol = getItem(position).getProdcol();
                if (tvProdcol == null) {
                    tvProdcol = "";
                }
                spUtils.put(context, "tvFTYDLDetailProdcol", tvProdcol);//花色

                String megitem = getItem(position).getMegitem();//合并款号
                if (megitem == null) {
                    megitem = "";
                }
                spUtils.put(context, "tvFTYDLDetailMegitem", megitem);

                String teamname = getItem(position).getTeamname();//外贸组别
                if (teamname == null) {
                    teamname = "";
                }
                spUtils.put(context, "tvFTYDLDetailTeamname", teamname);

                String recordat = getItem(position).getRecordat();//制单时间
                if (recordat == null) {
                    recordat = "";
                }
                spUtils.put(context, "tvFTYDLDetailRecordat", recordat);

                String recordid = getItem(position).getRecordid();//制单人id
                if (recordid == null) {
                    recordid = "";
                }
                spUtils.put(context, "tvFTYDLDetailRecordid", recordid);

                String recorder = getItem(position).getRecorder();//制单人
                if (recorder == null) {
                    recorder = "";
                }
                spUtils.put(context, "tvFTYDLDetailRecorder", recorder);

                String tvProFactory = getItem(position).getSubfactory();
                if (tvProFactory == null) {
                    tvProFactory = "";
                }
                spUtils.put(context, "tvFTYDLDetailFactory", tvProFactory);//工厂

                String tvProProcedure = getItem(position).getWorkingProcedure();
                if (tvProProcedure == null) {
                    tvProProcedure = "";
                }
                spUtils.put(context, "tvFTYDLDetailProcedure", tvProProcedure);//工序

                String tvProFactoryTeams = getItem(position).getSubfactoryTeams();
                if (tvProFactoryTeams == null) {
                    tvProFactoryTeams = "";
                }
                spUtils.put(context, "tvFTYDLDetailFactoryTeams", tvProFactoryTeams);//部门

                String tvProWorkers = getItem(position).getWorkers();
                if (tvProWorkers == null) {
                    tvProWorkers = "";
                }
                spUtils.put(context, "tvFTYDLDetailWorkers", tvProWorkers);//组别人数

                String tvProFactcutqty = String.valueOf(getItem(position).getFactcutqty());
                if (tvProFactcutqty == null) {
                    tvProFactcutqty = "";
                }
                spUtils.put(context, "tvFTYDLDetailFactcutqty", tvProFactcutqty);//实裁数

                String cutbdt = getItem(position).getCutbdt();//开裁日期
                if (cutbdt == null) {
                    cutbdt = "";
                }
                spUtils.put(context, "tvFTYDLDetailCutbdt", cutbdt);

                String sewbdt = getItem(position).getSewbdt();//上线日期
                if (sewbdt == null) {
                    sewbdt = "";
                }
                spUtils.put(context, "tvFTYDLDetailSewbdt", sewbdt);

                String sewedt = getItem(position).getSewedt();//完工日期
                if (sewedt == null) {
                    sewedt = "";
                }
                spUtils.put(context, "tvFTYDLDetailSewedt", sewedt);

                String sewDays = getItem(position).getSewDays();//天数
                if (sewDays == null) {
                    sewDays = "";
                }
                spUtils.put(context, "tvFTYDLDetailSewDays", sewDays);

                String perqty = getItem(position).getPerqty();//人均件数
                if (perqty == null) {
                    perqty = "";
                }
                spUtils.put(context, "tvFTYDLDetailPerqty", perqty);

                String cutamount = getItem(position).getCutamount();//裁剪金额
                if (cutamount == null) {
                    cutamount = "";
                }
                spUtils.put(context, "tvFTYDLDetailCutamount", cutamount);

                String sewamount = getItem(position).getSewamount();
                if (sewamount == null) {
                    sewamount = "";
                }
                spUtils.put(context, "tvFTYDLDetailSewamount", sewamount);

                String packamount = getItem(position).getPackamount();
                if (packamount == null) {
                    packamount = "";
                }
                spUtils.put(context, "tvFTYDLDetailPackamount", packamount);

                String amount = getItem(position).getAmount();//总价
                if (amount == null) {
                    amount = "";
                }
                spUtils.put(context, "tvFTYDLDetailAmount", amount);

                String perMachineQty = getItem(position).getPerMachineQty();//车间人均台产
                if (perMachineQty == null) {
                    perMachineQty = "";
                }
                spUtils.put(context, "tvFTYDLDetailPerMachineQty", perMachineQty);

                String sumMachineQty = getItem(position).getSumMachineQty();//台总产
                if (sumMachineQty == null) {
                    sumMachineQty = "";
                }
                spUtils.put(context, "tvFTYDLDetailSumMachineQty", sumMachineQty);

                String tvProPrdstatus = getItem(position).getPrdstatus();
                if (tvProPrdstatus == null) {
                    tvProPrdstatus = "";
                }
                spUtils.put(context, "tvFTYDLDetailPrdstatus", tvProPrdstatus);//状态

                String prdmaster = getItem(position).getPrdmaster();//生产主管
                if (prdmaster == null) {
                    prdmaster = "";
                }
                spUtils.put(context, "tvFTYDLDetailPrdmaster", prdmaster);

                String tvProDocumentary = getItem(position).getPrddocumentary();
                if (tvProDocumentary == null) {
                    tvProDocumentary = "";
                }
                spUtils.put(context, "tvFTYDLDetailDocumentary", tvProDocumentary);//跟单

                String tvProColor = String.valueOf(getItem(position).getTaskqty());
                if (tvProColor == null) {
                    tvProColor = "";
                }
                spUtils.put(context, "tvFTYDLDetailTaskqty", tvProColor);//任务数

                String tvProCompletedLastMonth = String.valueOf(getItem(position).getSumCompletedQty());
                if (tvProCompletedLastMonth == null) {
                    tvProCompletedLastMonth = "";
                }
                spUtils.put(context, "tvFTYDLDetailSumCompletedQty", tvProCompletedLastMonth);//总完工数

                String leftQty = String.valueOf(getItem(position).getLeftQty());//结余数量
                if (leftQty == null) {
                    leftQty = "";
                }
                spUtils.put(context, "tvFTYDLDetailLeftQty", leftQty);

                String lastMonQty = String.valueOf(getItem(position).getLastMonQty());//上月结余数量
                if (lastMonQty == null) {
                    lastMonQty = "";
                }
                spUtils.put(context, "tvFTYDLDetailLastMonQty", lastMonQty);

                String year = String.valueOf(getItem(position).getYear());//年
                if (year == null) {
                    year = "";
                }
                spUtils.put(context, "tvFTYDLDetailYear", year);

                String month = String.valueOf(getItem(position).getMonth());//月
                if (month == null) {
                    month = "";
                }
                spUtils.put(context, "tvFTYDLDetailMonth", month);

                String day1 = getItem(position).getDay1();
                if (day1 == null) {
                    day1 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay1", day1);

                String day2 = getItem(position).getDay2();
                if (day2 == null) {
                    day2 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay2", day2);

                String day3 = getItem(position).getDay3();
                if (day3 == null) {
                    day3 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay3", day3);

                String day4 = getItem(position).getDay4();
                if (day4 == null) {
                    day4 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay4", day4);

                String day5 = getItem(position).getDay5();
                if (day5 == null) {
                    day5 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay5", day5);

                String day6 = getItem(position).getDay6();
                if (day6 == null) {
                    day6 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay6", day6);

                String day7 = getItem(position).getDay7();
                if (day7 == null) {
                    day7 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay7", day7);

                String day8 = getItem(position).getDay8();
                if (day8 == null) {
                    day8 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay8", day8);

                String day9 = getItem(position).getDay9();
                if (day9 == null) {
                    day9 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay9", day9);

                String day10 = getItem(position).getDay10();
                if (day10 == null) {
                    day10 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay10", day10);

                String day11 = getItem(position).getDay11();
                if (day11 == null) {
                    day11 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay11", day11);

                String day12 = getItem(position).getDay12();
                if (day12 == null) {
                    day12 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay12", day12);

                String day13 = getItem(position).getDay13();
                if (day13 == null) {
                    day13 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay13", day13);

                String day14 = getItem(position).getDay14();
                if (day14 == null) {
                    day14 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay14", day14);

                String day15 = getItem(position).getDay15();
                if (day15 == null) {
                    day15 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay15", day15);

                String day16 = getItem(position).getDay16();
                if (day16 == null) {
                    day16 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay16", day16);

                String day17 = getItem(position).getDay17();
                if (day17 == null) {
                    day17 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay17", day17);

                String day18 = getItem(position).getDay18();
                if (day18 == null) {
                    day18 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay18", day18);

                String day19 = getItem(position).getDay19();
                if (day19 == null) {
                    day19 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay19", day19);

                String day20 = getItem(position).getDay20();
                if (day20 == null) {
                    day20 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay20", day20);

                String day21 = getItem(position).getDay21();
                if (day21 == null) {
                    day21 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay21", day21);

                String day22 = getItem(position).getDay22();
                if (day22 == null) {
                    day22 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay22", day22);

                String day23 = getItem(position).getDay23();
                if (day23 == null) {
                    day23 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay23", day23);

                String day24 = getItem(position).getDay24();
                if (day24 == null) {
                    day24 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay24", day24);

                String day25 = getItem(position).getDay25();
                if (day25 == null) {
                    day25 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay25", day25);

                String day26 = getItem(position).getDay26();
                if (day26 == null) {
                    day26 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay26", day26);

                String day27 = getItem(position).getDay27();
                if (day27 == null) {
                    day27 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay27", day27);

                String day28 = getItem(position).getDay28();
                if (day28 == null) {
                    day28 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay28", day28);

                String day29 = getItem(position).getDay29();
                if (day29 == null) {
                    day29 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay29", day29);

                String day30 = getItem(position).getDay30();
                if (day30 == null) {
                    day30 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay30", day30);

                String day31 = getItem(position).getDay31();
                if (day31 == null) {
                    day31 = "";
                }
                spUtils.put(context, "tvFTYDLDetailDay31", day31);

                String prddocumentaryid = getItem(position).getPrddocumentaryid();//跟单id
                if (prddocumentaryid == null) {
                    prddocumentaryid = "";
                }
                spUtils.put(context, "tvFTYDLDetailPrdDocumentaryId", prddocumentaryid);

                boolean isdiffc = Boolean.parseBoolean(getItem(position).getIsdiffc());
                String ssisdiffc = String.valueOf(getItem(position).getIsdiffc());
                if (ssisdiffc == null) {
                    spUtils.put(context, "tvFTYDLDetailIsdiffc", "");
                } else {
                    spUtils.put(context, "tvFTYDLDetailBoolIsdiffc", isdiffc);
                    spUtils.put(context, "tvFTYDLDetailIsdiffc", "");
                }

                if (tvProProcedure.equals("裁床")) {
                    isprodure = 1;
                    spUtils.put(context, "FTYDLDetailISProdure", String.valueOf(isprodure));
                } else if (tvProProcedure.equals("选择工序")) {
                    ToastUtils.ShowToastMessage("选择工序后再新建", context);
                    return false;
                } else {
                    isprodure = 0;
                    spUtils.put(context, "FTYDLDetailISProdure", String.valueOf(isprodure));
                }

                viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_item);
                context.startActivity(new Intent(context,
                        FTYDLSearchDetailActivity.class));
                return true;
            }
        });

        return convertView;
    }

    class ViewHolder {
        LinearLayout lin_content;
        TextView tvProDocumentary,//跟单
                tvProFactory,//工厂
                tvProDepartment,//部门/组别
                tvProState,//状态
                tvProProcedure,//工序
                tvProOthers, //组别人
                tvProCompletedLastMonth,//上月完工
                tvProTaskNumber,//任务数;
                tvProSingularSystem,//制单数
                tvProColor,//花色
                tvProSize,//尺码
                tvProMonth,//月
                tvProClippingNumber,//实裁数
                tvProTotalCompletion,//总完工数
                tvProBalanceAmount,//结余数量
                tvProYear,//年
                tvProOneDay,//1日
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
                tvProRemarks,//备注
                tvProRecorder,//制单人
                tvProRecordat;//制单时间
    }
}