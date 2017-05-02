package com.daoran.newfactory.onefactory.activity.work.car;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.SqlCarApplyAdapter;
import com.daoran.newfactory.onefactory.base.BaseListActivity;
import com.daoran.newfactory.onefactory.bean.SqlCarApplyBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.RefreshLayout;
import com.daoran.newfactory.onefactory.view.dialog.ContentDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 出车单
 * Created by lizhipeng on 2017/4/19.
 */

public class SqlcarApplyActivity extends BaseListActivity implements View.OnClickListener {
    private ImageButton ibSqlCarDialog;
    private Button btnSqlopen;
    private RefreshLayout swipeLayout;
    private ImageView ivBack, ivSearch;
    private TextView tvTbarTitle;
    private TextView tvInitialDate;
    private ContentDialog dialog;

    private SqlCarApplyBean.DataBean dataBean;
    private SharedPreferences sp;
    private SPUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_carapply);
        getViews();
        init(R.id.listview);
        setPullToRefreshListViewModeBOTH();
        getData();
    }

    private void getViews() {
        btnSqlopen = (Button) findViewById(R.id.btnSqlopen);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        tvInitialDate = (TextView) findViewById(R.id.tvInitialDate);
        ivBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
    }

    @Override
    public BaseAdapter setListAdapter() {
        return new SqlCarApplyAdapter(SqlcarApplyActivity.this);
    }

    @Override
    public void getData(int pageIndex) {
        String sqlcar = HttpUrl.debugoneUrl + "UCarsApply/UCarsApplySearch/";
        sp = SqlcarApplyActivity.this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String datetime = sp.getString("datetime", "");
        String endtime = sp.getString("endtime", "");
        String spinnerPosition = sp.getString("spinnerPosition", "");
        System.out.print(datetime);
        if (NetWork.isNetWorkAvailable(this)) {
            OkHttpUtils
                    .post()
                    .url(sqlcar)
                    .addParams("start", datetime)
                    .addParams("endtime", endtime)
                    .addParams("title", spinnerPosition)
                    .addParams("pageNum", pageIndex + "0")
                    .addParams("pageSize", "10")
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
                            ToastUtils.ShowToastMessage("获取失败，请稍后再试", SqlcarApplyActivity.this);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                System.out.print(response);
                                SqlCarApplyBean carApplyBean = new Gson().fromJson(response, SqlCarApplyBean.class);
                                setListData(carApplyBean.getData());
                            } catch (JsonSyntaxException e) {
                                setListData(new ArrayList());
                            } catch (Exception e) {
                                setListData(new ArrayList());
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(getString(R.string.noHttp), SqlcarApplyActivity.this);
        }
    }

    @Override
    public void onListItemClick(Object o) {
        System.out.print(o);
        dataBean = (SqlCarApplyBean.DataBean) o;
        startActivity(new Intent(SqlcarApplyActivity.this,CarapplyActivity.class)
        .putExtra("id",dataBean.getId()));
    }

    @Override
    public void onListItemLongClick(Object o) {

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
                    SqlcarApplyActivity.this.getData();
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
