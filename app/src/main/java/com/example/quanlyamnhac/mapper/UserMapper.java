package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.model.UserModel;

public class UserMapper {
    public static UserModel convertToModel(Cursor cursor){
        UserModel userModel = new UserModel();
        userModel.setEmail(cursor.getString(1)); //lay data tu cot 1: email
        userModel.setPhone(cursor.getString(2));
        userModel.setUsername(cursor.getString(3));
        userModel.setPassword(cursor.getString(4));
        userModel.setAvatar(cursor.getString(5));
        return userModel;
    }
}
