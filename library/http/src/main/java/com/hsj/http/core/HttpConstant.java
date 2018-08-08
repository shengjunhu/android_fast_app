package com.hsj.http.base;

import com.hsj.http.BuildConfig;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/6/12 12:51
 * @Class:HttpConstant
 * @Description:
 */
public interface HttpConstant {

    /**
     * 服务器主机IP
     */
    String  SERVER_HOST = BuildConfig.HOST;

    /**
     * 默认连接超时 5s
     */
    int HTTP_CONNECT_TIME_OUT = 5000;

    /**
     * 默认读取超时 10s
     */
    int HTTP_READ_TIME_OUT = 10000;

    /**
     * 默认写入时间 10s
     */
    int HTTP_WRITE_TIME_OUT = 10000;

    /**
     * 默认刷新时间 200ms
     */
    int HTTP_REFRESH_TIME = 200;



}
