package com.hsj.basetool.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.hsj.basetool.helper.FileHelper;
import java.io.InputStream;

public class OkHttpGlideModule implements GlideModule {

    public OkHttpGlideModule() {
    }

    /**
     * 配置Glide加载图片参数
     *      1、图片加载缓存路径默认优先使用SD卡的缓存路径，没有SD卡选择内部存储的缓存路径
     *      2、磁盘缓存优化，图片缓存目录 image，申请缓存大小为可用磁盘的1/4
     *      3、根据设备参数配置动态配置内存缓存大小和Bitmap池的大小
     * @param context
     * @param builder
     */
    public void applyOptions(Context context, GlideBuilder builder) {

        String cacheDir = FileHelper.getCacheDir(context);
        int imageCacheSize = (int) (FileHelper.getStorageSize()/4);

        builder.setDiskCache(new DiskLruCacheFactory(cacheDir, "image", imageCacheSize));

        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize));
    }

    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

}
