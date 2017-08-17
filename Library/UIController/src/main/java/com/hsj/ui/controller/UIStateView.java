package com.hsj.ui.controller;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/28 12:32
 * @Class:StateView
 * @Description:界面加载视图
 */
public class UIStateView extends FrameLayout {

    /**
     * 没有运行权限（点击去设置权限）
     */
    public static final int STATE_PERMISSION = -3;

    /**
     * 没有登陆(点击去登陆)
     */
    public static final int STATE_NO_LOGIN   = -2;

    /**
     * 加载失败(点击视图，可重新请求网络)
     */
    public static final int STATE_FAILURE    = -1;

    /**
     * 加载中
     */
    public static final int STATE_LOAD       = 0;

    /**
     * 加载成功（销毁本控件）
     */
    public static final int STATE_SUCCESS    = 1;

    /**
     * 加载成功却无数据（显示无数据占位图）
     */
    public static final int STATE_EMPTY      = 2;

    @IntDef({STATE_PERMISSION, STATE_NO_LOGIN, STATE_FAILURE, STATE_LOAD, STATE_SUCCESS, STATE_EMPTY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {

    }

    /**
     * 设置状态：每设置一个状态销毁前一个状态
     */
    public void setState(@State int uiState) {

    }

    public UIStateView(@NonNull Context context) {
        super(context);
        initUIStateView();
    }

    public UIStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUIStateView();
    }

    public UIStateView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUIStateView();
    }

    private void initUIStateView() {

    }


}

