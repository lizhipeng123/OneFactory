package com.daoran.newfactory.onefactory.activity.work;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.Manager.SoftKeyboardManager;
import com.daoran.newfactory.onefactory.view.MyEditText;

import java.util.ArrayList;
import java.util.List;

public class SearchViaPointActivity extends AppCompatActivity implements Inputtips.InputtipsListener{

    //搜索
    private MyEditText txt_search_rally_point;
    //删除按钮
    private LinearLayout layout_delete;
    private Context context;

    private ListView listView;
    //位置的适配器
    private ChooseLocationAdapter chooseLocationAdapter;

    List<Tip> tipList;

    private int currentShowPoint;
    private Intent intent;

    public static final int SEARCH_VIA_POINT_FOR_RESULT = 165;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_via_point);

//        //状态栏颜色的设置
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout_main);
//        StatusBarManager.SetStatusBar(getWindow(), this, getResources(), "#323535", linearLayout);

        context = getBaseContext();
        initView();
        initData();
        initListener();
    }


    private void initView() {

        //搜索
        txt_search_rally_point = (MyEditText) findViewById(R.id.txt_select_city_search);
        //删除
        layout_delete = (LinearLayout) findViewById(R.id.layout_delete);

        listView = (ListView) findViewById(R.id.listview);
        chooseLocationAdapter = new ChooseLocationAdapter(this);
    }

    private void initData() {
        intent=getIntent();
        currentShowPoint=intent.getIntExtra("currentShowPoint",1);

    }

    private void initListener() {
        //返回
        findViewById(R.id.txt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoftKeyboardManager.HideSoftKeyboard(v);
                finish();
            }
        });

        //搜索
        txt_search_rally_point.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newText = s.toString().trim();
                if (newText != null) {
                    InputtipsQuery inputquery = new InputtipsQuery(newText, txt_search_rally_point.getText().toString());
                    Inputtips inputTips = new Inputtips(SearchViaPointActivity.this, inputquery);
                    inputTips.setInputtipsListener(SearchViaPointActivity.this);
                    inputTips.requestInputtipsAsyn();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //点击选择集结点返回选择集结点
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Tip tip=tipList.get(position);
                Intent intent=new Intent();
                intent.putExtra("tip",tip);
                intent.putExtra("currentShowPoint",currentShowPoint);
                setResult(SEARCH_VIA_POINT_FOR_RESULT,intent);

                SoftKeyboardManager.HideSoftKeyboard(view);
                finish();

            }
        });

    }

    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        if (rCode == 1000) {// 正确返回
            this.tipList=tipList;
            listView.setAdapter(chooseLocationAdapter);
            chooseLocationAdapter.setData(tipList);
            chooseLocationAdapter.notifyDataSetChanged();
//            List<String> listString = new ArrayList<String>();
//            for (int i = 0; i < tipList.size(); i++) {
//                listString.add(tipList.get(i).getName());
//            }
//            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(
//                    getApplicationContext(),
//                    R.layout.route_inputs, listString);
//            searchText.setAdapter(aAdapter);
//            aAdapter.notifyDataSetChanged();
        } else {
//            ToastUtil.showerror(this, rCode);
        }
    }

    public class ChooseLocationAdapter extends BaseAdapter {

        private Context mcontext;
        ArrayList<Tip> poiItemList = new ArrayList<Tip>();

        public ChooseLocationAdapter(Context context) {
            mcontext = context;
        }

        //数据操作方法
        public void setData(List<Tip> data) {

            if (data != null) {
                poiItemList = (ArrayList<Tip>) data;
            }
        }

        @Override
        public int getCount() {
            return poiItemList.size();
        }

        @Override
        public Tip getItem(int position) {
            return poiItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ChooseLocationViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.inflate_choose_location, parent, false);
                holder = new ChooseLocationViewHolder();
                holder.layout_choose_location = (LinearLayout) convertView.findViewById(R.id.layout_choose_location);
                holder.txt_location_detail = (TextView) convertView.findViewById(R.id.txt_location_detail);
                holder.txt_location = (TextView) convertView.findViewById(R.id.tv_location);
                convertView.setTag(holder);
            } else {
                holder = (ChooseLocationViewHolder) convertView.getTag();
            }
            //设置数据
            Tip tip = poiItemList.get(position);

            //店名
            holder.txt_location_detail.setText(tip.getName());
            //具体位置                        北京市                                           朝阳区                         街道
            holder.txt_location.setText(tip.getDistrict());
//            holder.txt_location.setText(tip.getName()
//                    + "" + tip.getAdcode()
//                    + "" + tip.getDistrict()
//                    + "" + tip.getPoiID()
//                    + "" + tip.getPoint()
//            );


            return convertView;
        }


        public class ChooseLocationViewHolder {

            private LinearLayout layout_choose_location;
            //具体位置
            private TextView txt_location_detail;
            //附近
            private TextView txt_location;

        }

    }

}
