package com.hsj.http.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
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

        Request request = chain.request();

        //请求前--打印请求信息
        long t1 = System.nanoTime();
        //Log.info(String.format("%s on %s%n%s",  request.url(), chain.connection(), request.headers()));

        //网络请求
        Response response = chain.proceed(request);

        //网络响应后--打印响应信息
        long t2 = System.nanoTime();
        //logger.info(String.format("%s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
