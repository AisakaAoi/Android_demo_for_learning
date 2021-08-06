package com.example.a19_androidnetwork.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a19_androidnetwork.R;
import com.example.a19_androidnetwork.domain.GetTextItem;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/08/05
 * </pre>
 */
public class GetResultListAdapter extends RecyclerView.Adapter<GetResultListAdapter.InnerHolder> {

    private List<GetTextItem.DataBean> mData = new ArrayList<>();

    @Override
    public GetResultListAdapter.InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_text, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GetResultListAdapter.InnerHolder holder, int position) {
        View itemView = holder.itemView;
        TextView titleTv = itemView.findViewById(R.id.item_title);
        GetTextItem.DataBean dataBean = mData.get(position);
        titleTv.setText(dataBean.getTitle());

        ImageView cover = itemView.findViewById(R.id.item_iv);
        Glide.with(itemView.getContext()).load("http://10.0.2.2:9102" + mData.get(position).getCover()).into(cover);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(GetTextItem getTextItem) {
        mData.clear();
        mData.addAll(getTextItem.getData());
        notifyDataSetChanged();
    }
    
    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(View itemView) {
            super(itemView);
        }
    }
}
