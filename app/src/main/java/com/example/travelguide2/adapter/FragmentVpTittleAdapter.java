package com.example.travelguide2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class FragmentVpTittleAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> titleList;

    public FragmentVpTittleAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
        super(fm);
        this.fragmentList=fragmentList;
        this.titleList=titleList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList==null ? null :fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList==null ? 0 :fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList==null ? "" :titleList.get(position);

    }
}
