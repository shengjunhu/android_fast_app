package com.hsj.tool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Company:北京****科技有限公司
 * @Author:HSJ
 * @Version:Framework V1.0
 * @Date:2017/3/22 12:54
 * @E-mail:mr.ajun@foxmail.com
 * @Class:ThreadPoolTool
 * @Description:线程池管理工具类
 */
public class ThreadPoolTool {

    private static ThreadPoolTool mInstance = new ThreadPoolTool();

    public static ThreadPoolTool getInstance() {
        return mInstance;
    }

    /**
     *  线程池核心数量（同时能够执行的线程数量）
     */
    private int corePoolSize;

    /**
     *最大线程池数量，表示当缓冲队列满的时候能继续容纳的等待任务的数量
     */
    private int maximumPoolSize;
    /**
     * 保活时间
     */
    private long keepAliveTime = 1;
    /**
     * 保活时间级别
     */
    private TimeUnit unit = TimeUnit.SECONDS;
    /**
     * 线程池
     */
    private ThreadPoolExecutor executor;

    private ThreadPoolTool() {
        /**
         * 给corePoolSize赋值：当前设备可用处理器核心数*2 + 1,能够让cpu的效率得到最大程度执行（有研究论证的）
         */
        corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        /**
         * 虽然maximumPoolSize用不到，但是需要赋值，否则报错
         */
        maximumPoolSize = corePoolSize;

        executor = new ThreadPoolExecutor(
                corePoolSize,                           //当某个核心任务执行完毕，会依次从缓冲队列中取出等待任务
                maximumPoolSize,                        //5,先corePoolSize,然后new LinkedBlockingQueue<Runnable>(),然后maximumPoolSize,但是它的数量是包含了corePoolSize的
                keepAliveTime,                          //表示的是maximumPoolSize当中等待任务的存活时间
                unit,                                   //时间单位数量级
                new LinkedBlockingQueue<Runnable>(),    //缓冲队列，用于存放等待任务，Linked的先进先出
                Executors.defaultThreadFactory(),       //创建线程的工厂
                new ThreadPoolExecutor.AbortPolicy()    //用来对超出maximumPoolSize的任务的处理策略
        );
    }

    /**
     * 执行任务
     */
    public void execute(Runnable runnable) {
        if (runnable != null){
            executor.execute(runnable);
        }
    }

    /**
     * 从线程池中移除任务
     */
    public void remove(Runnable runnable) {
        if (runnable != null){
            executor.remove(runnable);
        }
    }

}
