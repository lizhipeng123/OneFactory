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

        holder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salesid = getItem(position).getID();
                spUtils.put(context, "tvFTYDLNewlyId", salesid);
                String said = getItem(position).getSalesid();
                spUtils.put(context,"tvFTYDLNewlySalesId",said);
                flag = true;
                spUtils.put(context, "tvFTYDLNewlyflag", flag);
                String tvItem = getItem(position).getItem();
                spUtils.put(context, "tvFTYDLNewlyItem", tvItem);//款号
                String tvProDocumentary = getItem(position).getPrddocumentary();
                spUtils.put(context, "tvFTYDLNewlyDocumentary", tvProDocumentary);//跟单
                String tvProDocumentaryid = getItem(position).getPrddocumentaryid();//跟单人id
                spUtils.put(context,"tvFTYDLNewlyDocumentaryId",tvProDocumentaryid);
                String tvProFactory = getItem(position).getSubfactory();
                spUtils.put(context, "tvFTYDLNewlyFactory", tvProFactory);//工厂
                String tvProDepartment = getItem(position).getSubfactoryTeams();
                spUtils.put(context, "tvFTYDLNewlyDepartment", tvProDepartment);//部门
                String tvProProcedure = getItem(position).getWorkingProcedure();
                spUtils.put(context, "tvFTYDLNewlyProcedure", tvProProcedure);//工序
                if (tvProProcedure.equals("裁床")) {
                    isprodure = 1;
                    spUtils.put(context, "FTYDLNewlyIsProdure", String.valueOf(isprodure));
                } else if (tvProProcedure.equals("选择工序")) {
                    ToastUtils.ShowToastMessage("选择工序后再新建", context);
                    return;
                } else {
                    isprodure = 0;
                    spUtils.put(context, "FTYDLNewlyIsProdure", String.valueOf(isprodure));
                }
                String tvProWorkers = getItem(position).getWorkers();
                spUtils.put(context, "tvFTYDLNewlyWorkers", tvProWorkers);//组别人数

                String tvProPqty = getItem(position).getPqty();
                spUtils.put(context, "tvFTYDLNewlyPqty", tvProPqty);//制单数

                String tvProTaskqty = getItem(position).getTaskqty();
                spUtils.put(context, "tvFTYDLNewlyTaskqty", tvProTaskqty);//任务数

                String tvProMdl = getItem(position).getMdl();
                spUtils.put(context, "tvFTYDLNewlyMdl", tvProMdl);//尺码

                String tvProProdcol = getItem(position).getProdcol();
                spUtils.put(context, "tvFTYDLNewlyProcal", tvProProdcol);//花色

                String tvctmtxt = getItem(position).getCtmtxt();
                spUtils.put(context,"tvFTYDLNewlyCtmtxt",tvctmtxt);//客户

                String tvProFactcutqty = getItem(position).getFactcutqty();
                spUtils.put(context, "tvFTYDLNewlyFactcutqty", tvProFactcutqty);//实裁数

                String tvProSumCompletedQty = getItem(position).getSumCompletedQty();
                spUtils.put(context, "tvFTYDLNewlySumCompletedQty", tvProSumCompletedQty);//总完工数

                String tvProPrdstatus = getItem(position).getPrdstatus();
                spUtils.put(context, "tvFTYDLNewlyPrdstatus", tvProPrdstatus);//状态

                holder.lin_content.setBackgroundResource(R.drawable.bill_record_item);

                /*从点击item中进入新建数据界面*/
                Intent intent = new Intent(context,
                        FTYDLNewlyComfigDetailActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvLeft;
        RelativeLayout lin_content;
    }
}