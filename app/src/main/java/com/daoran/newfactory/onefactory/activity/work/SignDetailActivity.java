package com.daoran.newfactory.onefactory.activity.work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.SignDetailAdapter;
import com.daoran.newfactory.onefactory.base.BaseListActivity;
import com.daoran.newfactory.onefactory.bean.SignDetailBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.JsonSyntaxException;
import com.i5tong.epubreaderlib.view.pulltorefresh.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 签到查询
 * Created by lizhipeng on 2017/3/29.
 */

public class SignDetailActivity extends BaseListActivity
        implements View.OnClickListener {
    private NoscrollListView mLeft;
    private LeftAdapter mLeftAdapter;
    private SignDetailAdapter detailAdapter;
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;

    private List<SignDetailBean.DataBean> mListData = new ArrayList<SignDetailBean.DataBean>();
    private SignDetailBean signBean;
    private ImageView ivSiganSqlDetail;

    public boolean isRefreshing = false;
    public int defaultPageIndex = 0;
    public int pageIndex = defaultPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_activity_detail);
        initView();
        getViews();

    }

    private void initView() {
        ivSiganSqlDetail = (ImageView) findViewById(R.id.ivSiganSqlDetail);
        mLeft = (NoscrollListView) findViewById(R.id.lv_left);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);

        mLeftAdapter = new LeftAdapter();
        mLeft.setAdapter(mLeftAdapter);
        init(R.id.lv_data);
        setPullToRefreshListViewModeBOTH();
        getData();
    }

    private void getViews() {
        ivSiganSqlDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSiganSqlDetail:
                finish();
                break;
        }
    }

    /**
     * 签到查询服务器
     */
    private void setSignDetail() {
//        String str = HttpUrl.debugoneUrl + "OutRegister/BindSearchAPPPage/";
//        if (NetWork.isNetWorkAvailable(this)) {
//            ResponseDialog.showLoading(this, "正在查询，请稍后");
//            OkHttpUtils
//                    .post()
//                    .url(str)
//                    .addParams("pageNum", "1")
//                    .addParams("pageSize", "20")
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//                            e.printStackTrace();
//                        }
//
//                        @Override
//                        public void onResponse(String response, int id) {
//                            System.out.print(response);
//                            try {
//                                Gson gson = new Gson();
//                                signBean = gson.fromJson(response, SignDetailBean.class);
//                                mListData = signBean.getData();
//                                detailAdapter = new SignDetailAdapter(mListData, SignDetailActivity.this);
//                                waterDropListView.setAdapter(detailAdapter);
////                                waterDropListView.setWaterDropListViewListener(SignDetailActivity.this);
//                                waterDropListView.setPullLoadEnable(true);
//                            } catch (JsonSyntaxException e) {
//                                ToastUtils.ShowToastMessage("获取列表失败,请重新再试", SignDetailActivity.this);
//                                ResponseDialog.closeLoading();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                ResponseDialog.closeLoading();
//                            }
//                        }
//                    });
//        } else {
//            ToastUtils.ShowToastMessage(R.string.disNetworking, SignDetailActivity.this);
//        }
    }

    @Override
    public BaseAdapter setListAdapter() {
        return new SignDetailAdapter(mListData, this);
    }

    @Override
    public void getData(int pageIndex) {
        String str = HttpUrl.debugoneUrl + "OutRegister/BindSearchAPPPage/";
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this, "正在查询，请稍后");
            OkHttpUtils
                    .post()
                    .url(str)
                    .addParams("pageNum", "1")
                    .addParams("pageSize", pageIndex * 10 + "")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.print(response);
                            try {
                                Gson gson = new Gson();
                                signBean = gson.fromJson(response, SignDetailBean.class);
                                mListData = signBean.getData();
                                setListDataRelpy(mListData);
                            } catch (JsonSyntaxException e) {
                                ToastUtils.ShowToastMessage("获取列表失败,请重新再试", SignDetailActivity.this);
                                ResponseDialog.closeLoading();
                            } catch (Exception e) {
                                e.printStackTrace();
                                setListDataRelpy(new ArrayList());
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignDetailActivity.this);
        }
    }

    @Override
    public void onListItemClick(Object o) {

    }

    @Override
    public void onListItemLongClick(Object o) {

    }

    /**
     * 左列行号适配
     */
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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(SignDetailActivity.this).inflate(R.layout.debug_item_left, null);
                holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvLeft.setText(String.valueOf(position + 1));

            return convertView;
        }

        class ViewHolder {
            TextView tvLeft;
        }
    }

}
