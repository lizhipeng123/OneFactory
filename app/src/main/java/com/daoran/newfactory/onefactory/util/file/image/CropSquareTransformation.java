package com.daoran.newfactory.onefactory.util.file.image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

/**
 * 自定义接口，实现图像缩小为原来的一半
 * Created by lizhipeng on 2017/5/18.
 */

public class CropSquareTransformation implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
        int widthLight = source.getWidth();//获取图片宽度
        int heightLight = source.getHeight();//获取图片高度

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        Paint paintColor = new Paint();
        paintColor.setFlags(Paint.ANTI_ALIAS_FLAG);

        RectF rectF = new RectF(new Rect(0, 0, widthLight, heightLight));

        canvas.drawRoundRect(rectF, widthLight / 5, heightLight / 5, paintColor);

        Paint paintImage = new Paint();
        paintImage.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(source, 0, 0, paintImage);
        source.recycle();//销毁对象
        return output;
    }

    @Override
    public String key() {
        return "roundcorner";
    }
}
