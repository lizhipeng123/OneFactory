package com.daoran.newfactory.onefactory.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.login.LoginDebugActivity;


/**
 * 设置模块
 * Created by lizhipeng on 2017/3/22.
 */

public class SetupFragment extends Fragment implements View.OnClickListener {

    Activity mactivity;
    private Toolbar tbarSetup;
    private View view;
    private RelativeLayout rlAgainLogin,rlEditionUpdate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mactivity = getActivity();
        view = inflater.inflate(R.layout.fragment_setup, container, false);
        tbarSetup = (Toolbar) view.findViewById(R.id.tbarSetup);
        tbarSetup.setTitle("");
        getViews();
        initViews();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getViews() {
        rlAgainLogin = (RelativeLayout) view.findViewById(R.id.rlAgainLogin);
        rlEditionUpdate = (RelativeLayout) view.findViewById(R.id.rlEditionUpdate);
    }

    private void initViews(){
        rlAgainLogin.setOnClickListener(this);
        rlEditionUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlAgainLogin:
                startActivity(new Intent(getActivity(), LoginDebugActivity.class));
                break;
            case R.id.rlEditionUpdate:

                break;
        }
    }
}
