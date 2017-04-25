package com.daoran.newfactory.onefactory.activity.work;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.base.BaseListActivity;
import com.daoran.newfactory.onefactory.bean.SignDetailBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.SignContentDialog;
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

public class SignDetailActivity extends BaseFrangmentActivity implements View.OnClickListener {
    private NoscrollListView mLeft;

    private NoscrollListView mData;
    private SignDetailAdapter detailAdapter;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;

    private List<SignDetailBean.DataBean> mListData = new ArrayList<SignDetailBean.DataBean>();
    private SignDetailBean signBean;
    private ImageView ivSiganSqlDetail;
    private ImageView ivSearch;
    private SignContentDialog dialog;

    private SharedPreferences sp;
    private SPUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_activity_detail);
        initView();
        getViews();
        setSignDetail();
    }

    private void initView() {
        ivSiganSqlDetail = (ImageView) findViewById(R.id.ivSiganSqlDetail);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
    }

    private void getViews() {
        ivSiganSqlDetail.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSiganSqlDetail:
                finish();
                break;
            case R.id.ivSearch:
                showDialog(v);
                break;
        }
    }

    /**
     * 签到查询服务器
     */
    private void setSignDetail() {
        String str = HttpUrl.debugoneUrl + "OutRegister/BindSearchAPPPage/";
        sp = SignDetailActivity.this.getSharedPreferences("my_sp", Context.MODE_WORLD_READABLE);
        String name = sp.getString("etAudit","");
        String datetime = sp.getString("datetimesign","");
        String endtime = sp.getString("endtimesign","");
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this, "正在查询，请稍后");
            OkHttpUtils
                    .post()
                    .url(str)
                    .addParams("pageNum", "0")
                    .addParams("pageSize", "10")
                    .addParams("recorder",name)
                    .addParams("recordat_start",datetime)
                    .addParams("recordat_end",endtime)
                    .addParams("recordplace","")
                    .addParams("memo","")
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
                                detailAdapter = new SignDetailAdapter(mListData, SignDetailActivity.this);
                                mData.setAdapter(detailAdapter);
                            } catch (JsonSyntaxException e) {
                                ToastUtils.ShowToastMessage("获取列表失败,请重新再试", SignDetailActivity.this);
                                ResponseDialog.closeLoading();
                            } catch (Exception e) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignDetailActivity.this);
        }
    }

    private void showDialog(View view) {
        dialog = new SignContentDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
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
                    setSignDetail();
                    dialog.dismiss();
                    break;
            }
        }
    };
}
