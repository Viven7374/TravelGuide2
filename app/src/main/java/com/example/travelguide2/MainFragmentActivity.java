package com.example.travelguide2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragmentActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new FirstFragment()).commit();
        }
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.fragment1:
                        fragment =new FirstFragment();
                        break;
                    case R.id.fragment2:
                        fragment =new SecondFragment();
                        break;
                    case R.id.fragment3:
                        fragment =new ThirdFragment();
                        break;
                    case R.id.fragment4:
                        fragment =new FourthFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                return true;
            }
        });
    }
}