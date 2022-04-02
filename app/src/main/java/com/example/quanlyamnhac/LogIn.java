package com.example.quanlyamnhac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LogIn extends AppCompatActivity {
    EditText txtUser,txtPass;
    Button btnXacNhan;
    FloatingActionButton fbDangKy, fbThoat, fbBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setControl();
        setEvent();
    }

    private void setEvent() {

        khoiTao();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUser.getText().toString().equals("")){
                    Toast.makeText(LogIn.this, "Vui lòng nhập Username", Toast.LENGTH_SHORT).show();
                }
                else if(txtPass.getText().toString().equals("")){
                    Toast.makeText(LogIn.this, "Vui lòng nhập Password", Toast.LENGTH_SHORT).show();
                }
                else if(!txtUser.getText().toString().equals("admin") || !txtPass.getText().toString().equals("123")){
                    Intent intent = new Intent(LogIn.this,Register.class);
                    Toast.makeText(LogIn.this, "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                }
                else if(txtUser.getText().toString().equals("admin") && txtPass.getText().toString().equals("123")){
                    // cho nay de chuyen sang home
//                    Intent intent = new Intent(DangNhap.this,DangKy.class);
//                    startActivity(intent);
                    Toast.makeText(LogIn.this, "HOME", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogIn.this, Home.class);
                    startActivity(intent);
                }
            }
        });

        fbDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this,Register.class);
                startActivity(intent);
            }
        });

        fbThoat.setOnClickListener(new View.OnClickListener() {
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

    private void ganDL(){


    }

    private void setControl() {
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        fbDangKy = findViewById(R.id.fbDangKy);
        fbThoat = findViewById(R.id.fbThoat);
        fbBack = findViewById(R.id.fbBack);
    }
}