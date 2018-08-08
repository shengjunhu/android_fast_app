/*
 *   Copyright (c) 2017.  HSJ
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.hsj.ui.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/6/28 12:32
 * @Class:StateView
 * @Description:界面加载视图
 */
public class UIStatusView extends View implements View.OnClickListener {

    /**
     * 界面当前状态
     */
    private int currentStatus;

    private Context context;

    /**
     * 获取权限后，刷新数据
     */
    private IStateView iStateView;

    public UIStatusView(@NonNull Context context) {
        this(context,null);
    }

    public UIStatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UIStatusView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUIStatusView(context, attrs);
    }

    /**
     * 初始化视图
     *
     * @param context
     * @param attrs
     */
    private void initUIStatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this.setOnClickListener(this);

        //TODO 控件初始化

        //TODO 自我检测运行权限
    }

    /**
     * 设置状态：每设置一个状态销毁前一个状态
     */
    public void setUIStatus(@UIStatus int uiStatus) {

    }

    @Override
    public void onClick(View view) {
        switch (currentStatus) {
            case 0://加载中

                break;
            case 1://加载成功有数据

                break;
            case -1://加载成功无数据

                break;
            case -2://数据异常

                break;
            case -3://网络异常

                break;
            case -4://服务器异常

                break;
            case -5://没有登录

                break;
            case -6://没有查看/执行权限

                break;
            case -7://没有运行权限
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
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
    public void setStatusView(IStateView iStateView) {
        this.iStateView = iStateView;
    }

}

