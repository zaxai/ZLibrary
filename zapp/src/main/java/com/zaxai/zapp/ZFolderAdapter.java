package com.zaxai.zapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zaxai.util.ZFileFind;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZFolderAdapter extends RecyclerView.Adapter<ZFolderAdapter.ViewHolder> {
    private List<ZFolderItem> zFolderItemList;
    private onRecyclerItemClickerListener itemClickerListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView itemImage;
        TextView itemName;
        TextView itemInfo;
        CheckBox itemCheck;
        public ViewHolder(View view) {
            super(view);
            itemView = view;
            itemImage = (ImageView) view.findViewById(R.id.folder_item_image);
            itemName = (TextView) view.findViewById(R.id.folder_item_name);
            itemInfo = (TextView) view.findViewById(R.id.folder_item_info);
            itemCheck=(CheckBox)view.findViewById(R.id.folder_item_check);
        }
    }
    public ZFolderAdapter(List<ZFolderItem> zFolderItemList){ this.zFolderItemList=zFolderItemList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zfolder_item,viewGroup,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                if(itemClickerListener!=null)
                    itemClickerListener.onRecyclerItemClick(position);
            }
        });
        holder.itemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position=holder.getAdapterPosition();
                zFolderItemList.get(position).setSelected(isChecked);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ZFolderItem zFolderItem=zFolderItemList.get(position);
        holder.itemImage.setImageResource(zFolderItem.getImageId());
        holder.itemName.setText(zFolderItem.getItemName());
        holder.itemInfo.setText(zFolderItem.getItemInfo());
        holder.itemCheck.setChecked(zFolderItem.getSelected());
    }

    @Override
    public int getItemCount() {
        return zFolderItemList.size();
    }

    public interface onRecyclerItemClickerListener{
        void onRecyclerItemClick(int position);
    }

    public void setItemClickerListener(onRecyclerItemClickerListener itemClickerListener){
        this.itemClickerListener=itemClickerListener;
    }
}
