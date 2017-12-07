package com.daoran.newfactory.onefactory.bean.qacworkbean;

/**
 * 查货跟踪请求参数实体类
 * Created by lizhipeng on 2017/4/27.
 */

public class QACworkSearchBean {

    private Conditions conditions;
    private int pageNum;//页数
    private int pageSize;//
    public class Conditions {
        private String item;//款号
        private String prddocumentary;//制单人
        private String prdmaster;//生产主管
        private String IPQC;//巡检
        private Boolean prdmasterisnull;//生产主管是否为空

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

        public String getPrdmaster() {
            return prdmaster;
        }

        public void setPrdmaster(String prdmaster) {
            this.prdmaster = prdmaster;
        }

        public Boolean getPrdmasterisnull() {
            return prdmasterisnull;
        }

        public void setPrdmasterisnull(Boolean prdmasterisnull) {
            this.prdmasterisnull = prdmasterisnull;
        }

        public String getIPQC() {
            return IPQC;
        }

        public void setIPQC(String IPQC) {
            this.IPQC = IPQC;
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
