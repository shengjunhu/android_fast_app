package com.hsj.fastandroid.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * @Company: 南京荣之誉信息科技有限公司
 * @Author: HSJ
 * @E-mail: mr.ajun@foxmail.com
 * @Date: 2017/3/1 11:27
 * @Version: XBS V2.0
 * @Class: App
 * @Description: 主Module Application初始化
 */
public class App extends MultiDexApplication {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }


}
