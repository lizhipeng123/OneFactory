package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.production.ProductionNewlyComfigActivity;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;

/**
 * Created by lizhipeng on 2017/5/9.
 */

public class ProductionNewlyComfigAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private SharedPreferences sp;//轻量级存储
    private SPUtils spUtils;//保存在手机中的目录

    public ProductionNewlyComfigAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_production_data, null);
            viewHolder.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
            viewHolder.tvProDocumentary = (TextView) convertView.findViewById(R.id.tvProDocumentary);
            viewHolder.tvProFactory = (TextView) convertView.findViewById(R.id.tvProFactory);
            viewHolder.tvProDepartment = (TextView) convertView.findViewById(R.id.tvProDepartment);
            viewHolder.tvProProcedure = (TextView) convertView.findViewById(R.id.tvProProcedure);
            viewHolder.tvProOthers = (EditText) convertView.findViewById(R.id.tvProOthers);
            viewHolder.tvProSingularSystem = (TextView) convertView.findViewById(R.id.tvProSingularSystem);
            viewHolder.tvProColor = (TextView) convertView.findViewById(R.id.tvProColor);
            viewHolder.tvProTaskNumber = (EditText) convertView.findViewById(R.id.tvProTaskNumber);
            viewHolder.tvProSize = (TextView) convertView.findViewById(R.id.tvProSize);
            viewHolder.tvProClippingNumber = (TextView) convertView.findViewById(R.id.tvProClippingNumber);
            viewHolder.tvProCompletedLastMonth = (EditText) convertView.findViewById(R.id.tvProCompletedLastMonth);
            viewHolder.tvProTotalCompletion = (TextView) convertView.findViewById(R.id.tvProTotalCompletion);
            viewHolder.tvProBalanceAmount = (TextView) convertView.findViewById(R.id.tvProBalanceAmount);
            viewHolder.tvProState = (TextView) convertView.findViewById(R.id.tvProState);
            viewHolder.tvProYear = (TextView) convertView.findViewById(R.id.tvProYear);
            viewHolder.tvProMonth = (TextView) convertView.findViewById(R.id.tvProMonth);
            viewHolder.tvProOneDay = (EditText) convertView.findViewById(R.id.tvProOneDay);
            viewHolder.tvProTwoDay = (EditText) convertView.findViewById(R.id.tvProTwoDay);
            viewHolder.tvProThreeDay = (EditText) convertView.findViewById(R.id.tvProThreeDay);
            viewHolder.tvProForeDay = (EditText) convertView.findViewById(R.id.tvProForeDay);
            viewHolder.tvProFiveDay = (EditText) convertView.findViewById(R.id.tvProFiveDay);
            viewHolder.tvProSixDay = (EditText) convertView.findViewById(R.id.tvProSixDay);
            viewHolder.tvProSevenDay = (EditText) convertView.findViewById(R.id.tvProSevenDay);
            viewHolder.tvProEightDay = (EditText) convertView.findViewById(R.id.tvProEightDay);
            viewHolder.tvProNineDay = (EditText) convertView.findViewById(R.id.tvProNineDay);
            viewHolder.tvProTenDay = (EditText) convertView.findViewById(R.id.tvProTenDay);
            viewHolder.tvProElevenDay = (EditText) convertView.findViewById(R.id.tvProElevenDay);
            viewHolder.tvProTwelveDay = (EditText) convertView.findViewById(R.id.tvProTwelveDay);
            viewHolder.tvProThirteenDay = (EditText) convertView.findViewById(R.id.tvProThirteenDay);
            viewHolder.tvProFourteenDay = (EditText) convertView.findViewById(R.id.tvProFourteenDay);
            viewHolder.tvProFifteenDay = (EditText) convertView.findViewById(R.id.tvProFifteenDay);
            viewHolder.tvProSixteenDay = (EditText) convertView.findViewById(R.id.tvProSixteenDay);
            viewHolder.tvProSeventeenDay = (EditText) convertView.findViewById(R.id.tvProSeventeenDay);
            viewHolder.tvProEighteenDay = (EditText) convertView.findViewById(R.id.tvProEighteenDay);
            viewHolder.tvProNineteenDay = (EditText) convertView.findViewById(R.id.tvProNineteenDay);
            viewHolder.tvProTwentyDay = (EditText) convertView.findViewById(R.id.tvProTwentyDay);
            viewHolder.tvProTwentyOneDay = (EditText) convertView.findViewById(R.id.tvProTwentyOneDay);
            viewHolder.tvProTwentyTwoDay = (EditText) convertView.findViewById(R.id.tvProTwentyTwoDay);
            viewHolder.tvProTwentyThreeDay = (EditText) convertView.findViewById(R.id.tvProTwentyThreeDay);
            viewHolder.tvProTwentyForeDay = (EditText) convertView.findViewById(R.id.tvProTwentyForeDay);
            viewHolder.tvProTwentyFiveDay = (EditText) convertView.findViewById(R.id.tvProTwentyFiveDay);
            viewHolder.tvProTwentySixDay = (EditText) convertView.findViewById(R.id.tvProTwentySixDay);
            viewHolder.tvProTwentySevenDay = (EditText) convertView.findViewById(R.id.tvProTwentySevenDay);
            viewHolder.tvProTwentyEightDay = (EditText) convertView.findViewById(R.id.tvProTwentyEightDay);
            viewHolder.tvProTwentyNineDay = (EditText) convertView.findViewById(R.id.tvProTwentyNineDay);
            viewHolder.tvProThirtyDay = (EditText) convertView.findViewById(R.id.tvProThirtyDay);
            viewHolder.tvProThirtyOneDay = (EditText) convertView.findViewById(R.id.tvProThirtyOneDay);
            viewHolder.tvProRemarks = (EditText) convertView.findViewById(R.id.tvProRemarks);
            viewHolder.tvProRecorder = (TextView) convertView.findViewById(R.id.tvProRecorder);
            viewHolder.tvProRecordat = (TextView) convertView.findViewById(R.id.tvProRecordat);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        sp = context.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String tvnewlydate = sp.getString("tvnewlydate", "");//款号
        ToastUtils.ShowToastMessage("tvnewlydate", context);
        viewHolder.tv_data.setText(tvnewlydate);
        String tvnewlyDocumentary = sp.getString("tvnewlyDocumentary", "");//跟单
        String tvnewlyFactory = sp.getString("tvnewlyFactory", "");//工厂
        String tvnewlyDepartment = sp.getString("tvnewlyDepartment", "");//部门/组别
        String tvnewlyProcedure = sp.getString("tvnewlyProcedure", "");//工序
        String tvnewlyOthers = sp.getString("tvnewlyOthers", "");//组别人
        String tvnewSingularSystem = sp.getString("tvnewSingularSystem", "");//制单数
        String tvdate = sp.getString("tvdate", "");//任务数
        String tvnewTaskNumber = sp.getString("tvnewTaskNumber", "");//尺码
        String tvnewlySize = sp.getString("tvnewlySize", "");//花色
        String tvnewlyClippingNumber = sp.getString("tvnewlyClippingNumber", "");//实裁数
        String tvnewlyCompletedLastMonth = sp.getString("tvnewlyCompletedLastMonth", "");//总完工数
        String tvnewlyTotalCompletion = sp.getString("tvnewlyTotalCompletion", "");//状态
        return convertView;
    }

    class ViewHolder {
        TextView tv_data;//款号
        TextView tvProDocumentary,//跟单
                tvProFactory;//工厂
        TextView tvProDepartment,//部门/组别
                tvProState,//状态
                tvProProcedure;//工序
        EditText tvProOthers,//组别人
                tvProCompletedLastMonth,//上月完工
                tvProTaskNumber;//任务数;
        TextView tvProSingularSystem,//制单数
                tvProColor,//花色
                tvProSize,//尺码
                tvProMonth,//月
                tvProClippingNumber,//实裁数
                tvProTotalCompletion,//总完工数
                tvProBalanceAmount,//结余数量
                tvProYear;//年
        EditText tvProOneDay,//1日
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
                tvProRemarks;//备注
        TextView tvProRecorder,//制单人
                tvProRecordat;//制单时间
    }
}
