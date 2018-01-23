package com.hsj.image.loader.glide.engine;

import android.content.Context;
import android.os.Environment;
import android.text.format.Formatter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.hsj.image.loader.R;

import java.io.File;
import java.io.InputStream;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/15 10:21
 * @Class:OkHttpGlide
 * @Description:图片加载者
 */
@GlideModule(glideName = "ImageLoader")
public class OkHttpGlide extends AppGlideModule {

    /**
     * 默认磁盘缓存大小200MB
     */
    private int DEFAULT_IMAGE_CACHE_SIZE = 200 * 1024 * 1024;

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        /**
         * 1、内存缓存、bitmap缓存池 ： 采用默认
         * 2、磁盘缓存：
         */
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File sdDir = Environment.getExternalStorageDirectory();
            long usableSpace = sdDir.getUsableSpace();
            String usableSpaceFormat = Formatter.formatFileSize(context, usableSpace);
            if (usableSpace < DEFAULT_IMAGE_CACHE_SIZE) {
                Toast.makeText(context, context.getString(R.string.storage_hint) + usableSpaceFormat, Toast.LENGTH_SHORT).show();
            } else {
                builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "image", DEFAULT_IMAGE_CACHE_SIZE));
            }
        } else {
            File dataDir = Environment.getDataDirectory();
            long usableSpace = dataDir.getUsableSpace();
            String usableSpaceFormat = Formatter.formatFileSize(context, usableSpace);
            if (usableSpace < DEFAULT_IMAGE_CACHE_SIZE) {
                Toast.makeText(context, context.getString(R.string.storage_hint) + usableSpaceFormat, Toast.LENGTH_SHORT).show();
            } else {
                builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "image", DEFAULT_IMAGE_CACHE_SIZE));
            }
        }
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

}
