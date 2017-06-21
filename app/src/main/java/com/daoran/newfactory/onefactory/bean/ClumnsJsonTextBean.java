package com.daoran.newfactory.onefactory.bean;

/**
 * 查货列权限接口Common/GetClumns中JsonText字段中json数据
 * Created by lizhipeng on 2017/6/21.
 */

public class ClumnsJsonTextBean {

    /**
     * id : 0
     * pId : -1
     * name : pd_saleslist:查货跟踪表
     * isParent : true
     * checked : true
     * open : true
     */

    private String id;
    private String pId;
    private String name;//表名
    private boolean isParent;
    private boolean checked;
    private boolean open;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
