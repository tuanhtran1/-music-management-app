package com.example.quanlyamnhac;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyamnhac.adapter.SingerAdapter;
import com.example.quanlyamnhac.mapper.SingerMapper;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
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


    Integer idSong;

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
        SingerAdapter singerAdapter = new SingerAdapter(this, getList());
        rvDanhSachBaiHat.setAdapter(singerAdapter);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = sqLite.getData("SELECT singer.id FROM singer WHERE EXISTS " +
                        " (SELECT performance_info.singer_id FROM performance_info WHERE performance_info.singer_id = " + SingerDetail.idSinger + ")");
                if (cursor.moveToNext()) {
                    Toast.makeText(view.getContext(), "Hay chac chan vi da co  ", Toast.LENGTH_SHORT).show();
                    return;
                }
                sqLite.queryData("DELETE FROM singer WHERE singer.id = " + SingerDetail.idSinger);
                Toast.makeText(view.getContext(), "Deleting...", Toast.LENGTH_SHORT).show();


//                // Find the view pager that will allow the user to swipe between fragments
//                ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
//                viewPager.setCurrentItem(2);
//
                Intent intent = new Intent(view.getContext(), MainTabActivity.class);
                intent.putExtra("cTab", "SingerTab");
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
                String name = singerModel.getNameSinger(), image = singerModel.getImageSinger();
                singerName.setText(name);
                linkImageSinger.setText(image);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sqLite.queryData("UPDATE singer SET name = '" + singerName.getText() + "', image = '" + linkImageSinger.getText() + "'" +
                                " WHERE singer.id = " + SingerDetail.idSinger);
                        dialog.dismiss();
                        Intent intent = new Intent(view.getContext(), MainTabActivity.class);
                        intent.putExtra("cTab", "SingerTab");
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });
        btnThemBaiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SingerDetail.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_performance_info_dialog);

                final Spinner sp_song = dialog.findViewById(R.id.sp_song);
                Cursor cursor = sqLite.getData(" SELECT song.id, song.name FROM song");
                List<Integer> listIdSong = new ArrayList<>();
                List<String> listNameSong = new ArrayList<>();
                while (cursor.moveToNext()) {
                    listIdSong.add(cursor.getInt(0));
                    listNameSong.add(cursor.getString(1));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter(SingerDetail.this, android.R.layout.simple_list_item_1, listNameSong);
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                sp_song.setAdapter(adapter);

                sp_song.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(SingerDetail.this, listIdSong.get(i) + "", Toast.LENGTH_SHORT).show();
                        idSong = listIdSong.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                final TextView tv_singer_name = dialog.findViewById(R.id.tv_singer_name);
                cursor = sqLite.getData(" SELECT singer.name FROM singer WHERE singer.id = " +
                        SingerDetail.idSinger);
                if (cursor.moveToNext()) {
                    tv_singer_name.setText(cursor.getString(0));
                }
                final EditText et_performance_day = dialog.findViewById(R.id.et_performance_day);
                final EditText et_place = dialog.findViewById(R.id.et_place);
                Button btn_submit = dialog.findViewById(R.id.btn_submit);

                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sqLite.queryData("INSERT INTO performance_info VALUES" +
                                "(null, " + SingerDetail.idSinger + "," + idSong + ",'" + et_performance_day.getText()
                                + "','" + et_place.getText() + "')");
                        dialog.dismiss();
                        setEvent();
                    }
                });
                dialog.show();
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
        //2 luong
        try {
            ItemSingerReponse singerModel = (ItemSingerReponse) getIntent().getSerializableExtra("toSongOfPerformanceInfo");
            et_name.setText(singerModel.getNameSinger());
            Picasso.get().load(singerModel.getImageSinger()).into(ivImg);
        } catch (Exception e) {
            ItemSingerReponse singerModel = (ItemSingerReponse) getIntent().getSerializableExtra("item");
            et_name.setText(singerModel.getNameSinger());
            Picasso.get().load(singerModel.getImageSinger()).into(ivImg);
        }
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
                intent = new Intent(SingerDetail.this, User.class);
                startActivity(intent);
                break;
            case R.id.it_log_out:
                Toast.makeText(this, "Ban chon log out", Toast.LENGTH_SHORT).show();
                intent = new Intent(SingerDetail.this, LogIn.class);
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
        Intent intent = new Intent(this, MainTabActivity.class);
        intent.putExtra("cTab", "SingerTab");
        startActivity(intent);
    }
}