package com.zaxai.zlibrary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zaxai.zapp.ZFFDialogFragment;
import com.zaxai.zapp.ZFileDialogFragment;
import com.zaxai.zapp.ZFolderDialogFragment;
import com.zaxai.zapp.ZShareDialogFragment;
import com.zaxai.zapp.ZShareItem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        Button folderBtn=(Button)findViewById(R.id.folder_btn);
        folderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ZFolderDialogFragment()
                        .setTitle("添加目录")
                        .setTitleColor(0xFFFF0000)
                        .setBackColor(0xFFFF0000)
                        .setPositiveButton("确定", new ZFFDialogFragment.OnClickListener() {
                            @Override
                            public void onClick(String[] selectedPaths) {
                                for(String path:selectedPaths)
                                    Toast.makeText(MainActivity.this,path,Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getSupportFragmentManager(),getStoragePaths()[0]);
            }
        });
        Button fileBtn=(Button)findViewById(R.id.file_btn);
        fileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ZFileDialogFragment().setTitle("添加文件").setTitleColor(0xFF0000FF).setBackColor(0xFF0000FF).show(getSupportFragmentManager(),getStoragePaths()[0]);
            }
        });
        Button shareBtn=findViewById(R.id.share_btn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ZShareItem> zShareItemList=new ArrayList<>();
                zShareItemList.add(new ZShareItem(1,"微信",R.drawable.ic_wechat));
                zShareItemList.add(new ZShareItem(2,"QQ",R.drawable.ic_qq));
                zShareItemList.add(new ZShareItem(3,"复制链接",R.drawable.ic_link));
                new ZShareDialogFragment().setOnClickListener(new ZShareDialogFragment.OnItemClickListener() {
                    @Override
                    public void onItemClick(ZShareItem zShareItem) {
                        Toast.makeText(MainActivity.this,zShareItem.getItemName(),Toast.LENGTH_SHORT).show();
                    }
                }).show(getSupportFragmentManager(),zShareItemList);
            }
        });
    }
    public String[] getStoragePaths() {
        try {
            StorageManager sm = (StorageManager) getSystemService(STORAGE_SERVICE);
            Method getVolumePathsMethod = StorageManager.class.getMethod("getVolumePaths");
            String[] paths = (String[]) getVolumePathsMethod.invoke(sm);
            return paths;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:{
                if (grantResults.length > 0){
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            break;
            default:
                break;
        }
    }
}
