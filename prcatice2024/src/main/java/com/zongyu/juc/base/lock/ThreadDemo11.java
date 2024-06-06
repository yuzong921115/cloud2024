package com.zongyu.juc.base.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、公平锁和非公平锁
 * 非公平锁：线程饿死，效率高
 * 公平锁：阳光普照，效率相对低
 * <p>
 * 2、可重复锁（递归锁）
 * synchronized隐式
 * Lock显式
 */
public class ThreadDemo11 {
    /**
     * 可重复锁（递归锁） 实践Lock
     *
     * @param args
     */
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "外层");

                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "内层");
                } finally {
                    lock.unlock();
                }

            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            System.out.println("hello");
            lock.unlock();
        }, "t2").start();
    }

    public synchronized void add() {
        add();
    }

    /**
     * 运行报错：StackOverflowError
     *
     * @param args
     */
    public static void main3(String[] args) {
        new ThreadDemo11().add();
    }

    /**
     * 可重复锁（递归锁） 实践synchronized
     *
     * @param args
     */
    public static void main2(String[] args) {
//        synchronized 隐式
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "外层");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + "中层");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "内层");
                    }
                }
            }
        }, "t1").start();
    }

    public static void main1(String[] args) {
//        ReentrantLock false 非公平，默认是false
        Lock lock = new ReentrantLock();
    }
}
