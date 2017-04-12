package com.daoran.newfactory.onefactory.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapTools {
    public static String compressWidthAndHeight(String srcPath, int width, int height, String savePath) {
        if (srcPath == null || srcPath.trim().equals("")) {
            return null;
        }
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float ww = width;
        float hh = height;
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressBitmap(bitmap, srcPath, savePath);
    }

    private static String compressBitmap(Bitmap image, String path, String savePath) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            int options = 100;
            while (baos.toByteArray().length / 1024 > 100) {
                if (options <= 0) {
                    break;
                }
                baos.reset();
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);
                options -= 10;
            }
            String name = path.substring(path.lastIndexOf("/") + 1);
            String filePath = "";
            if (savePath == null || savePath.trim().equals("")) {
                filePath = path;
            } else {
                filePath = savePath + File.separator + name;
            }
            FileOutputStream fos = new FileOutputStream(filePath);
            baos.writeTo(fos);
            baos.flush();
            baos.close();
            return filePath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 图片转成string
     *
     * @param bitmap
     * @return
     */
    public static String convertIconToString(Bitmap bitmap) {
        String stringPhoto = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte bytes[] = baos.toByteArray();// 转为byte数组
        stringPhoto = Base64.encodeToString(bytes, Base64.DEFAULT);
        return stringPhoto;

    }

    /**
     * string转成bitmap
     *
     * @param st
     */
    public static Bitmap convertStringToIcon(String st) {
        // OutputStream out;
        Bitmap bitmap = null;
        try {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bitmapArray;
            bitmapArray = Base64.decode(st, Base64.DEFAULT);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

}
