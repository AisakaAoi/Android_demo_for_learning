package com.example.a20_androidretrofitdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a20_androidretrofitdemo.R;
import com.example.a20_androidretrofitdemo.domain.JsonResult;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/08/06
 * </pre>
 */
public class JsonResultListAdapter extends RecyclerView.Adapter<JsonResultListAdapter.InnerHolder> {

    private List<JsonResult.DataBean> mData = new ArrayList<>();

    @Override
    public InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_json_result, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InnerHolder holder, int position) {
        ImageView coverIv = holder.itemView.findViewById(R.id.item_iv);
        TextView titleTv = holder.itemView.findViewById(R.id.item_title);

        JsonResult.DataBean dataBean = mData.get(position);

        titleTv.setText(dataBean.getTitle());
        Glide.with(holder.itemView.getContext()).load("http://10.0.2.2:9102" + mData.get(position).getCover()).into(coverIv);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(JsonResult jsonResult) {
        mData.clear();
        mData.addAll(jsonResult.getData());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(View itemView) {
            super(itemView);
        }
    }
}
