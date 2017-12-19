package com.daoran.newfactory.onefactory.bean.ftydlbean;

/**
 * 创建时间：2017/12/1
 * 编写人：lizhipeng
 * 功能描述：生产日报 请求条件的数据集合
 */

public class FTYDLDetailSearchBean {
    private Conditions conditions;
    private int pageNum;
    private int pageSize;

    public class Conditions {
        private String item;//款号
        private String prddocumentary;//制单人
        private String subfactoryTeams;//部门
        private String workingProcedure;//车间工序
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

        public String getSubfactoryTeams() {
            return subfactoryTeams;
        }

        public void setSubfactoryTeams(String subfactoryTeams) {
            this.subfactoryTeams = subfactoryTeams;
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
