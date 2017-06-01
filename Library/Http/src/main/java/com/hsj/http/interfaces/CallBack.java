package com.hsj.http.interfaces;

/**
 * @Company:南京荣之誉信息科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/1 09:45
 * @Version:XBS V2.0
 * @Class:CallBack
 * @Description: 网络回调过程
 */
public abstract class CallBack {

    abstract void onStart();

    abstract void onDoing(long currentSize, long totalSize, float progress, long networkSpeed);

    abstract void parseError();

    abstract void onSuccess();

    abstract void onCacheSuccess();

    abstract void onCacheError();

    abstract void onError();

    abstract void onFinish();
}
