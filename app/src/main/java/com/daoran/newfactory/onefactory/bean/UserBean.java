package com.daoran.newfactory.onefactory.bean;

/**
 * Created by lizhipeng on 2017/3/23.
 */

public class UserBean {
    private String username;
    private String password;


    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
