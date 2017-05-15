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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.commo.CommoditySqlActivity;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;

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
    private int index = -1;

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
            holder.tvCommoItem = (TextView) convertView.findViewById(R.id.tvCommoItem);
            holder.tvCommoCtmtxt = (TextView) convertView.findViewById(R.id.tvCommoCtmtxt);
            holder.tvCommoPrddocumentary = (TextView) convertView.findViewById(R.id.tvCommoPrddocumentary);
            holder.tvCommoprdmaster = (TextView) convertView.findViewById(R.id.tvCommoprdmaster);
            holder.tvCommoQCMasterScore = (EditText) convertView.findViewById(R.id.tvCommoQCMasterScore);
            holder.tvCommoSealedrev = (TextView) convertView.findViewById(R.id.tvCommoSealedrev);
            holder.tvCommoDocback = (TextView) convertView.findViewById(R.id.tvCommoDocback);
            holder.tvCommoLcdat = (TextView) convertView.findViewById(R.id.tvCommoLcdat);
            holder.tvCommoTaskqty = (TextView) convertView.findViewById(R.id.tvCommoTaskqty);
            holder.tvCommoPreMemo = (EditText) convertView.findViewById(R.id.tvCommoPreMemo);
            holder.tvCommoPredocdt = (TextView) convertView.findViewById(R.id.tvCommoPredocdt);
            holder.tvCommoPred = (TextView) convertView.findViewById(R.id.tvCommoPred);
            holder.tvCommoPredoc = (EditText) convertView.findViewById(R.id.tvCommoPredoc);
            holder.tvCommoFabricsok = (EditText) convertView.findViewById(R.id.tvCommoFabricsok);
            holder.tvCommoAccessoriesok = (EditText) convertView.findViewById(R.id.tvCommoAccessoriesok);
            holder.tvCommoSpcproDec = (EditText) convertView.findViewById(R.id.tvCommoSpcproDec);
            holder.tvCommoSpcproMemo = (EditText) convertView.findViewById(R.id.tvCommoSpcproMemo);
            holder.tvCommoCutqty = (EditText) convertView.findViewById(R.id.tvCommoCutqty);
            holder.tvCommoSewFdt = (TextView) convertView.findViewById(R.id.tvCommoSewFdt);
            holder.tvCommoSewMdt = (TextView) convertView.findViewById(R.id.tvCommoSewMdt);
            holder.tvCommoSubfactory = (TextView) convertView.findViewById(R.id.tvCommoSubfactory);
            holder.tvCommoPrebdt = (TextView) convertView.findViewById(R.id.tvCommoPrebdt);
            holder.tvCommoQCbdt = (TextView) convertView.findViewById(R.id.tvCommoQCbdt);
            holder.tvCommoQCbdtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCbdtDoc);
            holder.tvCommoPremdt = (TextView) convertView.findViewById(R.id.tvCommoPremdt);
            holder.tvCommoQCmdt = (TextView) convertView.findViewById(R.id.tvCommoQCmdt);
            holder.tvCommoQCmdtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCmdtDoc);
            holder.tvCommoPreedt = (TextView) convertView.findViewById(R.id.tvCommoPreedt);
            holder.tvCommoQCMedt = (TextView) convertView.findViewById(R.id.tvCommoQCMedt);
            holder.tvCommoQCedtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCedtDoc);
            holder.tvCommoFctmdt = (TextView) convertView.findViewById(R.id.tvCommoFctmdt);
            holder.tvCommoFctedt = (TextView) convertView.findViewById(R.id.tvCommoFctedt);
            holder.tvCommoPackbdat = (TextView) convertView.findViewById(R.id.tvCommoPackbdat);
            holder.tvCommoPackqty2 = (EditText) convertView.findViewById(R.id.tvCommoPackqty2);
            holder.tvCommoQCMemo = (EditText) convertView.findViewById(R.id.tvCommoQCMemo);
            holder.tvCommoFactlcdat = (TextView) convertView.findViewById(R.id.tvCommoFactlcdat);
            holder.tvCommoBatchid = (EditText) convertView.findViewById(R.id.tvCommoBatchid);
            holder.tvCommoOurAfter = (TextView) convertView.findViewById(R.id.tvCommoOurAfter);
            holder.tvCommoCtmchkdt = (TextView) convertView.findViewById(R.id.tvCommoCtmchkdt);
            holder.tvCommoIPQCPedt = (EditText) convertView.findViewById(R.id.tvCommoIPQCPedt);
            holder.tvCommoIPQCmdt = (EditText) convertView.findViewById(R.id.tvCommoIPQCmdt);
            holder.tvCommoQAname = (EditText) convertView.findViewById(R.id.tvCommoQAname);
            holder.tvCommoQAScore = (EditText) convertView.findViewById(R.id.tvCommoQAScore);
            holder.tvCommoQAMemo = (TextView) convertView.findViewById(R.id.tvCommoQAMemo);
            holder.lin_content = (LinearLayout) convertView.findViewById(R.id.lin_content);
            spUtils.put(context, "strposition", position);
            convertView.setClickable(true);
            convertView.setOnClickListener(myOnclicklistener);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCommoOurAfter.setText(getItem(position).getOurAfter());

        holder.tvCommoOurAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopuphdMenu(holder.tvCommoOurAfter);
            }
        });
        
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });

        /*判断item中制单人是否是登录用户，是为可改，否为不可改*/
        sp = context.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String nameid = sp.getString("usernamerecoder", "");
        String documentary = getItem(position).getPrddocumentary();
        String master = getItem(position).getPrdmaster();
        if (documentary != null && !documentary.equals("")) {
            if (documentary.equals("吕玉如") || master.equals(nameid)) {
                holder.lin_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String proid = String.valueOf(getItem(position).getID());
                        spUtils.put(context, "commoproid", proid);
                        String uriid = String.valueOf(getItem(position).getID());
                        spUtils.put(context, "uriid", uriid);
                    }
                });

                holder.tvCommoItem.setEnabled(true);
                holder.tvCommoItem.setText(getItem(position).getItem());

                holder.tvCommoCtmtxt.setEnabled(true);
                holder.tvCommoCtmtxt.setText(getItem(position).getCtmtxt());

                holder.tvCommoPrddocumentary.setEnabled(true);
                holder.tvCommoPrddocumentary.setText(getItem(position).getPrddocumentary());


                holder.tvCommoprdmaster.setEnabled(true);
                holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());


                holder.tvCommoQCMasterScore.setEnabled(true);
                final EditText editTextQCMasterScore = holder.tvCommoQCMasterScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCMasterScore.getTag() instanceof TextWatcher) {
                    editTextQCMasterScore.removeTextChangedListener((TextWatcher) editTextQCMasterScore.getTag());
                }
                editTextQCMasterScore.setText(getItem(position).getQCMasterScore());
                editTextQCMasterScore.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityQCMasterScore", proitem);
                    }
                };
                editTextQCMasterScore.addTextChangedListener(TvQCMasterScore);
                editTextQCMasterScore.setTag(TvQCMasterScore);
            /*光标放置在文本最后*/
                holder.tvCommoQCMasterScore.setSelection(holder.tvCommoQCMasterScore.length());


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
                                            if (attentionArr.contains(position)) {
                                                spUtils.put(context, "dateSealedrewtimesign", datetime);
                                            }
                                        }
                                    });
                            datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                                    "清除", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            holder.tvCommoSealedrev.setText("");
                                            spUtils.put(context, "dateSealedrewtimesign", "");
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
                                        spUtils.put(context, "dateDocbacktimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoDocback.setText("");
                                        spUtils.put(context, "dateDocbacktimesign", "");
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
                editTextPreMemo.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityPreMemo", proitem);
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
                                        spUtils.put(context, "datePredocdttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoPredocdt.setText("");
                                        spUtils.put(context, "datePredocdttimesign", "");
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
                holder.tvCommoPred.setText(getItem(position).getPredocdt());
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
                                        spUtils.put(context, "datePredtimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoPred.setText("");
                                        spUtils.put(context, "datePredtimesign", "");
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
                editTextPredoc.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityPredoc", proitem);
                    }
                };
                editTextPredoc.addTextChangedListener(TvPredoc);
                editTextPredoc.setTag(TvPredoc);
            /*光标放置在文本最后*/
                holder.tvCommoPredoc.setSelection(holder.tvCommoPredoc.length());


                holder.tvCommoFabricsok.setEnabled(true);
                final EditText editTextFabricsok = holder.tvCommoFabricsok;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextFabricsok.getTag() instanceof TextWatcher) {
                    editTextFabricsok.removeTextChangedListener((TextWatcher) editTextFabricsok.getTag());
                }
                editTextFabricsok.setText(getItem(position).getFabricsok());
                editTextFabricsok.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityFabricsok", proitem);
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
                editTextAccessoriesok.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityAccessoriesok", proitem);
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
                editTextSpcproDec.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommoditySpcproDec", proitem);
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
                editTextSpcproMemo.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommoditySpcproMemo", proitem);
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
                editTextCutqty.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityCutqty", proitem);
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
                                        spUtils.put(context, "dateSewFdttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoSewFdt.setText("");
                                        spUtils.put(context, "dateSewFdttimesign", "");
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
                                        spUtils.put(context, "dateSewMdttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoSewMdt.setText("");
                                        spUtils.put(context, "dateSewMdttimesign", "");
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
                                        spUtils.put(context, "datePrebdttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoPrebdt.setText("");
                                        spUtils.put(context, "datePrebdttimesign", "");
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
                                        spUtils.put(context, "dateQCbdttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoQCbdt.setText("");
                                        spUtils.put(context, "dateQCbdttimesign", "");
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


                holder.tvCommoQCbdtDoc.setEnabled(true);
                final EditText editTextQCbdtDoc = holder.tvCommoQCbdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCbdtDoc.getTag() instanceof TextWatcher) {
                    editTextQCbdtDoc.removeTextChangedListener((TextWatcher) editTextQCbdtDoc.getTag());
                }
                editTextQCbdtDoc.setText(getItem(position).getQCbdtDoc());
                editTextQCbdtDoc.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityQCbdtDoc", proitem);
                    }
                };
                editTextQCbdtDoc.addTextChangedListener(TvQCbdtDoc);
                editTextQCbdtDoc.setTag(TvQCbdtDoc);
            /*光标放置在文本最后*/
                holder.tvCommoQCbdtDoc.setSelection(holder.tvCommoQCbdtDoc.length());


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
                                        spUtils.put(context, "datePremdttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoPremdt.setText("");
                                        spUtils.put(context, "datePremdttimesign", "");
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
                                        spUtils.put(context, "dateQCmdttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoQCmdt.setText("");
                                        spUtils.put(context, "dateQCmdttimesign", "");
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


                holder.tvCommoQCmdtDoc.setEnabled(true);
                final EditText editTextQCmdtDoc = holder.tvCommoQCmdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCmdtDoc.getTag() instanceof TextWatcher) {
                    editTextQCmdtDoc.removeTextChangedListener((TextWatcher) editTextQCmdtDoc.getTag());
                }
                editTextQCmdtDoc.setText(getItem(position).getQCmdtDoc());
                editTextQCmdtDoc.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityQCmdtDoc", proitem);
                    }
                };
                editTextQCmdtDoc.addTextChangedListener(TvQCmdtDoc);
                editTextQCmdtDoc.setTag(TvQCmdtDoc);
            /*光标放置在文本最后*/
                holder.tvCommoQCmdtDoc.setSelection(holder.tvCommoQCmdtDoc.length());


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
                                        spUtils.put(context, "datePreedttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoPreedt.setText("");
                                        spUtils.put(context, "datePreedttimesign", "");
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
                                        spUtils.put(context, "dateQCMedttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoQCMedt.setText("");
                                        spUtils.put(context, "dateQCMedttimesign", "");
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


                holder.tvCommoQCedtDoc.setEnabled(true);
                final EditText editTextQCedtDoc = holder.tvCommoQCedtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCedtDoc.getTag() instanceof TextWatcher) {
                    editTextQCedtDoc.removeTextChangedListener((TextWatcher) editTextQCedtDoc.getTag());
                }
                editTextQCedtDoc.setText(getItem(position).getQCedtDoc());
                editTextQCedtDoc.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityQCedtDoc", proitem);
                    }
                };
                editTextQCedtDoc.addTextChangedListener(TvQCedtDoc);
                editTextQCedtDoc.setTag(TvQCedtDoc);
            /*光标放置在文本最后*/
                holder.tvCommoQCedtDoc.setSelection(holder.tvCommoQCedtDoc.length());


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
                                        spUtils.put(context, "dateFctmdttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoFctmdt.setText("");
                                        spUtils.put(context, "dateFctmdttimesign", "");
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
                                        spUtils.put(context, "dateFctedttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoFctedt.setText("");
                                        spUtils.put(context, "dateFctedttimesign", "");
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
                                        spUtils.put(context, "datePackbdattimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoPackbdat.setText("");
                                        spUtils.put(context, "datePackbdattimesign", "");
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
                editTextPackqty2.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityPackqty2", proitem);
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
                editTextQCMemo.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityQCMemo", proitem);
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
                                        spUtils.put(context, "dateFactlcdattimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoFactlcdat.setText("");
                                        spUtils.put(context, "dateFactlcdattimesign", "");
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
                editTextBatchid.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityBatchid", proitem);
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
                                        spUtils.put(context, "dateCtmchkdttimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoCtmchkdt.setText("");
                                        spUtils.put(context, "dateCtmchkdttimesign", "");
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
                editTextIPQCPedt.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityIPQCPedt", proitem);
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
                editTextIPQCmdt.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityIPQCmdt", proitem);
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
                editTextQAname.setText(getItem(position).getQAname());
                editTextQAname.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityQAname", proitem);
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
                editTextQAScore.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            index = position;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        return false;
                    }
                });
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
                        spUtils.put(context, "CommodityQAScore", proitem);
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
                                        spUtils.put(context, "dateQAMemotimesign", datetime);
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                                , "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoQAMemo.setText("");
                                        spUtils.put(context, "dateQAMemotimesign", "");
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

            } else {
                holder.tvCommoItem.setEnabled(false);
                holder.tvCommoItem.setText(getItem(position).getItem());

                holder.tvCommoCtmtxt.setEnabled(false);
                holder.tvCommoCtmtxt.setText(getItem(position).getCtmtxt());

                holder.tvCommoPrddocumentary.setEnabled(false);
                holder.tvCommoPrddocumentary.setText(getItem(position).getPrddocumentary());

                holder.tvCommoprdmaster.setEnabled(false);
                holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());

                holder.tvCommoQCMasterScore.setEnabled(false);
                final EditText editTextQCMasterScore = holder.tvCommoQCMasterScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCMasterScore.getTag() instanceof TextWatcher) {
                    editTextQCMasterScore.removeTextChangedListener((TextWatcher) editTextQCMasterScore.getTag());
                }
                editTextQCMasterScore.setText(getItem(position).getQCMasterScore());

                holder.tvCommoSealedrev.setEnabled(false);
                holder.tvCommoSealedrev.setText(getItem(position).getSealedrev());

                holder.tvCommoDocback.setEnabled(false);
                holder.tvCommoDocback.setText(getItem(position).getDocback());

                holder.tvCommoLcdat.setEnabled(false);
                holder.tvCommoLcdat.setText(getItem(position).getLcdat());

                holder.tvCommoTaskqty.setEnabled(false);
                holder.tvCommoTaskqty.setText(getItem(position).getTaskqty());

                holder.tvCommoPreMemo.setEnabled(false);
                final EditText editTextPreMemo = holder.tvCommoPreMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPreMemo.getTag() instanceof TextWatcher) {
                    editTextPreMemo.removeTextChangedListener((TextWatcher) editTextPreMemo.getTag());
                }
                editTextPreMemo.setText(getItem(position).getPreMemo());

                holder.tvCommoPredocdt.setEnabled(false);
                holder.tvCommoPredocdt.setText(getItem(position).getPredocdt());

                holder.tvCommoPred.setEnabled(false);
                holder.tvCommoPred.setText(getItem(position).getPredocdt());

                holder.tvCommoPredoc.setEnabled(false);
                final EditText editTextPredoc = holder.tvCommoPredoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPredoc.getTag() instanceof TextWatcher) {
                    editTextPredoc.removeTextChangedListener((TextWatcher) editTextPredoc.getTag());
                }
                editTextPredoc.setText(getItem(position).getPredoc());

                holder.tvCommoFabricsok.setEnabled(false);
                final EditText editTextFabricsok = holder.tvCommoFabricsok;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextFabricsok.getTag() instanceof TextWatcher) {
                    editTextFabricsok.removeTextChangedListener((TextWatcher) editTextFabricsok.getTag());
                }
                editTextFabricsok.setText(getItem(position).getFabricsok());

                holder.tvCommoAccessoriesok.setEnabled(false);
                final EditText editTextAccessoriesok = holder.tvCommoAccessoriesok;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextAccessoriesok.getTag() instanceof TextWatcher) {
                    editTextAccessoriesok.removeTextChangedListener((TextWatcher) editTextAccessoriesok.getTag());
                }
                editTextAccessoriesok.setText(getItem(position).getAccessoriesok());

                holder.tvCommoSpcproDec.setEnabled(false);
                final EditText editTextSpcproDec = holder.tvCommoSpcproDec;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSpcproDec.getTag() instanceof TextWatcher) {
                    editTextSpcproDec.removeTextChangedListener((TextWatcher) editTextSpcproDec.getTag());
                }
                editTextSpcproDec.setText(getItem(position).getSpcproDec());

                holder.tvCommoSpcproMemo.setEnabled(false);
                final EditText editTextSpcproMemo = holder.tvCommoSpcproMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSpcproMemo.getTag() instanceof TextWatcher) {
                    editTextSpcproMemo.removeTextChangedListener((TextWatcher) editTextSpcproMemo.getTag());
                }
                editTextSpcproMemo.setText(getItem(position).getSpcproMemo());

                holder.tvCommoCutqty.setEnabled(false);
                final EditText editTextCutqty = holder.tvCommoCutqty;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextCutqty.getTag() instanceof TextWatcher) {
                    editTextCutqty.removeTextChangedListener((TextWatcher) editTextCutqty.getTag());
                }
                editTextCutqty.setText(getItem(position).getCutqty());

                holder.tvCommoSewFdt.setEnabled(false);
                holder.tvCommoSewFdt.setText(getItem(position).getSewFdt());

                holder.tvCommoSewMdt.setEnabled(false);
                holder.tvCommoSewMdt.setText(getItem(position).getSewMdt());

                holder.tvCommoSubfactory.setEnabled(false);
                holder.tvCommoSubfactory.setText(getItem(position).getSubfactory());

                holder.tvCommoPrebdt.setEnabled(false);
                holder.tvCommoPrebdt.setText(getItem(position).getPrebdt());

                holder.tvCommoQCbdt.setEnabled(false);
                holder.tvCommoQCbdt.setText(getItem(position).getQCbdt());

                holder.tvCommoQCbdtDoc.setEnabled(false);
                final EditText editTextQCbdtDoc = holder.tvCommoQCbdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCbdtDoc.getTag() instanceof TextWatcher) {
                    editTextQCbdtDoc.removeTextChangedListener((TextWatcher) editTextQCbdtDoc.getTag());
                }
                editTextQCbdtDoc.setText(getItem(position).getQCbdtDoc());

                holder.tvCommoPremdt.setEnabled(false);
                holder.tvCommoPremdt.setText(getItem(position).getPremdt());

                holder.tvCommoQCmdt.setEnabled(false);
                holder.tvCommoQCmdt.setText(getItem(position).getQCmdt());

                holder.tvCommoQCmdtDoc.setEnabled(false);
                final EditText editTextQCmdtDoc = holder.tvCommoQCmdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCmdtDoc.getTag() instanceof TextWatcher) {
                    editTextQCmdtDoc.removeTextChangedListener((TextWatcher) editTextQCmdtDoc.getTag());
                }
                editTextQCmdtDoc.setText(getItem(position).getQCmdtDoc());

                holder.tvCommoPreedt.setEnabled(false);
                holder.tvCommoPreedt.setText(getItem(position).getPreedt());

                holder.tvCommoQCMedt.setEnabled(false);
                holder.tvCommoQCMedt.setText(getItem(position).getQCMedt());

                holder.tvCommoQCedtDoc.setEnabled(false);
                final EditText editTextQCedtDoc = holder.tvCommoQCedtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCedtDoc.getTag() instanceof TextWatcher) {
                    editTextQCedtDoc.removeTextChangedListener((TextWatcher) editTextQCedtDoc.getTag());
                }
                editTextQCedtDoc.setText(getItem(position).getQCedtDoc());

                holder.tvCommoFctmdt.setEnabled(false);
                holder.tvCommoFctmdt.setText(getItem(position).getFctmdt());

                holder.tvCommoFctedt.setEnabled(false);
                holder.tvCommoFctedt.setText(getItem(position).getFctedt());

                holder.tvCommoPackbdat.setEnabled(false);
                holder.tvCommoPackbdat.setText(getItem(position).getPackbdat());

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

                holder.tvCommoBatchid.setEnabled(false);
                final EditText editTextBatchid = holder.tvCommoBatchid;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextBatchid.getTag() instanceof TextWatcher) {
                    editTextBatchid.removeTextChangedListener((TextWatcher) editTextBatchid.getTag());
                }
                editTextBatchid.setText(getItem(position).getBatchid());

                holder.tvCommoOurAfter.setEnabled(false);

                holder.tvCommoCtmchkdt.setEnabled(false);
                holder.tvCommoCtmchkdt.setText(getItem(position).getCtmchkdt());

                holder.tvCommoIPQCPedt.setEnabled(false);
                final EditText editTextIPQCPedt = holder.tvCommoIPQCPedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextIPQCPedt.getTag() instanceof TextWatcher) {
                    editTextIPQCPedt.removeTextChangedListener((TextWatcher) editTextIPQCPedt.getTag());
                }
                editTextIPQCPedt.setText(getItem(position).getIPQCPedt());

                holder.tvCommoIPQCmdt.setEnabled(false);
                final EditText editTextIPQCmdt = holder.tvCommoIPQCmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextIPQCmdt.getTag() instanceof TextWatcher) {
                    editTextIPQCmdt.removeTextChangedListener((TextWatcher) editTextIPQCmdt.getTag());
                }
                editTextIPQCmdt.setText(getItem(position).getIPQCmdt());

                holder.tvCommoQAname.setEnabled(false);
                final EditText editTextQAname = holder.tvCommoQAname;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQAname.getTag() instanceof TextWatcher) {
                    editTextQAname.removeTextChangedListener((TextWatcher) editTextQAname.getTag());
                }
                editTextQAname.setText(getItem(position).getQAname());

                holder.tvCommoQAScore.setEnabled(false);
                final EditText editTextQAScore = holder.tvCommoQAScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQAScore.getTag() instanceof TextWatcher) {
                    editTextQAScore.removeTextChangedListener((TextWatcher) editTextQAScore.getTag());
                }
                editTextQAScore.setText(getItem(position).getQAScore());

                holder.tvCommoQAMemo.setEnabled(false);
                holder.tvCommoQAMemo.setText(getItem(position).getQAMemo());

            }

        } else {
            holder.tvCommoItem.setEnabled(false);
            holder.tvCommoItem.setText(getItem(position).getItem());

            holder.tvCommoCtmtxt.setEnabled(false);
            holder.tvCommoCtmtxt.setText(getItem(position).getCtmtxt());

            holder.tvCommoPrddocumentary.setEnabled(false);
            holder.tvCommoPrddocumentary.setText(getItem(position).getPrddocumentary());

            holder.tvCommoprdmaster.setEnabled(false);
            holder.tvCommoprdmaster.setText(getItem(position).getPrdmaster());

            holder.tvCommoQCMasterScore.setEnabled(false);
            final EditText editTextQCMasterScore = holder.tvCommoQCMasterScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMasterScore.getTag() instanceof TextWatcher) {
                editTextQCMasterScore.removeTextChangedListener((TextWatcher) editTextQCMasterScore.getTag());
            }
            editTextQCMasterScore.setText(getItem(position).getQCMasterScore());

            holder.tvCommoSealedrev.setEnabled(false);
            holder.tvCommoSealedrev.setText(getItem(position).getSealedrev());

            holder.tvCommoDocback.setEnabled(false);
            holder.tvCommoDocback.setText(getItem(position).getDocback());

            holder.tvCommoLcdat.setEnabled(false);
            holder.tvCommoLcdat.setText(getItem(position).getLcdat());

            holder.tvCommoTaskqty.setEnabled(false);
            holder.tvCommoTaskqty.setText(getItem(position).getTaskqty());

            holder.tvCommoPreMemo.setEnabled(false);
            final EditText editTextPreMemo = holder.tvCommoPreMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPreMemo.getTag() instanceof TextWatcher) {
                editTextPreMemo.removeTextChangedListener((TextWatcher) editTextPreMemo.getTag());
            }
            editTextPreMemo.setText(getItem(position).getPreMemo());

            holder.tvCommoPredocdt.setEnabled(false);
            holder.tvCommoPredocdt.setText(getItem(position).getPredocdt());

            holder.tvCommoPred.setEnabled(false);
            holder.tvCommoPred.setText(getItem(position).getPredocdt());

            holder.tvCommoPredoc.setEnabled(false);
            final EditText editTextPredoc = holder.tvCommoPredoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPredoc.getTag() instanceof TextWatcher) {
                editTextPredoc.removeTextChangedListener((TextWatcher) editTextPredoc.getTag());
            }
            editTextPredoc.setText(getItem(position).getPredoc());

            holder.tvCommoFabricsok.setEnabled(false);
            final EditText editTextFabricsok = holder.tvCommoFabricsok;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFabricsok.getTag() instanceof TextWatcher) {
                editTextFabricsok.removeTextChangedListener((TextWatcher) editTextFabricsok.getTag());
            }
            editTextFabricsok.setText(getItem(position).getFabricsok());

            holder.tvCommoAccessoriesok.setEnabled(false);
            final EditText editTextAccessoriesok = holder.tvCommoAccessoriesok;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextAccessoriesok.getTag() instanceof TextWatcher) {
                editTextAccessoriesok.removeTextChangedListener((TextWatcher) editTextAccessoriesok.getTag());
            }
            editTextAccessoriesok.setText(getItem(position).getAccessoriesok());

            holder.tvCommoSpcproDec.setEnabled(false);
            final EditText editTextSpcproDec = holder.tvCommoSpcproDec;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproDec.getTag() instanceof TextWatcher) {
                editTextSpcproDec.removeTextChangedListener((TextWatcher) editTextSpcproDec.getTag());
            }
            editTextSpcproDec.setText(getItem(position).getSpcproDec());

            holder.tvCommoSpcproMemo.setEnabled(false);
            final EditText editTextSpcproMemo = holder.tvCommoSpcproMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSpcproMemo.getTag() instanceof TextWatcher) {
                editTextSpcproMemo.removeTextChangedListener((TextWatcher) editTextSpcproMemo.getTag());
            }
            editTextSpcproMemo.setText(getItem(position).getSpcproMemo());

            holder.tvCommoCutqty.setEnabled(false);
            final EditText editTextCutqty = holder.tvCommoCutqty;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextCutqty.getTag() instanceof TextWatcher) {
                editTextCutqty.removeTextChangedListener((TextWatcher) editTextCutqty.getTag());
            }
            editTextCutqty.setText(getItem(position).getCutqty());

            holder.tvCommoSewFdt.setEnabled(false);
            holder.tvCommoSewFdt.setText(getItem(position).getSewFdt());

            holder.tvCommoSewMdt.setEnabled(false);
            holder.tvCommoSewMdt.setText(getItem(position).getSewMdt());

            holder.tvCommoSubfactory.setEnabled(false);
            holder.tvCommoSubfactory.setText(getItem(position).getSubfactory());

            holder.tvCommoPrebdt.setEnabled(false);
            holder.tvCommoPrebdt.setText(getItem(position).getPrebdt());

            holder.tvCommoQCbdt.setEnabled(false);
            holder.tvCommoQCbdt.setText(getItem(position).getQCbdt());

            holder.tvCommoQCbdtDoc.setEnabled(false);
            final EditText editTextQCbdtDoc = holder.tvCommoQCbdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCbdtDoc.getTag() instanceof TextWatcher) {
                editTextQCbdtDoc.removeTextChangedListener((TextWatcher) editTextQCbdtDoc.getTag());
            }
            editTextQCbdtDoc.setText(getItem(position).getQCbdtDoc());

            holder.tvCommoPremdt.setEnabled(false);
            holder.tvCommoPremdt.setText(getItem(position).getPremdt());

            holder.tvCommoQCmdt.setEnabled(false);
            holder.tvCommoQCmdt.setText(getItem(position).getQCmdt());

            holder.tvCommoQCmdtDoc.setEnabled(false);
            final EditText editTextQCmdtDoc = holder.tvCommoQCmdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCmdtDoc.getTag() instanceof TextWatcher) {
                editTextQCmdtDoc.removeTextChangedListener((TextWatcher) editTextQCmdtDoc.getTag());
            }
            editTextQCmdtDoc.setText(getItem(position).getQCmdtDoc());

            holder.tvCommoPreedt.setEnabled(false);
            holder.tvCommoPreedt.setText(getItem(position).getPreedt());

            holder.tvCommoQCMedt.setEnabled(false);
            holder.tvCommoQCMedt.setText(getItem(position).getQCMedt());

            holder.tvCommoQCedtDoc.setEnabled(false);
            final EditText editTextQCedtDoc = holder.tvCommoQCedtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCedtDoc.getTag() instanceof TextWatcher) {
                editTextQCedtDoc.removeTextChangedListener((TextWatcher) editTextQCedtDoc.getTag());
            }
            editTextQCedtDoc.setText(getItem(position).getQCedtDoc());

            holder.tvCommoFctmdt.setEnabled(false);
            holder.tvCommoFctmdt.setText(getItem(position).getFctmdt());

            holder.tvCommoFctedt.setEnabled(false);
            holder.tvCommoFctedt.setText(getItem(position).getFctedt());

            holder.tvCommoPackbdat.setEnabled(false);
            holder.tvCommoPackbdat.setText(getItem(position).getPackbdat());

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

            holder.tvCommoBatchid.setEnabled(false);
            final EditText editTextBatchid = holder.tvCommoBatchid;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextBatchid.getTag() instanceof TextWatcher) {
                editTextBatchid.removeTextChangedListener((TextWatcher) editTextBatchid.getTag());
            }
            editTextBatchid.setText(getItem(position).getBatchid());

            holder.tvCommoOurAfter.setEnabled(false);

            holder.tvCommoCtmchkdt.setEnabled(false);
            holder.tvCommoCtmchkdt.setText(getItem(position).getCtmchkdt());

            holder.tvCommoIPQCPedt.setEnabled(false);
            final EditText editTextIPQCPedt = holder.tvCommoIPQCPedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCPedt.getTag() instanceof TextWatcher) {
                editTextIPQCPedt.removeTextChangedListener((TextWatcher) editTextIPQCPedt.getTag());
            }
            editTextIPQCPedt.setText(getItem(position).getIPQCPedt());

            holder.tvCommoIPQCmdt.setEnabled(false);
            final EditText editTextIPQCmdt = holder.tvCommoIPQCmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextIPQCmdt.getTag() instanceof TextWatcher) {
                editTextIPQCmdt.removeTextChangedListener((TextWatcher) editTextIPQCmdt.getTag());
            }
            editTextIPQCmdt.setText(getItem(position).getIPQCmdt());

            holder.tvCommoQAname.setEnabled(false);
            final EditText editTextQAname = holder.tvCommoQAname;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAname.getTag() instanceof TextWatcher) {
                editTextQAname.removeTextChangedListener((TextWatcher) editTextQAname.getTag());
            }
            editTextQAname.setText(getItem(position).getQAname());

            holder.tvCommoQAScore.setEnabled(false);
            final EditText editTextQAScore = holder.tvCommoQAScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAScore.getTag() instanceof TextWatcher) {
                editTextQAScore.removeTextChangedListener((TextWatcher) editTextQAScore.getTag());
            }
            editTextQAScore.setText(getItem(position).getQAScore());

            holder.tvCommoQAMemo.setEnabled(false);
            holder.tvCommoQAMemo.setText(getItem(position).getQAMemo());
        }
        return convertView;
    }

    public View.OnClickListener myOnclicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ss = sp.getString("strposition", "");
//            ToastUtils.ShowToastMessage("点击是" + ss, context);
        }
    };

    /**
     * 弹出后道选择菜单
     *
     * @param view
     */
    private void showPopuphdMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_commo_hd, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sp = context.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
                String title = item.getTitle().toString();
                spUtils.put(context, "commohdTitle", title);
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
        LinearLayout lin_content;
        TextView tvCommoOurAfter, tvCommoItem, tvCommoCtmtxt, tvCommoQAMemo,
                tvCommoPrddocumentary, tvCommoprdmaster, tvCommoSealedrev,
                tvCommoDocback, tvCommoLcdat, tvCommoTaskqty, tvCommoPredocdt, tvCommoPred, tvCommoSewFdt, tvCommoSewMdt, tvCommoSubfactory, tvCommoPrebdt, tvCommoQCbdt, tvCommoPremdt, tvCommoQCmdt, tvCommoPreedt, tvCommoQCMedt, tvCommoFctmdt, tvCommoFctedt, tvCommoPackbdat, tvCommoFactlcdat, tvCommoCtmchkdt;
        EditText
                tvCommoQCMasterScore, tvCommoPreMemo,
                tvCommoPredoc, tvCommoFabricsok, tvCommoAccessoriesok,
                tvCommoSpcproDec, tvCommoSpcproMemo, tvCommoCutqty,
                tvCommoQCbdtDoc, tvCommoQCmdtDoc, tvCommoQCedtDoc, tvCommoPackqty2, tvCommoQCMemo, tvCommoBatchid,
                tvCommoIPQCPedt, tvCommoIPQCmdt, tvCommoQAname, tvCommoQAScore;
    }
}
