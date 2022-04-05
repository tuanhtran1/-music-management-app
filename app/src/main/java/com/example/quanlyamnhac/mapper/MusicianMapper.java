package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.entity.MusicianEntity;

public class MusicianMapper {
    public static MusicianEntity convertToModel(Cursor cursor){
        MusicianEntity musicianModel = new MusicianEntity();
        musicianModel.setId(cursor.getLong(1));
        musicianModel.setName(cursor.getString(2));
        musicianModel.setLinkImg(cursor.getString(3));
        return musicianModel;
    }
}
