package com.daoran.newfactory.onefactory.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

/**
 * Created by lizhipeng on 2017/4/26.
 */

public class CommoDialog extends Dialog {
    Activity content;
    private View.OnClickListener mClickListener, mCancleLinstener;
    private EditText etprodialogStyle, etprodialogFactory, etprodialogRecode, etprodialogProcedure;
    private CheckBox checkboxNull;
    private Button btnCancle, btnComfirm;
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

    private void getViews() {
        etprodialogStyle = (EditText) findViewById(R.id.etprodialogStyle);
        etprodialogFactory = (EditText) findViewById(R.id.etprodialogFactory);
        etprodialogRecode = (EditText) findViewById(R.id.etprodialogRecode);
        etprodialogProcedure = (EditText) findViewById(R.id.etprodialogProcedure);
        checkboxNull = (CheckBox) findViewById(R.id.checkboxNull);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);

    }

    private void initViews() {
        sp = content.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        Window dialogWindow = this.getWindow();
        WindowManager m = content.getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (display.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        String style = sp.getString("commoStyle", "");
        etprodialogStyle.setText(style);
        String factory = sp.getString("commoFactory", "");
        etprodialogFactory.setText(factory);
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
            spUtils.put(content, "commoFactory", textchanged);
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
}
