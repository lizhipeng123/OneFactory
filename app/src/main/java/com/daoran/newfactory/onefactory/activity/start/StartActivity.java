package com.daoran.newfactory.onefactory.activity.start;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.guide.GuideActivity;
import com.daoran.newfactory.onefactory.activity.main.MainActivity;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.base.Common;
import com.daoran.newfactory.onefactory.util.Comfig;
import com.daoran.newfactory.onefactory.util.PreferencesUtils;

import java.util.Timer;

/**
 * 启动页
 * Created by lizhipeng on 2017/3/21.
 */

public class StartActivity extends BaseFrangmentActivity {
    private Handler handler = new Handler();
    private ImageView ivStart;
    private boolean auto_login;
    private Common common = new Common();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initViews();
        getViews();
    }

    private void getViews() {

    }

    private void initViews() {
        auto_login = PreferencesUtils.getString(this, Comfig.USERNAME)
                != null && PreferencesUtils.getString(this, Comfig.PASSWORD) != null;
        if (auto_login) {
            DelayToLogin(1500);
        } else {
            DelayToLoginActivity(1500);
        }
        ivStart = (ImageView) findViewById(R.id.ivStart);
    }

    private void DelayToLogin(int time) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this,MainActivity.class));
                finish();
            }
        }, time);
    }

    private void DelayToLoginActivity(int time) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,GuideActivity.class);
                startActivity(intent);
                finish();
            }
        }, time);
    }

    private void login() {

    }
}
