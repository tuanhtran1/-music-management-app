package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

//import com.example.quanlyamnhac.adapter.PerformanceInfoAdapter;
import com.example.quanlyamnhac.model.reponse.PerformanceInfoReponse;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PerformanceInfo extends AppCompatActivity {

    RecyclerView recycler_performance;
//    PerformanceInfoAdapter performanceAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_detail);
        mapping();
//        setRecyclerPerformance();
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

    private void mapping() {
//        recycler_performance = findViewById(R.id.recycler_performance);
        toolbar = findViewById(R.id.toolbar);
    }

//    private void setRecyclerPerformance() {
//        recycler_performance.setHasFixedSize(true);
//        recycler_performance.setLayoutManager(new LinearLayoutManager((this)));
//        performanceAdapter = new PerformanceInfoAdapter(this,getList());
//        recycler_performance.setAdapter(performanceAdapter);
//    }

//    private List<PerformanceInfoReponse> getList() {
//        List<PerformanceInfoReponse> performanceModels = new ArrayList<>();
//        performanceModels.add(new PerformanceInfoReponse("Trần Anh Tú","Hoa Nở Không Màu" , new Date("4/1/2022"), "97 Man Thiện"));
//        performanceModels.add(new PerformanceInfoReponse("Trần Anh Tú","Hoa Nở Không Màu" , new Date("4/1/2022"), "97 Man Thiện"));
//        performanceModels.add(new PerformanceInfoReponse("Trần Anh Tú","Hoa Nở Không Màu" , new Date("4/1/2022"), "97 Man Thiện"));
//        performanceModels.add(new PerformanceInfoReponse("Trần Anh Tú","Hoa Nở Không Màu" , new Date("4/1/2022"), "97 Man Thiện"));
//        return performanceModels;
//    }
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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
