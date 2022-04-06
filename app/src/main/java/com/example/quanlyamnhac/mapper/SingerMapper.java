package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;

public class SingerMapper {
    public static ItemSingerReponse toItemSingerReponse(Cursor cursor){
        ItemSingerReponse itemSingerReponse = new ItemSingerReponse();
        itemSingerReponse.setNameSinger(cursor.getString(1));
        itemSingerReponse.setImageSinger(cursor.getString(2));
        return itemSingerReponse;
    }
}
