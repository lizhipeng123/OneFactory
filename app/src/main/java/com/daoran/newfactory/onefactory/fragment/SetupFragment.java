package com.daoran.newfactory.onefactory.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.login.LoginDebugActivity;
import com.daoran.newfactory.onefactory.bean.VerCodeBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.util.settings.Comfig;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

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

    private String curVersionName;
    private int curVersionCode;
    private VerCodeBean bean;
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
    }

    /**
     * 操作控件
     */
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
                checkAppVersion(true);
                break;
        }
    }

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
                            try {
                                codeBean = new Gson().fromJson(strfram, VerCodeBean.class);
                                String vercode = codeBean.getVerCode();
                                System.out.print(vercode);
                                String apkpath = codeBean.getApkPath();
                                System.out.print(apkpath);
                                String reason = codeBean.getReason();
                                System.out.print(reason);
                                spUtils.put(mactivity,"vercodeupdate",vercode);
                                spUtils.put(mactivity,"apkpath",apkpath);
                                spUtils.put(mactivity,"reason",reason);
                                String versioncode = String.valueOf(curVersionName);
                                if (!versioncode.equals(vercode)) {
//                                    ToastUtils.ShowToastMessage("需要更新", mactivity);
//                                    ToastUtils.ShowToastMessage("code:" + vercode + "," +
//                                            "curversion:" + curVersionCode, mactivity);
                                    showNoticeDialog(0,slience);
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
        String reason = sp.getString("reason","");
        String reaid = sp.getString("vercodeupdate","");
        if (Comfig.isDebug) {
            System.out.println(focuseUpdate);
        }
        if (focuseUpdate == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
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
                sp = mactivity.getSharedPreferences("my_sp",0);
                String apkpath = sp.getString("apkpath","");
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

}
