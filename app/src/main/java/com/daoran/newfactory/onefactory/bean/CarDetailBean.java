package com.daoran.newfactory.onefactory.bean;

/**
 * 用车单详情实体类
 * Created by lizhipeng on 2017/4/26.
 */

public class CarDetailBean {

    /**
     * id : 6895
     * code : CA170419001
     * compytxt : null
     * recordt : 2017-04-19T17:38:22
     * recorder : 毕三军
     * recordid : 0678
     * handler : null
     * handlid : null
     * appstatus :
     * ctemp1 : SS261
     * ctemp2 : null
     * isend : null
     * isMsgCompleted : null
     * assememo :
     * memo :
     * road : 按时
     * withcarpsn :
     * preoutdate : null
     * reason : 按时
     * driver :
     * plateNumber :
     * departureDate : null
     * departureBdt : 2017/04/19 17:38
     * departureEdt : 2017/04/20 17:38
     * bKG : null
     * eKG : null
     * curKG : null
     * islong : null
     * isrange : null
     * roadamount : null
     * stopamount : null
     * authorizeamount : null
     * otheramount : null
     * asseid1 :
     * asser1 :
     * assedat1 : null
     * asseid2 : null
     * asser2 : null
     * assedat2 : null
     * asseid3 : null
     * asser3 : null
     * assedat3 : null
     * asseid4 : null
     * asser4 : null
     * assedat4 : null
     * asseid5 : null
     * asser5 : 否
     * assedat5 : null
     * printmemo : null
     * isprivate : false
     */

    private int id;//id唯一
    private String code;//用车单编号
    private String compytxt;
    private String recordt;//申请时间
    private String recorder;//申请人
    private String recordid;//申请人id
    private String handler;
    private String handlid;
    private String appstatus;//审核状态
    private String ctemp1;//明细款号
    private String ctemp2;
    private String isend;
    private String isMsgCompleted;
    private String assememo;
    private String memo;
    private String road;//地点按时
    private String withcarpsn;
    private String preoutdate;
    private String reason;//事由
    private String driver;
    private String plateNumber;
    private String departureDate;
    private String departureBdt;//预计出发日期
    private String departureEdt;
    private String bKG;
    private String eKG;
    private String curKG;
    private String islong;
    private String isrange;
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
    private String printmemo;
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
}
