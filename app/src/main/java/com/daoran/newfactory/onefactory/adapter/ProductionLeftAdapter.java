package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;

import java.util.List;

/**
 * 生产日报左侧列表适配
 * Created by lizhipeng on 2017/6/22.
 */

public class ProductionLeftAdapter extends BaseAdapter {
    private List<ProducationDetailBean.DataBean> dataBeen;
    private Context context;

    public ProductionLeftAdapter(Context context, List<ProducationDetailBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public ProducationDetailBean.DataBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_left_adapter, null);
            holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String productionItem = getItem(position).getItem();
        holder.tvLeft.setText(productionItem);
        return convertView;
    }

    class ViewHolder {
        TextView tvLeft;
    }
}
