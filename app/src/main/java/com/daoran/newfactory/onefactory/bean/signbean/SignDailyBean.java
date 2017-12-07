package com.daoran.newfactory.onefactory.bean.signbean;

import java.util.List;

/**
 * 签到查询实体类
 * Created by lizhipeng on 2017/4/6.
 */

public class SignDailyBean {

    /**
     * Data : [{"id":"19458","recorder":"陈春英","recordat":"2017/3/3 19:00:25","recordplace":"浙江省杭州市下城区石桥街道杭州框架广告有限公司跨贸小镇","memo":"","code":"OR170303055","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19458.png","regedittyp":"下班"},{"id":"19457","recorder":"徐吉峰","recordat":"2017/3/3 18:57:06","recordplace":"浙江省绍兴市诸暨市暨阳街道城北路93号凡友单身公寓","memo":"","code":"OR170303054","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19457.png","regedittyp":"下班"},{"id":"19456","recorder":"杨腾飞","recordat":"2017/3/3 18:42:04","recordplace":"杭州剑道品牌设计有限公司\n(经纬大道5号楼A座)","memo":"","code":"OR170303053","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19456.png","regedittyp":"下班"},{"id":"19455","recorder":"李传新","recordat":"2017/3/3 18:39:19","recordplace":"浙江省杭州市萧山区新塘街道东康路","memo":"","code":"OR170303052","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19455.png","regedittyp":"下班"},{"id":"19454","recorder":"徐九成","recordat":"2017/3/3 18:38:12","recordplace":"杭州道然进出口有限公司\n(石桥路279号经纬创意产业园11幢A楼)","memo":"","code":"OR170303051","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19454.png","regedittyp":"下班"},{"id":"19453","recorder":"杨素珍","recordat":"2017/3/3 18:35:22","recordplace":"浙江省杭州市余杭区良渚街道杭州丝田时装有限公司杭丝时装工业园","memo":"","code":"OR170303050","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19453.png","regedittyp":"下班"},{"id":"19452","recorder":"余新和","recordat":"2017/3/3 18:29:18","recordplace":"杭州同耀进出口有限公司\n(留石快速路出口与秋石快速路交叉口西50米)","memo":"","code":"OR170303049","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19452.png","regedittyp":"下班"},{"id":"19451","recorder":"李传新","recordat":"2017/3/3 18:26:39","recordplace":"浙江省杭州市萧山区新塘街道东康路","memo":"","code":"OR170303048","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19451.png","regedittyp":"下班"},{"id":"19450","recorder":"胡彩娥","recordat":"2017/3/3 18:25:57","recordplace":"杭州道然进出口有限公司\n(石桥路279号经纬创意产业园11幢A楼)","memo":"","code":"OR170303047","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19450.png","regedittyp":"下班"},{"id":"19449","recorder":"张银旺","recordat":"2017/3/3 18:09:56","recordplace":"浙江省杭州市下城区石桥街道新天地街杨家沁苑(沈家路)","memo":"","code":"OR170303046","picpath":"http://192.168.3.120/Pic/Images/OutRePic/2017/3/19449.png","regedittyp":"下班"}]
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

        /**
         * id : 19458
         * recorder : 陈春英
         * recordat : 2017/3/3 19:00:25
         * recordplace : 浙江省杭州市下城区石桥街道杭州框架广告有限公司跨贸小镇
         * memo :
         * code : OR170303055
         * picpath : http://192.168.3.120/Pic/Images/OutRePic/2017/3/19458.png
         * regedittyp : 下班
         */

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
