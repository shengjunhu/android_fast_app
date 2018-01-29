package com.app.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:43
 * @Class:SharedPrefer
 * @Description:SharedPreferences管理者
 */
public class SharedPrefer {

    /**
     * AppInfo:App相关信息（版本号、该版本号是否第一次启动、主题、字体样式和颜色）
     */
    public static final String APP_INFO = "app_info";

    /**
     * UserInfo:用户信息(账号、密码、token、设备ID)
     */
    public static final String USER_INFO = "user_info";

    /**
     * ActionInfo: 上一次程序未执行完的操作标志，继续操作
     */
    public static final String ACTION_INFO = "action_info";

    @StringDef({APP_INFO, USER_INFO, ACTION_INFO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SPFile {

    }

    /**
     * 默认sp文件名：
     * 1、AppInfo:    App相关信息（版本号、该版本号第一次登陆）
     * 2、UserInfo:   用户信息(账号、密码、token、设备ID)
     * 3、ActionInfo: 上一次程序未执行完的操作标志，继续操作
     */
    private String fileName = USER_INFO;

    public SharedPrefer(@SPFile String fileName) {
        this.fileName = fileName;
    }

    public void setSharedPreferFile(@SPFile String fileName) {
        this.fileName = fileName;
    }

    /**
     * 可以put ：Number:int,long,float
     * String、boolean、Set<String>
     *
     * @param key
     * @param value
     */
    public void put(@NonNull Context context, @NonNull String key, @NonNull Object value) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (boolean) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (int) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else {
            edit.putStringSet(key, (Set<String>) value);
        }
        edit.apply();
    }

    /**
     * 可以remove ：String Key
     *
     * @param key
     */
    public void remove(@NonNull Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.remove(key);
        edit.apply();
    }

    /**
     * 获取布尔
     *
     * @param key
     * @return
     */
    public boolean getBoolean(@NonNull Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    public String getString(@NonNull Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * 获取字符串集合
     *
     * @param key
     * @return
     */
    public Set<String> getStringSet(@NonNull Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getStringSet(key, null);
    }

    /**
     * 获取整数
     *
     * @param key
     * @return
     */
    public int getInt(@NonNull Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getInt(key, -1);
    }

    /**
     * 获取long
     *
     * @param key
     * @return
     */
    public long getLong(@NonNull Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getLong(key, -1);
    }

    /**
     * 获取float
     *
     * @param key
     * @return
     */
    public float getFloat(@NonNull Context context, @NonNull String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getFloat(key, (float) 0.00);
    }

    /**
     * 清空 SharedPreferences 中数据
     */
    public void clearAll(@NonNull Context context) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.apply();
    }

}
