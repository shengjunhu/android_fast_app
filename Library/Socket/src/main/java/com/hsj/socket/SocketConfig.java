package com.hsj.socket;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/8 10:23
 * @Class:SocketConfig
 * @Description:Socket配置信息
 */
public class SocketConfig {

    /**
     * 远程服务器 Ip
     */
    private String hostIp;

    /**
     * 服务器端口
     */
    private String hostPort;

    /**
     * 本地端口
     */
    private String clientPort;

    /**
     * 链接超时时间 ms
     */
    private long connectionTimeout;

    /**
     * 编码格式：默认utf-8
     */
    private String charset = "UTF-8";

    /**
     * 是否执行心跳，默认执行
     */
    private boolean isHeartBeat = true;

    /**
     * 是否自动发送心跳包
     */
    private boolean isAutoSendData;

    /**
     * 心跳间隔时间
     */
    private long heartBeatTime;

}
