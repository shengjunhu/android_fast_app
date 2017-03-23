package com.hsj.core.tool;

import android.content.Context;
import android.content.SharedPreferences;

import com.hsj.core.BaseContext;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/2/21 12:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  SpTool
 * @Description :  首选项帮助者
 */
public class SpTool {

    private static String config = BaseContext.SpConfigName;

    private static Context mContext = BaseContext.mContext;

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
        edit.apply();
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
        ed.apply();
    }

}
