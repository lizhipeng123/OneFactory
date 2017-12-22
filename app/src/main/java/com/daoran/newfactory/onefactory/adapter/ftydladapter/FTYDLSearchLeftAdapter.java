package com.daoran.newfactory.onefactory.adapter.ftydladapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.ftydl.FTYDLSearchCopyDetailActivity;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDailyBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.util.List;

/**
 * 生产日报左侧列表适配
 * Created by lizhipeng on 2017/6/22.
 */

public class FTYDLSearchLeftAdapter extends BaseAdapter {
    private List<FTYDLDailyBean.DataBean> dataBeen;
    private Context context;
    private SPUtils spUtils;
    private boolean flag = false;
    private int isprodure;
    private OnClickFTYDLCopyLinter mOnClickFTYDLCopyLinter;

    public FTYDLSearchLeftAdapter(Context context, List<FTYDLDailyBean.DataBean> dataBeen) {
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_left_adapter, null);
            viewHolder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            viewHolder.lin_content = (RelativeLayout) convertView.findViewById(R.id.lin_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String productionItem = getItem(position).getItem();
        viewHolder.tvLeft.setText(productionItem);

        if(mOnClickFTYDLCopyLinter!=null){
            viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickFTYDLCopyLinter.MyFTYDLCopyLinter(position);
                }
            });
        }
//        /*点击复制*/
//        viewHolder.lin_content.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                String ssisdiffc = String.valueOf(getItem(position).getIsdiffc());
//                if (ssisdiffc == null) {
//                    spUtils.put(context, "tvFTYDLLeftIsdiffc", "");
//                } else {
//                    spUtils.put(context, "tvFTYDLLeftBoolIsdiffc", isdiffc);
//                    spUtils.put(context, "tvFTYDLLeftIsdiffc", "");
//                }
//                flag = true;
//                spUtils.put(context, "tvprodetailflag", flag);
//
//                if (tvProProcedure.equals("裁床")) {
//                    isprodure = 1;
//                    spUtils.put(context, "FTYDLLeftIsProdure", String.valueOf(isprodure));
//                } else if (tvProProcedure.equals("选择工序")) {
//                    ToastUtils.ShowToastMessage("选择工序后再新建", context);
//                    return false;
//                } else {
//                    isprodure = 0;
//                    spUtils.put(context, "FTYDLLeftIsProdure", String.valueOf(isprodure));
//                }
//
//                viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_item);
//                context.startActivity(new Intent(context,
//                        FTYDLSearchCopyDetailActivity.class));
//                return true;
//            }
//        });
//
//        viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String salesid = String.valueOf(getItem(position).getID());
//                String said = String.valueOf(getItem(position).getSalesid());
//                spUtils.put(context, "tvFTYDLLeftId", salesid);
//                spUtils.put(context, "tvFTYDLLeftSalesId", said);
//                String planid = getItem(position).getPlanid();//引用的工厂计划id
//                if (planid == null) {
//                    planid = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPlanId", planid);
//
//                String sn = String.valueOf(getItem(position).getSn());//序列号
//                if (sn == null) {
//                    sn = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftSn", sn);
//
//                String contractno = getItem(position).getContractno();//销售合同号
//                if (contractno == null) {
//                    contractno = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftContractno", contractno);
//
//                String inbill = getItem(position).getInbill();//内部id
//                if (inbill == null) {
//                    inbill = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftInbill", inbill);
//
//                String area = getItem(position).getArea();//片区号
//                if (area == null) {
//                    area = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftArea", area);
//
//                String companytxt = getItem(position).getCompanytxt();//公司名称
//                if (companytxt == null) {
//                    companytxt = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftCompanytxt", companytxt);
//
//                String po = getItem(position).getPo();//po
//                if (po == null) {
//                    po = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPo", po);
//
//                String tvdate = getItem(position).getItem();
//                if (tvdate == null) {
//                    tvdate = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftItem", tvdate);//款号
//
//                String oitem = getItem(position).getOitem();//原款号
//                if (oitem == null) {
//                    oitem = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftOitem", oitem);
//
//                String tvProMdl = getItem(position).getMdl();
//                if (tvProMdl == null) {
//                    tvProMdl = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftMdl", tvProMdl);//尺码
//
//                String ctmid = getItem(position).getCtmid();//客户id
//                if (ctmid == null) {
//                    ctmid = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftCtmid", ctmid);
//
//                String tvctmtxt = getItem(position).getCtmtxt();
//                if (tvctmtxt == null) {
//                    tvctmtxt = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftCtmtxt", tvctmtxt);//客户
//
//                String ctmcompanytxt = getItem(position).getCtmcompanytxt();//客户归属公司
//                if (ctmcompanytxt == null) {
//                    ctmcompanytxt = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftCtmcompanytxt", ctmcompanytxt);
//
//                String prdtyp = getItem(position).getPrdtyp();//产品大类
//                if (prdtyp == null) {
//                    prdtyp = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPrdtyp", prdtyp);
//
//                String lcdat = getItem(position).getLcdat();//计划离厂日期
//                if (lcdat == null) {
//                    lcdat = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftLcdat", lcdat);
//
//                String lbdat = getItem(position).getLbdat();//计划离岸日期
//                if (lbdat == null) {
//                    lbdat = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftLbdat", lbdat);
//
//                String styp = getItem(position).getStyp();//po类型(分类)
//                if (styp == null) {
//                    styp = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftStyp", styp);
//
//                String fsaler = getItem(position).getFsaler();//外贸业务员
//                if (fsaler == null) {
//                    fsaler = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftFsaler", fsaler);
//
//                String psaler = getItem(position).getPsaler();//生产业务员
//                if (psaler == null) {
//                    psaler = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPsaler", psaler);
//
//                String memo = getItem(position).getMemo();//备注
//                if (memo == null) {
//                    memo = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftMemo", memo);
//
//                String tvProPqty = String.valueOf(getItem(position).getPqty());
//                if (tvProPqty == null) {
//                    tvProPqty = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPqty", tvProPqty);//制单数
//
//                String unit = getItem(position).getUnit();//单位
//                if (unit == null) {
//                    unit = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftUnit", unit);
//
//                String tvProdcol = getItem(position).getProdcol();
//                if (tvProdcol == null) {
//                    tvProdcol = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftProdcol", tvProdcol);//花色
//
//                String megitem = getItem(position).getMegitem();//合并款号
//                if (megitem == null) {
//                    megitem = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftMegitem", megitem);
//
//                String teamname = getItem(position).getTeamname();//外贸组别
//                if (teamname == null) {
//                    teamname = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftTeamname", teamname);
//
//                String recordat = getItem(position).getRecordat();//制单时间
//                if (recordat == null) {
//                    recordat = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftRecordat", recordat);
//
//                String recordid = getItem(position).getRecordid();//制单人id
//                if (recordid == null) {
//                    recordid = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftRecordid", recordid);
//
//                String recorder = getItem(position).getRecorder();//制单人
//                if (recorder == null) {
//                    recorder = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftRecorder", recorder);
//
//                String tvProFactory = getItem(position).getSubfactory();
//                if (tvProFactory == null) {
//                    tvProFactory = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftFactory", tvProFactory);//工厂
//
//                String tvProProcedure = getItem(position).getWorkingProcedure();
//                if (tvProProcedure == null) {
//                    tvProProcedure = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftProcedure", tvProProcedure);//工序
//
//                String tvProFactoryTeams = getItem(position).getSubfactoryTeams();
//                if (tvProFactoryTeams == null) {
//                    tvProFactoryTeams = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftFactoryTeams", tvProFactoryTeams);//部门
//
//                String tvProWorkers = getItem(position).getWorkers();
//                if (tvProWorkers == null) {
//                    tvProWorkers = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftWorkers", tvProWorkers);//组别人数
//
//                String tvProFactcutqty = String.valueOf(getItem(position).getFactcutqty());
//                if (tvProFactcutqty == null) {
//                    tvProFactcutqty = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftFactcutqty", tvProFactcutqty);//实裁数
//
//                String cutbdt = getItem(position).getCutbdt();//开裁日期
//                if (cutbdt == null) {
//                    cutbdt = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftCutbdt", cutbdt);
//
//                String sewbdt = getItem(position).getSewbdt();//上线日期
//                if (sewbdt == null) {
//                    sewbdt = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftSewbdt", sewbdt);
//
//                String sewedt = getItem(position).getSewedt();//完工日期
//                if (sewedt == null) {
//                    sewedt = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftSewedt", sewedt);
//
//                String sewDays = getItem(position).getSewDays();//天数
//                if (sewDays == null) {
//                    sewDays = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftSewDays", sewDays);
//
//                String perqty = getItem(position).getPerqty();//人均件数
//                if (perqty == null) {
//                    perqty = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPerqty", perqty);
//
//                String cutamount = getItem(position).getCutamount();//裁剪金额
//                if (cutamount == null) {
//                    cutamount = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftCutamount", cutamount);
//
//                String sewamount = getItem(position).getSewamount();
//                if (sewamount == null) {
//                    sewamount = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftSewamount", sewamount);
//
//                String packamount = getItem(position).getPackamount();
//                if (packamount == null) {
//                    packamount = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPackamount", packamount);
//
//                String amount = getItem(position).getAmount();//总价
//                if (amount == null) {
//                    amount = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftAmount", amount);
//
//                String perMachineQty = getItem(position).getPerMachineQty();//车间人均台产
//                if (perMachineQty == null) {
//                    perMachineQty = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPerMachineQty", perMachineQty);
//
//                String sumMachineQty = getItem(position).getSumMachineQty();//台总产
//                if (sumMachineQty == null) {
//                    sumMachineQty = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftSumMachineQty", sumMachineQty);
//
//                String tvProPrdstatus = getItem(position).getPrdstatus();
//                if (tvProPrdstatus == null) {
//                    tvProPrdstatus = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPrdstatus", tvProPrdstatus);//状态
//
//                String prdmaster = getItem(position).getPrdmaster();//生产主管
//                if (prdmaster == null) {
//                    prdmaster = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPrdmaster", prdmaster);
//
//                String tvProDocumentary = getItem(position).getPrddocumentary();
//                if (tvProDocumentary == null) {
//                    tvProDocumentary = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDocumentary", tvProDocumentary);//跟单
//
//                String tvProTaskqty = String.valueOf(getItem(position).getTaskqty());
//                if (tvProTaskqty == null) {
//                    tvProTaskqty = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftTaskqty", tvProTaskqty);//任务数
//
//                String tvProCompletedLastMonth = String.valueOf(getItem(position).getSumCompletedQty());
//                if (tvProCompletedLastMonth == null) {
//                    tvProCompletedLastMonth = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftSumCompletedQty", tvProCompletedLastMonth);//总完工数
//
//                String leftQty = String.valueOf(getItem(position).getLeftQty());//结余数量
//                if (leftQty == null) {
//                    leftQty = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftLeftQty", leftQty);
//
//                String lastMonQty = String.valueOf(getItem(position).getLastMonQty());//上月结余数量
//                if (lastMonQty == null) {
//                    lastMonQty = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftLastMonQty", lastMonQty);
//
//                String year = String.valueOf(getItem(position).getYear());//年
//                if (year == null) {
//                    year = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftYear", year);
//
//                String month = String.valueOf(getItem(position).getMonth());//月
//                if (month == null) {
//                    month = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftMonth", month);
//
//                String day1 = getItem(position).getDay1();
//                if (day1 == null) {
//                    day1 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay1", day1);
//
//                String day2 = getItem(position).getDay2();
//                if (day2 == null) {
//                    day2 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay2", day2);
//
//                String day3 = getItem(position).getDay3();
//                if (day3 == null) {
//                    day3 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay3", day3);
//
//                String day4 = getItem(position).getDay4();
//                if (day4 == null) {
//                    day4 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay4", day4);
//
//                String day5 = getItem(position).getDay5();
//                if (day5 == null) {
//                    day5 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay5", day5);
//
//                String day6 = getItem(position).getDay6();
//                if (day6 == null) {
//                    day6 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay6", day6);
//
//                String day7 = getItem(position).getDay7();
//                if (day7 == null) {
//                    day7 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay7", day7);
//
//                String day8 = getItem(position).getDay8();
//                if (day8 == null) {
//                    day8 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay8", day8);
//
//                String day9 = getItem(position).getDay9();
//                if (day9 == null) {
//                    day9 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay9", day9);
//
//                String day10 = getItem(position).getDay10();
//                if (day10 == null) {
//                    day10 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay10", day10);
//
//                String day11 = getItem(position).getDay11();
//                if (day11 == null) {
//                    day11 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay11", day11);
//
//                String day12 = getItem(position).getDay12();
//                if (day12 == null) {
//                    day12 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay12", day12);
//
//                String day13 = getItem(position).getDay13();
//                if (day13 == null) {
//                    day13 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay13", day13);
//
//                String day14 = getItem(position).getDay14();
//                if (day14 == null) {
//                    day14 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay14", day14);
//
//                String day15 = getItem(position).getDay15();
//                if (day15 == null) {
//                    day15 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay15", day15);
//
//                String day16 = getItem(position).getDay16();
//                if (day16 == null) {
//                    day16 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay16", day16);
//
//                String day17 = getItem(position).getDay17();
//                if (day17 == null) {
//                    day17 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay17", day17);
//
//                String day18 = getItem(position).getDay18();
//                if (day18 == null) {
//                    day18 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay18", day18);
//
//                String day19 = getItem(position).getDay19();
//                if (day19 == null) {
//                    day19 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay19", day19);
//
//                String day20 = getItem(position).getDay20();
//                if (day20 == null) {
//                    day20 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay20", day20);
//
//                String day21 = getItem(position).getDay21();
//                if (day21 == null) {
//                    day21 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay21", day21);
//
//                String day22 = getItem(position).getDay22();
//                if (day22 == null) {
//                    day22 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay22", day22);
//
//                String day23 = getItem(position).getDay23();
//                if (day23 == null) {
//                    day23 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay23", day23);
//
//                String day24 = getItem(position).getDay24();
//                if (day24 == null) {
//                    day24 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay24", day24);
//
//                String day25 = getItem(position).getDay25();
//                if (day25 == null) {
//                    day25 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay25", day25);
//
//                String day26 = getItem(position).getDay26();
//                if (day26 == null) {
//                    day26 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay26", day26);
//
//                String day27 = getItem(position).getDay27();
//                if (day27 == null) {
//                    day27 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay27", day27);
//
//                String day28 = getItem(position).getDay28();
//                if (day28 == null) {
//                    day28 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay28", day28);
//
//                String day29 = getItem(position).getDay29();
//                if (day29 == null) {
//                    day29 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay29", day29);
//
//                String day30 = getItem(position).getDay30();
//                if (day30 == null) {
//                    day30 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay30", day30);
//
//                String day31 = getItem(position).getDay31();
//                if (day31 == null) {
//                    day31 = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftDay31", day31);
//
//                String prddocumentaryid = getItem(position).getPrddocumentaryid();//跟单id
//                if (prddocumentaryid == null) {
//                    prddocumentaryid = "";
//                }
//                spUtils.put(context, "tvFTYDLLeftPrdDocumentaryId", prddocumentaryid);
//
//                boolean isdiffc = Boolean.parseBoolean(getItem(position).getIsdiffc());
//                String ssisdiffc = String.valueOf(getItem(position).getIsdiffc());
//                if (ssisdiffc == null) {
//                    spUtils.put(context, "tvFTYDLLeftIsdiffc", "");
//                } else {
//                    spUtils.put(context, "tvFTYDLLeftBoolIsdiffc", isdiffc);
//                    spUtils.put(context, "tvFTYDLLeftIsdiffc", "");
//                }
//                flag = true;
//                spUtils.put(context, "tvprodetailflag", flag);
//
//                if (tvProProcedure.equals("裁床")) {
//                    isprodure = 1;
//                    spUtils.put(context, "FTYDLLeftIsProdure", String.valueOf(isprodure));
//                } else if (tvProProcedure.equals("选择工序")) {
//                    ToastUtils.ShowToastMessage("选择工序后再新建", context);
//                    return;
//                } else {
//                    isprodure = 0;
//                    spUtils.put(context, "FTYDLLeftIsProdure", String.valueOf(isprodure));
//                }
//
//                viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_item);
//                context.startActivity(new Intent(context,
//                        FTYDLSearchCopyDetailActivity.class));
//            }
//        });
        return convertView;
    }

    class ViewHolder {
        TextView tvLeft;
        RelativeLayout lin_content;
    }

    public interface OnClickFTYDLCopyLinter{
        void MyFTYDLCopyLinter(int id);//创建回调函数
    }

    public void setmOnClickFTYDLCopyLinter(OnClickFTYDLCopyLinter mOnClickFTYDLCopyLinter) {
        this.mOnClickFTYDLCopyLinter = mOnClickFTYDLCopyLinter;
    }
}