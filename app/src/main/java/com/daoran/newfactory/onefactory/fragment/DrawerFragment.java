package com.daoran.newfactory.onefactory.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;

/**
 * You may think you know what the following code does.
 * But you dont. Trust me.
 * Fiddle with it, and youll spend many a sleepless
 * night cursing the moment you thought youd be clever
 * enough to "optimize" the code below.
 * Now close this file and go play with something else.
 * 侧滑页（暂隐藏）
 * Created by lizhipeng on 2017/5/18.
 */

public class DrawerFragment extends Fragment {
    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private View rootView;
    private BaseFrangmentActivity baseFrangmentActivity;

    public DrawerFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentContainerView = inflater.inflate(R.layout.fragment_navigation_drawer_menu, container, false);
        baseFrangmentActivity = (BaseFrangmentActivity) getActivity();
        return mFragmentContainerView;

    }

    /**
     * 控件点击调用此方法，可以打开侧滑菜单
     */
    public void openDeawer() {
        mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    /**
     * 手势
     * @param fragmentId
     * @param drawerLayout
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
//                initUserView();
            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
}
