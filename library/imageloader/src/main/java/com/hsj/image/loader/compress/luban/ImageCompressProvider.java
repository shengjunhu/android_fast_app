package com.hsj.image.loader.compress.luban;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hsj.image.loader.interfaces.IImageCompressListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/9/18/20:17
 * @Class:ImageCompressProvider
 * @Description:图片压缩提供者
 */
public class ImageCompressProvider implements Handler.Callback {

    /**
     * 存放目录
     */
    private static final String DEFAULT_DISK_CACHE_DIR = "/image/compress";

    private Handler mHandler;
    private static final int MSG_COMPRESS_SUCCESS = 0;
    private static final int MSG_COMPRESS_START = 1;
    private static final int MSG_COMPRESS_ERROR = 2;
    /**
     * 压缩当前位置
     */
    private int currentPosition = 0;
    private int allPosition = 0;
    /**
     * 压缩后的文件
     */
    private List<File> compressFiles;
    /**
     * 压缩文件集合
     */
    private List<String> filePaths;
    /**
     * 存放根目录
     */
    private File cacheRootDir;
    /**
     * 默认存放的目录 cachePath + DEFAULT_DISK_CACHE_DIR
     */
    private String mTargetDir;
    /**
     * 允许压缩最小体积
     */
    private int mLeastCompressSize;
    /**
     * 压缩监听
     */
    private IImageCompressListener iImageCompressListener;

    private ImageCompressProvider(Builder builder) {
        this.filePaths = builder.filePaths;
        this.mLeastCompressSize = builder.mLeastCompressSize;
        this.mTargetDir = builder.mTargetDir;
        this.iImageCompressListener = builder.iImageCompressListener;
        this.mHandler = new Handler(Looper.getMainLooper(), this);
    }

    /**
     * 实例化建造者
     *
     * @param context
     * @return
     */
    public static Builder with(Context context) {
        return new Builder(context);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (iImageCompressListener == null) return false;
        switch (msg.what) {
            case MSG_COMPRESS_START:
                iImageCompressListener.onCompressStart();
                break;
            case MSG_COMPRESS_SUCCESS:
                ++currentPosition;
                compressFiles.add((File) msg.obj);
                if (currentPosition == filePaths.size()) {
                    iImageCompressListener.onCompressStop(compressFiles);
                } else {
                    iImageCompressListener.onCompressProgress(currentPosition, allPosition);
                }
                break;
            case MSG_COMPRESS_ERROR:
                iImageCompressListener.onCompressError((Throwable) msg.obj);
                break;
        }
        return false;
    }

    /**
     * 开始异步压缩
     */
    @MainThread
    private void start(final Context context) {
        if (filePaths == null || filePaths.size() == 0) {
            iImageCompressListener.onCompressError(new NullPointerException("image file cannot be null"));
            return;
        }

        if (iImageCompressListener == null) {
            throw new NullPointerException("IImageCompressListener is null");
        }

        cacheRootDir = getImageCompressDir(context);
        allPosition = filePaths.size();
        compressFiles = new ArrayList<>();

        mHandler.sendMessage(mHandler.obtainMessage(MSG_COMPRESS_START));
        Iterator<String> iterator = filePaths.iterator();
        while (iterator.hasNext()) {
            final String path = iterator.next();
            if (ImageCheck.isImage(path)) {
                AsyncTask.SERIAL_EXECUTOR.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            File result = ImageCheck.isNeedCompress(mLeastCompressSize, path) ?
                                    new CompressEngine(path, setCompressImageFile(context, ImageCheck.checkSuffix(path))).compress() :
                                    new File(path);

                            mHandler.sendMessage(mHandler.obtainMessage(MSG_COMPRESS_SUCCESS, result));
                        } catch (IOException e) {
                            mHandler.sendMessage(mHandler.obtainMessage(MSG_COMPRESS_ERROR, e));
                        }
                    }
                });
            } else {
                iImageCompressListener.onCompressError(new FileNotFoundException("can not read the file : " + path));
            }

            iterator.remove();
        }
    }

    /**
     * 获取压缩文件的根目录
     * @param context
     */
    private File getImageCompressDir(Context context) {
        File cacheDir = null;
        if (TextUtils.isEmpty(mTargetDir)) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheDir = context.getExternalCacheDir();
                cacheDir = new File(cacheDir, DEFAULT_DISK_CACHE_DIR);
            } else {
                cacheDir = context.getCacheDir();
                cacheDir = new File(cacheDir, DEFAULT_DISK_CACHE_DIR);
            }
        } else {
            cacheDir = new File(mTargetDir);
        }
        return cacheDir;
    }

    /**
     * 设置文件名
     *
     * @param context
     * @param suffix
     * @return
     */
    private File setCompressImageFile(Context context, String suffix) {
        String compressFileName = cacheRootDir + "/" +
                System.currentTimeMillis() +
                (TextUtils.isEmpty(suffix) ? ".jpg" : suffix);

        return new File(compressFileName);
    }

    /**
     * 删除压缩目录
     *
     * @return
     */
    private void deletImageCacheDir() {

    }

    /**
     * 建造类
     */
    public static class Builder {

        private Context context;
        private String mTargetDir;
        private List<String> filePaths;
        private int mLeastCompressSize = 100;
        private IImageCompressListener iImageCompressListener;

        Builder(Context context) {
            this.context = context;
            this.filePaths = new ArrayList<>();
        }

        private ImageCompressProvider build() {
            return new ImageCompressProvider(this);
        }

        public Builder load(@NonNull File file) {
            this.filePaths.add(file.getAbsolutePath());
            return this;
        }

        public Builder load(String filePath) {
            this.filePaths.add(filePath);
            return this;
        }

        public Builder load(List<String> filePaths) {
            this.filePaths.addAll(filePaths);
            return this;
        }

        public Builder setIImageCompressListener(IImageCompressListener listener) {
            this.iImageCompressListener = listener;
            return this;
        }

        /**
         * 存放目录 : 全路径
         *
         * @param targetDir
         * @return
         */
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
    }

}