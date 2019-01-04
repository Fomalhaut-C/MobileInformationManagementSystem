package com.example.mims.mobileinformationmanagementsystem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.mims.mobileinformationmanagementsystem.Mobile.Mobile;

import java.util.ArrayList;
import java.util.List;


public class MobileDataAdapter {
    private Context context;
    private SQLiteDatabase database = null;

    public MobileDataAdapter(Context context) {
        this.context = context;
    }

    private void OpenDB(){
        MobileDatabaseHelper DatabaseHelper = new MobileDatabaseHelper(context,"Mobile.db",null,1);
        database = DatabaseHelper.getWritableDatabase();
    }

    private void closeDB(){
        if (database.isOpen()){
            database.close();
        }
        database = null;
    }

    //添加
    public Long insert(Mobile mobile){
        OpenDB();
        ContentValues values = new ContentValues();
        values.put("Name",mobile.getName());
        values.put("Time",mobile.getTime());
        values.put("Country",mobile.getCountry());
        values.put("CEO",mobile.getCeo());
        values.put("Introduce",mobile.getIntroduce());
        values.put("Image",mobile.getImage());
        Long result = database.insert("mobile",null,values);
        closeDB();
        return result;
    }

    //查询
    public List<Mobile> query(String query_information){
        List<Mobile> list = new ArrayList<>();
        OpenDB();
        Cursor cursor = database.query("mobile",new String[]{"Name","Time","Country","CEO","Introduce"},"Name = ? or Time = ? or Country = ? or CEO = ? or Introduce = ?",new String[]{query_information},null,null,null);
        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    String name = cursor.getString(cursor.getColumnIndex("Name"));
                    String time = cursor.getString(cursor.getColumnIndex("Time"));
                    String country = cursor.getString(cursor.getColumnIndex("Country"));
                    String CEO = cursor.getString(cursor.getColumnIndex("CEO"));
                    String introduce = cursor.getString(cursor.getColumnIndex("Introduce"));
                    //String image = cursor.getString(cursor.getColumnIndex("Image"));
                    Mobile mobile = new Mobile();
                    mobile.setName(name);
                    mobile.setTime(time);
                    mobile.setCountry(country);
                    mobile.setCeo(CEO);
                    mobile.setIntroduce(introduce);
                    //mobile.setImage(image);
                    list.add(mobile);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        closeDB();
        return list;
    }

    //修改
    public int update(Mobile mobile){
        OpenDB();
        ContentValues values = new ContentValues();
        values.put("Time",mobile.getTime());
        values.put("Country",mobile.getCountry());
        values.put("CEO",mobile.getCeo());
        values.put("Introduce",mobile.getIntroduce());
        int result = database.update("mobile",values,"Name = ?",new String[]{mobile.getName()});
        closeDB();
        return result;
    }

    //删除
    public int delete(String delete_information){
        OpenDB();
        int result = database.delete("mobile","Name = ? or Time = ? or Country = ? or CEO = ? or Introduce = ?",new String[]{delete_information});
        closeDB();
        return result;
    }
}
