package com.hsj.common.core;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/5/27 15:22
 * @Class:Constants
 * @Description:变量
 */
public class BaseConstants {

////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * App主题
     * 0为白色主题
     * 1为黑色主题
     * 2为粉色主题
     * 3为橙色主题
     * 4为蓝色主题
     * 5为绿色主题
     * 6为紫色主题
     */
    public static int appTheme;

    /**
     * App字体样式
     * 0为系统字体样式
     * 1为行楷字体样式
     */
    public static int appFont;

    /**
     * 是否接收推送
     * false为不接收
     * true为接收
     */
    public static boolean receivePush;

////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 用户设备ID
     */
    public static String deviceId;

    /**
     * 用户登录状态
     */
    public static boolean isLogin;

    /**
     * 用户账号
     */
    public static String account;

    /**
     * 用户登录后加密的登陆token
     */
    public static String loginToken;

    /**
     * 融云imToken
     */
    public static String imToken;

}
