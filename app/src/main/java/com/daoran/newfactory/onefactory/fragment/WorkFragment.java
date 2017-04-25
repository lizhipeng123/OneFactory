package com.daoran.newfactory.onefactory.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.main.MainActivity;
import com.daoran.newfactory.onefactory.activity.work.CommoditySqlActivity;
import com.daoran.newfactory.onefactory.activity.work.SqlcarApplyActivity;
import com.daoran.newfactory.onefactory.activity.work.SignDetailActivity;
import com.daoran.newfactory.onefactory.activity.work.DebugGaodeActivity;
import com.daoran.newfactory.onefactory.activity.work.ProductionActivity;
import com.daoran.newfactory.onefactory.activity.work.SignActivity;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.StringUtil;
import com.daoran.newfactory.onefactory.util.image.BitmapTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 工作模块
 * Created by lizhipeng on 2017/3/22.
 */

public class WorkFragment extends Fragment implements View.OnClickListener {

    Activity mactivity;
    private Toolbar tbarWrok;
    private View view;
    private LinearLayout llOpenCarDetail, llOpenSign, llOPenSqlSign, llOpenBusRoute, llProduction, llSqlgoods;
    private TextView tvOpenCarDetail, tvSign, tvSqlSign, tvBusRoute, tvProduction, tvSqlgoods;
    private ImageView ivopenCarDetail, ivOpenSign, tvOpenSqlSign, ivOpenBusRoute, ivProduction, ivSqlgoods;
    private TextView idworkname;
    private SharedPreferences sp;
    private SPUtils spUtils;

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
//        setPhoneMenu();
//        setListener();
        return view;
    }

    private void getViews() {
        tbarWrok = (Toolbar) view.findViewById(R.id.tbarWrok);
        tvOpenCarDetail = (TextView) view.findViewById(R.id.tvOpenCarDetail);

        tvSign = (TextView) view.findViewById(R.id.tvSign);
        tvSqlSign = (TextView) view.findViewById(R.id.tvSqlSign);
        tvBusRoute = (TextView) view.findViewById(R.id.tvBusRoute);
        tvProduction = (TextView) view.findViewById(R.id.tvProduction);
        tvSqlgoods = (TextView) view.findViewById(R.id.tvSqlgoods);
        idworkname = (TextView) view.findViewById(R.id.idworkname);
        ivopenCarDetail = (ImageView) view.findViewById(R.id.ivopenCarDetail);
        ivOpenSign = (ImageView) view.findViewById(R.id.ivOpenSign);
        tvOpenSqlSign = (ImageView) view.findViewById(R.id.tvOpenSqlSign);
        ivOpenBusRoute = (ImageView) view.findViewById(R.id.ivOpenBusRoute);
        ivProduction = (ImageView) view.findViewById(R.id.ivProduction);
        ivSqlgoods = (ImageView) view.findViewById(R.id.ivSqlgoods);
        llOpenCarDetail = (LinearLayout) view.findViewById(R.id.llOpenCarDetail);
        llOpenSign = (LinearLayout) view.findViewById(R.id.llOpenSign);
        llOPenSqlSign = (LinearLayout) view.findViewById(R.id.llOPenSqlSign);
        llOpenBusRoute = (LinearLayout) view.findViewById(R.id.llOpenBusRoute);
        llProduction = (LinearLayout) view.findViewById(R.id.llProduction);
        llSqlgoods = (LinearLayout) view.findViewById(R.id.llSqlgoods);
    }

    private void initViews() {
        tbarWrok.setTitle("");
        llOpenCarDetail.setOnClickListener(this);
        llOpenSign.setOnClickListener(this);
        llOPenSqlSign.setOnClickListener(this);
        llOpenBusRoute.setOnClickListener(this);
        llProduction.setOnClickListener(this);
        llSqlgoods.setOnClickListener(this);

        tvOpenCarDetail.setOnClickListener(this);
        tvSqlSign.setOnClickListener(this);
        tvProduction.setOnClickListener(this);
        tvSqlgoods.setOnClickListener(this);
        tvBusRoute.setOnClickListener(this);
        tvSign.setOnClickListener(this);
        Bundle bundle = getActivity().getIntent().getExtras();
        String name = bundle.getString("u_name");
        spUtils.put(getActivity(), "u_name", name);
        idworkname.setText(name);
    }

    private void setListener() {

    }

    private void setPhoneMenu() {
        sp = mactivity.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
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
                        //用车申请单
                        JSONObject objectcar = (JSONObject) temp.get(0);
                        String Stringcar = objectcar.getString("text");
                        tvOpenCarDetail.setVisibility(View.VISIBLE);
                        tvOpenCarDetail.setText(Stringcar);
                        //签到
                        JSONObject objectsign = (JSONObject) temp.get(1);
                        String Stringsign = objectsign.getString("text");
                        tvSign.setVisibility(View.VISIBLE);
                        tvSign.setText(Stringsign);
                        //签到查询
                        JSONObject objectsignsql = (JSONObject) temp.get(2);
                        String Stringsignsql = objectsignsql.getString("text");
                        tvSqlSign.setVisibility(View.VISIBLE);
                        tvSqlSign.setText(Stringsignsql + "表");
                        //公交路线
                        JSONObject objectbus = (JSONObject) temp.get(3);
                        String Stringbus = objectbus.getString("text");
                        tvBusRoute.setVisibility(View.VISIBLE);
                        tvBusRoute.setText(Stringbus);
                        //生产日报
                        JSONObject objectproduction = (JSONObject) temp.get(4);
                        String Stringproduction = objectproduction.getString("text");
                        tvProduction.setVisibility(View.VISIBLE);
                        tvProduction.setText(Stringproduction);
                        //查货跟踪
                        JSONObject objectCommo = (JSONObject) temp.get(5);
                        String StringCommo = objectCommo.getString("text");
                        tvSqlgoods.setVisibility(View.VISIBLE);
                        tvSqlgoods.setText(StringCommo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llOpenCarDetail://用车单
                getActivity().startActivity(new Intent(getActivity(), SqlcarApplyActivity.class));
                break;
            case R.id.llOPenSqlSign://查询签到详情
                getActivity().startActivity(new Intent(getActivity(), SignDetailActivity.class));
                break;
            case R.id.llProduction://生产日报
                getActivity().startActivity(new Intent(getActivity(), ProductionActivity.class));
                break;
            case R.id.llSqlgoods://查货跟踪单
                getActivity().startActivity(new Intent(getActivity(), CommoditySqlActivity.class));
                break;
            case R.id.llOpenBusRoute://公交路线
                getActivity().startActivity(new Intent(getActivity(), DebugGaodeActivity.class));
                break;
            case R.id.llOpenSign://外勤签到
                getActivity().startActivity(new Intent(getActivity(), SignActivity.class));
                break;
        }

    }
}
