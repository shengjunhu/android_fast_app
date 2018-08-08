package com.hsj.common.interfaces;

import com.hsj.common.annotations.NetType;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
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
