package com.daoran.newfactory.onefactory.bean.ftydlbean;

import java.util.List;

/**
 * 创建时间：2017/11/17
 * 编写人：lizhipeng
 * 功能描述：新建时，区分花色保存数量及计算实体类
 */

public class FTYDLColCountBean {
    private List<FTYDLColCountBean.Data> datas;

    public List<FTYDLColCountBean.Data> getDatas() {
        return datas;
    }

    public void setDatas(List<FTYDLColCountBean.Data> datas) {
        this.datas = datas;
    }

    public static class Data {
        private String ProText;//花色
        private String ProCount;
        private String ProNum;//每日实裁数
        private String ProClippingnumber;//实裁数
        private String ProTotalCompletion;//总完工数
        private String BalanceAmount;//结余数量

        public String getProClippingnumber() {
            return ProClippingnumber;
        }

        public void setProClippingnumber(String proClippingnumber) {
            ProClippingnumber = proClippingnumber;
        }

        public String getProTotalCompletion() {
            return ProTotalCompletion;
        }

        public void setProTotalCompletion(String proTotalCompletion) {
            ProTotalCompletion = proTotalCompletion;
        }

        public String getBalanceAmount() {
            return BalanceAmount;
        }

        public void setBalanceAmount(String balanceAmount) {
            BalanceAmount = balanceAmount;
        }

        public String getProText() {
            return ProText;
        }

        public void setProText(String proText) {
            ProText = proText;
        }

        public String getProCount() {
            return ProCount;
        }

        public void setProCount(String proCount) {
            ProCount = proCount;
        }

        public String getProNum() {
            return ProNum;
        }

        public void setProNum(String proNum) {
            ProNum = proNum;
        }
    }
}
