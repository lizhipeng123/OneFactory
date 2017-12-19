package com.daoran.newfactory.onefactory.bean.signbean;

import java.util.List;

/**
 * 签到查询实体类
 * Created by lizhipeng on 2017/4/6.
 */

public class SignDailyBean {

    /**
     * Data : [{"id":"19458","recorder":"陈春英","recordat":"2017/3/3 19:00:25",
     * "recordplace":"浙江省杭州市下城区石桥街道杭州框架广告有限公司跨贸小镇",
     * "memo":"","code":"OR170303055",
     * "picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19458.png","regedittyp":"下班"}]
     * totalCount : 19405
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
        private String id;
        private String recorder;
        private String recordat;
        private String recordplace;
        private String memo;
        private String code;
        private String picpath;
        private String regedittyp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRecorder() {
            return recorder;
        }

        public void setRecorder(String recorder) {
            this.recorder = recorder;
        }

        public String getRecordat() {
            return recordat;
        }

        public void setRecordat(String recordat) {
            this.recordat = recordat;
        }

        public String getRecordplace() {
            return recordplace;
        }

        public void setRecordplace(String recordplace) {
            this.recordplace = recordplace;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPicpath() {
            return picpath;
        }

        public void setPicpath(String picpath) {
            this.picpath = picpath;
        }

        public String getRegedittyp() {
            return regedittyp;
        }

        public void setRegedittyp(String regedittyp) {
            this.regedittyp = regedittyp;
        }
    }
}