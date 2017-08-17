package com.hsj.base.lib.interfaces;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/06/30 00:34
 * @Class:NetListener
 * @Description:
 */
public interface NetListener {

    /**
     * 网络状态回调
     * @param netType     -- 当前网络类型
     * @param isAvailable -- 网络是否可用
     */
    void netStatus(int netType, boolean isAvailable);

}
