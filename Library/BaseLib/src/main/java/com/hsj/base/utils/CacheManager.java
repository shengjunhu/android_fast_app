package com.hsj.base.utils;

import android.content.Context;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/07/23 00:30
 * @Class:CacheManager
 * @Description:缓存管理者
 */
public class CacheManager {

    /**
     * 获取应缓存大小
     * @param context
     * @return
     */
    public static double getChacheSize(Context context){
        return 0.0;
    }

    /**
     * 在子线程执行清除本地缓存
     * 注：数据库 -> 用户信息默认不清理
     */
    public static void clearCache(){

    }

    /**
     * 在主线程执行清除内存缓存
     */
    public static void clearMemoryCache(){

    }

}
