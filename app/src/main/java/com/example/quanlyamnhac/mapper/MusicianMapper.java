package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.entity.MusicianEntity;
import com.example.quanlyamnhac.model.reponse.HomeReponse;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;

public class MusicianMapper {
    public static ItemMusicianReponse toItemMusicianReponse(Cursor cursor){
        ItemMusicianReponse itemMusicianReponse = new ItemMusicianReponse();
        itemMusicianReponse.setNameMusician(cursor.getString(1));
        itemMusicianReponse.setImageMusician(cursor.getString(2));
        return itemMusicianReponse;
    }

    public static MusicianEntity toMusicianEntity(Cursor cursor){
        MusicianEntity musicianEntity = new MusicianEntity();
        musicianEntity.setId(cursor.getInt(0));
        musicianEntity.setName(cursor.getString(1));
        musicianEntity.setLinkImg(cursor.getString(2));
        return musicianEntity;
    }

    public static MusicianReponse toMusicianReponse(Cursor cursor){
        MusicianReponse musicianReponse = new MusicianReponse();
        musicianReponse.setSongName(cursor.getString(0));
        musicianReponse.setYearOfCreation(cursor.getString(1));
        musicianReponse.setSingerName(cursor.getString(2));
        return musicianReponse;
    }
}
