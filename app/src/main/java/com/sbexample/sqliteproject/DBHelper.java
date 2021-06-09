package com.sbexample.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "userdata.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create Table Userdetails(name TEXT primary key,contact TEXT,dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop Table if exists Userdetails");
    }
    public boolean insertuserdata(String name,String contact,String dob){
    SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        long res=db.insert("userdetails",null,contentValues);
        if(res==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean updateuserdata(String name,String contact,String dob){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        Cursor cursor=db.rawQuery("Select * from userdetails where name=?",new String[]{name});
        if(cursor.getCount()>0) {
            long res = db.update("userdetails", contentValues, "name=?", new String[]{name});
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }
    public boolean deletedata(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from userdetails where name=?",new String[]{name});
        if(cursor.getCount()>0) {
            long res = db.delete("userdetails","name=?", new String[]{name});
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from userdetails",null);
        return cursor;
    }
}
