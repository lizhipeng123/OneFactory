package com.daoran.newfactory.onefactory.activity.work.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.base.BaseFrangmentActivity;
import com.daoran.newfactory.onefactory.bean.PictureSetBean;
import com.daoran.newfactory.onefactory.util.Http.AsyncHttpResponseHandler;
import com.daoran.newfactory.onefactory.util.Http.HttpUrl;
import com.daoran.newfactory.onefactory.util.Http.NetUtil;
import com.daoran.newfactory.onefactory.util.Http.NetWork;
import com.daoran.newfactory.onefactory.util.Http.RequestParams;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;
import com.daoran.newfactory.onefactory.util.utils.Tools;
import com.daoran.newfactory.onefactory.util.utils.Util;
import com.lidroid.xutils.BitmapUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 放置图片位置发布上传
 * Created by lizhipeng on 2017/9/15.
 */

public class PictureSelectActivity extends BaseFrangmentActivity
        implements View.OnClickListener {

    private LinearLayout back_lyt;
    private String title;
    private EditText content_et;
    private ImageView photo_iv;
    private TextView title_right_tv;
    String pathpic;
    private InputMethodManager inputMethodManager;
    private SharedPreferences sp;
    private SPUtils spUtils;
    PictureSetBean setBean;
    private List<PictureSetBean.Data> datas = new ArrayList<PictureSetBean.Data>();
    private int year, month, date, hour, minute, second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vgtalk_publish);
        initViews();
        setListener();
        initData();
    }

    private void initViews() {
        back_lyt = (LinearLayout) findViewById(R.id.back_lyt);
        content_et = (EditText) findViewById(R.id.content_et);
        photo_iv = (ImageView) findViewById(R.id.photo_iv);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
    }

    private void initData() {
        inputMethodManager = (InputMethodManager) this.
                getSystemService(Context.INPUT_METHOD_SERVICE);
        content_et.addTextChangedListener(new EditChangedListener());
        Intent intent = getIntent();
        pathpic = intent.getStringExtra("Pathpic");
        if (pathpic != null) {
            BitmapUtils bitmapUtils = new BitmapUtils(this);
            bitmapUtils.display(photo_iv, pathpic);
        }
    }

    private void setListener() {
        back_lyt.setOnClickListener(this);
        title_right_tv.setOnClickListener(this);
    }

    /**
     * 保存
     */
    private void setDate() {
        sp = this.getSharedPreferences("my_sp", 0);
        getDate();
        String strurl = HttpUrl.debugoneUrl + "OutRegister/SavePhoto/";
        String recodername = sp.getString("name", "");
        String userna = sp.getString("username", "");
        String text = Tools.urlEncoder(content_et.getText() + "");
        String daterecor = year + "/" + month + "/" + date;
        if (NetWork.isNetWorkAvailable(this)) {
            if(setBean==null){

            }else{

            }
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(NetUtil.createParam("id", ""));
//            params.add(NetUtil.createParam("recorder", recodername));
//            params.add(NetUtil.createParam("recordat", daterecor));
//            params.add(NetUtil.createParam("memo", text));
//            params.add(NetUtil.createParam("pic", ""));
//            params.add(NetUtil.createParam("picpath", pathpic));
//            params.add(NetUtil.createParam("recorderID", userna));
//            RequestParams requestParams = new RequestParams(params);
//            NetUtil.getAsyncHttpClient().post(strurl, requestParams,
//                    new AsyncHttpResponseHandler() {
//                        @Override
//                        public void onSuccess(String content) {
//                            super.onSuccess(content);
//                            System.out.print(content);
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            super.onFinish();
//                        }
//
//                        @Override
//                        public void onFailure(Throwable error, String content) {
//                            super.onFailure(error, content);
//                        }
//                    });
//            for(int i=0;i<userna.length();i++){
//                datas.get(i).setId(0);
//            }
//            List<Map<String,String>> map = new ArrayList<>();
//
//            for(int i = 0;i<datas.size();i++){
//                Map<String,String> m = new HashMap<>();
//                m.put("id","");
//                m.put()
//
//            }
            Map<String,String> map = new HashMap<>();
            map.put("id", "");
            map.put("recorder", recodername);
            map.put("recordat", daterecor);
            map.put("memo", text);
            map.put("pic", "");
            map.put("picpath", pathpic);
            map.put("recorderID", userna);
//            NetUtil.getAsyncHttpClient().post(strurl,);
//            MultipartBody.Builder builder =
//                    new MultipartBody.Builder().setType(MultipartBody.FORM);
//            builder.addFormDataPart("id", "");
//            builder.addFormDataPart("recorder", recodername);
//            builder.addFormDataPart("recordat", daterecor);
//            builder.addFormDataPart("memo", text);
//            builder.addFormDataPart("pic", "");
//            builder.addFormDataPart("picpath", pathpic);
//            builder.addFormDataPart("recorderID", userna);
//            MultipartBody requestBody = builder.build();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strurl)
//                    .post()
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    System.out.print(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.print(response);
                }
            });
        } else {
            ToastUtils.ShowToastMessage(R.string.noHttp, PictureSelectActivity.this);
        }
    }

    private class EditChangedListener implements TextWatcher {
        private CharSequence temp;//监听前的文本
        private int editStart;//光标开始位置
        private int editEnd;//光标结束位置
        private final int charMaxNum = 1000;
        int length;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = content_et.getSelectionStart();
            editEnd = content_et.getSelectionEnd();
            length = content_et.length() + Util.getChineseNum(content_et.getText().toString());
            if (length > charMaxNum) {
                s.delete(editStart - 1, editEnd);
                ToastUtils.ShowToastMessage("内容限500字内", PictureSelectActivity.this);
            }
        }

    }

    void hideSoftKeyboard() {
        if (this.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (this.getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取系统时间
     */
    private void getDate() {
        Time t = new Time(); // or Time t=new Time("GMT+8");
        t.setToNow(); // 取得系统时间。
        year = t.year;
        month = t.month;
        date = t.monthDay;
        hour = t.hour; // 0-23
        minute = t.minute;
        second = t.second;
    }

    private boolean validate() {
        boolean result = true;
        String message = "";
        try {
            if (pathpic.equals("")) {
                message = "请选择图片";
                result = false;
                return result;
            }
        } catch (Exception e) {
            Log.e(getClass().getName(), "validate ==>" + e.getMessage());
        } finally {
            if (!result) {
                Toast.makeText(getApplicationContext(), message,
                        Toast.LENGTH_SHORT).show();
            }
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_lyt:
                finish();
                break;
            case R.id.title_right_tv:
                hideSoftKeyboard();
                if (validate()) {
                    setDate();
                }
                break;
        }
    }
}
