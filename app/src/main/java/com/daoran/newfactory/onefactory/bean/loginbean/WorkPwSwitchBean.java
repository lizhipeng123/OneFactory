package com.daoran.newfactory.onefactory.bean.loginbean;

import java.util.List;

/**
 * 切换用户或添加用户时实体类
 * Created by lizhipeng on 2017/7/7.
 */

public class WorkPwSwitchBean {

    private List<Data> datas;

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public static class Data {
        private String u_name;//用户名
        private String logid;//账号
        private String passwork;//密码

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getLogid() {
            return logid;
        }

        public void setLogid(String logid) {
            this.logid = logid;
        }

        public String getPasswork() {
            return passwork;
        }

        public void setPasswork(String passwork) {
            this.passwork = passwork;
        }
    }
}
