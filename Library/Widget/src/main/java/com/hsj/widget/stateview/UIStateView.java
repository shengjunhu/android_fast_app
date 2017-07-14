package com.hsj.widget.stateview;

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
     * 加载中
     */
    public static final int STATE_LOAD      = 0;
    /**
     * 加载成功
     */
    public static final int STATE_SUCCESS   = 1;
    /**
     * 加载失败(点击视图，可重新请求网络)
     */
    public static final int STATE_FAILURE   = -1;
    /**
     * 加载成功却无数据
     */
    public static final int STATE_EMPTY     = 2;

    @IntDef({STATE_LOAD, STATE_SUCCESS, STATE_FAILURE, STATE_EMPTY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {

    }

    /**
     * 设置状态
     */
    public void setState(@State int uiState){

    }

    public UIStateView(@NonNull Context context) {
        super(context);
    }

    public UIStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UIStateView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUIStateView();
    }

    private void initUIStateView() {

    }


}

