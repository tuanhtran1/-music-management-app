package com.example.quanlyamnhac.sqline;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLine extends SQLiteOpenHelper {
    public SQLine(@Nullable Context context) {
        super(context, "music-managerment", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create Table musician (id text, name text, image text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // cursor = resultset
    public Cursor excuteTable(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void excuteUpdate(String sql, String array[]){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql, array);
        database.close();
    }

//    public void ThemDL(MonHoc monHoc) {
//        String sql = "insert into tbMonhoc values(?,?,?)";
//        SQLiteDatabase database = getWritableDatabase();
//        database.execSQL(sql, new String[]{monHoc.getMaMH(), monHoc.getTenMH(), monHoc.getSoTiet()});
//        database.close();
//    }
//
//
//
//    public ArrayList<MonHoc> DocDL() {
//        ArrayList<MonHoc> data = new ArrayList<>();
//        String sql = "select * from tbMonhoc";
//        SQLiteDatabase database = getReadableDatabase();
//        Cursor cursor = database.rawQuery(sql, null);
//        if(cursor.moveToFirst())
//        {
//            do {
//                MonHoc monHoc = new MonHoc();
//                monHoc.setMaMH(cursor.getString(0));
//                monHoc.setTenMH(cursor.getString(1));
//                monHoc.setSoTiet(cursor.getString(2));
//                data.add(monHoc);
//            }
//            while (cursor.moveToNext());
//        }
//        return data;
//    }
//
//    public void SuaDL(MonHoc monHoc)
//    {
//        String sql="Update tbMonHoc set tenMH=?,soTiet=? where maMH=?";
//        SQLiteDatabase database = getWritableDatabase();
//        database.execSQL(sql, new String[]{ monHoc.getTenMH(), monHoc.getSoTiet(),monHoc.getMaMH()});
//        database.close();
//    }
//
//    public void XoaDL(MonHoc monHoc)
//    {
//        String sql="delete from tbMonHoc  where maMH=?";
//        SQLiteDatabase database = getWritableDatabase();
//        database.execSQL(sql, new String[]{ monHoc.getMaMH()});
//        database.close();
//    }
}
