package com.example.travelguide2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    //声明变量
    SearchView guide_search;
    ImageView p1,p2,p3,p4,p5,p6,p7,p8;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_second, container, false);

        //控件初始化
        guide_search = v.findViewById(R.id.guide_search);
        p1 = v.findViewById(R.id.guide_p1);
        p2 = v.findViewById(R.id.guide_p2);
        p3 = v.findViewById(R.id.guide_p3);
        p4 = v.findViewById(R.id.guide_p4);
        p5 = v.findViewById(R.id.guide_p5);
        p6 = v.findViewById(R.id.guide_p6);
        p7 = v.findViewById(R.id.guide_p7);
        p8 = v.findViewById(R.id.guide_p8);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),LocationActivity.class);
                intent.putExtra("sight","dq");
                startActivity(intent);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),LocationActivity.class);
                intent.putExtra("sight","yh");
                startActivity(intent);
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),LocationActivity.class);
                intent.putExtra("sight","ll");
                startActivity(intent);
            }
        });

        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),LocationActivity.class);
                intent.putExtra("sight","xx");
                startActivity(intent);
            }
        });


        return v;
    }
}










