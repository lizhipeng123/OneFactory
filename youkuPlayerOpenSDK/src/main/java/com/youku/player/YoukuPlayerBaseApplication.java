package com.youku.player;

import android.os.Handler;

import com.decapi.DecAPI;
import com.youku.player.ui.R;
import com.youku.service.download.DownloadManager;

public abstract class YoukuPlayerBaseApplication extends YoukuPlayerApplication {
	
	@Override
	public void onCreate() {
		super.onCreate();
		DownloadManager.getInstance();
		DecAPI.init(context,R.raw.aes);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				DownloadManager.getInstance().startNewTask();
			}
		}, 1000);
	}


	@Override
	public int getNotifyLayoutId() {
		return R.layout.notify;
	}
	
	public static void exit(){
		YoukuPlayerApplication.exit();
		DownloadManager.getInstance().unregister();
	}


}
