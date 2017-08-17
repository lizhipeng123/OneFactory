package com.daoran.newfactory.onefactory.activity.side;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.SideAdatper;
import com.daoran.newfactory.onefactory.fragment.SideFragment;
import com.daoran.newfactory.onefactory.bean.PersionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类菜单
 * Created by lizhipeng on 2017/8/17.
 */

public class SideActivity extends FragmentActivity
        implements AdapterView.OnItemClickListener, View.OnClickListener {
    List<PersionInfo> infoList = new ArrayList<PersionInfo>();
    private ListView listView;
    private SideAdatper sideAdatper;
    private SideFragment sideFragment;
    private PersionInfo persionInfo;

    private ImageView ivBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);
        getView();
        setViews();
    }

    private void getView() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
    }

    private void setViews() {
        for (int i = 0; i < 10; i++) {
            persionInfo = new PersionInfo("常用分类" + i);
            infoList.add(persionInfo);
        }
        listView = (ListView) findViewById(R.id.listview);
        infoList.get(0).setChick(true);
        sideAdatper = new SideAdatper(this, infoList);
        listView.setAdapter(sideAdatper);
        listView.setOnItemClickListener(this);
        sideFragment = new SideFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, sideFragment);

        Bundle mbundle = new Bundle();
        mbundle.putSerializable("info", infoList.get(0));
        sideFragment.setArguments(mbundle);
        fragmentTransaction.commit();
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        persionInfo = infoList.get(position);
        for (int i = 0; i < infoList.size(); i++) {
            if (infoList.get(i).getNameString().equals(persionInfo.getNameString())) {
                infoList.get(i).setChick(true);
            } else {
                infoList.get(i).setChick(false);
            }
        }
        sideAdatper.notifyDataSetChanged();
        sideFragment = new SideFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, sideFragment);
        Bundle bundle = new Bundle();
        bundle.putSerializable("info", infoList.get(position));
        sideFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
        }
    }
}
