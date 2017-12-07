package com.daoran.newfactory.onefactory.activity.work.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.file.camera.CrameUtils;
import com.daoran.newfactory.onefactory.view.dialog.settingdialog.ActionSheetDialog;

import java.io.File;

/**
 * 图片
 * Created by lizhipeng on 2017/9/11.
 */

public class PictureActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private static final String TAG = "PictureActivity";
    private ImageView ivBack;
    private Button btndialog;

    /**
     * 获取到的图片路径
     */
    private String picPath;

    private Intent lastIntent;
    private CrameUtils crameUtils;
    private SharedPreferences sp;
    private SPUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getViews();
        setViews();
        setListener();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        btndialog = (Button) findViewById(R.id.btndialog);
        lastIntent = getIntent();
    }

    /**
     * 操作控件
     */
    private void setViews() {
        crameUtils = new CrameUtils();
    }

    /**
     * 点击事件
     */
    private void setListener() {
        ivBack.setOnClickListener(this);
        btndialog.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CrameUtils.CAMERA:
                if (crameUtils.saveFile != null) {
                    picPath = crameUtils.saveFile.getAbsolutePath();
                    if (!new File(picPath).exists()) {
                        return;
                    }
                } else {
                    return;
                }
                break;
            case CrameUtils.ALBUM:
                if (data == null) {
                    return;
                } else {
                    picPath = crameUtils.getPath(PictureActivity.this,
                            data.getData());
                }
                break;
        }
        if (!picPath.equals("")) {
            Intent intent = new Intent(this, PictureSelectActivity.class);
            intent.putExtra("Pathpic", picPath);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回按钮*/
            case R.id.ivBack:
                finish();
                break;
            case R.id.btndialog:
                new ActionSheetDialog(PictureActivity.this)
                        .builder()
                        .setTitle("选择上传方式")
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        crameUtils.camera(PictureActivity.this);
                                    }
                                })
                        .addSheetItem("图库", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        crameUtils.album(PictureActivity.this);
                                    }
                                }).show();
                break;
        }
    }
}