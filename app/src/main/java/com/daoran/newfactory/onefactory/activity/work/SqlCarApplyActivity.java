package com.daoran.newfactory.onefactory.activity.work;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.login.LoginActivity;
import com.daoran.newfactory.onefactory.adapter.RecycleAdatper;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.SqlCarApplyBean;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.RefreshLayout;
import com.daoran.newfactory.onefactory.view.dialog.ContentDialog;

import com.daoran.newfactory.onefactory.view.RefreshLayout.OnLoadListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by lizhipeng on 2017/3/24.
 */

public class SqlCarApplyActivity extends BaseFrangmentActivity implements OnRefreshListener, OnLoadListener, View.OnClickListener {


    private ImageButton ibSqlCarDialog;
    private Button btnSqlopen;
    private RefreshLayout swipeLayout;
    private ListView listView;
    private RecycleAdatper adatper;
    private View header;
    private List<SqlCarApplyBean> actAllList;
    private SqlCarApplyBean bean;
    private boolean isFirstIn = true;//是否是第一次加载
    private ImageView ivBack, ivSearch;
    private TextView tvTbarTitle;
    private TextView tvInitialDate;
    private ContentDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_carapply);
        getViews();
        setDatas();
    }


    private void getViews() {
        btnSqlopen = (Button) findViewById(R.id.btnSqlopen);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        tvInitialDate = (TextView) findViewById(R.id.tvInitialDate);

        ivBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);


    }

    private void setDatas() {
        String sqlcar = HttpUrl.Url + HttpUrl.sqlCarApply;
        if (NetWork.isNetWorkAvailable(this)) {

            OkHttpUtils.post().url(sqlcar)
                    .build()
                    .execute(new StringCallback() {

                        @Override
                        public void onBefore(Request request, int id) {
                            super.onBefore(request, id);
                        }

                        @Override
                        public void onAfter(int id) {
                            super.onAfter(id);
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            ToastUtils.ShowToastMessage("获取失败，请稍后再试", SqlCarApplyActivity.this);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            SqlCarApplyBean bean = new Gson().fromJson(response, SqlCarApplyBean.class);

                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(getString(R.string.noHttp), SqlCarApplyActivity.this);
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initViews() {
        header = getLayoutInflater().inflate(R.layout.item_load_view, null);
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoad() {

    }

    public void showEditDialog(View view) {
        dialog =
                new ContentDialog(this, R.style.dialogstyle, onClickListener, onCancleListener);
        dialog.show();
    }

    private View.OnClickListener onCancleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCancle:
                    dialog.dismiss();
                    break;
            }
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
                    dialog.dismiss();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivSearch:
                showEditDialog(v);
                break;
        }
    }
}
