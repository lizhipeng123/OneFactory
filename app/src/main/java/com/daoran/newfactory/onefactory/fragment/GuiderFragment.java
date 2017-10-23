package com.daoran.newfactory.onefactory.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoran.newfactory.onefactory.R;

/**
 * 引导页最后一页
 */
public class GuiderFragment extends Fragment {
    //回调接口
    public interface OnLoginRegisterListener {
        void login();
    }

    private OnLoginRegisterListener mOnLoginRegisterListener;
    private int resId; //图片的resId
    private boolean isLast; //是否是最后一页
    private ImageView ivGuider;
    private LinearLayout llytLoginRegister;
    private TextView tvLoginbtn;

    public static Fragment newInstance(int resId, boolean isLast) {
        Fragment fragment = new GuiderFragment();
        Bundle data = new Bundle();
        data.putInt("resId", resId);
        data.putBoolean("isLast", isLast);
        fragment.setArguments(data);//fragment中保存bundle的值
        return fragment;
    }

    public GuiderFragment() {
        super();
    }

    /**
     * 回调接口放在这里（fragment与activity相关联时调用,第一个执行）
     * Android6.0以上已改成onAttach(Context context)之前是onAttach(Activity activity)
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof OnLoginRegisterListener)) {
            throw new ClassCastException("must be instanceof OnLoginRegisterListener");
        }
        mOnLoginRegisterListener = (OnLoginRegisterListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getArguments();
        if (data != null) {
            resId = data.getInt("resId");
            isLast = data.getBoolean("isLast");
        }
    }

    /**
     * 此为Android 创建fragment对应的视图
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guider, container, false);
        ivGuider = (ImageView) view.findViewById(R.id.ivGuider);
        llytLoginRegister = (LinearLayout) view.findViewById(R.id.llytLoginRegister);
        tvLoginbtn = (TextView) view.findViewById(R.id.tvLoginbtn);
        ivGuider.setImageResource(resId);
        if (isLast) {
            llytLoginRegister.setVisibility(View.VISIBLE);
        } else {
            llytLoginRegister.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnLoginRegisterListener.login();
            }
        });
    }
}
