package com.example.quanlyamnhac.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.HomeAdapter;
import com.example.quanlyamnhac.model.HomeModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

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
        recycler_home = view.findViewById(R.id.recycler_home);
    }

    private void setRecyclerHome() {
        recycler_home.setHasFixedSize(true);
        recycler_home.setLayoutManager(new LinearLayoutManager((getContext())));
        homeAdapter = new HomeAdapter(getContext(),getList());
        recycler_home.setAdapter(homeAdapter);
    }

    private List<HomeModel> getList() {
        List<HomeModel> homeModels = new ArrayList<>();
        homeModels.add(new HomeModel("1", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("2", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("3", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("4", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("5", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("6", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("7", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("8", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("9", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("10", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("11", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("12", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("13", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("14", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("15", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("16", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("17", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("18", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("19", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("20", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("21", "Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeModel("22", "Đi và suy", "C-ANH", "2021"));
        return homeModels;
    }
}