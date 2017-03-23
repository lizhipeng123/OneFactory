package com.daoran.newfactory.onefactory.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daoran.newfactory.onefactory.Listener.OnLoginRegisterListener;
import com.daoran.newfactory.onefactory.R;


/**
 * Created by lizhipeng on 2017/3/21.
 */

public class GuideFragment extends Fragment implements OnLoginRegisterListener {

    private OnLoginRegisterListener mOnLoginRegisterListener;
    private int resId;//图片id
    private boolean isLast;//是否是最后一页

    private RelativeLayout rlGuideThree;
    private ImageView ivGuideThree;
    private Button btnOpen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ivGuideThree = (ImageView) view.findViewById(R.id.ivGuideThree);
        btnOpen = (Button) view.findViewById(R.id.btnOpen);
        rlGuideThree = (RelativeLayout) view.findViewById(R.id.rlGuideThree);
        ivGuideThree.setImageResource(resId);
        if (isLast) {
            btnOpen.setVisibility(View.VISIBLE);
        } else {
            btnOpen.setVisibility(View.GONE);
        }
        return view;
    }

    public static Fragment newInstance(int resId, boolean isLast) {
        Fragment fragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("resId", resId);
        bundle.putBoolean("isLast", isLast);
        fragment.setArguments(bundle);
        return fragment;
    }

    public GuideFragment() {
        super();
    }

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
        Bundle bundle = getArguments();
        if (bundle != null) {
            resId = bundle.getInt("resId");
            isLast = bundle.getBoolean("isLast");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnLoginRegisterListener.login();
            }
        });
    }

    @Override
    public void login() {

    }

    @Override
    public void register() {

    }
}
