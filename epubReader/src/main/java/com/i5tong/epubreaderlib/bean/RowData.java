package com.i5tong.epubreaderlib.bean;

import java.io.Serializable;

import nl.siegmann.epublib.util.StringUtil;

/**
 * Created by 王霖 on 2015/4/24 0024.
 */
public class RowData implements Serializable {
    private String title;
    private String parentFolder;
    //    private Resource resource;
    private String href;

    public RowData() {
        super();
    }

    public String getParentFolder() {
        if (StringUtil.isEmpty(parentFolder)) {
            return "";
        } else {
            return parentFolder + "/";
        }
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setParentFolder(String parentFolder) {
        this.parentFolder = parentFolder;
    }

    public String getTitle() {
        return title;
    }

//    public Resource getResource() {
//        return resource;
//    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public void setResource(Resource resource) {
//        this.resource = resource;
//    }
}