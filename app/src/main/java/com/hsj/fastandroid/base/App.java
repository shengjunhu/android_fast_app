package com.hsj.fastandroid.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  FastAndroid V1.0
 * @Date        :  2017/3/22 12:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  App
 * @Description :  主Module Application初始化
 */
public class App extends MultiDexApplication {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }


}
