package com.daoran.newfactory.onefactory.activity.work.car;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.SqlCarApplyAdapter;
import com.daoran.newfactory.onefactory.base.BaseListActivity;
import com.daoran.newfactory.onefactory.bean.SqlCarApplyBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.save.ExcelUtil;
import com.daoran.newfactory.onefactory.view.dialog.ContentDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.i5tong.epubreaderlib.view.pulltorefresh.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 出车单详情
 * Created by lizhipeng on 2017/4/19.
 */

public class SqlcarApplyActivity extends BaseListActivity implements View.OnClickListener {
    private ImageView ivBack, ivSearch;
    private TextView tvInitialDate;
    private ContentDialog dialog;//条件查询dialog弹出框
    private LinearLayout ll_visibi;//隐藏的空页面
    private PullToRefreshListView listview;//可刷新的listview
    private SqlCarApplyBean.DataBean dataBean;//列表显示的实体
    private List<SqlCarApplyBean.DataBean> dataBeenlist =
            new ArrayList<SqlCarApplyBean.DataBean>();//保存excel文件的list集合
    private SharedPreferences sp;//取值地址
    private SPUtils spUtils;//存储上传地址
    private Button btnExcel;//点击保存Excel控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_carapply);
        getViews();
        init(R.id.listview);
        setPullToRefreshListViewModeBOTH();
        getData();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        tvInitialDate = (TextView) findViewById(R.id.tvInitialDate);
        ll_visibi = (LinearLayout) findViewById(R.id.ll_visibi);
        listview = (PullToRefreshListView) findViewById(R.id.listview);
        btnExcel = (Button) findViewById(R.id.btnExcel);
        ivBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnExcel.setOnClickListener(this);
    }

    @Override
    public BaseAdapter setListAdapter() {
        return new SqlCarApplyAdapter(SqlcarApplyActivity.this);
    }

    @Override
    public void getData(int pageIndex) {
        String sqlcar = HttpUrl.debugoneUrl + "UCarsApply/UCarsApplySearch/";
        sp = SqlcarApplyActivity.this.getSharedPreferences("my_sp", 0);
        String datetime = sp.getString("datetime", "");
        String endtime = sp.getString("endtime", "");
        String spinnerPosition = sp.getString("spinnerPosition", "");
        System.out.print(datetime);
        if (NetWork.isNetWorkAvailable(this)) {
            final ProgressDialog progressDialog = ProgressDialog.show(this,
                    "请稍候...", "正在查询中...", false, true);
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
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                            thread.start();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                System.out.print(response);
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
                                SqlCarApplyBean carApplyBean =
                                        new Gson().fromJson(response,
                                                SqlCarApplyBean.class);
                                ll_visibi.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                                dataBeenlist = carApplyBean.getData();
                                setListData(carApplyBean.getData());
                            } catch (JsonSyntaxException e) {
                                setListData(new ArrayList());
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
                            } catch (Exception e) {
                                setListData(new ArrayList());
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
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
        startActivity(new Intent(SqlcarApplyActivity.this, CarapplyActivity.class)
                .putExtra("id", dataBean.getId()));
    }

    @Override
    public void onListItemLongClick(Object o) {

    }

    /**
     * 条件查询弹出输入框
     *
     * @param view
     */
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
            /*返回按钮*/
            case R.id.ivBack:
                finish();
                break;
            /*条件查询*/
            case R.id.ivSearch:
                showEditDialog(v);
                break;
            /*保存为excel文件*/
            case R.id.btnExcel:
                final ProgressDialog progressDialog = ProgressDialog.show(this,
                        "请稍候...", "正在生成Excel中...", false, true);
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            if(dataBeenlist.size()!=0){
                                Looper.prepare();
                                ToastUtils.ShowToastMessage("写入成功", SqlcarApplyActivity.this);
                                ExcelUtil.writeExcel(SqlcarApplyActivity.this,
                                        dataBeenlist,
                                        "dfcarexcel+" + new Date().toString());
                                progressDialog.dismiss();
                                Looper.loop();
                            }else{
                                Looper.prepare();
                                ToastUtils.ShowToastMessage("没有数据", SqlcarApplyActivity.this);
                                progressDialog.dismiss();
                                Looper.loop();
                            }
                        } catch (Exception e) {
                            Looper.prepare();
                            ToastUtils.ShowToastMessage("写入失败", SqlcarApplyActivity.this);
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Looper.loop();
                        }
                    }
                });
                thread.start();
                break;
        }
    }
}