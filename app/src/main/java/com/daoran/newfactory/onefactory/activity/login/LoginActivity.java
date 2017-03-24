package com.daoran.newfactory.onefactory.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.main.MainActivity;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.UserBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;


/**
 * 登录
 * Created by lizhipeng on 2017/3/21.
 */

public class LoginActivity extends BaseFrangmentActivity implements View.OnClickListener {

    private Button btnLogin;
    private String user = "0023";
    private String password = "1234";

    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initDatas();
        getViews();
        setListener();
    }

    private void initViews() {

    }

    private void initDatas() {

    }

    private void getViews() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    private void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (validate()) {
                    postLogin();
                }
                break;
        }
    }

    private void postLogin() {
        String loginuserUrl = HttpUrl.Url + HttpUrl.loginUrl;
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this,"登录中");
            OkHttpUtils.post().url(loginuserUrl)
                    .addParams("Logid", user)
                    .addParams("pwd", password)
                    .build()
                    .execute(new StringCallback() {

                        @Override
                        public void onBefore(Request request, int id) {
                            super.onBefore(request, id);
                            Toast.makeText(LoginActivity.this, "onBefore", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAfter(int id) {
                            super.onAfter(id);
                            Toast.makeText(LoginActivity.this, "onAfter", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "OnError" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                System.out.print(response);
                                UserBean userBean = new Gson().fromJson(response, UserBean.class);

                                user = userBean.getUsername();
                                password = userBean.getPassword();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(getString(R.string.noHttp), LoginActivity.this);
        }

    }

    private boolean validate() {
        boolean result = true;
        String message = "";
        try {
            String username = etUsername.getText().toString().trim();
            if (username.equals("")) {
                message = "请输入账号";
                etUsername.requestFocus();
                result = false;
                return result;
            }
            String password = etPassword.getText().toString().trim();
            if (password.length() == 0) {
                message = "请输入密码";
                etPassword.setHint("请输入密码");
                etPassword.requestFocus();
                result = false;
                return result;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            Log.e(getClass().getName(), "validate==>" + e.getMessage());
        } finally {
            if (!result) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        }
        return result;
    }
}
