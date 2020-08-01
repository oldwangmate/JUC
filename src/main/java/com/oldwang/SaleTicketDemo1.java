package com.oldwang;

/**
 * 卖票的例子
 *
 * 线程就是一个单独的资源类，没有任何的附属操作
 * 1.属性
 * 2,方法
 */
public class SaleTicketDemo1 {

    public static void main(String[] args) {
        //并发 多线程操作操作同一个资源类
        Ticket ticket = new Ticket();
        //Runnable是函数式接口
        new Thread(() -> {
            for(int i = 0;i < 40 ; i++){
                ticket.sale();
            }
        },"A").start();
        new Thread(() -> {
            for(int i = 0;i < 40 ; i++){
                ticket.sale();
            }
        },"B").start();
        new Thread(() -> {
            for(int i = 0;i < 40 ; i++){
                ticket.sale();
            }
        },"C").start();
    }
}

//资源类  OOP
class Ticket{
    //属性，方法
    private int number = 30;

    //卖票的方式
    // synchronized :本质 队列，锁
    public synchronized void sale(){
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,剩余"+number);
        }
    }
}

