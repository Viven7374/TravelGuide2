package com.example.travelguide2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {

    public static final String ARTICLE_NAME = "title";
    public static final String ARTICLE_IMAGE_ID = "imageId";
    public static final String ARTICLE_CONTENT="content";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //获取传递进来的数据
        Intent intent=getIntent();
        String articleName = intent.getStringExtra(ARTICLE_NAME);
        String articleImageId = intent.getStringExtra(ARTICLE_IMAGE_ID);
        String articleContent = intent.getStringExtra(ARTICLE_CONTENT);
        //获取实例
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        CollapsingToolbarLayout collapsingToolbarLayout= findViewById(R.id.collapsing_toolbar_detail);
        ImageView article_imageView = findViewById(R.id.cover_image_view);
        TextView article_content = findViewById(R.id.article_content_text);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.this.onBackPressed();
            }
        });
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar!=null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeButtonEnabled(true);
//        }
        //对实例进行赋值
        collapsingToolbarLayout.setTitle(articleName);
//        Glide.with(this).load(fruitImageId).into(article_imageView);//Glide从网上下载
//        String fruitContent = generateFruitContent(articleName);
        article_content.setText(articleContent);
    }

//    private String generateFruitContent(String fruitName) {
//        StringBuilder fruitContent = new StringBuilder();
//        for (int i=0; i<500; i++){
//            fruitContent.append(fruitName);
//        }
//        return fruitContent.toString();
//    }

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