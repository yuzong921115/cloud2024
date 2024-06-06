package com.zongyu.juc.base.sync;

public class ThreadDemo4 {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}

/**
 * 创建资源类，定义属性和操作方法
 */
class Share {
    private int number = 0;

    /**
     * +1
     *
     * @throws InterruptedException
     */
    public synchronized void incr() throws InterruptedException {
        // 判断number是否0 如果不是0 则等待
//        if (number != 0) // AA BB CC DD 结果错了，虚假唤醒
            while (number != 0) {
            this.wait();
        }
        // 反之+1
        number++;
        System.out.println(Thread.currentThread().getName() + ":加一：" + number);
        // 通知其他线程
        this.notifyAll();
    }

    /**
     * -1
     *
     * @throws InterruptedException
     */
    public synchronized void decr() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + ":减一：" + number);
        // 通知其他线程
        this.notifyAll();
    }
}
