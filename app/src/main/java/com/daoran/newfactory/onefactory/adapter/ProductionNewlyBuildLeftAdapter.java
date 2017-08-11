package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.production.ProductionNewlyComfigActivity;
import com.daoran.newfactory.onefactory.bean.ProNewlyBuildBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PhoneSaveUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 款号选择左侧编号适配
 * Created by lizhipeng on 2017/6/23.
 */

public class ProductionNewlyBuildLeftAdapter extends BaseAdapter {
    private Context context;
    private List<ProNewlyBuildBean.DataBean> dataBeen;//初始化全部的数据
    private SharedPreferences sp;
    private SPUtils spUtils;
    private boolean flag = false;
    private int isprodure;

    public ProductionNewlyBuildLeftAdapter(Context context
            , List<ProNewlyBuildBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public ProNewlyBuildBean.DataBean getItem(int position) {
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
            holder.lin_content = (LinearLayout) convertView.findViewById(R.id.lin_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String productionItem = getItem(position).getItem();
        holder.tvLeft.setText(productionItem);

        holder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = context.getSharedPreferences("my_sp", 0);
                String salesid = getItem(position).getID();
                String said = getItem(position).getSalesid();
                spUtils.put(context, "tvnewlysalesid", salesid);
                flag = true;
                spUtils.put(context, "tvflag", flag);
                String tvdate = getItem(position).getItem();
                spUtils.put(context, "tvnewlydate", tvdate);//款号
                String tvProDocumentary = getItem(position).getPrddocumentary();
                spUtils.put(context, "tvnewlyDocumentary", tvProDocumentary);//跟单
                String tvProFactory = getItem(position).getSubfactory();
                spUtils.put(context, "tvnewlyFactory", tvProFactory);//工厂
                String tvProDepartment = getItem(position).getSubfactoryTeams();
                spUtils.put(context, "tvnewlyDepartment", tvProDepartment);//部门
                String tvProProcedure = getItem(position).getWorkingProcedure();
                spUtils.put(context, "tvnewlyProcedure", tvProProcedure);//工序
                if (tvProProcedure.equals("裁床")) {
                    isprodure = 1;
                    spUtils.put(context, "isprodure", String.valueOf(isprodure));
                } else {
                    isprodure = 0;
                    spUtils.put(context, "isprodure", String.valueOf(isprodure));
                }
                String tvProOthers = getItem(position).getWorkers();
                spUtils.put(context, "tvnewlyOthers", tvProOthers);//组别人数

                String tvProSingularSystem = getItem(position).getPqty();
                spUtils.put(context, "tvnewSingularSystem", tvProSingularSystem);//制单数

                String tvProColor = getItem(position).getTaskqty();
                spUtils.put(context, "tvColorTaskqty", tvProColor);//任务数

                String tvProTaskNumber = getItem(position).getMdl();
                spUtils.put(context, "tvnewTaskNumber", tvProTaskNumber);//尺码

                String tvProSize = getItem(position).getProdcol();
                if (tvProSize.contains("/")) {
                    System.out.print(tvProSize);
                    String[] temp = null;
                    temp = tvProSize.split("/");
                    System.out.print(temp);
                    List<String> list = Arrays.asList(temp);
                    System.out.print(list);
                    SharedPreferences spes = context.getSharedPreferences("mylist", 0);
                    SharedPreferences.Editor editor = spes.edit();
                    try {
                        String liststr = PhoneSaveUtil.SceneList2String(list);
                        editor.putString("mylistStr", liststr);
                        spUtils.put(context, "tvnewlySize", tvProSize);//花色
                        editor.commit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.print(tvProSize);
                    spUtils.put(context, "tvnewlySize", tvProSize);//花色
                }

                String tvProClippingNumber = getItem(position).getFactcutqty();
                spUtils.put(context, "tvnewlyClippingNumber", tvProClippingNumber);//实裁数

                String tvProCompletedLastMonth = getItem(position).getSumCompletedQty();
                spUtils.put(context, "tvnewlyCompletedLastMonth", tvProCompletedLastMonth);//总完工数

                String tvProTotalCompletion = getItem(position).getPrdstatus();
                spUtils.put(context, "tvnewlyTotalCompletion", tvProTotalCompletion);//状态
                holder.lin_content.setBackgroundResource(R.drawable.bill_record_item);
                System.out.print("");
                String itemm = sp.getString("tvnewlydate", "");
                /*从点击item中进入新建数据界面*/
                Intent intent = new Intent(context,
                        ProductionNewlyComfigActivity.class);
                context.startActivity(intent);
                System.out.print(itemm);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvLeft;
        LinearLayout lin_content;
    }
}
