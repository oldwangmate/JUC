package com.oldwang;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 卖票的例子
 *
 * 线程就是一个单独的资源类，没有任何的附属操作
 * 1.属性
 * 2,方法
 */
public class SaleTicketDemo2 {

    public static void main(String[] args) {
        //并发 多线程操作操作同一个资源类
        Ticket2 ticket = new Ticket2();
        new Thread(() -> { for(int i = 0;i < 60 ; i++) ticket.sale(); },"A").start();
        new Thread(() -> { for(int i = 0;i < 60 ; i++) ticket.sale(); },"B").start();
        new Thread(() -> { for(int i = 0;i < 60 ; i++) ticket.sale(); },"C").start();

    }
}

// Lock

/**
 * 三部曲
 * 1.new ReentrantLock //创建锁
 * 2.lock.lock 加锁
 * 3. 解锁.  lock.unlock()
 */
class Ticket2{
    //属性，方法
    private int number = 60;

    Lock lock = new ReentrantLock();
    //公平锁：十分公平，可以先来后到
    //非公平锁：十分不公平，可以插队，默认是非公平锁，
    //卖票的方式
    // synchronized :本质 队列，锁
    public  void sale(){
        lock.lock();
        lock.tryLock() ;//尝试获取锁
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,剩余"+number);
            }
        }finally {
            lock.unlock();  //解锁
        }
    }
}

/*
 * synchronized与Lock的区别
 * 1. synchronized 内置的关键字 lock是一个类
 * 2.synchronized 无法判断获取锁的状态，lock可以判断是否获取了锁
 * 3.synchronized会自动释放锁，lock必须要手动释放锁
 * 4.synchronized 线程1（获取锁，阻塞） 线程2(等待) lock不一定会等待
 * 5.synchronized可重入锁，不可以中断，非公平，lock可重入锁，可以判断锁，非公平可以自己设置
 * 6.synchronized适合锁少量的代码，Lock适合锁大量的同步代码块
 *
 * 锁是什么，如何判断锁的是谁
 * 8锁现象
 * 对象 Class
 * */