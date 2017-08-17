package com.daoran.newfactory.onefactory.activity.main;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.TabHostBean;
import com.daoran.newfactory.onefactory.fragment.DrawerFragment;
import com.daoran.newfactory.onefactory.fragment.InformationFragment;
import com.daoran.newfactory.onefactory.fragment.OfficeFragment;
import com.daoran.newfactory.onefactory.fragment.SetupFragment;
import com.daoran.newfactory.onefactory.fragment.WorkFragment;
import com.daoran.newfactory.onefactory.view.fragment.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

/**
 * 主框架
 */
public class MainActivity extends BaseFrangmentActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private AlertDialog noticeDialog;
    private List<TabHostBean> mTabs = new ArrayList<>(4);
    private DrawerFragment drawerFragment;
    private Fragment navigation_drawer;
    String idd;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = getIntent().getIntExtra(idd, 0);
        getViews();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("系统提示");
            builder.setMessage("确定要退出吗");
            builder.setPositiveButton("确定"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int currentVersion = android.os.Build.VERSION.SDK_INT;
                            if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
                                Intent startMain = new Intent(Intent.ACTION_MAIN);
                                startMain.addCategory(Intent.CATEGORY_HOME);
                                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(startMain);
                                System.exit(0);
                            } else {//android2.1
                                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                                am.restartPackage(getPackageName());
                            }
                            dialog.dismiss();
                        }
                    });
            builder.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            noticeDialog = builder.create();
            noticeDialog.setCanceledOnTouchOutside(false);
            noticeDialog.show();
        }
        return false;
    }

    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    int currentVersion = android.os.Build.VERSION.SDK_INT;
                    if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        System.exit(0);
                    } else {//android2.1
                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        am.restartPackage(getPackageName());
                    }
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 操作控件
     */
    private void getViews() {
//        drawerFragment = (DrawerFragment) getSupportFragmentManager().
//                findFragmentById(R.id.navigation_drawer);
//        drawerFragment.setUp(R.id.navigation_drawer,
//                (DrawerLayout) findViewById(R.id.activity_main));
        TabHostBean tab_work = new TabHostBean(R.string.work,
                R.drawable.selector_icon_work, WorkFragment.class);
        TabHostBean tab_office = new TabHostBean(R.string.office,
                R.drawable.selector_icon_message, OfficeFragment.class);
        TabHostBean tab_information = new TabHostBean(R.string.information,
                R.drawable.selector_icon_people, InformationFragment.class);
        TabHostBean tab_setup = new TabHostBean(R.string.setup,
                R.drawable.selector_icon_my, SetupFragment.class);
        mTabs.add(tab_work);
        mTabs.add(tab_office);
        mTabs.add(tab_information);
        mTabs.add(tab_setup);

        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.flLayoutTabcontent);
        for (TabHostBean bean : mTabs) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(bean.getTitle()));
            tabSpec.setIndicator(getTabItemView(bean));
            mTabHost.addTab(tabSpec, bean.getFragment(), null);
        }
        mTabHost.getTabWidget().setDividerDrawable(R.color.transparent);
        if(id==1){
            Fragment workFragment = new WorkFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.flLayoutTabcontent,workFragment);
        }
    }

    public void setChantWrokFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.mainLayout,WorkFragment.class);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * 底部菜单项
     * @param bean
     * @return
     */
    private View getTabItemView(TabHostBean bean) {
        View view = mInflater.inflate(R.layout.tabitem_home, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageResource(bean.getIcon());
        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        textView.setText(bean.getTitle());
        return view;
    }
}