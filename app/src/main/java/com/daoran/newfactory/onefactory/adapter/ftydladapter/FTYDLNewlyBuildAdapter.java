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
    private OnClickNewlyBuildLister mOnClickNewlyBuildLister;

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
//        //长按弹出花色具体信息
//        viewHolder.tvProSize.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage(getItem(position).getProdcol());
//                builder.setNegativeButton("退出",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog = builder.create();
//                alertDialog.setCanceledOnTouchOutside(false);
//                alertDialog.show();
//                return false;
//            }
//        });
//        //长按弹出尺码具体信息
//        viewHolder.tvProTaskNumber.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage(getItem(position).getMdl());
//                builder.setNegativeButton("退出",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog = builder.create();
//                alertDialog.setCanceledOnTouchOutside(false);
//                alertDialog.show();
//                return false;
//            }
//        });
        if (mOnClickNewlyBuildLister != null) {
            viewHolder.tvProTaskNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickNewlyBuildLister.myNewlyBuildClick(position);
                }
            });
        }

        if (mOnClickNewlyBuildLister != null) {
            viewHolder.tvProSize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickNewlyBuildLister.myNewlyBuildClick(position);
                }
            });
        }

        if (mOnClickNewlyBuildLister != null) {
            viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickNewlyBuildLister.myNewlyBuildClick(position);
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvProDocumentary,//跟单
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

    /*创建回调函数,实例化接口具体化此方法*/
    public interface OnClickNewlyBuildLister {
        void myNewlyBuildClick(int id);
    }

    /*注册函数*/
    public void setmOnClickNewlyBuildLister(OnClickNewlyBuildLister mOnClickNewlyBuildLister) {
        this.mOnClickNewlyBuildLister = mOnClickNewlyBuildLister;
    }
}