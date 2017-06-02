package com.daoran.newfactory.onefactory.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.settings.Comfig;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PreferencesUtils;

import java.util.List;

/**
 * activity父框架
 * Created by lizhipeng on 2017/3/29.
 */
public class BaseFrangmentActivity extends FragmentActivity {
    public static String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        token = getToken();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        HXSDKHelper.getInstance().getNotifier().reset();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /*获取token*/
    public String getToken() {
        return PreferencesUtils.getString(this, Comfig.TOKEN);
    }

    private Dialog progressDialog;

    public void showDialog(Context context, String string) {
        if (context == null) {
            return;
        }
        if (!this.isFinishing()) {
            if (progressDialog == null) {
                progressDialog = new Dialog(context, R.style.CustomProgressDialog);
            }
            progressDialog.setContentView(R.layout.customprogress);
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText(string);
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    public void dismissDialog() {
        if (this == null) {
            return;
        }
        if (!this.isFinishing() && progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private static final String TAG = "BaseActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FragmentManager fm = getSupportFragmentManager();
        int index = requestCode >> 16;
        if (index != 0) {
            index--;
            if (fm.getFragments() == null || index < 0
                    || index >= fm.getFragments().size()) {
                Log.w(TAG, "Activity result fragment index out of range: 0x"
                        + Integer.toHexString(requestCode));
                return;
            }
            Fragment frag = fm.getFragments().get(index);
            if (frag == null) {
                Log.w(TAG, "Activity result no fragment exists for index: 0x"
                        + Integer.toHexString(requestCode));
            } else {
                handleResult(frag, requestCode, resultCode, data);
            }
            return;
        }
    }

    /**
     * 递归调用，对所有子Fragement生效
     *
     * @param frag
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment frag, int requestCode, int resultCode,
                              Intent data) {
        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    handleResult(f, requestCode, resultCode, data);
            }
        }
    }
}