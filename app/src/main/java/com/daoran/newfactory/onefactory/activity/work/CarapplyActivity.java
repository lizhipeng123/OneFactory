package com.daoran.newfactory.onefactory.activity.work;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;

/**
 * Created by lizhipeng on 2017/3/24.
 */

public class CarapplyActivity extends BaseFrangmentActivity {

    private Toolbar tbarCarapply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carapply);
        getViews();
        initViews();
        setListener();
    }

    private void getViews() {
        tbarCarapply = (Toolbar) findViewById(R.id.tbarCarapply);

    }

    private void initViews(){
        tbarCarapply.setNavigationIcon(R.mipmap.button_cross);
        tbarCarapply.setTitle("");
    }

    private void setListener(){
        tbarCarapply.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
