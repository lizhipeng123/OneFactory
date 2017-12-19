package com.daoran.newfactory.onefactory.bean.ftydlbean;

/**
 * 创建时间：2017/12/1
 * 编写人：lizhipeng
 * 功能描述：精确查询的请求数据集合
 */

public class FTYDLDetailDetailBean {
    private Conditions conditions;
    private int pageNum;
    private int pageSize;

    public class Conditions {
        private int ID;//int
        private int salesid;//引用的排单计划表id  int
        private String item;//款号
        private String prddocumentary;//跟单人
        private String subfactoryTeams;//部门
        private String workingProcedure;//车间工序
        private String prodcol;//花色
        private String recorder;//制单人
        private String recordat;//制单时间
        private String recordid;//制单人id
        private String year;//年
        private String month;//月
        private Boolean prddocumentaryisnull;//是否为空

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getSalesid() {
            return salesid;
        }

        public void setSalesid(int salesid) {
            this.salesid = salesid;
        }

        public String getProdcol() {
            return prodcol;
        }

        public void setProdcol(String prodcol) {
            this.prodcol = prodcol;
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

        public String getRecordid() {
            return recordid;
        }

        public void setRecordid(String recordid) {
            this.recordid = recordid;
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
