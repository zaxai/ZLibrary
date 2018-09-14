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

import java.util.List;

public abstract class ZFFAdapter extends RecyclerView.Adapter<ZFFAdapter.ZFFViewHolder> {
    protected List<ZFFItem> mZFFItemList;
    private onRecyclerItemClickerListener mItemClickerListener;

    public ZFFAdapter(List<ZFFItem> zffItemList) {
        mZFFItemList = zffItemList;
    }

    static class ZFFViewHolder extends RecyclerView.ViewHolder {
        View mItemView;
        ImageView mItemImage;
        TextView mItemName;
        TextView mItemInfo;
        CheckBox mItemCheck;

        public ZFFViewHolder(View view) {
            super(view);
            mItemView = view;
            mItemImage = (ImageView) view.findViewById(R.id.item_image);
            mItemName = (TextView) view.findViewById(R.id.item_name);
            mItemInfo = (TextView) view.findViewById(R.id.item_info);
            mItemCheck = (CheckBox) view.findViewById(R.id.item_check);
        }
    }

    @NonNull
    @Override
    public ZFFViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zff_item, viewGroup, false);
        final ZFFViewHolder holder = new ZFFViewHolder(view);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mItemClickerListener != null)
                    mItemClickerListener.onRecyclerItemClick(position);
            }
        });
        holder.mItemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position = holder.getAdapterPosition();
                mZFFItemList.get(position).setSelected(isChecked);
            }
        });
        return holder;
    }

    @Override
    public int getItemCount() {
        return mZFFItemList.size();
    }

    public interface onRecyclerItemClickerListener {
        void onRecyclerItemClick(int position);
    }

    public void setItemClickerListener(onRecyclerItemClickerListener itemClickerListener) {
        mItemClickerListener = itemClickerListener;
    }
}
