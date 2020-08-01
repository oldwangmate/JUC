package com.productandconsumer;

/**
 * 线程之间的通信问题：生产者和消费者 等待唤醒，通知唤醒
 * 线程交替执行。 两个线程操作同一个变量，
 * A线程+1
 * B线程-1
 * synchronized 版本
 */
public class ProductAndConsumber {
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
                    data.decrement();
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

/**
 * 判断等待 业务 通知
 */
class Data{
    private int number = 0;

    /**
     * 加1
     */
    public synchronized void increment() throws InterruptedException {
        while (number != 0){
            //等待
            this.wait();
        }
        number ++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程 我+1完毕
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0){
            //等待
            this.wait();
        }
        number --;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程 我-1完毕
        this.notifyAll();
    }

}

/*
* 问题 现在有4个线程还安全吗
* 不安全；会产生虚假唤醒问题， 判断的时候不能使用if 必须使用while作为判断
* */