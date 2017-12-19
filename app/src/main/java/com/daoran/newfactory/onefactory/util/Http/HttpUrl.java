package com.daoran.newfactory.onefactory.util.Http;

/**
 * 接口地址
 * Created by lizhipeng on 2017/3/23.
 */

public class HttpUrl {

    /*正式版—— hzm*/
    public static final String  debugoneUrl   = "http://115.238.84.67:8888/WebAPI/api/";//正式服务器
    /*测试账号—— hzm*/
    public static final String debugthreeUrl  = "http://192.168.3.120/WebAPI/api/";
    /*测试账号—— bsj*/
    public static final String  debugtwoUrl   = "http://192.168.3.130/WebAPI/api/";

    public static final String Url = "http://192.168.3.134/WebAPI/api/";
    /*登录地址*/
    public static final String loginUrl = "Login/UserLogin/";
    /*获取后台权限*/
    public static final String GetPhoneMenuBean = "Login/GetPhoneMenuBean/";
    /*获取新版本app*/
    public static final String GetAppVersion = "AppVersion/GetAppVersion";
    /*签到*/
    public static final String SaveBill = "OutRegister/SaveBill/";
    /*出车列表*/
    public static final String UCarsApplySearch = "UCarsApply/UCarsApplySearch/";
    /*生产单查询*/
    public static final String BindGridDailyAPP = "FactoryPlan/BindGridDailyAPP/";
    /*查货跟踪表*/
    public static final String BindSearchQACworkAPP = "QACwork/BindSearchQACworkAPP/";//获取表中全部数据
    public static final String APPGetDetail = "QACwork/APPGetDetail/";//获取明细
    public static final String APPGetBarRight = "QACwork/APPGetBarRight/";//获取列权限
    public static final String dataToExcel = "QACwork/dataToExcel/";//输出的是一个url地址（js上的接口）
    public static final String SaveQACsam = "QACwork/SaveQACsam/";//打样列查询(js上的接口，不确定能用否)
    public static final String BindSearchQACSam = "QACwork/BindSearchQACSam/";//打样列查询（）

    public static final String DoGetImage = "QACwork/DoGetImage/";//获取图片
    /*签到统计*/
    public static final String BindSearchAPPPage = "OutRegister/BindSearchAPPPage/";
    /*司机*/
    public static final String DriverBind = "UCarsExamine/DriverBind/";
    /*车牌*/
    public static final String CarNumberBind = "UCarsExamine/CarNumberBind/";
    /*获取出车单详细信息*/
    public static final String GetUCarsApplyModel = "UCarsApply/GetUCarsApplyModel/";

    /*保存生产日报的数据*/
    public static final String SaveFactoryDaily = "FactoryPlan/SaveFactoryDaily/";
    /*款号选择新建*/
    public static final String FactoryDaily = "FactoryPlan/FactoryDailyAPP/";
    /*裁床下查询分色数据*/
    public static final String SearchDailyData = "FactoryPlan/SearchDailyData/";
    /*根据排单id查询花色以及数量的数据*/
    public static final String FTYDLColSelect = "FactoryPlan/FTYDLColSelect/";

}
