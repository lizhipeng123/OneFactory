package com.daoran.newfactory.onefactory.activity.work;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.SignDetailAdapter;
import com.daoran.newfactory.onefactory.adapter.SignDetailLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.SignDetailBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.save.SignDetailExcelUtil;
import com.daoran.newfactory.onefactory.view.dialog.SignContentDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
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
    private SignDetailLeftAdapter leftAdapter;
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
    private ImageView ivUpLeftPage, ivDownRightPage;
    private Button spinnermenu;

    private SharedPreferences sp;
    private SPUtils spUtils;
    int pageIndex = 0;
    int pageCount;
    private String configid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_detail);
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
        mLeft = (NoscrollListView) findViewById(R.id.lv_left);
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
        ivUpLeftPage = (ImageView) findViewById(R.id.ivUpLeftPage);
        ivDownRightPage = (ImageView) findViewById(R.id.ivDownRightPage);
        spinnermenu = (Button) findViewById(R.id.spinnermenu);
        getClumnsSpinner();
    }

    /**
     * 操作控件
     */
    private void getViews() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("info", "landscape"); // 横屏
            configid = String.valueOf(1);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("info", "portrait"); // 竖屏
            configid = String.valueOf(2);
        }
        ivSiganSqlDetail.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        btnSignPage.setOnClickListener(this);
        etSqlDetail.setOnClickListener(this);
        ivUpLeftPage.setOnClickListener(this);
        ivDownRightPage.setOnClickListener(this);
        etSqlDetail.setSelection(etSqlDetail.getText().length());
        spinnermenu.setOnClickListener(this);
    }

    /**
     * 填充签到查询每页显示条目spinner数据
     */
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

    /**
     * 弹出选择菜单
     *
     * @param view
     */
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(SignDetailActivity.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_signdetail, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                switch (title) {
                    case "刷新":
                        setSignDetail();
                        break;
                    case "横竖屏切换":
                        if (configid.equals("1")) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        } else if (configid.equals("2")) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        } else {

                        }
                        break;
                    case "保存为Excel":
                        final ProgressDialog progressDialog = ProgressDialog.show(SignDetailActivity.this,
                                "请稍候...", "正在生成Excel中...", false, true);
                        final Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    if (mListData.size() != 0) {
                                        Looper.prepare();
                                        ToastUtils.ShowToastMessage("写入成功", SignDetailActivity.this);
                                        SignDetailExcelUtil.writeExcel(SignDetailActivity.this,
                                                mListData,
                                                "dfsignexcel+" + new Date().toString());
                                        progressDialog.dismiss();
                                        Looper.loop();
                                    } else {
                                        Looper.prepare();
                                        ToastUtils.ShowToastMessage("没有数据", SignDetailActivity.this);
                                        progressDialog.dismiss();
                                        Looper.loop();
                                    }
                                } catch (Exception e) {
                                    Looper.prepare();
                                    ToastUtils.ShowToastMessage("写入失败", SignDetailActivity.this);
                                    e.printStackTrace();
                                    progressDialog.dismiss();
                                    Looper.loop();
                                }
                            }
                        });
                        thread.start();
                        break;
                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
            }
        });
        popupMenu.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回*/
            case R.id.ivSiganSqlDetail:
                finish();
                break;
            /*条件查询*/
            case R.id.ivSearch:
                showDialog(v);
                break;
            /*翻页*/
            case R.id.btnSignPage:
                String txt = etSqlDetail.getText().toString();
                String txtint = tvSignPage.getText().toString();
                if (txt.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", SignDetailActivity.this);
                } else {
                    int txtindex = Integer.parseInt(txt);
                    int txtcountindex = Integer.parseInt(txtint);
                    if (txtindex > txtcountindex) {
                        ToastUtils.ShowToastMessage("已经是最后一页", SignDetailActivity.this);
                    } else if (txtindex < 1) {
                        ToastUtils.ShowToastMessage("已经是第一页", SignDetailActivity.this);
                    } else if (txt.length() == 0) {
                        ToastUtils.ShowToastMessage("页码不能为空", SignDetailActivity.this);
                        return;
                    } else if (txt.length() > txtint.length()) {
                        ToastUtils.ShowToastMessage("页码超出输入范围", SignDetailActivity.this);
                        return;
                    } else {
                        setPageSignDetail();
                    }
                }
                break;
            /*上一页*/
            case R.id.ivUpLeftPage:
                String stredit = etSqlDetail.getText().toString();
                if (stredit.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", SignDetailActivity.this);
                } else {
                    int pageindex = Integer.parseInt(stredit);
                    int index = pageindex - 2;
                    if (index < 0) {
                        ToastUtils.ShowToastMessage("已经是第一页", SignDetailActivity.this);
                    } else {
                        String indexstr = String.valueOf(index);
                        int indexcount = index + 1;
                        etSqlDetail.setText(String.valueOf(indexcount));
                        etSqlDetail.setSelection(String.valueOf(indexcount).length());
                        setPageDate(indexstr);
                    }
                }
                break;
            /*下一页*/
            case R.id.ivDownRightPage:
                String stredit2 = etSqlDetail.getText().toString();
                if (stredit2.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", SignDetailActivity.this);
                } else {
                    int pageIndexx = Integer.parseInt(stredit2);
                    int index2 = pageIndexx;
                    String maxpageindex = tvSignPage.getText().toString();
                    int indexmax = Integer.parseInt(maxpageindex);
                    int index3 = index2 + 1;
                    if (index3 > indexmax) {
                        ToastUtils.ShowToastMessage("已经是最后一页", SignDetailActivity.this);
                    } else {
                        String index2str = String.valueOf(index2);
                        int indexcount = index2 + 1;
                        etSqlDetail.setText(String.valueOf(indexcount));
                        etSqlDetail.setSelection(String.valueOf(indexcount).length());
                        setPageDate(index2str);
                    }
                }
                break;
            case R.id.etSqlDetail:

                break;
            /*弹出菜单*/
            case R.id.spinnermenu:
                showPopupMenu(spinnermenu);
                break;
        }
    }

    /**
     * 上一页下一页
     *
     * @param pageIndex1
     */
    private void setPageDate(String pageIndex1) {
        String str = HttpUrl.debugoneUrl + "OutRegister/BindSearchAPPPage/";
        sp = SignDetailActivity.this.getSharedPreferences("my_sp", 0);
        String name = sp.getString("name", "");
        String datetime = sp.getString("datetimesign", "");
        String endtime = sp.getString("endtimesign", "");
        String getsize = sp.getString("clumnssignspinner", "");
        if (getsize.equals("")) {
            getsize = String.valueOf(10);
        }
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this);
            final int finalGetsize = Integer.parseInt(getsize);
            OkHttpUtils
                    .post()
                    .url(str)
                    .addParams("pageNum", pageIndex1)
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
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    System.out.print(count);
                                    tvSignPage.setText(count);
                                    detailAdapter = new SignDetailAdapter(mListData, SignDetailActivity.this);
                                    mData.setAdapter(detailAdapter);
                                    leftAdapter = new SignDetailLeftAdapter(SignDetailActivity.this, mListData);
                                    mLeft.setAdapter(leftAdapter);
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
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignDetailActivity.this);
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
            final ProgressDialog progressDialog = ProgressDialog.show(this,
                    "请稍候...", "正在查询中...", false, true);
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
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    System.out.print(count);
                                    tvSignPage.setText(count);
                                    detailAdapter = new SignDetailAdapter(mListData, SignDetailActivity.this);
                                    mData.setAdapter(detailAdapter);
                                    leftAdapter = new SignDetailLeftAdapter(SignDetailActivity.this, mListData);
                                    mLeft.setAdapter(leftAdapter);
                                    detailAdapter.notifyDataSetChanged();
                                } else {
                                    ll_visibi.setVisibility(View.VISIBLE);
                                    scroll_content.setVisibility(View.GONE);
                                    tv_visibi.setText("没有更多信息");
                                }
                            } catch (JsonSyntaxException e) {
                                ToastUtils.ShowToastMessage("获取列表失败,请重新再试", SignDetailActivity.this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignDetailActivity.this);
        }
    }

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
                            ResponseDialog.closeLoading();
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
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    System.out.print(count);
                                    tvSignPage.setText(count);
                                    detailAdapter = new SignDetailAdapter(mListData, SignDetailActivity.this);
                                    mData.setAdapter(detailAdapter);
                                    leftAdapter = new SignDetailLeftAdapter(SignDetailActivity.this, mListData);
                                    mLeft.setAdapter(leftAdapter);
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
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignDetailActivity.this);
        }
    }

    private void showDialog(View view) {
        dialog = new SignContentDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        dialog.show();
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
                    String etsql2 = etSqlDetail.getText().toString();
                    if (etsql2.equals("")) {
                        ToastUtils.ShowToastMessage("页码不能为空", SignDetailActivity.this);
                    } else {
                        setPageSignDetail();
                    }
                    dialog.dismiss();
                    break;
            }
        }
    };
}