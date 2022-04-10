package com.example.quanlyamnhac.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.ItemSingerAdapter;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


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
        sqLite = new SQLite(getContext(),"music-managerment.sqlite", null, 1);
    }


}