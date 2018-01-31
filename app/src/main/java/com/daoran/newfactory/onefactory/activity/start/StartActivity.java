package com.daoran.newfactory.onefactory.activity.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.guide.GuideActivity;
import com.daoran.newfactory.onefactory.activity.main.MainActivity;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.application.settings.Comfig;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PreferencesUtils;

/**
 * 启动页
 * Created by lizhipeng on 2017/3/21.
 */

public class StartActivity extends BaseFrangmentActivity {
    private Handler handler = new Handler();
    private ImageView ivStart;
    private boolean auto_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        initViews();
    }

    /*初始化信息*/
    private void initViews() {
        //判断是否登录过用户
        auto_login = PreferencesUtils.getString(this, Comfig.USERNAME)
                != null && PreferencesUtils.getString(this, Comfig.PASSWORD) != null;
        //如果第一次打开则先进去引导页，否则直接进入首页
        if (auto_login) {
            DelayToLogin(1500);//传入等待时间
        } else {
            DelayToLoginActivity(1500);
        }
        ivStart = (ImageView) findViewById(R.id.ivStart);
    }

    /*已打开过app*/
    private void DelayToLogin(int time) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this,MainActivity.class));
                finish();
            }
        }, time);
    }

    /*第一次打开app*/
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
}