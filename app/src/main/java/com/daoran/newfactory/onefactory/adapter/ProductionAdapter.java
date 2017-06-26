package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.production.ProductionActivity;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * 生产日报适配器
 * Created by lizhipeng on 2017/4/26.
 */

public class ProductionAdapter extends BaseAdapter {
    private static final String TAG = "test";
    private Context context;
    private List<ProducationDetailBean.DataBean> dataBeen;
    private SharedPreferences sp;
    private SPUtils spUtils;

    private Vector<Collection> collections;

    private boolean flag = false;

    private int index = -1;
    private int last_item = -1;
    private int year, month, datetime, hour, minute, second;
    int lastmont, day1, day2, day3, day4, day5, day6, day7, day8, day9,
            day10, day11, day12, day13, day14, day15, day16, day17, day18,
            day19, day20, day21, day22, day23, day24, day25, day26, day27,
            day28, day29, day30, day31;
    int countmon, skNumber;

    public ProductionAdapter(Context context, List<ProducationDetailBean.DataBean> dataBeen
    ) {
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

    public void setSelectItem(int selectItem) {
        this.last_item = selectItem;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_production_data, null);
            viewHolder.lin_content = (LinearLayout) convertView.findViewById(R.id.lin_content);
//            viewHolder.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
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
            viewHolder.data_ll_vertical = convertView.findViewById(R.id.data_ll_vertical);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
         /*判断item中制单人是否是登录用户，是为可改，否为不可改*/
        sp = context.getSharedPreferences("my_sp", 0);
        String nameid = sp.getString("usernamerecoder", "");
        String recorder = getItem(position).getRecorder();
        if (recorder == null) {
            recorder = "";
        }
        if (!recorder.equals("")) {
            if (recorder.equals("倪新红")) {
                viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_historylist);
                        String proid = String.valueOf(getItem(position).getID());
                        spUtils.put(context, "proadapterid", proid);
                        String urlid = String.valueOf(getItem(position).getID());
                        spUtils.put(context, "prouriid", urlid);
                        String salesid = String.valueOf(getItem(position).getSalesid());
                        spUtils.put(context, "prosalesid", salesid);
                        String copyitem = getItem(position).getItem();
                        spUtils.put(context, "copyitem", copyitem);
                        String copyDocumentary = getItem(position).getPrddocumentary();
                        spUtils.put(context, "copyDocumentary", copyDocumentary);
                        String copyFactory = getItem(position).getSubfactory();
                        spUtils.put(context, "copyFactory", copyFactory);
                        String copyDepartment = getItem(position).getSubfactoryTeams();
                        spUtils.put(context, "copyDepartment", copyDepartment);
                        String copyProcedure = getItem(position).getWorkingProcedure();
                        spUtils.put(context, "copyProcedure", copyProcedure);
                        String copyOthers = getItem(position).getWorkers();
                        spUtils.put(context, "copyOthers", copyOthers);
                        String copySingularSystem = getItem(position).getPqty();
                        spUtils.put(context, "copySingularSystem", copySingularSystem);
                        String copyColor = getItem(position).getProdcol();
                        spUtils.put(context, "copyColor", copyColor);
                        String copyTaskNumber = getItem(position).getTaskqty();
                        spUtils.put(context, "copyTaskNumber", copyTaskNumber);
                        String copySize = getItem(position).getMdl();
                        spUtils.put(context, "copySize", copySize);
                        String copyClippingNumber = getItem(position).getFactcutqty();
                        spUtils.put(context, "copyClippingNumber", copyClippingNumber);
                        String copyCompletedLastMonth = getItem(position).getLastMonQty();
                        spUtils.put(context, "copyCompletedLastMonth", copyCompletedLastMonth);
                        String copyTotalCompletion = getItem(position).getSumCompletedQty();
                        spUtils.put(context, "copyTotalCompletion", copyTotalCompletion);
                        String copyBalanceAmount = getItem(position).getLeftQty();
                        spUtils.put(context, "copyBalanceAmount", copyBalanceAmount);
                        String copyState = getItem(position).getPrdstatus();
                        spUtils.put(context, "copyState", copyState);
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
                        spUtils.put(context, "copyProYear", copyProYear);
                        String copyMonth = month + "";
                        spUtils.put(context, "copyMonth", copyMonth);

                        String copyRemarks = getItem(position).getMemo();
                        spUtils.put(context, "copyRemarks", copyRemarks);
                        String copyRecorder = getItem(position).getRecorder();
                        spUtils.put(context, "copyRecorder", copyRecorder);
                        String copyRecordat = getItem(position).getRecordat();
                        spUtils.put(context, "copyRecordat", copyRecordat);

                        ArrayList<String> list = new ArrayList<String>();
                        list.add(copyitem);
                        Intent intent = new Intent(context, ProductionActivity.class);
                        intent.putStringArrayListExtra("copyitemlist", list);
                    }
                });
//                String proid = sp.getString("proadapterid", "");
//
//                spUtils.put(context, "proadapterid", proid);
//                String urlid = String.valueOf(getItem(position).getID());
//                spUtils.put(context, "prourisaveid", urlid);
//                String salesid = String.valueOf(getItem(position).getSalesid());
//                spUtils.put(context, "prosalessaveid", salesid);
//                viewHolder.tv_data.setEnabled(true);
//                String productionItem = getItem(position).getItem();
//                viewHolder.tv_data.setText(productionItem);
                viewHolder.tvProDocumentary.setEnabled(true);
                String productionadapterDocumentary = getItem(position).getPrddocumentary();
                viewHolder.tvProDocumentary.setText(productionadapterDocumentary);

                viewHolder.tvProFactory.setEnabled(true);
                String productionFactory = getItem(position).getSubfactory();
                viewHolder.tvProFactory.setText(productionFactory);

                viewHolder.tvProDepartment.setEnabled(true);
                viewHolder.tvProDepartment.setText(getItem(position).getSubfactoryTeams());

                viewHolder.tvProProcedure.setEnabled(true);
                viewHolder.tvProProcedure.setText(getItem(position).getWorkingProcedure());

                viewHolder.tvProOthers.setEnabled(true);
                final EditText editTexOthers = viewHolder.tvProOthers;
                final int MIN_MARK_OTHER = 0;
                final int MAX_MARK_OTHER = 200;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexOthers.getTag() instanceof TextWatcher) {
                    editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
                }
                viewHolder.tvProOthers.setText(getItem(position).getWorkers());
                TextWatcher TvOthers = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        Log.d(TAG, "beforeTextChanged");
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Log.d(TAG, "onTextChanged");
                        if (start > 1) {
                            if (MIN_MARK_OTHER != -1 && MAX_MARK_OTHER != -1) {
                                int num = Integer.parseInt(s.toString());
                                if (num > MAX_MARK_OTHER) {
                                    s = String.valueOf(MAX_MARK_OTHER);
                                    editTexOthers.setText(s);
                                    editTexOthers.setSelection(editTexOthers.length());
                                } else if (num < MIN_MARK_OTHER) {
                                    s = String.valueOf(MIN_MARK_OTHER);
                                    return;
                                }
                            }
                        }
                        InputFilter[] filters = {new InputFilter.LengthFilter(MAX_MARK_OTHER)};
                        viewHolder.tvProTaskNumber.setFilters(filters);
//                        ToastUtils.ShowToastMessage("输入超过了限制人数", context);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.d(TAG, "afterTextChanged");
                        if (s != null && s.equals("")) {
                            if (MIN_MARK_OTHER != -1 && MAX_MARK_OTHER != -1) {
                                int markVal = 0;
                                try {
                                    markVal = Integer.parseInt(s.toString());
                                } catch (NumberFormatException e) {
                                    markVal = 0;
                                }
                                if (markVal > MAX_MARK_OTHER) {
                                    ToastUtils.ShowToastMessage("大小不能超过200", context);
                                    editTexOthers.setText(String.valueOf(MAX_MARK_OTHER));
                                    editTexOthers.setSelection(editTexOthers.length());
                                }
                                return;
                            }
                        }
                        String proitem = viewHolder.tvProOthers.getText().toString();
                        getItem(position).setWorkers(proitem);

//                        int proid = getItem(position).getID();
//                        int sriid = getItem(position).getSalesid();
//                        ArrayList<ProducationDetailBean.DataBean> list =
//                                new ArrayList<ProducationDetailBean.DataBean>();
//                        for (int i = 0; i < dataBeen.size(); i++) {
//                            ProducationDetailBean.DataBean dataBean =
//                                    new ProducationDetailBean.DataBean();
//                            dataBean.setID(proid);
//                            dataBean.setSalesid(sriid);
//                            dataBean.setWorkers(proitem);
//                            list.add(dataBean);
//                        }
//                        String json = JsonUtil.changeArrayDateToJson(list);
//                        System.out.print(json);

//                        spUtils.put(context, "proadapterid", proid);
//                        String urlid = String.valueOf(getItem(position).getID());
//                        spUtils.put(context, "prouriid", urlid);
                        viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.length());
//                        spUtils.put(context, "productionsaveOthers", proitem);
                    }
                };
                editTexOthers.addTextChangedListener(TvOthers);
                editTexOthers.setTag(TvOthers);
                /*光标放置在文本最后*/
                viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.length());


                viewHolder.tvProSingularSystem.setEnabled(true);
                String productionadapterSingularSystem = getItem(position).getPqty();
                viewHolder.tvProSingularSystem.setText(productionadapterSingularSystem);

                viewHolder.tvProColor.setEnabled(true);
                String productionColor = getItem(position).getProdcol();
                viewHolder.tvProColor.setText(productionColor);

                viewHolder.tvProTaskNumber.setEnabled(true);
                final int singular = Integer.parseInt(viewHolder.tvProSingularSystem.getText().toString());
                final int MIN_MARK = 0;
                final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                    editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
                }
                editTexTaskNumber.setText(getItem(position).getTaskqty());
                /**
                 * 任务数不能大于制单数
                 */
                TextWatcher TvTaskNumber = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        Log.d(TAG, "beforeTextChanged");
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Log.d(TAG, "onTextChanged");
                        if (start > 1) {
                            if (MIN_MARK != -1 && singular != -1) {
                                int num = Integer.parseInt(s.toString());
                                if (num > singular) {
                                    s = String.valueOf(singular);
                                    editTexTaskNumber.setText(getItem(position).getTaskqty());
                                    editTexTaskNumber.setSelection(editTexTaskNumber.length());
                                    InputFilter[] filters = {new InputFilter.LengthFilter(singular)};
                                    viewHolder.tvProTaskNumber.setFilters(filters);
                                    ToastUtils.ShowToastMessage("输入超过了制单数", context);
                                } else if (num < MIN_MARK) {
                                    s = String.valueOf(MIN_MARK);
                                    return;
                                }
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.d(TAG, "afterTextChanged");
                        if (s != null && s.equals("")) {
                            if (MIN_MARK != -1 && singular != -1) {
                                int markVal = 0;
                                try {
                                    markVal = Integer.parseInt(s.toString());
                                } catch (NumberFormatException e) {
                                    markVal = 0;
                                }
                                if (markVal > singular) {
                                    ToastUtils.ShowToastMessage("大小不能超过制单数", context);
                                    editTexTaskNumber.setText(getItem(position).getTaskqty());
                                    editTexTaskNumber.setSelection(editTexTaskNumber.length());
                                }
                                return;
                            }
                        }
                        String proitem = viewHolder.tvProTaskNumber.getText().toString();
                        getItem(position).setTaskqty(proitem);
                        viewHolder.tvProTaskNumber.setSelection(viewHolder.tvProTaskNumber.length());
//                        spUtils.put(context, "productionTaskNumber", proitem);

                    }
                };
                editTexTaskNumber.addTextChangedListener(TvTaskNumber);
                editTexTaskNumber.setTag(TvTaskNumber);
            /*光标放置在文本最后*/
                viewHolder.tvProTaskNumber.setSelection(viewHolder.tvProTaskNumber.length());

                viewHolder.tvProSize.setEnabled(true);
                String productionSize = getItem(position).getMdl();
                viewHolder.tvProSize.setText(productionSize);

                viewHolder.tvProClippingNumber.setEnabled(true);
                String productionClippingNumber = getItem(position).getFactcutqty();
                viewHolder.tvProClippingNumber.setText(productionClippingNumber);

                viewHolder.tvProCompletedLastMonth.setEnabled(true);
                final int MIN_MARK_LASTMONTH = 0;
//                final int lastmonth_MAX =
                final EditText editTexCompletedLastMonth = viewHolder.tvProCompletedLastMonth;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexCompletedLastMonth.getTag() instanceof TextWatcher) {
                    editTexCompletedLastMonth.removeTextChangedListener((TextWatcher) editTexCompletedLastMonth.getTag());
                }
                editTexCompletedLastMonth.setText(getItem(position).getLastMonQty());
                TextWatcher TvCompletedLastMonth = new TextWatcher() {
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
                        String proitem = viewHolder.tvProCompletedLastMonth.getText().toString();
                        getItem(position).setLastMonQty(proitem);
//                        spUtils.put(context, "productionCompletedLastMonth", proitem);

                    }
                };
                editTexCompletedLastMonth.addTextChangedListener(TvCompletedLastMonth);
                editTexCompletedLastMonth.setTag(TvCompletedLastMonth);
            /*光标放置在文本最后*/
                viewHolder.tvProCompletedLastMonth.setSelection(viewHolder.tvProCompletedLastMonth.length());

                viewHolder.tvProTotalCompletion.setEnabled(true);
                viewHolder.tvProTotalCompletion.setText(getItem(position).getSumCompletedQty());

                viewHolder.tvProBalanceAmount.setEnabled(true);
                viewHolder.tvProBalanceAmount.setText(getItem(position).getLeftQty());

                viewHolder.tvProState.setEnabled(true);
                viewHolder.tvProState.setText(getItem(position).getPrdstatus());

                viewHolder.tvProYear.setEnabled(true);
                String productionYear = getItem(position).getYear();
                viewHolder.tvProYear.setText(productionYear);

                viewHolder.tvProMonth.setEnabled(true);
                viewHolder.tvProMonth.setText(getItem(position).getMonth());

                viewHolder.tvProOneDay.setEnabled(true);
                final EditText editTexOneDay = viewHolder.tvProOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexOneDay.getTag() instanceof TextWatcher) {
                    editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
                }
                editTexOneDay.setText(getItem(position).getDay1());

                TextWatcher TvOneDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProOneDay.getText().toString();
                        getItem(position).setDay1(proitem);
//                        spUtils.put(context, "productionOneDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);

                    }
                };
                editTexOneDay.addTextChangedListener(TvOneDay);
                editTexOneDay.setTag(TvOneDay);
            /*光标放置在文本最后*/
                viewHolder.tvProOneDay.setSelection(viewHolder.tvProOneDay.length());


                viewHolder.tvProTwoDay.setEnabled(true);
                final EditText editTexTwoDay = viewHolder.tvProTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwoDay.getTag() instanceof TextWatcher) {
                    editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
                }
                editTexTwoDay.setText(getItem(position).getDay2());

                TextWatcher TvTwoDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwoDay.getText().toString();
                        getItem(position).setDay2(proitem);
//                        spUtils.put(context, "productionTwoDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwoDay.addTextChangedListener(TvTwoDay);
                editTexTwoDay.setTag(TvTwoDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwoDay.setSelection(viewHolder.tvProTwoDay.length());


                viewHolder.tvProThreeDay.setEnabled(true);
                final EditText editTexThreeDay = viewHolder.tvProThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThreeDay.getTag() instanceof TextWatcher) {
                    editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
                }
                editTexThreeDay.setText(getItem(position).getDay3());

                TextWatcher TvThreeDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProThreeDay.getText().toString();
                        getItem(position).setDay3(proitem);
//                        spUtils.put(context, "productionThreeDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexThreeDay.addTextChangedListener(TvThreeDay);
                editTexThreeDay.setTag(TvThreeDay);
            /*光标放置在文本最后*/
                viewHolder.tvProThreeDay.setSelection(viewHolder.tvProThreeDay.length());


                viewHolder.tvProForeDay.setEnabled(true);
                final EditText editTexForeDay = viewHolder.tvProForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexForeDay.getTag() instanceof TextWatcher) {
                    editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
                }
                editTexForeDay.setText(getItem(position).getDay4());
                TextWatcher TvForeDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProForeDay.getText().toString();
                        getItem(position).setDay4(proitem);
//                        spUtils.put(context, "productionForeDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);

                    }
                };
                editTexForeDay.addTextChangedListener(TvForeDay);
                editTexForeDay.setTag(TvForeDay);
            /*光标放置在文本最后*/
                viewHolder.tvProForeDay.setSelection(viewHolder.tvProForeDay.length());


                viewHolder.tvProFiveDay.setEnabled(true);
                final EditText editTexFiveDay = viewHolder.tvProFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFiveDay.getTag() instanceof TextWatcher) {
                    editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
                }
                editTexFiveDay.setText(getItem(position).getDay5());
                TextWatcher TvFiveDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProFiveDay.getText().toString();
                        getItem(position).setDay5(proitem);
//                        spUtils.put(context, "productionFiveDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);

                    }
                };
                editTexFiveDay.addTextChangedListener(TvFiveDay);
                editTexFiveDay.setTag(TvFiveDay);
            /*光标放置在文本最后*/
                viewHolder.tvProFiveDay.setSelection(viewHolder.tvProFiveDay.length());


                viewHolder.tvProSixDay.setEnabled(true);
                final EditText editTexSixDay = viewHolder.tvProSixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSixDay.getTag() instanceof TextWatcher) {
                    editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
                }
                editTexSixDay.setText(getItem(position).getDay6());
                TextWatcher TvSixDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProSixDay.getText().toString();
                        getItem(position).setDay6(proitem);
//                        spUtils.put(context, "productionSixDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexSixDay.addTextChangedListener(TvSixDay);
                editTexSixDay.setTag(TvSixDay);
            /*光标放置在文本最后*/
                viewHolder.tvProSixDay.setSelection(viewHolder.tvProSixDay.length());


                viewHolder.tvProSevenDay.setEnabled(true);
                final EditText editTexSevenDay = viewHolder.tvProSevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSevenDay.getTag() instanceof TextWatcher) {
                    editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
                }
                editTexSevenDay.setText(getItem(position).getDay7());
                TextWatcher TvSevenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProSevenDay.getText().toString();
                        getItem(position).setDay7(proitem);
//                        spUtils.put(context, "productionSevenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexSevenDay.addTextChangedListener(TvSevenDay);
                editTexSevenDay.setTag(TvSevenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProSevenDay.setSelection(viewHolder.tvProSevenDay.length());


                viewHolder.tvProEightDay.setEnabled(true);
                final EditText editTexEightDay = viewHolder.tvProEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexEightDay.getTag() instanceof TextWatcher) {
                    editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
                }
                editTexEightDay.setText(getItem(position).getDay8());
                TextWatcher TvEightDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProEightDay.getText().toString();
                        getItem(position).setDay8(proitem);
//                        spUtils.put(context, "productionEightDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexEightDay.addTextChangedListener(TvEightDay);
                editTexEightDay.setTag(TvEightDay);
            /*光标放置在文本最后*/
                viewHolder.tvProEightDay.setSelection(viewHolder.tvProEightDay.length());


                viewHolder.tvProNineDay.setEnabled(true);
                final EditText editTexNineDay = viewHolder.tvProNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexNineDay.getTag() instanceof TextWatcher) {
                    editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
                }
                editTexNineDay.setText(getItem(position).getDay9());
                TextWatcher TvNineDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProNineDay.getText().toString();
                        getItem(position).setDay9(proitem);
//                        spUtils.put(context, "productionNineDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexNineDay.addTextChangedListener(TvNineDay);
                editTexNineDay.setTag(TvNineDay);
            /*光标放置在文本最后*/
                viewHolder.tvProNineDay.setSelection(viewHolder.tvProNineDay.length());


                viewHolder.tvProTenDay.setEnabled(true);
                final EditText editTexTenDay = viewHolder.tvProTenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTenDay.getTag() instanceof TextWatcher) {
                    editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
                }
                editTexTenDay.setText(getItem(position).getDay10());
                TextWatcher TvTenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTenDay.getText().toString();
                        getItem(position).setDay10(proitem);
//                        spUtils.put(context, "productionTenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTenDay.addTextChangedListener(TvTenDay);
                editTexTenDay.setTag(TvTenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTenDay.setSelection(viewHolder.tvProTenDay.length());


                viewHolder.tvProElevenDay.setEnabled(true);
                final EditText editTexElevenDay = viewHolder.tvProElevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexElevenDay.getTag() instanceof TextWatcher) {
                    editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
                }
                editTexElevenDay.setText(getItem(position).getDay11());
                TextWatcher TvElevenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProElevenDay.getText().toString();
                        getItem(position).setDay11(proitem);
//                        spUtils.put(context, "productionElevenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexElevenDay.addTextChangedListener(TvElevenDay);
                editTexElevenDay.setTag(TvElevenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProElevenDay.setSelection(viewHolder.tvProElevenDay.length());


                viewHolder.tvProTwelveDay.setEnabled(true);
                final EditText editTexTwelveDay = viewHolder.tvProTwelveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwelveDay.getTag() instanceof TextWatcher) {
                    editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
                }
                editTexTwelveDay.setText(getItem(position).getDay12());
                TextWatcher TvTwelveDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwelveDay.getText().toString();
                        getItem(position).setDay12(proitem);
//                        spUtils.put(context, "productionTwelveDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwelveDay.addTextChangedListener(TvTwelveDay);
                editTexTwelveDay.setTag(TvTwelveDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwelveDay.setSelection(viewHolder.tvProTwelveDay.length());


                viewHolder.tvProThirteenDay.setEnabled(true);
                final EditText editTexThirteenDay = viewHolder.tvProThirteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirteenDay.getTag() instanceof TextWatcher) {
                    editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
                }
                editTexThirteenDay.setText(getItem(position).getDay13());
                TextWatcher TvThirteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProThirteenDay.getText().toString();
                        getItem(position).setDay13(proitem);
//                        spUtils.put(context, "productionThirteenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexThirteenDay.addTextChangedListener(TvThirteenDay);
                editTexThirteenDay.setTag(TvThirteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProThirteenDay.setSelection(viewHolder.tvProThirteenDay.length());


                viewHolder.tvProFourteenDay.setEnabled(true);
                final EditText editTexFourteenDay = viewHolder.tvProFourteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFourteenDay.getTag() instanceof TextWatcher) {
                    editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
                }
                editTexFourteenDay.setText(getItem(position).getDay14());
                TextWatcher TvFourteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProFourteenDay.getText().toString();
                        getItem(position).setDay14(proitem);
//                        spUtils.put(context, "productionFourteenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexFourteenDay.addTextChangedListener(TvFourteenDay);
                editTexFourteenDay.setTag(TvFourteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProFourteenDay.setSelection(viewHolder.tvProFourteenDay.length());


                viewHolder.tvProFifteenDay.setEnabled(true);
                final EditText editTexFifteenDay = viewHolder.tvProFifteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFifteenDay.getTag() instanceof TextWatcher) {
                    editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
                }
                editTexFifteenDay.setText(getItem(position).getDay15());
                TextWatcher TvFifteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProFifteenDay.getText().toString();
                        getItem(position).setDay15(proitem);
//                        spUtils.put(context, "productionFifteenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexFifteenDay.addTextChangedListener(TvFifteenDay);
                editTexFifteenDay.setTag(TvFifteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProFifteenDay.setSelection(viewHolder.tvProFifteenDay.length());


                viewHolder.tvProSixteenDay.setEnabled(true);
                final EditText editTexSixteenDay = viewHolder.tvProSixteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSixteenDay.getTag() instanceof TextWatcher) {
                    editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
                }
                editTexSixteenDay.setText(getItem(position).getDay16());
                TextWatcher TvSixteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProSixteenDay.getText().toString();
                        getItem(position).setDay16(proitem);
//                        spUtils.put(context, "productionSixteenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexSixteenDay.addTextChangedListener(TvSixteenDay);
                editTexSixteenDay.setTag(TvSixteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProSixteenDay.setSelection(viewHolder.tvProSixteenDay.length());


                viewHolder.tvProSeventeenDay.setEnabled(true);
                final EditText editTexSeventeenDay = viewHolder.tvProSeventeenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
                    editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
                }
                editTexSeventeenDay.setText(getItem(position).getDay17());
                TextWatcher TvSeventeenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProSeventeenDay.getText().toString();
                        getItem(position).setDay17(proitem);
//                        spUtils.put(context, "productionSeventeenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexSeventeenDay.addTextChangedListener(TvSeventeenDay);
                editTexSeventeenDay.setTag(TvSeventeenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProSeventeenDay.setSelection(viewHolder.tvProSeventeenDay.length());


                viewHolder.tvProEighteenDay.setEnabled(true);
                final EditText editTexEighteenDay = viewHolder.tvProEighteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexEighteenDay.getTag() instanceof TextWatcher) {
                    editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
                }
                editTexEighteenDay.setText(getItem(position).getDay18());
                TextWatcher TvEighteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProEighteenDay.getText().toString();
                        getItem(position).setDay18(proitem);
//                        spUtils.put(context, "productionEighteenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexEighteenDay.addTextChangedListener(TvEighteenDay);
                editTexEighteenDay.setTag(TvEighteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProEighteenDay.setSelection(viewHolder.tvProEighteenDay.length());


                viewHolder.tvProNineteenDay.setEnabled(true);
                final EditText editTexNineteenDay = viewHolder.tvProNineteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexNineteenDay.getTag() instanceof TextWatcher) {
                    editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
                }
                editTexNineteenDay.setText(getItem(position).getDay19());
                TextWatcher TvNineteenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProNineteenDay.getText().toString();
                        getItem(position).setDay19(proitem);
//                        spUtils.put(context, "productionNineteenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexNineteenDay.addTextChangedListener(TvNineteenDay);
                editTexNineteenDay.setTag(TvNineteenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProNineteenDay.setSelection(viewHolder.tvProNineteenDay.length());


                viewHolder.tvProTwentyDay.setEnabled(true);
                final EditText editTexTwentyDay = viewHolder.tvProTwentyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyDay.getTag() instanceof TextWatcher) {
                    editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
                }
                editTexTwentyDay.setText(getItem(position).getDay20());
                TextWatcher TvTwentyDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyDay.getText().toString();
                        getItem(position).setDay20(proitem);
//                        spUtils.put(context, "productionTwentyDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentyDay.addTextChangedListener(TvTwentyDay);
                editTexTwentyDay.setTag(TvTwentyDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyDay.setSelection(viewHolder.tvProTwentyDay.length());


                viewHolder.tvProTwentyOneDay.setEnabled(true);
                final EditText editTexTwentyOneDay = viewHolder.tvProTwentyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
                    editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
                }
                editTexTwentyOneDay.setText(getItem(position).getDay21());
                TextWatcher TvTwentyOneDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyOneDay.getText().toString();
                        getItem(position).setDay21(proitem);
//                        spUtils.put(context, "productionTwentyOneDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentyOneDay.addTextChangedListener(TvTwentyOneDay);
                editTexTwentyOneDay.setTag(TvTwentyOneDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyOneDay.setSelection(viewHolder.tvProTwentyOneDay.length());


                viewHolder.tvProTwentyTwoDay.setEnabled(true);
                final EditText editTexTwentyTwoDay = viewHolder.tvProTwentyTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
                    editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
                }
                editTexTwentyTwoDay.setText(getItem(position).getDay22());
                TextWatcher TvTwentyTwoDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyTwoDay.getText().toString();
                        getItem(position).setDay22(proitem);
//                        spUtils.put(context, "productionTwentyTwoDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentyTwoDay.addTextChangedListener(TvTwentyTwoDay);
                editTexTwentyTwoDay.setTag(TvTwentyTwoDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyTwoDay.setSelection(viewHolder.tvProTwentyTwoDay.length());


                viewHolder.tvProTwentyThreeDay.setEnabled(true);
                final EditText editTexTwentyThreeDay = viewHolder.tvProTwentyThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
                    editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
                }
                editTexTwentyThreeDay.setText(getItem(position).getDay23());
                TextWatcher TvTwentyThreeDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyThreeDay.getText().toString();
                        getItem(position).setDay23(proitem);
//                        spUtils.put(context, "productionTwentyThreeDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentyThreeDay.addTextChangedListener(TvTwentyThreeDay);
                editTexTwentyThreeDay.setTag(TvTwentyThreeDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyThreeDay.setSelection(viewHolder.tvProTwentyThreeDay.length());


                viewHolder.tvProTwentyForeDay.setEnabled(true);
                final EditText editTexTwentyForeDay = viewHolder.tvProTwentyForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
                    editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
                }
                editTexTwentyForeDay.setText(getItem(position).getDay24());
                TextWatcher TvTwentyForeDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyForeDay.getText().toString();
                        getItem(position).setDay24(proitem);
//                        spUtils.put(context, "productionTwentyForeDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentyForeDay.addTextChangedListener(TvTwentyForeDay);
                editTexTwentyForeDay.setTag(TvTwentyForeDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyForeDay.setSelection(viewHolder.tvProTwentyForeDay.length());


                viewHolder.tvProTwentyFiveDay.setEnabled(true);
                final EditText editTexTwentyFiveDay = viewHolder.tvProTwentyFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
                    editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
                }
                editTexTwentyFiveDay.setText(getItem(position).getDay25());
                TextWatcher TvTwentyFiveDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyFiveDay.getText().toString();
                        getItem(position).setDay25(proitem);
//                        spUtils.put(context, "productionTwentyFiveDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentyFiveDay.addTextChangedListener(TvTwentyFiveDay);
                editTexTwentyFiveDay.setTag(TvTwentyFiveDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyFiveDay.setSelection(viewHolder.tvProTwentyFiveDay.length());


                viewHolder.tvProTwentySixDay.setEnabled(true);
                final EditText editTexTwentySixDay = viewHolder.tvProTwentySixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
                    editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
                }
                editTexTwentySixDay.setText(getItem(position).getDay26());
                TextWatcher TvTwentySixDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentySixDay.getText().toString();
                        getItem(position).setDay26(proitem);
//                        spUtils.put(context, "productionTwentySixDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentySixDay.addTextChangedListener(TvTwentySixDay);
                editTexTwentySixDay.setTag(TvTwentySixDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentySixDay.setSelection(viewHolder.tvProTwentySixDay.length());


                viewHolder.tvProTwentySevenDay.setEnabled(true);
                final EditText editTexTwentySevenDay = viewHolder.tvProTwentySevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
                    editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
                }
                editTexTwentySevenDay.setText(getItem(position).getDay27());
                TextWatcher TvTwentySevenDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentySevenDay.getText().toString();
                        getItem(position).setDay27(proitem);
//                        spUtils.put(context, "productionTwentySevenDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentySevenDay.addTextChangedListener(TvTwentySevenDay);
                editTexTwentySevenDay.setTag(TvTwentySevenDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentySevenDay.setSelection(viewHolder.tvProTwentySevenDay.length());


                viewHolder.tvProTwentyEightDay.setEnabled(true);
                final EditText editTexTwentyEightDay = viewHolder.tvProTwentyEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
                    editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
                }
                editTexTwentyEightDay.setText(getItem(position).getDay28());
                TextWatcher TvTwentyEightDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyEightDay.getText().toString();
                        getItem(position).setDay28(proitem);
//                        spUtils.put(context, "productionTwentyEightDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentyEightDay.addTextChangedListener(TvTwentyEightDay);
                editTexTwentyEightDay.setTag(TvTwentyEightDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyEightDay.setSelection(viewHolder.tvProTwentyEightDay.length());


                viewHolder.tvProTwentyNineDay.setEnabled(true);
                final EditText editTexTwentyNineDay = viewHolder.tvProTwentyNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
                    editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
                }
                editTexTwentyNineDay.setText(getItem(position).getDay29());
                TextWatcher TvTwentyNineDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProTwentyNineDay.getText().toString();
                        getItem(position).setDay29(proitem);
//                        spUtils.put(context, "productionTwentyNineDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexTwentyNineDay.addTextChangedListener(TvTwentyNineDay);
                editTexTwentyNineDay.setTag(TvTwentyNineDay);
            /*光标放置在文本最后*/
                viewHolder.tvProTwentyNineDay.setSelection(viewHolder.tvProTwentyNineDay.length());


                viewHolder.tvProThirtyDay.setEnabled(true);
                final EditText editTexThirtyDay = viewHolder.tvProThirtyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirtyDay.getTag() instanceof TextWatcher) {
                    editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
                }
                editTexThirtyDay.setText(getItem(position).getDay30());
                TextWatcher TvThirtyDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProThirtyDay.getText().toString();
                        getItem(position).setDay30(proitem);
//                        spUtils.put(context, "productionThirtyDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexThirtyDay.addTextChangedListener(TvThirtyDay);
                editTexThirtyDay.setTag(TvThirtyDay);
            /*光标放置在文本最后*/
                viewHolder.tvProThirtyDay.setSelection(viewHolder.tvProThirtyDay.length());


                viewHolder.tvProThirtyOneDay.setEnabled(true);
                final EditText editTexThirtyOneDay = viewHolder.tvProThirtyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
                    editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
                }
                editTexThirtyOneDay.setText(getItem(position).getDay31());
                TextWatcher TvThirtyOneDay = new TextWatcher() {
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
                        String proitem = viewHolder.tvProThirtyOneDay.getText().toString();
                        getItem(position).setDay31(proitem);
//                        spUtils.put(context, "productionThirtyOneDay", proitem);
                        String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                        if (lastmonth.equals("")) {
                            lastmont = 0;
                        } else {
                            lastmont = Integer.parseInt(lastmonth);
                        }
                        String dayone = viewHolder.tvProOneDay.getText().toString();
                        if (dayone.equals("")) {
                            day1 = 0;
                        } else {
                            day1 = Integer.parseInt(dayone);
                        }
                        String daytwo = viewHolder.tvProTwoDay.getText().toString();
                        if (daytwo.equals("")) {
                            day2 = 0;
                        } else {
                            day2 = Integer.parseInt(daytwo);
                        }
                        String dayThree = viewHolder.tvProThreeDay.getText().toString();
                        if (dayThree.equals("")) {
                            day3 = 0;
                        } else {
                            day3 = Integer.parseInt(dayThree);
                        }
                        String dayfore = viewHolder.tvProForeDay.getText().toString();
                        if (dayfore.equals("")) {
                            day4 = 0;
                        } else {
                            day4 = Integer.parseInt(dayfore);
                        }
                        String dayfive = viewHolder.tvProFiveDay.getText().toString();
                        if (dayfive.equals("")) {
                            day5 = 0;
                        } else {
                            day5 = Integer.parseInt(dayfive);
                        }
                        String daysix = viewHolder.tvProSixDay.getText().toString();
                        if (daysix.equals("")) {
                            day6 = 0;
                        } else {
                            day6 = Integer.parseInt(daysix);
                        }
                        String daySeven = viewHolder.tvProSevenDay.getText().toString();
                        if (daySeven.equals("")) {
                            day7 = 0;
                        } else {
                            day7 = Integer.parseInt(daySeven);
                        }
                        String dayEight = viewHolder.tvProEightDay.getText().toString();
                        if (dayEight.equals("")) {
                            day8 = 0;
                        } else {
                            day8 = Integer.parseInt(dayEight);
                        }
                        String dayNine = viewHolder.tvProNineDay.getText().toString();
                        if (dayNine.equals("")) {
                            day9 = 0;
                        } else {
                            day9 = Integer.parseInt(dayNine);
                        }
                        String dayTen = viewHolder.tvProTenDay.getText().toString();
                        if (dayTen.equals("")) {
                            day10 = 0;
                        } else {
                            day10 = Integer.parseInt(dayTen);
                        }
                        String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                        if (dayEleven.equals("")) {
                            day11 = 0;
                        } else {
                            day11 = Integer.parseInt(dayEleven);
                        }
                        String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                        if (dayTwelve.equals("")) {
                            day12 = 0;
                        } else {
                            day12 = Integer.parseInt(dayTwelve);
                        }
                        String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                        if (dayThirteen.equals("")) {
                            day13 = 0;
                        } else {
                            day13 = Integer.parseInt(dayThirteen);
                        }
                        String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day14 = 0;
                        } else {
                            day14 = Integer.parseInt(dayFourteen);
                        }
                        String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                        if (dayFourteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFourteen);
                        }
                        String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                        if (daySixteen.equals("")) {
                            day16 = 0;
                        } else {
                            day16 = Integer.parseInt(daySixteen);
                        }
                        String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                        if (daySeventeen.equals("")) {
                            day17 = 0;
                        } else {
                            day17 = Integer.parseInt(daySeventeen);
                        }
                        String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                        if (dayEighteen.equals("")) {
                            day18 = 0;
                        } else {
                            day18 = Integer.parseInt(dayEighteen);
                        }
                        String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                        if (dayNineteen.equals("")) {
                            day19 = 0;
                        } else {
                            day19 = Integer.parseInt(dayNineteen);
                        }
                        String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                        if (dayTwenty.equals("")) {
                            day20 = 0;
                        } else {
                            day20 = Integer.parseInt(dayTwenty);
                        }
                        String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                        if (dayTwentyOne.equals("")) {
                            day21 = 0;
                        } else {
                            day21 = Integer.parseInt(dayTwentyOne);
                        }
                        String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                        if (dayTwentyTwo.equals("")) {
                            day22 = 0;
                        } else {
                            day22 = Integer.parseInt(dayTwentyTwo);
                        }
                        String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                        if (dayTwentyThree.equals("")) {
                            day23 = 0;
                        } else {
                            day23 = Integer.parseInt(dayTwentyThree);
                        }
                        String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                        if (dayTwentyFore.equals("")) {
                            day24 = 0;
                        } else {
                            day24 = Integer.parseInt(dayTwentyFore);
                        }
                        String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                        if (dayTwentyFive.equals("")) {
                            day25 = 0;
                        } else {
                            day25 = Integer.parseInt(dayTwentyFive);
                        }
                        String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                        if (dayTwentySix.equals("")) {
                            day26 = 0;
                        } else {
                            day26 = Integer.parseInt(dayTwentySix);
                        }
                        String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                        if (dayTwentySeven.equals("")) {
                            day27 = 0;
                        } else {
                            day27 = Integer.parseInt(dayTwentySeven);
                        }
                        String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                        if (dayTwentyEight.equals("")) {
                            day28 = 0;
                        } else {
                            day28 = Integer.parseInt(dayTwentyEight);
                        }
                        String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                        if (dayTwentyNine.equals("")) {
                            day29 = 0;
                        } else {
                            day29 = Integer.parseInt(dayTwentyNine);
                        }
                        String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                        if (dayThirty.equals("")) {
                            day30 = 0;
                        } else {
                            day30 = Integer.parseInt(dayThirty);
                        }
                        String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                        if (dayThirtyOne.equals("")) {
                            day31 = 0;
                        } else {
                            day31 = Integer.parseInt(dayThirtyOne);
                        }
                        /**
                         * 计算总完工数（每月数量相加）
                         */
                        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                + day29 + day30 + day31;
                        String countmonth = String.valueOf(count);
                        viewHolder.tvProTotalCompletion.setText(countmonth);
                    }
                };
                editTexThirtyOneDay.addTextChangedListener(TvThirtyOneDay);
                editTexThirtyOneDay.setTag(TvThirtyOneDay);
            /*光标放置在文本最后*/
                viewHolder.tvProThirtyOneDay.setSelection(viewHolder.tvProThirtyOneDay.length());


                viewHolder.tvProRemarks.setEnabled(true);
                final EditText editTexRemarks = viewHolder.tvProRemarks;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexRemarks.getTag() instanceof TextWatcher) {
                    editTexRemarks.removeTextChangedListener((TextWatcher) editTexRemarks.getTag());
                }
                editTexRemarks.setText(getItem(position).getMemo());
                TextWatcher TvRemarks = new TextWatcher() {
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
                        String proitem = viewHolder.tvProRemarks.getText().toString();
                        getItem(position).setMemo(proitem);
//                        spUtils.put(context, "productionRemarks", proitem);

                    }
                };
                editTexRemarks.addTextChangedListener(TvRemarks);
                editTexRemarks.setTag(TvRemarks);
            /*光标放置在文本最后*/
                viewHolder.tvProRemarks.setSelection(viewHolder.tvProRemarks.length());

                viewHolder.tvProRecorder.setEnabled(true);
                String productionRecorder = getItem(position).getRecorder();
                viewHolder.tvProRecorder.setText(productionRecorder);

                viewHolder.tvProRecordat.setEnabled(true);
                String productionRecordat = getItem(position).getRecordat();
                viewHolder.tvProRecordat.setText(productionRecordat);
            } else {
//                viewHolder.tv_data.setEnabled(false);
//                viewHolder.tv_data.setText(getItem(position).getItem());

                viewHolder.tvProDocumentary.setEnabled(false);
                viewHolder.tvProDocumentary.setText(getItem(position).getPrddocumentary());

                viewHolder.tvProFactory.setEnabled(false);
                viewHolder.tvProFactory.setText(getItem(position).getSubfactory());

                viewHolder.tvProDepartment.setEnabled(false);
                viewHolder.tvProDepartment.setText(getItem(position).getSubfactoryTeams());

                viewHolder.tvProProcedure.setEnabled(false);
                viewHolder.tvProProcedure.setText(getItem(position).getWorkingProcedure());

                viewHolder.tvProOthers.setEnabled(false);
//                final EditText editTexOthers = viewHolder.tvProOthers;
//            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
//                if (editTexOthers.getTag() instanceof TextWatcher) {
//                    editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
//                }
                viewHolder.tvProOthers.setText(getItem(position).getWorkers());
                viewHolder.tvProOthers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                viewHolder.tvProSingularSystem.setEnabled(false);
                viewHolder.tvProSingularSystem.setText(getItem(position).getPqty());

                viewHolder.tvProColor.setEnabled(false);
                viewHolder.tvProColor.setText(getItem(position).getProdcol());

                viewHolder.tvProTaskNumber.setEnabled(false);
                final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                    editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
                }
                editTexTaskNumber.setText(getItem(position).getTaskqty());

                viewHolder.tvProSize.setEnabled(false);
                viewHolder.tvProSize.setText(getItem(position).getMdl());

                viewHolder.tvProClippingNumber.setEnabled(false);
                viewHolder.tvProClippingNumber.setText(getItem(position).getFactcutqty());

                viewHolder.tvProCompletedLastMonth.setEnabled(false);
                final EditText editTexCompletedLastMonth = viewHolder.tvProCompletedLastMonth;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexCompletedLastMonth.getTag() instanceof TextWatcher) {
                    editTexCompletedLastMonth.removeTextChangedListener((TextWatcher) editTexCompletedLastMonth.getTag());
                }
                editTexCompletedLastMonth.setText(getItem(position).getLastMonQty());

                viewHolder.tvProTotalCompletion.setEnabled(false);
                viewHolder.tvProTotalCompletion.setText(getItem(position).getSumCompletedQty());

                viewHolder.tvProBalanceAmount.setEnabled(false);
                viewHolder.tvProBalanceAmount.setText(getItem(position).getLeftQty());

                viewHolder.tvProState.setEnabled(false);
                viewHolder.tvProState.setText(getItem(position).getPrdstatus());

                viewHolder.tvProYear.setEnabled(false);
                viewHolder.tvProYear.setText(getItem(position).getYear());

                viewHolder.tvProMonth.setEnabled(false);
                viewHolder.tvProMonth.setText(getItem(position).getMonth());

                viewHolder.tvProOneDay.setEnabled(false);
                final EditText editTexOneDay = viewHolder.tvProOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexOneDay.getTag() instanceof TextWatcher) {
                    editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
                }
                editTexOneDay.setText(getItem(position).getDay1());

                viewHolder.tvProTwoDay.setEnabled(false);
                final EditText editTexTwoDay = viewHolder.tvProTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwoDay.getTag() instanceof TextWatcher) {
                    editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
                }
                editTexTwoDay.setText(getItem(position).getDay2());

                viewHolder.tvProThreeDay.setEnabled(false);
                final EditText editTexThreeDay = viewHolder.tvProThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThreeDay.getTag() instanceof TextWatcher) {
                    editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
                }
                editTexThreeDay.setText(getItem(position).getDay3());

                viewHolder.tvProForeDay.setEnabled(false);
                final EditText editTexForeDay = viewHolder.tvProForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexForeDay.getTag() instanceof TextWatcher) {
                    editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
                }
                editTexForeDay.setText(getItem(position).getDay4());

                viewHolder.tvProFiveDay.setEnabled(false);
                final EditText editTexFiveDay = viewHolder.tvProFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFiveDay.getTag() instanceof TextWatcher) {
                    editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
                }
                editTexFiveDay.setText(getItem(position).getDay5());

                viewHolder.tvProSixDay.setEnabled(false);
                final EditText editTexSixDay = viewHolder.tvProSixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSixDay.getTag() instanceof TextWatcher) {
                    editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
                }
                editTexSixDay.setText(getItem(position).getDay6());

                viewHolder.tvProSevenDay.setEnabled(false);
                final EditText editTexSevenDay = viewHolder.tvProSevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSevenDay.getTag() instanceof TextWatcher) {
                    editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
                }
                editTexSevenDay.setText(getItem(position).getDay7());

                viewHolder.tvProEightDay.setEnabled(false);
                final EditText editTexEightDay = viewHolder.tvProEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexEightDay.getTag() instanceof TextWatcher) {
                    editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
                }
                editTexEightDay.setText(getItem(position).getDay8());

                viewHolder.tvProNineDay.setEnabled(false);
                final EditText editTexNineDay = viewHolder.tvProNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexNineDay.getTag() instanceof TextWatcher) {
                    editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
                }
                editTexNineDay.setText(getItem(position).getDay9());

                viewHolder.tvProTenDay.setEnabled(false);
                final EditText editTexTenDay = viewHolder.tvProTenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTenDay.getTag() instanceof TextWatcher) {
                    editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
                }
                editTexTenDay.setText(getItem(position).getDay10());

                viewHolder.tvProElevenDay.setEnabled(false);
                final EditText editTexElevenDay = viewHolder.tvProElevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexElevenDay.getTag() instanceof TextWatcher) {
                    editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
                }
                editTexElevenDay.setText(getItem(position).getDay11());

                viewHolder.tvProTwelveDay.setEnabled(false);
                final EditText editTexTwelveDay = viewHolder.tvProTwelveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwelveDay.getTag() instanceof TextWatcher) {
                    editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
                }
                editTexTwelveDay.setText(getItem(position).getDay12());

                viewHolder.tvProThirteenDay.setEnabled(false);
                final EditText editTexThirteenDay = viewHolder.tvProThirteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirteenDay.getTag() instanceof TextWatcher) {
                    editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
                }
                editTexThirteenDay.setText(getItem(position).getDay13());

                viewHolder.tvProFourteenDay.setEnabled(false);
                final EditText editTexFourteenDay = viewHolder.tvProFourteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFourteenDay.getTag() instanceof TextWatcher) {
                    editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
                }
                editTexFourteenDay.setText(getItem(position).getDay14());

                viewHolder.tvProFifteenDay.setEnabled(false);
                final EditText editTexFifteenDay = viewHolder.tvProFifteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexFifteenDay.getTag() instanceof TextWatcher) {
                    editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
                }
                editTexFifteenDay.setText(getItem(position).getDay15());

                viewHolder.tvProSixteenDay.setEnabled(false);
                final EditText editTexSixteenDay = viewHolder.tvProSixteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSixteenDay.getTag() instanceof TextWatcher) {
                    editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
                }
                editTexSixteenDay.setText(getItem(position).getDay16());

                viewHolder.tvProSeventeenDay.setEnabled(false);
                final EditText editTexSeventeenDay = viewHolder.tvProSeventeenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
                    editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
                }
                editTexSeventeenDay.setText(getItem(position).getDay17());

                viewHolder.tvProEighteenDay.setEnabled(false);
                final EditText editTexEighteenDay = viewHolder.tvProEighteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexEighteenDay.getTag() instanceof TextWatcher) {
                    editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
                }
                editTexEighteenDay.setText(getItem(position).getDay18());

                viewHolder.tvProNineteenDay.setEnabled(false);
                final EditText editTexNineteenDay = viewHolder.tvProNineteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexNineteenDay.getTag() instanceof TextWatcher) {
                    editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
                }
                editTexNineteenDay.setText(getItem(position).getDay19());

                viewHolder.tvProTwentyDay.setEnabled(false);
                final EditText editTexTwentyDay = viewHolder.tvProTwentyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyDay.getTag() instanceof TextWatcher) {
                    editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
                }
                editTexTwentyDay.setText(getItem(position).getDay20());

                viewHolder.tvProTwentyOneDay.setEnabled(false);
                final EditText editTexTwentyOneDay = viewHolder.tvProTwentyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
                    editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
                }
                editTexTwentyOneDay.setText(getItem(position).getDay21());

                viewHolder.tvProTwentyTwoDay.setEnabled(false);
                final EditText editTexTwentyTwoDay = viewHolder.tvProTwentyTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
                    editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
                }
                editTexTwentyTwoDay.setText(getItem(position).getDay22());

                viewHolder.tvProTwentyThreeDay.setEnabled(false);
                final EditText editTexTwentyThreeDay = viewHolder.tvProTwentyThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
                    editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
                }
                editTexTwentyThreeDay.setText(getItem(position).getDay23());

                viewHolder.tvProTwentyForeDay.setEnabled(false);
                final EditText editTexTwentyForeDay = viewHolder.tvProTwentyForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
                    editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
                }
                editTexTwentyForeDay.setText(getItem(position).getDay24());

                viewHolder.tvProTwentyFiveDay.setEnabled(false);
                final EditText editTexTwentyFiveDay = viewHolder.tvProTwentyFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
                    editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
                }
                editTexTwentyFiveDay.setText(getItem(position).getDay25());

                viewHolder.tvProTwentySixDay.setEnabled(false);
                final EditText editTexTwentySixDay = viewHolder.tvProTwentySixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
                    editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
                }
                editTexTwentySixDay.setText(getItem(position).getDay26());

                viewHolder.tvProTwentySevenDay.setEnabled(false);
                final EditText editTexTwentySevenDay = viewHolder.tvProTwentySevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
                    editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
                }
                editTexTwentySevenDay.setText(getItem(position).getDay27());

                viewHolder.tvProTwentyEightDay.setEnabled(false);
                final EditText editTexTwentyEightDay = viewHolder.tvProTwentyEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
                    editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
                }
                editTexTwentyEightDay.setText(getItem(position).getDay28());

                viewHolder.tvProTwentyNineDay.setEnabled(false);
                final EditText editTexTwentyNineDay = viewHolder.tvProTwentyNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
                    editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
                }
                editTexTwentyNineDay.setText(getItem(position).getDay29());

                viewHolder.tvProThirtyDay.setEnabled(false);
                final EditText editTexThirtyDay = viewHolder.tvProThirtyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirtyDay.getTag() instanceof TextWatcher) {
                    editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
                }
                editTexThirtyDay.setText(getItem(position).getDay30());

                viewHolder.tvProThirtyOneDay.setEnabled(false);
                final EditText editTexThirtyOneDay = viewHolder.tvProThirtyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
                    editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
                }
                editTexThirtyOneDay.setText(getItem(position).getDay31());

                viewHolder.tvProRemarks.setEnabled(false);
                final EditText editTexRemarks = viewHolder.tvProRemarks;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexRemarks.getTag() instanceof TextWatcher) {
                    editTexRemarks.removeTextChangedListener((TextWatcher) editTexRemarks.getTag());
                }
                editTexRemarks.setText(getItem(position).getMemo());

                viewHolder.tvProRecorder.setEnabled(false);
                viewHolder.tvProRecorder.setText(getItem(position).getRecorder());

                viewHolder.tvProRecordat.setEnabled(false);
                viewHolder.tvProRecordat.setText(getItem(position).getRecordat());
            }
            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
            if (lastmonth.equals("")) {
                lastmont = 0;
            } else {
                lastmont = Integer.parseInt(lastmonth);
            }
            String dayone = viewHolder.tvProOneDay.getText().toString();
            if (dayone.equals("")) {
                day1 = 0;
            } else {
                day1 = Integer.parseInt(dayone);
            }
            String daytwo = viewHolder.tvProTwoDay.getText().toString();
            if (daytwo.equals("")) {
                day2 = 0;
            } else {
                day2 = Integer.parseInt(daytwo);
            }
            String dayThree = viewHolder.tvProThreeDay.getText().toString();
            if (dayThree.equals("")) {
                day3 = 0;
            } else {
                day3 = Integer.parseInt(dayThree);
            }
            String dayfore = viewHolder.tvProForeDay.getText().toString();
            if (dayfore.equals("")) {
                day4 = 0;
            } else {
                day4 = Integer.parseInt(dayfore);
            }
            String dayfive = viewHolder.tvProFiveDay.getText().toString();
            if (dayfive.equals("")) {
                day5 = 0;
            } else {
                day5 = Integer.parseInt(dayfive);
            }
            String daysix = viewHolder.tvProSixDay.getText().toString();
            if (daysix.equals("")) {
                day6 = 0;
            } else {
                day6 = Integer.parseInt(daysix);
            }
            String daySeven = viewHolder.tvProSevenDay.getText().toString();
            if (daySeven.equals("")) {
                day7 = 0;
            } else {
                day7 = Integer.parseInt(daySeven);
            }
            String dayEight = viewHolder.tvProEightDay.getText().toString();
            if (dayEight.equals("")) {
                day8 = 0;
            } else {
                day8 = Integer.parseInt(dayEight);
            }
            String dayNine = viewHolder.tvProNineDay.getText().toString();
            if (dayNine.equals("")) {
                day9 = 0;
            } else {
                day9 = Integer.parseInt(dayNine);
            }
            String dayTen = viewHolder.tvProTenDay.getText().toString();
            if (dayTen.equals("")) {
                day10 = 0;
            } else {
                day10 = Integer.parseInt(dayTen);
            }
            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
            if (dayEleven.equals("")) {
                day11 = 0;
            } else {
                day11 = Integer.parseInt(dayEleven);
            }
            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
            if (dayTwelve.equals("")) {
                day12 = 0;
            } else {
                day12 = Integer.parseInt(dayTwelve);
            }
            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
            if (dayThirteen.equals("")) {
                day13 = 0;
            } else {
                day13 = Integer.parseInt(dayThirteen);
            }
            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
            if (dayFourteen.equals("")) {
                day14 = 0;
            } else {
                day14 = Integer.parseInt(dayFourteen);
            }
            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
            if (dayFourteen.equals("")) {
                day15 = 0;
            } else {
                day15 = Integer.parseInt(dayFourteen);
            }
            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
            if (daySixteen.equals("")) {
                day16 = 0;
            } else {
                day16 = Integer.parseInt(daySixteen);
            }
            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
            if (daySeventeen.equals("")) {
                day17 = 0;
            } else {
                day17 = Integer.parseInt(daySeventeen);
            }
            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
            if (dayEighteen.equals("")) {
                day18 = 0;
            } else {
                day18 = Integer.parseInt(dayEighteen);
            }
            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
            if (dayNineteen.equals("")) {
                day19 = 0;
            } else {
                day19 = Integer.parseInt(dayNineteen);
            }
            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
            if (dayTwenty.equals("")) {
                day20 = 0;
            } else {
                day20 = Integer.parseInt(dayTwenty);
            }
            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
            if (dayTwentyOne.equals("")) {
                day21 = 0;
            } else {
                day21 = Integer.parseInt(dayTwentyOne);
            }
            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
            if (dayTwentyTwo.equals("")) {
                day22 = 0;
            } else {
                day22 = Integer.parseInt(dayTwentyTwo);
            }
            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
            if (dayTwentyThree.equals("")) {
                day23 = 0;
            } else {
                day23 = Integer.parseInt(dayTwentyThree);
            }
            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
            if (dayTwentyFore.equals("")) {
                day24 = 0;
            } else {
                day24 = Integer.parseInt(dayTwentyFore);
            }
            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
            if (dayTwentyFive.equals("")) {
                day25 = 0;
            } else {
                day25 = Integer.parseInt(dayTwentyFive);
            }
            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
            if (dayTwentySix.equals("")) {
                day26 = 0;
            } else {
                day26 = Integer.parseInt(dayTwentySix);
            }
            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
            if (dayTwentySeven.equals("")) {
                day27 = 0;
            } else {
                day27 = Integer.parseInt(dayTwentySeven);
            }
            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
            if (dayTwentyEight.equals("")) {
                day28 = 0;
            } else {
                day28 = Integer.parseInt(dayTwentyEight);
            }
            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
            if (dayTwentyNine.equals("")) {
                day29 = 0;
            } else {
                day29 = Integer.parseInt(dayTwentyNine);
            }
            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
            if (dayThirty.equals("")) {
                day30 = 0;
            } else {
                day30 = Integer.parseInt(dayThirty);
            }
            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
            if (dayThirtyOne.equals("")) {
                day31 = 0;
            } else {
                day31 = Integer.parseInt(dayThirtyOne);
            }
            /**
             * 计算总完工数（每月数量相加）
             */
            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                    + day29 + day30 + day31;
            String countmonth = String.valueOf(count);
            viewHolder.tvProTotalCompletion.setText(countmonth);

            String TaskNumber = viewHolder.tvProTaskNumber.getText().toString();
            if (TaskNumber.equals("")) {
                skNumber = 0;
            } else {
                skNumber = Integer.parseInt(TaskNumber);
            }
            String Completion = viewHolder.tvProTotalCompletion.getText().toString();
            if (Completion.equals("")) {
                countmon = 0;
            } else {
                countmon = Integer.parseInt(Completion);
            }
            /**
             * 计算结余数量（任务数-总完工数）
             */
            int Amount = skNumber - countmon;
            String BalanceAmount = String.valueOf(Amount);
            viewHolder.tvProBalanceAmount.setText(BalanceAmount);

        } else {
//            viewHolder.tv_data.setEnabled(false);
//            viewHolder.tv_data.setText(getItem(position).getItem());

            viewHolder.tvProDocumentary.setEnabled(false);
            viewHolder.tvProDocumentary.setText(getItem(position).getPrddocumentary());

            viewHolder.tvProFactory.setEnabled(false);
            viewHolder.tvProFactory.setText(getItem(position).getSubfactory());

            viewHolder.tvProDepartment.setEnabled(false);
            viewHolder.tvProDepartment.setText(getItem(position).getSubfactoryTeams());

            viewHolder.tvProProcedure.setEnabled(false);
            viewHolder.tvProProcedure.setText(getItem(position).getWorkingProcedure());

            viewHolder.tvProOthers.setEnabled(false);
//            final EditText editTexOthers = viewHolder.tvProOthers;
//            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
//            if (editTexOthers.getTag() instanceof TextWatcher) {
//                editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
//            }
            viewHolder.tvProOthers.setText(getItem(position).getWorkers());
            viewHolder.tvProOthers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            viewHolder.tvProSingularSystem.setEnabled(false);
            viewHolder.tvProSingularSystem.setText(getItem(position).getPqty());

            viewHolder.tvProColor.setEnabled(false);
            viewHolder.tvProColor.setText(getItem(position).getProdcol());

            viewHolder.tvProTaskNumber.setEnabled(false);
            final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
            }
            editTexTaskNumber.setText(getItem(position).getTaskqty());

            viewHolder.tvProSize.setEnabled(false);
            viewHolder.tvProSize.setText(getItem(position).getMdl());

            viewHolder.tvProClippingNumber.setEnabled(false);
            viewHolder.tvProClippingNumber.setText(getItem(position).getFactcutqty());

            viewHolder.tvProCompletedLastMonth.setEnabled(false);
            final EditText editTexCompletedLastMonth = viewHolder.tvProCompletedLastMonth;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexCompletedLastMonth.getTag() instanceof TextWatcher) {
                editTexCompletedLastMonth.removeTextChangedListener((TextWatcher) editTexCompletedLastMonth.getTag());
            }
            editTexCompletedLastMonth.setText(getItem(position).getLastMonQty());

            viewHolder.tvProTotalCompletion.setEnabled(false);
            viewHolder.tvProTotalCompletion.setText(getItem(position).getSumCompletedQty());

            viewHolder.tvProBalanceAmount.setEnabled(false);
            viewHolder.tvProBalanceAmount.setText(getItem(position).getLeftQty());

            viewHolder.tvProState.setEnabled(false);
            viewHolder.tvProState.setText(getItem(position).getPrdstatus());

            viewHolder.tvProYear.setEnabled(false);
            viewHolder.tvProYear.setText(getItem(position).getYear());

            viewHolder.tvProMonth.setEnabled(false);
            viewHolder.tvProMonth.setText(getItem(position).getMonth());

            viewHolder.tvProOneDay.setEnabled(false);
            final EditText editTexOneDay = viewHolder.tvProOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexOneDay.getTag() instanceof TextWatcher) {
                editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
            }
            editTexOneDay.setText(getItem(position).getDay1());

            viewHolder.tvProTwoDay.setEnabled(false);
            final EditText editTexTwoDay = viewHolder.tvProTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwoDay.getTag() instanceof TextWatcher) {
                editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
            }
            editTexTwoDay.setText(getItem(position).getDay2());

            viewHolder.tvProThreeDay.setEnabled(false);
            final EditText editTexThreeDay = viewHolder.tvProThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexThreeDay.getTag() instanceof TextWatcher) {
                editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
            }
            editTexThreeDay.setText(getItem(position).getDay3());

            viewHolder.tvProForeDay.setEnabled(false);
            final EditText editTexForeDay = viewHolder.tvProForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexForeDay.getTag() instanceof TextWatcher) {
                editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
            }
            editTexForeDay.setText(getItem(position).getDay4());

            viewHolder.tvProFiveDay.setEnabled(false);
            final EditText editTexFiveDay = viewHolder.tvProFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexFiveDay.getTag() instanceof TextWatcher) {
                editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
            }
            editTexFiveDay.setText(getItem(position).getDay5());

            viewHolder.tvProSixDay.setEnabled(false);
            final EditText editTexSixDay = viewHolder.tvProSixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexSixDay.getTag() instanceof TextWatcher) {
                editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
            }
            editTexSixDay.setText(getItem(position).getDay6());

            viewHolder.tvProSevenDay.setEnabled(false);
            final EditText editTexSevenDay = viewHolder.tvProSevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexSevenDay.getTag() instanceof TextWatcher) {
                editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
            }
            editTexSevenDay.setText(getItem(position).getDay7());

            viewHolder.tvProEightDay.setEnabled(false);
            final EditText editTexEightDay = viewHolder.tvProEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexEightDay.getTag() instanceof TextWatcher) {
                editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
            }
            editTexEightDay.setText(getItem(position).getDay8());

            viewHolder.tvProNineDay.setEnabled(false);
            final EditText editTexNineDay = viewHolder.tvProNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexNineDay.getTag() instanceof TextWatcher) {
                editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
            }
            editTexNineDay.setText(getItem(position).getDay9());

            viewHolder.tvProTenDay.setEnabled(false);
            final EditText editTexTenDay = viewHolder.tvProTenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTenDay.getTag() instanceof TextWatcher) {
                editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
            }
            editTexTenDay.setText(getItem(position).getDay10());

            viewHolder.tvProElevenDay.setEnabled(false);
            final EditText editTexElevenDay = viewHolder.tvProElevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexElevenDay.getTag() instanceof TextWatcher) {
                editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
            }
            editTexElevenDay.setText(getItem(position).getDay11());

            viewHolder.tvProTwelveDay.setEnabled(false);
            final EditText editTexTwelveDay = viewHolder.tvProTwelveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwelveDay.getTag() instanceof TextWatcher) {
                editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
            }
            editTexTwelveDay.setText(getItem(position).getDay12());

            viewHolder.tvProThirteenDay.setEnabled(false);
            final EditText editTexThirteenDay = viewHolder.tvProThirteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexThirteenDay.getTag() instanceof TextWatcher) {
                editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
            }
            editTexThirteenDay.setText(getItem(position).getDay13());

            viewHolder.tvProFourteenDay.setEnabled(false);
            final EditText editTexFourteenDay = viewHolder.tvProFourteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexFourteenDay.getTag() instanceof TextWatcher) {
                editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
            }
            editTexFourteenDay.setText(getItem(position).getDay14());

            viewHolder.tvProFifteenDay.setEnabled(false);
            final EditText editTexFifteenDay = viewHolder.tvProFifteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexFifteenDay.getTag() instanceof TextWatcher) {
                editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
            }
            editTexFifteenDay.setText(getItem(position).getDay15());

            viewHolder.tvProSixteenDay.setEnabled(false);
            final EditText editTexSixteenDay = viewHolder.tvProSixteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexSixteenDay.getTag() instanceof TextWatcher) {
                editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
            }
            editTexSixteenDay.setText(getItem(position).getDay16());

            viewHolder.tvProSeventeenDay.setEnabled(false);
            final EditText editTexSeventeenDay = viewHolder.tvProSeventeenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
                editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
            }
            editTexSeventeenDay.setText(getItem(position).getDay17());

            viewHolder.tvProEighteenDay.setEnabled(false);
            final EditText editTexEighteenDay = viewHolder.tvProEighteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexEighteenDay.getTag() instanceof TextWatcher) {
                editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
            }
            editTexEighteenDay.setText(getItem(position).getDay18());

            viewHolder.tvProNineteenDay.setEnabled(false);
            final EditText editTexNineteenDay = viewHolder.tvProNineteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexNineteenDay.getTag() instanceof TextWatcher) {
                editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
            }
            editTexNineteenDay.setText(getItem(position).getDay19());

            viewHolder.tvProTwentyDay.setEnabled(false);
            final EditText editTexTwentyDay = viewHolder.tvProTwentyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentyDay.getTag() instanceof TextWatcher) {
                editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
            }
            editTexTwentyDay.setText(getItem(position).getDay20());

            viewHolder.tvProTwentyOneDay.setEnabled(false);
            final EditText editTexTwentyOneDay = viewHolder.tvProTwentyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
                editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
            }
            editTexTwentyOneDay.setText(getItem(position).getDay21());

            viewHolder.tvProTwentyTwoDay.setEnabled(false);
            final EditText editTexTwentyTwoDay = viewHolder.tvProTwentyTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
                editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
            }
            editTexTwentyTwoDay.setText(getItem(position).getDay22());

            viewHolder.tvProTwentyThreeDay.setEnabled(false);
            final EditText editTexTwentyThreeDay = viewHolder.tvProTwentyThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
                editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
            }
            editTexTwentyThreeDay.setText(getItem(position).getDay23());

            viewHolder.tvProTwentyForeDay.setEnabled(false);
            final EditText editTexTwentyForeDay = viewHolder.tvProTwentyForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
                editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
            }
            editTexTwentyForeDay.setText(getItem(position).getDay24());

            viewHolder.tvProTwentyFiveDay.setEnabled(false);
            final EditText editTexTwentyFiveDay = viewHolder.tvProTwentyFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
                editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
            }
            editTexTwentyFiveDay.setText(getItem(position).getDay25());

            viewHolder.tvProTwentySixDay.setEnabled(false);
            final EditText editTexTwentySixDay = viewHolder.tvProTwentySixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
                editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
            }
            editTexTwentySixDay.setText(getItem(position).getDay26());

            viewHolder.tvProTwentySevenDay.setEnabled(false);
            final EditText editTexTwentySevenDay = viewHolder.tvProTwentySevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
                editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
            }
            editTexTwentySevenDay.setText(getItem(position).getDay27());

            viewHolder.tvProTwentyEightDay.setEnabled(false);
            final EditText editTexTwentyEightDay = viewHolder.tvProTwentyEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
                editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
            }
            editTexTwentyEightDay.setText(getItem(position).getDay28());

            viewHolder.tvProTwentyNineDay.setEnabled(false);
            final EditText editTexTwentyNineDay = viewHolder.tvProTwentyNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
                editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
            }
            editTexTwentyNineDay.setText(getItem(position).getDay29());

            viewHolder.tvProThirtyDay.setEnabled(false);
            final EditText editTexThirtyDay = viewHolder.tvProThirtyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexThirtyDay.getTag() instanceof TextWatcher) {
                editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
            }
            editTexThirtyDay.setText(getItem(position).getDay30());

            viewHolder.tvProThirtyOneDay.setEnabled(false);
            final EditText editTexThirtyOneDay = viewHolder.tvProThirtyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
                editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
            }
            editTexThirtyOneDay.setText(getItem(position).getDay31());

            viewHolder.tvProRemarks.setEnabled(false);
            final EditText editTexRemarks = viewHolder.tvProRemarks;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexRemarks.getTag() instanceof TextWatcher) {
                editTexRemarks.removeTextChangedListener((TextWatcher) editTexRemarks.getTag());
            }
            editTexRemarks.setText(getItem(position).getMemo());

            viewHolder.tvProRecorder.setEnabled(false);
            viewHolder.tvProRecorder.setText(getItem(position).getRecorder());

            viewHolder.tvProRecordat.setEnabled(false);
            viewHolder.tvProRecordat.setText(getItem(position).getRecordat());
        }

        viewHolder.tvProMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_mouth,
                        popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        sp = context.getSharedPreferences("userInfo", 0);
                        String title = item.getTitle().toString();
//                        spUtils.put(context, "proadapterMonthTitle", title);
                        viewHolder.tvProMonth.setText(title);
                        getItem(position).setMonth(title);
                        return false;
                    }
                });
                // PopupMenu关闭事件
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                    }
                });
                popupMenu.show();
            }
        });


        viewHolder.tvProDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_column, popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        sp = context.getSharedPreferences("userInfo", 0);
                        String title = item.getTitle().toString();
//                        spUtils.put(context, "proColumnTitle", title);
                        viewHolder.tvProDepartment.setText(title);
                        getItem(position).setPrddocumentary(title);
                        return false;
                    }
                });
                // PopupMenu关闭事件
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                    }
                });
                popupMenu.show();
            }
        });

        viewHolder.tvProProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_procedure, popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        sp = context.getSharedPreferences("userInfo", 0);
                        String title = item.getTitle().toString();
//                        spUtils.put(context, "proProcedureTitle", title);
                        viewHolder.tvProProcedure.setText(title);
                        getItem(position).setWorkingProcedure(title);
                        return false;
                    }
                });
                // PopupMenu关闭事件
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                    }
                });
                popupMenu.show();
            }
        });

        viewHolder.tvProState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_prdstatus, popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        sp = context.getSharedPreferences("userInfo", 0);
                        String title = item.getTitle().toString();
//                        spUtils.put(context, "proadapterPrdstatusTitle", title);
                        viewHolder.tvProState.setText(title);
                        getItem(position).setPrdstatus(title);
                        return false;
                    }
                });
                // PopupMenu关闭事件
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                    }
                });
                popupMenu.show();
            }
        });


        return convertView;
    }

    void getcon() {

    }

    class ViewHolder {
        View data_ll_vertical;
        LinearLayout lin_content;
        TextView tv_data;//款号
        TextView tvProDocumentary,//跟单
                tvProFactory;//工厂
        TextView
                tvProDepartment,//部门/组别
                tvProState,//状态
                tvProProcedure;//工序
        EditText tvProOthers, //组别人
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
