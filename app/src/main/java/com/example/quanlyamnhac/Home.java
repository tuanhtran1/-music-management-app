package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlyamnhac.adapter.HomeAdapter;
import com.example.quanlyamnhac.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity{

    RecyclerView recycler_home;
    HomeAdapter homeAdapter;
    Button bt_loc;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mapping();
        setRecyclerHome();
        setButtonLoc();
        setToolBar();
    }

    private void setToolBar() {
        //Set toolbar as action bar
        setSupportActionBar(toolbar);
        //Enable Home icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Toolbar title
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void setButtonLoc() {
        bt_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this,"vao trang loc",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Home.this, Musician.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        recycler_home = findViewById(R.id.recycler_home);
        bt_loc = findViewById(R.id.bt_loc);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setRecyclerHome() {
        recycler_home.setHasFixedSize(true);
        recycler_home.setLayoutManager(new LinearLayoutManager((this)));
        homeAdapter = new HomeAdapter(this,getList());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it_search:
                Toast.makeText(this, "Ban chon search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_home:
                Toast.makeText(this, "Ban chon home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_advance_search:
                Toast.makeText(this, "Ban chon advance search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_log_out:
                Toast.makeText(this, "Ban chon log out", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}