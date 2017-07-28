package com.daoran.newfactory.onefactory.view.edit;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.daoran.newfactory.onefactory.R;

/**
 * 动态监听用户输入
 */
public class EditTextWithDelete extends EditText {

    private Context mContext;
    private Drawable mDelDrawable;

    public EditTextWithDelete(Context context) {
        this(context, null);
    }

    public EditTextWithDelete(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public EditTextWithDelete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    private void init() {
        mDelDrawable = mContext.getResources().getDrawable(R.mipmap.delete_px);
        addTextChangedListener(new TextWatcher() {//监听用户输入
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handleDelDrawable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                handleDelDrawable();
            }
        });
    }

    /**
     * 显示或隐藏Del图标
     */
    private void handleDelDrawable() {
        if (length() >= 1 && hasFocus()) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, mDelDrawable, null);
            //  setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)表示左、上、右、下
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int gapWidth = this.getWidth() - this.getCompoundPaddingRight();
            Rect rect = new Rect(gapWidth, 0, getWidth(), getHeight());
            if (rect.contains(x, y)) {
                setText("");//清除输入的字符串
                handleDelDrawable();
            }
        }
        return super.onTouchEvent(event);
    }
}