package com.daoran.newfactory.onefactory.bean.ftydlbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产日报详情实体类(条件性质)
 * Created by lizhipeng on 2017/4/27.
 * 生产日报点击修改时，判断裁床多色查询接收的全部数据
 */

public class FTYDLDailyBean implements Parcelable {
    private int totalCount;
    private List<DataBean> Data;

    public FTYDLDailyBean() {}

    public FTYDLDailyBean(Parcel in) {
        totalCount = in.readInt();
        Data = new ArrayList<>();
        in.readTypedList(Data, DataBean.CREATOR);
    }

    public static final Creator<FTYDLDailyBean> CREATOR = new Creator<FTYDLDailyBean>() {
        @Override
        public FTYDLDailyBean createFromParcel(Parcel in) {
            return new FTYDLDailyBean(in);
        }

        @Override
        public FTYDLDailyBean[] newArray(int size) {
            return new FTYDLDailyBean[size];
        }
    };

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

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(totalCount);
    }

    public static class DataBean implements Parcelable {
        private int ID;//id
        private int salesid;//排单id
        private String planid;
        private int sn;
        private String contractno;
        private String inbill;
        private String area;
        private String companytxt;
        private String po;
        private String item;//款号
        private String oitem;//原款号
        private String mdl;//尺码
        private String ctmid;//客户id
        private String ctmtxt;//客户
        private String ctmcompanytxt;
        private String prdtyp;
        private String lcdat;//离厂日期
        private String lbdat;//离岸日期
        private String styp;
        private String fsaler;
        private String psaler;
        private String memo;//备注
        private int pqty;//制单数
        private String unit;//单位
        private String prodcol;//花色
        private String megitem;//合并款号
        private String teamname;
        private String recordat;//制单时间
        private String recorder;//制单人
        private String recordid;//制单人id
        private String subfactory;//工厂
        private String workingProcedure;//工序
        private String subfactoryTeams;//部门组别
        private String workers;//组别人数
        private int factcutqty;//实裁数
        private String cutbdt;
        private String sewbdt;
        private String sewedt;
        private String sewDays;
        private String perqty;
        private String cutamount;
        private String sewamount;
        private String packamount;
        private String amount;
        private String perMachineQty;
        private String sumMachineQty;
        private String prdstatus;//状态
        private String prdmaster;//主管
        private String prddocumentary;//跟单
        private String prddocumentaryid;//跟单id
        private int taskqty;//任务数
        private int sumCompletedQty;//总数
        private int leftQty;//结余数量
        private int lastMonQty;//上月结余数量
        private int year;//年
        private int month;//月
        private String day1;
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
        private String isdiffc;//是否分色

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            ID = in.readInt();
            salesid = in.readInt();
            planid = in.readString();
            sn = in.readInt();
            contractno = in.readString();
            inbill = in.readString();
            area = in.readString();
            companytxt = in.readString();
            po = in.readString();
            item = in.readString();
            oitem = in.readString();
            mdl = in.readString();
            ctmid = in.readString();
            ctmtxt = in.readString();
            ctmcompanytxt = in.readString();
            prdtyp = in.readString();
            lcdat = in.readString();
            lbdat = in.readString();
            styp = in.readString();
            fsaler = in.readString();
            psaler = in.readString();
            memo = in.readString();
            pqty = in.readInt();
            unit = in.readString();
            prodcol = in.readString();
            megitem = in.readString();
            teamname = in.readString();
            recordat = in.readString();
            recorder = in.readString();
            recordid = in.readString();
            subfactory = in.readString();
            workingProcedure = in.readString();
            subfactoryTeams = in.readString();
            workers = in.readString();
            factcutqty = in.readInt();
            cutbdt = in.readString();
            sewbdt = in.readString();
            sewedt = in.readString();
            sewDays = in.readString();
            perqty = in.readString();
            cutamount = in.readString();
            sewamount = in.readString();
            packamount = in.readString();
            amount = in.readString();
            perMachineQty = in.readString();
            sumMachineQty = in.readString();
            prdstatus = in.readString();
            prdmaster = in.readString();
            prddocumentary = in.readString();
            prddocumentaryid = in.readString();
            taskqty = in.readInt();
            sumCompletedQty = in.readInt();
            leftQty = in.readInt();
            lastMonQty = in.readInt();
            year = in.readInt();
            month = in.readInt();
            day1 = in.readString();
            day2 = in.readString();
            day3 = in.readString();
            day4 = in.readString();
            day5 = in.readString();
            day6 = in.readString();
            day7 = in.readString();
            day8 = in.readString();
            day9 = in.readString();
            day10 = in.readString();
            day11 = in.readString();
            day12 = in.readString();
            day13 = in.readString();
            day14 = in.readString();
            day15 = in.readString();
            day16 = in.readString();
            day17 = in.readString();
            day18 = in.readString();
            day19 = in.readString();
            day20 = in.readString();
            day21 = in.readString();
            day22 = in.readString();
            day23 = in.readString();
            day24 = in.readString();
            day25 = in.readString();
            day26 = in.readString();
            day27 = in.readString();
            day28 = in.readString();
            day29 = in.readString();
            day30 = in.readString();
            day31 = in.readString();
            isdiffc = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ID);
            dest.writeInt(salesid);
            dest.writeString(planid);
            dest.writeInt(sn);
            dest.writeString(contractno);
            dest.writeString(inbill);
            dest.writeString(area);
            dest.writeString(companytxt);
            dest.writeString(po);
            dest.writeString(item);
            dest.writeString(oitem);
            dest.writeString(mdl);
            dest.writeString(ctmid);
            dest.writeString(ctmtxt);
            dest.writeString(ctmcompanytxt);
            dest.writeString(prdtyp);
            dest.writeString(lcdat);
            dest.writeString(lbdat);
            dest.writeString(styp);
            dest.writeString(fsaler);
            dest.writeString(psaler);
            dest.writeString(memo);
            dest.writeInt(pqty);
            dest.writeString(unit);
            dest.writeString(prodcol);
            dest.writeString(megitem);
            dest.writeString(teamname);
            dest.writeString(recordat);
            dest.writeString(recorder);
            dest.writeString(recordid);
            dest.writeString(subfactory);
            dest.writeString(workingProcedure);
            dest.writeString(subfactoryTeams);
            dest.writeString(workers);
            dest.writeInt(factcutqty);
            dest.writeString(cutbdt);
            dest.writeString(sewbdt);
            dest.writeString(sewedt);
            dest.writeString(sewDays);
            dest.writeString(perqty);
            dest.writeString(cutamount);
            dest.writeString(sewamount);
            dest.writeString(packamount);
            dest.writeString(amount);
            dest.writeString(perMachineQty);
            dest.writeString(sumMachineQty);
            dest.writeString(prdstatus);
            dest.writeString(prdmaster);
            dest.writeString(prddocumentary);
            dest.writeString(prddocumentaryid);
            dest.writeInt(taskqty);
            dest.writeInt(sumCompletedQty);
            dest.writeInt(leftQty);
            dest.writeInt(lastMonQty);
            dest.writeInt(year);
            dest.writeInt(month);
            dest.writeString(day1);
            dest.writeString(day2);
            dest.writeString(day3);
            dest.writeString(day4);
            dest.writeString(day5);
            dest.writeString(day6);
            dest.writeString(day7);
            dest.writeString(day8);
            dest.writeString(day9);
            dest.writeString(day10);
            dest.writeString(day11);
            dest.writeString(day12);
            dest.writeString(day13);
            dest.writeString(day14);
            dest.writeString(day15);
            dest.writeString(day16);
            dest.writeString(day17);
            dest.writeString(day18);
            dest.writeString(day19);
            dest.writeString(day20);
            dest.writeString(day21);
            dest.writeString(day22);
            dest.writeString(day23);
            dest.writeString(day24);
            dest.writeString(day25);
            dest.writeString(day26);
            dest.writeString(day27);
            dest.writeString(day28);
            dest.writeString(day29);
            dest.writeString(day30);
            dest.writeString(day31);
            dest.writeString(isdiffc);
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

        public int getPqty() {
            return pqty;
        }

        public void setPqty(int pqty) {
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

        public String getRecordid() {
            return recordid;
        }

        public void setRecordid(String recordid) {
            this.recordid = recordid;
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

        public int getFactcutqty() {
            return factcutqty;
        }

        public void setFactcutqty(int factcutqty) {
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

        public String getPrddocumentaryid() {
            return prddocumentaryid;
        }

        public void setPrddocumentaryid(String prddocumentaryid) {
            this.prddocumentaryid = prddocumentaryid;
        }

        public int getTaskqty() {
            return taskqty;
        }

        public void setTaskqty(int taskqty) {
            this.taskqty = taskqty;
        }

        public int getSumCompletedQty() {
            return sumCompletedQty;
        }

        public void setSumCompletedQty(int sumCompletedQty) {
            this.sumCompletedQty = sumCompletedQty;
        }

        public int getLeftQty() {
            return leftQty;
        }

        public void setLeftQty(int leftQty) {
            this.leftQty = leftQty;
        }

        public int getLastMonQty() {
            return lastMonQty;
        }

        public void setLastMonQty(int lastMonQty) {
            this.lastMonQty = lastMonQty;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
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

        public String getIsdiffc() {
            return isdiffc;
        }

        public void setIsdiffc(String isdiffc) {
            this.isdiffc = isdiffc;
        }
    }
}
