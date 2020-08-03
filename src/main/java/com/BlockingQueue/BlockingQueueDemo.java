package com.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 阻塞
 * 队列
 *
 * 不得不阻塞
 * 写入： 如果队列慢了就必须阻塞等待
 * 读取：如果队列是空的，必须阻塞等待生成
 *
 * 阻塞队列：
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();

    }
    /**
     * 抛出异常  java.lang.IllegalStateException: Queue full 队列满了
     */
    public static void test1(){
        //队列的大小是3
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        System.out.println(arrayBlockingQueue.add("A"));
        System.out.println(arrayBlockingQueue.add("B"));
        System.out.println(arrayBlockingQueue.add("C"));
        System.out.println("--------------------------");
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());  //Exception in thread "main" java.util.NoSuchElementException 没有元素
    }

    /**
     * 不抛出异常  返回boolean值不抛出异常
     */
    public static void test2(){
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        System.out.println(arrayBlockingQueue.offer("A"));
        System.out.println(arrayBlockingQueue.offer("B"));
        System.out.println(arrayBlockingQueue.offer("C"));
        System.out.println(arrayBlockingQueue.offer("D"));
        System.out.println(arrayBlockingQueue.element()); //查看队首元素
        System.out.println(arrayBlockingQueue.peek()); //检查队首元素
        System.out.println("--------------------------");
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
    }

    /**
     * 等待，阻塞 （一直）
     * 等待（超时）
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        //一直阻塞
        arrayBlockingQueue.put("A");
        arrayBlockingQueue.put("B");
        arrayBlockingQueue.put("C");
//        arrayBlockingQueue.put("D");  //一直等待

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());

    }

    /**
     * 等待超时  存入等待时间超过2S就推出
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        arrayBlockingQueue.offer("A");
        arrayBlockingQueue.offer("B");
        arrayBlockingQueue.offer("C");
        arrayBlockingQueue.offer("A",2,TimeUnit.SECONDS);

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll(2,TimeUnit.SECONDS));
    }

}
