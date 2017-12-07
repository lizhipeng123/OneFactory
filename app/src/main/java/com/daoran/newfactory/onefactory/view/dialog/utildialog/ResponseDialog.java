package com.daoran.newfactory.onefactory.view.dialog.utildialog;

import android.content.Context;
import android.os.Handler;

/**
 * 加载等待dialog
 * Created by lizhipeng on 2017/3/24.
 */

public class ResponseDialog {

    private static CustomProgress progress;

    public static void showLoading(Context context) {
        showLoading(context, "请稍后");
    }

    public static void showLoading(final Context context, String msg) {
        if (progress == null) {
            progress = CustomProgress.createDialog(context);
            progress.setMessage(msg);
            progress.show();
            progress.setCancelable(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (progress != null && progress.isShowing()) {
                        progress.dismiss();
                    }
                }
            }, 10 * 1000);
        } else {
            progress.dismiss();
        }
    }

    public static void closeLoading() {
        if (progress != null) {
            progress.dismiss();
            progress = null;
        }
    }

    public static void isShowing() {
        progress.isShowing();
    }
}
