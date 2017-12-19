package com.daoran.newfactory.onefactory.bean.settingbean;

import java.util.List;

/**
 * 图片列表实体
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
        private int id;//id
        private String recorder;//制单人
        private String memo;//备注
        private String pic;//图片
        private String picpath;//图片地址
        private String recorderID;//制单人id
        private String recordat;//制单时间

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
