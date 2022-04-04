package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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

import com.example.quanlyamnhac.adapter.SongAdapter;
import com.example.quanlyamnhac.entity.MusicianEntity;
import com.example.quanlyamnhac.model.SongModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MusicianDetail extends AppCompatActivity {
    TextView tvId,tvFieldName, tvID, tvName, tvYear;
    ImageView ivImg;
    EditText txtName, txtLinkImg; // linkImg từ từ
    ImageButton btnThemBaiHat, btnXoa, btnSua;
    RecyclerView rvDanhSachBaiHat;
    SongAdapter songAdapter;
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

        tvID.setText("ID");
        tvName.setText("Tên bài hát");
        tvYear.setText("Năm sáng tác");

        //list Bai Hat
        rvDanhSachBaiHat.setHasFixedSize(true);
        rvDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(this));
        SongAdapter songAdapter= new SongAdapter(this, getList());
        rvDanhSachBaiHat.setAdapter(songAdapter);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("item",getDanhLam());
//                setResult(3,intent);
//                finish();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("item",getDanhLam());
//                setResult(2,intent);
//                finish();
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
                final EditText nameSong = dialog.findViewById(R.id.et_songName);
                final EditText yearOfCreation = dialog.findViewById(R.id.et_yearOfCreation);
                Button submitButton = dialog.findViewById(R.id.btn_submit);


                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MusicianDetail.this,"Da them bai hat",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }


    private List<SongModel> getList() {
        List<SongModel> songModels = new ArrayList<>();
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
//        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        return songModels;
    }

    private void layDL() {
        MusicianEntity musicianModel = (MusicianEntity) getIntent().getSerializableExtra("item");
        //tvId.setText(nhacSi.getId()); //Không cần thiết id
        txtName.setText(musicianModel.getName());
        Picasso.get().load(musicianModel.getLinkImg()).into(ivImg);
        tvFieldName.setText("Tên nhạc sĩ:");
    }
    //ánh xạ qua
    private void setControl() {
        txtName = findViewById(R.id.txtName);
        tvID = findViewById(R.id.tvID);
        tvName = findViewById(R.id.tvName);
        tvYear = findViewById(R.id.tvYear);
        ivImg = findViewById(R.id.ivImage);
        tvFieldName = findViewById(R.id.tvFieldName);
        rvDanhSachBaiHat = findViewById(R.id.rvListData);
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