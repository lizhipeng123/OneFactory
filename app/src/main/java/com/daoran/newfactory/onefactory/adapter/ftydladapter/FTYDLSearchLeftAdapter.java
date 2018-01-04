package com.daoran.newfactory.onefactory.adapter.ftydladapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDailyBean;

import java.util.List;

/**
 * 生产日报左侧列表适配
 * Created by lizhipeng on 2017/6/22.
 */

public class FTYDLSearchLeftAdapter extends BaseAdapter {
    private List<FTYDLDailyBean.DataBean> dataBeen;
    private Context context;
    private OnClickFTYDLCopyLinter mOnClickFTYDLCopyLinter;

    public FTYDLSearchLeftAdapter(Context context, List<FTYDLDailyBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public FTYDLDailyBean.DataBean getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_left_adapter, null);
            viewHolder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            viewHolder.lin_content = (RelativeLayout) convertView.findViewById(R.id.lin_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String productionItem = getItem(position).getItem();
        viewHolder.tvLeft.setText(productionItem);

        if(mOnClickFTYDLCopyLinter!=null){
            viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickFTYDLCopyLinter.MyFTYDLCopyLinter(position);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tvLeft;
        RelativeLayout lin_content;
    }

    public interface OnClickFTYDLCopyLinter{
        void MyFTYDLCopyLinter(int id);//创建回调函数
    }

    public void setmOnClickFTYDLCopyLinter(OnClickFTYDLCopyLinter mOnClickFTYDLCopyLinter) {
        this.mOnClickFTYDLCopyLinter = mOnClickFTYDLCopyLinter;
    }
}