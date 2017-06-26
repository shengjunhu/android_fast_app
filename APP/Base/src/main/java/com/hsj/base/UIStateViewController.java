package com.hsj.base;

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
 * @Date:2017/5/27 15:17
 * @Class:UIStateViewController
 * @Description:数据显示状态
 */
public class UIStateViewController extends FrameLayout{
    /**
     * 界面四种状态：
     * 1、网络数据加载中
     * 2、网络数据请求成功
     * 3、网络故障
     * 4、网络数据请求失败
     */
    public static final int STATE_LOAD    = 1;
    public static final int STATE_SUESSES = 2;
    public static final int STATE_EMPTY   = 3;
    public static final int STATE_NETWORK = 4;
    public static final int STATE_ERROR   = -1;

    public UIStateViewController(@NonNull Context context) {
        super(context);
    }

    public UIStateViewController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UIStateViewController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
