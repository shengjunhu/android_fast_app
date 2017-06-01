package com.hsj.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:01
 * @Class:App
 * @Description:
 */
public class App extends MultiDexApplication {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) return;
        LeakCanary.install(this);

        appContext = getApplicationContext();

        initUserInfo();
    }

    /**
     * 初始化用户信息
     */
    private void initUserInfo() {

    }

}
