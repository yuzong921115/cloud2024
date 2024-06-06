package com.zongyu.juc.base.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟三个售票员卖票，类似ThreadDemo2
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        LTicket lTicket = new LTicket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        }, "CC").start();
    }
}

class LTicket {
    private int number = 30;

    // 创建可重入锁
    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        // 上锁
        lock.lock();

        try {
            // 判断是否有票
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "" +
                        "，卖出：（" + number-- + "），剩下：" + number);
            }
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}