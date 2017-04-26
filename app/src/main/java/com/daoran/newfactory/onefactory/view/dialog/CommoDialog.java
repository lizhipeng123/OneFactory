package com.daoran.newfactory.onefactory.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    private EditText etprodialogStyle,etprodialogFactory,etprodialogRecode
            ,etprodialogProcedure;
    private CheckBox checkboxNull;
    private Button btnCancle,btnComfirm;
    private SharedPreferences sp;
    private SPUtils spUtils;

    public CommoDialog(Context context) {
        super(context);
    }

    public CommoDialog(Activity content, int theme, View.OnClickListener mClickListener, View.OnClickListener mCancleLinstener) {
        super(content,theme);
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

    private void getViews(){
        etprodialogStyle = (EditText) findViewById(R.id.etprodialogStyle);
        etprodialogFactory = (EditText) findViewById(R.id.etprodialogFactory);
        etprodialogRecode = (EditText) findViewById(R.id.etprodialogRecode);
        checkboxNull = (CheckBox) findViewById(R.id.checkboxNull);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnComfirm = (Button) findViewById(R.id.btnComfirm);
        etprodialogProcedure = (EditText) findViewById(R.id.etprodialogProcedure);
    }

    private void initViews(){
        sp = content.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        Window dialogWindow = this.getWindow();
        WindowManager m = content.getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (display.getWidth() * 0.8);
        dialogWindow.setAttributes(p);
        btnComfirm.setOnClickListener(mClickListener);
        btnCancle.setOnClickListener(mCancleLinstener);
        this.setCancelable(true);
    }
}
