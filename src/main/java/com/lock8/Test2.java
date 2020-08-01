package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 增加了一个普通方法，先执行发短信还是hello 先执行普通方法
 * 两个对象 两个同步方法 先执行哪个  打电话
 */
public class Test2 {
    public static void main(String[] args) {
        //这里两个对象 两个不同的对象，有两把锁
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        //锁的存在
        new Thread(()->{
            phone1.sendms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}

class Phone2{
    //被synchronized修饰的对象是方法的调用者   两个方法用的是同一个锁，谁先拿到谁执行。
    public synchronized void sendms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    //
    public synchronized void call(){
        System.out.println("打电话");
    }

    //这里没有锁，不是同步方法，不收锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
