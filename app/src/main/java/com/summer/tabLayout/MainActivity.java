package com.summer.tabLayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.summer.extendtablayout.ExtendTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.act_main_viewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), new String[]{"热门", "关注", "喜欢", "附近"}, fragments));
        setUpWithViewPager(R.id.act_main_tabLayout, viewPager);
        setUpWithViewPager(R.id.act_main_tabLayout2, viewPager);
        setUpWithViewPager(R.id.act_main_tabLayout3, viewPager);
        setUpWithViewPager(R.id.act_main_tabLayout4, viewPager);
        setUpWithViewPager(R.id.act_main_tabLayout5, viewPager);
    }

    private void setUpWithViewPager(int id, ViewPager viewPager) {
        ExtendTabLayout tabLayout = findViewById(id);
        tabLayout.setupWithViewPager(viewPager);
    }

}
