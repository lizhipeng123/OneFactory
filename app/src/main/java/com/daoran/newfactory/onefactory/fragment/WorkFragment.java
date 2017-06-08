package com.daoran.newfactory.onefactory.fragment;

import android.app.Activity;
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
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ScrollWrokAdapter;
import com.daoran.newfactory.onefactory.bean.WorkBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.ScrollGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

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
    private ScrollGridView sgv_gridview;
    private String workitemview;
    private String sl;

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
//                    setPhoneMenu();
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
        setPhoneMenu();
        getViews();
        initViews();
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getActivity().getIntent().getExtras();
        String name = bundle.getString("u_name");
        spUtils.put(mactivity, "u_name", name);
        idworkname.setText(name);
        idworkname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                drawerFragment.openDeawer();
            }
        });
        spUtils.put(mactivity, "usernamerecoder", name);
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
//        idworkname.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return false;
//            }
//        });
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 查询角色相关菜单
     */
    private void setPhoneMenu() {
        sp = getContext().getSharedPreferences("my_sp", 0);
        String name = sp.getString("username", "");
        String strmenu = HttpUrl.debugoneUrl + "login/getphonemenu/" + name;
        OkHttpUtils.get()
                .url(strmenu)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String ress = response.replace("\\", "");
                        String ression = StringUtil.sideTrim(ress, "\"");
                        String resscontent = ression.replace("\'", "\"");
                        System.out.print(resscontent);
                        try {
                            JSONArray temp = new JSONArray(resscontent);
                            for (int i = 0; i < temp.length(); i++) {
                                String Stringcar = temp.getString(i);
                                String txt = new JSONObject(Stringcar).getString("text");
                                String phoneurl = new JSONObject(Stringcar).getString("PhoneUrl");
                                String img = new JSONObject(Stringcar).getString("img");
                                workBeen.add(new WorkBean(phoneurl, txt, img));
                            }
                            sl = temp.getString(0);
                            JSONObject jsonObject = new JSONObject(sl);
                            workitemview = jsonObject.getString("text");
                            sgv_gridview = (ScrollGridView) view.findViewById(R.id.sgv_gridview);
                            sgv_gridview.setAdapter(new ScrollWrokAdapter(getActivity(), workBeen));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
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
