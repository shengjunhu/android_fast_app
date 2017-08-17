package com.hsj.http.oauth2;

import android.util.Log;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/12 12:51
 * @Class:HttpManager
 * @Description:
 */
public class HttpManagers {

    private String TAG = this.getClass().getSimpleName();

    /**
     * 应用拦截器
     */
    Interceptor appInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            builder.header("OAUTH2","");

            //---------请求之前-----
            Log.d(TAG,"app interceptor:begin");
            Response response = chain.proceed(request);
            Log.d(TAG,"app interceptor:end");
            //---------请求之后------------
            return response;
        }

    };


}
