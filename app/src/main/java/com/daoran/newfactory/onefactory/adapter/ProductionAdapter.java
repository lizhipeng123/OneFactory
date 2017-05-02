package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;

import java.util.List;

/**
 * 生产日报适配器
 * Created by lizhipeng on 2017/4/26.
 */

public class ProductionAdapter extends BaseAdapter {
    private Context context;
    private List<ProducationDetailBean.DataBean> dataBeen;
    private SharedPreferences sp;
    private SPUtils spUtils;

    public ProductionAdapter(Context context, List<ProducationDetailBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public ProducationDetailBean.DataBean getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 填充item
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.
                    from(context).inflate(R.layout.item_production_data, null);
            viewHolder.tv_data = (EditText) convertView.findViewById(R.id.tv_data);
            viewHolder.tvProDocumentary = (EditText) convertView.findViewById(R.id.tvProDocumentary);
            viewHolder.tvProFactory = (EditText) convertView.findViewById(R.id.tvProFactory);
            viewHolder.tvProDepartment = (TextView) convertView.findViewById(R.id.tvProDepartment);
            viewHolder.tvProProcedure = (TextView) convertView.findViewById(R.id.tvProProcedure);
            viewHolder.tvProOthers = (EditText) convertView.findViewById(R.id.tvProOthers);
            viewHolder.tvProSingularSystem = (EditText) convertView.findViewById(R.id.tvProSingularSystem);
            viewHolder.tvProColor = (EditText) convertView.findViewById(R.id.tvProColor);
            viewHolder.tvProTaskNumber = (EditText) convertView.findViewById(R.id.tvProTaskNumber);
            viewHolder.tvProSize = (EditText) convertView.findViewById(R.id.tvProSize);
            viewHolder.tvProClippingNumber = (EditText) convertView.findViewById(R.id.tvProClippingNumber);
            viewHolder.tvProCompletedLastMonth = (EditText) convertView.findViewById(R.id.tvProCompletedLastMonth);
            viewHolder.tvProTotalCompletion = (EditText) convertView.findViewById(R.id.tvProTotalCompletion);
            viewHolder.tvProBalanceAmount = (EditText) convertView.findViewById(R.id.tvProBalanceAmount);
            viewHolder.tvProState = (TextView) convertView.findViewById(R.id.tvProState);
            viewHolder.tvProYear = (EditText) convertView.findViewById(R.id.tvProYear);
            viewHolder.tvProMonth = (EditText) convertView.findViewById(R.id.tvProMonth);
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
            viewHolder.tvProRecorder = (EditText) convertView.findViewById(R.id.tvProRecorder);
            viewHolder.tvProRecordat = (EditText) convertView.findViewById(R.id.tvProRecordat);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /*判断item中制单人是否是登录用户，是为可改，否为不可改*/
        sp = context.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        String nameid = sp.getString("username", "");
        String recorder = getItem(position).getRecorder().toString();
        if (nameid == recorder || recorder.equals(nameid)) {
            viewHolder.tv_data.setEnabled(true);
            viewHolder.tvProDocumentary.setEnabled(true);
            viewHolder.tvProFactory.setEnabled(true);
            viewHolder.tvProDepartment.setEnabled(true);
            viewHolder.tvProProcedure.setEnabled(true);
            viewHolder.tvProOthers.setEnabled(true);
            viewHolder.tvProSingularSystem.setEnabled(true);
            viewHolder.tvProColor.setEnabled(true);
            viewHolder.tvProTaskNumber.setEnabled(true);
            viewHolder.tvProSize.setEnabled(true);
            viewHolder.tvProClippingNumber.setEnabled(true);
            viewHolder.tvProCompletedLastMonth.setEnabled(true);
            viewHolder.tvProTotalCompletion.setEnabled(true);
            viewHolder.tvProBalanceAmount.setEnabled(true);
            viewHolder.tvProState.setEnabled(true);
            viewHolder.tvProYear.setEnabled(true);
            viewHolder.tvProMonth.setEnabled(true);
            viewHolder.tvProOneDay.setEnabled(true);
            viewHolder.tvProTwoDay.setEnabled(true);
            viewHolder.tvProThreeDay.setEnabled(true);
            viewHolder.tvProForeDay.setEnabled(true);
            viewHolder.tvProFiveDay.setEnabled(true);
            viewHolder.tvProSixDay.setEnabled(true);
            viewHolder.tvProSevenDay.setEnabled(true);
            viewHolder.tvProEightDay.setEnabled(true);
            viewHolder.tvProNineDay.setEnabled(true);
            viewHolder.tvProTenDay.setEnabled(true);
            viewHolder.tvProElevenDay.setEnabled(true);
            viewHolder.tvProTwelveDay.setEnabled(true);
            viewHolder.tvProThirteenDay.setEnabled(true);
            viewHolder.tvProFourteenDay.setEnabled(true);
            viewHolder.tvProFifteenDay.setEnabled(true);
            viewHolder.tvProSixteenDay.setEnabled(true);
            viewHolder.tvProSeventeenDay.setEnabled(true);
            viewHolder.tvProEighteenDay.setEnabled(true);
            viewHolder.tvProNineteenDay.setEnabled(true);
            viewHolder.tvProTwentyDay.setEnabled(true);
            viewHolder.tvProTwentyOneDay.setEnabled(true);
            viewHolder.tvProTwentyTwoDay.setEnabled(true);
            viewHolder.tvProTwentyThreeDay.setEnabled(true);
            viewHolder.tvProTwentyForeDay.setEnabled(true);
            viewHolder.tvProTwentyFiveDay.setEnabled(true);
            viewHolder.tvProTwentySixDay.setEnabled(true);
            viewHolder.tvProTwentySevenDay.setEnabled(true);
            viewHolder.tvProTwentyEightDay.setEnabled(true);
            viewHolder.tvProTwentyNineDay.setEnabled(true);
            viewHolder.tvProThirtyDay.setEnabled(true);
            viewHolder.tvProThirtyOneDay.setEnabled(true);
            viewHolder.tvProRemarks.setEnabled(true);
            viewHolder.tvProRecorder.setEnabled(true);
            viewHolder.tvProRecordat.setEnabled(true);
        } else {
            viewHolder.tv_data.setEnabled(false);
            viewHolder.tvProDocumentary.setEnabled(false);
            viewHolder.tvProFactory.setEnabled(false);
            viewHolder.tvProDepartment.setEnabled(false);
            viewHolder.tvProProcedure.setEnabled(false);
            viewHolder.tvProOthers.setEnabled(false);
            viewHolder.tvProSingularSystem.setEnabled(false);
            viewHolder.tvProColor.setEnabled(false);
            viewHolder.tvProTaskNumber.setEnabled(false);
            viewHolder.tvProSize.setEnabled(false);
            viewHolder.tvProClippingNumber.setEnabled(false);
            viewHolder.tvProCompletedLastMonth.setEnabled(false);
            viewHolder.tvProTotalCompletion.setEnabled(false);
            viewHolder.tvProBalanceAmount.setEnabled(false);
            viewHolder.tvProState.setEnabled(false);
            viewHolder.tvProYear.setEnabled(false);
            viewHolder.tvProMonth.setEnabled(false);
            viewHolder.tvProOneDay.setEnabled(false);
            viewHolder.tvProTwoDay.setEnabled(false);
            viewHolder.tvProThreeDay.setEnabled(false);
            viewHolder.tvProForeDay.setEnabled(false);
            viewHolder.tvProFiveDay.setEnabled(false);
            viewHolder.tvProSixDay.setEnabled(false);
            viewHolder.tvProSevenDay.setEnabled(false);
            viewHolder.tvProEightDay.setEnabled(false);
            viewHolder.tvProNineDay.setEnabled(false);
            viewHolder.tvProTenDay.setEnabled(false);
            viewHolder.tvProElevenDay.setEnabled(false);
            viewHolder.tvProTwelveDay.setEnabled(false);
            viewHolder.tvProThirteenDay.setEnabled(false);
            viewHolder.tvProFourteenDay.setEnabled(false);
            viewHolder.tvProFifteenDay.setEnabled(false);
            viewHolder.tvProSixteenDay.setEnabled(false);
            viewHolder.tvProSeventeenDay.setEnabled(false);
            viewHolder.tvProEighteenDay.setEnabled(false);
            viewHolder.tvProNineteenDay.setEnabled(false);
            viewHolder.tvProTwentyDay.setEnabled(false);
            viewHolder.tvProTwentyOneDay.setEnabled(false);
            viewHolder.tvProTwentyTwoDay.setEnabled(false);
            viewHolder.tvProTwentyThreeDay.setEnabled(false);
            viewHolder.tvProTwentyForeDay.setEnabled(false);
            viewHolder.tvProTwentyFiveDay.setEnabled(false);
            viewHolder.tvProTwentySixDay.setEnabled(false);
            viewHolder.tvProTwentySevenDay.setEnabled(false);
            viewHolder.tvProTwentyEightDay.setEnabled(false);
            viewHolder.tvProTwentyNineDay.setEnabled(false);
            viewHolder.tvProThirtyDay.setEnabled(false);
            viewHolder.tvProThirtyOneDay.setEnabled(false);
            viewHolder.tvProRemarks.setEnabled(false);
            viewHolder.tvProRecorder.setEnabled(false);
            viewHolder.tvProRecordat.setEnabled(false);
        }

        viewHolder.tvProDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupColumnMenu(viewHolder.tvProDepartment);
            }
        });

        viewHolder.tvProProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupProcedureMenu(viewHolder.tvProProcedure);
            }
        });

        viewHolder.tvProState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupPrdstatusMenu(viewHolder.tvProState);
            }
        });

        class Tvdate implements TextWatcher {
            public Tvdate(ViewHolder holder) {
                mHolder = holder;
            }

            private ViewHolder mHolder;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !"".equals(s.toString())) {
                    int position = (int) mHolder.tv_data.getTag();

                }
            }
        }

        viewHolder.tv_data.setText(getItem(position).getItem());
        viewHolder.tvProDocumentary.setText(getItem(position).getPrddocumentary());
        viewHolder.tvProFactory.setText(getItem(position).getSubfactory());
        viewHolder.tvProDepartment.setText(getItem(position).getSubfactoryTeams());
        viewHolder.tvProProcedure.setText(getItem(position).getWorkingProcedure());
        viewHolder.tvProOthers.setText(getItem(position).getWorkers());
        viewHolder.tvProSingularSystem.setText(getItem(position).getPqty());
        viewHolder.tvProColor.setText(getItem(position).getProdcol());
        viewHolder.tvProTaskNumber.setText(getItem(position).getTaskqty());
        viewHolder.tvProSize.setText(getItem(position).getMdl());
        viewHolder.tvProClippingNumber.setText(getItem(position).getFactcutqty());
        viewHolder.tvProCompletedLastMonth.setText(getItem(position).getLastMonQty());
        viewHolder.tvProTotalCompletion.setText(getItem(position).getSumCompletedQty());
        viewHolder.tvProBalanceAmount.setText(getItem(position).getLeftQty());
        viewHolder.tvProState.setText(getItem(position).getPrdstatus());
        viewHolder.tvProYear.setText(getItem(position).getYear());
        viewHolder.tvProMonth.setText(getItem(position).getMonth());
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
        return convertView;
    }

    /**
     * 弹出部门选择菜单
     *
     * @param view
     */
    private void showPopupColumnMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pro_column, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sp = context.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
                String title = item.getTitle().toString();
                spUtils.put(context, "proColumnTitle", title);
                ToastUtils.ShowToastMessage("点击的是：" + title, context);
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    /**
     * 弹出工序选择菜单
     *
     * @param view
     */
    private void showPopupProcedureMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pro_procedure, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sp = context.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
                String title = item.getTitle().toString();
                spUtils.put(context, "proProcedureTitle", title);
                ToastUtils.ShowToastMessage("点击的是：" + title, context);
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });

        popupMenu.show();
    }

    /**
     * 弹出状态选择菜单
     *
     * @param view
     */
    private void showPopupPrdstatusMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pro_prdstatus, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sp = context.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
                String title = item.getTitle().toString();
                spUtils.put(context, "proPrdstatusTitle", title);
                ToastUtils.ShowToastMessage("点击的是：" + title, context);
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    class ViewHolder {
        EditText tv_data;//款号
        EditText tvProDocumentary,//跟单
                tvProFactory;//工厂
        TextView tvProDepartment,//部门/组别
                tvProState,//状态
                tvProProcedure;//工序
        EditText tvProOthers,//组别人
                tvProSingularSystem,//制单数
                tvProColor,//花色
                tvProTaskNumber,//任务数
                tvProSize,//尺码
                tvProClippingNumber,//实裁数
                tvProCompletedLastMonth,//上月完工
                tvProTotalCompletion,//总完工数
                tvProBalanceAmount,//结余数量

        tvProYear,//年
                tvProMonth,//月
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
}
