package com.daoran.newfactory.onefactory.util.Http.sharedparams;

import android.content.Context;
import android.content.SharedPreferences;

import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储用户名和密码
 * Created by lizhipeng on 2017/4/10.
 */

public class SharedHelper {

    private Context context;
    public SharedHelper() {

    }
    public SharedHelper(Context context) {
        this.context = context;
    }

    /**
     * 保存数据
     *
     * @param username
     * @param passwd
     */
    public void save(String username, String passwd) {
        SharedPreferences sp = context.getSharedPreferences("mysp", 0);//存储到mysp.xml文件
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.commit();
        ToastUtils.ShowToastMessage("信息已保存入sharedPreferences", context);
    }

    //定义一个读取SP文件的方法
    public Map<String, String> read() {
        Map<String, String> data = new HashMap<String, String>();
        SharedPreferences sp = context.getSharedPreferences("mysp", 0);
        data.put("username", sp.getString("username", ""));
        data.put("passwd", sp.getString("passwd", ""));
        return data;
    }

}
