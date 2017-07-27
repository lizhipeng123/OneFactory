package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.production.ProductionActivity;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

import java.util.List;

/**
 * 查货跟踪左侧列表适配
 * Created by lizhipeng on 2017/6/22.
 */

public class CommoditySqlLeftAdapter extends BaseAdapter {
    private Context context;
    private List<CommoditydetailBean.DataBean> dataBeen;
    private SPUtils spUtils;
    private SharedPreferences sp;

    public CommoditySqlLeftAdapter(Context context, List<CommoditydetailBean.DataBean> dataBeen) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_left_adapter, null);
            holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String productionItem = getItem(position).getItem();
        holder.tvLeft.setText(productionItem);
        holder.tvLeft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, ProductionActivity.class);
                spUtils.put(context,"productionleftItem",productionItem);
                context.startActivity(intent);
                return true;
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvLeft;
    }
}