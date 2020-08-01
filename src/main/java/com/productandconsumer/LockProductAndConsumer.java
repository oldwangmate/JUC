package com.productandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class LockProductAndConsumer {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}


class LockData{
    private int number = 10;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    /**
     * 加1
     */
    public  void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0){
                //等待
                condition.await(); //等待
            }
            number ++;
            condition.signalAll(); //唤醒全部
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程 我+1完毕
        }finally {
            lock.unlock();
        }

    }

    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0){
                //等待
                condition.await();
            }
            number --;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程 我-1完毕
        }finally {
            lock.unlock();
        }

    }
}

