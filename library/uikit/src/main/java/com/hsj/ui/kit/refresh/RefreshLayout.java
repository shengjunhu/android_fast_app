package com.hsj.ui.kit.refresh;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/11/14/10:00
 * @Class:RefreshLayout
 * @Description:刷新布局
 */
public class RefreshLayout extends ViewGroup implements NestedScrollingParent, NestedScrollingChild {

    //NestedScrollingChild
    //NestedScrollingParent
    //NestedScrollingChildHelper
    //NestedScrollingParentHelper

    /**
     * 是否可刷新
     */
    private boolean isRefresh;
    /**
     * 是否可加载
     */
    private boolean isLoadMore;
    /**
     * 是否自动刷新
     */
    private boolean isAutoRefresh;
    /**
     * 是否自动加载
     */
    private boolean isAutoLoadMore;
    /**
     * 停止刷新或加载（无论出于刷新or加载更多）
     */
    private boolean isStopRefreshOrLoad;
    /**
     * 刷新时间
     */
    private long refreshTime = 2000;
    /**
     * 加载时间
     */
    private long loadTime = 2000;
    /**
     * 刷新各状态提示文字
     */
    private String headerText;
    /**
     * 加载各状态提示问题
     */
    private String footerText;
    /**
     * 监听刷新状态
     */
    private RefreshListener refreshListener;

    public RefreshLayout(@NonNull Context context) {
        super(context);
        initRefreshLayout(context, null);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initRefreshLayout(context, attrs);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRefreshLayout(context, attrs);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initRefreshLayout(context, attrs);
    }

    /**
     * 0、单纯的上拉/下拉布局滚动，回弹
     * 1、下拉刷新：下拉刷新动画
     * 2、上拉加载布局：上拉刷新动画、文本提示状态(数据加载完毕、没有更多数据、点击加载更多)
     * 3、加头：支持View、layoutId
     * 4、加脚：支持View、layoutId
     * 5、支持RecycleView、LinearLayout、RelativeLayout、ScrollView
     * 6、当RecycleView无数据，显示无数据占位布局（支持View、layoutId）
     */
    private void initRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {

    }

    /**
     * 添加头
     *
     * @param headerViewId
     */
    public void addHeader(@LayoutRes int headerViewId) {

    }

    /**
     * 添加头
     *
     * @param headerView
     */
    public void addHeader(View headerView) {

    }

    /**
     * 添加脚
     *
     * @param footerViewId
     */
    public void addFooter(@LayoutRes int footerViewId) {

    }

    /**
     * 添加头
     *
     * @param footerView
     */
    public void addFooter(View footerView) {

    }

    /**
     * 添加内容：RecyclerView
     *
     * @param rv
     */
    public void setLayout(RecyclerView rv) {

    }

    /**
     * 添加内容：RelativeLayout
     *
     * @param rl
     */
    public void setLayout(RelativeLayout rl) {

    }

    /**
     * 添加内容：LinearLayout
     *
     * @param ll
     */
    public void setLayout(LinearLayout ll) {

    }

    /**
     * 添加内容：ScrollView
     *
     * @param sv
     */
    public void setLayout(ScrollView sv) {

    }

    /**
     * 没有数据设置占位布局
     */
    public void setBackgroundView(@LayoutRes int bgLayout) {

    }

    /**
     * 没有数据设置占位布局
     */
    public void setBackgroundView(View bgView) {

    }

    /**
     * 添加监听
     *
     * @param refreshListener
     */
    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    /**
     * 设置刷新超时时间
     *
     * @param refreshTime milliseconds
     */
    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    /**
     * 设置加载超时时间超时时间
     *
     * @param loadTime milliseconds
     */
    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    /**
     * 设置刷新头 文字
     *
     * @param headerText (刷新时期状态)
     */
    public void setHeaderText(@Nullable String headerText) {
        this.headerText = headerText;
    }

    /**
     * 设置加载脚 文字
     *
     * @param footerText (加载时各文本状态)
     */
    public void setFooterText(@Nullable String footerText) {
        this.footerText = footerText;
    }

    /**
     * 设置刷新状态
     *
     * @param refresh
     */
    public void setRefreshEnable(boolean refresh) {
        isRefresh = refresh;
    }

    /**
     * 设置刷新状态
     *
     * @param autoRefresh
     */
    public void setAutoRefreshEnable(boolean autoRefresh) {
        isRefresh = autoRefresh;
    }

    /**
     * 设置加载状态
     *
     * @param loadMore
     */
    public void setLoadMoreEnable(boolean loadMore) {
        isLoadMore = loadMore;
    }

    /**
     * 设置自动加载更多
     *
     * @param autoLoadMore
     */
    public void setAutoLoadMoreEnable(boolean autoLoadMore) {
        isRefresh = autoLoadMore;
    }

    /**
     * 停止刷新or加载状态
     *
     * @param stopRefreshOrLoad
     */
    public void setStop(boolean stopRefreshOrLoad) {
        isStopRefreshOrLoad = stopRefreshOrLoad;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 开始加载在Window上
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /**
     * 从window上移除
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return false;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {

    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }
}
