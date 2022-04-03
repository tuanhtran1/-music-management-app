package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.example.quanlyamnhac.adapter.MusicianAdapter;
import com.example.quanlyamnhac.mapper.MusicianMapper;
import com.example.quanlyamnhac.model.MusicianModel;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.util.ArrayList;

public class Musician extends AppCompatActivity {

    GridView gvListMusician;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician);
        setControl();
        setEvent();
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

    private void setEvent() {
        ArrayList<MusicianModel> musicianModels = khoitao(); // doc du lieu tu SQL
        //list Nhac si
        MusicianAdapter musicianAdapter = new MusicianAdapter(this, R.layout.item_musician_layout, musicianModels);
        gvListMusician.setAdapter(musicianAdapter);
    }

    private ArrayList<MusicianModel> khoitao() {
        ArrayList<MusicianModel> data = new ArrayList<>();
        MusicianModel musician1 = new MusicianModel("1", "Trịnh Công Sơn", "https://upload.wikimedia.org/wikipedia/vi/5/5b/Trinhcongson.jpg");
        MusicianModel musician2 = new MusicianModel("2", "Văn Cao", "https://upload.wikimedia.org/wikipedia/vi/thumb/1/1a/Vancao.jpg/175px-Vancao.jpg");
        MusicianModel musician3 = new MusicianModel("3", "Trần Tiến", "http://baokhanhhoa.vn/dataimages/201507/original/images1088188_nhac_sy_Tran_tien.jpg");
        MusicianModel musician4 = new MusicianModel("4", "Phương Uyên", "http://image.vtc.vn/files/f1/2012/09/17/avapu1jpg.jpg");
        MusicianModel musician5 = new MusicianModel("5", "Ngọc Châu", "https://vtv1.mediacdn.vn/thumb_w/650/2022/3/17/104311693297023005203625988508962618143060o-16474902650871024895088-crop-1647490273721790446797.jpg");
        data.add(musician1);
        data.add(musician2);
        data.add(musician3);
        data.add(musician4);
        data.add(musician5);
        return data;
//        ArrayList<MusicianModel> musicianModels = new ArrayList<>();
//        Cursor cursor = sqLite.getData("select * from musician");
//        if(cursor.moveToFirst()){
//            do{
//                musicianModels.add(MusicianMapper.convertToModel(cursor));
//            }while (cursor.moveToNext());
//        }
//        return musicianModels;
    }

    private void setControl() {
        gvListMusician = findViewById(R.id.gvDanhSachMusician);
        toolbar = findViewById(R.id.toolbar);
    }
}