package com.example.quanlyamnhac.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quanlyamnhac.fragment.HomeFragment;
import com.example.quanlyamnhac.fragment.MusicianFragment;
import com.example.quanlyamnhac.fragment.RankFragment;
import com.example.quanlyamnhac.fragment.SingerFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MusicianFragment();
            case 2:
                return new SingerFragment();
            case 3:
                return new RankFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
