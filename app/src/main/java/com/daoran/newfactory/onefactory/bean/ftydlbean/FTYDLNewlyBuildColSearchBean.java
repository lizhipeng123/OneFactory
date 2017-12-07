package com.daoran.newfactory.onefactory.bean.ftydlbean;

/**
 * 新建生产日报保存获取同款号不同花色数据请求参数
 * Created by lizhipeng on 2017/5/8.
 */

public class FTYDLNewlyBuildColSearchBean {
    private Conditions conditions;
    private int pageNum;
    private int pageSize;

    public class Conditions {
        private String item;//款号
        private String workingProcedure;//工序
        private String styp;//样衣种类

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
