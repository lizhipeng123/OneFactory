package com.daoran.newfactory.onefactory.adapter;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.CommoditydetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;

import java.util.List;

/**
 * 查货跟踪适配
 * Created by lizhipeng on 2017/4/6.
 */

public class CommoditySqlAdapter extends BaseAdapter {
    private static final String TAG = "commotest";
    private Context context;
    private List<CommoditydetailBean.DataBean> dataBeen;
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
            holder.tvCommoItem = (EditText) convertView.findViewById(R.id.tvCommoItem);
            holder.tvCommoCtmtxt = (EditText) convertView.findViewById(R.id.tvCommoCtmtxt);
            holder.tvCommoPrddocumentary = (EditText) convertView.findViewById(R.id.tvCommoPrddocumentary);
            holder.tvCommoprdmaster = (EditText) convertView.findViewById(R.id.tvCommoprdmaster);
            holder.tvCommoQCMasterScore = (EditText) convertView.findViewById(R.id.tvCommoQCMasterScore);
            holder.tvCommoSealedrev = (EditText) convertView.findViewById(R.id.tvCommoSealedrev);
            holder.tvCommoDocback = (EditText) convertView.findViewById(R.id.tvCommoDocback);
            holder.tvCommoLcdat = (EditText) convertView.findViewById(R.id.tvCommoLcdat);
            holder.tvCommoTaskqty = (EditText) convertView.findViewById(R.id.tvCommoTaskqty);
            holder.tvCommoPreMemo = (EditText) convertView.findViewById(R.id.tvCommoPreMemo);
            holder.tvCommoPredocdt = (EditText) convertView.findViewById(R.id.tvCommoPredocdt);
            holder.tvCommoPred = (EditText) convertView.findViewById(R.id.tvCommoPred);
            holder.tvCommoPredoc = (EditText) convertView.findViewById(R.id.tvCommoPredoc);
            holder.tvCommoFabricsok = (EditText) convertView.findViewById(R.id.tvCommoFabricsok);
            holder.tvCommoAccessoriesok = (EditText) convertView.findViewById(R.id.tvCommoAccessoriesok);
            holder.tvCommoSpcproDec = (EditText) convertView.findViewById(R.id.tvCommoSpcproDec);
            holder.tvCommoSpcproMemo = (EditText) convertView.findViewById(R.id.tvCommoSpcproMemo);
            holder.tvCommoCutqty = (EditText) convertView.findViewById(R.id.tvCommoCutqty);
            holder.tvCommoSewFdt = (EditText) convertView.findViewById(R.id.tvCommoSewFdt);
            holder.tvCommoSewMdt = (EditText) convertView.findViewById(R.id.tvCommoSewMdt);
            holder.tvCommoSubfactory = (EditText) convertView.findViewById(R.id.tvCommoSubfactory);
            holder.tvCommoPrebdt = (EditText) convertView.findViewById(R.id.tvCommoPrebdt);
            holder.tvCommoQCbdt = (EditText) convertView.findViewById(R.id.tvCommoQCbdt);
            holder.tvCommoQCbdtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCbdtDoc);
            holder.tvCommoPremdt = (EditText) convertView.findViewById(R.id.tvCommoPremdt);
            holder.tvCommoQCmdt = (EditText) convertView.findViewById(R.id.tvCommoQCmdt);
            holder.tvCommoQCmdtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCmdtDoc);
            holder.tvCommoPreedt = (EditText) convertView.findViewById(R.id.tvCommoPreedt);
            holder.tvCommoQCMedt = (EditText) convertView.findViewById(R.id.tvCommoQCMedt);
            holder.tvCommoQCedtDoc = (EditText) convertView.findViewById(R.id.tvCommoQCedtDoc);
            holder.tvCommoFctmdt = (EditText) convertView.findViewById(R.id.tvCommoFctmdt);
            holder.tvCommoFctedt = (EditText) convertView.findViewById(R.id.tvCommoFctedt);
            holder.tvCommoPackbdat = (EditText) convertView.findViewById(R.id.tvCommoPackbdat);
            holder.tvCommoPackqty2 = (EditText) convertView.findViewById(R.id.tvCommoPackqty2);
            holder.tvCommoQCMemo = (EditText) convertView.findViewById(R.id.tvCommoQCMemo);
            holder.tvCommoFactlcdat = (EditText) convertView.findViewById(R.id.tvCommoFactlcdat);
            holder.tvCommoBatchid = (EditText) convertView.findViewById(R.id.tvCommoBatchid);
            holder.tvCommoOurAfter = (TextView) convertView.findViewById(R.id.tvCommoOurAfter);
            holder.tvCommoCtmchkdt = (EditText) convertView.findViewById(R.id.tvCommoCtmchkdt);
            holder.tvCommoIPQCPedt = (EditText) convertView.findViewById(R.id.tvCommoIPQCPedt);
            holder.tvCommoIPQCmdt = (EditText) convertView.findViewById(R.id.tvCommoIPQCmdt);
            holder.tvCommoQAname = (EditText) convertView.findViewById(R.id.tvCommoQAname);
            holder.tvCommoQAScore = (EditText) convertView.findViewById(R.id.tvCommoQAScore);
            holder.tvCommoQAMemo = (EditText) convertView.findViewById(R.id.tvCommoQAMemo);
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
        /*判断item中制单人是否是登录用户，是为可改，否为不可改*/
        sp = context.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        String nameid = sp.getString("username", "");
        String documentary = getItem(position).getPrddocumentary();
        String master = getItem(position).getPrdmaster();

        if (documentary != null && !documentary.equals("")) {
            if (documentary.equals("吕玉如") || master.equals(nameid)) {

                holder.tvCommoItem.setEnabled(true);
                final EditText editTextItem = holder.tvCommoItem;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextItem.getTag() instanceof TextWatcher) {
                    editTextItem.removeTextChangedListener((TextWatcher) editTextItem.getTag());
                }
                editTextItem.setText(getItem(position).getItem());
                editTextItem.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvItem = new TextWatcher() {
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
                        String proitem = holder.tvCommoItem.getText().toString();
                        spUtils.put(context, "CommodityItem", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextItem.addTextChangedListener(TvItem);
                editTextItem.setTag(TvItem);
            /*光标放置在文本最后*/
                holder.tvCommoItem.setSelection(holder.tvCommoItem.length());


                holder.tvCommoCtmtxt.setEnabled(true);
                final EditText editTextCtmtxt = holder.tvCommoCtmtxt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextCtmtxt.getTag() instanceof TextWatcher) {
                    editTextCtmtxt.removeTextChangedListener((TextWatcher) editTextCtmtxt.getTag());
                }
                editTextCtmtxt.setText(getItem(position).getCtmtxt());
                editTextCtmtxt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvCtmtxt = new TextWatcher() {
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
                        String proitem = holder.tvCommoCtmtxt.getText().toString();
                        spUtils.put(context, "CommodityCtmtxt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextCtmtxt.addTextChangedListener(TvCtmtxt);
                editTextCtmtxt.setTag(TvCtmtxt);
            /*光标放置在文本最后*/
                holder.tvCommoCtmtxt.setSelection(holder.tvCommoCtmtxt.length());


                holder.tvCommoPrddocumentary.setEnabled(true);
                final EditText editTextPrddocumentary = holder.tvCommoPrddocumentary;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPrddocumentary.getTag() instanceof TextWatcher) {
                    editTextPrddocumentary.removeTextChangedListener((TextWatcher) editTextPrddocumentary.getTag());
                }
                editTextPrddocumentary.setText(getItem(position).getPrddocumentary());
                editTextPrddocumentary.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvPrddocumentary = new TextWatcher() {
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
                        String proitem = holder.tvCommoPrddocumentary.getText().toString();
                        spUtils.put(context, "CommodityPrddocumentary", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextPrddocumentary.addTextChangedListener(TvPrddocumentary);
                editTextPrddocumentary.setTag(TvPrddocumentary);
            /*光标放置在文本最后*/
                holder.tvCommoPrddocumentary.setSelection(holder.tvCommoPrddocumentary.length());


                holder.tvCommoprdmaster.setEnabled(true);
                final EditText editTextprdmaster = holder.tvCommoprdmaster;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextprdmaster.getTag() instanceof TextWatcher) {
                    editTextprdmaster.removeTextChangedListener((TextWatcher) editTextprdmaster.getTag());
                }
                editTextprdmaster.setText(getItem(position).getPrdmaster());
                editTextprdmaster.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher Tvprdmaster = new TextWatcher() {
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
                        String proitem = holder.tvCommoprdmaster.getText().toString();
                        spUtils.put(context, "Commodityprdmaster", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextprdmaster.addTextChangedListener(Tvprdmaster);
                editTextprdmaster.setTag(Tvprdmaster);
            /*光标放置在文本最后*/
                holder.tvCommoprdmaster.setSelection(holder.tvCommoprdmaster.length());


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
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQCMasterScore.addTextChangedListener(TvQCMasterScore);
                editTextQCMasterScore.setTag(TvQCMasterScore);
            /*光标放置在文本最后*/
                holder.tvCommoQCMasterScore.setSelection(holder.tvCommoQCMasterScore.length());


                holder.tvCommoSealedrev.setEnabled(true);
                final EditText editTextSealedrev = holder.tvCommoSealedrev;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSealedrev.getTag() instanceof TextWatcher) {
                    editTextSealedrev.removeTextChangedListener((TextWatcher) editTextSealedrev.getTag());
                }
                editTextSealedrev.setText(getItem(position).getSealedrev());
                editTextSealedrev.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvSealedrev = new TextWatcher() {
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
                        String proitem = holder.tvCommoSealedrev.getText().toString();
                        spUtils.put(context, "CommoditySealedrev", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextSealedrev.addTextChangedListener(TvSealedrev);
                editTextSealedrev.setTag(TvSealedrev);
            /*光标放置在文本最后*/
                holder.tvCommoSealedrev.setSelection(holder.tvCommoSealedrev.length());


                holder.tvCommoDocback.setEnabled(true);
                final EditText editTextDocback = holder.tvCommoDocback;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextDocback.getTag() instanceof TextWatcher) {
                    editTextDocback.removeTextChangedListener((TextWatcher) editTextDocback.getTag());
                }
                editTextDocback.setText(getItem(position).getDocback());
                editTextDocback.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvDocback = new TextWatcher() {
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
                        String proitem = holder.tvCommoDocback.getText().toString();
                        spUtils.put(context, "CommodityDocback", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextDocback.addTextChangedListener(TvDocback);
                editTextDocback.setTag(TvDocback);
            /*光标放置在文本最后*/
                holder.tvCommoDocback.setSelection(holder.tvCommoDocback.length());


                holder.tvCommoLcdat.setEnabled(true);
                final EditText editTextLcdat = holder.tvCommoLcdat;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextLcdat.getTag() instanceof TextWatcher) {
                    editTextLcdat.removeTextChangedListener((TextWatcher) editTextLcdat.getTag());
                }
                editTextLcdat.setText(getItem(position).getLcdat());
                editTextLcdat.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvLcdat = new TextWatcher() {
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
                        String proitem = holder.tvCommoLcdat.getText().toString();
                        spUtils.put(context, "CommodityLcdat", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextLcdat.addTextChangedListener(TvLcdat);
                editTextLcdat.setTag(TvLcdat);
            /*光标放置在文本最后*/
                holder.tvCommoLcdat.setSelection(holder.tvCommoLcdat.length());


                holder.tvCommoTaskqty.setEnabled(true);
                final EditText editTextTaskqty = holder.tvCommoTaskqty;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextTaskqty.getTag() instanceof TextWatcher) {
                    editTextTaskqty.removeTextChangedListener((TextWatcher) editTextTaskqty.getTag());
                }
                editTextTaskqty.setText(getItem(position).getTaskqty());
                editTextTaskqty.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvTaskqty = new TextWatcher() {
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
                        String proitem = holder.tvCommoTaskqty.getText().toString();
                        spUtils.put(context, "CommodityTaskqty", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextTaskqty.addTextChangedListener(TvTaskqty);
                editTextTaskqty.setTag(TvTaskqty);
            /*光标放置在文本最后*/
                holder.tvCommoTaskqty.setSelection(holder.tvCommoTaskqty.length());


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
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextPreMemo.addTextChangedListener(TvPreMemo);
                editTextPreMemo.setTag(TvPreMemo);
            /*光标放置在文本最后*/
                holder.tvCommoPreMemo.setSelection(holder.tvCommoPreMemo.length());


                holder.tvCommoPredocdt.setEnabled(true);
                final EditText editTextPredocdt = holder.tvCommoPredocdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPredocdt.getTag() instanceof TextWatcher) {
                    editTextPredocdt.removeTextChangedListener((TextWatcher) editTextPredocdt.getTag());
                }
                editTextPredocdt.setText(getItem(position).getPredocdt());
                editTextPredocdt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvPredocdt = new TextWatcher() {
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
                        String proitem = holder.tvCommoPredocdt.getText().toString();
                        spUtils.put(context, "CommodityPredocdt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextPredocdt.addTextChangedListener(TvPredocdt);
                editTextPredocdt.setTag(TvPredocdt);
            /*光标放置在文本最后*/
                holder.tvCommoPredocdt.setSelection(holder.tvCommoPredocdt.length());


                holder.tvCommoPred.setEnabled(true);
                final EditText editTextPred = holder.tvCommoPred;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPred.getTag() instanceof TextWatcher) {
                    editTextPred.removeTextChangedListener((TextWatcher) editTextPred.getTag());
                }
                editTextPred.setText(getItem(position).getPredocdt());
                editTextPred.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvPred = new TextWatcher() {
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
                        String proitem = holder.tvCommoPred.getText().toString();
                        spUtils.put(context, "CommodityPred", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextPred.addTextChangedListener(TvPred);
                editTextPred.setTag(TvPred);
            /*光标放置在文本最后*/
                holder.tvCommoPred.setSelection(holder.tvCommoPred.length());


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
                        ToastUtils.ShowToastMessage(proitem, context);
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
                        ToastUtils.ShowToastMessage(proitem, context);
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
                        ToastUtils.ShowToastMessage(proitem, context);
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
                        ToastUtils.ShowToastMessage(proitem, context);
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
                        ToastUtils.ShowToastMessage(proitem, context);
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
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextCutqty.addTextChangedListener(TvCutqty);
                editTextCutqty.setTag(TvCutqty);
            /*光标放置在文本最后*/
                holder.tvCommoCutqty.setSelection(holder.tvCommoCutqty.length());


                holder.tvCommoSewFdt.setEnabled(true);
                final EditText editTextSewFdt = holder.tvCommoSewFdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSewFdt.getTag() instanceof TextWatcher) {
                    editTextSewFdt.removeTextChangedListener((TextWatcher) editTextSewFdt.getTag());
                }
                editTextSewFdt.setText(getItem(position).getSewFdt());
                editTextSewFdt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvSewFdt = new TextWatcher() {
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
                        String proitem = holder.tvCommoSewFdt.getText().toString();
                        spUtils.put(context, "CommoditySewFdt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextSewFdt.addTextChangedListener(TvSewFdt);
                editTextSewFdt.setTag(TvSewFdt);
            /*光标放置在文本最后*/
                holder.tvCommoSewFdt.setSelection(holder.tvCommoSewFdt.length());


                holder.tvCommoSewMdt.setEnabled(true);
                final EditText editTextSewMdt = holder.tvCommoSewMdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSewMdt.getTag() instanceof TextWatcher) {
                    editTextSewMdt.removeTextChangedListener((TextWatcher) editTextSewMdt.getTag());
                }
                editTextSewMdt.setText(getItem(position).getSewMdt());
                editTextSewMdt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvSewMdt = new TextWatcher() {
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
                        String proitem = holder.tvCommoSewMdt.getText().toString();
                        spUtils.put(context, "CommoditySewMdt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextSewMdt.addTextChangedListener(TvSewMdt);
                editTextSewMdt.setTag(TvSewMdt);
            /*光标放置在文本最后*/
                holder.tvCommoSewMdt.setSelection(holder.tvCommoSewMdt.length());


                holder.tvCommoSubfactory.setEnabled(true);
                final EditText editTextSubfactory = holder.tvCommoSubfactory;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSubfactory.getTag() instanceof TextWatcher) {
                    editTextSubfactory.removeTextChangedListener((TextWatcher) editTextSubfactory.getTag());
                }
                editTextSubfactory.setText(getItem(position).getSubfactory());
                editTextSubfactory.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvSubfactory = new TextWatcher() {
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
                        String proitem = holder.tvCommoSubfactory.getText().toString();
                        spUtils.put(context, "CommoditySubfactory", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextSubfactory.addTextChangedListener(TvSubfactory);
                editTextSubfactory.setTag(TvSubfactory);
            /*光标放置在文本最后*/
                holder.tvCommoSubfactory.setSelection(holder.tvCommoSubfactory.length());


                holder.tvCommoPrebdt.setEnabled(true);
                final EditText editTextPrebdt = holder.tvCommoPrebdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPrebdt.getTag() instanceof TextWatcher) {
                    editTextPrebdt.removeTextChangedListener((TextWatcher) editTextPrebdt.getTag());
                }
                editTextPrebdt.setText(getItem(position).getPrebdt());
                editTextPrebdt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvPrebdt = new TextWatcher() {
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
                        String proitem = holder.tvCommoPrebdt.getText().toString();
                        spUtils.put(context, "CommodityPrebdt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextPrebdt.addTextChangedListener(TvPrebdt);
                editTextPrebdt.setTag(TvPrebdt);
            /*光标放置在文本最后*/
                holder.tvCommoPrebdt.setSelection(holder.tvCommoPrebdt.length());


                holder.tvCommoQCbdt.setEnabled(true);
                final EditText editTextQCbdt = holder.tvCommoQCbdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCbdt.getTag() instanceof TextWatcher) {
                    editTextQCbdt.removeTextChangedListener((TextWatcher) editTextQCbdt.getTag());
                }
                editTextQCbdt.setText(getItem(position).getQCbdt());
                editTextQCbdt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvQCbdt = new TextWatcher() {
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
                        String proitem = holder.tvCommoQCbdt.getText().toString();
                        spUtils.put(context, "CommodityQCbdt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQCbdt.addTextChangedListener(TvQCbdt);
                editTextQCbdt.setTag(TvQCbdt);
            /*光标放置在文本最后*/
                holder.tvCommoQCbdt.setSelection(holder.tvCommoQCbdt.length());


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
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQCbdtDoc.addTextChangedListener(TvQCbdtDoc);
                editTextQCbdtDoc.setTag(TvQCbdtDoc);
            /*光标放置在文本最后*/
                holder.tvCommoQCbdtDoc.setSelection(holder.tvCommoQCbdtDoc.length());


                holder.tvCommoPremdt.setEnabled(true);
                final EditText editTextPremdt = holder.tvCommoPremdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPremdt.getTag() instanceof TextWatcher) {
                    editTextPremdt.removeTextChangedListener((TextWatcher) editTextPremdt.getTag());
                }
                editTextPremdt.setText(getItem(position).getPremdt());
                editTextPremdt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvPremdt = new TextWatcher() {
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
                        String proitem = holder.tvCommoPremdt.getText().toString();
                        spUtils.put(context, "CommodityPremdt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextPremdt.addTextChangedListener(TvPremdt);
                editTextPremdt.setTag(TvPremdt);
            /*光标放置在文本最后*/
                holder.tvCommoPremdt.setSelection(holder.tvCommoPremdt.length());


                holder.tvCommoQCmdt.setEnabled(true);
                final EditText editTextQCmdt = holder.tvCommoQCmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCmdt.getTag() instanceof TextWatcher) {
                    editTextQCmdt.removeTextChangedListener((TextWatcher) editTextQCmdt.getTag());
                }
                editTextQCmdt.setText(getItem(position).getQCmdt());
                editTextQCmdt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvQCmdt = new TextWatcher() {
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
                        String proitem = holder.tvCommoQCmdt.getText().toString();
                        spUtils.put(context, "CommodityQCmdt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQCmdt.addTextChangedListener(TvQCmdt);
                editTextQCmdt.setTag(TvQCmdt);
            /*光标放置在文本最后*/
                holder.tvCommoQCmdt.setSelection(holder.tvCommoQCmdt.length());


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
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQCmdtDoc.addTextChangedListener(TvQCmdtDoc);
                editTextQCmdtDoc.setTag(TvQCmdtDoc);
            /*光标放置在文本最后*/
                holder.tvCommoQCmdtDoc.setSelection(holder.tvCommoQCmdtDoc.length());


                holder.tvCommoPreedt.setEnabled(true);
                final EditText editTextPreedt = holder.tvCommoPreedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPreedt.getTag() instanceof TextWatcher) {
                    editTextPreedt.removeTextChangedListener((TextWatcher) editTextPreedt.getTag());
                }
                editTextPreedt.setText(getItem(position).getPreedt());
                editTextPreedt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvPreedt = new TextWatcher() {
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
                        String proitem = holder.tvCommoPreedt.getText().toString();
                        spUtils.put(context, "CommodityPreedt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextPreedt.addTextChangedListener(TvPreedt);
                editTextPreedt.setTag(TvPreedt);
            /*光标放置在文本最后*/
                holder.tvCommoPreedt.setSelection(holder.tvCommoPreedt.length());


                holder.tvCommoQCMedt.setEnabled(true);
                final EditText editTextQCMedt = holder.tvCommoQCMedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCMedt.getTag() instanceof TextWatcher) {
                    editTextQCMedt.removeTextChangedListener((TextWatcher) editTextQCMedt.getTag());
                }
                editTextQCMedt.setText(getItem(position).getQCMedt());
                editTextQCMedt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvQCMedt = new TextWatcher() {
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
                        String proitem = holder.tvCommoQCMedt.getText().toString();
                        spUtils.put(context, "CommodityQCMedt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQCMedt.addTextChangedListener(TvQCMedt);
                editTextQCMedt.setTag(TvQCMedt);
            /*光标放置在文本最后*/
                holder.tvCommoQCMedt.setSelection(holder.tvCommoQCMedt.length());


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
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQCedtDoc.addTextChangedListener(TvQCedtDoc);
                editTextQCedtDoc.setTag(TvQCedtDoc);
            /*光标放置在文本最后*/
                holder.tvCommoQCedtDoc.setSelection(holder.tvCommoQCedtDoc.length());


                holder.tvCommoFctmdt.setEnabled(true);
                final EditText editTextFctmdt = holder.tvCommoFctmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextFctmdt.getTag() instanceof TextWatcher) {
                    editTextFctmdt.removeTextChangedListener((TextWatcher) editTextFctmdt.getTag());
                }
                editTextFctmdt.setText(getItem(position).getFctmdt());
                editTextFctmdt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvFctmdt = new TextWatcher() {
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
                        String proitem = holder.tvCommoFctmdt.getText().toString();
                        spUtils.put(context, "CommodityFctmdt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextFctmdt.addTextChangedListener(TvFctmdt);
                editTextFctmdt.setTag(TvFctmdt);
            /*光标放置在文本最后*/
                holder.tvCommoFctmdt.setSelection(holder.tvCommoFctmdt.length());


                holder.tvCommoFctedt.setEnabled(true);
                final EditText editTextFctedt = holder.tvCommoFctedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextFctedt.getTag() instanceof TextWatcher) {
                    editTextFctedt.removeTextChangedListener((TextWatcher) editTextFctedt.getTag());
                }
                editTextFctedt.setText(getItem(position).getFctedt());
                editTextFctedt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvFctedt = new TextWatcher() {
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
                        String proitem = holder.tvCommoFctedt.getText().toString();
                        spUtils.put(context, "CommodityFctedt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextFctedt.addTextChangedListener(TvFctedt);
                editTextFctedt.setTag(TvFctedt);
            /*光标放置在文本最后*/
                holder.tvCommoFctedt.setSelection(holder.tvCommoFctedt.length());


                holder.tvCommoPackbdat.setEnabled(true);
                final EditText editTextPackbdat = holder.tvCommoPackbdat;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPackbdat.getTag() instanceof TextWatcher) {
                    editTextPackbdat.removeTextChangedListener((TextWatcher) editTextPackbdat.getTag());
                }
                editTextPackbdat.setText(getItem(position).getPackbdat());
                editTextPackbdat.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvPackbdat = new TextWatcher() {
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
                        String proitem = holder.tvCommoPackbdat.getText().toString();
                        spUtils.put(context, "CommodityPackbdat", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextPackbdat.addTextChangedListener(TvPackbdat);
                editTextPackbdat.setTag(TvPackbdat);
            /*光标放置在文本最后*/
                holder.tvCommoPackbdat.setSelection(holder.tvCommoPackbdat.length());


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
                        ToastUtils.ShowToastMessage(proitem, context);
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
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQCMemo.addTextChangedListener(TvQCMemo);
                editTextQCMemo.setTag(TvQCMemo);
            /*光标放置在文本最后*/
                holder.tvCommoQCMemo.setSelection(holder.tvCommoQCMemo.length());


                holder.tvCommoFactlcdat.setEnabled(true);
                final EditText editTextFactlcdat = holder.tvCommoFactlcdat;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextFactlcdat.getTag() instanceof TextWatcher) {
                    editTextFactlcdat.removeTextChangedListener((TextWatcher) editTextFactlcdat.getTag());
                }
                editTextFactlcdat.setText(getItem(position).getFactlcdat());
                editTextFactlcdat.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvFactlcdat = new TextWatcher() {
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
                        String proitem = holder.tvCommoFactlcdat.getText().toString();
                        spUtils.put(context, "CommodityFactlcdat", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextFactlcdat.addTextChangedListener(TvFactlcdat);
                editTextFactlcdat.setTag(TvFactlcdat);
            /*光标放置在文本最后*/
                holder.tvCommoFactlcdat.setSelection(holder.tvCommoFactlcdat.length());


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
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextBatchid.addTextChangedListener(TvBatchid);
                editTextBatchid.setTag(TvBatchid);
            /*光标放置在文本最后*/
                holder.tvCommoBatchid.setSelection(holder.tvCommoBatchid.length());


                holder.tvCommoOurAfter.setEnabled(true);

                holder.tvCommoCtmchkdt.setEnabled(true);
                final EditText editTextCtmchkdt = holder.tvCommoCtmchkdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextCtmchkdt.getTag() instanceof TextWatcher) {
                    editTextCtmchkdt.removeTextChangedListener((TextWatcher) editTextCtmchkdt.getTag());
                }
                editTextCtmchkdt.setText(getItem(position).getCtmchkdt());
                editTextCtmchkdt.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvCtmchkdt = new TextWatcher() {
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
                        String proitem = holder.tvCommoCtmchkdt.getText().toString();
                        spUtils.put(context, "CommodityCtmchkdt", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextCtmchkdt.addTextChangedListener(TvCtmchkdt);
                editTextCtmchkdt.setTag(TvCtmchkdt);
            /*光标放置在文本最后*/
                holder.tvCommoCtmchkdt.setSelection(holder.tvCommoCtmchkdt.length());


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
                        ToastUtils.ShowToastMessage(proitem, context);
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
                        ToastUtils.ShowToastMessage(proitem, context);
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
                        ToastUtils.ShowToastMessage(proitem, context);
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
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQAScore.addTextChangedListener(TvQAScore);
                editTextQAScore.setTag(TvQAScore);
            /*光标放置在文本最后*/
                holder.tvCommoQAScore.setSelection(holder.tvCommoQAScore.length());


                holder.tvCommoQAMemo.setEnabled(true);
                final EditText editTextQAMemo = holder.tvCommoQAMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQAMemo.getTag() instanceof TextWatcher) {
                    editTextQAMemo.removeTextChangedListener((TextWatcher) editTextQAMemo.getTag());
                }
                editTextQAMemo.setText(getItem(position).getQAMemo());
                editTextQAMemo.setOnTouchListener(new View.OnTouchListener() {
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
                TextWatcher TvQAMemo = new TextWatcher() {
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
                        String proitem = holder.tvCommoQAMemo.getText().toString();
                        spUtils.put(context, "CommodityQAMemo", proitem);
                        ToastUtils.ShowToastMessage(proitem, context);
                    }
                };
                editTextQAMemo.addTextChangedListener(TvQAMemo);
                editTextQAMemo.setTag(TvQAMemo);
            /*光标放置在文本最后*/
                holder.tvCommoQAMemo.setSelection(holder.tvCommoQAMemo.length());


            } else {
                holder.tvCommoItem.setEnabled(false);
                final EditText editTextItem = holder.tvCommoItem;
                if (editTextItem.getTag() instanceof TextWatcher) {
                    editTextItem.removeTextChangedListener((TextWatcher) editTextItem.getTag());
                }
                editTextItem.setText(getItem(position).getItem());

                holder.tvCommoCtmtxt.setEnabled(false);
                final EditText editTextCtmtxt = holder.tvCommoCtmtxt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextCtmtxt.getTag() instanceof TextWatcher) {
                    editTextCtmtxt.removeTextChangedListener((TextWatcher) editTextCtmtxt.getTag());
                }
                editTextCtmtxt.setText(getItem(position).getCtmtxt());

                holder.tvCommoPrddocumentary.setEnabled(false);
                final EditText editTextPrddocumentary = holder.tvCommoPrddocumentary;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPrddocumentary.getTag() instanceof TextWatcher) {
                    editTextPrddocumentary.removeTextChangedListener((TextWatcher) editTextPrddocumentary.getTag());
                }
                editTextPrddocumentary.setText(getItem(position).getPrddocumentary());

                holder.tvCommoprdmaster.setEnabled(false);
                final EditText editTextprdmaster = holder.tvCommoprdmaster;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextprdmaster.getTag() instanceof TextWatcher) {
                    editTextprdmaster.removeTextChangedListener((TextWatcher) editTextprdmaster.getTag());
                }
                editTextprdmaster.setText(getItem(position).getPrdmaster());

                holder.tvCommoQCMasterScore.setEnabled(false);
                final EditText editTextQCMasterScore = holder.tvCommoQCMasterScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCMasterScore.getTag() instanceof TextWatcher) {
                    editTextQCMasterScore.removeTextChangedListener((TextWatcher) editTextQCMasterScore.getTag());
                }
                editTextQCMasterScore.setText(getItem(position).getQCMasterScore());

                holder.tvCommoSealedrev.setEnabled(false);
                final EditText editTextSealedrev = holder.tvCommoSealedrev;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSealedrev.getTag() instanceof TextWatcher) {
                    editTextSealedrev.removeTextChangedListener((TextWatcher) editTextSealedrev.getTag());
                }
                editTextSealedrev.setText(getItem(position).getSealedrev());

                holder.tvCommoDocback.setEnabled(false);
                final EditText editTextDocback = holder.tvCommoDocback;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextDocback.getTag() instanceof TextWatcher) {
                    editTextDocback.removeTextChangedListener((TextWatcher) editTextDocback.getTag());
                }
                editTextDocback.setText(getItem(position).getDocback());

                holder.tvCommoLcdat.setEnabled(false);
                final EditText editTextLcdat = holder.tvCommoLcdat;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextLcdat.getTag() instanceof TextWatcher) {
                    editTextLcdat.removeTextChangedListener((TextWatcher) editTextLcdat.getTag());
                }
                editTextLcdat.setText(getItem(position).getLcdat());

                holder.tvCommoTaskqty.setEnabled(false);
                final EditText editTextTaskqty = holder.tvCommoTaskqty;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextTaskqty.getTag() instanceof TextWatcher) {
                    editTextTaskqty.removeTextChangedListener((TextWatcher) editTextTaskqty.getTag());
                }
                editTextTaskqty.setText(getItem(position).getTaskqty());

                holder.tvCommoPreMemo.setEnabled(false);
                final EditText editTextPreMemo = holder.tvCommoPreMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPreMemo.getTag() instanceof TextWatcher) {
                    editTextPreMemo.removeTextChangedListener((TextWatcher) editTextPreMemo.getTag());
                }
                editTextPreMemo.setText(getItem(position).getPreMemo());

                holder.tvCommoPredocdt.setEnabled(false);
                final EditText editTextPredocdt = holder.tvCommoPredocdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPredocdt.getTag() instanceof TextWatcher) {
                    editTextPredocdt.removeTextChangedListener((TextWatcher) editTextPredocdt.getTag());
                }
                editTextPredocdt.setText(getItem(position).getPredocdt());

                holder.tvCommoPred.setEnabled(false);
                final EditText editTextPred = holder.tvCommoPred;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPred.getTag() instanceof TextWatcher) {
                    editTextPred.removeTextChangedListener((TextWatcher) editTextPred.getTag());
                }
                editTextPred.setText(getItem(position).getPredocdt());

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
                final EditText editTextSewFdt = holder.tvCommoSewFdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSewFdt.getTag() instanceof TextWatcher) {
                    editTextSewFdt.removeTextChangedListener((TextWatcher) editTextSewFdt.getTag());
                }
                editTextSewFdt.setText(getItem(position).getSewFdt());

                holder.tvCommoSewMdt.setEnabled(false);
                final EditText editTextSewMdt = holder.tvCommoSewMdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSewMdt.getTag() instanceof TextWatcher) {
                    editTextSewMdt.removeTextChangedListener((TextWatcher) editTextSewMdt.getTag());
                }
                editTextSewMdt.setText(getItem(position).getSewMdt());

                holder.tvCommoSubfactory.setEnabled(false);
                final EditText editTextSubfactory = holder.tvCommoSubfactory;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextSubfactory.getTag() instanceof TextWatcher) {
                    editTextSubfactory.removeTextChangedListener((TextWatcher) editTextSubfactory.getTag());
                }
                editTextSubfactory.setText(getItem(position).getSubfactory());

                holder.tvCommoPrebdt.setEnabled(false);
                final EditText editTextPrebdt = holder.tvCommoPrebdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPrebdt.getTag() instanceof TextWatcher) {
                    editTextPrebdt.removeTextChangedListener((TextWatcher) editTextPrebdt.getTag());
                }
                editTextPrebdt.setText(getItem(position).getPrebdt());

                holder.tvCommoQCbdt.setEnabled(false);
                final EditText editTextQCbdt = holder.tvCommoQCbdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCbdt.getTag() instanceof TextWatcher) {
                    editTextQCbdt.removeTextChangedListener((TextWatcher) editTextQCbdt.getTag());
                }
                editTextQCbdt.setText(getItem(position).getQCbdt());

                holder.tvCommoQCbdtDoc.setEnabled(false);
                final EditText editTextQCbdtDoc = holder.tvCommoQCbdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCbdtDoc.getTag() instanceof TextWatcher) {
                    editTextQCbdtDoc.removeTextChangedListener((TextWatcher) editTextQCbdtDoc.getTag());
                }
                editTextQCbdtDoc.setText(getItem(position).getQCbdtDoc());

                holder.tvCommoPremdt.setEnabled(false);
                final EditText editTextPremdt = holder.tvCommoPremdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPremdt.getTag() instanceof TextWatcher) {
                    editTextPremdt.removeTextChangedListener((TextWatcher) editTextPremdt.getTag());
                }
                editTextPremdt.setText(getItem(position).getPremdt());

                holder.tvCommoQCmdt.setEnabled(false);
                final EditText editTextQCmdt = holder.tvCommoQCmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCmdt.getTag() instanceof TextWatcher) {
                    editTextQCmdt.removeTextChangedListener((TextWatcher) editTextQCmdt.getTag());
                }
                editTextQCmdt.setText(getItem(position).getQCmdt());

                holder.tvCommoQCmdtDoc.setEnabled(false);
                final EditText editTextQCmdtDoc = holder.tvCommoQCmdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCmdtDoc.getTag() instanceof TextWatcher) {
                    editTextQCmdtDoc.removeTextChangedListener((TextWatcher) editTextQCmdtDoc.getTag());
                }
                editTextQCmdtDoc.setText(getItem(position).getQCmdtDoc());

                holder.tvCommoPreedt.setEnabled(false);
                final EditText editTextPreedt = holder.tvCommoPreedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPreedt.getTag() instanceof TextWatcher) {
                    editTextPreedt.removeTextChangedListener((TextWatcher) editTextPreedt.getTag());
                }
                editTextPreedt.setText(getItem(position).getPreedt());

                holder.tvCommoQCMedt.setEnabled(false);
                final EditText editTextQCMedt = holder.tvCommoQCMedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCMedt.getTag() instanceof TextWatcher) {
                    editTextQCMedt.removeTextChangedListener((TextWatcher) editTextQCMedt.getTag());
                }
                editTextQCMedt.setText(getItem(position).getQCMedt());

                holder.tvCommoQCedtDoc.setEnabled(false);
                final EditText editTextQCedtDoc = holder.tvCommoQCedtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQCedtDoc.getTag() instanceof TextWatcher) {
                    editTextQCedtDoc.removeTextChangedListener((TextWatcher) editTextQCedtDoc.getTag());
                }
                editTextQCedtDoc.setText(getItem(position).getQCedtDoc());

                holder.tvCommoFctmdt.setEnabled(false);
                final EditText editTextFctmdt = holder.tvCommoFctmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextFctmdt.getTag() instanceof TextWatcher) {
                    editTextFctmdt.removeTextChangedListener((TextWatcher) editTextFctmdt.getTag());
                }
                editTextFctmdt.setText(getItem(position).getFctmdt());

                holder.tvCommoFctedt.setEnabled(false);
                final EditText editTextFctedt = holder.tvCommoFctedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextFctedt.getTag() instanceof TextWatcher) {
                    editTextFctedt.removeTextChangedListener((TextWatcher) editTextFctedt.getTag());
                }
                editTextFctedt.setText(getItem(position).getFctedt());

                holder.tvCommoPackbdat.setEnabled(false);
                final EditText editTextPackbdat = holder.tvCommoPackbdat;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextPackbdat.getTag() instanceof TextWatcher) {
                    editTextPackbdat.removeTextChangedListener((TextWatcher) editTextPackbdat.getTag());
                }
                editTextPackbdat.setText(getItem(position).getPackbdat());

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
                final EditText editTextFactlcdat = holder.tvCommoFactlcdat;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextFactlcdat.getTag() instanceof TextWatcher) {
                    editTextFactlcdat.removeTextChangedListener((TextWatcher) editTextFactlcdat.getTag());
                }
                editTextFactlcdat.setText(getItem(position).getFactlcdat());

                holder.tvCommoBatchid.setEnabled(false);
                final EditText editTextBatchid = holder.tvCommoBatchid;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextBatchid.getTag() instanceof TextWatcher) {
                    editTextBatchid.removeTextChangedListener((TextWatcher) editTextBatchid.getTag());
                }
                editTextBatchid.setText(getItem(position).getBatchid());

                holder.tvCommoOurAfter.setEnabled(false);

                holder.tvCommoCtmchkdt.setEnabled(false);
                final EditText editTextCtmchkdt = holder.tvCommoCtmchkdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextCtmchkdt.getTag() instanceof TextWatcher) {
                    editTextCtmchkdt.removeTextChangedListener((TextWatcher) editTextCtmchkdt.getTag());
                }
                editTextCtmchkdt.setText(getItem(position).getCtmchkdt());

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
                final EditText editTextQAMemo = holder.tvCommoQAMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                if (editTextQAMemo.getTag() instanceof TextWatcher) {
                    editTextQAMemo.removeTextChangedListener((TextWatcher) editTextQAMemo.getTag());
                }
                editTextQAMemo.setText(getItem(position).getQAMemo());

            }

        } else {
            holder.tvCommoItem.setEnabled(false);
            final EditText editTextItem = holder.tvCommoItem;
            if (editTextItem.getTag() instanceof TextWatcher) {
                editTextItem.removeTextChangedListener((TextWatcher) editTextItem.getTag());
            }
            editTextItem.setText(getItem(position).getItem());

            holder.tvCommoCtmtxt.setEnabled(false);
            final EditText editTextCtmtxt = holder.tvCommoCtmtxt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextCtmtxt.getTag() instanceof TextWatcher) {
                editTextCtmtxt.removeTextChangedListener((TextWatcher) editTextCtmtxt.getTag());
            }
            editTextCtmtxt.setText(getItem(position).getCtmtxt());

            holder.tvCommoPrddocumentary.setEnabled(false);
            final EditText editTextPrddocumentary = holder.tvCommoPrddocumentary;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPrddocumentary.getTag() instanceof TextWatcher) {
                editTextPrddocumentary.removeTextChangedListener((TextWatcher) editTextPrddocumentary.getTag());
            }
            editTextPrddocumentary.setText(getItem(position).getPrddocumentary());

            holder.tvCommoprdmaster.setEnabled(false);
            final EditText editTextprdmaster = holder.tvCommoprdmaster;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextprdmaster.getTag() instanceof TextWatcher) {
                editTextprdmaster.removeTextChangedListener((TextWatcher) editTextprdmaster.getTag());
            }
            editTextprdmaster.setText(getItem(position).getPrdmaster());

            holder.tvCommoQCMasterScore.setEnabled(false);
            final EditText editTextQCMasterScore = holder.tvCommoQCMasterScore;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMasterScore.getTag() instanceof TextWatcher) {
                editTextQCMasterScore.removeTextChangedListener((TextWatcher) editTextQCMasterScore.getTag());
            }
            editTextQCMasterScore.setText(getItem(position).getQCMasterScore());

            holder.tvCommoSealedrev.setEnabled(false);
            final EditText editTextSealedrev = holder.tvCommoSealedrev;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSealedrev.getTag() instanceof TextWatcher) {
                editTextSealedrev.removeTextChangedListener((TextWatcher) editTextSealedrev.getTag());
            }
            editTextSealedrev.setText(getItem(position).getSealedrev());

            holder.tvCommoDocback.setEnabled(false);
            final EditText editTextDocback = holder.tvCommoDocback;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextDocback.getTag() instanceof TextWatcher) {
                editTextDocback.removeTextChangedListener((TextWatcher) editTextDocback.getTag());
            }
            editTextDocback.setText(getItem(position).getDocback());

            holder.tvCommoLcdat.setEnabled(false);
            final EditText editTextLcdat = holder.tvCommoLcdat;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextLcdat.getTag() instanceof TextWatcher) {
                editTextLcdat.removeTextChangedListener((TextWatcher) editTextLcdat.getTag());
            }
            editTextLcdat.setText(getItem(position).getLcdat());

            holder.tvCommoTaskqty.setEnabled(false);
            final EditText editTextTaskqty = holder.tvCommoTaskqty;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextTaskqty.getTag() instanceof TextWatcher) {
                editTextTaskqty.removeTextChangedListener((TextWatcher) editTextTaskqty.getTag());
            }
            editTextTaskqty.setText(getItem(position).getTaskqty());

            holder.tvCommoPreMemo.setEnabled(false);
            final EditText editTextPreMemo = holder.tvCommoPreMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPreMemo.getTag() instanceof TextWatcher) {
                editTextPreMemo.removeTextChangedListener((TextWatcher) editTextPreMemo.getTag());
            }
            editTextPreMemo.setText(getItem(position).getPreMemo());

            holder.tvCommoPredocdt.setEnabled(false);
            final EditText editTextPredocdt = holder.tvCommoPredocdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPredocdt.getTag() instanceof TextWatcher) {
                editTextPredocdt.removeTextChangedListener((TextWatcher) editTextPredocdt.getTag());
            }
            editTextPredocdt.setText(getItem(position).getPredocdt());

            holder.tvCommoPred.setEnabled(false);
            final EditText editTextPred = holder.tvCommoPred;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPred.getTag() instanceof TextWatcher) {
                editTextPred.removeTextChangedListener((TextWatcher) editTextPred.getTag());
            }
            editTextPred.setText(getItem(position).getPredocdt());

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
            final EditText editTextSewFdt = holder.tvCommoSewFdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSewFdt.getTag() instanceof TextWatcher) {
                editTextSewFdt.removeTextChangedListener((TextWatcher) editTextSewFdt.getTag());
            }
            editTextSewFdt.setText(getItem(position).getSewFdt());

            holder.tvCommoSewMdt.setEnabled(false);
            final EditText editTextSewMdt = holder.tvCommoSewMdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSewMdt.getTag() instanceof TextWatcher) {
                editTextSewMdt.removeTextChangedListener((TextWatcher) editTextSewMdt.getTag());
            }
            editTextSewMdt.setText(getItem(position).getSewMdt());

            holder.tvCommoSubfactory.setEnabled(false);
            final EditText editTextSubfactory = holder.tvCommoSubfactory;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextSubfactory.getTag() instanceof TextWatcher) {
                editTextSubfactory.removeTextChangedListener((TextWatcher) editTextSubfactory.getTag());
            }
            editTextSubfactory.setText(getItem(position).getSubfactory());

            holder.tvCommoPrebdt.setEnabled(false);
            final EditText editTextPrebdt = holder.tvCommoPrebdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPrebdt.getTag() instanceof TextWatcher) {
                editTextPrebdt.removeTextChangedListener((TextWatcher) editTextPrebdt.getTag());
            }
            editTextPrebdt.setText(getItem(position).getPrebdt());

            holder.tvCommoQCbdt.setEnabled(false);
            final EditText editTextQCbdt = holder.tvCommoQCbdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCbdt.getTag() instanceof TextWatcher) {
                editTextQCbdt.removeTextChangedListener((TextWatcher) editTextQCbdt.getTag());
            }
            editTextQCbdt.setText(getItem(position).getQCbdt());

            holder.tvCommoQCbdtDoc.setEnabled(false);
            final EditText editTextQCbdtDoc = holder.tvCommoQCbdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCbdtDoc.getTag() instanceof TextWatcher) {
                editTextQCbdtDoc.removeTextChangedListener((TextWatcher) editTextQCbdtDoc.getTag());
            }
            editTextQCbdtDoc.setText(getItem(position).getQCbdtDoc());

            holder.tvCommoPremdt.setEnabled(false);
            final EditText editTextPremdt = holder.tvCommoPremdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPremdt.getTag() instanceof TextWatcher) {
                editTextPremdt.removeTextChangedListener((TextWatcher) editTextPremdt.getTag());
            }
            editTextPremdt.setText(getItem(position).getPremdt());

            holder.tvCommoQCmdt.setEnabled(false);
            final EditText editTextQCmdt = holder.tvCommoQCmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCmdt.getTag() instanceof TextWatcher) {
                editTextQCmdt.removeTextChangedListener((TextWatcher) editTextQCmdt.getTag());
            }
            editTextQCmdt.setText(getItem(position).getQCmdt());

            holder.tvCommoQCmdtDoc.setEnabled(false);
            final EditText editTextQCmdtDoc = holder.tvCommoQCmdtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCmdtDoc.getTag() instanceof TextWatcher) {
                editTextQCmdtDoc.removeTextChangedListener((TextWatcher) editTextQCmdtDoc.getTag());
            }
            editTextQCmdtDoc.setText(getItem(position).getQCmdtDoc());

            holder.tvCommoPreedt.setEnabled(false);
            final EditText editTextPreedt = holder.tvCommoPreedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPreedt.getTag() instanceof TextWatcher) {
                editTextPreedt.removeTextChangedListener((TextWatcher) editTextPreedt.getTag());
            }
            editTextPreedt.setText(getItem(position).getPreedt());

            holder.tvCommoQCMedt.setEnabled(false);
            final EditText editTextQCMedt = holder.tvCommoQCMedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCMedt.getTag() instanceof TextWatcher) {
                editTextQCMedt.removeTextChangedListener((TextWatcher) editTextQCMedt.getTag());
            }
            editTextQCMedt.setText(getItem(position).getQCMedt());

            holder.tvCommoQCedtDoc.setEnabled(false);
            final EditText editTextQCedtDoc = holder.tvCommoQCedtDoc;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQCedtDoc.getTag() instanceof TextWatcher) {
                editTextQCedtDoc.removeTextChangedListener((TextWatcher) editTextQCedtDoc.getTag());
            }
            editTextQCedtDoc.setText(getItem(position).getQCedtDoc());

            holder.tvCommoFctmdt.setEnabled(false);
            final EditText editTextFctmdt = holder.tvCommoFctmdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFctmdt.getTag() instanceof TextWatcher) {
                editTextFctmdt.removeTextChangedListener((TextWatcher) editTextFctmdt.getTag());
            }
            editTextFctmdt.setText(getItem(position).getFctmdt());

            holder.tvCommoFctedt.setEnabled(false);
            final EditText editTextFctedt = holder.tvCommoFctedt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFctedt.getTag() instanceof TextWatcher) {
                editTextFctedt.removeTextChangedListener((TextWatcher) editTextFctedt.getTag());
            }
            editTextFctedt.setText(getItem(position).getFctedt());

            holder.tvCommoPackbdat.setEnabled(false);
            final EditText editTextPackbdat = holder.tvCommoPackbdat;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextPackbdat.getTag() instanceof TextWatcher) {
                editTextPackbdat.removeTextChangedListener((TextWatcher) editTextPackbdat.getTag());
            }
            editTextPackbdat.setText(getItem(position).getPackbdat());

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
            final EditText editTextFactlcdat = holder.tvCommoFactlcdat;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFactlcdat.getTag() instanceof TextWatcher) {
                editTextFactlcdat.removeTextChangedListener((TextWatcher) editTextFactlcdat.getTag());
            }
            editTextFactlcdat.setText(getItem(position).getFactlcdat());

            holder.tvCommoBatchid.setEnabled(false);
            final EditText editTextBatchid = holder.tvCommoBatchid;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextBatchid.getTag() instanceof TextWatcher) {
                editTextBatchid.removeTextChangedListener((TextWatcher) editTextBatchid.getTag());
            }
            editTextBatchid.setText(getItem(position).getBatchid());

            holder.tvCommoOurAfter.setEnabled(false);
            holder.tvCommoCtmchkdt.setEnabled(false);
            final EditText editTextCtmchkdt = holder.tvCommoCtmchkdt;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextCtmchkdt.getTag() instanceof TextWatcher) {
                editTextCtmchkdt.removeTextChangedListener((TextWatcher) editTextCtmchkdt.getTag());
            }
            editTextCtmchkdt.setText(getItem(position).getCtmchkdt());

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
            final EditText editTextQAMemo = holder.tvCommoQAMemo;
                /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextQAMemo.getTag() instanceof TextWatcher) {
                editTextQAMemo.removeTextChangedListener((TextWatcher) editTextQAMemo.getTag());
            }
            editTextQAMemo.setText(getItem(position).getQAMemo());

        }

        return convertView;
    }

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
        TextView tvCommoOurAfter;
        EditText tvCommoItem, tvCommoCtmtxt, tvCommoPrddocumentary, tvCommoprdmaster,
                tvCommoQCMasterScore, tvCommoSealedrev, tvCommoDocback, tvCommoLcdat,
                tvCommoTaskqty, tvCommoPreMemo, tvCommoPredocdt, tvCommoPred,
                tvCommoPredoc, tvCommoFabricsok, tvCommoAccessoriesok,
                tvCommoSpcproDec, tvCommoSpcproMemo, tvCommoCutqty, tvCommoSewFdt,
                tvCommoSewMdt, tvCommoSubfactory, tvCommoPrebdt, tvCommoQCbdt,
                tvCommoQCbdtDoc, tvCommoPremdt, tvCommoQCmdt, tvCommoQCmdtDoc,
                tvCommoPreedt, tvCommoQCMedt, tvCommoQCedtDoc, tvCommoFctmdt,
                tvCommoFctedt, tvCommoPackbdat, tvCommoPackqty2, tvCommoQCMemo,
                tvCommoFactlcdat, tvCommoBatchid, tvCommoCtmchkdt,
                tvCommoIPQCPedt, tvCommoIPQCmdt, tvCommoQAname, tvCommoQAScore,
                tvCommoQAMemo;
    }
}
