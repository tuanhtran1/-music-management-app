package com.example.quanlyamnhac;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyamnhac.adapter.MusicianAdapter;
import com.example.quanlyamnhac.fragment.MusicianFragment;
import com.example.quanlyamnhac.mapper.MusicianMapper;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MusicianDetail extends AppCompatActivity {

    public static Integer idMusician;
    ItemMusicianReponse musicianModel = new ItemMusicianReponse();
    SQLite sqLite;

    ImageView ivImg;
    TextView et_name;
    ImageButton btnXoa, btnSua, btnThemBaiHat;
    RecyclerView rvDanhSachBaiHat;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_detail);
        setControl();
        setEvent();
        setToolBar();
    }

    private void setEvent() {
        layDL(); //lấy dữ liệu từ layout cũ qua layout_edit

        //list Bai Hat
        rvDanhSachBaiHat.setHasFixedSize(true);
        rvDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(this));
        MusicianAdapter musicianAdapter = new MusicianAdapter(this, getList());
        rvDanhSachBaiHat.setAdapter(musicianAdapter);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Cursor cursor = sqLite.getData("SELECT musician.id FROM musician WHERE EXISTS " +
                            " (SELECT song.id_musician FROM song WHERE song.id_musician = " + MusicianDetail.idMusician + ")");
                    if(cursor.moveToNext()){
                        Toast.makeText(view.getContext(),"Hay chac chan vi da co bai hat",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    sqLite.queryData("DELETE FROM musician WHERE musician.id = " + MusicianDetail.idMusician);
                    Toast.makeText(view.getContext(),"Deleting...",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(view.getContext(), MainTabActivity.class); // MainActivity
                    intent.putExtra("cTab", "MusicianTab");
                    startActivity(intent);

            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_musician_dialog);

                //Initializing the views of the dialog.
                final EditText musicianName = dialog.findViewById(R.id.et_musicianName);
                final EditText linkImageMusician = dialog.findViewById(R.id.et_linkImageMusician);
                Button submitButton = dialog.findViewById(R.id.btn_submit);

                ItemMusicianReponse musicianModel = (ItemMusicianReponse) getIntent().getSerializableExtra("item");
                String name = musicianModel.getNameMusician(), image =musicianModel.getImageMusician() ;
                musicianName.setText(name);
                linkImageMusician.setText(image);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sqLite.queryData("UPDATE musician SET name = '" + musicianName.getText() + "', image = '" + linkImageMusician.getText()+ "'" +
                                " WHERE musician.id = " + MusicianDetail.idMusician);
                        dialog.dismiss();
                        Intent intent = new Intent(view.getContext(), MainTabActivity.class);
                        intent.putExtra("cTab", "MusicianTab");
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });
        btnThemBaiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MusicianDetail.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_song_dialog);

                //Initializing the views of the dialog.
                final TextView tv_musicianName = dialog.findViewById(R.id.et_musicianName);
                final EditText et_songName = dialog.findViewById(R.id.et_songName);
                final EditText et_yearOfCreation = dialog.findViewById(R.id.et_yearOfCreation);
                Button btn_submit = dialog.findViewById(R.id.btn_submit);

                ItemMusicianReponse musicianModel = (ItemMusicianReponse) getIntent().getSerializableExtra("item");
                tv_musicianName.setText(musicianModel.getNameMusician());

                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sqLite.queryData("INSERT INTO song VALUES(null, '" + et_songName.getText() + "','" + et_yearOfCreation.getText() + "'," + MusicianDetail.idMusician + ")");
                        dialog.dismiss();
                        setEvent();
                    }
                });

                dialog.show();
            }
        });
    }
    private ArrayList<MusicianReponse> getList() {
        Cursor cursor = sqLite.getData(" SELECT song.name, song.yearofcreation " +
                " FROM song WHERE song.id_musician = " + MusicianDetail.idMusician);
        ArrayList<MusicianReponse> musicianReponses = new ArrayList<>();
        while (cursor.moveToNext()) {
            musicianReponses.add(MusicianMapper.toMusicianReponse(cursor));
        }
        return musicianReponses;
    }

    private void layDL() {
        //ý tưởng chia 2 luồng
        try {
            musicianModel = (ItemMusicianReponse) getIntent().getSerializableExtra("ItemFromSongDetail");
            et_name.setText(musicianModel.getNameMusician());
            Picasso.get().load(musicianModel.getImageMusician()).into(ivImg);
        }
        catch (Exception e)
        {
            musicianModel = (ItemMusicianReponse) getIntent().getSerializableExtra("item");
            et_name.setText(musicianModel.getNameMusician());
            Picasso.get().load(musicianModel.getImageMusician()).into(ivImg);
        }
    }
    //ánh xạ qua
    private void setControl() {

        sqLite = new SQLite(this, "music-managerment.sqlite", null, 1);

        et_name = findViewById(R.id.et_name);
        ivImg = findViewById(R.id.ivImage);
        rvDanhSachBaiHat = findViewById(R.id.rvListDataMusician);
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
                Intent intent = new Intent(MusicianDetail.this, LogIn.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}






