package com.daoran.newfactory.onefactory.activity.main;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.GetPhoneMenuBean;
import com.daoran.newfactory.onefactory.bean.TabHostBean;
import com.daoran.newfactory.onefactory.fragment.InformationFragment;
import com.daoran.newfactory.onefactory.fragment.OfficeFragment;
import com.daoran.newfactory.onefactory.fragment.SetupFragment;
import com.daoran.newfactory.onefactory.fragment.WorkFragment;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.view.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

/**
 * 主框架
 */
public class MainActivity extends BaseFrangmentActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<TabHostBean> mTabs = new ArrayList<>(4);
    private SharedPreferences sp;
    private SPUtils spUtils;
    private GetPhoneMenuBean getPhoneMenuBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();
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
                    } else {// android2.1
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

    private void getViews() {
        TabHostBean tab_work = new TabHostBean(R.string.work, R.drawable.selector_icon_work, WorkFragment.class);
        TabHostBean tab_office = new TabHostBean(R.string.office, R.drawable.selector_icon_message, OfficeFragment.class);
        TabHostBean tab_information = new TabHostBean(R.string.information, R.drawable.selector_icon_people, InformationFragment.class);
        TabHostBean tab_setup = new TabHostBean(R.string.setup, R.drawable.selector_icon_my, SetupFragment.class);
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
    }

    private View getTabItemView(TabHostBean bean) {
        View view = mInflater.inflate(R.layout.tabitem_home, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageResource(bean.getIcon());

        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        textView.setText(bean.getTitle());
        return view;
    }
}
