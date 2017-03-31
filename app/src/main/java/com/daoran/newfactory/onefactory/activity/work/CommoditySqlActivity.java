package com.daoran.newfactory.onefactory.activity.work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.view.NoscrollListView;
import com.daoran.newfactory.onefactory.view.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * 查货跟踪单
 * Created by lizhipeng on 2017/3/29.
 */

public class CommoditySqlActivity extends BaseFrangmentActivity implements View.OnClickListener {

    private NoscrollListView mLeft;
    private LeftAdapter mLeftAdapter;

    private NoscrollListView mData;
    private DataAdapter mDataAdapter;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack;
    private List<String> mListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);

        initView();
        getViews();
    }


    private void initView() {
        ivProductionBack = (ImageView) findViewById(R.id.ivCommoditySql);

        mLeft = (NoscrollListView) findViewById(R.id.lv_left);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);

        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);

        mListData = new ArrayList<>();
        mListData.add("1");
        mListData.add("2");
        mListData.add("3");
        mListData.add("4");
        mListData.add("5");
        mListData.add("6");
        mListData.add("7");
        mListData.add("8");
        mListData.add("9");
        mListData.add("10");
        mListData.add("11");
        mListData.add("12");
        mListData.add("13");

        mLeftAdapter = new LeftAdapter();
        mLeft.setAdapter(mLeftAdapter);

        mDataAdapter = new DataAdapter();
        mData.setAdapter(mDataAdapter);
    }

    private void getViews() {
        ivProductionBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivCommoditySql:
                finish();
                break;
        }
    }

    class LeftAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(CommoditySqlActivity.this).inflate(R.layout.debug_item_left, null);
                holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvLeft.setText("第" + position + "行");
            return convertView;
        }

        class ViewHolder {
            TextView tvLeft;
        }
    }

    class DataAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(CommoditySqlActivity.this).inflate(R.layout.item_commodity_data, null);
                holder.tvData = (TextView) convertView.findViewById(R.id.tv_data1);
                holder.linContent = (LinearLayout) convertView.findViewById(R.id.lin_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        class ViewHolder {
            TextView tvData;
            LinearLayout linContent;
        }
    }
}
