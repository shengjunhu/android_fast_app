package com.hsj.widget.recycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/2 17:28
 * @Class:XRecycleView
 * @Description: 自定义RecycleView： * 主要实现下拉刷新、添加头、添加脚、上拉加载更多、item点击事件、item长按事件
 */
public class XRecycleView extends RecyclerView {
    /**
     * XRecycleView监听者:刷新、加载
     */
    private IXRecyclerViewListener mIXRecyclerViewListener;
    /**
     * RecyclerView Item监听z者：点击和长按
     */
    private IXItemOnClickListener mIXItemOnClickListener;
    /**
     * 默认可刷新
     */
    private boolean isRefresh = true;
    /**
     * 默认可加载更多
     */
    private boolean isLoadMore = true;
    /**
     * 默认不可自动加载
     */
    private boolean isAutoLoadMore = false;
    /**
     * 添加头布局
     */
    private View headerView;
    /**
     * 添加脚布局
     */
    private View footerView;
    /**
     * 默认无数据提示布局
     */
    private View emptyView;
    /**
     * 传入的adapter
     */
    private Adapter adapter;

    public XRecycleView(Context context) {
        super(context);
        initXRecycleView(context);
    }

    public XRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initXRecycleView(context);
    }

    public XRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initXRecycleView(context);
    }

    /**
     * 第一步：RecyclerView设置监听
     * @param mXIRecyclerViewListener
     */
    public void setRecyclerViewListener(IXRecyclerViewListener mXIRecyclerViewListener){
        this.mIXRecyclerViewListener = mXIRecyclerViewListener;
    }

    /**
     * 第一步：RecyclerView Item设置监听
     * @param mIXItemOnClickListener
     */
    public void setItemOnClickListener(IXItemOnClickListener mIXItemOnClickListener){
        this.mIXItemOnClickListener = mIXItemOnClickListener;
    }

    /**
     * 第二步：设置是否可刷新
     * @param isRefresh
     */
    public void setOnRefresh(boolean isRefresh){
        this.isRefresh = isRefresh;
    }

    /**
     * 第二步：设置是否可加载更多
     * @param isLoadMore
     */
    public void setOnLoadMore(boolean isLoadMore){
        this.isLoadMore = isLoadMore;
    }

    /**
     * 第二步：设置是否可自动加载更多
     * @param isAutoLoadMore
     */
    public void setOnAutoLoadMore(boolean isAutoLoadMore){
        this.isAutoLoadMore = isAutoLoadMore;
    }

    /**
     * 第三步：添加头布局
     * @param headerView
     */
    public void addHeaderView(View headerView){
        this.headerView = headerView;
    }

    /**
     * 第三步：添加脚布局
     * @param footerView
     */
    public void addFooterView(View footerView){
        this.footerView = footerView;
    }

    /**
     * 第四步：设置数据为空时提示布局
     * @param emptyView
     */
    public void setDataEmptyView(View emptyView){
        this.emptyView = emptyView;
    }

    /**
     * 第五步：传入适配器
     * @param adapter
     */
    public void setAdapter(Adapter adapter){
        this.adapter = adapter;
    }

    /**
     * 初始化自定义XRecycleView
     * @param context
     */
    private void initXRecycleView(Context context){

    }

}
