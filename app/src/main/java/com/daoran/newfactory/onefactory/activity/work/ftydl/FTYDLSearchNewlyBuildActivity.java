package com.daoran.newfactory.onefactory.activity.work.ftydl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.ftydladapter.FTYDLNewlyBuildAdapter;
import com.daoran.newfactory.onefactory.adapter.ftydladapter.FTYDLNewlyBuildLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLFactoryDailyBean;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLNewlyBuildSearchBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.utils.Util;
import com.daoran.newfactory.onefactory.view.dialog.utildialog.ResponseDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 新建生产日报选择款号页面
 * Created by lizhipeng on 2017/5/2.
 */

public class FTYDLSearchNewlyBuildActivity
        extends BaseFrangmentActivity
        implements View.OnClickListener {
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;

    private ImageView ivProductionBack;//返回
    private Button btnNewbuildConfirm;//确定
    private TextView spinnerNewbuild;//选择工序
    private EditText etNewbuildDetail;//页数输入框
    private Button etNewbuildSql,//查找按钮
            btnNewbuildPage;//翻页确定按钮
    private TextView tvNewbuildPage;//
    private EditText etNewbuild;//款号输入框
    private ImageView ivUpLeftPage, ivDownRightPage;
    private ImageView ivNewlyFilp;

    private NoscrollListView lv_pleft;//左侧布局
    private ListView lv_newbuild_data;//款号信息列表
    private FTYDLNewlyBuildAdapter buildAdapter;//款号信息列表适配
    private FTYDLFactoryDailyBean newlyBuildBean;//新建数据实体bean
    private List<FTYDLFactoryDailyBean.DataBean> dataBeen =
            new ArrayList<FTYDLFactoryDailyBean.DataBean>();//新建数据实体list

    private FTYDLNewlyBuildLeftAdapter leftAdapter;
    private LinearLayout ll_visibi;//隐藏的不存在数据的页面
    private TextView tv_visibi;
    private ScrollView scroll_content;
    private Spinner spinnProNewPageClumns;

    private int pageCount;//请求获取的总页数
    private int pageIndex = 0;//初始页数
    private String configid,pagesize;

    private SharedPreferences sp;//轻量级存储
    private SPUtils spUtils;//保存在手机中的目录
    private int orientation;//横竖屏切换

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*1是竖屏，2是横屏*/
        setContentView(R.layout.activity_production_newbuid);
        getViews();
        initViews();
        setListener();
        getClumnsSpinner();
        lv_newbuild_data.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.ShowToastMessage("点击的是" + position, FTYDLSearchNewlyBuildActivity.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*实例化控件*/
    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        btnNewbuildConfirm = (Button) findViewById(R.id.btnNewbuildConfirm);
        spinnerNewbuild = (TextView) findViewById(R.id.spinnerNewbuild);
        etNewbuild = (EditText) findViewById(R.id.etNewbuild);
        etNewbuildDetail = (EditText) findViewById(R.id.etNewbuildDetail);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        etNewbuildSql = (Button) findViewById(R.id.etNewbuildSql);
        btnNewbuildPage = (Button) findViewById(R.id.btnNewbuildPage);
        tvNewbuildPage = (TextView) findViewById(R.id.tvNewbuildPage);
        lv_newbuild_data = (ListView) findViewById(R.id.lv_newbuild_data);
        lv_pleft = (NoscrollListView) findViewById(R.id.lv_pleft);
        ll_visibi = (LinearLayout) findViewById(R.id.ll_visibi);
        tv_visibi = (TextView) findViewById(R.id.tv_visibi);
        scroll_content = (ScrollView) findViewById(R.id.scroll_content);
        spinnProNewPageClumns = (Spinner) findViewById(R.id.spinnProNewPageClumns);
        ivUpLeftPage = (ImageView) findViewById(R.id.ivUpLeftPage);
        ivDownRightPage = (ImageView) findViewById(R.id.ivDownRightPage);
        ivNewlyFilp = (ImageView) findViewById(R.id.ivNewlyFilp);
        Util.setEditTextInhibitInputSpeChat(etNewbuildDetail);
        Util.setEditTextInhibitInputSpeChat(etNewbuild);
    }

    /*初始化控件*/
    private void initViews() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
        etNewbuildDetail.setSelection(etNewbuildDetail.getText().length());
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("info", "landscape"); // 横屏
            configid = String.valueOf(1);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("info", "portrait"); // 竖屏
            configid = String.valueOf(2);
        }
    }

    /*控件赋予点击属性*/
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        spinnerNewbuild.setOnClickListener(this);
        btnNewbuildPage.setOnClickListener(this);
        etNewbuildSql.setOnClickListener(this);
        btnNewbuildConfirm.setOnClickListener(this);
        ivUpLeftPage.setOnClickListener(this);
        ivDownRightPage.setOnClickListener(this);
        ivNewlyFilp.setOnClickListener(this);
    }

    /*填充新建生产日报中每页显示spinner中数据*/
    private void getClumnsSpinner() {
        String[] spinner = getResources().getStringArray(R.array.clumnsCommon);
        ArrayAdapter<String> adapterclumns = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner);
        adapterclumns.setDropDownViewResource(R.layout.dropdown_stytle);
        spinnProNewPageClumns.setAdapter(adapterclumns);
        spinnProNewPageClumns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().
                        getStringArray(R.array.clumnsCommon);
                spUtils.put(FTYDLSearchNewlyBuildActivity.this,
                        "clumnsFTYDLpageSize", languages[position]);
                setDate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /*初始化查询*/
    private void setDate() {
        sp = getSharedPreferences("my_sp", 0);
        String urlDaily = HttpUrl.debugoneUrl + "FactoryPlan/FactoryDailyAPP/";
        pagesize = sp.getString("clumnsFTYDLpageSize", "");
        String editNewlyBuild = etNewbuild.getText().toString();//输入款号
        String spinner = spinnerNewbuild.getText().toString();
        if (pagesize.equals("")) {
            pagesize = String.valueOf(10);
        }
        Gson gson = new Gson();
        final FTYDLNewlyBuildSearchBean buildBean = new FTYDLNewlyBuildSearchBean();
        FTYDLNewlyBuildSearchBean.Conditions conditions = buildBean.new Conditions();
        conditions.setItem(editNewlyBuild);
        conditions.setWorkingProcedure(spinner);
        buildBean.setConditions(conditions);
        buildBean.setPageNum(0);
        buildBean.setPageSize(Integer.parseInt(pagesize));
        final String bean = gson.toJson(buildBean);
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this, "正在查询");
            final int finalGetsize = Integer.parseInt(pagesize);
            OkHttpUtils.postString()
                    .url(urlDaily)
                    .content(bean)
                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            ResponseDialog.closeLoading();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                String ress = response.replace("\\", "");
                                String ression = StringUtil.sideTrim(ress, "\"");
                                newlyBuildBean = new Gson().fromJson(ression, FTYDLFactoryDailyBean.class);
                                dataBeen = newlyBuildBean.getData();
                                if (newlyBuildBean.getTotalCount() != 0) {
                                    ll_visibi.setVisibility(View.GONE);
                                    scroll_content.setVisibility(View.VISIBLE);
                                    pageCount = newlyBuildBean.getTotalCount();
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    tvNewbuildPage.setText(count);
                                    buildAdapter = new FTYDLNewlyBuildAdapter(
                                            FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                    lv_newbuild_data.setAdapter(buildAdapter);
                                    leftAdapter = new FTYDLNewlyBuildLeftAdapter(
                                            FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                    lv_pleft.setAdapter(leftAdapter);
                                } else {
                                    ll_visibi.setVisibility(View.VISIBLE);
                                    scroll_content.setVisibility(View.GONE);
                                    tv_visibi.setText("没有更多数据");
                                }
                                ResponseDialog.closeLoading();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLSearchNewlyBuildActivity.this);
        }
    }

    /*根据工序及款号查找信息*/
    private void setPageDate() {
        sp = getSharedPreferences("my_sp", 0);
        String urlDaily = HttpUrl.debugoneUrl + "FactoryPlan/FactoryDailyAPP/";
        pagesize = sp.getString("clumnsFTYDLpageSize", "");
        if (pagesize.equals("")) {
            pagesize = String.valueOf(10);
        }
        String spinner = spinnerNewbuild.getText().toString();
        String editNewlyBuild = etNewbuild.getText().toString();//输入款号
        if (spinner.equals("选择工序") || spinner == null) {
            new AlertDialog.Builder(FTYDLSearchNewlyBuildActivity.this).setTitle("提示信息")
                    .setMessage("请选择工序,再查找款号信息")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();//相应事件
        } else {
            Gson gson = new Gson();
            final FTYDLNewlyBuildSearchBean buildBean = new FTYDLNewlyBuildSearchBean();
            FTYDLNewlyBuildSearchBean.Conditions conditions = buildBean.new Conditions();
            conditions.setItem(editNewlyBuild);
            conditions.setWorkingProcedure(spinner);
            buildBean.setConditions(conditions);
            buildBean.setPageNum(0);
            buildBean.setPageSize(Integer.parseInt(pagesize));
            final String bean = gson.toJson(buildBean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this, "正在查询");
                final int finalGetsize = Integer.parseInt(pagesize);
                OkHttpUtils.postString()
                        .url(urlDaily)
                        .content(bean)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                try {
                                    String ress = response.replace("\\", "");
                                    String ression = StringUtil.sideTrim(ress, "\"");
                                    newlyBuildBean = new Gson().fromJson(ression, FTYDLFactoryDailyBean.class);
                                    dataBeen = newlyBuildBean.getData();
                                    if (newlyBuildBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        pageCount = newlyBuildBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvNewbuildPage.setText(count);
                                        buildAdapter = new FTYDLNewlyBuildAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_newbuild_data.setAdapter(buildAdapter);
                                        leftAdapter = new FTYDLNewlyBuildLeftAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_pleft.setAdapter(leftAdapter);
                                        buildAdapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多数据");
                                    }
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLSearchNewlyBuildActivity.this);
            }
        }
    }

    /*根据工序查询信息*/
    private void setPagefistDate() {
        sp = getSharedPreferences("my_sp", 0);
        String urlDaily = HttpUrl.debugoneUrl + "FactoryPlan/FactoryDailyAPP/";
        String spinner = spinnerNewbuild.getText().toString();//工序
        String editNewlyBuild = etNewbuild.getText().toString();//输入款号
        pageIndex = Integer.parseInt(etNewbuildDetail.getText().toString());
        int ind = pageIndex - 1;
        String pagesize = sp.getString("clumnsFTYDLpageSize", "");
        if (pagesize.equals("")) {
            pagesize = String.valueOf(10);
        }
        if (spinner == "选择工序" || spinner.equals("选择工序")) {
            Gson gson = new Gson();
            final FTYDLNewlyBuildSearchBean buildBean = new FTYDLNewlyBuildSearchBean();
            FTYDLNewlyBuildSearchBean.Conditions conditions = buildBean.new Conditions();
            conditions.setItem("");
            conditions.setWorkingProcedure("");
            buildBean.setConditions(conditions);
            buildBean.setPageNum(ind);
            buildBean.setPageSize(Integer.parseInt(pagesize));
            final String bean = gson.toJson(buildBean);
            if (NetWork.isNetWorkAvailable(this)) {
               ResponseDialog.showLoading(this,"正在查询");
                final int finalGetsize = Integer.parseInt(pagesize);
                OkHttpUtils.postString()
                        .url(urlDaily)
                        .content(bean)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                try {
                                    String ress = response.replace("\\", "");
                                    String ression = StringUtil.sideTrim(ress, "\"");
                                    newlyBuildBean = new Gson().fromJson(ression, FTYDLFactoryDailyBean.class);
                                    dataBeen = newlyBuildBean.getData();
                                    if (newlyBuildBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        pageCount = newlyBuildBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvNewbuildPage.setText(count);
                                        buildAdapter = new FTYDLNewlyBuildAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_newbuild_data.setAdapter(buildAdapter);
                                        leftAdapter = new FTYDLNewlyBuildLeftAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_pleft.setAdapter(leftAdapter);
                                        buildAdapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多数据");
                                    }
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLSearchNewlyBuildActivity.this);
            }
        } else {
            Gson gson = new Gson();
            final FTYDLNewlyBuildSearchBean buildBean = new FTYDLNewlyBuildSearchBean();
            FTYDLNewlyBuildSearchBean.Conditions conditions = buildBean.new Conditions();
            conditions.setItem(editNewlyBuild);
            conditions.setWorkingProcedure(spinner);
            buildBean.setConditions(conditions);
            buildBean.setPageNum(ind);
            buildBean.setPageSize(Integer.parseInt(pagesize));
            final String bean = gson.toJson(buildBean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this,"正在查询");
                final int finalGetsize = Integer.parseInt(pagesize);
                OkHttpUtils.postString()
                        .url(urlDaily)
                        .content(bean)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                try {
                                    String ress = response.replace("\\", "");
                                    String ression = StringUtil.sideTrim(ress, "\"");
                                    newlyBuildBean = new Gson().fromJson(ression, FTYDLFactoryDailyBean.class);
                                    dataBeen = newlyBuildBean.getData();
                                    if (newlyBuildBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        pageCount = newlyBuildBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize);
                                        tvNewbuildPage.setText(count);
                                        buildAdapter = new FTYDLNewlyBuildAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_newbuild_data.setAdapter(buildAdapter);
                                        leftAdapter = new FTYDLNewlyBuildLeftAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_pleft.setAdapter(leftAdapter);
                                        buildAdapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多数据");
                                    }
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLSearchNewlyBuildActivity.this);
            }
        }
    }

    /*上一页下一页*/
    private void setPageUpDate(String pageupdateindex) {
        sp = getSharedPreferences("my_sp", 0);
        String urlDaily = HttpUrl.debugoneUrl + "FactoryPlan/FactoryDailyAPP/";
        String spinner = spinnerNewbuild.getText().toString();//工序
        String editNewlyBuild = etNewbuild.getText().toString();//输入款号
        pagesize = sp.getString("clumnsFTYDLpageSize", "");
        if (pagesize.equals("")) {
            pagesize = String.valueOf(10);
        }
        if (spinner == "选择工序" || spinner.equals("选择工序")) {
            Gson gson = new Gson();
            final FTYDLNewlyBuildSearchBean buildBean = new FTYDLNewlyBuildSearchBean();
            FTYDLNewlyBuildSearchBean.Conditions conditions = buildBean.new Conditions();
            conditions.setItem("");
            conditions.setWorkingProcedure("");
            buildBean.setConditions(conditions);
            buildBean.setPageNum(Integer.parseInt(pageupdateindex));
            buildBean.setPageSize(Integer.parseInt(pagesize));
            final String bean = gson.toJson(buildBean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this,"正在查询");
                final int finalGetsize = Integer.parseInt(pagesize);
                OkHttpUtils.postString()
                        .url(urlDaily)
                        .content(bean)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                try {
                                    String ress = response.replace("\\", "");
                                    String ression = StringUtil.sideTrim(ress, "\"");
                                    newlyBuildBean = new Gson().fromJson(ression, FTYDLFactoryDailyBean.class);
                                    dataBeen = newlyBuildBean.getData();
                                    if (newlyBuildBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        pageCount = newlyBuildBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvNewbuildPage.setText(count);
                                        buildAdapter = new FTYDLNewlyBuildAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_newbuild_data.setAdapter(buildAdapter);
                                        leftAdapter = new FTYDLNewlyBuildLeftAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_pleft.setAdapter(leftAdapter);
                                        buildAdapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多数据");
                                    }
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLSearchNewlyBuildActivity.this);
            }
        } else {
            Gson gson = new Gson();
            final FTYDLNewlyBuildSearchBean buildBean = new FTYDLNewlyBuildSearchBean();
            FTYDLNewlyBuildSearchBean.Conditions conditions = buildBean.new Conditions();
            conditions.setItem(editNewlyBuild);
            conditions.setWorkingProcedure(spinner);
            buildBean.setConditions(conditions);
            buildBean.setPageNum(Integer.parseInt(pageupdateindex));
            buildBean.setPageSize(Integer.parseInt(pagesize));
            final String bean = gson.toJson(buildBean);
            if (NetWork.isNetWorkAvailable(this)) {
                ResponseDialog.showLoading(this,"正在查询");
                final int finalGetsize = Integer.parseInt(pagesize);
                OkHttpUtils.postString()
                        .url(urlDaily)
                        .content(bean)
                        .mediaType(MediaType.parse("application/json;charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                try {
                                    String ress = response.replace("\\", "");
                                    String ression = StringUtil.sideTrim(ress, "\"");
                                    newlyBuildBean = new Gson().fromJson(ression, FTYDLFactoryDailyBean.class);
                                    dataBeen = newlyBuildBean.getData();
                                    if (newlyBuildBean.getTotalCount() != 0) {
                                        ll_visibi.setVisibility(View.GONE);
                                        scroll_content.setVisibility(View.VISIBLE);
                                        pageCount = newlyBuildBean.getTotalCount();
                                        String count = String.valueOf(pageCount / finalGetsize + 1);
                                        tvNewbuildPage.setText(count);
                                        buildAdapter = new FTYDLNewlyBuildAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_newbuild_data.setAdapter(buildAdapter);
                                        leftAdapter = new FTYDLNewlyBuildLeftAdapter(
                                                FTYDLSearchNewlyBuildActivity.this, dataBeen);
                                        lv_pleft.setAdapter(leftAdapter);
                                        buildAdapter.notifyDataSetChanged();
                                    } else {
                                        ll_visibi.setVisibility(View.VISIBLE);
                                        scroll_content.setVisibility(View.GONE);
                                        tv_visibi.setText("没有更多数据");
                                    }
                                    ResponseDialog.closeLoading();
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ResponseDialog.closeLoading();
                                }
                            }
                        });
            } else {
                ToastUtils.ShowToastMessage(R.string.noHttp, FTYDLSearchNewlyBuildActivity.this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回按键*/
            case R.id.ivProductionBack:
                sethideSoft(v);
                finish();
                break;
            /*选择工序*/
            case R.id.spinnerNewbuild:
                sethideSoft(v);
                PopupMenu popupMenu = new PopupMenu(FTYDLSearchNewlyBuildActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_spinnernew, popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        spUtils.put(FTYDLSearchNewlyBuildActivity.this, "spinnerNewbuild", title);
                        spinnerNewbuild.setText(title);
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
                break;
            /*翻页确定按钮*/
            case R.id.btnNewbuildPage:
                sethideSoft(v);
                String countpage = tvNewbuildPage.getText().toString();
                String text = etNewbuildDetail.getText().toString();
                if (text.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchNewlyBuildActivity.this);
                } else {
                    int txtindex = Integer.parseInt(text);
                    int txtcountindex = Integer.parseInt(countpage);
                    if (txtindex > txtcountindex) {
                        ToastUtils.ShowToastMessage("已经是最后一页", FTYDLSearchNewlyBuildActivity.this);
                    } else if (txtindex < 1) {
                        ToastUtils.ShowToastMessage("已经是第一页", FTYDLSearchNewlyBuildActivity.this);
                    } else if (text.length() == 0) {
                        ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchNewlyBuildActivity.this);
                        return;
                    } else if (text.length() > countpage.length()) {
                        ToastUtils.ShowToastMessage("页码超出输入范围", FTYDLSearchNewlyBuildActivity.this);
                    } else {
                        setPagefistDate();
                    }
                }
                break;
            /*根据工序及款号查找信息*/
            case R.id.etNewbuildSql:
                sethideSoft(v);
                setPageDate();
                break;
            /*上一页*/
            case R.id.ivUpLeftPage:
                sethideSoft(v);
                String texteditstr = etNewbuildDetail.getText().toString();
                if (texteditstr.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchNewlyBuildActivity.this);
                } else {
                    int textedit = Integer.parseInt(texteditstr);
                    int texteditindex = textedit - 2;
                    if (texteditindex < 0) {
                        ToastUtils.ShowToastMessage("已经是第一页", FTYDLSearchNewlyBuildActivity.this);
                    } else {
                        String indexstr = String.valueOf(texteditindex);
                        int indexcount = texteditindex + 1;
                        etNewbuildDetail.setText(String.valueOf(indexcount));
                        etNewbuildDetail.setSelection(String.valueOf(indexcount).length());
                        setPageUpDate(indexstr);
                    }
                }
                break;
            /*下一页*/
            case R.id.ivDownRightPage:
                sethideSoft(v);
                String texteditstr2 = etNewbuildDetail.getText().toString();
                if (texteditstr2.equals("")) {
                    ToastUtils.ShowToastMessage("页码不能为空", FTYDLSearchNewlyBuildActivity.this);
                } else {
                    int textedit2 = Integer.parseInt(texteditstr2);
                    int index2 = textedit2;
                    String countpageindex = tvNewbuildPage.getText().toString();
                    int indexmax = Integer.parseInt(countpageindex);
                    int index3 = index2 + 1;
                    if (index3 > indexmax) {
                        ToastUtils.ShowToastMessage("已经是最后一页", FTYDLSearchNewlyBuildActivity.this);
                    } else {
                        String indexstr = String.valueOf(index2);
                        int indexcount = index2 + 1;
                        etNewbuildDetail.setText(String.valueOf(indexcount));
                        etNewbuildDetail.setSelection(String.valueOf(indexcount).length());
                        setPageUpDate(indexstr);
                    }
                }
                break;
            /*横竖屏切换*/
            case R.id.ivNewlyFilp:
                sethideSoft(v);
                if (configid.equals("1")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (configid.equals("2")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                }
                break;
        }
    }

    /*判断软键盘是否弹出*/
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

    /*禁止EditText输入特殊字符*/
    public static void setEditTextInhibitInputSpeChat(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (source.equals(" ") || source.equals("\n") || matcher.find())
                    return "";
                else
                    return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}