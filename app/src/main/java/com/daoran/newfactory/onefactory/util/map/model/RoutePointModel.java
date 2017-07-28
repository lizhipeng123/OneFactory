package com.daoran.newfactory.onefactory.util.map.model;

/**
 * Created by Administrator on 2016/10/19.
 *
 * @auther madreain
 */

public class RoutePointModel {

    private double latitude;// 纬度

    private double longitude;// 经度

    private String title;// 位置

    public RoutePointModel() {
    }

    public RoutePointModel(double latitude, double longitude, String title) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RoutePointModel{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", title='" + title + '\'' +
                '}';
    }
}