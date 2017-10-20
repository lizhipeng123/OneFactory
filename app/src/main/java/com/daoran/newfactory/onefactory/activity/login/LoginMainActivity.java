package com.daoran.newfactory.onefactory.activity.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.main.MainActivity;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.UsergetBean;
import com.daoran.newfactory.onefactory.bean.VerCodeBean;
import com.daoran.newfactory.onefactory.bean.WorkPwSwitchBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.RequestParams;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.bsdiff.PatchUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.image.CropSquareTransformation;
import com.daoran.newfactory.onefactory.util.file.setting.FileUtils;
import com.daoran.newfactory.onefactory.view.edit.EditTextWithDelete;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.NameValuePair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录页
 * Created by lizhipeng on 2017/4/10.
 */
public class LoginMainActivity extends BaseFrangmentActivity {

    private Button btnLogin;//登录按钮
    private EditTextWithDelete etUsername, etPassword;
    private SPUtils spUtils;
    private CheckBox checkBoxPw, checkboxopen;
    private SharedPreferences sp;
    private String userNameValue, passwordValue;
    private ImageView image_login;
    private WorkPwSwitchBean workPwSwitchBean;
    private WorkPwSwitchBean.Data switchBean;
    private List<WorkPwSwitchBean.Data> switchBeendatalist
            = new ArrayList<WorkPwSwitchBean.Data>();//将已登录的用户保存到类中

    private String curVersionName;//版本信息
    private int curVersionCode;//版本号
    private VerCodeBean codeBean;
    private AlertDialog noticeDialog;//下载弹窗
    private ProgressBar nProgress;
    private TextView nProgressText;
    protected boolean interceptFlag, inupdateFlag;
    private AlertDialog updateloadDialog;
    private TextView tvcode;
    private TextView text_notuse;

    //下载返回handler通信结果
    private static final int DOWN_NOSDCARD = 0;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
    private static final int DOWN_NOUPDATE = 3;

    // 成功
    private static final int WHAT_SUCCESS = 1;
    // 合成失败
    private static final int WHAT_FAIL_PATCH = 0;

    protected int progress, updateProgress;
    protected String apkUpdateFileSize;
    protected String tmpUpdateFileSize;
    protected String savePath;//临时保存地址
    protected String apkFilePath;//apk地址

    private String patchPath;//增量包地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences("my_sp", 0);
        if (Build.VERSION.SDK_INT >= 21) {//android沉浸式标题栏
            View decorView = getWindow().getDecorView();
            //View.SYSTEM_UI_FLAG_FULLSCREEN  activity全局显示，但状态栏不会隐藏覆盖
            //View.SYSTEM_UI_FLAG_LAYOUT_STABLE 防止状态栏隐藏
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色
            getWindow().setStatusBarColor(getResources().getColor(R.color.lightblue));
        }
        getViews();
        initViews();
        checkAppVersion(true);//调用版本更新
        String name = sp.getString("username", "");//保存的用户名
        String passwd = sp.getString("passwd", "");//保存的密码
        boolean choseRemember = sp.getBoolean("remember", false);//保存的记住密码状态
        boolean choseAutoLogin = sp.getBoolean("autologin", false);//保存的直接登录状态
        if (choseRemember == true) {
            etUsername.setText(name);
            etUsername.setSelection(etUsername.length());
            etPassword.setText(passwd);
            etPassword.setSelection(etPassword.length());
            checkBoxPw.setChecked(true);
        }
        if (choseAutoLogin) {
            checkboxopen.setChecked(true);
            etUsername.setText(name);
            etUsername.setSelection(etUsername.length());
            etPassword.setText(passwd);
            etPassword.setSelection(etPassword.length());
            if (etUsername.getText().toString() != null) {
                postLogin();
            }
        }
        //点击登录按钮的事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {//判断非空
                    postLogin();
                }
            }
        });
    }

    /**
     * wifi监听弹出事件
     */
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

    /**
     * 实例化控件
     */
    private void getViews() {
        etUsername = (EditTextWithDelete) findViewById(R.id.etUsername);
        etPassword = (EditTextWithDelete) findViewById(R.id.etPassword);
        checkBoxPw = (CheckBox) findViewById(R.id.checkBoxPw);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        checkboxopen = (CheckBox) findViewById(R.id.checkboxopen);
        image_login = (ImageView) findViewById(R.id.image_login);
        tvcode = (TextView) findViewById(R.id.tvcode);
        text_notuse = (TextView) findViewById(R.id.text_notuse);
        text_notuse.requestFocus();
    }

    /**
     * 操作控件
     */
    private void initViews() {
        setEditTextInhibitInputSpeChat(etUsername);//用户名调用判断规范方法
        setEditTextInhibitInputSpeChat(etPassword);//密码调用判断规范方法
        //picasso加载登录页上方图片，并且处理为圆角
        Picasso.with(LoginMainActivity.this)
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
                //定义不可输入的字符
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                //如果输入有空格或者有以上不可输入字符，则输入框不输入字符
                if (source.equals(" ") || source.equals("\n") || matcher.find())
                    return "";
                else
                    return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});//添加exittext字符过滤
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
//        getCurrentVersion();
        if (NetWork.isNetWorkAvailable(this)) {
            /*登录是否设置保存时间，以及加密*/
            /*检测是否为可用WiFi*/
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String infossid = wifiInfo.getSSID();
            infossid = infossid.replace("\"", "");
            if (infossid.equals("taoxinxi")) {
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle("系统提示");
                dialog.setMessage("当前 " + infossid + " 为测试WiFi,请连接到公共WiFi或者流量状态");
                dialog.setButton("确定", listenerwifi);
                dialog.show();
            } else {
                final ProgressDialog progressDialog = ProgressDialog.show(this,
                        getResources().getString(R.string.login_his_later), getResources().getString(R.string.logining), false, true);//等待加载弹窗
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
                        if (!content.equals("null")) {//如果返回结果不为空则执行方法内部
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                            thread.start();
                            userNameValue = etUsername.getText().toString();
                            passwordValue = etPassword.getText().toString();
                            Editor editor = sp.edit();
                            Gson gson = new Gson();
                            UsergetBean userBean = gson.fromJson(content, UsergetBean.class);
                            if (userBean.isStatus() == true) {
                                spUtils.put(LoginMainActivity.this, "username", userNameValue);
                                spUtils.put(LoginMainActivity.this, "passwd", passwordValue);
                                //记住密码
                                if (checkBoxPw.isChecked()) {
                                    spUtils.put(LoginMainActivity.this, "remember", true);
                                    String listwork = sp.getString("workbeenlist", "");
                                    workPwSwitchBean = new Gson().fromJson(listwork, WorkPwSwitchBean.class);
                                    //实体类等于空，也就是第一次进的时候，数据是空的
                                    //第一次加载为空时，添加当前登录人信息到bean类保存
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
                                    } else {//bean不为空时，继续按list顺序添加保存
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
                                            ToastUtils.ShowToastMessage("已有当前账号,登录中", LoginMainActivity.this);
                                        }
                                    }
                                    System.out.print(workPwSwitchBean);
                                    String workbeenlist = gson.toJson(switchBeendatalist);
                                    String worklist = gson.toJson(workPwSwitchBean);
                                    System.out.print(worklist);
                                    System.out.print(workbeenlist);
                                    spUtils.put(LoginMainActivity.this, "workbeenlist", worklist);
                                } else {
                                    spUtils.put(LoginMainActivity.this, "remember", false);
                                }
                                if (checkboxopen.isChecked()) {
                                    spUtils.put(LoginMainActivity.this, "autologin", true);
                                } else {
                                    spUtils.put(LoginMainActivity.this, "autologin", false);
                                }
                                editor.commit();
                                //登录成功后，保存用户名与用户ID
                                spUtils.put(getApplicationContext(), "name", userBean.getU_name());
                                spUtils.put(getApplicationContext(), "proname", userBean.getU_name());
                                spUtils.put(getApplicationContext(), "commoname", userBean.getU_name());
                                spUtils.put(getApplicationContext(), "commologinid", userBean.getLogid());
                                Intent intent = new Intent(LoginMainActivity.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("u_name", userBean.getU_name());
                                intent.putExtras(bundle);
                                startActivity(intent);

                            } else {
                                ToastUtils.ShowToastMessage(R.string.user_tips, LoginMainActivity.this);
                                ResponseDialog.closeLoading();
                            }
                        } else {
                            progressDialog.dismiss();
                            ToastUtils.ShowToastMessage("返回为空,请确认本机ip", LoginMainActivity.this);
                        }
                    }

                    @Override
                    public void onFailure(Throwable error, String content) {//异常
                        super.onFailure(error, content);
                        ToastUtils.ShowToastMessage(R.string.login_has_error, LoginMainActivity.this);
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                            }
                        });
                        thread.start();
                    }

                    @Override
                    public void onFinish() {//登录成功后所走方法
                        super.onFinish();
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();

                                LoginMainActivity.this.finish();
                            }
                        });
                        thread.start();
                    }
                });
            }
        } else {
            ToastUtils.ShowToastMessage(getString(R.string.noHttp), LoginMainActivity.this);
        }
    }

    /**
     * 比较两个数组
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

    /**
     * 获取本机版本号
     */
    private void getCurrentVersion() {
        try {
            PackageInfo info =
                    getPackageManager().
                            getPackageInfo(getPackageName(), 0);
            curVersionName = info.versionName;//版本名称
            curVersionCode = info.versionCode;//版本号
            tvcode.setText("v" + curVersionName);
            spUtils.put(LoginMainActivity.this, "curVersionCode", curVersionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * 版本更新
     */
    private void checkAppVersion(final boolean slience) {
        getCurrentVersion();
        String strversion = HttpUrl.debugoneUrl + "AppVersion/GetAppVersion";
        if (NetWork.isNetWorkAvailable(this)) {
            NetUtil.getAsyncHttpClient().get(strversion, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    System.out.print(content);
                    //转义字符
                    content = content.replace("{", "{\"");
                    System.out.print(content);
                    content = content.replace("\'", "\"");
                    System.out.print(content);
                    content = content.replace(",", ",\"");
                    System.out.print(content);
                    content = content.replace(":\"", "\":\"");
                    System.out.print(content);
                    String strfram = StringUtil.sideTrim(content, "\"");
                    System.out.print(strfram);
                    try {
                        codeBean = new Gson().fromJson(strfram, VerCodeBean.class);
                        String vercode = codeBean.getVerCode();//版本号
                        System.out.print(vercode);
                        String apkpath = codeBean.getApkPath();//版本地址
                        System.out.print(apkpath);
                        String reason = codeBean.getReason();//版本说明
                        System.out.print(reason);
                        spUtils.put(getApplicationContext(), "applicationvercodeupdate", vercode);
                        spUtils.put(LoginMainActivity.this, "applicationapkpath", apkpath);
                        spUtils.put(LoginMainActivity.this, "applicationreason", reason);
                        String versioncode = String.valueOf(curVersionName);
                        //目前只能是判断当前已安装的apk与最新全量包的版本
                        //所以要将之后未写的几个版本提前添加到下面已好判断
                        if (!versioncode.equals(vercode)) {
                            if (versioncode.equals("1.0.0")) {
                                patchPath = "http://owrlsx373.bkt.clouddn.com/patch.patch";
                                showUpdateApk("1.0.1");
                                spUtils.put(getApplicationContext(), "Applicationscode", "新版本: v" + vercode);
                                return;
                            } else if (versioncode.equals("1.0.1")) {
                                patchPath = "http://owrlsx373.bkt.clouddn.com/patch1.patch";
                                showUpdateApk("1.0.2");
                                spUtils.put(getApplicationContext(), "Applicationscode", "新版本: v" + vercode);
                                return;
                            } else if (versioncode.equals("1.0.2")) {
                                patchPath = "http://owrlsx373.bkt.clouddn.com/patch2.patch";
                                showUpdateApk("1.0.3");
                                spUtils.put(getApplicationContext(), "Applicationscode", "新版本: v" + vercode);
                                return;
                            } else if (versioncode.equals("1.0.3")) {
                                patchPath = "http://owrlsx373.bkt.clouddn.com/patch3.patch";
                                showUpdateApk("1.0.4");
                                spUtils.put(getApplicationContext(), "Applicationscode", "新版本: v" + vercode);
                                return;
                            } else if (versioncode.equals("1.0.4")) {
                                patchPath = "http://owrlsx373.bkt.clouddn.com/patch4.patch";
                                showUpdateApk("1.0.5");
                                spUtils.put(getApplicationContext(), "Applicationscode", "新版本: v" + vercode);
                                return;
                            } else if (versioncode.equals("1.0.5")) {
                                patchPath = "http://owrlsx373.bkt.clouddn.com/patch5.patch";
                                showUpdateApk("1.0.6");
                                spUtils.put(getApplicationContext(), "Applicationscode", "新版本: v" + vercode);
                                return;
                            }
                        } else {
                            if (!slience) {
                                String scode = "已经是最新版本" + vercode;
                                spUtils.put(getApplicationContext(), "Applicationscode", scode);
                                new AlertDialog.Builder(LoginMainActivity.this)
                                        .setTitle("检查新版本")
                                        .setMessage("您所使用的已经是最新版")
                                        .setPositiveButton("OK", null).create()
                                        .show();
                            }
                        }
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
            ToastUtils.ShowToastMessage("当前网络不可用，请重新尝试", LoginMainActivity.this);
        }
    }

    /**
     * 增量更新弹窗
     *
     * @param vCode
     */
    private void showUpdateApk(String vCode) {
        getSelfApkPath();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("软件版本更新");
        builder.setMessage("增量更新：v" + vCode + "版本");
        builder.setPositiveButton("立即更新",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showuupdateDownloadDialog(1);
                    }
                });
        noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.setCancelable(false);
        noticeDialog.show();
    }

    private void startWifi() {
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String infossid = wifiInfo.getSSID();
    }

    /**
     * 下载增量包进度条
     *
     * @param focuseUpdate
     */
    private void showuupdateDownloadDialog(int focuseUpdate) {
        AlertDialog.Builder buildupdate = new AlertDialog.Builder(this);
        buildupdate.setTitle("正在下载新版本");
        final LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.update_progress, null);//下载进度条
        nProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        nProgressText = (TextView) v.findViewById(R.id.update_progress_text);
        buildupdate.setView(v);
        if (focuseUpdate == 0) {
            buildupdate.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    dialog.dismiss();
                    inupdateFlag = true;
                }
            });
        }
        updateloadDialog = buildupdate.create();
        updateloadDialog.setCanceledOnTouchOutside(false);
        updateloadDialog.setCancelable(focuseUpdate != 1);
        updateloadDialog.show();//使dialog可以正常运行
        updateLoadApk();//开启线程下载文件
    }

    private Thread updateLoadThread;

    /**
     * 开启线程
     */
    private void updateLoadApk() {
        updateLoadThread = new Thread(mupdateApkRunnable);
        updateLoadThread.start();
    }

    /**
     * 开启线程将网络文件下载到本地
     */
    private Runnable mupdateApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                String apkName = "CLApp_" + "Dfapp" + ".patch";//命名patch文件
                // 判断是否挂载了SD卡
                String apkpath = patchPath;
                URL url = new URL(apkpath);//将地址转化为url路径
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                //获取apk内容长度
                Long toasize = Long.parseLong(conn.getHeaderField("Content-Length"));
                //如果远程文件存在，则进入下面方法进行下载
                if (toasize > 30) {
                    System.out.println("存在");
                    String storageState = Environment.getExternalStorageState();
                    if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                        //获取sd卡读写路径
                        savePath = Environment.getExternalStorageDirectory()
                                .getAbsolutePath() + "/dfAppupdate/";
                        File file = new File(savePath);//将地址转化为file路径
                        if (!file.exists()) {
                            file.mkdirs();//创建该文件夹
                        }
                        apkFilePath = savePath + apkName;//拼接地址与命名
                    }
                    // 没有挂载SD卡，无法下载文件
                    if (apkFilePath == null || apkFilePath == "") {
                        mUpdateHandler.sendEmptyMessage(DOWN_NOSDCARD);
                        return;
                    }
                    File ApkFile = new File(apkFilePath);
                    // 是否已下载更新文件，如果已经下载，则关闭进度条弹窗然后合并
                    if (ApkFile.exists()) {
                        updateloadDialog.dismiss();
                        new PatchTask().execute();
                        return;
                    }
                    // 输出临时下载文件
                    File tmpFile = new File(apkFilePath);
                    FileOutputStream fos = new FileOutputStream(tmpFile);
                    sp = LoginMainActivity.this.getSharedPreferences("my_sp", 0);

                    conn.connect();
                    int length = conn.getContentLength();

                    InputStream is = conn.getInputStream();
                    // 显示文件大小格式：2个小数点显示
                    DecimalFormat df = new DecimalFormat("0.00");
                    // 进度条下面显示的总文件大小
                    apkUpdateFileSize = df.format((float) length / 1024 / 1024) + "MB";
                    int count = 0;
                    byte buf[] = new byte[1024];
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 进度条下面显示的当前下载文件大小
                        tmpUpdateFileSize = df.format((float) count / 1024 / 1024) + "MB";
                        // 当前进度值
                        updateProgress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mUpdateHandler.sendEmptyMessage(DOWN_UPDATE);
                        if (numread <= 0) {
                            // 下载完成 - 将临时下载文件转成APK文件
                            if (tmpFile.renameTo(ApkFile)) {
                                // 通知安装
                                mUpdateHandler.sendEmptyMessage(DOWN_OVER);
                            }
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!interceptFlag);// 点击取消就停止下载
                    fos.close();//关闭文件传输流
                    is.close();
                } else {
                    mUpdateHandler.sendEmptyMessage(DOWN_NOUPDATE);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 线程中的通信机制弹出信息
     */
    private Handler mUpdateHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE://正常更新下载
                    nProgress.setProgress(updateProgress);
                    nProgressText.setText(tmpUpdateFileSize + "/" + apkUpdateFileSize);
                    break;
                case DOWN_OVER://下载完毕
                    updateloadDialog.dismiss();
                    new PatchTask().execute();
                    break;
                //sd卡没有找到
                case DOWN_NOSDCARD:
                    updateloadDialog.dismiss();
                    ToastUtils.ShowToastMessage("无法下载安装文件，请检查SD卡是否挂载"
                            , LoginMainActivity.this);
                    break;
                //找不到网络中的增量文件
                case DOWN_NOUPDATE:
                    updateloadDialog.dismiss();
                    ToastUtils.ShowToastMessage("该增量文件不存在", LoginMainActivity.this);
                    break;
            }
        }
    };

    /**
     * 合并网络patch增量包和本地旧apk，并安装
     */
    private class PatchTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            final String newDir, patchDir;
            try {
                // 指定包名的程序源文件路径
                sp = LoginMainActivity.this.getSharedPreferences("my_sp", 0);
                String infosouDir = sp.getString("infosouDir", "");
                //获取到本地的patch文件地址以及命名合并apk的名称地址
                newDir = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/dfAppupdate/newPatchdaff.apk";//创建的新apk地址
                patchDir = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/dfAppupdate/" + "CLApp_" +
                        "Dfapp" + ".patch";//已转到本地的增量包patch
                File patchFile = new File(patchDir);
                final File newFile = new File(newDir);
                //判断本地增量文件是否存在
                if (!patchFile.exists()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.ShowToastMessage("文件不存在...", LoginMainActivity.this);
                        }
                    });
                    return WHAT_FAIL_PATCH;
                }
                //合并本地apk与远程patch差异包
                int result = PatchUtils.getInstance().patch(infosouDir, newDir, patchDir);
                //0为合并成功
                if (result == 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "合成APK成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            //合并成功后打开该apk文件进行安装
                            intent.setDataAndType(Uri.parse("file://" + newFile.toString()),
                                    "application/vnd.android.package-archive");
                            startActivity(intent);
                        }
                    });
                    return WHAT_SUCCESS;
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.ShowToastMessage("合成APK失败", LoginMainActivity.this);
                        }
                    });
                    return WHAT_FAIL_PATCH;
                }
            } catch (Exception e) {
                Log.i("jw", "error:" + Log.getStackTraceString(e));
            }
            return WHAT_FAIL_PATCH;
        }
    }

    //获取本应用的apk包路径
    public String getSelfApkPath() {
        //查询所有已安装的应用程序
        List<ApplicationInfo> installList = getPackageManager().
                getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (int i = 0; i < installList.size(); i++) {//循环所有应用程序
            ApplicationInfo info = installList.get(i);
            //如果当前应用程序包名和本程序的包名一致则进入方法
            if (info.packageName.equals(getPackageName())) {
                Log.i("jw", "publicdir:" + info.publicSourceDir + ",sourcedir:" + info.sourceDir);
                spUtils.put(this, "infosouDir", info.sourceDir.toString());
                return info.sourceDir;
            }
        }
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}