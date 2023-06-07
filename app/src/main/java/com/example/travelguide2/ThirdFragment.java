package com.example.travelguide2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.travelguide2.dao.ArticleDao;
import com.example.travelguide2.entity.Article;
import com.example.travelguide2.utils.ImageHelper;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    //声明变量
    private EditText etTitle, etType, etContent;
    private ImageView etPicture;
    private Button btClear,btSubmit;
    private String userName;
    private String[] strType;
    private boolean[] checkedID;
    private final int CODE_PICK_PHOTO = 1;
    private Uri imageUri;
    private String bitmapToString = null;
    private String photoPath = null;
    private Bitmap bitmap;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //适用于网络请求数据量很小
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container, false);

        //控件初始化
        etTitle = v.findViewById(R.id.post_title);
        etType = v.findViewById(R.id.post_type);
        etPicture = v.findViewById(R.id.post_picture);
        etContent = v.findViewById(R.id.post_content);
        btClear = v.findViewById(R.id.post_clear);
        btSubmit = v.findViewById(R.id.post_submit);
        strType = new String[]{"美食探店","景点介绍","住宿推荐","旅游攻略"};
        checkedID = new boolean[strType.length];

        //获取当前登陆用户
        SharedPreferences sp = getActivity().getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        userName = sp.getString("logUser","");

        //选择类型
        etType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("选择文章类型");
                builder.setMultiChoiceItems(strType, checkedID, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        checkedID[i]=b;
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String text = "";
                        for(int i=0;i<checkedID.length;i++){
                            if(checkedID[i])
                                text=text+strType[i]+" ";
                        }
                        etType.setText(text);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        etType.setText("");
                    }
                });
                builder.show();
            }
        });

        //上传图片
        etPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_gallery = new Intent(Intent.ACTION_PICK);
                intent_gallery.setType("image/*");
                startActivityForResult(intent_gallery,CODE_PICK_PHOTO);
            }
        });

        //“清空”按钮监听
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etTitle.setText("");
                etType.setText("");
                etContent.setText("");
            }
        });

        //“发布”按钮监听
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date nowDate = new Date(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Article article = new Article();
                article.setTitle(etTitle.getText().toString());
                article.setAuthor(userName);
                article.setType(etType.getText().toString());
                article.setRelease_date(Timestamp.valueOf(sdf.format(nowDate)).toString());
                article.setContent(etContent.getText().toString());
                article.setViews(0);
                article.setCover_picture(bitmapToString);

                ArticleDao articleDao = new ArticleDao();
                boolean flag = articleDao.addArticle(article);
                if (flag){
                    Toast.makeText(getActivity().getApplicationContext(),"文章发布成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(),"文章发布失败！",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }

    //获取图片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_PICK_PHOTO) {//相册
            imageUri= data.getData();
            //获取照片路径
            etPicture.setImageURI(imageUri);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                bitmapToString = ImageHelper.bitmapToString(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}