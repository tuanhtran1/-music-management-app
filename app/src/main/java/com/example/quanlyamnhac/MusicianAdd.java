package com.example.quanlyamnhac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MusicianAdd extends AppCompatActivity {

    EditText txtName ;
    EditText txtLinkImg;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musician_add);
        setControl();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MusicianAdd.this,"them",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setControl() {
        txtName = findViewById(R.id.txtNameMusician);
        txtLinkImg = findViewById(R.id.txtLinkImgAdd);
        btnThem = findViewById(R.id.btnThemMusician);
    }
}