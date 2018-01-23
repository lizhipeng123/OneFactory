package com.daoran.newfactory.onefactory.activity.work.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.settingadapter.WorkPwSwitchAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.loginbean.WorkPwSwitchBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2018/1/23
 * 编写人：lizhipeng
 * 功能描述：账号管理
 */

public class UserManagerActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private SPUtils spUtils;
    private SharedPreferences sp;
    private ImageView ivBack;//返回按键
    private NoscrollListView nolist_manager;//已保存的用户列表
    private RelativeLayout rl_add_user;//添加账号
    private WorkPwSwitchBean workPwSwitchBean;//保存的实体数据
    private List<WorkPwSwitchBean.Data> switchBeendatalist
            = new ArrayList<WorkPwSwitchBean.Data>();//用户实体集合
    private WorkPwSwitchAdapter switchAdapter;//用户list显示列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermanager);
        getView();
        initView();
    }

    /*实例化控件*/
    private void getView() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        nolist_manager = (NoscrollListView) findViewById(R.id.nolist_manager);
        rl_add_user = (RelativeLayout) findViewById(R.id.rl_add_user);
    }

    private void initView() {
        ivBack.setOnClickListener(this);
        rl_add_user.setOnClickListener(this);
        sp = getSharedPreferences("my_sp", 0);
        String listwork = sp.getString("workbeenlist", "");
        workPwSwitchBean = new Gson().fromJson(listwork, WorkPwSwitchBean.class);
        if (listwork.equals("")) {
        } else {
            switchBeendatalist = workPwSwitchBean.getDatas();
            for (int i = 0; i < switchBeendatalist.size(); i++) {
                String struname = sp.getString("name", "");
                String uname = switchBeendatalist.get(i).getU_name();
                if (struname.equals(uname) && switchBeendatalist.size() > 1) {
                    switchBeendatalist.remove(switchBeendatalist.get(i));
                }
            }
            switchAdapter = new WorkPwSwitchAdapter(getApplicationContext(), switchBeendatalist);
            nolist_manager.setAdapter(switchAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回*/
            case R.id.ivBack:
                finish();
                break;
            /*添加账号*/
            case R.id.rl_add_user:
                startActivity(new Intent(this, WorkPwSwitchActivity.class));
                break;
        }
    }
}
