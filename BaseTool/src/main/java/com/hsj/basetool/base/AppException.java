package com.hsj.basetool.base;

import android.widget.Toast;
import com.hsj.basetool.tool.Logger;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/2/21 12:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  AppException
 * @Description :  全局抛异常：具体异常类型根据业务设置
 */
public class AppException extends RuntimeException {

    public static final int ERROR_CODE              = 999;              //网络请求：失败码
    public static final int OFFLINE_CODE            = 401;              //网络请求：账号被踢
    public static final int TOKEN_INVALID_CODE      = 403;              //网络请求：token过期
    public static final int REFUSE_CODE_            = 405;              //网络请求：请求没有授权
    public static final int DATA_UNREADABLE_CODE    = 0;                //网络请求数据解析错误
    public static final int UNKNOWN_ERROR_CODE      = -1;               //未知错误

    public AppException(int exceptionCode) {
        super(ExceptionMsg(exceptionCode, null));
    }

    public AppException(String exceptionMsg) {
        super(ExceptionMsg(-1, exceptionMsg));
    }

    public AppException(int exceptionCode,String exceptionMsg) {
        super(ExceptionMsg(exceptionCode, exceptionMsg));
    }

    /**
     * 异常信息
     *
     * @param code -- 响应码
     * @param msg  -- 响应信息（msg不为null，code无效）
     * @return
     */
    public static String ExceptionMsg(int code, String msg) {
        switch (code) {
            case ERROR_CODE:
                msg = "Warning：网络请求失败!";
                break;
            case OFFLINE_CODE:
                msg = "Warning：账号已下线!";
                break;
            case TOKEN_INVALID_CODE:
                msg = "Waring：token无效!";
                break;
            case REFUSE_CODE_:
                msg = "Waring：访问被拒绝!";
                break;
            case DATA_UNREADABLE_CODE:
                msg = "Error：数据解析错误";
                break;
            case UNKNOWN_ERROR_CODE:
                msg = "Error：未知错误!";
                break;
            default:
                break;
        }
        Logger.w("AppException", "全局异常信息: AppException：" + msg);
        Toast.makeText(BaseToolContext.mContext,msg,Toast.LENGTH_SHORT).show();
        return msg;
    }


}
