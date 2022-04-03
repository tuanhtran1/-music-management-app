package com.example.quanlyamnhac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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
    Button btnXoa, btnSua;
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
    }

    private List<SongModel> getList() {
        List<SongModel> songModels = new ArrayList<>();
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
}