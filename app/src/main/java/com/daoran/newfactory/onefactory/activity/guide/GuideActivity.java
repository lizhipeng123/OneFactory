package com.daoran.newfactory.onefactory.activity.guide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.login.LoginMainActivity;
import com.daoran.newfactory.onefactory.adapter.GuiderFragmentPagerAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.fragment.GuiderFragment;
import com.daoran.newfactory.onefactory.util.application.settings.Comfig;
import com.daoran.newfactory.onefactory.view.image.IndicatorView;

/**
 * 引导页
 * Created by lizhipeng on 2017/3/21.
 */

public class GuideActivity extends BaseFrangmentActivity
        implements GuiderFragment.OnLoginRegisterListener {
    private ViewPager vpGuider;
    private IndicatorView vIndicator;
    private GuiderFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        //是否第一次打开app，如果是则进入引导页，否则进入登录页
        SharedPreferences sp = this.getSharedPreferences("guider", 0);
        boolean isFirstOpen = sp.getBoolean("isFirstOpen", true);
        if (!isFirstOpen) {
            Intent intent = new Intent(this, LoginMainActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        getViews();
        initViews();
        setListeners();
    }

    /**
     * 获取View
     */
    private void getViews() {
        vpGuider = (ViewPager) this.findViewById(R.id.vpGuider);
        vIndicator = (IndicatorView) this.findViewById(R.id.vIndicator);
    }

    /**
     * 初始化View
     */
    private void initViews() {
        int[] resIds = Comfig.getGuiderResIds();
        mAdapter = new GuiderFragmentPagerAdapter(getSupportFragmentManager());
        mAdapter.setResIds(resIds);
        vpGuider.setAdapter(mAdapter);
        vIndicator.setIndicatorCount(resIds.length);
    }

    /**
     * 设置监听
     */
    private void setListeners() {
        vpGuider.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float pageWidth = page.getWidth();
                if (position < -1) {
                    page.setAlpha(0);
                } else if (position >= -1 && position < 0) {
                    page.setAlpha(1);
                    page.setTranslationX(0);
                    page.setScaleX(1);
                    page.setScaleY(1);
                } else if (position >= 0 && position <= 1) {
                    page.setAlpha(1 - position);
                    page.setTranslationX(-position * pageWidth);
                    page.setScaleX((float) (position * -0.25 + 1));
                    page.setScaleY((float) (position * -0.25 + 1));
                } else {
                    page.setAlpha(0);
                }
            }
        });

        vpGuider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vIndicator.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void login() {
        setSharedPreferences();
        //进入登录页
        Intent intent = new Intent(this, LoginMainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 设置为非第一次打开app
     */
    private void setSharedPreferences() {
        SharedPreferences sp = this.getSharedPreferences("guider", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isFirstOpen", false);
        editor.apply();
    }
}
