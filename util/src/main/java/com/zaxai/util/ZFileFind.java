package com.zaxai.util;

import java.io.File;
import java.util.List;

public class ZFileFind {
    public static void browseDirectory(File dirFile, List<File> files) {
        if(dirFile==null)
            return;
        File[] subFiles = dirFile.listFiles();
        if(subFiles!=null) {
            for (File subFile : subFiles) {
                files.add(subFile);
                if (subFile.isDirectory()) {
                    browseDirectory(subFile, files);
                }
            }
        }
    }

    public static void browseDirectory( File dirFile, List<File> files,boolean isBrowseSubDir) {
        if(dirFile==null)
            return;
        File[] subFiles = dirFile.listFiles();
        if(subFiles!=null) {
            for (File subFile : subFiles) {
                files.add(subFile);
                if (isBrowseSubDir && subFile.isDirectory()) {
                    browseDirectory(subFile, files,isBrowseSubDir);
                }
            }
        }
    }

    public static void browseDirectory( String dirPath, List<String> paths) {
        if(dirPath==null||dirPath.isEmpty())
            return;
        File file=new File(dirPath);
        File[] subFiles = file.listFiles();
        if(subFiles!=null) {
            for (File subFile : subFiles) {
                paths.add(subFile.getPath());
                if (subFile.isDirectory()) {
                    browseDirectory(subFile.getPath(), paths);
                }
            }
        }
    }

    public static void browseDirectory( String dirPath, List<String> paths,boolean isBrowseSubDir) {
        if(dirPath==null||dirPath.isEmpty())
            return;
        File file=new File(dirPath);
        File[] subFiles = file.listFiles();
        if(subFiles!=null) {
            for (File subFile : subFiles) {
                paths.add(subFile.getPath());
                if (isBrowseSubDir && subFile.isDirectory()) {
                    browseDirectory(subFile.getPath(), paths,isBrowseSubDir);
                }
            }
        }
    }
}
