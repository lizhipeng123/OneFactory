package com.daoran.newfactory.onefactory.bean.loginbean;

/**
 *  版本更新实体
 * Created by lizhipeng on 2017/4/28.
 */

public class VerCodeBean {
    private String verCode;//版本号
    private String apkPath;//版本地址
    private String reason;//版本信息

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}