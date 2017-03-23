package com.daoran.newfactory.onefactory.fragment;


import android.app.Activity;
import android.content.Context;
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


/**
 * Created by lizhipeng on 2017/3/22.
 */

public class WorkFragment extends Fragment {

    Activity mactivity;
    private Toolbar tbarWrok;
    private View view;
    private int tvWidthNumber;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViews();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mactivity = getActivity();
        view = inflater.inflate(R.layout.fragment_work, container, false);
        tbarWrok = (Toolbar) view.findViewById(R.id.tbarWrok);
        tbarWrok.setTitle("");

        TextView tvSign = (TextView) view.findViewById(R.id.tvSign);
        int spec = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        tvSign.measure(spec,spec);
        tvWidthNumber = tvSign.getMeasuredWidth();
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tvSign.getLayoutParams();
        lp.height = tvWidthNumber;
        tvSign.setLayoutParams(lp);

        getViews();
        return view;
    }

    private void getViews(){

    }
}
