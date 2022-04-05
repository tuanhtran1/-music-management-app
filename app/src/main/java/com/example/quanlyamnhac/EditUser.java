package com.example.quanlyamnhac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyamnhac.model.UserModel;
import com.squareup.picasso.Picasso;

public class EditUser extends AppCompatActivity {
    EditText txtEmail,txtPhone,txtUsername,txtPass,txtAvatar;
    ImageView ivAvatar;
    Button btnXoa,btnSua,btnHuy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        setControl();
        setEvent();

    }

    private void setEvent() {
        layDL();
        btnSua.setOnClickListener(new View.OnClickListener() {  // không xử lý hàm sửa ở đây mà phải chuyển qua hàm main
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("item",getUser()); // đưa dữ liệu lấy được từ getCountry vào item
                setResult(2,intent);    // set result code cho intent để khi gửi qua main phân biệt đc
                finish();   // finish giúp quay về trang trước
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("item",getUser());
                setResult(3,intent);
                finish();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(EditUser.this, DatabaseUserAdapter.class);
//                startActivity(intent);
                Toast.makeText(EditUser.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtUsername = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPass);
        txtAvatar = findViewById(R.id.txtAvatar);
        ivAvatar = findViewById(R.id.ivAvatar);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnHuy = findViewById(R.id.btnHuy);
    }

    private UserModel getUser(){
        UserModel user = new UserModel();
        user.setEmail(txtEmail.getText().toString());
        user.setPhone(txtPhone.getText().toString());
        user.setUsername(txtUsername.getText().toString());
        user.setPassword(txtPass.getText().toString());
        user.setAvatar(txtAvatar.getText().toString());
        return user;
    }

    private void layDL(){
        UserModel user = (UserModel) getIntent().getSerializableExtra("item");   //lay nguyen doi tuong(getSerializableExtra) country mà bên kia (adapter) gửi qua (bằng intent) (nhớ ép kiểu lại)
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtUsername.setText(user.getUsername());
        txtPass.setText(user.getPassword());
        txtAvatar.setText(user.getAvatar());
        //new DownloadImageTask(ivAvatar).execute(user.getAvatar());
        Picasso.get().load(user.getAvatar()).into(ivAvatar);
    }
}