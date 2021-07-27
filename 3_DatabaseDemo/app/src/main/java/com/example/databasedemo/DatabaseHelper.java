package com.example.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/16
 * </pre>
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    /**
     * @param context 上下文
     * @name 数据库名称
     * @factory 游标工厂
     * @version 版本号
     */
    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }

    /**
     * Called when the database is created for the first time.
     * 第一次创建数据库时被调用
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建时的回调
        Log.d(TAG, "创建数据库...");
        // 创建字段
        String sql = "create table " + Constants.TABLE_NAME + "(_id integer,name varchar,age integer,salary integer)";
        db.execSQL(sql);
        sql = "alter table " + Constants.TABLE_NAME + " add address varchar";
        db.execSQL(sql);
        sql = "alter table " + Constants.TABLE_NAME + " add phone integer";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 升级时的回调
        Log.d(TAG, "升级数据库...");
        // 添加字段
        String sql;
//        switch (oldVersion) {
//            case 1:
//                sql = "alter table " + Constants.TABLE_NAME + " add address varchar";
//                db.execSQL(sql);
//            case 2:
//                sql = "alter table " + Constants.TABLE_NAME + " add phone integer";
//                db.execSQL(sql);
//        }

    }
}
