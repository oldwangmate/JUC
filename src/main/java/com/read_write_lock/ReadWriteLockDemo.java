package com.read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁  写锁 一次只能被一个线程占有
 * 共享锁  读锁 多个线程可以同时占有
 *
 * ReadWriteLock
 * 读和读  可以共存
 * 读和写 不能共存
 * 写这写 不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        MyCacheLock myCacheLock = new MyCacheLock();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}


class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    //读写锁 更加细粒度的控制
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    //存 写入的时候只希望只有一个线程写
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    //取 读 所有人都可以进行读取
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK");
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/*
 * 自定义缓存
 */

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    //存
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入OK");
    }

    //取 读
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取OK");
    }
}