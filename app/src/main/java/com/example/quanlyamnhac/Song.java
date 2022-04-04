package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlyamnhac.adapter.SongAdapter;
import com.example.quanlyamnhac.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class Song extends AppCompatActivity {

    RecyclerView recycler_song;
    SongAdapter songAdapter;
    Toolbar toolbar;

    EditText et_songName, et_musicianName, et_yearOfCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        mapping();
        setRecyclerSong();
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
        recycler_song = findViewById(R.id.recycler_song);
        toolbar = findViewById(R.id.toolbar);

        et_songName = findViewById(R.id.et_songName);
        et_musicianName= findViewById(R.id.et_musicianName);
        et_yearOfCreation = findViewById(R.id.et_yearOfCreation);
    }

    private void setRecyclerSong() {
        recycler_song.setHasFixedSize(true);
        recycler_song.setLayoutManager(new LinearLayoutManager((this)));
        songAdapter = new SongAdapter(this,getList());
        recycler_song.setAdapter(songAdapter);
    }

    private List<SongModel> getList() {
        List<SongModel> songModels = new ArrayList<>();
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        songModels.add(new SongModel("Trần Anh Tú", "4/1/2022", "97 Man Thiện"));
        return songModels;
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