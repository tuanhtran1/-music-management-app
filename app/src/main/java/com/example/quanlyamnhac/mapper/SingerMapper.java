package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.example.quanlyamnhac.model.reponse.SingerReponse;

public class SingerMapper {
    public static ItemSingerReponse toItemSingerReponse(Cursor cursor){
        ItemSingerReponse itemSingerReponse = new ItemSingerReponse();
        itemSingerReponse.setNameSinger(cursor.getString(1));
        itemSingerReponse.setImageSinger(cursor.getString(2));
        return itemSingerReponse;
    }

    public static SingerReponse toSingerReponse(Cursor cursor) {
        SingerReponse singerReponse = new SingerReponse();
        singerReponse.setSongName(cursor.getString(0));
        singerReponse.setMusicianName(cursor.getString(1));
        singerReponse.setYearOfCreation(cursor.getString(2));
        return singerReponse;
    }
}
