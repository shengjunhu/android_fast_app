package com.hsj.http.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/08/07 23:31
 * @Class:OAuth2Interceptor
 * @Description:基于OAuth2网络请求，拦截本次请求获取token
 */
public class OAuth2Interceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
