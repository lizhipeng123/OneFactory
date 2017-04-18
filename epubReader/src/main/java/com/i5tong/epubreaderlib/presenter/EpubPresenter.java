package com.i5tong.epubreaderlib.presenter;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.i5tong.epubreaderlib.bean.RowData;
import com.i5tong.epubreaderlib.ui.EpubReaderInterface;
import com.i5tong.epubreaderlib.utils.AppConfig;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubReader;

public class EpubPresenter {
    private Context context;
    private EpubReaderInterface epubReaderInterface;
    private ProgressDialog progressDialog;
    private List<RowData> contentDetails;

    public EpubPresenter(Context context) {
        this.context = context;
        this.epubReaderInterface = (EpubReaderInterface) context;
        this.progressDialog = new ProgressDialog(context);
        this.contentDetails = new ArrayList<RowData>();
        File f = new File(AppConfig.EpubCacheFolder);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public void init(String url, String filename) {
        downloadEpub(url, filename);
    }

    private void downloadEpub(String url, final String filename) {
        progressDialog.setMessage("下载中");
        progressDialog.show();
        Ion.with(context)
                .load(url)
                .progress(new ProgressCallback() {
                    @Override
                    public void onProgress(final long downloaded, final long total) {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.setMessage("下载中 " + (downloaded * 100 / total) + "%");
                            }
                        });
                        Log.d("epub", "download ===> " + downloaded + " of " + total);
                    }
                })
                .write(new File(AppConfig.EpubCacheFolder + filename + ".epub"))
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File file) {
                        progressDialog.dismiss();
                        if (null == e) {
                            unzipFile(file.getPath(), filename);
                        } else {
                            e.printStackTrace();
                            Toast.makeText(context, "文件损坏，请重试", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void unzipFile(String sourcePath, String filename) {
        try {
            ZipFile zipFile = new ZipFile(sourcePath);
            zipFile.extractAll(AppConfig.EpubCacheFolder + filename);
            parseEpub(sourcePath);
        } catch (ZipException e) {
            e.printStackTrace();
            Toast.makeText(context, "文件损坏，请重试", Toast.LENGTH_LONG).show();
        }
    }

    private void parseEpub(String filePath) {
        try {
            InputStream epubInputStream = new FileInputStream(filePath);
            Book book = (new EpubReader()).readEpub(epubInputStream);

            File opsFile = new File(book.getOpfResource().getHref());
            String opsFileParent = opsFile.getParent();

            logContentsTable(book.getTableOfContents().getTocReferences(), opsFileParent, 0);

            epubReaderInterface.onCharacterGet(contentDetails);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "文件损坏，请重试", Toast.LENGTH_LONG).show();
        }
    }

    private void logContentsTable(List<TOCReference> tocReferences, String parent, int depth) {
        if (tocReferences == null) {
            return;
        }

        for (TOCReference tocReference : tocReferences) {
            StringBuilder tocString = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                tocString.append("\t");
            }
            tocString.append(tocReference.getTitle());
            RowData row = new RowData();
            row.setTitle(tocString.toString());
            row.setParentFolder(parent);
            row.setHref(tocReference.getResource().getHref());
            contentDetails.add(row);
            logContentsTable(tocReference.getChildren(), parent, depth + 1);
        }
    }

}
