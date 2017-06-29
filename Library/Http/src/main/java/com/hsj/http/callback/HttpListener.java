package com.hsj.http.callback;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/1 09:43
 * @Class:HttpListener
 * @Description:
 */
public abstract class HttpListener {

    abstract void onSuccess(int requestId,String result);

    public void onFailure(int requestId,Exception e) {

    }

}
