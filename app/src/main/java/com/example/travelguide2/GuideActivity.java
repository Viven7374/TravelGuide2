package com.example.travelguide2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GuideActivity extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    Button skipbtn,nextbtn;
    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        skipbtn=findViewById(R.id.skipButton);
        nextbtn=findViewById(R.id.nextbtn);

        //get started 按钮跳转
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getitem(0) < 3)
                    mSlideViewPager.setCurrentItem(getitem(1),true);
                else{
                    Intent i = new Intent(GuideActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
        //跳过按钮跳转
        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        mSlideViewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    public void setUpindicator(int positon){
        dots=new TextView[4];
        mDotLayout.removeAllViews();

        for (int i = 0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.md_theme_dark_outline,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }
        dots[positon].setTextColor(getResources().getColor(R.color.seed,getApplicationContext().getTheme()));
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);

            if (position == 3)
                nextbtn.setVisibility(View.VISIBLE);
            else
                nextbtn.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getitem(int i){
        return mSlideViewPager.getCurrentItem()+i;
    }
}