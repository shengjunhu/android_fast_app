package com.hsj.base.lib.core;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
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
    public static BaseApp instance;
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

        if (LeakCanary.isInAnalyzerProcess(this)) {
            mRefWatcher = RefWatcher.DISABLED;
        } else {
            mRefWatcher = LeakCanary.install(this);
        }

        if (isInMainProcess(this)) {
            instance = this;

            appContext = getApplicationContext();

            initModule();
        }

    }

    /**
     * 初始化各模块
     */
    protected abstract void initModule();

    /**
     * 获取实例
     *
     * @return
     */
    public static BaseApp getInstance() {
        return instance;
    }

    /**
     * 获取观察者
     *
     * @return
     */
    public static RefWatcher getRefWatcher() {
        return getInstance().mRefWatcher;
    }

    /**
     * 获取当前是否在主进程
     *
     * @param context
     * @return
     */
    public static boolean isInMainProcess(Context context) {
        String mainProcess = context.getApplicationInfo().packageName;
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                if (mainProcess.equals(appProcess.processName)){
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }

}
