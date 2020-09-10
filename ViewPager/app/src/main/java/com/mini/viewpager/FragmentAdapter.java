package com.mini.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

//FragmentPagerAdapter = 모든 프에그먼트를 메모리에 미리 로딩 시켜둠 소멸안시킴
//FragmentStatePagerAdapter = default 3 , 자기를 포함한 양옆만 메로리에 로딩
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    //FragmentList에 아이템 추가하는 함수 필요
    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
