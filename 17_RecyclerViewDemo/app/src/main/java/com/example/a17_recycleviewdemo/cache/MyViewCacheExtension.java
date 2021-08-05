package com.example.a17_recycleviewdemo.cache;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/07/29
 * </pre>
 */
public class MyViewCacheExtension extends RecyclerView.ViewCacheExtension  {

    @Override
    public View getViewForPositionAndType(RecyclerView.Recycler recycler, int position, int type) {
        //如果viewType为TYPE_SPECIAL,使用自己缓存的View去构建ViewHolder
        // 否则返回null，会使用系统RecyclerPool缓存或者从新通过onCreateViewHolder构建View及ViewHolder
//        return type == DemoAdapter.TYPE_SPECIAL ? adapter.caches.get(position) : null;
        return null;
    }

}
