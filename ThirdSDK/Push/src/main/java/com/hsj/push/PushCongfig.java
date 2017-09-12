package com.hsj.push;

import android.content.Context;

import com.xiaomi.mipush.sdk.MiPushClient;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/12 11:11
 * @Class:PushCongfig
 * @Description:推送配置类
 */
public class PushCongfig {

    /**
     * 初始化推送模块（外部调用）
     * @param isDebug
     * @param context
     */
    public static void initPush(boolean isDebug,Context context){

    }

    /**
     * 停止推送（外部调用）
     * @param context
     */
    public static void stopPush(Context context){

    }

    public static void registerHw() {

    }

    /**
     * 注册小米推送
     * @param context
     * @param miAppId
     * @param miAppKey
     */
    public static void registerMi(Context context, String miAppId, String miAppKey) {
        try {
            MiPushClient.registerPush(context, miAppId, miAppKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerJp() {

    }

    public static void unRegisterHw() {

    }

    public static void unRegisterMi() {

    }

    public static void unRegisterJp() {

    }

    /**
     * 停止JPush推送
     * @param context
     */
    public static void stopJPush(Context context){

    }

    /**
     * 通过Alias推送
     *
     * @param context
     * @param alias
     * @param category
     */
    public static void setMiAlias(Context context, String alias, String category) {

    }

    /**
     * 取消指定用户的Alias
     *
     * @param context
     * @param alias
     * @param category
     */
    public static void unsetMiAlias(Context context, String alias, String category) {

    }

    /**
     * 根据用户账户推送
     *
     * @param context
     * @param userAccount
     * @param category
     */
    public static void setMiUserAccount(Context context, String userAccount, String category) {

    }

    /**
     * 取消账户推送
     *
     * @param context
     * @param userAccount
     * @param category
     */
    public static void unsetMiUserAccount(Context context, String userAccount, String category) {

    }

}
