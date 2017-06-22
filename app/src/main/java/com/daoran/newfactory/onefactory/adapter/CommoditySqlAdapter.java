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
//            holder.tvCommoItem = (TextView) convertView.findViewById(R.id.tvCommoItem);
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
//            holder.tvCommoItem.setEnabled(true);
//            holder.tvCommoItem.setText(getItem(position).getItem());

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
//                        spUtils.put(context, "commohdTitle", title);
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

            holder.tvCommoQCMasterScore.setEnabled(true);
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
                                getItem(position).setQCMasterScore(proitem);
//                                spUtils.put(context, "CommodityQCMasterScore", proitem);
                            }
                        };
                        editTextQCMasterScore.addTextChangedListener(TvQCMasterScore);
                        editTextQCMasterScore.setTag(TvQCMasterScore);
                    } else {
                        //失去焦点
                        String qcmastertwoo = holder.tvCommoQCMasterScore.getText().toString();
                        getItem(position).setQCMasterScore(qcmastertwoo);
                        System.out.print(qcmastertwoo);
//                        spUtils.put(context, "CommodityQCMasterScore", qcmastertwoo);
                    }
                }
            });
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
                                            getItem(position).setSealedrev(datetime);
//                                            spUtils.put(context, "dateSealedrewtimesign", datetime);
                                        }
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                                "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoSealedrev.setText("");
                                        getItem(position).setSealedrev("");
//                                        spUtils.put(context, "dateSealedrewtimesign", "");
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
                                    getItem(position).setDocback(datetime);
//                                    spUtils.put(context, "dateDocbacktimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoDocback.setText("");
                                    holder.tvCommoDocback.setText("");
//                                    spUtils.put(context, "dateDocbacktimesign", "");
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
                    getItem(position).setPreMemo(proitem);
//                    spUtils.put(context, "CommodityPreMemo", proitem);
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
                                    getItem(position).setPredocdt(datetime);
//                                    spUtils.put(context, "datePredocdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPredocdt.setText("");
                                    getItem(position).setPredocdt("");
//                                    spUtils.put(context, "datePredocdttimesign", "");
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
                                    getItem(position).setPredocdt(datetime);
//                                    spUtils.put(context, "datePredtimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPred.setText("");
                                    getItem(position).setPredocdt("");
//                                    spUtils.put(context, "datePredtimesign", "");
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
                    getItem(position).setFabricsok(proitem);
//                    spUtils.put(context, "CommodityFabricsok", proitem);
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
                    getItem(position).setAccessoriesok(proitem);
//                    spUtils.put(context, "CommodityAccessoriesok", proitem);
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
                    getItem(position).setSpcproDec(proitem);
//                    spUtils.put(context, "CommoditySpcproDec", proitem);
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
                    getItem(position).setSpcproMemo(proitem);
//                    spUtils.put(context, "CommoditySpcproMemo", proitem);
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
                    getItem(position).setCutqty(proitem);
//                    spUtils.put(context, "CommodityCutqty", proitem);
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
                                    getItem(position).setSewFdt(datetime);
//                                    spUtils.put(context, "dateSewFdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewFdt.setText("");
                                    getItem(position).setSewFdt("");
//                                    spUtils.put(context, "dateSewFdttimesign", "");
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
                                    getItem(position).setSewMdt(datetime);
//                                    spUtils.put(context, "dateSewMdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewMdt.setText("");
                                    getItem(position).setSewMdt("");
//                                    spUtils.put(context, "dateSewMdttimesign", "");
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
                                    getItem(position).setPrebdt(datetime);
//                                    spUtils.put(context, "datePrebdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPrebdt.setText("");
                                    getItem(position).setPrebdt("");
//                                    spUtils.put(context, "datePrebdttimesign", "");
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
                                    getItem(position).setQCbdt(datetime);
//                                    spUtils.put(context, "dateQCbdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCbdt.setText("");
                                    getItem(position).setQCbdt("");
//                                    spUtils.put(context, "dateQCbdttimesign", "");
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
                                    getItem(position).setPremdt(datetime);
//                                    spUtils.put(context, "datePremdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPremdt.setText("");
                                    getItem(position).setPremdt("");
//                                    spUtils.put(context, "datePremdttimesign", "");
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
                                    getItem(position).setQCmdt(datetime);
//                                    spUtils.put(context, "dateQCmdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCmdt.setText("");
                                    getItem(position).setQCmdt("");
//                                    spUtils.put(context, "dateQCmdttimesign", "");
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
                                    getItem(position).setPreedt(datetime);
//                                    spUtils.put(context, "datePreedttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPreedt.setText("");
                                    getItem(position).setPreedt("");
//                                    spUtils.put(context, "datePreedttimesign", "");
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
                                    getItem(position).setQCMedt(datetime);
//                                    spUtils.put(context, "dateQCMedttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCMedt.setText("");
                                    getItem(position).setQCMedt("");
//                                    spUtils.put(context, "dateQCMedttimesign", "");
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
                                    getItem(position).setFctmdt(datetime);
//                                    spUtils.put(context, "dateFctmdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctmdt.setText("");
                                    getItem(position).setFctmdt("");
//                                    spUtils.put(context, "dateFctmdttimesign", "");
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
                                    getItem(position).setFctedt(datetime);
//                                    spUtils.put(context, "dateFctedttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctedt.setText("");
                                    getItem(position).setFctedt("");
//                                    spUtils.put(context, "dateFctedttimesign", "");
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
                                    getItem(position).setPackbdat(datetime);
//                                    spUtils.put(context, "datePackbdattimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPackbdat.setText("");
                                    getItem(position).setPackbdat("");
//                                    spUtils.put(context, "datePackbdattimesign", "");
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
                    getItem(position).setPackqty2(proitem);
//                    spUtils.put(context, "CommodityPackqty2", proitem);
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
                    getItem(position).setQCMemo(proitem);
//                    spUtils.put(context, "CommodityQCMemo", proitem);
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
                                    getItem(position).setFactlcdat(datetime);
//                                    spUtils.put(context, "dateFactlcdattimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFactlcdat.setText("");
                                    getItem(position).setFactlcdat("");
//                                    spUtils.put(context, "dateFactlcdattimesign", "");
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
                    getItem(position).setBatchid(proitem);
//                    spUtils.put(context, "CommodityBatchid", proitem);
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
                                    getItem(position).setCtmchkdt(datetime);
//                                    spUtils.put(context, "dateCtmchkdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoCtmchkdt.setText("");
                                    getItem(position).setCtmchkdt("");
//                                    spUtils.put(context, "dateCtmchkdttimesign", "");
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
                    getItem(position).setIPQCPedt(proitem);
//                    spUtils.put(context, "CommodityIPQCPedt", proitem);
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
                    getItem(position).setIPQCmdt(proitem);
//                    spUtils.put(context, "CommodityIPQCmdt", proitem);
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
                    getItem(position).setQAname(proitem);
//                    spUtils.put(context, "CommodityQAname", proitem);
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
                    getItem(position).setQAScore(proitem);
//                    spUtils.put(context, "CommodityQAScore", proitem);
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
                                    getItem(position).setQAMemo(datetime);
//                                    spUtils.put(context, "dateQAMemotimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQAMemo.setText("");
                                    getItem(position).setQAMemo("");
//                                    spUtils.put(context, "dateQAMemotimesign", "");
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
//            holder.tvCommoItem.setEnabled(true);
//            holder.tvCommoItem.setText(getItem(position).getItem());

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
//                        spUtils.put(context, "commohdTitle", title);
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
//            holder.tvCommoQCMasterScore.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if (hasFocus) {
//                        //得到焦点
//                        sp = context.getSharedPreferences("my_sp", 0);
//                        String qcmaster = sp.getString("CommodityQCMasterScore", "");
//                        System.out.print(qcmaster);
//                        spUtils.put(context, "debugQCMasterScore", qcmaster);
//                        TextWatcher TvQCMasterScore = new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                                Log.d(TAG, "beforeTextChanged");
//                            }
//
//                            @Override
//                            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                                Log.d(TAG, "onTextChanged");
//                            }
//
//                            @Override
//                            public void afterTextChanged(Editable s) {
//                                Log.d(TAG, "afterTextChanged");
//                                String proitem = holder.tvCommoQCMasterScore.getText().toString();
//                                getItem(position).setQCMasterScore(proitem);
////                                spUtils.put(context, "CommodityQCMasterScore", proitem);
//                            }
//                        };
//                        editTextQCMasterScore.addTextChangedListener(TvQCMasterScore);
//                        editTextQCMasterScore.setTag(TvQCMasterScore);
//                    } else {
//                        //失去焦点
//                        String qcmastertwoo = holder.tvCommoQCMasterScore.getText().toString();
//                        getItem(position).setQCMasterScore(qcmastertwoo);
//                        System.out.print(qcmastertwoo);
////                        spUtils.put(context, "CommodityQCMasterScore", qcmastertwoo);
//                    }
//                }
//            });
//            /*光标放置在文本最后*/
//            holder.tvCommoQCMasterScore.setSelection(holder.tvCommoQCMasterScore.length());

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
                                            getItem(position).setSealedrev(datetime);
//                                            spUtils.put(context, "dateSealedrewtimesign", datetime);
                                        }
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                                "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoSealedrev.setText("");
                                        getItem(position).setSealedrev("");
//                                        spUtils.put(context, "dateSealedrewtimesign", "");
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
                                    getItem(position).setDocback(datetime);
//                                    spUtils.put(context, "dateDocbacktimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoDocback.setText("");
                                    holder.tvCommoDocback.setText("");
//                                    spUtils.put(context, "dateDocbacktimesign", "");
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
                    getItem(position).setPreMemo(proitem);
//                    spUtils.put(context, "CommodityPreMemo", proitem);
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
                                    getItem(position).setPredocdt(datetime);
//                                    spUtils.put(context, "datePredocdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPredocdt.setText("");
                                    getItem(position).setPredocdt("");
//                                    spUtils.put(context, "datePredocdttimesign", "");
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
                                    getItem(position).setPredocdt(datetime);
//                                    spUtils.put(context, "datePredtimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPred.setText("");
                                    getItem(position).setPredocdt("");
//                                    spUtils.put(context, "datePredtimesign", "");
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
                    getItem(position).setFabricsok(proitem);
//                    spUtils.put(context, "CommodityFabricsok", proitem);
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
                    getItem(position).setAccessoriesok(proitem);
//                    spUtils.put(context, "CommodityAccessoriesok", proitem);
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
                    getItem(position).setSpcproDec(proitem);
//                    spUtils.put(context, "CommoditySpcproDec", proitem);
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
                    getItem(position).setSpcproMemo(proitem);
//                    spUtils.put(context, "CommoditySpcproMemo", proitem);
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
                    getItem(position).setCutqty(proitem);
//                    spUtils.put(context, "CommodityCutqty", proitem);
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
                                    getItem(position).setSewFdt(datetime);
//                                    spUtils.put(context, "dateSewFdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewFdt.setText("");
                                    getItem(position).setSewFdt("");
//                                    spUtils.put(context, "dateSewFdttimesign", "");
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
                                    getItem(position).setSewMdt(datetime);
//                                    spUtils.put(context, "dateSewMdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewMdt.setText("");
                                    getItem(position).setSewMdt("");
//                                    spUtils.put(context, "dateSewMdttimesign", "");
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
                                    getItem(position).setPrebdt(datetime);
//                                    spUtils.put(context, "datePrebdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPrebdt.setText("");
                                    getItem(position).setPrebdt("");
//                                    spUtils.put(context, "datePrebdttimesign", "");
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
                                    getItem(position).setQCbdt(datetime);
//                                    spUtils.put(context, "dateQCbdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCbdt.setText("");
                                    getItem(position).setQCbdt("");
//                                    spUtils.put(context, "dateQCbdttimesign", "");
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
                                    getItem(position).setPremdt(datetime);
//                                    spUtils.put(context, "datePremdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPremdt.setText("");
                                    getItem(position).setPremdt("");
//                                    spUtils.put(context, "datePremdttimesign", "");
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
                                    getItem(position).setQCmdt(datetime);
//                                    spUtils.put(context, "dateQCmdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCmdt.setText("");
                                    getItem(position).setQCmdt("");
//                                    spUtils.put(context, "dateQCmdttimesign", "");
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
                                    getItem(position).setPreedt(datetime);
//                                    spUtils.put(context, "datePreedttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPreedt.setText("");
                                    getItem(position).setPreedt("");
//                                    spUtils.put(context, "datePreedttimesign", "");
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
                                    getItem(position).setQCMedt(datetime);
//                                    spUtils.put(context, "dateQCMedttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCMedt.setText("");
                                    getItem(position).setQCMedt("");
//                                    spUtils.put(context, "dateQCMedttimesign", "");
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
                                    getItem(position).setFctmdt(datetime);
//                                    spUtils.put(context, "dateFctmdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctmdt.setText("");
                                    getItem(position).setFctmdt("");
//                                    spUtils.put(context, "dateFctmdttimesign", "");
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
                                    getItem(position).setFctedt(datetime);
//                                    spUtils.put(context, "dateFctedttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctedt.setText("");
                                    getItem(position).setFctedt("");
//                                    spUtils.put(context, "dateFctedttimesign", "");
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
                                    getItem(position).setPackbdat(datetime);
//                                    spUtils.put(context, "datePackbdattimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPackbdat.setText("");
                                    getItem(position).setPackbdat("");
//                                    spUtils.put(context, "datePackbdattimesign", "");
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
                    getItem(position).setPackqty2(proitem);
//                    spUtils.put(context, "CommodityPackqty2", proitem);
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
                    getItem(position).setQCMemo(proitem);
//                    spUtils.put(context, "CommodityQCMemo", proitem);
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
                                    getItem(position).setFactlcdat(datetime);
//                                    spUtils.put(context, "dateFactlcdattimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFactlcdat.setText("");
                                    getItem(position).setFactlcdat("");
//                                    spUtils.put(context, "dateFactlcdattimesign", "");
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
                    getItem(position).setBatchid(proitem);
//                    spUtils.put(context, "CommodityBatchid", proitem);
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
                                    getItem(position).setCtmchkdt(datetime);
//                                    spUtils.put(context, "dateCtmchkdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoCtmchkdt.setText("");
                                    getItem(position).setCtmchkdt("");
//                                    spUtils.put(context, "dateCtmchkdttimesign", "");
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
                    getItem(position).setIPQCPedt(proitem);
//                    spUtils.put(context, "CommodityIPQCPedt", proitem);
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
                    getItem(position).setIPQCmdt(proitem);
//                    spUtils.put(context, "CommodityIPQCmdt", proitem);
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
                    getItem(position).setQAname(proitem);
//                    spUtils.put(context, "CommodityQAname", proitem);
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
                    getItem(position).setQAScore(proitem);
//                    spUtils.put(context, "CommodityQAScore", proitem);
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
                                    getItem(position).setQAMemo(datetime);
//                                    spUtils.put(context, "dateQAMemotimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQAMemo.setText("");
                                    getItem(position).setQAMemo("");
//                                    spUtils.put(context, "dateQAMemotimesign", "");
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
            /**
             * 判断登录人是否是陈慧萍（跟单负责人）
             */
        } else if (nameid.equals("陈慧萍")) {
//            holder.tvCommoItem.setEnabled(true);
//            holder.tvCommoItem.setText(getItem(position).getItem());

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
//                        spUtils.put(context, "commohdTitle", title);
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
//            holder.tvCommoQCMasterScore.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if (hasFocus) {
//                        //得到焦点
//                        sp = context.getSharedPreferences("my_sp", 0);
//                        String qcmaster = sp.getString("CommodityQCMasterScore", "");
//                        System.out.print(qcmaster);
//                        spUtils.put(context, "debugQCMasterScore", qcmaster);
//                        TextWatcher TvQCMasterScore = new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                                Log.d(TAG, "beforeTextChanged");
//                            }
//
//                            @Override
//                            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                                Log.d(TAG, "onTextChanged");
//                            }
//
//                            @Override
//                            public void afterTextChanged(Editable s) {
//                                Log.d(TAG, "afterTextChanged");
//                                String proitem = holder.tvCommoQCMasterScore.getText().toString();
//                                getItem(position).setQCMasterScore(proitem);
////                                spUtils.put(context, "CommodityQCMasterScore", proitem);
//                            }
//                        };
//                        editTextQCMasterScore.addTextChangedListener(TvQCMasterScore);
//                        editTextQCMasterScore.setTag(TvQCMasterScore);
//                    } else {
//                        //失去焦点
//                        String qcmastertwoo = holder.tvCommoQCMasterScore.getText().toString();
//                        getItem(position).setQCMasterScore(qcmastertwoo);
//                        System.out.print(qcmastertwoo);
////                        spUtils.put(context, "CommodityQCMasterScore", qcmastertwoo);
//                    }
//                }
//            });
//            /*光标放置在文本最后*/
//            holder.tvCommoQCMasterScore.setSelection(holder.tvCommoQCMasterScore.length());

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
                                            getItem(position).setSealedrev(datetime);
//                                            spUtils.put(context, "dateSealedrewtimesign", datetime);
                                        }
                                    }
                                });
                        datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL,
                                "清除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        holder.tvCommoSealedrev.setText("");
                                        getItem(position).setSealedrev("");
//                                        spUtils.put(context, "dateSealedrewtimesign", "");
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
                                    getItem(position).setDocback(datetime);
//                                    spUtils.put(context, "dateDocbacktimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoDocback.setText("");
                                    holder.tvCommoDocback.setText("");
//                                    spUtils.put(context, "dateDocbacktimesign", "");
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
                    getItem(position).setPreMemo(proitem);
//                    spUtils.put(context, "CommodityPreMemo", proitem);
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
                                    getItem(position).setPredocdt(datetime);
//                                    spUtils.put(context, "datePredocdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPredocdt.setText("");
                                    getItem(position).setPredocdt("");
//                                    spUtils.put(context, "datePredocdttimesign", "");
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
                                    getItem(position).setPredocdt(datetime);
//                                    spUtils.put(context, "datePredtimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPred.setText("");
                                    getItem(position).setPredocdt("");
//                                    spUtils.put(context, "datePredtimesign", "");
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
                    getItem(position).setFabricsok(proitem);
//                    spUtils.put(context, "CommodityFabricsok", proitem);
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
                    getItem(position).setAccessoriesok(proitem);
//                    spUtils.put(context, "CommodityAccessoriesok", proitem);
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
                    getItem(position).setSpcproDec(proitem);
//                    spUtils.put(context, "CommoditySpcproDec", proitem);
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
                    getItem(position).setSpcproMemo(proitem);
//                    spUtils.put(context, "CommoditySpcproMemo", proitem);
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
                    getItem(position).setCutqty(proitem);
//                    spUtils.put(context, "CommodityCutqty", proitem);
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
                                    getItem(position).setSewFdt(datetime);
//                                    spUtils.put(context, "dateSewFdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewFdt.setText("");
                                    getItem(position).setSewFdt("");
//                                    spUtils.put(context, "dateSewFdttimesign", "");
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
                                    getItem(position).setSewMdt(datetime);
//                                    spUtils.put(context, "dateSewMdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoSewMdt.setText("");
                                    getItem(position).setSewMdt("");
//                                    spUtils.put(context, "dateSewMdttimesign", "");
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
                                    getItem(position).setPrebdt(datetime);
//                                    spUtils.put(context, "datePrebdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPrebdt.setText("");
                                    getItem(position).setPrebdt("");
//                                    spUtils.put(context, "datePrebdttimesign", "");
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
                                    getItem(position).setQCbdt(datetime);
//                                    spUtils.put(context, "dateQCbdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCbdt.setText("");
                                    getItem(position).setQCbdt("");
//                                    spUtils.put(context, "dateQCbdttimesign", "");
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
                                    getItem(position).setPremdt(datetime);
//                                    spUtils.put(context, "datePremdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPremdt.setText("");
                                    getItem(position).setPremdt("");
//                                    spUtils.put(context, "datePremdttimesign", "");
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
                                    getItem(position).setQCmdt(datetime);
//                                    spUtils.put(context, "dateQCmdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCmdt.setText("");
                                    getItem(position).setQCmdt("");
//                                    spUtils.put(context, "dateQCmdttimesign", "");
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
                                    getItem(position).setPreedt(datetime);
//                                    spUtils.put(context, "datePreedttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPreedt.setText("");
                                    getItem(position).setPreedt("");
//                                    spUtils.put(context, "datePreedttimesign", "");
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
                                    getItem(position).setQCMedt(datetime);
//                                    spUtils.put(context, "dateQCMedttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQCMedt.setText("");
                                    getItem(position).setQCMedt("");
//                                    spUtils.put(context, "dateQCMedttimesign", "");
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
                                    getItem(position).setFctmdt(datetime);
//                                    spUtils.put(context, "dateFctmdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctmdt.setText("");
                                    getItem(position).setFctmdt("");
//                                    spUtils.put(context, "dateFctmdttimesign", "");
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
                                    getItem(position).setFctedt(datetime);
//                                    spUtils.put(context, "dateFctedttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFctedt.setText("");
                                    getItem(position).setFctedt("");
//                                    spUtils.put(context, "dateFctedttimesign", "");
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
                                    getItem(position).setPackbdat(datetime);
//                                    spUtils.put(context, "datePackbdattimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoPackbdat.setText("");
                                    getItem(position).setPackbdat("");
//                                    spUtils.put(context, "datePackbdattimesign", "");
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
                    getItem(position).setPackqty2(proitem);
//                    spUtils.put(context, "CommodityPackqty2", proitem);
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
                    getItem(position).setQCMemo(proitem);
//                    spUtils.put(context, "CommodityQCMemo", proitem);
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
                                    getItem(position).setFactlcdat(datetime);
//                                    spUtils.put(context, "dateFactlcdattimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoFactlcdat.setText("");
                                    getItem(position).setFactlcdat("");
//                                    spUtils.put(context, "dateFactlcdattimesign", "");
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
                    getItem(position).setBatchid(proitem);
//                    spUtils.put(context, "CommodityBatchid", proitem);
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
                                    getItem(position).setCtmchkdt(datetime);
//                                    spUtils.put(context, "dateCtmchkdttimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoCtmchkdt.setText("");
                                    getItem(position).setCtmchkdt("");
//                                    spUtils.put(context, "dateCtmchkdttimesign", "");
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
                    getItem(position).setIPQCPedt(proitem);
//                    spUtils.put(context, "CommodityIPQCPedt", proitem);
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
                    getItem(position).setIPQCmdt(proitem);
//                    spUtils.put(context, "CommodityIPQCmdt", proitem);
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
                    getItem(position).setQAname(proitem);
//                    spUtils.put(context, "CommodityQAname", proitem);
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
                    getItem(position).setQAScore(proitem);
//                    spUtils.put(context, "CommodityQAScore", proitem);
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
                                    getItem(position).setQAMemo(datetime);
//                                    spUtils.put(context, "dateQAMemotimesign", datetime);
                                }
                            });
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                            , "清除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.tvCommoQAMemo.setText("");
                                    getItem(position).setQAMemo("");
//                                    spUtils.put(context, "dateQAMemotimesign", "");
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
                    getItem(position).setPredoc(proitem);
//                    spUtils.put(context, "CommodityPredoc", proitem);
                }
            };
            editTextPredoc.addTextChangedListener(TvPredoc);
            editTextPredoc.setTag(TvPredoc);
            /*光标放置在文本最后*/
            holder.tvCommoPredoc.setSelection(holder.tvCommoPredoc.length());


            holder.tvCommoQCbdtDoc.setEnabled(true);
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
                    getItem(position).setQCbdtDoc(proitem);
//                    spUtils.put(context, "CommodityQCbdtDoc", proitem);
                }
            };
            editTextQCbdtDoc.addTextChangedListener(TvQCbdtDoc);
            editTextQCbdtDoc.setTag(TvQCbdtDoc);
            /*光标放置在文本最后*/
            holder.tvCommoQCbdtDoc.setSelection(holder.tvCommoQCbdtDoc.length());


            holder.tvCommoQCmdtDoc.setEnabled(true);
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
                    getItem(position).setQCmdtDoc(proitem);
//                    spUtils.put(context, "CommodityQCmdtDoc", proitem);
                }
            };
            editTextQCmdtDoc.addTextChangedListener(TvQCmdtDoc);
            editTextQCmdtDoc.setTag(TvQCmdtDoc);
            /*光标放置在文本最后*/
            holder.tvCommoQCmdtDoc.setSelection(holder.tvCommoQCmdtDoc.length());


            holder.tvCommoQCedtDoc.setEnabled(true);
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
                    getItem(position).setQCedtDoc(proitem);
//                    spUtils.put(context, "CommodityQCedtDoc", proitem);
                }
            };
            editTextQCedtDoc.addTextChangedListener(TvQCedtDoc);
            editTextQCedtDoc.setTag(TvQCedtDoc);
            /*光标放置在文本最后*/
            holder.tvCommoQCedtDoc.setSelection(holder.tvCommoQCedtDoc.length());
        } else {
//            holder.tvCommoItem.setEnabled(false);
//            holder.tvCommoItem.setText(getItem(position).getItem());
//            holder.tvCommoItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

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
            holder.tvCommoPred.setText(getItem(position).getPredocdt());
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
            editTextQAname.setText(getItem(position).getQAname());
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
                tvCommoDocback, tvCommoLcdat, tvCommoTaskqty, tvCommoPredocdt, tvCommoPred, tvCommoSewFdt, tvCommoSewMdt, tvCommoSubfactory, tvCommoPrebdt, tvCommoQCbdt, tvCommoPremdt, tvCommoQCmdt, tvCommoPreedt, tvCommoQCMedt, tvCommoFctmdt, tvCommoFctedt, tvCommoPackbdat, tvCommoFactlcdat, tvCommoCtmchkdt;
        EditText
                tvCommoQCMasterScore, tvCommoPreMemo,
                tvCommoPredoc, tvCommoFabricsok, tvCommoAccessoriesok,
                tvCommoSpcproDec, tvCommoSpcproMemo, tvCommoCutqty,
                tvCommoQCbdtDoc, tvCommoQCmdtDoc, tvCommoQCedtDoc, tvCommoPackqty2, tvCommoQCMemo, tvCommoBatchid,
                tvCommoIPQCPedt, tvCommoIPQCmdt, tvCommoQAname, tvCommoQAScore;
    }
}