package com.hsj.core.tool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/3/22 12:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  ThreadTool
 * @Description :  线程池管理工具类
 */
public abstract class ThreadTool {

    /**
     * 创建一个线程池
     */
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    /**
     * 要执行的耗时操作
     */
    public abstract void doTask();

    /**
     * 执行异步任务
     */
    public void start() {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                doTask();
            }
        });
    }

}
