package com.daoran.newfactory.onefactory.bean.ftydlbean;

/**
 * 创建时间：2017/11/22
 * 编写人：lizhipeng
 * 功能描述：(未用到)
 */

public class PropostDetailBean {
    private PropostDetailBean.Conditions conditions;
    private int pageNum;
    private int pageSize;

    public class Conditions {
        private String item;//款号
        private String prddocumentary;//制单人
        private String subfactory;//工厂
        private String workingProcedure;//车间
        private String styp;//样衣种类
        private Boolean prddocumentaryisnull;//是否为空

        public String getStyp() {
            return styp;
        }

        public void setStyp(String styp) {
            this.styp = styp;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getPrddocumentary() {
            return prddocumentary;
        }

        public void setPrddocumentary(String prddocumentary) {
            this.prddocumentary = prddocumentary;
        }

        public String getSubfactory() {
            return subfactory;
        }

        public void setSubfactory(String subfactory) {
            this.subfactory = subfactory;
        }

        public String getWorkingProcedure() {
            return workingProcedure;
        }

        public void setWorkingProcedure(String workingProcedure) {
            this.workingProcedure = workingProcedure;
        }

        public Boolean getPrddocumentaryisnull() {
            return prddocumentaryisnull;
        }

        public void setPrddocumentaryisnull(Boolean prddocumentaryisnull) {
            this.prddocumentaryisnull = prddocumentaryisnull;
        }

    }

    public PropostDetailBean.Conditions getConditions() {
        return conditions;
    }

    public void setConditions(PropostDetailBean.Conditions conditions) {
        this.conditions = conditions;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
