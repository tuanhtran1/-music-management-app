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

import com.example.quanlyamnhac.adapter.PerformanceAdapter;
import com.example.quanlyamnhac.model.PerformanceModel;


import java.util.ArrayList;
import java.util.List;

public class PerformanceDetail extends AppCompatActivity {

    RecyclerView recycler_performance;
    PerformanceAdapter performanceAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_detail);
        mapping();
        setRecyclerPerformance();
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
        recycler_performance = findViewById(R.id.recycler_performance);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setRecyclerPerformance() {
        recycler_performance.setHasFixedSize(true);
        recycler_performance.setLayoutManager(new LinearLayoutManager((this)));
        performanceAdapter = new PerformanceAdapter(this,getList());
        recycler_performance.setAdapter(performanceAdapter);
    }

    private List<PerformanceModel> getList() {
        List<PerformanceModel> performanceModels = new ArrayList<>();
        performanceModels.add(new PerformanceModel("1","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("2","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("3","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("4","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("5","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("6","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("7","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("8","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("9","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("10","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("11","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("12","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("13","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("14","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("15","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("16","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("17","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("18","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("19","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("20","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("21","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("22","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("23","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("24","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        performanceModels.add(new PerformanceModel("25","Trần Anh Tú","Hoa Nở Không Màu" , "4/1/2022", "97 Man Thiện"));
        return performanceModels;
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
