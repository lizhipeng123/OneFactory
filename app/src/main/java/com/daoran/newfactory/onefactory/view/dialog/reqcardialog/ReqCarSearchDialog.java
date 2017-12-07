package com.daoran.newfactory.onefactory.view.dialog.reqcardialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用车申请单条件查询弹框
 * Created by lizhipeng on 2017/3/28.
 */

public class ReqCarSearchDialog extends Dialog {

    Activity content;
    private Spinner spinnerAudit;//审核状态选择spinner
    private TextView tvInitialDate, tvEndDate;
    private Button btnCancle, btnComfirm;//确认和取消按钮

    private View.OnClickListener mClickListener, mCancleLinstener;
    private AdapterView.OnItemClickListener spinnerListener;
    private SharedPreferences sp;
    private SPUtils spUtils;
    final int DATE_DIALOG = 1;

    public ReqCarSearchDialog(Activity context) {
        super(context);
        this.content = context;
    }

    //构造方法
    public ReqCarSearchDialog(Activity context, int theme, View.OnClickListener clickListener, View.OnClickListener mCancleLinstener) {
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

    //实例化控件
    private void getViews() {
        tvInitialDate = (TextView) findViewById(R.id.tvInitialDate);
        tvEndDate = (TextView) findViewById(R.id.tvEndDate);
        spinnerAudit = (Spinner) findViewById(R.id.spinnerAudit);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);
    }

    //初始化控件信息
    private void initViews() {
        sp = content.getSharedPreferences("my_sp", 0);
        Window dialogWindow = this.getWindow();
        WindowManager m = content.getWindowManager();
        String tvdate = sp.getString("ReqCarDateTime", "");
        tvInitialDate.setText(tvdate);
        String tvend = sp.getString("ReqCarEndTime", "");
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
                spUtils.put(content, "ReqCarState", lanages[position]);
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
                                String endtime = year + "/" + (month + 1) + "/" + day;
                                tvEndDate.setText(endtime);
                                spUtils.put(content, "ReqCarEndTime", endtime);
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                        , "清除", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvEndDate.setText("");
                                spUtils.put(content, "ReqCarEndTime", "");
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
                                spUtils.put(content, "ReqCarDateTime", datetime);
                            }
                        });
                datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL
                        , "清除", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvInitialDate.setText("");
                                spUtils.put(content, "ReqCarDateTime", "");
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