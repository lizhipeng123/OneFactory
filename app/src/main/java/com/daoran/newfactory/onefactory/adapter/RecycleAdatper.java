package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.SqlCarApplyBean;
import com.gan.myrecycleview.CommonAdapter;
import com.gan.myrecycleview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lizhipeng on 2017/3/27.
 */

public class RecycleAdatper extends BaseAdapter {


    public List<SqlCarApplyBean> list;
    public Context context;
    public LayoutInflater layoutInflater;

    public RecycleAdatper(Context context, List<SqlCarApplyBean> list) {
        this.context = context;
        if (list != null) {
            this.list = list;
        } else {
            this.list = new ArrayList<>();
        }
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_sqlcar, null);
            holder = new ViewHolder();
            holder.tvSqlCarId = (TextView) view.findViewById(R.id.tvSqlCarId);
            holder.tvSqlApplyName = (TextView) view.findViewById(R.id.tvSqlApplyName);
            holder.tvSqlCarDate = (TextView) view.findViewById(R.id.tvSqlCarDate);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.tvSqlCarId.setText("carid");
        holder.tvSqlApplyName.setText("name");
        holder.tvSqlCarDate.setText("date");
        return view;
    }

    static class ViewHolder {
        TextView tvSqlCarId, tvSqlApplyName, tvSqlCarDate;
    }
}
