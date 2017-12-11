package com.daoran.newfactory.onefactory.util.exception;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.application.CrashApplication;

import java.io.File;
import java.io.FileInputStream;

/**
 * 创建时间：2017/12/11
 * 编写人：lizhipeng
 * 功能描述：
 */

public class ActOccurError extends Activity {
    private CrashApplication softApplication;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        softApplication = (CrashApplication) getApplication();
        // 一开始进入程序恢复为"need2Exit=false"。
        softApplication.setNeed2Exit(false);
        Log.d("ANDROID_LAB", "ActOccurError.onCreate()");
        Button btnMain = (Button) findViewById(R.id.btnThrowMain);
        btnMain.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Log.d("ANDROID_LAB", "Thread.main.run()");
                int i = 0;
                i = 100 / i;
            }
        });
        Button btnChild = (Button) findViewById(R.id.btnThrowChild);
        btnChild.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        Log.d("ANDROID_LAB", "Thread.child.run()");
                        int i = 0;
                        i = 100 / i;
                    }
                }.start();
            }
        });
        // 处理记录于error.log中的异常
        String errorContent = getErrorLog();
        if (errorContent != null) {
            Intent intent = new Intent(this, ActErrorReport.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("error", errorContent);
            intent.putExtra("by", "error.log");
            startActivity(intent);
        }
    }
    public void onStart() {
        super.onStart();
        if (softApplication.need2Exit()) {
            Log.d("ANDROID_LAB", "ActOccurError.finish()");
            ActOccurError.this.finish();
        } else {
            // do normal things
        }
    }
    /**
     * 读取是否有未处理的报错信息。<br/>
     * 每次读取后都会将error.log清空。<br/>
     *
     * @return 返回未处理的报错信息或null。
     */
    private String getErrorLog() {
        File fileErrorLog = new File(CrashApplication.PATH_ERROR_LOG);
        String content = null;
        FileInputStream fis = null;
        try {
            if (fileErrorLog.exists()) {
                byte[] data = new byte[(int) fileErrorLog.length()];
                fis = new FileInputStream(fileErrorLog);
                fis.read(data);
                content = new String(data);
                data = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fileErrorLog.exists()) {
                    fileErrorLog.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
