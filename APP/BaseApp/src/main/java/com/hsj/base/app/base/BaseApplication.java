package com.hsj.base.app.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/2 14:08
 * @Class:
 * @Description:基本Application
 */
public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initModule();
    }

    protected abstract void initModule();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
