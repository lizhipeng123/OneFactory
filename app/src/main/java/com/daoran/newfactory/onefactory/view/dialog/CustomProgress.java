package com.daoran.newfactory.onefactory.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;

/**
 * Created by lizhipeng on 2017/3/24.
 */

public class CustomProgress extends Dialog {
    private Context contextt = null;
    private static CustomProgress progress = null;

    public CustomProgress(Context context) {
        super(context);
        this.contextt = context;
    }

    public CustomProgress(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static CustomProgress createDialog(Context context) {
        progress = new CustomProgress(context, R.style.CustomProgressDialog);
        progress.setContentView(R.layout.customprogress);
        progress.getWindow().getAttributes().gravity = Gravity.CENTER;
        return progress;
    }

    public void onWindowFocusChanged(boolean hasFous) {
        if (progress == null) {
            return;
        }
        View progressBar = progress.findViewById(R.id.loadbar);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 标题
     *
     * @param strTitle
     * @return
     */
    public CustomProgress setTitile(String strTitle) {
        return progress;
    }

    /**
     * 提示内容
     * @param strMessage
     * @return
     */
    public CustomProgress setMessage(String strMessage) {
        TextView tvMsg = (TextView) progress.findViewById(R.id.id_tv_loadingmsg);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
        return progress;
    }
}
