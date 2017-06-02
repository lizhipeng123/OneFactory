package com.daoran.newfactory.onefactory.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;

/**
 * 在办事项模块
 * Created by lizhipeng on 2017/3/22.
 */

public class OfficeFragment extends Fragment {
    Activity mactivity;
    private Toolbar tbarOffice;
    private TextView tv_visibi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mactivity = getActivity();
        View view = inflater.inflate(R.layout.fragment_office, container, false);
        tbarOffice = (Toolbar) view.findViewById(R.id.tbarOffice);
        tv_visibi = (TextView) view.findViewById(R.id.tv_visibi);
        tbarOffice.setTitle("");
        tv_visibi.setText("功能正在完善");
        getViews();
        initViews();
        return view;
    }

    private void getViews(){

    }

    private void initViews(){

    }
}
