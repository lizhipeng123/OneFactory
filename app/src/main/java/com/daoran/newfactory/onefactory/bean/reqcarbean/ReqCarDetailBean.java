package com.daoran.newfactory.onefactory.bean.reqcarbean;

import java.io.Serializable;

/**
 * 用车单详情实体类
 * Created by lizhipeng on 2017/4/26.
 */

public class ReqCarDetailBean implements Serializable {
    private int id;//id唯一
    private String code;//用车单编号/单据号
    private String compytxt;//公司名称
    private String recordt;//申请时间/制单时间
    private String recorder;//申请人/制单人
    private String recordid;//申请人id/制单人id
    private String handler;//经手人
    private String handlid;//经手人id/采购人id
    private String appstatus;//审核状态
    private String ctemp1;//明细客户款号
    private String ctemp2;//明细，单据编号
    private String isend;//是否结束
    private String isMsgCompleted;//消息签字是否完成
    private String assememo;//审核过程
    private String memo;//备注
    private String road;//地点按时
    private String withcarpsn;//随车人
    private String preoutdate;
    private String reason;//事由
    private String driver;
    private String plateNumber;//车牌
    private String departureDate;//出车时间
    private String departureBdt;//预计出发日期
    private String departureEdt;//预计回来日期
    private String bKG;//出发公里数
    private String eKG;//结束公里数
    private String curKG;//本次公里数
    private String islong;//是否长途
    private String isrange;//非公里数补贴范围
    private String roadamount;
    private String stopamount;
    private String authorizeamount;
    private String otheramount;
    private String asseid1;//审核人id
    private String asser1;//审核人
    private String assedat1;//审核日期
    private String asseid2;
    private String asser2;
    private String assedat2;
    private String asseid3;
    private String asser3;
    private String assedat3;
    private String asseid4;
    private String asser4;
    private String assedat4;
    private String asseid5;
    private String asser5;
    private String assedat5;
    private String printmemo;//打印备注
    private boolean isprivate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getCompytxt() {
        return compytxt;
    }

    public void setCompytxt(String compytxt) {
        this.compytxt = compytxt;
    }

    public String getRecordt() {
        return recordt;
    }

    public void setRecordt(String recordt) {
        this.recordt = recordt;
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

    public Object getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Object getHandlid() {
        return handlid;
    }

    public void setHandlid(String handlid) {
        this.handlid = handlid;
    }

    public String getAppstatus() {
        return appstatus;
    }

    public void setAppstatus(String appstatus) {
        this.appstatus = appstatus;
    }

    public String getCtemp1() {
        return ctemp1;
    }

    public void setCtemp1(String ctemp1) {
        this.ctemp1 = ctemp1;
    }

    public Object getCtemp2() {
        return ctemp2;
    }

    public void setCtemp2(String ctemp2) {
        this.ctemp2 = ctemp2;
    }

    public Object getIsend() {
        return isend;
    }

    public void setIsend(String isend) {
        this.isend = isend;
    }

    public Object getIsMsgCompleted() {
        return isMsgCompleted;
    }

    public void setIsMsgCompleted(String isMsgCompleted) {
        this.isMsgCompleted = isMsgCompleted;
    }

    public String getAssememo() {
        return assememo;
    }

    public void setAssememo(String assememo) {
        this.assememo = assememo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getWithcarpsn() {
        return withcarpsn;
    }

    public void setWithcarpsn(String withcarpsn) {
        this.withcarpsn = withcarpsn;
    }

    public Object getPreoutdate() {
        return preoutdate;
    }

    public void setPreoutdate(String preoutdate) {
        this.preoutdate = preoutdate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Object getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureBdt() {
        return departureBdt;
    }

    public void setDepartureBdt(String departureBdt) {
        this.departureBdt = departureBdt;
    }

    public String getDepartureEdt() {
        return departureEdt;
    }

    public void setDepartureEdt(String departureEdt) {
        this.departureEdt = departureEdt;
    }

    public Object getBKG() {
        return bKG;
    }

    public void setBKG(String bKG) {
        this.bKG = bKG;
    }

    public Object getEKG() {
        return eKG;
    }

    public void setEKG(String eKG) {
        this.eKG = eKG;
    }

    public Object getCurKG() {
        return curKG;
    }

    public void setCurKG(String curKG) {
        this.curKG = curKG;
    }

    public Object getIslong() {
        return islong;
    }

    public void setIslong(String islong) {
        this.islong = islong;
    }

    public Object getIsrange() {
        return isrange;
    }

    public void setIsrange(String isrange) {
        this.isrange = isrange;
    }

    public Object getRoadamount() {
        return roadamount;
    }

    public void setRoadamount(String roadamount) {
        this.roadamount = roadamount;
    }

    public Object getStopamount() {
        return stopamount;
    }

    public void setStopamount(String stopamount) {
        this.stopamount = stopamount;
    }

    public Object getAuthorizeamount() {
        return authorizeamount;
    }

    public void setAuthorizeamount(String authorizeamount) {
        this.authorizeamount = authorizeamount;
    }

    public Object getOtheramount() {
        return otheramount;
    }

    public void setOtheramount(String otheramount) {
        this.otheramount = otheramount;
    }

    public String getAsseid1() {
        return asseid1;
    }

    public void setAsseid1(String asseid1) {
        this.asseid1 = asseid1;
    }

    public String getAsser1() {
        return asser1;
    }

    public void setAsser1(String asser1) {
        this.asser1 = asser1;
    }

    public Object getAssedat1() {
        return assedat1;
    }

    public void setAssedat1(String assedat1) {
        this.assedat1 = assedat1;
    }

    public Object getAsseid2() {
        return asseid2;
    }

    public void setAsseid2(String asseid2) {
        this.asseid2 = asseid2;
    }

    public Object getAsser2() {
        return asser2;
    }

    public void setAsser2(String asser2) {
        this.asser2 = asser2;
    }

    public Object getAssedat2() {
        return assedat2;
    }

    public void setAssedat2(String assedat2) {
        this.assedat2 = assedat2;
    }

    public Object getAsseid3() {
        return asseid3;
    }

    public void setAsseid3(String asseid3) {
        this.asseid3 = asseid3;
    }

    public Object getAsser3() {
        return asser3;
    }

    public void setAsser3(String asser3) {
        this.asser3 = asser3;
    }

    public Object getAssedat3() {
        return assedat3;
    }

    public void setAssedat3(String assedat3) {
        this.assedat3 = assedat3;
    }

    public Object getAsseid4() {
        return asseid4;
    }

    public void setAsseid4(String asseid4) {
        this.asseid4 = asseid4;
    }

    public Object getAsser4() {
        return asser4;
    }

    public void setAsser4(String asser4) {
        this.asser4 = asser4;
    }

    public Object getAssedat4() {
        return assedat4;
    }

    public void setAssedat4(String assedat4) {
        this.assedat4 = assedat4;
    }

    public Object getAsseid5() {
        return asseid5;
    }

    public void setAsseid5(String asseid5) {
        this.asseid5 = asseid5;
    }

    public String getAsser5() {
        return asser5;
    }

    public void setAsser5(String asser5) {
        this.asser5 = asser5;
    }

    public Object getAssedat5() {
        return assedat5;
    }

    public void setAssedat5(String assedat5) {
        this.assedat5 = assedat5;
    }

    public Object getPrintmemo() {
        return printmemo;
    }

    public void setPrintmemo(String printmemo) {
        this.printmemo = printmemo;
    }

    public boolean isIsprivate() {
        return isprivate;
    }

    public void setIsprivate(boolean isprivate) {
        this.isprivate = isprivate;
    }

    public ReqCarDetailBean(int id, String code, String compytxt,
                            String recordt, String recorder,
                            String recordid, String handler, String handlid,
                            String appstatus, String ctemp1, String ctemp2,
                            String isend, String isMsgCompleted, String assememo,
                            String memo, String road, String withcarpsn,
                            String preoutdate, String reason, String driver,
                            String plateNumber, String departureDate,
                            String departureBdt, String departureEdt,
                            String bKG, String eKG, String curKG, String islong,
                            String isrange, String roadamount, String stopamount,
                            String authorizeamount, String otheramount,
                            String asseid1, String asser1, String assedat1,
                            String asseid2, String asser2, String assedat2,
                            String asseid3, String asser3, String assedat3,
                            String asseid4, String asser4, String assedat4,
                            String asseid5, String asser5, String assedat5,
                            String printmemo, boolean isprivate) {
        this.id = id;
        this.code = code;
        this.compytxt = compytxt;
        this.recordt = recordt;
        this.recorder = recorder;
        this.recordid = recordid;
        this.handler = handler;
        this.handlid = handlid;
        this.appstatus = appstatus;
        this.ctemp1 = ctemp1;
        this.ctemp2 = ctemp2;
        this.isend = isend;
        this.isMsgCompleted = isMsgCompleted;
        this.assememo = assememo;
        this.memo = memo;
        this.road = road;
        this.withcarpsn = withcarpsn;
        this.preoutdate = preoutdate;
        this.reason = reason;
        this.driver = driver;
        this.plateNumber = plateNumber;
        this.departureDate = departureDate;
        this.departureBdt = departureBdt;
        this.departureEdt = departureEdt;
        this.bKG = bKG;
        this.eKG = eKG;
        this.curKG = curKG;
        this.islong = islong;
        this.isrange = isrange;
        this.roadamount = roadamount;
        this.stopamount = stopamount;
        this.authorizeamount = authorizeamount;
        this.otheramount = otheramount;
        this.asseid1 = asseid1;
        this.asser1 = asser1;
        this.assedat1 = assedat1;
        this.asseid2 = asseid2;
        this.asser2 = asser2;
        this.assedat2 = assedat2;
        this.asseid3 = asseid3;
        this.asser3 = asser3;
        this.assedat3 = assedat3;
        this.asseid4 = asseid4;
        this.asser4 = asser4;
        this.assedat4 = assedat4;
        this.asseid5 = asseid5;
        this.asser5 = asser5;
        this.assedat5 = assedat5;
        this.printmemo = printmemo;
        this.isprivate = isprivate;
    }
}