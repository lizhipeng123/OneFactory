package com.daoran.newfactory.onefactory.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;

/**
 * Created by lizhipeng on 2017/3/28.
 */

public class ContentDialog extends Dialog {

    Activity content;
    private Spinner spinnerAudit;
    private TextView tvInitialDate, tvEndDate;
    private Button btnCancle, btnComfirm;

    private View.OnClickListener mClickListener, mCancleLinstener;

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
