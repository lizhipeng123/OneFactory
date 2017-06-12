package com.daoran.newfactory.onefactory.util.file;

import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *  Json帮助类
 * Created by lizhipeng on 2017/4/5.
 */

public class JsonUtil {

    private static Gson mGson = new Gson();

    /**
     * 将json字符串转化成实体对象
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static Object stringToObject(String json, Class classOfT) {
        return mGson.fromJson(json, classOfT);
    }

    /**
     * 将对象准换为json字符串 或者 把list 转化成json
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String objectToString(T object) {
        return mGson.toJson(object);
    }

    /**
     * 把json 字符串转化成list
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> stringToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        ArrayList<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }
    public static String changeArrayDateToJson(
            ArrayList<ProducationDetailBean.DataBean> dataBeenlist){
        try {
            JSONArray array = new JSONArray();
            JSONObject object = new JSONObject();
            int length = dataBeenlist.size();
            for(int i = 0;i<length;i++){
                ProducationDetailBean.DataBean stone = dataBeenlist.get(i);
                int id = stone.getID();
                int salesid = stone.getSalesid();
                String item = stone.getWorkers();
                JSONObject stoneobject =new JSONObject();
                stoneobject.put("workers",item);
                stoneobject.put("ID",id);
                stoneobject.put("salesid",salesid);
                array.put(stoneobject);
            }
            object.put("DataBean",array);
            return object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
