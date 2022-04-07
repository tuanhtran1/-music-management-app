package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.model.reponse.HomeReponse;

public class HomeMapper {
    public static HomeReponse toHomeReponse(Cursor cursor){
        HomeReponse homeReponse = new HomeReponse();
        homeReponse.setSongName(cursor.getString(0));
        homeReponse.setMusicianName(cursor.getString(1));
        homeReponse.setSingerName(cursor.getString(2));
        homeReponse.setYearOfCreation(cursor.getString(3));
        return homeReponse;
    }
}
