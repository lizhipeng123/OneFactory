package com.i5tong.epubreaderlib.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.i5tong.epubreaderlib.R;
import com.i5tong.epubreaderlib.bean.RowData;
import com.i5tong.epubreaderlib.utils.AppConfig;
import com.i5tong.epubreaderlib.view.pulltorefresh.PullToRefreshBase;
import com.i5tong.epubreaderlib.view.pulltorefresh.PullToRefreshWebView;

import java.util.ArrayList;

/**
 * Created by 王霖 on 2015/4/24 0024.
 */
public class ContentViewActivity extends Activity implements PullToRefreshBase.OnRefreshListener2<WebView> {

    private WebView webView;
    private ArrayList<RowData> datas;
    private int index;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.epub_book_content);

        datas = (ArrayList<RowData>) getIntent().getSerializableExtra("data");
        title = getIntent().getStringExtra("title");
        index = getIntent().getIntExtra("index", 0);

        PullToRefreshWebView pullRefreshWebView = (PullToRefreshWebView) findViewById(R.id.content);
        pullRefreshWebView.setOnRefreshListener(this);
        pullRefreshWebView.setMode(PullToRefreshBase.Mode.BOTH);

        webView = pullRefreshWebView.getRefreshableView();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new SampleWebViewClient());

        RowData data = datas.get(index);
        String parentFolder = data.getParentFolder();
        String href = data.getHref();
        String url = "file://" + AppConfig.EpubCacheFolder + title + "/" + parentFolder + href;
        webView.loadUrl(url);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<WebView> refreshView) {
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("查看上一章");
        if (index == 0) {
            Toast.makeText(this, "已经是第一页了", Toast.LENGTH_LONG).show();
            finish();
        } else {
            startActivity(new Intent(this, ContentViewActivity.class)
                    .putExtra("data", datas)
                    .putExtra("title", title)
                    .putExtra("index", --index));
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
            finish();
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<WebView> refreshView) {
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("查看下一章");
        if (index == datas.size() - 1) {
            Toast.makeText(this, "已经是最后一页了", Toast.LENGTH_LONG).show();
            finish();
        } else {
            startActivity(new Intent(this, ContentViewActivity.class)
                    .putExtra("data", datas)
                    .putExtra("title", title)
                    .putExtra("index", ++index));
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            finish();
        }
    }

    private static class SampleWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
