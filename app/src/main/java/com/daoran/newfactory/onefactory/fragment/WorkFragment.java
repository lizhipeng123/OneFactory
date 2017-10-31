package com.daoran.newfactory.onefactory.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.setting.WorkPwSwitchActivity;
import com.daoran.newfactory.onefactory.adapter.ScrollWrokAdapter;
import com.daoran.newfactory.onefactory.adapter.WorkPwSwitchAdapter;
import com.daoran.newfactory.onefactory.bean.WorkBean;
import com.daoran.newfactory.onefactory.bean.WorkPwSwitchBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.Listener.XXListener;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.setting.FileUtils;
import com.daoran.newfactory.onefactory.view.listview.ScrollGridView;
import com.google.gson.Gson;

import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作模块
 * Created by lizhipeng on 2017/3/22.
 */

public class WorkFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    Activity mactivity;
    private static final int REFRESH_COMPLETE = 0X110;
    private View view;
    private LinearLayout llOpenCarDetail;//listview中item布局
    private TextView tvOpenCarDetail;//item中各项功能名称
    private ImageView ivopenCarDetail;//item中各项功能图片
    private TextView idworkname;//切换用户的显示文本按钮
    private SharedPreferences sp;
    private SPUtils spUtils;
    private SwipeRefreshLayout swipeRefreshLayout;//工作模块除了顶部之外的页面layout
    ScrollWrokAdapter adapter;

    /*重新登陆后赋予的权限*/
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private boolean isNeedCheck = true;
    private List<WorkBean> workBeen = new ArrayList<WorkBean>();//菜单bean的list集合
    private WorkBean workBean;//菜单bean实体类
    private ScrollGridView sgv_gridview;//工作模块显示的各个功能控件
    private String workitemview;
    private String sl;

    private PopupWindow popupWindow;//长按左侧文本按钮弹出的窗体控件

    private static final int PERMISSON_REQUESTCODE = 0;

    private WorkPwSwitchBean workPwSwitchBean;//保存的用户实体类
    private List<WorkPwSwitchBean.Data> switchBeendatalist
            = new ArrayList<WorkPwSwitchBean.Data>();//用户实体集合
    private WorkPwSwitchAdapter switchAdapter;//用户list显示列表
    private DrawerFragment drawerFragment;
    //工作模块中间gridview每个item显示的图片（已固定要显示的图片）
    private String[] intimage = {String.valueOf(R.mipmap.count_500),
            String.valueOf(R.mipmap.daily),
            String.valueOf(R.mipmap.navigation_20),
            String.valueOf(R.mipmap.prd_220),
            String.valueOf(R.mipmap.regedit_220),
            String.valueOf(R.mipmap.ucar_220)};
    //开启线程刷新当前工作页面
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mactivity = getActivity();
        //加载工作页面
        view = inflater.inflate(R.layout.fragment_work, container, false);
        getViews();
        initViews();
        setPhoneMenu();
        setListener();
        /**
         * 登录进来之后删除本机存储中的新版apk与path从增量包
         */
        String newDir = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/dfAppupdate/newPatchdaff.apk";//创建的新apk地址
        String patchDir = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/dfAppupdate/" + "CLApp_" +
                "Dfapp" + ".patch";//已转到本地的增量包patch
        FileUtils.deleteFile(patchDir);
        FileUtils.deleteFile(newDir);
        return view;
    }

    @Override
    public void onResume() {//执行完onCreate之后执行此状态
        super.onResume();
        String namebuld = sp.getString("name", "");
        spUtils.put(mactivity, "name", namebuld);
        if (namebuld.equals("") && namebuld == null) {
            namebuld = "长按重新登录";
        }
        idworkname.setText(namebuld);
//        idworkname.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                drawerFragment.openDeawer();
//            }
//        });
        spUtils.put(mactivity, "usernamerecoder", namebuld);
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        tvOpenCarDetail = (TextView) view.findViewById(R.id.tvOpenCarDetail);
        idworkname = (TextView) view.findViewById(R.id.idworkname);
        ivopenCarDetail = (ImageView) view.findViewById(R.id.ivopenCarDetail);
        llOpenCarDetail = (LinearLayout) view.findViewById(R.id.llOpenCarDetail);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);
    }

    /**
     * 操作控件
     */
    private void initViews() {
        /*需要的时候可以加上*/
//        drawerFragment = (DrawerFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
//        drawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) getActivity().findViewById(R.id.activity_main));
    }

    /**
     * 点击事件
     */
    private void setListener() {
//        //长按左侧用户名按钮后弹出添加账号或切换用户弹窗
//        idworkname.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                initPopWindow();
//                return false;
//            }
//        });
        //刷新当前页面
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 适配7.0权限
     *
     * @param
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(mactivity,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            //判断
            if (ContextCompat.checkSelfPermission(mactivity,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    mactivity, perm)) {
                needRequestPermissonList.add(perm);//集合中添加权限
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 查询角色相关菜单
     */
    private void setPhoneMenu() {
        sp = getContext().getSharedPreferences("my_sp", 0);
        String name = sp.getString("username", "");
        String strmenu = HttpUrl.debugoneUrl + "login/getphonemenu/" + name;
        final ProgressDialog progressDialog = ProgressDialog.show(mactivity,
                "请稍候...", "正在加载中...", false, true);
        NetUtil.getAsyncHttpClient().get(strmenu, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                });
                thread.start();
                workBeen.clear();//先清空当前用户菜单，防止重复加载
                String ress = content.replace("\\", "");
                String ression = StringUtil.sideTrim(ress, "\"");
                String resscontent = ression.replace("\'", "\"");
                System.out.print(resscontent);
                String[] worklist = new String[workBeen.size()];
                for (int i = 0; i < workBeen.size(); i++) {
                    worklist[i] = workBeen.get(i).getText();
                }
                System.out.print(worklist);
                try {
                    //循环解析角色菜单添加到自定义实体类中
                    JSONArray temp = new JSONArray(resscontent);
                    for (int i = 0; i < temp.length(); i++) {
                        String Stringcar = temp.getString(i);
                        String txt = new JSONObject(Stringcar).getString("text");
                        String[] stringcarlist = txt.split(",");
                        boolean stringcar = containsAll(worklist, stringcarlist);
                        if (stringcar == false) {
                            String txt1 = new JSONObject(Stringcar).getString("text");
                            String phoneurl = new JSONObject(Stringcar).getString("PhoneUrl");
                            String img = new JSONObject(Stringcar).getString("img");
                            workBeen.add(new WorkBean(phoneurl, txt1, img));
                        } else {

                        }
                    }
                    System.out.print(workBeen);
                    sl = temp.getString(0);
                    JSONObject jsonObject = new JSONObject(sl);
                    workitemview = jsonObject.getString("text");
                    sgv_gridview = (ScrollGridView) view.findViewById(R.id.sgv_gridview);
                    sgv_gridview.setAdapter(new ScrollWrokAdapter(getActivity(), workBeen));//填充菜单数据到视图
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    /**
     * 长按弹出菜单
     */
    private void initPopWindow() {
        View contenview = LayoutInflater.from(mactivity.getApplicationContext()).
                inflate(R.layout.popupwindow_name_switch, null);
        //设置弹出的用户菜单界面
        popupWindow = new PopupWindow(
                mactivity.findViewById(R.id.mainLayout), 500, 600, true);
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contenview);//加载xml界面
        TextView tvPwSwitch = (TextView) contenview.findViewById(R.id.tvPwSwitch);
        tvPwSwitch.setText("添加账号");
        //点击添加账号后跳转添加账号页面
        tvPwSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mactivity, WorkPwSwitchActivity.class);
                mactivity.startActivity(intent);
                mactivity.finish();
                popupWindow.dismiss();
            }
        });
        //处理已保存的所有用户列表
        ListView llPwSwitch = (ListView) contenview.findViewById(R.id.llPwSwitch);
        String listwork = sp.getString("workbeenlist", "");
        workPwSwitchBean = new Gson().fromJson(listwork, WorkPwSwitchBean.class);
        if (listwork.equals("")) {
        } else {
            switchBeendatalist = workPwSwitchBean.getDatas();
            for (int i = 0; i < switchBeendatalist.size(); i++) {
                String struname = sp.getString("name", "");
                String uname = switchBeendatalist.get(i).getU_name();
                if (struname.equals(uname)) {
                    switchBeendatalist.remove(switchBeendatalist.get(i));
                }
            }
            switchAdapter = new WorkPwSwitchAdapter(mactivity.getApplicationContext(), switchBeendatalist);
            llPwSwitch.setAdapter(switchAdapter);//填充list
            switchAdapter.setOnXXClickListener(new XXListener() {
                @Override
                public void onXXClick() {
                    popupWindow.dismiss();
                    setPhoneMenu();//点击某一项时可以切换用户并且刷新页面
                }
            });
        }
        popupWindow.setFocusable(true);//设置为true才可以获取控件的点击事件
        popupWindow.showAsDropDown(idworkname);//贴在点击控件的下方显示
    }

    /**
     * 两个数组比较是否存在
     *
     * @param array1
     * @param array2
     * @return
     */
    private static boolean containsAll(String[] array1, String[] array2) {
        for (String str : array2) {
            if (!ArrayUtils.contains(array1, str)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//新打开的activity关闭后返回的数据
        if (resultCode == 2) {
            if (requestCode == 1) {
                getViews();
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
        ToastUtils.ShowToastMessage("刷新了", getActivity());
    }
}