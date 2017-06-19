package com.daoran.newfactory.onefactory.activity.work.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ExcelSDBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.file.GetFilesUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询sd卡中的excel文件
 * Created by lizhipeng on 2017/6/15.
 */

public class ExcelSDActivity extends BaseFrangmentActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener {

    private SPUtils spUtils;
    private SharedPreferences sp;
    private ExcelSDBean docBean;

    private ImageView ivBack;
    private AlertDialog dlgSpecItem;
    private View specItemView;

    private List<Map<String, Object>> aList;
    private ListView folderLv;
    private TextView foldernowTv;
    private SimpleAdapter sAdapter;
    private String baseFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excelsd);
        getViews();
        initViews();
        setListener();
//        queryFiles();
    }

    private void getViews() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        folderLv = (ListView) findViewById(R.id.folder_list);
        foldernowTv = (TextView) findViewById(R.id.folder_now);
//        specItemView = factory.inflate(R.layout.dialog_spec_item, null);
    }

    private void initViews() {
        aList = new ArrayList<Map<String, Object>>();
        baseFile = GetFilesUtils.getInstance().getBasePath();
        sAdapter = new SimpleAdapter(this, aList, R.layout.listitem_folder
                , new String[]{"fImg", "fName", "fInfo"},
                new int[]{R.id.folder_img, R.id.folder_name
                        , R.id.folder_info});
        folderLv.setAdapter(sAdapter);
        folderLv.setOnItemClickListener(this);
        try {
            String root = Environment.getExternalStorageDirectory()
                    .getPath();
            String rootpath = getExternalFilesDir(null).getPath();
            loadFolderList(rootpath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setListener() {
        ivBack.setOnClickListener(this);
    }

    private void loadFolderList(String file) throws IOException {
        List<Map<String, Object>> list = GetFilesUtils.getInstance().getSonNode(file);
        if (list != null) {
            Collections.sort(list, GetFilesUtils.getInstance().defaultOrder());
            aList.clear();
            for (Map<String, Object> map : list) {
                String fileType = (String) map.get(GetFilesUtils.FILE_INFO_TYPE);
                Map<String, Object> gMap = new HashMap<String, Object>();
                if (map.get(GetFilesUtils.FILE_INFO_ISFOLDER).equals(true)) {
                    gMap.put("fIsDir", true);
//                    gMap.put("fImg",R.drawable.filetype_folder );
                    gMap.put("fInfo", map.get(GetFilesUtils.FILE_INFO_NUM_SONDIRS) + "个文件夹和" +
                            map.get(GetFilesUtils.FILE_INFO_NUM_SONFILES) + "个文件");
                } else {
                    gMap.put("fIsDir", false);
                    if (fileType.equals("txt") || fileType.equals("text")) {
//                        gMap.put("fImg", R.drawable.filetype_text);
                    } else {
//                        gMap.put("fImg", R.drawable.filetype_unknow);
                    }
                    gMap.put("fInfo", "文件大小:" + GetFilesUtils.getInstance().getFileSize(map.get(GetFilesUtils.FILE_INFO_PATH).toString()));
                }
                gMap.put("fName", map.get(GetFilesUtils.FILE_INFO_NAME));
                gMap.put("fPath", map.get(GetFilesUtils.FILE_INFO_PATH));
                aList.add(gMap);
            }
        } else {
            aList.clear();
        }
        sAdapter.notifyDataSetChanged();
        foldernowTv.setText(file);
    }

    //Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        String strexcel = param.replace("");
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    //Android获取一个用于打开APK文件的intent
    public static Intent getAllIntent(String param) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    public static Intent openFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if (end.equals("xls")) {
            return getExcelFileIntent(filePath);
        } else {
            return getAllIntent(filePath);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            String fpath = String.valueOf(aList.get(position).get("fPath"));
            System.out.print(fpath);
            if (aList.get(position).get("fIsDir").equals(true)) {
                loadFolderList(aList.get(position).get("fPath").toString());
            } else {
                openFile(fpath);
//                ToastUtils.ShowToastMessage("这是文件，待处理", ExcelSDActivity.this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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