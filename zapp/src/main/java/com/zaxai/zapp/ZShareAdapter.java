package com.zaxai.zapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ZShareAdapter extends RecyclerView.Adapter<ZShareAdapter.ZShareViewHolder> {
    private List<ZShareItem> mZShareItemList;
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;

    public ZShareAdapter(List<ZShareItem> zShareItemList){mZShareItemList=zShareItemList;}

    static class ZShareViewHolder extends RecyclerView.ViewHolder{
        View mItemView;
        ImageView mItemImage;
        TextView mItemName;

        public ZShareViewHolder(View view){
            super(view);
            mItemView = view;
            mItemImage = view.findViewById(R.id.item_image);
            mItemName = view.findViewById(R.id.item_name);
        }
    }

    @NonNull
    @Override
    public ZShareViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zshare_item, viewGroup, false);
        final ZShareViewHolder holder=new ZShareViewHolder(view);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mOnRecyclerItemClickListener != null)
                    mOnRecyclerItemClickListener.onRecyclerItemClick(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ZShareViewHolder zShareViewHolder, int i) {
        ZShareItem zShareItem=mZShareItemList.get(i);
        zShareViewHolder.mItemImage.setImageResource(zShareItem.getImageId());
        zShareViewHolder.mItemName.setText(zShareItem.getItemName());
    }

    @Override
    public int getItemCount() {
        return mZShareItemList.size();
    }

    public interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(int position);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener l) {
        mOnRecyclerItemClickListener = l;
    }
}
