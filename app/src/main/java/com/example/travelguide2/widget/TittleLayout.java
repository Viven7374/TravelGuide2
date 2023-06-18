package com.example.travelguide2.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.travelguide2.InfoActivity;
import com.example.travelguide2.R;
import com.example.travelguide2.utils.ActivityCollector;

public class TittleLayout extends LinearLayout {
    private ImageView iv_backward;
    private TextView tv_title, tv_forward;

    public TittleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(ActivityCollector.getCurrentActivity().getClass().equals(InfoActivity.class)){
            tv_forward.setText("保存");
            tv_title.setText("编辑资料");
        }

        //设置监听器
        //如果点击back则结束活动
        iv_backward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
    public TextView getTextView_forward(){
        return tv_forward;
    }

}
