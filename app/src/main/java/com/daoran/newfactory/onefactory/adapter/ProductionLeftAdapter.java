package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.production.ProductionActivity;
import com.daoran.newfactory.onefactory.activity.work.production.ProductionCopyComfigActivity;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PhoneSaveUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 生产日报左侧列表适配
 * Created by lizhipeng on 2017/6/22.
 */

public class ProductionLeftAdapter extends BaseAdapter {
    private List<ProducationDetailBean.DataBean> dataBeen;
    private Context context;
    private SharedPreferences sp;
    private SPUtils spUtils;
    private boolean flag = false;
    private int isprodure;
    private int year, month, datetime, hour, minute, second;

    public ProductionLeftAdapter(Context context, List<ProducationDetailBean.DataBean> dataBeen) {
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
           /*判断item中制单人是否是登录用户，是为可改，否为不可改*/
        sp = context.getSharedPreferences("my_sp", 0);
        String nameid = sp.getString("usernamerecoder", "");
        String recorder = getItem(position).getRecorder();
        if (recorder == null) {
            recorder = "";
        }
        if (!recorder.equals("")) {
            if (recorder.equals(nameid)) {
                holder.lin_content.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        String prorecorid = getItem(position).getRecordid();
                        spUtils.put(context, "prorecorid", prorecorid);//制单人id
                        String proid = String.valueOf(getItem(position).getID());
                        spUtils.put(context, "proadapterid", proid);//id
                        String urlid = String.valueOf(getItem(position).getID());
                        spUtils.put(context, "prouriid", urlid);//id
                        String salesid = String.valueOf(getItem(position).getSalesid());
                        spUtils.put(context, "prosalesid", salesid);//行id
                        String copyitem = getItem(position).getItem();
                        spUtils.put(context, "copyitem", copyitem);//款号
                        String copyDocumentary = getItem(position).getPrddocumentary();
                        spUtils.put(context, "copyDocumentary", copyDocumentary);//跟单
                        String copyFactory = getItem(position).getSubfactory();
                        spUtils.put(context, "copyFactory", copyFactory);//工厂
                        String copyDepartment = getItem(position).getSubfactoryTeams();
                        spUtils.put(context, "copyDepartment", copyDepartment);//部门组别
                        String copyProcedure = getItem(position).getWorkingProcedure();
                        spUtils.put(context, "copyProcedure", copyProcedure);//工序

                        String copyOthers = getItem(position).getWorkers();
                        spUtils.put(context, "copyOthers", copyOthers);//组别人数
                        String copySingularSystem = getItem(position).getPqty();
                        spUtils.put(context, "copySingularSystem", copySingularSystem);//制单数
                        String copyColor = getItem(position).getProdcol();//花色
                        if (copyColor.contains("/")) {
                            System.out.print(copyColor);
                            String[] temp = null;
                            temp = copyColor.split("/");
                            System.out.print(temp);
                            List<String> list = Arrays.asList(temp);
                            System.out.print(list);
                            SharedPreferences spes = context.getSharedPreferences("mylist", 0);
                            SharedPreferences.Editor editor = spes.edit();
                            try {
                                String liststr = PhoneSaveUtil.SceneList2String(list);
                                editor.putString("mycopylistStr", liststr);//花色集合
                                spUtils.put(context, "copyyColor", copyColor);//花色
                                editor.commit();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            spUtils.put(context, "copyyColor", copyColor);
                        }
                        String copyTaskNumber = getItem(position).getTaskqty();
                        spUtils.put(context, "copyTaskNumber", copyTaskNumber);//任务数
                        String copySize = getItem(position).getMdl();
                        spUtils.put(context, "copySize", copySize);//尺码
                        String copyClippingNumber = getItem(position).getFactcutqty();
                        spUtils.put(context, "copyClippingNumber", copyClippingNumber);//实裁数
                        String copyCompletedLastMonth = getItem(position).getLastMonQty();
                        spUtils.put(context, "copyCompletedLastMonth", copyCompletedLastMonth);//上月完工
                        String copyTotalCompletion = getItem(position).getSumCompletedQty();
                        spUtils.put(context, "copyTotalCompletion", copyTotalCompletion);//总完工数
                        String copyBalanceAmount = getItem(position).getLeftQty();
                        spUtils.put(context, "copyBalanceAmount", copyBalanceAmount);//结余数量
                        String copyState = getItem(position).getPrdstatus();
                        spUtils.put(context, "copyState", copyState);//状态
                        Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
                        t.setToNow(); // 取得系统时间。
                        year = t.year;
                        month = t.month;
                        datetime = t.monthDay;
                        hour = t.hour; // 0-23
                        minute = t.minute;
                        second = t.second;
                        month = month + 1;
                        String copyProYear = year + "";
                        spUtils.put(context, "copyProYear", copyProYear);//年
                        String copyMonth = month + "";
                        spUtils.put(context, "copyMonth", copyMonth);//月

                        String copyRemarks = getItem(position).getMemo();
                        spUtils.put(context, "copyRemarks", copyRemarks);//备注
                        String copyRecorder = getItem(position).getRecorder();
                        spUtils.put(context, "copyRecorder", copyRecorder);//制单人
                        String copyRecordat = getItem(position).getRecordat();
                        spUtils.put(context, "copyRecordat", copyRecordat);//制单时间
                        String copyproducament = getItem(position).getPrddocumentaryid();
                        spUtils.put(context, "copyproducamentid", copyproducament);//跟单人ID
                        ArrayList<String> list = new ArrayList<String>();
                        list.add(copyitem);
                        Intent intent = new Intent(context, ProductionCopyComfigActivity.class);
                        intent.putStringArrayListExtra("copyitemlist", list);//
                        context.startActivity(intent);
                        ProductionActivity productionActivity = new ProductionActivity();
                        productionActivity.finish();
                        return false;
                    }
                });
            } else {
                holder.lin_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.ShowToastMessage("登录人不是制单人，无法复制", context);
                    }
                });
            }
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvLeft;
        LinearLayout lin_content;
    }
}
