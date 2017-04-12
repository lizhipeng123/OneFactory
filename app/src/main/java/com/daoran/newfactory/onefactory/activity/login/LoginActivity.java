package com.daoran.newfactory.onefactory.activity.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.main.MainActivity;
import com.daoran.newfactory.onefactory.activity.work.DebugDetailActivity;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.UserBean;
import com.daoran.newfactory.onefactory.bean.UsergetBean;
import com.daoran.newfactory.onefactory.util.Comfig;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.RequestParams;
import com.daoran.newfactory.onefactory.util.PreferencesUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.CustomProgress;
import com.daoran.newfactory.onefactory.view.dialog.LoadingDialog;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 登录
 * Created by lizhipeng on 2017/3/21.
 */

public class LoginActivity extends BaseFrangmentActivity
//        implements View.OnClickListener
{

    private Button btnLogin,btnLogindebug;
    private EditText etUsername, etPassword;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initDatas();
        getViews();
//        setListener();
    }

    private void initViews() {

    }

    private void initDatas() {

    }

    private void getViews() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
//        setEditTextInhibitInputSpeChat(etUsername);
//        setEditTextInhibitInputSpeChat(etPassword);
//        setEditTextInhibitInputSpace(etUsername);
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText) {

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());

                if (matcher.find()) return "";

                else return null;

            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ")) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    private void setListener() {

    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnLogin:
//////                if (validate()) {
////                startActivity(new Intent(LoginActivity.this, DebugDetailActivity.class));
////                finish();
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//////                    postLogin();
//////                }
//                break;
//        }
//    }

    private void postLogin() {
        String loginuserUrl = HttpUrl.debugoneUrl + "Login/UserLogin/";
        if (NetWork.isNetWorkAvailable(this)) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(NetUtil.createParam("Logid", etUsername.getText().toString()));
            params.add(NetUtil.createParam("pwd", etPassword.getText().toString()));
            params.add(NetUtil.createParam("Ischeckpwd", true));
            params.add(NetUtil.createParam("Company", "杭州道然进出口有限公司"));
            RequestParams requestParams = new RequestParams(params);
            NetUtil.getAsyncHttpClient().post(loginuserUrl, requestParams, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    System.out.print(content);
                    UsergetBean bean = new Gson().fromJson(content, UsergetBean.class);
                    System.out.print(bean);
//                    UserBean.data userBean = new Gson().fromJson(content, UserBean.data.class);
//                    if (userBean.isStatus() == true) {
//                        PreferencesUtils.putString(LoginActivity.this, Comfig.USERNAME, etUsername.getText().toString());
//                        PreferencesUtils.putString(LoginActivity.this, Comfig.PASSWORD, etPassword.getText().toString());
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    mhander.sendEmptyMessage(0);

//                    } else {
//                        ToastUtils.ShowToastMessage("登录失败", LoginActivity.this);
//                    }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                    ToastUtils.ShowToastMessage("登录失败", LoginActivity.this);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }
            });
        } else {
            ToastUtils.ShowToastMessage(getString(R.string.noHttp), LoginActivity.this);

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        dialog.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.close();

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
            Log.e(getClass().getName(), "validate==>" + e.getMessage());
        } finally {
            if (!result) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        }
        return result;
    }

    private Handler mhander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };


}
