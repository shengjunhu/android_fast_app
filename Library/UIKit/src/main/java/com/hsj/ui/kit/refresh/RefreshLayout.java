package com.hsj.ui.kit.refresh;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/7/14 18:11
 * @Class:RefreshLoadLayout
 * @Description:刷新、加载控件
 */
public class RefreshLayout extends FrameLayout {

    private boolean isRefresh,isLoadMore;
    private int refreshTime,loadTime;
    private RefreshListener refreshListener;

    public RefreshLayout(@NonNull Context context) {
        super(context);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRefreshLayout();
    }

     /*
        1、下拉刷新：下拉刷新动画
        2、上拉加载布局：上拉刷新动画、文本提示状态(数据加载完毕、没有更多数据、点击加载更多)
        3、加头：支持View、layoutId
        5、加脚：支持View、layoutId
        6、支持RecycleView、LinearLayout、RelativeLayout、ScrollView
        7、当RecycleView无数据，显示无数据占位布局（支持View、layoutId）

     */

    private void initRefreshLayout() {

    }

    /**
     * 添加头
     * @param headerLayout
     */
    public void addHeader(@LayoutRes int headerLayout) {

    }

    /**
     * 添加头
     * @param headerView
     */
    public void addHeader(View headerView) {

    }

    /**
     * 添加脚
     * @param footerLayout
     */
    public void addFooter(@LayoutRes int footerLayout) {

    }

    /**
     * 添加头
     * @param footerView
     */
    public void addFooter(View footerView) {

    }

    /**
     * 添加内容
     * @param rv
     */
    public void setContentView(RecyclerView rv) {

    }

    /**
     * 添加内容
     * @param rl
     */
    public void setContentView(RelativeLayout rl) {

    }

    /**
     * 添加内容
     * @param ll
     */
    public void setContentView(LinearLayout ll) {

    }

    /**
     * 添加内容
     * @param sv
     */
    public void setContentView(ScrollView sv) {

    }

    /**
     * 添加监听
     * @param refreshListener
     */
    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    /**
     * 设置刷新超时时间
    * @param refreshTime milliseconds
     */
    public void setRefreshTime(int refreshTime) {

    }

    /**
     * 设置加载超时时间超时时间
     * @param loadTime milliseconds
     */
    public void setLoadTime(int loadTime) {

    }

    /**
     * 设置脚文字
     * @param footText (数据加载完毕、没有更多数据、点击加载更多)(加载成功、加载失败)
     */
    public void setFootText(@Nullable String footText) {

    }

}
