package com.example.quanlyamnhac;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    }

    private void setEvent() {

    }

    private void setControl() {
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
}
