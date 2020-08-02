package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 计数器  CountDownLatch是一个减法计数器
 *  countDownLatch.countDown();//数量减一
 *    countDownLatch.await();//等待计数器归零 在向下执行
 *    每次有线程调用countDown()数据量减一 假设计数器变为0 countDownLatch.await()就会被唤醒继续继续执行
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6  必须要执行任务的时候，在使用
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                countDownLatch.countDown();//数量减一
                System.out.println(Thread.currentThread().getName()+"Go out");
            },String.valueOf(i)).start();
        }
        countDownLatch.await();//等待计数器归零 在向下执行

        System.out.println("close door");
    }
}

