package com.example.travelguide2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.travelguide2.dao.UserDao;
import com.example.travelguide2.entity.User;
import com.example.travelguide2.utils.ActivityCollector;
import com.example.travelguide2.widget.ItemGroup;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.ToolbarUtils;

public class InfoActivity extends AppCompatActivity {

    private ItemGroup igId,igName,igDescription,igGender,igEmail,igBirthday,igCreateTime;
    private LinearLayout riPortrait;
    Toolbar toolBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_info);

        //适用于网络请求数据量很小
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //初始化
        igId = findViewById(R.id.ig_id);
        igName = findViewById(R.id.ig_name);
        igDescription = findViewById(R.id.ig_description);
        igGender = findViewById(R.id.ig_gender);
        igEmail = findViewById(R.id.ig_email);
        igBirthday = findViewById(R.id.ig_birthday);
        igCreateTime = findViewById(R.id.ig_createTime);
        toolBar=findViewById(R.id.toolbar_edit);

        //获取当前登陆用户
        SharedPreferences sp = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("logUser","");

        //显示用户基本信息
        UserDao userDao = new UserDao();
        User user = userDao.getUserInfo(userName);
        igId.getContentEdt().setText(user.getUserId()+"");
        igName.getContentEdt().setText(user.getUserName());
        igDescription.getContentEdt().setText(user.getDescription());
        igGender.getContentEdt().setText(user.getGender());
        igEmail.getContentEdt().setText(user.getEmail());
        igBirthday.getContentEdt().setText(user.getBirthday());
        igCreateTime.getContentEdt().setText(user.getCreate_time());

        //"完成"按钮监听
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.finish_edit:
                        break;
                }
                return true;

            }
        });
        //填写简介
//        igDescription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

}












