package com.hsj.app.base.core;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/2 14:08
 * @Class:BaseApp
 * @Description:基本Application
 */
public abstract class BaseApp extends Application {

    @SuppressLint("StaticFieldLeak")
    private static BaseApp instance;
    public static Context appContext;
    private RefWatcher mRefWatcher;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        appContext = getApplicationContext();

        mRefWatcher = LeakCanary.install(this);

        initModule();
    }

    /**
     * 初始化各模块
     */
    protected abstract void initModule();

    /**
     * 获取实例
     * @return
     */
    public static BaseApp getInstance() {
        return instance;
    }

    /**
     * 获取观察者
     * @return
     */
    public static RefWatcher getRefWatcher() {
        return getInstance().mRefWatcher;
    }

}
