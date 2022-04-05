package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyamnhac.adapter.SongAdapter;
import com.example.quanlyamnhac.model.MusicianModel;
import com.example.quanlyamnhac.model.SongModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MusicianDetail extends AppCompatActivity {
    TextView tvId,tvFieldName, tvID, tvName, tvYear;
    ImageView ivImg;
    EditText txtName, txtLinkImg; // linkImg từ từ
    ImageButton btnXoa, btnSua;
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

    }

    private List<SongModel> getList() {
        List<SongModel> songModels = new ArrayList<>();
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        songModels.add(new SongModel("1","Hoa Nở Không Màu","2020","1"));
        return songModels;
    }

    private void layDL() {
        MusicianModel musicianModel = (MusicianModel) getIntent().getSerializableExtra("item");
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
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
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
}