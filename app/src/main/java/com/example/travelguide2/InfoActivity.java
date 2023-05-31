package com.example.travelguide2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.travelguide2.utils.ActivityCollector;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_info);
    }
}