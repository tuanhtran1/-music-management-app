package com.example.quanlyamnhac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyamnhac.sqlite.SQLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Register extends AppCompatActivity {

    SQLite sqLite;

    EditText txtEmail,txtPhone, txtUsername, txtPass, txtConfirmPass, txtAvatar;
    Button btnDangKy;
    FloatingActionButton fbBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setControl();
        setEvent();
    }

    private void setControl() {

        sqLite = new SQLite(this,"music-managerment.sqlite", null, 1);

        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtUsername = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPass);
        txtConfirmPass = findViewById(R.id.txtConfirmPass);
        txtAvatar = findViewById(R.id.txtAvatar);
        btnDangKy = findViewById(R.id.btnDangKy);
        fbBack = findViewById(R.id.fbBack);
    }

    private void setEvent() {
        fbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LogIn.class);
                startActivity(intent);
                Toast.makeText(Register.this, "BACK", Toast.LENGTH_SHORT).show();
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String phone = txtPhone.getText().toString();
                String userName = txtUsername.getText().toString();
                String password = txtPass.getText().toString();
                String confirmPass = txtConfirmPass.getText().toString();
                String avatar = txtAvatar.getText().toString();
                if(userName.equals("") || password.equals("") || confirmPass.equals("")){
                    Toast.makeText(Register.this, "Phải có đầy đủ thông tin user name và password", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(confirmPass)){
                    Toast.makeText(Register.this, "Password không khớp", Toast.LENGTH_SHORT).show();
                }
                else {
                    String sql= "INSERT INTO User VALUES(null,'"+email+"','"+phone+"','"+userName+"','"+password+"','"+avatar+"')";
                    sqLite.queryData(sql);
                    Intent intent = new Intent(Register.this, LogIn.class);
                    intent.putExtra("username", txtUsername.getText().toString());
                    intent.putExtra("password", txtPass.getText().toString());
                    startActivity(intent);
                    Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}





