package com.zongyu.juc.base.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap 线程不安全
 */
public class ThreadDemo9 {
    /**
     * 运行报错 ConcurrentModificationException
     *
     * @param args
     */
    public static void main1(String[] args) {
        Map<String, String> map = new HashMap();

        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                // 向集合添加内容
                map.put(key, UUID.randomUUID().toString().substring(0, 8));

                // 从集合获取内容
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 解决办法 ConcurrentHashMap
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                // 向集合添加内容
                map.put(key, UUID.randomUUID().toString().substring(0, 8));

                // 从集合获取内容
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
