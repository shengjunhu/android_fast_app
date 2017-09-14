package com.hsj.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:43
 * @Class:SharedPrefer
 * @Description:SharedPreferences管理者
 */
public class SharedPrefer {

    /**
     * 默认sp文件名：
     * 1、AppInfo:    App相关信息（版本号、该版本号第一次登陆）
     * 2、UserInfo:   用户信息(账号、密码、token、设备ID)
     * 3、ActionInfo: 上一次程序未执行完的操作标志，继续操作
     */
    private String fileName = "config";

    /**
     * 动态配置sp文件名
     *
     * @param fileName
     */
    public void setFileName(@NonNull String fileName) {
        this.fileName = fileName;
    }

    /**
     * 可以set ：int 类型数据
     *
     * @param key
     * @param value
     */
    public void setInt(Context context, @NonNull String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    /**
     * 可以set ：String类型数据
     *
     * @param key
     * @param value
     */
    public void setString(Context context, @NonNull String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.apply();
    }

    /**
     * 可以set ：boolean类型数据
     *
     * @param key
     * @param value
     */
    public void setBoolean(Context context, @NonNull String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    public String getString(Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * 获取整数
     *
     * @param key
     * @return
     */
    public int getInt(Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getInt(key, -1);
    }

    /**
     * 获取布尔
     *
     * @param key
     * @return
     */
    public boolean getBoolean(Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 清空 SharedPreferences 中数据
     */
    public void clearAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.apply();
    }

}
