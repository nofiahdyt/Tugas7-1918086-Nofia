package com.example.tugaspraktikum7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_kampus";
    private static final String tb_dokter = "tb_dokter";
    private static final String tb_dokter_id = "id";
    private static final String tb_dokter_nama = "nama";
    private static final String tb_dokter_kelas = "sp";
    private static final String CREATE_TABLE_DOKTER = "CREATE TABLE " + tb_dokter +"("
            + tb_dokter_id + " INTEGER PRIMARY KEY ,"
            + tb_dokter_nama + " TEXT ,"
            + tb_dokter_kelas + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DOKTER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void CreateDokter(Dokter data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_dokter_id, data.get_id());
        values.put(tb_dokter_nama, data.get_nama());
        values.put(tb_dokter_kelas, data.get_sp());
        db.insert(tb_dokter, null, values);
        db.close();
    }
    public List<Dokter> ReadDokter() {
        List<Dokter> listMhs = new ArrayList<Dokter>();
        String selectQuery = "SELECT * FROM " + tb_dokter;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Dokter data = new Dokter();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_sp(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;
    }
    public int UpdateDokter (Dokter data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_dokter_nama, data.get_nama());
        values.put(tb_dokter_kelas, data.get_sp());
        return db.update(tb_dokter, values, tb_dokter_id +
                        " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void DeleteDokter(Dokter data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_dokter,tb_dokter_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
