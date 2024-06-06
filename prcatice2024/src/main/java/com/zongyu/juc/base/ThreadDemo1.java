package com.zongyu.juc.base;

import org.junit.Test;

/**
 * 概念
 * 1、进程：指在系统中运行的一个应用程序；
 * 程序一旦运行就是进程；
 * 进程——资源分配的最小单位。
 * <p>
 * 2、线程：系统分配处理器时间资源的基本单元，或者进程之内独立执行的一个单元执行流；
 * 线程——程序执行的最小单位
 * <p>
 * 3、并行：多项工作一起执行，之后再汇总
 * 比如：泡泡面，可以烧水，撕调料等
 * 4、串行：排队等待
 * 5、并发：同一时刻多个线程在访问同一资源，多个线程对一个点
 * 比如：春运抢票，电商秒杀
 * 6、wait和sleep的区别
 * sleep是Thread的静态方法，wait是Object的方法，任何对象实例都能调用；
 * sleep不会释放锁，它也不需要占用锁。wait会释放锁，但调用它的前提是当前线程占有锁（即代码要在synchronized中）；
 * 它们都可以被interrupted方法中断
 * 7、管程
 * Monitor 监视器
 * 是一种同步机制，保证同一个时间，只有一个线程访问被保护数据或代码
 * JVM同步基于进入和退出，使用管程对象实现的
 */
public class ThreadDemo1 {
    /**
     * 线程的状态
     * NEW 新建
     * RUNNABLE 准备就绪
     * BLOCKED 阻塞
     * WAITING 不见不散
     * TIMED_WAITING 过时不候
     * TERMINATED 终结
     */
    @Test
    public void test3() {
//        Thread.State;
    }

    /**
     * Thread.currentThread().isDaemon() 输出true，表示用户线程
     * 没有用户线程，都是守护线程，JVM结束
     * 整体运行，打印：
     * main
     * Thread-0::true
     */
    @Test
    public void test2() {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        });

        // 设置为守护线程，必须在start之前
        thread.setDaemon(true);
        // 启动线程
        thread.start();
        System.out.println(Thread.currentThread().getName());
    }

    /**
     * Thread.currentThread().isDaemon() 输出false，表示用户线程
     * 主线程结束，用户线程还在运行，JVM存活
     * 整体运行，打印：
     * main
     * Thread-0::false
     */
    @Test
    public void test1() {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        });

        // 启动线程
        thread.start();
        System.out.println(Thread.currentThread().getName());
    }
}
