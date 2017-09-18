package com.hsj.image.loader.compress.luban;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;

import com.hsj.image.loader.interfaces.IImageCompressListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/18/20:17
 * @Class:ImageCompressProvider
 * @Description:图片压缩提供者
 */
public class ImageCompressProvider implements Handler.Callback {

    private static final String TAG = "ImageCompressProvider";

    private static final String DEFAULT_DISK_CACHE_DIR = "luban_disk_cache";

    private static final int MSG_COMPRESS_SUCCESS   = 0;
    private static final int MSG_COMPRESS_START     = 1;
    private static final int MSG_COMPRESS_ERROR     = 2;

    private Handler mHandler;

    private String mTargetDir;
    private List<String> mPaths;
    private int mLeastCompressSize;
    private IImageCompressListener iImageCompressListener;

    private ImageCompressProvider(Builder builder) {
        this.mPaths = builder.mPaths;
        this.mTargetDir = builder.mTargetDir;
        this.iImageCompressListener = builder.iImageCompressListener;
        this.mLeastCompressSize = builder.mLeastCompressSize;
        mHandler = new Handler(Looper.getMainLooper(), this);
    }

    /**
     * 建造模式
     * @param context
     * @return
     */
    public static Builder with(Context context) {
        return new Builder(context);
    }

    /**
     * 获取缓存文件
     *
     * @param context A context.
     */
    private File getImageCacheFile(Context context, String suffix) {
        if (TextUtils.isEmpty(mTargetDir)) {
            mTargetDir = getImageCacheDir(context).getAbsolutePath();
        }

        String cacheBuilder = mTargetDir + "/" +
                System.currentTimeMillis() +
                (int) (Math.random() * 1000) +
                (TextUtils.isEmpty(suffix) ? ".jpg" : suffix);

        return new File(cacheBuilder);
    }

    /**
     * 获取缓存缓存的目录
     *
     * @param context A context.
     * @see #getImageCacheDir(Context, String)
     */
    @Nullable
    private File getImageCacheDir(Context context) {
        return getImageCacheDir(context, DEFAULT_DISK_CACHE_DIR);
    }

    /**
     * 返回应用程序的私有缓存目录中给定名称的目录
     * 使用存储检索媒体和缩略图。
     *
     * @param context   A context.
     * @param cacheName The name of the subdirectory in which to store the cache.
     * @see #getImageCacheDir(Context)
     */
    @Nullable
    private File getImageCacheDir(Context context, String cacheName) {
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir != null) {
            File result = new File(cacheDir, cacheName);
            if (!result.mkdirs() && (!result.exists() || !result.isDirectory())) {
                // 文件无法创建目录，或结果存在，但不能目录
                return null;
            }
            return result;
        }
        if (Log.isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, "default disk cache dir is null");
        }
        return null;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (iImageCompressListener == null) return false;
        switch (msg.what) {
            case MSG_COMPRESS_START:
                iImageCompressListener.onStart();
                break;
            case MSG_COMPRESS_SUCCESS:
                iImageCompressListener.onSuccess((File) msg.obj);
                break;
            case MSG_COMPRESS_ERROR:
                iImageCompressListener.onError((Throwable) msg.obj);
                break;
        }

        return false;
    }

    /**
     * 开始异步压缩
     */
    @UiThread
    private void start(final Context context) {
        if (mPaths == null || mPaths.size() == 0 || iImageCompressListener == null) {
            iImageCompressListener.onError(new NullPointerException("image file cannot be null"));
            return;
        }

        Iterator<String> iterator = mPaths.iterator();
        while (iterator.hasNext()) {
            final String path = iterator.next();
            if (ImageCheck.isImage(path)) {
                AsyncTask.SERIAL_EXECUTOR.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mHandler.sendMessage(mHandler.obtainMessage(MSG_COMPRESS_START));

                            File result = ImageCheck.isNeedCompress(mLeastCompressSize, path) ?
                                    new CompressEngine(path, getImageCacheFile(context, ImageCheck.checkSuffix(path))).compress() :
                                    new File(path);

                            mHandler.sendMessage(mHandler.obtainMessage(MSG_COMPRESS_SUCCESS, result));
                        } catch (IOException e) {
                            mHandler.sendMessage(mHandler.obtainMessage(MSG_COMPRESS_ERROR, e));
                        }
                    }
                });
            } else {
                iImageCompressListener.onError(new FileNotFoundException("can not read the path : " + path));
            }
            iterator.remove();
        }
    }

    /**
     * 获取要压缩单个文件,开始压缩
     * @param path
     * @param context
     * @return
     * @throws IOException
     */
    @WorkerThread
    private File get(String path, Context context) throws IOException {
        return new CompressEngine(path, getImageCacheFile(context, ImageCheck.checkSuffix(path))).compress();
    }

    /**
     * 获取要压缩文件集合,开始压缩
     * @param context
     * @return
     * @throws IOException
     */
    @WorkerThread
    private List<File> get(Context context) throws IOException {
        List<File> results = new ArrayList<>();
        Iterator<String> iterator = mPaths.iterator();
        while (iterator.hasNext()) {
            String path = iterator.next();
            if (ImageCheck.isImage(path)) {
                results.add(new CompressEngine(path, getImageCacheFile(context, ImageCheck.checkSuffix(path))).compress());
            }
            iterator.remove();
        }

        return results;
    }

    /**
     * 建造类
     */
    public static class Builder {

        private Context context;
        private String mTargetDir;
        private List<String> mPaths;
        private int mLeastCompressSize = 100;
        private IImageCompressListener iImageCompressListener;

        Builder(Context context) {
            this.context = context;
            this.mPaths = new ArrayList<>();
        }

        private ImageCompressProvider build() {
            return new ImageCompressProvider(this);
        }

        public Builder load(File file) {
            this.mPaths.add(file.getAbsolutePath());
            return this;
        }

        public Builder load(String string) {
            this.mPaths.add(string);
            return this;
        }

        public Builder load(List<String> list) {
            this.mPaths.addAll(list);
            return this;
        }

        public Builder setCompressLevel(int compressLevel) {
            return this;
        }

        public Builder setIImageCompressListener(IImageCompressListener listener) {
            this.iImageCompressListener = listener;
            return this;
        }

        public Builder setTargetDir(String targetDir) {
            this.mTargetDir = targetDir;
            return this;
        }

        /**
         * fileSize<100,不做处理
         *
         * @param size mLeastCompressSize = 100kb
         */
        public Builder ignoreBy(int size) {
            this.mLeastCompressSize = size;
            return this;
        }

        /**
         * 开始异步压缩
         */
        public void start() {
            build().start(context);
        }

        /**
         * 获取单个文件
         * @param path
         * @return
         * @throws IOException
         */
        public File get(String path) throws IOException {
            return build().get(path, context);
        }

        /**
         * 获取文件集合
         *
         * @return the thumb image file list
         */
        public List<File> get() throws IOException {
            return build().get(context);
        }

    }

}