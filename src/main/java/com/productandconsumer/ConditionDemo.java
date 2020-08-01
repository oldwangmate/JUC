package com.productandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Condition 精准通知和唤醒
 * A-》B -》C - 》A 顺序执行
 * 生产线 下单-》支付->交易-》物流
 * */
public class ConditionDemo {

    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        },"C").start();
    }
}

class Data3{
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    private int number = 1;

    public void printA(){
        lock.lock();
        try {
            while (number != 1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"AAAA");
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while (number !=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"BBBB");
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while (number !=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"CCC");
            number =1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}