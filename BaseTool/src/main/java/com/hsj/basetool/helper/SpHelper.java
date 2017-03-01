package com.hsj.basetool.helper;

import android.content.Context;
import android.content.SharedPreferences;
import com.hsj.basetool.base.BaseToolContext;

public class SpHelper {

    private static String config = BaseToolContext.SpConfigName;

    private static Context mContext = BaseToolContext.mContext;

    /**
     * 可以put ：Int String Boolean类型数据
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        SharedPreferences sp = mContext.getSharedPreferences(config, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (int) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (boolean) value);
        }
        edit.commit();
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(config, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * 获取整数
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(config, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    /**
     * 获取布尔
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(config, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 清空 SharedPreferences 中数据
     */
    public void clearAll() {
        SharedPreferences sp = mContext.getSharedPreferences(config, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.commit();
    }

}
