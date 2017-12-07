package com.daoran.newfactory.onefactory.adapter.ftydladapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.ftydl.FTYDLNewlyComfigDetailActivity;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLFactoryDailyBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.util.List;

/**
 * 生产日报新建适配
 * Created by lizhipeng on 2017/5/5.
 */
public class FTYDLNewlyBuildAdapter extends BaseAdapter {
    private Context context;
    private List<FTYDLFactoryDailyBean.DataBean> dataBeen;
    private int selectItem = -1;
    private SPUtils spUtils;
    private boolean flag = false;
    private AlertDialog alertDialog;
    private int isprodure;

    public FTYDLNewlyBuildAdapter(Context context,
                                  List<FTYDLFactoryDailyBean.DataBean> dataBeen) {
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

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_production_newlybuild, null);
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
        //长按弹出花色具体信息
        viewHolder.tvProSize.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(getItem(position).getProdcol());
                builder.setNegativeButton("退出",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
                return false;
            }
        });
        //长按弹出尺码具体信息
        viewHolder.tvProTaskNumber.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(getItem(position).getMdl());
                builder.setNegativeButton("退出",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
                return false;
            }
        });
        viewHolder.tvProSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salesid = getItem(position).getID();
                spUtils.put(context, "tvFTYDLNewlyId", salesid);
                String said = getItem(position).getSalesid();
                spUtils.put(context,"tvFTYDLNewlySalesId",said);
                flag = true;
                spUtils.put(context, "FTYDLNewly", flag);
                String tvdate = getItem(position).getItem();
                spUtils.put(context, "tvFTYDLNewlyItem", tvdate);//款号
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
                String tvProOthers = getItem(position).getWorkers();
                spUtils.put(context, "tvFTYDLNewlyWorkers", tvProOthers);//组别人数

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

                viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_item);
                /*从点击item中进入新建数据界面*/
                Intent intent = new Intent(context,
                        FTYDLNewlyComfigDetailActivity.class);
                context.startActivity(intent);
            }
        });

        viewHolder.tvProTaskNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salesid = getItem(position).getID();
                String said = getItem(position).getSalesid();
                spUtils.put(context, "tvFTYDLNewlyId", salesid);
                spUtils.put(context,"tvFTYDLNewlySalesId",said);
                flag = true;
                spUtils.put(context, "FTYDLNewly", flag);
                String tvdate = getItem(position).getItem();
                spUtils.put(context, "tvFTYDLNewlyItem", tvdate);//款号
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
                String tvProOthers = getItem(position).getWorkers();
                spUtils.put(context, "tvFTYDLNewlyWorkers", tvProOthers);//组别人数

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

                viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_item);
                /*从点击item中进入新建数据界面*/
                Intent intent = new Intent(context,
                        FTYDLNewlyComfigDetailActivity.class);
                context.startActivity(intent);
            }
        });

        viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String salesid = getItem(position).getID();
                spUtils.put(context, "tvFTYDLNewlyId", salesid);
                String said = getItem(position).getSalesid();
                spUtils.put(context,"tvFTYDLNewlySalesId",said);
                flag = true;
                spUtils.put(context, "FTYDLNewly", flag);
                String tvdate = getItem(position).getItem();
                spUtils.put(context, "tvFTYDLNewlyItem", tvdate);//款号
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
                String tvProOthers = getItem(position).getWorkers();
                spUtils.put(context, "tvFTYDLNewlyWorkers", tvProOthers);//组别人数

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

                viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_item);
                /*从点击item中进入新建数据界面*/
                Intent intent = new Intent(context,
                        FTYDLNewlyComfigDetailActivity.class);
                context.startActivity(intent);
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