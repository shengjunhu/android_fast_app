package com.hsj.http.interfaces;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/1 09:43
 * @Class:HttpListener
 * @Description:
 */
public abstract class HttpListener {

    abstract void onSuccess();

    public void onFail() {

    }

}
