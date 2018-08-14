package com.hsj.common.core;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/6/2 14:08
 * @Class:BaseApp
 * @Description:基本Application
 */
public abstract class BaseApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        initModule();
    }

    /**
     * 初始化各模块
     */
    protected abstract void initModule();

}
