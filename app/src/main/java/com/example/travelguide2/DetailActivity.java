package com.example.travelguide2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelguide2.adapter.RecyclerViewAdapter;
import com.example.travelguide2.dao.ArticleDao;
import com.example.travelguide2.entity.Article;
import com.example.travelguide2.utils.ImageHelper;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {

    public static final String ARTICLE_NAME = "title";
    public static final String ARTICLE_IMAGE_ID = "imageId";
    public static final String ARTICLE_CONTENT="content";
    public static final String ARTICLE_AUTHOR = "author";
    public static final String RELEASE_DATE = "releaseDate";
//    public static final String COVER_PICTURE = "coverPicture";
    public static final String ARTICLE_TYPE = "articleType";
    public static final String ARTICLE_ID = "articleId";


    private Toolbar toolbar;
    private TextView tv_author,tv_type,tv_date;
    private ImageView article_imageView;
    //Bitmap
    private Bitmap static_bitmap,static_bit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        article_imageView = findViewById(R.id.cover_image_view);
        //获取传递进来的数据
        Intent intent=getIntent();
//        if (intent!=null){
//            //转为bitmap
//            static_bitmap = intent.getParcelableExtra("photo_bitmap");
//            //给ImageView赋予图片
//            article_imageView.setImageBitmap(static_bitmap);
//        }
        String articleName = intent.getStringExtra(ARTICLE_NAME);
        String articleContent = intent.getStringExtra(ARTICLE_CONTENT);
        String articleAuthor = intent.getStringExtra(ARTICLE_AUTHOR);
        String releaseDate=intent.getStringExtra(RELEASE_DATE);
        String articleType=intent.getStringExtra(ARTICLE_TYPE);
        int article_id = intent.getIntExtra("ARTICLE_ID",1);
//        int article_id=intent.getIntExtra(ARTICLE_ID);
        //获取实例
        tv_author=findViewById(R.id.author);
        tv_date=findViewById(R.id.release_date);
//        tv_type=findViewById(R.id.article_type);
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        CollapsingToolbarLayout collapsingToolbarLayout= findViewById(R.id.collapsing_toolbar_detail);
//        article_imageView = findViewById(R.id.cover_image_view);
        TextView article_content = findViewById(R.id.article_content_text);
        //返回按钮监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.this.onBackPressed();
            }
        });

        //对实例进行赋值
        collapsingToolbarLayout.setTitle(articleName);
        article_content.setText(articleContent);
        tv_author.setText(articleAuthor);
        tv_date.setText(releaseDate);
//        tv_type.setText(articleType);
//        Glide.with(this).load(fruitImageId).into(article_imageView);//Glide从网上下载
//        String fruitContent = generateFruitContent(articleName);
        //传图片
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ArticleDao articleDao = new ArticleDao();
//                Article article = new Article();
//                article=articleDao.getInfoById(article_id);
//                String string=article.getCover_picture();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Bitmap bitmap = ImageHelper.stringToBitmap(string);
//                        article_imageView.setImageBitmap(bitmap);
//                    }
//                });
////                Message message = handler.obtainMessage();
////                if (!article.isEmpty()){
////                    message.what=0x11;
////                    handler.sendMessage(message);
////                }
//
//            }
//        }).start();




    }
    @SuppressLint("HandlerLeak") Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0x11:
                    //获得集合，开始列表初始化
                    ImageView article_imageView = findViewById(R.id.cover_image_view);

//                    Log.e("arraylist","值:  "+);
                    break;
                case 0x12:
                    break;
            }
            super.handleMessage(msg);
        }
    };



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