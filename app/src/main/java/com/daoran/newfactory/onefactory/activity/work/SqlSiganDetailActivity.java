package com.daoran.newfactory.onefactory.activity.work;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.view.NoscrollListView;
import com.daoran.newfactory.onefactory.view.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * 签到查询主页
 * Created by lizhipeng on 2017/3/28.
 */

public class SqlSiganDetailActivity extends BaseFrangmentActivity implements View.OnClickListener {

    private NoscrollListView mleft;
    private NoscrollListView mData;
    private SyncHorizontalScrollView mHeaderHorizonal;
    private SyncHorizontalScrollView mDataHorizonal;
    private List<String> mListData;

    private LeftAdapter leftAdapter;
    private DataAdatper dataAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigandetail);
        getViews();
        initViews();
    }

    private void getViews() {
        mleft = (NoscrollListView) findViewById(R.id.lvLeft);
        mData = (NoscrollListView) findViewById(R.id.lvData);
        mHeaderHorizonal = (SyncHorizontalScrollView) findViewById(R.id.headerHorizontal);
        mDataHorizonal = (SyncHorizontalScrollView) findViewById(R.id.dataHorizontal);
        mDataHorizonal.setSrollView(mHeaderHorizonal);
        mHeaderHorizonal.setSrollView(mDataHorizonal);
    }

    private void initViews() {
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
        leftAdapter = new LeftAdapter();
        mleft.setAdapter(leftAdapter);

        dataAdatper = new DataAdatper();
        mData.setAdapter(dataAdatper);
    }

    class DataAdatper extends BaseAdapter {

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
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(SqlSiganDetailActivity.this).inflate(R.layout.item_sign_detail, null);
                viewHolder.tvData = (TextView) convertView.findViewById(R.id.tvDatat);
                viewHolder.linContent = (LinearLayout) convertView.findViewById(R.id.llContent);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        class ViewHolder {
            TextView tvData;
            LinearLayout linContent;
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
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(SqlSiganDetailActivity.this).inflate(R.layout.item_numbervertical, null);
                viewHolder.tvLeft = (TextView) convertView.findViewById(R.id.tvLeftSignNumber);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvLeft.setText("第" + position + "行");
            return convertView;
        }

        class ViewHolder {
            TextView tvLeft;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
