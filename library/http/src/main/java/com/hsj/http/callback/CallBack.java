package com.hsj.http.callback;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/6/1 09:45
 * @Class:CallBack
 * @Description: 网络回调过程
 */
public interface CallBack {

    void onStart();

    void onDoing(long currentSize, long totalSize, float progress, long networkSpeed);

    void parseError();

    void onSuccess();

    void onCacheSuccess();

    void onCacheError();

    void onError();

    void onFinish();

}
