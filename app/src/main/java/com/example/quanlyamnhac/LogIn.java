package com.example.quanlyamnhac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlyamnhac.mapper.UserMapper;
import com.example.quanlyamnhac.model.UserModel;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LogIn extends AppCompatActivity {

    SQLite sqLite;

    EditText txtUser,txtPass;
    Button btnXacNhan;
    FloatingActionButton fbDangKy, fbBack, fbQuenMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setDatabase();
        setControl();
        setEvent();
    }

    private void setDatabase() {
        sqLite = new SQLite(this,"music-managerment.sqlite", null, 1);
        sqLite.queryData("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email VARCHAR(50), phone VARCHAR(50), username VARCHAR(50), password VARCHAR(50), " +
                "avatar VARCHAR(50))");

        // musician
        sqLite.queryData("CREATE TABLE IF NOT EXISTS musician(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(50), image VARCHAR(50))");
        // song
        sqLite.queryData("CREATE TABLE IF NOT EXISTS song(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(50), yearofcreation VARCHAR(50), id_musician INTEGER," +
                "FOREIGN KEY (id_musician) REFERENCES musician(id))");

        // singer
        sqLite.queryData("CREATE TABLE IF NOT EXISTS singer(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(50), image VARCHAR(50))");

        // performance_info
        sqLite.queryData("CREATE TABLE IF NOT EXISTS performance_info(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "singer_id INTEGER , song_id INTEGER, username VARCHAR(50), performance_day VARCHAR(50), " +
                "place VARCHAR(50)," +
                "FOREIGN KEY (singer_id) REFERENCES singer(id), FOREIGN KEY (song_id) REFERENCES song(id))");

        //sqLite.queryData("INSERT INTO song VALUES(null,'canh','2022',1)");

        //sqLite.queryData("INSERT INTO User VALUES(null,'admin@gmail','0987654321','admin','123','https://haycafe.vn/wp-content/uploads/2021/11/Anh-avatar-dep-chat-lam-hinh-dai-dien.jpg')");
        //sqLite.queryData("INSERT INTO User VALUES(null,'hoang@gmail','0123456789','hoang','321','https://thuthuatnhanh.com/wp-content/uploads/2021/02/Anh-avatar-bua-cute-dep-390x390.jpg')");
    }

    private void setControl() {
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        fbDangKy = findViewById(R.id.fbDangKy);
        fbBack = findViewById(R.id.fbBack);
        fbQuenMK = findViewById(R.id.fbQuenMK);
    }

    private void setEvent() {

        khoiTao();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = txtUser.getText().toString();
                String password = txtPass.getText().toString();
//                if(userName.equals("") || password.equals("")){
//                    Toast.makeText(LogIn.this, "Vui lòng nhập đủ username và password", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Cursor cursor = sqLite.getData("SELECT * FROM user");
//                    List<UserModel> userModels = new ArrayList<>();
//                    while(cursor.moveToNext()){
//                        if(cursor.getString(3).equals(userName) && cursor.getString(4).equals(password)) {
                            Intent intent = new Intent(LogIn.this, MainTabActivity.class);
                            startActivity(intent);
                            Toast.makeText(LogIn.this, "MAIN TAB", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    }
//                    for(UserModel x: userModels){
//                        System.out.println(x.getUsername());
//                    }
//                    Toast.makeText(LogIn.this, "HOME", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LogIn.this, Home.class);
//                    startActivity(intent);
//                }
            }
        });

        fbDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
            }
        });

        fbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

    }

    private void khoiTao() {
//        User user1 = new User("admin","123","admin@gmail.com","0987654321","https://haycafe.vn/wp-content/uploads/2021/11/Anh-avatar-dep-chat-lam-hinh-dai-dien.jpg");
        txtUser.setText(getIntent().getStringExtra("username"));
        txtPass.setText(getIntent().getStringExtra("password"));
    }
}