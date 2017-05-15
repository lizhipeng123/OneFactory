package com.daoran.newfactory.onefactory.bean;

/**
 * 角色菜单实体
 * Created by lizhipeng on 2017/4/25.
 */

public class GetPhoneMenuBean {

    /**
     * PhoneUrl : PublicAdm/UseCarApply/reqcarmSearch.html
     * text : 用车申请单
     * img : ../../../images/UCar.jpg
     */

    private String PhoneUrl;
    private String text;
    private String img;

    public String getPhoneUrl() {
        return PhoneUrl;
    }

    public void setPhoneUrl(String PhoneUrl) {
        this.PhoneUrl = PhoneUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
