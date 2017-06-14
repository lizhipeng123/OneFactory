package com.daoran.newfactory.onefactory.activity.work.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.view.CropSquareTransformation;
import com.squareup.picasso.Picasso;

/**
 * Created by lizhipeng on 2017/6/13.
 */

public class AboutActivity extends BaseFrangmentActivity
        implements View.OnClickListener{

    private ImageView ivBack;
    private ImageView ivAbout;
    private TextView tvCoreAbout;
    private SPUtils spUtils;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        sp = getSharedPreferences("my_sp",0);

        getViews();
        initViews();
        setListener();
    }

    private void getViews(){
        ivBack = (ImageView) findViewById(R.id.ivBack);
        tvCoreAbout = (TextView) findViewById(R.id.tvCoreAbout);
        ivAbout = (ImageView) findViewById(R.id.ivAbout);
    }

    private void initViews(){
        String vercode = sp.getString("curVersionCode", "");
        tvCoreAbout.setText("V "+vercode);
        Picasso.with(AboutActivity.this)
                .load(R.mipmap.daoran)
                .error(R.mipmap.daoran)
                .transform(new CropSquareTransformation())
                .into(ivAbout);
    }

    private void setListener(){
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
