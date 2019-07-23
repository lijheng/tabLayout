package com.summer.tabLayout;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Create By: lijheng
 * Date: 2019/2/28 14:01
 */
public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> title;
    private List<Fragment> fragmentList;

    public FragmentPagerAdapter(FragmentManager fm, String[] title, List<Fragment> fragmentList) {
        super(fm);
        if (title.length != fragmentList.size()) {
            throw new IllegalArgumentException("标题数和fragment数 必须保持一致");
        }
        this.title = Arrays.asList(title);
        this.fragmentList = fragmentList;
    }

    public FragmentPagerAdapter(FragmentManager fm, List<String> title, List<Fragment> fragmentList) {
        super(fm);
        if (title.size() != fragmentList.size()) {
            throw new IllegalArgumentException("标题数和fragment数 必须保持一致");
        }
        this.title = title;
        this.fragmentList = fragmentList;
    }

    public FragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (title != null) {
            return title.get(position);
        }
        return super.getPageTitle(position);
    }
}
