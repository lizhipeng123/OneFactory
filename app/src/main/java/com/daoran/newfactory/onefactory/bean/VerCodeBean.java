package com.daoran.newfactory.onefactory.bean;

/**
 * Created by lizhipeng on 2017/4/28.
 */

public class VerCodeBean {

    /**
     * verCode : 0.2.3
     * apkPath : http://www.taoandcompany.com/AppVersion/DFAPP.apk
     * reason : 生产日报表新建和查获跟踪表的巡检显示
     */

    private String verCode;
    private String apkPath;
    private String reason;

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
