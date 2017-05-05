package com.daoran.newfactory.onefactory.activity.work.production;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.activity.work.SignActivity;
import com.daoran.newfactory.onefactory.adapter.ProductionNewlyBuildAdapter;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;

/**
 * Created by lizhipeng on 2017/5/2.
 */

public class ProductionNewlyBuildActivity
        extends BaseFrangmentActivity
        implements View.OnClickListener {

    private ImageView ivProductionBack;//返回
    private Button btnNewbuildConfirm;//确定
    private TextView spinnerNewbuild;//选择工序
    private EditText etNewbuild, etNewbuildDetail;
    private Button etNewbuildSql, btnNewbuildPage;
    private TextView tvNewbuildPage;

    private NoscrollListView lv_newbuild_data;
    private ProductionNewlyBuildAdapter buildAdapter;
    private SharedPreferences sp;
    private SPUtils spUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_newbuid);
        getViews();
        initViews();
        setListener();
    }

    /**
     * 实例化控件
     */
    private void getViews() {
        ivProductionBack = (ImageView) findViewById(R.id.ivProductionBack);
        btnNewbuildConfirm = (Button) findViewById(R.id.btnNewbuildConfirm);
        spinnerNewbuild = (TextView) findViewById(R.id.spinnerNewbuild);
        etNewbuild = (EditText) findViewById(R.id.etNewbuild);
        etNewbuildDetail = (EditText) findViewById(R.id.etNewbuildDetail);
        etNewbuildSql = (Button) findViewById(R.id.etNewbuildSql);
        btnNewbuildPage = (Button) findViewById(R.id.btnNewbuildPage);
        tvNewbuildPage = (TextView) findViewById(R.id.tvNewbuildPage);
        lv_newbuild_data = (NoscrollListView) findViewById(R.id.lv_newbuild_data);
    }

    /**
     * 初始化控件
     */
    private void initViews() {

    }



    /**
     * 控件属性
     */
    private void setListener() {
        ivProductionBack.setOnClickListener(this);
        spinnerNewbuild.setOnClickListener(this);
    }

    private void getDate() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivProductionBack:
                finish();
                break;
            case R.id.spinnerNewbuild:
                PopupMenu popupMenu = new PopupMenu(ProductionNewlyBuildActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pro_spinnernew, popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        spUtils.put(ProductionNewlyBuildActivity.this, "spinnerNewbuild", title);
                        spinnerNewbuild.setText(title);
                        ToastUtils.ShowToastMessage("点击的是：" + title, ProductionNewlyBuildActivity.this);
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
        }
    }
}
