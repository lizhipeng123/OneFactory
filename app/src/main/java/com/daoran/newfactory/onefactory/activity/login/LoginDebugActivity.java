package com.daoran.newfactory.onefactory.activity.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.main.MainActivity;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.UsergetBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.RequestParams;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SharedHelper;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录
 * Created by lizhipeng on 2017/4/10.
 */

public class LoginDebugActivity extends BaseFrangmentActivity {

    private Button btnLogin;
    private EditText etUsername, etPassword;
    private SharedHelper sh;
    private SPUtils spUtils;
    private CheckBox checkBoxPw, checkboxopen;
    private SharedPreferences sp;
    private String userNameValue, passwordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sh = new SharedHelper(this);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    postLogin();
                }
            }
        });
        getViews();
        initViews();
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        String name = sp.getString("username", "");
        String passwd = sp.getString("passwd", "");
        boolean choseRemember = sp.getBoolean("remember", false);
        boolean choseAutoLogin = sp.getBoolean("autologin", false);
        if (choseRemember == true) {
            etUsername.setText(name);
            etPassword.setText(passwd);
            checkBoxPw.setChecked(true);
        }
        if (choseAutoLogin) {
            checkboxopen.setChecked(true);
            etUsername.setText(name);
            etPassword.setText(passwd);
            if (etUsername.getText().toString() != null) {
                postLogin();
            }
        }
    }

    private void getViews() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        checkBoxPw = (CheckBox) findViewById(R.id.checkBoxPw);
        checkboxopen = (CheckBox) findViewById(R.id.checkboxopen);
    }

    private void initViews() {
        setEditTextInhibitInputSpeChat(etUsername);
        setEditTextInhibitInputSpeChat(etPassword);
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

    /**
     * 登录
     */
    private void postLogin() {
        String loginuserUrl = HttpUrl.debugoneUrl + "Login/UserLogin/";
        if (NetWork.isNetWorkAvailable(this)) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(NetUtil.createParam("Logid", etUsername.getText().toString()));
            params.add(NetUtil.createParam("pwd", etPassword.getText().toString()));
            params.add(NetUtil.createParam("Ischeckpwd", true));
            params.add(NetUtil.createParam("Company", "杭州道然进出口有限公司"));
            RequestParams requestParams = new RequestParams(params);
            ResponseDialog.showLoading(this, "登录中");
            NetUtil.getAsyncHttpClient().post(loginuserUrl, requestParams, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    System.out.print(content);
                    userNameValue = etUsername.getText().toString();
                    passwordValue = etPassword.getText().toString();
                    Editor editor = sp.edit();
                    Gson gson = new Gson();
                    UsergetBean userBean = gson.fromJson(content, UsergetBean.class);
                    if (userBean.isStatus() == true) {
                        editor.putString("username", userNameValue);
                        editor.putString("passwd", passwordValue);
                        //记住密码
                        if (checkBoxPw.isChecked()) {
                            editor.putBoolean("remember", true);
                        } else {
                            editor.putBoolean("remember", false);
                        }
                        //
                        if (checkboxopen.isChecked()) {
                            editor.putBoolean("autologin", true);
                        } else {
                            editor.putBoolean("autologin", false);
                        }
                        editor.commit();
                        Intent intent = new Intent(LoginDebugActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("u_name", userBean.getU_name());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        ToastUtils.ShowToastMessage("用户名密码错误，请重新输入", LoginDebugActivity.this);
                        ResponseDialog.closeLoading();
                    }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                    ToastUtils.ShowToastMessage("登录失败", LoginDebugActivity.this);
                    ResponseDialog.closeLoading();
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }
            });
        } else {
            ToastUtils.ShowToastMessage(getString(R.string.noHttp), LoginDebugActivity.this);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
