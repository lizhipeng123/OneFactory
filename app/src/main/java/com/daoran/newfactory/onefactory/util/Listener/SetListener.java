package com.daoran.newfactory.onefactory.util.Listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Adapter;

import com.daoran.newfactory.onefactory.activity.work.ftydl.FTYDLSearchActivity;
import com.daoran.newfactory.onefactory.activity.work.ftydl.FTYDLSearchCopyDetailActivity;
import com.daoran.newfactory.onefactory.activity.work.ftydl.FTYDLSearchDetailActivity;
import com.daoran.newfactory.onefactory.activity.work.qacwork.QACworkDetailActivity;
import com.daoran.newfactory.onefactory.activity.work.qacwork.QACworkSearchActivity;
import com.daoran.newfactory.onefactory.adapter.ftydladapter.FTYDLSearchAdapter;
import com.daoran.newfactory.onefactory.adapter.ftydladapter.FTYDLSearchLeftAdapter;
import com.daoran.newfactory.onefactory.adapter.qacworkadapter.QACworkSearchAdapter;
import com.daoran.newfactory.onefactory.bean.ftydlbean.FTYDLDailyBean;
import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkPageDataBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2017/12/20
 * 编写人：lizhipeng
 * 功能描述：点击跳转事件回调接口
 */

public class SetListener {
    private SPUtils spUtils;
    private Context context;
    private int isprodure;
    List<FTYDLDailyBean.DataBean> detailBeenList =
            new ArrayList<FTYDLDailyBean.DataBean>();//生产日报数据
    List<QACworkPageDataBean.DataBean> qaCworkDataBean =
            new ArrayList<QACworkPageDataBean.DataBean>();//查货跟踪数据

    FTYDLSearchAdapter adapter;//生产日报总表适配器
    FTYDLSearchLeftAdapter leftAdapter;//生产日报左侧固定列表适配
    QACworkSearchAdapter qaCworkSearchAdapter;//查货跟踪总表适配

    /*生产日报点击进入修改页面回调方法*/
    public void setFTYDLSearchLister(final Context context, FTYDLSearchAdapter adapter
            , final List<FTYDLDailyBean.DataBean> detailBeenList) {
        this.context = context;
        this.adapter = adapter;
        this.detailBeenList = detailBeenList;

        adapter.setmOnClickFTYDLSearchLinter(
                new FTYDLSearchAdapter.OnClickFTYDLSearchLinter() {
                    @Override
                    public void MyFTYDLSearchLinter(int id) {
                        spUtils.put(context, "tvFTYDLDetailId", String.valueOf(id));//位置索引

                        String tvProProcedure = detailBeenList.get(id).getWorkingProcedure();
                        if (tvProProcedure == null) {
                            tvProProcedure = "";
                        }
                        spUtils.put(context,
                                "tvFTYDLDetailProcedure", tvProProcedure);//工序
                        if (tvProProcedure.equals("裁床")) {
                            isprodure = 1;
                            spUtils.put(context,
                                    "FTYDLDetailISProdure", String.valueOf(isprodure));
                        } else if (tvProProcedure.equals("选择工序")) {
                            ToastUtils.ShowToastMessage("选择工序后再新建",
                                    context);
                            return;
                        } else {
                            isprodure = 0;
                            spUtils.put(context,
                                    "FTYDLDetailISProdure", String.valueOf(isprodure));
                        }
                        Intent intent = new Intent(context,
                                FTYDLSearchDetailActivity.class);
                        Bundle bundle = new Bundle();//序列化数据
                        bundle.putParcelableArrayList("detailBeenList",
                                (ArrayList<? extends Parcelable>) detailBeenList);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });
    }

    /*生产日报点击进入复制页面回调方法*/
    public void setFTYDLCopyLister(final Context context, FTYDLSearchLeftAdapter leftAdapter,
                                   final List<FTYDLDailyBean.DataBean> detailBeenList) {
        this.context = context;
        this.leftAdapter = leftAdapter;
        this.detailBeenList = detailBeenList;
        leftAdapter.setmOnClickFTYDLCopyLinter(new FTYDLSearchLeftAdapter.OnClickFTYDLCopyLinter() {
            @Override
            public void MyFTYDLCopyLinter(int id) {
                spUtils.put(context, "tvFTYDLCopyId", String.valueOf(id));//位置索引

                String tvProProcedure = detailBeenList.get(id).getWorkingProcedure();
                if (tvProProcedure == null) {
                    tvProProcedure = "";
                }
                spUtils.put(context,
                        "tvFTYDLLeftProcedure", tvProProcedure);//工序
                if (tvProProcedure.equals("裁床")) {
                    isprodure = 1;
                    spUtils.put(context,
                            "FTYDLLeftIsProdure", String.valueOf(isprodure));
                } else if (tvProProcedure.equals("选择工序")) {
                    ToastUtils.ShowToastMessage("选择工序后再新建",
                            context);
                    return;
                } else {
                    isprodure = 0;
                    spUtils.put(context,
                            "FTYDLLeftIsProdure", String.valueOf(isprodure));
                }
                Intent intent = new Intent(context,
                        FTYDLSearchCopyDetailActivity.class);
                Bundle bundle = new Bundle();//序列化数据
                bundle.putParcelableArrayList("detailCopyBeenList",
                        (ArrayList<? extends Parcelable>) detailBeenList);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    /*查货跟踪点击进入修改页面回调方法*/
    public void setQACworkDetailLister(final Context context, QACworkSearchAdapter qaCworkSearchAdapter,
                                       final List<QACworkPageDataBean.DataBean> qaCworkDataBean){
        this.context = context;
        this.qaCworkSearchAdapter = qaCworkSearchAdapter;
        this.qaCworkDataBean = qaCworkDataBean;
        qaCworkSearchAdapter.setmOnClickQACworkLinter(new QACworkSearchAdapter.OnClickQACworkLinter() {
            @Override
            public void myQACworkClick(int id) {
                spUtils.put(context, "tvQACWorkDetailId", String.valueOf(id));//位置索引
                Intent intent = new Intent(context,
                        QACworkDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("qaCworkDataBean",
                        (ArrayList<? extends Parcelable>) qaCworkDataBean);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }
}