package com.daoran.newfactory.onefactory.util.application.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理异常
 * Created by lizhipeng on 2017/7/18.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    StringBuffer sbs = new StringBuffer();
    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();
    //程序的Context对象
    private Context mContext;

    //用于格式化日期
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {}

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当uncaught发生变化时，转入函数处理
     *
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        final Throwable exsThrowable = ex;
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                showDialog(mContext);
                Looper.loop();
            }
        }.start();
        // 1.获取软件版本信息
        String versioninfo = getVersionInfo();
        // 2.获取手机的硬件信息.
        String mobileInfo = getMobileInfo();
        // 3.把错误的堆栈信息 获取出来
        String errorinfo = getErrorInfo(ex);
        String time = formatter.format(new Date());
        sbs.append(time);
        sbs.append("\n");
        sbs.append("软件版本:" + versioninfo);
        sbs.append("\n");
        sbs.append("手机型号" + mobileInfo);
        sbs.append("\n");
        sbs.append(errorinfo);
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 弹窗提示错误信息
     *
     * @param context
     */
    private void showDialog(final Context context) {
        final Dialog dialog;
        AlertDialog.Builder buder = new AlertDialog.Builder(context);
        buder.setTitle("温馨提示");
        buder.setMessage("由于发生了一个未知错误，应用已关闭，" +
                sbs.toString());
        buder.setPositiveButton("上传", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                //上传处理---将上传的信息
                Log.e(TAG, "---" + sbs.toString());
                Toast.makeText(mContext, sbs.toString(), Toast.LENGTH_LONG).show();
                new Thread(new Runnable() {

                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            Thread.sleep(10000);
                            //  mDefaultHandler.uncaughtException(thread, ex);
                        } catch (InterruptedException e) {
                            Log.e(TAG, "error : ", e);
                        }
                        //退出程序
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }).start();
            }
        });
        buder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        dialog = buder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.setCanceledOnTouchOutside(false);//设置点击屏幕其他地方，dialog不消失
        dialog.setCancelable(false);//设置点击返回键和HOme键，dialog不消失
        dialog.show();
        Log.i("PLog", "2");
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = "/sdcard/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }

    //打印错误信息
    private String getErrorInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        arg1.printStackTrace(printWriter);
        Throwable cause = arg1.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String error = writer.toString();
        return error;
    }

    /**
     * 获取手机的硬件信息
     *
     * @return
     */
    private String getMobileInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("" + android.os.Build.BRAND + android.os.Build.MODEL);
        sb.append("\n");
        return sb.toString();
    }

    /**
     * 获取软件的版本信息
     *
     * @return
     */
    private String getVersionInfo() {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }
}