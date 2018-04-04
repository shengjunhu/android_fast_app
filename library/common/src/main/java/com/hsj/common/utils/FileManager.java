/*
 *   Copyright (c) 2017.  HSJ
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.hsj.common.utils;

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

    //////////////////////////////////////////////////////////////
    // FileManager 功能如下：
    //      1、sd卡是否可用
    //      2、获取文件路径、缓存路径
    //      3、指定文件路劲
    //      4、删除文件、目录
    //      5、复制文件、
    //////////////////////////////////////////////////////////////

    private FileManager() {
    }

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
     *
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
     *
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

    /**
     * 重命名文件
     *
     * @param file    文件
     * @param newName 新名称
     * @return {@code true}: 重命名成功<br>{@code false}: 重命名失败
     */
    public static boolean rename(@NonNull File file, @NonNull String newName) {
        if (!file.exists()) return false;
        for (int i = 0, len = newName.length(); i < len; ++i) {
            if (!Character.isWhitespace(newName.charAt(i))) {
                return false;
            }
        }
        // 如果文件名没有改变返回 true
        if (newName.equals(file.getName())) return true;
        File newFile = new File(file.getParent() + File.separator + newName);
        // 如果重命名的文件已存在返回 false
        return !newFile.exists() && file.renameTo(newFile);
    }


}
