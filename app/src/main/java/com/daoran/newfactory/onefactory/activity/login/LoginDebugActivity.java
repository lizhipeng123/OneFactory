package com.daoran.newfactory.onefactory.activity.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.daoran.newfactory.onefactory.view.CropSquareTransformation;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录页
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
    private ImageView image_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getViews();
        initViews();
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
        sp = this.getSharedPreferences("userInfo", 0);
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

    DialogInterface.OnClickListener listenerwifi = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE://确定
                    break;
                default:
                    break;
            }
        }
    };

    private void getViews() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        checkBoxPw = (CheckBox) findViewById(R.id.checkBoxPw);
        checkboxopen = (CheckBox) findViewById(R.id.checkboxopen);
        image_login = (ImageView) findViewById(R.id.image_login);
    }

    private void initViews() {
        setEditTextInhibitInputSpeChat(etUsername);
        setEditTextInhibitInputSpeChat(etPassword);
        Picasso.with(LoginDebugActivity.this)
                .load(R.mipmap.daoran)
                .error(R.mipmap.daoran)
                .transform(new CropSquareTransformation())
                .into(image_login);
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
     * 登录请求
     */
    private void postLogin() {
        String loginuserUrl = HttpUrl.debugoneUrl + "Login/UserLogin/";
        if (NetWork.isNetWorkAvailable(this)) {
            /*检测是否为可用WiFi*/
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String infossid = wifiInfo.getSSID();
            infossid = infossid.replace("\"", "");
//            if (!infossid.equals("taoxinxi")) {
//                AlertDialog dialog = new AlertDialog.Builder(this).create();
//                dialog.setTitle("系统提示");
//                dialog.setMessage("当前WiFi为公共网络，运行请转到测试WiFi状态");
//                dialog.setButton("确定", listenerwifi);
//                dialog.show();
//            } else {
                ResponseDialog.showLoading(this, "登录中");
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
                            if (checkboxopen.isChecked()) {
                                editor.putBoolean("autologin", true);
                            } else {
                                editor.putBoolean("autologin", false);
                            }
                            editor.commit();
                            spUtils.put(getApplicationContext(), "name", userBean.getU_name());
                            Intent intent = new Intent(LoginDebugActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("u_name", userBean.getU_name());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            ResponseDialog.closeLoading();
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
                        ResponseDialog.closeLoading();
                    }
                });
//            }
        } else {
            ToastUtils.ShowToastMessage(getString(R.string.noHttp), LoginDebugActivity.this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
