package com.example.quanlyamnhac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Register extends AppCompatActivity {
    EditText txtEmail,txtPhone, txtUsername, txtPass, txtConfirmPass, txtAvatar;
    Button btnDangKy;
    FloatingActionButton fbBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setControl();
        setEvent();
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
                if(txtEmail.getText().toString().equals("")){
                    Toast.makeText(Register.this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
                }
                else if(txtPhone.getText().toString().equals("")){
                    Toast.makeText(Register.this, "Vui lòng nhập Phone", Toast.LENGTH_SHORT).show();
                }
                else if(txtUsername.getText().toString().equals("")){
                    Toast.makeText(Register.this, "Vui lòng nhập Username", Toast.LENGTH_SHORT).show();
                }
                else if(txtPass.getText().toString().equals("")){
                    Toast.makeText(Register.this, "Vui lòng nhập Password", Toast.LENGTH_SHORT).show();
                }
                else if(!txtConfirmPass.getText().toString().equals(txtPass.getText().toString())){
                    Toast.makeText(Register.this, "Password xác nhận không khớp", Toast.LENGTH_SHORT).show();
                }
                else if(txtAvatar.getText().toString().equals("")){
                    Toast.makeText(Register.this, "Vui lòng nhập Avatar", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Register.this, LogIn.class);
                    intent.putExtra("username", txtUsername.getText().toString());
                    intent.putExtra("password", txtPass.getText().toString());
                    startActivity(intent);
                    Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setControl() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtUsername = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPass);
        txtConfirmPass = findViewById(R.id.txtConfirmPass);
        txtAvatar = findViewById(R.id.txtAvatar);
        btnDangKy = findViewById(R.id.btnDangKy);
        fbBack = findViewById(R.id.fbBack);
    }
}


