package com.daoran.newfactory.onefactory.adapter.ftydladapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.ftydl.FTYDLNewlyComfigDetailActivity;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLFactoryDailyBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.util.List;

/**
 * 款号选择左侧编号适配
 * Created by lizhipeng on 2017/6/23.
 */

public class FTYDLNewlyBuildLeftAdapter extends BaseAdapter {
    private Context context;
    private List<FTYDLFactoryDailyBean.DataBean> dataBeen;//初始化全部的数据
    private OnClickNewlyBuildLister mOnClickNewlyBuildLister;
    private SPUtils spUtils;
    private boolean flag = false;
    private int isprodure;

    public FTYDLNewlyBuildLeftAdapter(Context context
            , List<FTYDLFactoryDailyBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public FTYDLFactoryDailyBean.DataBean getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_left_adapter, null);
            holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            holder.lin_content = (RelativeLayout) convertView.findViewById(R.id.lin_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String productionItem = getItem(position).getItem();
        holder.tvLeft.setText(productionItem);
        if(mOnClickNewlyBuildLister!=null){
            holder.lin_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickNewlyBuildLister.myNewlyBuildClick(position);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tvLeft;
        RelativeLayout lin_content;
    }

    /*创建回调函数,实例化接口具体化此方法*/
    public interface OnClickNewlyBuildLister{
        void myNewlyBuildClick(int id);
    }

    /*注册函数*/
    public void setmOnClickNewlyBuildLister(OnClickNewlyBuildLister mOnClickNewlyBuildLister) {
        this.mOnClickNewlyBuildLister = mOnClickNewlyBuildLister;
    }
}