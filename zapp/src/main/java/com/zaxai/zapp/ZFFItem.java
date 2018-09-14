package com.zaxai.zapp;

public class ZFFItem {
    private String mItemPath;
    private String mItemName;
    private String mItemInfo;
    private int mImageId;
    private boolean mIsFile;
    private boolean mIsSelected;
    public ZFFItem(String itemPath,String itemName,String itemInfo,int imageId){
        mItemPath=itemPath;
        mItemName=itemName;
        mItemInfo=itemInfo;
        mImageId=imageId;
        mIsFile=true;
        mIsSelected=false;
    }

    public ZFFItem(String itemPath,String itemName,String itemInfo,int imageId,boolean isFile){
        mItemPath=itemPath;
        mItemName=itemName;
        mItemInfo=itemInfo;
        mImageId=imageId;
        mIsFile=isFile;
        mIsSelected=false;
    }

    public String getItemPath() { return mItemPath; }

    public String getItemName() { return mItemName; }

    public String getItemInfo() { return mItemInfo; }

    public int getImageId() {
        return mImageId;
    }

    public boolean isFile() { return mIsFile; }

    public boolean isSelected() { return mIsSelected; }

    public void setSelected(boolean isSelected) {
        this.mIsSelected = isSelected;
    }
}
