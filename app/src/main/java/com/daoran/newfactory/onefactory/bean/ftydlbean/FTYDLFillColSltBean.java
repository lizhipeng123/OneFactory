package com.daoran.newfactory.onefactory.bean.ftydlbean;

import java.util.List;

/**
 * 创建时间：2017/11/27
 * 编写人：lizhipeng
 * 功能描述：获取id对应的花色以及数量
 */

public class FTYDLFillColSltBean {
    private List<FTYDLFillColSltBean.Data> datas;

    public List<FTYDLFillColSltBean.Data> getDatas() {
        return datas;
    }

    public void setDatas(List<FTYDLFillColSltBean.Data> datas) {
        this.datas = datas;
    }

    public static class Data{
        /**
         * ID : 23769
         * Text : 豹纹印花
         * picID : 2000
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
