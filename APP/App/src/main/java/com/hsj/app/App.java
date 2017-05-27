package com.hsj.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;

/**
 * @Company:南京荣之誉信息科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:01
 * @Version:XBS V2.0
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
