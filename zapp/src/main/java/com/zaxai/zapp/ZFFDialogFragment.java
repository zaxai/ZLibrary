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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class ZFFDialogFragment extends DialogFragment {
    private View mContentView;
    private TextView mTitleView;
    private TextView mPathView;
    private TextView mBackView;
    protected List<ZFFItem> mZFFItemList;
    protected ZFFAdapter mZAdapter;
    private String mDirPath;
    private String mTitle="添加项目";
    private int mTitleColor=0xFF444444;
    private int mBackColor=0xFF444444;
    private String mPositiveButtonText="确定";
    private OnClickListener mOnPositiveListener;
    private String mNegativeButtonText="取消";
    private OnClickListener mOnNegativeListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        mContentView=getActivity().getLayoutInflater().inflate(R.layout.zff_dialog,null);
        initView();
        builder.setView(mContentView)
                .setPositiveButton(mPositiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mOnPositiveListener != null)
                            mOnPositiveListener.onClick(getSelectedPaths());
                    }
                })
                .setNegativeButton(mNegativeButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mOnNegativeListener != null) {
                            mOnNegativeListener.onClick(getSelectedPaths());
                        }
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.getWindow().setGravity(Gravity.TOP);
        return dialog;
    }

    @NonNull
    private String[] getSelectedPaths(){
        List<String> selectedPaths=new ArrayList<>();
        for(ZFFItem zffItem:mZFFItemList){
            if(zffItem.isSelected())
                selectedPaths.add(zffItem.getItemPath());
        }
        String[] arrayItem=new String[selectedPaths.size()];
        return selectedPaths.toArray(arrayItem);
    }

    private void initView(){
        mTitleView=(TextView)mContentView.findViewById(R.id.dialog_title);
        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleColor);
        mPathView=(TextView)mContentView.findViewById(R.id.dir_path);
        mPathView.setText(mDirPath);
        mBackView=(TextView)mContentView.findViewById(R.id.back_view);
        mBackView.setTextColor(mBackColor);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File parentFile=new File(mDirPath).getParentFile();
                if(parentFile!=null&&parentFile.list()!=null) {
                    mDirPath=parentFile.getPath();
                    mPathView.setText(mDirPath);
                    onRefreshItem(mDirPath);
                }else{
                    Toast.makeText(getActivity(),"到顶了",Toast.LENGTH_SHORT).show();
                }
            }
        });
        RecyclerView folderListView=(RecyclerView)mContentView.findViewById(R.id.dialog_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        folderListView.setLayoutManager(layoutManager);
        mZFFItemList=new ArrayList<>();
        mZAdapter=onCreateAdapter(mZFFItemList);
        mZAdapter.setOnRecyclerItemClickListener(new ZFolderAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                ZFFItem zffItem=mZFFItemList.get(position);
                if(!zffItem.isFile()) {
                    mDirPath = zffItem.getItemPath();
                    onRefreshItem(mDirPath);
                    mPathView.setText(mDirPath);
                }
            }
        });
        folderListView.setAdapter(mZAdapter);
        folderListView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        onRefreshItem(mDirPath);
    }

    public interface OnClickListener {
        void onClick(String[] selectedPaths);
    }

    public ZFFDialogFragment setPositiveButton(String text, final OnClickListener listener) {
        mPositiveButtonText=text;
        mOnPositiveListener=listener;
        return this;
    }

    public ZFFDialogFragment setNegativeButton(String text, final OnClickListener listener) {
        mNegativeButtonText=text;
        mOnNegativeListener=listener;
        return this;
    }

    public ZFFDialogFragment setTitle(String title){
        mTitle=title;
        return this;
    }

    public ZFFDialogFragment setTitleColor(int titleColor){
        mTitleColor=titleColor;
        return this;
    }

    public ZFFDialogFragment setBackColor(int backColor){
        mBackColor=backColor;
        return this;
    }

    @Override
    public void show(FragmentManager fragmentManager, String dirPath) {
        if (dirPath == null)
            return;
        mDirPath = dirPath;
        super.show(fragmentManager, toString());
    }

    public abstract ZFFAdapter onCreateAdapter(List<ZFFItem> zffItemList);
    public abstract void onRefreshItem(String dirPath);
}
