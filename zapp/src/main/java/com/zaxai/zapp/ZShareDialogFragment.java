package com.zaxai.zapp;
/*
 ** Version  1.0.0
 ** Date     2019.03.09
 ** Author   zax
 ** Copyright © Since 2009 Zaxai.Com All Rights Reserved
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class ZShareDialogFragment extends DialogFragment {
    private View mContentView;
    private List<ZShareItem> mZShareItemList=new ArrayList<>();
    private ZShareAdapter mZAdapter;
    private OnItemClickListener mOnItemClickListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Activity activity=getActivity();
        if(activity!=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            mContentView = activity.getLayoutInflater().inflate(R.layout.zshare_dialog, null);
            initView();
            builder.setView(mContentView)
                    .setNegativeButton("取消", null);
            AlertDialog dialog = builder.create();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.ZShareDialogAnim);
            }
            return dialog;
        }else
            return super.onCreateDialog(savedInstanceState);
    }

    private void initView(){
        RecyclerView recyclerView=mContentView.findViewById(R.id.dialog_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        mZAdapter=new ZShareAdapter(mZShareItemList);
        mZAdapter.setOnRecyclerItemClickListener(new ZShareAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                if(mOnItemClickListener!=null)
                    mOnItemClickListener.onItemClick(mZShareItemList.get(position));
            }
        });
        recyclerView.setAdapter(mZAdapter);
    }

    public void show(FragmentManager fragmentManager,@NonNull List<ZShareItem> zShareItemList){
        mZShareItemList=zShareItemList;
        show(fragmentManager, toString());
    }

    public interface OnItemClickListener {
        void onItemClick(ZShareItem zShareItem);
    }

    public ZShareDialogFragment setOnClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
        return this;
    }
}
