package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 查货跟踪适配
 * Created by lizhipeng on 2017/4/6.
 */

public class CommoditySqlAdapter extends BaseAdapter {
    private Context context;
    private List<CommoditydetailBean.DataBean> dataBeen;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_commodity_data, null);
            holder.tvCommoItem = (TextView) convertView.findViewById(R.id.tvCommoItem);
            holder.tvCommoCtmtxt = (TextView) convertView.findViewById(R.id.tvCommoCtmtxt);
            holder.tvCommoPrddocumentary = (TextView) convertView.findViewById(R.id.tvCommoPrddocumentary);
            holder.tvCommoprdmaster = (TextView) convertView.findViewById(R.id.tvCommoprdmaster);
            holder.tvCommoQCMasterScore = (TextView) convertView.findViewById(R.id.tvCommoQCMasterScore);
            holder.tvCommoSealedrev = (TextView) convertView.findViewById(R.id.tvCommoSealedrev);
            holder.tvCommoDocback = (TextView) convertView.findViewById(R.id.tvCommoDocback);
            holder.tvCommoLcdat = (TextView) convertView.findViewById(R.id.tvCommoLcdat);
            holder.tvCommoTaskqty = (TextView) convertView.findViewById(R.id.tvCommoTaskqty);
            holder.tvCommoPreMemo = (TextView) convertView.findViewById(R.id.tvCommoPreMemo);
            holder.tvCommoPredocdt = (TextView) convertView.findViewById(R.id.tvCommoPredocdt);
            holder.tvCommoPred = (TextView) convertView.findViewById(R.id.tvCommoPred);
            holder.tvCommoPredoc = (TextView) convertView.findViewById(R.id.tvCommoPredoc);
            holder.tvCommoFabricsok = (TextView) convertView.findViewById(R.id.tvCommoFabricsok);
            holder.tvCommoAccessoriesok = (TextView) convertView.findViewById(R.id.tvCommoAccessoriesok);
            holder.tvCommoSpcproDec = (TextView) convertView.findViewById(R.id.tvCommoSpcproDec);
            holder.tvCommoSpcproMemo = (TextView) convertView.findViewById(R.id.tvCommoSpcproMemo);
            holder.tvCommoCutqty = (TextView) convertView.findViewById(R.id.tvCommoCutqty);
            holder.tvCommoSewFdt = (TextView) convertView.findViewById(R.id.tvCommoSewFdt);
            holder.tvCommoSewMdt = (TextView) convertView.findViewById(R.id.tvCommoSewMdt);
            holder.tvCommoSubfactory = (TextView) convertView.findViewById(R.id.tvCommoSubfactory);
            holder.tvCommoPrebdt = (TextView) convertView.findViewById(R.id.tvCommoPrebdt);
            holder.tvCommoQCbdt = (TextView) convertView.findViewById(R.id.tvCommoQCbdt);
            holder.tvCommoQCbdtDoc = (TextView) convertView.findViewById(R.id.tvCommoQCbdtDoc);
            holder.tvCommoPremdt = (TextView) convertView.findViewById(R.id.tvCommoPremdt);
            holder.tvCommoQCmdt = (TextView) convertView.findViewById(R.id.tvCommoQCmdt);
            holder.tvCommoQCmdtDoc = (TextView) convertView.findViewById(R.id.tvCommoQCmdtDoc);
            holder.tvCommoPreedt = (TextView) convertView.findViewById(R.id.tvCommoPreedt);
            holder.tvCommoQCMedt = (TextView) convertView.findViewById(R.id.tvCommoQCMedt);
            holder.tvCommoQCedtDoc = (TextView) convertView.findViewById(R.id.tvCommoQCedtDoc);
            holder.tvCommoFctmdt = (TextView) convertView.findViewById(R.id.tvCommoFctmdt);
            holder.tvCommoFctedt = (TextView) convertView.findViewById(R.id.tvCommoFctedt);
            holder.tvCommoPackbdat = (TextView) convertView.findViewById(R.id.tvCommoPackbdat);
            holder.tvCommoPackqty2 = (TextView) convertView.findViewById(R.id.tvCommoPackqty2);
            holder.tvCommoQCMemo = (TextView) convertView.findViewById(R.id.tvCommoQCMemo);
            holder.tvCommoFactlcdat = (TextView) convertView.findViewById(R.id.tvCommoFactlcdat);
            holder.tvCommoBatchid = (TextView) convertView.findViewById(R.id.tvCommoBatchid);
            holder.tvCommoOurAfter = (TextView) convertView.findViewById(R.id.tvCommoOurAfter);
            holder.tvCommoCtmchkdt = (TextView) convertView.findViewById(R.id.tvCommoCtmchkdt);
            holder.tvCommoIPQCPedt = (TextView) convertView.findViewById(R.id.tvCommoIPQCPedt);
            holder.tvCommoIPQCmdt = (TextView) convertView.findViewById(R.id.tvCommoIPQCmdt);
            holder.tvCommoQAname = (TextView) convertView.findViewById(R.id.tvCommoQAname);
            holder.tvCommoQAScore = (TextView) convertView.findViewById(R.id.tvCommoQAScore);
            holder.tvCommoQAMemo = (TextView) convertView.findViewById(R.id.tvCommoQAMemo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCommoItem.setText(getItem(position).getItem());
        holder.tvCommoCtmtxt.setText(getItem(position).getCtmtxt());
        holder.tvCommoPrddocumentary.setText(getItem(position).getPrddocumentary());
        holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());
        holder.tvCommoQCMasterScore.setText(getItem(position).getQCMasterScore());
        holder.tvCommoSealedrev.setText(getItem(position).getSealedrev());
        holder.tvCommoDocback.setText(getItem(position).getDocback());
        holder.tvCommoLcdat.setText(getItem(position).getLcdat());
        holder.tvCommoTaskqty.setText(getItem(position).getTaskqty());
        holder.tvCommoPreMemo.setText(getItem(position).getPreMemo());
        holder.tvCommoPredocdt.setText(getItem(position).getPredocdt());
        holder.tvCommoPred.setText(getItem(position).getPredt());
        holder.tvCommoPredoc.setText(getItem(position).getPredoc());
        holder.tvCommoFabricsok.setText(getItem(position).getFabricsok());
        holder.tvCommoAccessoriesok.setText(getItem(position).getAccessoriesok());
        holder.tvCommoSpcproDec.setText(getItem(position).getSpcproDec());
        holder.tvCommoSpcproMemo.setText(getItem(position).getSpcproMemo());
        holder.tvCommoCutqty.setText(getItem(position).getCutqty());
        holder.tvCommoSewFdt.setText(getItem(position).getSewFdt());
        holder.tvCommoSewMdt.setText(getItem(position).getSewMdt());
        holder.tvCommoSubfactory.setText(getItem(position).getSubfactory());
        holder.tvCommoPrebdt.setText(getItem(position).getPrebdt());
        holder.tvCommoQCbdt.setText(getItem(position).getQCbdt());
        holder.tvCommoQCbdtDoc.setText(getItem(position).getQCbdtDoc());
        holder.tvCommoPremdt.setText(getItem(position).getPremdt());
        holder.tvCommoQCmdt.setText(getItem(position).getQCmdt());
        holder.tvCommoQCmdtDoc.setText(getItem(position).getQCmdtDoc());
        holder.tvCommoPreedt.setText(getItem(position).getPreedt());
        holder.tvCommoQCMedt.setText(getItem(position).getQCMedt());
        holder.tvCommoQCedtDoc.setText(getItem(position).getQCedtDoc());
        holder.tvCommoFctmdt.setText(getItem(position).getFctmdt());
        holder.tvCommoFctedt.setText(getItem(position).getFctedt());
        holder.tvCommoPackbdat.setText(getItem(position).getPackbdat());
        holder.tvCommoPackqty2.setText(getItem(position).getPackqty2());
        holder.tvCommoQCMemo.setText(getItem(position).getQCMemo());
        holder.tvCommoFactlcdat.setText(getItem(position).getFactlcdat());
        holder.tvCommoBatchid.setText(getItem(position).getBatchid());
        holder.tvCommoOurAfter.setText(getItem(position).getOurAfter());
        holder.tvCommoCtmchkdt.setText(getItem(position).getCtmchkdt());
        holder.tvCommoIPQCPedt.setText(getItem(position).getIPQCPedt());
        holder.tvCommoIPQCmdt.setText(getItem(position).getIPQCmdt());
        holder.tvCommoQAname.setText(getItem(position).getQAname());
        holder.tvCommoQAScore.setText(getItem(position).getQAScore());
        holder.tvCommoQAMemo.setText(getItem(position).getQAMemo());
        return convertView;
    }

    class ViewHolder {
        TextView tvCommoItem, tvCommoCtmtxt, tvCommoPrddocumentary, tvCommoprdmaster,
                tvCommoQCMasterScore, tvCommoSealedrev, tvCommoDocback, tvCommoLcdat,
                tvCommoTaskqty, tvCommoPreMemo, tvCommoPredocdt, tvCommoPred,
                tvCommoPredoc, tvCommoFabricsok, tvCommoAccessoriesok,
                tvCommoSpcproDec, tvCommoSpcproMemo, tvCommoCutqty, tvCommoSewFdt,
                tvCommoSewMdt, tvCommoSubfactory, tvCommoPrebdt, tvCommoQCbdt,
                tvCommoQCbdtDoc, tvCommoPremdt, tvCommoQCmdt, tvCommoQCmdtDoc,
                tvCommoPreedt, tvCommoQCMedt, tvCommoQCedtDoc, tvCommoFctmdt,
                tvCommoFctedt, tvCommoPackbdat, tvCommoPackqty2, tvCommoQCMemo,
                tvCommoFactlcdat, tvCommoBatchid, tvCommoOurAfter, tvCommoCtmchkdt,
                tvCommoIPQCPedt, tvCommoIPQCmdt, tvCommoQAname, tvCommoQAScore,
                tvCommoQAMemo;
    }
}
