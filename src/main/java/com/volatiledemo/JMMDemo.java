package com.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * 程序不知道主内存的值已经被修改了以下代码会产生死循环
 */
public class JMMDemo {

    private static int num = 0;

    public static void main(String[] args) {

        new Thread(() -> {  //线程1
            while (num == 0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num =1;
        System.out.println(num);
    }
}
