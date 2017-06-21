package com.hsj.tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:43
 * @Class:SPManager
 * @Description: SharedPreferences工具类
 */
public class SPManager {

    private String config = "Config";

    /**
     * 可以put ：Int String Boolean类型数据
     *
     * @param key
     * @param value
     */
    public void put(Context context, String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences(config, Context.MODE_PRIVATE);
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
     *
     * @param key
     * @return
     */
    public String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(config, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * 获取整数
     *
     * @param key
     * @return
     */
    public int getInt(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(config, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    /**
     * 获取布尔
     *
     * @param key
     * @return
     */
    public boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(config, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 清空 SharedPreferences 中数据
     */
    public void clearAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(config, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.apply();
    }


}
