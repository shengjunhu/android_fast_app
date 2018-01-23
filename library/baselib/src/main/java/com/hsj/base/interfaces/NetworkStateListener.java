package com.hsj.base.interfaces;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/06/30/00:34
 * @Class:NetworkStateListener
 * @Description:网络状态监听
 */
public interface NetworkStateListener {

    int NETWORK_CLOSE       = -1;
    int NETWORK_UNAVAILABLE = 1;
    int NETWORK_2G          = 2;
    int NETWORK_3G          = 3;
    int NETWORK_4G          = 4;
    int NETWORK_5G          = 5;
    int NETWORK_WIFI        = 0;

    @IntDef({NETWORK_CLOSE, NETWORK_UNAVAILABLE, NETWORK_2G, NETWORK_3G, NETWORK_4G, NETWORK_5G, NETWORK_WIFI})
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.SOURCE)
    @Documented
    @Inherited
    @interface NetType {

    }

    /**
     * 网络状态回调
     *
     * @param isAvailable 网络是否可用
     * @param netType
     */
    void netState(boolean isAvailable, @NetType int netType);

}
