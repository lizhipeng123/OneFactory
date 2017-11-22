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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.ProducationCopyDailyBean;
import com.daoran.newfactory.onefactory.bean.ProducationCopyNewlyComfigSaveBean;
import com.daoran.newfactory.onefactory.bean.ProductionNewlybooleanBean;
import com.daoran.newfactory.onefactory.bean.Propostbean;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.PhoneSaveUtil;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.file.json.StringUtil;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.view.dialog.ResponseDialog;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 复制保存界面
 * Created by lizhipeng on 2017/7/24.
 */

public class ProductionCopyComfigActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private static final String TAG = "configtest";
    private NoscrollListView mData;//主列表listview
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack;//返回图片按钮
    private List<ProductionNewlybooleanBean.DataBean> booleandatelist =
            new ArrayList<ProductionNewlybooleanBean.DataBean>();//新建和复制生产日报时用于判断是否可以同一个人同一款号创建集合
    private ProductionNewlybooleanBean newlybooleanBean;//新建和复制生产日报时用于判断是否可以同一个人同一款号创建实体
    private List<ProducationCopyNewlyComfigSaveBean> newlyComfigSaveBeen
            = new ArrayList<ProducationCopyNewlyComfigSaveBean>();//生产日报复制保存集合
    private AlertDialog noticeDialog;//退出时是否保存弹出框
    private Button btnProSave;//保存按钮
    private MyAdatper comfigAdapter;//新建保存适配列表数据
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
    private SharedPreferences sp;//轻量级存储
    private SPUtils spUtils;
    private List<Map<String, Object>> mdate;
    private int year, month, datetime, hour, minute, second;
    int isprodure;
    private List<ProducationCopyDailyBean> dailyBeanList =
            new ArrayList<ProducationCopyDailyBean>();//生产日报新建保存集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_production_config);
        getViews();
        initViews();
        setNewlyComfig();
        setListener();
        sp = getSharedPreferences("my_sp", 0);
        String isprodure = sp.getString("isprodure", "");//款号选择页面选择的工序
        //如果选择的是裁床，则隐藏掉每日数量的输入框
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
     * 默认填充款号选择传过来数据
     */
    private void initViews() {
        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        final ProgressDialog progressDialog = ProgressDialog.show(this,
                "请稍候...", "正在查询中...", false, true);
        mdate = getData();
        comfigAdapter = new MyAdatper(this);
        mData.setAdapter(comfigAdapter);//绑定
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
        String tvnewlydate = sp.getString("copyitem", "");//生产日报点击款号传过来的款号
        String tvnewlyDocumentary = sp.getString("copyDocumentary", "");//传过来的跟单
        String tvnewlyFactory = sp.getString("copyFactory", "");//传过来的工厂
        String tvnewlyDepartment = sp.getString("copyDepartment", "");//传过来的部门/组别
        String tvnewlyProcedure = sp.getString("copyProcedure", "");//传过来的工序
        String tvnewlyOthers = sp.getString("copyOthers", "");//传过来的组别人数
        String tvnewSingularSystem = sp.getString("copySingularSystem", "");//传过来的制单数
        String tvdate = sp.getString("copyTaskNumber", "");//传过来的任务数
        String tvnewTaskNumber = sp.getString("copySize", "");//传过来的尺码
        String tvnewlySize = sp.getString("copyyColor", "");//传过来的花色
        String tvnewlyClippingNumber = sp.getString("copyClippingNumber", "");//传过来的实裁数
        String tvnewlyCompletedLastMonth = sp.getString("copyTotalCompletion", "");//传过来的总完工数
        String tvnewlyTotalCompletion = sp.getString("copyState", "");//传过来的状态
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();//将数据放入map集合
        map.put("copyitem", tvnewlydate);
        map.put("copyDocumentary", tvnewlyDocumentary);
        map.put("copyFactory", tvnewlyFactory);
        map.put("copyDepartment", tvnewlyDepartment);
        map.put("copyProcedure", tvnewlyProcedure);
        map.put("copyOthers", tvnewlyOthers);
        map.put("copySingularSystem", tvnewSingularSystem);
        map.put("copyTaskNumber", tvdate);
        map.put("copySize", tvnewTaskNumber);
        map.put("copyyColor", tvnewlySize);
        map.put("copyClippingNumber", tvnewlyClippingNumber);
        map.put("copyTotalCompletion", tvnewlyCompletedLastMonth);
        map.put("copyState", tvnewlyTotalCompletion);
        list.add(map);
        SharedPreferences.Editor editor = sp.edit();
        editor.commit();
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*返回按钮*/
            case R.id.ivProductionBack:
                sethideSoft(v);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("保存提示");
                builder.setMessage("退出是否保存");
                builder.setPositiveButton("保存后退出"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setdeilyData();
                                dialog.dismiss();
                            }
                        });
                builder.setNegativeButton("不保存，直接退出",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                noticeDialog = builder.create();
                noticeDialog.setCanceledOnTouchOutside(false);
                noticeDialog.show();
                break;
            /*复制保存按钮*/
            case R.id.btnProSave:
                sethideSoft(v);
                setdeilyData();
                break;
        }
    }

    /**
     * 判断软键盘是否弹出
     *
     * @param v
     */
    private void sethideSoft(View v) {
        //判断软件盘是否弹出
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //如果弹出则收回
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
    private void setdeilyData() {
        if (NetWork.isNetWorkAvailable(this)) {
            String stridata = HttpUrl.debugoneUrl + "FactoryPlan/SaveFactoryDaily/";
            sp = getSharedPreferences("my_sp", 0);
            String liststr = sp.getString("mycopylistStr", "");//长按款号传过来的花色集合
            String tvnewlydate = sp.getString("copyitem", "");//生产日报点击款号传过来的款号
            String tvnewlyDocumentary = sp.getString("copyDocumentary", "");//生产日报点击款号传过来的跟单
            String tvnewlyFactory = sp.getString("copyFactory", "");//生产日报点击款号传过来的工厂
            String tvnewlyDepartment = sp.getString("copyDepartment", "");//生产日报点击款号传过来的部门/组别
            String tvnewlyProcedure = sp.getString("copyProcedure", "");//生产日报点击款号传过来的工序
            String proProcedureadapterTitle = sp.getString("ConfigProcedure", "");//本类工序adapter中修改过的
            String procudureTitle;//工序变量
            //如果修改过工序,则将修改过的工序放入工序变量
            //如果没有修改过工序,则默认取点击款号传过来的工序
            if (!proProcedureadapterTitle.equals("")) {
                procudureTitle = proProcedureadapterTitle;
            } else {
                procudureTitle = tvnewlyProcedure;
            }
            String tvnewlyOthers = sp.getString("copyOthers", "");//生产日报点击款号传过来的组别人数
            String tvnewSingularSystem = sp.getString("copySingularSystem", "");//生产日报点击款号传过来的制单数
            String tvdate = sp.getString("copyTaskNumber", "");//生产日报点击款号传过来的任务数
            String tvnewTaskNumber = sp.getString("copySize", "");//生产日报点击款号传过来的尺码
            String tvnewlySize = sp.getString("copyyColor", "");//生产日报点击款号传过来的花色
            String tvcopynewlycolor = sp.getString("configcolor", "");//生产日报点击款号传过来的表中传过来的花色
            String tvnewlyClippingNumber = sp.getString("copyClippingNumber", "");//生产日报点击款号传过来的实裁数
            String tvnewlyCompletedLastMonth = sp.getString("copyTotalCompletion", "");//生产日报点击款号传过来的总完工数
            String copyBalanceAmount = sp.getString("copyBalanceAmount", "");//生产日报点击款号传过来的结余数量
            String copyCompletedLastMonth = sp.getString("copyCompletedLastMonth", "");//生产日报点击款号传过来的上月完工
            String tvnewlyTotalCompletion = sp.getString("copyState", "");//生产日报点击款号传过来的状态
            String tvnewlyid = sp.getString("proadapterid", "");//生产日报点击款号传过来的id
            String tvnewlyserid = sp.getString("prosalesid", "");//生产日报点击款号传过来的行id
            String tvnewlyProYear = sp.getString("copyProYear", "");//生产日报点击款号传过来的年
            String tvnewlyMonth = sp.getString("copyMonth", "");//生产日报点击款号传过来的月份
            String productionMonth = sp.getString("ComfigMonth", "");//本adapter修改过的月份
            String copyRecorder = sp.getString("copyRecorder", "");//生产日报点击款号传过来的制单人
            String copyRecordat = sp.getString("copyRecordat", "");//生产日报点击款号传过来的制单时间
            String copyRecordid = sp.getString("username", "");//登录页传过来的当前用户名
            String copymonth;//月份变量
            //如果修改过月份，则将修改过的月份放入月份变量中，
            //如果没有修改过月份，则默认取款号传过来的月份
            if (productionMonth.equals("")) {
                copymonth = tvnewlyMonth;
            } else {
                copymonth = productionMonth;
            }
            //如果工序是裁床，实裁数变为0
            if (tvnewlyProcedure.equals("裁床")) {
                tvnewlyClippingNumber = String.valueOf(0);
            }
            int listsize = booleandatelist.size();//
            if (listsize == 0) {
                listsize = 1;
            } else {
                listsize = booleandatelist.size();
            }
            String[] arrsitem = tvnewlydate.split(",");//修改的款号数组
            String[] arrspredure = procudureTitle.split(",");//修改的工序数组
            String[] arrsmonth = productionMonth.split(",");//修改的月份数组
            String[] arrsdatemonth = new String[listsize];
            String[] arrsdatepredure = new String[listsize];//符合条件的工序数组
            //在循环中如果修改过数据，则将对应的款号，工序，月份，保存到数组中
            for (int i = 0; i < listsize; i++) {
                if (listsize != 0) {
                    //如果集合中的款号不为空,则将对应的款号添加到数组中
                    if (booleandatelist.get(i).getItem() != null) {
                        String woritem = booleandatelist.get(i).getItem();
                        String[] workitempro = woritem.split(",");
                        boolean probool = containsAll(arrsitem, workitempro);
                        //如果款号对比一致的话，则数组赋值为空
                        //如果款号不一致的话，则将集合中的工序和月份赋值到数组中
                        if (probool == true) {
                            arrsdatepredure[i] = booleandatelist.get(i).getWorkingProcedure();
                            arrsdatemonth[i] = booleandatelist.get(i).getMonth();
                        } else {
                            arrsdatepredure[i] = "";
                            arrsdatemonth[i] = "";
                        }
                    }
                } else {
                    arrsdatepredure[i] = "";
                    arrsdatemonth[i] = "";
                }
            }
            System.out.print(arrsdatepredure + "");//符合条件的工序
            System.out.print(arrsdatemonth + "");//符合条件的月份
            StringBuffer sb = new StringBuffer();//实例化截取字符串
            //循环中，如果有字符是空值，则跳过，不取，
            //如果有值，则数组中字符不变，将数组中的空值去掉，只保留有值的
            for (int i = 0; i < arrsdatepredure.length; i++) {
                if ("".equals(arrsdatepredure[i])) {
                    continue;
                }
                sb.append(arrsdatepredure[i]);
                if (i != arrsdatepredure.length - 1) {
                    sb.append(";");
                }
            }
            arrsdatepredure = sb.toString().split(";");
            for (int i = 0; i < arrsdatepredure.length; i++) {
                System.out.print(arrsdatepredure[i] + "");
            }
            System.out.print(arrsdatepredure + "");

            StringBuffer sb2 = new StringBuffer();
            for (int i = 0; i < arrsdatemonth.length; i++) {
                if ("".equals(arrsdatemonth[i])) {
                    continue;
                }
                sb2.append(arrsdatemonth[i]);
                if (i != arrsdatemonth.length - 1) {
                    sb2.append(";");
                }
            }
            arrsdatemonth = sb2.toString().split(";");
            for (int i = 0; i < arrsdatemonth.length; i++) {
                System.out.print(arrsdatemonth[i] + "");
            }
            System.out.print(arrsdatemonth + "");
            boolean monthbool = containsAll(arrsdatemonth, arrsmonth);//判断月份是否有一样的
            boolean predurebool = containsAll(arrsdatepredure, arrspredure);//判断工序是否有一样的
            final ProgressDialog progressDialog = ProgressDialog.show(this,
                    "请稍候...", "正在保存中...", false, true);
            //如果工序一样，则继续判断月份，如果月份也一样，则提示存在相同的，不可保存
            //只要工序，月份有一个不一样，则可以执行后面的方法保存
            if (predurebool == true) {
                if (monthbool == true) {
                    ToastUtils.ShowToastMessage("已存在相同月份，工序的款号", ProductionCopyComfigActivity.this);
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
                } else {
                    //如果工序是裁床，则需判断花色是否有多种，有则需创建多条数据
                    if (procudureTitle.equals("裁床")) {
                        if (!TextUtils.isEmpty(liststr)) {
                            List<String> list = null;
                            Gson gson = new Gson();
                            try {
                                list = PhoneSaveUtil.String2SceneList(liststr);
                                //循环中，将符合条件的数据加到实体bean中
                                for (int j = 0; j < list.size(); j++) {
                                    ProducationCopyNewlyComfigSaveBean consaveBean =
                                            new ProducationCopyNewlyComfigSaveBean();
                                    consaveBean.setID("0");
                                    consaveBean.setRecordid(copyRecordid);
                                    consaveBean.setSalesid(tvnewlyserid);
                                    consaveBean.setProdcol(list.get(j));
                                    consaveBean.setItem(tvnewlydate);
                                    consaveBean.setPrddocumentary(tvnewlyDocumentary);
                                    consaveBean.setSubfactory(tvnewlyFactory);
                                    consaveBean.setSubfactoryTeams(tvnewlyDepartment);
                                    consaveBean.setWorkingProcedure(procudureTitle);
                                    consaveBean.setWorkers(tvnewlyOthers);
                                    consaveBean.setPqty(tvnewSingularSystem);
                                    consaveBean.setTaskqty(tvdate);
                                    consaveBean.setMdl(tvnewTaskNumber);
                                    consaveBean.setProdcol(tvnewlySize);
                                    consaveBean.setFactcutqty(tvnewlyClippingNumber);
                                    consaveBean.setSumCompletedQty(tvnewlyCompletedLastMonth);
                                    consaveBean.setLastMonQty(copyCompletedLastMonth);
                                    consaveBean.setLeftQty(copyBalanceAmount);
                                    consaveBean.setPrdstatus(tvnewlyTotalCompletion);
                                    consaveBean.setYear(tvnewlyProYear);
                                    consaveBean.setMonth(copymonth);
                                    consaveBean.setRecorder(copyRecorder);
                                    consaveBean.setRecordat(copyRecordat);
                                    newlyComfigSaveBeen.add(consaveBean);
                                }
                                System.out.print(list);
                                System.out.print(newlyComfigSaveBeen);
                                String detailb = gson.toJson(newlyComfigSaveBeen);//将实体bean数据转化为json
                                String dateee = detailb.replace("\"\"", "null");//格式化
                                //判断保存中的数据是否为空
                                if (newlyComfigSaveBeen.equals("")) {
                                    ToastUtils.ShowToastMessage("没有数据可以保存", ProductionCopyComfigActivity.this);
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
                                } else {
                                    if (NetWork.isNetWorkAvailable(this)) {
                                        OkHttpUtils.postString()
                                                .url(stridata)//url地址
                                                .content(dateee)//要传输的json内容
                                                .mediaType(MediaType.parse("application/json;charset=utf-8"))//传输格式
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
                                                    public void onResponse(final String response, int id) {
                                                        System.out.print(response);
                                                        Thread thread = new Thread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                try {
                                                                    Thread.sleep(1000);
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                String ression = StringUtil.sideTrim(response, "\"");
                                                                System.out.print(ression);
                                                                int resindex = Integer.parseInt(ression);
                                                                if (resindex > 4) {
                                                                    ToastUtils.ShowToastMessage("保存成功，请返回生产日报页面并刷新",
                                                                            ProductionCopyComfigActivity.this);
                                                                    saveDestroy();
                                                                    startActivity(new Intent(ProductionCopyComfigActivity.this,
                                                                            ProductionActivity.class));
                                                                } else if (resindex == 3) {
                                                                    ToastUtils.ShowToastMessage("保存失败",
                                                                            ProductionCopyComfigActivity.this);
                                                                } else if (resindex == 4) {
                                                                    ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                            ProductionCopyComfigActivity.this);
                                                                } else if (resindex == 2) {
                                                                    ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                            ProductionCopyComfigActivity.this);
                                                                } else {
                                                                    ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                            ProductionCopyComfigActivity.this);
                                                                }
                                                                progressDialog.dismiss();
                                                            }
                                                        });
                                                        thread.start();
                                                    }
                                                });
                                    } else {
                                        ToastUtils.ShowToastMessage(R.string.noHttp, ProductionCopyComfigActivity.this);
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ProducationCopyDailyBean dailyBean = new ProducationCopyDailyBean();
                            dailyBean.setID("");
                            dailyBean.setSalesid(tvnewlyserid);
                            dailyBean.setItem(tvnewlydate);
                            dailyBean.setPrddocumentary(tvnewlyDocumentary);
                            dailyBean.setSubfactory(tvnewlyFactory);
                            dailyBean.setSubfactoryTeams(tvnewlyDepartment);
                            dailyBean.setWorkingProcedure(procudureTitle);
                            dailyBean.setWorkers(tvnewlyOthers);
                            dailyBean.setPqty(tvnewSingularSystem);
                            dailyBean.setTaskqty(tvdate);
                            dailyBean.setMdl(tvnewTaskNumber);
                            dailyBean.setProdcol(tvnewlySize);
                            dailyBean.setFactcutqty(tvnewlyClippingNumber);
                            dailyBean.setSumCompletedQty(tvnewlyCompletedLastMonth);
                            dailyBean.setPrdstatus(tvnewlyTotalCompletion);
                            dailyBean.setYear(tvnewlyProYear);
                            dailyBean.setMonth(copymonth);
                            dailyBean.setRecorder(copyRecorder);
                            dailyBean.setRecordat(copyRecordat);
                            dailyBean.setRecordid(copyRecordid);
                            dailyBeanList.add(dailyBean);
                            Gson gson = new Gson();
                            String savebeanlist = gson.toJson(dailyBeanList);
                            String dateee = savebeanlist.replace("\"\"", "null");
                            if (dailyBeanList.equals("")) {
                                ToastUtils.ShowToastMessage("没有数据可以保存", ProductionCopyComfigActivity.this);
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
                            } else {
                                if (NetWork.isNetWorkAvailable(this)) {
                                    OkHttpUtils.postString()
                                            .url(stridata)
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
                                                public void onResponse(final String response, int id) {
                                                    System.out.print(response);
                                                    Thread thread = new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                Thread.sleep(1000);
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            String ression = StringUtil.sideTrim(response, "\"");
                                                            System.out.print(ression);
                                                            int resindex = Integer.parseInt(ression);
                                                            if (resindex > 4) {
                                                                ToastUtils.ShowToastMessage("保存成功，请返回生产日报页面并刷新",
                                                                        ProductionCopyComfigActivity.this);
                                                                saveDestroy();
                                                                startActivity(new Intent(ProductionCopyComfigActivity.this,
                                                                        ProductionActivity.class));
                                                            } else if (resindex == 3) {
                                                                ToastUtils.ShowToastMessage("保存失败",
                                                                        ProductionCopyComfigActivity.this);
                                                            } else if (resindex == 4) {
                                                                ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                        ProductionCopyComfigActivity.this);
                                                            } else if (resindex == 2) {
                                                                ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                        ProductionCopyComfigActivity.this);
                                                            } else {
                                                                ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                        ProductionCopyComfigActivity.this);
                                                            }
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                                    thread.start();
                                                }
                                            });
                                } else {
                                    ToastUtils.ShowToastMessage(R.string.noHttp, ProductionCopyComfigActivity.this);
                                }
                            }
                        }
                    } else {
                        ProducationCopyDailyBean dailyBean = new ProducationCopyDailyBean();
                        dailyBean.setID("");
                        dailyBean.setSalesid(tvnewlyserid);
                        dailyBean.setItem(tvnewlydate);
                        dailyBean.setPrddocumentary(tvnewlyDocumentary);
                        dailyBean.setSubfactory(tvnewlyFactory);
                        dailyBean.setSubfactoryTeams(tvnewlyDepartment);
                        dailyBean.setWorkingProcedure(procudureTitle);
                        dailyBean.setWorkers(tvnewlyOthers);
                        dailyBean.setPqty(tvnewSingularSystem);
                        dailyBean.setTaskqty(tvdate);
                        dailyBean.setMdl(tvnewTaskNumber);
                        dailyBean.setProdcol(tvcopynewlycolor);
                        dailyBean.setFactcutqty(tvnewlyClippingNumber);
                        dailyBean.setSumCompletedQty(tvnewlyCompletedLastMonth);
                        dailyBean.setPrdstatus(tvnewlyTotalCompletion);
                        dailyBean.setYear(tvnewlyProYear);
                        dailyBean.setMonth(copymonth);
                        dailyBean.setRecorder(copyRecorder);
                        dailyBean.setRecordat(copyRecordat);
                        dailyBean.setRecordid(copyRecordid);
                        dailyBeanList.add(dailyBean);
                        Gson gson = new Gson();
                        String savebeanlist = gson.toJson(dailyBeanList);
                        String dateee = savebeanlist.replace("\"\"", "null");
                        if (dailyBeanList.equals("")) {
                            ToastUtils.ShowToastMessage("没有数据可以保存", ProductionCopyComfigActivity.this);
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
                        } else {
                            if (NetWork.isNetWorkAvailable(this)) {
                                OkHttpUtils.postString()
                                        .url(stridata)
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
                                            public void onResponse(final String response, int id) {
                                                System.out.print(response);

                                                Thread thread = new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(1000);
                                                            String ression = StringUtil.sideTrim(response, "\"");
                                                            System.out.print(ression);
                                                            int resindex = Integer.parseInt(ression);
                                                            if (resindex > 4) {
                                                                ToastUtils.ShowToastMessage("保存成功，请返回生产日报页面并刷新",
                                                                        ProductionCopyComfigActivity.this);
                                                                saveDestroy();
                                                                startActivity(new Intent(ProductionCopyComfigActivity.this,
                                                                        ProductionActivity.class));
                                                            } else if (resindex == 3) {
                                                                ToastUtils.ShowToastMessage("保存失败",
                                                                        ProductionCopyComfigActivity.this);
                                                            } else if (resindex == 4) {
                                                                ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                        ProductionCopyComfigActivity.this);
                                                            } else if (resindex == 2) {
                                                                ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                        ProductionCopyComfigActivity.this);
                                                            } else {
                                                                ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                        ProductionCopyComfigActivity.this);
                                                            }
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                                thread.start();
                                            }
                                        });
                            } else {
                                ToastUtils.ShowToastMessage(R.string.noHttp, ProductionCopyComfigActivity.this);
                            }
                        }
                    }
                }
            } else {
                if (procudureTitle.equals("裁床")) {
                    if (!TextUtils.isEmpty(liststr)) {
                        List<String> list = null;
                        Gson gson = new Gson();
                        try {
                            list = PhoneSaveUtil.String2SceneList(liststr);
                            for (int j = 0; j < list.size(); j++) {
                                ProducationCopyNewlyComfigSaveBean consaveBean =
                                        new ProducationCopyNewlyComfigSaveBean();
                                consaveBean.setID("0");
                                consaveBean.setRecordid(copyRecordid);
                                consaveBean.setSalesid(tvnewlyserid);
                                consaveBean.setProdcol(list.get(j));
                                consaveBean.setItem(tvnewlydate);
                                consaveBean.setPrddocumentary(tvnewlyDocumentary);
                                consaveBean.setSubfactory(tvnewlyFactory);
                                consaveBean.setSubfactoryTeams(tvnewlyDepartment);
                                consaveBean.setWorkingProcedure(procudureTitle);
                                consaveBean.setWorkers(tvnewlyOthers);
                                consaveBean.setPqty(tvnewSingularSystem);
                                consaveBean.setTaskqty(tvdate);
                                consaveBean.setMdl(tvnewTaskNumber);
                                consaveBean.setProdcol(tvnewlySize);
                                consaveBean.setFactcutqty(tvnewlyClippingNumber);
                                consaveBean.setSumCompletedQty(tvnewlyCompletedLastMonth);
                                consaveBean.setLastMonQty(copyCompletedLastMonth);
                                consaveBean.setLeftQty(copyBalanceAmount);
                                consaveBean.setPrdstatus(tvnewlyTotalCompletion);
                                consaveBean.setYear(tvnewlyProYear);
                                consaveBean.setMonth(copymonth);
                                consaveBean.setRecorder(copyRecorder);
                                consaveBean.setRecordat(copyRecordat);
                                newlyComfigSaveBeen.add(consaveBean);
                            }
                            System.out.print(list);
                            System.out.print(newlyComfigSaveBeen);
                            String detailb = gson.toJson(newlyComfigSaveBeen);
                            String dateee = detailb.replace("\"\"", "null");
                            if (newlyComfigSaveBeen.equals("")) {
                                ToastUtils.ShowToastMessage("没有数据可以保存", ProductionCopyComfigActivity.this);
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
                            } else {
                                if (NetWork.isNetWorkAvailable(this)) {
                                    OkHttpUtils.postString()
                                            .url(stridata)
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
                                                public void onResponse(final String response, int id) {
                                                    System.out.print(response);
                                                    Thread thread = new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                Thread.sleep(1000);
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            String ression = StringUtil.sideTrim(response, "\"");
                                                            System.out.print(ression);
                                                            int resindex = Integer.parseInt(ression);
                                                            if (resindex > 4) {
                                                                ToastUtils.ShowToastMessage("保存成功，请返回生产日报页面并刷新",
                                                                        ProductionCopyComfigActivity.this);
                                                                saveDestroy();
                                                                startActivity(new Intent(ProductionCopyComfigActivity.this,
                                                                        ProductionActivity.class));
                                                            } else if (resindex == 3) {
                                                                ToastUtils.ShowToastMessage("保存失败",
                                                                        ProductionCopyComfigActivity.this);
                                                            } else if (resindex == 4) {
                                                                ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                        ProductionCopyComfigActivity.this);
                                                            } else if (resindex == 2) {
                                                                ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                        ProductionCopyComfigActivity.this);
                                                            } else {
                                                                ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                        ProductionCopyComfigActivity.this);
                                                            }
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                                    thread.start();
                                                }
                                            });
                                } else {
                                    ToastUtils.ShowToastMessage(R.string.noHttp, ProductionCopyComfigActivity.this);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ProducationCopyDailyBean dailyBean = new ProducationCopyDailyBean();
                        dailyBean.setID("");
                        dailyBean.setSalesid(tvnewlyserid);
                        dailyBean.setItem(tvnewlydate);
                        dailyBean.setPrddocumentary(tvnewlyDocumentary);
                        dailyBean.setSubfactory(tvnewlyFactory);
                        dailyBean.setSubfactoryTeams(tvnewlyDepartment);
                        dailyBean.setWorkingProcedure(procudureTitle);
                        dailyBean.setWorkers(tvnewlyOthers);
                        dailyBean.setPqty(tvnewSingularSystem);
                        dailyBean.setTaskqty(tvdate);
                        dailyBean.setMdl(tvnewTaskNumber);
                        dailyBean.setProdcol(tvnewlySize);
                        dailyBean.setFactcutqty(tvnewlyClippingNumber);
                        dailyBean.setSumCompletedQty(tvnewlyCompletedLastMonth);
                        dailyBean.setPrdstatus(tvnewlyTotalCompletion);
                        dailyBean.setYear(tvnewlyProYear);
                        dailyBean.setMonth(copymonth);
                        dailyBean.setRecorder(copyRecorder);
                        dailyBean.setRecordat(copyRecordat);
                        dailyBean.setRecordid(copyRecordid);
                        dailyBeanList.add(dailyBean);
                        Gson gson = new Gson();
                        String savebeanlist = gson.toJson(dailyBeanList);
                        String dateee = savebeanlist.replace("\"\"", "null");
                        if (dailyBeanList.equals("")) {
                            ToastUtils.ShowToastMessage("没有数据可以保存", ProductionCopyComfigActivity.this);
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
                        } else {
                            if (NetWork.isNetWorkAvailable(this)) {
                                OkHttpUtils.postString()
                                        .url(stridata)
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
                                            public void onResponse(final String response, int id) {
                                                System.out.print(response);
                                                Thread thread = new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(1000);
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        }
                                                        String ression = StringUtil.sideTrim(response, "\"");
                                                        System.out.print(ression);
                                                        int resindex = Integer.parseInt(ression);
                                                        if (resindex > 4) {
                                                            ToastUtils.ShowToastMessage("保存成功，请返回生产日报页面并刷新",
                                                                    ProductionCopyComfigActivity.this);
                                                            saveDestroy();
                                                            startActivity(new Intent(ProductionCopyComfigActivity.this,
                                                                    ProductionActivity.class));
                                                        } else if (resindex == 3) {
                                                            ToastUtils.ShowToastMessage("保存失败",
                                                                    ProductionCopyComfigActivity.this);
                                                        } else if (resindex == 4) {
                                                            ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                    ProductionCopyComfigActivity.this);
                                                        } else if (resindex == 2) {
                                                            ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                    ProductionCopyComfigActivity.this);
                                                        } else {
                                                            ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                    ProductionCopyComfigActivity.this);
                                                        }
                                                        progressDialog.dismiss();
                                                    }
                                                });
                                                thread.start();
                                            }
                                        });
                            } else {
                                ToastUtils.ShowToastMessage(R.string.noHttp, ProductionCopyComfigActivity.this);
                            }
                        }
                    }
                } else {
                    ProducationCopyDailyBean dailyBean = new ProducationCopyDailyBean();
                    dailyBean.setID("");
                    dailyBean.setSalesid(tvnewlyserid);
                    dailyBean.setItem(tvnewlydate);
                    dailyBean.setPrddocumentary(tvnewlyDocumentary);
                    dailyBean.setSubfactory(tvnewlyFactory);
                    dailyBean.setSubfactoryTeams(tvnewlyDepartment);
                    dailyBean.setWorkingProcedure(procudureTitle);
                    dailyBean.setWorkers(tvnewlyOthers);
                    dailyBean.setPqty(tvnewSingularSystem);
                    dailyBean.setTaskqty(tvdate);
                    dailyBean.setMdl(tvnewTaskNumber);
                    dailyBean.setProdcol(tvcopynewlycolor);
                    dailyBean.setFactcutqty(tvnewlyClippingNumber);
                    dailyBean.setSumCompletedQty(tvnewlyCompletedLastMonth);
                    dailyBean.setPrdstatus(tvnewlyTotalCompletion);
                    dailyBean.setYear(tvnewlyProYear);
                    dailyBean.setMonth(copymonth);
                    dailyBean.setRecorder(copyRecorder);
                    dailyBean.setRecordat(copyRecordat);
                    dailyBean.setRecordid(copyRecordid);
                    dailyBeanList.add(dailyBean);
                    Gson gson = new Gson();
                    String savebeanlist = gson.toJson(dailyBeanList);
                    String dateee = savebeanlist.replace("\"\"", "null");
                    if (dailyBeanList.equals("")) {
                        ToastUtils.ShowToastMessage("没有数据可以保存", ProductionCopyComfigActivity.this);
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
                    } else {
                        if (NetWork.isNetWorkAvailable(this)) {
                            OkHttpUtils.postString()
                                    .url(stridata)
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
                                        public void onResponse(final String response, int id) {
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
                                                    String ression = StringUtil.sideTrim(response, "\"");
                                                    System.out.print(ression);
                                                    int resindex = Integer.parseInt(ression);
                                                    if (resindex > 4) {
                                                        ToastUtils.ShowToastMessage("保存成功，请返回生产日报页面并刷新",
                                                                ProductionCopyComfigActivity.this);
                                                        saveDestroy();
                                                        startActivity(new Intent(ProductionCopyComfigActivity.this,
                                                                ProductionActivity.class));
                                                    } else if (resindex == 3) {
                                                        ToastUtils.ShowToastMessage("保存失败",
                                                                ProductionCopyComfigActivity.this);
                                                    } else if (resindex == 4) {
                                                        ToastUtils.ShowToastMessage("数据错误，请重试",
                                                                ProductionCopyComfigActivity.this);
                                                    } else if (resindex == 2) {
                                                        ToastUtils.ShowToastMessage("该单已存在，无法新建！",
                                                                ProductionCopyComfigActivity.this);
                                                    } else {
                                                        ToastUtils.ShowToastMessage("未知错误，请联系管理员",
                                                                ProductionCopyComfigActivity.this);
                                                    }
                                                }
                                            });
                                            thread.start();
                                        }
                                    });
                        } else {
                            ToastUtils.ShowToastMessage(R.string.noHttp, ProductionCopyComfigActivity.this);
                        }
                    }
                }
            }
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, ProductionCopyComfigActivity.this);
        }
    }

    /**
     * 判断 array1是否包含所有的 array2
     */
    private static boolean containsAll(String[] array1, String[] array2) {
        for (String str : array2) {
            if (!ArrayUtils.contains(array1, str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 新建时点击款号后查询有关当前登录用户的数据
     * 查询当前用户在数据库中的全部数据，以便可以和将要复制保存的数据进行比较
     */
    private void setNewlyComfig() {
        String str = HttpUrl.debugoneUrl + "FactoryPlan/BindGridDailyAPP/";//查询生产日报全部的数据
        sp = getSharedPreferences("my_sp", 0);
        String namedure = sp.getString("usernamerecoder", "");//制单人
        String stis = sp.getString("ischeckedd", "");//是否为空
        boolean stris = Boolean.parseBoolean(stis);//将字符串转化为Boolean类型（true或false）
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
        //先将数据添加到bean实体，然后转化成能上传的json数据
        String gsonbeanStr = gson.toJson(propostbean);
        if (NetWork.isNetWorkAvailable(this)) {
            ResponseDialog.showLoading(this);//显示圆圈进度条
            OkHttpUtils.postString()
                    .url(str)//url接口地址
                    .content(gsonbeanStr)//条件内容
                    .mediaType(MediaType.parse("application/json;charset=utf-8"))//格式
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            ResponseDialog.closeLoading();//关闭dialog
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                /*成功返回的结果*/
                                System.out.print(response);
                                String ress = response.replace("\\", "");
                                System.out.print(ress);
                                String ression = StringUtil.sideTrim(ress, "\"");
                                System.out.print(ression);
                                //将查询好的数据转化为实体bean
                                newlybooleanBean = new Gson().fromJson(ression, ProductionNewlybooleanBean.class);
                                booleandatelist = newlybooleanBean.getData();
                                ResponseDialog.closeLoading();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                ResponseDialog.closeLoading();
                            }
                        }
                    });
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请重新再试",
                    ProductionCopyComfigActivity.this);
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
        Spinner tvProMonth;
        TextView tvProSingularSystem,//制单数
                tvProColor,//花色
                tvProSize,//尺码
        //                tvProMonth,//月
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
            final ProductionCopyComfigActivity.ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ProductionCopyComfigActivity.ViewHolder();
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
                viewHolder.tvProMonth = (Spinner) convertView.findViewById(R.id.tvProMonth);
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
                viewHolder = (ProductionCopyComfigActivity.ViewHolder) convertView.getTag();
            }
            sp = getSharedPreferences("my_sp", 0);
            String tvnewly = String.valueOf(mdate.get(position).get("copyitem"));
            System.out.print(tvnewly);
            viewHolder.tv_data.setText(tvnewly);
            String nameid = sp.getString("usernamerecoder", "");//用户名
            String tvnewlyDocumen = String.valueOf(mdate.get(position).get("copyDocumentary"));
            String tvnewlyFactory = String.valueOf(mdate.get(position).get("copyFactory"));
            String tvnewlyDepartment = String.valueOf(mdate.get(position).get("copyDepartment"));
            String tvnewlyProcedure = String.valueOf(mdate.get(position).get("copyProcedure"));
            String tvnewlyOthers = String.valueOf(mdate.get(position).get("copyOthers"));
            String tvnewSingularSystem = String.valueOf(mdate.get(position).get("copySingularSystem"));
            final String tvdate = String.valueOf(mdate.get(position).get("copyTaskNumber"));
            String tvnewTaskNumber = String.valueOf(mdate.get(position).get("copySize"));
            String tvnewlySize = String.valueOf(mdate.get(position).get("copyyColor"));
            String tvnewlyClippingNumber = String.valueOf(mdate.get(position).get("copyClippingNumber"));
            String tvnewlyCompletedLastMonth = String.valueOf(mdate.get(position).get("copyTotalCompletion"));
            String tvnewlyTotalCompletion = String.valueOf(mdate.get(position).get("copyState"));

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
                //判断用户名是否为空
                if (nameid != null && !nameid.equals("")) {
                    viewHolder.tvProDocumentary.setText(tvnewlyDocumen);
                    viewHolder.tvProFactory.setText(tvnewlyFactory);
                    viewHolder.tvProDepartment.setText(tvnewlyDepartment);
                    viewHolder.tvProProcedure.setText(tvnewlyProcedure);
                    viewHolder.tvProOthers.setText(tvnewlyOthers);
                    viewHolder.tvProSingularSystem.setText(tvnewSingularSystem);
                    viewHolder.tvProSize.setText(tvnewTaskNumber);
                    viewHolder.tvProColor.setText(tvnewlySize);
                    viewHolder.tvProState.setText(tvnewlyTotalCompletion);
                    viewHolder.tvProClippingNumber.setText(tvnewlyClippingNumber);
                    viewHolder.tvProRecordat.setText(year + "/" + month + "/" + datetime);
                    String comfigitem = viewHolder.tv_data.getText().toString();
                    spUtils.put(context, "comfigitem", comfigitem);
                    //保存轻量级存储
                    String configdocument = viewHolder.tvProDocumentary.getText().toString();
                    spUtils.put(context, "configdocument", configdocument);

                    String configfactory = viewHolder.tvProFactory.getText().toString();
                    spUtils.put(context, "configfactory", configfactory);
                    //点击选择部门并保存到轻量级存储中
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
                    //点击选择工序并保存到轻量级存储中
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
                                    booleandatelist.get(position).setMemoprdure(title);
                                    //如果选择了裁床，则隐藏从1日到31日的输入框，否则就显示
                                    if (title.equals("裁床")) {
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

                                    } else {
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
                                        viewHolder.tvProOneDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwoDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProThreeDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProForeDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProFiveDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProSixDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProSevenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProEightDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProNineDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProElevenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwelveDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProThirteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProFourteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProFifteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProSixteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProSeventeenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProEighteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProNineteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyOneDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyTwoDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyThreeDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyForeDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyFiveDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentySixDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentySevenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyEightDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyNineDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProThirtyDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProThirtyOneDay.setVisibility(View.VISIBLE);
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
                    });

                    //监听组别人数
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

                    //制单数
                    String configsingular = viewHolder.tvProSingularSystem.getText().toString();
                    spUtils.put(context, "configsingular", configsingular);

                    //花色
                    String configcolor = viewHolder.tvProColor.getText().toString();
                    spUtils.put(context, "configcolor", configcolor);

                    //监听任务数
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
                    spUtils.put(context, "configsize", configsize);//尺码

                    String configclipping = viewHolder.tvProClippingNumber.getText().toString();
                    spUtils.put(context, "configclipping", configclipping);//实裁数

                    final EditText editTexLastMonth = viewHolder.tvProCompletedLastMonth;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexLastMonth.getTag() instanceof TextWatcher) {//监听上月完工
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

                    viewHolder.tvProTotalCompletion.setText(tvnewlyCompletedLastMonth);
                    String configcompletion = viewHolder.tvProTotalCompletion.getText().toString();
                    spUtils.put(context, "configcompletion", configcompletion);//总完工数

                    String configamount = viewHolder.tvProBalanceAmount.getText().toString();
                    spUtils.put(context, "configamount", configamount);//结余数量

                    //监听状态
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

                    viewHolder.tvProYear.setText(year + "");//年
                    String configyear = viewHolder.tvProYear.getText().toString();
                    spUtils.put(context, "configyear", configyear);

                    //可选择的月
                    String[] spinnermonth = context.getResources().getStringArray(R.array.timemonth);
                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(context,
                                    R.layout.adapter_pronewlycomfig_spinner,
                                    spinnermonth);
                    adapter.setDropDownViewResource(R.layout.adapter_pronewlycomfig_spinner_item);
                    viewHolder.tvProMonth.setAdapter(adapter);
                    Calendar now = Calendar.getInstance();
                    int month = now.get(Calendar.MONTH);
                    viewHolder.tvProMonth.setSelection(month, true);
                    viewHolder.tvProMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String[] languages = context.getResources().getStringArray(R.array.timemonth);
                            System.out.print(languages[position]);
                            spUtils.put(context, "ComfigMonth", languages[position]);
//                            booleandatelist.get(position).setMemomonth(languages[position]);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

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

                    final EditText editTexRemarks = viewHolder.tvProRemarks;//监听备注信息
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

                    viewHolder.tvProRecorder.setText(nameid);//制单人
                    String configrecorder = viewHolder.tvProRecorder.getText().toString();
                    spUtils.put(context, "configrecorder", configrecorder);

                    viewHolder.tvProRecordat.setText(year + "/" + month + "/" + datetime);
                    String configrecordat = viewHolder.tvProRecordat.getText().toString();
                    spUtils.put(context, "configrecordat", configrecordat);//制单时间
                } else {//
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
                    viewHolder.tvProState.setText(tvnewlyTotalCompletion);
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
            } else {//工序不是裁床的情况，将1日到31日的输入框显示出来
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
                    viewHolder.tvProState.setText(tvnewlyTotalCompletion);
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
                                    if (title.equals("裁床")) {
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
                                    } else {
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
                                        viewHolder.tvProOneDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwoDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProThreeDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProForeDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProFiveDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProSixDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProSevenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProEightDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProNineDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProElevenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwelveDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProThirteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProFourteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProFifteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProSixteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProSeventeenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProEighteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProNineteenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyOneDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyTwoDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyThreeDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyForeDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyFiveDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentySixDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentySevenDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyEightDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProTwentyNineDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProThirtyDay.setVisibility(View.VISIBLE);
                                        viewHolder.tvProThirtyOneDay.setVisibility(View.VISIBLE);
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
                                    }
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

                    final EditText editTexOthers = viewHolder.tvProOthers;//监听组别人数
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

                    viewHolder.tvProTotalCompletion.setText(tvnewlyCompletedLastMonth);
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

                    String[] spinnermonththree = context.getResources().getStringArray(R.array.timemonth);
                    ArrayAdapter<String> adapterthree =
                            new ArrayAdapter<String>(context,
                                    R.layout.adapter_pronewlycomfig_spinner,
                                    spinnermonththree);
                    adapterthree.setDropDownViewResource(R.layout.adapter_pronewlycomfig_spinner_item);
                    viewHolder.tvProMonth.setAdapter(adapterthree);
                    Calendar now3 = Calendar.getInstance();
                    int month3 = now3.get(Calendar.MONTH);//获取当前月
                    viewHolder.tvProMonth.setSelection(month3, true);//设置默认月份
                    viewHolder.tvProMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String[] languages = context.getResources().getStringArray(R.array.timemonth);
                            System.out.print(languages[position]);
                            spUtils.put(context, "ComfigMonth", languages[position]);
//                            booleandatelist.get(position).setMemomonth(languages[position]);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

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
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
                    if (editTexTaskNumber.getTag() instanceof TextWatcher) {
                        editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
                    }
                    viewHolder.tvProTaskNumber.setText(tvdate);

                    final EditText editTexLastMonth = viewHolder.tvProCompletedLastMonth;
                    /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
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

    private void saveDestroy() {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("mylistStr");//保存集合
        editor.remove("copyProcedure");//工序
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
        editor.remove("copyitem");
        editor.remove("copyDocumentary");
        editor.remove("copyFactory");
        editor.remove("copyDepartment");
        editor.remove("copyOthers");
        editor.remove("copySingularSystem");
        editor.remove("copyTaskNumber");
        editor.remove("copySize");
        editor.remove("copyyColor");
        editor.remove("copyClippingNumber");
        editor.remove("copyTotalCompletion");
        editor.remove("copyState");
        editor.remove("proadapterid");
        editor.remove("prosalesid");
        editor.remove("copyProYear");
        editor.remove("copyMonth");
        editor.remove("copyProcedure");
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        //关闭界面时清除缓存中可输入的数据
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("mylistStr");//保存集合
        editor.remove("copyProcedure");//工序
        editor.remove("Configdepartment");//部门
        editor.remove("ComfigMonth");//月份
        editor.remove("ConfigProcedure");//工序
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
        editor.remove("copyitem");//款号
        editor.remove("copyDocumentary");//跟单
        editor.remove("copyFactory");//工厂
        editor.remove("copyDepartment");//部门
        editor.remove("copyOthers");//组别人数
        editor.remove("copySingularSystem");//制单数
        editor.remove("copyTaskNumber");//任务数
        editor.remove("copySize");//尺码
        editor.remove("copyyColor");//花色
        editor.remove("copyClippingNumber");//实裁数
        editor.remove("copyTotalCompletion");
        editor.remove("copyState");//状态
        editor.remove("proadapterid");//id
        editor.remove("prosalesid");//行ID
        editor.remove("copyProYear");//年
        editor.remove("copyMonth");//月
        editor.remove("copyProcedure");//制单人
        editor.commit();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("保存提示");
            builder.setMessage("退出是否保存");
            builder.setPositiveButton("保存后退出"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setdeilyData();
                            dialog.dismiss();
                        }
                    });
            builder.setNegativeButton("不保存，直接退出",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
            noticeDialog = builder.create();
            noticeDialog.setCanceledOnTouchOutside(false);
            noticeDialog.show();
        }
        return false;
    }
}