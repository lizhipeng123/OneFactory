package com.daoran.newfactory.onefactory.activity.work.setting;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.setting.GetFilesUtils;
import com.daoran.newfactory.onefactory.util.file.storage.WpsModel;

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

    private ImageView ivBack;
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
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        folderLv = (ListView) findViewById(R.id.folder_list);
        foldernowTv = (TextView) findViewById(R.id.folder_now);
    }

    /**
     * 操作控件
     */
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
            String rootpath = getExternalFilesDir(null).getPath();
            loadFolderList(rootpath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setListener() {
        ivBack.setOnClickListener(this);
    }

    /**
     * 处理从本地文件中查询出来的excel文件，并显示其信息
     * @param file
     * @throws IOException
     */
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
                boolean flag = openFile(fpath);
                if (flag == true) {
                    ToastUtils.ShowToastMessage("打开文件成功", ExcelSDActivity.this);
                } else {
                    ToastUtils.ShowToastMessage("手机中未安装wps，打开失败", ExcelSDActivity.this);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在wps中打开excel文件
     *
     * @param path
     * @return
     */
    boolean openFile(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Bundle bundle = new Bundle();
        bundle.putString(WpsModel.OPEN_MODE, WpsModel.OpenMode.NORMAL); // 打开模式
        bundle.putBoolean(WpsModel.SEND_CLOSE_BROAD, true); // 关闭时是否发送广播
        bundle.putString(WpsModel.THIRD_PACKAGE, getPackageName()); // 第三方应用的包名，用于对改应用合法性的验证
        bundle.putBoolean(WpsModel.CLEAR_TRACE, true);// 清除打开记录
        // bundle.putBoolean(CLEAR_FILE, true); //关闭后删除打开文件
        intent.setClassName(WpsModel.PackageName.NORMAL, WpsModel.ClassName.NORMAL);
        File file = new File(path);
        if (file == null || !file.exists()) {//判断文件是否为空
            System.out.println("文件为空或者不存在");
            return false;
        }
        //解决Android7.0手机无法读取文件路径的问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //第二个参数对应清单文件中的authorities属性
            Uri uri = FileProvider.getUriForFile(ExcelSDActivity.this,
                    "com.daoran.newfactory.onefactory.fileprovider", file);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.putExtras(bundle);//调用上面的wps路径
        } else {//如果Android手机版本在7.0以下，则不需处理文件
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        try {
            startActivity(intent);//跳转
        } catch (ActivityNotFoundException e) {//抛出打开异常
            System.out.println("打开wps异常：" + e.toString());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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