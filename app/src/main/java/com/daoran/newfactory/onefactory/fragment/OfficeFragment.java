package com.daoran.newfactory.onefactory.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoran.newfactory.onefactory.R;


/**
 * Created by lizhipeng on 2017/3/22.
 */

public class OfficeFragment extends Fragment {

    Activity mactivity;
    private Toolbar tbarOffice;

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
        tbarOffice.setTitle("");
        return view;
    }

    private void getViews(){}
}
