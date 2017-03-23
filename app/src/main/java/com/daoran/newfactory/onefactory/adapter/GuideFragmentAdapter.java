package com.daoran.newfactory.onefactory.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
 * 引导页面适配器
 * Created by lizhipeng on 2017/3/21.
 */

public class GuideFragmentAdapter extends PagerAdapter {

    private final ArrayList<View> mViews;

    public GuideFragmentAdapter(ArrayList<View> views) {
        mViews = views;
    }

    /**
     * 返回页面数目
     *
     * @return
     */
    @Override
    public int getCount() {
        if (mViews != null) {
            return mViews.size();
        }
        return 0;
    }

    /**
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    /**
     * 初始化position位置的界面
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mViews.get(position), 0);
        return mViews.get(position);
    }

    /**
     * 判断是否由对象生成页面
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    /**
     * 销毁position位置的页面
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(mViews.get(position));
    }
}
