package com.daoran.newfactory.onefactory.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.settingbean.PersionInfo;

/**
 * Created by lizhipeng on 2017/8/17.
 */

public class SideFragment extends Fragment {
    Activity mActivity;
    View view;
    private TextView tv_title;
    private PersionInfo persionInfo;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = getActivity();
        view = inflater.inflate(R.layout.fragment_side, container,false);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        persionInfo = (PersionInfo) getArguments().getSerializable("info");
        tv_title.setText(persionInfo.getNameString());
        return view;
    }
}
