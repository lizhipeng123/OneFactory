package com.daoran.newfactory.onefactory.util.bsdiff;

/**
 * 合并旧版安装包和patch增量包
 */
public class PatchUtils {

	static PatchUtils instance;

	public static PatchUtils getInstance() {
		if (instance == null)
			instance = new PatchUtils();
		return instance;
	}

	static {
		System.loadLibrary("apk_patch_lib");
	}
	
	public native int patch(String oldApkPath, String newApkPath, String patchPath);
}