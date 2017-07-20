package com.daoran.newfactory.onefactory.util.Http;

/**
 * 接口地址
 * Created by lizhipeng on 2017/3/23.
 */

public class HttpUrl {

    /*正式版—— hzm*/
    public static final String debugoneUrl   = "http://115.238.84.67:8888/WebAPI/api/";//正式服务器
    /*测试账号—— hzm*/
    public static final String  Url = "http://192.168.3.120/WebAPI/api/";
    /*测试账号—— bsj*/
    public static final String  debugtwoUrl  = "http://192.168.3.130/WebAPI/api/";

    public static final String debugthreeUrl = "http://192.168.3.134/WebAPI/api/";
    /*登录地址*/
    public static final String loginUrl = "Login/UserLogin/";
    /*获取后台权限*/
    public static final String menuUrl = "Login/GetPhoneMenuBean/";
    /*获取新版本app*/
    public static final String updateAppUrl = "AppVersion/GetAppVersion";
    /*签到*/
    public static final String savebillUrl = "OutRegister/SaveBill/";
    /*出车列表*/
    public static final String sqlCarApply = "UCarsApply/UCarsApplySearch/";
    /*生产单查询*/
    public static final String sqlcork = "FactoryPlan/BindGridDailyAPP/";
    /*查货跟踪表*/
    public static final String sqldebugcorgetdetail = "QACwork/BindSearchQACworkAPP/";
    public static final String sqlcorgetdetail = "QACwork/APPGetDetail/";
    /*签到统计*/
    public static final String BindSearchAPPPage = "OutRegister/BindSearchAPPPage/";
    /*司机*/
    public static final String DriverBind = "UCarsExamine/DriverBind/";
    /*车牌*/
    public static final String CarNumberBind = "UCarsExamine/CarNumberBind/";
    /*获取出车单详细信息*/
    public static final String GetUCarsApplyModel = "UCarsApply/GetUCarsApplyModel/";
    /*保存修改生产日报信息*/
    public static final String SaveFactoryDaily = "FactoryPlan/SaveFactoryDaily/";
    /*款号选择新建*/
    public static final String FactoryDaily = "FactoryPlan/FactoryDailyAPP/";
}
