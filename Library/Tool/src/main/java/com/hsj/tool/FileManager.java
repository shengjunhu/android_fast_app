package com.hsj.tool;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.zip.GZIPInputStream;

import static com.hsj.tool.L.isSdUsable;

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
     * 获取应用下的缓存目录
     * @param context
     * @return
     */
    public static File getCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    /**
     * 获取应用下的存储目录
     * @param context
     * @return
     */
    public static File getFileDir(Context context) {
        return context.getExternalCacheDir();
    }

    /**
     * 获得SD卡可用大小
     *
     * @return
     */
    public static long getSdUsableSize() {
        if (!isSdUsable()) {
            return 0;
        }
        File file = Environment.getExternalStorageDirectory();
        return file.getUsableSpace();
    }

    /**
     * 获取设备内部存储可用大小
     *
     * @return
     */
    public static long getUsableInternalSize() {
        File file = Environment.getDataDirectory();
        return file.getUsableSpace();
    }

    /**
     * 获取可用存储空间: 优先使用SD卡存储，次选手机设备内部存储
     * @return
     */
    public static long getStorageSize() {
        File file = null;
        if(isSdUsable()){
            file = Environment.getExternalStorageDirectory();
        }else {
            file = Environment.getDataDirectory();
        }
        return file.getUsableSpace();
    }

    /**
     * 获取根缓存目录: 优先获取SD卡目录
     *
     * @return
     */
    public static String getCacheDir1(Context context) {
        String cachePath = null;
        if (isSdUsable()) {
            //路径： /sdcard/Android/data/(PackageName)/cache
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            //路径： /data/data/(PackageName)/cache
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 递归创建文件夹
     *
     * @param dirPath
     * @return 创建失败返回 null
     */
    public static String createDir(String dirPath) {
        try {
            File file = new File(dirPath);
            if (file.getParentFile().exists()) {
                file.mkdir();
                return file.getAbsolutePath();
            } else {
                createDir(file.getParentFile().getAbsolutePath());
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将内容写入文件
     *
     * @param filePath  eg:/mnt/sdcard/xbs/log/log20170223.txt
     * @param content  内容
     */
    public static void writeFile2Sd(String filePath, String content, boolean isAppend) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath, isAppend);
            byte[] bytes = content.getBytes();
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Asset下的文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static InputStream getAssetFile(Context context, String fileName) {
        AssetManager am = context.getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 获取Raw下的文件内容
     *
     * @param context
     * @param resId
     * @return 文件内容
     */
    public static String getRawFile(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 文件拷贝
     *
     * @param src  源文件
     * @param desc 目的文件
     */
    public static void fileCopy(File src, File desc) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(desc);
            FileChannel in = fis.getChannel();  //得到对应的文件通道
            FileChannel out = fos.getChannel(); //得到对应的文件通道
            in.transferTo(0, in.size(), out);  //连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 转换文件大小
     *
     * @param fileLength 单位B
     * @return
     */
    public static String formatFileSizeToString(long fileLength) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileLength < 1024) {
            fileSizeString = df.format((double) fileLength) + "B";
        } else if (fileLength < 1048576) {
            fileSizeString = df.format((double) fileLength / 1024) + "K";
        } else if (fileLength < 1073741824) {
            fileSizeString = df.format((double) fileLength / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileLength / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 删除指定文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(File file) throws IOException {
        return deleteFileOrDirectory(file);
    }

    /**
     * 删除指定文件，如果是文件夹，则递归删除
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean deleteFileOrDirectory(File file) throws IOException {
        try {
            if (file != null && file.isFile()) {
                return file.delete();
            }
            if (file != null && file.isDirectory()) {
                File[] childFiles = file.listFiles();
                // 删除空文件夹
                if (childFiles == null || childFiles.length == 0) {
                    return file.delete();
                }
                // 递归删除文件夹下的子文件
                for (int i = 0; i < childFiles.length; i++) {
                    deleteFileOrDirectory(childFiles[i]);
                }
                return file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 获取文件扩展名
     *
     * @param filename 文件名
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取文件内容
     *
     * @param path
     * @return
     */
    public static String getFileOutputString(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path), 8192);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append("\n").append(line);
            }
            bufferedReader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件绝对路径Uri
     *
     * @param context
     * @param uri
     * @return
     */
    @SuppressLint("NewApi")
    public static String getPathByUri(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider：文档提供者
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {// ExternalStorageProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {// DownloadsProvider：下载的提供者
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {// MediaProvider：媒体提供者
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {// MediaStore：媒体库
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {// File：文件
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * 将文件File转成Uri
     * @param context
     * @param file
     * @return
     */
    public static Uri file2Uri(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
    }

    /**
     * 获取包信息.
     */
    public static PackageInfo getPackageInfo(@NonNull Context mContext) {
        PackageInfo info = null;
        try {
            String packageName = mContext.getPackageName();
            info = mContext.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 获取当前版本号
     *
     * @return
     * @throws Exception
     */
    public static String getVersionName(@NonNull Context mContext) {
        StringBuilder builder = new StringBuilder("v");
        try {
            PackageInfo packInfo = getPackageInfo(mContext);
            builder.append(packInfo.versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * 描述：打开并安装文件.
     *
     * @param context the context
     * @param file    apk文件路径
     */
    public static void installApk(@NonNull Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 解压数据库到sd卡中
     *
     * @param mContext--上下文：传递XBSApplication.appContext
     * @param zipName--  压缩文件名
     * @param unZipName--解压文件名
     */
    public static void unzipToSd(@NonNull final Context mContext, final String zipName, final String unZipName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AssetManager am = mContext.getAssets();
                    InputStream is = am.open(zipName);
                    OutputStream os = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), unZipName));
                    unzip(is, os);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 解压方法
     *
     * @param is
     * @param os
     */
    public static void unzip(InputStream is, OutputStream os) {
        try {
            GZIPInputStream gis = new GZIPInputStream(is);
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = gis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            is.close();
            gis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断sd卡中是否有fileName文件
     *
     * @param fileName
     * @return:存在为true
     */
    public static boolean sdExists(String fileName) {
        if (!isSdUsable()) {
            return false;
        }
        return new File(Environment.getExternalStorageDirectory(), fileName).exists();
    }


}
