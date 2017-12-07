package com.daoran.newfactory.onefactory.view.dialog.ftydldialog;

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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 生产日报条件查询dialog setOnCheckedChangeListener
 * Created by lizhipeng on 2017/4/26.
 */

public class FTYDLSearchDialog extends Dialog {
    Activity content;
    private View.OnClickListener mClickListener, mCancleLinstener;//确定和取消按钮点击事件
    private EditText etprodialogStyle, etprodialogFactory, etprodialogRecode;//需要填入的款号、工厂、制单人
    private CheckBox checkboxNull;//单选框
    private Button btnCancle, btnComfirm;//确定和取消按钮
    private Spinner tvprodialogProcedure;//工序下拉控件
    private TextView tvProDialogcheck;//是否可空
    private SharedPreferences sp;//存放临时数据
    private SPUtils spUtils;

    public FTYDLSearchDialog(Context context) {
        super(context);
    }

    public FTYDLSearchDialog(Activity content, int theme, View.OnClickListener mClickListener, View.OnClickListener mCancleLinstener) {
        super(content, theme);
        this.mClickListener = mClickListener;
        this.mCancleLinstener = mCancleLinstener;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.create_proction_dialog);
        getViews();
        initViews();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        etprodialogStyle = (EditText) findViewById(R.id.etprodialogStyle);//款号
        etprodialogFactory = (EditText) findViewById(R.id.etprodialogFactory);
        etprodialogRecode = (EditText) findViewById(R.id.etprodialogRecode);
        checkboxNull = (CheckBox) findViewById(R.id.checkboxNull);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);
        tvprodialogProcedure = (Spinner) findViewById(R.id.tvprodialogProcedure);
        tvProDialogcheck = (TextView) findViewById(R.id.tvProDialogcheck);
        setEditTextInhibitInputSpace(etprodialogStyle);
        setEditTextInhibitInputSpace(etprodialogFactory);
        setEditTextInhibitInputSpace(etprodialogRecode);
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
        String etaaname = sp.getString("proname", "");//登录页登录之后传过来的用户名
        etprodialogRecode.setText(etaaname);//将用户名填充制单人
        String Factory = sp.getString("etprodialogFactory", "");//工厂输入框监听传过来的输入信息
        etprodialogFactory.setText(Factory);//填充工厂
        String productionleftItem = sp.getString("productionleftItem", "");//查货跟踪长按传过来的款号
        String productionDialogitem = sp.getString("etprodialogStyle", "");//本dialog监听款号输入框穿过来的信息
        //如果查货跟踪穿过来的款号为空，则按照本地页面dialog监听的款号填充查询
        if (productionleftItem.equals("")) {
            etprodialogRecode.setText(etaaname);
            etprodialogStyle.setText(productionDialogitem);//填充款号到输入框
        } else {//如果不为空，则根据查货跟踪传过来的款号填充查询
            etprodialogRecode.setText("");
            etprodialogStyle.setText(productionleftItem);//填充款号到输入框
        }
        String[] spinnerProcedure = content.getResources().getStringArray(R.array.Procedure);
        //填充工序资源信息到spinner中
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>
                        (content, R.layout.adapter_mytopactionbar_spinner, spinnerProcedure) {
                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        if (convertView == null) {
                            convertView = getLayoutInflater().inflate(
                                    R.layout.adapter_mytopactionbar_spinner_item, parent, false);
                        }
                        TextView spinnerText = (TextView) convertView.findViewById(R.id.spinner_textView);
                        spinnerText.setText(getItem(position));
                        return convertView;
                    }
                };
        adapter.setDropDownViewResource(R.layout.adapter_mytopactionbar_spinner_item);
        tvprodialogProcedure.setAdapter(adapter);
        tvprodialogProcedure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = content.getResources().getStringArray(R.array.Procedure);
                spUtils.put(content, "Procedure", languages[position]);//将选择的工序保存到轻量级存储中
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etprodialogStyle.addTextChangedListener(textWatcher);
        etprodialogFactory.addTextChangedListener(etprodialog);
        etprodialogRecode.addTextChangedListener(etproRecode);
        btnComfirm.setOnClickListener(mClickListener);
        btnCancle.setOnClickListener(mCancleLinstener);
        /*是否可空点击文本改变checkbox状态*/
        tvProDialogcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxNull.toggle();
                checkboxNull.isChecked();
                boolean ischeck = checkboxNull.isChecked();
                String is = String.valueOf(ischeck);
                spUtils.put(content, "ischeckedd", is);
            }
        });
        checkboxNull.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String is = String.valueOf(isChecked);
                spUtils.put(content, "ischeckedd", is);
            }
        });
        this.setCancelable(true);
    }

    /**
     * 监听款号输入信息
     */
    private TextWatcher textWatcher = new TextWatcher() {
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
            spUtils.put(content, "etprodialogStyle", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogStyle.getText().toString() + "<--");
        }
    };

    /**
     * 监听工厂输入信息
     * 初始化的工厂信息是空的，在添加过之后，将信息保存到存储信息中，
     * 供procationActivity调用查询
     */
    private TextWatcher etprodialog = new TextWatcher() {
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
            spUtils.put(content, "etprodialogFactory", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogFactory.getText().toString() + "<--");
        }
    };

    /**
     * 监听制单人输入信息
     * 初始为当前登录用户，这里方法修改过制单人之后，
     * 将新添加的制单人替换到存储信息中，再传到procationActivity中进行查询
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
            spUtils.put(content, "proname", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogRecode.getText().toString() + "<--");
        }
    };

    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (source.equals(" ") || source.equals("\n") || matcher.find())
                    return "";
                else
                    return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}