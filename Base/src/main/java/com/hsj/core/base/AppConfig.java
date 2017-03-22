package com.hsj.core.base;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/3/21 14:03
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  ConfigInfo
 * @Description :  全局信息配置
 */
public class AppConfig {

    interface Info{
        /**
         * 服务器IP
         */
        String HOST = "";

        /**
         * 应用ID
         */
        String APPID = "";

        /**
         * 应用KEY
         */
        String APPKEY = "";
    }

    /**
     * 用户设备ID
     */
    public static String deviceId;

    /**
     * 用户账号
     */
    public static String account;

    /**
     * 用户密码
     */
    public static String password;

}

