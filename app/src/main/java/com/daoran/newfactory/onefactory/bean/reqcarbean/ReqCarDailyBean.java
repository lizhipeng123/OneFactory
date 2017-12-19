package com.daoran.newfactory.onefactory.bean.reqcarbean;

import java.util.List;

/**
 * 用车查询页数据实体类
 * Created by lizhipeng on 2017/3/27.
 */

public class ReqCarDailyBean {

    /**
     * Data : [{"id":6873,"code":"CA170302005","recordid":214,"recorder":"杨英爱",
     * "recordt":"2017/3/2 13:41:32","asseid1":214,"asser1":"杨英爱","assedt1":"2017/3/2 13:44:18",
     * "appstatus":"结束","ctemp1":"J73K39","departureBdt":"2017/03/02 08:43","road":"勾运路",
     * "isprivate":null}]
     * totalCount : 6551
     */
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
        private int id;
        private String code;
        private int recordid;
        private String recorder;
        private String recordt;
        private int asseid1;
        private String asser1;
        private String assedt1;
        private String appstatus;
        private String ctemp1;
        private String departureBdt;
        private String road;
        private Object isprivate;

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

        public int getRecordid() {
            return recordid;
        }

        public void setRecordid(int recordid) {
            this.recordid = recordid;
        }

        public String getRecorder() {
            return recorder;
        }

        public void setRecorder(String recorder) {
            this.recorder = recorder;
        }

        public String getRecordt() {
            return recordt;
        }

        public void setRecordt(String recordt) {
            this.recordt = recordt;
        }

        public int getAsseid1() {
            return asseid1;
        }

        public void setAsseid1(int asseid1) {
            this.asseid1 = asseid1;
        }

        public String getAsser1() {
            return asser1;
        }

        public void setAsser1(String asser1) {
            this.asser1 = asser1;
        }

        public String getAssedt1() {
            return assedt1;
        }

        public void setAssedt1(String assedt1) {
            this.assedt1 = assedt1;
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

        public String getDepartureBdt() {
            return departureBdt;
        }

        public void setDepartureBdt(String departureBdt) {
            this.departureBdt = departureBdt;
        }

        public String getRoad() {
            return road;
        }

        public void setRoad(String road) {
            this.road = road;
        }

        public Object getIsprivate() {
            return isprivate;
        }

        public void setIsprivate(Object isprivate) {
            this.isprivate = isprivate;
        }
    }
}
