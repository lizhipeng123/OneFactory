package com.daoran.newfactory.onefactory.bean;

import java.util.List;

/**
 * 查货跟踪实体类
 * Created by lizhipeng on 2017/4/27.
 */

public class CommoditydetailBean {

    private int totalCount;
    private List<DataBean> Data;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * ID : 11041
         * subfactory : null
         * item : SOFTtest
         * ctmtxt : WHITE STUFF
         * sealedrev : null
         * docback : null
         * predt : null
         * lcdat : 2017/04/20
         * sewFdt : null
         * sewMdt : null
         * taskqty : 2000.0
         * cutqty : null
         * preMemo : null
         * predoc : null
         * fabricsok : null
         * accessoriesok : null
         * spcproDec : null
         * spcproMemo : null
         * QCbdt : null
         * QCmdt : null
         * QCMedt : null
         * QCbdtDoc : null
         * QCmdtDoc : null
         * QCedtDoc : null
         * fctmdt : null
         * fctedt : null
         * prddocumentary : null
         * QCMemo : null
         * packbdat : null
         * packqty2 : null
         * factlcdat : null
         * ourAfter : null
         * prdmaster : null
         * QCMasterScore : null
         * batchid : null
         * QAname : null
         * firstsamQA : null
         * QAScore : null
         * QAMemo : null
         * ctmchkdt : null
         * IPQC : null
         * IPQCmdt : null
         * IPQCPedt : null
         * predocdt : null
         * prebdt : null
         * premdt : null
         * preedt : null
         */

        private int ID;
        private String subfactory;//加工厂
        private String item;//款号
        private String ctmtxt;//客户
        private String sealedrev;//封样资料接收时间
        private String docback;//大货资料接收时间
        private String predt;//开产前会时间
        private String lcdat;//出货时间
        private String sewFdt;//上线日期
        private String sewMdt;//下线日期
        private String taskqty;//制单数量
        private String cutqty;//实裁数
        private String preMemo;//需要特别备注的情况
        private String predoc;//产前会报告
        private String fabricsok;//大货面料情况
        private String accessoriesok;//大货辅料情况
        private String spcproDec;//大货特殊工艺情况
        private String spcproMemo;//特殊工艺特别备注
        private String QCbdt;//自查早期时间
        private String QCmdt;//自查中期时间
        private String QCMedt;//自查尾期时间
        private String QCbdtDoc;//早期报告
        private String QCmdtDoc;//中期报告
        private String QCedtDoc;//尾期报告
        private String fctmdt;//客查中期时间
        private String fctedt;//客查尾期时间
        private String prddocumentary;//跟单
        private String QCMemo;//QC特别备注
        private String packbdat;//成品包装开始日期
        private String packqty2;//装箱数量
        private String factlcdat;//离厂日期
        private String ourAfter;//后道
        private String prdmaster;//生产主管
        private String QCMasterScore;//主管评分
        private String batchid;//查货批次
        private String QAname;//QA首扎
        private String firstsamQA;
        private String QAScore;//QA首扎件数
        private String QAMemo;//QA首扎日期
        private String ctmchkdt;//业务员确认客查日期
        private String IPQC;
        private String IPQCmdt;//巡检中查
        private String IPQCPedt;//尾查预查
        private String predocdt;//预计产前会报告时间
        private String prebdt;//预计早期时间
        private String premdt;//预计中期时间
        private String preedt;//预计尾期时间

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getSubfactory() {
            return subfactory;
        }

        public void setSubfactory(String subfactory) {
            this.subfactory = subfactory;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getCtmtxt() {
            return ctmtxt;
        }

        public void setCtmtxt(String ctmtxt) {
            this.ctmtxt = ctmtxt;
        }

        public String getSealedrev() {
            return sealedrev;
        }

        public void setSealedrev(String sealedrev) {
            this.sealedrev = sealedrev;
        }

        public String getDocback() {
            return docback;
        }

        public void setDocback(String docback) {
            this.docback = docback;
        }

        public String getPredt() {
            return predt;
        }

        public void setPredt(String predt) {
            this.predt = predt;
        }

        public String getLcdat() {
            return lcdat;
        }

        public void setLcdat(String lcdat) {
            this.lcdat = lcdat;
        }

        public String getSewFdt() {
            return sewFdt;
        }

        public void setSewFdt(String sewFdt) {
            this.sewFdt = sewFdt;
        }

        public String getSewMdt() {
            return sewMdt;
        }

        public void setSewMdt(String sewMdt) {
            this.sewMdt = sewMdt;
        }

        public String getTaskqty() {
            return taskqty;
        }

        public void setTaskqty(String taskqty) {
            this.taskqty = taskqty;
        }

        public String getCutqty() {
            return cutqty;
        }

        public void setCutqty(String cutqty) {
            this.cutqty = cutqty;
        }

        public String getPreMemo() {
            return preMemo;
        }

        public void setPreMemo(String preMemo) {
            this.preMemo = preMemo;
        }

        public String getPredoc() {
            return predoc;
        }

        public void setPredoc(String predoc) {
            this.predoc = predoc;
        }

        public String getFabricsok() {
            return fabricsok;
        }

        public void setFabricsok(String fabricsok) {
            this.fabricsok = fabricsok;
        }

        public String getAccessoriesok() {
            return accessoriesok;
        }

        public void setAccessoriesok(String accessoriesok) {
            this.accessoriesok = accessoriesok;
        }

        public String getSpcproDec() {
            return spcproDec;
        }

        public void setSpcproDec(String spcproDec) {
            this.spcproDec = spcproDec;
        }

        public String getSpcproMemo() {
            return spcproMemo;
        }

        public void setSpcproMemo(String spcproMemo) {
            this.spcproMemo = spcproMemo;
        }

        public String getQCbdt() {
            return QCbdt;
        }

        public void setQCbdt(String QCbdt) {
            this.QCbdt = QCbdt;
        }

        public String getQCmdt() {
            return QCmdt;
        }

        public void setQCmdt(String QCmdt) {
            this.QCmdt = QCmdt;
        }

        public String getQCMedt() {
            return QCMedt;
        }

        public void setQCMedt(String QCMedt) {
            this.QCMedt = QCMedt;
        }

        public String getQCbdtDoc() {
            return QCbdtDoc;
        }

        public void setQCbdtDoc(String QCbdtDoc) {
            this.QCbdtDoc = QCbdtDoc;
        }

        public String getQCmdtDoc() {
            return QCmdtDoc;
        }

        public void setQCmdtDoc(String QCmdtDoc) {
            this.QCmdtDoc = QCmdtDoc;
        }

        public String getQCedtDoc() {
            return QCedtDoc;
        }

        public void setQCedtDoc(String QCedtDoc) {
            this.QCedtDoc = QCedtDoc;
        }

        public String getFctmdt() {
            return fctmdt;
        }

        public void setFctmdt(String fctmdt) {
            this.fctmdt = fctmdt;
        }

        public String getFctedt() {
            return fctedt;
        }

        public void setFctedt(String fctedt) {
            this.fctedt = fctedt;
        }

        public String getPrddocumentary() {
            return prddocumentary;
        }

        public void setPrddocumentary(String prddocumentary) {
            this.prddocumentary = prddocumentary;
        }

        public String getQCMemo() {
            return QCMemo;
        }

        public void setQCMemo(String QCMemo) {
            this.QCMemo = QCMemo;
        }

        public String getPackbdat() {
            return packbdat;
        }

        public void setPackbdat(String packbdat) {
            this.packbdat = packbdat;
        }

        public String getPackqty2() {
            return packqty2;
        }

        public void setPackqty2(String packqty2) {
            this.packqty2 = packqty2;
        }

        public String getFactlcdat() {
            return factlcdat;
        }

        public void setFactlcdat(String factlcdat) {
            this.factlcdat = factlcdat;
        }

        public String getOurAfter() {
            return ourAfter;
        }

        public void setOurAfter(String ourAfter) {
            this.ourAfter = ourAfter;
        }

        public String getPrdmaster() {
            return prdmaster;
        }

        public void setPrdmaster(String prdmaster) {
            this.prdmaster = prdmaster;
        }

        public String getQCMasterScore() {
            return QCMasterScore;
        }

        public void setQCMasterScore(String QCMasterScore) {
            this.QCMasterScore = QCMasterScore;
        }

        public String getBatchid() {
            return batchid;
        }

        public void setBatchid(String batchid) {
            this.batchid = batchid;
        }

        public String getQAname() {
            return QAname;
        }

        public void setQAname(String QAname) {
            this.QAname = QAname;
        }

        public String getFirstsamQA() {
            return firstsamQA;
        }

        public void setFirstsamQA(String firstsamQA) {
            this.firstsamQA = firstsamQA;
        }

        public String getQAScore() {
            return QAScore;
        }

        public void setQAScore(String QAScore) {
            this.QAScore = QAScore;
        }

        public String getQAMemo() {
            return QAMemo;
        }

        public void setQAMemo(String QAMemo) {
            this.QAMemo = QAMemo;
        }

        public String getCtmchkdt() {
            return ctmchkdt;
        }

        public void setCtmchkdt(String ctmchkdt) {
            this.ctmchkdt = ctmchkdt;
        }

        public String getIPQC() {
            return IPQC;
        }

        public void setIPQC(String IPQC) {
            this.IPQC = IPQC;
        }

        public String getIPQCmdt() {
            return IPQCmdt;
        }

        public void setIPQCmdt(String IPQCmdt) {
            this.IPQCmdt = IPQCmdt;
        }

        public String getIPQCPedt() {
            return IPQCPedt;
        }

        public void setIPQCPedt(String IPQCPedt) {
            this.IPQCPedt = IPQCPedt;
        }

        public String getPredocdt() {
            return predocdt;
        }

        public void setPredocdt(String predocdt) {
            this.predocdt = predocdt;
        }

        public String getPrebdt() {
            return prebdt;
        }

        public void setPrebdt(String prebdt) {
            this.prebdt = prebdt;
        }

        public String getPremdt() {
            return premdt;
        }

        public void setPremdt(String premdt) {
            this.premdt = premdt;
        }

        public String getPreedt() {
            return preedt;
        }

        public void setPreedt(String preedt) {
            this.preedt = preedt;
        }
    }
}
