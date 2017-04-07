package com.daoran.newfactory.onefactory.util.Http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version V1.0
 * @Description: TODO
 * @date 2013-4-26 下午3:42:34
 */
public class RequestParams {
    private static String ENCODING = "UTF-8";

    protected ConcurrentHashMap<String, String> urlParams;
    protected ConcurrentHashMap<String, ArrayList<String>> urlParamsWithArray;

    public RequestParams() {
        init();
    }

    public RequestParams(Map<String, String> source) {
        init();

        for (Map.Entry<String, String> entry : source.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public RequestParams(List<NameValuePair> source) {
        init();

        int len = source.size();
        for (int i = 0; i < len; i++) {
            String key = String.valueOf(source.get(i).getName());
            String val = String.valueOf(source.get(i).getValue());
            put(key, val);
        }
    }

    public RequestParams(NameValuePair source) {
        init();

        String key = String.valueOf(source.getName());
        String val = String.valueOf(source.getValue());
        put(key, val);
    }

    public RequestParams(String key, String value) {
        init();

        put(key, value);
    }

    public RequestParams(Object... keysAndValues) {
        init();
        int len = keysAndValues.length;
        if (len % 2 != 0)
            throw new IllegalArgumentException("Supplied arguments must be even");
        for (int i = 0; i < len; i += 2) {
            String key = String.valueOf(keysAndValues[i]);
            String val = String.valueOf(keysAndValues[i + 1]);
            put(key, val);
        }
    }

    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    public void put(String key, ArrayList<String> values) {
        if (key != null && values != null) {
            urlParamsWithArray.put(key, values);
        }
    }

    public void remove(String key) {
        urlParams.remove(key);
        urlParamsWithArray.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        for (ConcurrentHashMap.Entry<String, ArrayList<String>> entry : urlParamsWithArray.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            ArrayList<String> values = entry.getValue();
            for (int i = 0; i < values.size(); i++) {
                if (i != 0)
                    result.append("&");
                result.append(entry.getKey());
                result.append("=");
                result.append(values.get(i));
            }
        }

        return result.toString();
    }

    public HttpEntity getEntity() {
        HttpEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(sign(0), ENCODING);
            System.out.println(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return entity;
    }

    private void init() {
        urlParams = new ConcurrentHashMap<String, String>();
        urlParamsWithArray = new ConcurrentHashMap<String, ArrayList<String>>();
    }

    protected List<BasicNameValuePair> getParamsList() {
        List<BasicNameValuePair> lparams = new LinkedList<BasicNameValuePair>();

        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            lparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        for (ConcurrentHashMap.Entry<String, ArrayList<String>> entry : urlParamsWithArray.entrySet()) {
            ArrayList<String> values = entry.getValue();
            for (String value : values) {
                lparams.add(new BasicNameValuePair(entry.getKey(), value));
            }
        }
        return lparams;
    }

    protected String getParamString(int i) {
        return URLEncodedUtils.format(sign(i), ENCODING);
    }

    public List<BasicNameValuePair> sign(int i) {
        List<String> params = new ArrayList<String>();
        List<BasicNameValuePair> paramsList = getParamsList();
        for (BasicNameValuePair pair : paramsList) {
            params.add(pair.getName() + pair.getValue());
        }
        if (i == 1) {
//            paramsList.add(new BasicNameValuePair("sign", Md5Util.MD5(sort(params))));
        } else {
//            paramsList.add(new BasicNameValuePair("sign", Md5Util.MD5(Md5Util.MD5(sort(params)) + CLApplication.getInstance().getUser().getUsersession())));
        }
        return paramsList;
    }

    private static String sort(List<String> string) {
        Collections.sort(string, String.CASE_INSENSITIVE_ORDER);
        String sign = "";
        for (int i = 0; i < string.size(); i++) {
            sign += string.get(i);
        }
        return sign;
    }
}
