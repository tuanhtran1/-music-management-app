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
import android.widget.Toast;

import com.example.quanlyamnhac.adapter.SongAdapter;
import com.example.quanlyamnhac.entity.MusicianEntity;
import com.example.quanlyamnhac.mapper.MusicianMapper;
import com.example.quanlyamnhac.mapper.SongMapper;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;
import com.example.quanlyamnhac.model.reponse.SongReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Song extends AppCompatActivity {

    SQLite sqLite;

    RecyclerView recycler_song;
    SongAdapter songAdapter;
    Toolbar toolbar;

    EditText et_songName, et_musicianName, et_singerName;

    public static Integer idSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        mapping();
        setRecyclerSong();
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

        recycler_song = findViewById(R.id.recycler_song);
        toolbar = findViewById(R.id.toolbar);

        et_songName = findViewById(R.id.et_songName);
        et_musicianName= findViewById(R.id.et_musicianName);
        et_singerName = findViewById(R.id.et_singerName);
    }

    private void setRecyclerSong() {
        recycler_song.setHasFixedSize(true);
        recycler_song.setLayoutManager(new LinearLayoutManager((this)));
        songAdapter = new SongAdapter(this,getList());
        recycler_song.setAdapter(songAdapter);
    }

    private void layDL() {

        Cursor cursor = sqLite.getData(" SELECT song.name, singer.name, musician.name " +
                " FROM song, singer, musician " +
                " WHERE song.id = " + Song.idSong + " AND singer.id = " + SingerDetail.idSinger+ " AND musician.id = " + MusicianDetail.idMusician);
        if(cursor.moveToNext()){
            et_songName.setText(cursor.getString(0));
            et_musicianName.setText(cursor.getString(2));
            et_singerName.setText(cursor.getString(1));
        }
    }

    private List<SongReponse> getList() {
        List<SongReponse> songReponses = new ArrayList<>();
        Cursor cursor = sqLite.getData(" SELECT singer.name, performance_info.performance_day, performance_info.place " +
                " FROM singer, performance_info, song " +
                " WHERE performance_info.singer_id = singer.id AND performance_info.song_id = song.id" +
                " AND song.id = " + Song.idSong + " AND singer.id = " + SingerDetail.idSinger);
        while (cursor.moveToNext()) {
            songReponses.add(SongMapper.toSongReponse(cursor));
        }
        return songReponses;
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