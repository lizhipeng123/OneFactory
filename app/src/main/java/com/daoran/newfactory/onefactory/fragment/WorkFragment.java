package com.daoran.newfactory.onefactory.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.CommoditySqlActivity;
import com.daoran.newfactory.onefactory.activity.work.DebugDetailActivity;
import com.daoran.newfactory.onefactory.activity.work.ProductionActivity;
import com.daoran.newfactory.onefactory.activity.work.SqlCarApplyActivity;
import com.daoran.newfactory.onefactory.activity.work.SqlSiganDetailActivity;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;


/**
 * Created by lizhipeng on 2017/3/22.
 */

public class WorkFragment extends Fragment implements View.OnClickListener {

    Activity mactivity;
    private Toolbar tbarWrok;
    private View view;
    private int tvWidthNumber;
    private TextView tvOpenCarDetail, tvSign, tvSqlSign, tvBusRoute, tvProduction, tvSqlgoods;

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
    }

    private void initViews() {
        tbarWrok.setTitle("");
        tvOpenCarDetail.setOnClickListener(this);
        tvSqlSign.setOnClickListener(this);
        tvProduction.setOnClickListener(this);
        tvSqlgoods.setOnClickListener(this);
    }

    private void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvOpenCarDetail:
                ResponseDialog.showLoading(getActivity(), "请稍后");
                getActivity().startActivity(new Intent(getActivity(), SqlCarApplyActivity.class));
                break;
            case R.id.tvSqlSign:
                ResponseDialog.showLoading(getActivity(), "请稍后");
                getActivity().startActivity(new Intent(getActivity(), DebugDetailActivity.class));
                break;
            case R.id.tvProduction:
                ResponseDialog.showLoading(getActivity(), "请稍后");
                getActivity().startActivity(new Intent(getActivity(), ProductionActivity.class));
                break;
            case R.id.tvSqlgoods:
                getActivity().startActivity(new Intent(getActivity(), CommoditySqlActivity.class));
                break;
        }

    }
}
