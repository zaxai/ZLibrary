package com.zaxai.zapp;

public class ZFFItem {
    private String itemPath;
    private String itemName;
    private String itemInfo;
    private int imageId;
    private boolean isFile;
    private boolean isSelected;
    public ZFFItem(String itemPath,String itemName,String itemInfo,int imageId){
        this.itemPath=itemPath;
        this.itemName=itemName;
        this.itemInfo=itemInfo;
        this.imageId=imageId;
        isFile=true;
        isSelected=false;
    }

    public ZFFItem(String itemPath,String itemName,String itemInfo,int imageId,boolean isFile){
        this.itemPath=itemPath;
        this.itemName=itemName;
        this.itemInfo=itemInfo;
        this.imageId=imageId;
        this.isFile=isFile;
        isSelected=false;
    }

    public String getItemPath() { return itemPath; }

    public String getItemName() { return itemName; }

    public String getItemInfo() { return itemInfo; }

    public int getImageId() {
        return imageId;
    }

    public boolean isFile() { return isFile; }

    public boolean isSelected() { return isSelected; }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
