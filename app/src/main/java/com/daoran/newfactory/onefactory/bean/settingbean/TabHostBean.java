package com.daoran.newfactory.onefactory.bean.settingbean;

/**
 * fragment和toolbar控件实体类
 * Created by lizhipeng on 2017/3/15.
 */

public class TabHostBean {
    private int title;//标题
    private int icon;//图标
    private Class Fragment;//模块

    public Class getFragment() {
        return Fragment;
    }

    public void setFragment(Class fragment) {
        Fragment = fragment;
    }

    public TabHostBean(int title, int icon, Class fragment) {
        this.title = title;
        this.icon = icon;
        Fragment = fragment;
    }

    public int getTitle() {

        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
