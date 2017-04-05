package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.DebugDetailActivity;
import com.daoran.newfactory.onefactory.bean.SignBean;

import java.util.List;

/**
 * Created by lizhipeng on 2017/4/5.
 */

public class SignDetailAdapter extends BaseAdapter {
    private List<SignBean.Data> signdata;
    private Context context;

    public SignDetailAdapter(List<SignBean.Data> signdata, Context context) {
        this.signdata = signdata;
        this.context = context;
    }

    @Override
    public int getCount() {
        return signdata.size();
    }

    @Override
    public Object getItem(int position) {
        return signdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        SignBean.Data data = signdata.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.debug_item_data, null);
            holder.tvData = (TextView) convertView.findViewById(R.id.tv_data);
            holder.tvsignName = (TextView) convertView.findViewById(R.id.tvsignName);
            holder.tvClasses = (TextView) convertView.findViewById(R.id.tvClasses);
            holder.tvSignData = (TextView) convertView.findViewById(R.id.tvSignData);
            holder.tvSignAddre = (TextView) convertView.findViewById(R.id.tvSignAddre);
            holder.tvSignRemarks = (TextView) convertView.findViewById(R.id.tvSignRemarks);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvData.setText(data.getCode());
        holder.tvsignName.setText(data.getRecorder());
        holder.tvClasses.setText(data.getRegedittyp());
        holder.tvSignData.setText(data.getRecordat());
        holder.tvSignAddre.setText(data.getRecordplace());
        holder.tvSignRemarks.setText(data.getMemo());
        return convertView;
    }

    class ViewHolder {
        TextView tvData;
        TextView tvsignName;
        TextView tvClasses;
        TextView tvSignData;
        TextView tvSignAddre;
        TextView tvSignRemarks;
    }
}
