package com.hsj.tool;

import android.content.Context;
import android.os.Environment;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:39
 * @Class:FileManager
 * @Description:文件操作工具类
 */
public class FileManager {

    /**
     * 0、sd卡缓存
     *    /storage/emulated/0/Android/data/packageName/files
     *      Context.getExternalFilesDir
     *    /storage/emulated/0/Android/data/packageName/cache
     *      Context.getExternalCacheDir();
     *
     * 1、内存缓存
     *   Context.getCacheDir(); //  /data/data/packageName/cache
     *   Context.getFilesDir(); //  /data/data/packageName/files
     *
     * 1、创建应用目录  packageName/image、log、download、temp、file、cache
     * 2、创建缓存目录  packageName/cache/image、db
     * 3、
     * 4、通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
     *   通过Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
     *
     * 5、Environment.getExternalStorageState()
     *
     *
     L.e("getDatabasePath():"+getDatabasePath("student.db"));
     L.e("getCacheDir():" + getCacheDir());
     L.e("getFilesDir():" + getFilesDir());
     String[] strings = fileList();
     for (String path : strings){//为空
     L.e("fileList():---" + path);
     }
     L.e("getDir(\"zhao\"):" + getDir("zhao", MODE_PRIVATE));
     //        L.e("getCodeCacheDir():" + getCodeCacheDir()); //java.lang.NoSuchMethodError
     L.e("getPackageCodePath():" + getPackageCodePath());
     L.e("getPackageResourcePath():" + getPackageResourcePath());
     L.e("getExternalFilesDir():" + getExternalFilesDir(null));
     File[] paths = getExternalFilesDirs(null);
     for (File path : paths){
     L.e("getExternalFilesDirs():---" + path.getPath());
     }
     L.e("getExternalCacheDir():" + getExternalCacheDir());
     paths = getExternalCacheDirs();
     for (File path : paths){
     L.e("getExternalCacheDirs():---" + path.getPath());
     }
     L.e("getObbDir():" + getObbDir());
     paths = getObbDirs();
     for (File path : paths){
     L.e("getObbDirs():---" + path.getPath());
     }
     L.e("Environment.getExternalStorageState():"+ Environment.getExternalStorageState());
     L.e("Environment.getExternalStorageDirectory():"+Environment.getExternalStorageDirectory());
     L.e("Environment.getDownloadCacheDirectory():"+Environment.getDownloadCacheDirectory());
     L.e("Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC):"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
     L.e("Environment.getRootDirectory():"+Environment.getRootDirectory());

     E/com.android.framework﹕ getDatabasePath():/data/data/com.android.framework/databases/student.db
     E/com.android.framework﹕ getCacheDir():/data/data/com.android.framework/cache
     E/com.android.framework﹕ getFilesDir():/data/data/com.android.framework/files
     E/com.android.framework﹕ getDir("zhao"):/data/data/com.android.framework/app_zhao
     E/com.android.framework﹕ getPackageCodePath():/data/app/com.android.framework-1.apk
     E/com.android.framework﹕ getPackageResourcePath():/data/app/com.android.framework-1.apk
     E/com.android.framework﹕ getExternalFilesDir():/storage/emulated/0/Android/data/com.android.framework/files
     E/com.android.framework﹕ getExternalFilesDirs():---/storage/emulated/0/Android/data/com.android.framework/files
     E/com.android.framework﹕ getExternalFilesDirs():---/storage/ext_sd/Android/data/com.android.framework/files*******
     E/com.android.framework﹕ getExternalCacheDir():/storage/emulated/0/Android/data/com.android.framework/cache
     E/com.android.framework﹕ getExternalCacheDirs():---/storage/emulated/0/Android/data/com.android.framework/cache
     E/com.android.framework﹕ getExternalCacheDirs():---/storage/ext_sd/Android/data/com.android.framework/cache*******
     E/com.android.framework﹕ getObbDir():/storage/emulated/0/Android/obb/com.android.framework
     E/com.android.framework﹕ getObbDirs():---/storage/emulated/0/Android/obb/com.android.framework
     E/com.android.framework﹕ getObbDirs():---/storage/ext_sd/Android/obb/com.android.framework*******
     E/com.android.framework﹕ Environment.getExternalStorageState():mounted
     E/com.android.framework﹕ Environment.getExternalStorageDirectory():/storage/emulated/0
     E/com.android.framework﹕ Environment.getDownloadCacheDirectory():/cache
     E/com.android.framework﹕ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC):/storage/emulated/0/Music
     E/com.android.framework﹕ Environment.getRootDirectory():/system
     */

    /**
     * SD卡是否可用
     *
     * @return
     */
    public static boolean isSd() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     *
     * @param context
     * @return
     */
    public static String getCacheDir(Context context) {
        return "";
    }


}
