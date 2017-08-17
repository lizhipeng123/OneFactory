package com.daoran.newfactory.onefactory.bean;

import java.io.Serializable;

/**
 * side实体类
 * Created by lizhipeng on 2017/8/17.
 */

public class PersionInfo implements Serializable {
    private String nameString;
    private boolean chick;//标识

    public PersionInfo(String nameString) {
        this.nameString = nameString;
    }

    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public boolean isChick() {
        return chick;
    }

    public void setChick(boolean chick) {
        this.chick = chick;
    }
}
