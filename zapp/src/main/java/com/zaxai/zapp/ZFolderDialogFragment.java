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
import android.view.View;
import android.widget.TextView;

import com.zaxai.util.ZFileFind;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZFolderDialogFragment extends DialogFragment {
    private View contentView;
    private TextView pathView;
    private TextView backView;
    private List<ZFolderItem> zFolderItemList;
    private ZFolderAdapter zFolderAdapter;
    private String dirPath;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        contentView=getActivity().getLayoutInflater().inflate(R.layout.zfolder_dialog,null);
        initContent();
        builder.setView(contentView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    @Override
    public void show(FragmentManager fragmentManager, String dirPath) {
        if (dirPath == null)
            return;
        this.dirPath = dirPath;
        super.show(fragmentManager, "ZFolderDialogFragment");
    }

    private void initContent(){
        pathView=(TextView)contentView.findViewById(R.id.dir_path);
        pathView.setText(dirPath);
        backView=(TextView)contentView.findViewById(R.id.back_button);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parentPath=new File(dirPath).getParent();
                if(parentPath!=null) {
                    dirPath=parentPath;
                    pathView.setText(dirPath);
                    refreshItem(dirPath);
                }
            }
        });
        RecyclerView folderListView=(RecyclerView)contentView.findViewById(R.id.folder_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        folderListView.setLayoutManager(layoutManager);
        zFolderItemList=new ArrayList<>();
        zFolderAdapter=new ZFolderAdapter(zFolderItemList);
        zFolderAdapter.setItemClickerListener(new ZFolderAdapter.onRecyclerItemClickerListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                dirPath=zFolderItemList.get(position).getItemPath();
                refreshItem(dirPath);
                pathView.setText(dirPath);
            }
        });
        folderListView.setAdapter(zFolderAdapter);
        folderListView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        refreshItem(dirPath);
    }

    private void refreshItem(String dirPath){
        List<File> files=new ArrayList<>();
        ZFileFind.BrowseDirectory(new File(dirPath),files,false);
        zFolderItemList.clear();
        Collections.sort(files);
        for(File file:files){
            if (file.isDirectory()&&file.list()!=null) {
                ZFolderItem item = new ZFolderItem(file.getPath(),file.getName(),String.format("%d", file.list().length), R.drawable.ic_item_folderlist);
                zFolderItemList.add(item);
            }
        }
        zFolderAdapter.notifyDataSetChanged();
    }
}
