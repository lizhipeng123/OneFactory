package com.daoran.newfactory.onefactory.adapter.ftydladapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLFactoryDailyColBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLColCountBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2017/11/17
 * 编写人：lizhipeng
 * 功能描述：分色列表适配
 */

public class FTYDLNewlyVerticalAdatper extends BaseAdapter {
    private static final String TAG = "adatpertest";
    private Context context;
    private List<FTYDLFactoryDailyColBean.DataBean> dataBeen =
            new ArrayList<FTYDLFactoryDailyColBean.DataBean>();//初始化全部的数据
    private List<FTYDLColCountBean.Data> dataList =
            new ArrayList<FTYDLColCountBean.Data>();
    private SPUtils spUtils;

    public FTYDLNewlyVerticalAdatper(Context context,
                                     List<FTYDLFactoryDailyColBean.DataBean> dataBeen,
                                     List<FTYDLColCountBean.Data> dataList) {
        this.context = context;
        this.dataBeen = dataBeen;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public FTYDLFactoryDailyColBean.DataBean getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_pro_config_clippingnumber, null);
            viewHolder.tv_color_item_config = (TextView) convertView.findViewById(R.id.tv_color_item_config);
            viewHolder.tv_color_item_count = (TextView) convertView.findViewById(R.id.tv_color_item_count);
            viewHolder.et_color_item_config_clipping = (EditText)
                    convertView.findViewById(R.id.et_color_item_config_clipping);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_color_item_config.setText(getItem(position).getProdcol());
        final TextView et_color_item_config_clipping = viewHolder.et_color_item_config_clipping;
        final int MIN_MARK_OTHER = 0;
        et_color_item_config_clipping.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged");
                if (s != null || s.equals("")) {
                    if (MIN_MARK_OTHER != -1 || 99999 != -1) {
                        int markVal = 0;
                        try {
                            markVal = Integer.parseInt(s.toString());
                        } catch (NumberFormatException e) {
                            markVal = 0;
                        }
                        if (markVal > 99999) {
                            ToastUtils.ShowToastMessage("数值输入过大",
                                    context);
                            viewHolder.et_color_item_config_clipping.setText(String.valueOf(99999));
                            viewHolder.et_color_item_config_clipping.setSelection(viewHolder.et_color_item_config_clipping.length());
                        }
                    }
                }
                String afterClipping = viewHolder.et_color_item_config_clipping.getText().toString();
                int afteramount = Integer.parseInt(dataList.get(position).getBalanceAmount());//结余数量
                if (afterClipping == null || afterClipping.isEmpty()) {
                    dataList.get(position).setProNum("");//每日数量
                    dataList.get(position).setProClippingnumber("");//实裁数
                    dataList.get(position).setProTotalCompletion("");//总完工数
                    dataList.get(position).setBalanceAmount(String.valueOf(afteramount));//结余数量
                } else {
                    dataList.get(position).setProNum(afterClipping);
                    dataList.get(position).setProClippingnumber(afterClipping);
                    dataList.get(position).setProTotalCompletion(afterClipping);
                    spUtils.put(context,"adapterafterClipping",afterClipping);
                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv_color_item_config;
        TextView tv_color_item_count;
        EditText et_color_item_config_clipping;
    }
}