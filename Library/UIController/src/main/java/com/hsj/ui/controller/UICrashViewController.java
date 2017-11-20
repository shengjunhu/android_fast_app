package com.hsj.ui.controller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/7/3 09:32
 * @Class:UICrashViewController
 * @Description:应用崩溃提示视图
 */
public class UICrashViewController extends View implements View.OnClickListener{

    public UICrashViewController(Context context) {
        this(context,null);
    }

    public UICrashViewController(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UICrashViewController(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    /**
     * 功能：
     * 1、app crash 显示给用户的提示视图
     * 2、触摸后立即重启应用
     */
    private void initView() {

    }

    @Override
    public void onClick(View v) {

    }
}
