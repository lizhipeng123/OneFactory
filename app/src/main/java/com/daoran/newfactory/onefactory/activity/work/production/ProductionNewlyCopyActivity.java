package com.daoran.newfactory.onefactory.activity.work.production;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProducationNewlyComfigSaveBean;
import com.daoran.newfactory.onefactory.bean.ProductionNewlybooleanBean;
import com.daoran.newfactory.onefactory.bean.Propostbean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PhoneSaveUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 新建保存
 * Created by lizhipeng on 2017/5/9.
 */

public class ProductionNewlyCopyActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private static final String TAG = "configtest";
    private AlertDialog noticeDialog;
    private NoscrollListView mData;
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack;
    private List<ProductionNewlybooleanBean.DataBean> booleandatelist =
            new ArrayList<ProductionNewlybooleanBean.DataBean>();
    private ProductionNewlybooleanBean newlybooleanBean;
    private List<ProducationNewlyComfigSaveBean> newlyComfigSaveBeen
            = new ArrayList<ProducationNewlyComfigSaveBean>();
    private Button btnProSave;
    private MyAdatper comfigAdapter;
    private TextView tvconfigone, tvconfigtwo, tvconfigthree, tvconfigfore, tvconfigfive,
            tvconfigsix, tvconfigseven, tvconfigeight, tvconfignine, tvconfigten, tvconfigeleven,
            tvconfigtwtlve, tvconfigtwthreeteen, tvconfigtwforeteen, tvconfigtwfifteen,
            tvconfigtwsixteen, tvconfigtwseventeen, tvconfigtweightteen,
            tvconfigtwnineteen, tvconfigtwTwenty, tvconfigtwTwentyone,
            tvconfigtwTwentytwo, tvconfigtwTwentythree, tvconfigtwTwentyfore,
            tvconfigtwTwentyfive, tvconfigtwTwentysix, tvconfigtwTwentyseven,
            tvconfigtwTwentyeight, tvconfigtwTwentynine, tvconfigtwThirty, tvconfigtwThirtyone;
    private View vconfigone, vconfigtwo, vconfigthree, vconfigfore, vconfigfive,
            vconfigsix, vconfigseven, vconfigeight, vconfignine, vconfigten, vconfigeleven,
            vconfigtwtlve, vconfigtwthreeteen, vconfigtwforeteen, vconfigtwfifteen,
            vconfigtwsixteen, vconfigtwseventeen, vconfigtweightteen,
            vconfigtwnineteen, vconfigtwTwenty, vconfigtwTwentyone,
            vconfigtwTwentytwo, vconfigtwTwentythree, vconfigtwTwentyfore,
            vconfigtwTwentyfive, vconfigtwTwentysix, vconfigtwTwentyseven,
            vconfigtwTwentyeight, vconfigtwTwentynine, vconfigtwThirty, vconfigtwThirtyone;
    private SharedPreferences sp;
    private SPUtils spUtils;
    private List<Map<String, Object>> mdate;
    private int year, month, datetime, hour, minute, second;
    int isprodure;
    private String listItemm, listPrecoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_config);
        getViews();
        initViews();
        setNewlyComfig();
        setListener();
        sp = getSharedPreferences("my_sp", 0);
        String isprodure = sp.getString("isprodure", "");
        //如果是裁床，则隐藏1~31日填写信息
        if (isprodure.equals("0")) {
            tvconfigone.setVisibility(View.VISIBLE);
            tvconfigtwo.setVisibility(View.VISIBLE);
            tvconfigthree.setVisibility(View.VISIBLE);
            tvconfigfore.setVisibility(View.VISIBLE);
            tvconfigfive.setVisibility(View.VISIBLE);
            tvconfigsix.setVisibility(View.VISIBLE);
            tvconfigseven.setVisibility(View.VISIBLE);
            tvconfigeight.setVisibility(View.VISIBLE);
            tvconfignine.setVisibility(View.VISIBLE);
            tvconfigten.setVisibility(View.VISIBLE);
            tvconfigeleven.setVisibility(View.VISIBLE);
            tvconfigtwtlve.setVisibility(View.VISIBLE);
            tvconfigtwthreeteen.setVisibility(View.VISIBLE);
            tvconfigtwforeteen.setVisibility(View.VISIBLE);
            tvconfigtwfifteen.setVisibility(View.VISIBLE);
            tvconfigtwsixteen.setVisibility(View.VISIBLE);
            tvconfigtwseventeen.setVisibility(View.VISIBLE);
            tvconfigtweightteen.setVisibility(View.VISIBLE);
            tvconfigtwnineteen.setVisibility(View.VISIBLE);
            tvconfigtwTwenty.setVisibility(View.VISIBLE);
            tvconfigtwTwentyone.setVisibility(View.VISIBLE);
            tvconfigtwTwentytwo.setVisibility(View.VISIBLE);
            tvconfigtwTwentythree.setVisibility(View.VISIBLE);
            tvconfigtwTwentyfore.setVisibility(View.VISIBLE);
            tvconfigtwTwentyfive.setVisibility(View.VISIBLE);
            tvconfigtwTwentysix.setVisibility(View.VISIBLE);
            tvconfigtwTwentyseven.setVisibility(View.VISIBLE);
            tvconfigtwTwentyeight.setVisibility(View.VISIBLE);
            tvconfigtwTwentynine.setVisibility(View.VISIBLE);
            tvconfigtwThirty.setVisibility(View.VISIBLE);
            tvconfigtwThirtyone.setVisibility(View.VISIBLE);
            vconfigone.setVisibility(View.VISIBLE);
            vconfigtwo.setVisibility(View.VISIBLE);
            vconfigthree.setVisibility(View.VISIBLE);
            vconfigfore.setVisibility(View.VISIBLE);
            vconfigfive.setVisibility(View.VISIBLE);
            vconfigsix.setVisibility(View.VISIBLE);
            vconfigseven.setVisibility(View.VISIBLE);
            vconfigeight.setVisibility(View.VISIBLE);
            vconfignine.setVisibility(View.VISIBLE);
            vconfigten.setVisibility(View.VISIBLE);
            vconfigeleven.setVisibility(View.VISIBLE);
            vconfigtwtlve.setVisibility(View.VISIBLE);
            vconfigtwthreeteen.setVisibility(View.VISIBLE);
            vconfigtwforeteen.setVisibility(View.VISIBLE);
            vconfigtwfifteen.setVisibility(View.VISIBLE);
            vconfigtwsixteen.setVisibility(View.VISIBLE);
            vconfigtwseventeen.setVisibility(View.VISIBLE);
            vconfigtweightteen.setVisibility(View.VISIBLE);
            vconfigtwnineteen.setVisibility(View.VISIBLE);
            vconfigtwTwenty.setVisibility(View.VISIBLE);
            vconfigtwTwentyone.setVisibility(View.VISIBLE);
            vconfigtwTwentytwo.setVisibility(View.VISIBLE);
            vconfigtwTwentythree.setVisibility(View.VISIBLE);
            vconfigtwTwentyfore.setVisibility(View.VISIBLE);
            vconfigtwTwentyfive.setVisibility(View.VISIBLE);
            vconfigtwTwentysix.setVisibility(View.VISIBLE);
            vconfigtwTwentyseven.setVisibility(View.VISIBLE);
            vconfigtwTwentyeight.setVisibility(View.VISIBLE);
            vconfigtwTwentynine.setVisibility(View.VISIBLE);
            vconfigtwThirty.setVisibility(View.VISIBLE);
            vconfigtwThirtyone.setVisibility(View.VISIBLE);
        }
        if (isprodure.equals("1")) {
            tvconfigone.setVisibility(View.GONE);
            tvconfigtwo.setVisibility(View.GONE);
            tvconfigthree.setVisibility(View.GONE);
            tvconfigfore.setVisibility(View.GONE);
            tvconfigfive.setVisibility(View.GONE);
            tvconfigsix.setVisibility(View.GONE);
            tvconfigseven.setVisibility(View.GONE);
            tvconfigeight.setVisibility(View.GONE);
            tvconfignine.setVisibility(View.GONE);
            tvconfigten.setVisibility(View.GONE);
            tvconfigeleven.setVisibility(View.GONE);
            tvconfigtwtlve.setVisibility(View.GONE);
            tvconfigtwthreeteen.setVisibility(View.GONE);
            tvconfigtwforeteen.setVisibility(View.GONE);
            tvconfigtwfifteen.setVisibility(View.GONE);
            tvconfigtwsixteen.setVisibility(View.GONE);
            tvconfigtwseventeen.setVisibility(View.GONE);
            tvconfigtweightteen.setVisibility(View.GONE);
            tvconfigtwnineteen.setVisibility(View.GONE);
            tvconfigtwTwenty.setVisibility(View.GONE);
            tvconfigtwTwentyone.setVisibility(View.GONE);
            tvconfigtwTwentytwo.setVisibility(View.GONE);
            tvconfigtwTwentythree.setVisibility(View.GONE);
            tvconfigtwTwentyfore.setVisibility(View.GONE);
            tvconfigtwTwentyfive.setVisibility(View.GONE);
            tvconfigtwTwentysix.setVisibility(View.GONE);
            tvconfigtwTwentyseven.setVisibility(View.GONE);
            tvconfigtwTwentyeight.setVisibility(View.GONE);
            tvconfigtwTwentynine.setVisibility(View.GONE);
            tvconfigtwThirty.setVisibility(View.GONE);
            tvconfigtwThirtyone.setVisibility(View.GONE);
            vconfigone.setVisibility(View.GONE);
            vconfigtwo.setVisibility(View.GONE);
            vconfigthree.setVisibility(View.GONE);
            vconfigfore.setVisibility(View.GONE);
            vconfigfive.setVisibility(View.GONE);
            vconfigsix.setVisibility(View.GONE);
            vconfigseven.setVisibility(View.GONE);
            vconfigeight.setVisibility(View.GONE);
            vconfignine.setVisibility(View.GONE);
            vconfigten.setVisibility(View.GONE);
            vconfigeleven.setVisibility(View.GONE);
            vconfigtwtlve.setVisibility(View.GONE);
            vconfigtwthreeteen.setVisibility(View.GONE);
            vconfigtwforeteen.setVisibility(View.GONE);
            vconfigtwfifteen.setVisibility(View.GONE);
            vconfigtwsixteen.setVisibility(View.GONE);
            vconfigtwseventeen.setVisibility(View.GONE);
            vconfigtweightteen.setVisibility(View.GONE);
            vconfigtwnineteen.setVisibility(View.GONE);
            vconfigtwTwenty.setVisibility(View.GONE);
            vconfigtwTwentyone.setVisibility(View.GONE);
            vconfigtwTwentytwo.setVisibility(View.GONE);
            vconfigtwTwentythree.setVisibility(View.GONE);
            vconfigtwTwentyfore.setVisibility(View.GONE);
            vconfigtwTwentyfive.setVisibility(View.GONE);
            vconfigtwTwentysix.setVisibility(View.GONE);
            vconfigtwTwentyseven.setVisibility(View.GONE);
            vconfigtwTwentyeight.setVisibility(View.GONE);
            vconfigtwTwentynine.setVisibility(View.GONE);
            vconfigtwThirty.setVisibility(View.GONE);
            vconfigtwThirtyone.setVisibility(View.GONE);
        }
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        btnProSave = (Button) findViewById(R.id.btnProSave);
        tvconfigone = (TextView) findViewById(R.id.tvconfigone);
        tvconfigtwo = (TextView) findViewById(R.id.tvconfigtwo);
        tvconfigthree = (TextView) findViewById(R.id.tvconfigthree);
        tvconfigfore = (TextView) findViewById(R.id.tvconfigfore);
        tvconfigfive = (TextView) findViewById(R.id.tvconfigfive);
        tvconfigsix = (TextView) findViewById(R.id.tvconfigsix);
        tvconfigseven = (TextView) findViewById(R.id.tvconfigseven);
        tvconfigeight = (TextView) findViewById(R.id.tvconfigeight);
        tvconfignine = (TextView) findViewById(R.id.tvconfignine);
        tvconfigten = (TextView) findViewById(R.id.tvconfigten);
        tvconfigeleven = (TextView) findViewById(R.id.tvconfigeleven);
        tvconfigtwtlve = (TextView) findViewById(R.id.tvconfigtwtlve);
        tvconfigtwthreeteen = (TextView) findViewById(R.id.tvconfigtwthreeteen);
        tvconfigtwforeteen = (TextView) findViewById(R.id.tvconfigtwforeteen);
        tvconfigtwfifteen = (TextView) findViewById(R.id.tvconfigtwfifteen);
        tvconfigtwsixteen = (TextView) findViewById(R.id.tvconfigtwsixteen);
        tvconfigtwseventeen = (TextView) findViewById(R.id.tvconfigtwseventeen);
        tvconfigtweightteen = (TextView) findViewById(R.id.tvconfigtweightteen);
        tvconfigtwnineteen = (TextView) findViewById(R.id.tvconfigtwnineteen);
        tvconfigtwTwenty = (TextView) findViewById(R.id.tvconfigtwTwenty);
        tvconfigtwTwentyone = (TextView) findViewById(R.id.tvconfigtwTwentyone);
        tvconfigtwTwentytwo = (TextView) findViewById(R.id.tvconfigtwTwentytwo);
        tvconfigtwTwentythree = (TextView) findViewById(R.id.tvconfigtwTwentythree);
        tvconfigtwTwentyfore = (TextView) findViewById(R.id.tvconfigtwTwentyfore);
        tvconfigtwTwentyfive = (TextView) findViewById(R.id.tvconfigtwTwentyfive);
        tvconfigtwTwentysix = (TextView) findViewById(R.id.tvconfigtwTwentysix);
        tvconfigtwTwentyseven = (TextView) findViewById(R.id.tvconfigtwTwentyseven);
        tvconfigtwTwentyeight = (TextView) findViewById(R.id.tvconfigtwTwentyeight);
        tvconfigtwTwentynine = (TextView) findViewById(R.id.tvconfigtwTwentynine);
        tvconfigtwThirty = (TextView) findViewById(R.id.tvconfigtwThirty);
        tvconfigtwThirtyone = (TextView) findViewById(R.id.tvconfigtwThirtyone);
        vconfigone = findViewById(R.id.vconfigone);
        vconfigtwo = findViewById(R.id.vconfigtwo);
        vconfigthree = findViewById(R.id.vconfigthree);
        vconfigfore = findViewById(R.id.vconfigfore);
        vconfigfive = findViewById(R.id.vconfigfive);
        vconfigsix = findViewById(R.id.vconfigsix);
        vconfigseven = findViewById(R.id.vconfigseven);
        vconfigeight = findViewById(R.id.vconfigeight);
        vconfignine = findViewById(R.id.vconfignine);
        vconfigten = findViewById(R.id.vconfigten);
        vconfigeleven = findViewById(R.id.vconfigeleven);
        vconfigtwtlve = findViewById(R.id.vconfigtwtlve);
        vconfigtwthreeteen = findViewById(R.id.vconfigtwthreeteen);
        vconfigtwforeteen = findViewById(R.id.vconfigtwforeteen);
        vconfigtwfifteen = findViewById(R.id.vconfigtwfifteen);
        vconfigtwsixteen = findViewById(R.id.vconfigtwsixteen);
        vconfigtwseventeen = findViewById(R.id.vconfigtwseventeen);
        vconfigtweightteen = findViewById(R.id.vconfigtweightteen);
        vconfigtwnineteen = findViewById(R.id.vconfigtwnineteen);
        vconfigtwTwenty = findViewById(R.id.vconfigtwTwenty);
        vconfigtwTwentyone = findViewById(R.id.vconfigtwTwentyone);
        vconfigtwTwentytwo = findViewById(R.id.vconfigtwTwentytwo);
        vconfigtwTwentythree = findViewById(R.id.vconfigtwTwentythree);
        vconfigtwTwentyfore = findViewById(R.id.vconfigtwTwentyfore);
        vconfigtwTwentyfive = findViewById(R.id.vconfigtwTwentyfive);
        vconfigtwTwentysix = findViewById(R.id.vconfigtwTwentysix);
        vconfigtwTwentyseven = findViewById(R.id.vconfigtwTwentyseven);
        vconfigtwTwentyeight = findViewById(R.id.vconfigtwTwentyeight);
        vconfigtwTwentynine = findViewById(R.id.vconfigtwTwentynine);
        vconfigtwThirty = findViewById(R.id.vconfigtwThirty);
        vconfigtwThirtyone = findViewById(R.id.vconfigtwThirtyone);
    }

    /**
     * 控件操作
     */
    private void initViews() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mdate = getData();
        comfigAdapter = new MyAdatper(this);
        mData.setAdapter(comfigAdapter);
    }

    /**
     * 点击事件
     */
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        btnProSave.setOnClickListener(this);
    }

    /**
     * 保存list数据
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        sp = getSharedPreferences("my_sp", 0);
        String tvnewlydate = sp.getString("tvnewlydate", "");//款号
        String tvnewlyDocumentary = sp.getString("tvnewlyDocumentary", "");//跟单
        String tvnewlyFactory = sp.getString("tvnewlyFactory", "");//工厂
        String tvnewlyDepartment = sp.getString("tvnewlyDepartment", "");//部门/组别
        String tvnewlyProcedure = sp.getString("tvnewlyProcedure", "");//工序
        String tvnewlyOthers = sp.getString("tvnewlyOthers", "");//组别人数
        String tvnewSingularSystem = sp.getString("tvnewSingularSystem", "");//制单数
        String tvdate = sp.getString("tvColorTaskqty", "");//任务数
        String tvnewTaskNumber = sp.getString("tvnewTaskNumber", "");//尺码
        String tvnewlySize = sp.getString("tvnewlySize", "");//花色
        String tvnewlyClippingNumber = sp.getString("tvnewlyClippingNumber", "");//实裁数
        String tvnewlyCompletedLastMonth = sp.getString("tvnewlyCompletedLastMonth", "");//总完工数
        String tvnewlyTotalCompletion = sp.getString("tvnewlyTotalCompletion", "");//状态
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tvnewlydate", tvnewlydate);
        map.put("tvnewlyDocumentary", tvnewlyDocumentary);
        map.put("tvnewlyFactory", tvnewlyFactory);
        map.put("tvnewlyDepartment", tvnewlyDepartment);
        map.put("tvnewlyProcedure", tvnewlyProcedure);
        map.put("tvnewlyOthers", tvnewlyOthers);
        map.put("tvnewSingularSystem", tvnewSingularSystem);
        map.put("tvColorTaskqty", tvdate);
        map.put("tvnewTaskNumber", tvnewTaskNumber);
        map.put("tvnewlySize", tvnewlySize);
        map.put("tvnewlyClippingNumber", tvnewlyClippingNumber);
        map.put("tvnewlyCompletedLastMonth", tvnewlyCompletedLastMonth);
        map.put("tvnewlyTotalCompletion", tvnewlyTotalCompletion);
        list.add(map);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("tvnewlySize");
        editor.commit();
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回*/
            case R.id.ivProductionBack:
                sethideSoft(v);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("保存提示");
                builder.setMessage("退出是否保存");
                builder.setPositiveButton("保存后退出"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setSave();
                                dialog.dismiss();
                            }
                        });
                builder.setNegativeButton("不保存，直接退出",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                noticeDialog = builder.create();
                noticeDialog.setCanceledOnTouchOutside(false);
                noticeDialog.show();
                break;
            /*复制保存*/
            case R.id.btnProSave:
                sethideSoft(v);
                setSave();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //判断软件盘是否弹出
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                if (imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0)) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                            0);
                } else {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                            0);
                }
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("保存提示");
            builder.setMessage("退出是否保存");
            builder.setPositiveButton("保存后退出"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setSave();
                            dialog.dismiss();
                        }
                    });
            builder.setNegativeButton("不保存，直接退出",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            noticeDialog = builder.create();
            noticeDialog.setCanceledOnTouchOutside(false);
            noticeDialog.show();
        }
        return false;
    }

    /**
     * 判断软键盘是否弹出
     * @param v
     */
    private void sethideSoft(View v){
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

    /**
     * 复制保存
     */
    private void setSave() {
        String saveurl = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
        sp = this.getSharedPreferences("my_sp", 0);
        SharedPreferences spes = getSharedPreferences("mylist", 0);
        String liststr = spes.getString("mylistStr", "");
        String tvnewlySizes = sp.getString("tvnewlySize", "");//花色
        String salesid = sp.getString("tvnewlysalesid", "");//款号id
        String recordid = sp.getString("username", "");//制单人id
        String proColumnTitle = sp.getString("Configdepartment", "");//部门
        String proadaptertitle = sp.getString("tvnewlyDepartment", "");//
        String columntitle;//部门组别变量
        if (proColumnTitle.equals("")) {
            columntitle = proadaptertitle;
        } else {
            columntitle = proColumnTitle;
        }
        String proProcedureadapterTitle = sp.getString("ConfigProcedure", "");//工序adapter中修改过的
        String proprocudureTitle = sp.getString("tvnewlyProcedure", "");//从款号选择传过来的工序
        String procudureTitle;//工序变量
        if (proprocudureTitle.equals("选择工序") && proProcedureadapterTitle.equals("")) {
            procudureTitle = "";
        } else if (!proProcedureadapterTitle.equals("")) {
            procudureTitle = proProcedureadapterTitle;
        } else {
            procudureTitle = proprocudureTitle;
        }
        String productionRecorder = sp.getString("configrecorder", "");//制单人
        int listsize = booleandatelist.size();
        sp = getSharedPreferences("my_sp", 0);
        String tvnewlydate = sp.getString("tvnewlydate", "");
        for (int i = 0; i < listsize; i++) {
            listItemm = booleandatelist.get(i).getItem();
            listPrecoder = booleandatelist.get(i).getWorkingProcedure();
            if (procudureTitle.equals(listPrecoder) && tvnewlydate.equals(listItemm)) {
                ToastUtils.ShowToastMessage("已存在相同的款号", ProductionNewlyCopyActivity.this);
            } else {
                String proPrdstatusTitle = sp.getString("ComfigPrdstatus", "");//状态//
                String productionItem = sp.getString("comfigitem", "");//款号
                String productionDocumentary = sp.getString("configdocument", "");//跟单//
                String productionFactory = sp.getString("configfactory", "");//工厂
                String productionOthers = sp.getString("ConfigOthers", "");//组别人数
                String productionSingularSystem = sp.getString("configsingular", "");//制单数//
                String productionColor = sp.getString("configcolor", "");//花色
                String productionTaskNumber = sp.getString("ConfigTaskNumber", "");//任务数
                String productionSize = sp.getString("configsize", "");//尺码
                String productionClippingNumber = sp.getString("configclipping", "");//实裁数
                String productionCompletedLastMonth = sp.getString("ConfigLastMonth", "");//上月完工
                String productionTotalCompletion = sp.getString("configcompletion", "");//总完工数
                String productionBalanceAmount = sp.getString("configamount", "");//结余数量
                String productionYear = sp.getString("configyear", "");//年
                String productionMonth = sp.getString("ComfigMonth", "");//月
                String productionOneDay = sp.getString("configOneDay", "");//1
                String productionTwoDay = sp.getString("configTwoDay", "");//2
                String productionThreeDay = sp.getString("configThreeDay", "");//3
                String productionForeDay = sp.getString("configForeDay", "");//4
                String productionFiveDay = sp.getString("configFiveDay", "");//5
                String productionSixDay = sp.getString("configSixDay", "");//6
                String productionSevenDay = sp.getString("configSevenDay", "");//7
                String productionEightDay = sp.getString("configEightDay", "");//8
                String productionNineDay = sp.getString("configNineDay", "");//9
                String productionTenDay = sp.getString("configTenDay", "");//10
                String productionElevenDay = sp.getString("configElevenDay", "");//11
                String productionTwelveDay = sp.getString("configTwelveDay", "");//12
                String productionThirteenDay = sp.getString("configThirteenDay", "");//13
                String productionFourteenDay = sp.getString("configFourteenDay", "");//14
                String productionFifteenDay = sp.getString("configFifteenDay", "");//15
                String productionSixteenDay = sp.getString("configSixteenDay", "");//16
                String productionSeventeenDay = sp.getString("configSeventeenDay", "");//17
                String productionEighteenDay = sp.getString("configEighteenDay", "");//18
                String productionNineteenDay = sp.getString("configNineteenDay", "");//19
                String productionTwentyDay = sp.getString("configTwentyDay", "");//20
                String productionTwentyOneDay = sp.getString("configTwentyOneDay", "");//21
                String productionTwentyTwoDay = sp.getString("configTwentyTwoDay", "");//22
                String productionTwentyThreeDay = sp.getString("configTwentyThreeDay", "");//23
                String productionTwentyForeDay = sp.getString("configTwentyForeDay", "");//24
                String productionTwentyFiveDay = sp.getString("configTwentyFiveDay", "");//25
                String productionTwentySixDay = sp.getString("configTwentySixDay", "");//26
                String productionTwentySevenDay = sp.getString("configTwentySevenDay", "");//27
                String productionTwentyEightDay = sp.getString("configTwentyEightDay", "");//28
                String productionTwentyNineDay = sp.getString("configTwentyNineDay", "");//29
                String productionThirtyDay = sp.getString("configThirtyDay", "");//30
                String productionThirtyOneDay = sp.getString("configThirtyOneDay", "");//31
                String productionRemarks = sp.getString("configRemarks", "");//备注
                String productionRecordat = sp.getString("configrecordat", "");//制单时间
                Gson gson = new Gson();
                booleandatelist.size();
                final ProgressDialog progressDialog = ProgressDialog.show(this,
                        "请稍候...", "正在保存中...", false, true);
                if (procudureTitle.equals("裁床")) {
                    if (!TextUtils.isEmpty(liststr)) {
                        try {
                            List<String> list = PhoneSaveUtil.String2SceneList(liststr);
                            for (int j = 0; j < list.size(); j++) {
                                ProducationNewlyComfigSaveBean consaveBean =
                                        new ProducationNewlyComfigSaveBean();
                                consaveBean.setID("0");
                                consaveBean.setRecordid(recordid);
                                consaveBean.setSalesid(salesid);
                                consaveBean.setProdcol(list.get(j));
                                consaveBean.setItem(productionItem);
                                consaveBean.setPrddocumentary(productionDocumentary);
                                consaveBean.setSubfactory(productionFactory);
                                consaveBean.setSubfactoryTeams(columntitle);
                                consaveBean.setWorkingProcedure(procudureTitle);
                                consaveBean.setWorkers(productionOthers);
                                consaveBean.setPqty(productionSingularSystem);
                                consaveBean.setTaskqty(productionTaskNumber);
                                consaveBean.setMdl(productionSize);
                                consaveBean.setFactcutqty(productionClippingNumber);
                                consaveBean.setSumCompletedQty(productionTotalCompletion);
                                consaveBean.setLastMonQty(productionCompletedLastMonth);
                                consaveBean.setLeftQty(productionBalanceAmount);
                                consaveBean.setPrdstatus(proPrdstatusTitle);
                                consaveBean.setYear(productionYear);
                                consaveBean.setMonth(productionMonth);
                                consaveBean.setMemo(productionRemarks);
                                consaveBean.setRecorder(productionRecorder);
                                consaveBean.setRecordat(productionRecordat);
                                newlyComfigSaveBeen.add(consaveBean);
                            }
                            System.out.print(list);
                            System.out.print(newlyComfigSaveBeen);
                            String detailb = gson.toJson(newlyComfigSaveBeen);
                            String dateee = detailb.replace("\"\"", "null");
                            if (newlyComfigSaveBeen.equals("")) {
                                ToastUtils.ShowToastMessage("没有数据可以保存", ProductionNewlyCopyActivity.this);
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
                            } else {
                                if (NetWork.isNetWorkAvailable(this)) {
                                    OkHttpUtils.postString().
                                            url(saveurl)
                                            .content(dateee)
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
                                                    String ression = StringUtil.sideTrim(response, "\"");
                                                    System.out.print(ression);
                                                    int resindex = Integer.parseInt(ression);
                                                    if (resindex > 4) {
                                                        ToastUtils.ShowToastMessage("保存成功，请刷新页面",
                                                                ProductionNewlyCopyActivity.this);
                                                        startActivity(new Intent(ProductionNewlyCopyActivity.this,
                                                                ProductionActivity.class));
                                                    } else if (resindex == 3) {
                                                        ToastUtils.ShowToastMessage("保存失败",
                                                                ProductionNewlyCopyActivity.this);
                                                    } else if (resindex == 4) {
                                                        ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                ProductionNewlyCopyActivity.this);
                                                    } else if (resindex == 2) {
                                                        ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                ProductionNewlyCopyActivity.this);
                                                    } else {
                                                        ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                ProductionNewlyCopyActivity.this);
                                                    }
                                                }
                                            });
                                } else {
                                    ToastUtils.ShowToastMessage(R.string.noHttp, ProductionNewlyCopyActivity.this);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        ProducationNewlyComfigSaveBean consaveBean =
                                new ProducationNewlyComfigSaveBean();
                        consaveBean.setID("0");
                        consaveBean.setSalesid(salesid);
                        consaveBean.setRecordid(recordid);
                        consaveBean.setProdcol(productionColor);
                        consaveBean.setItem(productionItem);
                        consaveBean.setPrddocumentary(productionDocumentary);
                        consaveBean.setSubfactory(productionFactory);
                        consaveBean.setSubfactoryTeams(columntitle);
                        consaveBean.setWorkingProcedure(procudureTitle);
                        consaveBean.setWorkers(productionOthers);
                        consaveBean.setPqty(productionSingularSystem);
                        consaveBean.setTaskqty(productionTaskNumber);
                        consaveBean.setMdl(productionSize);
                        consaveBean.setFactcutqty(productionClippingNumber);
                        consaveBean.setSumCompletedQty(productionTotalCompletion);
                        consaveBean.setLastMonQty(productionCompletedLastMonth);
                        consaveBean.setLeftQty(productionBalanceAmount);
                        consaveBean.setPrdstatus(proPrdstatusTitle);
                        consaveBean.setYear(productionYear);
                        consaveBean.setMonth(productionMonth);
                        consaveBean.setDay1(productionOneDay);
                        consaveBean.setDay2(productionTwoDay);
                        consaveBean.setDay3(productionThreeDay);
                        consaveBean.setDay4(productionForeDay);
                        consaveBean.setDay5(productionFiveDay);
                        consaveBean.setDay6(productionSixDay);
                        consaveBean.setDay7(productionSevenDay);
                        consaveBean.setDay8(productionEightDay);
                        consaveBean.setDay9(productionNineDay);
                        consaveBean.setDay10(productionTenDay);
                        consaveBean.setDay11(productionElevenDay);
                        consaveBean.setDay12(productionTwelveDay);
                        consaveBean.setDay13(productionThirteenDay);
                        consaveBean.setDay14(productionFourteenDay);
                        consaveBean.setDay15(productionFifteenDay);
                        consaveBean.setDay16(productionSixteenDay);
                        consaveBean.setDay17(productionSeventeenDay);
                        consaveBean.setDay18(productionEighteenDay);
                        consaveBean.setDay19(productionNineteenDay);
                        consaveBean.setDay20(productionTwentyDay);
                        consaveBean.setDay21(productionTwentyOneDay);
                        consaveBean.setDay22(productionTwentyTwoDay);
                        consaveBean.setDay23(productionTwentyThreeDay);
                        consaveBean.setDay24(productionTwentyForeDay);
                        consaveBean.setDay25(productionTwentyFiveDay);
                        consaveBean.setDay26(productionTwentySixDay);
                        consaveBean.setDay27(productionTwentySevenDay);
                        consaveBean.setDay28(productionTwentyEightDay);
                        consaveBean.setDay29(productionTwentyNineDay);
                        consaveBean.setDay30(productionThirtyDay);
                        consaveBean.setDay31(productionThirtyOneDay);
                        consaveBean.setMemo(productionRemarks);
                        consaveBean.setRecorder(productionRecorder);
                        consaveBean.setRecordat(productionRecordat);
                        newlyComfigSaveBeen.add(consaveBean);
                        System.out.print(newlyComfigSaveBeen);
                        String detailb = gson.toJson(newlyComfigSaveBeen);
                        String dateee = detailb.replace("\"\"", "null");
                        if (newlyComfigSaveBeen.equals("")) {
                            ToastUtils.ShowToastMessage("没有数据可以保存", ProductionNewlyCopyActivity.this);
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
                        } else {
                            if (NetWork.isNetWorkAvailable(this)) {
                                OkHttpUtils.postString().
                                        url(saveurl)
                                        .content(dateee)
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
                                                String ression = StringUtil.sideTrim(response, "\"");
                                                System.out.print(ression);
                                                int resindex = Integer.parseInt(ression);
                                                if (resindex > 4) {
                                                    ToastUtils.ShowToastMessage("保存成功，请刷新页面",
                                                            ProductionNewlyCopyActivity.this);
                                                    startActivity(new Intent(ProductionNewlyCopyActivity.this,
                                                            ProductionActivity.class));
                                                } else if (resindex == 3) {
                                                    ToastUtils.ShowToastMessage("保存失败",
                                                            ProductionNewlyCopyActivity.this);
                                                } else if (resindex == 4) {
                                                    ToastUtils.ShowToastMessage("数据错误，请重试",
                                                            ProductionNewlyCopyActivity.this);
                                                } else {
                                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                            ProductionNewlyCopyActivity.this);
                                                }
                                            }
                                        });
                            } else {
                                ToastUtils.ShowToastMessage(R.string.noHttp, ProductionNewlyCopyActivity.this);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    /**
     * 新建时点击款号后查询有关当前登录用户的数据
     */
    private void setNewlyComfig() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";
        sp = getSharedPreferences("my_sp", 0);
        String namedure = sp.getString("usernamerecoder", "");//制单人
        String stis = sp.getString("ischeckedd", "");//是否为空
        boolean stris = Boolean.parseBoolean(stis);
        Gson gson = new Gson();
        Propostbean propostbean = new Propostbean();
        Propostbean.Conditions conditions = propostbean.new Conditions();
        conditions.setItem("");//款号
        conditions.setPrddocumentary(namedure);//制单人
        conditions.setSubfactory("");//工厂
        conditions.setWorkingProcedure("");//工序
        conditions.setPrddocumentaryisnull(stris);//是否为空
        propostbean.setConditions(conditions);//外部嵌套
        propostbean.setPageNum(0);//默认第几页
        propostbean.setPageSize(500);//默认每页多少条数据
        String gsonbeanStr = gson.toJson(propostbean);
        if (NetWork.isNetWorkAvailable(this)) {
            final ProgressDialog progressDialog = ProgressDialog.show(this,
                    "请稍候...", "正在保存中...", false, true);
            OkHttpUtils.postString()
                    .url(str)
                    .content(gsonbeanStr)
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
                                /*成功返回的结果*/
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
                                System.out.print(response);
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                newlybooleanBean = new Gson().fromJson(ression, ProductionNewlybooleanBean.class);
                                booleandatelist = newlybooleanBean.getData();
                            } catch (JsonSyntaxException e) {
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
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请重新再试",
                    ProductionNewlyCopyActivity.this);
        }
    }

    public final class ViewHolder {
        TextView tv_data;//款号
        TextView tvProDocumentary,//跟单
                tvProFactory;//工厂
        TextView tvProDepartment,//部门/组别
                tvProState,//状态
                tvProProcedure;//工序
        EditText tvProOthers,//组别人
                tvProCompletedLastMonth,//上月完工
                tvProTaskNumber;//任务数;
        TextView tvProSingularSystem,//制单数
                tvProColor,//花色
                tvProSize,//尺码
                tvProMonth,//月
                tvProClippingNumber,//实裁数
                tvProTotalCompletion,//总完工数
                tvProBalanceAmount,//结余数量
                tvProYear;//年
        EditText tvProOneDay,//1日
                tvProTwoDay,//2
                tvProThreeDay,//3
                tvProForeDay,//4
                tvProFiveDay,//5
                tvProSixDay,//6
                tvProSevenDay,//7
                tvProEightDay,//8
                tvProNineDay,//9
                tvProTenDay,//10
                tvProElevenDay,//11
                tvProTwelveDay,//12
                tvProThirteenDay,//13
                tvProFourteenDay,//14
                tvProFifteenDay,//15
                tvProSixteenDay,//16
                tvProSeventeenDay,//17
                tvProEighteenDay,//18
                tvProNineteenDay,//19
                tvProTwentyDay,//20
                tvProTwentyOneDay,//21
                tvProTwentyTwoDay,//22
                tvProTwentyThreeDay,//23
                tvProTwentyForeDay,//24
                tvProTwentyFiveDay,//25
                tvProTwentySixDay,//26
                tvProTwentySevenDay,//27
                tvProTwentyEightDay,//28
                tvProTwentyNineDay,//29
                tvProThirtyDay,//30
                tvProThirtyOneDay,//31
                tvProRemarks;//备注
        TextView tvProRecorder,//制单人
                tvProRecordat;//制单时间
        View vProOneDay,//1日
                vProTwoDay,//2
                vProThreeDay,//3
                vProForeDay,//4
                vProFiveDay,//5
                vProSixDay,//6
                vProSevenDay,//7
                vProEightDay,//8
                vProNineDay,//9
                vProTenDay,//10
                vProElevenDay,//11
                vProTwelveDay,//12
                vProThirteenDay,//13
                vProFourteenDay,//14
                vProFifteenDay,//15
                vProSixteenDay,//16
                vProSeventeenDay,//17
                vProEighteenDay,//18
                vProNineteenDay,//19
                vProTwentyDay,//20
                vProTwentyOneDay,//21
                vProTwentyTwoDay,//22
                vProTwentyThreeDay,//23
                vProTwentyForeDay,//24
                vProTwentyFiveDay,//25
                vProTwentySixDay,//26
                vProTwentySevenDay,//27
                vProTwentyEightDay,//28
                vProTwentyNineDay,//29
                vProThirtyDay,//30
                vProThirtyOneDay;//31
    }

    /**
     * 新建保存适配
     */
    public class MyAdatper extends BaseAdapter {
        private Context context;
        int lastmont, day1, day2, day3, day4, day5, day6, day7, day8, day9,
                day10, day11, day12, day13, day14, day15, day16, day17, day18,
                day19, day20, day21, day22, day23, day24, day25, day26, day27,
                day28, day29, day30, day31;

        public MyAdatper(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return mdate.size();
        }

        @Override
        public Object getItem(int position) {
            return mdate.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_producation_new_data, null);
                viewHolder.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
                viewHolder.tvProDocumentary = (TextView) convertView.findViewById(R.id.tvProDocumentary);
                viewHolder.tvProFactory = (TextView) convertView.findViewById(R.id.tvProFactory);
                viewHolder.tvProDepartment = (TextView) convertView.findViewById(R.id.tvProDepartment);
                viewHolder.tvProProcedure = (TextView) convertView.findViewById(R.id.tvProProcedure);
                viewHolder.tvProOthers = (EditText) convertView.findViewById(R.id.tvProOthers);
                viewHolder.tvProSingularSystem = (TextView) convertView.findViewById(R.id.tvProSingularSystem);
                viewHolder.tvProColor = (TextView) convertView.findViewById(R.id.tvProColor);
                viewHolder.tvProTaskNumber = (EditText) convertView.findViewById(R.id.tvProTaskNumber);
                viewHolder.tvProSize = (TextView) convertView.findViewById(R.id.tvProSize);
                viewHolder.tvProClippingNumber = (TextView) convertView.findViewById(R.id.tvProClippingNumber);
                viewHolder.tvProCompletedLastMonth = (EditText) convertView.findViewById(R.id.tvProCompletedLastMonth);
                viewHolder.tvProTotalCompletion = (TextView) convertView.findViewById(R.id.tvProTotalCompletion);
                viewHolder.tvProBalanceAmount = (TextView) convertView.findViewById(R.id.tvProBalanceAmount);
                viewHolder.tvProState = (TextView) convertView.findViewById(R.id.tvProState);
                viewHolder.tvProYear = (TextView) convertView.findViewById(R.id.tvProYear);
                viewHolder.tvProMonth = (TextView) convertView.findViewById(R.id.tvProMonth);
                viewHolder.tvProOneDay = (EditText) convertView.findViewById(R.id.tvProOneDay);
                viewHolder.tvProTwoDay = (EditText) convertView.findViewById(R.id.tvProTwoDay);
                viewHolder.tvProThreeDay = (EditText) convertView.findViewById(R.id.tvProThreeDay);
                viewHolder.tvProForeDay = (EditText) convertView.findViewById(R.id.tvProForeDay);
                viewHolder.tvProFiveDay = (EditText) convertView.findViewById(R.id.tvProFiveDay);
                viewHolder.tvProSixDay = (EditText) convertView.findViewById(R.id.tvProSixDay);
                viewHolder.tvProSevenDay = (EditText) convertView.findViewById(R.id.tvProSevenDay);
                viewHolder.tvProEightDay = (EditText) convertView.findViewById(R.id.tvProEightDay);
                viewHolder.tvProNineDay = (EditText) convertView.findViewById(R.id.tvProNineDay);
                viewHolder.tvProTenDay = (EditText) convertView.findViewById(R.id.tvProTenDay);
                viewHolder.tvProElevenDay = (EditText) convertView.findViewById(R.id.tvProElevenDay);
                viewHolder.tvProTwelveDay = (EditText) convertView.findViewById(R.id.tvProTwelveDay);
                viewHolder.tvProThirteenDay = (EditText) convertView.findViewById(R.id.tvProThirteenDay);
                viewHolder.tvProFourteenDay = (EditText) convertView.findViewById(R.id.tvProFourteenDay);
                viewHolder.tvProFifteenDay = (EditText) convertView.findViewById(R.id.tvProFifteenDay);
                viewHolder.tvProSixteenDay = (EditText) convertView.findViewById(R.id.tvProSixteenDay);
                viewHolder.tvProSeventeenDay = (EditText) convertView.findViewById(R.id.tvProSeventeenDay);
                viewHolder.tvProEighteenDay = (EditText) convertView.findViewById(R.id.tvProEighteenDay);
                viewHolder.tvProNineteenDay = (EditText) convertView.findViewById(R.id.tvProNineteenDay);
                viewHolder.tvProTwentyDay = (EditText) convertView.findViewById(R.id.tvProTwentyDay);
                viewHolder.tvProTwentyOneDay = (EditText) convertView.findViewById(R.id.tvProTwentyOneDay);
                viewHolder.tvProTwentyTwoDay = (EditText) convertView.findViewById(R.id.tvProTwentyTwoDay);
                viewHolder.tvProTwentyThreeDay = (EditText) convertView.findViewById(R.id.tvProTwentyThreeDay);
                viewHolder.tvProTwentyForeDay = (EditText) convertView.findViewById(R.id.tvProTwentyForeDay);
                viewHolder.tvProTwentyFiveDay = (EditText) convertView.findViewById(R.id.tvProTwentyFiveDay);
                viewHolder.tvProTwentySixDay = (EditText) convertView.findViewById(R.id.tvProTwentySixDay);
                viewHolder.tvProTwentySevenDay = (EditText) convertView.findViewById(R.id.tvProTwentySevenDay);
                viewHolder.tvProTwentyEightDay = (EditText) convertView.findViewById(R.id.tvProTwentyEightDay);
                viewHolder.tvProTwentyNineDay = (EditText) convertView.findViewById(R.id.tvProTwentyNineDay);
                viewHolder.tvProThirtyDay = (EditText) convertView.findViewById(R.id.tvProThirtyDay);
                viewHolder.tvProThirtyOneDay = (EditText) convertView.findViewById(R.id.tvProThirtyOneDay);
                viewHolder.tvProRemarks = (EditText) convertView.findViewById(R.id.tvProRemarks);
                viewHolder.tvProRecorder = (TextView) convertView.findViewById(R.id.tvProRecorder);
                viewHolder.tvProRecordat = (TextView) convertView.findViewById(R.id.tvProRecordat);
                viewHolder.vProOneDay = convertView.findViewById(R.id.vProOneDay);
                viewHolder.vProTwoDay = convertView.findViewById(R.id.vProTwoDay);
                viewHolder.vProThreeDay = convertView.findViewById(R.id.vProThreeDay);
                viewHolder.vProForeDay = convertView.findViewById(R.id.vProForeDay);
                viewHolder.vProFiveDay = convertView.findViewById(R.id.vProFiveDay);
                viewHolder.vProSixDay = convertView.findViewById(R.id.vProSixDay);
                viewHolder.vProSevenDay = convertView.findViewById(R.id.vProSevenDay);
                viewHolder.vProEightDay = convertView.findViewById(R.id.vProEightDay);
                viewHolder.vProNineDay = convertView.findViewById(R.id.vProNineDay);
                viewHolder.vProTenDay = convertView.findViewById(R.id.vProTenDay);
                viewHolder.vProElevenDay = convertView.findViewById(R.id.vProElevenDay);
                viewHolder.vProTwelveDay = convertView.findViewById(R.id.vProTwelveDay);
                viewHolder.vProThirteenDay = convertView.findViewById(R.id.vProThirteenDay);
                viewHolder.vProFourteenDay = convertView.findViewById(R.id.vProFourteenDay);
                viewHolder.vProFifteenDay = convertView.findViewById(R.id.vProFifteenDay);
                viewHolder.vProSixteenDay = convertView.findViewById(R.id.vProSixteenDay);
                viewHolder.vProSeventeenDay = convertView.findViewById(R.id.vProSeventeenDay);
                viewHolder.vProEighteenDay = convertView.findViewById(R.id.vProEighteenDay);
                viewHolder.vProNineteenDay = convertView.findViewById(R.id.vProNineteenDay);
                viewHolder.vProTwentyDay = convertView.findViewById(R.id.vProTwentyDay);
                viewHolder.vProTwentyOneDay = convertView.findViewById(R.id.vProTwentyOneDay);
                viewHolder.vProTwentyTwoDay = convertView.findViewById(R.id.vProTwentyTwoDay);
                viewHolder.vProTwentyThreeDay = convertView.findViewById(R.id.vProTwentyThreeDay);
                viewHolder.vProTwentyForeDay = convertView.findViewById(R.id.vProTwentyForeDay);
                viewHolder.vProTwentyFiveDay = convertView.findViewById(R.id.vProTwentyFiveDay);
                viewHolder.vProTwentySixDay = convertView.findViewById(R.id.vProTwentySixDay);
                viewHolder.vProTwentySevenDay = convertView.findViewById(R.id.vProTwentySevenDay);
                viewHolder.vProTwentyEightDay = convertView.findViewById(R.id.vProTwentyEightDay);
                viewHolder.vProTwentyNineDay = convertView.findViewById(R.id.vProTwentyNineDay);
                viewHolder.vProThirtyDay = convertView.findViewById(R.id.vProThirtyDay);
                viewHolder.vProThirtyOneDay = convertView.findViewById(R.id.vProThirtyOneDay);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String tvnewly = String.valueOf(mdate.get(position).get("tvnewlydate"));
            System.out.print(tvnewly);
            viewHolder.tv_data.setText(tvnewly);
            sp = getSharedPreferences("my_sp", 0);
            String nameid = sp.getString("usernamerecoder", "");
            String tvnewlyDocumen = String.valueOf(mdate.get(position).get("tvnewlyDocumentary"));
            String tvnewlyFactory = String.valueOf(mdate.get(position).get("tvnewlyFactory"));
            String tvnewlyDepartment = String.valueOf(mdate.get(position).get("tvnewlyDepartment"));
            String tvnewlyProcedure = String.valueOf(mdate.get(position).get("tvnewlyProcedure"));
            String tvnewlyOthers = String.valueOf(mdate.get(position).get("tvnewlyOthers"));
            String tvnewSingularSystem = String.valueOf(mdate.get(position).get("tvnewSingularSystem"));
            final String tvdate = String.valueOf(mdate.get(position).get("tvColorTaskqty"));
            String tvnewTaskNumber = String.valueOf(mdate.get(position).get("tvnewTaskNumber"));
            String tvnewlySize = String.valueOf(mdate.get(position).get("tvnewlySize"));
            String tvnewlyClippingNumber = String.valueOf(mdate.get(position).get("tvnewlyClippingNumber"));
            String tvnewlyCompletedLastMonth = String.valueOf(mdate.get(position).get("tvnewlyCompletedLastMonth"));
            String tvnewlyTotalCompletion = String.valueOf(mdate.get(position).get("tvnewlyTotalCompletion"));

            Time t = new Time("GMT+8"); // or Time t=new Time("GMT+8");
            t.setToNow(); // 取得系统时间。
            year = t.year;
            month = t.month;
            datetime = t.monthDay;
            hour = t.hour; // 0-23
            minute = t.minute;
            second = t.second;
            month = month + 1;
            final int MIN_MARK_OTHER = 0;
            final int MAX_MARK_OTHER = 200;
            if (tvnewlyProcedure.equals("裁床")) {
                isprodure = 1;
                sp = context.getSharedPreferences("my_sp", 0);
                String strisprodure = String.valueOf(isprodure);
                if (nameid != null && !nameid.equals("")) {
                    viewHolder.tvProDocumentary.setText(tvnewlyDocumen);
                    viewHolder.tvProFactory.setText(tvnewlyFactory);
                    viewHolder.tvProDepartment.setText(tvnewlyDepartment);
                    viewHolder.tvProProcedure.setText(tvnewlyProcedure);
                    viewHolder.tvProOthers.setText(tvnewlyOthers);
                    viewHolder.tvProSingularSystem.setText(tvnewSingularSystem);
                    viewHolder.tvProSize.setText(tvnewTaskNumber);
                    viewHolder.tvProColor.setText(tvnewlySize);
                    viewHolder.tvProClippingNumber.setText(tvnewlyClippingNumber);
                    viewHolder.tvProRecordat.setText(year + "/" + month + "/" + datetime);
                    String comfigitem = viewHolder.tv_data.getText().toString();
                    spUtils.put(context, "comfigitem", comfigitem);

                    String configdocument = viewHolder.tvProDocumentary.getText().toString();
                    spUtils.put(context, "configdocument", configdocument);

                    String configfactory = viewHolder.tvProFactory.getText().toString();
                    spUtils.put(context, "configfactory", configfactory);

                    viewHolder.tvProDepartment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popupMenu = new PopupMenu(context, v);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_pro_column, popupMenu.getMenu());
                            // menu的item点击事件
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    String title = item.getTitle().toString();
                                    spUtils.put(context, "Configdepartment", title);
                                    viewHolder.tvProDepartment.setText(title);
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
                    });

                    viewHolder.tvProProcedure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popupMenu = new PopupMenu(context, v);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_pro_procedure, popupMenu.getMenu());
                            // menu的item点击事件
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    String title = item.getTitle().toString();
                                    spUtils.put(context, "ConfigProcedure", title);
                                    viewHolder.tvProProcedure.setText(title);
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
                    });

                    final EditText editTexOthers = viewHolder.tvProOthers;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexOthers.getTag() instanceof TextWatcher) {
                        editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
                    }
                    TextWatcher TvOthers = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            if (s != null && s.equals("")) {
                                if (MIN_MARK_OTHER != -1 && MAX_MARK_OTHER != -1) {
                                    int markVal = 0;
                                    try {
                                        markVal = Integer.parseInt(s.toString());
                                    } catch (NumberFormatException e) {
                                        markVal = 0;
                                    }
                                    if (markVal > MAX_MARK_OTHER) {
                                        ToastUtils.ShowToastMessage("大小不能超过200", context);
                                        editTexOthers.setText(String.valueOf(MAX_MARK_OTHER));
                                        editTexOthers.setSelection(editTexOthers.length());
                                    }
                                    return;
                                }
                            }
                            String proitem = viewHolder.tvProOthers.getText().toString();
                            spUtils.put(context, "ConfigOthers", proitem);
                            viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.length());
                        }
                    };
                    editTexOthers.addTextChangedListener(TvOthers);
                    editTexOthers.setTag(TvOthers);
                    /*光标放置在文本最后*/
                    viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.length());

                    String configsingular = viewHolder.tvProSingularSystem.getText().toString();
                    spUtils.put(context, "configsingular", configsingular);

                    String configcolor = viewHolder.tvProColor.getText().toString();
                    spUtils.put(context, "configcolor", configcolor);

                    final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    final int singular = Integer.parseInt(viewHolder.tvProSingularSystem.getText().toString());
                    final int MIN_MARK = 0;
                    if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                        editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
                    }
                    viewHolder.tvProTaskNumber.setText(tvdate);
                    /**
                     * 任务数不能大于制单数
                     */
                    TextWatcher TvTaskNumber = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                            if (start > 1) {
                                if (MIN_MARK != -1 && singular != -1) {
                                    int num = Integer.parseInt(s.toString());
                                    if (num > singular) {
                                        s = String.valueOf(singular);
                                        editTexTaskNumber.setText(tvdate);
                                        editTexTaskNumber.setSelection(editTexTaskNumber.length());
                                        InputFilter[] filters = {new InputFilter.LengthFilter(singular)};
                                        viewHolder.tvProTaskNumber.setFilters(filters);
                                        ToastUtils.ShowToastMessage("输入超过了制单数", context);
                                    } else if (num < MIN_MARK) {
                                        s = String.valueOf(MIN_MARK);
                                        return;
                                    }
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            if (s != null && s.equals("")) {
                                if (MIN_MARK != -1 && singular != -1) {
                                    int markVal = 0;
                                    try {
                                        markVal = Integer.parseInt(s.toString());
                                    } catch (NumberFormatException e) {
                                        markVal = 0;
                                    }
                                    if (markVal > singular) {
                                        ToastUtils.ShowToastMessage("大小不能超过制单数", context);
                                        editTexTaskNumber.setText(tvdate);
                                        editTexTaskNumber.setSelection(editTexTaskNumber.length());
                                    }
                                    return;
                                }
                            }
                            String proitem = viewHolder.tvProTaskNumber.getText().toString();
                            spUtils.put(context, "ConfigTaskNumber", proitem);
                            viewHolder.tvProTaskNumber.setSelection(viewHolder.tvProTaskNumber.length());
                        }
                    };
                    editTexTaskNumber.addTextChangedListener(TvTaskNumber);
                    editTexTaskNumber.setTag(TvTaskNumber);
                    /*光标放置在文本最后*/
                    viewHolder.tvProTaskNumber.setSelection(viewHolder.tvProTaskNumber.length());

                    String configsize = viewHolder.tvProSize.getText().toString();
                    spUtils.put(context, "configsize", configsize);

                    String configclipping = viewHolder.tvProClippingNumber.getText().toString();
                    spUtils.put(context, "configclipping", configclipping);

                    final EditText editTexLastMonth = viewHolder.tvProCompletedLastMonth;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexLastMonth.getTag() instanceof TextWatcher) {
                        editTexLastMonth.removeTextChangedListener((TextWatcher) editTexLastMonth.getTag());
                    }
                    viewHolder.tvProCompletedLastMonth.setText(tvnewlyCompletedLastMonth);
                    TextWatcher TvLastMonth = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProCompletedLastMonth.getText().toString();
                            spUtils.put(context, "ConfigTaskNumber", proitem);
                        }
                    };
                    editTexLastMonth.addTextChangedListener(TvLastMonth);
                    editTexLastMonth.setTag(TvLastMonth);
                    /*光标放置在文本最后*/
                    viewHolder.tvProCompletedLastMonth.setSelection(viewHolder.tvProCompletedLastMonth.length());

                    viewHolder.tvProTotalCompletion.setText(tvnewlyTotalCompletion);
                    String configcompletion = viewHolder.tvProTotalCompletion.getText().toString();
                    spUtils.put(context, "configcompletion", configcompletion);

                    String configamount = viewHolder.tvProBalanceAmount.getText().toString();
                    spUtils.put(context, "configamount", configamount);


                    viewHolder.tvProState.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popupMenu = new PopupMenu(context, v);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_pro_prdstatus, popupMenu.getMenu());
                            // menu的item点击事件
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    String title = item.getTitle().toString();
                                    spUtils.put(context, "ComfigPrdstatus", title);
                                    viewHolder.tvProState.setText(title);
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
                    });

                    viewHolder.tvProYear.setText(year + "");
                    String configyear = viewHolder.tvProYear.getText().toString();
                    spUtils.put(context, "configyear", configyear);


                    viewHolder.tvProMonth.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popupMenu = new PopupMenu(context, v);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_pro_mouth, popupMenu.getMenu());
                            // menu的item点击事件
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    String title = item.getTitle().toString();
                                    spUtils.put(context, "ComfigMonth", title);
                                    viewHolder.tvProMonth.setText(title);
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
                    });

                    viewHolder.tvProOneDay.setVisibility(View.GONE);
                    viewHolder.tvProTwoDay.setVisibility(View.GONE);
                    viewHolder.tvProThreeDay.setVisibility(View.GONE);
                    viewHolder.tvProForeDay.setVisibility(View.GONE);
                    viewHolder.tvProFiveDay.setVisibility(View.GONE);
                    viewHolder.tvProSixDay.setVisibility(View.GONE);
                    viewHolder.tvProSevenDay.setVisibility(View.GONE);
                    viewHolder.tvProEightDay.setVisibility(View.GONE);
                    viewHolder.tvProNineDay.setVisibility(View.GONE);
                    viewHolder.tvProTenDay.setVisibility(View.GONE);
                    viewHolder.tvProElevenDay.setVisibility(View.GONE);
                    viewHolder.tvProTwelveDay.setVisibility(View.GONE);
                    viewHolder.tvProThirteenDay.setVisibility(View.GONE);
                    viewHolder.tvProFourteenDay.setVisibility(View.GONE);
                    viewHolder.tvProFifteenDay.setVisibility(View.GONE);
                    viewHolder.tvProSixteenDay.setVisibility(View.GONE);
                    viewHolder.tvProSeventeenDay.setVisibility(View.GONE);
                    viewHolder.tvProEighteenDay.setVisibility(View.GONE);
                    viewHolder.tvProNineteenDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentyDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentyOneDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentyTwoDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentyThreeDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentyForeDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentyFiveDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentySixDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentySevenDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentyEightDay.setVisibility(View.GONE);
                    viewHolder.tvProTwentyNineDay.setVisibility(View.GONE);
                    viewHolder.tvProThirtyDay.setVisibility(View.GONE);
                    viewHolder.tvProThirtyOneDay.setVisibility(View.GONE);
                    viewHolder.vProOneDay.setVisibility(View.GONE);
                    viewHolder.vProTwoDay.setVisibility(View.GONE);
                    viewHolder.vProThreeDay.setVisibility(View.GONE);
                    viewHolder.vProForeDay.setVisibility(View.GONE);
                    viewHolder.vProFiveDay.setVisibility(View.GONE);
                    viewHolder.vProSixDay.setVisibility(View.GONE);
                    viewHolder.vProSevenDay.setVisibility(View.GONE);
                    viewHolder.vProEightDay.setVisibility(View.GONE);
                    viewHolder.vProNineDay.setVisibility(View.GONE);
                    viewHolder.vProTenDay.setVisibility(View.GONE);
                    viewHolder.vProElevenDay.setVisibility(View.GONE);
                    viewHolder.vProTwelveDay.setVisibility(View.GONE);
                    viewHolder.vProThirteenDay.setVisibility(View.GONE);
                    viewHolder.vProFourteenDay.setVisibility(View.GONE);
                    viewHolder.vProFifteenDay.setVisibility(View.GONE);
                    viewHolder.vProSixteenDay.setVisibility(View.GONE);
                    viewHolder.vProSeventeenDay.setVisibility(View.GONE);
                    viewHolder.vProEighteenDay.setVisibility(View.GONE);
                    viewHolder.vProNineteenDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyOneDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyTwoDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyThreeDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyForeDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyFiveDay.setVisibility(View.GONE);
                    viewHolder.vProTwentySixDay.setVisibility(View.GONE);
                    viewHolder.vProTwentySevenDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyEightDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyNineDay.setVisibility(View.GONE);
                    viewHolder.vProThirtyDay.setVisibility(View.GONE);
                    viewHolder.vProThirtyOneDay.setVisibility(View.GONE);

                    final EditText editTexRemarks = viewHolder.tvProRemarks;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexRemarks.getTag() instanceof TextWatcher) {
                        editTexRemarks.removeTextChangedListener((TextWatcher) editTexRemarks.getTag());
                    }
                    TextWatcher TvRemarks = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProRemarks.getText().toString();
                            spUtils.put(context, "configRemarks", proitem);
                        }
                    };
                    editTexRemarks.addTextChangedListener(TvRemarks);
                    editTexRemarks.setTag(TvRemarks);
                    /*光标放置在文本最后*/
                    viewHolder.tvProRemarks.setSelection(viewHolder.tvProRemarks.length());


                    viewHolder.tvProRecorder.setText(nameid);
                    String configrecorder = viewHolder.tvProRecorder.getText().toString();
                    spUtils.put(context, "configrecorder", configrecorder);


                    viewHolder.tvProRecordat.setText(year + "/" + month + "/" + datetime);
                    String configrecordat = viewHolder.tvProRecordat.getText().toString();
                    spUtils.put(context, "configrecordat", configrecordat);

                } else {
                    final EditText editTexOthers = viewHolder.tvProOthers;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexOthers.getTag() instanceof TextWatcher) {
                        editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
                    }
                    editTexOthers.setText(tvnewlyOthers);

                    final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                        editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
                    }
                    viewHolder.tvProTaskNumber.setText(tvdate);

                    viewHolder.tvProColor.setText(tvnewlySize);

                    final EditText editTexLastMonth = viewHolder.tvProCompletedLastMonth;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexLastMonth.getTag() instanceof TextWatcher) {
                        editTexLastMonth.removeTextChangedListener((TextWatcher) editTexLastMonth.getTag());
                    }
                    viewHolder.tvProCompletedLastMonth.setText(tvnewlyCompletedLastMonth);

                    viewHolder.tvProOneDay.setVisibility(View.GONE);
                    viewHolder.tvProTwoDay.setVisibility(View.GONE);
                    viewHolder.tvProThreeDay.setVisibility(View.GONE);
                    viewHolder.tvProForeDay.setVisibility(View.GONE);
                    viewHolder.tvProFiveDay.setVisibility(View.GONE);
                    viewHolder.vProOneDay.setVisibility(View.GONE);
                    viewHolder.vProTwoDay.setVisibility(View.GONE);
                    viewHolder.vProThreeDay.setVisibility(View.GONE);
                    viewHolder.vProForeDay.setVisibility(View.GONE);
                    viewHolder.vProFiveDay.setVisibility(View.GONE);
                    viewHolder.vProSixDay.setVisibility(View.GONE);
                    viewHolder.vProSevenDay.setVisibility(View.GONE);
                    viewHolder.vProEightDay.setVisibility(View.GONE);
                    viewHolder.vProNineDay.setVisibility(View.GONE);
                    viewHolder.vProTenDay.setVisibility(View.GONE);
                    viewHolder.vProElevenDay.setVisibility(View.GONE);
                    viewHolder.vProTwelveDay.setVisibility(View.GONE);
                    viewHolder.vProThirteenDay.setVisibility(View.GONE);
                    viewHolder.vProFourteenDay.setVisibility(View.GONE);
                    viewHolder.vProFifteenDay.setVisibility(View.GONE);
                    viewHolder.vProSixteenDay.setVisibility(View.GONE);
                    viewHolder.vProSeventeenDay.setVisibility(View.GONE);
                    viewHolder.vProEighteenDay.setVisibility(View.GONE);
                    viewHolder.vProNineteenDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyOneDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyTwoDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyThreeDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyForeDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyFiveDay.setVisibility(View.GONE);
                    viewHolder.vProTwentySixDay.setVisibility(View.GONE);
                    viewHolder.vProTwentySevenDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyEightDay.setVisibility(View.GONE);
                    viewHolder.vProTwentyNineDay.setVisibility(View.GONE);
                    viewHolder.vProThirtyDay.setVisibility(View.GONE);
                    viewHolder.vProThirtyOneDay.setVisibility(View.GONE);
                }
            } else {
                viewHolder.vProOneDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwoDay.setVisibility(View.VISIBLE);
                viewHolder.vProThreeDay.setVisibility(View.VISIBLE);
                viewHolder.vProForeDay.setVisibility(View.VISIBLE);
                viewHolder.vProFiveDay.setVisibility(View.VISIBLE);
                viewHolder.vProSixDay.setVisibility(View.VISIBLE);
                viewHolder.vProSevenDay.setVisibility(View.VISIBLE);
                viewHolder.vProEightDay.setVisibility(View.VISIBLE);
                viewHolder.vProNineDay.setVisibility(View.VISIBLE);
                viewHolder.vProTenDay.setVisibility(View.VISIBLE);
                viewHolder.vProElevenDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwelveDay.setVisibility(View.VISIBLE);
                viewHolder.vProThirteenDay.setVisibility(View.VISIBLE);
                viewHolder.vProFourteenDay.setVisibility(View.VISIBLE);
                viewHolder.vProFifteenDay.setVisibility(View.VISIBLE);
                viewHolder.vProSixteenDay.setVisibility(View.VISIBLE);
                viewHolder.vProSeventeenDay.setVisibility(View.VISIBLE);
                viewHolder.vProEighteenDay.setVisibility(View.VISIBLE);
                viewHolder.vProNineteenDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentyDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentyOneDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentyTwoDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentyThreeDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentyForeDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentyFiveDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentySixDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentySevenDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentyEightDay.setVisibility(View.VISIBLE);
                viewHolder.vProTwentyNineDay.setVisibility(View.VISIBLE);
                viewHolder.vProThirtyDay.setVisibility(View.VISIBLE);
                viewHolder.vProThirtyOneDay.setVisibility(View.VISIBLE);
                isprodure = 0;
                sp = context.getSharedPreferences("my_sp", 0);
                spUtils.put(context, "strprodure", String.valueOf(isprodure));
                if (nameid != null && !nameid.equals("")) {
                    viewHolder.tvProDocumentary.setText(tvnewlyDocumen);
                    viewHolder.tvProFactory.setText(tvnewlyFactory);
                    viewHolder.tvProDepartment.setText(tvnewlyDepartment);
                    viewHolder.tvProProcedure.setText(tvnewlyProcedure);
                    viewHolder.tvProOthers.setText(tvnewlyOthers);
                    viewHolder.tvProSingularSystem.setText(tvnewSingularSystem);
                    viewHolder.tvProSize.setText(tvnewTaskNumber);
                    viewHolder.tvProColor.setText(tvnewlySize);
                    viewHolder.tvProClippingNumber.setText(tvnewlyClippingNumber);
                    viewHolder.tvProRecordat.setText(year + "/" + month + "/" + datetime);
                    String comfigitem = viewHolder.tv_data.getText().toString();
                    spUtils.put(context, "comfigitem", comfigitem);

                    String configdocument = viewHolder.tvProDocumentary.getText().toString();
                    spUtils.put(context, "configdocument", configdocument);

                    String configfactory = viewHolder.tvProFactory.getText().toString();
                    spUtils.put(context, "configfactory", configfactory);

                    viewHolder.tvProDepartment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popupMenu = new PopupMenu(context, v);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_pro_column, popupMenu.getMenu());
                            // menu的item点击事件
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    String title = item.getTitle().toString();
                                    spUtils.put(context, "Configdepartment", title);
                                    viewHolder.tvProDepartment.setText(title);
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
                    });

                    viewHolder.tvProProcedure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popupMenu = new PopupMenu(context, v);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_pro_procedure, popupMenu.getMenu());
                            // menu的item点击事件
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    sp = context.getSharedPreferences("userInfo", 0);
                                    String title = item.getTitle().toString();
                                    spUtils.put(context, "ConfigProcedure", title);
                                    viewHolder.tvProProcedure.setText(title);
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
                    });

                    final EditText editTexOthers = viewHolder.tvProOthers;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexOthers.getTag() instanceof TextWatcher) {
                        editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
                    }
                    TextWatcher TvOthers = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");

                            if (s != null && s.equals("")) {
                                if (MIN_MARK_OTHER != -1 && MAX_MARK_OTHER != -1) {
                                    int markVal = 0;
                                    try {
                                        markVal = Integer.parseInt(s.toString());
                                    } catch (NumberFormatException e) {
                                        markVal = 0;
                                    }
                                    if (markVal > MAX_MARK_OTHER) {
                                        ToastUtils.ShowToastMessage("大小不能超过200", context);
                                        editTexOthers.setText(String.valueOf(MAX_MARK_OTHER));
                                        editTexOthers.setSelection(editTexOthers.length());
                                    }
                                    return;
                                }
                            }
                            String proitem = viewHolder.tvProOthers.getText().toString();
                            spUtils.put(context, "ConfigOthers", proitem);
                            viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.length());
                        }
                    };
                    editTexOthers.addTextChangedListener(TvOthers);
                    editTexOthers.setTag(TvOthers);
                    /*光标放置在文本最后*/
                    viewHolder.tvProOthers.setSelection(viewHolder.tvProOthers.length());

                    String configsingular = viewHolder.tvProSingularSystem.getText().toString();
                    spUtils.put(context, "configsingular", configsingular);

                    String configcolor = viewHolder.tvProColor.getText().toString();
                    spUtils.put(context, "configcolor", configcolor);

                    final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    final int singular = Integer.parseInt(viewHolder.tvProSingularSystem.getText().toString());
                    final int MIN_MARK = 0;
                    if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                        editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
                    }
                    viewHolder.tvProTaskNumber.setText(tvdate);
                    TextWatcher TvTaskNumber = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                            if (start > 1) {
                                if (MIN_MARK != -1 && singular != -1) {
                                    int num = Integer.parseInt(s.toString());
                                    if (num > singular) {
                                        s = String.valueOf(singular);
                                        editTexTaskNumber.setText(tvdate);
                                        editTexTaskNumber.setSelection(editTexTaskNumber.length());
                                        InputFilter[] filters = {new InputFilter.LengthFilter(singular)};
                                        viewHolder.tvProTaskNumber.setFilters(filters);
                                        ToastUtils.ShowToastMessage("输入超过了制单数", context);
                                    } else if (num < MIN_MARK) {
                                        s = String.valueOf(MIN_MARK);
                                        return;
                                    }
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            if (s != null && s.equals("")) {
                                if (MIN_MARK != -1 && singular != -1) {
                                    int markVal = 0;
                                    try {
                                        markVal = Integer.parseInt(s.toString());
                                    } catch (NumberFormatException e) {
                                        markVal = 0;
                                    }
                                    if (markVal > singular) {
                                        ToastUtils.ShowToastMessage("大小不能超过制单数", context);
                                        editTexTaskNumber.setText(tvdate);
                                        editTexTaskNumber.setSelection(editTexTaskNumber.length());
                                    }
                                    return;
                                }
                            }
                            String proitem = viewHolder.tvProTaskNumber.getText().toString();
                            spUtils.put(context, "ConfigTaskNumber", proitem);
                        }
                    };
                    editTexTaskNumber.addTextChangedListener(TvTaskNumber);
                    editTexTaskNumber.setTag(TvTaskNumber);
                    /*光标放置在文本最后*/
                    viewHolder.tvProTaskNumber.setSelection(viewHolder.tvProTaskNumber.length());

                    String configsize = viewHolder.tvProSize.getText().toString();
                    spUtils.put(context, "configsize", configsize);

                    String configclipping = viewHolder.tvProClippingNumber.getText().toString();
                    spUtils.put(context, "configclipping", configclipping);

                    final EditText editTexLastMonth = viewHolder.tvProCompletedLastMonth;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexLastMonth.getTag() instanceof TextWatcher) {
                        editTexLastMonth.removeTextChangedListener((TextWatcher) editTexLastMonth.getTag());
                    }
                    viewHolder.tvProCompletedLastMonth.setText(tvnewlyCompletedLastMonth);
                    TextWatcher TvLastMonth = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProCompletedLastMonth.getText().toString();
                            spUtils.put(context, "ConfigLastMonth", proitem);
                        }
                    };
                    editTexLastMonth.addTextChangedListener(TvLastMonth);
                    editTexLastMonth.setTag(TvLastMonth);
                    /*光标放置在文本最后*/
                    viewHolder.tvProCompletedLastMonth.setSelection(viewHolder.tvProCompletedLastMonth.length());

                    viewHolder.tvProTotalCompletion.setText(tvnewlyTotalCompletion);
                    String configcompletion = viewHolder.tvProTotalCompletion.getText().toString();
                    spUtils.put(context, "configcompletion", configcompletion);

                    String configamount = viewHolder.tvProBalanceAmount.getText().toString();
                    spUtils.put(context, "configamount", configamount);


                    viewHolder.tvProState.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popupMenu = new PopupMenu(context, v);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_pro_prdstatus, popupMenu.getMenu());
                            // menu的item点击事件
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    String title = item.getTitle().toString();
                                    spUtils.put(context, "ComfigPrdstatus", title);
                                    viewHolder.tvProState.setText(title);
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
                    });

                    viewHolder.tvProYear.setText(year + "");
                    String configyear = viewHolder.tvProYear.getText().toString();
                    spUtils.put(context, "configyear", configyear);


                    viewHolder.tvProMonth.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupMenu popupMenu = new PopupMenu(context, v);
                            popupMenu.getMenuInflater().inflate(R.menu.menu_pro_mouth, popupMenu.getMenu());
                            // menu的item点击事件
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    String title = item.getTitle().toString();
                                    spUtils.put(context, "ComfigMonth", title);
                                    viewHolder.tvProMonth.setText(title);
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
                    });


                    final EditText editTexOneDay = viewHolder.tvProOneDay;
                    viewHolder.tvProOneDay.setVisibility(View.VISIBLE);
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexOneDay.getTag() instanceof TextWatcher) {
                        editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
                    }
                    TextWatcher TvOneDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProOneDay.getText().toString();
                            spUtils.put(context, "configOneDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexOneDay.addTextChangedListener(TvOneDay);
                    editTexOneDay.setTag(TvOneDay);
                    /*光标放置在文本最后*/
                    viewHolder.tvProOneDay.setSelection(viewHolder.tvProOneDay.length());


                    viewHolder.tvProTwoDay.setEnabled(true);
                    final EditText editTexTwoDay = viewHolder.tvProTwoDay;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwoDay.getTag() instanceof TextWatcher) {
                        editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
                    }
                    TextWatcher TvTwoDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwoDay.getText().toString();
                            spUtils.put(context, "configTwoDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwoDay.addTextChangedListener(TvTwoDay);
                    editTexTwoDay.setTag(TvTwoDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwoDay.setSelection(viewHolder.tvProTwoDay.length());


                    viewHolder.tvProThreeDay.setEnabled(true);
                    final EditText editTexThreeDay = viewHolder.tvProThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexThreeDay.getTag() instanceof TextWatcher) {
                        editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
                    }
                    TextWatcher TvThreeDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProThreeDay.getText().toString();
                            spUtils.put(context, "configThreeDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexThreeDay.addTextChangedListener(TvThreeDay);
                    editTexThreeDay.setTag(TvThreeDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProThreeDay.setSelection(viewHolder.tvProThreeDay.length());


                    viewHolder.tvProForeDay.setEnabled(true);
                    final EditText editTexForeDay = viewHolder.tvProForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexForeDay.getTag() instanceof TextWatcher) {
                        editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
                    }
                    TextWatcher TvForeDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProForeDay.getText().toString();
                            spUtils.put(context, "configForeDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexForeDay.addTextChangedListener(TvForeDay);
                    editTexForeDay.setTag(TvForeDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProForeDay.setSelection(viewHolder.tvProForeDay.length());


                    viewHolder.tvProFiveDay.setEnabled(true);
                    final EditText editTexFiveDay = viewHolder.tvProFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexFiveDay.getTag() instanceof TextWatcher) {
                        editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
                    }
                    TextWatcher TvFiveDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProFiveDay.getText().toString();
                            spUtils.put(context, "configFiveDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexFiveDay.addTextChangedListener(TvFiveDay);
                    editTexFiveDay.setTag(TvFiveDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProFiveDay.setSelection(viewHolder.tvProFiveDay.length());


                    viewHolder.tvProSixDay.setEnabled(true);
                    final EditText editTexSixDay = viewHolder.tvProSixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexSixDay.getTag() instanceof TextWatcher) {
                        editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
                    }
                    TextWatcher TvSixDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProSixDay.getText().toString();
                            spUtils.put(context, "configSixDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexSixDay.addTextChangedListener(TvSixDay);
                    editTexSixDay.setTag(TvSixDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProSixDay.setSelection(viewHolder.tvProSixDay.length());


                    viewHolder.tvProSevenDay.setEnabled(true);
                    final EditText editTexSevenDay = viewHolder.tvProSevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexSevenDay.getTag() instanceof TextWatcher) {
                        editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
                    }
                    TextWatcher TvSevenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProSevenDay.getText().toString();
                            spUtils.put(context, "configSevenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexSevenDay.addTextChangedListener(TvSevenDay);
                    editTexSevenDay.setTag(TvSevenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProSevenDay.setSelection(viewHolder.tvProSevenDay.length());


                    viewHolder.tvProEightDay.setEnabled(true);
                    final EditText editTexEightDay = viewHolder.tvProEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexEightDay.getTag() instanceof TextWatcher) {
                        editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
                    }
                    TextWatcher TvEightDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProEightDay.getText().toString();
                            spUtils.put(context, "configEightDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexEightDay.addTextChangedListener(TvEightDay);
                    editTexEightDay.setTag(TvEightDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProEightDay.setSelection(viewHolder.tvProEightDay.length());


                    viewHolder.tvProNineDay.setEnabled(true);
                    final EditText editTexNineDay = viewHolder.tvProNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexNineDay.getTag() instanceof TextWatcher) {
                        editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
                    }
                    TextWatcher TvNineDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProNineDay.getText().toString();
                            spUtils.put(context, "configNineDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexNineDay.addTextChangedListener(TvNineDay);
                    editTexNineDay.setTag(TvNineDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProNineDay.setSelection(viewHolder.tvProNineDay.length());


                    viewHolder.tvProTenDay.setEnabled(true);
                    final EditText editTexTenDay = viewHolder.tvProTenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTenDay.getTag() instanceof TextWatcher) {
                        editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
                    }
                    TextWatcher TvTenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTenDay.getText().toString();
                            spUtils.put(context, "configTenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTenDay.addTextChangedListener(TvTenDay);
                    editTexTenDay.setTag(TvTenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTenDay.setSelection(viewHolder.tvProTenDay.length());


                    viewHolder.tvProElevenDay.setEnabled(true);
                    final EditText editTexElevenDay = viewHolder.tvProElevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexElevenDay.getTag() instanceof TextWatcher) {
                        editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
                    }
                    TextWatcher TvElevenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProElevenDay.getText().toString();
                            spUtils.put(context, "configElevenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexElevenDay.addTextChangedListener(TvElevenDay);
                    editTexElevenDay.setTag(TvElevenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProElevenDay.setSelection(viewHolder.tvProElevenDay.length());


                    viewHolder.tvProTwelveDay.setEnabled(true);
                    final EditText editTexTwelveDay = viewHolder.tvProTwelveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwelveDay.getTag() instanceof TextWatcher) {
                        editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
                    }
                    TextWatcher TvTwelveDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwelveDay.getText().toString();
                            spUtils.put(context, "configTwelveDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwelveDay.addTextChangedListener(TvTwelveDay);
                    editTexTwelveDay.setTag(TvTwelveDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwelveDay.setSelection(viewHolder.tvProTwelveDay.length());


                    viewHolder.tvProThirteenDay.setEnabled(true);
                    final EditText editTexThirteenDay = viewHolder.tvProThirteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexThirteenDay.getTag() instanceof TextWatcher) {
                        editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
                    }
                    TextWatcher TvThirteenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProThirteenDay.getText().toString();
                            spUtils.put(context, "configThirteenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexThirteenDay.addTextChangedListener(TvThirteenDay);
                    editTexThirteenDay.setTag(TvThirteenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProThirteenDay.setSelection(viewHolder.tvProThirteenDay.length());


                    viewHolder.tvProFourteenDay.setEnabled(true);
                    final EditText editTexFourteenDay = viewHolder.tvProFourteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexFourteenDay.getTag() instanceof TextWatcher) {
                        editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
                    }
                    TextWatcher TvFourteenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProFourteenDay.getText().toString();
                            spUtils.put(context, "configFourteenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexFourteenDay.addTextChangedListener(TvFourteenDay);
                    editTexFourteenDay.setTag(TvFourteenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProFourteenDay.setSelection(viewHolder.tvProFourteenDay.length());


                    viewHolder.tvProFifteenDay.setEnabled(true);
                    final EditText editTexFifteenDay = viewHolder.tvProFifteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexFifteenDay.getTag() instanceof TextWatcher) {
                        editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
                    }
                    TextWatcher TvFifteenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProFifteenDay.getText().toString();
                            spUtils.put(context, "configFifteenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexFifteenDay.addTextChangedListener(TvFifteenDay);
                    editTexFifteenDay.setTag(TvFifteenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProFifteenDay.setSelection(viewHolder.tvProFifteenDay.length());


                    viewHolder.tvProSixteenDay.setEnabled(true);
                    final EditText editTexSixteenDay = viewHolder.tvProSixteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexSixteenDay.getTag() instanceof TextWatcher) {
                        editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
                    }
                    TextWatcher TvSixteenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProSixteenDay.getText().toString();
                            spUtils.put(context, "configSixteenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexSixteenDay.addTextChangedListener(TvSixteenDay);
                    editTexSixteenDay.setTag(TvSixteenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProSixteenDay.setSelection(viewHolder.tvProSixteenDay.length());


                    viewHolder.tvProSeventeenDay.setEnabled(true);
                    final EditText editTexSeventeenDay = viewHolder.tvProSeventeenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
                        editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
                    }
                    TextWatcher TvSeventeenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProSeventeenDay.getText().toString();
                            spUtils.put(context, "configSeventeenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexSeventeenDay.addTextChangedListener(TvSeventeenDay);
                    editTexSeventeenDay.setTag(TvSeventeenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProSeventeenDay.setSelection(viewHolder.tvProSeventeenDay.length());


                    viewHolder.tvProEighteenDay.setEnabled(true);
                    final EditText editTexEighteenDay = viewHolder.tvProEighteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexEighteenDay.getTag() instanceof TextWatcher) {
                        editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
                    }
                    TextWatcher TvEighteenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProEighteenDay.getText().toString();
                            spUtils.put(context, "configEighteenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexEighteenDay.addTextChangedListener(TvEighteenDay);
                    editTexEighteenDay.setTag(TvEighteenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProEighteenDay.setSelection(viewHolder.tvProEighteenDay.length());


                    viewHolder.tvProNineteenDay.setEnabled(true);
                    final EditText editTexNineteenDay = viewHolder.tvProNineteenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexNineteenDay.getTag() instanceof TextWatcher) {
                        editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
                    }
                    TextWatcher TvNineteenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProNineteenDay.getText().toString();
                            spUtils.put(context, "configNineteenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexNineteenDay.addTextChangedListener(TvNineteenDay);
                    editTexNineteenDay.setTag(TvNineteenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProNineteenDay.setSelection(viewHolder.tvProNineteenDay.length());


                    viewHolder.tvProTwentyDay.setEnabled(true);
                    final EditText editTexTwentyDay = viewHolder.tvProTwentyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentyDay.getTag() instanceof TextWatcher) {
                        editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
                    }
                    TextWatcher TvTwentyDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentyDay.getText().toString();
                            spUtils.put(context, "configTwentyDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentyDay.addTextChangedListener(TvTwentyDay);
                    editTexTwentyDay.setTag(TvTwentyDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentyDay.setSelection(viewHolder.tvProTwentyDay.length());


                    viewHolder.tvProTwentyOneDay.setEnabled(true);
                    final EditText editTexTwentyOneDay = viewHolder.tvProTwentyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
                        editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
                    }
                    TextWatcher TvTwentyOneDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentyOneDay.getText().toString();
                            spUtils.put(context, "configTwentyOneDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentyOneDay.addTextChangedListener(TvTwentyOneDay);
                    editTexTwentyOneDay.setTag(TvTwentyOneDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentyOneDay.setSelection(viewHolder.tvProTwentyOneDay.length());


                    viewHolder.tvProTwentyTwoDay.setEnabled(true);
                    final EditText editTexTwentyTwoDay = viewHolder.tvProTwentyTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
                        editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
                    }
                    TextWatcher TvTwentyTwoDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentyTwoDay.getText().toString();
                            spUtils.put(context, "configTwentyTwoDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentyTwoDay.addTextChangedListener(TvTwentyTwoDay);
                    editTexTwentyTwoDay.setTag(TvTwentyTwoDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentyTwoDay.setSelection(viewHolder.tvProTwentyTwoDay.length());


                    viewHolder.tvProTwentyThreeDay.setEnabled(true);
                    final EditText editTexTwentyThreeDay = viewHolder.tvProTwentyThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
                        editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
                    }
                    TextWatcher TvTwentyThreeDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentyThreeDay.getText().toString();
                            spUtils.put(context, "configTwentyThreeDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentyThreeDay.addTextChangedListener(TvTwentyThreeDay);
                    editTexTwentyThreeDay.setTag(TvTwentyThreeDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentyThreeDay.setSelection(viewHolder.tvProTwentyThreeDay.length());


                    viewHolder.tvProTwentyForeDay.setEnabled(true);
                    final EditText editTexTwentyForeDay = viewHolder.tvProTwentyForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
                        editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
                    }
                    TextWatcher TvTwentyForeDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentyForeDay.getText().toString();
                            spUtils.put(context, "configTwentyForeDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentyForeDay.addTextChangedListener(TvTwentyForeDay);
                    editTexTwentyForeDay.setTag(TvTwentyForeDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentyForeDay.setSelection(viewHolder.tvProTwentyForeDay.length());


                    viewHolder.tvProTwentyFiveDay.setEnabled(true);
                    final EditText editTexTwentyFiveDay = viewHolder.tvProTwentyFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
                        editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
                    }
                    TextWatcher TvTwentyFiveDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentyFiveDay.getText().toString();
                            spUtils.put(context, "configTwentyFiveDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentyFiveDay.addTextChangedListener(TvTwentyFiveDay);
                    editTexTwentyFiveDay.setTag(TvTwentyFiveDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentyFiveDay.setSelection(viewHolder.tvProTwentyFiveDay.length());


                    viewHolder.tvProTwentySixDay.setEnabled(true);
                    final EditText editTexTwentySixDay = viewHolder.tvProTwentySixDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
                        editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
                    }
                    TextWatcher TvTwentySixDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentySixDay.getText().toString();
                            spUtils.put(context, "configTwentySixDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentySixDay.addTextChangedListener(TvTwentySixDay);
                    editTexTwentySixDay.setTag(TvTwentySixDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentySixDay.setSelection(viewHolder.tvProTwentySixDay.length());


                    viewHolder.tvProTwentySevenDay.setEnabled(true);
                    final EditText editTexTwentySevenDay = viewHolder.tvProTwentySevenDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
                        editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
                    }
                    TextWatcher TvTwentySevenDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentySevenDay.getText().toString();
                            spUtils.put(context, "configTwentySevenDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentySevenDay.addTextChangedListener(TvTwentySevenDay);
                    editTexTwentySevenDay.setTag(TvTwentySevenDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentySevenDay.setSelection(viewHolder.tvProTwentySevenDay.length());


                    viewHolder.tvProTwentyEightDay.setEnabled(true);
                    final EditText editTexTwentyEightDay = viewHolder.tvProTwentyEightDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
                        editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
                    }
                    TextWatcher TvTwentyEightDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentyEightDay.getText().toString();
                            spUtils.put(context, "configTwentyEightDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentyEightDay.addTextChangedListener(TvTwentyEightDay);
                    editTexTwentyEightDay.setTag(TvTwentyEightDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentyEightDay.setSelection(viewHolder.tvProTwentyEightDay.length());


                    viewHolder.tvProTwentyNineDay.setEnabled(true);
                    final EditText editTexTwentyNineDay = viewHolder.tvProTwentyNineDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
                        editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
                    }
                    TextWatcher TvTwentyNineDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProTwentyNineDay.getText().toString();
                            spUtils.put(context, "configTwentyNineDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexTwentyNineDay.addTextChangedListener(TvTwentyNineDay);
                    editTexTwentyNineDay.setTag(TvTwentyNineDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProTwentyNineDay.setSelection(viewHolder.tvProTwentyNineDay.length());


                    viewHolder.tvProThirtyDay.setEnabled(true);
                    final EditText editTexThirtyDay = viewHolder.tvProThirtyDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexThirtyDay.getTag() instanceof TextWatcher) {
                        editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
                    }
                    TextWatcher TvThirtyDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProThirtyDay.getText().toString();
                            spUtils.put(context, "configThirtyDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexThirtyDay.addTextChangedListener(TvThirtyDay);
                    editTexThirtyDay.setTag(TvThirtyDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProThirtyDay.setSelection(viewHolder.tvProThirtyDay.length());


                    viewHolder.tvProThirtyOneDay.setEnabled(true);
                    final EditText editTexThirtyOneDay = viewHolder.tvProThirtyOneDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
                        editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
                    }
                    TextWatcher TvThirtyOneDay = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProThirtyOneDay.getText().toString();
                            spUtils.put(context, "configThirtyOneDay", proitem);
                            String lastmonth = viewHolder.tvProCompletedLastMonth.getText().toString();
                            if (lastmonth.equals("")) {
                                lastmont = 0;
                            } else {
                                lastmont = Integer.parseInt(lastmonth);
                            }
                            String dayone = viewHolder.tvProOneDay.getText().toString();
                            if (dayone.equals("")) {
                                day1 = 0;
                            } else {
                                day1 = Integer.parseInt(dayone);
                            }
                            String daytwo = viewHolder.tvProTwoDay.getText().toString();
                            if (daytwo.equals("")) {
                                day2 = 0;
                            } else {
                                day2 = Integer.parseInt(daytwo);
                            }
                            String dayThree = viewHolder.tvProThreeDay.getText().toString();
                            if (dayThree.equals("")) {
                                day3 = 0;
                            } else {
                                day3 = Integer.parseInt(dayThree);
                            }
                            String dayfore = viewHolder.tvProForeDay.getText().toString();
                            if (dayfore.equals("")) {
                                day4 = 0;
                            } else {
                                day4 = Integer.parseInt(dayfore);
                            }
                            String dayfive = viewHolder.tvProFiveDay.getText().toString();
                            if (dayfive.equals("")) {
                                day5 = 0;
                            } else {
                                day5 = Integer.parseInt(dayfive);
                            }
                            String daysix = viewHolder.tvProSixDay.getText().toString();
                            if (daysix.equals("")) {
                                day6 = 0;
                            } else {
                                day6 = Integer.parseInt(daysix);
                            }
                            String daySeven = viewHolder.tvProSevenDay.getText().toString();
                            if (daySeven.equals("")) {
                                day7 = 0;
                            } else {
                                day7 = Integer.parseInt(daySeven);
                            }
                            String dayEight = viewHolder.tvProEightDay.getText().toString();
                            if (dayEight.equals("")) {
                                day8 = 0;
                            } else {
                                day8 = Integer.parseInt(dayEight);
                            }
                            String dayNine = viewHolder.tvProNineDay.getText().toString();
                            if (dayNine.equals("")) {
                                day9 = 0;
                            } else {
                                day9 = Integer.parseInt(dayNine);
                            }
                            String dayTen = viewHolder.tvProTenDay.getText().toString();
                            if (dayTen.equals("")) {
                                day10 = 0;
                            } else {
                                day10 = Integer.parseInt(dayTen);
                            }
                            String dayEleven = viewHolder.tvProElevenDay.getText().toString();
                            if (dayEleven.equals("")) {
                                day11 = 0;
                            } else {
                                day11 = Integer.parseInt(dayEleven);
                            }
                            String dayTwelve = viewHolder.tvProTwelveDay.getText().toString();
                            if (dayTwelve.equals("")) {
                                day12 = 0;
                            } else {
                                day12 = Integer.parseInt(dayTwelve);
                            }
                            String dayThirteen = viewHolder.tvProThirteenDay.getText().toString();
                            if (dayThirteen.equals("")) {
                                day13 = 0;
                            } else {
                                day13 = Integer.parseInt(dayThirteen);
                            }
                            String dayFourteen = viewHolder.tvProFourteenDay.getText().toString();
                            if (dayFourteen.equals("")) {
                                day14 = 0;
                            } else {
                                day14 = Integer.parseInt(dayFourteen);
                            }
                            String dayFifteen = viewHolder.tvProFifteenDay.getText().toString();
                            if (dayFifteen.equals("")) {
                                day15 = 0;
                            } else {
                                day15 = Integer.parseInt(dayFifteen);
                            }
                            String daySixteen = viewHolder.tvProSixteenDay.getText().toString();
                            if (daySixteen.equals("")) {
                                day16 = 0;
                            } else {
                                day16 = Integer.parseInt(daySixteen);
                            }
                            String daySeventeen = viewHolder.tvProSeventeenDay.getText().toString();
                            if (daySeventeen.equals("")) {
                                day17 = 0;
                            } else {
                                day17 = Integer.parseInt(daySeventeen);
                            }
                            String dayEighteen = viewHolder.tvProEighteenDay.getText().toString();
                            if (dayEighteen.equals("")) {
                                day18 = 0;
                            } else {
                                day18 = Integer.parseInt(dayEighteen);
                            }
                            String dayNineteen = viewHolder.tvProNineteenDay.getText().toString();
                            if (dayNineteen.equals("")) {
                                day19 = 0;
                            } else {
                                day19 = Integer.parseInt(dayNineteen);
                            }
                            String dayTwenty = viewHolder.tvProTwentyDay.getText().toString();
                            if (dayTwenty.equals("")) {
                                day20 = 0;
                            } else {
                                day20 = Integer.parseInt(dayTwenty);
                            }
                            String dayTwentyOne = viewHolder.tvProTwentyOneDay.getText().toString();
                            if (dayTwentyOne.equals("")) {
                                day21 = 0;
                            } else {
                                day21 = Integer.parseInt(dayTwentyOne);
                            }
                            String dayTwentyTwo = viewHolder.tvProTwentyTwoDay.getText().toString();
                            if (dayTwentyTwo.equals("")) {
                                day22 = 0;
                            } else {
                                day22 = Integer.parseInt(dayTwentyTwo);
                            }
                            String dayTwentyThree = viewHolder.tvProTwentyThreeDay.getText().toString();
                            if (dayTwentyThree.equals("")) {
                                day23 = 0;
                            } else {
                                day23 = Integer.parseInt(dayTwentyThree);
                            }
                            String dayTwentyFore = viewHolder.tvProTwentyForeDay.getText().toString();
                            if (dayTwentyFore.equals("")) {
                                day24 = 0;
                            } else {
                                day24 = Integer.parseInt(dayTwentyFore);
                            }
                            String dayTwentyFive = viewHolder.tvProTwentyFiveDay.getText().toString();
                            if (dayTwentyFive.equals("")) {
                                day25 = 0;
                            } else {
                                day25 = Integer.parseInt(dayTwentyFive);
                            }
                            String dayTwentySix = viewHolder.tvProTwentySixDay.getText().toString();
                            if (dayTwentySix.equals("")) {
                                day26 = 0;
                            } else {
                                day26 = Integer.parseInt(dayTwentySix);
                            }
                            String dayTwentySeven = viewHolder.tvProTwentySevenDay.getText().toString();
                            if (dayTwentySeven.equals("")) {
                                day27 = 0;
                            } else {
                                day27 = Integer.parseInt(dayTwentySeven);
                            }
                            String dayTwentyEight = viewHolder.tvProTwentyEightDay.getText().toString();
                            if (dayTwentyEight.equals("")) {
                                day28 = 0;
                            } else {
                                day28 = Integer.parseInt(dayTwentyEight);
                            }
                            String dayTwentyNine = viewHolder.tvProTwentyNineDay.getText().toString();
                            if (dayTwentyNine.equals("")) {
                                day29 = 0;
                            } else {
                                day29 = Integer.parseInt(dayTwentyNine);
                            }
                            String dayThirty = viewHolder.tvProThirtyDay.getText().toString();
                            if (dayThirty.equals("")) {
                                day30 = 0;
                            } else {
                                day30 = Integer.parseInt(dayThirty);
                            }
                            String dayThirtyOne = viewHolder.tvProThirtyOneDay.getText().toString();
                            if (dayThirtyOne.equals("")) {
                                day31 = 0;
                            } else {
                                day31 = Integer.parseInt(dayThirtyOne);
                            }
                            /**
                             * 计算总完工数（每月数量相加）
                             */
                            int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                                    + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                                    + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                                    + day29 + day30 + day31;
                            String countmonth = String.valueOf(count);
                            viewHolder.tvProTotalCompletion.setText(countmonth);
                        }
                    };
                    editTexThirtyOneDay.addTextChangedListener(TvThirtyOneDay);
                    editTexThirtyOneDay.setTag(TvThirtyOneDay);
            /*光标放置在文本最后*/
                    viewHolder.tvProThirtyOneDay.setSelection(viewHolder.tvProThirtyOneDay.length());


                    final EditText editTexRemarks = viewHolder.tvProRemarks;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexRemarks.getTag() instanceof TextWatcher) {
                        editTexRemarks.removeTextChangedListener((TextWatcher) editTexRemarks.getTag());
                    }
                    TextWatcher TvRemarks = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            Log.d(TAG, "beforeTextChanged");
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Log.d(TAG, "onTextChanged");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Log.d(TAG, "afterTextChanged");
                            String proitem = viewHolder.tvProRemarks.getText().toString();
                            spUtils.put(context, "configRemarks", proitem);
                        }
                    };
                    editTexRemarks.addTextChangedListener(TvRemarks);
                    editTexRemarks.setTag(TvRemarks);
            /*光标放置在文本最后*/
                    viewHolder.tvProRemarks.setSelection(viewHolder.tvProRemarks.length());


                    viewHolder.tvProRecorder.setText(nameid);
                    String configrecorder = viewHolder.tvProRecorder.getText().toString();
                    spUtils.put(context, "configrecorder", configrecorder);


                    viewHolder.tvProRecordat.setText(year + "/" + month + "/" + datetime);
                    String configrecordat = viewHolder.tvProRecordat.getText().toString();
                    spUtils.put(context, "configrecordat", configrecordat);

                } else {
                    final EditText editTexOthers = viewHolder.tvProOthers;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexOthers.getTag() instanceof TextWatcher) {
                        editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
                    }
                    editTexOthers.setText(tvnewlyOthers);

                    final EditText editTexTaskNumber = viewHolder.tvProTaskNumber;
//            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                        editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
                    }
                    viewHolder.tvProTaskNumber.setText(tvdate);

                    final EditText editTexLastMonth = viewHolder.tvProCompletedLastMonth;
//            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexLastMonth.getTag() instanceof TextWatcher) {
                        editTexLastMonth.removeTextChangedListener((TextWatcher) editTexLastMonth.getTag());
                    }
                    viewHolder.tvProCompletedLastMonth.setText(tvnewlyCompletedLastMonth);

                    final EditText editTexOneDay = viewHolder.tvProOneDay;
                    viewHolder.tvProOneDay.setVisibility(View.VISIBLE);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexOneDay.getTag() instanceof TextWatcher) {
                        editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
                    }

                    final EditText editTexTwoDay = viewHolder.tvProTwoDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTwoDay.getTag() instanceof TextWatcher) {
                        editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
                    }

                    final EditText editTexThreeDay = viewHolder.tvProThreeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexThreeDay.getTag() instanceof TextWatcher) {
                        editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
                    }

                    final EditText editTexForeDay = viewHolder.tvProForeDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexForeDay.getTag() instanceof TextWatcher) {
                        editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
                    }

                    final EditText editTexFiveDay = viewHolder.tvProFiveDay;
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexFiveDay.getTag() instanceof TextWatcher) {
                        editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
                    }
                }
            }
            return convertView;
        }
    }

    @Override
    protected void onDestroy() {
        //关闭界面时清除缓存中可输入的数据
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("mylistStr");//保存集合
        editor.remove("tvnewlyProcedure");//工序
        editor.remove("Configdepartment");//部门
        editor.remove("ComfigMonth");//月份
        editor.remove("ConfigProcedure");
        editor.remove("ConfigOthers");//组别人数
        editor.remove("configOneDay");//1
        editor.remove("configTwoDay");//2
        editor.remove("configThreeDay");//3
        editor.remove("configForeDay");//4
        editor.remove("configFiveDay");//5
        editor.remove("configSixDay");//6
        editor.remove("configSevenDay");//7
        editor.remove("configEightDay");//8
        editor.remove("configNineDay");//9
        editor.remove("configTenDay");//10
        editor.remove("configElevenDay");//11
        editor.remove("configTwelveDay");//12
        editor.remove("configThirteenDay");//13
        editor.remove("configFourteenDay");//14
        editor.remove("configFifteenDay");//15
        editor.remove("configSixteenDay");//16
        editor.remove("configSeventeenDay");//17
        editor.remove("configEighteenDay");//18
        editor.remove("configNineteenDay");//19
        editor.remove("configTwentyDay");//20
        editor.remove("configTwentyOneDay");//21
        editor.remove("configTwentyTwoDay");//22
        editor.remove("configTwentyThreeDay");//23
        editor.remove("configTwentyForeDay");//24
        editor.remove("configTwentyFiveDay");//25
        editor.remove("configTwentySixDay");//26
        editor.remove("configTwentySevenDay");//27
        editor.remove("configTwentyEightDay");//28
        editor.remove("configTwentyNineDay");//29
        editor.remove("configThirtyDay");//30
        editor.remove("configThirtyOneDay");//31
        editor.remove("configRemarks");//备注
        editor.remove("ConfigLastMonth");//上月完工
        editor.remove("ConfigTaskNumber");//任务数
        editor.commit();
        super.onDestroy();
    }
}