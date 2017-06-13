package com.daoran.newfactory.onefactory.activity.work.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;

/**
 * Created by lizhipeng on 2017/6/13.
 */

public class AboutActivity extends BaseFrangmentActivity
        implements View.OnClickListener{

    private ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getViews();
        initViews();
        setListener();
    }

    private void getViews(){
        ivBack = (ImageView) findViewById(R.id.ivBack);
    }

    private void initViews(){

    }

    private void setListener(){
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
