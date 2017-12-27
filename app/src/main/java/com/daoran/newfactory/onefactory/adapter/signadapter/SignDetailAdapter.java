package com.daoran.newfactory.onefactory.adapter.signadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.signbean.SignDailyBean;

import java.util.List;

/**
 * 签到查询适配
 * Created by lizhipeng on 2017/4/5.
 */

public class SignDetailAdapter extends BaseAdapter {
    private List<SignDailyBean.DataBean> signdata;
    private Context context;

    public SignDetailAdapter(List<SignDailyBean.DataBean> signdata, Context context) {
        this.signdata = signdata;
        this.context = context;
    }

    @Override
    public int getCount() {
        return signdata.size();
    }

    @Override
    public SignDailyBean.DataBean getItem(int position) {
        return signdata.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_data_signdetail_adapter, null);
            holder.tvsignName = (TextView) convertView.findViewById(R.id.tvsignName);
            holder.tvClasses = (TextView) convertView.findViewById(R.id.tvClasses);
            holder.tvSignData = (TextView) convertView.findViewById(R.id.tvSignData);
            holder.tvSignAddre = (TextView) convertView.findViewById(R.id.tvSignAddre);
            holder.tvSignRemarks = (TextView) convertView.findViewById(R.id.tvSignRemarks);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvsignName.setText(getItem(position).getRecorder());
        holder.tvClasses.setText(getItem(position).getRegedittyp());
        holder.tvSignData.setText(getItem(position).getRecordat());
        holder.tvSignAddre.setText(getItem(position).getRecordplace());
        holder.tvSignRemarks.setText(getItem(position).getMemo());
        return convertView;
    }

    static class ViewHolder {
        TextView tvsignName;
        TextView tvClasses;
        TextView tvSignData;
        TextView tvSignAddre;
        TextView tvSignRemarks;
    }
}