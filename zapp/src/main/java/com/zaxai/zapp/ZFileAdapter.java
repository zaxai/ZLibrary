package com.zaxai.zapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.List;

public class ZFileAdapter extends RecyclerView.Adapter<ZFFViewHolder> {
    private List<ZFFItem> zffItemList;
    private onRecyclerItemClickerListener itemClickerListener;
    public ZFileAdapter(List<ZFFItem> zffItemList){ this.zffItemList=zffItemList; }

    @NonNull
    @Override
    public ZFFViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zff_item,viewGroup,false);
        final ZFFViewHolder holder=new ZFFViewHolder(view);
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
                zffItemList.get(position).setSelected(isChecked);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ZFFViewHolder holder, int position) {
        ZFFItem zffItem=zffItemList.get(position);
        holder.itemImage.setImageResource(zffItem.getImageId());
        holder.itemName.setText(zffItem.getItemName());
        holder.itemInfo.setText(zffItem.getItemInfo());
        holder.itemCheck.setChecked(zffItem.isSelected());
        if(!zffItem.isFile())
            holder.itemCheck.setVisibility(View.GONE);
        else
            holder.itemCheck.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return zffItemList.size();
    }

    public interface onRecyclerItemClickerListener{
        void onRecyclerItemClick(int position);
    }

    public void setItemClickerListener(onRecyclerItemClickerListener itemClickerListener){
        this.itemClickerListener=itemClickerListener;
    }
}
