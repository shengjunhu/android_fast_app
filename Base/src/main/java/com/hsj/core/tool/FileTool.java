package com.hsj.core.tool;

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
import android.support.v4.content.FileProvider;

import com.hsj.core.BaseContext;

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

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/2/21 12:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  FileTool
 * @Description :  文件帮助者
 */
public class FileTool {

    /**
     * SD卡是否可用
     *
     * @return
     */
    public static boolean isSdUsable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
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
    public static String getCacheDir(Context context) {
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
    public static PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            String packageName = BaseContext.mContext.getPackageName();
            info = BaseContext.mContext.getPackageManager().getPackageInfo(packageName, 0);
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
    public static String getVersionName() {
        StringBuilder builder = new StringBuilder("v");
        try {
            PackageInfo packInfo = getPackageInfo();
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
    public static void installApk(Context context, File file) {
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
    public static void unzipToSd(final Context mContext, final String zipName, final String unZipName) {
        if (sdExists(unZipName) | mContext == null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AssetManager am = mContext.getAssets();
                    InputStream is = am.open(zipName);
                    OutputStream os = new FileOutputStream(
                            new File(Environment.getExternalStorageDirectory(), unZipName));
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
