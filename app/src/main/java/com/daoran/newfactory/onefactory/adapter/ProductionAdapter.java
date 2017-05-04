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
import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;

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
    private SPUtils spUtils;
    private int index = -1;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        viewHolder.tvProDepartment.setText(getItem(position).getSubfactoryTeams());
        viewHolder.tvProProcedure.setText(getItem(position).getWorkingProcedure());
        viewHolder.tvProState.setText(getItem(position).getPrdstatus());
         /*判断item中制单人是否是登录用户，是为可改，否为不可改*/
        sp = context.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        String nameid = sp.getString("username", "");
        String recorder = getItem(position).getRecorder().toString();
        if (nameid == recorder || recorder.equals("test22")) {

            viewHolder.tv_data.setEnabled(true);
            final EditText editTextItem = viewHolder.tv_data;
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
                    String proitem = viewHolder.tv_data.getText().toString();
                    spUtils.put(context, "productionItem", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTextItem.addTextChangedListener(TvItem);
            editTextItem.setTag(TvItem);
            /*光标放置在文本最后*/
            viewHolder.tv_data.setSelection(viewHolder.tv_data.length());


            viewHolder.tvProDocumentary.setEnabled(true);
            final EditText editTextDocumentary = viewHolder.tvProDocumentary;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextDocumentary.getTag() instanceof TextWatcher) {
                editTextDocumentary.removeTextChangedListener((TextWatcher) editTextDocumentary.getTag());
            }
            editTextDocumentary.setText(getItem(position).getPrddocumentary());
            editTextDocumentary.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvDocumentary = new TextWatcher() {
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
                    String proitem = viewHolder.tvProDocumentary.getText().toString();
                    spUtils.put(context, "productionadapterDocumentary", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTextDocumentary.addTextChangedListener(TvDocumentary);
            editTextDocumentary.setTag(TvDocumentary);
            /*光标放置在文本最后*/
            viewHolder.tvProDocumentary.setSelection(viewHolder.tvProDocumentary.length());


            viewHolder.tvProFactory.setEnabled(true);
            final EditText editTextFactory = viewHolder.tvProFactory;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFactory.getTag() instanceof TextWatcher) {
                editTextFactory.removeTextChangedListener((TextWatcher) editTextFactory.getTag());
            }
            editTextFactory.setText(getItem(position).getSubfactory());
            editTextFactory.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvFactory = new TextWatcher() {
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
                    String proitem = viewHolder.tvProFactory.getText().toString();
                    spUtils.put(context, "productionFactory", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTextFactory.addTextChangedListener(TvFactory);
            editTextFactory.setTag(TvFactory);
            /*光标放置在文本最后*/
            viewHolder.tvProFactory.setSelection(viewHolder.tvProFactory.length());


            viewHolder.tvProDepartment.setEnabled(true);
            viewHolder.tvProProcedure.setEnabled(true);
            viewHolder.tvProOthers.setEnabled(true);
            final EditText editTexOthers = viewHolder.tvProOthers;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexOthers.getTag() instanceof TextWatcher) {
                editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
            }
            editTexOthers.setText(getItem(position).getWorkers());
            editTexOthers.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvOthers = new TextWatcher() {
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
                    String proitem = viewHolder.tvProOthers.getText().toString();
                    spUtils.put(context, "productionOthers", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexOthers.addTextChangedListener(TvOthers);
            editTexOthers.setTag(TvOthers);
            /*光标放置在文本最后*/
            viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.length());


            viewHolder.tvProSingularSystem.setEnabled(true);
            final EditText editTexSingularSystem = viewHolder.tvProSingularSystem;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexSingularSystem.getTag() instanceof TextWatcher) {
                editTexSingularSystem.removeTextChangedListener((TextWatcher) editTexSingularSystem.getTag());
            }
            editTexSingularSystem.setText(getItem(position).getPqty());
            editTexSingularSystem.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvSingularSystem = new TextWatcher() {
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
                    String proitem = viewHolder.tvProSingularSystem.getText().toString();
                    spUtils.put(context, "productionadapterSingularSystem", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexSingularSystem.addTextChangedListener(TvSingularSystem);
            editTexSingularSystem.setTag(TvSingularSystem);
            /*光标放置在文本最后*/
            viewHolder.tvProSingularSystem.setSelection(viewHolder.tvProSingularSystem.length());


            viewHolder.tvProColor.setEnabled(true);
            final EditText editTexColor = viewHolder.tvProColor;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexColor.getTag() instanceof TextWatcher) {
                editTexColor.removeTextChangedListener((TextWatcher) editTexColor.getTag());
            }
            editTexColor.setText(getItem(position).getProdcol());
            editTexColor.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvColor = new TextWatcher() {
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
                    String proitem = viewHolder.tvProColor.getText().toString();
                    spUtils.put(context, "productionColor", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexColor.addTextChangedListener(TvColor);
            editTexColor.setTag(TvColor);
            /*光标放置在文本最后*/
            viewHolder.tvProColor.setSelection(viewHolder.tvProColor.length());


            viewHolder.tvProTaskNumber.setEnabled(true);
            final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
            }
            editTexTaskNumber.setText(getItem(position).getTaskqty());
            editTexTaskNumber.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvTaskNumber = new TextWatcher() {
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
                    String proitem = viewHolder.tvProTaskNumber.getText().toString();
                    spUtils.put(context, "productionTaskNumber", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexTaskNumber.addTextChangedListener(TvTaskNumber);
            editTexTaskNumber.setTag(TvTaskNumber);
            /*光标放置在文本最后*/
            viewHolder.tvProTaskNumber.setSelection(viewHolder.tvProTaskNumber.length());


            viewHolder.tvProSize.setEnabled(true);
            final EditText editTexSize = viewHolder.tvProSize;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexSize.getTag() instanceof TextWatcher) {
                editTexSize.removeTextChangedListener((TextWatcher) editTexSize.getTag());
            }
            editTexSize.setText(getItem(position).getMdl());
            editTexSize.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvSize = new TextWatcher() {
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
                    String proitem = viewHolder.tvProSize.getText().toString();
                    spUtils.put(context, "productionSize", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexSize.addTextChangedListener(TvSize);
            editTexSize.setTag(TvSize);
            /*光标放置在文本最后*/
            viewHolder.tvProSize.setSelection(viewHolder.tvProSize.length());


            viewHolder.tvProClippingNumber.setEnabled(true);
            final EditText editTexClippingNumber = viewHolder.tvProClippingNumber;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexClippingNumber.getTag() instanceof TextWatcher) {
                editTexClippingNumber.removeTextChangedListener((TextWatcher) editTexClippingNumber.getTag());
            }
            editTexClippingNumber.setText(getItem(position).getFactcutqty());
            editTexClippingNumber.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvClippingNumber = new TextWatcher() {
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
                    String proitem = viewHolder.tvProClippingNumber.getText().toString();
                    spUtils.put(context, "productionClippingNumber", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexClippingNumber.addTextChangedListener(TvClippingNumber);
            editTexClippingNumber.setTag(TvClippingNumber);
            /*光标放置在文本最后*/
            viewHolder.tvProClippingNumber.setSelection(viewHolder.tvProClippingNumber.length());


            viewHolder.tvProCompletedLastMonth.setEnabled(true);
            final EditText editTexCompletedLastMonth = viewHolder.tvProCompletedLastMonth;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexCompletedLastMonth.getTag() instanceof TextWatcher) {
                editTexCompletedLastMonth.removeTextChangedListener((TextWatcher) editTexCompletedLastMonth.getTag());
            }
            editTexCompletedLastMonth.setText(getItem(position).getLastMonQty());
            editTexCompletedLastMonth.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionCompletedLastMonth", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexCompletedLastMonth.addTextChangedListener(TvCompletedLastMonth);
            editTexCompletedLastMonth.setTag(TvCompletedLastMonth);
            /*光标放置在文本最后*/
            viewHolder.tvProCompletedLastMonth.setSelection(viewHolder.tvProCompletedLastMonth.length());


            viewHolder.tvProTotalCompletion.setEnabled(true);
            final EditText editTexTotalCompletion = viewHolder.tvProTotalCompletion;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTotalCompletion.getTag() instanceof TextWatcher) {
                editTexTotalCompletion.removeTextChangedListener((TextWatcher) editTexTotalCompletion.getTag());
            }
            editTexTotalCompletion.setText(getItem(position).getSumCompletedQty());
            editTexTotalCompletion.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvTotalCompletion = new TextWatcher() {
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
                    String proitem = viewHolder.tvProTotalCompletion.getText().toString();
                    spUtils.put(context, "productionTotalCompletion", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexTotalCompletion.addTextChangedListener(TvTotalCompletion);
            editTexTotalCompletion.setTag(TvTotalCompletion);
            /*光标放置在文本最后*/
            viewHolder.tvProTotalCompletion.setSelection(viewHolder.tvProTotalCompletion.length());


            viewHolder.tvProBalanceAmount.setEnabled(true);
            final EditText editTexBalanceAmount = viewHolder.tvProBalanceAmount;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexBalanceAmount.getTag() instanceof TextWatcher) {
                editTexBalanceAmount.removeTextChangedListener((TextWatcher) editTexBalanceAmount.getTag());
            }
            editTexBalanceAmount.setText(getItem(position).getLeftQty());
            editTexBalanceAmount.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvBalanceAmount = new TextWatcher() {
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
                    String proitem = viewHolder.tvProBalanceAmount.getText().toString();
                    spUtils.put(context, "productionBalanceAmount", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexBalanceAmount.addTextChangedListener(TvBalanceAmount);
            editTexBalanceAmount.setTag(TvBalanceAmount);
            /*光标放置在文本最后*/
            viewHolder.tvProBalanceAmount.setSelection(viewHolder.tvProBalanceAmount.length());


            viewHolder.tvProState.setEnabled(true);
            viewHolder.tvProYear.setEnabled(true);
            final EditText editTexYear = viewHolder.tvProYear;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexYear.getTag() instanceof TextWatcher) {
                editTexYear.removeTextChangedListener((TextWatcher) editTexYear.getTag());
            }
            editTexYear.setText(getItem(position).getYear());
            editTexYear.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvYear = new TextWatcher() {
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
                    String proitem = viewHolder.tvProYear.getText().toString();
                    spUtils.put(context, "productionYear", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexYear.addTextChangedListener(TvYear);
            editTexYear.setTag(TvYear);
            /*光标放置在文本最后*/
            viewHolder.tvProYear.setSelection(viewHolder.tvProYear.length());


            viewHolder.tvProMonth.setEnabled(true);
            final EditText editTexMonth = viewHolder.tvProMonth;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexMonth.getTag() instanceof TextWatcher) {
                editTexMonth.removeTextChangedListener((TextWatcher) editTexMonth.getTag());
            }
            editTexMonth.setText(getItem(position).getMonth());
            editTexMonth.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvMonth = new TextWatcher() {
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
                    String proitem = viewHolder.tvProMonth.getText().toString();
                    spUtils.put(context, "productionMonth", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexMonth.addTextChangedListener(TvMonth);
            editTexMonth.setTag(TvMonth);
            /*光标放置在文本最后*/
            viewHolder.tvProMonth.setSelection(viewHolder.tvProMonth.length());


            viewHolder.tvProOneDay.setEnabled(true);
            final EditText editTexOneDay = viewHolder.tvProOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexOneDay.getTag() instanceof TextWatcher) {
                editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
            }
            editTexOneDay.setText(getItem(position).getDay1());
            editTexOneDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionOneDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwoDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwoDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexThreeDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionThreeDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexForeDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionForeDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexFiveDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionFiveDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexSixDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionSixDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexSevenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionSevenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexEightDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionEightDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexNineDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionNineDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexElevenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionElevenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwelveDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwelveDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexThirteenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionThirteenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexFourteenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionFourteenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexFifteenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionFifteenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexSixteenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionSixteenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexSeventeenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionSeventeenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexEighteenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionEighteenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexNineteenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionNineteenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentyDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentyDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentyOneDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentyOneDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentyTwoDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentyTwoDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentyThreeDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentyThreeDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentyForeDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentyForeDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentyFiveDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentyFiveDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentySixDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentySixDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentySevenDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentySevenDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentyEightDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentyEightDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexTwentyNineDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionTwentyNineDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexThirtyDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionThirtyDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexThirtyOneDay.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionThirtyOneDay", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
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
            editTexRemarks.setOnTouchListener(new View.OnTouchListener() {
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
                    spUtils.put(context, "productionRemarks", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexRemarks.addTextChangedListener(TvRemarks);
            editTexRemarks.setTag(TvRemarks);
            /*光标放置在文本最后*/
            viewHolder.tvProRemarks.setSelection(viewHolder.tvProRemarks.length());


            viewHolder.tvProRecorder.setEnabled(true);
            final EditText editTexRecorder = viewHolder.tvProRecorder;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexRecorder.getTag() instanceof TextWatcher) {
                editTexRecorder.removeTextChangedListener((TextWatcher) editTexRecorder.getTag());
            }
            editTexRecorder.setText(getItem(position).getRecorder());
            editTexRecorder.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvRecorder = new TextWatcher() {
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
                    String proitem = viewHolder.tvProRecorder.getText().toString();
                    spUtils.put(context, "productionRecorder", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexRecorder.addTextChangedListener(TvRecorder);
            editTexRecorder.setTag(TvRecorder);
            /*光标放置在文本最后*/
            viewHolder.tvProRecorder.setSelection(viewHolder.tvProRecorder.length());


            viewHolder.tvProRecordat.setEnabled(true);
            final EditText editTexRecordat = viewHolder.tvProRecordat;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexRecordat.getTag() instanceof TextWatcher) {
                editTexRecordat.removeTextChangedListener((TextWatcher) editTexRecordat.getTag());
            }
            editTexRecordat.setText(getItem(position).getRecordat());
            editTexRecordat.setOnTouchListener(new View.OnTouchListener() {
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
            TextWatcher TvRecordat = new TextWatcher() {
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
                    String proitem = viewHolder.tvProRecordat.getText().toString();
                    spUtils.put(context, "productionRecordat", proitem);
                    ToastUtils.ShowToastMessage(proitem, context);
                }
            };
            editTexRecordat.addTextChangedListener(TvRecordat);
            editTexRecordat.setTag(TvRecordat);
            /*光标放置在文本最后*/
            viewHolder.tvProRecordat.setSelection(viewHolder.tvProRecordat.length());

        } else {
            viewHolder.tv_data.setEnabled(false);
            final EditText editTextItem = viewHolder.tv_data;
            if (editTextItem.getTag() instanceof TextWatcher) {
                editTextItem.removeTextChangedListener((TextWatcher) editTextItem.getTag());
            }
            editTextItem.setText(getItem(position).getItem());


            viewHolder.tvProDocumentary.setEnabled(false);
            final EditText editTextDocumentary = viewHolder.tvProDocumentary;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextDocumentary.getTag() instanceof TextWatcher) {
                editTextDocumentary.removeTextChangedListener((TextWatcher) editTextDocumentary.getTag());
            }
            editTextDocumentary.setText(getItem(position).getPrddocumentary());


            viewHolder.tvProFactory.setEnabled(false);
            final EditText editTextFactory = viewHolder.tvProFactory;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTextFactory.getTag() instanceof TextWatcher) {
                editTextFactory.removeTextChangedListener((TextWatcher) editTextFactory.getTag());
            }
            editTextFactory.setText(getItem(position).getSubfactory());


            viewHolder.tvProDepartment.setEnabled(false);
            viewHolder.tvProProcedure.setEnabled(false);
            viewHolder.tvProOthers.setEnabled(false);
            final EditText editTexOthers = viewHolder.tvProOthers;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexOthers.getTag() instanceof TextWatcher) {
                editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
            }
            editTexOthers.setText(getItem(position).getWorkers());


            viewHolder.tvProSingularSystem.setEnabled(false);
            final EditText editTexSingularSystem = viewHolder.tvProSingularSystem;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexSingularSystem.getTag() instanceof TextWatcher) {
                editTexSingularSystem.removeTextChangedListener((TextWatcher) editTexSingularSystem.getTag());
            }
            editTexSingularSystem.setText(getItem(position).getPqty());


            viewHolder.tvProColor.setEnabled(false);
            final EditText editTexColor = viewHolder.tvProColor;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexColor.getTag() instanceof TextWatcher) {
                editTexColor.removeTextChangedListener((TextWatcher) editTexColor.getTag());
            }
            editTexColor.setText(getItem(position).getProdcol());


            viewHolder.tvProTaskNumber.setEnabled(false);
            final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
            }
            editTexTaskNumber.setText(getItem(position).getTaskqty());


            viewHolder.tvProSize.setEnabled(false);
            final EditText editTexSize = viewHolder.tvProSize;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexSize.getTag() instanceof TextWatcher) {
                editTexSize.removeTextChangedListener((TextWatcher) editTexSize.getTag());
            }
            editTexSize.setText(getItem(position).getMdl());


            viewHolder.tvProClippingNumber.setEnabled(false);
            final EditText editTexClippingNumber = viewHolder.tvProClippingNumber;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexClippingNumber.getTag() instanceof TextWatcher) {
                editTexClippingNumber.removeTextChangedListener((TextWatcher) editTexClippingNumber.getTag());
            }
            editTexClippingNumber.setText(getItem(position).getFactcutqty());


            viewHolder.tvProCompletedLastMonth.setEnabled(false);
            final EditText editTexCompletedLastMonth = viewHolder.tvProCompletedLastMonth;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexCompletedLastMonth.getTag() instanceof TextWatcher) {
                editTexCompletedLastMonth.removeTextChangedListener((TextWatcher) editTexCompletedLastMonth.getTag());
            }
            editTexCompletedLastMonth.setText(getItem(position).getLastMonQty());


            viewHolder.tvProTotalCompletion.setEnabled(false);
            final EditText editTexTotalCompletion = viewHolder.tvProTotalCompletion;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexTotalCompletion.getTag() instanceof TextWatcher) {
                editTexTotalCompletion.removeTextChangedListener((TextWatcher) editTexTotalCompletion.getTag());
            }
            editTexTotalCompletion.setText(getItem(position).getSumCompletedQty());


            viewHolder.tvProBalanceAmount.setEnabled(false);
            final EditText editTexBalanceAmount = viewHolder.tvProBalanceAmount;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexBalanceAmount.getTag() instanceof TextWatcher) {
                editTexBalanceAmount.removeTextChangedListener((TextWatcher) editTexBalanceAmount.getTag());
            }
            editTexBalanceAmount.setText(getItem(position).getLeftQty());


            viewHolder.tvProState.setEnabled(false);
            viewHolder.tvProYear.setEnabled(false);
            final EditText editTexYear = viewHolder.tvProYear;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexYear.getTag() instanceof TextWatcher) {
                editTexYear.removeTextChangedListener((TextWatcher) editTexYear.getTag());
            }
            editTexYear.setText(getItem(position).getYear());

            viewHolder.tvProMonth.setEnabled(false);
            final EditText editTexMonth = viewHolder.tvProMonth;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexMonth.getTag() instanceof TextWatcher) {
                editTexMonth.removeTextChangedListener((TextWatcher) editTexMonth.getTag());
            }
            editTexMonth.setText(getItem(position).getMonth());

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
            final EditText editTexRecorder = viewHolder.tvProRecorder;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexRecorder.getTag() instanceof TextWatcher) {
                editTexRecorder.removeTextChangedListener((TextWatcher) editTexRecorder.getTag());
            }
            editTexRecorder.setText(getItem(position).getRecorder());

            viewHolder.tvProRecordat.setEnabled(false);
            final EditText editTexRecordat = viewHolder.tvProRecordat;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
            if (editTexRecordat.getTag() instanceof TextWatcher) {
                editTexRecordat.removeTextChangedListener((TextWatcher) editTexRecordat.getTag());
            }
            editTexRecordat.setText(getItem(position).getRecordat());
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
                sp = context.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
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
                sp = context.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
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
                sp = context.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
                String title = item.getTitle().toString();
                spUtils.put(context, "proadapterPrdstatusTitle", title);
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
