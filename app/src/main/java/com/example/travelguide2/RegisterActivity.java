package com.example.travelguide2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelguide2.dao.UserDao;
import com.example.travelguide2.entity.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName,regPw,regRpw;
    private Button btSign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //适用于网络请求数据量很小
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //控件初始化
        regName=findViewById(R.id.reg_name);
        regPw=findViewById(R.id.reg_pw);
        regRpw=findViewById(R.id.reg_rpw);
        btSign=findViewById(R.id.bt_sign);

        //注册控件操作
        btSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reName = regName.getText().toString();
                String rePw = regPw.getText().toString();
                String reRpw = regRpw.getText().toString();

                if (reName.equals(""))
                    Toast.makeText(getApplicationContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
                else if (rePw.equals(""))
                    Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                else if (!rePw.trim().equals(reRpw.trim()))
                    Toast.makeText(getApplicationContext(),"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                else {
                    User user = new User();
                    user.setUserName(reName);
                    user.setUserPw(rePw);
                    UserDao userDao = new UserDao();
                    User user1 = userDao.findUser(user.getUserName());
                    if (user1 != null){
                        Toast.makeText(getApplicationContext(),"该账号已存在！",Toast.LENGTH_SHORT).show();
                    }else {
                        boolean flag = userDao.register(user);
                        if (flag){
                            Toast.makeText(getApplicationContext(),"注册成功！",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }







            }
        });

















    }
}