package com.daoran.newfactory.onefactory.util.Http;

/**
 * 接口地址
 * Created by lizhipeng on 2017/3/23.
 */

public class HttpUrl {

    /*正式版—— hzm*/
    public static final String Url = " http://192.168.3.120/WebAPI/api/";//正式服务器
    /*测试账号—— hzm*/
    public static final String debugoneUrl = "http://115.238.84.67:8888/WebAPI/api/";
    /*测试账号—— bsj*/
    public static final String debugtwoUrl = "http://192.168.3.130/WebAPI/api/";
    /*登录地址*/
    public static final String loginUrl = "Login/UserLogin/";
    /*获取后台权限*/
    public static final String menuUrl = "Login/GetPhoneMenu/";
    /*获取新版本app*/
    public static final String updateAppUrl = "AppVersion/GetAppVersion";
    /*签到*/
    public static final String savebillUrl = "OutRegister/SaveBill/";
    /*出车列表*/
    public static final String sqlCarApply = "UCarsApply/UCarsApplySearch/";

    public static final String updateCarApply = "UCarsExamine/CarNumberBind/";

    public static final String sqldriver = "UCarsExamine/DriverBind/";
    /*获取车单*/
    public static final String sqlcarsApplyModel = "UCarsApply/GetUCarsApplyModel/";
    /*生产单查询*/
    public static final String sqlcork = "QACwork/BindSearchQACworkAPP/";
    /*查货跟踪表*/
    public static final String sqldebugcorgetdetail = "QACwork/BindSearchQACworkAPP/";
    public static final String sqlcorgetdetail = "QACwork/APPGetDetail/";
    /*签到统计*/
    public static final String BindSearchAPPPage ="OutRegister/BindSearchAPPPage/";

}
