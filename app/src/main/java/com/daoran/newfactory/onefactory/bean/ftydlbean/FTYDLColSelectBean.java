package com.daoran.newfactory.onefactory.bean.ftydlbean;

import java.util.List;

/**
 * 创建时间：2017/11/15
 * 编写人：lizhipeng
 * 功能描述：
 */

public class FTYDLColSelectBean {

    private List<Data> datas;

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public static class Data{
        /**
         * ID : 23497
         * Text : UTILITY BLUE MULTI/丈青底
         * picID : 36
         */

        private String ID;
        private String Text;
        private String picID;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }

        public String getPicID() {
            return picID;
        }

        public void setPicID(String picID) {
            this.picID = picID;
        }
    }
}
