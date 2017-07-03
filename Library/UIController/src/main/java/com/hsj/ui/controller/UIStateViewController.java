package com.hsj.ui.controller;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/28 12:32
 * @Class:StateView
 * @Description:界面加载视图
 */
public class UIStateViewController extends FrameLayout {

    /**
     * 功能：
     * 0、网络加载中状态  ->显示网络加载布局
     * 1、加载成功状态   ->显示界面并显示数据
     * 2、网络断开状态   ->显示网络断开布局
     * 3、网络成功无数据 ->显示无数据布局
     * 4、服务器异常状态 ->显示服务器异常布局
     * 5、在2,3,4三个状态，触摸界面即可重新请求数据
     */

    public UIStateViewController(@NonNull Context context) {
        super(context);
    }

    public UIStateViewController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UIStateViewController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UIStateViewController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

