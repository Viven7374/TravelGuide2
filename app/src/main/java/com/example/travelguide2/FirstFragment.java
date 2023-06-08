package com.example.travelguide2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.travelguide2.adapter.RecyclerViewAdapter;
import com.example.travelguide2.dao.ArticleDao;
import com.example.travelguide2.entity.Article;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    //声明
    ArrayList<Article> articleList;
    RecyclerViewAdapter rcAdapter;
    RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_first, container, false);
        View v = inflater.inflate(R.layout.fragment_first, container, false);


        new Thread(new Runnable() {
            @Override
            public void run() {
                ArticleDao articleDao = new ArticleDao();
                articleList = (ArrayList<Article>) articleDao.getAllInfo();
                recyclerView=v.findViewById(R.id.recycler_view);
                //把得到集合的信息通知到主线程
                Message message = handler.obtainMessage();
                if (!articleList.isEmpty()){
                    message.what=0x11;
                    handler.sendMessage(message);
                }

//                articleList = (ArrayList<Article>) ArticleDao.getInfoById(2);
            }
        }).start();

        return v;

        //控件初始化
//        recyclerView=v.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recycelerView.setHasFixedSize(true);
//        modelArrayList = new ArrayList<>();
//        rcAdapter = new RecyclerViewAdapter(getContext(),modelArrayList);
//        recyclerView.setAdapter(rcAdapter);
//        for (int i=0; i<title.length;i++){
//            RCModel rcModel = new RCModel(title[i],image[i]);
//            modelArrayList.add(rcModel);
//        }
//        rcAdapter.notifyDataSetChanged();

    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0x11:
                    //获得集合，开始列表初始化
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    Log.e("arraylist","值:  "+articleList);
//                    articleList=new ArrayList<>();
                    rcAdapter=new RecyclerViewAdapter(getContext(),articleList);
                    recyclerView.setAdapter(rcAdapter);
//                    rcAdapter.setItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(int position) {
//                            Toast.makeText(getActivity(),articleList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
//                            Intent intent =new Intent(getActivity(),DetailActivity.class);
//                            startActivity(intent);
//                        }
//                    });
                    rcAdapter.notifyDataSetChanged();
                    break;
                case 0x12:
                    break;
            }
            super.handleMessage(msg);
        }
    };

//    private void InitRecyclerView() {
//        //初始化视图
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        rcAdapter=new RecyclerViewAdapter(articleList);
//        recyclerView.setAdapter(rcAdapter);
//    }
}