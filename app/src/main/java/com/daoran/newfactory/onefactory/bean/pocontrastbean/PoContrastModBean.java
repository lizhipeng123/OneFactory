package com.daoran.newfactory.onefactory.bean.pocontrastbean;

/**
 * Created by luoqf on 2017/11/30.
 * 生产单物料审核Models
 */

public class PoContrastModBean {
    private int pageNum;//页数
    private int pageSize;//

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    private Conditions conditions;
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
public class Conditions{
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getTl1() {
        return tl1;
    }

    public void setTl1(String tl1) {
        this.tl1 = tl1;
    }

    public String getTl2() {
        return tl2;
    }

    public void setTl2(String tl2) {
        this.tl2 = tl2;
    }

    public String getLcdat1() {
        return lcdat1;
    }

    public void setLcdat1(String lcdat1) {
        this.lcdat1 = lcdat1;
    }

    public String getLcdat2() {
        return lcdat2;
    }

    public void setLcdat2(String lcdat2) {
        this.lcdat2 = lcdat2;
    }

    public String getMtrtxt() {
        return mtrtxt;
    }

    public void setMtrtxt(String mtrtxt) {
        this.mtrtxt = mtrtxt;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getPur() {
        return pur;
    }

    public void setPur(String pur) {
        this.pur = pur;
    }

    public String getIsPur() {
        return isPur;
    }

    public void setIsPur(String isPur) {
        this.isPur = isPur;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPushDate() {
        return pushDate;
    }

    public void setPushDate(String pushDate) {
        this.pushDate = pushDate;
    }

    public String getMegitem() {
        return megitem;
    }

    public void setMegitem(String megitem) {
        this.megitem = megitem;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getIsorderdt() {
        return isorderdt;
    }

    public void setIsorderdt(String isorderdt) {
        this.isorderdt = isorderdt;
    }

    private String cod ;
    private String tl1 ;
    private String tl2 ;
    private String lcdat1;
    private String lcdat2 ;
    private String mtrtxt ;
    private String select ;
    private String pur ;
    private String isPur ;
    private String username ;
    private String pushDate ;
    private String megitem ;
    private String delivered ;
    private String isorderdt ;



}
}
