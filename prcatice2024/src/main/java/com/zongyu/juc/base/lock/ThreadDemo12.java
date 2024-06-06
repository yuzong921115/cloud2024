package com.zongyu.juc.base.lock;

import java.util.concurrent.TimeUnit;

/**
 * 一、死锁
 * 两个或以上进程在执行过程中，因为争夺资源而造成一种互相等待的现象
 * 如果没有外力干涉，他们无法再执行下去
 * <p>
 * 二、产生死锁的原因
 * 1、系统资源不足
 * 2、进程运行推进顺序不合适
 * 3、资源分配不当
 * <p>
 * 三、验证是否死锁
 * jps 类似Linux ps -ef
 * jps -l
 * jstack jvm自带堆栈跟踪工具
 * jstack id（jps -l输出的ID）
 */
public class ThreadDemo12 {
    static Object a = new Object();
    static Object b = new Object();

    /**
     * 模拟死锁
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "持有锁a，试图获取锁b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取锁b");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "持有锁b，试图获取锁a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "获取锁a");
                }
            }
        }, "B").start();
    }
}
