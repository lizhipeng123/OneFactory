package com.daoran.newfactory.onefactory.activity.work.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.util.image.BitmapTools;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码
 * Created by lizhipeng on 2017/6/8.
 */

public class CoreActivity extends BaseFrangmentActivity
        implements View.OnClickListener {

    private ImageView ivcore;
    private ImageView ivBack;
    private ImageView ivZxingSearch;
    private TextView tvZingtext;
    private SharedPreferences sp;
    private SPUtils spUtils;
    private Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
        mTencent = Tencent.createInstance("1106135321", this.getApplicationContext());
        getViews();
        initViews();
        setListener();
        generate();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        ivcore = (ImageView) findViewById(R.id.ivcore);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        tvZingtext = (TextView) findViewById(R.id.tvZingtext);
    }

    private void initViews() {

    }

    private void setListener() {
        ivBack.setOnClickListener(this);
        ivcore.setOnCreateContextMenuListener(this);
    }

    /**
     * 把apk路径生成二维码图片
     */
    private void generate() {
        sp = getSharedPreferences("my_sp", 0);
        String apkpath = sp.getString("applicationapkpath", "");
        System.out.print(apkpath);
        if (apkpath.equals("") || "".equals(apkpath)) {
            ToastUtils.ShowToastMessage("没有版本可以生成", CoreActivity.this);
        } else {
            Bitmap qrBitmap = generateBitmap(apkpath, 600, 600);
            Bitmap logobitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.daoran);
            Bitmap bitmap = addLogo(qrBitmap, logobitmap);
            String strbitmap = BitmapTools.convertIconToString(bitmap);
            spUtils.put(getApplicationContext(), "strbitmap", strbitmap);
            ivcore.setImageBitmap(bitmap);
        }
    }

    /**
     * 生成二维码
     *
     * @param content
     * @param width
     * @param height
     * @return
     */
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

    /**
     * 给二维码中心添加logo
     *
     * @param qrbitmap
     * @param logoBitmap
     * @return
     */
    private Bitmap addLogo(Bitmap qrbitmap, Bitmap logoBitmap) {
        int qrBitmapWidth = qrbitmap.getWidth();
        int qrBitmapHeight = qrbitmap.getHeight();
        int logoBitmapWidth = logoBitmap.getWidth();
        int logoBitmapHeight = logoBitmap.getHeight();
        Bitmap blankBitmap = Bitmap.createBitmap(qrBitmapWidth,
                qrBitmapHeight, Bitmap
                        .Config.ARGB_8888);
        Canvas canvas = new Canvas(blankBitmap);
        canvas.drawBitmap(qrbitmap, 0, 0, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        float scaleSize = 1.0f;
        while ((logoBitmapWidth / scaleSize) >
                (qrBitmapWidth / 5) || (logoBitmapHeight / scaleSize) >
                (qrBitmapHeight / 5)) {
            scaleSize *= 2;
        }
        float sx = 1.0f / scaleSize;
        canvas.scale(sx, sx, qrBitmapWidth / 2, qrBitmapHeight / 2);
        canvas.drawBitmap(logoBitmap, (qrBitmapWidth - logoBitmapWidth) / 2,
                (qrBitmapHeight - logoBitmapHeight) / 2, null);
        canvas.restore();
        return blankBitmap;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
//            case R.id.tvFenxiangBtn:
//                QQshare();
//                break;
        }
    }

    public void scan(View view) {
        startActivityForResult(new Intent(CoreActivity.this,
                CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result = bundle.getString("result");
                System.out.print(result);
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri uri = Uri.parse(result);
                intent.setData(uri);
                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(intent);
            }
        }
    }

    /**
     * qq分享
     */
    public void QQshare() {
        sp = getSharedPreferences("my_sp", 0);
        String apkpath = sp.getString("apkpath", "");
        ShareListener shareListener = new ShareListener();
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "DFAPP");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "随身移动的工厂");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, apkpath);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://www.taoandcompany.com/CN/Recources/images/Tao.png");
        mTencent.shareToQQ(this, params, shareListener);
    }

    /**
     * 处理分享返回结果
     */
    public class ShareListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            doComplete((JSONObject) o);
        }

        protected void doComplete(JSONObject values) {
            //分享成功
        }

        @Override
        public void onError(UiError uiError) {
            //分享失败
            if (uiError.errorDetail == null) {
                ToastUtils.ShowToastMessage("错误，为空", CoreActivity.this);
            } else {
                ToastUtils.ShowToastMessage("" + uiError.errorDetail, CoreActivity.this);
            }
        }

        @Override
        public void onCancel() {
            //分享取消
            ToastUtils.ShowToastMessage("分享被取消", CoreActivity.this);
        }
    }

    /**
     * 保存图片到系统图库
     *
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "DfAPP");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(file.getPath()))));
        ToastUtils.ShowToastMessage("保存成功", context);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 1001, 0, "保存到系统图库");
        menu.add(0, 1002, 1, "发送给朋友");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getTitle().toString()) {
            case "保存到系统图库":
                sp = getSharedPreferences("my_sp", 0);
                String strpath = sp.getString("strbitmap", "");
                Bitmap bitmap = BitmapTools.convertStringToIcon(strpath);
                saveImageToGallery(getApplicationContext(), bitmap);
                break;
            case "发送给朋友":
                QQshare();
                break;
        }
        return super.onContextItemSelected(item);
    }
}