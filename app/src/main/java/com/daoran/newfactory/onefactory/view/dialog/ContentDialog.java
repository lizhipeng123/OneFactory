package com.daoran.newfactory.onefactory.view.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;

import java.util.Calendar;

/**
 * 用车申请单条件查询弹框
 * Created by lizhipeng on 2017/3/28.
 */

public class ContentDialog extends Dialog {

    Activity content;
    private Spinner spinnerAudit;
    private TextView tvInitialDate, tvEndDate;
    private Button btnCancle, btnComfirm;

    private View.OnClickListener mClickListener, mCancleLinstener;
    private AdapterView.OnItemClickListener spinnerListener;
    private SharedPreferences sp;
    private SPUtils spUtils;
    final int DATE_DIALOG = 1;

    public ContentDialog(Activity context) {
        super(context);
        this.content = context;
    }

    public ContentDialog(Activity context, int theme, View.OnClickListener clickListener, View.OnClickListener mCancleLinstener) {
        super(context, theme);
        this.content = context;
        this.mClickListener = clickListener;
        this.mCancleLinstener = mCancleLinstener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.create_user_dialog);
        getViews();
        initViews();
    }

    private void getViews() {
        tvInitialDate = (TextView) findViewById(R.id.tvInitialDate);
        tvEndDate = (TextView) findViewById(R.id.tvEndDate);
        spinnerAudit = (Spinner) findViewById(R.id.spinnerAudit);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);
    }

    private void initViews() {
        sp = content.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        Window dialogWindow = this.getWindow();
        WindowManager m = content.getWindowManager();
        String tvdate = sp.getString("datetime", "");
        tvInitialDate.setText(tvdate);
        String tvend = sp.getString("endtime", "");
        tvEndDate.setText(tvend);
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (display.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        String[] mItems = getContext().getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(content, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(R.layout.dropdown_stytle);
        spinnerAudit.setAdapter(adapter);
        btnComfirm.setOnClickListener(mClickListener);
        btnCancle.setOnClickListener(mCancleLinstener);
        spinnerAudit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] lanages = content.getResources().getStringArray(R.array.languages);
                spUtils.put(content, "spinnerPosition", lanages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                                spUtils.put(content, "endtime", endtime);
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
                                spUtils.put(content, "endtime", "");
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
                                tvInitialDate.setText(datetime);
                                spUtils.put(content, "datetime", datetime);
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                        , "清除", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvInitialDate.setText("");
                                spUtils.put(content, "datetime", "");
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
}
