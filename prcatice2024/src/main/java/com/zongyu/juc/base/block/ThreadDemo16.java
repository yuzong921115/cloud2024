package com.zongyu.juc.base.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * ArrayBlockingQueue
 * 数组定长，链路定长
 */
public class ThreadDemo16 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(3);

        // 第四组
        System.out.println(blockingDeque.offer("a"));
        System.out.println(blockingDeque.offer("b"));
        System.out.println(blockingDeque.offer("c"));
        System.out.println(blockingDeque.offer("w", 3L, TimeUnit.SECONDS));
    }

    public static void main3(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(3);

        // 第三组
        blockingDeque.put("a");
        blockingDeque.put("b");
        blockingDeque.put("c");
//        blockingDeque.put("d");

        System.out.println(blockingDeque.take());
        System.out.println(blockingDeque.take());
        System.out.println(blockingDeque.take());
        System.out.println(blockingDeque.take());
    }

    public static void main2(String[] args) {
        BlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(3);

        // 第二组
        System.out.println(blockingDeque.offer("a"));
        System.out.println(blockingDeque.offer("b"));
        System.out.println(blockingDeque.offer("c"));
        System.out.println(blockingDeque.offer("w"));

        System.out.println(blockingDeque.poll());
        System.out.println(blockingDeque.poll());
        System.out.println(blockingDeque.poll());
        System.out.println(blockingDeque.poll());
    }

    public static void main1(String[] args) {
        // 创建阻塞队列
        BlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(3);

        // 第一组
        System.out.println(blockingDeque.add("a"));
        System.out.println(blockingDeque.add("b"));
        System.out.println(blockingDeque.add("c"));
        System.out.println(blockingDeque.element());

//        System.out.println(blockingDeque.add("w"));

        System.out.println(blockingDeque.remove());
        System.out.println(blockingDeque.remove());
        System.out.println(blockingDeque.remove());
        System.out.println(blockingDeque.remove());
    }
}
