package com.daoran.newfactory.onefactory.bean.loginbean;

/**
 * 菜单实体
 * Created by lizhipeng on 2017/5/10.
 */

public class WorkBean {
    private String PhoneUrl;//链接
    private String text;//文本
    private String img;//图片

    public WorkBean(String phoneUrl, String text, String img) {
        PhoneUrl = phoneUrl;
        this.text = text;
        this.img = img;
    }

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
