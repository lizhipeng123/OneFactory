package com.daoran.newfactory.onefactory.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.daoran.newfactory.onefactory.activity.work.WorkPwSwitchActivity;
import com.daoran.newfactory.onefactory.adapter.ScrollWrokAdapter;
import com.daoran.newfactory.onefactory.adapter.WorkPwSwitchAdapter;
import com.daoran.newfactory.onefactory.bean.WorkBean;
import com.daoran.newfactory.onefactory.bean.WorkPwSwitchBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.Listener.XXListener;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.ScrollGridView;
//import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.Gson;

import org.apache.commons.lang.ArrayUtils;
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
    private LinearLayout llOpenCarDetail;
    private TextView tvOpenCarDetail;
    private ImageView ivopenCarDetail;
    private TextView idworkname;
    private SharedPreferences sp;
    private SPUtils spUtils;
    private SwipeRefreshLayout swipeRefreshLayout;
    ScrollWrokAdapter adapter;

    private List<WorkBean> workBeen = new ArrayList<WorkBean>();
    private WorkBean workBean;
    private ScrollGridView sgv_gridview;
    private String workitemview;
    private String sl;

    private PopupWindow popupWindow;

    private WorkPwSwitchBean workPwSwitchBean;
    private List<WorkPwSwitchBean.Data> switchBeendatalist
            = new ArrayList<WorkPwSwitchBean.Data>();
    private WorkPwSwitchAdapter switchAdapter;
    private DrawerFragment drawerFragment;
    private String[] intimage = {String.valueOf(R.mipmap.count_500),
            String.valueOf(R.mipmap.daily),
            String.valueOf(R.mipmap.navigation_20),
            String.valueOf(R.mipmap.prd_220),
            String.valueOf(R.mipmap.regedit_220),
            String.valueOf(R.mipmap.ucar_220)};

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
        view = inflater.inflate(R.layout.fragment_work, container, false);
        getViews();
        initViews();
        setPhoneMenu();
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getActivity().getIntent().getExtras();
        String name = bundle.getString("u_name");
        String namebuld = sp.getString("name", "");
        spUtils.put(mactivity, "name", namebuld);
        idworkname.setText(namebuld);
        idworkname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                drawerFragment.openDeawer();
            }
        });
        spUtils.put(mactivity, "usernamerecoder", namebuld);
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
////        drawerFragment = (DrawerFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
////        drawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) getActivity().findViewById(R.id.activity_main));
    }

    private void setListener() {
        idworkname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                initPopWindow();
                return false;
            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);
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
                workBeen.clear();
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
                            String txt1 = "";
                            String phoneurl = "";
                            String img = "";
                            String s = workBeen.get(i).getText();
                        }
                    }
                    System.out.print(workBeen);
                    sl = temp.getString(0);
                    JSONObject jsonObject = new JSONObject(sl);
                    workitemview = jsonObject.getString("text");
                    sgv_gridview = (ScrollGridView) view.findViewById(R.id.sgv_gridview);
                    sgv_gridview.setAdapter(new ScrollWrokAdapter(getActivity(), workBeen));
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
        popupWindow = new PopupWindow(
                mactivity.findViewById(R.id.mainLayout), 500, 600, true);
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contenview);
        TextView tvPwSwitch = (TextView) contenview.findViewById(R.id.tvPwSwitch);
        tvPwSwitch.setText("添加账号");
        tvPwSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mactivity, WorkPwSwitchActivity.class);
                mactivity.startActivity(intent);
                mactivity.finish();
                popupWindow.dismiss();
            }
        });
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
            llPwSwitch.setAdapter(switchAdapter);
            switchAdapter.setOnXXClickListener(new XXListener() {
                @Override
                public void onXXClick() {
                    popupWindow.dismiss();
                    setPhoneMenu();
                }
            });
        }
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(idworkname);
    }

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
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            if(requestCode==1){
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
