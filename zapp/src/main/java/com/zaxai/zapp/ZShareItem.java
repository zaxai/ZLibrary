package com.zaxai.zapp;

public class ZShareItem {
    private int mTag;
    private String mItemName;
    private int mImageId;

    public ZShareItem(int tag,String itemName,int imageId){
        mTag=tag;
        mItemName=itemName;
        mImageId=imageId;
    }
    public int getTag() { return mTag; }

    public String getItemName() { return mItemName; }

    public int getImageId() { return mImageId; }
}
