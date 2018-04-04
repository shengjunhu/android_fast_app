package com.hsj.common.core;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/7/12 09:58
 * @Class:BaseException
 * @Description:基类异常
 */
public class BaseException extends RuntimeException {

    /**
     * 自定义网络异常码
     */
    private static final int ERROR_CODE_9999 = 9999;//自定义异常：存储空间不够

    private BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(int code) {
        super(parseException(code));
    }

    private static String parseException(int code){
        String msg;
        switch (code){
            case ERROR_CODE_9999:
                msg = "可用存储空间小于100MB";
                break;
            default:
                msg = "未知异常";
                break;
        }
        return msg;
    }


}
