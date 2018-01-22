package com.daoran.newfactory.onefactory.util.Listener;

import android.content.Context;
import android.view.Gravity;
import android.view.animation.TranslateAnimation;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;
import com.daoran.newfactory.onefactory.util.utils.DisplayUtils;

/**
 * 创建时间：2018/1/22
 * 编写人：lizhipeng
 * 功能描述：
 */

public class ToggleLintener implements OnCheckedChangeListener {
    private Context context;
    private String settingName;
    private ToggleButton toggle;
    private ImageButton toggle_Button;
    private SPUtils spUtils;

    public ToggleLintener(Context context, String settingName,
                          ToggleButton toggle, ImageButton toggle_Button) {
        this.context = context;
        this.settingName = settingName;
        this.toggle = toggle;
        this.toggle_Button = toggle_Button;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if ("开启".equals(settingName)) {
            spUtils.put(context, "truePushflag", isChecked);
        }

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                toggle_Button.getLayoutParams();
        if (isChecked) {
            //调整位置
            params.addRule(RelativeLayout.ALIGN_RIGHT, -1);
            if ("开启".equals(settingName)) {
                params.addRule(RelativeLayout.ALIGN_LEFT, R.id.toggle_AutoPlay);
            }
            toggle_Button.setLayoutParams(params);
            toggle_Button.setImageResource(R.drawable.push_thumb);
            toggle.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
            //播放动画
            TranslateAnimation animation = new TranslateAnimation(
                    DisplayUtils.dip2px(context, 40), 0, 0, 0);
            animation.setDuration(200);
            toggle_Button.startAnimation(animation);
        } else {
            //调整位置
            if ("开启".equals(settingName)) {
                params.addRule(RelativeLayout.ALIGN_RIGHT, R.id.toggle_AutoPlay);
            }
            params.addRule(RelativeLayout.ALIGN_LEFT, -1);
            toggle_Button.setLayoutParams(params);
            toggle_Button.setImageResource(R.drawable.push_thumb_off);
            toggle.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            //播放动画
            TranslateAnimation animation = new TranslateAnimation(
                    DisplayUtils.dip2px(context, -40), 0, 0, 0);
            animation.setDuration(200);
            toggle_Button.startAnimation(animation);
        }
    }
}
