package com.daoran.newfactory.onefactory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhipeng on 2017/4/19.
 */

public class DebugSignDetailAdapter
        extends RecyclerView.Adapter<DebugSignDetailAdapter.SignViewHolder> {
    public static final int IS_IFRST = 1;
    private List<String> datas;


    public DebugSignDetailAdapter() {
        this.datas = new ArrayList<>();
    }

    @Override
    public SignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.debug_item_data,parent,false);
        return new SignViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public void onBindViewHolder(final SignViewHolder holder, final int position) {
        holder.tvData.setText(datas.get(position));
        holder.tvsignName.setText(datas.get(position));
        holder.tvClasses.setText(datas.get(position));
        holder.tvSignData.setText(datas.get(position));
        holder.tvSignAddre.setText(datas.get(position));
        holder.tvSignRemarks.setText(datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), ""+datas.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    class SignViewHolder extends RecyclerView.ViewHolder {
        TextView tvData;
        TextView tvsignName;
        TextView tvClasses;
        TextView tvSignData;
        TextView tvSignAddre;
        TextView tvSignRemarks;
        public SignViewHolder(View itemView) {
            super(itemView);
            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            tvsignName = (TextView) itemView.findViewById(R.id.tvsignName);
            tvClasses = (TextView) itemView.findViewById(R.id.tvClasses);
            tvSignData = (TextView) itemView.findViewById(R.id.tvSignData);
            tvSignAddre = (TextView) itemView.findViewById(R.id.tvSignAddre);
            tvSignRemarks = (TextView) itemView.findViewById(R.id.tvSignRemarks);
        }
    }

    /**
     * 初始化数据
     */
    public void refresh() {
        datas.clear();
        for(int i = 0;i<30;i++){

            datas.add(i+"");
        }
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     */
    public void add(){
        int length = datas.size();
        for(int i=length ;i<length+20;i++){
            datas.add(i+"");
        }
        notifyDataSetChanged();
    }
}
