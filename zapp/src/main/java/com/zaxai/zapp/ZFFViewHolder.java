package com.zaxai.zapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ZFFViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    ImageView itemImage;
    TextView itemName;
    TextView itemInfo;
    CheckBox itemCheck;
    public ZFFViewHolder(View view) {
        super(view);
        itemView = view;
        itemImage = (ImageView) view.findViewById(R.id.item_image);
        itemName = (TextView) view.findViewById(R.id.item_name);
        itemInfo = (TextView) view.findViewById(R.id.item_info);
        itemCheck=(CheckBox)view.findViewById(R.id.item_check);
    }
}
