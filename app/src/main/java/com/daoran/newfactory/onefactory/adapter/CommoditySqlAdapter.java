package com.daoran.newfactory.onefactory.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.commo.CommoditySqlActivity;
import com.daoran.newfactory.onefactory.activity.work.commo.CommoditydetailActivity;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 查货跟踪适配
 * Created by lizhipeng on 2017/4/6.
 */

public class CommoditySqlAdapter extends BaseAdapter {
    private static final String TAG = "commotest";
    private Context context;//创建上下文对象
    private List<CommoditydetailBean.DataBean> dataBeen;//查货跟踪信息实体集合
    private SharedPreferences sp;//临时存储
    private SPUtils spUtils;

    public CommoditySqlAdapter(Context context, List<CommoditydetailBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public CommoditydetailBean.DataBean getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_commodity_data, null);
            //客户
            holder.tvCommoCtmtxt = (TextView) convertView.findViewById(R.id.tvCommoCtmtxt);
            //跟单
            holder.tvCommoPrddocumentary = (TextView) convertView.findViewById(R.id.tvCommoPrddocumentary);
            //生产主管
            holder.tvCommoprdmaster = (TextView) convertView.findViewById(R.id.tvCommoprdmaster);
            //主管评分
            holder.tvCommoQCMasterScore = (TextView) convertView.findViewById(R.id.tvCommoQCMasterScore);
            //封样资料接收时间
            holder.tvCommoSealedrev = (TextView) convertView.findViewById(R.id.tvCommoSealedrev);
            //大货资料接收时间
            holder.tvCommoDocback = (TextView) convertView.findViewById(R.id.tvCommoDocback);
            //出货时间
            holder.tvCommoLcdat = (TextView) convertView.findViewById(R.id.tvCommoLcdat);
            //制单数量
            holder.tvCommoTaskqty = (TextView) convertView.findViewById(R.id.tvCommoTaskqty);
            //需要备注的特殊情况
            holder.tvCommoPreMemo = (TextView) convertView.findViewById(R.id.tvCommoPreMemo);
            //预计产前报告时间
            holder.tvCommoPredocdt = (TextView) convertView.findViewById(R.id.tvCommoPredocdt);
            //开产前会时间
            holder.tvCommoPred = (TextView) convertView.findViewById(R.id.tvCommoPred);
            //产前会报告
            holder.tvCommoPredoc = (TextView) convertView.findViewById(R.id.tvCommoPredoc);
            //大货面料情况
            holder.tvCommoFabricsok = (TextView) convertView.findViewById(R.id.tvCommoFabricsok);
            //大货辅料情况
            holder.tvCommoAccessoriesok = (TextView) convertView.findViewById(R.id.tvCommoAccessoriesok);
            //大货特殊工艺情况
            holder.tvCommoSpcproDec = (TextView) convertView.findViewById(R.id.tvCommoSpcproDec);
            //特殊工艺特别备注
            holder.tvCommoSpcproMemo = (TextView) convertView.findViewById(R.id.tvCommoSpcproMemo);
            //实裁数
            holder.tvCommoCutqty = (TextView) convertView.findViewById(R.id.tvCommoCutqty);
            //上线日期
            holder.tvCommoSewFdt = (TextView) convertView.findViewById(R.id.tvCommoSewFdt);
            //下线日期
            holder.tvCommoSewMdt = (TextView) convertView.findViewById(R.id.tvCommoSewMdt);
            //加工厂
            holder.tvCommoSubfactory = (TextView) convertView.findViewById(R.id.tvCommoSubfactory);
            //预计早期时间
            holder.tvCommoPrebdt = (TextView) convertView.findViewById(R.id.tvCommoPrebdt);
            //自查早期时间
            holder.tvCommoQCbdt = (TextView) convertView.findViewById(R.id.tvCommoQCbdt);
            //早期报告
            holder.tvCommoQCbdtDoc = (TextView) convertView.findViewById(R.id.tvCommoQCbdtDoc);
            //预计中期时间
            holder.tvCommoPremdt = (TextView) convertView.findViewById(R.id.tvCommoPremdt);
            //自查中期时间
            holder.tvCommoQCmdt = (TextView) convertView.findViewById(R.id.tvCommoQCmdt);
            //中期报告
            holder.tvCommoQCmdtDoc = (TextView) convertView.findViewById(R.id.tvCommoQCmdtDoc);
            //预计尾期时间
            holder.tvCommoPreedt = (TextView) convertView.findViewById(R.id.tvCommoPreedt);
            //自查尾期时间
            holder.tvCommoQCMedt = (TextView) convertView.findViewById(R.id.tvCommoQCMedt);
            //尾期报告
            holder.tvCommoQCedtDoc = (TextView) convertView.findViewById(R.id.tvCommoQCedtDoc);
            //客查中期时间
            holder.tvCommoFctmdt = (TextView) convertView.findViewById(R.id.tvCommoFctmdt);
            //客查尾期报告
            holder.tvCommoFctedt = (TextView) convertView.findViewById(R.id.tvCommoFctedt);
            //成品包装开始日期
            holder.tvCommoPackbdat = (TextView) convertView.findViewById(R.id.tvCommoPackbdat);
            //装箱数量
            holder.tvCommoPackqty2 = (TextView) convertView.findViewById(R.id.tvCommoPackqty2);
            //qc特别备注
            holder.tvCommoQCMemo = (TextView) convertView.findViewById(R.id.tvCommoQCMemo);
            //离厂日期
            holder.tvCommoFactlcdat = (TextView) convertView.findViewById(R.id.tvCommoFactlcdat);
            //查货批次
            holder.tvCommoBatchid = (TextView) convertView.findViewById(R.id.tvCommoBatchid);
            //后道
            holder.tvCommoOurAfter = (TextView) convertView.findViewById(R.id.tvCommoOurAfter);
            //业务员确认客查时间
            holder.tvCommoCtmchkdt = (TextView) convertView.findViewById(R.id.tvCommoCtmchkdt);
            //尾查预查
            holder.tvCommoIPQCPedt = (TextView) convertView.findViewById(R.id.tvCommoIPQCPedt);
            //巡检中查
            holder.tvCommoIPQCmdt = (TextView) convertView.findViewById(R.id.tvCommoIPQCmdt);
            //QA首扎
            holder.tvCommoQAname = (TextView) convertView.findViewById(R.id.tvCommoQAname);
            //QA首扎件数
            holder.tvCommoQAScore = (TextView) convertView.findViewById(R.id.tvCommoQAScore);
            //QA首扎日
            holder.tvCommoQAMemo = (TextView) convertView.findViewById(R.id.tvCommoQAMemo);
            //每项item
            holder.lin_content = (LinearLayout) convertView.findViewById(R.id.lin_content);
            //件查
            holder.tvCommoThing = (TextView) convertView.findViewById(R.id.tvCommoThing);
            //预计件查时间
            holder.tvCommoThingExpectedTime = (TextView) convertView.findViewById(R.id.tvCommoThingExpectedTime);
            //实际件查时间
            holder.tvCommoThingTime = (TextView) convertView.findViewById(R.id.tvCommoThingTime);
            //查货地点
            holder.tvCommoThingAddress = (TextView) convertView.findViewById(R.id.tvCommoThingAddress);
            spUtils.put(context, "strposition", position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /*判断item中制单人是否是登录用户，是为可改，否为不可改*/
        sp = context.getSharedPreferences("my_sp", 0);
        holder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proid = String.valueOf(getItem(position).getID());
                spUtils.put(context, "commodetailproid", proid);//id
                String commoitem = getItem(position).getItem();//款号
                spUtils.put(context, "commodetailitem", commoitem);
                String commoCtmtxt = getItem(position).getCtmtxt();//客户
                spUtils.put(context, "commodetailCtmtxt", commoCtmtxt);
                String commoPrddocumentary =
                        getItem(position).getPrddocumentary();//跟单
                String prddocumentary;
                if (commoPrddocumentary == null) {
                    prddocumentary = "";
                } else {
                    prddocumentary = commoPrddocumentary;
                }
                spUtils.put(context, "commodetailPrddocumentary", prddocumentary);
                String commoSubfactory = getItem(position).getSubfactory();//工厂
                spUtils.put(context, "commodetailSubfactory", commoSubfactory);
                String commoTaskqty = getItem(position).getTaskqty();//制单数量
                spUtils.put(context, "commodetailTaskqty", commoTaskqty);
                String commoprdmaster = getItem(position).getPrdmaster();//生产主管
                String prdmaster;
                if (commoprdmaster == null) {
                    prdmaster = "";
                } else {
                    prdmaster = commoprdmaster;
                }
                spUtils.put(context, "commodetailprdmaster", prdmaster);

                String prdmasterid = getItem(position).getPrdmasterid();//生产主管id
                spUtils.put(context,"commodetailprdmasterid",prdmasterid);

                String commoQCMasterScore = getItem(position).getQCMasterScore();//主管评分
                spUtils.put(context, "commodetailQCMasterScore", commoQCMasterScore);

                String commoSealedrev = getItem(position).getSealedrev();//封样资料接收时间
                spUtils.put(context, "commodetailSealedrev", commoSealedrev);

                String commoDocback = getItem(position).getDocback();//大货资料接收时间
                spUtils.put(context, "commodetailDocback", commoDocback);

                String commoLcdat = getItem(position).getLcdat();//出货时间
                spUtils.put(context, "commodetailLcdat", commoLcdat);

                String commoPreMemo = getItem(position).getPreMemo();//特别备注情况
                spUtils.put(context, "commodetailPreMemo", commoPreMemo);

                String commoPredocdt = getItem(position).getPredocdt();//预计产前会报告时间
                spUtils.put(context, "commodetailPredocdt", commoPredocdt);

                String commoPred = getItem(position).getPredt();//开产前会时间
                spUtils.put(context, "commodetailPred", commoPred);

                String commoPredoc = getItem(position).getPredoc();//产前会报告
                spUtils.put(context, "commodetailPredoc", commoPredoc);

                String commoFabricsok = getItem(position).getFabricsok();//大货面料情况
                spUtils.put(context, "commodetailFabricsok", commoFabricsok);

                String commoAccessoriesok = getItem(position).getAccessoriesok();//大货辅料情况
                spUtils.put(context, "commodetailAccessoriesok", commoAccessoriesok);

                String commoSpcproDec = getItem(position).getSpcproDec();//特殊工艺情况
                spUtils.put(context, "commodetailSpcproDec", commoSpcproDec);

                String commoSpcproMemo = getItem(position).getSpcproMemo();//特殊工艺备注
                spUtils.put(context, "commodetailSpcproMemo", commoSpcproMemo);

                String commoCutqty = getItem(position).getCutqty();//实裁数
                spUtils.put(context, "commodetailCutqty", commoCutqty);

                String commoSewFdt = getItem(position).getSewFdt();//上线日期
                spUtils.put(context, "commodetailSewFdt", commoSewFdt);

                String commoSewMdt = getItem(position).getSewMdt();//下线日期
                spUtils.put(context, "commodetailSewMdt", commoSewMdt);

                String commoPrebdt = getItem(position).getPrebdt();//预计早期时间
                spUtils.put(context, "commodetailPrebdt", commoPrebdt);

                String commoQCbdt = getItem(position).getQCbdt();//自查早起时间
                spUtils.put(context, "commodetailQCbdt", commoQCbdt);

                String commoQCbdtDoc = getItem(position).getQCbdtDoc();//早期报告
                spUtils.put(context, "commodetailQCbdtDoc", commoQCbdtDoc);

                String commoPremdt = getItem(position).getPremdt();//预计中期时间
                spUtils.put(context, "commodetailPremdt", commoPremdt);

                String commoQCmdt = getItem(position).getQCmdt();//自查中期时间
                spUtils.put(context, "commodetailQCmdt", commoQCmdt);

                String commoQCmdtDoc = getItem(position).getQCmdtDoc();//中期报告
                spUtils.put(context, "commodetailQCmdtDoc", commoQCmdtDoc);

                String commoPreedt = getItem(position).getPreedt();//预计尾期时间
                spUtils.put(context, "commodetailPreedt", commoPreedt);

                String commoQCMedt = getItem(position).getQCMedt();//自查尾期时间
                spUtils.put(context, "commodetailQCMedt", commoQCMedt);

                String commoQCedtDoc = getItem(position).getQCedtDoc();//尾期报告
                spUtils.put(context, "commodetailQCedtDoc", commoQCedtDoc);

                String commoFctmdt = getItem(position).getFctmdt();//客查中期报告
                spUtils.put(context, "commodetailFctmdt", commoFctmdt);

                String commoFctedt = getItem(position).getFctedt();//客查尾期报告
                spUtils.put(context, "commodetailFctedt", commoFctedt);

                String commoPackbdat = getItem(position).getPackbdat();//成品包装开始日期
                spUtils.put(context, "commodetailPackbdat", commoPackbdat);

                String commoPackqty2 = getItem(position).getPackqty2();//装箱数量
                spUtils.put(context, "commoPackqty2", commoPackqty2);

                String commoQCMemo = getItem(position).getQCMemo();//QC特别备注
                spUtils.put(context, "commodetailQCMemo", commoQCMemo);

                String commoFactlcdat = getItem(position).getFactlcdat();//离厂日期
                spUtils.put(context, "commodetailFactlcdat", commoFactlcdat);

                String commoBatchid = getItem(position).getBatchid();//查获批次
                spUtils.put(context, "commodetailBatchid", commoBatchid);

                String commoOurAfter = getItem(position).getOurAfter();//后道
                spUtils.put(context, "commodetailOurAfter", commoOurAfter);

                String commoCtmchkdt = getItem(position).getCtmchkdt();//业务员确认客查日期
                spUtils.put(context, "commodetailCtmchkdt", commoCtmchkdt);

                String commoIPQCPedt = getItem(position).getIPQCPedt();//尾查预查
                spUtils.put(context, "commodetailIPQCPedt", commoIPQCPedt);

                String commoIPQCmdt = getItem(position).getIPQCmdt();//巡检中查
                spUtils.put(context, "commodetailIPQCmdt", commoIPQCmdt);

                String commoIPQC = getItem(position).getIPQC();//巡检
                spUtils.put(context, "commodetailIPQC", commoIPQC);

                String commoIPQCid = getItem(position).getIPQCid();//巡检id
                spUtils.put(context,"commodetailIPQCid",commoIPQCid);

                String commoQAname = getItem(position).getQAname();//QA首扎
                spUtils.put(context, "commodetailQAname", commoQAname);

                String commofirstsamQA = getItem(position).getFirstsamQA();//QA首扎改后
                spUtils.put(context,"commodetailfirstsamQA",commofirstsamQA);

                String commoQAScore = getItem(position).getQAScore();//QA首扎件数
                spUtils.put(context, "commodetailQAScore", commoQAScore);

                String commofirstsamQAid = getItem(position).getFirstsamQAid();//
                spUtils.put(context,"commodetailfirstsamQAid",commofirstsamQAid);

                String commoQAMemo = getItem(position).getQAMemo();//QA首扎日期
                spUtils.put(context, "commodetailQAMemo", commoQAMemo);

                String commochker = getItem(position).getChker();//件查
                spUtils.put(context, "commodetailchker", commochker);

                String commochkpdt = getItem(position).getChkpdt();//预计件查时间
                spUtils.put(context, "commodetailchkpdt", commochkpdt);

                String commochkfctdt = getItem(position).getChkfctdt();//实际件查时间
                spUtils.put(context, "commodetailchkfctdt", commochkfctdt);

                String commochkplace = getItem(position).getChkplace();//件查地址
                spUtils.put(context, "commodetailchkplace", commochkplace);

                Intent intent = new Intent(context, CommoditydetailActivity.class);
                context.startActivity(intent);
            }
        });

        /**
         * 判断生产主管是否是当前登录用户
         * 如果是当前用户，则可以修改对应的字符
         * 如果不是，则判断跟单是否是当前用户
         * 可修改后道、主管评分、封样资料接收时间、大货资料接收时间、需要特别备注的情况、
         * 预计产前报告时间、开产前会时间、大货面料情况、大货辅料情况、大货特殊工艺情况
         * 特殊工艺特别备注、实裁数、上线日期、下线日期、预计早期时间、自查早期时间、
         * 预计中期时间、自查中期时间、预计尾期时间、自查尾期时间、客查尾期时间、
         * 客查中期时间、成品包装开始日期、装箱数量、离厂日期、业务员确认客查日期
         */
        //客户
        holder.tvCommoCtmtxt.setText(getItem(position).getCtmtxt());
        //跟单
        holder.tvCommoPrddocumentary.setText(getItem(position).getPrddocumentary());
        //生产主管
        holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());
        holder.tvCommoOurAfter.setText(getItem(position).getOurAfter());//后道
        //主管评分
        holder.tvCommoQCMasterScore.setText(getItem(position).getQCMasterScore());
        //封样资料接收时间
        holder.tvCommoSealedrev.setText(getItem(position).getSealedrev());
        //大货资料接收时间
        holder.tvCommoDocback.setText(getItem(position).getDocback());
        //出货时间
        holder.tvCommoLcdat.setText(getItem(position).getLcdat());
        //制单数量
        holder.tvCommoTaskqty.setText(getItem(position).getTaskqty());
        //需要特别备注的情况
        holder.tvCommoPreMemo.setText(getItem(position).getPreMemo());
        //预计产前报告时间
        holder.tvCommoPredocdt.setText(getItem(position).getPredocdt());
        //开产前会时间
        holder.tvCommoPred.setText(getItem(position).getPredt());
        //大货面料情况
        holder.tvCommoFabricsok.setText(getItem(position).getFabricsok());
        //大货辅料情况
        holder.tvCommoAccessoriesok.setText(getItem(position).getAccessoriesok());
        //大货特殊工艺情况
        holder.tvCommoSpcproDec.setText(getItem(position).getSpcproDec());
        //特殊工艺特别备注
        holder.tvCommoSpcproMemo.setText(getItem(position).getSpcproMemo());
        //实裁数
        holder.tvCommoCutqty.setText(getItem(position).getCutqty());
        //上线日期
        holder.tvCommoSewFdt.setText(getItem(position).getSewFdt());
        //下线日期
        holder.tvCommoSewMdt.setText(getItem(position).getSewMdt());
        //加工厂
        holder.tvCommoSubfactory.setText(getItem(position).getSubfactory());
        //预计早期时间
        holder.tvCommoPrebdt.setText(getItem(position).getPrebdt());
        //自查早期时间
        holder.tvCommoQCbdt.setText(getItem(position).getQCbdt());
        //预计中期时间
        holder.tvCommoPremdt.setText(getItem(position).getPremdt());
        //自查中期时间
        holder.tvCommoQCmdt.setText(getItem(position).getQCmdt());
        //预计尾期时间
        holder.tvCommoPreedt.setText(getItem(position).getPreedt());
        //自查尾期时间
        holder.tvCommoQCMedt.setText(getItem(position).getQCMedt());
        //客查中期时间
        holder.tvCommoFctmdt.setText(getItem(position).getFctmdt());
        //客查尾期时间
        holder.tvCommoFctedt.setText(getItem(position).getFctedt());
        //成品包装开始时间
        holder.tvCommoPackbdat.setText(getItem(position).getPackbdat());
        //装箱数量
        holder.tvCommoPackqty2.setText(getItem(position).getPackqty2());
        //qc特别备注
        holder.tvCommoQCMemo.setText(getItem(position).getQCMemo());
        //离厂日期
        holder.tvCommoFactlcdat.setText(getItem(position).getFactlcdat());
        //查货批次
        holder.tvCommoBatchid.setText(getItem(position).getBatchid());
        //业务员确认客查日期
        holder.tvCommoCtmchkdt.setText(getItem(position).getCtmchkdt());
        //尾查预查
        holder.tvCommoIPQCPedt.setText(getItem(position).getIPQCPedt());
        //巡检中查
        holder.tvCommoIPQCmdt.setText(getItem(position).getIPQCmdt());
        //QA首扎
        holder.tvCommoQAname.setText(getItem(position).getFirstsamQA());
        //QA首扎件数
        holder.tvCommoQAScore.setText(getItem(position).getQAScore());
        //QA首扎日期
        holder.tvCommoQAMemo.setText(getItem(position).getQAMemo());
        //产前会报告
        holder.tvCommoPredoc.setText(getItem(position).getPredoc());
        //早期报告
        holder.tvCommoQCbdtDoc.setText(getItem(position).getQCbdtDoc());
        //中期报告
        holder.tvCommoQCmdtDoc.setText(getItem(position).getQCmdtDoc());
        //尾期报告
        holder.tvCommoQCedtDoc.setText(getItem(position).getQCedtDoc());
        //件查
        holder.tvCommoThing.setText(getItem(position).getChker());
        //查货地点（件查）
        holder.tvCommoThingAddress.setText(getItem(position).getChkplace());
        //预计件查时间
        holder.tvCommoThingExpectedTime.setText(getItem(position).getChkpdt());
        //实际件查时间
        holder.tvCommoThingTime.setText(getItem(position).getChkfctdt());
        return convertView;
    }

    class ViewHolder {
        LinearLayout lin_content;
        TextView tvCommoOurAfter, tvCommoItem, tvCommoCtmtxt, tvCommoQAMemo,
                tvCommoPrddocumentary, tvCommoprdmaster, tvCommoSealedrev,
                tvCommoDocback, tvCommoLcdat, tvCommoTaskqty,
                tvCommoPredocdt, tvCommoPred, tvCommoSewFdt,
                tvCommoSewMdt, tvCommoSubfactory, tvCommoPrebdt,
                tvCommoQCbdt, tvCommoPremdt, tvCommoQCmdt, tvCommoPreedt,
                tvCommoQCMedt, tvCommoFctmdt, tvCommoFctedt, tvCommoPackbdat,
                tvCommoFactlcdat, tvCommoCtmchkdt, tvCommoThingExpectedTime,
                tvCommoThingTime;
        TextView
                tvCommoQCMasterScore, tvCommoPreMemo,
                tvCommoPredoc, tvCommoFabricsok, tvCommoAccessoriesok,
                tvCommoSpcproDec, tvCommoSpcproMemo, tvCommoCutqty,
                tvCommoQCbdtDoc, tvCommoQCmdtDoc, tvCommoQCedtDoc,
                tvCommoPackqty2, tvCommoQCMemo, tvCommoBatchid,
                tvCommoIPQCPedt, tvCommoIPQCmdt, tvCommoQAname, tvCommoQAScore,
                tvCommoThing, tvCommoThingAddress;
    }
}