package com.daoran.newfactory.onefactory.view.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
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
import com.daoran.newfactory.onefactory.util.ToastUtils;

import java.util.Calendar;

/**
 * Created by lizhipeng on 2017/4/20.
 */

public class SignContentDialog extends Dialog {

    Activity content;
    private TextView tvInitialDate, tvEndDate;
    private Button btnCancle, btnComfirm;
    private View.OnClickListener mClickListener, mCancleLinstener;
    private SharedPreferences sp;
    private SPUtils spUtils;
    private EditText etAudit;

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

    private void getViews() {
        tvInitialDate = (TextView) findViewById(R.id.tvInitialDate);
        tvEndDate = (TextView) findViewById(R.id.tvEndDate);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);
        etAudit = (EditText) findViewById(R.id.etAudit);
    }

    private void initViews() {
        sp = content.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        Window dialogWindow = this.getWindow();
        WindowManager m = content.getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (display.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        String etaa = sp.getString("name","");
        etAudit.setText(etaa);
        etAudit.addTextChangedListener(textWatcher);
        String tvInitial = sp.getString("datetimesign","");
        tvInitialDate.setText(tvInitial);
        String tvend = sp.getString("endtimesign","");
        tvEndDate.setText(tvend);
        btnComfirm.setOnClickListener(mClickListener);
        btnCancle.setOnClickListener(mCancleLinstener);

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
                                String etaudit= etAudit.getText().toString();
                                spUtils.put(content,"etaudit",etaudit);
                                tvInitialDate.setText(datetime);
                                spUtils.put(content, "datetimesign", datetime);
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                        , "取消", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvInitialDate.setText("");
                                spUtils.put(content, "datetimesign", "");
                                String etaudit= etAudit.getText().toString();
                                spUtils.put(content,"etaudit","");
//                                String tvinit = tvInitialDate.getText().toString();
//                                spUtils.put(content, "datetimesign", tvinit);
                            }
                        });
                datePickerDialog.show();
            }
        });
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
                                String etaudit= etAudit.getText().toString();
                                spUtils.put(content,"etaudit",etaudit);
                                System.out.print(endtime);
                                System.out.print(datetime);
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE
                        , "取消", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("BUTTON_NEGATIVE~~");
                                tvEndDate.setText("");
                                spUtils.put(content, "endtimesign", "");
                                String etaudit= etAudit.getText().toString();
                                spUtils.put(content,"etaudit","");
                            }
                        });
                datePickerDialog.show();
            }
        });
        this.setCancelable(true);
    }

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
            spUtils.put(content,"name",textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etAudit.getText().toString() + "<--");
        }
    };



}
