package com.hsj.http.callback;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/6/1 09:43
 * @Class:IHttpListener
 * @Description:
 */
public abstract class IHttpListener<T> {

    abstract void onSuccess(int requestId,T t);

    public void onFailure(int requestId,Exception e) {

    }

}
