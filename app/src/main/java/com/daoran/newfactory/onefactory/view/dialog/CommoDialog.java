package com.daoran.newfactory.onefactory.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查货dialog弹出框且监听
 * Created by lizhipeng on 2017/4/26.
 */

public class CommoDialog extends Dialog {
    Activity content;
    private View.OnClickListener mClickListener, mCancleLinstener;
    private EditText etprodialogStyle, etprodialogFactory, etprodialogRecode, etprodialogProcedure;
    private CheckBox checkboxNull;
    private Button btnCancle, btnComfirm;
    private TextView tvCommoDialogcheck;
    private SharedPreferences sp;
    private SPUtils spUtils;

    public CommoDialog(Context context) {
        super(context);
    }

    public CommoDialog(Activity content, int theme, View.OnClickListener mClickListener, View.OnClickListener mCancleLinstener) {
        super(content, theme);
        this.mClickListener = mClickListener;
        this.mCancleLinstener = mCancleLinstener;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.create_commo_dialog);
        getViews();
        initViews();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        etprodialogStyle = (EditText) findViewById(R.id.etprodialogStyle);
        etprodialogFactory = (EditText) findViewById(R.id.etprodialogFactory);
        etprodialogRecode = (EditText) findViewById(R.id.etprodialogRecode);
        etprodialogProcedure = (EditText) findViewById(R.id.etprodialogProcedure);
        checkboxNull = (CheckBox) findViewById(R.id.checkboxNull);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);
        tvCommoDialogcheck = (TextView) findViewById(R.id.tvCommoDialogcheck);
        setEditTextInhibitInputSpace(etprodialogStyle);
        setEditTextInhibitInputSpace(etprodialogFactory);
        setEditTextInhibitInputSpace(etprodialogRecode);
        setEditTextInhibitInputSpace(etprodialogProcedure);
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        sp = content.getSharedPreferences("my_sp", 0);
        Window dialogWindow = this.getWindow();
        WindowManager m = content.getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (display.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        String style = sp.getString("commoStyle", "");
        etprodialogStyle.setText(style);
//        String factory = sp.getString("commoFactory", "");
        String factoryname = sp.getString("commoname","");
        etprodialogFactory.setText(factoryname);
        String recode = sp.getString("commoRecode", "");
        etprodialogRecode.setText(recode);
        String precedure = sp.getString("etproProcedure", "");
        etprodialogProcedure.setText(precedure);
        etprodialogStyle.addTextChangedListener(etproStyle);//款号
        etprodialogFactory.addTextChangedListener(etproFactory);//跟单
        etprodialogRecode.addTextChangedListener(etproRecode);//巡检
        etprodialogProcedure.addTextChangedListener(etproProcedure);//生产主管
        btnComfirm.setOnClickListener(mClickListener);
        btnCancle.setOnClickListener(mCancleLinstener);
        tvCommoDialogcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxNull.toggle();
                checkboxNull.isChecked();
                boolean ischeck = checkboxNull.isChecked();
                String is = String.valueOf(ischeck);
                spUtils.put(content,"ischeckedd",is);
            }
        });
        checkboxNull.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String is = String.valueOf(isChecked);
                spUtils.put(content,"ischeckedd",is);
            }
        });
        this.setCancelable(true);
    }

    /**
     * 监听款号输入信息
     */
    private TextWatcher etproStyle = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("-2-beforeTextChanged-->"
                    + etprodialogStyle.getText().toString() + "<--");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("-1-onTextChanged-->"
                    + etprodialogStyle.getText().toString() + "<--");
            String textchanged = etprodialogStyle.getText().toString();
            spUtils.put(content, "commoStyle", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogStyle.getText().toString() + "<--");
        }
    };

    /**
     * 监听跟单员输入信息
     */
    private TextWatcher etproFactory = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("-2-beforeTextChanged-->"
                    + etprodialogFactory.getText().toString() + "<--");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("-1-onTextChanged-->"
                    + etprodialogFactory.getText().toString() + "<--");
            String textchanged = etprodialogFactory.getText().toString();
            spUtils.put(content, "commoname", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogStyle.getText().toString() + "<--");
        }
    };

    /**
     * 监听巡检输入信息
     */
    private TextWatcher etproRecode = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("-2-beforeTextChanged-->"
                    + etprodialogRecode.getText().toString() + "<--");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("-1-onTextChanged-->"
                    + etprodialogRecode.getText().toString() + "<--");
            String textchanged = etprodialogRecode.getText().toString();
            spUtils.put(content, "commoRecode", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogRecode.getText().toString() + "<--");
        }
    };

    /**
     * 监听生产主管输入信息
     */
    private TextWatcher etproProcedure = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("-2-beforeTextChanged-->"
                    + etprodialogProcedure.getText().toString() + "<--");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("-1-onTextChanged-->"
                    + etprodialogProcedure.getText().toString() + "<--");
            String textchanged = etprodialogProcedure.getText().toString();
            spUtils.put(content, "etproProcedure", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogProcedure.getText().toString() + "<--");
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