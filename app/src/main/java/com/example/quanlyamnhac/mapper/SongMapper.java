package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.entity.SongEntity;
import com.example.quanlyamnhac.model.reponse.SongReponse;

public class SongMapper {
    public static SongReponse toSongReponse(Cursor cursor) {
        SongReponse songReponse = new SongReponse();
        songReponse.setSingerName(cursor.getString(0));
        songReponse.setPerformanceDay(cursor.getString(1));
        songReponse.setPlace(cursor.getString(2));
        return songReponse;
    }

    public static SongEntity toSongEntity(Cursor cursor) {
        SongEntity songEntity = new SongEntity();
        songEntity.setId(cursor.getInt(0));
        songEntity.setName(cursor.getString(1));
        songEntity.setYearCreation(cursor.getString(2));
        songEntity.setMusicianId(cursor.getInt(3));
        return songEntity;
    }
}
