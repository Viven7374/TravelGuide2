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

public class LoginActivity extends AppCompatActivity {

    private Button btReg,btSubmit;
    private EditText loginName,loginPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //适用于网络请求数据量很小
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //控件初始化
        btReg=findViewById(R.id.log_ButtonRegister);
        btSubmit=findViewById(R.id.bt_login);
        loginName=findViewById(R.id.login_name);
        loginPw=findViewById(R.id.login_pw);

        //跳转到注册页面
        btReg.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        //登陆控件操作
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserDao userDao = new UserDao();
                int msg = userDao.login(loginName.getText().toString(),loginPw.getText().toString());
                if (msg == 0){
                    Toast.makeText(getApplicationContext(),"登陆失败！",Toast.LENGTH_SHORT).show();
                }else if (msg == 1){
                    Toast.makeText(getApplicationContext(),"登陆成功！",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this,MainFragmentActivity.class);
                    startActivity(intent);
                    finish();

                }else if (msg == 2){
                    Toast.makeText(getApplicationContext(),"密码错误！",Toast.LENGTH_SHORT).show();
                }else if (msg == 3){
                    Toast.makeText(getApplicationContext(),"用户不存在！",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
































