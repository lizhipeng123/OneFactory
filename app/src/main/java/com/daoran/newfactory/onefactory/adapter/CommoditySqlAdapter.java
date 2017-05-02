package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;

import java.util.List;

/**
 * 查货跟踪适配
 * Created by lizhipeng on 2017/4/6.
 */

public class CommoditySqlAdapter extends BaseAdapter {
    private Context context;
    private List<CommoditydetailBean.DataBean> dataBeen;
    private SharedPreferences sp;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_commodity_data, null);
            holder.tvCommoItem = (EditText) convertView.findViewById(R.id.tvCommoItem);
            holder.tvCommoCtmtxt = (EditText) convertView.findViewById(R.id.tvCommoCtmtxt);
            holder.tvCommoPrddocumentary = (EditText) convertView.findViewById(R.id.tvCommoPrddocumentary);
            holder.tvCommoprdmaster = (EditText) convertView.findViewById(R.id.tvCommoprdmaster);
            holder.tvCommoQCMasterScore = (EditText) convertView.findViewById(R.id.tvCommoQCMasterScore);
            holder.tvCommoSealedrev = (EditText) convertView.findViewById(R.id.tvCommoSealedrev);
            holder.tvCommoDocback = (EditText) convertView.findViewById(R.id.tvCommoDocback);
            holder.tvCommoLcdat = (EditText) convertView.findViewById(R.id.tvCommoLcdat);
            holder.tvCommoTaskqty = (EditText) convertView.findViewById(R.id.tvCommoTaskqty);
            holder.tvCommoPreMemo = (EditText) convertView.findViewById(R.id.tvCommoPreMemo);
            holder.tvCommoPredocdt = (EditText) convertView.findViewById(R.id.tvCommoPredocdt);
            holder.tvCommoPred = (EditText) convertView.findViewById(R.id.tvCommoPred);
            holder.tvCommoPredoc = (EditText) convertView.findViewById(R.id.tvCommoPredoc);
            holder.tvCommoFabricsok = (EditText) convertView.findViewById(R.id.tvCommoFabricsok);
            holder.tvCommoAccessoriesok = (EditText) convertView.findViewById(R.id.tvCommoAccessoriesok);
            holder.tvCommoSpcproDec = (EditText) convertView.findViewById(R.id.tvCommoSpcproDec);
            holder.tvCommoSpcproMemo = (EditText) convertView.findViewById(R.id.tvCommoSpcproMemo);
            holder.tvCommoCutqty = (EditText) convertView.findViewById(R.id.tvCommoCutqty);
            holder.tvCommoSewFdt = (EditText) convertView.findViewById(R.id.tvCommoSewFdt);
            holder.tvCommoSewMdt = (EditText) convertView.findViewById(R.id.tvCommoSewMdt);
            holder.tvCommoSubfactory = (EditText) convertView.findViewById(R.id.tvCommoSubfactory);
            holder.tvCommoPrebdt = (EditText) convertView.findViewById(R.id.tvCommoPrebdt);
            holder.tvCommoQCbdt = (EditText) convertView.findViewById(R.id.tvCommoQCbdt);
            holder.tvCommoQCbdtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCbdtDoc);
            holder.tvCommoPremdt = (EditText) convertView.findViewById(R.id.tvCommoPremdt);
            holder.tvCommoQCmdt = (EditText) convertView.findViewById(R.id.tvCommoQCmdt);
            holder.tvCommoQCmdtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCmdtDoc);
            holder.tvCommoPreedt = (EditText) convertView.findViewById(R.id.tvCommoPreedt);
            holder.tvCommoQCMedt = (EditText) convertView.findViewById(R.id.tvCommoQCMedt);
            holder.tvCommoQCedtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCedtDoc);
            holder.tvCommoFctmdt = (EditText) convertView.findViewById(R.id.tvCommoFctmdt);
            holder.tvCommoFctedt = (EditText) convertView.findViewById(R.id.tvCommoFctedt);
            holder.tvCommoPackbdat = (EditText) convertView.findViewById(R.id.tvCommoPackbdat);
            holder.tvCommoPackqty2 = (EditText) convertView.findViewById(R.id.tvCommoPackqty2);
            holder.tvCommoQCMemo = (EditText) convertView.findViewById(R.id.tvCommoQCMemo);
            holder.tvCommoFactlcdat = (EditText) convertView.findViewById(R.id.tvCommoFactlcdat);
            holder.tvCommoBatchid = (EditText) convertView.findViewById(R.id.tvCommoBatchid);
            holder.tvCommoOurAfter = (TextView) convertView.findViewById(R.id.tvCommoOurAfter);
            holder.tvCommoCtmchkdt = (EditText) convertView.findViewById(R.id.tvCommoCtmchkdt);
            holder.tvCommoIPQCPedt = (EditText) convertView.findViewById(R.id.tvCommoIPQCPedt);
            holder.tvCommoIPQCmdt = (EditText) convertView.findViewById(R.id.tvCommoIPQCmdt);
            holder.tvCommoQAname = (EditText) convertView.findViewById(R.id.tvCommoQAname);
            holder.tvCommoQAScore = (EditText) convertView.findViewById(R.id.tvCommoQAScore);
            holder.tvCommoQAMemo = (EditText) convertView.findViewById(R.id.tvCommoQAMemo);
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

        holder.tvCommoOurAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopuphdMenu(holder.tvCommoOurAfter);
            }
        });
/*判断item中制单人是否是登录用户，是为可改，否为不可改*/
//        sp = context.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
//        String nameid = sp.getString("username", "");
//        String documentary = getItem(position).getPrddocumentary().toString();
//        String master = getItem(position).getPrdmaster().toString();

//        if (nameid == documentary || nameid == master ) {
//            holder.tvCommoItem.setEnabled(true);
//            holder.tvCommoCtmtxt.setEnabled(true);
//            holder.tvCommoPrddocumentary.setEnabled(true);
//            holder.tvCommoprdmaster.setEnabled(true);
//            holder.tvCommoQCMasterScore.setEnabled(true);
//            holder.tvCommoSealedrev.setEnabled(true);
//            holder.tvCommoDocback.setEnabled(true);
//            holder.tvCommoLcdat.setEnabled(true);
//            holder.tvCommoTaskqty.setEnabled(true);
//            holder.tvCommoPreMemo.setEnabled(true);
//            holder.tvCommoPredocdt.setEnabled(true);
//            holder.tvCommoPred.setEnabled(true);
//            holder.tvCommoPredoc.setEnabled(true);
//            holder.tvCommoFabricsok.setEnabled(true);
//            holder.tvCommoAccessoriesok.setEnabled(true);
//            holder.tvCommoSpcproDec.setEnabled(true);
//            holder.tvCommoSpcproMemo.setEnabled(true);
//            holder.tvCommoCutqty.setEnabled(true);
//            holder.tvCommoSewFdt.setEnabled(true);
//            holder.tvCommoSewMdt.setEnabled(true);
//            holder.tvCommoSubfactory.setEnabled(true);
//            holder.tvCommoPrebdt.setEnabled(true);
//            holder.tvCommoQCbdt.setEnabled(true);
//            holder.tvCommoQCbdtDoc.setEnabled(true);
//            holder.tvCommoPremdt.setEnabled(true);
//            holder.tvCommoQCmdt.setEnabled(true);
//            holder.tvCommoQCmdtDoc.setEnabled(true);
//            holder.tvCommoPreedt.setEnabled(true);
//            holder.tvCommoQCMedt.setEnabled(true);
//            holder.tvCommoQCedtDoc.setEnabled(true);
//            holder.tvCommoFctmdt.setEnabled(true);
//            holder.tvCommoFctedt.setEnabled(true);
//            holder.tvCommoPackbdat.setEnabled(true);
//            holder.tvCommoPackqty2.setEnabled(true);
//            holder.tvCommoQCMemo.setEnabled(true);
//            holder.tvCommoFactlcdat.setEnabled(true);
//            holder.tvCommoBatchid.setEnabled(true);
//            holder.tvCommoOurAfter.setEnabled(true);
//            holder.tvCommoCtmchkdt.setEnabled(true);
//            holder.tvCommoIPQCPedt.setEnabled(true);
//            holder.tvCommoIPQCmdt.setEnabled(true);
//            holder.tvCommoQAname.setEnabled(true);
//            holder.tvCommoQAScore.setEnabled(true);
//            holder.tvCommoQAMemo.setEnabled(true);
//        } else {
//            holder.tvCommoItem.setEnabled(false);
//            holder.tvCommoCtmtxt.setEnabled(false);
//            holder.tvCommoPrddocumentary.setEnabled(false);
//            holder.tvCommoprdmaster.setEnabled(false);
//            holder.tvCommoQCMasterScore.setEnabled(false);
//            holder.tvCommoSealedrev.setEnabled(false);
//            holder.tvCommoDocback.setEnabled(false);
//            holder.tvCommoLcdat.setEnabled(false);
//            holder.tvCommoTaskqty.setEnabled(false);
//            holder.tvCommoPreMemo.setEnabled(false);
//            holder.tvCommoPredocdt.setEnabled(false);
//            holder.tvCommoPred.setEnabled(false);
//            holder.tvCommoPredoc.setEnabled(false);
//            holder.tvCommoFabricsok.setEnabled(false);
//            holder.tvCommoAccessoriesok.setEnabled(false);
//            holder.tvCommoSpcproDec.setEnabled(false);
//            holder.tvCommoSpcproMemo.setEnabled(false);
//            holder.tvCommoCutqty.setEnabled(false);
//            holder.tvCommoSewFdt.setEnabled(false);
//            holder.tvCommoSewMdt.setEnabled(false);
//            holder.tvCommoSubfactory.setEnabled(false);
//            holder.tvCommoPrebdt.setEnabled(false);
//            holder.tvCommoQCbdt.setEnabled(false);
//            holder.tvCommoQCbdtDoc.setEnabled(false);
//            holder.tvCommoPremdt.setEnabled(false);
//            holder.tvCommoQCmdt.setEnabled(false);
//            holder.tvCommoQCmdtDoc.setEnabled(false);
//            holder.tvCommoPreedt.setEnabled(false);
//            holder.tvCommoQCMedt.setEnabled(false);
//            holder.tvCommoQCedtDoc.setEnabled(false);
//            holder.tvCommoFctmdt.setEnabled(false);
//            holder.tvCommoFctedt.setEnabled(false);
//            holder.tvCommoPackbdat.setEnabled(false);
//            holder.tvCommoPackqty2.setEnabled(false);
//            holder.tvCommoQCMemo.setEnabled(false);
//            holder.tvCommoFactlcdat.setEnabled(false);
//            holder.tvCommoBatchid.setEnabled(false);
//            holder.tvCommoOurAfter.setEnabled(false);
//            holder.tvCommoCtmchkdt.setEnabled(false);
//            holder.tvCommoIPQCPedt.setEnabled(false);
//            holder.tvCommoIPQCmdt.setEnabled(false);
//            holder.tvCommoQAname.setEnabled(false);
//            holder.tvCommoQAScore.setEnabled(false);
//            holder.tvCommoQAMemo.setEnabled(false);
//
//        }

        return convertView;
    }

    /**
     * 弹出后道选择菜单
     *
     * @param view
     */
    private void showPopuphdMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_commo_hd, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sp = context.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
                String title = item.getTitle().toString();
                spUtils.put(context, "commohdTitle", title);
                ToastUtils.ShowToastMessage("点击的是：" + title, context);
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

    class ViewHolder {
        TextView tvCommoOurAfter;
        EditText tvCommoItem, tvCommoCtmtxt, tvCommoPrddocumentary, tvCommoprdmaster,
                tvCommoQCMasterScore, tvCommoSealedrev, tvCommoDocback, tvCommoLcdat,
                tvCommoTaskqty, tvCommoPreMemo, tvCommoPredocdt, tvCommoPred,
                tvCommoPredoc, tvCommoFabricsok, tvCommoAccessoriesok,
                tvCommoSpcproDec, tvCommoSpcproMemo, tvCommoCutqty, tvCommoSewFdt,
                tvCommoSewMdt, tvCommoSubfactory, tvCommoPrebdt, tvCommoQCbdt,
                tvCommoQCbdtDoc, tvCommoPremdt, tvCommoQCmdt, tvCommoQCmdtDoc,
                tvCommoPreedt, tvCommoQCMedt, tvCommoQCedtDoc, tvCommoFctmdt,
                tvCommoFctedt, tvCommoPackbdat, tvCommoPackqty2, tvCommoQCMemo,
                tvCommoFactlcdat, tvCommoBatchid, tvCommoCtmchkdt,
                tvCommoIPQCPedt, tvCommoIPQCmdt, tvCommoQAname, tvCommoQAScore,
                tvCommoQAMemo;
    }
}
