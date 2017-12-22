package com.daoran.newfactory.onefactory.adapter.pocontrastadpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.pocontrastbean.PoContrastEntyBean;

import java.util.List;

/**
 * Created by luoqf on 2017/12/20.
 * name
 */

public class PoContrastLeftAdapter extends BaseAdapter{
    private Context context;
    private List<PoContrastEntyBean.DataBean> dataBeanList;

    public  PoContrastLeftAdapter(Context context, List<PoContrastEntyBean.DataBean>dataBeanList)
    {
        this.context = context;

        this.dataBeanList =dataBeanList;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public PoContrastEntyBean.DataBean getItem(int position) {
        return dataBeanList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pocontrastleft_data, null);
            holder.tvasser = (TextView) convertView.findViewById(R.id.tv_asser);
            holder.tvasser2 = (TextView) convertView.findViewById(R.id.tv_asser2);
            holder.tvasser3 = (TextView) convertView.findViewById(R.id.tv_asser3);
            holder.tvitem = (TextView) convertView.findViewById(R.id.tv_item);
            holder.tvmtr = (TextView) convertView.findViewById(R.id.tv_mtr);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String productionItem = getItem(position).getItem();
        holder.tvasser.setText(getItem(position).getAsser());
        holder.tvasser2.setText(getItem(position).getAsser2());
        holder.tvasser3.setText(getItem(position).getAsser3());
        holder.tvitem.setText(getItem(position).getItem());
        holder.tvmtr.setText(getItem(position).getMtr());
        return convertView;
    }
    class ViewHolder{
         TextView tvasser;
         TextView tvasser2;
         TextView tvasser3;
         TextView tvitem;
         TextView tvmtr;
    }
}
