package com.example.travelguide2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.travelguide2.adapter.FragmentVpTittleAdapter;
import com.example.travelguide2.fragment.CollectFragment;
import com.example.travelguide2.fragment.PostFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FourthFragment extends Fragment {
    //声明变量
    private MaterialToolbar toolbar;
    private Button editButton;
    private ViewPager vp;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private FragmentVpTittleAdapter vpAdapter;
    String[] mTitles={"发布","收藏"};

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FourthFragment() {
    }

    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
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
        View v=inflater.inflate(R.layout.fragment_fourth, container, false);
        //通过id找到组件
        toolbar=v.findViewById(R.id.toolbar4);
        tabLayout=v.findViewById(R.id.tab_layout4);
        vp=v.findViewById(R.id.viewpager4);
        // 设置TAB滚动显示
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 添加TAB标签
        for (String mTitle : mTitles) {
            tabLayout.addTab(tabLayout.newTab().setText(mTitle));
        }
        //设置字体选中颜色
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorBlack, null),
                getResources().getColor(R.color.colorPrimary, null));
        // 设置选中下划线颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary, null));
        //绑定viewpager
        tabLayout.setupWithViewPager(vp,false);
        initData();
        vpAdapter=new FragmentVpTittleAdapter(getChildFragmentManager(),fragmentList,titleList);
        vp.setAdapter(vpAdapter);
        // 设置ViewPager默认显示index
        vp.setCurrentItem(0);

        //---------------------------编辑按钮监听--------------------------------------------------
        editButton=v.findViewById(R.id.edit_info_bt);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    private void initData() {
        fragmentList=new ArrayList<>();
        PostFragment postFragment= PostFragment.newInstance("发布","");
        CollectFragment collectFragment=CollectFragment.newInstance("收藏","");
        fragmentList.add(postFragment);
        fragmentList.add(collectFragment);

        titleList=new ArrayList<>();
        titleList.add("发布");
        titleList.add("收藏");
    }
}