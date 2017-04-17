package com.daoran.newfactory.onefactory.fragment;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.CommoditySqlActivity;
import com.daoran.newfactory.onefactory.activity.work.SignDetailActivity;
import com.daoran.newfactory.onefactory.activity.work.DebugGaodeActivity;
import com.daoran.newfactory.onefactory.activity.work.ProductionActivity;
import com.daoran.newfactory.onefactory.activity.work.SignActivity;
import com.daoran.newfactory.onefactory.activity.work.SqlCarApplyActivity;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;


/**
 * 工作模块
 * Created by lizhipeng on 2017/3/22.
 */

public class WorkFragment extends Fragment implements View.OnClickListener {

    Activity mactivity;
    private Toolbar tbarWrok;
    private View view;
    private TextView tvOpenCarDetail, tvSign, tvSqlSign, tvBusRoute, tvProduction, tvSqlgoods;
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
        getViews();
        initViews();
        setListener();
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
    }

    private void initViews() {
        tbarWrok.setTitle("");
        tvOpenCarDetail.setOnClickListener(this);
        tvSqlSign.setOnClickListener(this);
        tvProduction.setOnClickListener(this);
        tvSqlgoods.setOnClickListener(this);
        tvBusRoute.setOnClickListener(this);
        tvSign.setOnClickListener(this);
        Bundle bundle = getActivity().getIntent().getExtras();
        String name = bundle.getString("u_name");
        spUtils.put(getActivity(),"u_name",name);
        idworkname.setText(name);
    }

    private void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvOpenCarDetail://用车单
                getActivity().startActivity(new Intent(getActivity(), SqlCarApplyActivity.class));
                break;
            case R.id.tvSqlSign://查询签到详情
                getActivity().startActivity(new Intent(getActivity(), SignDetailActivity.class));
                break;
            case R.id.tvProduction://生产日报
                getActivity().startActivity(new Intent(getActivity(), ProductionActivity.class));
                break;
            case R.id.tvSqlgoods://查货跟踪单
                getActivity().startActivity(new Intent(getActivity(), CommoditySqlActivity.class));
                break;
            case R.id.tvBusRoute://公交路线
                getActivity().startActivity(new Intent(getActivity(), DebugGaodeActivity.class));
                break;
            case R.id.tvSign://外勤签到
                getActivity().startActivity(new Intent(getActivity(), SignActivity.class));
                break;
        }

    }
}
