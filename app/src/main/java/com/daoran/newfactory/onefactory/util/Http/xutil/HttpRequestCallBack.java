package com.daoran.newfactory.onefactory.util.Http.xutil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.SystemParams;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;



/**
 * Created by djx on 16/1/18.
 */
public abstract class HttpRequestCallBack<T> extends RequestCallBack<String> {
    private Class<?> clazz;
    private BaseFrangmentActivity activity;
    private String msg;
    private boolean bl = true;  //为true显示加载框  false不显示

    public HttpRequestCallBack(Class<?> clazz) {
        this.clazz = clazz;
    }

    public HttpRequestCallBack(Class<?> clazz, Context activity) {
        this.clazz = clazz;
        this.activity = (BaseFrangmentActivity) activity;
    }

    public HttpRequestCallBack(Class<?> clazz, Context activity, String msg) {
        this.clazz = clazz;
        this.activity = (BaseFrangmentActivity) activity;
        this.msg = msg;
    }

    public HttpRequestCallBack(Class<?> clazz, Context activity, boolean msg) {
        this.clazz = clazz;
        this.activity = (BaseFrangmentActivity) activity;
        this.bl = msg;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (NetUtil.isNetAvailable(activity)) {
            if (bl)
                show(msg == null ? getToasString() : msg);
        } else {
            ToastUtils.ShowToastMessage(activity.getString(R.string.nonet), activity);
        }

    }


    @Override
    public void onSuccess(ResponseInfo<String> arg0) {
        String gsonStr = arg0.result;
        Log.i("tag", gsonStr);
        if (gsonStr == null || "".equals(gsonStr)) {
            onFailure(null, null);
        }
        try {
//            T t = (T) new Gson().fromJson(gsonStr, clazz);
            T t = (T) JSONObject.parseObject(gsonStr, clazz);
            if (t == null && this.activity != null) {
                Toast.makeText(activity, "未获得相关数据，请检查网络！", Toast.LENGTH_SHORT).show();
                onFailure(null, null);
                return;
            }
            JSONObject object = JSONObject.parseObject(gsonStr);
            if (object.containsKey("result")) {
                if (object.getString("result").equals("false")) {
                    if (!gsonStr.contains("PHONE_EXISTED")) {
                        Toast.makeText(activity, SystemParams.getInstance().getErrorMsg(object.getString("data")), Toast.LENGTH_SHORT).show();
//                    return;
                    }
                }
            }
            dismissDialog();
            onSuccess(t);
        } catch (Exception e) {
            e.printStackTrace();
            dismissDialog();
            Log.v("onFailure", e.getMessage() + "");
            onFailure(null, e.getMessage() + "");
        }
    }

    protected abstract void onSuccess(T t);


    public void onFailure(HttpException arg0, String arg1) {
        dismissDialog();
        if (this.activity != null) {

        }
    }

    private void dismissDialog() {
        if (this.activity != null) {
            if (!activity.isFinishing() && isShow()) {
                activity.dismissDialog();
            }
        }
    }

    /**
     * 显示提示框
     *
     * @param ToastString
     */
    public void show(String ToastString) {
        if (activity == null) {
            return;
        }
        if (!activity.isFinishing() && isShow()) {
            activity.showDialog(activity, ToastString);
        }
    }

    /**
     * 是否显示提示框
     *
     * @return
     */
    protected boolean isShow() {
        return true;
    }

    /**
     * 提示框显示文字
     *
     * @return
     */
    protected String getToasString() {
        return "请稍候";
    }
}
