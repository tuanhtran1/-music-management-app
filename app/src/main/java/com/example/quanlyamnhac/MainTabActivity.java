package com.example.quanlyamnhac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.quanlyamnhac.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainTabActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mviewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        setControl();
        setEvent();
    }

    private void setEvent() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mviewPager);
    }

    private void setControl() {
        mviewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tabMain);
    }
}