package com.daoran.newfactory.onefactory.bean.loginbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 登录获取到的个人信息(序列化)
 * Created by lizhipeng on 2017/3/24.
 */

public class UsergetBean implements Parcelable {

    private int id;//Id
    private String logid;//用户名
    private String u_name;//姓名
    private boolean status;//启动登录
    private String log_date;//登录时间
    private String deptno;//部门ID
    private String logIP;//登录IP
    private String deptname;//部门
    private String Company;//公司
    private String CanLogin;//是否允许登录

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getLogIP() {
        return logIP;
    }

    public void setLogIP(String logIP) {
        this.logIP = logIP;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getCanLogin() {
        return CanLogin;
    }

    public void setCanLogin(String canLogin) {
        CanLogin = canLogin;
    }

    /*默认返回0*/
    @Override
    public int describeContents() {
        return 0;
    }

    /*把值写入parcel中*/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.logid);
        dest.writeString(this.u_name);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeString(this.log_date);
        dest.writeString(this.deptno);
        dest.writeString(this.logIP);
        dest.writeString(this.deptname);
        dest.writeString(this.Company);
        dest.writeString(this.CanLogin);
    }

    public UsergetBean() {}

    /*读的顺序必须和writeToParcel中写的顺序一致*/
    protected UsergetBean(Parcel in) {
        this.id = in.readInt();
        this.logid = in.readString();
        this.u_name = in.readString();
        this.status = in.readByte() != 0;
        this.log_date = in.readString();
        this.deptno = in.readString();
        this.logIP = in.readString();
        this.deptname = in.readString();
        this.Company = in.readString();
        this.CanLogin = in.readString();
    }

    public static final Parcelable.Creator<UsergetBean> CREATOR = new Parcelable.Creator<UsergetBean>() {
        /*从Parcel中读取数据*/
        @Override
        public UsergetBean createFromParcel(Parcel source) {
            return new UsergetBean(source);
        }

        /*供外部类反序列化本类数组使用*/
        @Override
        public UsergetBean[] newArray(int size) {
            return new UsergetBean[size];
        }
    };
}
