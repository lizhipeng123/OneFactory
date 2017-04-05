package com.daoran.newfactory.onefactory.bean;

import java.util.List;

/**
 * 签到查询
 * Created by lizhipeng on 2017/4/3.
 */

public class SignBean {
    private int totalCount;
    private List<Data> datas;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public class Data {
        private String id;//id
        private String recorder;//姓名
        private String recordat;//签到时间
        private String recordplace;//签到地点
        private String memo;//
        private String code;//编号
        private String picpath;//图片地址
        private String regedittyp;//签到时间类型

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

        public String getPicpath() {
            return picpath;
        }

        public void setPicpath(String picpath) {
            this.picpath = picpath;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRegedittyp() {
            return regedittyp;
        }

        public void setRegedittyp(String regedittyp) {
            this.regedittyp = regedittyp;
        }
    }

}
