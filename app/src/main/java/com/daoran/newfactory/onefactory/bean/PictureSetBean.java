package com.daoran.newfactory.onefactory.bean;

import java.util.List;

/**
 * Created by lizhipeng on 2017/9/15.
 */

public class PictureSetBean {

    private List<Data> datas;

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public static class Data {
        private int id;
        private String recorder;
        private String memo;
        private String pic;
        private String picpath;
        private String recorderID;
        private String recordat;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRecorder() {
            return recorder;
        }

        public void setRecorder(String recorder) {
            this.recorder = recorder;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPicpath() {
            return picpath;
        }

        public void setPicpath(String picpath) {
            this.picpath = picpath;
        }

        public String getRecorderID() {
            return recorderID;
        }

        public void setRecorderID(String recorderID) {
            this.recorderID = recorderID;
        }

        public String getRecordat() {
            return recordat;
        }

        public void setRecordat(String recordat) {
            this.recordat = recordat;
        }
    }
}
