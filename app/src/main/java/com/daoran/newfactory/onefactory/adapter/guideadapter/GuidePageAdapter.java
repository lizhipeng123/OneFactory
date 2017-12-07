package com.daoran.newfactory.onefactory.adapter.guideadapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 轮播适配
 * Created by lizhipeng on 2017/8/16.
 */

public class GuidePageAdapter extends PagerAdapter {
    private List<View> viewList;

    public GuidePageAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        if (viewList != null) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int newPosition = position % viewList.size();
        container.addView(viewList.get(newPosition));
        return viewList.get(newPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int newPosition = position % viewList.size();
        container.removeView(viewList.get(newPosition));
    }
}
