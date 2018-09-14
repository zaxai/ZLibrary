package com.zaxai.zapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.List;

public class ZFileAdapter extends ZFFAdapter {
    public ZFileAdapter(List<ZFFItem> zffItemList){
        super(zffItemList);
    }

    @Override
    public void onBindViewHolder(@NonNull ZFFViewHolder holder, int position) {
        ZFFItem zffItem=mZFFItemList.get(position);
        holder.mItemImage.setImageResource(zffItem.getImageId());
        holder.mItemName.setText(zffItem.getItemName());
        holder.mItemInfo.setText(zffItem.getItemInfo());
        holder.mItemCheck.setChecked(zffItem.isSelected());
        if(!zffItem.isFile())
            holder.mItemCheck.setVisibility(View.GONE);
        else
            holder.mItemCheck.setVisibility(View.VISIBLE);
    }
}
