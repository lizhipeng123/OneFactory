package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.SqlCarApplyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhipeng on 2017/4/12.
 */

public class SqlCarApplyAdapter extends BaseAdapter {
    private List<SqlCarApplyBean.DataBean> dataBeen;
    private Context context;

    public SqlCarApplyAdapter(List<SqlCarApplyBean.DataBean> dataBeen, Context context) {
        this.dataBeen = dataBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public SqlCarApplyBean.DataBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sqlcar, null);
            holder.tvSqlCarId = (TextView) convertView.findViewById(R.id.tvSqlCarId);
            holder.tvSqlApplyName = (TextView) convertView.findViewById(R.id.tvSqlApplyName);
            holder.tvSqlCarDate = (TextView) convertView.findViewById(R.id.tvSqlCarDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvSqlCarId.setText(getItem(position).getCode());
        holder.tvSqlApplyName.setText(getItem(position).getRecorder());
        holder.tvSqlCarDate.setText(getItem(position).getRecordt());
        return convertView;
    }

    class ViewHolder {
        TextView tvSqlCarId;
        TextView tvSqlApplyName;
        TextView tvSqlCarDate;
    }
}
