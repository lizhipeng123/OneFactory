package com.daoran.newfactory.onefactory.adapter.ftydladapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.ftydl.FTYDLSearchDetailActivity;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDailyBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.util.List;

/**
 * 生产日报主列表适配
 * Created by lizhipeng on 2017/4/26.
 */

public class FTYDLSearchAdapter extends BaseAdapter {
    private Context context;
    private List<FTYDLDailyBean.DataBean> dataBeen;
    private SPUtils spUtils;
    private int isprodure;
    private OnClickFTYDLSearchLinter mOnClickFTYDLSearchLinter;

    public FTYDLSearchAdapter(Context context, List<FTYDLDailyBean.DataBean> dataBeen
    ) {
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

    /*填充item*/
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_production_data, null);
            viewHolder.lin_content = (LinearLayout) convertView.findViewById(R.id.data_ll_vertical);
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
            viewHolder.tvProBalanceAmount = (TextView) convertView.findViewById(R.id.tvProBalanceAmount);
            viewHolder.tvProState = (TextView) convertView.findViewById(R.id.tvProState);
            viewHolder.tvProYear = (TextView) convertView.findViewById(R.id.tvProYear);
            viewHolder.tvProMonth = (TextView) convertView.findViewById(R.id.tvProMonth);
            viewHolder.tvProOneDay = (TextView) convertView.findViewById(R.id.tvProOneDay);
            viewHolder.tvProTwoDay = (TextView) convertView.findViewById(R.id.tvProTwoDay);
            viewHolder.tvProThreeDay = (TextView) convertView.findViewById(R.id.tvProThreeDay);
            viewHolder.tvProForeDay = (TextView) convertView.findViewById(R.id.tvProForeDay);
            viewHolder.tvProFiveDay = (TextView) convertView.findViewById(R.id.tvProFiveDay);
            viewHolder.tvProSixDay = (TextView) convertView.findViewById(R.id.tvProSixDay);
            viewHolder.tvProSevenDay = (TextView) convertView.findViewById(R.id.tvProSevenDay);
            viewHolder.tvProEightDay = (TextView) convertView.findViewById(R.id.tvProEightDay);
            viewHolder.tvProNineDay = (TextView) convertView.findViewById(R.id.tvProNineDay);
            viewHolder.tvProTenDay = (TextView) convertView.findViewById(R.id.tvProTenDay);
            viewHolder.tvProElevenDay = (TextView) convertView.findViewById(R.id.tvProElevenDay);
            viewHolder.tvProTwelveDay = (TextView) convertView.findViewById(R.id.tvProTwelveDay);
            viewHolder.tvProThirteenDay = (TextView) convertView.findViewById(R.id.tvProThirteenDay);
            viewHolder.tvProFourteenDay = (TextView) convertView.findViewById(R.id.tvProFourteenDay);
            viewHolder.tvProFifteenDay = (TextView) convertView.findViewById(R.id.tvProFifteenDay);
            viewHolder.tvProSixteenDay = (TextView) convertView.findViewById(R.id.tvProSixteenDay);
            viewHolder.tvProSeventeenDay = (TextView) convertView.findViewById(R.id.tvProSeventeenDay);
            viewHolder.tvProEighteenDay = (TextView) convertView.findViewById(R.id.tvProEighteenDay);
            viewHolder.tvProNineteenDay = (TextView) convertView.findViewById(R.id.tvProNineteenDay);
            viewHolder.tvProTwentyDay = (TextView) convertView.findViewById(R.id.tvProTwentyDay);
            viewHolder.tvProTwentyOneDay = (TextView) convertView.findViewById(R.id.tvProTwentyOneDay);
            viewHolder.tvProTwentyTwoDay = (TextView) convertView.findViewById(R.id.tvProTwentyTwoDay);
            viewHolder.tvProTwentyThreeDay = (TextView) convertView.findViewById(R.id.tvProTwentyThreeDay);
            viewHolder.tvProTwentyForeDay = (TextView) convertView.findViewById(R.id.tvProTwentyForeDay);
            viewHolder.tvProTwentyFiveDay = (TextView) convertView.findViewById(R.id.tvProTwentyFiveDay);
            viewHolder.tvProTwentySixDay = (TextView) convertView.findViewById(R.id.tvProTwentySixDay);
            viewHolder.tvProTwentySevenDay = (TextView) convertView.findViewById(R.id.tvProTwentySevenDay);
            viewHolder.tvProTwentyEightDay = (TextView) convertView.findViewById(R.id.tvProTwentyEightDay);
            viewHolder.tvProTwentyNineDay = (TextView) convertView.findViewById(R.id.tvProTwentyNineDay);
            viewHolder.tvProThirtyDay = (TextView) convertView.findViewById(R.id.tvProThirtyDay);
            viewHolder.tvProThirtyOneDay = (TextView) convertView.findViewById(R.id.tvProThirtyOneDay);
            viewHolder.tvProRemarks = (TextView) convertView.findViewById(R.id.tvProRemarks);
            viewHolder.tvProRecorder = (TextView) convertView.findViewById(R.id.tvProRecorder);
            viewHolder.tvProRecordat = (TextView) convertView.findViewById(R.id.tvProRecordat);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvProDocumentary.setText(getItem(position).getPrddocumentary());
        viewHolder.tvProFactory.setText(getItem(position).getSubfactory());
        viewHolder.tvProDepartment.setText(getItem(position).getSubfactoryTeams());
        viewHolder.tvProProcedure.setText(getItem(position).getWorkingProcedure());
        viewHolder.tvProOthers.setText(getItem(position).getWorkers());
        viewHolder.tvProSingularSystem.setText(String.valueOf(getItem(position).getPqty()));
        viewHolder.tvProColor.setText(getItem(position).getProdcol());
        viewHolder.tvProTaskNumber.setText(String.valueOf(getItem(position).getTaskqty()));
        viewHolder.tvProSize.setText(getItem(position).getMdl());
        viewHolder.tvProClippingNumber.setText(String.valueOf(getItem(position).getFactcutqty()));
        viewHolder.tvProCompletedLastMonth.setText(String.valueOf(getItem(position).getLastMonQty()));
        viewHolder.tvProTotalCompletion.setText(String.valueOf(getItem(position).getSumCompletedQty()));
        viewHolder.tvProBalanceAmount.setText(String.valueOf(getItem(position).getLeftQty()));
        viewHolder.tvProState.setText(getItem(position).getPrdstatus());
        viewHolder.tvProYear.setText(String.valueOf(getItem(position).getYear()));
        viewHolder.tvProMonth.setText(String.valueOf(getItem(position).getMonth()));
        viewHolder.tvProOneDay.setText(getItem(position).getDay1());
        viewHolder.tvProTwoDay.setText(getItem(position).getDay2());
        viewHolder.tvProThreeDay.setText(getItem(position).getDay3());
        viewHolder.tvProForeDay.setText(getItem(position).getDay4());
        viewHolder.tvProFiveDay.setText(getItem(position).getDay5());
        viewHolder.tvProSixDay.setText(getItem(position).getDay6());
        viewHolder.tvProSevenDay.setText(getItem(position).getDay7());
        viewHolder.tvProEightDay.setText(getItem(position).getDay8());
        viewHolder.tvProNineDay.setText(getItem(position).getDay9());
        viewHolder.tvProTenDay.setText(getItem(position).getDay10());
        viewHolder.tvProElevenDay.setText(getItem(position).getDay11());
        viewHolder.tvProTwelveDay.setText(getItem(position).getDay12());
        viewHolder.tvProThirteenDay.setText(getItem(position).getDay13());
        viewHolder.tvProFourteenDay.setText(getItem(position).getDay14());
        viewHolder.tvProFifteenDay.setText(getItem(position).getDay15());
        viewHolder.tvProSixteenDay.setText(getItem(position).getDay16());
        viewHolder.tvProSeventeenDay.setText(getItem(position).getDay17());
        viewHolder.tvProEighteenDay.setText(getItem(position).getDay18());
        viewHolder.tvProNineteenDay.setText(getItem(position).getDay19());
        viewHolder.tvProTwentyDay.setText(getItem(position).getDay20());
        viewHolder.tvProTwentyOneDay.setText(getItem(position).getDay21());
        viewHolder.tvProTwentyTwoDay.setText(getItem(position).getDay22());
        viewHolder.tvProTwentyThreeDay.setText(getItem(position).getDay23());
        viewHolder.tvProTwentyForeDay.setText(getItem(position).getDay24());
        viewHolder.tvProTwentyFiveDay.setText(getItem(position).getDay25());
        viewHolder.tvProTwentySixDay.setText(getItem(position).getDay26());
        viewHolder.tvProTwentySevenDay.setText(getItem(position).getDay27());
        viewHolder.tvProTwentyEightDay.setText(getItem(position).getDay28());
        viewHolder.tvProTwentyNineDay.setText(getItem(position).getDay29());
        viewHolder.tvProThirtyDay.setText(getItem(position).getDay30());
        viewHolder.tvProThirtyOneDay.setText(getItem(position).getDay31());
        viewHolder.tvProRemarks.setText(getItem(position).getMemo());
        viewHolder.tvProRecorder.setText(getItem(position).getRecorder());
        viewHolder.tvProRecordat.setText(getItem(position).getRecordat());
        if(mOnClickFTYDLSearchLinter!=null){
            viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickFTYDLSearchLinter.MyFTYDLSearchLinter(position);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        LinearLayout lin_content;
        TextView tvProDocumentary,//跟单
                tvProFactory,//工厂
                tvProDepartment,//部门/组别
                tvProState,//状态
                tvProProcedure,//工序
                tvProOthers, //组别人
                tvProCompletedLastMonth,//上月完工
                tvProTaskNumber,//任务数;
                tvProSingularSystem,//制单数
                tvProColor,//花色
                tvProSize,//尺码
                tvProMonth,//月
                tvProClippingNumber,//实裁数
                tvProTotalCompletion,//总完工数
                tvProBalanceAmount,//结余数量
                tvProYear,//年
                tvProOneDay,//1日
                tvProTwoDay,//2
                tvProThreeDay,//3
                tvProForeDay,//4
                tvProFiveDay,//5
                tvProSixDay,//6
                tvProSevenDay,//7
                tvProEightDay,//8
                tvProNineDay,//9
                tvProTenDay,//10
                tvProElevenDay,//11
                tvProTwelveDay,//12
                tvProThirteenDay,//13
                tvProFourteenDay,//14
                tvProFifteenDay,//15
                tvProSixteenDay,//16
                tvProSeventeenDay,//17
                tvProEighteenDay,//18
                tvProNineteenDay,//19
                tvProTwentyDay,//20
                tvProTwentyOneDay,//21
                tvProTwentyTwoDay,//22
                tvProTwentyThreeDay,//23
                tvProTwentyForeDay,//24
                tvProTwentyFiveDay,//25
                tvProTwentySixDay,//26
                tvProTwentySevenDay,//27
                tvProTwentyEightDay,//28
                tvProTwentyNineDay,//29
                tvProThirtyDay,//30
                tvProThirtyOneDay,//31
                tvProRemarks,//备注
                tvProRecorder,//制单人
                tvProRecordat;//制单时间
    }

    /*创建回调函数，实例化接口具体化此方法*/
    public interface OnClickFTYDLSearchLinter{
        void MyFTYDLSearchLinter(int id);//创建回调函数
    }

    public void setmOnClickFTYDLSearchLinter(OnClickFTYDLSearchLinter mOnClickFTYDLSearchLinter) {
        this.mOnClickFTYDLSearchLinter = mOnClickFTYDLSearchLinter;
    }
}