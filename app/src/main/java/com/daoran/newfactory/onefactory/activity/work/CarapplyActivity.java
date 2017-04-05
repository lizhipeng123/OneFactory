package com.daoran.newfactory.onefactory.activity.work;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;

/**
 * 出车单展示
 * Created by lizhipeng on 2017/3/24.
 */

public class CarapplyActivity extends BaseFrangmentActivity implements View.OnClickListener {


    private Toolbar tbarCarapply;
    private EditText etStartDataClick, etEndDataClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carapply);
        getViews();
        initViews();
        setListener();
    }

    private void getViews() {
        tbarCarapply = (Toolbar) findViewById(R.id.tbarCarapply);
        etStartDataClick = (EditText) findViewById(R.id.etStartDataClick);
        etEndDataClick = (EditText) findViewById(R.id.etEndDataClick);
    }

    private void initViews() {
        tbarCarapply.setNavigationIcon(R.mipmap.button_cross);
        tbarCarapply.setTitle("");
        etStartDataClick.setOnClickListener(this);
        etEndDataClick.setOnClickListener(this);
    }

    private void setListener() {
        tbarCarapply.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showEditClickPopupWindow() {
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
//        View menuView = LayoutInflater.from(this).inflate()
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etStartDataClick:
                showEditClickPopupWindow();
                break;
        }
    }
}
