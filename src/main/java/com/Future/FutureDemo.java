package com.Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用：
 * 成功回调
 * 失败回调
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 有返回值的异步回调
         */
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 1000; i++) {
                sum += i;
            }
            int i = 1 /0;
            return sum;
        });
        System.out.println(supplyAsync.whenComplete((t, u) -> {
            System.out.println("t=>" + t);
            System.out.println("u=>" + u);
        }).exceptionally((throwable) -> {
           throwable.getMessage();
            return 233;
        }).get());


        //没有返回值的异步回调
        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"runAsync=>Void");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("2222");
        Void voids = future.get();
        System.out.println(voids);
    }
}
