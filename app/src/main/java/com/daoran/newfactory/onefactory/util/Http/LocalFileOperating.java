package com.daoran.newfactory.onefactory.util.Http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.daoran.newfactory.onefactory.util.settings.Comfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LocalFileOperating {

    public static boolean write = true;

    public static void writeLocalFile(Context context, InputStream is, OutputStream os) throws IOException {
        byte buf[] = new byte[1024];

        int numread;
        try {
            while ((numread = is.read(buf)) != -1) {
                os.write(buf, 0, numread);

            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
                is = null;
            }
            if (os != null) {
                os.close();
                os = null;
            }
        }
    }

    public static void deleteFile(Context context, String fileName) {
        File file = new File(context.getCacheDir().getPath() + File.separator + fileName);
        file.delete();
    }

    public static void deleteFiles(Context context) {
        File file = context.getCacheDir();
        if (file.exists()) {

            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    f.delete();
                }
            }
        }
    }

    public static OutputStream getLocalOS(Context context, String fileName) throws FileNotFoundException {
        File file = context.getCacheDir();
        return new FileOutputStream(file.getPath() + File.separator + fileName);
    }

    public static InputStream getLocalIS(Context context, String fileName) throws FileNotFoundException {
        File file = context.getCacheDir();
        return new FileInputStream(file.getPath() + File.separator + fileName);
    }

    public static Bitmap getImageBitmap(Context context, String imageName) throws IOException {

        Bitmap bitmap = null;
        InputStream input = null;
        try {
            input = getLocalIS(context, imageName);
            if (Comfig.isDebug) {
                System.out.println("getImageBitmapInputStream == " + input);
            }
            bitmap = BitmapFactory.decodeStream(input);

            if (Comfig.isDebug) {
                System.out.println("getImageBitmap == " + bitmap);
            }
        } catch (IOException e) {
            if (Comfig.isDebug) {
                System.out.println("ImageBitmapIOException");
            }
            throw e;
        } finally {
            if (input != null) {
                input.close();
                input = null;
            }
        }
        return bitmap;
    }

    public static Bitmap getBitmap(Context context, String url) throws IOException {
        String fileName = NetUtil.getFileName(url);
        if (Comfig.isDebug) {
            System.out.println("imageUrl == " + url);
        }
        Bitmap bitmap = null;
        try {
            bitmap = getImageBitmap(context, fileName);
        } catch (IOException e) {

        }
        if (bitmap == null) {
            NetUtil.downloadFile(context, url);
            bitmap = getImageBitmap(context, fileName);
        } else {

        }
        return bitmap;
    }
}
