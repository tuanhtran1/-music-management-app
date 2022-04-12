package com.example.quanlyamnhac.mapper;

import android.database.Cursor;

import com.example.quanlyamnhac.model.reponse.PerformanceInfoReponse;

import java.util.Date;

public class PerformanceInfoMapper {
    public static PerformanceInfoReponse toPerformanceInfoReponse(Cursor cursor) {
        PerformanceInfoReponse performanceInfoReponse = new PerformanceInfoReponse();
        performanceInfoReponse.setSongName(cursor.getString(0));
        performanceInfoReponse.setSingerName(cursor.getString(1));
        performanceInfoReponse.setDate(new Date(cursor.getString(2)));
        performanceInfoReponse.setPlace(cursor.getString(3));
        return performanceInfoReponse;
    }
}
