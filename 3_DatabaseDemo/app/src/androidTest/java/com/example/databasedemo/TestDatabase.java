package com.example.databasedemo;

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
    public void testCreate() {
        // 测试创建数据库
    }

    @Test
    public void testInsert() {
        // 测试插入数据
        Dao dao = new Dao(getContext());
        dao.insert();
    }

    @Test
    public void testDelete() {
        // 测试删除数据
        Dao dao = new Dao(getContext());
        dao.delete();
    }

    @Test
    public void testUpdate() {
        // 测试更新数据
        Dao dao = new Dao(getContext());
        dao.update();
    }

    @Test
    public void testQuery() {
        // 测试查询数据
        Dao dao = new Dao(getContext());
        dao.query();
    }

}
