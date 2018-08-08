package com.hsj.socket;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/9/8 10:33
 * @Class:SocketListener
 * @Description:Socket监听
 */
public interface SocketListener {

    /**
     * 未链接或断开链接
     */
    void onDisConnected();

    /**
     * 正在链接
     */
    void onConnecting();

    /**
     * 链接成功
     */
    void onConnected();

    /**
     * 被踢下线或该账号在另外一台设备上登陆
     */
    void onKicked();

    /**
     * 接收到服务器响应
     */
    void onResponse();

}
