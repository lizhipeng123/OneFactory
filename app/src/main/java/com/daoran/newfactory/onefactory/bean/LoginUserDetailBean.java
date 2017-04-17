package com.daoran.newfactory.onefactory.bean;

/**
 * 用户登录信息实体类
 * Created by lizhipeng on 2017/4/7.
 */

public class LoginUserDetailBean {

    /**
     * id : 28
     * logid : 0023
     * u_name : test5
     * status : true
     * log_date : 2017-04-07T10:21:45.3010601+08:00
     * deptno : 272
     * logIP : null
     * deptname : 离职
     * Company : null
     * CanLogin : null
     */

    private int id;
    private String logid;
    private String u_name;
    private boolean status;
    private String log_date;
    private String deptno;
    private Object logIP;
    private String deptname;
    private Company company;
    private Object CanLogin;

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

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public Object getLogIP() {
        return logIP;
    }

    public void setLogIP(Object logIP) {
        this.logIP = logIP;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Object getCanLogin() {
        return CanLogin;
    }

    public void setCanLogin(Object CanLogin) {
        this.CanLogin = CanLogin;
    }


    private class Company{

    }
}
