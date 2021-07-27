package com.example.a4_transcationdemo;

import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;

import static androidx.test.InstrumentationRegistry.getContext;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/19
 * </pre>
 */
public class TestDatabase {

    @Test
    public void testDatabase() {
        // 测试数据库的创建
        DatabaseHelper mHelper = new DatabaseHelper(getContext());
        mHelper.getReadableDatabase();
    }

    @Test
    public void testInsert() {
        DatabaseHelper mHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = mHelper.getReadableDatabase();

        db.execSQL("insert into account values(1,'company', 1000000)");
        db.execSQL("insert into account values(1,'mycount', 0)");

        db.close();
    }

    @Test
    public void testUpdate() {
        // 公司-12000 个人+12000
        DatabaseHelper mHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = mHelper.getReadableDatabase();

        db.beginTransaction();

        try {
            db.execSQL("update account set money = 1000000-12000 where name = 'company'");

            // 在这里发生异常
            int i = 1/0;

            db.execSQL("update account set money = 12000 where name = 'mycount'");

            db.setTransactionSuccessful();
        } catch (Exception e) {
            throw new RuntimeException("bomb...");
        } finally {
            db.endTransaction();
            db.close();
        }



    }

}
