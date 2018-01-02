package com.daoran.newfactory.onefactory.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理所有的activity容器
 * Created by lizhipeng on 2015/5/29.
 */
public class ActivityCollector {
    public static List<Activity> acticities = new ArrayList<Activity>();
    private static ActivityCollector instance;

    public static ActivityCollector getInstance() {
        if (instance == null) {
            instance = new ActivityCollector();
        }
        return instance;
    }

    /*添加activity到列表*/
    public static void addActivity(Activity activity) {
        if (acticities == null) {
            acticities = new ArrayList<Activity>();
        }
        acticities.add(activity);
    }

    /*获取界面数量*/
    public static int getActivitySize() {
        if (acticities != null) {
            return acticities.size();
        }
        return 0;
    }

    /*获取当前activity - 堆栈中最后一个被压入的*/
    public static Activity getCurrentActivity() {
        if (acticities != null && acticities.size() > 0) {
            Activity activity = acticities.get(acticities.size() - 1);
            return activity;
        }
        return null;
    }

    /*获取指定类名的activity*/
    public static Activity getActivity(Class<?> cls) {
        if (acticities == null) {
            return null;
        }
        for (Activity activity : acticities) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /*结束指定的activity*/
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            acticities.remove(activity);
        }
    }

    /*结束指定类名的activity*/
    public static void removeActivity(Class<?> cls) {
        if (acticities == null) {
            return;
        }
        for (Activity activity : acticities) {
            if (activity.getClass().equals(cls)) {
                acticities.remove(activity);
            }
        }
    }

    /*结束所有的activity*/
    public static void finishAllActivity() {
        if (acticities == null) {
            return;
        }
        int size = acticities.size();
        for (int i = 0; i < size; i++) {
            if (null != acticities.get(i)) {
                acticities.get(i).finish();
            }
        }
        acticities.clear();
    }

    /*结束其他所有的activity*/
    public static void finishOtherAllActivity(Activity activity) {
        if (acticities == null) {
            return;
        }
        for (int i = 0, size = acticities.size(); i < size; i++) {
            if (activity != acticities.get(i)) {
                acticities.get(i).finish();
                acticities.remove(i);
            }
        }
    }

    public static void finishAll() {
        for (Activity activity : acticities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}