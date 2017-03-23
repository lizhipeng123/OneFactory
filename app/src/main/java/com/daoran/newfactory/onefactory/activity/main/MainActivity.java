package com.daoran.newfactory.onefactory.activity.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.TabHostBean;
import com.daoran.newfactory.onefactory.fragment.InformationFragment;
import com.daoran.newfactory.onefactory.fragment.OfficeFragment;
import com.daoran.newfactory.onefactory.fragment.SetupFragment;
import com.daoran.newfactory.onefactory.fragment.WorkFragment;
import com.daoran.newfactory.onefactory.view.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFrangmentActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<TabHostBean> mTabs = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
    }

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
        ImageView imageView = (ImageView)view.findViewById(R.id.tab_icon);
        imageView.setImageResource(bean.getIcon());

        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        textView.setText(bean.getTitle());
        return view;
    }
}
