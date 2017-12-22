package com.daoran.newfactory.onefactory.activity.work.car;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.reqcaradapter.ReqCarSearchAdapter;
import com.daoran.newfactory.onefactory.base.BaseListActivity;
import com.daoran.newfactory.onefactory.bean.reqcarbean.ReqCarDailyBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.reqcardialog.ReqCarSearchDialog;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.i5tong.epubreaderlib.view.pulltorefresh.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 出车单列表
 * Created by lizhipeng on 2017/4/19.
 */

public class ReqcarmSearchActivity extends BaseListActivity implements View.OnClickListener {
    private ImageView ivBack, ivSearch;//返回与查询图片按钮
    private TextView tvInitialDate;//出车单初始日期
    private ReqCarSearchDialog dialog;//条件查询dialog弹出框
    private LinearLayout ll_visibi;//隐藏的空页面
    private PullToRefreshListView listview;//可刷新的listview
    private ReqCarDailyBean.DataBean dataBean;//列表显示的实体
    private List<ReqCarDailyBean.DataBean> dataBeenlist =
            new ArrayList<ReqCarDailyBean.DataBean>();//接收数据集合
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

    /*实例化控件*/
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
        return new ReqCarSearchAdapter(ReqcarmSearchActivity.this);
    }

    @Override
    public void getData(int pageIndex) {
        String sqlcar = HttpUrl.debugoneUrl + "UCarsApply/UCarsApplySearch/";
        sp = ReqcarmSearchActivity.this.getSharedPreferences("my_sp", 0);
        String datetime = sp.getString("ReqCarDateTime", "");//开始时间
        String endtime = sp.getString("ReqCarEndTime", "");//结束时间
        String spinnerPosition = sp.getString("ReqCarState", "");
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this,"正在查询");
            OkHttpUtils
                    .post()
                    .url(sqlcar)
                    .addParams("start", datetime)
                    .addParams("endtime", endtime)
                    .addParams("title", spinnerPosition)
                    .addParams("pageNum", pageIndex * 1 + "")
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
                            ToastUtils.ShowToastMessage("获取失败，请稍后再试", ReqcarmSearchActivity.this);
                            ResponseDialog.closeLoading();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                ReqCarDailyBean carApplyBean =
                                        new Gson().fromJson(response,
                                                ReqCarDailyBean.class);
                                ll_visibi.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                                dataBeenlist = carApplyBean.getData();
                                setListData(carApplyBean.getData());
                            } catch (JsonSyntaxException e) {
                                setListData(new ArrayList());
                            } catch (Exception e) {
                                setListData(new ArrayList());
                            }
                            ResponseDialog.closeLoading();
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(getString(R.string.noHttp), ReqcarmSearchActivity.this);
        }
    }

    @Override//点击item
    public void onListItemClick(Object o) {
        System.out.print(o);
        dataBean = (ReqCarDailyBean.DataBean) o;
        startActivity(new Intent(ReqcarmSearchActivity.this, ReqCarmDetailActivity.class)
                .putExtra("id", dataBean.getId()));
    }

    @Override//长按
    public void onListItemLongClick(Object o) {

    }

    /*条件查询弹出输入框*/
    public void showEditDialog(View view) {
        dialog =
                new ReqCarSearchDialog(this, R.style.dialogstyle, onClickListener, onCancleListener);
        dialog.show();
        dialog.setCancelable(false);
    }

    /*取消*/
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

    /*确认*/
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
                    getData();
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
        }
    }
}