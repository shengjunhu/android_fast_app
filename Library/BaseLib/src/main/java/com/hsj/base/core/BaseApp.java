package com.hsj.base.core;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/2 14:08
 * @Class:BaseApp
 * @Description:基本Application
 */
public class BaseApp extends Application {

    public BaseApp instance;
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

        if (isInMainProcess()) {
            instance = this;

            Thread.setDefaultUncaughtExceptionHandler(new AppCrashHandler(this));

            initModule();
        }

    }

    /**
     * 初始化各模块
     */
    protected void initModule() {

    }

    /**
     * 获取当前是否在主进程
     *
     * @return
     */
    public boolean isInMainProcess() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        if (am == null) return false;
        List<ActivityManager.RunningAppProcessInfo> processInfo = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfo) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

}
