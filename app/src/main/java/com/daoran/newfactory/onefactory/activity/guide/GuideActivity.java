package com.daoran.newfactory.onefactory.activity.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.login.LoginDebugActivity;
import com.daoran.newfactory.onefactory.adapter.GuideFragmentAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.base.Common;

import java.util.ArrayList;

/**
 * 引导页
 * Created by lizhipeng on 2017/3/21.
 */

public class GuideActivity extends BaseFrangmentActivity implements View.OnClickListener {

    private Common common = new Common();
    private ViewPager vpGuide;//引导页面viewpager
    private TextView tvLoginbtn;//开始
    private GuideFragmentAdapter mAdapter;
    private ArrayList<View> mViews;//页面图片列表
    //图片资源
    private final int images[] = {
            R.drawable.welcome_01, R.drawable.welcome_02, R.drawable.welcome_03};

    private ImageView[] guideDots;//底部导航小点
    private int currentIndex;//记录当前的图片

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getViews();
        initDatas();
        vpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getViews() {
        tvLoginbtn = (TextView) findViewById(R.id.tvLoginbtn);
        tvLoginbtn.setOnClickListener(this);
        vpGuide = (ViewPager) findViewById(R.id.vpGuide);
        mViews = new ArrayList<View>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setImageResource(images[i]);
            mViews.add(imageView);
        }
        mAdapter = new GuideFragmentAdapter(mViews);
        vpGuide.setAdapter(mAdapter);
    }

    /**
     * 监听
     */
    private void setListener() {

    }

    /**
     * 初始化导航小点
     */
    private void initDatas() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llGuideDots);
        guideDots = new ImageView[mViews.size()];//初始化小点数组
        //使每个小点处于正常状态
        for (int i = 0; i < mViews.size(); i++) {
            guideDots[i] = (ImageView) linearLayout.getChildAt(i);
            guideDots[i].setSelected(false);

        }
        //初始化第一个小点为选中状态
        currentIndex = 0;
        guideDots[currentIndex].setSelected(true);
    }

    /**
     * 页面更换时，更新小点状态
     *
     * @param position
     */
    private void setCurrentDot(int position) {
        if (position < 0 || position > mViews.size() - 1 || currentIndex == position) {
            return;
        }
        guideDots[position].setSelected(true);
        guideDots[currentIndex].setSelected(false);
        currentIndex = position;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLoginbtn:
                startActivity(new Intent(this,LoginDebugActivity.class));
                finish();
                break;
        }
    }
}
