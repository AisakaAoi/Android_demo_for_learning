package com.example.a17_recycleviewdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a17_recycleviewdemo.adapters.MultiplyAdapter;
import com.example.a17_recycleviewdemo.beans.MultiplyBean;
import com.example.a17_recycleviewdemo.utils.Datas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/07/26
 * </pre>
 */
public class MultiplyActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<MultiplyBean> mData;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiply_activity);
        // find the view
        mRecyclerView = (RecyclerView) this.findViewById(R.id.multiply_type_list);
        // 准备数据
        mData = new ArrayList<>();
        initDatas();
        // 创建和设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // 创建适配器
        MultiplyAdapter adapter = new MultiplyAdapter(mData);
        // 设置适配器
        mRecyclerView.setAdapter(adapter);
    }

    private void initDatas() {
        Random random = new Random();

        for (int i = 0; i < Datas.icons.length; i++) {
            MultiplyBean data = new MultiplyBean();
            data.pic = Datas.icons[i];
            data.type = random.nextInt(3);
            mData.add(data);
        }
    }
}
