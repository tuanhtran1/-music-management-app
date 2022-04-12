package com.example.quanlyamnhac;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyamnhac.adapter.SongOfPerformanceInfoAdapter;
import com.example.quanlyamnhac.mapper.SongMapper;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.example.quanlyamnhac.model.reponse.SongReponse;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.util.ArrayList;
import java.util.List;

public class SongOfPerformanceInfo extends AppCompatActivity {

    public static Integer idSong;
    ItemSingerReponse itemSingerReponse = new ItemSingerReponse();

    SQLite sqLite;

    RecyclerView recycler_song;
    SongOfPerformanceInfoAdapter songOfPerformanceInfoAdapter;
    Toolbar toolbar;

    TextView tv_songName, tv_musicianName, tv_singerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_of_performance_info);
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
        sqLite = new SQLite(this, "music-managerment.sqlite", null, 1);

        recycler_song = findViewById(R.id.recycler_song);
        toolbar = findViewById(R.id.toolbar);

        tv_songName = findViewById(R.id.tv_songName);
        tv_musicianName = findViewById(R.id.tv_musicianName);
        tv_singerName = findViewById(R.id.tv_singerName);
    }

    private void setRecyclerSong() {
        recycler_song.setHasFixedSize(true);
        recycler_song.setLayoutManager(new LinearLayoutManager((this)));
        songOfPerformanceInfoAdapter = new SongOfPerformanceInfoAdapter(this, getList());
        recycler_song.setAdapter(songOfPerformanceInfoAdapter);
    }

    private void layDL() {
        boolean check = getIntent().getBooleanExtra("toSongOfPerformanceInfo", false);
        if (check) {
            tv_songName.setText(getIntent().getStringExtra("songName"));
            tv_musicianName.setText(getIntent().getStringExtra("musicianName"));
            tv_singerName.setText(getIntent().getStringExtra("singerName"));
        } else {
            Cursor cursor = sqLite.getData(" SELECT song.name, singer.name, musician.name " +
                    " FROM song, singer, musician " +
                    " WHERE song.id = " + SongOfPerformanceInfo.idSong + " AND singer.id = " + SingerDetail.idSinger + " AND musician.id = " + MusicianDetail.idMusician);
            if (cursor.moveToNext()) {
                tv_songName.setText(cursor.getString(0));
                tv_musicianName.setText(cursor.getString(2));
                tv_singerName.setText(cursor.getString(1));
            }
        }
    }

    private List<SongReponse> getList() {
        List<SongReponse> songReponses = new ArrayList<>();
        Cursor cursor = sqLite.getData(" SELECT singer.name, performance_info.performance_day, performance_info.place " +
                " FROM singer, performance_info, song " +
                " WHERE performance_info.singer_id = singer.id AND performance_info.song_id = song.id" +
                " AND song.id = " + SongOfPerformanceInfo.idSong + " AND singer.id = " + SingerDetail.idSinger);
        while (cursor.moveToNext()) {
            songReponses.add(SongMapper.toSongReponse(cursor));
        }
        return songReponses;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.it_search:
                Toast.makeText(this, "Ban chon search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_home:
                Toast.makeText(this, "Ban chon home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_list_user:
                Toast.makeText(this, "Ban chon list user", Toast.LENGTH_SHORT).show();
                intent = new Intent(SongOfPerformanceInfo.this, User.class);
                startActivity(intent);
                break;
            case R.id.it_log_out:
                Toast.makeText(this, "Ban chon log out", Toast.LENGTH_SHORT).show();
                intent = new Intent(SongOfPerformanceInfo.this, LogIn.class);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SingerDetail.class);
        Cursor cursor = sqLite.getData(" SELECT singer.name, singer.image FROM singer WHERE singer.id =" + SingerDetail.idSinger);
        if (cursor.moveToNext()) {
            itemSingerReponse.setNameSinger(cursor.getString(0));
            itemSingerReponse.setImageSinger(cursor.getString(1));
        }
        intent.putExtra("toSongOfPerformanceInfo", itemSingerReponse);
        startActivity(intent);
    }
}