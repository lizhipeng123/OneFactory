package com.daoran.newfactory.onefactory.adapter.reqcaradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.reqcarbean.ReqCarDailyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 用车申请单刷新适配
 * Created by lizhipeng on 2017/4/19.
 */

public class ReqCarSearchAdapter extends BaseAdapter {
    private List<ReqCarDailyBean.DataBean> dataBeen;//用车单实体集合
    private Context context;//上下文

    public ReqCarSearchAdapter(Context context) {
        this.dataBeen = new ArrayList<>();
        this.context = context;
    }

    //清除数据
    public void clear() {
        dataBeen.clear();//清除数据
        notifyDataSetChanged();//重新加载
    }

    //新增数据
    public void addAll(List<ReqCarDailyBean.DataBean> _c) {
        dataBeen.addAll(_c);//新增数据
        notifyDataSetChanged();//重新加载
    }

    //替换数据
    public void replaceAll(List<ReqCarDailyBean.DataBean> _c) {
        dataBeen.clear();//清除数据
        addAll(_c);//新增数据
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public ReqCarDailyBean.DataBean getItem(int position) {
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
            holder = new ViewHolder();//实例化相关信息
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sqlcar, null);
            holder.tvSqlCarId = (TextView) convertView.findViewById(R.id.tvSqlCarId);
            holder.tvSqlApplyName = (TextView) convertView.findViewById(R.id.tvSqlApplyName);
            holder.tvSqlCarDate = (TextView) convertView.findViewById(R.id.tvSqlCarDate);
            convertView.setTag(holder);//将查找的view缓存起来可以多次使用，表示给view添加一个额外的数据
        } else {
            holder = (ViewHolder) convertView.getTag();//将数据取出来
        }
        holder.tvSqlCarId.setText(getItem(position).getCode());//编号
        holder.tvSqlApplyName.setText(getItem(position).getRecorder());//申请人
        holder.tvSqlCarDate.setText(getItem(position).getRecordt());//出车日期
        return convertView;
    }

    class ViewHolder {
        TextView tvSqlCarId;
        TextView tvSqlApplyName;
        TextView tvSqlCarDate;
    }
}