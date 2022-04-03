package com.example.quanlyamnhac.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlyamnhac.Home;
import com.example.quanlyamnhac.LogIn;
import com.example.quanlyamnhac.MainTabActivity;
import com.example.quanlyamnhac.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Home home = (Home) getActivity();
        return inflater.inflate(R.layout.activity_home, container, false);
    }
}