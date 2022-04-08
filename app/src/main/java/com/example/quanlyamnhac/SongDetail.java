package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyamnhac.adapter.SongAdapter;
import com.example.quanlyamnhac.mapper.SongMapper;
import com.example.quanlyamnhac.model.reponse.SongReponse;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.util.ArrayList;
import java.util.List;

public class SongDetail extends AppCompatActivity {

    SQLite sqLite;
    Toolbar toolbar;

    TextView tv_musicianName;
    EditText et_songName, et_yearOfCreation;

    public static Integer idSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        mapping();
        setToolBar();
        layDL();
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
        et_songName = findViewById(R.id.et_songName);
        tv_musicianName= findViewById(R.id.tv_musicianName);
        et_yearOfCreation = findViewById(R.id.et_yearOfCreation);
    }

    private void layDL() {

        Cursor cursor = sqLite.getData(" SELECT song.name, song.yearofcreation, musician.name " +
                " FROM song, musician " +
                " WHERE musician.id = song.id_musician"+
                " AND song.id = " + Song.idSong);
        if(cursor.moveToNext()){
            et_songName.setText(cursor.getString(0));
            tv_musicianName.setText(cursor.getString(2));
            et_yearOfCreation.setText(cursor.getString(1));
        }
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}