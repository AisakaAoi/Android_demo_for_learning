package com.example.a17_recycleviewdemo.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.a17_recycleviewdemo.R;
import com.example.a17_recycleviewdemo.beans.ItemBean;

import java.util.List;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/07/26
 * </pre>
 */
public class StaggerViewAdapter extends RecyclerViewBaseAdapter {

    public StaggerViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_stagger_view, null);
        return view;
    }
}
