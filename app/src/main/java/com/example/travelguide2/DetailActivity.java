package com.example.travelguide2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {

    public static final String FRUIT_NAME = "name";
    public static final String FRUIT_IMAGE_ID = "imageId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //获取传递进来的数据
        Intent intent=getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        String fruitImageId = intent.getStringExtra(FRUIT_IMAGE_ID);
        //获取实例
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        CollapsingToolbarLayout collapsingToolbarLayout= findViewById(R.id.collapsing_toolbar_detail);
        ImageView fruit_imageView = findViewById(R.id.cover_image_view);
        TextView fruit_textView = findViewById(R.id.fruit_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //对实例进行赋值
        collapsingToolbarLayout.setTitle(fruitName);
//        Glide.with(this).load(fruitImageId).into(fruit_imageView);//Glide从网上下载
        String fruitContent = generateFruitContent(fruitName);
        fruit_textView.setText(fruitContent);
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i=0; i<500; i++){
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    //处理点赞按钮点击事件，点击之后调用finish() 关闭当前Activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}