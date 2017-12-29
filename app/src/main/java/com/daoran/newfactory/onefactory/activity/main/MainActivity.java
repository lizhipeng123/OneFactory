package com.daoran.newfactory.onefactory.activity.main;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
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
import com.daoran.newfactory.onefactory.bean.settingbean.TabHostBean;
import com.daoran.newfactory.onefactory.fragment.InformationFragment;
import com.daoran.newfactory.onefactory.fragment.OfficeFragment;
import com.daoran.newfactory.onefactory.fragment.SetupFragment;
import com.daoran.newfactory.onefactory.fragment.WorkFragment;
import com.daoran.newfactory.onefactory.util.receiver.ExampleUtil;
import com.daoran.newfactory.onefactory.view.fragment.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 主框架
 */
public class MainActivity extends BaseFrangmentActivity {
    private FragmentTabHost mTabHost;//底部切换模块
    private LayoutInflater mInflater;//用来查找res/layout下的xml布局,并且实例化
    private AlertDialog noticeDialog;//退出弹窗
    private List<TabHostBean> mTabs = new ArrayList<>(4);//便签组
    //    private DrawerFragment drawerFragment;
    //    private Fragment navigation_drawer;
    String idd;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//加载主页面
        JPushInterface.setDebugMode(true);//设置开启日志，发布时关闭日志
        JPushInterface.init(this);//初始化JPush
        id = getIntent().getIntExtra(idd, 0);//接收添加账号传过来的id以便刷新本页
        getViews();//调用实例化控件方法
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //虚拟键盘按回退退出提示
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("系统提示");//dialog标题
            builder.setMessage("确定要退出吗");//dialog内容
            //点击确定按钮退出程序
            builder.setPositiveButton("确定"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);//终止程序（系统方法，强制性）
                            dialog.dismiss();//关闭dialog
                        }
                    });
            //点击取消按钮则关闭dialog
            builder.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            noticeDialog = builder.create();//调用create()方法创建AlertDialog对象
            noticeDialog.setCanceledOnTouchOutside(false);//点击屏幕dialog不会消失，只有点击物理返回键才会消失
            noticeDialog.show();//使回退时，弹窗可以正常运行
        }
        return false;
    }

    /*操作控件*/
    private void getViews() {
        //添加侧滑页面
//        drawerFragment = (DrawerFragment) getSupportFragmentManager().
//                findFragmentById(R.id.navigation_drawer);
//        drawerFragment.setUp(R.id.navigation_drawer,
//                (DrawerLayout) findViewById(R.id.activity_main));
        /*bean中添加tab中要显示的文字，图片，内容*/
        TabHostBean tab_work = new TabHostBean(R.string.work,
                R.drawable.selector_icon_work, WorkFragment.class);
        TabHostBean tab_office = new TabHostBean(R.string.office,
                R.drawable.selector_icon_message, OfficeFragment.class);
        TabHostBean tab_information = new TabHostBean(R.string.information,
                R.drawable.selector_icon_people, InformationFragment.class);
        TabHostBean tab_setup = new TabHostBean(R.string.setup,
                R.drawable.selector_icon_my, SetupFragment.class);
        //list中绑定tabhost相关联的名称，图片，页面
        mTabs.add(tab_work);//绑定工作页面相关信息
        mTabs.add(tab_office);//绑定在办页面相关信息
        mTabs.add(tab_information);//绑定资讯页面相关信息
        mTabs.add(tab_setup);//绑定设置页面相关信息

        mInflater = LayoutInflater.from(this);//实例化当前页面的xml布局
        mTabHost = (FragmentTabHost)
                findViewById(android.R.id.tabhost);//实例化tabhost对象
        mTabHost.setup(this, getSupportFragmentManager(),
                R.id.flLayoutTabcontent);//设置替换哪个布局
        for (TabHostBean bean : mTabs) {//为每一个tab按钮设置图标，文字，内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(bean.getTitle()));
            tabSpec.setIndicator(getTabItemView(bean));//添加图片
            mTabHost.addTab(tabSpec, bean.getFragment(), null);//添加页面
        }
        //设置tab边框颜色
        mTabHost.getTabWidget().setDividerDrawable(R.color.transparent);
        if (id == 1) {//刷新当前页面
            Fragment workFragment = new WorkFragment();//实例化工作页面，并且刷新
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.flLayoutTabcontent, workFragment);//在本页面中刷新workFragment
        }
    }

//    public void setChantWrokFragment() {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
////        ft.replace(R.id.mainLayout,WorkFragment.class);
//        ft.addToBackStack(null);
//        ft.commit();
//    }

    /*加载底部菜单项*/
    private View getTabItemView(TabHostBean bean) {
        View view = mInflater.inflate(R.layout.tabitem_home, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageResource(bean.getIcon());
        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        textView.setText(bean.getTitle());
        return view;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}