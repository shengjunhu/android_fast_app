package com.hsj.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/08/07 23:31
 * @Class:LogInterceptor
 * @Description:拦截网络日志
 */
public class LogInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
