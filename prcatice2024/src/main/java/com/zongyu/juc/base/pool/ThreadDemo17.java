package com.zongyu.juc.base.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 线程池常用分类:
 * 1、一池N线程
 * 2、一池一线程
 * 3、一池可扩容线程
 * 注意：实际工作中，使用自定义线程ThreadDemo18
 */
public class ThreadDemo17 {
    public static void main3(String[] args) {
        // 一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            // 10顾客请求
            for (int i = 1; i <= 10; i++) {
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } finally {
            // 关闭
            threadPool3.shutdown();
        }
    }

    public static void main2(String[] args) {
        // 一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        try {
            // 10顾客请求
            for (int i = 1; i <= 10; i++) {
                threadPool2.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } finally {
            // 关闭
            threadPool2.shutdown();
        }
    }

    public static void main1(String[] args) {
        // 一池五线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
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
