package com.daoran.newfactory.onefactory.activity.work;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daoran.newfactory.onefactory.R;

import java.util.List;

/**
 * 自动轮播
 * Created by lizhipeng on 2017/8/15.
 */

public class ViewPageLaActivity extends Activity {

    private ViewPager vp;
    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    private ViewGroup vg;//放置圆点

    private ImageView iv_point;
    private ImageView []ivPointArray;

    private boolean isLooper;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);
    }

    private void getView() {

    }

    private void setViews() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
