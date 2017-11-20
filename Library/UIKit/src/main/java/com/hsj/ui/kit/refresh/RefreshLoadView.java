package com.hsj.ui.kit.refresh;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/11/14/10:00
 * @Class:RefreshLoadView
 * @Description:下拉刷新、上拉加载
 */
public class RefreshLoadView extends View {

    /**
     * 处于刷新状态
     */
    private boolean isRefresh;
    /**
     * 处于加载状态
     */
    private boolean isLoadMore;
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
    private RefreshLoadListener refreshListener;

    public RefreshLoadView(@NonNull Context context) {
        this(context,null);
    }

    public RefreshLoadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshLoadView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRefreshLayout(context, attrs);
    }

    /**
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
     * @param headerLayout
     */
    public void addHeader(@LayoutRes int headerLayout) {

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
     * @param footerLayout
     */
    public void addFooter(@LayoutRes int footerLayout) {

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
    public void setContentView(RecyclerView rv) {

    }

    /**
     * 添加内容：RelativeLayout
     *
     * @param rl
     */
    public void setContentView(RelativeLayout rl) {

    }

    /**
     * 添加内容：LinearLayout
     *
     * @param ll
     */
    public void setContentView(LinearLayout ll) {

    }

    /**
     * 添加内容：ScrollView
     *
     * @param sv
     */
    public void setContentView(ScrollView sv) {

    }

    /**
     * 没有数据设置占位布局
     */
    public void setBackgroundView(@LayoutRes int bgLayout){

    }

    /**
     * 没有数据设置占位布局
     */
    public void setBackgroundView(View bgView){

    }

    /**
     * 添加监听
     *
     * @param refreshListener
     */
    public void setRefreshListener(RefreshLoadListener refreshListener) {
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
    public void  setHeaderText(@Nullable String headerText) {
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
     *  设置刷新状态
     * @param refresh
     */
    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    /**
     * 设置加载状态
     * @param loadMore
     */
    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

    /**
     * 停止刷新or加载状态
     * @param stopRefreshOrLoad
     */
    public void setStopRefreshOrLoad(boolean stopRefreshOrLoad) {
        isStopRefreshOrLoad = stopRefreshOrLoad;
    }

}
