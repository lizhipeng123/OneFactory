package com.daoran.newfactory.onefactory.activity.work.setting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.main.MainActivity;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.loginbean.UsergetBean;
import com.daoran.newfactory.onefactory.bean.loginbean.WorkPwSwitchBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.RequestParams;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.view.edit.EditTextWithDelete;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
import com.google.gson.Gson;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 添加账号
 * Created by lizhipeng on 2017/7/7.
 */

public class WorkPwSwitchActivity extends BaseFrangmentActivity implements
        View.OnClickListener {
    private SPUtils spUtils;
    private SharedPreferences sp;
    private TextView tvPwSwitchCancle;
    private Button btnLogin;
    private EditTextWithDelete etUsername, etPassword;
    private String userNameValue, passwordValue;
    private List<WorkPwSwitchBean.Data> switchBeendatalist
            = new ArrayList<WorkPwSwitchBean.Data>();
    private WorkPwSwitchBean.Data switchBean;
    private WorkPwSwitchBean workPwSwitchBean;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwswitch_work);
        sp = getSharedPreferences("my_sp", 0);
        getViews();
        setViews();
        setListener();
    }

    /**
     * 初始化控件
     */
    private void getViews() {
        tvPwSwitchCancle = (TextView) findViewById(R.id.tvPwSwitchCancle);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUsername = (EditTextWithDelete) findViewById(R.id.etUsername);
        etPassword = (EditTextWithDelete) findViewById(R.id.etPassword);
    }

    /**
     * 操作控件
     */
    private void setViews() {
        setEditTextInhibitInputSpeChat(etUsername);
        setEditTextInhibitInputSpeChat(etPassword);
    }

    /**
     * 赋予点击事件
     */
    private void setListener() {
        btnLogin.setOnClickListener(this);
        tvPwSwitchCancle.setOnClickListener(this);
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
                if (source.equals(" ") || source.equals("\n") || matcher.find())
                    return "";
                else
                    return null;
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

    /**
     * 非空验证
     *
     * @return
     */
    private boolean validate() {
        boolean result = true;
        String message = "";
        try {
            String username = etUsername.getText().toString().trim();
            if (username.equals("")) {
                message = getResources().getString(R.string.login_hint_account);
                etUsername.requestFocus();
                result = false;
                return result;
            }
            String password = etPassword.getText().toString().trim();
            if (password.length() == 0) {
                message = getResources().getString(R.string.login_hint_password);
                etPassword.setHint(getResources().getString(R.string.login_hint_password));
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
            ResponseDialog.showLoading(this, "正在登录");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(NetUtil.createParam("Logid", etUsername.getText().toString()));
            params.add(NetUtil.createParam("pwd", etPassword.getText().toString()));
            params.add(NetUtil.createParam("Ischeckpwd", true));
            params.add(NetUtil.createParam("Company", "杭州道然进出口有限公司"));
            RequestParams requestParams = new RequestParams(params);
            NetUtil.getAsyncHttpClient().post(loginuserUrl,
                    requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            System.out.print(content);
                            userNameValue = etUsername.getText().toString();
                            passwordValue = etPassword.getText().toString();
                            Gson gson = new Gson();
                            UsergetBean userBean = gson.fromJson(content, UsergetBean.class);
                            if (userBean.isStatus() == true) {
                                spUtils.put(WorkPwSwitchActivity.this, "username", userNameValue);
                                spUtils.put(WorkPwSwitchActivity.this, "passwd", passwordValue);
                                spUtils.put(getApplicationContext(), "name", userBean.getU_name());
                                spUtils.put(getApplicationContext(), "FTYDLName", userBean.getU_name());
                                spUtils.put(getApplicationContext(), "QACworkDialogPrddocumentary", userBean.getU_name());
                                spUtils.put(getApplicationContext(), "commologinid", userBean.getLogid());
                                String listwork = sp.getString("workbeenlist", "");
                                workPwSwitchBean = new Gson().fromJson(listwork, WorkPwSwitchBean.class);
                                //实体类等于空，也就是第一次进的时候，数据是空的
                                if (workPwSwitchBean == null) {
                                    workPwSwitchBean = new WorkPwSwitchBean();
                                    switchBean = new WorkPwSwitchBean.Data();
                                    String uuname = userBean.getU_name();
                                    switchBean.setU_name(uuname);
                                    String uulogid = userBean.getLogid();
                                    switchBean.setLogid(uulogid);
                                    switchBean.setPasswork(passwordValue);
                                    switchBeendatalist.add(switchBean);
                                    workPwSwitchBean.setDatas(switchBeendatalist);
                                } else {
                                    switchBeendatalist = workPwSwitchBean.getDatas();
                                    switchBean = new WorkPwSwitchBean.Data();
                                    String uuname = userBean.getU_name();
                                    String uulogid = userBean.getLogid();
                                    String[] uname = uuname.split(",");
                                    String[] listname = new String[switchBeendatalist.size()];
                                    for (int i = 0; i < switchBeendatalist.size(); i++) {
                                        listname[i] = switchBeendatalist.get(i).getU_name();
                                    }
                                    System.out.print(switchBeendatalist);
                                    System.out.print(listname);
                                    boolean booname = containsAll(listname, uname);
                                    //添加账号信息，如果不相同则添加，相同则不添加
                                    if (booname == false) {
                                        switchBean.setU_name(uuname);
                                        switchBean.setLogid(uulogid);
                                        switchBean.setPasswork(passwordValue);
                                        switchBeendatalist.add(switchBean);
                                        workPwSwitchBean.setDatas(switchBeendatalist);
                                    } else {
                                        ToastUtils.ShowToastMessage("已有当前账号", WorkPwSwitchActivity.this);
                                    }
                                }
                                System.out.print(workPwSwitchBean);
                                String workbeenlist = gson.toJson(switchBeendatalist);
                                String worklist = gson.toJson(workPwSwitchBean);
                                System.out.print(worklist);
                                System.out.print(workbeenlist);
                                spUtils.put(WorkPwSwitchActivity.this, "workbeenlist", worklist);
                                Intent intent = new Intent(WorkPwSwitchActivity.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("u_name", userBean.getU_name());
                                intent.putExtra(id, 1);
                                intent.putExtras(bundle);
                                setResult(2, intent);
                                startActivity(intent);
                                ResponseDialog.closeLoading();
                            } else {
                                ToastUtils.ShowToastMessage(R.string.user_tips, WorkPwSwitchActivity.this);
                                ResponseDialog.closeLoading();
                            }
                        }

                        @Override
                        public void onFailure(Throwable error, String content) {
                            super.onFailure(error, content);
                            ResponseDialog.closeLoading();
                            ToastUtils.ShowToastMessage(R.string.login_has_error, WorkPwSwitchActivity.this);
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            ResponseDialog.closeLoading();
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(getString(R.string.noHttp), WorkPwSwitchActivity.this);
        }
    }

    /**
     * 判断数组是否相同
     *
     * @param array1
     * @param array2
     * @return
     */
    private static boolean containsAll(String[] array1, String[] array2) {
        for (String str : array2) {
            if (!ArrayUtils.contains(array1, str)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录按钮
            case R.id.btnLogin:
                if (validate()) {
                    //判断软件盘是否弹出（InputMethodManager为软键盘管理类）
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {//判断为空防止运行时异常
                        if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
                            //windowtaken为之前请求显示软件盘的view的windowtaken，0为标记不管任何情况都可以隐藏
                            // 实际情况是，使用任意布局中已经加载的view的windowtaken都可以隐藏软键盘
                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                                    0);
                        } else {
                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                                    0);
                        }
                    }
                    postLogin();
                }
                break;
            //取消按钮（退出当前页）
            case R.id.tvPwSwitchCancle:
                Intent intent = new Intent(WorkPwSwitchActivity.this, MainActivity.class);
                intent.putExtra(id, 1);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}