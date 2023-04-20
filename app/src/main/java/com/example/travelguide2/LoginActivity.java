package com.example.travelguide2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button btReg,btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btReg=findViewById(R.id.log_ButtonRegister);
        btSubmit=findViewById(R.id.bt_login);
        //跳转到注册页面
        btReg.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,GuideActivity.class);
            startActivity(intent);
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(LoginActivity.this,MainFragmentActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}