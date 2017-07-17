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
 * 用车申请单刷新适配
 * Created by lizhipeng on 2017/4/19.
 */

public class SqlCarApplyAdapter extends BaseAdapter {
    private List<SqlCarApplyBean.DataBean> dataBeen;
    private Context context;

    public SqlCarApplyAdapter(Context context) {
        this.dataBeen = new ArrayList<>();
        this.context = context;
    }

    public void clear() {
        dataBeen.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<SqlCarApplyBean.DataBean> _c) {
        dataBeen.addAll(_c);
        notifyDataSetChanged();
    }

    public void replaceAll(List<SqlCarApplyBean.DataBean> _c) {
        dataBeen.clear();
        addAll(_c);
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
        SqlCarApplyAdapter.ViewHolder holder;
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