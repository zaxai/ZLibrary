package com.zaxai.zapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
