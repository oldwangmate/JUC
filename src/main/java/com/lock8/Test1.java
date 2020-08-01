package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁就是关于锁的8个问题
 * 1.标准情况下两个线程先打印发短信还是打电话  结果 发短信 打电话
 * 2.发短信的方法延迟 两个线程那个执行
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        //锁的存在
        new Thread(()->{
            phone.sendms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        },"B").start();
    }
}

class Phone{
    //被synchronized修饰的对象是方法的调用者   两个方法用的是同一个锁，谁先拿到谁执行。
    public synchronized void sendms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}
