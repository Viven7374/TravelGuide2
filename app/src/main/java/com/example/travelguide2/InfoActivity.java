package com.example.travelguide2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelguide2.dao.UserDao;
import com.example.travelguide2.entity.User;
import com.example.travelguide2.utils.ActivityCollector;
import com.example.travelguide2.utils.ImageHelper;
import com.example.travelguide2.widget.ItemGroup;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.internal.ToolbarUtils;

import java.io.IOException;
import java.util.Calendar;

public class InfoActivity extends AppCompatActivity {

    private ItemGroup igId,igName,igDescription,igGender,igEmail,igBirthday,igCreateTime;
    private ImageView riPortrait;
    Toolbar toolBar;
    int selectedIndex;
    private String[] strGender;
    private final int CODE_PICK_PORTRAIT = 2;
    private Uri imageUri;
    private String bitmapToString = null;



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
        riPortrait = findViewById(R.id.ri_portrait);
        toolBar=findViewById(R.id.toolbar_edit);
        selectedIndex=-1;
        strGender=new String[]{"男","女"};

        //返回按钮
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoActivity.this.onBackPressed();
            }
        });
//        setSupportActionBar(toolBar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar!=null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeButtonEnabled(true);
//        }
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

        //上传图片
        riPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_gallery = new Intent(Intent.ACTION_PICK);
                intent_gallery.setType("image/*");
                startActivityForResult(intent_gallery,CODE_PICK_PORTRAIT);
            }
        });

        //填写简介
        igDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(InfoActivity.this);
                input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(200)});
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setTitle("请填写");
                builder.setView(input).setNegativeButton("取消",null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sign = input.getText().toString();
                        if (sign!=null && !sign.isEmpty()){
                            igDescription.getContentEdt().setText(sign);
                        }
                    }
                });
                builder.show();
            }
        });
        //选择性别
        igGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setTitle("请选择");
                builder.setSingleChoiceItems(strGender, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedIndex = i;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (selectedIndex == 0){
                            igGender.getContentEdt().setText("男");
                        }else if (selectedIndex == 1){
                            igGender.getContentEdt().setText("女");
                        }
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }
        });
        //填写邮箱
        igEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(InfoActivity.this);
                input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setTitle("请填写");
                builder.setView(input).setNegativeButton("取消",null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sign = input.getText().toString();
                        if (sign!=null && !sign.isEmpty()){
                            igEmail.getContentEdt().setText(sign);
                        }
                    }
                });
                builder.show();
            }
        });
        //选择生日
        igBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        igBirthday.getContentEdt().setText(new StringBuilder().append(year).append("-")
                                .append(month+1).append("-").append(dayOfMonth));
                    }
                }, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        igId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"只能修改简介、性别、邮箱及生日！",Toast.LENGTH_SHORT).show();
            }
        });

        igName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"只能修改简介、性别、邮箱及生日！",Toast.LENGTH_SHORT).show();
            }
        });

        igCreateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"只能修改简介、性别、邮箱及生日！",Toast.LENGTH_SHORT).show();
            }
        });

        //"完成"按钮监听
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.finish_edit:
                        //将修改内容更新到数据库
                        User user1 = new User();
                        user1.setGender(igGender.getContentEdt().getText().toString());
                        user1.setEmail(igEmail.getContentEdt().getText().toString());
                        user1.setBirthday(igBirthday.getContentEdt().getText().toString());
                        user1.setDescription(igDescription.getContentEdt().getText().toString());
                        user1.setUserName(igName.getContentEdt().getText().toString());
                        user1.setHead_portrait(bitmapToString);
                        boolean flag = userDao.updateInfo(user1);
                        if (flag){
                            Toast.makeText(getApplicationContext(),"个人信息修改成功！",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"个人信息修改失败！",Toast.LENGTH_SHORT).show();
                        }
                }
                return true;
            }
        });

    }

    //获取图片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_PICK_PORTRAIT) {//相册
            imageUri= data.getData();
            //获取照片路径
            riPortrait.setImageURI(imageUri);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                bitmapToString = ImageHelper.bitmapToString(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}












