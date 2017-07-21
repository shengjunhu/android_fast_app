package com.hsj.base.app.uitls;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.lang.reflect.Field;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ThreadManager {

    // UI线程
    private static Handler mMainHandler;
    private static Object mMainHandlerLock = new Object();

    // 本地改就行..不要传svn
    public static final boolean DEBUG_THREAD = false;

    /**
     * AsyncTask的默认线程池Executor. 负责长时间的任务(网络访问) 默认3个线程
     */
    public static final Executor NETWORK_EXECUTOR;

    /**
     * 副线程的Handle, 只有一个线程 可以执行比较快但不能在ui线程执行的操作. 文件读写不建议在此线程执行,
     * 请使用FILE_THREAD_HANDLER 此线程禁止进行网络操作.如果需要进行网络操作. 请使用NETWORK_EXECUTOR
     */
    private static Handler SUB_THREAD_HANDLER;

    private static HandlerThread SUB_THREAD;

    /**
     * 文件读写线程的Handle, 只有一个线程 可以执行文件读写操作, 如图片解码等 此线程禁止进行网络操作.如果需要进行网络操作.
     * 请使用NETWORK_EXECUTOR
     */
    private static Handler FILE_THREAD_HANDLER;
    /**
     * 文件读写用的线程
     */
    private static HandlerThread FILE_THREAD;

    static {
        NETWORK_EXECUTOR = initNetworkExecutor();
    }

    private static Executor initNetworkExecutor() {
        Executor result = null;
        // 3.0以上
        if (Build.VERSION.SDK_INT >= 15) {
            //result = AsyncTask.THREAD_POOL_EXECUTOR;
            result = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        } else {// 3.0以下, 反射获取
            Executor tmp = null;
            try {
                Field field = AsyncTask.class.getDeclaredField("sExecutor");
                field.setAccessible(true);
                tmp = (Executor) field.get(null);
            } catch (Exception e) {
                tmp = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>());
            }
            result = tmp;
        }

        if (result instanceof ThreadPoolExecutor) {
            // core size减少为3个
            ((ThreadPoolExecutor) result).setCorePoolSize(3);
        }

        return result;
    }

    public static void init() {

    }

    /**
     * 取得UI线程Handler
     *
     * @return
     */
    public static Handler getMainHandler() {
        if (mMainHandler == null) {
            synchronized (mMainHandlerLock) {
                if (mMainHandler == null) {
                    mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return mMainHandler;
    }

    /**
     * 在网络线程上执行异步操作. 该线程池负责网络请求等操作 长时间的执行(如网络请求使用此方法执行) 当然也可以执行其他 线程和AsyncTask公用
     *
     * @param run
     */
    public static void executeOnNetWorkThread(Runnable run) {
        try {
            NETWORK_EXECUTOR.execute(run);
        } catch (RejectedExecutionException e) {
        }
    }

    /**
     * 获得文件线程的Handler.<br>
     * 副线程可以执行本地文件读写等比较快但不能在ui线程执行的操作.<br>
     * <b>此线程禁止进行网络操作.如果需要进行网络操作. 请使用NETWORK_EXECUTOR</b>
     *
     * @return handler
     */
    public static Handler getFileThreadHandler() {
        if (FILE_THREAD_HANDLER == null) {
            synchronized (ThreadManager.class) {
                FILE_THREAD = new HandlerThread("QQ_FILE_RW");
                FILE_THREAD.start();
                FILE_THREAD_HANDLER = new Handler(FILE_THREAD.getLooper());
            }
        }
        return FILE_THREAD_HANDLER;
    }

    public static Looper getFileThreadLooper() {
        return getFileThreadHandler().getLooper();
    }

    public static Thread getSubThread() {
        if (SUB_THREAD == null) {
            getSubThreadHandler();
        }
        return SUB_THREAD;
    }

    /**
     * 获得副线程的Handler.<br>
     * 副线程可以执行比较快但不能在ui线程执行的操作.<br>
     * 另外, 文件读写建议放到FileThread中执行 <b>此线程禁止进行网络操作.如果需要进行网络操作.
     * 请使用NETWORK_EXECUTOR</b>
     *
     * @return handler
     */
    public static Handler getSubThreadHandler() {
        if (SUB_THREAD_HANDLER == null) {
            synchronized (ThreadManager.class) {
                SUB_THREAD = new HandlerThread("QQ_SUB");
                SUB_THREAD.start();
                SUB_THREAD_HANDLER = new Handler(SUB_THREAD.getLooper());
            }
        }
        return SUB_THREAD_HANDLER;
    }

    public static Looper getSubThreadLooper() {
        return getSubThreadHandler().getLooper();
    }

    /**
     * 在副线程执行. <br>
     * 可以执行本地文件读写等比较快但不能在ui线程执行的操作.<br>
     * <b>此线程禁止进行网络操作.如果需要进行网络操作. 请使用NETWORK_EXECUTOR</b>
     *
     * @return
     */
    public static void executeOnSubThread(Runnable run) {
        getSubThreadHandler().post(run);
    }

}
