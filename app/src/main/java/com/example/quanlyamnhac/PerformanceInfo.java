package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyamnhac.mapper.PerformanceInfoMapper;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.example.quanlyamnhac.model.reponse.PerformanceInfoReponse;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.text.SimpleDateFormat;


public class PerformanceInfo extends AppCompatActivity {

    SQLite sqLite;

    public static Integer idPerformanceInfo;

    EditText et_performance_day, et_place;
    TextView et_song_name, et_singer_name;
    ImageButton ib_save, ib_delete;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_info);
        mapping();
        setToolBar();

        layDL();

        ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLite.queryData("DELETE FROM performance_info WHERE performance_info.id = " + PerformanceInfo.idPerformanceInfo);
                Toast.makeText(view.getContext(),"Deleting...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), MainTabActivity.class);
                startActivity(intent);
            }
        });
        ib_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLite.queryData("UPDATE performance_info SET performance_day = '" + et_performance_day.getText() + "', place = '" + et_place.getText()+ "'" +
                                " WHERE performance_info.id = " + PerformanceInfo.idPerformanceInfo);
                Intent intent = new Intent(view.getContext(), MainTabActivity.class);
                startActivity(intent);
            }
        });
    }

    private void layDL() {
        idPerformanceInfo = (Integer) getIntent().getSerializableExtra("idPerformanceInfo");
        Cursor cursor = sqLite.getData(" SELECT song.name, singer.name, performance_info.performance_day, performance_info.place" +
                " FROM song, singer, performance_info" +
                " WHERE performance_info.singer_id =  singer.id AND performance_info.song_id = song.id " +
                " AND performance_info.id = " + PerformanceInfo.idPerformanceInfo);
        if(cursor.moveToNext()){
            PerformanceInfoReponse performanceInfoReponse = PerformanceInfoMapper.toPerformanceInfoReponse(cursor);
            et_song_name.setText(performanceInfoReponse.getSongName());
            et_singer_name.setText(performanceInfoReponse.getSingerName());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String strDate = formatter.format(performanceInfoReponse.getDate());
            et_performance_day.setText(strDate);
            et_place.setText(performanceInfoReponse.getPlace());
        }
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
        sqLite = new SQLite(this,"music-managerment.sqlite", null, 1);

        toolbar = findViewById(R.id.toolbar);

        et_song_name = findViewById(R.id.tv_song_name);
        et_singer_name = findViewById(R.id.tv_singer_name);
        et_performance_day = findViewById(R.id.et_performance_day);
        et_place = findViewById(R.id.et_place);
        ib_save = findViewById(R.id.ib_save);
        ib_delete = findViewById(R.id.ib_delete);
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
                Intent intent = new Intent(PerformanceInfo.this, LogIn.class);
                startActivity(intent);
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
