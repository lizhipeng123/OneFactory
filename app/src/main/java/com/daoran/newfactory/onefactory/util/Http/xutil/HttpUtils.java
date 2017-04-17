package com.daoran.newfactory.onefactory.util.Http.xutil;


import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by djx on 16/1/18.
 */
public class HttpUtils<T> extends com.lidroid.xutils.HttpUtils {
    public static HttpUtils httpUtils;

    public static HttpUtils getInstance() {
        if (httpUtils == null) {
            return new HttpUtils();
        } else {
            return httpUtils;
        }
    }

    public HttpUtils() {
    }

    /**
     * post请求 调用接口
     *
     * @param callBack 回调处理
     * @param hp       参数
     */
    public void sendPost(RequestCallBack<T> callBack, HttpParams hp) {
        this.send(HttpRequest.HttpMethod.POST, hp.toString(), callBack);
    }
}
