package com.hsj.http.interfaces;

/**
 * @Company:南京荣之誉信息科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/1 09:43
 * @Version:XBS V2.0
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
