package com.app.base.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.format.Formatter;

import java.io.File;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/19/15:23
 * @Class:FileManager
 * @Description:文件管理者
 */
public class FileManager {

    private FileManager() {
    }

    /**
     * 默认可用存储大小：200MB
     */
    private static final long DEFAULT_USABLE_SPACE = 100 * 1024 * 1024;

    /**
     * SD卡是否可用
     *
     * @return
     */
    public static boolean isSDEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取SD卡可用存储大小
     *
     * @param context
     * @return
     */
    public static long getSdCardUsableSpace(@NonNull Context context) {
        if (isSDEnable()) {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                return externalCacheDir.getUsableSpace();
            }
            return 0;
        }
        return 0;
    }

    /**
     * 获取内存卡可用存储大小
     *
     * @param context
     * @return
     */
    public static long getMemoryCardUsableSpace(@NonNull Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            return cacheDir.getUsableSpace();
        }
        return 0;
    }

    /**
     * 获取可用存储大小：默认使用SdCard
     *
     * @param context
     * @return
     */
    public static long getUsableSpace(@NonNull Context context) {
        if (isSDEnable()) {
            return getSdCardUsableSpace(context);
        } else {
            return getMemoryCardUsableSpace(context);
        }
    }

    /**
     * 获取文件大小
     *
     * @return
     */
    public static String getFileSize(Context context, @NonNull String filePath) {
        File file = new File(filePath);
        return Formatter.formatFileSize(context, file.length());
    }

    /**
     * 获取文件大小
     *
     * @return
     */
    public static long getFileSize(@NonNull String filePath) {
        File file = new File(filePath);
        return file.length();
    }

    /**
     * 获取缓存目录 ：
     *
     * @param context
     * @param cacheDirName:db、image、video、audio、web、log
     * @return /storage/emulated/0/Android/data/{packageName}/cache/ or /data/data/{packageName}/cache/
     */
    public static File getCacheDir(@NonNull Context context, @NonNull String cacheDirName) {
        File cacheDir = null;
        if (isSDEnable()) {
            cacheDir = context.getExternalCacheDir();
            cacheDir = new File(cacheDir, cacheDirName);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
        } else {
            cacheDir = context.getCacheDir();
            cacheDir = new File(cacheDir, cacheDirName);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
        }
        return cacheDir;
    }

    /**
     * 获取文件目录
     *
     * @param context
     * @param fileDirName :log、apk、doc、image、audio、video、adver、upload、download
     * @return : /storage/emulated/0/Android/data/{packageName}/files/ or  /data/data/{packageName}/files/
     */
    public static File getFileDir(@NonNull Context context, @NonNull String fileDirName) {
        File fileDir = null;
        if (isSDEnable()) {
            fileDir = context.getExternalFilesDir("");
            fileDir = new File(fileDir, fileDirName);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
        } else {
            fileDir = context.getFilesDir();
            fileDir = new File(fileDir, fileDirName);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
        }
        return fileDir;
    }

    /**
     * 删除单文件： IO线程 Or 主线程
     *
     * @return
     */
    public static boolean deleteFile(@NonNull String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isFile()) {
                return file.delete();
            }
        }
        return true;
    }

    /**
     * 删除多个文件：IO线程
     *
     * @param filePaths
     * @return
     */
    public static boolean deleteFiles(@NonNull List<String> filePaths) {
        for (String path : filePaths) {
            File file = new File(path);
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
        return true;
    }

    /**
     * 删除目录: IO线程
     *
     * @return
     */
    public static boolean deleteDir(@NonNull String dirPath) {
        File dir = new File(dirPath);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    } else {
                        deleteDir(file.getPath());
                    }
                }
            }
            return dir.delete();
        }
        return true;
    }

    /**
     * 清理缓存：IO线程
     * @param context
     * @return
     */
    public static boolean deleteAllCacheDir(@NonNull Context context) {
        if (isSDEnable()) {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                File[] files = externalCacheDir.listFiles();
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        deleteDir(file.getPath());
                    }
                }
            }
        }

        File cacheDir = context.getCacheDir();
        File[] files = cacheDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                deleteDir(file.getPath());
            }
        }
        return true;
    }

    /**
     * 清理文件目录: IO线程
     * @param context
     * @return
     */
    public static boolean deleteAllFileDir(@NonNull Context context) {
        if (isSDEnable()) {
            File externalCacheDir = context.getExternalFilesDir("");
            if (externalCacheDir != null) {
                File[] files = externalCacheDir.listFiles();
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        deleteDir(file.getPath());
                    }
                }
            }
        }

        File cacheDir = context.getFilesDir();
        File[] files = cacheDir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                deleteDir(file.getPath());
            }
        }
        return true;
    }

}
