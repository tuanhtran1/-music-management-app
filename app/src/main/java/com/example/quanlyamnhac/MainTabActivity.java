package com.example.quanlyamnhac;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.quanlyamnhac.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainTabActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager2 mviewPager;
    ViewPagerAdapter viewPagerAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        setControl();
        setEvent();
        setToolBar();
        checkTab();
    }

    private void checkTab() {
        String current_tab;
        try {
            current_tab = getIntent().getStringExtra("cTab");
            if (current_tab.equals("MusicianTab")) {
                mviewPager.setCurrentItem(1);
            }
            if (current_tab.equals("SingerTab")) {
                mviewPager.setCurrentItem(2);
            }
        } catch (Exception e) {
            mviewPager.setCurrentItem(0);
        }
    }

    private void setToolBar() {
        //Set toolbar as action bar
        setSupportActionBar(toolbar);
        //Enable Home icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Toolbar title
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void setEvent() {
        viewPagerAdapter = new ViewPagerAdapter(this);
        mviewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(mTabLayout, mviewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    break;
                case 1:
                    tab.setText("Musician");
                    break;
                case 2:
                    tab.setText("Singer");
                    break;
                case 3:
                    tab.setText("Rank");
                    break;
            }
        }).attach();
    }

    private void setControl() {
        mviewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tabMain);
        toolbar = findViewById(R.id.toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.it_search:
                Toast.makeText(this, "Ban chon search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_home:
                Toast.makeText(this, "Ban chon home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_list_user:
                Toast.makeText(this, "Ban chon list user", Toast.LENGTH_SHORT).show();
                intent = new Intent(MainTabActivity.this, User.class);
                startActivity(intent);
                break;
            case R.id.it_log_out:
                Toast.makeText(this, "Ban chon log out", Toast.LENGTH_SHORT).show();
                intent = new Intent(MainTabActivity.this, LogIn.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}