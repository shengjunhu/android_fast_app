package com.hsj.image.loader.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/15 10:21
 * @Class:XImageLoader
 * @Description:图片加载工具类
 */
public class XImageLoader {

    File cacheDir;

    Runtime runtime = Runtime.getRuntime();

    {
        runtime.maxMemory();

        runtime.totalMemory();

        runtime.freeMemory();
    }

    public XImageLoader(Context context, String fileName) {
        if(TextUtils.isEmpty(fileName)){
            cacheDir = getCacheDir(context,"img");
        }else {
            cacheDir = getCacheDir(context,fileName);
        }
        cacheDir.mkdirs();
    }

    LruCache<String, byte[]> cache = new LruCache<String, byte[]>((int) (runtime.maxMemory() / 8)) {
        @Override
        protected int sizeOf(String key, byte[] value) {
            return value.length;
        }
    };

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public void display(ImageView iv, String url) {
        threadPoolExecutor.execute(new LoadImageRunnable(iv, url));
    }

    static Handler handler = new Handler();

    private class LoadImageRunnable implements Runnable {
        ImageView iv;
        String url;

        public LoadImageRunnable(ImageView iv, String url) {
            this.iv = iv;
            this.url = url;
        }

        @Override
        public void run() {
            try {
                byte[] bytes = getBytes(url);
                Bitmap bitmap = getBitmap(bytes);
                handler.post(new ShowImageRunnable(bitmap, iv));
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    private class ShowImageRunnable implements Runnable {
        ImageView iv;
        Bitmap bitmap;

        public ShowImageRunnable(Bitmap bitmap, ImageView iv) {
            this.bitmap = bitmap;
            this.iv = iv;
        }

        @Override
        public void run() {
            iv.setImageBitmap(bitmap);
        }
    }

    private Bitmap getBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    private byte[] getBytes(String url) throws IOException {

        byte[] result = null;
        result = readFromMem(url);
        if (result != null) {
            //.d("getBytes", "内存");
            return result;
        }
        result = readFromDisk(url);
        if (result != null) {
            //.d("getBytes", "磁盘");
            writeToMem(url, result);
            return result;
        }
        result = readFromNet(url);
        //Log.d("getBytes", "网络");
        writeToDisk(url, result);
        writeToMem(url, result);
        return result;
    }


    private byte[] readFromMem(String url) {
        return cache.get(url);
    }

    private void writeToMem(String url, byte[] result) {
        cache.put(url, result);
    }


    private byte[] readFromDisk(String url) throws IOException {
        File file = getFile(url);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();
            copyStream(fis, baos);
        } finally {
            close(fis);
            close(baos);
        }

        return baos.toByteArray();
    }

    private void writeToDisk(String url, byte[] result) throws IOException {
        File file = getFile(url);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(result);
        } finally {
            close(fos);
        }

    }

    private byte[] readFromNet(String url) throws IOException {
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new URL(url).openStream();
            baos = new ByteArrayOutputStream();
            copyStream(is, baos);

            return baos.toByteArray();
        } finally {
            close(is);
            close(baos);
        }

    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyStream(InputStream is, OutputStream os) throws IOException {
        int len = 0;
        byte[] buf = new byte[1024 * 8];

        while ((len = is.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
    }

    private File getFile(String url) {
        return new File(cacheDir, md5(url));
    }

    public static String md5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * SD卡是否可用
     *
     * @return
     */
    public static boolean isSdUsable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static File getCacheDir(@NonNull Context context, @NonNull String cacheDirName) {
        File cacheDir = null;
        if (isSdUsable()) {
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
}
