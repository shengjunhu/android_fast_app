package com.hsj.ui.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.AttrRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/28 12:32
 * @Class:StateView
 * @Description:界面加载视图
 */
public class UIStateView extends View implements View.OnClickListener {

    /**
     * 界面当前状态
     */
    private int currentState;

    private Context context;

    /**
     * 获取权限后，刷新数据
     */
    private IStateView iStateView;

    /**
     * 没有运行权限（点击去设置权限）
     */
    public static final int STATE_PERMISSION = -4;

    /**
     * 没有登陆(点击去登陆)
     */
    public static final int STATE_LOGIN = -3;

    /**
     * 服务器异常(点击视图，可重新请求网络)
     */
    public static final int STATE_FAILURE = -2;

    /**
     * 网络异常（去打开网络）
     */
    public static final int STATE_NETWORK = -1;

    /**
     * 加载中
     */
    public static final int STATE_LOAD = 0;

    /**
     * 加载成功（销毁本控件）
     */
    public static final int STATE_SUCCESS = 1;

    /**
     * 加载成功却无数据（显示无数据占位图）
     */
    public static final int STATE_EMPTY = 2;

    @IntDef({STATE_PERMISSION, STATE_LOGIN, STATE_FAILURE, STATE_NETWORK, STATE_LOAD, STATE_SUCCESS, STATE_EMPTY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {

    }

    public UIStateView(@NonNull Context context) {
        this(context,null);
    }

    public UIStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UIStateView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUIStateView(context, attrs);
    }

    /**
     * 初始化视图
     *
     * @param context
     * @param attrs
     */
    private void initUIStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this.setOnClickListener(this);

        //TODO 控件初始化

        //TODO 自我检测运行权限
    }

    /**
     * 设置状态：每设置一个状态销毁前一个状态
     */
    public void setUIState(@State int uiCurrentState) {

    }

    @Override
    public void onClick(View view) {
        switch (currentState) {
            case -4:
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
                break;
            case -3:
                break;
            case -2:
                break;
            case -1:
                break;
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }

    /**
     * 若不需运行权限，可不设置
     *
     * @param iStateView
     */
    public void setiStateView(IStateView iStateView) {
        this.iStateView = iStateView;
    }

}

