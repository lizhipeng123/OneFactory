package com.daoran.newfactory.onefactory.bean.ftydlbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 款号选择页面列表数据
 * Created by lizhipeng on 2017/5/8.
 */

public class FTYDLFactoryDailyBean implements Parcelable {

    private int totalCount;
    private List<DataBean> Data;

    protected FTYDLFactoryDailyBean(Parcel in) {
        totalCount = in.readInt();
    }

    public static final Creator<FTYDLFactoryDailyBean> CREATOR = new Creator<FTYDLFactoryDailyBean>() {
        @Override
        public FTYDLFactoryDailyBean createFromParcel(Parcel in) {
            return new FTYDLFactoryDailyBean(in);
        }

        @Override
        public FTYDLFactoryDailyBean[] newArray(int size) {
            return new FTYDLFactoryDailyBean[size];
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(totalCount);
    }

    public static class DataBean implements Parcelable {
        private String id;//id
        private String salesid;//排单计划id
        private String item;//款号
        private String megitem;//合并款号
        private String prdtyp;//产品大类
        private String fsaler;//外贸业务员
        private String fsalerid;//外贸业务员id
        private String psaler;//生产业务员
        private String psalerid;//生产业务员id
        private String taskqty;//下达任务数量
        private String inbill;//内部id
        private String subfactory;//加工厂
        private String lbdat;//离岸日期
        private String lcdat;//出货日期
        private String ctmid;//客户id
        private String ctmtxt;//客户
        private String pqty;//产品数量
        private String taskdat;//下达任务日期
        private String prddocumentary;//跟单
        private String prddocumentaryid;//跟单id
        private String prdmaster;//生产主管
        private String styp;//样衣种类
        private String ourmainpart;
        private String teamname;//外贸组别
        private String mtr;
        private String prodcol;//花色
        private String sewamount;
        private String mdl;//尺码
        private String recordat;//制单时间
        private String subfactoryTeams;//部门组别
        private String workers;//车位人数
        private String factcutqty;//实裁数
        private String prdstatus;//生产情况
        private String workingProcedure;//工序
        private String sumCompletedQty;//已完工数

        protected DataBean(Parcel in) {
            id = in.readString();
            salesid = in.readString();
            item = in.readString();
            megitem = in.readString();
            prdtyp = in.readString();
            fsaler = in.readString();
            fsalerid = in.readString();
            psaler = in.readString();
            psalerid = in.readString();
            taskqty = in.readString();
            inbill = in.readString();
            subfactory = in.readString();
            lbdat = in.readString();
            lcdat = in.readString();
            ctmid = in.readString();
            ctmtxt = in.readString();
            pqty = in.readString();
            taskdat = in.readString();
            prddocumentary = in.readString();
            prddocumentaryid = in.readString();
            prdmaster = in.readString();
            styp = in.readString();
            ourmainpart = in.readString();
            teamname = in.readString();
            mtr = in.readString();
            prodcol = in.readString();
            sewamount = in.readString();
            mdl = in.readString();
            recordat = in.readString();
            subfactoryTeams = in.readString();
            workers = in.readString();
            factcutqty = in.readString();
            prdstatus = in.readString();
            workingProcedure = in.readString();
            sumCompletedQty = in.readString();
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

        public String getID() {
            return id;
        }

        public void setID(String ID) {
            this.id = ID;
        }

        public String getSalesid() {
            return salesid;
        }

        public void setSalesid(String salesid) {
            this.salesid = salesid;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getMegitem() {
            return megitem;
        }

        public void setMegitem(String megitem) {
            this.megitem = megitem;
        }

        public String getPrdtyp() {
            return prdtyp;
        }

        public void setPrdtyp(String prdtyp) {
            this.prdtyp = prdtyp;
        }

        public String getFsaler() {
            return fsaler;
        }

        public void setFsaler(String fsaler) {
            this.fsaler = fsaler;
        }

        public String getFsalerid() {
            return fsalerid;
        }

        public void setFsalerid(String fsalerid) {
            this.fsalerid = fsalerid;
        }

        public String getPsaler() {
            return psaler;
        }

        public void setPsaler(String psaler) {
            this.psaler = psaler;
        }

        public String getPsalerid() {
            return psalerid;
        }

        public void setPsalerid(String psalerid) {
            this.psalerid = psalerid;
        }

        public String getTaskqty() {
            return taskqty;
        }

        public void setTaskqty(String taskqty) {
            this.taskqty = taskqty;
        }

        public String getInbill() {
            return inbill;
        }

        public void setInbill(String inbill) {
            this.inbill = inbill;
        }

        public String getSubfactory() {
            return subfactory;
        }

        public void setSubfactory(String subfactory) {
            this.subfactory = subfactory;
        }

        public String getLbdat() {
            return lbdat;
        }

        public void setLbdat(String lbdat) {
            this.lbdat = lbdat;
        }

        public String getLcdat() {
            return lcdat;
        }

        public void setLcdat(String lcdat) {
            this.lcdat = lcdat;
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

        public String getPqty() {
            return pqty;
        }

        public void setPqty(String pqty) {
            this.pqty = pqty;
        }

        public String getTaskdat() {
            return taskdat;
        }

        public void setTaskdat(String taskdat) {
            this.taskdat = taskdat;
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

        public String getPrdmaster() {
            return prdmaster;
        }

        public void setPrdmaster(String prdmaster) {
            this.prdmaster = prdmaster;
        }

        public String getStyp() {
            return styp;
        }

        public void setStyp(String styp) {
            this.styp = styp;
        }

        public String getOurmainpart() {
            return ourmainpart;
        }

        public void setOurmainpart(String ourmainpart) {
            this.ourmainpart = ourmainpart;
        }

        public String getTeamname() {
            return teamname;
        }

        public void setTeamname(String teamname) {
            this.teamname = teamname;
        }

        public String getMtr() {
            return mtr;
        }

        public void setMtr(String mtr) {
            this.mtr = mtr;
        }

        public String getProdcol() {
            return prodcol;
        }

        public void setProdcol(String prodcol) {
            this.prodcol = prodcol;
        }

        public String getSewamount() {
            return sewamount;
        }

        public void setSewamount(String sewamount) {
            this.sewamount = sewamount;
        }

        public String getMdl() {
            return mdl;
        }

        public void setMdl(String mdl) {
            this.mdl = mdl;
        }

        public String getRecordat() {
            return recordat;
        }

        public void setRecordat(String recordat) {
            this.recordat = recordat;
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

        public String getPrdstatus() {
            return prdstatus;
        }

        public void setPrdstatus(String prdstatus) {
            this.prdstatus = prdstatus;
        }

        public String getWorkingProcedure() {
            return workingProcedure;
        }

        public void setWorkingProcedure(String workingProcedure) {
            this.workingProcedure = workingProcedure;
        }

        public String getSumCompletedQty() {
            return sumCompletedQty;
        }

        public void setSumCompletedQty(String sumCompletedQty) {
            this.sumCompletedQty = sumCompletedQty;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(salesid);
            dest.writeString(item);
            dest.writeString(megitem);
            dest.writeString(prdtyp);
            dest.writeString(fsaler);
            dest.writeString(fsalerid);
            dest.writeString(psaler);
            dest.writeString(psalerid);
            dest.writeString(taskqty);
            dest.writeString(inbill);
            dest.writeString(subfactory);
            dest.writeString(lbdat);
            dest.writeString(lcdat);
            dest.writeString(ctmid);
            dest.writeString(ctmtxt);
            dest.writeString(pqty);
            dest.writeString(taskdat);
            dest.writeString(prddocumentary);
            dest.writeString(prddocumentaryid);
            dest.writeString(prdmaster);
            dest.writeString(styp);
            dest.writeString(ourmainpart);
            dest.writeString(teamname);
            dest.writeString(mtr);
            dest.writeString(prodcol);
            dest.writeString(sewamount);
            dest.writeString(mdl);
            dest.writeString(recordat);
            dest.writeString(subfactoryTeams);
            dest.writeString(workers);
            dest.writeString(factcutqty);
            dest.writeString(prdstatus);
            dest.writeString(workingProcedure);
            dest.writeString(sumCompletedQty);
        }
    }
}
