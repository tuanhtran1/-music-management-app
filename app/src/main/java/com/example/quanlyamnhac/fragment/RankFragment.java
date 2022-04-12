package com.example.quanlyamnhac.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.sqlite.SQLite;


public class RankFragment extends Fragment {
    SQLite sqLite;
    //them menu de logout
    View view = null;

    public RankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rank, container, false);
        setControl();
        setEvent();

        return view;
    }

    private void setEvent() {

    }


    private void setControl() {
        sqLite = new SQLite(getContext(), "music-managerment.sqlite", null, 1);
    }


}