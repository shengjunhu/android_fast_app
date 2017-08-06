package com.hsj.app.base.uitls;

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
     * 1、AppInfo:  App相关信息（版本号、该版本号第一次登陆）
     * 2、UserInfo: 用户信息(账号、密码、token、设备ID)
     */
    private String fileName = "config";

    /**
     * 动态配置sp文件名
     * @param fileName
     */
    public void setFileName(@NonNull String fileName) {
        this.fileName = fileName;
    }

    /**
     * 可以set ：Int String Boolean类型数据
     *
     * @param key
     * @param value
     */
    public void set(Context context, String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (int) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (boolean) value);
        }
        edit.apply();
    }

    /**
     * 获取字符串
     * @param key
     * @return
     */
    public String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * 获取整数
     * @param key
     * @return
     */
    public int getInt(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    /**
     * 获取布尔
     * @param key
     * @return
     */
    public boolean getBoolean(Context context, String key) {
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
