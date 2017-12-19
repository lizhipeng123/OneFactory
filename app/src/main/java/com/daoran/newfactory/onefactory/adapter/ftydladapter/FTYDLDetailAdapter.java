package com.daoran.newfactory.onefactory.adapter.ftydladapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDetailColorBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLColCountBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLMonthBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.utils.ProductionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2017/11/22
 * 编写人：lizhipeng
 * 功能描述：裁床下各个花色对应的总数量以及每日数量填充适配器
 */

public class FTYDLDetailAdapter extends BaseAdapter {
    private static final String TAG = "adatperdetailtest";
    private Context context;
    private List<FTYDLDetailColorBean.DataBean> dataBeen =
            new ArrayList<FTYDLDetailColorBean.DataBean>();
    private List<FTYDLColCountBean.Data> dataList =
            new ArrayList<FTYDLColCountBean.Data>();
    private List<FTYDLMonthBean.DataBean> monthlistBean =
            new ArrayList<FTYDLMonthBean.DataBean>();//同款号，部门，工序的数据集合
    private int datetime;

    private SharedPreferences sp;
    private SPUtils spUtils;

    public FTYDLDetailAdapter(Context context,
                              List<FTYDLDetailColorBean.DataBean> dataBeen
            , List<FTYDLColCountBean.Data> dataList, List<FTYDLMonthBean.DataBean> monthlistBean) {
        this.context = context;
        this.dataBeen = dataBeen;
        this.dataList = dataList;
        this.monthlistBean = monthlistBean;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public FTYDLDetailColorBean.DataBean getItem(int position) {
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
        sp = context.getSharedPreferences("my_sp", 0);
        viewHolder.tv_color_item_config.setText(getItem(position).getProdcol());//花色
        viewHolder.tv_color_item_count.setText(String.valueOf
                (getItem(position).getSumCompletedQty()));//各花色完成的总数
        String strpre = sp.getString("procedureboolean", "");//可输入状态
        //判断是否是可输入状态
        if (strpre.equals("1")) {
            viewHolder.et_color_item_config_clipping.setEnabled(true);
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
                            String afterClipping = viewHolder.et_color_item_config_clipping.getText().toString();
                            int afteramount;
                            String blan = String.valueOf(dataBeen.get(position).getLeftQty());
                            if (dataList.get(position).getBalanceAmount() == null) {
                                afteramount = Integer.parseInt(dataList.get(position).getBalanceAmount());
                            } else {
                                afteramount = Integer.parseInt(blan);
                            }

                            ProductionUtil productionUtil = new ProductionUtil();
                            Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
                            t.setToNow(); // 取得系统时间。
                            datetime = t.monthDay;
                            String day1 = dataBeen.get(position).getDay1();
                            String day2 = dataBeen.get(position).getDay2();
                            String day3 = dataBeen.get(position).getDay3();
                            String day4 = dataBeen.get(position).getDay4();
                            String day5 = dataBeen.get(position).getDay5();
                            String day6 = dataBeen.get(position).getDay6();
                            String day7 = dataBeen.get(position).getDay7();
                            String day8 = dataBeen.get(position).getDay8();
                            String day9 = dataBeen.get(position).getDay9();
                            String day10 = dataBeen.get(position).getDay10();
                            String day11 = dataBeen.get(position).getDay11();
                            String day12 = dataBeen.get(position).getDay12();
                            String day13 = dataBeen.get(position).getDay13();
                            String day14 = dataBeen.get(position).getDay14();
                            String day15 = dataBeen.get(position).getDay15();
                            String day16 = dataBeen.get(position).getDay16();
                            String day17 = dataBeen.get(position).getDay17();
                            String day18 = dataBeen.get(position).getDay18();
                            String day19 = dataBeen.get(position).getDay19();
                            String day20 = dataBeen.get(position).getDay20();
                            String day21 = dataBeen.get(position).getDay21();
                            String day22 = dataBeen.get(position).getDay22();
                            String day23 = dataBeen.get(position).getDay23();
                            String day24 = dataBeen.get(position).getDay24();
                            String day25 = dataBeen.get(position).getDay25();
                            String day26 = dataBeen.get(position).getDay26();
                            String day27 = dataBeen.get(position).getDay27();
                            String day28 = dataBeen.get(position).getDay28();
                            String day29 = dataBeen.get(position).getDay29();
                            String day30 = dataBeen.get(position).getDay30();
                            String day31 = dataBeen.get(position).getDay31();
                            String lastqty = String.valueOf(dataBeen.get(position).getLastMonQty());
                            String CountMonthstr = null;
                            if (datetime == 1) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, afterClipping, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 2) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, afterClipping, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 3) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, afterClipping
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 4) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , afterClipping, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 5) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, afterClipping, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 6) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, afterClipping, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 7) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, afterClipping, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 8) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, afterClipping, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 9) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, afterClipping, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 10) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, afterClipping, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 11) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, afterClipping, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 12) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, afterClipping, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 13) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, afterClipping, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 14) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, afterClipping, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 15) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, afterClipping,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 16) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        afterClipping, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 17) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, afterClipping, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 18) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, afterClipping, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 19) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, afterClipping, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 20) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, afterClipping, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 21) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, afterClipping, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 22) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, afterClipping, day23, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 23) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, afterClipping, day24, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 24) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, afterClipping, day25,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 25) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, afterClipping,
                                        day26, day27, day28, day29, day30, day31);
                            } else if (datetime == 26) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        afterClipping, day27, day28, day29, day30, day31);
                            } else if (datetime == 27) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, afterClipping, day28, day29, day30, day31);
                            } else if (datetime == 28) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, afterClipping, day29, day30, day31);
                            } else if (datetime == 29) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, afterClipping, day30, day31);
                            } else if (datetime == 30) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, afterClipping, day31);
                            } else if (datetime == 31) {
                                CountMonthstr = productionUtil.CountMonth(lastqty, day1, day2, day3
                                        , day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15,
                                        day16, day17, day18, day19, day20, day21, day22, day23, day24, day25,
                                        day26, day27, day28, day29, day30, afterClipping);
                            }
                            if (afterClipping == null || afterClipping.isEmpty()) {
                                dataList.get(position).setProNum("");
                                dataList.get(position).setProClippingnumber("");
                                dataList.get(position).setProTotalCompletion("");
                                dataList.get(position).setBalanceAmount(String.valueOf(afteramount));
                                spUtils.put(context, "prosavecount", "");
                            } else {
                                dataList.get(position).setProNum(afterClipping);//填写的数量
                                dataList.get(position).setProClippingnumber(CountMonthstr);//实裁数
                                dataList.get(position).setProTotalCompletion(CountMonthstr);//总完工数
                                spUtils.put(context, "prosavecount", "2");
                            }
                        }
                        return;
                    }
                }
            });
        } else {
            viewHolder.et_color_item_config_clipping.setEnabled(false);
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_color_item_config;
        TextView tv_color_item_count;
        EditText et_color_item_config_clipping;
    }
}