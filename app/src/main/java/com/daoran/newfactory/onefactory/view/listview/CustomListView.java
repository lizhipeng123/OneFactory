package com.daoran.newfactory.onefactory.view.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * 解决ListView+EditText 焦点的问题
 * Created by lizhipeng on 2017/5/16.
 */

public class CustomListView extends ScrollView {
    private BaseAdapter mAdapter;//用于接收传入的Adapter
    /**
     * 由于ScrollView只能有一个子View，所以自定义一个LinearLayout来装载多个ItemView
     */
    private LinearLayout mLayout;
    private Context mContext;
    /**
     * 为自定义的ListView设置BaseAdapter
     * @param adapter
     */
    public void setAdapter(BaseAdapter adapter){
        this.mAdapter = adapter;
        this.mLayout = new LinearLayout(mContext);
        //设置mLayout的Orientation属性为垂直
        this.mLayout.setOrientation(LinearLayout.VERTICAL);
        //把mLayout添加到ScrollView中
        this.addView(mLayout);
        if(null == mAdapter)
            return;
        fillView();
    }
    /**
     * 根据传入的BaseAdapter适配器来画出整个自定义的ListView的布局
     */
    public void fillView() {
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View v = mAdapter.getView(i, null, null);
            this.mLayout.addView(v, i);
        }
    }
    public CustomListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
    }
    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomListView(Context context) {
        super(context);
        mContext = context;
    }
}
