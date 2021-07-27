package com.example.a17_recycleviewdemo.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a17_recycleviewdemo.R;
import com.example.a17_recycleviewdemo.beans.MultiplyBean;

import java.util.List;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/07/26
 * </pre>
 */
public class MultiplyAdapter extends RecyclerView.Adapter {

    // 定义三个常量标识三种类型
    private static final int TYPE_FULL_IMAGE = 0;
    private static final int TYPE_RIGHT_IMAGE = 1;
    private static final int TYPE_THREE_IMAGE = 2;

    private final List<MultiplyBean> mData;

    public MultiplyAdapter(List<MultiplyBean> data) {
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        if (viewType == TYPE_FULL_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_full_image, null);
            return new FullImageHolder(view);
        } else if (viewType == TYPE_RIGHT_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_right_image, null);
            return new RightImageHolder(view);
        } else if (viewType == TYPE_THREE_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_three_image, null);
            return new ThreeImageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    // 我们要复写一个方法 这个方法是根据条件来返回条目类型
    @Override
    public int getItemViewType(int position) {
        MultiplyBean multiplyBean = mData.get(position);
        if (multiplyBean.type == 0) {
            return TYPE_FULL_IMAGE;
        } else if (multiplyBean.type == 1) {
            return TYPE_RIGHT_IMAGE;
        } else if (multiplyBean.type == 2) {
            return TYPE_THREE_IMAGE;
        }
        return super.getItemViewType(position);
    }

    private class FullImageHolder extends RecyclerView.ViewHolder {
        public FullImageHolder(View itemView) {
            super(itemView);
        }
    }

    private class RightImageHolder extends RecyclerView.ViewHolder {
        public RightImageHolder(View itemView) {
            super(itemView);
        }
    }

    private class ThreeImageHolder extends RecyclerView.ViewHolder {
        public ThreeImageHolder(View itemView) {
            super(itemView);
        }
    }
}
