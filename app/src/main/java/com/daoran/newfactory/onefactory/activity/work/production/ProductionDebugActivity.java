package com.daoran.newfactory.onefactory.activity.work.production;

import android.os.Bundle;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.view.ScrollablePanel;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 测试所用的表格代码
 * Created by lizhipeng on 2017/5/18.
 */

public class ProductionDebugActivity extends BaseFrangmentActivity {
    public static final SimpleDateFormat DAY_UI_MONTH_DAY_FORMAT
            = new SimpleDateFormat("MM-dd");
    public static final SimpleDateFormat WEEK_FORMAT
            = new SimpleDateFormat("EEE", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_debugtwo);
        final ScrollablePanel scrollablePanel = (ScrollablePanel) findViewById(R.id.scrollable_panel);
    }

}
