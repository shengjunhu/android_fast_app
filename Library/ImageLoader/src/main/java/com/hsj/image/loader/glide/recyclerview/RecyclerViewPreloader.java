package com.hsj.image.loader.glide.recyclerview;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.ListPreloader.PreloadModelProvider;
import com.bumptech.glide.ListPreloader.PreloadSizeProvider;
import com.bumptech.glide.RequestManager;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/10/9/16:07
 * @Class:RecyclerViewPreloader
 * @Description:glide在RecyclerView中预加载
 */
public final class RecyclerViewPreloader<T> extends RecyclerView.OnScrollListener {

    private final RecyclerToListViewScrollListener recyclerScrollListener;

    /**
     * Helper constructor that accepts an {@link AppCompatActivity}.
     */
    public RecyclerViewPreloader(AppCompatActivity activity,
                                 PreloadModelProvider<T> preloadModelProvider,
                                 PreloadSizeProvider<T> preloadDimensionProvider,
                                 int maxPreload) {

        this(Glide.with(activity), preloadModelProvider, preloadDimensionProvider, maxPreload);
    }

    /**
     * Helper constructor that accepts an {@link Fragment}.
     */
    public RecyclerViewPreloader(Fragment fragment,
                                 PreloadModelProvider<T> preloadModelProvider,
                                 PreloadSizeProvider<T> preloadDimensionProvider,
                                 int maxPreload) {

        this(Glide.with(fragment), preloadModelProvider, preloadDimensionProvider, maxPreload);
    }
    /**
     * Constructor that accepts interfaces for providing the dimensions of images to preload, the list
     * of models to preload for a given position, and the request to use to load images.
     *
     * @param preloadModelProvider     Provides models to load and requests capable of loading them.
     * @param preloadDimensionProvider Provides the dimensions of images to load.
     * @param maxPreload               Maximum number of items to preload.
     */
    public RecyclerViewPreloader(RequestManager requestManager,
                                 PreloadModelProvider<T> preloadModelProvider,
                                 PreloadSizeProvider<T> preloadDimensionProvider,
                                 int maxPreload) {

        ListPreloader<T> listPreloader = new ListPreloader<>(requestManager,
                preloadModelProvider,
                preloadDimensionProvider,
                maxPreload);

        recyclerScrollListener = new RecyclerToListViewScrollListener(listPreloader);
    }

    /**
     * 滑动监听 提前预加载
     * @param recyclerView
     * @param dx
     * @param dy
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        recyclerScrollListener.onScrolled(recyclerView, dx, dy);
    }

}
