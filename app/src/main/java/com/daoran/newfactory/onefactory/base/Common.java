package com.daoran.newfactory.onefactory.base;

import android.content.Intent;

/**
 * 公共跳转类（不带参）
 * Created by lizhipeng on 2017/3/22.
 */

public class Common extends BaseFrangmentActivity {

    public void skipActivity(Class<?> clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
        this.finish();
    }
}
