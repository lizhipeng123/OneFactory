package com.i5tong.epubreaderlib.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.i5tong.epubreaderlib.adapter.CharacterAdapter;
import com.i5tong.epubreaderlib.bean.RowData;
import com.i5tong.epubreaderlib.presenter.EpubPresenter;
import com.i5tong.epubreaderlib.utils.AppConfig;

import java.util.ArrayList;
import java.util.List;

public class EpubReaderActivity extends ListActivity implements EpubReaderInterface {
    private EpubPresenter mEpubPresenter;
    private String title, url;
    private ArrayList<RowData> rowDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("bookTitle");
        url = getIntent().getStringExtra("epubUrl");
        AppConfig.EpubCacheFolder = getIntent().getStringExtra("cacheFolder");
        mEpubPresenter = new EpubPresenter(this);
        mEpubPresenter.init(url, title);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        RowData rowData = (RowData) l.getAdapter().getItem(position);
        Intent intent = new Intent(this, ContentViewActivity.class);
        intent.putExtra("data", rowDatas);
        intent.putExtra("title", title);
        intent.putExtra("index", position);
//        intent.putExtra("url", "file://" + AppConfig.EpubCacheFolder + title + "/" + rowData.getParentFolder() + rowData.getResource().getHref());
        startActivity(intent);
    }

    @Override
    public void onCharacterGet(List<RowData> datas) {
        rowDatas = (ArrayList<RowData>) datas;
        CharacterAdapter adapter = new CharacterAdapter(this, rowDatas);
        setListAdapter(adapter);
        getListView().setTextFilterEnabled(true);
    }
}
