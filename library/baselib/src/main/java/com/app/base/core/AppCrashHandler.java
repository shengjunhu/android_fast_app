package com.app.base.core;

import android.content.Context;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/12/25/13:00
 * @Class:AppCrashHandler
 * @Description:全局异常抓捕类
 */
public class AppCrashHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    public AppCrashHandler(Context context) {
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable e) {

    }
}
