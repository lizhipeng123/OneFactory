package com.daoran.newfactory.onefactory.activity.work.pocontrast;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.pocontrastadpter.PoContrastAdapter;
import com.daoran.newfactory.onefactory.adapter.pocontrastadpter.PoContrastLeftAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.pocontrastbean.PoContrastEntyBean;
import com.daoran.newfactory.onefactory.bean.pocontrastbean.PoContrastModBean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by luoqf on 2017/11/28.
 * 生产单物料审核表
 */

public class PoContrastActivity extends BaseFrangmentActivity implements View.OnClickListener {
    //region 控件声明
    private ImageView ivPoContrast;
    private View viewtitletwo;
    private ImageView ivSearch;
    private TextView tvPoContrastTitle;
    private View viewtitle;
    private Button spinnermenu;
    private TextView tvAsser;
    private TextView tvAsser2;
    private TextView tvAsser3;
    private TextView tvItem;
    private TextView tvMtr;
    private LinearLayout linHeaderContent;
    private SyncHorizontalScrollView headerHorizontal;
    private LinearLayout linYearTitle;
    private LinearLayout llVisibi;
    private TextView tvVisibi;
    private ScrollView scrollContent;
    private NoscrollListView lvCleft;
    private LinearLayout linDataContent;
    private SyncHorizontalScrollView dataHorizontal;
    private NoscrollListView lvData;
    private ImageView ivUpLeftPage;
    private EditText etSqlDetail;
    private TextView tvSignPage;
    private Button btnSignPage;
    private Spinner spinnCommoPageClumns;
    private TextView textView4;
    private ImageView ivDownRightPage;
    //endregion
    public static PoContrastActivity instance = null;
    private PoContrastEntyBean poContrastEntyBean;//列表实体bean
    private List<PoContrastEntyBean.DataBean> dataBeenList = new ArrayList<PoContrastEntyBean.DataBean>();//查货信息实体集合
    private PoContrastAdapter poContrastAdapter;//列表适配
    private PoContrastLeftAdapter poContrastLeftAdapter;

    private int pageCount;//查询获取的总页数
    private String configid;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocontrast);//加载主页面
        instance = this;
        configid="1";
        getViews();
        initView();
        setData();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * 初始化控件
     */
    private void getViews() {
        ivPoContrast = (ImageView) findViewById(R.id.ivPoContrast);
        viewtitletwo = (View) findViewById(R.id.viewtitletwo);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        tvPoContrastTitle = (TextView) findViewById(R.id.tvPoContrastTitle);
        viewtitle = (View) findViewById(R.id.viewtitle);
        spinnermenu = (Button) findViewById(R.id.spinnermenu);
        linHeaderContent = (LinearLayout) findViewById(R.id.lin_header_content);
        headerHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        linYearTitle = (LinearLayout) findViewById(R.id.lin_year_title);
        llVisibi = (LinearLayout) findViewById(R.id.ll_visibi);
        tvVisibi = (TextView) findViewById(R.id.tv_visibi);
        scrollContent = (ScrollView) findViewById(R.id.scroll_content);
        lvCleft = (NoscrollListView) findViewById(R.id.lv_cleft);
        linDataContent = (LinearLayout) findViewById(R.id.lin_data_content);
        dataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        lvData = (NoscrollListView) findViewById(R.id.lv_data);
        ivUpLeftPage = (ImageView) findViewById(R.id.ivUpLeftPage);
        etSqlDetail = (EditText) findViewById(R.id.etSqlDetail);
        tvSignPage = (TextView) findViewById(R.id.tvSignPage);
        btnSignPage = (Button) findViewById(R.id.btnSignPage);
        spinnCommoPageClumns = (Spinner) findViewById(R.id.spinnCommoPageClumns);
        textView4 = (TextView) findViewById(R.id.textView4);
        ivDownRightPage = (ImageView) findViewById(R.id.ivDownRightPage);
    }

    /**
     * 控件操作
     */
    private void initView() {
        dataHorizontal.setSrollView(headerHorizontal);
        headerHorizontal.setSrollView(dataHorizontal);
        etSqlDetail.setSelection(etSqlDetail.getText().length());
        spinnermenu.setOnClickListener(this);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("info", "landscape"); // 横屏
            configid = String.valueOf(1);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("info", "portrait"); // 竖屏
            configid = String.valueOf(2);
        }
    }

    /**
     * 控件操作
     */
    public void setData() {
        String strUrl = HttpUrl.debugoneUrl + "PO/BindGridPoContrast/";
        String pagesize = String.valueOf(10);
        Gson gson = new Gson();

        //将获取到的数据放入bean中
        PoContrastModBean poMBean = new PoContrastModBean();
        PoContrastModBean.Conditions conditions = poMBean.new Conditions();
        poMBean.setPageNum(0);
        poMBean.setPageSize(Integer.parseInt(pagesize));
        conditions.setCod("INTDLM040");
        conditions.setSelect("");
        poMBean.setConditions(conditions);
        //将bean中的数据转成json数据
        String strPoMBean = gson.toJson(poMBean);
        if (NetWork.isNetWorkAvailable(this)) {
            final ProgressDialog progressDialog = ProgressDialog.show(this,
                    "请稍候...", "正在查询中...", false, true);
            final int finalGetsize = Integer.parseInt(pagesize);
            OkHttpUtils.postString()
                    .url(strUrl)
                    .content(strPoMBean)
                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
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
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        progressDialog.dismiss();
                                    }
                                });
                                thread.start();
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                poContrastEntyBean = new Gson().fromJson(ression, PoContrastEntyBean.class);
                                dataBeenList = poContrastEntyBean.getData();
                                //判断得到的数据是否为空,决定要显示的页面
                                if (poContrastEntyBean.getTotalCount() != 0) {
                                    llVisibi.setVisibility(View.GONE);
                                    scrollContent.setVisibility(View.VISIBLE);
                                    System.out.print(dataBeenList);
                                    pageCount = poContrastEntyBean.getTotalCount();
                                    String count = String.valueOf(pageCount / finalGetsize + 1);
                                    tvSignPage.setText(count);
                                    poContrastAdapter = new PoContrastAdapter(PoContrastActivity.this, dataBeenList);
                                    lvData.setAdapter(poContrastAdapter);
                                    poContrastLeftAdapter = new PoContrastLeftAdapter(PoContrastActivity.this, dataBeenList);
                                    lvCleft.setAdapter(poContrastLeftAdapter);
                                } else {
                                    llVisibi.setVisibility(View.VISIBLE);
                                    scrollContent.setVisibility(View.GONE);
                                    tvVisibi.setText("没有更多数据");
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1000);
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
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", PoContrastActivity.this);
        }

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PoContrast Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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
}