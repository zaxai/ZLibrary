package com.zaxai.zapp;
/*
 ** Version  1.0.0
 ** Date     2019.03.09
 ** Author   zax
 ** Copyright © Since 2009 Zaxai.Com All Rights Reserved
 */

import com.zaxai.util.ZFileFind;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZFolderDialogFragment extends ZFFDialogFragment {
    @Override
    public ZFFAdapter onCreateAdapter(List<ZFFItem> zffItemList) {
        return new ZFolderAdapter(zffItemList);
    }

    @Override
    public void onRefreshItem(String dirPath){
        List<File> files=new ArrayList<>();
        ZFileFind.browseDirectory(new File(dirPath),files,false);
        mZFFItemList.clear();
        Collections.sort(files);
        for(File file:files){
            if (file.isDirectory()) {
                String info="";
                if(file.list()!=null)
                    info=String.format("%d", file.list().length);
                mZFFItemList.add(new ZFFItem(file.getPath(),file.getName(),info, R.drawable.ic_item_folder,false));
            }
        }
        mZAdapter.notifyDataSetChanged();
    }
}
