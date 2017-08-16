package com.hsj.app.base.core;

import com.hsj.app.base.BuildConfig;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 15:17
 * @Class:Constant
 * @Description:常量
 */
public interface Constant {

    /**
     * 服务器主机IP
     */
    String HOST                 = "http://192.168.1.152";

    /**
     * 应用APPID
     */
    String APP_ID               = "8CCC8FB7F38DD347";

    /**
     * 应用APPKEY
     */
    String APP_KEY              = "ad02c51d982e4747dfe1430f92548077";

    /**
     * 应用AUTHORIZATION
     */
    String AUTHORIZATION        = "Basic+OENDQzhGQjdGMzhERDM0NzphZDAyYzUxZDk4MmU0NzQ3ZGZlMTQzMGY5MjU0ODA3Nw%3D%3D%0A";

    /**
     * 用户账号
     */
    String ACCOUNT              = "account";

    /**
     * 用户token
     */
    String USER_TOKEN           = "userToken";

    /**
     * IM token
     */
    String IM_TOKEN             = "imToken";

    /**
     * 手机ID
     */
    String DEVICE_ID            = "deviceId";

    /**
     *  当前app版本
     */
    String APP_VERSION_CODE     = "appVersionCode";

    /**
     * 新版本第一次启动
     */
    String NEW_VERSION_START    = "newVersionStart";

    /**
     * 数据库名
     */
    String DB_NAME              = "data.db";

    /**
     * 数据库版本号筒app版本号
     */
    int DB_VERSION              = BuildConfig.VERSION_CODE;


/**********************************  第三方配置参数常量  *********************************************/

    /**
     * QQ登陆AppId
     */
    String QQ_APPID             = "1105437700";

    /**
     * 微信登陆AppId
     */
    String WX_APPID             = "wx0813d97dd646762e";

    /**
     * 微博登陆的AppKey
     */
    String WB_APPKEY            = "1852375498";

    /**
     * 微博登陆成功自己的回调页面
     */
    String REDIRECT_URL         = "https://api.weibo.com/oauth2/default.html";

    /**
     * 微博的OAuth2.0 授权机制中 authorize 接口的一个参数
     * 需要访问用户信息填写，不需要填null
     */
    String SCOPE               = "email,direct_messages_read,direct_messages_write,"
                             + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                             + "follow_app_official_microblog," + "invitation_write";


}
