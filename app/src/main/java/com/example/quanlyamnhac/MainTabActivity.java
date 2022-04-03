package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.quanlyamnhac.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainTabActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager2 mviewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        setControl();
        setEvent();
    }

    private void setEvent() {
        viewPagerAdapter = new ViewPagerAdapter(this);
        mviewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(mTabLayout, mviewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("home");
                    break;
                case 1:
                    tab.setText("Musician");
                    break;
                case 2:
                    tab.setText("Singer");
                    break;
            }
        });
    }

    private void setControl() {
        mviewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tabMain);
    }
}