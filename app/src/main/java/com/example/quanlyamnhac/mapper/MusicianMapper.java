package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.Musician;
import com.example.quanlyamnhac.model.MusicianModel;

import java.util.ArrayList;
import java.util.List;

public class MusicianMapper {
    public static MusicianModel convertToModel(Cursor cursor){
        MusicianModel musicianModel = new MusicianModel();
        musicianModel.setId(cursor.getString(1));
        musicianModel.setName(cursor.getString(2));
        musicianModel.setLinkImg(cursor.getString(3));
        return musicianModel;
    }
}
