package com.daoran.newfactory.onefactory.view.dialog.qacworkdialog;

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

public class QACworkSearchDialog extends Dialog {
    Activity content;
    private View.OnClickListener mClickListener, mCancleLinstener;//监听确认和取消
    private EditText etprodialogItem, etprodialogPrddocumentary,
            etprodialogIPQC, etprodialogprdmaster;
    private CheckBox checkboxNull;//是否为空
    private Button btnCancle, btnComfirm;//确认和取消按钮
    private TextView tvCommoDialogcheck;
    private SharedPreferences sp;
    private SPUtils spUtils;

    public QACworkSearchDialog(Context context) {
        super(context);
    }

    public QACworkSearchDialog(Activity content, int theme, View.OnClickListener mClickListener, View.OnClickListener mCancleLinstener) {
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
        etprodialogItem = (EditText) findViewById(R.id.etprodialogStyle);
        etprodialogPrddocumentary = (EditText) findViewById(R.id.etprodialogFactory);
        etprodialogIPQC = (EditText) findViewById(R.id.etprodialogRecode);
        etprodialogprdmaster = (EditText) findViewById(R.id.etprodialogProcedure);
        checkboxNull = (CheckBox) findViewById(R.id.checkboxNull);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);
        tvCommoDialogcheck = (TextView) findViewById(R.id.tvCommoDialogcheck);
        setEditTextInhibitInputSpace(etprodialogItem);
        setEditTextInhibitInputSpace(etprodialogPrddocumentary);
        setEditTextInhibitInputSpace(etprodialogIPQC);
        setEditTextInhibitInputSpace(etprodialogprdmaster);
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
        String style = sp.getString("QACworkDialogItem", "");//款号
        etprodialogItem.setText(style);
//        String factory = sp.getString("commoFactory", "");
        //默认查询当前登录用户的相关信息
        String factoryname = sp.getString("QACworkDialogPrddocumentary","");//登录之后传过来的跟单
        etprodialogPrddocumentary.setText(factoryname);
        String recode = sp.getString("QACworkDialogIPQC", "");//巡检
        etprodialogIPQC.setText(recode);
        String precedure = sp.getString("QACworkDialogprdmaster", "");//生产主管
        etprodialogprdmaster.setText(precedure);
        etprodialogItem.addTextChangedListener(etQACworkItem);//款号
        etprodialogPrddocumentary.addTextChangedListener(etproPrddocumentary);//跟单
        etprodialogIPQC.addTextChangedListener(etproIPQC);//巡检
        etprodialogprdmaster.addTextChangedListener(etproProcedure);//生产主管
        btnComfirm.setOnClickListener(mClickListener);
        btnCancle.setOnClickListener(mCancleLinstener);
        tvCommoDialogcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxNull.toggle();
                checkboxNull.isChecked();
                boolean ischeck = checkboxNull.isChecked();
                String is = String.valueOf(ischeck);
                spUtils.put(content,"QACworkCheckedd",is);
            }
        });
        checkboxNull.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String is = String.valueOf(isChecked);
                spUtils.put(content,"FTYDLCheckedd",is);
            }
        });
        this.setCancelable(true);
    }

    /**
     * 监听款号输入信息
     */
    private TextWatcher etQACworkItem = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("-2-beforeTextChanged-->"
                    + etprodialogItem.getText().toString() + "<--");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("-1-onTextChanged-->"
                    + etprodialogItem.getText().toString() + "<--");
            String textchanged = etprodialogItem.getText().toString();
            spUtils.put(content, "QACworkDialogItem", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogItem.getText().toString() + "<--");
        }
    };

    /**
     * 监听跟单员输入信息
     */
    private TextWatcher etproPrddocumentary = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("-2-beforeTextChanged-->"
                    + etprodialogPrddocumentary.getText().toString() + "<--");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("-1-onTextChanged-->"
                    + etprodialogPrddocumentary.getText().toString() + "<--");
            String textchanged = etprodialogPrddocumentary.getText().toString();
            spUtils.put(content, "QACworkDialogPrddocumentary", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogPrddocumentary.getText().toString() + "<--");
        }
    };

    /**
     * 监听巡检输入信息
     */
    private TextWatcher etproIPQC = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("-2-beforeTextChanged-->"
                    + etprodialogIPQC.getText().toString() + "<--");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("-1-onTextChanged-->"
                    + etprodialogIPQC.getText().toString() + "<--");
            String textchanged = etprodialogIPQC.getText().toString();
            spUtils.put(content, "QACworkDialogIPQC", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogIPQC.getText().toString() + "<--");
        }
    };

    /**
     * 监听生产主管输入信息
     */
    private TextWatcher etproProcedure = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            System.out.println("-2-beforeTextChanged-->"
                    + etprodialogprdmaster.getText().toString() + "<--");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("-1-onTextChanged-->"
                    + etprodialogprdmaster.getText().toString() + "<--");
            String textchanged = etprodialogprdmaster.getText().toString();
            spUtils.put(content, "QACworkDialogprdmaster", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogprdmaster.getText().toString() + "<--");
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