package com.daoran.newfactory.onefactory.bean.ftydlbean;

/**
 * 生产日报请求参数实体类
 * Created by lizhipeng on 2017/4/26.
 */

public class FTYDLSearchBean {

    private Conditions conditions;
    private int pageNum;
    private int pageSize;

    public class Conditions {
        private String item;//款号
        private String prddocumentary;//制单人
        private String subfactory;//工厂
        private String workingProcedure;//车间
        private Boolean prddocumentaryisnull;//是否为空

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

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
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
