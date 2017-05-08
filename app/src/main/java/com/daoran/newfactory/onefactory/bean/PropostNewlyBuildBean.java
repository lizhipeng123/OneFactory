package com.daoran.newfactory.onefactory.bean;

/**
 * 新建生产日报post请求参数
 * Created by lizhipeng on 2017/5/8.
 */

public class PropostNewlyBuildBean {
    private Conditions conditions;
    private int pageNum;
    private int pageSize;

    public class Conditions {
        private String item;
        private String workingProcedure;

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getWorkingProcedure() {
            return workingProcedure;
        }

        public void setWorkingProcedure(String workingProcedure) {
            this.workingProcedure = workingProcedure;
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
