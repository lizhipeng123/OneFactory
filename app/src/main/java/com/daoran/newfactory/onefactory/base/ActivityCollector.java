package com.daoran.newfactory.onefactory.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理所有的activity
 * Created by lizhipeng on 2015/5/29.
 */
public class ActivityCollector {
    public static List<Activity> acticities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        acticities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        acticities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : acticities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}