package com.daoran.newfactory.onefactory.bean;

/**
 * 创建时间：2017/11/20
 * 编写人：lizhipeng
 * 功能描述：
 */

public class ProductionVerticalColBean {
    private int ID;
    private int salesid;//排单id
    private String item;//款号
    private String ctmtxt;//客户
    private String year;//年
    private String month;//月
    private String subfactory;//工厂
    private String subfactoryTeams;//部门
    private String workingProcedure;//工序
    private String prodcol;//花色
    private String pqty;//制单数
    private String prddocumentary;//跟单
    private String workers;//组别人数
    private String taskqty;//下达任务数量
    private String mdl;//尺码
    private String factcutqty;//实裁数
    private String lastMonQty;//上月结余数量
    private String sumCompletedQty;//已完工数
    private String leftQty;//结余数量
    private String prdstatus;//生产状态

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCtmtxt() {
        return ctmtxt;
    }

    public void setCtmtxt(String ctmtxt) {
        this.ctmtxt = ctmtxt;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPqty() {
        return pqty;
    }

    public void setPqty(String pqty) {
        this.pqty = pqty;
    }

    public String getPrddocumentary() {
        return prddocumentary;
    }

    public void setPrddocumentary(String prddocumentary) {
        this.prddocumentary = prddocumentary;
    }

    public String getWorkers() {
        return workers;
    }

    public void setWorkers(String workers) {
        this.workers = workers;
    }

    public String getTaskqty() {
        return taskqty;
    }

    public void setTaskqty(String taskqty) {
        this.taskqty = taskqty;
    }

    public String getMdl() {
        return mdl;
    }

    public void setMdl(String mdl) {
        this.mdl = mdl;
    }

    public String getFactcutqty() {
        return factcutqty;
    }

    public void setFactcutqty(String factcutqty) {
        this.factcutqty = factcutqty;
    }

    public String getLastMonQty() {
        return lastMonQty;
    }

    public void setLastMonQty(String lastMonQty) {
        this.lastMonQty = lastMonQty;
    }

    public String getSumCompletedQty() {
        return sumCompletedQty;
    }

    public void setSumCompletedQty(String sumCompletedQty) {
        this.sumCompletedQty = sumCompletedQty;
    }

    public String getLeftQty() {
        return leftQty;
    }

    public void setLeftQty(String leftQty) {
        this.leftQty = leftQty;
    }

    public String getPrdstatus() {
        return prdstatus;
    }

    public void setPrdstatus(String prdstatus) {
        this.prdstatus = prdstatus;
    }

    public int getSalesid() {
        return salesid;
    }

    public void setSalesid(int salesid) {
        this.salesid = salesid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSubfactory() {
        return subfactory;
    }

    public void setSubfactory(String subfactory) {
        this.subfactory = subfactory;
    }

    public String getSubfactoryTeams() {
        return subfactoryTeams;
    }

    public void setSubfactoryTeams(String subfactoryTeams) {
        this.subfactoryTeams = subfactoryTeams;
    }

    public String getWorkingProcedure() {
        return workingProcedure;
    }

    public void setWorkingProcedure(String workingProcedure) {
        this.workingProcedure = workingProcedure;
    }

    public String getProdcol() {
        return prodcol;
    }

    public void setProdcol(String prodcol) {
        this.prodcol = prodcol;
    }
}
