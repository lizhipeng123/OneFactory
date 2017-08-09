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
import com.daoran.newfactory.onefactory.activity.work.production.ProductionCopyComfigActivity;
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.bean.ProducationSaveBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PhoneSaveUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 生产日报适配器
 * Created by lizhipeng on 2017/4/26.
 */

public class ProductionAdapter extends BaseAdapter {
    private static final String TAG = "test";
    private Context context;
    private List<ProducationDetailBean.DataBean> dataBeen;
    private SharedPreferences sp;
    private ProducationSaveBean saveBean;
    private SPUtils spUtils;
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
        saveBean = new ProducationSaveBean();
        String nameid = sp.getString("usernamerecoder", "");
        String recorder = getItem(position).getRecorder();
        if (recorder == null) {
            recorder = "";
        }
        if (!recorder.equals("")) {
            if (recorder.equals(nameid)) {
                viewHolder.lin_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.lin_content.setBackgroundResource(R.drawable.bill_record_historylist);
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
                                editor.putString("mycopylistStr", liststr);
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
                    }
                });
                viewHolder.tvProDocumentary.setEnabled(true);
                String productionadapterDocumentary = getItem(position).getPrddocumentary();
                viewHolder.tvProDocumentary.setText(productionadapterDocumentary);

                viewHolder.tvProFactory.setEnabled(true);
                String productionFactory = getItem(position).getSubfactory();
                viewHolder.tvProFactory.setText(productionFactory);

                viewHolder.tvProDepartment.setEnabled(true);
                viewHolder.tvProDepartment.setText(getItem(position).getSubfactoryTeams());

                viewHolder.tvProProcedure.setEnabled(false);
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
                        if (start > 0) {
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
                        spUtils.put(context, "prosaveothers", proitem);
                        String progetothers = getItem(position).getWorkers();
                        if (progetothers == null) {
                            progetothers = "";
                        }
                        String nullothers;
                        if (progetothers.equals(proitem)) {
                            nullothers = "1";
                        } else {
                            nullothers = "2";
                            saveBean.setWorkers(proitem);
                            getItem(position).setWorkers(proitem);
                        }
                        spUtils.put(context, "pronullothers", nullothers);
                        viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.length());
                    }
                };
                editTexOthers.addTextChangedListener(TvOthers);
                editTexOthers.setTag(TvOthers);
                /*光标放置在文本最后*/
                viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.getText().length());


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
                        spUtils.put(context, "prosavetasknunber", proitem);
                        String progettasknumber = getItem(position).getTaskqty();
                        if (progettasknumber == null) {
                            progettasknumber = "";
                        }
                        String nulltasknumber;
                        if (progettasknumber.equals(proitem)) {
                            nulltasknumber = "1";
                        } else {
                            nulltasknumber = "2";
                            getItem(position).setTaskqty(proitem);
                            saveBean.setTaskqty(proitem);
                        }
                        spUtils.put(context, "pronulltasknumber", nulltasknumber);
                        viewHolder.tvProTaskNumber.setSelection(viewHolder.tvProTaskNumber.length());
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
                        String progetlastmon = getItem(position).getLastMonQty();
                        if (progetlastmon == null) {
                            progetlastmon = "";
                        }
                        String nulllastmon;
                        if (progetlastmon.equals(proitem)) {
                            nulllastmon = "1";
                        } else {
                            nulllastmon = "2";
                            getItem(position).setLastMonQty(proitem);
                            saveBean.setLastMonQty(proitem);
                        }
                        spUtils.put(context, "pronulllastmon", nulllastmon);
                        spUtils.put(context, "prosavecompletedlastmonth", proitem);
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
                        String progetday1 = getItem(position).getDay1();
                        if (progetday1 == null) {
                            progetday1 = "";
                        }
                        String nullday1;
                        if (progetday1.equals(proitem)) {
                            nullday1 = "1";
                        } else {
                            nullday1 = "2";
                            if (proitem.equals("")) {
                                proitem = String.valueOf(0);
                            }
                            getItem(position).setDay1(proitem);
                            saveBean.setDay1(proitem);
                        }
                        spUtils.put(context, "pronullday1", nullday1);
                        spUtils.put(context, "prosaveoneday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProOneDay.setSelection(viewHolder.tvProOneDay.getText().length());


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
                        String progetday2 = getItem(position).getDay2();
                        if (progetday2 == null) {
                            progetday2 = "";
                        }
                        String nullday2;
                        if (progetday2.equals(proitem)) {
                            nullday2 = "1";
                        } else {
                            nullday2 = "2";
                            if (proitem.equals("")) {
                                proitem = String.valueOf(0);
                            }
                            getItem(position).setDay2(proitem);
                            saveBean.setDay2(proitem);
                        }
                        spUtils.put(context, "pronullday2", nullday2);
                        spUtils.put(context, "prosavetwoday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwoDay.setSelection(viewHolder.tvProTwoDay.getText().length());


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
                        String progetday3 = getItem(position).getDay3();
                        if (progetday3 == null) {
                            progetday3 = "";
                        }
                        String nullday3;
                        if (progetday3.equals(proitem)) {
                            nullday3 = "1";
                        } else {
                            nullday3 = "2";
                            if (proitem.equals("")) {
                                proitem = String.valueOf(0);
                            }
                            getItem(position).setDay3(proitem);
                            saveBean.setDay3(proitem);
                        }
                        spUtils.put(context, "pronullday3", nullday3);
                        spUtils.put(context, "prothreeday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProThreeDay.setSelection(viewHolder.tvProThreeDay.getText().length());


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
                        String progetday4 = getItem(position).getDay4();
                        if (progetday4 == null) {
                            progetday4 = "";
                        }
                        String nullday4;
                        if (progetday4.equals(proitem)) {
                            nullday4 = "1";
                        } else {
                            nullday4 = "2";
                            if (proitem.equals("")) {
                                proitem = String.valueOf(0);
                            }
                            getItem(position).setDay4(proitem);
                            saveBean.setDay4(proitem);
                        }
                        spUtils.put(context, "pronullday4", nullday4);
                        spUtils.put(context, "prosaveforeday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProForeDay.setSelection(viewHolder.tvProForeDay.getText().length());


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
                        String progetday5 = getItem(position).getDay5();
                        if (progetday5 == null) {
                            progetday5 = "";
                        }
                        String nullday5;
                        if (progetday5.equals(proitem)) {
                            nullday5 = "1";
                        } else {
                            nullday5 = "2";
                            if (proitem.equals("")) {
                                proitem = String.valueOf(0);
                            }
                            getItem(position).setDay5(proitem);
                            saveBean.setDay5(proitem);
                        }
                        spUtils.put(context, "pronullday5", nullday5);
                        spUtils.put(context, "prosavefiveday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProFiveDay.setSelection(viewHolder.tvProFiveDay.getText().length());


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
                        String progetday6 = getItem(position).getDay6();
                        if (progetday6 == null) {
                            progetday6 = "";
                        }
                        String nullday6;
                        if (progetday6.equals(proitem)) {
                            nullday6 = "1";
                        } else {
                            nullday6 = "2";
                            getItem(position).setDay6(proitem);
                            saveBean.setDay6(proitem);
                        }
                        spUtils.put(context, "pronullday6", nullday6);
                        spUtils.put(context, "prosavesixday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProSixDay.setSelection(viewHolder.tvProSixDay.getText().length());


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
                        String progetday7 = getItem(position).getDay7();
                        if (progetday7 == null) {
                            progetday7 = "";
                        }
                        String nullday7;
                        if (progetday7.equals(proitem)) {
                            nullday7 = "1";
                        } else {
                            nullday7 = "2";
                            getItem(position).setDay7(proitem);
                            saveBean.setDay7(proitem);
                        }
                        spUtils.put(context, "pronullday7", nullday7);
                        spUtils.put(context, "prosavesevenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProSevenDay.setSelection(viewHolder.tvProSevenDay.getText().length());


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
                        String progetday8 = getItem(position).getDay8();
                        if (progetday8 == null) {
                            progetday8 = "";
                        }
                        String nullday8;
                        if (progetday8.equals(proitem)) {
                            nullday8 = "1";
                        } else {
                            nullday8 = "2";
                            getItem(position).setDay8(proitem);
                            saveBean.setDay8(proitem);
                        }
                        spUtils.put(context, "pronullday8", nullday8);
                        spUtils.put(context, "prosaveeightday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProEightDay.setSelection(viewHolder.tvProEightDay.getText().length());


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
                        String progetday9 = getItem(position).getDay9();
                        if (progetday9 == null) {
                            progetday9 = "";
                        }
                        String nullday9;
                        if (progetday9.equals(proitem)) {
                            nullday9 = "1";
                        } else {
                            nullday9 = "2";
                            getItem(position).setDay9(proitem);
                            saveBean.setDay9(proitem);
                        }
                        spUtils.put(context, "pronullday9", nullday9);
                        spUtils.put(context, "prosavenineday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProNineDay.setSelection(viewHolder.tvProNineDay.getText().length());


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
                        String progetday10 = getItem(position).getDay10();
                        if (progetday10 == null) {
                            progetday10 = "";
                        }
                        String nullday10;
                        if (progetday10.equals(proitem)) {
                            nullday10 = "1";
                        } else {
                            nullday10 = "2";
                            getItem(position).setDay10(proitem);
                            saveBean.setDay10(proitem);
                        }
                        spUtils.put(context, "pronullday10", nullday10);
                        spUtils.put(context, "prosavetenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTenDay.setSelection(viewHolder.tvProTenDay.getText().length());


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
                        String progetday11 = getItem(position).getDay11();
                        if (progetday11 == null) {
                            progetday11 = "";
                        }
                        String nullday11;
                        if (progetday11.equals(proitem)) {
                            nullday11 = "1";
                        } else {
                            nullday11 = "2";
                            getItem(position).setDay11(proitem);
                            saveBean.setDay11(proitem);
                        }
                        spUtils.put(context, "pronullday11", nullday11);
                        spUtils.put(context, "prosaveelevenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProElevenDay.setSelection(viewHolder.tvProElevenDay.getText().length());


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
                        String progetday12 = getItem(position).getDay12();
                        if (progetday12 == null) {
                            progetday12 = "";
                        }
                        String nullday12;
                        if (progetday12.equals(proitem)) {
                            nullday12 = "1";
                        } else {
                            nullday12 = "2";
                            getItem(position).setDay12(proitem);
                            saveBean.setDay12(proitem);
                        }
                        spUtils.put(context, "pronullday12", nullday12);
                        spUtils.put(context, "prosavetwelveday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwelveDay.setSelection(viewHolder.tvProTwelveDay.getText().length());


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
                        String progetday13 = getItem(position).getDay13();
                        if (progetday13 == null) {
                            progetday13 = "";
                        }
                        String nullday13;
                        if (progetday13.equals(proitem)) {
                            nullday13 = "1";
                        } else {
                            nullday13 = "2";
                            getItem(position).setDay13(proitem);
                            saveBean.setDay13(proitem);
                        }
                        spUtils.put(context, "pronullday13", nullday13);
                        spUtils.put(context, "prosavethirteenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProThirteenDay.setSelection(viewHolder.tvProThirteenDay.getText().length());


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
                        String progetday14 = getItem(position).getDay14();
                        if (progetday14 == null) {
                            progetday14 = "";
                        }
                        String nullday14;
                        if (progetday14.equals(proitem)) {
                            nullday14 = "1";
                        } else {
                            nullday14 = "2";
                            getItem(position).setDay14(proitem);
                            saveBean.setDay14(proitem);
                        }
                        spUtils.put(context, "pronullday14", nullday14);
                        spUtils.put(context, "prosavefourteenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProFourteenDay.setSelection(viewHolder.tvProFourteenDay.getText().length());


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
                        String progetday15 = getItem(position).getDay15();
                        if (progetday15 == null) {
                            progetday15 = "";
                        }
                        String nullday15;
                        if (progetday15.equals(proitem)) {
                            nullday15 = "1";
                        } else {
                            nullday15 = "2";
                            getItem(position).setDay15(proitem);
                            saveBean.setDay15(proitem);
                        }
                        spUtils.put(context, "pronullday15", nullday15);
                        spUtils.put(context, "prosavefifteenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProFifteenDay.setSelection(viewHolder.tvProFifteenDay.getText().length());


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
                        String progetday16 = getItem(position).getDay16();
                        if (progetday16 == null) {
                            progetday16 = "";
                        }
                        String nullday16;
                        if (progetday16.equals(proitem)) {
                            nullday16 = "1";
                        } else {
                            nullday16 = "2";
                            getItem(position).setDay16(proitem);
                            saveBean.setDay16(proitem);
                        }
                        spUtils.put(context, "pronullday16", nullday16);
                        spUtils.put(context, "prosavesixteenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProSixteenDay.setSelection(viewHolder.tvProSixteenDay.getText().length());


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
                        String progetday17 = getItem(position).getDay17();
                        if (progetday17 == null) {
                            progetday17 = "";
                        }
                        String nullday17;
                        if (progetday17.equals(proitem)) {
                            nullday17 = "1";
                        } else {
                            nullday17 = "2";
                            getItem(position).setDay17(proitem);
                            saveBean.setDay17(proitem);
                        }
                        spUtils.put(context, "pronullday17", nullday17);
                        spUtils.put(context, "prosaveserventeenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProSeventeenDay.setSelection(viewHolder.tvProSeventeenDay.getText().length());


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
                        String progetday18 = getItem(position).getDay18();
                        if (progetday18 == null) {
                            progetday18 = "";
                        }
                        String nullday18;
                        if (progetday18.equals(proitem)) {
                            nullday18 = "1";
                        } else {
                            nullday18 = "2";
                            getItem(position).setDay18(proitem);
                            saveBean.setDay18(proitem);
                        }
                        spUtils.put(context, "pronullday18", nullday18);
                        spUtils.put(context, "prosaveeighteenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProEighteenDay.setSelection(viewHolder.tvProEighteenDay.getText().length());


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
                        String progetday19 = getItem(position).getDay19();
                        if (progetday19 == null) {
                            progetday19 = "";
                        }
                        String nullday19;
                        if (progetday19.equals(proitem)) {
                            nullday19 = "1";
                        } else {
                            nullday19 = "2";
                            getItem(position).setDay19(proitem);
                            saveBean.setDay19(proitem);
                        }
                        spUtils.put(context, "pronullday19", nullday19);
                        spUtils.put(context, "prosavenineteenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProNineteenDay.setSelection(viewHolder.tvProNineteenDay.getText().length());


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
                        String progetday20 = getItem(position).getDay20();
                        if (progetday20 == null) {
                            progetday20 = "";
                        }
                        String nullday20;
                        if (progetday20.equals(proitem)) {
                            nullday20 = "1";
                        } else {
                            nullday20 = "2";
                            getItem(position).setDay20(proitem);
                            saveBean.setDay20(proitem);
                        }
                        spUtils.put(context, "pronullday20", nullday20);
                        spUtils.put(context, "prosavetwentyday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentyDay.setSelection(viewHolder.tvProTwentyDay.getText().length());


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
                        String progetday21 = getItem(position).getDay21();
                        if (progetday21 == null) {
                            progetday21 = "";
                        }
                        String nullday21;
                        if (progetday21.equals(proitem)) {
                            nullday21 = "1";
                        } else {
                            nullday21 = "2";
                            getItem(position).setDay21(proitem);
                            saveBean.setDay21(proitem);
                        }
                        spUtils.put(context, "pronullday21", nullday21);
                        spUtils.put(context, "prosavetwentyoneday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentyOneDay.setSelection(viewHolder.tvProTwentyOneDay.getText().length());


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
                        String progetday22 = getItem(position).getDay22();
                        if (progetday22 == null) {
                            progetday22 = "";
                        }
                        String nullday22;
                        if (progetday22.equals(proitem)) {
                            nullday22 = "1";
                        } else {
                            nullday22 = "2";
                            getItem(position).setDay22(proitem);
                            saveBean.setDay22(proitem);
                        }
                        spUtils.put(context, "pronullday22", nullday22);
                        spUtils.put(context, "prosavetwentytwoday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentyTwoDay.setSelection(viewHolder.tvProTwentyTwoDay.getText().length());


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
                        String progetday23 = getItem(position).getDay23();
                        if (progetday23 == null) {
                            progetday23 = "";
                        }
                        String nullday23;
                        if (progetday23.equals(proitem)) {
                            nullday23 = "1";
                        } else {
                            nullday23 = "2";
                            getItem(position).setDay23(proitem);
                            saveBean.setDay23(proitem);
                        }
                        spUtils.put(context, "pronullday23", nullday23);
                        spUtils.put(context, "prosavetwentythreeday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentyThreeDay.setSelection(viewHolder.tvProTwentyThreeDay.getText().length());


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
                        String progetday24 = getItem(position).getDay24();
                        if (progetday24 == null) {
                            progetday24 = "";
                        }
                        String nullday24;
                        if (progetday24.equals(proitem)) {
                            nullday24 = "1";
                        } else {
                            nullday24 = "2";
                            getItem(position).setDay24(proitem);
                            saveBean.setDay24(proitem);
                        }
                        spUtils.put(context, "pronullday24", nullday24);
                        spUtils.put(context, "prosavetwentyforeday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentyForeDay.setSelection(viewHolder.tvProTwentyForeDay.getText().length());


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
                        String progetday25 = getItem(position).getDay25();
                        if (progetday25 == null) {
                            progetday25 = "";
                        }
                        String nullday25;
                        if (progetday25.equals(proitem)) {
                            nullday25 = "1";
                        } else {
                            nullday25 = "2";
                            getItem(position).setDay25(proitem);
                            saveBean.setDay25(proitem);
                        }
                        spUtils.put(context, "pronullday25", nullday25);
                        spUtils.put(context, "prosavetwentyfiveday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentyFiveDay.setSelection(viewHolder.tvProTwentyFiveDay.getText().length());


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
                        String progetday26 = getItem(position).getDay26();
                        if (progetday26 == null) {
                            progetday26 = "";
                        }
                        String nullday26;
                        if (progetday26.equals(proitem)) {
                            nullday26 = "1";
                        } else {
                            nullday26 = "2";
                            getItem(position).setDay26(proitem);
                            saveBean.setDay26(proitem);
                        }
                        spUtils.put(context, "pronullday26", nullday26);
                        spUtils.put(context, "prosavetwentysixday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentySixDay.setSelection(viewHolder.tvProTwentySixDay.getText().length());


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
                        String progetday27 = getItem(position).getDay27();
                        if (progetday27 == null) {
                            progetday27 = "";
                        }
                        String nullday27;
                        if (progetday27.equals(proitem)) {
                            nullday27 = "1";
                        } else {
                            nullday27 = "2";
                            getItem(position).setDay27(proitem);
                            saveBean.setDay27(proitem);
                        }
                        spUtils.put(context, "pronullday27", nullday27);
                        spUtils.put(context, "prosavetwentysevenday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentySevenDay.setSelection(viewHolder.tvProTwentySevenDay.getText().length());


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
                        String progetday28 = getItem(position).getDay28();
                        if (progetday28 == null) {
                            progetday28 = "";
                        }
                        String nullday28;
                        if (progetday28.equals(proitem)) {
                            nullday28 = "1";
                        } else {
                            nullday28 = "2";
                            getItem(position).setDay28(proitem);
                            saveBean.setDay28(proitem);
                        }
                        spUtils.put(context, "pronullday28", nullday28);
                        spUtils.put(context, "prosavetwentyeightday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentyEightDay.setSelection(viewHolder.tvProTwentyEightDay.getText().length());


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
                        String progetday29 = getItem(position).getDay29();
                        if (progetday29 == null) {
                            progetday29 = "";
                        }
                        String nullday29;
                        if (progetday29.equals(proitem)) {
                            nullday29 = "1";
                        } else {
                            nullday29 = "2";
                            getItem(position).setDay29(proitem);
                            saveBean.setDay29(proitem);
                        }
                        spUtils.put(context, "pronullday29", nullday29);
                        spUtils.put(context, "prosavetwentynineday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProTwentyNineDay.setSelection(viewHolder.tvProTwentyNineDay.getText().length());


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
                        String progetday30 = getItem(position).getDay30();
                        if (progetday30 == null) {
                            progetday30 = "";
                        }
                        String nullday30;
                        if (progetday30.equals(proitem)) {
                            nullday30 = "1";
                        } else {
                            nullday30 = "2";
                            getItem(position).setDay30(proitem);
                            saveBean.setDay30(proitem);
                        }
                        spUtils.put(context, "pronullday30", nullday30);
                        spUtils.put(context, "prosavethirtyday", proitem);
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
                        if (dayFifteen.equals("")) {
                            day15 = 0;
                        } else {
                            day15 = Integer.parseInt(dayFifteen);
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
                viewHolder.tvProThirtyDay.setSelection(viewHolder.tvProThirtyDay.getText().length());


                viewHolder.tvProThirtyOneDay.setEnabled(true);
                final EditText editTexThirtyOneDay = viewHolder.tvProThirtyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
                    editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
                }

                final int MIN_MARK_OTHER1 = 0;
                final int MAX_MARK_OTHER2 = 0;
                editTexThirtyOneDay.setText(getItem(position).getDay31());
                TextWatcher TvThirtyOneDay = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        Log.d(TAG, "beforeTextChanged");
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Log.d(TAG, "onTextChanged");
                        if (start > 1) {
                            if (MIN_MARK_OTHER1 != -1 && MAX_MARK_OTHER2 != -1) {
                                int num = Integer.parseInt(s.toString());
                                if (num > MAX_MARK_OTHER2) {
                                    s = String.valueOf(MAX_MARK_OTHER2);
                                    editTexThirtyOneDay.setText(s);
                                    editTexThirtyOneDay.setSelection(editTexThirtyOneDay.length());
                                } else if (num < MIN_MARK_OTHER1) {
                                    s = String.valueOf(MIN_MARK_OTHER1);
                                    return;
                                }
                            }
                        }
                        InputFilter[] filters = {new InputFilter.LengthFilter(MAX_MARK_OTHER2)};
                        viewHolder.tvProThirtyOneDay.setFilters(filters);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.d(TAG, "afterTextChanged");
                        CharSequence[] month = {"2", "4", "6", "9", "11"};
                        String monthstr = getItem(position).getMonth();
                        if (monthstr.equals("2") || monthstr.equals("4")
                                || monthstr.equals("6") || monthstr.equals("9")
                                || monthstr.equals("11")) {
                            ToastUtils.ShowToastMessage("当前" + monthstr + "月没" +
                                    "有31日", context);
                        } else {
                            String proitem = viewHolder.tvProThirtyOneDay.getText().toString();
                            String progetday31 = getItem(position).getDay31();
                            if (progetday31 == null) {
                                progetday31 = "";
                            }
                            String nullday31;
                            if (progetday31.equals(proitem)) {
                                nullday31 = "1";
                            } else {
                                nullday31 = "2";
                                getItem(position).setDay31(proitem);
                                saveBean.setDay31(proitem);
                            }
                            spUtils.put(context, "pronullday31", nullday31);
                            spUtils.put(context, "prosavethirtyoneday", proitem);
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
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
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
                    }
                };
                editTexThirtyOneDay.addTextChangedListener(TvThirtyOneDay);
                editTexThirtyOneDay.setTag(TvThirtyOneDay);
            /*光标放置在文本最后*/
                viewHolder.tvProThirtyOneDay.setSelection(viewHolder.tvProThirtyOneDay.getText().length());


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
                        String progetmemo = getItem(position).getMemo();
                        if (progetmemo == null) {
                            progetmemo = "";
                        }
                        String nullmemo;
                        if (progetmemo.equals(proitem)) {
                            nullmemo = "1";
                        } else {
                            nullmemo = "2";
                            getItem(position).setMemo(proitem);
                            saveBean.setMemo(proitem);
                        }
                        spUtils.put(context, "pronullmemo", nullmemo);
                        spUtils.put(context, "prosaveremarks", proitem);

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
            if (dayFifteen.equals("")) {
                day15 = 0;
            } else {
                day15 = Integer.parseInt(dayFifteen);
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

        /*月份*/
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
                        viewHolder.tvProMonth.setText(title);
                        String pronullmonth = getItem(position).getMonth();
                        if (pronullmonth == null) {
                            pronullmonth = "";
                        }
                        String nullmonth;
                        if (pronullmonth.equals(title)) {
                            nullmonth = "1";
                        } else {
                            nullmonth = "2";
                            getItem(position).setMonth(title);
                            getItem(position).setMemomonth(title);
                        }
                        spUtils.put(context, "pronullmonth", nullmonth);
                        spUtils.put(context, "prosavemonth", title);
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

        /*部门组别*/
        viewHolder.tvProDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_column, popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        String pronullpartment = getItem(position).getSubfactoryTeams();
                        if (pronullpartment == null) {
                            pronullpartment = "";
                        }
                        String nullpartment;
                        if (pronullpartment.equals(title)) {
                            nullpartment = "1";
                        } else {
                            nullpartment = "2";
                            getItem(position).setSubfactoryTeams(title);
                        }
                        spUtils.put(context, "pronullpartment", nullpartment);
                        spUtils.put(context, "prosavedepartment", title);
                        viewHolder.tvProDepartment.setText(title);
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

        /**
         * 工序菜单事件
         */
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
                        String title = item.getTitle().toString();//菜单中选择的工序
                        String pronullprocedure = getItem(position).getWorkingProcedure();
                        if (pronullprocedure == null) {
                            pronullprocedure = "";
                        }
                        String nullprocedure;
                        if (pronullprocedure.equals(title)) {
                            nullprocedure = "1";
                        } else {
                            nullprocedure = "2";
                            getItem(position).setMemoprdure(title);//给默认的工序赋值
                            getItem(position).setWorkingProcedure(title);//改变原来的工序
                        }
                        spUtils.put(context, "pronullprocedure", nullprocedure);
                        spUtils.put(context, "probooleanProcedureTitle", title);//传递
                        viewHolder.tvProProcedure.setText(title);//给界面显示当前修改的值
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

        /**
         * 状态
         */
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
                        String pronullstate = getItem(position).getPrdstatus();
                        if (pronullstate == null) {
                            pronullstate = "";
                        }
                        String nullstate;
                        if (pronullstate.equals(title)) {
                            nullstate = "1";
                        } else {
                            nullstate = "2";
                            getItem(position).setPrdstatus(title);
                        }
                        spUtils.put(context, "pronullstate", nullstate);
                        spUtils.put(context, "prosavestate", title);
                        viewHolder.tvProState.setText(title);
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
        //        Spinner tvProMonth;//月份
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
