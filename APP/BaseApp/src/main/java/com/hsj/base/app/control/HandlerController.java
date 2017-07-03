package com.hsj.base.app.control;

import android.os.Handler;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/28 10:27
 * @Class:HandlerController
 * @Description:全局Handler--防止handler泄漏
 */
class HandlerController {

    private static final Handler mHandler = new Handler();

    public static Handler getInstance() {
        return mHandler;
    }

    private HandlerController() {

    }

}
