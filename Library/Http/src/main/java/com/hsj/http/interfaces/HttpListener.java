package com.hsj.http.interfaces;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/1 09:43
 * @Class:HttpListener
 * @Description:
 */
public abstract class HttpListener extends CallBack{

    @Override
    void onStart() {

    }

    @Override
    void onDoing(long currentSize, long totalSize, float progress, long networkSpeed) {

    }

    @Override
    void parseError() {

    }

    @Override
    void onCacheSuccess() {

    }

    @Override
    void onCacheError() {

    }

    @Override
    void onError() {

    }

    @Override
    void onFinish() {

    }
}
