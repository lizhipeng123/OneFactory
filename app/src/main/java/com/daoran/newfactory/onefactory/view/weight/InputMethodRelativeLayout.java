package com.daoran.newfactory.onefactory.view.weight;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.widget.RelativeLayout;

/**
 * 创建时间：2017/12/29
 * 编写人：lizhipeng
 * 功能描述：自定义布局
 */

public class InputMethodRelativeLayout extends RelativeLayout {
    private int width;
    private OnSizeChangedListenner onSizeChangedListenner;
    private boolean sizeChanged = false;//变化的标志
    private int height;
    private int screenWidth;//屏幕宽度
    private int screenHeight;//屏幕高度

    public InputMethodRelativeLayout(Context paramContext,
                                     AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public InputMethodRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Display localDisplay = ((Activity)context).getWindowManager()
                .getDefaultDisplay();
        this.screenWidth=localDisplay.getWidth();
        this.screenHeight = localDisplay.getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.width = widthMeasureSpec;
        this.height = heightMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if((this.onSizeChangedListenner!=null)&&(w==oldw)&&(oldw!=0)
                &&(oldh!=0)){
            if((h>=oldh)||(Math.abs(h-oldh)<=1*this.screenHeight/4)){
                if((h<=oldh)||(Math.abs(h-oldh)<=1*this.screenHeight/4)){
                    return;
                }
                this.sizeChanged = false;
            }else{
                this.sizeChanged = true;
            }
            this.onSizeChangedListenner.onSizeChange(this.sizeChanged,oldh,h);
            measure(this.width-w+getWidth(),
                    this.height -h+getHeight());
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /*设置监听接口*/
    public void setOnSizeChangedListener(InputMethodRelativeLayout.
                                         OnSizeChangedListenner
                                                 paramonSizeChangedListenner){
        this.onSizeChangedListenner = paramonSizeChangedListenner;
    }

    /*大小改变的内部接口*/
    public abstract interface OnSizeChangedListenner {
        public abstract void onSizeChange(boolean paramBoolean, int w,int h);
    }
}
