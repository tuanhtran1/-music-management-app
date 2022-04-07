package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.example.quanlyamnhac.model.reponse.SongReponse;

public class SongMapper {
    public static SongReponse toSongReponse(Cursor cursor){
        SongReponse songReponse = new SongReponse();
        songReponse.setSingerName(cursor.getString(0));
        songReponse.setPerformanceDay(cursor.getString(1));
        songReponse.setPlace(cursor.getString(2));
        return songReponse;
    }
}
