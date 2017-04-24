package com.daoran.newfactory.onefactory.util.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/19.
 *
 * @auther madreain
 */

public class RouteModel implements Serializable {

    private String routeid;// 路书ID

    private String title;// 标题

    private String userid;// 用户ID

    private String imgurl;// 图片URL

    private String datajson;// 数据

    private long mileage;// 里程

    private long addtime;// 添加时间

    public RouteModel() {
    }

    public RouteModel(String routeid, String title, String userid, String imgurl, String datajson, long mileage, long addtime) {
        this.routeid = routeid;
        this.title = title;
        this.userid = userid;
        this.imgurl = imgurl;
        this.datajson = datajson;
        this.mileage = mileage;
        this.addtime = addtime;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDatajson() {
        return datajson;
    }

    public void setDatajson(String datajson) {
        this.datajson = datajson;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public long getAddtime() {
        return addtime;
    }

    public void setAddtime(long addtime) {
        this.addtime = addtime;
    }

    @Override
    public String toString() {
        return "RouteModel{" +
                "routeid='" + routeid + '\'' +
                ", title='" + title + '\'' +
                ", userid='" + userid + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", datajson='" + datajson + '\'' +
                ", mileage=" + mileage +
                ", addtime=" + addtime +
                '}';
    }
}
