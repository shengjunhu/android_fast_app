package com.hsj.tool;

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
