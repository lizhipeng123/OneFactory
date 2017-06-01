package com.daoran.newfactory.onefactory.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class IndicatorView extends View {

    private Context mContext;
    private static final int DEFAULT_WIDTH = 200;  //默认的View的宽
    private static final int DEFAULT_HEIGHT = 50;  //默认的View的高

    private Paint mSelectedPaint;
    private Paint mUnSelectedPaint;

    private int mViewWidth;  //View的宽
    private int mViewHeight; //View的高
    private int mRadius; //圆形indicator半径
    private int mIndicatorMargin = 10; //两个indicator之间的margin
    private int mIndicatorCount = 3; //indicat
    private int mSelecetedIndex; //当前选中的indicator索引
    private int mSelectedColor; //选中时的paint color
    private int mUnSelectedColor; //未选中时的paint color

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        mSelecetedIndex = 0;
        mSelectedColor = Color.RED;
        mUnSelectedColor = Color.GRAY;

        initPaints();
    }

    /**
     * 初始化paint
     */
    private void initPaints() {
        mSelectedPaint = new Paint();
        mSelectedPaint.setAntiAlias(true);
        mSelectedPaint.setColor(mSelectedColor);

        mUnSelectedPaint = new Paint();
        mUnSelectedPaint.setAntiAlias(true);
        mUnSelectedPaint.setColor(mUnSelectedColor);
    }

    /**
     * 获取默认的radius
     *
     * @return
     */
    private int getDefaultRadius() {

        int diameter = (int) (((float) mViewWidth - mIndicatorMargin * (mIndicatorCount - 1))
                / mIndicatorCount);

        if (diameter > mViewHeight) {
            diameter = mViewHeight;
        }

        return diameter / 2;
    }

    public void setIndicatorCount(int indicatorCount){
        if(indicatorCount<=0){
            throw new IllegalArgumentException("count must be more than 0");
        }
        mIndicatorCount = indicatorCount;
        setSelected(0);
        postInvalidate();
    }

    public void setSelected(int index){
        if(index<0||index>=mIndicatorCount){
            throw new IllegalArgumentException("index wrong");
        }
        mSelecetedIndex = index;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = DEFAULT_WIDTH;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = DEFAULT_HEIGHT;
        }

        mViewWidth = widthSize;
        mViewHeight = heightSize;
        mRadius = getDefaultRadius();

        setMeasuredDimension(widthSize, heightSize);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawIndicators(canvas);
    }

    /**
     * 绘制Indicator
     *
     * @param canvas
     */
    private void drawIndicators(Canvas canvas) {

        int indicatoresLength = mRadius * 2 * mIndicatorCount
                + (mIndicatorCount - 1) * mIndicatorMargin;
        int centerX = mViewWidth / 2 - indicatoresLength / 2 + mRadius;
        int centerY = mViewHeight / 2;

        Paint paint;

        for (int i = 0; i < mIndicatorCount; i++) {
            if (i == mSelecetedIndex) {
                paint = mSelectedPaint;
            } else {
                paint = mUnSelectedPaint;
            }
            canvas.drawCircle(centerX, centerY, mRadius, paint);
            centerX = centerX + mRadius * 2 + mIndicatorMargin;
        }
    }
}
