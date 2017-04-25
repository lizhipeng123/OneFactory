package com.daoran.newfactory.onefactory.activity.work;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.ToastUtils;
import com.daoran.newfactory.onefactory.view.listview.NoscrollListView;
import com.daoran.newfactory.onefactory.view.listview.SyncHorizontalScrollView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 查货跟踪单
 * Created by lizhipeng on 2017/3/29.
 */

public class CommoditySqlActivity extends BaseFrangmentActivity
        implements View.OnClickListener {
    private NoscrollListView mData;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ImageView ivProductionBack;
    private List<String> mListData;
    private boolean prdmasterisnull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);

        initView();
        getViews();
    }


    private void initView() {
        ivProductionBack = (ImageView) findViewById(R.id.ivCommoditySql);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);

        mDataHorizontal.setSrollView(mHeaderHorizontal);
        mHeaderHorizontal.setSrollView(mDataHorizontal);

        new Thread(networkTask).start();
    }

    private void getViews() {
        ivProductionBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivCommoditySql:
                finish();
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String val = bundle.getString("value");
            Log.i("mylog", "请求结果" + val);
        }
    };

    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            final String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";

            try {
//                OkHttpClient client = new OkHttpClient();
//                OkHttp
//                        .post()
//                        .url(str)
//                        .build()
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//                                e.printStackTrace();
//                            }
//
//                            @Override
//                            public void onResponse(String response, int id) {
//                                Message msg = new Message();
//                                Bundle bundle = new Bundle();
//                                bundle.putString("value", response.toString());
//                                msg.setData(bundle);
//                                handler.sendMessage(msg);
//                                System.out.print(response);
//                                Log.e("responsedebug", response);
//                            }
//                        });
                HttpPost httpPost = new HttpPost(str);
                JSONObject jsonObject = new JSONObject();
                StringEntity entity = null;
                entity = new StringEntity(jsonObject.toString(), HTTP.UTF_8);
                entity.setContentType("application/json");
                HttpClient client = new DefaultHttpClient();
                httpPost.setEntity(entity);
                HttpResponse response = client.execute(httpPost);
                System.out.print(response);
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("value", response.toString());
                msg.setData(bundle);
                handler.sendMessage(msg);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .addHeader("Accept", "*/*")
                                .addHeader("Cookie", "add cookies here")
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();

        return httpClient;
    }

    private void setData() {
        final String str = HttpUrl.debugoneUrl + "QACwork/BindSearchQACworkAPP/";
        if (NetWork.isNetWorkAvailable(this)) {
            try {
                HttpPost httpPost = new HttpPost(str);
                JSONObject jsonObject = new JSONObject();
                StringEntity entity = new StringEntity(jsonObject.toString(), HTTP.UTF_8);
                entity.setContentType("application/json");
                HttpClient client = new DefaultHttpClient();
                httpPost.setEntity(entity);
                HttpResponse response = client.execute(httpPost);
                System.out.print(response);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ToastUtils.ShowToastMessage("当前网络不可用,请稍后再试", CommoditySqlActivity.this);
        }
    }

    class DataAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(CommoditySqlActivity.this).inflate(R.layout.item_commodity_data, null);
                holder.tvData = (TextView) convertView.findViewById(R.id.tv_data1);
                holder.linContent = (LinearLayout) convertView.findViewById(R.id.lin_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        class ViewHolder {
            TextView tvData;
            LinearLayout linContent;
        }
    }
}
