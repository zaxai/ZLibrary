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

public class ZFileDialogFragment extends DialogFragment {
    private View contentView;
    private TextView pathView;
    private TextView backView;
    private List<ZFFItem> zffItemList;
    private ZFileAdapter zAdapter;
    private String dirPath;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        contentView=getActivity().getLayoutInflater().inflate(R.layout.zff_dialog,null);
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
        AlertDialog dialog=builder.create();
        dialog.getWindow().setGravity(Gravity.TOP);
        return dialog;
    }

    @Override
    public void show(FragmentManager fragmentManager, String dirPath) {
        if (dirPath == null)
            return;
        this.dirPath = dirPath;
        super.show(fragmentManager, "ZFileDialogFragment");
    }

    private void initContent(){
        pathView=(TextView)contentView.findViewById(R.id.dir_path);
        pathView.setText(dirPath);
        backView=(TextView)contentView.findViewById(R.id.back_view);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File parentFile=new File(dirPath).getParentFile();
                if(parentFile!=null&&parentFile.list()!=null) {
                    dirPath=parentFile.getPath();
                    pathView.setText(dirPath);
                    refreshItem(dirPath);
                }else{
                    Toast.makeText(getActivity(),"到顶了",Toast.LENGTH_SHORT).show();
                }
            }
        });
        RecyclerView folderListView=(RecyclerView)contentView.findViewById(R.id.dialog_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        folderListView.setLayoutManager(layoutManager);
        zffItemList=new ArrayList<>();
        zAdapter=new ZFileAdapter(zffItemList);
        zAdapter.setItemClickerListener(new ZFileAdapter.onRecyclerItemClickerListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                ZFFItem zffItem=zffItemList.get(position);
                if(!zffItem.isFile()) {
                    dirPath = zffItem.getItemPath();
                    refreshItem(dirPath);
                    pathView.setText(dirPath);
                }
            }
        });
        folderListView.setAdapter(zAdapter);
        folderListView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        refreshItem(dirPath);
    }

    private void refreshItem(String dirPath){
        List<File> files=new ArrayList<>();
        ZFileFind.BrowseDirectory(new File(dirPath),files,false);
        zffItemList.clear();
        Collections.sort(files);
        for(File file:files){
            if (file.isDirectory()) {
                String info="";
                if(file.list()!=null)
                    info=String.format("%d", file.list().length);
                zffItemList.add(new ZFFItem(file.getPath(),file.getName(),info, R.drawable.ic_item_folder,false));
            }
        }
        for(File file:files){
            ZFFItem item;
            if (!file.isDirectory()) {
                zffItemList.add(new ZFFItem(file.getPath(),file.getName(),file.getName().substring(file.getName().lastIndexOf(".")+1), R.drawable.ic_item_file));
            }
        }
        zAdapter.notifyDataSetChanged();
    }
}
