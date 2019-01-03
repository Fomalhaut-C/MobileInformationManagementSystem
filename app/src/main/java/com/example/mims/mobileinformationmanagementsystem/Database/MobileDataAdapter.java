package com.example.mims.mobileinformationmanagementsystem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class MobileDataAdapter {
    private Context context = null;
    private SQLiteDatabase database = null;

    public MobileDataAdapter(Context context) {
        this.context = context;
    }

    public void OpenDB(){
        MobileDatabaseHelper DatabaseHelper = new MobileDatabaseHelper(context,"Mobile.db",null,1);
        database = DatabaseHelper.getWritableDatabase();
    }

    public void closeDB(){
        if (database.isOpen()){
            database.close();
        }
        database = null;
    }

    //添加
    public void insert(){
        OpenDB();
        ContentValues values = new ContentValues();
//        values.put("Name",);
//        values.put("Time",);
//        values.put("Country",);
//        values.put("CEO",);
//        values.put("Introduce",);
//        values.put("Image",);
//        database.insert(values);
        closeDB();
    }

    //查询
    public void query(){
        OpenDB();
        Cursor cursor = database.query("mobile",new String[]{"Name","Time","Country","CEO","Introduce"},"Name = ? or Time = ? or Country = ? or CEO = ? or Introduce = ?",new String[]{},null,null,null);
        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    String name = cursor.getString(cursor.getColumnIndex("Name"));
                    String time = cursor.getString(cursor.getColumnIndex("Time"));
                    String country = cursor.getString(cursor.getColumnIndex("Country"));
                    String CEO = cursor.getString(cursor.getColumnIndex("CEO"));
                    String introduce = cursor.getString(cursor.getColumnIndex("Introduce"));
                    String image = cursor.getString(cursor.getColumnIndex("Image"));
                }while (cursor.moveToNext());
            }
        }
        closeDB();
    }

    //修改
    public void update(){
        OpenDB();
        ContentValues values = new ContentValues();
        database.update("mobile",values,"Name = ? or Time = ? or Country = ? or CEO = ? or Introduce = ?",new String[]{});
        closeDB();
    }

    //删除
    public void delete(){
        OpenDB();
        database.delete("mobile","Name = ? or Time = ? or Country = ? or CEO = ? or Introduce = ?",new String[]{});
        closeDB();
    }
}
