package com.summer.tabLayout;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExtendTabLayout tabLayout = findViewById(R.id.act_main_tabLayout);
        ViewPager viewPager = findViewById(R.id.act_main_viewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), new String[]{"热门", "关注", "喜欢", "附近"}, fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

}
