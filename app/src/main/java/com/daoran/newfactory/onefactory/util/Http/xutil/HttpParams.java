package com.daoran.newfactory.onefactory.util.Http.xutil;





import com.daoran.newfactory.onefactory.util.utils.Tools;
import com.lidroid.xutils.http.RequestParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpParams extends RequestParams {
    private String serviceId;
    private Map<String, String> map = new HashMap<String, String>();

    public HttpParams(String serviceId) {
        this.serviceId = serviceId;
//        if (!WCCBaseActivity.token.equals(""))
//            map.put("t", WCCBaseActivity.token);
    }

    public HttpParams() {
    }

    public void putString(String key, String value) {
        value = value.replace(" ", "%20");
        map.put(key, value);
    }

    public String toString() {
        String value = "";
        for (Entry<String, String> en : map.entrySet()) {
            value += en.getKey() + "=" + en.getValue() + "&";
        }
        if (value.lastIndexOf("&") >= 0) {
            value = value.substring(0, value.lastIndexOf("&"));
        }
        if (Tools.StringHasContent(value)) {
            if (serviceId.contains("?")) {
                return serviceId + "&" + value;
            }else{
                return serviceId + "?" + value;
            }
        } else {
            return serviceId;
        }
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }
}
