package com.zongyu.juc.base.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 进阶
 * 一个资源可以被多个读线程访问，或者被一个写线程访问，但是不能同时存在读写线程，读写互斥，读读共享
 * <p>
 * 缺点：
 * 1、造成锁饥饿，一直读，没有写操作
 * 2、读时候，不能写，只有读完成之后才可以写；写操作可以读
 */
public class ThreadDemo15 {
    /**
     * 读写锁降级 读不可升为写
     * @param args
     */
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        // 锁降级

        // 获取读锁
        readLock.lock();
        System.out.println("read");

        // 获取写锁
        writeLock.lock();
        System.out.println("zongyu");

        writeLock.unlock();
        readLock.unlock();
    }

    /**
     * 读写锁降级 写可以降为读
     * @param args
     */
    public static void main1(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        // 锁降级
        // 获取写锁
        writeLock.lock();
        System.out.println("zongyu");

        readLock.lock();
        System.out.println("read");

        writeLock.unlock();
        readLock.unlock();
    }
}
