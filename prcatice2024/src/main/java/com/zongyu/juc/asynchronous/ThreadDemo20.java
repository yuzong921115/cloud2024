package com.zongyu.juc.asynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步回调
 */
public class ThreadDemo20 {
    /***
     * 异步调用有返回值
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " completableFuture");
            // 模拟异常 java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
//            int i = 10 / 0;
            return 2024;
        });
        completableFuture.whenComplete((t, u) -> {
            System.out.println("-------t=" + t); // 返回值
            System.out.println("-------u=" + u); // 异常信息
        });
    }

    /***
     * 异步调用无返回值
     * @param args
     */
    public static void main2(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " completableFuture");
        });
        completableFuture.get();
    }
}
