package com.daoran.newfactory.onefactory.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast;

    public static void ShowToastMessage(String msg, Context context) {
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void ShowToastMessage(int msgId, Context context) {
        toast = Toast.makeText(context, msgId, Toast.LENGTH_SHORT);
        toast.show();
    }
}
