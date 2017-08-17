package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.PersionInfo;

import java.util.List;

/**
 * Created by lizhipeng on 2017/8/17.
 */

public class SideAdatper extends BaseAdapter {
    private Context context;
    private List<PersionInfo> infoList;
    private PersionInfo info;

    public SideAdatper(Context context, List<PersionInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_side, null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        info = infoList.get(position);
        tv.setText(info.getNameString());
        if (info.isChick()) {
            convertView.setBackgroundResource(R.drawable.register_title_bg);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv;
    }
}
