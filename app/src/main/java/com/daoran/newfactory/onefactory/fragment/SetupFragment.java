package com.daoran.newfactory.onefactory.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.login.LoginDebugActivity;
import com.daoran.newfactory.onefactory.bean.VerCodeBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * 设置模块
 * Created by lizhipeng on 2017/3/22.
 */

public class SetupFragment extends Fragment implements View.OnClickListener {

    Activity mactivity;
    private Toolbar tbarSetup;
    private View view;
    private RelativeLayout rlAgainLogin, rlEditionUpdate;
    private TextView tvVersion;
    private VerCodeBean codeBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mactivity = getActivity();
        view = inflater.inflate(R.layout.fragment_setup, container, false);
        tbarSetup = (Toolbar) view.findViewById(R.id.tbarSetup);
        tbarSetup.setTitle("");
        getViews();
        initViews();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getViews() {
        rlAgainLogin = (RelativeLayout) view.findViewById(R.id.rlAgainLogin);
        rlEditionUpdate = (RelativeLayout) view.findViewById(R.id.rlEditionUpdate);
        tvVersion = (TextView) view.findViewById(R.id.tvVersion);
    }

    private void initViews() {
        rlAgainLogin.setOnClickListener(this);
        rlEditionUpdate.setOnClickListener(this);
        tvVersion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlAgainLogin:
                startActivity(new Intent(getActivity(), LoginDebugActivity.class));
                break;
            case R.id.rlEditionUpdate:

                break;
            case R.id.tvVersion:
                checkAppVersion();
                break;
        }
    }

    private void checkAppVersion() {
        String strversion = HttpUrl.debugoneUrl + "AppVersion/GetAppVersion";
        if (NetWork.isNetWorkAvailable(mactivity)) {
            OkHttpUtils.get().url(strversion)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.print(response);
                            response = response.replace("{", "{\"");
                            System.out.print(response);
                            response = response.replace("\'", "\"");
                            System.out.print(response);
                            response = response.replace(",", ",\"");
                            System.out.print(response);
                            response = response.replace(":\"", "\":\"");
                            System.out.print(response);
                            String strfram = StringUtil.sideTrim(response, "\"");
                            System.out.print(strfram);
                            codeBean = new Gson().fromJson(strfram, VerCodeBean.class);
                            String vercode = codeBean.getVerCode();
                            System.out.print(vercode);
                            String apkpath = codeBean.getApkPath();
                            System.out.print(apkpath);
                            String reason = codeBean.getReason();
                            System.out.print(reason);
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用，请重新尝试", mactivity);
        }
    }
}
