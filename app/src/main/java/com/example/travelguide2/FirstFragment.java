package com.example.travelguide2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelguide2.adapter.RCModel;
import com.example.travelguide2.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    //声明
    RecyclerView recyclerView;
    ArrayList<RCModel> modelArrayList;
    RecyclerViewAdapter rcAdapter;
    String[] title = new String[]{"最美西湖十景", "灵隐寺", "云栖竹径", "啦啦啦", "西湖泛舟"};
    int[] image = new int[]{
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.index_a,
    };

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_first, container, false);
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        //控件初始化
        recyclerView=v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        modelArrayList = new ArrayList<>();
        rcAdapter = new RecyclerViewAdapter(getContext(),modelArrayList);
        recyclerView.setAdapter(rcAdapter);
        for (int i=0; i<title.length;i++){
            RCModel rcModel = new RCModel(title[i],image[i]);
            modelArrayList.add(rcModel);
        }
        rcAdapter.notifyDataSetChanged();

        return v;
    }
}