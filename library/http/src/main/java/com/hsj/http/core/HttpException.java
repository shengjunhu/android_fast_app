package com.hsj.http.base;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/6/1 17:05
 * @Class:HttpException
 * @Description:网络请求模块异常控制类
 */
public class HttpException extends RuntimeException {

    /**
     * Http网络异常码
     */
    private static final int ERROR_CODE_400 = 400;//错误请求
    private static final int ERROR_CODE_401 = 401;//未授权
    private static final int ERROR_CODE_403 = 403;//没有权限
    private static final int ERROR_CODE_404 = 404;//没有找到资源
    private static final int ERROR_CODE_405 = 405;//请求方法不被允许
    private static final int ERROR_CODE_406 = 406;//缺少请求头
    private static final int ERROR_CODE_408 = 408;//请求超时
    private static final int ERROR_CODE_410 = 410;//请求资源在服务器不在可用
    private static final int ERROR_CODE_415 = 415;//请求资源或方法或请求体服务器不支持
    private static final int ERROR_CODE_500 = 500;//服务器错误
    private static final int ERROR_CODE_501 = 501;//服务器无法识别请求
    private static final int ERROR_CODE_502 = 502;//服务器无响应
    private static final int ERROR_CODE_503 = 503;//服务器过载
    private static final int ERROR_CODE_504 = 504;//未获取响应
    private static final int ERROR_CODE_505 = 505;//服务器不支持
    private static final int ERROR_CODE_509 = 509;//服务器达到带宽限制
    /**
     * 自定义网络异常码
     */
    private static final int ERROR_CODE_109 = 109;//自定义异常：客户端未知错误
    private static final int ERROR_CODE_609 = 609;//自定义异常：服务器未知错误
    private static final int ERROR_CODE_709 = 709;//自定义异常：客户端解析异常
    private static final int ERROR_CODE_909 = 909;//自定义异常：token（cookie、session）过期

    private HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(int code) {
        super(parseException(code));
    }

    private static String parseException(int code){
        String msg;
        switch (code){
            case ERROR_CODE_109:
                msg = "客户端未知错误";
                break;
            case ERROR_CODE_609:
                msg = "服务器未知错误";
                break;
            case ERROR_CODE_709:
                msg = "客户端解析异常";
                break;
            case ERROR_CODE_909:
                msg = "token（cookie、session）过期";
                break;
            case ERROR_CODE_401:
                msg = "未授权";
                break;
            case ERROR_CODE_403:
                msg = "没有权限";
                break;
            case ERROR_CODE_408:
                msg = "请求超时";
                break;
            case ERROR_CODE_500:
                msg = "服务器错误";
                break;
            case ERROR_CODE_400:
                msg = "错误请求";
                break;
            case ERROR_CODE_404:
                msg = "没有找到资源";
                break;
            case ERROR_CODE_405:
                msg = "请求方法不被允许";
                break;
            case ERROR_CODE_406:
                msg = "缺少请求头";
                break;
            default:
                msg = "未知网络层异常";
                break;
        }
        return msg;
    }

}