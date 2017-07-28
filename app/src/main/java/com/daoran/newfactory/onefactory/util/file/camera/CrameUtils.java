package com.daoran.newfactory.onefactory.util.file.camera;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.daoran.newfactory.onefactory.util.application.CLApplication;
import com.daoran.newfactory.onefactory.util.file.setting.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 照相机帮助类
 *
 * @author djx
 */
public class CrameUtils {

    public File saveFile;
    /**
     * 调用相机
     */
    public final static int CAMERA = 0x0002;
    /**
     * 切图
     */
    public final static int CROP = 0x0003;
    /**
     * 调用相册
     */
    public final static int ALBUM = 0x0004;

    Activity at;

    /**
     * 照相机拍照
     */
    public void camera(Activity activity) {
        at = activity;
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            String filePth = FileUtils.setFileName();
            File file = new File(CLApplication.photoPath, filePth + ".jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            this.saveFile = file;
//            startForResultActivity(activity, intent, CrameUtils.CAMERA);
            activity.startActivityForResult(intent, CrameUtils.CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 照相机拍照
     */
    public void camera(Fragment fragment) {
        at = fragment.getActivity();
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            String filePth = FileUtils.setFileName();
            File file = new File(CLApplication.photoPath, filePth + ".jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            this.saveFile = file;
            fragment.startActivityForResult(intent, CrameUtils.CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 相册
     */
    public void album(Activity activity) {
        at = activity;
        // 调用android的图库
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, CrameUtils.ALBUM);
    }

    /**
     * 相册
     */
    public void album(Fragment fragment) {
        at = fragment.getActivity();
        // 调用android的图库
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//		startForResultActivity(activity, i, CrameUtils.ALBUM);
        fragment.startActivityForResult(i, CrameUtils.ALBUM);
    }

    public String fromAlbumGetFilePath(Activity context, Uri uri) {
//        // ContentResolver resolver = context.getContentResolver();
//        Uri originalUri = uri; // 获得图片的uri
//        // bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); //
//        // 显得到bitmap图片
//        String[] proj = {MediaStore.Images.Media.DATA};
//        // 好像是android多媒体数据库的封装接口，具体的看Android文档
//        Cursor cursor = context.getContentResolver().query(originalUri, proj, null, null, null);
//        // 按我个人理解 这个是获得用户选择的图片的索引值
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        // 将光标移至开头 ，这个很重要，不小心很容易引起越界
//        cursor.moveToFirst();
//        // 最后根据索引值获取图片路径
//        String path = cursor.getString(column_index);
        return getPath(context, uri);
    }

    /**
     * 截取图片(返回图片)
     *
     * @param activity
     * @param uri
     * @param outputX     输出大小
     * @param outputY     输出大小
     * @param requestCode
     */
    public void cropImage(Activity activity, Uri uri, int outputX, int outputY, int requestCode, boolean returnData) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        // 图片格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // intent.putExtra("noFaceDetection", false);
        intent.putExtra("return-data", returnData);
        // intent.putExtra("scale", true);
        String filePth = FileUtils.setFileName();
        File file = new File(CLApplication.photoPath, filePth + ".jpg");
        file.delete();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        this.saveFile = file;
        startForResultActivity(activity, intent, requestCode);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public void cropImageUri(Activity activity, Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1.1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri.getPath());
        intent.putExtra("return-data", false);
        intent.putExtra("scale", true);//黑边
        intent.putExtra("scaleUpIfNeeded", true);//黑边
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, requestCode);

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /**
     * 返回activity
     */
    private void startForResultActivity(Activity activity, Intent intent, int reqest) {
        activity.startActivityForResult(intent, reqest);
    }
}
