package com.daoran.newfactory.onefactory.activity.work;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.production.ProductionActivity;
import com.daoran.newfactory.onefactory.adapter.SignDetailAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
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
    private EditText etSqlDetail;
    private TextView tvSignPage;
    private Button btnSignPage;
    private LinearLayout ll_visibi;
    private TextView tv_visibi;
    private ScrollView scroll_content;
    private Spinner spinnSignPageClumns;

    private SharedPreferences sp;
    private SPUtils spUtils;
    int pageIndex = 0;
    int pageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ResponseDialog.showLoading(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debug_activity_detail);
        initView();
        getViews();
        setSignDetail();
    }

    /**
     * 实例化控件
     */
    private void initView() {
        ivSiganSqlDetail = (ImageView) findViewById(R.id.ivSiganSqlDetail);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
        etSqlDetail = (EditText) findViewById(R.id.etSqlDetail);
        tvSignPage = (TextView) findViewById(R.id.tvSignPage);
        btnSignPage = (Button) findViewById(R.id.btnSignPage);
        ll_visibi = (LinearLayout) findViewById(R.id.ll_visibi);
        tv_visibi = (TextView) findViewById(R.id.tv_visibi);
        scroll_content = (ScrollView) findViewById(R.id.scroll_content);
        spinnSignPageClumns = (Spinner) findViewById(R.id.spinnSignPageClumns);
        getClumnsSpinner();
    }

    /**
     * 操作控件
     */
    private void getViews() {
        ivSiganSqlDetail.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);
        etSqlDetail.setOnClickListener(this);
        etSqlDetail.setSelection(etSqlDetail.getText().length());
    }

    private void getClumnsSpinner() {
        String[] spinner = getResources().getStringArray(R.array.clumnsCommon);
        ArrayAdapter<String> adapterclumns = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner);
        adapterclumns.setDropDownViewResource(R.layout.dropdown_stytle);
        spinnSignPageClumns.setAdapter(adapterclumns);
        spinnSignPageClumns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().
                        getStringArray(R.array.clumnsCommon);
                spUtils.put(SignDetailActivity.this,
                        "clumnssignspinner", languages[position]);
                setSignDetail();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
            case R.id.btnSignPage:
                String txt = etSqlDetail.getText().toString();
                String txtint = tvSignPage.getText().toString();
                if (txt.length() == 0) {
                    ToastUtils.ShowToastMessage("页码不能为空", SignDetailActivity.this);
                    return;
                } else if (txt.length() > txtint.length()) {
                    ToastUtils.ShowToastMessage("页码超出输入范围", SignDetailActivity.this);
                    return;
                } else {
                    setPageSignDetail();
                }
                break;
            case R.id.etSqlDetail:

                break;
        }
    }

    /**
     * 签到查询服务器
     */
    private void setSignDetail() {
        String str = HttpUrl.debugoneUrl + "OutRegister/BindSearchAPPPage/";
        sp = SignDetailActivity.this.getSharedPreferences("my_sp", 0);
        String name = sp.getString("name", "");
        String getsize = sp.getString("clumnssignspinner", "");
        if (getsize.equals("")) {
            getsize = String.valueOf(10);
        }
        String datetime = sp.getString("datetimesign", "");
        String endtime = sp.getString("endtimesign", "");
        if (NetWork.isNetWorkAvailable(this)) {
//             /*检测是否为可用WiFi*/
//            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
//            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//            String infossid = wifiInfo.getSSID();
//            infossid = infossid.replace("\"", "");
//            if (!infossid.equals("taoxinxi")) {
//                AlertDialog dialog = new AlertDialog.Builder(this).create();
//                dialog.setTitle("系统提示");
//                dialog.setMessage("当前WiFi为公共网络，运行请转到测试WiFi状态");
//                dialog.setButton("确定", listenerwifi);
//                dialog.show();
//            } else {
            ResponseDialog.showLoading(this);
            final int finalGetsize = Integer.parseInt(getsize);
            OkHttpUtils
                    .post()
                    .url(str)
                    .addParams("pageNum", "0")
                    .addParams("pageSize", getsize)
                    .addParams("recorder", name)
                    .addParams("recordat_start", datetime)
                    .addParams("recordat_end", endtime)
                    .addParams("recordplace", "")
                    .addParams("memo", "")
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
                                if (signBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    pageCount = signBean.getTotalCount();
                                    spUtils.put(SignDetailActivity.this, "pageCount", pageCount);
                                    System.out.print(pageCount);
                                    String count = String.valueOf(pageCount / finalGetsize);
                                    System.out.print(count);
                                    tvSignPage.setText(count);
                                    detailAdapter = new SignDetailAdapter(mListData, SignDetailActivity.this);
                                    mData.setAdapter(detailAdapter);
                                    detailAdapter.notifyDataSetChanged();
                                } else {
                                    ll_visibi.setVisibility(View.VISIBLE);
                                    scroll_content.setVisibility(View.GONE);
                                    tv_visibi.setText("没有更多信息");
                                }
                                ResponseDialog.closeLoading();
                            } catch (JsonSyntaxException e) {
                                ToastUtils.ShowToastMessage("获取列表失败,请重新再试", SignDetailActivity.this);
                                ResponseDialog.closeLoading();
                            } catch (Exception e) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
//            }
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignDetailActivity.this);
        }
    }

    DialogInterface.OnClickListener listenerwifi = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE://确定
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 翻页查找
     */
    private void setPageSignDetail() {
        String str = HttpUrl.debugoneUrl + "OutRegister/BindSearchAPPPage/";
        sp = SignDetailActivity.this.getSharedPreferences("my_sp", 0);
        String name = sp.getString("name", "");
        String datetime = sp.getString("datetimesign", "");
        String endtime = sp.getString("endtimesign", "");
        String getsize = sp.getString("clumnssignspinner", "");
        if (getsize.equals("")) {
            getsize = String.valueOf(10);
        }
        pageIndex = Integer.parseInt(etSqlDetail.getText().toString());
        String index = String.valueOf(pageIndex - 1);
        if (NetWork.isNetWorkAvailable(this)) {
//             /*检测是否为可用WiFi*/
//            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
//            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//            String infossid = wifiInfo.getSSID();
//            infossid = infossid.replace("\"", "");
//            if (!infossid.equals("taoxinxi")) {
//                AlertDialog dialog = new AlertDialog.Builder(this).create();
//                dialog.setTitle("系统提示");
//                dialog.setMessage("当前WiFi为公共网络，运行请转到测试WiFi状态");
//                dialog.setButton("确定", listenerwifi);
//                dialog.show();
//            } else {
            ResponseDialog.showLoading(this);
            final int finalGetsize = Integer.parseInt(getsize);
            OkHttpUtils
                    .post()
                    .url(str)
                    .addParams("pageNum", index)
                    .addParams("pageSize", getsize)
                    .addParams("recorder", name)
                    .addParams("recordat_start", datetime)
                    .addParams("recordat_end", endtime)
                    .addParams("recordplace", "")
                    .addParams("memo", "")
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
                                if (signBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    pageCount = signBean.getTotalCount();
                                    spUtils.put(SignDetailActivity.this, "pagesqlCount", pageCount);
                                    System.out.print(pageCount);
//                                        int pagesign = finalGetsize;
                                    String count = String.valueOf(pageCount / finalGetsize);
                                    System.out.print(count);
                                    tvSignPage.setText(count);
                                    detailAdapter = new SignDetailAdapter(mListData, SignDetailActivity.this);
                                    mData.setAdapter(detailAdapter);
                                    detailAdapter.notifyDataSetChanged();
                                } else {
                                    ll_visibi.setVisibility(View.VISIBLE);
                                    scroll_content.setVisibility(View.GONE);
                                    tv_visibi.setText("没有更多信息");
                                }
                                ResponseDialog.closeLoading();
                            } catch (JsonSyntaxException e) {
                                ToastUtils.ShowToastMessage("获取列表失败,请重新再试", SignDetailActivity.this);
                                ResponseDialog.closeLoading();
                            } catch (Exception e) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
//            }
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
                    setPageSignDetail();
                    dialog.dismiss();
                    break;
            }
        }
    };
}