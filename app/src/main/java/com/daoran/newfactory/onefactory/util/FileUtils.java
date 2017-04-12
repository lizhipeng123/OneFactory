package com.daoran.newfactory.onefactory.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件帮助类
 * 
 * @author djx
 * 
 */
public class FileUtils {
	/**
	 * 设置文件的名称
	 * 
	 * @return fileName
	 */
	public static String setFileName() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssMS");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 获得缓存文件的大小
	 * 
	 * @param file
	 * @return
	 */
	public static int findAllFileSize(File file) {
		int countSize = 0;
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isFile()) {
					System.out.println(f.getAbsolutePath());
					countSize += f.length();
				} else {
					return findAllFileSize(f);
				}
			}
		}
		return countSize;
	}

	/**
	 * 删除缓存文件
	 * 
	 * @param file
	 */
	public static void deleteAllFileSize(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isFile()) {
					System.out.println(f.getAbsolutePath());
					f.delete();
				} else {
					deleteAllFileSize(f);
				}
			}
		}
	}
}
