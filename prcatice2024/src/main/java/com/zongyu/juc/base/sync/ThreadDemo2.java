package com.zongyu.juc.base.sync;

/**
 * 多线程编程步骤：
 * 1、创建一个资源类，在资源类创建属性和操作方法
 * 2、在资源类操作方法
 * 判断——干活——通知
 * 3、创建多个线程，调用资源类的操作方法
 */
public class ThreadDemo2 {
    /**
     * 3个售票员 卖出30张票
     */
    public static void main(String[] args) {
        // 3、创建多个线程，调用资源类的操作方法
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.Sale();
                }
            }
        }, "AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.Sale();
                }
            }
        }, "BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.Sale();
                }
            }
        }, "CC").start();
    }
}

/**
 * 1、创建一个资源类，在资源类创建属性和操作方法
 */
class Ticket {
    // 票数
    private int number = 30;

    /**
     * 操作方法：卖票
     * 2、在资源类操作方法
     * * 判断——干活——通知
     */
    public synchronized void Sale() {
        // 判断：是否有票
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "" +
                    "，卖出：（" + number-- + "），剩下：" + number);
        }
    }
}