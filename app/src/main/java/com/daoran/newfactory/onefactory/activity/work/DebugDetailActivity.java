package com.daoran.newfactory.onefactory.activity.work;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.SignDetailAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.base.BaseListActivity;
import com.daoran.newfactory.onefactory.bean.SignDebugBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.NoscrollListView;
import com.daoran.newfactory.onefactory.view.SyncHorizontalScrollView;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.JsonSyntaxException;
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

public class DebugDetailActivity extends BaseFrangmentActivity implements View.OnClickListener {
    private NoscrollListView mLeft;
    private LeftAdapter mLeftAdapter;

    private NoscrollListView mData;
    private SignDetailAdapter detailAdapter;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;

    private List<SignDebugBean.DataBean> mListData = new ArrayList<SignDebugBean.DataBean>();
    private SignDebugBean signBean;
    private ImageView ivSiganSqlDetail;

    public boolean isRefreshing = false;
    public int defaultPageIndex = 0;
    public int pageIndex = defaultPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ResponseDialog.showLoading(this, "请稍后");
        setContentView(R.layout.debug_activity_detail);
        initView();
        getViews();
        setSignDetail();
    }

    private void initView() {
        ivSiganSqlDetail = (ImageView) findViewById(R.id.ivSiganSqlDetail);
        mLeft = (NoscrollListView) findViewById(R.id.lv_left);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);

        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);

        mLeftAdapter = new LeftAdapter();
        mLeft.setAdapter(mLeftAdapter);
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
        String str = HttpUrl.debugoneUrl + "OutRegister/BindSearchAPPPage/";
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this,"正在查询，请稍后");
            OkHttpUtils
                    .post()
                    .url(str)
                    .addParams("pageNum", "1")
                    .addParams("pageSize", "")
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
                                signBean = gson.fromJson(response, SignDebugBean.class);
                                mListData = signBean.getData();
                                detailAdapter = new SignDetailAdapter(mListData, DebugDetailActivity.this);
                                mData.setAdapter(detailAdapter);
                            } catch (JsonSyntaxException e) {
                                ToastUtils.ShowToastMessage("获取列表失败,请重新再试", DebugDetailActivity.this);
                                ResponseDialog.closeLoading();
                            } catch (Exception e) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, DebugDetailActivity.this);
        }
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
                convertView = LayoutInflater.from(DebugDetailActivity.this).inflate(R.layout.debug_item_left, null);
                holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvLeft.setText(String.valueOf(position+1));

            return convertView;
        }

        class ViewHolder {
            TextView tvLeft;
        }
    }

}
