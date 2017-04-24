package com.daoran.newfactory.onefactory.util.Manager;/**
 * Created by admin on 2016/4/7.
 */

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author wujun   键盘管理类
 */
public class SoftKeyboardManager {

    public static void HideSoftKeyboard(View v){
          /*隐藏软键盘*/
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    public static void HideSoftKeyboard(View v,Context context){
        //隐藏软键盘
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    public static  void ShowSoftKeyboard(View v,Context context){
        //软键盘弹出
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


}
