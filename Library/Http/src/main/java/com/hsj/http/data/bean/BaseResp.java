package com.hsj.http.data.bean;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/9/18/12:48
 * @Class:BaseResp
 * @Description:网络响应体
 */
public class BaseResp<T> {

    /**
     * 响应状态
     */
    private String returnStatus;
    /**
     * 响应消息
     */
    private String returnMsg;
    /**
     * 响应体：Void、String、Bean
     */
    private T t;

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
