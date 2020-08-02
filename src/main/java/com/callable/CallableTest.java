package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 有返回值
 * 可以抛出异常
 * 方法不同 run()/call
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start(); //结果会被缓存 提高效率
        Integer o = (Integer) futureTask.get(); //获取Callable返回结果  get方法可能会产生阻塞 可是使用异步通信处理
        System.out.println(o);
    }

}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 123;
    }
}