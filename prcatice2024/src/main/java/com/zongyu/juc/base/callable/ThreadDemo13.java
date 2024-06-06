package com.zongyu.juc.base.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的多种方式：
 * 1、继承Thread
 * 2、实现Runable接口
 * 注意：1和2方式，线程终止时，run完成，无法使用线程返回结果
 * 3、Callable接口
 * 4、线程池
 * <p>
 * Runable和Callable区别
 * Runable没有返回值，不会抛出异常
 * Callable有返回值，会抛出异常
 * 实现的方法名称不同，前者是run方法，后者是call方法
 */
public class ThreadDemo13 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Runnable接口创建线程
        new Thread(new MyThread1(), "AA").start();

        // Callable接口创建线程
//        new Thread(new MyThread2(),"BB").start(); // 报错
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName()+" come in callable");
            return 1024;
        });

        new Thread(futureTask2,"lucy").start();
        new Thread(futureTask,"mary").start();

        while (!futureTask2.isDone()){
            System.out.println("wait.......");
        }

        System.out.println(futureTask.get());
        System.out.println(futureTask2.get());
//        System.out.println(futureTask2.get());

        System.out.println(Thread.currentThread().getName()+" come over");
    }
}

class MyThread1 implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable {

    @Override
    public Integer call() throws Exception {
        return 200;
    }
}