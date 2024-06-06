package com.zongyu.juc.base.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList 集合线程不安全
 */
public class ThreadDemo7 {
    /**
     * 运行报错 ConcurrentModificationException
     *
     * @param args
     */
    public static void main1(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0, 8));

                // 从集合获取内容
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 解决上述问题，办法1：使用Vector解决
     * 比较古老 jdk 1.2
     *
     * @param args
     */
    public static void main2(String[] args) {
        List<String> list = new Vector<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0, 8));

                // 从集合获取内容
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 解决上述问题，办法2：Collections.synchronizedList
     * 比较古老，实际用的少
     *
     * @param args
     */
    public static void main3(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0, 8));

                // 从集合获取内容
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 解决上述问题，办法3：CopyOnWriteArrayList 写时复制技术
     * 并发读，独立写
     * 常用解决办法
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0, 8));

                // 从集合获取内容
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
