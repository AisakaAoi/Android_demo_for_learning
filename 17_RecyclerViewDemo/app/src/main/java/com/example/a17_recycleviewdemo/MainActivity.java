package com.example.a17_recycleviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a17_recycleviewdemo.adapters.GridViewAdapter;
import com.example.a17_recycleviewdemo.adapters.ListViewAdapter;
import com.example.a17_recycleviewdemo.adapters.RecyclerViewBaseAdapter;
import com.example.a17_recycleviewdemo.adapters.StaggerViewAdapter;
import com.example.a17_recycleviewdemo.beans.ItemBean;
import com.example.a17_recycleviewdemo.utils.Datas;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 通过findViewById找到控件
 * 2. 准备好数据
 * 3. 设置布局管理器
 * 4. 创建并设置适配器
 * 5. 显示数据
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private RecyclerView mList;
    private List<ItemBean> mData;
    private RecyclerViewBaseAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 找到控件
        //
        initView();
        // 准备数据
        /*
         * 准备数据 一般来说 在现实开发中 数据是从网络获取的 此处仅为演示
         * 此处模拟数据 开发中也是要模拟数据的 例如后台还未准备好时
         */
        initData();
        // 设置默认的样式为ListView的效果
        showList(true, false);

        // 处理下拉刷新
        handlerDownPullUpdate();
    }

    private void handlerDownPullUpdate() {
        mRefreshLayout.setColorSchemeResources(R.color.purple_200, R.color.purple_500, R.color.purple_700);
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 在这里执行刷新数据的操作
                /**
                 * 当在顶部下拉时 这个方法会被触发
                 * 但这个方法是MainThread是主线程 不可以执行耗时操作
                 * 一般来说 请求数据需要开一个线程去获取
                 * 此处演示 直接添加一条数据
                 */
                // 添加数据
                ItemBean data = new ItemBean();
                data.title = "new insert data";
                data.icon = R.mipmap.pic_01;
                mData.add(0, data);
                // 更新UI 会阻塞主线程 需要开一个新线程
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 这里做两件事 一件是让刷新停止 另一件是更新列表
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void initData() {
        // List<ItemBean> -> Adapter -> setAdapter -> 显示数据
        // 创建数据集合
        mData = new ArrayList<>();

        // 创建模拟数据
        for (int i = 0; i < Datas.icons.length; i++) {
            // 创建数据对象
            ItemBean data = new ItemBean();
            data.icon = Datas.icons[i];
            data.title = "No." + i + " item";
            mData.add(data);
        }
    }

    private void initView() {
        mList = (RecyclerView) this.findViewById(R.id.recycler_view);
        mRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.refresh_layout);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 设置处理条目的点击事件 该跳转就跳转
                Toast.makeText(MainActivity.this, "点击的是第" + position + "个条目", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            // ListView
            case R.id.list_item_vertical_stander:
                Log.d(TAG, "click ListView vertical stander");
                showList(true, false);
                break;
            case R.id.list_item_vertical_reverse:
                Log.d(TAG, "click ListView vertical reverse");
                showList(true, true);
                break;
            case R.id.list_item_horizontal_stander:
                Log.d(TAG, "click ListView horizontal stander");
                showList(false, false);
                break;
            case R.id.list_item_horizontal_reverse:
                Log.d(TAG, "click ListView horizontal reverse");
                showList(false, true);
                break;

            // GridView
            case R.id.grid_item_vertical_stander:
                Log.d(TAG, "click GridView vertical stander");
                showGrid(true, false);
                break;
            case R.id.grid_item_vertical_reverse:
                Log.d(TAG, "click GridView vertical reverse");
                showGrid(true, true);
                break;
            case R.id.grid_item_horizontal_stander:
                Log.d(TAG, "click GridView horizontal stander");
                showGrid(false, false);
                break;
            case R.id.grid_item_horizontal_reverse:
                Log.d(TAG, "click GridView horizontal reverse");
                showGrid(false, true);
                break;

            // StaggerView
            case R.id.stagger_item_vertical_stander:
                Log.d(TAG, "click StaggerView vertical stander");
                showStagger(true, false);
                break;
            case R.id.stagger_item_vertical_reverse:
                Log.d(TAG, "click StaggerView vertical reverse");
                showStagger(true, true);
                break;
            case R.id.stagger_item_horizontal_stander:
                Log.d(TAG, "click StaggerView horizontal stander");
                showStagger(false, false);
                break;
            case R.id.stagger_item_horizontal_reverse:
                Log.d(TAG, "click StaggerView horizontal reverse");
                showStagger(false, true);
                break;

            // 多种条目类型
            case R.id.multiply_type:
                // 跳到一个新的Activity里面去实现这个功能
                Intent intent = new Intent(this, MultiplyActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 这个方法用来显示StaggerView的效果
     */
    private void showStagger(boolean isVertical, boolean isReverse) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);
        mList.setLayoutManager(layoutManager);
        mAdapter = new StaggerViewAdapter(mData);
        mList.setAdapter(mAdapter);
        // 初始化事件
        initListener();
    }

    /**
     * 这个方法用来显示GridView的效果
     */
    private void showGrid(boolean isVertical, boolean isReverse) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);
        mList.setLayoutManager(layoutManager);
        mAdapter = new GridViewAdapter(mData);
        mList.setAdapter(mAdapter);
        // 初始化事件
        initListener();
    }

    /**
     * 这个方法用来显示ListView的效果
     */
    private void showList(boolean isVertical, boolean isReverse) {
        // RecyclerView需要设置样式 其实就是设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // 设置布局管理器来控制
        // 设置水平还是垂直
        layoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        // 设置正向还是反向
        layoutManager.setReverseLayout(isReverse);

        mList.setLayoutManager(layoutManager);

        // 创建适配器
        mAdapter = new ListViewAdapter(mData);
        // 设置到RecyclerView里面
        mList.setAdapter(mAdapter);
        // 初始化事件
        initListener();
    }
}