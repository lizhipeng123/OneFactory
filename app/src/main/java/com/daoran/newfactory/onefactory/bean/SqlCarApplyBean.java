package com.daoran.newfactory.onefactory.bean;

/**
 * 用车查询页数据实体类
 * Created by lizhipeng on 2017/3/27.
 */

public class SqlCarApplyBean {
    private String carId;
    private String carName;
    private String carDate;

    public SqlCarApplyBean(String carDate, String carId, String carName) {
        this.carDate = carDate;
        this.carId = carId;
        this.carName = carName;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarDate() {
        return carDate;
    }

    public void setCarDate(String carDate) {
        this.carDate = carDate;
    }
}
