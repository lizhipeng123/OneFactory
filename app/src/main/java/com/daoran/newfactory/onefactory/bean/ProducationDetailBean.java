package com.daoran.newfactory.onefactory.bean;

import java.util.List;

/**
 * 生产日报详情实体类
 * Created by lizhipeng on 2017/4/27.
 */

public class ProducationDetailBean {
    private int totalCount;
    private List<DataBean> Data;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * ID : 3218
         * salesid : 2461
         * planid : null
         * sn : 1
         * contractno : null
         * inbill : null
         * area : null
         * companytxt : null
         * po : null
         * item : R1231翻单一
         * oitem : null
         * mdl : 34,36,38,40,42,44
         * ctmid : null
         * ctmtxt : KOOKAI
         * ctmcompanytxt : null
         * prdtyp : null
         * lcdat : null
         * lbdat : null
         * styp : null
         * fsaler : null
         * psaler : null
         * memo : null
         * pqty : 236
         * unit : null
         * prodcol : 浅紫色印花
         * megitem : null
         * teamname : null
         * recordat : 2017/04/19 09:23:58
         * recorder : 毕三军
         * subfactory : 道同一车间
         * workingProcedure : 车间
         * subfactoryTeams :
         * workers :
         * factcutqty : null
         * cutbdt : null
         * sewbdt : null
         * sewedt : null
         * sewDays : null
         * perqty : null
         * cutamount : null
         * sewamount : null
         * packamount : null
         * amount : null
         * perMachineQty : null
         * sumMachineQty : null
         * prdstatus :
         * prdmaster : null
         * prddocumentary : 包龙
         * taskqty : 236
         * sumCompletedQty : null
         * leftQty : null
         * lastMonQty : null
         * year : 2017
         * month : 4
         * day1 : null
         * day2 : null
         * day3 : null
         * day4 : null
         * day5 : null
         * day6 : null
         * day7 : null
         * day8 : null
         * day9 : null
         * day10 : null
         * day11 : null
         * day12 : null
         * day13 : null
         * day14 : null
         * day15 : null
         * day16 : null
         * day17 : null
         * day18 : null
         * day19 : null
         * day20 : null
         * day21 : null
         * day22 : null
         * day23 : null
         * day24 : null
         * day25 : null
         * day26 : null
         * day27 : null
         * day28 : null
         * day29 : null
         * day30 : null
         * day31 : null
         * isdiffc : null
         */

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
        private String year;//产量年
        private String month;//产量月
        private String day1;//产量1号
        private String day2;
        private String day3;
        private String day4;
        private String day5;
        private String day6;
        private String day7;
        private String day8;
        private String day9;
        private String day10;
        private String day11;
        private String day12;
        private String day13;
        private String day14;
        private String day15;
        private String day16;
        private String day17;
        private String day18;
        private String day19;
        private String day20;
        private String day21;
        private String day22;
        private String day23;
        private String day24;
        private String day25;
        private String day26;
        private String day27;
        private String day28;
        private String day29;
        private String day30;
        private String day31;
        private boolean isdiffc;//是否分色

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

        public void String(String leftQty) {
            this.leftQty = leftQty;
        }

        public String getLastMonQty() {
            return lastMonQty;
        }

        public void setLastMonQty(String lastMonQty) {
            this.lastMonQty = lastMonQty;
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

        public String getDay1() {
            return day1;
        }

        public void setDay1(String day1) {
            this.day1 = day1;
        }

        public String getDay2() {
            return day2;
        }

        public void setDay2(String day2) {
            this.day2 = day2;
        }

        public String getDay3() {
            return day3;
        }

        public void setDay3(String day3) {
            this.day3 = day3;
        }

        public String getDay4() {
            return day4;
        }

        public void setDay4(String day4) {
            this.day4 = day4;
        }

        public String getDay5() {
            return day5;
        }

        public void setDay5(String day5) {
            this.day5 = day5;
        }

        public String getDay6() {
            return day6;
        }

        public void setDay6(String day6) {
            this.day6 = day6;
        }

        public String getDay7() {
            return day7;
        }

        public void setDay7(String day7) {
            this.day7 = day7;
        }

        public String getDay8() {
            return day8;
        }

        public void setDay8(String day8) {
            this.day8 = day8;
        }

        public String getDay9() {
            return day9;
        }

        public void setDay9(String day9) {
            this.day9 = day9;
        }

        public String getDay10() {
            return day10;
        }

        public void setDay10(String day10) {
            this.day10 = day10;
        }

        public String getDay11() {
            return day11;
        }

        public void setDay11(String day11) {
            this.day11 = day11;
        }

        public String getDay12() {
            return day12;
        }

        public void setDay12(String day12) {
            this.day12 = day12;
        }

        public String getDay13() {
            return day13;
        }

        public void setDay13(String day13) {
            this.day13 = day13;
        }

        public String getDay14() {
            return day14;
        }

        public void setDay14(String day14) {
            this.day14 = day14;
        }

        public String getDay15() {
            return day15;
        }

        public void setDay15(String day15) {
            this.day15 = day15;
        }

        public String getDay16() {
            return day16;
        }

        public void setDay16(String day16) {
            this.day16 = day16;
        }

        public String getDay17() {
            return day17;
        }

        public void setDay17(String day17) {
            this.day17 = day17;
        }

        public String getDay18() {
            return day18;
        }

        public void setDay18(String day18) {
            this.day18 = day18;
        }

        public String getDay19() {
            return day19;
        }

        public void setDay19(String day19) {
            this.day19 = day19;
        }

        public String getDay20() {
            return day20;
        }

        public void setDay20(String day20) {
            this.day20 = day20;
        }

        public String getDay21() {
            return day21;
        }

        public void setDay21(String day21) {
            this.day21 = day21;
        }

        public String getDay22() {
            return day22;
        }

        public void setDay22(String day22) {
            this.day22 = day22;
        }

        public String getDay23() {
            return day23;
        }

        public void setDay23(String day23) {
            this.day23 = day23;
        }

        public String getDay24() {
            return day24;
        }

        public void setDay24(String day24) {
            this.day24 = day24;
        }

        public String getDay25() {
            return day25;
        }

        public void setDay25(String day25) {
            this.day25 = day25;
        }

        public String getDay26() {
            return day26;
        }

        public void setDay26(String day26) {
            this.day26 = day26;
        }

        public String getDay27() {
            return day27;
        }

        public void setDay27(String day27) {
            this.day27 = day27;
        }

        public String getDay28() {
            return day28;
        }

        public void setDay28(String day28) {
            this.day28 = day28;
        }

        public String getDay29() {
            return day29;
        }

        public void setDay29(String day29) {
            this.day29 = day29;
        }

        public String getDay30() {
            return day30;
        }

        public void setDay30(String day30) {
            this.day30 = day30;
        }

        public String getDay31() {
            return day31;
        }

        public void setDay31(String day31) {
            this.day31 = day31;
        }

        public boolean getIsdiffc() {
            return isdiffc;
        }

        public void setIsdiffc(boolean isdiffc) {
            this.isdiffc = isdiffc;
        }
    }
}
