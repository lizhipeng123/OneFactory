package com.daoran.newfactory.onefactory.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by djx on 16/1/8.
 * 错误参数
 */
public class SystemParams {
    private static SystemParams systemParams;
    private static Map<String, String> map;
    public static final String NAME = "name";
    public static final String START_FOR_RESULT_Bean = "start_for_result_bean";
    public static final String BRANDINFO = "brand_info";
    public final String VOLLEY_ERROR_CODE = "服务器异常";
    public final String RESPONSE_FORMAT_ERROR = "返回数据格式不对";
    public final String RESPONSE_IS_NULL = "返回数据为空";
    public final String TOKEN_INVALID = "无效的token";
    public final String INVALID_REGIST_CODE = "无效的验证码";
    public final String INVALID_PHONE_OR_PASSWORD = "无效的账号或密码";
    public final String FAILURE = "操作失败";
    public final String INVALID_BE_FOLLOWED_UID = "不可关注";
    public final String FAILURE_SAVE_MOMENTS = "保存动态失败";
    public final String DUPLICATE_MOMENTS_COMMENT_LIKE = "重复点赞";

    public SystemParams() {

    }

    public static SystemParams getInstance() {
        if (systemParams == null) {
            systemParams = new SystemParams();
        }
        mapPutErrorInfo();
        return systemParams;
    }

    /**
     * 根据键值取相应的信息
     *
     * @param key
     * @return
     */
    public static String getErrorMsg(String key) {
        if (map == null) return "";
        return map.get(key);
    }

    private static void mapPutErrorInfo() {
        if (map != null) return;
        map = new HashMap<String, String>();
        map.put("VOLLEY_ERROR_CODE", "服务器异常");
        map.put("RESPONSE_FORMAT_ERROR", "返回数据格式不对");
        map.put("RESPONSE_IS_NULL", "返回数据为空");
        map.put("TOKEN_INVALID", "无效的token");
        map.put("INVALID_REGISTER_CODE", "验证码不正确");
        map.put("INVALID_PHONE_OR_PASSWORD", "手机号或密码错误");
        map.put("FAILURE", "FAILURE");
        map.put("INVALID_BE_FOLLOWED_UID", "不可关注");
        map.put("FAILURE_SAVE_MOMENTS", "保存动态失败");
        map.put("DUPLICATE_MOMENTS_COMMENT_LIKE", "重复点赞");
        map.put("PHONE_EXISTED", "该手机已注册");
        map.put("INVALID_PASSWORD", "旧密码不正确");
        map.put("INVALID_SMSCODE", "验证码不正确");

    }
}
