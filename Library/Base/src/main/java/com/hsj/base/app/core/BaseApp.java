package com.hsj.base.app.core;

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

        initLeakCanary();

        initModule();
    }

    protected abstract void initModule();

    private void initLeakCanary() {
        mRefWatcher = LeakCanary.install(this);
    }

    public static BaseApp getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher() {
        return getInstance().mRefWatcher;
    }


}
