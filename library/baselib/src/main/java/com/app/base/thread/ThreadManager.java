package com.app.base.thread;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 16:41
 * @Class:ThreadManager
 * @Description:线程池
 */
public class ThreadManager {

    private volatile static ThreadManager mThreadManager;

    private ThreadManager() {

    }

    public static ThreadManager getInstance() {
        if (mThreadManager == null) {
            synchronized (ThreadManager.class) {
                if (mThreadManager == null) {
                    mThreadManager = new ThreadManager();
                }
            }
        }
        return mThreadManager;
    }

}
