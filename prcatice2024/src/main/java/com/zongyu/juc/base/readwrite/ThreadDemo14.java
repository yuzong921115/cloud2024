package com.zongyu.juc.base.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 乐观锁、悲观锁
 * <p>
 * 行锁、表锁
 * <p>
 * 读锁，写锁
 */
public class ThreadDemo14 {
    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num+"");
            }, String.valueOf(i)).start();
        }

        TimeUnit.MICROSECONDS.sleep(300);

        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    // 创建读写锁
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " 正在写操作" + key);
            // 暂停一会儿
            TimeUnit.MICROSECONDS.sleep(300);
            // 放数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " -----写完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        rwLock.readLock().lock();

        Object result = null;

        try {
            System.out.println(Thread.currentThread().getName() + " 正在读操作" + key);
            // 暂停一会儿
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);

            System.out.println(Thread.currentThread().getName() + " 取完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
        return result;
    }
}
