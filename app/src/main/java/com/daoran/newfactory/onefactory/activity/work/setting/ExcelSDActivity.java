package com.daoran.newfactory.onefactory.activity.work.setting;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ExcelSDBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

import java.io.File;
import java.net.URL;

/**
 * 查询sd卡中的excel文件
 * Created by lizhipeng on 2017/6/15.
 */

public class ExcelSDActivity extends BaseFrangmentActivity
        implements View.OnClickListener {

    private SPUtils spUtils;
    private SharedPreferences sp;
    private ExcelSDBean docBean;

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excelsd);
//        queryFiles();
        getViews();
        initViews();
        setListener();
    }

    private void getViews() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
    }

    private void initViews() {

    }

    private void setListener() {
        ivBack.setOnClickListener(this);
    }

    private void queryFiles() {
        String[] projection = new String[]{MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.SIZE};
        File dir = new File(getExternalFilesDir(null).getPath());
        String dirstr = Environment.getExternalStorageDirectory().getAbsolutePath();
//        String sdcard = getExternalFilesDir(null).getPath();
//        getImageContentUri(this,dir);
        Uri sduri = Uri.fromFile(dir);
        String sdst = String.valueOf(sduri);
        String sdcard = String.valueOf(getImageContentUri(this, dir));
        Cursor cursor = getContentResolver().query(
                Uri.parse(sdcard),
                projection,
                MediaStore.Files.FileColumns.DATA + " like ?",
                new String[]{"%.xls"},
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int idindex = cursor
                        .getColumnIndex(MediaStore.Files.FileColumns._ID);
                int dataindex = cursor
                        .getColumnIndex(MediaStore.Files.FileColumns.DATA);
                int sizeindex = cursor
                        .getColumnIndex(MediaStore.Files.FileColumns.SIZE);
                do {
                    String id = cursor.getString(idindex);
                    String path = cursor.getString(dataindex);
                    String size = cursor.getString(sizeindex);
                    docBean = new ExcelSDBean();
                    docBean.setId(id);
                    docBean.setPath(path);
                    docBean.setSize(size);
                    int dot = path.lastIndexOf("/");
                    String name = path.substring(dot + 1);
                    Log.e("test", name);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
    }

    private Uri file2Content(Uri uri) {
        if (uri.getScheme().equals("file")) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(")
                        .append(MediaStore.Images.ImageColumns.DATA)
                        .append("=")
                        .append("'" + path + "'")
                        .append(")");
                Cursor cur = cr.query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    //do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        System.out.print(uri);
//        LogUtil.i("uri = " + uri);
        return uri;
    }

    public static Uri getImageContentUri(Context context, java.io.File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
        }
    }
}
