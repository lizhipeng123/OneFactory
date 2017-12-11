package com.daoran.newfactory.onefactory.util.exception;

import android.content.Intent;
import android.util.Log;

import com.daoran.newfactory.onefactory.util.application.CrashApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 创建时间：2017/12/11
 * 编写人：lizhipeng
 * 功能描述：
 */

public class UEHandler implements Thread.UncaughtExceptionHandler  {
    private CrashApplication softApp;
    private File fileErrorLog;

    public UEHandler(CrashApplication app) {
        softApp = app;
        fileErrorLog = new File(CrashApplication.PATH_ERROR_LOG);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // fetch Excpetion Info
        String info = null;
        ByteArrayOutputStream baos = null;
        PrintStream printStream = null;
        try {
            baos = new ByteArrayOutputStream();
            printStream = new PrintStream(baos);
            ex.printStackTrace(printStream);
            byte[] data = baos.toByteArray();
            info = new String(data);
            data = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (printStream != null) {
                    printStream.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // print
        long threadId = thread.getId();
        Log.d("ANDROID_LAB", "Thread.getName()=" + thread.getName() + " id=" + threadId + " state=" + thread.getState());
        Log.d("ANDROID_LAB", "Error[" + info + "]");
        if (threadId != 1) {
            // 此处示例跳转到汇报异常界面。
            Intent intent = new Intent(softApp, ActErrorReport.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("error", info);
            intent.putExtra("by", "uehandler");
            softApp.startActivity(intent);
        } else {
            // 此处示例发生异常后，重新启动应用
            Intent intent = new Intent(softApp, ActOccurError.class);
            // 如果<span style="background-color:rgb(255,255,255);">没有NEW_TASK标识且</span>是UI线程抛的异常则界面卡死直到ANR
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            softApp.startActivity(intent);
            // write 2 /data/data/<app_package>/files/error.log
            write2ErrorLog(fileErrorLog, info);
            // kill App Progress
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    private void write2ErrorLog(File file, String content) {
        FileOutputStream fos = null;
        try {
            if (file.exists()) {
                // 清空之前的记录
                file.delete();
            } else {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
