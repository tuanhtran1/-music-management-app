package com.example.quanlyamnhac.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.HomeAdapter;
import com.example.quanlyamnhac.mapper.HomeMapper;
import com.example.quanlyamnhac.model.reponse.HomeReponse;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    SQLite sqLite;

    RecyclerView recycler_home;
    HomeAdapter homeAdapter;
    View view;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mapping();
        setRecyclerHome();
        //https://www.tutorialspoint.com/how-to-call-an-activity-method-from-a-fragment-in-android-app
        return view;
    }

    private void mapping() {
        sqLite = new SQLite(getContext(),"music-managerment.sqlite", null, 1);
        recycler_home = view.findViewById(R.id.recycler_home);
    }

    private void setRecyclerHome() {
        recycler_home.setHasFixedSize(true);
        recycler_home.setLayoutManager(new LinearLayoutManager((getContext())));
        homeAdapter = new HomeAdapter(getContext(),getList());
        recycler_home.setAdapter(homeAdapter);
    }

    private List<HomeReponse> getList() {

        Cursor cursor = sqLite.getData("SELECT song.name, musician.name, song.yearofcreation FROM song, musician"
        + " WHERE song.id_musician = musician.id");
        List<HomeReponse> homeReponses = new ArrayList<>();
        while(cursor.moveToNext()){
            homeReponses.add(HomeMapper.toHomeReponse(cursor));
        }

        return homeReponses;
    }
}