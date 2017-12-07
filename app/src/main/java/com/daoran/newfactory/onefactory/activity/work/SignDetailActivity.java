package com.daoran.newfactory.onefactory.activity.work;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.daoran.newfactory.onefactory.adapter.signadapter.SignDetailAdapter;
import com.daoran.newfactory.onefactory.adapter.signadapter.SignDetailLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.signbean.SignDailyBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.utils.Util;
import com.daoran.newfactory.onefactory.view.dialog.signdialog.SignSearchDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
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
    private NoscrollListView lv_left;//页面左侧视图（签到信息编号）
    private NoscrollListView lv_data;//页面右侧视图（签到信息主体内容）
    private SignDetailAdapter detailAdapter;//页面右侧列表适配（签到信息主体内容）
    private SignDetailLeftAdapter leftAdapter;//页面左侧列表适配（签到信息编号）
    private SyncHorizontalScrollView header_horizontal;//可滑动scrollview视图顶部（签到信息命名列）
    private SyncHorizontalScrollView data_horizontal;//可滑动scrollview视图列表（签到信息主体视图）
    private List<SignDailyBean.DataBean> mListData = new ArrayList<SignDailyBean.DataBean>();//列表内容签到信息集合
    private SignDailyBean signBean;//签到信息实体类
    private ImageView ivSiganSqlDetail;//返回图片按钮
    private ImageView ivSearch;//条件查询图片按钮
    private SignSearchDialog dialog;//顶部右侧刷新等弹出菜单
    private EditText etSqlDetail;//页数输入框
    private TextView tvSignPage;//总页数
    private Button btnSignPage;//按页数查询按钮
    private LinearLayout ll_visibi;//没有数据时显示的页面
    private TextView tv_visibi;//空数据显示的信息
    private ScrollView scroll_content;//签到信息内容可上下滑动
    private Spinner spinnSignPageClumns;//每页显示的条目数
    private ImageView ivUpLeftPage, ivDownRightPage;//上下翻页图片按钮
    private Button spinnermenu;

    private SharedPreferences sp;
    private SPUtils spUtils;
    int pageIndex = 0;//初始页数
    int pageCount;//总页数
    private String configid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_detail);
        initView();
        getViews();
    }

    /*实例化控件*/
    private void initView() {
        ivSiganSqlDetail = (ImageView) findViewById(R.id.ivSiganSqlDetail);
        lv_data = (NoscrollListView) findViewById(R.id.lv_data);
        lv_left = (NoscrollListView) findViewById(R.id.lv_left);
        data_horizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        header_horizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        data_horizontal.setSrollView(header_horizontal);
        header_horizontal.setSrollView(data_horizontal);
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
        Util.setEditTextInhibitInputSpeChat(etSqlDetail);
        getClumnsSpinner();
    }

    /*操作控件*/
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
        ivUpLeftPage.setOnClickListener(this);
        ivDownRightPage.setOnClickListener(this);
        etSqlDetail.setSelection(etSqlDetail.getText().length());
        spinnermenu.setOnClickListener(this);
    }

    /*填充签到查询每页显示条目spinner数据*/
    private void getClumnsSpinner() {
        String[] spinner = getResources().getStringArray(R.array.clumnsCommon);
        ArrayAdapter<String> adapterclumns = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner);
        adapterclumns.setDropDownViewResource(R.layout.dropdown_stytle);
        spinnSignPageClumns.setAdapter(adapterclumns);//绑定数据
        spinnSignPageClumns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().
                        getStringArray(R.array.clumnsCommon);
                //将选择的条目数保存到轻量级存储中
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
            /*返回*/
            case R.id.ivSiganSqlDetail:
                sethideSoft(v);
                finish();
                break;
            /*条件查询*/
            case R.id.ivSearch:
                showDialog(v);
                break;
            /*翻页*/
            case R.id.btnSignPage:
                sethideSoft(v);
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
                sethideSoft(v);
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
                sethideSoft(v);
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
            /*弹出菜单*/
            case R.id.spinnermenu:
                if (configid.equals("1")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (configid.equals("2")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {

                }
                break;
        }
    }

    private void sethideSoft(View v) {
        //判断软件盘是否弹出
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                        0);
            } else {
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                        0);
            }
        }
    }

    /*上一页下一页*/
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
                            ResponseDialog.closeLoading();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            System.out.print(response);
                            try {
                                Gson gson = new Gson();
                                signBean = gson.fromJson(response, SignDailyBean.class);
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
                                    lv_data.setAdapter(detailAdapter);
                                    leftAdapter = new SignDetailLeftAdapter(SignDetailActivity.this, mListData);
                                    lv_left.setAdapter(leftAdapter);
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

    /*签到查询*/
    private void setSignDetail() {
        String str = HttpUrl.debugoneUrl + "OutRegister/BindSearchAPPPage/";
        sp = SignDetailActivity.this.getSharedPreferences("my_sp", 0);
        String name = sp.getString("name", "");//当前登录用户
        String getsize = sp.getString("clumnssignspinner", "");//每页显示的条目数
        //默认显示10条
        if (getsize.equals("")) {
            getsize = String.valueOf(10);
        }
        String datetime = sp.getString("datetimesign", "");//起始日期
        String endtime = sp.getString("endtimesign", "");//结束日期
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this,"正在查询");
            final int finalGetsize = Integer.parseInt(getsize);
            OkHttpUtils
                    .post()
                    .url(str)
                    .addParams("pageNum", "0")//起始页
                    .addParams("pageSize", getsize)//每页显示的条目数
                    .addParams("recorder", name)//制单人
                    .addParams("recordat_start", datetime)//起始日期
                    .addParams("recordat_end", endtime)//结束日期
                    .addParams("recordplace", "")
                    .addParams("memo", "")//备注
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
                                signBean = gson.fromJson(response, SignDailyBean.class);
                                mListData = signBean.getData();
                                //判断是否有数据，如果没有则显示另一个页面
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
                                    lv_data.setAdapter(detailAdapter);
                                    leftAdapter = new SignDetailLeftAdapter(SignDetailActivity.this, mListData);
                                    lv_left.setAdapter(leftAdapter);
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

    /*翻页查找*/
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
            ResponseDialog.showLoading(this,"正在查询");
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
                                signBean = gson.fromJson(response, SignDailyBean.class);
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
                                    lv_data.setAdapter(detailAdapter);
                                    leftAdapter = new SignDetailLeftAdapter(SignDetailActivity.this, mListData);
                                    lv_left.setAdapter(leftAdapter);
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
                                ResponseDialog.closeLoading();
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.disNetworking, SignDetailActivity.this);
        }
    }

    private void showDialog(View view) {
        dialog = new SignSearchDialog(this,
                R.style.dialogstyle, onClickListener, onCancleListener);
        dialog.show();
    }

    /*取消事件*/
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

    /*确认事件*/
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnComfirm:
                    sethideSoft(v);
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