package com.daoran.newfactory.onefactory.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daoran.newfactory.onefactory.fragment.GuiderFragment;

/**
 * 引导页面适配
 */
public class GuiderFragmentPagerAdapter extends FragmentPagerAdapter {
    private int[] resIds = null;

    public GuiderFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setResIds(int[] resIds) {
        if (resIds == null) {
            throw new IllegalArgumentException("resIds can not be null");
        }
        this.resIds = resIds;
    }

    @Override
    public Fragment getItem(int position) {
        boolean isLast = false;
        if (position == resIds.length - 1) {
            isLast = true;
        }

        return GuiderFragment.newInstance(resIds[position], isLast);
    }

    @Override
    public int getCount() {
        return resIds.length;
    }
}