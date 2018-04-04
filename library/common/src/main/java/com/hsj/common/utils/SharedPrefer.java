/*
 *   Copyright (c) 2017.  HSJ
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.hsj.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.hsj.common.annotations.SPFile;
import java.util.Set;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:43
 * @Class:SharedPrefer
 * @Description:SharedPreferences管理者
 */
public class SharedPrefer {

    //////////////////////////////////////////////////////////////
    // SharedPrefer 缓存功能如下：
    //      1、APP_INFO:    App相关信息缓存（版本号、该版本号第一次登陆）
    //      2、PHONE_INFO:  设备相关信息缓存（）
    //      3、WORK_INFO:   业务相关缓存信息
    //      4、USER_INFO:   用户相关缓存信息(账号、密码、token、设备ID)
    //      5、ACTION_INFO: App上次退出时未完成的信息
    //////////////////////////////////////////////////////////////

    /**
     * 默认sp文件名：
     * 1、APP_INFO:    App相关信息缓存（版本号、该版本号第一次登陆）
     * 2、PHONE_INFO:  设备相关信息缓存（）
     * 3、WORK_INFO:   业务相关缓存信息
     * 4、USER_INFO:   用户相关缓存信息(账号、密码、token、设备ID)
     * 5、ACTION_INFO: App上次退出时未完成的信息
     */
    private String fileName = SPFile.USER_INFO;

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
