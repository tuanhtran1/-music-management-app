package com.example.quanlyamnhac;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quanlyamnhac.model.UserModel;
import com.squareup.picasso.Picasso;

public class EditUser extends AppCompatActivity {
    TextView tvID;
    EditText txtEmail, txtPhone, txtUsername, txtPass, txtAvatar;
    ImageView ivAvatar;
    Button btnXoa, btnSua, btnHuy;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        setControl();
        setEvent();
        setToolBar();
    }

    private void setToolBar() {
        //Set toolbar as action bar
        setSupportActionBar(toolbar);
        //Enable Home icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Toolbar title
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void setEvent() {
        layDL();
        btnSua.setOnClickListener(new View.OnClickListener() {  // không xử lý hàm sửa ở đây mà phải chuyển qua hàm main
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("item", getUser()); // đưa dữ liệu lấy được từ getCountry vào item
                setResult(1, intent);    // set result code cho intent để khi gửi qua main phân biệt đc
                finish();   // finish giúp quay về trang trước
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("item", getUser());
                setResult(2, intent);
                finish();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(EditUser.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        toolbar = findViewById(R.id.toolbar);
        tvID = findViewById(R.id.tvID);
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

    private UserModel getUser() {
        UserModel user = new UserModel();
        user.setId(Integer.parseInt((tvID.getText().toString())));
        user.setEmail(txtEmail.getText().toString());
        user.setPhone(txtPhone.getText().toString());
        user.setUsername(txtUsername.getText().toString());
        user.setPassword(txtPass.getText().toString());
        user.setAvatar(txtAvatar.getText().toString());
        return user;
    }


    private void layDL(){
        UserModel user = (UserModel) getIntent().getSerializableExtra("item");   //lay nguyen doi tuong(getSerializableExtra) user mà bên kia (adapter) gửi qua (bằng intent) (nhớ ép kiểu lại)
        tvID.setText( String.valueOf(user.getId()));

        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtUsername.setText(user.getUsername());
        txtPass.setText(user.getPassword());
        txtAvatar.setText(user.getAvatar());
        Picasso.get().load(user.getAvatar()).into(ivAvatar);
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
                intent = new Intent(EditUser.this, User.class);
                startActivity(intent);
                break;
            case R.id.it_log_out:
                Toast.makeText(this, "Ban chon log out", Toast.LENGTH_SHORT).show();
                intent = new Intent(EditUser.this, LogIn.class);
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
}
