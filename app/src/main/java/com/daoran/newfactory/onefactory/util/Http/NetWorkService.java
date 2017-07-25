package com.daoran.newfactory.onefactory.util.Http;

import android.app.Service;
import android.content.Intent;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.daoran.newfactory.onefactory.util.ToastUtils;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 判断网速
 * Created by lizhipeng on 2017/7/25.
 */

public class NetWorkService extends Service {
    private Handler handler = new Handler();
    private Timer timer;
    private long rxtxTotal = 0;
    private boolean isNetBad = false;
    private int time;
    private double rxtxSpeed = 1.0f;
    private DecimalFormat showFloatFormat = new DecimalFormat("0.00");
    private Intent receiverIntent;
    public final static String NET_SPEED_RECEIVER_ACTION = "com.ridgepm.network_speed_action";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new RefreshTask(), 0L, (long) 2000);
        }
        receiverIntent = new Intent();
        receiverIntent.setAction(NET_SPEED_RECEIVER_ACTION);
        int result = super.onStartCommand(intent, flags, startId);
        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Service被终止的同时也停止定时器继续运行
        timer.cancel();
        timer = null;
    }
    //定时任务
    class RefreshTask extends TimerTask {

        @Override
        public void run() {
            isNetBad = false;
            long tempSum = TrafficStats.getTotalRxBytes()
                    + TrafficStats.getTotalTxBytes();
            long rxtxLast = tempSum - rxtxTotal;
            double tempSpeed = rxtxLast * 1000 / 2000;
            rxtxTotal = tempSum;
            if ((tempSpeed / 1024d) < 20 && (rxtxSpeed / 1024d) < 20) {
                time += 1;
            } else {
                time = 0;
            }
            rxtxSpeed = tempSpeed;
            Log.i("NetworkSpeedService", showFloatFormat.format(tempSpeed / 1024d) + "kb/s");
            if (time >= 4) {//连续四次检测网速都小于20kb/s  断定网速很差.
                isNetBad = true;
                Log.i("NetworkSpeedService", "网速差 " + isNetBad);
                ToastUtils.ShowToastMessage("网速差"+isNetBad,getApplicationContext());
                time = 0; //重新检测
            }
            if (isNetBad) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        receiverIntent.putExtra("is_slow_net_speed", isNetBad);
                        sendBroadcast(receiverIntent);//发送广播去取消这次请求.
                    }
                });
            }
        }

    }
}
