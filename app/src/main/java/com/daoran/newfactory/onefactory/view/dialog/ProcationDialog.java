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

/**
 *  生产日报条件查询dialog
 * Created by lizhipeng on 2017/4/26.
 */

public class ProcationDialog extends Dialog {
    Activity content;
    private View.OnClickListener mClickListener, mCancleLinstener;
    private EditText etprodialogStyle, etprodialogFactory, etprodialogRecode;
    private CheckBox checkboxNull;
    private Button btnCancle, btnComfirm;
    private Spinner tvprodialogProcedure;
    private SharedPreferences sp;
    private SPUtils spUtils;

    public ProcationDialog(Context context) {
        super(context);
    }

    public ProcationDialog(Activity content, int theme, View.OnClickListener mClickListener, View.OnClickListener mCancleLinstener) {
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

    private void getViews() {
        etprodialogStyle = (EditText) findViewById(R.id.etprodialogStyle);
        etprodialogFactory = (EditText) findViewById(R.id.etprodialogFactory);
        etprodialogRecode = (EditText) findViewById(R.id.etprodialogRecode);
        checkboxNull = (CheckBox) findViewById(R.id.checkboxNull);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);
        tvprodialogProcedure = (Spinner) findViewById(R.id.tvprodialogProcedure);
    }

    private void initViews() {
        sp = content.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        Window dialogWindow = this.getWindow();
        WindowManager m = content.getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (display.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        String etaa = sp.getString("etprodialogRecode","");
        etprodialogRecode.setText(etaa);
        String Factory = sp.getString("etprodialogFactory","");
        etprodialogFactory.setText(Factory);
        String style = sp.getString("etprodialogStyle","");
        etprodialogStyle.setText(style);
        String[] spinnerProcedure = content.getResources().getStringArray(R.array.Procedure);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>
                        (content, R.layout.adapter_mytopactionbar_spinner, spinnerProcedure){
                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        if(convertView==null){
                            convertView = getLayoutInflater().inflate(
                                    R.layout.adapter_mytopactionbar_spinner_item,parent,false);
                        }
                        TextView spinnerText=(TextView)convertView.findViewById(R.id.spinner_textView);
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
                spUtils.put(content, "Procedure", languages[position]);
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
            spUtils.put(content, "etprodialogRecode", textchanged);
        }

        @Override
        public void afterTextChanged(Editable s) {
            System.out.println("-3-afterTextChanged-->"
                    + etprodialogRecode.getText().toString() + "<--");
        }
    };
}
