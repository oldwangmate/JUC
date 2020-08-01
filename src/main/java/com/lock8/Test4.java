package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 一个普通一个静态方法 只有一个对象 先执行哪个
 * 一个普通一个静态方法 有两个对象 先执行哪个
 */
public class Test4 {
    public static void main(String[] args) {
        //这里两个对象 两个不同的对象，有两把锁 但是都是静态的 所以锁的对象是class对象 这两个对象的class只有一个
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

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

/**
 * 只有唯一的一个class对象
 */
class Phone4{
    //被synchronized修饰的对象是方法的调用者   两个方法用的是同一个锁，谁先拿到谁执行。
    //static 静态方法 类加载的时候就有了 是Class 模板，锁的是Class
    //静态同步方法  这个锁的是class模板
    public static synchronized void sendms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    //普通同步方法  锁的是调用者
    public synchronized void call(){
        System.out.println("打电话");
    }

    /**
     *  new this 具体的一个手机
     *  static Class唯一的模板
     */

}
