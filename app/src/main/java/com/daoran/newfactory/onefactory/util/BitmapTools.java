package com.daoran.newfactory.onefactory.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
}
