package com.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *Semaphore 原理
 * semaphore.acquire();获取  假设已经满了就等待被释放为止
 * semaphore.release(); 释放 //会将当前的信号量释放+1，然后唤醒等待的线程
 * 作用：多个共享资源互斥的使用，并发限流，控制最大的线程数
 *
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //停车位
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    // 得到 semaphore.acquire();
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
