package com.hsj.http.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/08/07 23:31
 * @Class:OAuth2Interceptor
 * @Description:基于OAuth2网络请求，拦截本次请求先获取token再执行本次请求
 */
public class OAuth2Interceptor implements Interceptor {

    private String TAG = this.getClass().getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {

        //执行token请求
        String accessToken = "";

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.header("Authorization","");
        builder.header("deviceId", "");
        builder.header("secToken", accessToken);
        builder.header("loginToken", "");

        //---------请求之前-----
        Log.d(TAG, "app interceptor:begin");

        Response response = chain.proceed(request);

        Log.d(TAG, "app interceptor:end");
        //---------请求之后------------
        return response;
    }
}
