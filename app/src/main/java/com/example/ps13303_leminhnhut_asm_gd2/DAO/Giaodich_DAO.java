package com.example.ps13303_leminhnhut_asm_gd2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ps13303_leminhnhut_asm_gd2.Model.Giaodich;
import com.example.ps13303_leminhnhut_asm_gd2.Model.Phanloai;
import com.example.ps13303_leminhnhut_asm_gd2.database.database;

import java.util.ArrayList;
import java.util.Date;

public class Giaodich_DAO {
    SQLiteDatabase db1;
    database db;


    public Giaodich_DAO(Context context) {
        db = new database(context);
    }

    public void them(Giaodich gd) {
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tieude", gd.getTieuDe());
        values.put("Ngay", gd.getNgay());
        values.put("Tien", gd.getTien());
        values.put("Mota", gd.getMoTa());
        values.put("Maloai", gd.getMaLoai());

        db1.insert("GIAO_DICH",null,values);
    }

    public ArrayList<Phanloai> getThu(){
        ArrayList<Phanloai> ds_pl = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM LOAI_TC WHERE Trangthai LIKE 'Thu'", null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String tenloai = cursor.getString(1);
                String trangthai = cursor.getString(2);
                ds_pl.add(new Phanloai(id,tenloai,trangthai));
            } while (cursor.moveToNext());
        }
        return ds_pl;
    }

    public ArrayList<Phanloai> getChi(){
        ArrayList<Phanloai> ds_pl = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM LOAI_TC WHERE Trangthai = '"+"Chi"+"'", null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String tenloai = cursor.getString(1);
                String trangthai = cursor.getString(2);
                ds_pl.add(new Phanloai(id,tenloai,trangthai));
            } while (cursor.moveToNext());
        }
        return ds_pl;
    }

    public String getTen(String id){
        String ten = "";
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT Tenloai FROM LOAI_TC WHERE Maloai = '"+id+"'",null);
        if (cursor.moveToFirst()){
            do {
                ten = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return ten;
    }
    public String get_trangthai(int magd, int ml){
        String trangthais= "";
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT trangthai from GIAO_DICH, LOAI_TC WHERE GIAO_DICH.maloai = LOAI_TC.maloai AND GIAO_DICH.magd = '"+magd+"' AND GIAO_DICH.maloai = '"+ml+"' ", null);
        if (cursor.moveToFirst()){
            do {
                trangthais = cursor.getString(0);
            } while (cursor.moveToNext());
        }

           return trangthais;
    }


    public double getTotalthubyDate(String date1, String date2){
        double total = 0.0;
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT SUM(Tien) FROM GIAO_DICH,LOAI_TC WHERE Ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Thu'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);

            }while (cursor.moveToNext());
        }
        return total;
    }

    public double getTotalchibyDate(String date1, String date2){
        double total = 0.0;
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT SUM(Tien) FROM GIAO_DICH,LOAI_TC WHERE Ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Chi'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);

            }while (cursor.moveToNext());
        }
        return total;
    }

    public double getTotalthu(){
        double total = 0.0;
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT SUM(tien) FROM GIAO_DICH INNER JOIN LOAI_TC ON GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Thu'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);

            }while (cursor.moveToNext());
        }
        return total;
    }

    public double getTotalchi(){
        double total = 0.0;
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT SUM(tien) FROM GIAO_DICH INNER JOIN LOAI_TC ON GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Chi'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);

            }while (cursor.moveToNext());
        }
        return total;
    }

    public ArrayList<Giaodich> getKhoanThu_Chi(String trangthai){
        ArrayList<Giaodich> ds_giaodich = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM GIAO_DICH INNER JOIN LOAI_TC ON GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE '"+trangthai+"'",null);
        if (cursor.moveToFirst()){
            do {
                Date date1 = null;
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                double Tien = Double.parseDouble(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                ds_giaodich.add(new Giaodich(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public ArrayList<Giaodich> get_Tk_Thu(String date1, String date2){
        ArrayList<Giaodich> ds_giaodich = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM GIAO_DICH,LOAI_TC WHERE ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Thu'" ,null);
        if (cursor.moveToFirst()){
            do {
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                double Tien = Double.parseDouble(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                ds_giaodich.add(new Giaodich(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public ArrayList<Giaodich> get_Tk_Chi(String date1, String date2){
        ArrayList<Giaodich> ds_giaodich = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM GIAO_DICH,LOAI_TC WHERE ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Chi'" ,null);
        if (cursor.moveToFirst()){
            do {
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                double Tien = Double.parseDouble(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                ds_giaodich.add(new Giaodich(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public void delete(int id){
        db1 = db.getWritableDatabase();
        db1.delete("GIAO_DICH","MaGD=?", new String[]{id+""});
    }
    public void update(Giaodich gd){
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tieude", gd.getTieuDe());
        values.put("Ngay", gd.getNgay());
        values.put("Tien", gd.getTien());
        values.put("Mota", gd.getMoTa());
        values.put("Maloai", gd.getMaLoai());
        db1.update("GIAO_DICH",values, "MaGD=?", new String[]{gd.getMaGiaoDich()+""});
    }
}
