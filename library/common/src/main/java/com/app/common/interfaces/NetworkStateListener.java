package com.app.common.interfaces;

import com.app.common.annotations.NetType;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/06/30/00:34
 * @Class:NetworkStateListener
 * @Description:网络状态监听
 */
public interface NetworkStateListener {

    /**
     * 网络状态回调
     *
     * @param isAvailable 网络是否可用
     * @param netType
     */
    void netState(boolean isAvailable, @NetType int netType);

}
