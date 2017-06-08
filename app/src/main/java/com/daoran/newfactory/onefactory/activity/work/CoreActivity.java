package com.daoran.newfactory.onefactory.activity.work;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成的二维码界面
 * Created by lizhipeng on 2017/6/8.
 */

public class CoreActivity extends BaseFrangmentActivity
        implements View.OnClickListener {

    private ImageView ivcore;
    private SharedPreferences sp;
    private SPUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
        getViews();
        initViews();
        setListener();
        generate();
    }

    private void getViews() {
        ivcore = (ImageView) findViewById(R.id.ivcore);
    }

    private void initViews() {

    }

    private void setListener() {

    }

    private void generate() {
        sp = getSharedPreferences("my_sp", 0);
        String apkpath = sp.getString("apkpath", "");
        System.out.print(apkpath);
        if (apkpath.equals("")||"".equals(apkpath)) {
            ToastUtils.ShowToastMessage("没有版本可以生成", CoreActivity.this);
        } else {
            Bitmap qrBitmap = generateBitmap(apkpath, 400, 400);
            ivcore.setImageBitmap(qrBitmap);
        }
    }

    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
