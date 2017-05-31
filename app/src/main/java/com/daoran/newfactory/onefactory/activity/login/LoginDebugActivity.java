package com.daoran.newfactory.onefactory.activity.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.RequestParams;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.util.settings.Comfig;
import com.daoran.newfactory.onefactory.view.CropSquareTransformation;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.NameValuePair;

import java.io.File;
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

import okhttp3.Call;

/**
 * 登录页
 * Created by lizhipeng on 2017/4/10.
 */
public class LoginDebugActivity extends BaseFrangmentActivity {
    private Button btnLogin;
    private EditText etUsername, etPassword;
//    private SharedHelper sh;
    private SPUtils spUtils;
    private CheckBox checkBoxPw, checkboxopen;
    private SharedPreferences sp;
    private String userNameValue, passwordValue;
    private ImageView image_login;

    private String curVersionName;
    private int curVersionCode;
    private VerCodeBean codeBean;
    private AlertDialog noticeDialog;
    private ProgressBar mProgress;
    private TextView mProgressText;
    protected boolean interceptFlag;
    private AlertDialog downloadDialog;

    private static final int DOWN_NOSDCARD = 0;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;

    private static final int DIALOG_TYPE_LATEST = 0;
    private static final int DIALOG_TYPE_FAIL = 1;

    protected int progress;
    protected String apkFileSize;
    protected String tmpFileSize;
    protected String savePath;
    protected String apkFilePath;
    protected String tmpFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getViews();
        initViews();
        checkAppVersion(true);
//        sh = new SharedHelper(this);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    postLogin();
                }
            }
        });
        sp = this.getSharedPreferences("my_sp", 0);
        String name = sp.getString("username", "");
        String passwd = sp.getString("passwd", "");
        boolean choseRemember = sp.getBoolean("remember", false);
        boolean choseAutoLogin = sp.getBoolean("autologin", false);
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
                            spUtils.put(LoginDebugActivity.this,"username",userNameValue);
                            spUtils.put(LoginDebugActivity.this,"passwd",passwordValue);
                            //记住密码
                            if (checkBoxPw.isChecked()) {
                                spUtils.put(LoginDebugActivity.this,"remember",true);
                            } else {
                                spUtils.put(LoginDebugActivity.this,"remember",false);
                            }
                            if (checkboxopen.isChecked()) {
                                spUtils.put(LoginDebugActivity.this,"autologin",true);
                            } else {
                                spUtils.put(LoginDebugActivity.this,"autologin",false);
                            }
                            editor.commit();
                            spUtils.put(getApplicationContext(), "name", userBean.getU_name());
                            spUtils.put(getApplicationContext(),"proname",userBean.getU_name());
                            spUtils.put(getApplicationContext(),"commoname",userBean.getU_name());
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

    /**
     * 获取本机版本号
     */
    private void getCurrentVersion() {
        try {
            PackageInfo info =
                    getPackageManager().
                            getPackageInfo(getPackageName(), 0);
            curVersionName = info.versionName;
            curVersionCode = info.versionCode;
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
            OkHttpUtils.get()
                    .url(strversion)
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
                            try {
                                codeBean = new Gson().fromJson(strfram, VerCodeBean.class);
                                String vercode = codeBean.getVerCode();//版本号
                                System.out.print(vercode);
                                String apkpath = codeBean.getApkPath();//版本地址
                                System.out.print(apkpath);
                                String reason = codeBean.getReason();//版本说明
                                System.out.print(reason);
                                spUtils.put(getApplicationContext(),"applicationvercodeupdate",vercode);
                                spUtils.put(LoginDebugActivity.this,"applicationapkpath",apkpath);
                                spUtils.put(LoginDebugActivity.this,"applicationreason",reason);
                                String versioncode = String.valueOf(curVersionName);
                                if (!versioncode.equals(vercode)) {
                                    String scode = "需要更新到"+vercode;
                                    spUtils.put(getApplicationContext(),"Applicationscode",scode);
                                    showNoticeDialog(0,slience);
                                } else {
                                    if (!slience) {
                                        String scode = "已经是最新版本"+vercode;
                                        spUtils.put(getApplicationContext(),"Applicationscode",scode);
                                        new AlertDialog.Builder(LoginDebugActivity.this)
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
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用，请重新尝试", LoginDebugActivity.this);
        }
    }

    /**
     * 显示版本更新通知对话框
     * focuseUpdate 0:自己服务端更新 1：自己服务端强制更新
     */
    public void showNoticeDialog(int focuseUpdate, boolean slience)  {
        sp = getSharedPreferences("my_sp", 0);
        String reason = sp.getString("applicationreason","");
        String reaid = sp.getString("applicationvercodeupdate","");
        if (Comfig.isDebug) {
            System.out.println(focuseUpdate);
        }
        if (focuseUpdate == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginDebugActivity.this);
            builder.setTitle("发现新版本： "+reaid);
            builder.setMessage("更新日志:   " + reason);
            builder.setPositiveButton("立即更新",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            showDownloadDialog(0);
                        }
                    });
            builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            noticeDialog = builder.create();
            noticeDialog.setCanceledOnTouchOutside(false);
            noticeDialog.show();
        } else if (focuseUpdate == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginDebugActivity.this);
            builder.setTitle("软件版本更新");
            builder.setMessage("发现新版本  " + reason + ",您必须安装此版本更新才能继续使用");
            builder.setPositiveButton("立即更新",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            showDownloadDialog(1);
                        }
                    });
            noticeDialog = builder.create();
            noticeDialog.setCanceledOnTouchOutside(false);
            noticeDialog.setCancelable(false);
            noticeDialog.show();
        }
    }

    /**
     * 显示下载对话框
     */
    private void showDownloadDialog(int focuseUpdate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginDebugActivity.this);
        builder.setTitle("正在下载新版本");
        final LayoutInflater inflater = LayoutInflater.from(LoginDebugActivity.this);
        View v = inflater.inflate(R.layout.update_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        mProgressText = (TextView) v.findViewById(R.id.update_progress_text);
        builder.setView(v);
        if (focuseUpdate == 0) {
//            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
////                    interceptFlag = true;
//                }
//            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    dialog.dismiss();
                    interceptFlag = true;
                }
            });
        }
        downloadDialog = builder.create();
        downloadDialog.setCanceledOnTouchOutside(false);
        downloadDialog.setCancelable(focuseUpdate != 1);
        downloadDialog.show();
        downloadApk();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    mProgressText.setText(tmpFileSize + "/" + apkFileSize);
                    break;
                case DOWN_OVER:
                    downloadDialog.dismiss();
                    installApk();
                    break;
                case DOWN_NOSDCARD:
                    downloadDialog.dismiss();
                    ToastUtils.ShowToastMessage("无法下载安装文件，请检查SD卡是否挂载",
                            LoginDebugActivity.this);
                    break;
            }
        }

        ;
    };

    /**
     * 开启线程更新app
     */
    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                String apkName = "CLApp_"
                        + "Dfapp" + ".apk";
                String tmpApk = "CLApp_"
                        + "Dfapp" + ".tmp";
                // 判断是否挂载了SD卡
                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                    savePath = Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + "/CL/Update/";
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    apkFilePath = savePath + apkName;
                    tmpFilePath = savePath + tmpApk;
                }

                // 没有挂载SD卡，无法下载文件
                if (apkFilePath == null || apkFilePath == "") {
                    mHandler.sendEmptyMessage(DOWN_NOSDCARD);
                    return;
                }

                File ApkFile = new File(apkFilePath);

                // 是否已下载更新文件
                if (ApkFile.exists()) {
                    downloadDialog.dismiss();
                    installApk();
                    return;
                }

                // 输出临时下载文件
                File tmpFile = new File(tmpFilePath);
                FileOutputStream fos = new FileOutputStream(tmpFile);
                sp = getSharedPreferences("my_sp",0);
                String apkpath = sp.getString("applicationapkpath","");
                URL url = new URL(apkpath);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                // 显示文件大小格式：2个小数点显示
                DecimalFormat df = new DecimalFormat("0.00");
                // 进度条下面显示的总文件大小
                apkFileSize = df.format((float) length / 1024 / 1024) + "MB";

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    // 进度条下面显示的当前下载文件大小
                    tmpFileSize = df.format((float) count / 1024 / 1024) + "MB";
                    // 当前进度值
                    progress = (int) (((float) count / length) * 100);
                    // 更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        // 下载完成 - 将临时下载文件转成APK文件
                        if (tmpFile.renameTo(ApkFile)) {
                            // 通知安装
                            mHandler.sendEmptyMessage(DOWN_OVER);
                        }
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);// 点击取消就停止下载
                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    private Thread downLoadThread;

//    private void startWifi(){
//        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        String infossid = wifiInfo.getSSID();
//        tvwifimanager.setText(wifiManager.toString());
//        tvwifissid.setText(infossid);
//    }

    /**
     * 下载apk
     */
    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 安装apk
     */
    private void installApk() {
        File apkfile = new File(apkFilePath);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        startActivity(i);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }
}
