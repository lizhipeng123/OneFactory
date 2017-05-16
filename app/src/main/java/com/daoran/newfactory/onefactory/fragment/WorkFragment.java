package com.daoran.newfactory.onefactory.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ScrollWrokAdapter;
import com.daoran.newfactory.onefactory.bean.WorkBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.view.ScrollGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 工作模块
 * Created by lizhipeng on 2017/3/22.
 */

public class WorkFragment extends Fragment {
    Activity mactivity;
    private Toolbar tbarWrok;
    private View view;
    private LinearLayout llOpenCarDetail, llOpenSign, llOPenSqlSign, llOpenBusRoute, llProduction, llSqlgoods;
    private TextView tvOpenCarDetail, tvSign, tvSqlSign, tvBusRoute, tvProduction, tvSqlgoods;
    private ImageView ivopenCarDetail, ivOpenSign, tvOpenSqlSign, ivOpenBusRoute, ivProduction, ivSqlgoods;
    private TextView idworkname;
    private SharedPreferences sp;
    private SPUtils spUtils;

    private List<WorkBean> workBeen = new ArrayList<WorkBean>();
    private ScrollGridView sgv_gridview;
    private String workitemview;
    private String sl;

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
        return view;
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        tbarWrok = (Toolbar) view.findViewById(R.id.tbarWrok);
        tvOpenCarDetail = (TextView) view.findViewById(R.id.tvOpenCarDetail);
        idworkname = (TextView) view.findViewById(R.id.idworkname);
        ivopenCarDetail = (ImageView) view.findViewById(R.id.ivopenCarDetail);
        llOpenCarDetail = (LinearLayout) view.findViewById(R.id.llOpenCarDetail);
    }

    /**
     * 操作控件
     */
    private void initViews() {
        tbarWrok.setTitle("");
        Bundle bundle = getActivity().getIntent().getExtras();
        String name = bundle.getString("u_name");
        spUtils.put(mactivity, "u_name", name);
        idworkname.setText(name);
        spUtils.put(mactivity, "usernamerecoder", name);
    }

    /**
     * 查询角色相关菜单
     */
    private void setPhoneMenu() {
        sp = mactivity.getSharedPreferences("userInfo", 0);
        String name = sp.getString("username", "");
        String strmenu = HttpUrl.debugoneUrl + "login/getphonemenu/" + name;
        NetUtil.getAsyncHttpClient().get(strmenu, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                String ress = content.replace("\\", "");
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

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }
}
