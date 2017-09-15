package com.daoran.newfactory.onefactory.activity.work.commo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;

/**
 * 查货跟踪详情（并获取打样id）
 * Created by lizhipeng on 2017/8/30.
 */

public class CommoditydetailActivity extends Activity
        implements View.OnClickListener {

    private SPUtils spUtils;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodetail);
        getViews();
        setViews();
        setDate();
        setListener();

    }

    private void getViews() {

    }

    private void setViews() {

    }

    private void setListener() {

    }

    /**
     * 查货跟踪获取明细（并获取打样id）
     */
    private void setDate() {
        sp = this.getSharedPreferences("my_sp", 0);
        String uriid = sp.getString("uriid", "");
        String urlstr = HttpUrl.debugoneUrl + "QACwork/APPGetDetail/" + uriid;
        if (NetWork.isNetWorkAvailable(this)) {
            NetUtil.getAsyncHttpClient().post(urlstr, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    System.out.print(content);
                    String ress = content.replace("\\", "");
                    System.out.print(ress);
                    String ression = StringUtil.sideTrim(ress, "\"");
                    System.out.print(ression);
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    setBarRight();
                }
            });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, CommoditydetailActivity.this);
        }
    }

    /**
     * 查货跟踪获取列权限（返回的是js代码）
     */
    private void setBarRight() {
        sp = this.getSharedPreferences("my_sp", 0);
        String uriid = sp.getString("uriid", "");
        String strbar = HttpUrl.debugoneUrl + "QACwork/APPGetBarRight/" + uriid;
        if (NetWork.isNetWorkAvailable(this)) {
            NetUtil.getAsyncHttpClient().post(strbar, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    System.out.print(content);
                    String ression = StringUtil.sideTrim(content, "\"");
                    System.out.print(ression);//输出的是Js代码
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                }
            });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, CommoditydetailActivity.this);
        }
    }

    /**
     *  打样列查询
     */
    private void setBindSearch() {
        String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACSam/";
        if (NetWork.isNetWorkAvailable(this)) {

        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, CommoditydetailActivity.this);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
