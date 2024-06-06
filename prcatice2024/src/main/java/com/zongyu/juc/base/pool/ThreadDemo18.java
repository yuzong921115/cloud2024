package com.zongyu.juc.base.pool;

import java.util.concurrent.*;

/**
 * ThreadPoolExecutor 参数介绍
 * int corePoolSize, 常驻线程数量（核心）
 * int maximumPoolSize, 最大线程数量
 * long keepAliveTime, 保持存活时间
 * TimeUnit unit, 保持存活时间的单位
 * BlockingQueue<Runnable> workQueue  阻塞队列
 * ThreadFactory threadFactory, 线程工厂
 * RejectedExecutionHandler handler 拒绝策略
 */
public class ThreadDemo18 {
    /**
     * 自定义线程池
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService threadPool1 = new ThreadPoolExecutor(2
                , 5
                , 2L
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(3)
                , Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy());

        try {
            // 10顾客请求
            for (int i = 1; i <= 10; i++) {
                threadPool1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } finally {
            // 关闭
            threadPool1.shutdown();
        }
    }
}
