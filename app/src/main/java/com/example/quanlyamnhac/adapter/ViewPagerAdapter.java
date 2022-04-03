package com.example.quanlyamnhac.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quanlyamnhac.fragment.HomeFragment;
import com.example.quanlyamnhac.fragment.MusicianFragment;
import com.example.quanlyamnhac.fragment.SingerFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new MusicianFragment();
            case 2:
                return new SingerFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Home";
                break;
            case 1:
                title = "Musician";
                break;
            case 2:
                title = "Singer";
                break;
        }
        return super.getPageTitle(position);

    }
}
