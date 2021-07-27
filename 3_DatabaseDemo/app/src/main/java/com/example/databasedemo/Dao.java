package com.example.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/16
 * </pre>
 */
public class Dao {

    private static final String TAG = "Dao";
    private final DatabaseHelper mHelper;

    public Dao(Context context) {
        mHelper = new DatabaseHelper(context);
    }

    public void insert() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        String sql = "insert into " + Constants.TABLE_NAME + "(_id,name,age,salary,phone,address) values (?,?,?,?,?,?)";
//        db.execSQL(sql, new Object[]{1, "AisakaAoi", 60, 1, 110, "China"});

        ContentValues values = new ContentValues();

        // 添加数据
        values.put("_id", 2);
        values.put("name", "AisakaSumire");
        values.put("salary", 2);
        values.put("phone", 120);
        values.put("address", "GuangDong");

        db.insert(Constants.TABLE_NAME, null, values);

        db.close();
    }

    public void delete() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        String sql = "delete from " + Constants.TABLE_NAME + " where age = 60";
//        db.execSQL(sql);

        int result = db.delete(Constants.TABLE_NAME, null, null);


        db.close();
    }

    public void update() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        String sql = "update " + Constants.TABLE_NAME + " set salary = 2 where _id = 1";
//        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("phone", 12345);

        db.update(Constants.TABLE_NAME, values, null, null);

        db.close();
    }

    public void query() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        String sql = "select * from " + Constants.TABLE_NAME;
//        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()) {
//            int index = cursor.getColumnIndex("name");
//            String name = cursor.getString(index);
//            Log.d(TAG, "name == " + name);
//        }
//        cursor.close();

        Cursor cursor = db.query(Constants.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Log.d(TAG, "id == " + id + " name == " + name);
        }
        cursor.close();
        db.close();
    }

}
