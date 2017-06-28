package com.daoran.newfactory.onefactory.bean;

/**
 * 生产日报删除行实体类
 * Created by lizhipeng on 2017/6/28.
 */

public class ProducationDeleteBean {
    private int ID;
    private int salesid;//引用的排单计划表id
    private String planid;//引用的工厂计划id
    private int sn;//序列号
    private String contractno;//销售合同号
    private String inbill;//内部id
    private String area;//片区号
    private String companytxt;//公司名称
    private String po;//po
    private String item;//款号
    private String oitem;//原款号
    private String mdl;//尺码
    private String ctmid;//客户
    private String ctmtxt;//客户
    private String ctmcompanytxt;//客户归属公司
    private String prdtyp;//产品大类(产品类型)
    private String lcdat;//计划离厂日期
    private String lbdat;//计划离岸日期
    private String styp;//po类型(分类)
    private String fsaler;//外贸业务员
    private String psaler;//生产业务员
    private String memo;
    private String pqty;//产品数量
    private String unit;//单位
    private String prodcol;//花色
    private String megitem;//合并款号
    private String teamname;//外贸组别
    private String recordat;//制单时间
    private String recorder;//制单人
    private String subfactory;//加工厂
    private String workingProcedure;//工序
    private String subfactoryTeams;//组别
    private String workers;//车位人数
    private String factcutqty;//实裁数
    private String cutbdt;//开裁日期
    private String sewbdt;//上线日期
    private String sewedt;//完工日期
    private String sewDays;//天数
    private String perqty;//人均件数
    private String cutamount;//裁剪金额
    private String sewamount;
    private String packamount;
    private String amount;//总价
    private String perMachineQty;//车间人均台产
    private String sumMachineQty;//总台产
    private String prdstatus;//生产状态
    private String prdmaster;//生产主管
    private String prddocumentary;//跟单
    private String taskqty;//下达任务数量
    private String sumCompletedQty;//已完工数
    private String leftQty;//结余数量
    private String lastMonQty;//上月结余数量
    private boolean isdiffc;//是否分色
    private int predayQty;
    private String ourmainpartprice;
    private String recordid;//制单人id

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSalesid() {
        return salesid;
    }

    public void setSalesid(int salesid) {
        this.salesid = salesid;
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public String getInbill() {
        return inbill;
    }

    public void setInbill(String inbill) {
        this.inbill = inbill;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanytxt() {
        return companytxt;
    }

    public void setCompanytxt(String companytxt) {
        this.companytxt = companytxt;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getOitem() {
        return oitem;
    }

    public void setOitem(String oitem) {
        this.oitem = oitem;
    }

    public String getMdl() {
        return mdl;
    }

    public void setMdl(String mdl) {
        this.mdl = mdl;
    }

    public String getCtmid() {
        return ctmid;
    }

    public void setCtmid(String ctmid) {
        this.ctmid = ctmid;
    }

    public String getCtmtxt() {
        return ctmtxt;
    }

    public void setCtmtxt(String ctmtxt) {
        this.ctmtxt = ctmtxt;
    }

    public String getCtmcompanytxt() {
        return ctmcompanytxt;
    }

    public void setCtmcompanytxt(String ctmcompanytxt) {
        this.ctmcompanytxt = ctmcompanytxt;
    }

    public String getPrdtyp() {
        return prdtyp;
    }

    public void setPrdtyp(String prdtyp) {
        this.prdtyp = prdtyp;
    }

    public String getLcdat() {
        return lcdat;
    }

    public void setLcdat(String lcdat) {
        this.lcdat = lcdat;
    }

    public String getLbdat() {
        return lbdat;
    }

    public void setLbdat(String lbdat) {
        this.lbdat = lbdat;
    }

    public String getStyp() {
        return styp;
    }

    public void setStyp(String styp) {
        this.styp = styp;
    }

    public String getFsaler() {
        return fsaler;
    }

    public void setFsaler(String fsaler) {
        this.fsaler = fsaler;
    }

    public String getPsaler() {
        return psaler;
    }

    public void setPsaler(String psaler) {
        this.psaler = psaler;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPqty() {
        return pqty;
    }

    public void setPqty(String pqty) {
        this.pqty = pqty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProdcol() {
        return prodcol;
    }

    public void setProdcol(String prodcol) {
        this.prodcol = prodcol;
    }

    public String getMegitem() {
        return megitem;
    }

    public void setMegitem(String megitem) {
        this.megitem = megitem;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getRecordat() {
        return recordat;
    }

    public void setRecordat(String recordat) {
        this.recordat = recordat;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getSubfactory() {
        return subfactory;
    }

    public void setSubfactory(String subfactory) {
        this.subfactory = subfactory;
    }

    public String getWorkingProcedure() {
        return workingProcedure;
    }

    public void setWorkingProcedure(String workingProcedure) {
        this.workingProcedure = workingProcedure;
    }

    public String getSubfactoryTeams() {
        return subfactoryTeams;
    }

    public void setSubfactoryTeams(String subfactoryTeams) {
        this.subfactoryTeams = subfactoryTeams;
    }

    public String getWorkers() {
        return workers;
    }

    public void setWorkers(String workers) {
        this.workers = workers;
    }

    public String getFactcutqty() {
        return factcutqty;
    }

    public void setFactcutqty(String factcutqty) {
        this.factcutqty = factcutqty;
    }

    public String getCutbdt() {
        return cutbdt;
    }

    public void setCutbdt(String cutbdt) {
        this.cutbdt = cutbdt;
    }

    public String getSewbdt() {
        return sewbdt;
    }

    public void setSewbdt(String sewbdt) {
        this.sewbdt = sewbdt;
    }

    public String getSewedt() {
        return sewedt;
    }

    public void setSewedt(String sewedt) {
        this.sewedt = sewedt;
    }

    public String getSewDays() {
        return sewDays;
    }

    public void setSewDays(String sewDays) {
        this.sewDays = sewDays;
    }

    public String getPerqty() {
        return perqty;
    }

    public void setPerqty(String perqty) {
        this.perqty = perqty;
    }

    public String getCutamount() {
        return cutamount;
    }

    public void setCutamount(String cutamount) {
        this.cutamount = cutamount;
    }

    public String getSewamount() {
        return sewamount;
    }

    public void setSewamount(String sewamount) {
        this.sewamount = sewamount;
    }

    public String getPackamount() {
        return packamount;
    }

    public void setPackamount(String packamount) {
        this.packamount = packamount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPerMachineQty() {
        return perMachineQty;
    }

    public void setPerMachineQty(String perMachineQty) {
        this.perMachineQty = perMachineQty;
    }

    public String getSumMachineQty() {
        return sumMachineQty;
    }

    public void setSumMachineQty(String sumMachineQty) {
        this.sumMachineQty = sumMachineQty;
    }

    public String getPrdstatus() {
        return prdstatus;
    }

    public void setPrdstatus(String prdstatus) {
        this.prdstatus = prdstatus;
    }

    public String getPrdmaster() {
        return prdmaster;
    }

    public void setPrdmaster(String prdmaster) {
        this.prdmaster = prdmaster;
    }

    public String getPrddocumentary() {
        return prddocumentary;
    }

    public void setPrddocumentary(String prddocumentary) {
        this.prddocumentary = prddocumentary;
    }

    public String getTaskqty() {
        return taskqty;
    }

    public void setTaskqty(String taskqty) {
        this.taskqty = taskqty;
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

    public String getLastMonQty() {
        return lastMonQty;
    }

    public void setLastMonQty(String lastMonQty) {
        this.lastMonQty = lastMonQty;
    }

    public boolean isdiffc() {
        return isdiffc;
    }

    public void setIsdiffc(boolean isdiffc) {
        this.isdiffc = isdiffc;
    }

    public int getPredayQty() {
        return predayQty;
    }

    public void setPredayQty(int predayQty) {
        this.predayQty = predayQty;
    }

    public String getOurmainpartprice() {
        return ourmainpartprice;
    }

    public void setOurmainpartprice(String ourmainpartprice) {
        this.ourmainpartprice = ourmainpartprice;
    }
}
