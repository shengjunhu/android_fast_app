package com.hsj.app.base;

import android.content.Context;
import com.hsj.baseapp.BaseApplication;
import com.hsj.baseapp.BuildConfig;
import com.squareup.leakcanary.LeakCanary;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:01
 * @Class:App
 * @Description:
 */
public class App extends BaseApplication {

    public static Context appContext;

    @Override
    protected void initModule() {

        if(BuildConfig.DEBUG){
            if (LeakCanary.isInAnalyzerProcess(this)) return;
            LeakCanary.install(this);

        }else {

        }

        appContext = getApplicationContext();

    }

}
