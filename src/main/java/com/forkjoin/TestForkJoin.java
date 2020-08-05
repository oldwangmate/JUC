package com.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TestForkJoin {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
//        test2();
        test3();
    }

    public static  void test1(){  //普通计算
        Long start = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 0L; i <= 1000000000; i++) {
            sum += i;
        }
        Long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"  耗时："+(end - start));  // 6661
    }

    public static  void test2() throws ExecutionException, InterruptedException {  //forkJoin
        Long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 1000000000L);
        Long sum = pool.submit(task).get();
        Long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"  耗时："+(end - start)); //4259
    }

    public static  void test3(){
        Long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 1000000000L).parallel().reduce(0, Long::sum);
        Long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"  耗时："+(end - start)); //218
    }
}
