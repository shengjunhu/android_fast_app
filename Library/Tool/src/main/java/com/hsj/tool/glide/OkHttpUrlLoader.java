package com.hsj.tool.glide;

import android.content.Context;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  FastAndroid V1.0
 * @Date        :  2017/3/24 11:08
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  OkHttpUrlLoader
 * @Description :  Glide网络加载图片配置
 */
public class OkHttpUrlLoader implements ModelLoader<GlideUrl, InputStream> {
    private final okhttp3.Call.Factory client;

    public OkHttpUrlLoader(okhttp3.Call.Factory client) {
        this.client = client;
    }

    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        return new OkHttpStreamFetcher(this.client, model);
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private static volatile okhttp3.Call.Factory internalClient;
        private okhttp3.Call.Factory client;

        public Factory() {
            this(getInternalClient());
        }

        public Factory(okhttp3.Call.Factory client) {
            this.client = client;
        }

        private static okhttp3.Call.Factory getInternalClient() {
            if(internalClient == null) {
                Class var0 = Factory.class;
                synchronized(Factory.class) {
                    if(internalClient == null) {
                        internalClient = new OkHttpClient();
                    }
                }
            }

            return internalClient;
        }

        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new OkHttpUrlLoader(this.client);
        }

        public void teardown() {
        }
    }
}