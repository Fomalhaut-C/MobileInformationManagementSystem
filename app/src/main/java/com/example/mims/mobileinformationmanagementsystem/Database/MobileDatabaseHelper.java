package com.example.mims.mobileinformationmanagementsystem.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MobileDatabaseHelper extends SQLiteOpenHelper {
    public MobileDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String mobile = "create table mobile(id integer primary key autoincrement ,Name varchar(20) unique,Time varchar(20),Country varchar(20),CEO varchar(20),Introduce text,Image text)";
        String init = "insert into mobile values(null,'nubia','2012-10-30','中国','里强','努比亚（nubia），新锐智能手机品牌，定位高端市场，立足中国面向全球，以“Be Yourself”为品牌理念。努比亚手机产品拥有诸多单反级摄影功能，被称为“可以拍星星的手机”。努比亚在业内推出了“全网通”，并通过搭载FiT交互技术的全球首款“无边框手机”nubia Z9为广大用户认可。世界足坛巨星C·罗纳尔多任努比亚品牌代言人。','@drawable/nubia.png')";
        db.execSQL(mobile);
        db.execSQL(init);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
