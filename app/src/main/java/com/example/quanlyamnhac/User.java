package com.example.quanlyamnhac;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quanlyamnhac.adapter.UserAdapter;
import com.example.quanlyamnhac.model.UserModel;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.util.ArrayList;

public class User extends AppCompatActivity {
    // tạo database và đổ dữ liệu vào
    SQLite sqLite;
    ListView lvUser;
    ArrayList<UserModel> arrayUser;
    UserAdapter adapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        lvUser = (ListView) findViewById(R.id.lvUser);
        arrayUser = new ArrayList<>();

        adapter = new UserAdapter(this, R.layout.item_user_layout, arrayUser);
        lvUser.setAdapter(adapter);

        sqLite = new SQLite(this,"music-managerment.sqlite", null, 1);
//        String pathdb = getDatabasePath("music-managerment.sqlite").getPath();
//        sqLite = SQLiteDatabase.openDatabase(pathdb, null, SQLiteDatabase.OPEN_READWRITE);

        GetDataUser();

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

    }

    private void setControl() {
        toolbar = findViewById(R.id.toolbar);
        lvUser = findViewById(R.id.lvUser);
    }

    public void GetDataUser(){
        Cursor dataUser = sqLite.getData("SELECT * FROM User ");
        arrayUser.clear();
        while (dataUser.moveToNext()){
            int id = dataUser.getInt(0);
            String email = dataUser.getString(1); //lay data tu cot 1: email
            String phone = dataUser.getString(2);
            String username = dataUser.getString(3);
            String password = dataUser.getString(4);
            String avatar = dataUser.getString(5);
            arrayUser.add(new UserModel(id,email,phone,username,password,avatar)); //list
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
          // lấy dữ liệu được gửi từ edit đưa sang
        if(requestCode == 2) {

            if (resultCode == 1) {
                UserModel user = (UserModel) data.getSerializableExtra("item");
                editUser(user);
                Toast.makeText(this, "Đã sửa", Toast.LENGTH_SHORT).show();
            } else if (resultCode == 2) {
                UserModel user = (UserModel) data.getSerializableExtra("item");
                deleteUser(user);
                Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show();
            }
        }else{
            finish();
        }
    }

    public void editUser(UserModel user){
        sqLite.queryData("UPDATE User SET email = '"+user.getEmail()+"', phone = '"+user.getPhone()+"', username = '"+user.getUsername()+"', password = '"+user.getPassword()+"', avatar = '"+user.getAvatar()+"' WHERE id = '"+user.getId()+"'");
        GetDataUser();
    }

    public void deleteUser(UserModel user){
        sqLite.queryData("DELETE FROM User WHERE id = '"+user.getId()+"' ");
        arrayUser.remove(user);
        GetDataUser();  //cập nhật lại DL
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
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
                intent = new Intent(User.this, User.class);
                startActivity(intent);
                break;
            case R.id.it_log_out:
                Toast.makeText(this, "Ban chon log out", Toast.LENGTH_SHORT).show();
                intent = new Intent(User.this, LogIn.class);
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
