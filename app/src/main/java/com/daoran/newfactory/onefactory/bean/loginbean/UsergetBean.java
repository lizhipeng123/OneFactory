package com.daoran.newfactory.onefactory.bean.loginbean;

/**
 * 登录获取到的个人信息
 * Created by lizhipeng on 2017/3/24.
 */

public class UsergetBean {

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
}
