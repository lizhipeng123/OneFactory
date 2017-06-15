package com.daoran.newfactory.onefactory.bean;

import java.io.Serializable;

/**
 * Created by lizhipeng on 2017/6/15.
 */

public class ExcelSDBean implements Serializable {

    private String id;
    private String path;
    private String size;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
