package com.zongyu.juc.base.subsidiary;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 辅助类
 * CountDownLatch、CyclicBarrier、Semaphore
 */
public class ThreadDemo14 {
    private static final int NUMBER = 7;

    /**
     * Semaphore 信号灯
     * 6辆汽车，停3个车位
     *
     * @param args
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {// 抢占
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + " 抢到了车位");

                    // 设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName() + " ----离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }

    /**
     * CyclicBarrier 循环栅栏
     *
     * @param args
     */
    public static void main2(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("--集齐7颗龙珠就可以召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 星龙被收集到了");

                    // 等待
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }, String.valueOf(i)).start();
        }
    }

    /**
     * CountDownLatch 减少计数
     * 6个同学陆续离开教室后，班长锁门
     *
     * @param args
     */
    public static void main1(String[] args) throws InterruptedException {
        // 创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开教室");

                // 计数 -1
                countDownLatch.countDown();

            }, String.valueOf(i)).start();
        }

        // 等待
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + " 班长锁门");
    }
}
