package com.daoran.newfactory.onefactory.view.dialog;

import android.content.Context;
import android.os.Handler;

/**
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
            }, 60 * 1000);
        }
    }

    public static void closeLoading() {
        if (progress != null) {
            progress.cancel();
            progress = null;
        }
    }
}
