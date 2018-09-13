package com.zaxai.zapp;

public class ZFolderItem {
    private String itemPath;
    private String itemName;
    private String itemInfo;
    private int imageId;
    private boolean isSelected;
    public ZFolderItem(String itemPath,String itemName,String itemInfo,int imageId){
        this.itemPath=itemPath;
        this.itemName=itemName;
        this.itemInfo=itemInfo;
        this.imageId=imageId;
        isSelected=false;
    }

    public String getItemPath() { return itemPath; }

    public String getItemName() { return itemName; }

    public String getItemInfo() { return itemInfo; }

    public int getImageId() {
        return imageId;
    }

    public boolean getSelected() { return isSelected; }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
