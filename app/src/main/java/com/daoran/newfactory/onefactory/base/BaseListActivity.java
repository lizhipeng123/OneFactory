package com.daoran.newfactory.onefactory.base;

import android.text.format.DateUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.adapter.reqcaradapter.ReqCarSearchAdapter;
import com.i5tong.epubreaderlib.view.pulltorefresh.ILoadingLayout;
import com.i5tong.epubreaderlib.view.pulltorefresh.PullToRefreshBase;
import com.i5tong.epubreaderlib.view.pulltorefresh.PullToRefreshListView;

import java.util.List;

/**
 * Pull To Refresh Listview
 * Created by lizhipeng on 17/3/19.
 * 支持PullToRefreshListView 和 普通的Listview
 * 使用方法
 * 1.继承BaseListActivity
 * 2.实现抽象方法。
 * 3.调用 init(int layoutId, int listViewId)；
 * 4.调用getData();
 */
public abstract class BaseListActivity extends BaseFrangmentActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2, PullToRefreshBase.OnPullEventListener, AdapterView.OnItemLongClickListener {
    public PullToRefreshListView listView = null;//可刷新的listview控件
    public BaseAdapter adapter;//适配listview数据列表
    private List datas;//数据集合
    public boolean isRefreshing = false;//是否刷新
    public int defaultPageIndex = 0;//默认初始页
    public int pageIndex = defaultPageIndex;//将默认初始页传给页数
    //    private View emptyView;
    private TextView emptyView;

    //实例化控件
    public void init(int listViewId) {
        listView = (PullToRefreshListView) findViewById(listViewId);
        adapter = setListAdapter();
        listView.setOnItemClickListener(this);
        listView.setOnRefreshListener(this);
        listView.setOnPullEventListener(this);
        //如果adapter不为空，则加载数据
        if (adapter != null) {
            listView.setAdapter(adapter);
        }
    }

    //上拉加载事件
    public void setPullToRefreshListViewModeBOTH() {
        //如果列表不为空，则添加上拉加载事件
        if (listView != null) {
            listView.setMode(PullToRefreshBase.Mode.BOTH);
            ILoadingLayout endLabels = listView.getLoadingLayoutProxy(false, true);
            endLabels.setPullLabel("上拉加载更多");
            endLabels.setReleaseLabel("松开加载");
            endLabels.setRefreshingLabel("正在加载");
        }
    }

    public void setPullToRefreshListViewModePullup() {
        if (listView != null) {
            listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
            ILoadingLayout endLabels = listView.getLoadingLayoutProxy(false, true);
            endLabels.setPullLabel("上拉加载更多");
            endLabels.setReleaseLabel("松开加载");
            endLabels.setRefreshingLabel("正在加载");
        }
    }

    public void setNoListViewMode() {
        if (listView != null) {
            listView.setMode(PullToRefreshBase.Mode.DISABLED);
        }
    }

    public Adapter getAdapter() {
        return adapter;
    }

    /**
     * 获得listview对象 需要强制转换到指定listview类型
     *
     * @return
     */
    public View getListView() {
        return listView;
    }

    /**
     * 设置listview的适配器
     */
    public abstract BaseAdapter setListAdapter();


    /**
     * 设置默认第一页的页码
     *
     * @param defaultPageIndex
     */
    public void setDefaultStartPageIndex(int defaultPageIndex) {
        this.defaultPageIndex = defaultPageIndex;
        pageIndex = defaultPageIndex;
    }

    /**
     * 请求数据的方法。默认请求第一页
     */
    public void getData() {
        pageIndex = defaultPageIndex;//默认请求第一页
        if (isRefreshing)
            return;
        isRefreshing = true;//刷新设置为可刷新
        getData(pageIndex);//执行查询数据
    }

    /**
     * 复写请求数据的方法。
     */
    public abstract void getData(int pageIndex);

    /**
     * listview 点击回调
     *
     * @param o
     */
    public abstract void onListItemClick(Object o);

    public abstract void onListItemLongClick(Object o);

    public List getListData() {
        return datas;
    }

    /**
     * 设置listView的数据
     */
    public void setListData(List datas) {
        this.datas = datas;
        listView.onRefreshComplete();//停止刷新操作
        isRefreshing = false;//刷新手势之后，结束刷新事件
        //填充数据
        //无数据
        if (datas == null || datas.size() == 0) {
            if (pageIndex == defaultPageIndex) {
                //如果没有数据时，则清空列表
                if (adapter instanceof ReqCarSearchAdapter) {
                    ((ReqCarSearchAdapter) adapter).clear();
                }
                setEmptyView();
            } else {
                Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (adapter == null)
                return;
            hideEmptyView();
            if (pageIndex == defaultPageIndex) {
                if (adapter instanceof ReqCarSearchAdapter) {
                    ((ReqCarSearchAdapter) adapter).replaceAll(datas);//替换数据
                }
            } else {
                if (adapter instanceof ReqCarSearchAdapter) {
                    ((ReqCarSearchAdapter) adapter).addAll(datas);//新增数据
                }
            }
            pageIndex++;//页数累加
        }
    }

    /**
     * 设置listView的数据，获取回复数据不显示无更多数据
     */
    public void setListDataRelpy(List datas) {
        this.datas = datas;
        listView.onRefreshComplete();
        isRefreshing = false;
        if (datas == null || datas.size() == 0) {
            if (pageIndex == defaultPageIndex) {
            } else {
                Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (adapter == null)
                return;
            hideEmptyView();
            if (pageIndex == defaultPageIndex) {
            } else {
            }
            pageIndex++;
        }
    }

    /**
     * 没有数据时显示
     */
    public void setEmptyView() {
        if (emptyView == null) {
            emptyView = new TextView(this);
            emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            emptyView.setText("没有更多内容了");
            emptyView.setTextColor(getResources().getColor(R.color.reg1color1));
            emptyView.setGravity(Gravity.CENTER);
            emptyView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            emptyView.setVisibility(View.GONE);
            ((ViewGroup) listView.getParent()).addView(emptyView);
            listView.setEmptyView(emptyView);
        }
        emptyView.setVisibility(View.VISIBLE);
    }

    //显示页面
    public void hideEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
    }

    /**
     * 点击的回调
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() != null)
            onListItemClick(parent.getAdapter().getItem(position));
    }

    //点击listview中item的事件
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
        if (parent.getAdapter() != null)
            onListItemLongClick(parent.getAdapter().getItem(position));
        return true;
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        String label = DateUtils.formatDateTime(this,
                System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_ABBREV_ALL);
        if (isRefreshing) {
            return;
        }
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel(label);
        getData(pageIndex);

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //格式化时间
        String label = DateUtils.formatDateTime(this,
                System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_ABBREV_ALL);
        if (isRefreshing) {
            return;
        }
        //使用proxy类对象，为layout对象中的textview赋值
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel(label);//设置更新时间
        getData();
    }

    @Override
    public void onPullEvent(PullToRefreshBase refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {
        Log.d("aaa1", state.toString());
        Log.d("aaa2", direction.toString());
        if (state.equals(PullToRefreshBase.State.PULL_TO_REFRESH)) {
            if (direction.equals(PullToRefreshBase.Mode.PULL_FROM_START)) {
                Log.d("aaa3", "1111111");
                refreshView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
                refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
            } else {
                Log.d("aaa3", "2222222");
                refreshView.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在载入");
            }
        }
    }
}