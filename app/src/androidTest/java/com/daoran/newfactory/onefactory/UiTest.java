package com.daoran.newfactory.onefactory;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import junit.framework.TestCase;

/**
 * Created by lizhipeng on 2017/8/18.
 */

public class UiTest extends TestCase {
    public void testA() throws UiObjectNotFoundException {
        //获取设备对象
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        UiDevice uiDevice = UiDevice.getInstance(instrumentation);
        //获取上下文
        Context context = instrumentation.getContext();
        //启动测试app
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.daoran.newfactory.onefactory");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        //打开collapsingToolbarlayout
        String btnLoginid = "com.daoran.newfactory.onefactory:id/btnLogin";
        UiObject btnLogin = uiDevice.findObject(new UiSelector().resourceId(btnLoginid));
//        btnLogin.click();
//        UiObject back = uiDevice.findObject(new UiSelector().description("Navigate up"));
//        back.click();
//        // 点击设备返回按钮
//        uiDevice.pressBack();
    }
}
