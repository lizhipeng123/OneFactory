package com.daoran.newfactory.onefactory.adapter.qacworkadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkRightsTableBean;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkPageDataBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 查货跟踪适配
 * Created by lizhipeng on 2017/4/6.
 */

public class QACworkSearchAdapter extends BaseAdapter {
    private static final String TAG = "commotest";
    private Context context;//创建上下文对象
    private List<QACworkPageDataBean.DataBean> dataBeen;//查货跟踪信息实体集合
    private List<QACworkRightsTableBean.JsonTextBean> jsonTextBeanlist =
            new ArrayList<QACworkRightsTableBean.JsonTextBean>();
    private String[] columns = new String[]{"ID", "subfactory", "item", "sealedrev",
            "docback", "predt", "lcdat", "sewFdt", "sewMdt", "taskqty",
            "cutqty", "preMemo", "predoc", "fabricsok", "accessoriesok",
            "spcproDec", "spcproMemo", "QCbdt", "QCmdt", "QCMedt", "QCbdtDoc",
            "QCmdtDoc", "QCedtDoc", "fctmdt", "fctedt", "prddocumentary",
            "packbdat", "packqty2", "QCMemo", "factlcdat", "ourAfter", "prdmaster",
            "QCMasterScore", "batchid", "QAname", "QAScore", "QAMemo", "ctmtxt",
            "ctmchkdt", "IPQCmdt", "IPQCPedt", "predocdt", "prebdt", "premdt",
            "preedt"};
    private List<String> columnlist = Arrays.asList(columns);

    private OnClickQACworkLinter mOnClickQACworkLinter;

    public QACworkSearchAdapter(Context context, List<QACworkPageDataBean.DataBean> dataBeen
            , List<QACworkRightsTableBean.JsonTextBean> jsonTextBeanlist) {
        this.context = context;
        this.dataBeen = dataBeen;
        this.jsonTextBeanlist = jsonTextBeanlist;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public QACworkPageDataBean.DataBean getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override//重写getView
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_qacwork_search_data, null);
            //客户
            holder.tvCommoctmtxt = (TextView) convertView.findViewById(R.id.tvCommoctmtxt);
            //跟单
            holder.tvCommoprddocumentary = (TextView) convertView.findViewById(R.id.tvCommoprddocumentary);
            //生产主管
            holder.tvCommoprdmaster = (TextView) convertView.findViewById(R.id.tvCommoprdmaster);
            //主管评分
            holder.tvCommoQCMasterScore = (TextView) convertView.findViewById(R.id.tvCommoQCMasterScore);
            //封样资料接收时间
            holder.tvCommosealedrev = (TextView) convertView.findViewById(R.id.tvCommosealedrev);
            //大货资料接收时间
            holder.tvCommodocback = (TextView) convertView.findViewById(R.id.tvCommodocback);
            //出货时间
            holder.tvCommolcdat = (TextView) convertView.findViewById(R.id.tvCommolcdat);
            //制单数量
            holder.tvCommotaskqty = (TextView) convertView.findViewById(R.id.tvCommotaskqty);
            //需要备注的特殊情况
            holder.tvCommopreMemo = (TextView) convertView.findViewById(R.id.tvCommopreMemo);
            //预计产前报告时间
            holder.tvCommopredocdt = (TextView) convertView.findViewById(R.id.tvCommopredocdt);
            //开产前会时间
            holder.tvCommopredt = (TextView) convertView.findViewById(R.id.tvCommopredt);
            //产前会报告
            holder.tvCommopredoc = (TextView) convertView.findViewById(R.id.tvCommopredoc);
            //大货面料情况
            holder.tvCommofabricsok = (TextView) convertView.findViewById(R.id.tvCommofabricsok);
            //大货辅料情况
            holder.tvCommoaccessoriesok = (TextView) convertView.findViewById(R.id.tvCommoaccessoriesok);
            //大货特殊工艺情况
            holder.tvCommospcproDec = (TextView) convertView.findViewById(R.id.tvCommospcproDec);
            //特殊工艺特别备注
            holder.tvCommospcproMemo = (TextView) convertView.findViewById(R.id.tvCommospcproMemo);
            //实裁数
            holder.tvCommocutqty = (TextView) convertView.findViewById(R.id.tvCommocutqty);
            //上线日期
            holder.tvCommosewFdt = (TextView) convertView.findViewById(R.id.tvCommosewFdt);
            //下线日期
            holder.tvCommosewMdt = (TextView) convertView.findViewById(R.id.tvCommosewMdt);
            //加工厂
            holder.tvCommosubfactory = (TextView) convertView.findViewById(R.id.tvCommosubfactory);
            //预计早期时间
            holder.tvCommoprebdt = (TextView) convertView.findViewById(R.id.tvCommoprebdt);
            //自查早期时间
            holder.tvCommoQCbdt = (TextView) convertView.findViewById(R.id.tvCommoQCbdt);
            //早期报告
            holder.tvCommoQCbdtDoc = (TextView) convertView.findViewById(R.id.tvCommoQCbdtDoc);
            //预计中期时间
            holder.tvCommopremdt = (TextView) convertView.findViewById(R.id.tvCommopremdt);
            //自查中期时间
            holder.tvCommoQCmdt = (TextView) convertView.findViewById(R.id.tvCommoQCmdt);
            //中期报告
            holder.tvCommoQCmdtDoc = (TextView) convertView.findViewById(R.id.tvCommoQCmdtDoc);
            //预计尾期时间
            holder.tvCommopreedt = (TextView) convertView.findViewById(R.id.tvCommopreedt);
            //自查尾期时间
            holder.tvCommoQCMedt = (TextView) convertView.findViewById(R.id.tvCommoQCMedt);
            //尾期报告
            holder.tvCommoQCedtDoc = (TextView) convertView.findViewById(R.id.tvCommoQCedtDoc);
            //客查中期时间
            holder.tvCommofctmdt = (TextView) convertView.findViewById(R.id.tvCommofctmdt);
            //客查尾期报告
            holder.tvCommofctedt = (TextView) convertView.findViewById(R.id.tvCommofctedt);
            //成品包装开始日期
            holder.tvCommopackbdat = (TextView) convertView.findViewById(R.id.tvCommopackbdat);
            //装箱数量
            holder.tvCommopackqty2 = (TextView) convertView.findViewById(R.id.tvCommopackqty2);
            //qc特别备注
            holder.tvCommoQCMemo = (TextView) convertView.findViewById(R.id.tvCommoQCMemo);
            //离厂日期
            holder.tvCommofactlcdat = (TextView) convertView.findViewById(R.id.tvCommofactlcdat);
            //查货批次
            holder.tvCommobatchid = (TextView) convertView.findViewById(R.id.tvCommobatchid);
            //后道
            holder.tvCommoourAfter = (TextView) convertView.findViewById(R.id.tvCommoourAfter);
            //业务员确认客查时间
            holder.tvCommoctmchkdt = (TextView) convertView.findViewById(R.id.tvCommoctmchkdt);
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
            holder.tvCommoIPQC = (TextView) convertView.findViewById(R.id.tvCommoIPQC);

            holder.tvviewctmtxt = convertView.findViewById(R.id.tvviewctmtxt);
            holder.tvviewprddocumentary = convertView.findViewById(R.id.tvviewprddocumentary);
            holder.tvviewprdmaster = convertView.findViewById(R.id.tvviewprdmaster);
            holder.tvviewQCMasterScore = convertView.findViewById(R.id.tvviewQCMasterScore);
            holder.tvviewsealedrev = convertView.findViewById(R.id.tvviewsealedrev);
            holder.tvviewdocback = convertView.findViewById(R.id.tvviewdocback);
            holder.tvviewlcdat = convertView.findViewById(R.id.tvviewlcdat);
            holder.tvviewtaskqty = convertView.findViewById(R.id.tvviewtaskqty);
            holder.tvviewpreMemo = convertView.findViewById(R.id.tvviewpreMemo);
            holder.tvviewpredocdt = convertView.findViewById(R.id.tvviewpredocdt);
            holder.tvviewpredt = convertView.findViewById(R.id.tvviewpredt);
            holder.tvviewpredoc = convertView.findViewById(R.id.tvviewpredoc);
            holder.tvviewfabricsok = convertView.findViewById(R.id.tvviewfabricsok);
            holder.tvviewaccessoriesok = convertView.findViewById(R.id.tvviewaccessoriesok);
            holder.tvviewspcproDec = convertView.findViewById(R.id.tvviewspcproDec);
            holder.tvviewspcproMemo = convertView.findViewById(R.id.tvviewspcproMemo);
            holder.tvviewcutqty = convertView.findViewById(R.id.tvviewcutqty);
            holder.tvviewsewFdt = convertView.findViewById(R.id.tvviewsewFdt);
            holder.tvviewsewMdt = convertView.findViewById(R.id.tvviewsewMdt);
            holder.tvviewsubfactory = convertView.findViewById(R.id.tvviewsubfactory);
            holder.tvviewprebdt = convertView.findViewById(R.id.tvviewprebdt);
            holder.tvviewQCbdt = convertView.findViewById(R.id.tvviewQCbdt);
            holder.tvviewQCbdtDoc = convertView.findViewById(R.id.tvviewQCbdtDoc);
            holder.tvviewpremdt = convertView.findViewById(R.id.tvviewpremdt);
            holder.tvviewQCmdt = convertView.findViewById(R.id.tvviewQCmdt);
            holder.tvviewQCmdtDoc = convertView.findViewById(R.id.tvviewQCmdtDoc);
            holder.tvviewpreedt = convertView.findViewById(R.id.tvviewpreedt);
            holder.tvviewQCMedt = convertView.findViewById(R.id.tvviewQCMedt);
            holder.tvviewQCedtDoc = convertView.findViewById(R.id.tvviewQCedtDoc);
            holder.tvviewfctmdt = convertView.findViewById(R.id.tvviewfctmdt);
            holder.tvviewfctedt = convertView.findViewById(R.id.tvviewfctedt);
            holder.tvviewpackbdat = convertView.findViewById(R.id.tvviewpackbdat);
            holder.tvviewpackqty2 = convertView.findViewById(R.id.tvviewpackqty2);
            holder.tvviewQCMemo = convertView.findViewById(R.id.tvviewQCMemo);
            holder.tvviewfactlcdat = convertView.findViewById(R.id.tvviewfactlcdat);
            holder.tvviewbatchid = convertView.findViewById(R.id.tvviewbatchid);
            holder.tvviewourAfter = convertView.findViewById(R.id.tvviewourAfter);
            holder.tvviewctmchkdt = convertView.findViewById(R.id.tvviewctmchkdt);
            holder.tvviewIPQCPedt = convertView.findViewById(R.id.tvviewIPQCPedt);
            holder.tvviewIPQCmdt = convertView.findViewById(R.id.tvviewIPQCmdt);
            holder.tvviewQAname = convertView.findViewById(R.id.tvviewQAname);
            holder.tvviewQAScore = convertView.findViewById(R.id.tvviewQAScore);
            holder.tvviewQAMemo = convertView.findViewById(R.id.tvviewQAMemo);
            holder.tvviewThing = convertView.findViewById(R.id.tvviewThing);
            holder.tvviewIPQC =convertView.findViewById(R.id.tvviewIPQC);
            holder.tvviewThingExpectedTime = convertView.
                    findViewById(R.id.tvviewThingExpectedTime);
            holder.tvviewThingTime = convertView.findViewById(R.id.tvviewThingTime);
            holder.tvviewThingAddress = convertView.findViewById(R.id.tvviewThingAddress);
            convertView.setTag(holder);//第二次绘制的时候从tag中取出
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCommoctmtxt.setText(getItem(position).getCtmtxt());//客户
        holder.tvCommoIPQC.setText(getItem(position).getIPQC());//巡检
        holder.tvCommoprddocumentary.setText(getItem(position).getPrddocumentary());//跟单
        holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());//生产主管
        holder.tvCommoourAfter.setText(getItem(position).getOurAfter());//后道
        holder.tvCommoQCMasterScore.setText(getItem(position).getQCMasterScore());//主管评分
        holder.tvCommosealedrev.setText(getItem(position).getSealedrev());//封样资料接收时间
        holder.tvCommodocback.setText(getItem(position).getDocback());//大货资料接收时间
        holder.tvCommolcdat.setText(getItem(position).getLcdat());//出货时间
        holder.tvCommotaskqty.setText(getItem(position).getTaskqty());//制单数量
        holder.tvCommopreMemo.setText(getItem(position).getPreMemo());//需要特别备注的情况
        holder.tvCommopredocdt.setText(getItem(position).getPredocdt());//预计产前报告时间
        holder.tvCommopredt.setText(getItem(position).getPredt());//开产前会时间
        holder.tvCommofabricsok.setText(getItem(position).getFabricsok());//大货面料情况
        holder.tvCommoaccessoriesok.setText(getItem(position).getAccessoriesok());//大货辅料情况
        holder.tvCommospcproDec.setText(getItem(position).getSpcproDec());//大货特殊工艺情况
        holder.tvCommospcproMemo.setText(getItem(position).getSpcproMemo());//特殊工艺特别备注
        holder.tvCommocutqty.setText(getItem(position).getCutqty());//实裁数
        holder.tvCommosewFdt.setText(getItem(position).getSewFdt());//上线日期
        holder.tvCommosewMdt.setText(getItem(position).getSewMdt());//下线日期
        holder.tvCommosubfactory.setText(getItem(position).getSubfactory());//加工厂
        holder.tvCommoprebdt.setText(getItem(position).getPrebdt());//预计早期时间
        holder.tvCommoQCbdt.setText(getItem(position).getQCbdt());//自查早期时间
        holder.tvCommopremdt.setText(getItem(position).getPremdt());//预计中期时间
        holder.tvCommoQCmdt.setText(getItem(position).getQCmdt());//自查中期时间
        holder.tvCommopreedt.setText(getItem(position).getPreedt());//预计尾期时间
        holder.tvCommoQCMedt.setText(getItem(position).getQCMedt());//自查尾期时间
        holder.tvCommofctmdt.setText(getItem(position).getFctmdt());//客查中期时间
        holder.tvCommofctedt.setText(getItem(position).getFctedt());//客查尾期时间
        holder.tvCommopackbdat.setText(getItem(position).getPackbdat());//成品包装开始时间
        holder.tvCommopackqty2.setText(getItem(position).getPackqty2());//装箱数量
        holder.tvCommoQCMemo.setText(getItem(position).getQCMemo());//qc特别备注
        holder.tvCommofactlcdat.setText(getItem(position).getFactlcdat());//离厂日期
        holder.tvCommobatchid.setText(getItem(position).getBatchid());//查货批次
        holder.tvCommoctmchkdt.setText(getItem(position).getCtmchkdt());//业务员确认客查日期
        holder.tvCommoIPQCPedt.setText(getItem(position).getIPQCPedt());//尾查预查
        holder.tvCommoIPQCmdt.setText(getItem(position).getIPQCmdt());//巡检中查
        holder.tvCommoQAname.setText(getItem(position).getFirstsamQA());//QA首扎
        holder.tvCommoQAScore.setText(getItem(position).getQAScore());//QA首扎件数
        holder.tvCommoQAMemo.setText(getItem(position).getQAMemo());//QA首扎日期
        holder.tvCommopredoc.setText(getItem(position).getPredoc());//产前会报告
        holder.tvCommoQCbdtDoc.setText(getItem(position).getQCbdtDoc());//早期报告
        holder.tvCommoQCmdtDoc.setText(getItem(position).getQCmdtDoc());//中期报告
        holder.tvCommoQCedtDoc.setText(getItem(position).getQCedtDoc());//尾期报告
        holder.tvCommoThing.setText(getItem(position).getChker());//件查
        holder.tvCommoThingAddress.setText(getItem(position).getChkplace());//查货地点（件查）
        holder.tvCommoThingExpectedTime.setText(getItem(position).getChkpdt()); //预计件查时间
        holder.tvCommoThingTime.setText(getItem(position).getChkfctdt());//实际件查时间
        String jsontext = String.valueOf(jsonTextBeanlist);
        if (jsontext.equals("null") || jsontext.equals("[]")) {
            for (int i = 0; i < columnlist.size(); i++) {
                String sfil = ("tvCommo" + columnlist.get(i));
                String tvview = ("tvview" + columnlist.get(i));
                try {
                    Field field = R.id.class.getField(sfil);
                    int idd = field.getInt(new R.id());
                    View view = convertView.findViewById(idd);
                    view.setVisibility(View.VISIBLE);

                    Field fieldtvview = R.id.class.getField(tvview);
                    int iddtvview = fieldtvview.getInt(new R.id());
                    View viewtvview = convertView.findViewById(iddtvview);
                    viewtvview.setVisibility(View.VISIBLE);
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
                                String sfil = ("tvCommo" + jsonTextBeanlist.get(i).getColumnName());
                                String tvview = ("tvview" + jsonTextBeanlist.get(i).getColumnName());
                                try {
                                    Field field = R.id.class.getField(sfil);
                                    int idd = field.getInt(new R.id());
                                    View view = convertView.findViewById(idd);
                                    view.setVisibility(View.VISIBLE);

                                    Field fieldtvview = R.id.class.getField(tvview);
                                    int iddtvview = fieldtvview.getInt(new R.id());
                                    View viewtvview = convertView.findViewById(iddtvview);
                                    viewtvview.setVisibility(View.VISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else if (jsonTextBeanlist.get(i).getName().equals("查看")) {
                                String sfil = ("tvCommo" + jsonTextBeanlist.get(i).getColumnName());
                                String tvview = ("tvview" + jsonTextBeanlist.get(i).getColumnName());
                                try {
                                    Field field = R.id.class.getField(sfil);
                                    int idd = field.getInt(new R.id());
                                    View view = convertView.findViewById(idd);
                                    view.setVisibility(View.VISIBLE);

                                    Field fieldtvview = R.id.class.getField(tvview);
                                    int iddtvview = fieldtvview.getInt(new R.id());
                                    View viewtvview = convertView.findViewById(iddtvview);
                                    viewtvview.setVisibility(View.VISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                String sfil = ("tvCommo" + jsonTextBeanlist.get(i).getColumnName());
                                String tvview = ("tvview" + jsonTextBeanlist.get(i).getColumnName());
                                try {
                                    Field field = R.id.class.getField(sfil);
                                    int idd = field.getInt(new R.id());
                                    View view = convertView.findViewById(idd);
                                    view.setVisibility(View.GONE);

                                    Field fieldtvview = R.id.class.getField(tvview);
                                    int iddtvview = fieldtvview.getInt(new R.id());
                                    View viewtvview = convertView.findViewById(iddtvview);
                                    viewtvview.setVisibility(View.GONE);
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
        if(mOnClickQACworkLinter!=null){
            holder.lin_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickQACworkLinter.myQACworkClick(position);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        LinearLayout lin_content;
        TextView tvCommoourAfter, tvCommoItem, tvCommoctmtxt, tvCommoQAMemo,
                tvCommoprddocumentary, tvCommoprdmaster, tvCommosealedrev,
                tvCommodocback, tvCommolcdat, tvCommotaskqty, tvCommopredocdt,
                tvCommopredt, tvCommosewFdt, tvCommosewMdt, tvCommosubfactory,
                tvCommoprebdt, tvCommoQCbdt, tvCommopremdt, tvCommoQCmdt,
                tvCommopreedt, tvCommoQCMedt, tvCommofctmdt, tvCommofctedt,
                tvCommopackbdat, tvCommofactlcdat, tvCommoctmchkdt,
                tvCommoThingExpectedTime, tvCommoThingTime, tvCommoIPQC,
                tvCommoQCMasterScore, tvCommopreMemo, tvCommopredoc,
                tvCommofabricsok, tvCommoaccessoriesok, tvCommospcproDec,
                tvCommospcproMemo, tvCommocutqty, tvCommoQCbdtDoc, tvCommoQCmdtDoc,
                tvCommoQCedtDoc, tvCommopackqty2, tvCommoQCMemo, tvCommobatchid,
                tvCommoIPQCPedt, tvCommoIPQCmdt, tvCommoQAname, tvCommoQAScore,
                tvCommoThing, tvCommoThingAddress;
        View tvviewctmtxt, tvviewprddocumentary, tvviewprdmaster, tvviewQCMasterScore,
                tvviewsealedrev, tvviewdocback, tvviewlcdat, tvviewtaskqty, tvviewpreMemo,
                tvviewpredocdt, tvviewpredt, tvviewpredoc, tvviewfabricsok, tvviewaccessoriesok,
                tvviewspcproDec, tvviewspcproMemo, tvviewcutqty, tvviewsewFdt, tvviewsewMdt,
                tvviewsubfactory, tvviewprebdt, tvviewQCbdt, tvviewQCbdtDoc, tvviewpremdt,
                tvviewQCmdt, tvviewQCmdtDoc, tvviewpreedt, tvviewQCMedt, tvviewQCedtDoc,
                tvviewfctmdt, tvviewfctedt, tvviewpackbdat, tvviewpackqty2, tvviewQCMemo,
                tvviewfactlcdat, tvviewbatchid, tvviewourAfter, tvviewctmchkdt, tvviewIPQCPedt,
                tvviewIPQCmdt, tvviewQAname, tvviewQAScore, tvviewQAMemo, tvviewThing,
                tvviewThingExpectedTime, tvviewThingTime, tvviewThingAddress,tvviewIPQC;
    }

    /*创建回调函数,实例化接口具体化此方法*/
    public interface OnClickQACworkLinter{
        void myQACworkClick(int id);//创建回调函数
    }

    /*注册函数*/
    public void setmOnClickQACworkLinter(OnClickQACworkLinter mOnClickQACworkLinter) {
        this.mOnClickQACworkLinter = mOnClickQACworkLinter;
    }
}