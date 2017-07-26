package com.daoran.newfactory.onefactory.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.login.LoginMainActivity;
import com.daoran.newfactory.onefactory.activity.work.setting.AboutActivity;
import com.daoran.newfactory.onefactory.activity.work.setting.CoreActivity;
import com.daoran.newfactory.onefactory.activity.work.setting.ExcelSDActivity;
import com.daoran.newfactory.onefactory.bean.VerCodeBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.DataCleanManager;
import com.daoran.newfactory.onefactory.util.settings.Comfig;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 设置模块
 * Created by lizhipeng on 2017/3/22.
 */

public class SetupFragment extends Fragment implements View.OnClickListener {
    Activity mactivity;
    private Toolbar tbarSetup;
    private View view;
    private RelativeLayout rlAgainLogin, rlEditionUpdate;
    private TextView tvVersion, tvNewVersion;
    private VerCodeBean codeBean;

    private String curVersionName;
    private int curVersionCode;
    private VerCodeBean bean;
    private AlertDialog noticeDialog;
    private ProgressBar mProgress;
    private TextView mProgressText;
    protected boolean interceptFlag;
    private AlertDialog downloadDialog;
    private TextView tv_clean;
    private RelativeLayout rlClean;
    private RelativeLayout rlwifi;
    private RelativeLayout rlCore;
    private RelativeLayout rlAbout;
    private RelativeLayout rlExcelSD;
    private RelativeLayout rlYunxin;
    private TextView tvwifimanager, tvwifissid;

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

    private Dialog progressDialog;
    private SharedPreferences sp;
    private SPUtils spUtils;

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

    /**
     * 实例化控件
     */
    private void getViews() {
        rlAgainLogin = (RelativeLayout) view.findViewById(R.id.rlAgainLogin);
        rlEditionUpdate = (RelativeLayout) view.findViewById(R.id.rlEditionUpdate);
        tvVersion = (TextView) view.findViewById(R.id.tvVersion);
        rlClean = (RelativeLayout) view.findViewById(R.id.rlClean);
        tv_clean = (TextView) view.findViewById(R.id.tv_clean);
        rlwifi = (RelativeLayout) view.findViewById(R.id.rlwifi);
        tvwifimanager = (TextView) view.findViewById(R.id.tvwifimanager);
        tvwifissid = (TextView) view.findViewById(R.id.tvwifissid);
        tvNewVersion = (TextView) view.findViewById(R.id.tvNewVersion);
        rlCore = (RelativeLayout) view.findViewById(R.id.rlCore);
        rlAbout = (RelativeLayout) view.findViewById(R.id.rlAbout);
        rlExcelSD = (RelativeLayout) view.findViewById(R.id.rlExcelSD);
        rlYunxin = (RelativeLayout) view.findViewById(R.id.rlYunxin);
        sp = mactivity.getSharedPreferences("my_sp", 0);
        String vercode = sp.getString("Applicationscode", "");
        tvNewVersion.setText(vercode);
        String cleanmana = getAppCacheSize();
        tv_clean.setText(cleanmana);
        spUtils.put(mactivity, "cleanmana", cleanmana);
    }

    /**
     * 操作控件
     */
    private void initViews() {
        rlAgainLogin.setOnClickListener(this);
        rlEditionUpdate.setOnClickListener(this);
        tvVersion.setOnClickListener(this);
        rlClean.setOnClickListener(this);
        rlwifi.setOnClickListener(this);
        rlCore.setOnClickListener(this);
        rlAbout.setOnClickListener(this);
        rlExcelSD.setOnClickListener(this);
        rlYunxin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*退出当前账号*/
            case R.id.rlAgainLogin:
                AlertDialog dialog = new AlertDialog.Builder(mactivity).create();
                dialog.setTitle("系统提示");
                dialog.setMessage("确定重新登录吗");
                dialog.setButton("确定", listener);
                dialog.setButton2("取消", listener);
                dialog.show();
                break;
            /**/
            case R.id.rlEditionUpdate:
                break;
            /*版本更新*/
            case R.id.tvVersion:
                checkAppVersion(true);
                break;
            /*清除缓存*/
            case R.id.rlClean:
                AlertDialog dialog1 = new AlertDialog.Builder(mactivity).create();
                dialog1.setTitle("系统提示");
                dialog1.setMessage("确定清除缓存吗");
                dialog1.setButton("确定", listenerClean);
                dialog1.setButton2("取消", listenerClean);
                dialog1.show();
                break;
            /**/
            case R.id.rlwifi:
                startWifi();
                break;
            /*二维码*/
            case R.id.rlCore:
                Intent intent = new Intent(mactivity, CoreActivity.class);
                mactivity.startActivity(intent);
                break;
            /*关于dfapp*/
            case R.id.rlAbout:
                Intent intentAbout = new Intent(mactivity, AboutActivity.class);
                mactivity.startActivity(intentAbout);
                break;
            /*excel文件*/
            case R.id.rlExcelSD:
                Intent intentExcel = new Intent(mactivity, ExcelSDActivity.class);
                mactivity.startActivity(intentExcel);
                break;
            /*云信*/
            case R.id.rlYunxin:
                boolean isinstall = isAppInstalled(mactivity, "com.netease.nim.demo");
                if (isinstall == false) {
//                    if(copyApkFromAssets(getContext(), "debug.apk", Environment.getExternalStorageDirectory().getAbsolutePath()+"/debug.apk")){
//                        AlertDialog.Builder m = new AlertDialog.Builder(mactivity)
//                                .setIcon(R.drawable.ic_launcher).setMessage("是否安装？")
//                                .setIcon(R.drawable.ic_launcher)
//                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        intent.setDataAndType(Uri.parse("file://" + Environment.getExternalStorageDirectory().getAbsolutePath()+"/debug.apk"),
//                                                "application/vnd.android.package-archive");
//                                        mactivity.startActivity(intent);
//                                    }
//                                });
//                        m.show();
//                    }
                    intallApp(mactivity);
                } else {
                    startApp(mactivity, "com.netease.nim.demo");
                }
                break;
        }
    }

    private boolean isAppInstalled(Context context, String packagename) {

        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            ToastUtils.ShowToastMessage("没有安装", mactivity);
//            intallApp(mactivity);
            return false;
        } else {
            ToastUtils.ShowToastMessage("已安装", mactivity);

            return true;
        }
    }

    public boolean copyApkFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }

    /**
     * 安装云信apk
     *
     * @param context
     */
    public void intallApp(Context context) {
        try {
            String path = context.getFilesDir().getAbsolutePath() + "/nim.apk";  //从assets中解压到这个目录
            File f = new File(path);
            if (!f.exists()) {
                f.createNewFile();
            }
            InputStream is = context.getAssets().open("nim.apk");//assets里的文件在应用安装后仍然存在于apk文件中
            inputStreamToFile(is, f);
            String cmd = "chmod 777 " + f.getAbsolutePath();
            Runtime.getRuntime().exec(cmd);
            cmd = "chmod 777 " + f.getParent();
            Runtime.getRuntime().exec(cmd);
            // 尝试提升上2级的父文件夹权限，在阅读插件下载到手机存储时，
            // 刚好用到了2级目录
            // /data/data/packagename/files/这个目录下面所有的层级目录都需要提升权限，
            // 才可安装apk，弹出安装界面
            cmd = "chmod 777 " + new File(f.getParent()).getParent();
            Runtime.getRuntime().exec(cmd);
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            String type = "application/vnd.android.package-archive";
            /* 设置intent的file与MimeType */
            intent.setDataAndType(Uri.fromFile(f), type);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inputStreamToFile(InputStream inputStream, File file) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 启动已安装的云信apk
     *
     * @param context
     * @param packageName
     * @return
     */
    public boolean startApp(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> listInfos = pm.queryIntentActivities(intent, 0);
        String className = null;
        for (ResolveInfo info : listInfos) {
            if (packageName.equals(info.activityInfo.packageName)) {
                className = info.activityInfo.name;
                break;
            }
        }
        if (className != null && className.length() > 0) {
            intent.setComponent(new ComponentName(packageName, className));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    startActivity(new Intent(getActivity(), LoginMainActivity.class));
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };

    DialogInterface.OnClickListener listenerClean = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE://确定
                    showClearDialog();
                    break;
                case AlertDialog.BUTTON_NEGATIVE://取消
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 获取本机版本号
     */
    private void getCurrentVersion() {
        try {
            PackageInfo info =
                    mactivity.getPackageManager().
                            getPackageInfo(mactivity.getPackageName(), 0);
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
        if (NetWork.isNetWorkAvailable(mactivity)) {
            NetUtil.getAsyncHttpClient().get(strversion, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    System.out.print(content);
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
                        spUtils.put(mactivity, "vercodeupdate", vercode);
                        spUtils.put(mactivity, "apkpath", apkpath);
                        spUtils.put(mactivity, "reason", reason);
                        String versioncode = String.valueOf(curVersionName);
                        if (!versioncode.equals(vercode)) {
                            showNoticeDialog(0, slience);
                        } else {
                            if (!slience) {
                                new AlertDialog.Builder(mactivity)
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
            ToastUtils.ShowToastMessage("当前网络不可用，请重新尝试", mactivity);
        }
    }

    /**
     * 显示版本更新通知对话框
     * focuseUpdate 0:自己服务端更新 1：自己服务端强制更新
     */
    public void showNoticeDialog(int focuseUpdate, boolean slience) {
        sp = mactivity.getSharedPreferences("my_sp", 0);
        String reason = sp.getString("reason", "");
        String reaid = sp.getString("vercodeupdate", "");
        if (Comfig.isDebug) {
            System.out.println(focuseUpdate);
        }
        if (focuseUpdate == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
            builder.setTitle("发现新版本： " + reaid);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
        builder.setTitle("正在下载新版本");
        final LayoutInflater inflater = LayoutInflater.from(mactivity);
        View v = inflater.inflate(R.layout.update_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        mProgressText = (TextView) v.findViewById(R.id.update_progress_text);
        builder.setView(v);
        if (focuseUpdate == 0) {
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
                    Toast.makeText(mactivity, "无法下载安装文件，请检查SD卡是否挂载",
                            Toast.LENGTH_SHORT).show();
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
                sp = mactivity.getSharedPreferences("my_sp", 0);
                String apkpath = sp.getString("apkpath", "");
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

    private void startWifi() {
        WifiManager wifiManager = (WifiManager) mactivity.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String infossid = wifiInfo.getSSID();
        tvwifimanager.setText(wifiManager.toString());
        tvwifissid.setText(infossid);
    }

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

    /**
     * 检测缓存大小
     *
     * @return
     */
    private String getAppCacheSize() {
        try {
            long cacheSize = DataCleanManager.getFolderSize(mactivity.getApplicationContext().getCacheDir()) +
                    DataCleanManager.getFolderSize(mactivity.getApplicationContext().getExternalCacheDir());
            return DataCleanManager.getFormatSize(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "--B";
    }

    /**
     * 清除缓存
     */
    private void showClearDialog() {
        progressDialog = new Dialog(mactivity, R.style.CustomProgressDialog);
        progressDialog.setContentView(R.layout.set_clearcache_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.message);
        Button cancelButton = (Button) progressDialog.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.dismiss();
            }
        });
        msg.setText("清理中，请稍后…");
        progressDialog.show();
        Boolean b1 = DataCleanManager.deleteDir(mactivity.getApplicationContext().getCacheDir());
        Boolean b2 = DataCleanManager.deleteDir(mactivity.getApplicationContext().getExternalCacheDir());
        if (b1 && b2) {
            final String cachesize = getAppCacheSize();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    tv_clean.setText(cachesize);
                    sp = mactivity.getSharedPreferences("my_sp", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    String spcleanmana = sp.getString("cleanmana", "");
                    ToastUtils.ShowToastMessage("清理了" + spcleanmana + "数据", mactivity);
                    editor.remove("cleanmana");
                    editor.commit();
                    progressDialog.dismiss();
                }
            }, 1000);
        }
    }
}