package com.SynchronousQueueDemo;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/***
 * 同步队列
 * 和其他的BlockingQueue 不一样，SynchronousQueue 不存储元素
 * put了一个元素，必须从里面先take取出来，
 * 否则不能在put进去值！
 * */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> es = new SynchronousQueue<String>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+": put 1");
                es.put("1");
                System.out.println(Thread.currentThread().getName()+": put 2");
                es.put("2");
                System.out.println(Thread.currentThread().getName()+": put 3");
                es.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+":" + es.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+":" + es.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+":" + es.take());

            }catch (Exception e){
                e.printStackTrace();
            }

        },"T2").start();
    }
}
