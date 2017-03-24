package com.daoran.newfactory.onefactory.activity.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;

/**
 * Created by lizhipeng on 2017/3/24.
 */

public class SqlCarApplyActivity extends BaseFrangmentActivity implements View.OnClickListener {

    private Toolbar tbarSqlCarapply;
    private ImageButton ibSqlCarDialog;
    private Button btnSqlopen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_carapply);
        getViews();
        initViews();
        setListener();
    }

    private void getViews() {
        tbarSqlCarapply = (Toolbar) findViewById(R.id.tbarSqlCarapply);
        btnSqlopen = (Button) findViewById(R.id.btnSqlopen);
    }

    private void initViews() {
        tbarSqlCarapply.setNavigationIcon(R.mipmap.button_cross);
        tbarSqlCarapply.setTitle("");
        btnSqlopen.setOnClickListener(this);
    }

    private void setListener() {
        tbarSqlCarapply.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSqlopen:
                startActivity(new Intent(SqlCarApplyActivity.this, CarapplyActivity.class));
                finish();
                break;
        }
    }
}
