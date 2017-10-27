package com.daoran.newfactory.onefactory.view.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 签到条件查询dialog
 * Created by lizhipeng on 2017/4/20.
 */

public class SignContentDialog extends Dialog {
    Activity content;
    private TextView tvInitialDate, tvEndDate;//起始日期与结束日期
    private Button btnCancle, btnComfirm;//取消与确定按钮
    private View.OnClickListener mClickListener, mCancleLinstener;//确定与取消点击事件
    private SharedPreferences sp;//临时存储
    private SPUtils spUtils;
    private EditText etAudit;//制单人填写框

    public SignContentDialog(Context context) {
        super(context);
    }

    public SignContentDialog(Activity context, int theme, View.OnClickListener clickListener, View.OnClickListener mCancleLinstener) {
        super(context, theme);
        this.content = context;
        this.mClickListener = clickListener;
        this.mCancleLinstener = mCancleLinstener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.create_sign_dialog);
        getViews();
        initViews();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        tvInitialDate = (TextView) findViewById(R.id.tvInitialDate);
        tvEndDate = (TextView) findViewById(R.id.tvEndDate);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);
        etAudit = (EditText) findViewById(R.id.etAudit);
        setEditTextInhibitInputSpace(etAudit);
    }

    /**
     * 初始化加载
     */
    private void initViews() {
        sp = content.getSharedPreferences("my_sp", 0);
        Window dialogWindow = this.getWindow();
        WindowManager m = content.getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (display.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        String etaa = sp.getString("name", "");//制单人（登录人）
        etAudit.setText(etaa);
        etAudit.setSelection(etAudit.getText().length());
        etAudit.addTextChangedListener(textWatcher);
        String tvInitial = sp.getString("datetimesign", "");//起始日期
        tvInitialDate.setText(tvInitial);
        String tvend = sp.getString("endtimesign", "");//结束日期
        tvEndDate.setText(tvend);
        btnComfirm.setOnClickListener(mClickListener);
        btnCancle.setOnClickListener(mCancleLinstener);
        //起始日期点击选择日期
        tvInitialDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final DatePickerDialog datePickerDialog = new DatePickerDialog(
                        content, null, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                        , "完成", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatePicker datePicker = datePickerDialog.getDatePicker();
                                int year = datePicker.getYear();
                                int month = datePicker.getMonth();
                                int day = datePicker.getDayOfMonth();
                                String datetime = year + "/" + (month + 1) + "/" + day;
                                String etaudit = etAudit.getText().toString();
                                spUtils.put(content, "etaudit", etaudit);
                                tvInitialDate.setText(datetime);
                                spUtils.put(content, "datetimesign", datetime);
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                        , "清除", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvInitialDate.setText("");
                                spUtils.put(content, "datetimesign", "");
                                String etaudit = etAudit.getText().toString();
                                spUtils.put(content, "etaudit", "");
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                        , "取消", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                datePickerDialog.show();
            }
        });
        //结束日期点击选择日期
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final DatePickerDialog datePickerDialog = new DatePickerDialog(
                        content, null, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE
                        , "完成", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatePicker datePicker = datePickerDialog.getDatePicker();
                                int year = datePicker.getYear();
                                int month = datePicker.getMonth();
                                int day = datePicker.getDayOfMonth();
                                String datetime = year + "/" + (month + 1) + "/" + day;
                                String endtime = year + "/" + (month + 1) + "/" + day;
                                tvEndDate.setText(endtime);
                                spUtils.put(content, "endtimesign", endtime);
                                String etaudit = etAudit.getText().toString();
                                spUtils.put(content, "etaudit", etaudit);
                                System.out.print(endtime);
                                System.out.print(datetime);
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                        , "清除", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("BUTTON_NEGATIVE~~");
                                tvEndDate.setText("");
                                spUtils.put(content, "endtimesign", "");
                                String etaudit = etAudit.getText().toString();
                                spUtils.put(content, "etaudit", "");
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                        , "取消", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                datePickerDialog.show();
            }
        });
        this.setCancelable(true);
    }

    //监听制单人输入信息
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("-2-beforeTextChanged-->"
                    + etAudit.getText().toString() + "<--");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("-1-onTextChanged-->"
                    + etAudit.getText().toString() + "<--");
            String textchanged = etAudit.getText().toString();
            spUtils.put(content, "name", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etAudit.getText().toString() + "<--");
        }
    };
    /**
     * 禁止EditText输入空格
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (source.equals(" ")||source.equals("\n")||matcher.find())
                    return "";
                else
                    return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}