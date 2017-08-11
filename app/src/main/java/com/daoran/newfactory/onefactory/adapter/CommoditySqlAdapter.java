package com.daoran.newfactory.onefactory.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 查货跟踪适配
 * Created by lizhipeng on 2017/4/6.
 */

public class CommoditySqlAdapter extends BaseAdapter {
    private static final String TAG = "commotest";
    private Context context;
    private List<CommoditydetailBean.DataBean> dataBeen;
    private ArrayList attentionArr = new ArrayList();
    private SharedPreferences sp;
    private SPUtils spUtils;

    public CommoditySqlAdapter(Context context, List<CommoditydetailBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public CommoditydetailBean.DataBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_commodity_data, null);
            //客户
            holder.tvCommoCtmtxt = (TextView) convertView.findViewById(R.id.tvCommoCtmtxt);
            //跟单
            holder.tvCommoPrddocumentary = (TextView) convertView.findViewById(R.id.tvCommoPrddocumentary);
            //生产主管
            holder.tvCommoprdmaster = (TextView) convertView.findViewById(R.id.tvCommoprdmaster);
            //主管评分
            holder.tvCommoQCMasterScore = (EditText) convertView.findViewById(R.id.tvCommoQCMasterScore);
            //封样资料接收时间
            holder.tvCommoSealedrev = (TextView) convertView.findViewById(R.id.tvCommoSealedrev);
            //大货资料接收时间
            holder.tvCommoDocback = (TextView) convertView.findViewById(R.id.tvCommoDocback);
            //出货时间
            holder.tvCommoLcdat = (TextView) convertView.findViewById(R.id.tvCommoLcdat);
            //制单数量
            holder.tvCommoTaskqty = (TextView) convertView.findViewById(R.id.tvCommoTaskqty);
            //需要备注的特殊情况
            holder.tvCommoPreMemo = (EditText) convertView.findViewById(R.id.tvCommoPreMemo);
            //预计产前报告时间
            holder.tvCommoPredocdt = (TextView) convertView.findViewById(R.id.tvCommoPredocdt);
            //开产前会时间
            holder.tvCommoPred = (TextView) convertView.findViewById(R.id.tvCommoPred);
            //产前会报告
            holder.tvCommoPredoc = (EditText) convertView.findViewById(R.id.tvCommoPredoc);
            //大货面料情况
            holder.tvCommoFabricsok = (EditText) convertView.findViewById(R.id.tvCommoFabricsok);
            //大货辅料情况
            holder.tvCommoAccessoriesok = (EditText) convertView.findViewById(R.id.tvCommoAccessoriesok);
            //大货特殊工艺情况
            holder.tvCommoSpcproDec = (EditText) convertView.findViewById(R.id.tvCommoSpcproDec);
            //特殊工艺特别备注
            holder.tvCommoSpcproMemo = (EditText) convertView.findViewById(R.id.tvCommoSpcproMemo);
            //实裁数
            holder.tvCommoCutqty = (EditText) convertView.findViewById(R.id.tvCommoCutqty);
            //上线日期
            holder.tvCommoSewFdt = (TextView) convertView.findViewById(R.id.tvCommoSewFdt);
            //下线日期
            holder.tvCommoSewMdt = (TextView) convertView.findViewById(R.id.tvCommoSewMdt);
            //加工厂
            holder.tvCommoSubfactory = (TextView) convertView.findViewById(R.id.tvCommoSubfactory);
            //预计早期时间
            holder.tvCommoPrebdt = (TextView) convertView.findViewById(R.id.tvCommoPrebdt);
            //自查早期时间
            holder.tvCommoQCbdt = (TextView) convertView.findViewById(R.id.tvCommoQCbdt);
            //早期报告
            holder.tvCommoQCbdtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCbdtDoc);
            //预计中期时间
            holder.tvCommoPremdt = (TextView) convertView.findViewById(R.id.tvCommoPremdt);
            //自查中期时间
            holder.tvCommoQCmdt = (TextView) convertView.findViewById(R.id.tvCommoQCmdt);
            //中期报告
            holder.tvCommoQCmdtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCmdtDoc);
            //预计尾期时间
            holder.tvCommoPreedt = (TextView) convertView.findViewById(R.id.tvCommoPreedt);
            //自查尾期时间
            holder.tvCommoQCMedt = (TextView) convertView.findViewById(R.id.tvCommoQCMedt);
            //尾期报告
            holder.tvCommoQCedtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCedtDoc);
            //客查中期时间
            holder.tvCommoFctmdt = (TextView) convertView.findViewById(R.id.tvCommoFctmdt);
            //客查尾期报告
            holder.tvCommoFctedt = (TextView) convertView.findViewById(R.id.tvCommoFctedt);
            //成品包装开始日期
            holder.tvCommoPackbdat = (TextView) convertView.findViewById(R.id.tvCommoPackbdat);
            //装箱数量
            holder.tvCommoPackqty2 = (EditText) convertView.findViewById(R.id.tvCommoPackqty2);
            //qc特别备注
            holder.tvCommoQCMemo = (EditText) convertView.findViewById(R.id.tvCommoQCMemo);
            //离厂日期
            holder.tvCommoFactlcdat = (TextView) convertView.findViewById(R.id.tvCommoFactlcdat);
            //查货批次
            holder.tvCommoBatchid = (EditText) convertView.findViewById(R.id.tvCommoBatchid);
            //后道
            holder.tvCommoOurAfter = (TextView) convertView.findViewById(R.id.tvCommoOurAfter);
            //业务员确认客查时间
            holder.tvCommoCtmchkdt = (TextView) convertView.findViewById(R.id.tvCommoCtmchkdt);
            //尾查预查
            holder.tvCommoIPQCPedt = (EditText) convertView.findViewById(R.id.tvCommoIPQCPedt);
            //巡检中查
            holder.tvCommoIPQCmdt = (EditText) convertView.findViewById(R.id.tvCommoIPQCmdt);
            //QA首扎
            holder.tvCommoQAname = (EditText) convertView.findViewById(R.id.tvCommoQAname);
            //QA首扎件数
            holder.tvCommoQAScore = (EditText) convertView.findViewById(R.id.tvCommoQAScore);
            //QA首扎日
            holder.tvCommoQAMemo = (TextView) convertView.findViewById(R.id.tvCommoQAMemo);
            //每项item
            holder.lin_content = (LinearLayout) convertView.findViewById(R.id.lin_content);
            //件查
            holder.tvCommoThing = (EditText) convertView.findViewById(R.id.tvCommoThing);
            //预计件查时间
            holder.tvCommoThingExpectedTime = (TextView) convertView.findViewById(R.id.tvCommoThingExpectedTime);
            //实际件查时间
            holder.tvCommoThingTime = (TextView) convertView.findViewById(R.id.tvCommoThingTime);
            //查货地点
            holder.tvCommoThingAddress = (EditText) convertView.findViewById(R.id.tvCommoThingAddress);
            spUtils.put(context, "strposition", position);
            convertView.setClickable(true);
            convertView.setOnClickListener(myOnclicklistener);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /*判断item中制单人是否是登录用户，是为可改，否为不可改*/
        sp = context.getSharedPreferences("my_sp", 0);
        String nameid = sp.getString("usernamerecoder", "");
        String documentary = getItem(position).getPrddocumentary();
        String master = getItem(position).getPrdmaster();
        if (documentary == null) {
            documentary = "";
        }
        if (master == null) {
            master = "";
        }

        /**
         * 判断生产主管是否是当前登录用户
         */
        if (master.equals(nameid)) {
            holder.lin_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String proid = String.valueOf(getItem(position).getID());
                    spUtils.put(context, "commoproid", proid);
                    String uriid = String.valueOf(getItem(position).getID());
                    spUtils.put(context, "uriid", uriid);
                }
            });
            holder.tvCommoCtmtxt.setEnabled(true);//客户
            holder.tvCommoCtmtxt.setText(getItem(position).getCtmtxt());

            holder.tvCommoPrddocumentary.setEnabled(true);//跟单
            holder.tvCommoPrddocumentary.setText(getItem(position).getPrddocumentary());

            holder.tvCommoprdmaster.setEnabled(true);//生产主管
            holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());

            holder.tvCommoOurAfter.setText(getItem(position).getOurAfter());//后道
            holder.tvCommoOurAfter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context, v);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_commo_hd, popupMenu.getMenu());
                    // menu的item点击事件
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            sp = context.getSharedPreferences("my_sp", 0);
                            String title = item.getTitle().toString();
                            String commotitle = getItem(position).getOurAfter();
                            if (commotitle == null) {
                                commotitle = "";
                            }
                            String nulltitle;
                            if (commotitle.equals(title)) {
                                nulltitle = "1";
                            } else {
                                nulltitle = "2";
                                getItem(position).setOurAfter(title);
                            }
                            spUtils.put(context, "commonulltitle", nulltitle);
                            spUtils.put(context, "commohdTitle", title);//后道
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

            holder.tvCommoQCMasterScore.setEnabled(true);//主管评分
            final EditText editTextQCMasterScore = holder.tvCommoQCMasterScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMasterScore.getTag() instanceof TextWatcher) {
                editTextQCMasterScore.removeTextChangedListener((TextWatcher) editTextQCMasterScore.getTag());
            }
            editTextQCMasterScore.setText(getItem(position).getQCMasterScore());
            holder.tvCommoQCMasterScore.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        //得到焦点
                        sp = context.getSharedPreferences("my_sp", 0);
                        String qcmaster = sp.getString("CommodityQCMasterScore", "");
                        System.out.print(qcmaster);
                        spUtils.put(context, "debugQCMasterScore", qcmaster);
                        TextWatcher TvQCMasterScore = new TextWatcher() {
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
                                String proitem = holder.tvCommoQCMasterScore.getText().toString();
                                String commoitem = getItem(position).getQCMasterScore();
                                if (commoitem == null) {
                                    commoitem = "";
                                }
                                String nullitem;
                                if (commoitem.equals(proitem)) {
                                    nullitem = "1";
                                } else {
                                    nullitem = "2";
                                    getItem(position).setQCMasterScore(proitem);
                                }
                                spUtils.put(context, "commonullitem", nullitem);
                                spUtils.put(context, "CommodityQCMasterScore", proitem);//主管评分
                            }
                        };
                        editTextQCMasterScore.addTextChangedListener(TvQCMasterScore);
                        editTextQCMasterScore.setTag(TvQCMasterScore);
                    } else { //失去焦点
                        String qcmastertwoo = holder.tvCommoQCMasterScore.getText().toString();
                        String commoitem = getItem(position).getQCMasterScore();
                        if (commoitem == null) {
                            commoitem = "";
                        }
                        String nullitem;
                        if (commoitem.equals(qcmastertwoo)) {
                            nullitem = "1";
                        } else {
                            nullitem = "2";
                            getItem(position).setQCMasterScore(qcmastertwoo);
                        }
                        spUtils.put(context, "commonullitem", nullitem);
                        System.out.print(qcmastertwoo);
                    }
                }
            });
            /*光标放置在文本最后*/
            holder.tvCommoQCMasterScore.setSelection(holder.tvCommoQCMasterScore.length());

            holder.tvCommoSealedrev.setEnabled(true);//封样资料接收时间
            holder.tvCommoSealedrev.getResources().getDrawable(R.drawable.shape_text_sql_boild);
            holder.tvCommoSealedrev.setText(getItem(position).getSealedrev());
            holder.tvCommoSealedrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attentionArr.add(position);
                    if (attentionArr.contains(position)) {
                        Calendar calendar = Calendar.getInstance();
                        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                                context, null, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );
                        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                                , "完成", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DatePicker datePicker = datePickerDialog.getDatePicker();
                                        int year = datePicker.getYear();
                                        int month = datePicker.getMonth();
                                        int day = datePicker.getDayOfMonth();
                                        String datetime = year + "/" + (month + 1) + "/" + day;
                                        holder.tvCommoSealedrev.setText(datetime);
                                        String commosearledrev = getItem(position).getSealedrev();
                                        if (commosearledrev == null) {
                                            commosearledrev = "";
                                        }
                                        String nullsearledrev;
                                        if (commosearledrev.equals(datetime)) {
                                            nullsearledrev = "1";
                                        } else {
                                            nullsearledrev = "2";
                                            if (attentionArr.contains(position)) {
                                                getItem(position).setSealedrev(datetime);
                                            }
                                        }
                                        spUtils.put(context, "commonullsearledrev", nullsearledrev);
                                        spUtils.put(context, "dateSealedrewtimesign", datetime);//封样资料接收时间
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                                "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoSealedrev.setText("");
                                        String commosearledrev = getItem(position).getSealedrev();
                                        if (commosearledrev == null) {
                                            commosearledrev = "";
                                        }
                                        String nullsearledrev;
                                        if (commosearledrev.equals("")) {
                                            nullsearledrev = "1";
                                        } else {
                                            nullsearledrev = "2";
                                            if (attentionArr.contains(position)) {
                                                getItem(position).setSealedrev("");
                                            }
                                        }
                                        spUtils.put(context, "commonullsearledrev", nullsearledrev);
                                        spUtils.put(context, "dateSealedrewtimesign", "");//封样资料接收时间
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                                , "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        datePickerDialog.show();
                    }
                }
            });

            holder.tvCommoDocback.setEnabled(true);//大货资料接收时间
            holder.tvCommoDocback.setText(getItem(position).getDocback());
            holder.tvCommoDocback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoDocback.setText(datetime);
                                    String commodocback = getItem(position).getDocback();
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals(datetime)) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                        getItem(position).setDocback(datetime);
                                    }
                                    spUtils.put(context, "commonulldocback", nulldocback);
                                    spUtils.put(context, "dateDocbacktimesign", datetime);//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoDocback.setText("");
                                    String commodocback = getItem(position).getDocback();
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals("")) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                        getItem(position).setDocback("");
                                    }
                                    spUtils.put(context, "commonulldocback", nulldocback);
                                    spUtils.put(context, "dateDocbacktimesign", "");//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoLcdat.setEnabled(true);
            holder.tvCommoLcdat.setText(getItem(position).getLcdat());

            holder.tvCommoTaskqty.setEnabled(true);
            holder.tvCommoTaskqty.setText(getItem(position).getTaskqty());

            holder.tvCommoPreMemo.setEnabled(true);//需要特别备注的情况
            final EditText editTextPreMemo = holder.tvCommoPreMemo;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPreMemo.getTag() instanceof TextWatcher) {
                editTextPreMemo.removeTextChangedListener((TextWatcher) editTextPreMemo.getTag());
            }
            editTextPreMemo.setText(getItem(position).getPreMemo());
            TextWatcher TvPreMemo = new TextWatcher() {
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
                    String proitem = holder.tvCommoPreMemo.getText().toString();
                    String commopromemo = getItem(position).getPreMemo();
                    if (commopromemo == null) {
                        commopromemo = "";
                    }
                    String nullmemo;
                    if (commopromemo.equals(proitem)) {
                        nullmemo = "1";
                    } else {
                        nullmemo = "2";
                        getItem(position).setPreMemo(proitem);
                    }
                    spUtils.put(context, "commonullmemo", nullmemo);
                    spUtils.put(context, "CommodityPreMemo", proitem);//需要特别备注的情况
                }
            };
            editTextPreMemo.addTextChangedListener(TvPreMemo);
            editTextPreMemo.setTag(TvPreMemo);
            /*光标放置在文本最后*/
            holder.tvCommoPreMemo.setSelection(holder.tvCommoPreMemo.length());


            holder.tvCommoPredocdt.setEnabled(true);//预计产前报告时间
            holder.tvCommoPredocdt.setText(getItem(position).getPredocdt());
            holder.tvCommoPredocdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPredocdt.setText(datetime);
                                    String commopreducdt = getItem(position).getPredocdt();
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals(datetime)) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                        getItem(position).setPredocdt(datetime);
                                    }
                                    spUtils.put(context, "commonullpreducdt", nullpreducdt);
                                    spUtils.put(context, "datePredocdttimesign", datetime);//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPredocdt.setText("");
                                    String commopreducdt = getItem(position).getPredocdt();
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals("")) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                        getItem(position).setPredocdt("");
                                    }
                                    spUtils.put(context, "commonullpreducdt", nullpreducdt);
                                    spUtils.put(context, "datePredocdttimesign", "");//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoPred.setEnabled(true);//开产前会时间
            holder.tvCommoPred.setText(getItem(position).getPredt());
            holder.tvCommoPred.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPred.setText(datetime);
                                    String commopred = getItem(position).getPredt();
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals(datetime)) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                        getItem(position).setPredt(datetime);
                                    }
                                    spUtils.put(context, "commonullpred", nullpred);
                                    spUtils.put(context, "datePredtimesign", datetime);//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPred.setText("");
                                    String commopred = getItem(position).getPredt();
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals("")) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                        getItem(position).setPredt("");
                                    }
                                    spUtils.put(context, "commonullpred", nullpred);
                                    spUtils.put(context, "datePredtimesign", "");//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoFabricsok.setEnabled(true);//大货面料情况
            final EditText editTextFabricsok = holder.tvCommoFabricsok;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFabricsok.getTag() instanceof TextWatcher) {
                editTextFabricsok.removeTextChangedListener((TextWatcher) editTextFabricsok.getTag());
            }
            editTextFabricsok.setText(getItem(position).getFabricsok());
            TextWatcher TvFabricsok = new TextWatcher() {
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
                    String proitem = holder.tvCommoFabricsok.getText().toString();
                    String commofabricsok = getItem(position).getFabricsok();
                    if (commofabricsok == null) {
                        commofabricsok = "";
                    }
                    String nullfabricsok;
                    if (commofabricsok.equals(proitem)) {
                        nullfabricsok = "1";
                    } else {
                        nullfabricsok = "2";
                        getItem(position).setFabricsok(proitem);
                    }
                    spUtils.put(context, "commonullfabricsok", nullfabricsok);
                    spUtils.put(context, "CommodityFabricsok", proitem);//大货面料情况
                }
            };
            editTextFabricsok.addTextChangedListener(TvFabricsok);
            editTextFabricsok.setTag(TvFabricsok);
            /*光标放置在文本最后*/
            holder.tvCommoFabricsok.setSelection(holder.tvCommoFabricsok.length());


            holder.tvCommoAccessoriesok.setEnabled(true);//大货辅料情况
            final EditText editTextAccessoriesok = holder.tvCommoAccessoriesok;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextAccessoriesok.getTag() instanceof TextWatcher) {
                editTextAccessoriesok.removeTextChangedListener((TextWatcher) editTextAccessoriesok.getTag());
            }
            editTextAccessoriesok.setText(getItem(position).getAccessoriesok());
            TextWatcher TvAccessoriesok = new TextWatcher() {
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
                    String proitem = holder.tvCommoAccessoriesok.getText().toString();
                    String commoaccessori = getItem(position).getAccessoriesok();
                    if (commoaccessori == null) {
                        commoaccessori = "";
                    }
                    String nullaccessori;
                    if (commoaccessori.equals(proitem)) {
                        nullaccessori = "1";
                    } else {
                        nullaccessori = "2";
                        getItem(position).setAccessoriesok(proitem);
                    }
                    spUtils.put(context, "commonullaccessori", nullaccessori);
                    spUtils.put(context, "CommodityAccessoriesok", proitem);//大货辅料情况
                }
            };
            editTextAccessoriesok.addTextChangedListener(TvAccessoriesok);
            editTextAccessoriesok.setTag(TvAccessoriesok);
            /*光标放置在文本最后*/
            holder.tvCommoAccessoriesok.setSelection(holder.tvCommoAccessoriesok.length());


            holder.tvCommoSpcproDec.setEnabled(true);//大货特殊工艺情况
            final EditText editTextSpcproDec = holder.tvCommoSpcproDec;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproDec.getTag() instanceof TextWatcher) {
                editTextSpcproDec.removeTextChangedListener((TextWatcher) editTextSpcproDec.getTag());
            }
            editTextSpcproDec.setText(getItem(position).getSpcproDec());
            TextWatcher TvSpcproDec = new TextWatcher() {
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
                    String proitem = holder.tvCommoSpcproDec.getText().toString();
                    String commospcprodec = getItem(position).getSpcproDec();
                    if (commospcprodec == null) {
                        commospcprodec = "";
                    }
                    String nullspcprodec;
                    if (commospcprodec.equals(proitem)) {
                        nullspcprodec = "1";
                    } else {
                        nullspcprodec = "2";
                        getItem(position).setSpcproDec(proitem);
                    }
                    spUtils.put(context, "commonullspcprodec", nullspcprodec);
                    spUtils.put(context, "CommoditySpcproDec", proitem);//大货特殊工艺情况
                }
            };
            editTextSpcproDec.addTextChangedListener(TvSpcproDec);
            editTextSpcproDec.setTag(TvSpcproDec);
            /*光标放置在文本最后*/
            holder.tvCommoSpcproDec.setSelection(holder.tvCommoSpcproDec.length());


            holder.tvCommoSpcproMemo.setEnabled(true);//特殊工艺特别备注
            final EditText editTextSpcproMemo = holder.tvCommoSpcproMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproMemo.getTag() instanceof TextWatcher) {
                editTextSpcproMemo.removeTextChangedListener((TextWatcher) editTextSpcproMemo.getTag());
            }
            editTextSpcproMemo.setText(getItem(position).getSpcproMemo());
            TextWatcher TvSpcproMemo = new TextWatcher() {
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
                    String proitem = holder.tvCommoSpcproMemo.getText().toString();
                    String commospcpromemo = getItem(position).getSpcproMemo();
                    if (commospcpromemo == null) {
                        commospcpromemo = "";
                    }
                    String nullspcpromemo;
                    if (commospcpromemo.equals(proitem)) {
                        nullspcpromemo = "1";
                    } else {
                        nullspcpromemo = "2";
                        getItem(position).setSpcproMemo(proitem);
                    }
                    spUtils.put(context, "commonullspcpromemo", nullspcpromemo);
                    spUtils.put(context, "CommoditySpcproMemo", proitem);//特殊工艺特别备注
                }
            };
            editTextSpcproMemo.addTextChangedListener(TvSpcproMemo);
            editTextSpcproMemo.setTag(TvSpcproMemo);
            /*光标放置在文本最后*/
            holder.tvCommoSpcproMemo.setSelection(holder.tvCommoSpcproMemo.length());


            holder.tvCommoCutqty.setEnabled(true);//实裁数
            final EditText editTextCutqty = holder.tvCommoCutqty;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextCutqty.getTag() instanceof TextWatcher) {
                editTextCutqty.removeTextChangedListener((TextWatcher) editTextCutqty.getTag());
            }
            editTextCutqty.setText(getItem(position).getCutqty());
            TextWatcher TvCutqty = new TextWatcher() {
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
                    String proitem = holder.tvCommoCutqty.getText().toString();
                    String commocutqty = getItem(position).getCutqty();
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullcutqty;
                    if (commocutqty.equals(proitem)) {
                        nullcutqty = "1";
                    } else {
                        nullcutqty = "2";
                        getItem(position).setCutqty(proitem);
                    }
                    spUtils.put(context, "commonullcutqty", nullcutqty);
                    spUtils.put(context, "CommodityCutqty", proitem);//实裁数
                }
            };
            editTextCutqty.addTextChangedListener(TvCutqty);
            editTextCutqty.setTag(TvCutqty);
            /*光标放置在文本最后*/
            holder.tvCommoCutqty.setSelection(holder.tvCommoCutqty.length());


            holder.tvCommoSewFdt.setEnabled(true);//上线日期
            holder.tvCommoSewFdt.setText(getItem(position).getSewFdt());
            holder.tvCommoSewFdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoSewFdt.setText(datetime);
                                    String commosewfdt = getItem(position).getSewFdt();
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals(datetime)) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                        getItem(position).setSewFdt(datetime);
                                    }
                                    spUtils.put(context, "commonullsewfdt", nullsewfdt);
                                    spUtils.put(context, "dateSewFdttimesign", datetime);//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewFdt.setText("");
                                    String commosewfdt = getItem(position).getSewFdt();
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals("")) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                        getItem(position).setSewFdt("");
                                    }
                                    spUtils.put(context, "commonullsewfdt", nullsewfdt);
                                    spUtils.put(context, "dateSewFdttimesign", "");//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoSewMdt.setEnabled(true);
            holder.tvCommoSewMdt.setText(getItem(position).getSewMdt());
            holder.tvCommoSewMdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoSewMdt.setText(datetime);
                                    String commosewmdt = getItem(position).getSewMdt();
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals(datetime)) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                        getItem(position).setSewMdt(datetime);
                                    }
                                    spUtils.put(context, "commonullsewmdt", nullsewmdt);
                                    spUtils.put(context, "dateSewMdttimesign", datetime);//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewMdt.setText("");
                                    String commosewmdt = getItem(position).getSewMdt();
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals("")) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                        getItem(position).setSewMdt("");
                                    }
                                    spUtils.put(context, "commonullsewmdt", nullsewmdt);
                                    spUtils.put(context, "dateSewMdttimesign", "");//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoSubfactory.setEnabled(true);
            holder.tvCommoSubfactory.setText(getItem(position).getSubfactory());
            holder.tvCommoSubfactory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPrebdt.setEnabled(true);//预计早期时间
            holder.tvCommoPrebdt.setText(getItem(position).getPrebdt());
            holder.tvCommoPrebdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPrebdt.setText(datetime);
                                    String commoprebdt = getItem(position).getPrebdt();
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals(datetime)) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                        getItem(position).setPrebdt(datetime);
                                    }
                                    spUtils.put(context, "commonullprebdt", nullprebdt);
                                    spUtils.put(context, "datePrebdttimesign", datetime);//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPrebdt.setText("");
                                    String commoprebdt = getItem(position).getPrebdt();
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals("")) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                        getItem(position).setPrebdt("");
                                    }
                                    spUtils.put(context, "commonullprebdt", nullprebdt);
                                    spUtils.put(context, "datePrebdttimesign", "");//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoQCbdt.setEnabled(true);//自查早期时间
            holder.tvCommoQCbdt.setText(getItem(position).getQCbdt());
            holder.tvCommoQCbdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQCbdt.setText(datetime);
                                    String commoqcbdt = getItem(position).getQCbdt();
                                    if (commoqcbdt == null) {
                                        commoqcbdt = "";
                                    }
                                    String nullqcbdt;
                                    if (commoqcbdt.equals(datetime)) {
                                        nullqcbdt = "1";
                                    } else {
                                        nullqcbdt = "2";
                                        getItem(position).setQCbdt(datetime);
                                    }
                                    spUtils.put(context, "commonullqcbdt", nullqcbdt);
                                    spUtils.put(context, "dateQCbdttimesign", datetime);//自查早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCbdt.setText("");
                                    String commoqcbdt = getItem(position).getQCbdt();
                                    if (commoqcbdt == null) {
                                        commoqcbdt = "";
                                    }
                                    String nullqcbdt;
                                    if (commoqcbdt.equals("")) {
                                        nullqcbdt = "1";
                                    } else {
                                        nullqcbdt = "2";
                                        getItem(position).setQCbdt("");
                                    }
                                    spUtils.put(context, "commonullqcbdt", nullqcbdt);
                                    spUtils.put(context, "dateQCbdttimesign", "");//自查早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPremdt.setEnabled(true);
            holder.tvCommoPremdt.setText(getItem(position).getPremdt());
            holder.tvCommoPremdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPremdt.setText(datetime);
                                    String commopremdt = getItem(position).getPremdt();
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals(datetime)) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                        getItem(position).setPremdt(datetime);
                                    }
                                    spUtils.put(context, "commonullpremdt", nullpremdt);
                                    spUtils.put(context, "datePremdttimesign", datetime);//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPremdt.setText("");
                                    String commopremdt = getItem(position).getPremdt();
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals("")) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                        getItem(position).setPremdt("");
                                    }
                                    spUtils.put(context, "commonullpremdt", nullpremdt);
                                    spUtils.put(context, "datePremdttimesign", "");//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoQCmdt.setEnabled(true);
            holder.tvCommoQCmdt.setText(getItem(position).getQCmdt());
            holder.tvCommoQCmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQCmdt.setText(datetime);
                                    String commoecmdt = getItem(position).getQCmdt();
                                    if (commoecmdt == null) {
                                        commoecmdt = "";
                                    }
                                    String nullqcmdt;
                                    if (commoecmdt.equals(datetime)) {
                                        nullqcmdt = "1";
                                    } else {
                                        nullqcmdt = "2";
                                        getItem(position).setQCmdt(datetime);
                                    }
                                    spUtils.put(context, "commonullqcmdt", nullqcmdt);
                                    spUtils.put(context, "dateQCmdttimesign", datetime);//自查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCmdt.setText("");
                                    String commoecmdt = getItem(position).getQCmdt();
                                    if (commoecmdt == null) {
                                        commoecmdt = "";
                                    }
                                    String nullqcmdt;
                                    if (commoecmdt.equals("")) {
                                        nullqcmdt = "1";
                                    } else {
                                        nullqcmdt = "2";
                                        getItem(position).setQCmdt("");
                                    }
                                    spUtils.put(context, "commonullqcmdt", nullqcmdt);
                                    spUtils.put(context, "dateQCmdttimesign", "");//自查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPreedt.setEnabled(true);
            holder.tvCommoPreedt.setText(getItem(position).getPreedt());
            holder.tvCommoPreedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPreedt.setText(datetime);
                                    String commopreedt = getItem(position).getPreedt();
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals(datetime)) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                        getItem(position).setPreedt(datetime);
                                    }
                                    spUtils.put(context, "commonullpreedt", nullpreedt);
                                    spUtils.put(context, "datePreedttimesign", datetime);//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPreedt.setText("");
                                    String commopreedt = getItem(position).getPreedt();
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals("")) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                        getItem(position).setPreedt("");
                                    }
                                    spUtils.put(context, "commonullpreedt", nullpreedt);
                                    spUtils.put(context, "datePreedttimesign", "");//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoQCMedt.setEnabled(true);//自查尾期时间
            holder.tvCommoQCMedt.setText(getItem(position).getQCMedt());
            holder.tvCommoQCMedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQCMedt.setText(datetime);
                                    String commoqcmedt = getItem(position).getQCMedt();
                                    if (commoqcmedt == null) {
                                        commoqcmedt = "";
                                    }
                                    String nullqcmedt;
                                    if (commoqcmedt.equals(datetime)) {
                                        nullqcmedt = "1";
                                    } else {
                                        nullqcmedt = "2";
                                        getItem(position).setQCMedt(datetime);
                                    }
                                    spUtils.put(context, "commonullqcmedt", nullqcmedt);
                                    spUtils.put(context, "dateQCMedttimesign", datetime);//自查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCMedt.setText("");
                                    String commoqcmedt = getItem(position).getQCMedt();
                                    if (commoqcmedt == null) {
                                        commoqcmedt = "";
                                    }
                                    String nullqcmedt;
                                    if (commoqcmedt.equals("")) {
                                        nullqcmedt = "1";
                                    } else {
                                        nullqcmedt = "2";
                                        getItem(position).setQCMedt("");
                                    }
                                    spUtils.put(context, "commonullqcmedt", nullqcmedt);
                                    spUtils.put(context, "dateQCMedttimesign", "");//自查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoFctmdt.setEnabled(true);//客查中期时间
            holder.tvCommoFctmdt.setText(getItem(position).getFctmdt());
            holder.tvCommoFctmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoFctmdt.setText(datetime);
                                    String commofctmdt = getItem(position).getFctmdt();
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals(datetime)) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                        getItem(position).setFctmdt(datetime);
                                    }
                                    spUtils.put(context, "commonullfctmdt", nullfctmdt);
                                    spUtils.put(context, "dateFctmdttimesign", datetime);//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctmdt.setText("");
                                    String commofctmdt = getItem(position).getFctmdt();
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals("")) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                        getItem(position).setFctmdt("");
                                    }
                                    spUtils.put(context, "commonullfctmdt", nullfctmdt);
                                    spUtils.put(context, "dateFctmdttimesign", "");//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoFctedt.setEnabled(true);//客查尾期时间
            holder.tvCommoFctedt.setText(getItem(position).getFctedt());
            holder.tvCommoFctedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoFctedt.setText(datetime);
                                    String commofctedt = getItem(position).getFctedt();
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals(datetime)) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                        getItem(position).setFctedt(datetime);
                                    }
                                    spUtils.put(context, "commonullfctedt", nullfctedt);
                                    spUtils.put(context, "dateFctedttimesign", datetime);//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctedt.setText("");
                                    String commofctedt = getItem(position).getFctedt();
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals("")) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                        getItem(position).setFctedt("");
                                    }
                                    spUtils.put(context, "commonullfctedt", nullfctedt);
                                    spUtils.put(context, "dateFctedttimesign", "");//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoPackbdat.setEnabled(true);//成品包装开始时间
            holder.tvCommoPackbdat.setText(getItem(position).getPackbdat());
            holder.tvCommoPackbdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPackbdat.setText(datetime);
                                    String commopackbdat = getItem(position).getPackbdat();
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals(datetime)) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                        getItem(position).setPackbdat(datetime);
                                    }
                                    spUtils.put(context, "commonullpackbdat", nullpackbdat);
                                    spUtils.put(context, "datePackbdattimesign", datetime);//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPackbdat.setText("");
                                    String commopackbdat = getItem(position).getPackbdat();
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals("")) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                        getItem(position).setPackbdat("");
                                    }
                                    spUtils.put(context, "commonullpackbdat", nullpackbdat);
                                    spUtils.put(context, "datePackbdattimesign", "");//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPackqty2.setEnabled(true);//装箱数量
            final EditText editTextPackqty2 = holder.tvCommoPackqty2;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPackqty2.getTag() instanceof TextWatcher) {
                editTextPackqty2.removeTextChangedListener((TextWatcher) editTextPackqty2.getTag());
            }
            editTextPackqty2.setText(getItem(position).getPackqty2());
            TextWatcher TvPackqty2 = new TextWatcher() {
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
                    String proitem = holder.tvCommoPackqty2.getText().toString();
                    String commopackqty2 = getItem(position).getPackqty2();
                    if (commopackqty2 == null) {
                        commopackqty2 = "";
                    }
                    String nullpackqty2;
                    if (commopackqty2.equals(proitem)) {
                        nullpackqty2 = "1";
                    } else {
                        nullpackqty2 = "2";
                        getItem(position).setPackqty2(proitem);
                    }
                    spUtils.put(context, "commonullpackqty2", nullpackqty2);
                    spUtils.put(context, "CommodityPackqty2", proitem);//装箱数量
                }
            };
            editTextPackqty2.addTextChangedListener(TvPackqty2);
            editTextPackqty2.setTag(TvPackqty2);
            /*光标放置在文本最后*/
            holder.tvCommoPackqty2.setSelection(holder.tvCommoPackqty2.length());


            holder.tvCommoQCMemo.setEnabled(true);//qc特别备注
            final EditText editTextQCMemo = holder.tvCommoQCMemo;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMemo.getTag() instanceof TextWatcher) {
                editTextQCMemo.removeTextChangedListener((TextWatcher) editTextQCMemo.getTag());
            }
            editTextQCMemo.setText(getItem(position).getQCMemo());
            TextWatcher TvQCMemo = new TextWatcher() {
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
                    String proitem = holder.tvCommoQCMemo.getText().toString();
                    String commoqcmemo = getItem(position).getQCMemo();
                    if (commoqcmemo == null) {
                        commoqcmemo = "";
                    }
                    String nullqcmemo;
                    if (commoqcmemo.equals(proitem)) {
                        nullqcmemo = "1";
                    } else {
                        nullqcmemo = "2";
                        getItem(position).setQCMemo(proitem);
                    }
                    spUtils.put(context, "commonullqcmemo", nullqcmemo);
                    spUtils.put(context, "CommodityQCMemo", proitem);//QC特别备注
                }
            };
            editTextQCMemo.addTextChangedListener(TvQCMemo);
            editTextQCMemo.setTag(TvQCMemo);
            /*光标放置在文本最后*/
            holder.tvCommoQCMemo.setSelection(holder.tvCommoQCMemo.length());

            holder.tvCommoFactlcdat.setEnabled(true);//离厂日期
            holder.tvCommoFactlcdat.setText(getItem(position).getFactlcdat());
            holder.tvCommoFactlcdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoFactlcdat.setText(datetime);
                                    String commofactlcdat = getItem(position).getFactlcdat();
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals(datetime)) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                        getItem(position).setFactlcdat(datetime);
                                    }
                                    spUtils.put(context, "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(context, "dateFactlcdattimesign", datetime);//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFactlcdat.setText("");
                                    String commofactlcdat = getItem(position).getFactlcdat();
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals("")) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                        getItem(position).setFactlcdat("");
                                    }
                                    spUtils.put(context, "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(context, "dateFactlcdattimesign", "");//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoBatchid.setEnabled(true);
            final EditText editTextBatchid = holder.tvCommoBatchid;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextBatchid.getTag() instanceof TextWatcher) {
                editTextBatchid.removeTextChangedListener((TextWatcher) editTextBatchid.getTag());
            }
            editTextBatchid.setText(getItem(position).getBatchid());
            TextWatcher TvBatchid = new TextWatcher() {
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
                    String proitem = holder.tvCommoBatchid.getText().toString();
                    String commoBatchid = getItem(position).getBatchid();
                    if (commoBatchid == null) {
                        commoBatchid = "";
                    }
                    String nullBatchid;
                    if (commoBatchid.equals(proitem)) {
                        nullBatchid = "1";
                    } else {
                        nullBatchid = "2";
                        getItem(position).setBatchid(proitem);
                    }
                    spUtils.put(context, "commonullBatchid", nullBatchid);
                    spUtils.put(context, "CommodityBatchid", proitem);//查货批次
                }
            };
            editTextBatchid.addTextChangedListener(TvBatchid);
            editTextBatchid.setTag(TvBatchid);
            /*光标放置在文本最后*/
            holder.tvCommoBatchid.setSelection(holder.tvCommoBatchid.length());

            holder.tvCommoOurAfter.setEnabled(true);

            holder.tvCommoCtmchkdt.setEnabled(true);
            holder.tvCommoCtmchkdt.setText(getItem(position).getCtmchkdt());
            holder.tvCommoCtmchkdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoCtmchkdt.setText(datetime);
                                    String commoCtmchkdt = getItem(position).getCtmchkdt();
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                        getItem(position).setCtmchkdt(datetime);
                                    }
                                    spUtils.put(context, "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(context, "dateCtmchkdttimesign", datetime);//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoCtmchkdt.setText("");
                                    String commoCtmchkdt = getItem(position).getCtmchkdt();
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                        getItem(position).setCtmchkdt("");
                                    }
                                    spUtils.put(context, "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(context, "dateCtmchkdttimesign", "");//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoIPQCPedt.setEnabled(true);//尾查预查
            final EditText editTextIPQCPedt = holder.tvCommoIPQCPedt;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCPedt.getTag() instanceof TextWatcher) {
                editTextIPQCPedt.removeTextChangedListener((TextWatcher) editTextIPQCPedt.getTag());
            }
            editTextIPQCPedt.setText(getItem(position).getIPQCPedt());
            TextWatcher TvIPQCPedt = new TextWatcher() {
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
                    String proitem = holder.tvCommoIPQCPedt.getText().toString();
                    String commoipqcpedt = getItem(position).getIPQCPedt();
                    if (commoipqcpedt == null) {
                        commoipqcpedt = "";
                    }
                    String nullipqcpedt;
                    if (commoipqcpedt.equals(proitem)) {
                        nullipqcpedt = "1";
                    } else {
                        nullipqcpedt = "2";
                        getItem(position).setIPQCPedt(proitem);
                    }
                    spUtils.put(context, "commonullipqcpedt", nullipqcpedt);
                    spUtils.put(context, "CommodityIPQCPedt", proitem);//尾查预查
                }
            };
            editTextIPQCPedt.addTextChangedListener(TvIPQCPedt);
            editTextIPQCPedt.setTag(TvIPQCPedt);
            /*光标放置在文本最后*/
            holder.tvCommoIPQCPedt.setSelection(holder.tvCommoIPQCPedt.length());


            holder.tvCommoIPQCmdt.setEnabled(true);//巡检中查
            final EditText editTextIPQCmdt = holder.tvCommoIPQCmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCmdt.getTag() instanceof TextWatcher) {
                editTextIPQCmdt.removeTextChangedListener((TextWatcher) editTextIPQCmdt.getTag());
            }
            editTextIPQCmdt.setText(getItem(position).getIPQCmdt());
            TextWatcher TvIPQCmdt = new TextWatcher() {
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
                    String proitem = holder.tvCommoIPQCmdt.getText().toString();
                    String commoipqcmdt = getItem(position).getIPQCmdt();
                    if (commoipqcmdt == null) {
                        commoipqcmdt = "";
                    }
                    String nullipqcmdt;
                    if (commoipqcmdt.equals(proitem)) {
                        nullipqcmdt = "1";
                    } else {
                        nullipqcmdt = "2";
                        getItem(position).setIPQCmdt(proitem);
                    }
                    spUtils.put(context, "commonullipqcmdt", nullipqcmdt);
                    spUtils.put(context, "CommodityIPQCmdt", proitem);//巡检中查
                }
            };
            editTextIPQCmdt.addTextChangedListener(TvIPQCmdt);
            editTextIPQCmdt.setTag(TvIPQCmdt);
            /*光标放置在文本最后*/
            holder.tvCommoIPQCmdt.setSelection(holder.tvCommoIPQCmdt.length());


            holder.tvCommoQAname.setEnabled(true);//QA首扎
            final EditText editTextQAname = holder.tvCommoQAname;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAname.getTag() instanceof TextWatcher) {
                editTextQAname.removeTextChangedListener((TextWatcher) editTextQAname.getTag());
            }
            editTextQAname.setText(getItem(position).getFirstsamQA());
            TextWatcher TvQAname = new TextWatcher() {
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
                    String proitem = holder.tvCommoQAname.getText().toString();
                    String commoQaname = getItem(position).getQAname();
                    if (commoQaname == null) {
                        commoQaname = "";
                    }
                    String nullqaname;
                    if (commoQaname.equals(proitem)) {
                        nullqaname = "1";
                    } else {
                        nullqaname = "2";
                        getItem(position).setQAname(proitem);
                    }
                    spUtils.put(context, "commonullqaname", nullqaname);
                    spUtils.put(context, "CommodityQAname", proitem);//QA首扎
                }
            };
            editTextQAname.addTextChangedListener(TvQAname);
            editTextQAname.setTag(TvQAname);
            /*光标放置在文本最后*/
            holder.tvCommoQAname.setSelection(holder.tvCommoQAname.length());


            holder.tvCommoQAScore.setEnabled(true);//QA首扎件数
            final EditText editTextQAScore = holder.tvCommoQAScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAScore.getTag() instanceof TextWatcher) {
                editTextQAScore.removeTextChangedListener((TextWatcher) editTextQAScore.getTag());
            }
            editTextQAScore.setText(getItem(position).getQAScore());
            TextWatcher TvQAScore = new TextWatcher() {
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
                    String proitem = holder.tvCommoQAScore.getText().toString();
                    String commoqascore = getItem(position).getQAScore();
                    if (commoqascore == null) {
                        commoqascore = "";
                    }
                    String nullqascore;
                    if (commoqascore.equals(proitem)) {
                        nullqascore = "1";
                    } else {
                        nullqascore = "2";
                        getItem(position).setQAScore(proitem);
                    }
                    spUtils.put(context, "commonullqascore", nullqascore);
                    spUtils.put(context, "CommodityQAScore", proitem);//QA首扎件数
                }
            };
            editTextQAScore.addTextChangedListener(TvQAScore);
            editTextQAScore.setTag(TvQAScore);
            /*光标放置在文本最后*/
            holder.tvCommoQAScore.setSelection(holder.tvCommoQAScore.length());


            holder.tvCommoQAMemo.setEnabled(true);
            holder.tvCommoQAMemo.setText(getItem(position).getQAMemo());
            holder.tvCommoQAMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQAMemo.setText(datetime);
                                    String commoqamemo = getItem(position).getQAMemo();
                                    if(commoqamemo==null){
                                        commoqamemo="";
                                    }
                                    String nullqamemo;
                                    if(commoqamemo.equals(datetime)){
                                        nullqamemo="1";
                                    }else{
                                        nullqamemo="2";
                                        getItem(position).setQAMemo(datetime);
                                    }
                                    spUtils.put(context,"commonullqamemo",nullqamemo);
                                    spUtils.put(context, "dateQAMemotimesign", datetime);//QA首扎日
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQAMemo.setText("");
                                    String commoqamemo = getItem(position).getQAMemo();
                                    if(commoqamemo==null){
                                        commoqamemo="";
                                    }
                                    String nullqamemo;
                                    if(commoqamemo.equals("")){
                                        nullqamemo="1";
                                    }else{
                                        nullqamemo="2";
                                        getItem(position).setQAMemo("");
                                    }
                                    spUtils.put(context,"commonullqamemo",nullqamemo);
                                    spUtils.put(context, "dateQAMemotimesign", "");//QA首扎日
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });
            holder.tvCommoPredoc.setEnabled(false);
            final EditText editTextPredoc = holder.tvCommoPredoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPredoc.getTag() instanceof TextWatcher) {
                editTextPredoc.removeTextChangedListener((TextWatcher) editTextPredoc.getTag());
            }
            editTextPredoc.setText(getItem(position).getPredoc());

            holder.tvCommoQCbdtDoc.setEnabled(false);
            final EditText editTextQCbdtDoc = holder.tvCommoQCbdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCbdtDoc.getTag() instanceof TextWatcher) {
                editTextQCbdtDoc.removeTextChangedListener((TextWatcher) editTextQCbdtDoc.getTag());
            }
            editTextQCbdtDoc.setText(getItem(position).getQCbdtDoc());

            holder.tvCommoQCmdtDoc.setEnabled(false);
            final EditText editTextQCmdtDoc = holder.tvCommoQCmdtDoc;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCmdtDoc.getTag() instanceof TextWatcher) {
                editTextQCmdtDoc.removeTextChangedListener((TextWatcher) editTextQCmdtDoc.getTag());
            }
            editTextQCmdtDoc.setText(getItem(position).getQCmdtDoc());

            holder.tvCommoQCedtDoc.setEnabled(false);
            final EditText editTextQCedtDoc = holder.tvCommoQCedtDoc;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCedtDoc.getTag() instanceof TextWatcher) {
                editTextQCedtDoc.removeTextChangedListener((TextWatcher) editTextQCedtDoc.getTag());
            }
            editTextQCedtDoc.setText(getItem(position).getQCedtDoc());

            holder.tvCommoThing.setEnabled(false);
            final EditText editTextThing = holder.tvCommoThing;
            if (editTextThing.getTag() instanceof TextWatcher) {
                editTextThing.removeTextChangedListener((TextWatcher) editTextThing.getTag());
            }
            editTextThing.setText(getItem(position).getChker());

            holder.tvCommoThingAddress.setEnabled(false);
            final EditText editTextThingAddress = holder.tvCommoThingAddress;
            if (editTextThingAddress.getTag() instanceof TextWatcher) {
                editTextThingAddress.removeTextChangedListener((TextWatcher) editTextThingAddress.getTag());
            }
            editTextThingAddress.setText(getItem(position).getChkplace());

            holder.tvCommoThingExpectedTime.setEnabled(false);
            holder.tvCommoThingExpectedTime.setText(getItem(position).getChkpdt());
            holder.tvCommoThingExpectedTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            holder.tvCommoThingTime.setEnabled(false);
            holder.tvCommoThingTime.setText(getItem(position).getChkfctdt());
            holder.tvCommoThingTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            /**
             * 判断跟单员是否是当前登录用户
             */
        } else if (documentary.equals(nameid)) {
            holder.lin_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String proid = String.valueOf(getItem(position).getID());
                    spUtils.put(context, "commoproid", proid);
                    String uriid = String.valueOf(getItem(position).getID());
                    spUtils.put(context, "uriid", uriid);
                }
            });

            holder.tvCommoCtmtxt.setEnabled(true);
            holder.tvCommoCtmtxt.setText(getItem(position).getCtmtxt());

            holder.tvCommoPrddocumentary.setEnabled(true);
            holder.tvCommoPrddocumentary.setText(getItem(position).getPrddocumentary());

            holder.tvCommoprdmaster.setEnabled(true);
            holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());


            holder.tvCommoOurAfter.setText(getItem(position).getOurAfter());

            holder.tvCommoOurAfter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context, v);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_commo_hd, popupMenu.getMenu());
                    // menu的item点击事件
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            sp = context.getSharedPreferences("my_sp", 0);
                            String title = item.getTitle().toString();
                            getItem(position).setOurAfter(title);
                            spUtils.put(context, "commohdTitle", title);//后道
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

            holder.tvCommoQCMasterScore.setEnabled(false);
            final EditText editTextQCMasterScore = holder.tvCommoQCMasterScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMasterScore.getTag() instanceof TextWatcher) {
                editTextQCMasterScore.removeTextChangedListener((TextWatcher) editTextQCMasterScore.getTag());
            }
            editTextQCMasterScore.setText(getItem(position).getQCMasterScore());

            holder.tvCommoSealedrev.setEnabled(true);
            holder.tvCommoSealedrev.getResources().getDrawable(R.drawable.shape_text_sql_boild);
            holder.tvCommoSealedrev.setText(getItem(position).getSealedrev());
            holder.tvCommoSealedrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attentionArr.add(position);
                    if (attentionArr.contains(position)) {
                        Calendar calendar = Calendar.getInstance();
                        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                                context, null, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );
                        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                                , "完成", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DatePicker datePicker = datePickerDialog.getDatePicker();
                                        int year = datePicker.getYear();
                                        int month = datePicker.getMonth();
                                        int day = datePicker.getDayOfMonth();
                                        String datetime = year + "/" + (month + 1) + "/" + day;
                                        holder.tvCommoSealedrev.setText(datetime);
                                        String commosearledrev = getItem(position).getSealedrev();
                                        if (commosearledrev == null) {
                                            commosearledrev = "";
                                        }
                                        String nullsearledrev;
                                        if (commosearledrev.equals(datetime)) {
                                            nullsearledrev = "1";
                                        } else {
                                            nullsearledrev = "2";
                                            if (attentionArr.contains(position)) {
                                                getItem(position).setSealedrev(datetime);
                                            }
                                        }
                                        spUtils.put(context, "commonullsearledrev", nullsearledrev);
                                        spUtils.put(context, "dateSealedrewtimesign", datetime);//封样资料接收时间

                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                                "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoSealedrev.setText("");
                                        String commosearledrev = getItem(position).getSealedrev();
                                        if (commosearledrev == null) {
                                            commosearledrev = "";
                                        }
                                        String nullsearledrev;
                                        if (commosearledrev.equals("")) {
                                            nullsearledrev = "1";
                                        } else {
                                            nullsearledrev = "2";
                                            if (attentionArr.contains(position)) {
                                                getItem(position).setSealedrev("");
                                            }
                                        }
                                        spUtils.put(context, "commonullsearledrev", nullsearledrev);
                                        spUtils.put(context, "dateSealedrewtimesign", "");//封样资料接收时间
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                                , "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        datePickerDialog.show();
                    }
                }
            });


            holder.tvCommoDocback.setEnabled(true);
            holder.tvCommoDocback.setText(getItem(position).getDocback());
            holder.tvCommoDocback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoDocback.setText(datetime);
                                    String commodocback = getItem(position).getDocback();
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals(datetime)) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                        getItem(position).setDocback(datetime);
                                    }
                                    spUtils.put(context, "commonulldocback", nulldocback);
                                    spUtils.put(context, "dateDocbacktimesign", datetime);//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoDocback.setText("");
                                    String commodocback = getItem(position).getDocback();
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals("")) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                        getItem(position).setDocback("");
                                    }
                                    spUtils.put(context, "commonulldocback", nulldocback);
                                    spUtils.put(context, "dateDocbacktimesign", "");//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoLcdat.setEnabled(true);
            holder.tvCommoLcdat.setText(getItem(position).getLcdat());

            holder.tvCommoTaskqty.setEnabled(true);
            holder.tvCommoTaskqty.setText(getItem(position).getTaskqty());

            holder.tvCommoPreMemo.setEnabled(true);
            final EditText editTextPreMemo = holder.tvCommoPreMemo;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPreMemo.getTag() instanceof TextWatcher) {
                editTextPreMemo.removeTextChangedListener((TextWatcher) editTextPreMemo.getTag());
            }
            editTextPreMemo.setText(getItem(position).getPreMemo());
            TextWatcher TvPreMemo = new TextWatcher() {
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
                    String proitem = holder.tvCommoPreMemo.getText().toString();
                    String commopromemo = getItem(position).getPreMemo();
                    if (commopromemo == null) {
                        commopromemo = "";
                    }
                    String nullmemo;
                    if (commopromemo.equals(proitem)) {
                        nullmemo = "1";
                    } else {
                        nullmemo = "2";
                        getItem(position).setPreMemo(proitem);
                    }
                    spUtils.put(context, "commonullmemo", nullmemo);
                    spUtils.put(context, "CommodityPreMemo", proitem);//需要特别备注的情况
                }
            };
            editTextPreMemo.addTextChangedListener(TvPreMemo);
            editTextPreMemo.setTag(TvPreMemo);
            /*光标放置在文本最后*/
            holder.tvCommoPreMemo.setSelection(holder.tvCommoPreMemo.length());


            holder.tvCommoPredocdt.setEnabled(true);
            holder.tvCommoPredocdt.setText(getItem(position).getPredocdt());
            holder.tvCommoPredocdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPredocdt.setText(datetime);
                                    String commopreducdt = getItem(position).getPredocdt();
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals(datetime)) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                        getItem(position).setPredocdt(datetime);
                                    }
                                    spUtils.put(context, "commonullpreducdt", nullpreducdt);
                                    spUtils.put(context, "datePredocdttimesign", datetime);//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPredocdt.setText("");
                                    String commopreducdt = getItem(position).getPredocdt();
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals("")) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                        getItem(position).setPredocdt("");
                                    }
                                    spUtils.put(context, "commonullpreducdt", nullpreducdt);
                                    spUtils.put(context, "datePredocdttimesign", "");//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoPred.setEnabled(true);
            holder.tvCommoPred.setText(getItem(position).getPredt());
            holder.tvCommoPred.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPred.setText(datetime);
                                    String commopred = getItem(position).getPredt();
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals(datetime)) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                        getItem(position).setPredt(datetime);
                                    }
                                    spUtils.put(context, "commonullpred", nullpred);
                                    spUtils.put(context, "datePredtimesign", datetime);//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPred.setText("");
                                    String commopred = getItem(position).getPredt();
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals("")) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                        getItem(position).setPredt("");
                                    }
                                    spUtils.put(context, "commonullpred", nullpred);
                                    spUtils.put(context, "datePredtimesign", "");//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoFabricsok.setEnabled(true);
            final EditText editTextFabricsok = holder.tvCommoFabricsok;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFabricsok.getTag() instanceof TextWatcher) {
                editTextFabricsok.removeTextChangedListener((TextWatcher) editTextFabricsok.getTag());
            }
            editTextFabricsok.setText(getItem(position).getFabricsok());
            TextWatcher TvFabricsok = new TextWatcher() {
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
                    String proitem = holder.tvCommoFabricsok.getText().toString();
                    String commofabricsok = getItem(position).getFabricsok();
                    if (commofabricsok == null) {
                        commofabricsok = "";
                    }
                    String nullfabricsok;
                    if (commofabricsok.equals(proitem)) {
                        nullfabricsok = "1";
                    } else {
                        nullfabricsok = "2";
                        getItem(position).setFabricsok(proitem);
                    }
                    spUtils.put(context, "commonullfabricsok", nullfabricsok);
                    spUtils.put(context, "CommodityFabricsok", proitem);//大货面料情况
                }
            };
            editTextFabricsok.addTextChangedListener(TvFabricsok);
            editTextFabricsok.setTag(TvFabricsok);
            /*光标放置在文本最后*/
            holder.tvCommoFabricsok.setSelection(holder.tvCommoFabricsok.length());


            holder.tvCommoAccessoriesok.setEnabled(true);
            final EditText editTextAccessoriesok = holder.tvCommoAccessoriesok;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextAccessoriesok.getTag() instanceof TextWatcher) {
                editTextAccessoriesok.removeTextChangedListener((TextWatcher) editTextAccessoriesok.getTag());
            }
            editTextAccessoriesok.setText(getItem(position).getAccessoriesok());
            TextWatcher TvAccessoriesok = new TextWatcher() {
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
                    String proitem = holder.tvCommoAccessoriesok.getText().toString();
                    String commoaccessori = getItem(position).getAccessoriesok();
                    if (commoaccessori == null) {
                        commoaccessori = "";
                    }
                    String nullaccessori;
                    if (commoaccessori.equals(proitem)) {
                        nullaccessori = "1";
                    } else {
                        nullaccessori = "2";
                        getItem(position).setAccessoriesok(proitem);
                    }
                    spUtils.put(context, "commonullaccessori", nullaccessori);
                    spUtils.put(context, "CommodityAccessoriesok", proitem);//大货辅料情况
                }
            };
            editTextAccessoriesok.addTextChangedListener(TvAccessoriesok);
            editTextAccessoriesok.setTag(TvAccessoriesok);
            /*光标放置在文本最后*/
            holder.tvCommoAccessoriesok.setSelection(holder.tvCommoAccessoriesok.length());


            holder.tvCommoSpcproDec.setEnabled(true);
            final EditText editTextSpcproDec = holder.tvCommoSpcproDec;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproDec.getTag() instanceof TextWatcher) {
                editTextSpcproDec.removeTextChangedListener((TextWatcher) editTextSpcproDec.getTag());
            }
            editTextSpcproDec.setText(getItem(position).getSpcproDec());
            TextWatcher TvSpcproDec = new TextWatcher() {
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
                    String proitem = holder.tvCommoSpcproDec.getText().toString();
                    String commospcprodec = getItem(position).getSpcproDec();
                    if (commospcprodec == null) {
                        commospcprodec = "";
                    }
                    String nullspcprodec;
                    if (commospcprodec.equals(proitem)) {
                        nullspcprodec = "1";
                    } else {
                        nullspcprodec = "2";
                        getItem(position).setSpcproDec(proitem);
                    }
                    spUtils.put(context, "commonullspcprodec", nullspcprodec);
                    spUtils.put(context, "CommoditySpcproDec", proitem);//大货特殊工艺情况
                }
            };
            editTextSpcproDec.addTextChangedListener(TvSpcproDec);
            editTextSpcproDec.setTag(TvSpcproDec);
            /*光标放置在文本最后*/
            holder.tvCommoSpcproDec.setSelection(holder.tvCommoSpcproDec.length());


            holder.tvCommoSpcproMemo.setEnabled(true);
            final EditText editTextSpcproMemo = holder.tvCommoSpcproMemo;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproMemo.getTag() instanceof TextWatcher) {
                editTextSpcproMemo.removeTextChangedListener((TextWatcher) editTextSpcproMemo.getTag());
            }
            editTextSpcproMemo.setText(getItem(position).getSpcproMemo());
            TextWatcher TvSpcproMemo = new TextWatcher() {
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
                    String proitem = holder.tvCommoSpcproMemo.getText().toString();
                    String commospcpromemo = getItem(position).getSpcproMemo();
                    if (commospcpromemo == null) {
                        commospcpromemo = "";
                    }
                    String nullspcpromemo;
                    if (commospcpromemo.equals(proitem)) {
                        nullspcpromemo = "1";
                    } else {
                        nullspcpromemo = "2";
                        getItem(position).setSpcproMemo(proitem);
                    }
                    spUtils.put(context, "commonullspcpromemo", nullspcpromemo);
                    spUtils.put(context, "CommoditySpcproMemo", proitem);//特殊工艺特别备注
                }
            };
            editTextSpcproMemo.addTextChangedListener(TvSpcproMemo);
            editTextSpcproMemo.setTag(TvSpcproMemo);
            /*光标放置在文本最后*/
            holder.tvCommoSpcproMemo.setSelection(holder.tvCommoSpcproMemo.length());


            holder.tvCommoCutqty.setEnabled(true);
            final EditText editTextCutqty = holder.tvCommoCutqty;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextCutqty.getTag() instanceof TextWatcher) {
                editTextCutqty.removeTextChangedListener((TextWatcher) editTextCutqty.getTag());
            }
            editTextCutqty.setText(getItem(position).getCutqty());
            TextWatcher TvCutqty = new TextWatcher() {
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
                    String proitem = holder.tvCommoCutqty.getText().toString();
                    String commocutqty = getItem(position).getCutqty();
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullcutqty;
                    if (commocutqty.equals(proitem)) {
                        nullcutqty = "1";
                    } else {
                        nullcutqty = "2";
                        getItem(position).setCutqty(proitem);
                    }
                    spUtils.put(context, "commonullcutqty", nullcutqty);
                    spUtils.put(context, "CommodityCutqty", proitem);//实裁数
                }
            };
            editTextCutqty.addTextChangedListener(TvCutqty);
            editTextCutqty.setTag(TvCutqty);
            /*光标放置在文本最后*/
            holder.tvCommoCutqty.setSelection(holder.tvCommoCutqty.length());


            holder.tvCommoSewFdt.setEnabled(true);
            holder.tvCommoSewFdt.setText(getItem(position).getSewFdt());
            holder.tvCommoSewFdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoSewFdt.setText(datetime);
                                    String commosewfdt = getItem(position).getSewFdt();
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals(datetime)) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                        getItem(position).setSewFdt(datetime);
                                    }
                                    spUtils.put(context, "commonullsewfdt", nullsewfdt);
                                    spUtils.put(context, "dateSewFdttimesign", datetime);//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewFdt.setText("");
                                    String commosewfdt = getItem(position).getSewFdt();
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals("")) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                        getItem(position).setSewFdt("");
                                    }
                                    spUtils.put(context, "commonullsewfdt", nullsewfdt);
                                    spUtils.put(context, "dateSewFdttimesign", "");//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoSewMdt.setEnabled(true);
            holder.tvCommoSewMdt.setText(getItem(position).getSewMdt());
            holder.tvCommoSewMdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoSewMdt.setText(datetime);
                                    String commosewmdt = getItem(position).getSewMdt();
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals(datetime)) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                        getItem(position).setSewMdt(datetime);
                                    }
                                    spUtils.put(context, "commonullsewmdt", nullsewmdt);
                                    spUtils.put(context, "dateSewMdttimesign", datetime);//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewMdt.setText("");
                                    String commosewmdt = getItem(position).getSewMdt();
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals("")) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                        getItem(position).setSewMdt("");
                                    }
                                    spUtils.put(context, "commonullsewmdt", nullsewmdt);
                                    spUtils.put(context, "dateSewMdttimesign", "");//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoSubfactory.setEnabled(true);
            holder.tvCommoSubfactory.setText(getItem(position).getSubfactory());
            holder.tvCommoSubfactory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPrebdt.setEnabled(true);
            holder.tvCommoPrebdt.setText(getItem(position).getPrebdt());
            holder.tvCommoPrebdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPrebdt.setText(datetime);
                                    String commoprebdt = getItem(position).getPrebdt();
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals(datetime)) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                        getItem(position).setPrebdt(datetime);
                                    }
                                    spUtils.put(context, "commonullprebdt", nullprebdt);
                                    spUtils.put(context, "datePrebdttimesign", datetime);//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPrebdt.setText("");
                                    String commoprebdt = getItem(position).getPrebdt();
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals("")) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                        getItem(position).setPrebdt("");
                                    }
                                    spUtils.put(context, "commonullprebdt", nullprebdt);
                                    spUtils.put(context, "datePrebdttimesign", "");//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoQCbdt.setEnabled(true);
            holder.tvCommoQCbdt.setText(getItem(position).getQCbdt());
            holder.tvCommoQCbdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQCbdt.setText(datetime);
                                    String commoqcbdt = getItem(position).getQCbdt();
                                    if (commoqcbdt == null) {
                                        commoqcbdt = "";
                                    }
                                    String nullqcbdt;
                                    if (commoqcbdt.equals(datetime)) {
                                        nullqcbdt = "1";
                                    } else {
                                        nullqcbdt = "2";
                                        getItem(position).setQCbdt(datetime);
                                    }
                                    spUtils.put(context, "commonullqcbdt", nullqcbdt);
                                    spUtils.put(context, "dateQCbdttimesign", datetime);//自查早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCbdt.setText("");
                                    String commoqcbdt = getItem(position).getQCbdt();
                                    if (commoqcbdt == null) {
                                        commoqcbdt = "";
                                    }
                                    String nullqcbdt;
                                    if (commoqcbdt.equals("")) {
                                        nullqcbdt = "1";
                                    } else {
                                        nullqcbdt = "2";
                                        getItem(position).setQCbdt("");
                                    }
                                    spUtils.put(context, "commonullqcbdt", nullqcbdt);
                                    spUtils.put(context, "dateQCbdttimesign", "");//自查早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPremdt.setEnabled(true);//预计中期时间
            holder.tvCommoPremdt.setText(getItem(position).getPremdt());
            holder.tvCommoPremdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPremdt.setText(datetime);
                                    String commopremdt = getItem(position).getPremdt();
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals(datetime)) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                        getItem(position).setPremdt(datetime);
                                    }
                                    spUtils.put(context, "commonullpremdt", nullpremdt);
                                    spUtils.put(context, "datePremdttimesign", datetime);//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPremdt.setText("");
                                    String commopremdt = getItem(position).getPremdt();
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals("")) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                        getItem(position).setPremdt("");
                                    }
                                    spUtils.put(context, "commonullpremdt", nullpremdt);
                                    spUtils.put(context, "datePremdttimesign", "");//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoQCmdt.setEnabled(true);
            holder.tvCommoQCmdt.setText(getItem(position).getQCmdt());
            holder.tvCommoQCmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQCmdt.setText(datetime);
                                    String commoecmdt = getItem(position).getQCmdt();
                                    if (commoecmdt == null) {
                                        commoecmdt = "";
                                    }
                                    String nullqcmdt;
                                    if (commoecmdt.equals(datetime)) {
                                        nullqcmdt = "1";
                                    } else {
                                        nullqcmdt = "2";
                                        getItem(position).setQCmdt(datetime);
                                    }
                                    spUtils.put(context, "commonullqcmdt", nullqcmdt);
                                    spUtils.put(context, "dateQCmdttimesign", datetime);//自查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCmdt.setText("");
                                    String commoecmdt = getItem(position).getQCmdt();
                                    if (commoecmdt == null) {
                                        commoecmdt = "";
                                    }
                                    String nullqcmdt;
                                    if (commoecmdt.equals("")) {
                                        nullqcmdt = "1";
                                    } else {
                                        nullqcmdt = "2";
                                        getItem(position).setQCmdt("");
                                    }
                                    spUtils.put(context, "commonullqcmdt", nullqcmdt);
                                    spUtils.put(context, "dateQCmdttimesign", "");//自查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPreedt.setEnabled(true);
            holder.tvCommoPreedt.setText(getItem(position).getPreedt());
            holder.tvCommoPreedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPreedt.setText(datetime);
                                    String commopreedt = getItem(position).getPreedt();
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals(datetime)) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                        getItem(position).setPreedt(datetime);
                                    }
                                    spUtils.put(context, "commonullpreedt", nullpreedt);
                                    spUtils.put(context, "datePreedttimesign", datetime);//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPreedt.setText("");
                                    String commopreedt = getItem(position).getPreedt();
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals("")) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                        getItem(position).setPreedt("");
                                    }
                                    spUtils.put(context, "commonullpreedt", nullpreedt);
                                    spUtils.put(context, "datePreedttimesign", "");//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoQCMedt.setEnabled(true);
            holder.tvCommoQCMedt.setText(getItem(position).getQCMedt());
            holder.tvCommoQCMedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQCMedt.setText(datetime);
                                    String commoqcmedt = getItem(position).getQCMedt();
                                    if (commoqcmedt == null) {
                                        commoqcmedt = "";
                                    }
                                    String nullqcmedt;
                                    if (commoqcmedt.equals(datetime)) {
                                        nullqcmedt = "1";
                                    } else {
                                        nullqcmedt = "2";
                                        getItem(position).setQCMedt(datetime);
                                    }
                                    spUtils.put(context, "commonullqcmedt", nullqcmedt);
                                    spUtils.put(context, "dateQCMedttimesign", datetime);//自查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCMedt.setText("");
                                    String commoqcmedt = getItem(position).getQCMedt();
                                    if (commoqcmedt == null) {
                                        commoqcmedt = "";
                                    }
                                    String nullqcmedt;
                                    if (commoqcmedt.equals("")) {
                                        nullqcmedt = "1";
                                    } else {
                                        nullqcmedt = "2";
                                        getItem(position).setQCMedt("");
                                    }
                                    spUtils.put(context, "commonullqcmedt", nullqcmedt);
                                    spUtils.put(context, "dateQCMedttimesign", "");//自查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoFctmdt.setEnabled(true);
            holder.tvCommoFctmdt.setText(getItem(position).getFctmdt());
            holder.tvCommoFctmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoFctmdt.setText(datetime);
                                    String commofctmdt = getItem(position).getFctmdt();
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals(datetime)) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                        getItem(position).setFctmdt(datetime);
                                    }
                                    spUtils.put(context, "commonullfctmdt", nullfctmdt);
                                    spUtils.put(context, "dateFctmdttimesign", datetime);//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctmdt.setText("");
                                    String commofctmdt = getItem(position).getFctmdt();
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals("")) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                        getItem(position).setFctmdt("");
                                    }
                                    spUtils.put(context, "commonullfctmdt", nullfctmdt);
                                    spUtils.put(context, "dateFctmdttimesign", "");//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoFctedt.setEnabled(true);
            holder.tvCommoFctedt.setText(getItem(position).getFctedt());
            holder.tvCommoFctedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoFctedt.setText(datetime);
                                    String commofctedt = getItem(position).getFctedt();
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals(datetime)) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                        getItem(position).setFctedt(datetime);
                                    }
                                    spUtils.put(context, "commonullfctedt", nullfctedt);
                                    spUtils.put(context, "dateFctedttimesign", datetime);//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctedt.setText("");
                                    String commofctedt = getItem(position).getFctedt();
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals("")) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                        getItem(position).setFctedt("");
                                    }
                                    spUtils.put(context, "commonullfctedt", nullfctedt);
                                    spUtils.put(context, "dateFctedttimesign", "");//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoPackbdat.setEnabled(true);
            holder.tvCommoPackbdat.setText(getItem(position).getPackbdat());
            holder.tvCommoPackbdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPackbdat.setText(datetime);
                                    String commopackbdat = getItem(position).getPackbdat();
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals(datetime)) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                        getItem(position).setPackbdat(datetime);
                                    }
                                    spUtils.put(context, "commonullpackbdat", nullpackbdat);
                                    spUtils.put(context, "datePackbdattimesign", datetime);//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPackbdat.setText("");
                                    String commopackbdat = getItem(position).getPackbdat();
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals("")) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                        getItem(position).setPackbdat("");
                                    }
                                    spUtils.put(context, "commonullpackbdat", nullpackbdat);
                                    spUtils.put(context, "datePackbdattimesign", "");//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPackqty2.setEnabled(true);
            final EditText editTextPackqty2 = holder.tvCommoPackqty2;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPackqty2.getTag() instanceof TextWatcher) {
                editTextPackqty2.removeTextChangedListener((TextWatcher) editTextPackqty2.getTag());
            }
            editTextPackqty2.setText(getItem(position).getPackqty2());
            TextWatcher TvPackqty2 = new TextWatcher() {
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
                    String proitem = holder.tvCommoPackqty2.getText().toString();
                    String commopackqty2 = getItem(position).getPackqty2();
                    if (commopackqty2 == null) {
                        commopackqty2 = "";
                    }
                    String nullpackqty2;
                    if (commopackqty2.equals(proitem)) {
                        nullpackqty2 = "1";
                    } else {
                        nullpackqty2 = "2";
                        getItem(position).setPackqty2(proitem);
                    }
                    spUtils.put(context, "commonullpackqty2", nullpackqty2);
                    spUtils.put(context, "CommodityPackqty2", proitem);//装箱数量
                }
            };
            editTextPackqty2.addTextChangedListener(TvPackqty2);
            editTextPackqty2.setTag(TvPackqty2);
            /*光标放置在文本最后*/
            holder.tvCommoPackqty2.setSelection(holder.tvCommoPackqty2.length());


            holder.tvCommoQCMemo.setEnabled(true);
            final EditText editTextQCMemo = holder.tvCommoQCMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMemo.getTag() instanceof TextWatcher) {
                editTextQCMemo.removeTextChangedListener((TextWatcher) editTextQCMemo.getTag());
            }
            editTextQCMemo.setText(getItem(position).getQCMemo());
            TextWatcher TvQCMemo = new TextWatcher() {
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
                    String proitem = holder.tvCommoQCMemo.getText().toString();
                    String commoqcmemo = getItem(position).getQCMemo();
                    if (commoqcmemo == null) {
                        commoqcmemo = "";
                    }
                    String nullqcmemo;
                    if (commoqcmemo.equals(proitem)) {
                        nullqcmemo = "1";
                    } else {
                        nullqcmemo = "2";
                        getItem(position).setQCMemo(proitem);
                    }
                    spUtils.put(context, "commonullqcmemo", nullqcmemo);
                    spUtils.put(context, "CommodityQCMemo", proitem);//QC特别备注
                }
            };
            editTextQCMemo.addTextChangedListener(TvQCMemo);
            editTextQCMemo.setTag(TvQCMemo);
            /*光标放置在文本最后*/
            holder.tvCommoQCMemo.setSelection(holder.tvCommoQCMemo.length());

            holder.tvCommoFactlcdat.setEnabled(true);
            holder.tvCommoFactlcdat.setText(getItem(position).getFactlcdat());
            holder.tvCommoFactlcdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoFactlcdat.setText(datetime);
                                    String commofactlcdat = getItem(position).getFactlcdat();
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals(datetime)) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                        getItem(position).setFactlcdat(datetime);
                                    }
                                    spUtils.put(context, "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(context, "dateFactlcdattimesign", datetime);//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFactlcdat.setText("");
                                    String commofactlcdat = getItem(position).getFactlcdat();
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals("")) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                        getItem(position).setFactlcdat("");
                                    }
                                    spUtils.put(context, "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(context, "dateFactlcdattimesign", "");//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoBatchid.setEnabled(true);
            final EditText editTextBatchid = holder.tvCommoBatchid;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextBatchid.getTag() instanceof TextWatcher) {
                editTextBatchid.removeTextChangedListener((TextWatcher) editTextBatchid.getTag());
            }
            editTextBatchid.setText(getItem(position).getBatchid());
            TextWatcher TvBatchid = new TextWatcher() {
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
                    String proitem = holder.tvCommoBatchid.getText().toString();
                    String commoBatchid = getItem(position).getBatchid();
                    if (commoBatchid == null) {
                        commoBatchid = "";
                    }
                    String nullBatchid;
                    if (commoBatchid.equals(proitem)) {
                        nullBatchid = "1";
                    } else {
                        nullBatchid = "2";
                        getItem(position).setBatchid(proitem);
                    }
                    spUtils.put(context, "commonullBatchid", nullBatchid);
                    spUtils.put(context, "CommodityBatchid", proitem);//查货批次
                }
            };
            editTextBatchid.addTextChangedListener(TvBatchid);
            editTextBatchid.setTag(TvBatchid);
            /*光标放置在文本最后*/
            holder.tvCommoBatchid.setSelection(holder.tvCommoBatchid.length());

            holder.tvCommoOurAfter.setEnabled(true);

            holder.tvCommoCtmchkdt.setEnabled(true);
            holder.tvCommoCtmchkdt.setText(getItem(position).getCtmchkdt());
            holder.tvCommoCtmchkdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoCtmchkdt.setText(datetime);
                                    String commoCtmchkdt = getItem(position).getCtmchkdt();
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                        getItem(position).setCtmchkdt(datetime);
                                    }
                                    spUtils.put(context, "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(context, "dateCtmchkdttimesign", datetime);//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoCtmchkdt.setText("");
                                    String commoCtmchkdt = getItem(position).getCtmchkdt();
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                        getItem(position).setCtmchkdt("");
                                    }
                                    spUtils.put(context, "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(context, "dateCtmchkdttimesign", "");//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoIPQCPedt.setEnabled(true);
            final EditText editTextIPQCPedt = holder.tvCommoIPQCPedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCPedt.getTag() instanceof TextWatcher) {
                editTextIPQCPedt.removeTextChangedListener((TextWatcher) editTextIPQCPedt.getTag());
            }
            editTextIPQCPedt.setText(getItem(position).getIPQCPedt());
            TextWatcher TvIPQCPedt = new TextWatcher() {
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
                    String proitem = holder.tvCommoIPQCPedt.getText().toString();
                    String commoipqcpedt = getItem(position).getIPQCPedt();
                    if (commoipqcpedt == null) {
                        commoipqcpedt = "";
                    }
                    String nullipqcpedt;
                    if (commoipqcpedt.equals(proitem)) {
                        nullipqcpedt = "1";
                    } else {
                        nullipqcpedt = "2";
                        getItem(position).setIPQCPedt(proitem);
                    }
                    spUtils.put(context, "commonullipqcpedt", nullipqcpedt);
                    spUtils.put(context, "CommodityIPQCPedt", proitem);//尾查预查
                }
            };
            editTextIPQCPedt.addTextChangedListener(TvIPQCPedt);
            editTextIPQCPedt.setTag(TvIPQCPedt);
            /*光标放置在文本最后*/
            holder.tvCommoIPQCPedt.setSelection(holder.tvCommoIPQCPedt.length());


            holder.tvCommoIPQCmdt.setEnabled(true);
            final EditText editTextIPQCmdt = holder.tvCommoIPQCmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCmdt.getTag() instanceof TextWatcher) {
                editTextIPQCmdt.removeTextChangedListener((TextWatcher) editTextIPQCmdt.getTag());
            }
            editTextIPQCmdt.setText(getItem(position).getIPQCmdt());
            TextWatcher TvIPQCmdt = new TextWatcher() {
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
                    String proitem = holder.tvCommoIPQCmdt.getText().toString();
                    String commoipqcmdt = getItem(position).getIPQCmdt();
                    if (commoipqcmdt == null) {
                        commoipqcmdt = "";
                    }
                    String nullipqcmdt;
                    if (commoipqcmdt.equals(proitem)) {
                        nullipqcmdt = "1";
                    } else {
                        nullipqcmdt = "2";
                        getItem(position).setIPQCmdt(proitem);
                    }
                    spUtils.put(context, "commonullipqcmdt", nullipqcmdt);
                    spUtils.put(context, "CommodityIPQCmdt", proitem);//巡检中查
                }
            };
            editTextIPQCmdt.addTextChangedListener(TvIPQCmdt);
            editTextIPQCmdt.setTag(TvIPQCmdt);
            /*光标放置在文本最后*/
            holder.tvCommoIPQCmdt.setSelection(holder.tvCommoIPQCmdt.length());


            holder.tvCommoQAname.setEnabled(true);
            final EditText editTextQAname = holder.tvCommoQAname;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAname.getTag() instanceof TextWatcher) {
                editTextQAname.removeTextChangedListener((TextWatcher) editTextQAname.getTag());
            }
            editTextQAname.setText(getItem(position).getFirstsamQA());
            TextWatcher TvQAname = new TextWatcher() {
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
                    String proitem = holder.tvCommoQAname.getText().toString();
                    String commoQaname = getItem(position).getQAname();
                    if (commoQaname == null) {
                        commoQaname = "";
                    }
                    String nullqaname;
                    if (commoQaname.equals(proitem)) {
                        nullqaname = "1";
                    } else {
                        nullqaname = "2";
                        getItem(position).setQAname(proitem);
                    }
                    spUtils.put(context, "commonullqaname", nullqaname);
                    spUtils.put(context, "CommodityQAname", proitem);//QA首扎
                }
            };
            editTextQAname.addTextChangedListener(TvQAname);
            editTextQAname.setTag(TvQAname);
            /*光标放置在文本最后*/
            holder.tvCommoQAname.setSelection(holder.tvCommoQAname.length());


            holder.tvCommoQAScore.setEnabled(true);
            final EditText editTextQAScore = holder.tvCommoQAScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAScore.getTag() instanceof TextWatcher) {
                editTextQAScore.removeTextChangedListener((TextWatcher) editTextQAScore.getTag());
            }
            editTextQAScore.setText(getItem(position).getQAScore());
            TextWatcher TvQAScore = new TextWatcher() {
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
                    String proitem = holder.tvCommoQAScore.getText().toString();
                    String commoqascore = getItem(position).getQAScore();
                    if (commoqascore == null) {
                        commoqascore = "";
                    }
                    String nullqascore;
                    if (commoqascore.equals(proitem)) {
                        nullqascore = "1";
                    } else {
                        nullqascore = "2";
                        getItem(position).setQAScore(proitem);
                    }
                    spUtils.put(context, "commonullqascore", nullqascore);
                    spUtils.put(context, "CommodityQAScore", proitem);//QA首扎件数
                }
            };
            editTextQAScore.addTextChangedListener(TvQAScore);
            editTextQAScore.setTag(TvQAScore);
            /*光标放置在文本最后*/
            holder.tvCommoQAScore.setSelection(holder.tvCommoQAScore.length());


            holder.tvCommoQAMemo.setEnabled(true);
            holder.tvCommoQAMemo.setText(getItem(position).getQAMemo());
            holder.tvCommoQAMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQAMemo.setText(datetime);
                                    String commoqamemo = getItem(position).getQAMemo();
                                    if(commoqamemo==null){
                                        commoqamemo="";
                                    }
                                    String nullqamemo;
                                    if(commoqamemo.equals(datetime)){
                                        nullqamemo="1";
                                    }else{
                                        nullqamemo="2";
                                        getItem(position).setQAMemo(datetime);
                                    }
                                    spUtils.put(context,"commonullqamemo",nullqamemo);
                                    spUtils.put(context, "dateQAMemotimesign", datetime);//QA首扎日
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQAMemo.setText("");
                                    String commoqamemo = getItem(position).getQAMemo();
                                    if(commoqamemo==null){
                                        commoqamemo="";
                                    }
                                    String nullqamemo;
                                    if(commoqamemo.equals("")){
                                        nullqamemo="1";
                                    }else{
                                        nullqamemo="2";
                                        getItem(position).setQAMemo("");
                                    }
                                    spUtils.put(context,"commonullqamemo",nullqamemo);
                                    spUtils.put(context, "dateQAMemotimesign", "");//QA首扎日
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });
            holder.tvCommoPredoc.setEnabled(false);
            final EditText editTextPredoc = holder.tvCommoPredoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPredoc.getTag() instanceof TextWatcher) {
                editTextPredoc.removeTextChangedListener((TextWatcher) editTextPredoc.getTag());
            }
            editTextPredoc.setText(getItem(position).getPredoc());

            holder.tvCommoQCbdtDoc.setEnabled(false);
            final EditText editTextQCbdtDoc = holder.tvCommoQCbdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCbdtDoc.getTag() instanceof TextWatcher) {
                editTextQCbdtDoc.removeTextChangedListener((TextWatcher) editTextQCbdtDoc.getTag());
            }
            editTextQCbdtDoc.setText(getItem(position).getQCbdtDoc());

            holder.tvCommoQCmdtDoc.setEnabled(false);
            final EditText editTextQCmdtDoc = holder.tvCommoQCmdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCmdtDoc.getTag() instanceof TextWatcher) {
                editTextQCmdtDoc.removeTextChangedListener((TextWatcher) editTextQCmdtDoc.getTag());
            }
            editTextQCmdtDoc.setText(getItem(position).getQCmdtDoc());

            holder.tvCommoQCedtDoc.setEnabled(false);
            final EditText editTextQCedtDoc = holder.tvCommoQCedtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCedtDoc.getTag() instanceof TextWatcher) {
                editTextQCedtDoc.removeTextChangedListener((TextWatcher) editTextQCedtDoc.getTag());
            }
            editTextQCedtDoc.setText(getItem(position).getQCedtDoc());

            holder.tvCommoThing.setEnabled(false);
            final EditText editTextThing = holder.tvCommoThing;
            if (editTextThing.getTag() instanceof TextWatcher) {
                editTextThing.removeTextChangedListener((TextWatcher) editTextThing.getTag());
            }
            editTextThing.setText(getItem(position).getChker());

            holder.tvCommoThingAddress.setEnabled(false);
            final EditText editTextThingAddress = holder.tvCommoThingAddress;
            if (editTextThingAddress.getTag() instanceof TextWatcher) {
                editTextThingAddress.removeTextChangedListener((TextWatcher) editTextThingAddress.getTag());
            }
            editTextThingAddress.setText(getItem(position).getChkplace());

            holder.tvCommoThingExpectedTime.setEnabled(false);
            holder.tvCommoThingExpectedTime.setText(getItem(position).getChkpdt());
            holder.tvCommoThingExpectedTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoThingTime.setEnabled(false);
            holder.tvCommoThingTime.setText(getItem(position).getChkfctdt());
            holder.tvCommoThingTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            /**
             * 判断登录人是否是陈慧萍（跟单负责人）
             */
        } else if (nameid.equals("陈慧萍")) {

            holder.tvCommoCtmtxt.setEnabled(true);
            holder.tvCommoCtmtxt.setText(getItem(position).getCtmtxt());

            holder.tvCommoPrddocumentary.setEnabled(true);
            holder.tvCommoPrddocumentary.setText(getItem(position).getPrddocumentary());

            holder.tvCommoprdmaster.setEnabled(true);
            holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());


            holder.tvCommoOurAfter.setText(getItem(position).getOurAfter());

            holder.tvCommoOurAfter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context, v);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_commo_hd, popupMenu.getMenu());
                    // menu的item点击事件
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            sp = context.getSharedPreferences("my_sp", 0);
                            String title = item.getTitle().toString();
                            getItem(position).setOurAfter(title);
                            spUtils.put(context, "commohdTitle", title);//后道
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

            holder.tvCommoQCMasterScore.setEnabled(false);
            final EditText editTextQCMasterScore = holder.tvCommoQCMasterScore;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMasterScore.getTag() instanceof TextWatcher) {
                editTextQCMasterScore.removeTextChangedListener((TextWatcher) editTextQCMasterScore.getTag());
            }
            editTextQCMasterScore.setText(getItem(position).getQCMasterScore());

            holder.tvCommoSealedrev.setEnabled(true);
            holder.tvCommoSealedrev.getResources().getDrawable(R.drawable.shape_text_sql_boild);
            holder.tvCommoSealedrev.setText(getItem(position).getSealedrev());
            holder.tvCommoSealedrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attentionArr.add(position);
                    if (attentionArr.contains(position)) {
                        Calendar calendar = Calendar.getInstance();
                        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                                context, null, calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );
                        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                                , "完成", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DatePicker datePicker = datePickerDialog.getDatePicker();
                                        int year = datePicker.getYear();
                                        int month = datePicker.getMonth();
                                        int day = datePicker.getDayOfMonth();
                                        String datetime = year + "/" + (month + 1) + "/" + day;
                                        holder.tvCommoSealedrev.setText(datetime);
                                        String commosearledrev = getItem(position).getSealedrev();
                                        if (commosearledrev == null) {
                                            commosearledrev = "";
                                        }
                                        String nullsearledrev;
                                        if (commosearledrev.equals(datetime)) {
                                            nullsearledrev = "1";
                                        } else {
                                            nullsearledrev = "2";
                                            if (attentionArr.contains(position)) {
                                                getItem(position).setSealedrev(datetime);
                                            }
                                        }
                                        spUtils.put(context, "commonullsearledrev", nullsearledrev);
                                        spUtils.put(context, "dateSealedrewtimesign", datetime);//封样资料接收时间
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                                "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoSealedrev.setText("");
                                        String commosearledrev = getItem(position).getSealedrev();
                                        if (commosearledrev == null) {
                                            commosearledrev = "";
                                        }
                                        String nullsearledrev;
                                        if (commosearledrev.equals("")) {
                                            nullsearledrev = "1";
                                        } else {
                                            nullsearledrev = "2";
                                            if (attentionArr.contains(position)) {
                                                getItem(position).setSealedrev("");
                                            }
                                        }
                                        spUtils.put(context, "commonullsearledrev", nullsearledrev);
                                        spUtils.put(context, "dateSealedrewtimesign", "");//封样资料接收时间
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                                , "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        datePickerDialog.show();
                    }
                }
            });


            holder.tvCommoDocback.setEnabled(true);
            holder.tvCommoDocback.setText(getItem(position).getDocback());
            holder.tvCommoDocback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoDocback.setText(datetime);
                                    String commodocback = getItem(position).getDocback();
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals(datetime)) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                        getItem(position).setDocback(datetime);
                                    }
                                    spUtils.put(context, "commonulldocback", nulldocback);
                                    spUtils.put(context, "dateDocbacktimesign", datetime);//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoDocback.setText("");
                                    String commodocback = getItem(position).getDocback();
                                    if (commodocback == null) {
                                        commodocback = "";
                                    }
                                    String nulldocback;
                                    if (commodocback.equals("")) {
                                        nulldocback = "1";
                                    } else {
                                        nulldocback = "2";
                                        getItem(position).setDocback("");
                                    }
                                    spUtils.put(context, "commonulldocback", nulldocback);
                                    spUtils.put(context, "dateDocbacktimesign", "");//大货资料接收时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoLcdat.setEnabled(true);
            holder.tvCommoLcdat.setText(getItem(position).getLcdat());

            holder.tvCommoTaskqty.setEnabled(true);
            holder.tvCommoTaskqty.setText(getItem(position).getTaskqty());

            holder.tvCommoPreMemo.setEnabled(true);
            final EditText editTextPreMemo = holder.tvCommoPreMemo;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPreMemo.getTag() instanceof TextWatcher) {
                editTextPreMemo.removeTextChangedListener((TextWatcher) editTextPreMemo.getTag());
            }
            editTextPreMemo.setText(getItem(position).getPreMemo());
            TextWatcher TvPreMemo = new TextWatcher() {
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
                    String proitem = holder.tvCommoPreMemo.getText().toString();
                    String commopromemo = getItem(position).getPreMemo();
                    if (commopromemo == null) {
                        commopromemo = "";
                    }
                    String nullmemo;
                    if (commopromemo.equals(proitem)) {
                        nullmemo = "1";
                    } else {
                        nullmemo = "2";
                        getItem(position).setPreMemo(proitem);
                    }
                    spUtils.put(context, "commonullmemo", nullmemo);
                    spUtils.put(context, "CommodityPreMemo", proitem);//需要特别备注的情况
                }
            };
            editTextPreMemo.addTextChangedListener(TvPreMemo);
            editTextPreMemo.setTag(TvPreMemo);
            /*光标放置在文本最后*/
            holder.tvCommoPreMemo.setSelection(holder.tvCommoPreMemo.length());


            holder.tvCommoPredocdt.setEnabled(true);
            holder.tvCommoPredocdt.setText(getItem(position).getPredocdt());
            holder.tvCommoPredocdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPredocdt.setText(datetime);
                                    String commopreducdt = getItem(position).getPredocdt();
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals(datetime)) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                        getItem(position).setPredocdt(datetime);
                                    }
                                    spUtils.put(context, "commonullpreducdt", nullpreducdt);
                                    spUtils.put(context, "datePredocdttimesign", datetime);//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPredocdt.setText("");
                                    String commopreducdt = getItem(position).getPredocdt();
                                    if (commopreducdt == null) {
                                        commopreducdt = "";
                                    }
                                    String nullpreducdt;
                                    if (commopreducdt.equals("")) {
                                        nullpreducdt = "1";
                                    } else {
                                        nullpreducdt = "2";
                                        getItem(position).setPredocdt("");
                                    }
                                    spUtils.put(context, "commonullpreducdt", nullpreducdt);
                                    spUtils.put(context, "datePredocdttimesign", "");//预计产前报告时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoPred.setEnabled(true);
            holder.tvCommoPred.setText(getItem(position).getPredt());
            holder.tvCommoPred.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPred.setText(datetime);
                                    String commopred = getItem(position).getPredt();
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals(datetime)) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                        getItem(position).setPredt(datetime);
                                    }
                                    spUtils.put(context, "commonullpred", nullpred);
                                    spUtils.put(context, "datePredtimesign", datetime);//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPred.setText("");
                                    String commopred = getItem(position).getPredt();
                                    if (commopred == null) {
                                        commopred = "";
                                    }
                                    String nullpred;
                                    if (commopred.equals("")) {
                                        nullpred = "1";
                                    } else {
                                        nullpred = "2";
                                        getItem(position).setPredt("");
                                    }
                                    spUtils.put(context, "commonullpred", nullpred);
                                    spUtils.put(context, "datePredtimesign", "");//开产前会时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoFabricsok.setEnabled(true);
            final EditText editTextFabricsok = holder.tvCommoFabricsok;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFabricsok.getTag() instanceof TextWatcher) {
                editTextFabricsok.removeTextChangedListener((TextWatcher) editTextFabricsok.getTag());
            }
            editTextFabricsok.setText(getItem(position).getFabricsok());
            TextWatcher TvFabricsok = new TextWatcher() {
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
                    String proitem = holder.tvCommoFabricsok.getText().toString();
                    String commofabricsok = getItem(position).getFabricsok();
                    if (commofabricsok == null) {
                        commofabricsok = "";
                    }
                    String nullfabricsok;
                    if (commofabricsok.equals(proitem)) {
                        nullfabricsok = "1";
                    } else {
                        nullfabricsok = "2";
                        getItem(position).setFabricsok(proitem);
                    }
                    spUtils.put(context, "commonullfabricsok", nullfabricsok);
                    spUtils.put(context, "CommodityFabricsok", proitem);//大货面料情况
                }
            };
            editTextFabricsok.addTextChangedListener(TvFabricsok);
            editTextFabricsok.setTag(TvFabricsok);
            /*光标放置在文本最后*/
            holder.tvCommoFabricsok.setSelection(holder.tvCommoFabricsok.length());


            holder.tvCommoAccessoriesok.setEnabled(true);
            final EditText editTextAccessoriesok = holder.tvCommoAccessoriesok;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextAccessoriesok.getTag() instanceof TextWatcher) {
                editTextAccessoriesok.removeTextChangedListener((TextWatcher) editTextAccessoriesok.getTag());
            }
            editTextAccessoriesok.setText(getItem(position).getAccessoriesok());
            TextWatcher TvAccessoriesok = new TextWatcher() {
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
                    String proitem = holder.tvCommoAccessoriesok.getText().toString();
                    String commoaccessori = getItem(position).getAccessoriesok();
                    if (commoaccessori == null) {
                        commoaccessori = "";
                    }
                    String nullaccessori;
                    if (commoaccessori.equals(proitem)) {
                        nullaccessori = "1";
                    } else {
                        nullaccessori = "2";
                        getItem(position).setAccessoriesok(proitem);
                    }
                    spUtils.put(context, "commonullaccessori", nullaccessori);
                    spUtils.put(context, "CommodityAccessoriesok", proitem);//大货辅料情况
                }
            };
            editTextAccessoriesok.addTextChangedListener(TvAccessoriesok);
            editTextAccessoriesok.setTag(TvAccessoriesok);
            /*光标放置在文本最后*/
            holder.tvCommoAccessoriesok.setSelection(holder.tvCommoAccessoriesok.length());


            holder.tvCommoSpcproDec.setEnabled(true);
            final EditText editTextSpcproDec = holder.tvCommoSpcproDec;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproDec.getTag() instanceof TextWatcher) {
                editTextSpcproDec.removeTextChangedListener((TextWatcher) editTextSpcproDec.getTag());
            }
            editTextSpcproDec.setText(getItem(position).getSpcproDec());
            TextWatcher TvSpcproDec = new TextWatcher() {
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
                    String proitem = holder.tvCommoSpcproDec.getText().toString();
                    String commospcprodec = getItem(position).getSpcproDec();
                    if (commospcprodec == null) {
                        commospcprodec = "";
                    }
                    String nullspcprodec;
                    if (commospcprodec.equals(proitem)) {
                        nullspcprodec = "1";
                    } else {
                        nullspcprodec = "2";
                        getItem(position).setSpcproDec(proitem);
                    }
                    spUtils.put(context, "commonullspcprodec", nullspcprodec);
                    spUtils.put(context, "CommoditySpcproDec", proitem);//大货特殊工艺情况
                }
            };
            editTextSpcproDec.addTextChangedListener(TvSpcproDec);
            editTextSpcproDec.setTag(TvSpcproDec);
            /*光标放置在文本最后*/
            holder.tvCommoSpcproDec.setSelection(holder.tvCommoSpcproDec.length());


            holder.tvCommoSpcproMemo.setEnabled(true);
            final EditText editTextSpcproMemo = holder.tvCommoSpcproMemo;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproMemo.getTag() instanceof TextWatcher) {
                editTextSpcproMemo.removeTextChangedListener((TextWatcher) editTextSpcproMemo.getTag());
            }
            editTextSpcproMemo.setText(getItem(position).getSpcproMemo());
            TextWatcher TvSpcproMemo = new TextWatcher() {
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
                    String proitem = holder.tvCommoSpcproMemo.getText().toString();
                    String commospcpromemo = getItem(position).getSpcproMemo();
                    if (commospcpromemo == null) {
                        commospcpromemo = "";
                    }
                    String nullspcpromemo;
                    if (commospcpromemo.equals(proitem)) {
                        nullspcpromemo = "1";
                    } else {
                        nullspcpromemo = "2";
                        getItem(position).setSpcproMemo(proitem);
                    }
                    spUtils.put(context, "commonullspcpromemo", nullspcpromemo);
                    spUtils.put(context, "CommoditySpcproMemo", proitem);//特殊工艺特别备注
                }
            };
            editTextSpcproMemo.addTextChangedListener(TvSpcproMemo);
            editTextSpcproMemo.setTag(TvSpcproMemo);
            /*光标放置在文本最后*/
            holder.tvCommoSpcproMemo.setSelection(holder.tvCommoSpcproMemo.length());


            holder.tvCommoCutqty.setEnabled(true);
            final EditText editTextCutqty = holder.tvCommoCutqty;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextCutqty.getTag() instanceof TextWatcher) {
                editTextCutqty.removeTextChangedListener((TextWatcher) editTextCutqty.getTag());
            }
            editTextCutqty.setText(getItem(position).getCutqty());
            TextWatcher TvCutqty = new TextWatcher() {
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
                    String proitem = holder.tvCommoCutqty.getText().toString();
                    String commocutqty = getItem(position).getCutqty();
                    if (commocutqty == null) {
                        commocutqty = "";
                    }
                    String nullcutqty;
                    if (commocutqty.equals(proitem)) {
                        nullcutqty = "1";
                    } else {
                        nullcutqty = "2";
                        getItem(position).setCutqty(proitem);
                    }
                    spUtils.put(context, "commonullcutqty", nullcutqty);
                    spUtils.put(context, "CommodityCutqty", proitem);//实裁数
                }
            };
            editTextCutqty.addTextChangedListener(TvCutqty);
            editTextCutqty.setTag(TvCutqty);
            /*光标放置在文本最后*/
            holder.tvCommoCutqty.setSelection(holder.tvCommoCutqty.length());


            holder.tvCommoSewFdt.setEnabled(true);
            holder.tvCommoSewFdt.setText(getItem(position).getSewFdt());
            holder.tvCommoSewFdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoSewFdt.setText(datetime);
                                    String commosewfdt = getItem(position).getSewFdt();
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals(datetime)) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                        getItem(position).setSewFdt(datetime);
                                    }
                                    spUtils.put(context, "commonullsewfdt", nullsewfdt);
                                    spUtils.put(context, "dateSewFdttimesign", datetime);//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewFdt.setText("");
                                    String commosewfdt = getItem(position).getSewFdt();
                                    if (commosewfdt == null) {
                                        commosewfdt = "";
                                    }
                                    String nullsewfdt;
                                    if (commosewfdt.equals("")) {
                                        nullsewfdt = "1";
                                    } else {
                                        nullsewfdt = "2";
                                        getItem(position).setSewFdt("");
                                    }
                                    spUtils.put(context, "commonullsewfdt", nullsewfdt);
                                    spUtils.put(context, "dateSewFdttimesign", "");//上线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoSewMdt.setEnabled(true);
            holder.tvCommoSewMdt.setText(getItem(position).getSewMdt());
            holder.tvCommoSewMdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoSewMdt.setText(datetime);
                                    String commosewmdt = getItem(position).getSewMdt();
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals(datetime)) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                        getItem(position).setSewMdt(datetime);
                                    }
                                    spUtils.put(context, "commonullsewmdt", nullsewmdt);
                                    spUtils.put(context, "dateSewMdttimesign", datetime);//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewMdt.setText("");
                                    String commosewmdt = getItem(position).getSewMdt();
                                    if (commosewmdt == null) {
                                        commosewmdt = "";
                                    }
                                    String nullsewmdt;
                                    if (commosewmdt.equals("")) {
                                        nullsewmdt = "1";
                                    } else {
                                        nullsewmdt = "2";
                                        getItem(position).setSewMdt("");
                                    }
                                    spUtils.put(context, "commonullsewmdt", nullsewmdt);
                                    spUtils.put(context, "dateSewMdttimesign", "");//下线日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoSubfactory.setEnabled(true);
            holder.tvCommoSubfactory.setText(getItem(position).getSubfactory());
            holder.tvCommoSubfactory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPrebdt.setEnabled(true);
            holder.tvCommoPrebdt.setText(getItem(position).getPrebdt());
            holder.tvCommoPrebdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPrebdt.setText(datetime);
                                    String commoprebdt = getItem(position).getPrebdt();
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals(datetime)) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                        getItem(position).setPrebdt(datetime);
                                    }
                                    spUtils.put(context, "commonullprebdt", nullprebdt);
                                    spUtils.put(context, "datePrebdttimesign", datetime);//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPrebdt.setText("");
                                    String commoprebdt = getItem(position).getPrebdt();
                                    if (commoprebdt == null) {
                                        commoprebdt = "";
                                    }
                                    String nullprebdt;
                                    if (commoprebdt.equals("")) {
                                        nullprebdt = "1";
                                    } else {
                                        nullprebdt = "2";
                                        getItem(position).setPrebdt("");
                                    }
                                    spUtils.put(context, "commonullprebdt", nullprebdt);
                                    spUtils.put(context, "datePrebdttimesign", "");//预计早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoQCbdt.setEnabled(true);
            holder.tvCommoQCbdt.setText(getItem(position).getQCbdt());
            holder.tvCommoQCbdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQCbdt.setText(datetime);
                                    String commoqcbdt = getItem(position).getQCbdt();
                                    if (commoqcbdt == null) {
                                        commoqcbdt = "";
                                    }
                                    String nullqcbdt;
                                    if (commoqcbdt.equals(datetime)) {
                                        nullqcbdt = "1";
                                    } else {
                                        nullqcbdt = "2";
                                        getItem(position).setQCbdt(datetime);
                                    }
                                    spUtils.put(context, "commonullqcbdt", nullqcbdt);
                                    spUtils.put(context, "dateQCbdttimesign", datetime);//自查早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCbdt.setText("");
                                    String commoqcbdt = getItem(position).getQCbdt();
                                    if (commoqcbdt == null) {
                                        commoqcbdt = "";
                                    }
                                    String nullqcbdt;
                                    if (commoqcbdt.equals("")) {
                                        nullqcbdt = "1";
                                    } else {
                                        nullqcbdt = "2";
                                        getItem(position).setQCbdt("");
                                    }
                                    spUtils.put(context, "commonullqcbdt", nullqcbdt);
                                    spUtils.put(context, "dateQCbdttimesign", "");//自查早期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPremdt.setEnabled(true);
            holder.tvCommoPremdt.setText(getItem(position).getPremdt());
            holder.tvCommoPremdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPremdt.setText(datetime);
                                    String commopremdt = getItem(position).getPremdt();
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals(datetime)) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                        getItem(position).setPremdt(datetime);
                                    }
                                    spUtils.put(context, "commonullpremdt", nullpremdt);
                                    spUtils.put(context, "datePremdttimesign", datetime);//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPremdt.setText("");
                                    String commopremdt = getItem(position).getPremdt();
                                    if (commopremdt == null) {
                                        commopremdt = "";
                                    }
                                    String nullpremdt;
                                    if (commopremdt.equals("")) {
                                        nullpremdt = "1";
                                    } else {
                                        nullpremdt = "2";
                                        getItem(position).setPremdt("");
                                    }
                                    spUtils.put(context, "commonullpremdt", nullpremdt);
                                    spUtils.put(context, "datePremdttimesign", "");//预计中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoQCmdt.setEnabled(true);
            holder.tvCommoQCmdt.setText(getItem(position).getQCmdt());
            holder.tvCommoQCmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQCmdt.setText(datetime);
                                    String commoecmdt = getItem(position).getQCmdt();
                                    if (commoecmdt == null) {
                                        commoecmdt = "";
                                    }
                                    String nullqcmdt;
                                    if (commoecmdt.equals(datetime)) {
                                        nullqcmdt = "1";
                                    } else {
                                        nullqcmdt = "2";
                                        getItem(position).setQCmdt(datetime);
                                    }
                                    spUtils.put(context, "commonullqcmdt", nullqcmdt);
                                    spUtils.put(context, "dateQCmdttimesign", datetime);//自查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCmdt.setText("");
                                    String commoecmdt = getItem(position).getQCmdt();
                                    if (commoecmdt == null) {
                                        commoecmdt = "";
                                    }
                                    String nullqcmdt;
                                    if (commoecmdt.equals("")) {
                                        nullqcmdt = "1";
                                    } else {
                                        nullqcmdt = "2";
                                        getItem(position).setQCmdt("");
                                    }
                                    spUtils.put(context, "commonullqcmdt", nullqcmdt);
                                    spUtils.put(context, "dateQCmdttimesign", "");//自查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPreedt.setEnabled(true);
            holder.tvCommoPreedt.setText(getItem(position).getPreedt());
            holder.tvCommoPreedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPreedt.setText(datetime);
                                    String commopreedt = getItem(position).getPreedt();
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals(datetime)) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                        getItem(position).setPreedt(datetime);
                                    }
                                    spUtils.put(context, "commonullpreedt", nullpreedt);
                                    spUtils.put(context, "datePreedttimesign", datetime);//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPreedt.setText("");
                                    String commopreedt = getItem(position).getPreedt();
                                    if (commopreedt == null) {
                                        commopreedt = "";
                                    }
                                    String nullpreedt;
                                    if (commopreedt.equals("")) {
                                        nullpreedt = "1";
                                    } else {
                                        nullpreedt = "2";
                                        getItem(position).setPreedt("");
                                    }
                                    spUtils.put(context, "commonullpreedt", nullpreedt);
                                    spUtils.put(context, "datePreedttimesign", "");//预计尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoQCMedt.setEnabled(true);
            holder.tvCommoQCMedt.setText(getItem(position).getQCMedt());
            holder.tvCommoQCMedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQCMedt.setText(datetime);
                                    String commoqcmedt = getItem(position).getQCMedt();
                                    if (commoqcmedt == null) {
                                        commoqcmedt = "";
                                    }
                                    String nullqcmedt;
                                    if (commoqcmedt.equals(datetime)) {
                                        nullqcmedt = "1";
                                    } else {
                                        nullqcmedt = "2";
                                        getItem(position).setQCMedt(datetime);
                                    }
                                    spUtils.put(context, "commonullqcmedt", nullqcmedt);
                                    spUtils.put(context, "dateQCMedttimesign", datetime);//自查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCMedt.setText("");
                                    String commoqcmedt = getItem(position).getQCMedt();
                                    if (commoqcmedt == null) {
                                        commoqcmedt = "";
                                    }
                                    String nullqcmedt;
                                    if (commoqcmedt.equals("")) {
                                        nullqcmedt = "1";
                                    } else {
                                        nullqcmedt = "2";
                                        getItem(position).setQCMedt("");
                                    }
                                    spUtils.put(context, "commonullqcmedt", nullqcmedt);
                                    spUtils.put(context, "dateQCMedttimesign", "");//自查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoFctmdt.setEnabled(true);
            holder.tvCommoFctmdt.setText(getItem(position).getFctmdt());
            holder.tvCommoFctmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoFctmdt.setText(datetime);
                                    String commofctmdt = getItem(position).getFctmdt();
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals(datetime)) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                        getItem(position).setFctmdt(datetime);
                                    }
                                    spUtils.put(context, "commonullfctmdt", nullfctmdt);
                                    spUtils.put(context, "dateFctmdttimesign", datetime);//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctmdt.setText("");
                                    String commofctmdt = getItem(position).getFctmdt();
                                    if (commofctmdt == null) {
                                        commofctmdt = "";
                                    }
                                    String nullfctmdt;
                                    if (commofctmdt.equals("")) {
                                        nullfctmdt = "1";
                                    } else {
                                        nullfctmdt = "2";
                                        getItem(position).setFctmdt("");
                                    }
                                    spUtils.put(context, "commonullfctmdt", nullfctmdt);
                                    spUtils.put(context, "dateFctmdttimesign", "");//客查中期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoFctedt.setEnabled(true);
            holder.tvCommoFctedt.setText(getItem(position).getFctedt());
            holder.tvCommoFctedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoFctedt.setText(datetime);
                                    String commofctedt = getItem(position).getFctedt();
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals(datetime)) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                        getItem(position).setFctedt(datetime);
                                    }
                                    spUtils.put(context, "commonullfctedt", nullfctedt);
                                    spUtils.put(context, "dateFctedttimesign", datetime);//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctedt.setText("");
                                    String commofctedt = getItem(position).getFctedt();
                                    if (commofctedt == null) {
                                        commofctedt = "";
                                    }
                                    String nullfctedt;
                                    if (commofctedt.equals("")) {
                                        nullfctedt = "1";
                                    } else {
                                        nullfctedt = "2";
                                        getItem(position).setFctedt("");
                                    }
                                    spUtils.put(context, "commonullfctedt", nullfctedt);
                                    spUtils.put(context, "dateFctedttimesign", "");//客查尾期时间
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoPackbdat.setEnabled(true);
            holder.tvCommoPackbdat.setText(getItem(position).getPackbdat());
            holder.tvCommoPackbdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoPackbdat.setText(datetime);
                                    String commopackbdat = getItem(position).getPackbdat();
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals(datetime)) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                        getItem(position).setPackbdat(datetime);
                                    }
                                    spUtils.put(context, "commonullpackbdat", nullpackbdat);
                                    spUtils.put(context, "datePackbdattimesign", datetime);//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPackbdat.setText("");
                                    String commopackbdat = getItem(position).getPackbdat();
                                    if (commopackbdat == null) {
                                        commopackbdat = "";
                                    }
                                    String nullpackbdat;
                                    if (commopackbdat.equals("")) {
                                        nullpackbdat = "1";
                                    } else {
                                        nullpackbdat = "2";
                                        getItem(position).setPackbdat("");
                                    }
                                    spUtils.put(context, "commonullpackbdat", nullpackbdat);
                                    spUtils.put(context, "datePackbdattimesign", "");//成品包装开始日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPackqty2.setEnabled(true);
            final EditText editTextPackqty2 = holder.tvCommoPackqty2;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPackqty2.getTag() instanceof TextWatcher) {
                editTextPackqty2.removeTextChangedListener((TextWatcher) editTextPackqty2.getTag());
            }
            editTextPackqty2.setText(getItem(position).getPackqty2());
            TextWatcher TvPackqty2 = new TextWatcher() {
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
                    String proitem = holder.tvCommoPackqty2.getText().toString();
                    String commopackqty2 = getItem(position).getPackqty2();
                    if (commopackqty2 == null) {
                        commopackqty2 = "";
                    }
                    String nullpackqty2;
                    if (commopackqty2.equals(proitem)) {
                        nullpackqty2 = "1";
                    } else {
                        nullpackqty2 = "2";
                        getItem(position).setPackqty2(proitem);
                    }
                    spUtils.put(context, "commonullpackqty2", nullpackqty2);
                    spUtils.put(context, "CommodityPackqty2", proitem);//装箱数量
                }
            };
            editTextPackqty2.addTextChangedListener(TvPackqty2);
            editTextPackqty2.setTag(TvPackqty2);
            /*光标放置在文本最后*/
            holder.tvCommoPackqty2.setSelection(holder.tvCommoPackqty2.length());


            holder.tvCommoQCMemo.setEnabled(true);
            final EditText editTextQCMemo = holder.tvCommoQCMemo;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMemo.getTag() instanceof TextWatcher) {
                editTextQCMemo.removeTextChangedListener((TextWatcher) editTextQCMemo.getTag());
            }
            editTextQCMemo.setText(getItem(position).getQCMemo());
            TextWatcher TvQCMemo = new TextWatcher() {
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
                    String proitem = holder.tvCommoQCMemo.getText().toString();
                    String commoqcmemo = getItem(position).getQCMemo();
                    if (commoqcmemo == null) {
                        commoqcmemo = "";
                    }
                    String nullqcmemo;
                    if (commoqcmemo.equals(proitem)) {
                        nullqcmemo = "1";
                    } else {
                        nullqcmemo = "2";
                        getItem(position).setQCMemo(proitem);
                    }
                    spUtils.put(context, "commonullqcmemo", nullqcmemo);
                    spUtils.put(context, "CommodityQCMemo", proitem);//QC特别备注
                }
            };
            editTextQCMemo.addTextChangedListener(TvQCMemo);
            editTextQCMemo.setTag(TvQCMemo);
            /*光标放置在文本最后*/
            holder.tvCommoQCMemo.setSelection(holder.tvCommoQCMemo.length());

            holder.tvCommoFactlcdat.setEnabled(true);
            holder.tvCommoFactlcdat.setText(getItem(position).getFactlcdat());
            holder.tvCommoFactlcdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoFactlcdat.setText(datetime);
                                    String commofactlcdat = getItem(position).getFactlcdat();
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals(datetime)) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                        getItem(position).setFactlcdat(datetime);
                                    }
                                    spUtils.put(context, "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(context, "dateFactlcdattimesign", datetime);//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFactlcdat.setText("");
                                    String commofactlcdat = getItem(position).getFactlcdat();
                                    if (commofactlcdat == null) {
                                        commofactlcdat = "";
                                    }
                                    String nullfactlcdat;
                                    if (commofactlcdat.equals("")) {
                                        nullfactlcdat = "1";
                                    } else {
                                        nullfactlcdat = "2";
                                        getItem(position).setFactlcdat("");
                                    }
                                    spUtils.put(context, "commonullfactlcdat", nullfactlcdat);
                                    spUtils.put(context, "dateFactlcdattimesign", "");//离厂日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });

            holder.tvCommoBatchid.setEnabled(true);
            final EditText editTextBatchid = holder.tvCommoBatchid;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextBatchid.getTag() instanceof TextWatcher) {
                editTextBatchid.removeTextChangedListener((TextWatcher) editTextBatchid.getTag());
            }
            editTextBatchid.setText(getItem(position).getBatchid());
            TextWatcher TvBatchid = new TextWatcher() {
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
                    String proitem = holder.tvCommoBatchid.getText().toString();
                    String commoBatchid = getItem(position).getBatchid();
                    if (commoBatchid == null) {
                        commoBatchid = "";
                    }
                    String nullBatchid;
                    if (commoBatchid.equals(proitem)) {
                        nullBatchid = "1";
                    } else {
                        nullBatchid = "2";
                        getItem(position).setBatchid(proitem);
                    }
                    spUtils.put(context, "commonullBatchid", nullBatchid);
                    spUtils.put(context, "CommodityBatchid", proitem);//查货批次
                }
            };
            editTextBatchid.addTextChangedListener(TvBatchid);
            editTextBatchid.setTag(TvBatchid);
            /*光标放置在文本最后*/
            holder.tvCommoBatchid.setSelection(holder.tvCommoBatchid.length());

            holder.tvCommoOurAfter.setEnabled(true);

            holder.tvCommoCtmchkdt.setEnabled(true);
            holder.tvCommoCtmchkdt.setText(getItem(position).getCtmchkdt());
            holder.tvCommoCtmchkdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoCtmchkdt.setText(datetime);
                                    String commoCtmchkdt = getItem(position).getCtmchkdt();
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals(datetime)) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                        getItem(position).setCtmchkdt(datetime);
                                    }
                                    spUtils.put(context, "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(context, "dateCtmchkdttimesign", datetime);//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoCtmchkdt.setText("");
                                    String commoCtmchkdt = getItem(position).getCtmchkdt();
                                    if (commoCtmchkdt == null) {
                                        commoCtmchkdt = "";
                                    }
                                    String nullCtmchkdt;
                                    if (commoCtmchkdt.equals("")) {
                                        nullCtmchkdt = "1";
                                    } else {
                                        nullCtmchkdt = "2";
                                        getItem(position).setCtmchkdt("");
                                    }
                                    spUtils.put(context, "commonullCtmchkdt", nullCtmchkdt);
                                    spUtils.put(context, "dateCtmchkdttimesign", "");//业务员确认客查日期
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoIPQCPedt.setEnabled(true);
            final EditText editTextIPQCPedt = holder.tvCommoIPQCPedt;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCPedt.getTag() instanceof TextWatcher) {
                editTextIPQCPedt.removeTextChangedListener((TextWatcher) editTextIPQCPedt.getTag());
            }
            editTextIPQCPedt.setText(getItem(position).getIPQCPedt());
            TextWatcher TvIPQCPedt = new TextWatcher() {
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
                    String proitem = holder.tvCommoIPQCPedt.getText().toString();
                    String commoipqcpedt = getItem(position).getIPQCPedt();
                    if (commoipqcpedt == null) {
                        commoipqcpedt = "";
                    }
                    String nullipqcpedt;
                    if (commoipqcpedt.equals(proitem)) {
                        nullipqcpedt = "1";
                    } else {
                        nullipqcpedt = "2";
                        getItem(position).setIPQCPedt(proitem);
                    }
                    spUtils.put(context, "commonullipqcpedt", nullipqcpedt);
                    spUtils.put(context, "CommodityIPQCPedt", proitem);//尾查预查
                }
            };
            editTextIPQCPedt.addTextChangedListener(TvIPQCPedt);
            editTextIPQCPedt.setTag(TvIPQCPedt);
            /*光标放置在文本最后*/
            holder.tvCommoIPQCPedt.setSelection(holder.tvCommoIPQCPedt.length());


            holder.tvCommoIPQCmdt.setEnabled(true);
            final EditText editTextIPQCmdt = holder.tvCommoIPQCmdt;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCmdt.getTag() instanceof TextWatcher) {
                editTextIPQCmdt.removeTextChangedListener((TextWatcher) editTextIPQCmdt.getTag());
            }
            editTextIPQCmdt.setText(getItem(position).getIPQCmdt());
            TextWatcher TvIPQCmdt = new TextWatcher() {
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
                    String proitem = holder.tvCommoIPQCmdt.getText().toString();
                    String commoipqcmdt = getItem(position).getIPQCmdt();
                    if (commoipqcmdt == null) {
                        commoipqcmdt = "";
                    }
                    String nullipqcmdt;
                    if (commoipqcmdt.equals(proitem)) {
                        nullipqcmdt = "1";
                    } else {
                        nullipqcmdt = "2";
                        getItem(position).setIPQCmdt(proitem);
                    }
                    spUtils.put(context, "commonullipqcmdt", nullipqcmdt);
                    spUtils.put(context, "CommodityIPQCmdt", proitem);//巡检中查
                }
            };
            editTextIPQCmdt.addTextChangedListener(TvIPQCmdt);
            editTextIPQCmdt.setTag(TvIPQCmdt);
            /*光标放置在文本最后*/
            holder.tvCommoIPQCmdt.setSelection(holder.tvCommoIPQCmdt.length());


            holder.tvCommoQAname.setEnabled(true);
            final EditText editTextQAname = holder.tvCommoQAname;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAname.getTag() instanceof TextWatcher) {
                editTextQAname.removeTextChangedListener((TextWatcher) editTextQAname.getTag());
            }
            editTextQAname.setText(getItem(position).getFirstsamQA());
            TextWatcher TvQAname = new TextWatcher() {
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
                    String proitem = holder.tvCommoQAname.getText().toString();
                    String commoQaname = getItem(position).getQAname();
                    if (commoQaname == null) {
                        commoQaname = "";
                    }
                    String nullqaname;
                    if (commoQaname.equals(proitem)) {
                        nullqaname = "1";
                    } else {
                        nullqaname = "2";
                        getItem(position).setQAname(proitem);
                    }
                    spUtils.put(context, "commonullqaname", nullqaname);
                    spUtils.put(context, "CommodityQAname", proitem);//QA首扎
                }
            };
            editTextQAname.addTextChangedListener(TvQAname);
            editTextQAname.setTag(TvQAname);
            /*光标放置在文本最后*/
            holder.tvCommoQAname.setSelection(holder.tvCommoQAname.length());


            holder.tvCommoQAScore.setEnabled(true);
            final EditText editTextQAScore = holder.tvCommoQAScore;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAScore.getTag() instanceof TextWatcher) {
                editTextQAScore.removeTextChangedListener((TextWatcher) editTextQAScore.getTag());
            }
            editTextQAScore.setText(getItem(position).getQAScore());
            TextWatcher TvQAScore = new TextWatcher() {
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
                    String proitem = holder.tvCommoQAScore.getText().toString();
                    String commoqascore = getItem(position).getQAScore();
                    if (commoqascore == null) {
                        commoqascore = "";
                    }
                    String nullqascore;
                    if (commoqascore.equals(proitem)) {
                        nullqascore = "1";
                    } else {
                        nullqascore = "2";
                        getItem(position).setQAScore(proitem);
                    }
                    spUtils.put(context, "commonullqascore", nullqascore);
                    spUtils.put(context, "CommodityQAScore", proitem);//QA首扎件数
                }
            };
            editTextQAScore.addTextChangedListener(TvQAScore);
            editTextQAScore.setTag(TvQAScore);
            /*光标放置在文本最后*/
            holder.tvCommoQAScore.setSelection(holder.tvCommoQAScore.length());


            holder.tvCommoQAMemo.setEnabled(true);
            holder.tvCommoQAMemo.setText(getItem(position).getQAMemo());
            holder.tvCommoQAMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(
                            context, null, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );
                    datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                            , "完成", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatePicker datePicker = datePickerDialog.getDatePicker();
                                    int year = datePicker.getYear();
                                    int month = datePicker.getMonth();
                                    int day = datePicker.getDayOfMonth();
                                    String datetime = year + "/" + (month + 1) + "/" + day;
                                    holder.tvCommoQAMemo.setText(datetime);
                                    String commoqamemo = getItem(position).getQAMemo();
                                    if(commoqamemo==null){
                                        commoqamemo="";
                                    }
                                    String nullqamemo;
                                    if(commoqamemo.equals(datetime)){
                                        nullqamemo="1";
                                    }else{
                                        nullqamemo="2";
                                        getItem(position).setQAMemo(datetime);
                                    }
                                    spUtils.put(context,"commonullqamemo",nullqamemo);
                                    spUtils.put(context, "dateQAMemotimesign", datetime);//QA首扎日
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQAMemo.setText("");
                                    String commoqamemo = getItem(position).getQAMemo();
                                    if(commoqamemo==null){
                                        commoqamemo="";
                                    }
                                    String nullqamemo;
                                    if(commoqamemo.equals("")){
                                        nullqamemo="1";
                                    }else{
                                        nullqamemo="2";
                                        getItem(position).setQAMemo("");
                                    }
                                    spUtils.put(context,"commonullqamemo",nullqamemo);
                                    spUtils.put(context, "dateQAMemotimesign", "");//QA首扎日
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                            , "取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    datePickerDialog.show();
                }
            });


            holder.tvCommoPredoc.setEnabled(true);
            final EditText editTextPredoc = holder.tvCommoPredoc;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPredoc.getTag() instanceof TextWatcher) {
                editTextPredoc.removeTextChangedListener((TextWatcher) editTextPredoc.getTag());
            }
            editTextPredoc.setText(getItem(position).getPredoc());
            TextWatcher TvPredoc = new TextWatcher() {
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
                    String proitem = holder.tvCommoPredoc.getText().toString();
                    String commopredoc = getItem(position).getPredoc();
                    if (commopredoc == null) {
                        commopredoc = "";
                    }
                    String nullpredoc;
                    if (commopredoc.equals(proitem)) {
                        nullpredoc = "1";
                    } else {
                        nullpredoc = "2";
                        getItem(position).setPredoc(proitem);
                    }
                    spUtils.put(context, "commonullpredoc", nullpredoc);
                    spUtils.put(context, "CommodityPredoc", proitem);//产前会报告
                }
            };
            editTextPredoc.addTextChangedListener(TvPredoc);
            editTextPredoc.setTag(TvPredoc);
            /*光标放置在文本最后*/
            holder.tvCommoPredoc.setSelection(holder.tvCommoPredoc.length());


            holder.tvCommoQCbdtDoc.setEnabled(true);//早期报告
            final EditText editTextQCbdtDoc = holder.tvCommoQCbdtDoc;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCbdtDoc.getTag() instanceof TextWatcher) {
                editTextQCbdtDoc.removeTextChangedListener((TextWatcher) editTextQCbdtDoc.getTag());
            }
            editTextQCbdtDoc.setText(getItem(position).getQCbdtDoc());
            TextWatcher TvQCbdtDoc = new TextWatcher() {
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
                    String proitem = holder.tvCommoQCbdtDoc.getText().toString();
                    String commoqcbdtdoc = getItem(position).getQCbdtDoc();
                    if (commoqcbdtdoc == null) {
                        commoqcbdtdoc = "";
                    }
                    String nullqcbdtdoc;
                    if (commoqcbdtdoc.equals(proitem)) {
                        nullqcbdtdoc = "1";
                    } else {
                        nullqcbdtdoc = "2";
                        getItem(position).setQCbdtDoc(proitem);
                    }
                    spUtils.put(context, "commonullqcbdtdoc", nullqcbdtdoc);
                    spUtils.put(context, "Commodityqcbdtdoc", proitem);//早期报告
                }
            };
            editTextQCbdtDoc.addTextChangedListener(TvQCbdtDoc);
            editTextQCbdtDoc.setTag(TvQCbdtDoc);
            /*光标放置在文本最后*/
            holder.tvCommoQCbdtDoc.setSelection(holder.tvCommoQCbdtDoc.length());


            holder.tvCommoQCmdtDoc.setEnabled(true);//中期报告
            final EditText editTextQCmdtDoc = holder.tvCommoQCmdtDoc;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCmdtDoc.getTag() instanceof TextWatcher) {
                editTextQCmdtDoc.removeTextChangedListener((TextWatcher) editTextQCmdtDoc.getTag());
            }
            editTextQCmdtDoc.setText(getItem(position).getQCmdtDoc());
            TextWatcher TvQCmdtDoc = new TextWatcher() {
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
                    String proitem = holder.tvCommoQCmdtDoc.getText().toString();
                    String commoqcmdtdoc = getItem(position).getQCmdtDoc();
                    if (commoqcmdtdoc == null) {
                        commoqcmdtdoc = "";
                    }
                    String nullqcmdtdoc;
                    if (commoqcmdtdoc.equals(proitem)) {
                        nullqcmdtdoc = "1";
                    } else {
                        nullqcmdtdoc = "2";
                        getItem(position).setQCmdtDoc(proitem);
                    }
                    spUtils.put(context, "commonullqcmdtdoc", nullqcmdtdoc);
                    spUtils.put(context, "CommodityQCmdtDoc", proitem);//中期报告
                }
            };
            editTextQCmdtDoc.addTextChangedListener(TvQCmdtDoc);
            editTextQCmdtDoc.setTag(TvQCmdtDoc);
            /*光标放置在文本最后*/
            holder.tvCommoQCmdtDoc.setSelection(holder.tvCommoQCmdtDoc.length());


            holder.tvCommoQCedtDoc.setEnabled(true);//尾期报告
            final EditText editTextQCedtDoc = holder.tvCommoQCedtDoc;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCedtDoc.getTag() instanceof TextWatcher) {
                editTextQCedtDoc.removeTextChangedListener((TextWatcher) editTextQCedtDoc.getTag());
            }
            editTextQCedtDoc.setText(getItem(position).getQCedtDoc());
            TextWatcher TvQCedtDoc = new TextWatcher() {
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
                    String proitem = holder.tvCommoQCedtDoc.getText().toString();
                    String commoqcedtdoc = getItem(position).getQCedtDoc();
                    if (commoqcedtdoc == null) {
                        commoqcedtdoc = "";
                    }
                    String nullqcedtdoc;
                    if (commoqcedtdoc.equals(proitem)) {
                        nullqcedtdoc = "1";
                    } else {
                        nullqcedtdoc = "2";
                        getItem(position).setQCedtDoc(proitem);
                    }
                    spUtils.put(context, "commonullqcedtdoc", nullqcedtdoc);
                    spUtils.put(context, "CommodityQCedtDoc", proitem);//尾查报告
                }
            };
            editTextQCedtDoc.addTextChangedListener(TvQCedtDoc);
            editTextQCedtDoc.setTag(TvQCedtDoc);
            /*光标放置在文本最后*/
            holder.tvCommoQCedtDoc.setSelection(holder.tvCommoQCedtDoc.length());


            holder.tvCommoThing.setEnabled(false);
            final EditText editTextThing = holder.tvCommoThing;
            if (editTextThing.getTag() instanceof TextWatcher) {
                editTextThing.removeTextChangedListener((TextWatcher) editTextThing.getTag());
            }
            editTextThing.setText(getItem(position).getChker());

            holder.tvCommoThingAddress.setEnabled(false);
            final EditText editTextThingAddress = holder.tvCommoThingAddress;
            if (editTextThingAddress.getTag() instanceof TextWatcher) {
                editTextThingAddress.removeTextChangedListener((TextWatcher) editTextThingAddress.getTag());
            }
            editTextThingAddress.setText(getItem(position).getChkplace());

            holder.tvCommoThingExpectedTime.setEnabled(false);
            holder.tvCommoThingExpectedTime.setText(getItem(position).getChkpdt());
            holder.tvCommoThingExpectedTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoThingTime.setEnabled(false);
            holder.tvCommoThingTime.setText(getItem(position).getChkfctdt());
            holder.tvCommoThingTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else {

            holder.tvCommoCtmtxt.setEnabled(false);
            holder.tvCommoCtmtxt.setText(getItem(position).getCtmtxt());
            holder.tvCommoCtmtxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPrddocumentary.setEnabled(false);
            holder.tvCommoPrddocumentary.setText(getItem(position).getPrddocumentary());
            holder.tvCommoPrddocumentary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoprdmaster.setEnabled(false);
            holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());
            holder.tvCommoprdmaster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQCMasterScore.setEnabled(false);
            final EditText editTextQCMasterScore = holder.tvCommoQCMasterScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMasterScore.getTag() instanceof TextWatcher) {
                editTextQCMasterScore.removeTextChangedListener((TextWatcher) editTextQCMasterScore.getTag());
            }
            editTextQCMasterScore.setText(getItem(position).getQCMasterScore());
            holder.tvCommoQCMasterScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoSealedrev.setEnabled(false);
            holder.tvCommoSealedrev.setText(getItem(position).getSealedrev());
            holder.tvCommoSealedrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoDocback.setEnabled(false);
            holder.tvCommoDocback.setText(getItem(position).getDocback());
            holder.tvCommoDocback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoLcdat.setEnabled(false);
            holder.tvCommoLcdat.setText(getItem(position).getLcdat());
            holder.tvCommoLcdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoTaskqty.setEnabled(false);
            holder.tvCommoTaskqty.setText(getItem(position).getTaskqty());
            holder.tvCommoTaskqty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPreMemo.setEnabled(false);
            final EditText editTextPreMemo = holder.tvCommoPreMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPreMemo.getTag() instanceof TextWatcher) {
                editTextPreMemo.removeTextChangedListener((TextWatcher) editTextPreMemo.getTag());
            }
            editTextPreMemo.setText(getItem(position).getPreMemo());
            holder.tvCommoPreMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPredocdt.setEnabled(false);
            holder.tvCommoPredocdt.setText(getItem(position).getPredocdt());
            holder.tvCommoPredocdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPred.setEnabled(false);
            holder.tvCommoPred.setText(getItem(position).getPredt());
            holder.tvCommoPred.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPredoc.setEnabled(false);
            final EditText editTextPredoc = holder.tvCommoPredoc;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPredoc.getTag() instanceof TextWatcher) {
                editTextPredoc.removeTextChangedListener((TextWatcher) editTextPredoc.getTag());
            }
            editTextPredoc.setText(getItem(position).getPredoc());
            holder.tvCommoPredoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoFabricsok.setEnabled(false);
            final EditText editTextFabricsok = holder.tvCommoFabricsok;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFabricsok.getTag() instanceof TextWatcher) {
                editTextFabricsok.removeTextChangedListener((TextWatcher) editTextFabricsok.getTag());
            }
            editTextFabricsok.setText(getItem(position).getFabricsok());
            holder.tvCommoFabricsok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoAccessoriesok.setEnabled(false);
            final EditText editTextAccessoriesok = holder.tvCommoAccessoriesok;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextAccessoriesok.getTag() instanceof TextWatcher) {
                editTextAccessoriesok.removeTextChangedListener((TextWatcher) editTextAccessoriesok.getTag());
            }
            editTextAccessoriesok.setText(getItem(position).getAccessoriesok());
            holder.tvCommoAccessoriesok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoSpcproDec.setEnabled(false);
            final EditText editTextSpcproDec = holder.tvCommoSpcproDec;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproDec.getTag() instanceof TextWatcher) {
                editTextSpcproDec.removeTextChangedListener((TextWatcher) editTextSpcproDec.getTag());
            }
            editTextSpcproDec.setText(getItem(position).getSpcproDec());
            holder.tvCommoSpcproDec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoSpcproMemo.setEnabled(false);
            final EditText editTextSpcproMemo = holder.tvCommoSpcproMemo;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproMemo.getTag() instanceof TextWatcher) {
                editTextSpcproMemo.removeTextChangedListener((TextWatcher) editTextSpcproMemo.getTag());
            }
            editTextSpcproMemo.setText(getItem(position).getSpcproMemo());
            holder.tvCommoSpcproMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoCutqty.setEnabled(false);
            final EditText editTextCutqty = holder.tvCommoCutqty;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextCutqty.getTag() instanceof TextWatcher) {
                editTextCutqty.removeTextChangedListener((TextWatcher) editTextCutqty.getTag());
            }
            editTextCutqty.setText(getItem(position).getCutqty());
            holder.tvCommoCutqty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoSewFdt.setEnabled(false);
            holder.tvCommoSewFdt.setText(getItem(position).getSewFdt());
            holder.tvCommoSewFdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoSewMdt.setEnabled(false);
            holder.tvCommoSewMdt.setText(getItem(position).getSewMdt());
            holder.tvCommoSewMdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoSubfactory.setEnabled(false);
            holder.tvCommoSubfactory.setText(getItem(position).getSubfactory());
            holder.tvCommoSubfactory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPrebdt.setEnabled(false);
            holder.tvCommoPrebdt.setText(getItem(position).getPrebdt());
            holder.tvCommoPrebdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQCbdt.setEnabled(false);
            holder.tvCommoQCbdt.setText(getItem(position).getQCbdt());
            holder.tvCommoQCbdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQCbdtDoc.setEnabled(false);
            final EditText editTextQCbdtDoc = holder.tvCommoQCbdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCbdtDoc.getTag() instanceof TextWatcher) {
                editTextQCbdtDoc.removeTextChangedListener((TextWatcher) editTextQCbdtDoc.getTag());
            }
            editTextQCbdtDoc.setText(getItem(position).getQCbdtDoc());
            holder.tvCommoQCbdtDoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPremdt.setEnabled(false);
            holder.tvCommoPremdt.setText(getItem(position).getPremdt());
            holder.tvCommoPremdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQCmdt.setEnabled(false);
            holder.tvCommoQCmdt.setText(getItem(position).getQCmdt());
            holder.tvCommoQCmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQCmdtDoc.setEnabled(false);
            final EditText editTextQCmdtDoc = holder.tvCommoQCmdtDoc;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCmdtDoc.getTag() instanceof TextWatcher) {
                editTextQCmdtDoc.removeTextChangedListener((TextWatcher) editTextQCmdtDoc.getTag());
            }
            editTextQCmdtDoc.setText(getItem(position).getQCmdtDoc());

            holder.tvCommoPreedt.setEnabled(false);
            holder.tvCommoPreedt.setText(getItem(position).getPreedt());
            holder.tvCommoPreedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoThing.setEnabled(false);
            final EditText editTextThing = holder.tvCommoThing;
            if (editTextThing.getTag() instanceof TextWatcher) {
                editTextThing.removeTextChangedListener((TextWatcher) editTextThing.getTag());
            }
            editTextThing.setText(getItem(position).getChker());

            holder.tvCommoThingAddress.setEnabled(false);
            final EditText editTextThingAddress = holder.tvCommoThingAddress;
            if (editTextThingAddress.getTag() instanceof TextWatcher) {
                editTextThingAddress.removeTextChangedListener((TextWatcher) editTextThingAddress.getTag());
            }
            editTextThingAddress.setText(getItem(position).getChkplace());

            holder.tvCommoThingExpectedTime.setEnabled(false);
            holder.tvCommoThingExpectedTime.setText(getItem(position).getChkpdt());
            holder.tvCommoThingExpectedTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoThingTime.setEnabled(false);
            holder.tvCommoThingTime.setText(getItem(position).getChkfctdt());
            holder.tvCommoThingTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQCMedt.setEnabled(false);
            holder.tvCommoQCMedt.setText(getItem(position).getQCMedt());
            holder.tvCommoQCMedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQCedtDoc.setEnabled(false);
            final EditText editTextQCedtDoc = holder.tvCommoQCedtDoc;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCedtDoc.getTag() instanceof TextWatcher) {
                editTextQCedtDoc.removeTextChangedListener((TextWatcher) editTextQCedtDoc.getTag());
            }
            editTextQCedtDoc.setText(getItem(position).getQCedtDoc());

            holder.tvCommoFctmdt.setEnabled(false);
            holder.tvCommoFctmdt.setText(getItem(position).getFctmdt());
            holder.tvCommoFctmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoFctedt.setEnabled(false);
            holder.tvCommoFctedt.setText(getItem(position).getFctedt());
            holder.tvCommoFctedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPackbdat.setEnabled(false);
            holder.tvCommoPackbdat.setText(getItem(position).getPackbdat());
            holder.tvCommoPackbdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoPackqty2.setEnabled(false);
            final EditText editTextPackqty2 = holder.tvCommoPackqty2;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPackqty2.getTag() instanceof TextWatcher) {
                editTextPackqty2.removeTextChangedListener((TextWatcher) editTextPackqty2.getTag());
            }
            editTextPackqty2.setText(getItem(position).getPackqty2());

            holder.tvCommoQCMemo.setEnabled(false);
            final EditText editTextQCMemo = holder.tvCommoQCMemo;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMemo.getTag() instanceof TextWatcher) {
                editTextQCMemo.removeTextChangedListener((TextWatcher) editTextQCMemo.getTag());
            }
            editTextQCMemo.setText(getItem(position).getQCMemo());

            holder.tvCommoFactlcdat.setEnabled(false);
            holder.tvCommoFactlcdat.setText(getItem(position).getFactlcdat());
            holder.tvCommoFactlcdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoBatchid.setEnabled(false);
            final EditText editTextBatchid = holder.tvCommoBatchid;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextBatchid.getTag() instanceof TextWatcher) {
                editTextBatchid.removeTextChangedListener((TextWatcher) editTextBatchid.getTag());
            }
            editTextBatchid.setText(getItem(position).getBatchid());
            holder.tvCommoBatchid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoOurAfter.setEnabled(false);
            holder.tvCommoOurAfter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoCtmchkdt.setEnabled(false);
            holder.tvCommoCtmchkdt.setText(getItem(position).getCtmchkdt());
            holder.tvCommoCtmchkdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoIPQCPedt.setEnabled(false);
            final EditText editTextIPQCPedt = holder.tvCommoIPQCPedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCPedt.getTag() instanceof TextWatcher) {
                editTextIPQCPedt.removeTextChangedListener((TextWatcher) editTextIPQCPedt.getTag());
            }
            editTextIPQCPedt.setText(getItem(position).getIPQCPedt());
            holder.tvCommoIPQCPedt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoIPQCmdt.setEnabled(false);
            final EditText editTextIPQCmdt = holder.tvCommoIPQCmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCmdt.getTag() instanceof TextWatcher) {
                editTextIPQCmdt.removeTextChangedListener((TextWatcher) editTextIPQCmdt.getTag());
            }
            editTextIPQCmdt.setText(getItem(position).getIPQCmdt());
            holder.tvCommoIPQCmdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQAname.setEnabled(false);
            final EditText editTextQAname = holder.tvCommoQAname;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAname.getTag() instanceof TextWatcher) {
                editTextQAname.removeTextChangedListener((TextWatcher) editTextQAname.getTag());
            }
            editTextQAname.setText(getItem(position).getFirstsamQA());
            holder.tvCommoQAname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQAScore.setEnabled(false);
            final EditText editTextQAScore = holder.tvCommoQAScore;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAScore.getTag() instanceof TextWatcher) {
                editTextQAScore.removeTextChangedListener((TextWatcher) editTextQAScore.getTag());
            }
            editTextQAScore.setText(getItem(position).getQAScore());
            holder.tvCommoQAScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.tvCommoQAMemo.setEnabled(false);
            holder.tvCommoQAMemo.setText(getItem(position).getQAMemo());
            holder.tvCommoQAMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return convertView;
    }

    public View.OnClickListener myOnclicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ss = sp.getString("strposition", "");
        }
    };

    class ViewHolder {
        LinearLayout lin_content;
        TextView tvCommoOurAfter, tvCommoItem, tvCommoCtmtxt, tvCommoQAMemo,
                tvCommoPrddocumentary, tvCommoprdmaster, tvCommoSealedrev,
                tvCommoDocback, tvCommoLcdat, tvCommoTaskqty,
                tvCommoPredocdt, tvCommoPred, tvCommoSewFdt,
                tvCommoSewMdt, tvCommoSubfactory, tvCommoPrebdt,
                tvCommoQCbdt, tvCommoPremdt, tvCommoQCmdt, tvCommoPreedt,
                tvCommoQCMedt, tvCommoFctmdt, tvCommoFctedt, tvCommoPackbdat,
                tvCommoFactlcdat, tvCommoCtmchkdt, tvCommoThingExpectedTime,
                tvCommoThingTime;
        EditText
                tvCommoQCMasterScore, tvCommoPreMemo,
                tvCommoPredoc, tvCommoFabricsok, tvCommoAccessoriesok,
                tvCommoSpcproDec, tvCommoSpcproMemo, tvCommoCutqty,
                tvCommoQCbdtDoc, tvCommoQCmdtDoc, tvCommoQCedtDoc,
                tvCommoPackqty2, tvCommoQCMemo, tvCommoBatchid,
                tvCommoIPQCPedt, tvCommoIPQCmdt, tvCommoQAname, tvCommoQAScore,
                tvCommoThing, tvCommoThingAddress;
    }
}