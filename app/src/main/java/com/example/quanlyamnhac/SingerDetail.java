package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyamnhac.adapter.SingerAdapter;
import com.example.quanlyamnhac.adapter.SongAdapter;
import com.example.quanlyamnhac.entity.SingerEntity;
import com.example.quanlyamnhac.fragment.MusicianFragment;
import com.example.quanlyamnhac.fragment.SingerFragment;
import com.example.quanlyamnhac.mapper.MusicianMapper;
import com.example.quanlyamnhac.mapper.SingerMapper;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;
import com.example.quanlyamnhac.model.reponse.SingerReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SingerDetail extends AppCompatActivity {

    public static Integer idSinger;

    SQLite sqLite;

    ImageView ivImg;
    TextView et_name;
    ImageButton btnXoa, btnSua, btnThemBaiHat;
    RecyclerView rvDanhSachBaiHat;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_detail);
        setControl();
        setEvent();
        setToolBar();
    }

    private void setEvent() {
        layDL(); //lấy dữ liệu từ layout cũ qua layout_edit

        //list Bai Hat
        rvDanhSachBaiHat.setHasFixedSize(true);
        rvDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(this));
        SingerAdapter singerAdapter= new SingerAdapter(this, getList());
        rvDanhSachBaiHat.setAdapter(singerAdapter);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = sqLite.getData("SELECT singer.id FROM singer WHERE EXISTS " +
                        " (SELECT performance_info.singer_id FROM performance_info WHERE performance_info.singer_id = " + SingerDetail.idSinger + ")");
                if(cursor.moveToNext()){
                    Toast.makeText(view.getContext(),"Hay chac chan vi da co  ",Toast.LENGTH_SHORT).show();
                    return;
                }
                sqLite.queryData("DELETE FROM singer WHERE singer.id = " + SingerDetail.idSinger);
                Toast.makeText(view.getContext(),"Deleting...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), SingerFragment.class);
                startActivity(intent);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_singer_dialog);

                //Initializing the views of the dialog.
                final EditText singerName = dialog.findViewById(R.id.et_singerName);
                final EditText linkImageSinger = dialog.findViewById(R.id.et_linkImageSinger);
                Button submitButton = dialog.findViewById(R.id.btn_submit);

                ItemSingerReponse singerModel = (ItemSingerReponse) getIntent().getSerializableExtra("item");
                String name = singerModel.getNameSinger(), image = singerModel.getImageSinger() ;
                singerName.setText(name);
                linkImageSinger.setText(image);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sqLite.queryData("UPDATE singer SET name = '" + singerName.getText() + "', image = '" + linkImageSinger.getText()+ "'" +
                                " WHERE musician.id = " + MusicianDetail.idMusician);
                        dialog.dismiss();
                        Intent intent = new Intent(view.getContext(), SingerFragment.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });

        btnThemBaiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final Dialog dialog = new Dialog(SingerDetail.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setCancelable(true);
//                dialog.setContentView(R.layout.add_song_dialog);
//
//                //Initializing the views of the dialog.
//                final TextView tv_musicianName = dialog.findViewById(R.id.tv_musicianName);
//                final EditText et_songName = dialog.findViewById(R.id.et_songName);
//                final EditText et_yearOfCreation = dialog.findViewById(R.id.et_yearOfCreation);
//                Button btn_submit = dialog.findViewById(R.id.btn_submit);
//
//                ItemSingerReponse musicianModel = (ItemSingerReponse) getIntent().getSerializableExtra("item");
//                tv_musicianName.setText(musicianModel.getNameMusician());
//
//                btn_submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        sqLite.queryData("INSERT INTO song VALUES(null, '" + et_songName.getText() + "','" + et_yearOfCreation.getText() + "')");
//                        dialog.dismiss();
//                        setEvent();
//                    }
//                });
//
//                dialog.show();
            }
        });
    }

    private ArrayList<SingerReponse> getList() {
        Cursor cursor = sqLite.getData(" SELECT DISTINCT song.name, musician.name, song.yearofcreation " +
                " FROM song, musician, singer, performance_info " +
                " WHERE performance_info.singer_id = singer.id AND performance_info.song_id = song.id" +
                " AND song.id_musician = musician.id AND singer.id = " + SingerDetail.idSinger);
        ArrayList<SingerReponse> singerReponses = new ArrayList<>();
        while (cursor.moveToNext()) {
            singerReponses.add(SingerMapper.toSingerReponse(cursor));
        }
        return singerReponses;
    }

    private void layDL() {
        ItemSingerReponse singerModel = (ItemSingerReponse) getIntent().getSerializableExtra("item");
        et_name.setText(singerModel.getNameSinger());
        Picasso.get().load(singerModel.getImageSinger()).into(ivImg);
    }

    //ánh xạ qua
    private void setControl() {
        sqLite = new SQLite(this, "music-managerment.sqlite", null, 1);

        et_name = findViewById(R.id.et_name);
        ivImg = findViewById(R.id.ivImage);
        rvDanhSachBaiHat = findViewById(R.id.rvListDataSinger);
        toolbar = findViewById(R.id.toolbar);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnThemBaiHat = findViewById(R.id.btnThem);
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
        getMenuInflater().inflate(R.menu.menu, menu);
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