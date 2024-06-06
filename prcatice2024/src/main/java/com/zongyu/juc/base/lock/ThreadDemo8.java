package com.zongyu.juc.base.lock;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * HashSet 线程不安全
 */
public class ThreadDemo8 {
    /**
     * 运行报错 ConcurrentModificationException
     *
     * @param args
     */
    public static void main1(String[] args) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合添加内容
                set.add(UUID.randomUUID().toString().substring(0, 8));

                // 从集合获取内容
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 解决办法 CopyOnWriteArraySet
     *
     * @param args
     */
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合添加内容
                set.add(UUID.randomUUID().toString().substring(0, 8));

                // 从集合获取内容
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
