package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyamnhac.entity.SongEntity;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;
import com.example.quanlyamnhac.sqlite.SQLite;

public class SongDetail extends AppCompatActivity {

    public static Integer idSong;
    ItemMusicianReponse itemMusicianReponse = new ItemMusicianReponse();

    SQLite sqLite;
    Toolbar toolbar;

    TextView tv_musicianName;
    EditText et_songName, et_yearOfCreation;
    ImageButton ib_save, ib_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        mapping();
        setToolBar();
        layDL();

        ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //putExtra qua MusicianDetail
                Cursor cursor1 = sqLite.getData("SELECT musician.name, musician.image FROM song, musician " +
                        "WHERE song.id_musician = musician.id " +
                        "AND song.id = " + SongDetail.idSong);
                if(cursor1.moveToNext())
                {
                    itemMusicianReponse.setNameMusician(cursor1.getString(0));
                    itemMusicianReponse.setImageMusician(cursor1.getString(1));
                }
                // Thực hiện xóa
                Cursor cursor = sqLite.getData("SELECT song.id FROM song WHERE EXISTS " +
                        " (SELECT performance_info.song_id FROM performance_info WHERE performance_info.song_id = " + SongDetail.idSong + ")");
                if(cursor.moveToNext()){
                    Toast.makeText(view.getContext(),"Hay chac chan vi da trinh dien",Toast.LENGTH_SHORT).show();
                    return;
                }
                sqLite.queryData("DELETE FROM song WHERE song.id = " + SongDetail.idSong);
                Toast.makeText(view.getContext(),"Deleting...",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), MusicianDetail.class); //MusicianDetail
                intent.putExtra("ItemFromSongDetail", itemMusicianReponse);
                startActivity(intent);
            }
        });

        ib_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLite.queryData("UPDATE song SET name = '" + et_songName.getText() + "', yearofcreation = '" + et_yearOfCreation.getText()+ "'" +
                        " WHERE song.id = " + SongDetail.idSong);
                Toast.makeText(view.getContext(),"Update sucess",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), MainTabActivity.class);
                intent.putExtra("cTab", "MusicianTab");
                startActivity(intent);
            }
        });
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

        ib_save = findViewById(R.id.ib_save);
        ib_delete = findViewById(R.id.ib_delete);
    }

    private void layDL() {
        SongEntity songEntity = (SongEntity) getIntent().getSerializableExtra("songEntity");
        et_songName.setText(songEntity.getName());
        et_yearOfCreation.setText(songEntity.getYearCreation());
        Cursor cursor = sqLite.getData(" SELECT musician.name FROM musician " +
                " WHERE musician.id = " + MusicianDetail.idMusician);
        if(cursor.moveToNext()){
            tv_musicianName.setText(cursor.getString(0));
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
                Intent intent = new Intent(SongDetail.this, LogIn.class);
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