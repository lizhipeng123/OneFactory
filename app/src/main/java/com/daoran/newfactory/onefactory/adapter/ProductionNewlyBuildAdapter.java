package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
 * 生产日报新建适配
 * Created by lizhipeng on 2017/5/5.
 */
public class ProductionNewlyBuildAdapter extends BaseAdapter {
    private Context context;
    private List<ProNewlyBuildBean.DataBean> dataBeen;
    private int selectItem = -1;
    private SharedPreferences sp;
    private SPUtils spUtils;
    private boolean flag = false;
    private int isprodure;

    public ProductionNewlyBuildAdapter(Context context, List<ProNewlyBuildBean.DataBean> dataBeen) {
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

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_production_newlybuild, null);
//            viewHolder.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
            viewHolder.tvProDocumentary = (TextView) convertView.findViewById(R.id.tvProDocumentary);
            viewHolder.tvProFactory = (TextView) convertView.findViewById(R.id.tvProFactory);
            viewHolder.tvProDepartment = (TextView) convertView.findViewById(R.id.tvProDepartment);
            viewHolder.tvProProcedure = (TextView) convertView.findViewById(R.id.tvProProcedure);
            viewHolder.tvProOthers = (TextView) convertView.findViewById(R.id.tvProOthers);
            viewHolder.tvProSingularSystem = (TextView) convertView.findViewById(R.id.tvProSingularSystem);
            viewHolder.tvProColor = (TextView) convertView.findViewById(R.id.tvProColor);
            viewHolder.tvProTaskNumber = (TextView) convertView.findViewById(R.id.tvProTaskNumber);
            viewHolder.tvProSize = (TextView) convertView.findViewById(R.id.tvProSize);
            viewHolder.tvProClippingNumber = (TextView) convertView.findViewById(R.id.tvProClippingNumber);
            viewHolder.tvProCompletedLastMonth = (TextView) convertView.findViewById(R.id.tvProCompletedLastMonth);
            viewHolder.tvProTotalCompletion = (TextView) convertView.findViewById(R.id.tvProTotalCompletion);
            viewHolder.lin_content = (LinearLayout) convertView.findViewById(R.id.lin_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.tv_data.setText(getItem(position).getItem());
        viewHolder.tvProDocumentary.setText(getItem(position).getPrddocumentary());
        viewHolder.tvProFactory.setText(getItem(position).getSubfactory());
        viewHolder.tvProDepartment.setText(getItem(position).getSubfactoryTeams());
        viewHolder.tvProProcedure.setText(getItem(position).getWorkingProcedure());
        viewHolder.tvProOthers.setText(getItem(position).getWorkers());
        viewHolder.tvProSingularSystem.setText(getItem(position).getPqty());
        viewHolder.tvProColor.setText(getItem(position).getTaskqty());
        viewHolder.tvProTaskNumber.setText(getItem(position).getMdl());
        viewHolder.tvProSize.setText(getItem(position).getProdcol());
        viewHolder.tvProClippingNumber.setText(getItem(position).getFactcutqty());
        viewHolder.tvProCompletedLastMonth.setText(getItem(position).getSumCompletedQty());
        viewHolder.tvProTotalCompletion.setText(getItem(position).getPrdstatus());
        if (selectItem == position) {
            viewHolder.lin_content.setBackgroundColor(Color.YELLOW);
        } else {
            viewHolder.lin_content.setBackgroundColor(Color.TRANSPARENT);
        }
        viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String getid = String.valueOf(v.getId());
//                List<String> stingid = new ArrayList<String>();
//                stingid.add(getid);
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

                viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_item);

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
        TextView tv_data,//款号
                tvProDocumentary,//跟单
                tvProFactory,//工厂
                tvProDepartment,//部门、组别
                tvProProcedure,//工序
                tvProOthers,//组别人数
                tvProSingularSystem,//制单数
                tvProColor,//任务数
                tvProTaskNumber,//尺码
                tvProSize,//花色
                tvProClippingNumber,//实裁数
                tvProCompletedLastMonth,//总完工数
                tvProTotalCompletion;//状态
        LinearLayout lin_content;
    }
}