package com.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * java.util.ConcurrentModificationException 并发修改异常
 */
public class ListTest {
    public static void main(String[] args) {
        //并发下ArrayList不安全的
        /**
         * 解决方案：
         *   1.List<String> list = new Vector<>();
         *   2. List<String> list = Collections.synchronizedList(new ArrayList<>());
         *   3.  List<String> list = new CopyOnWriteArrayList();
         */
        /**
         * CopyOnWrite 写入时复制  COW 计算机程序设计领域的一种优化设计
         * 多个线程调用的时候 list是唯一的，固定的，写入的时候可能会覆盖掉
         * 在写入的时候Copy一份避免覆盖造成数据问题
         * 读写分离
         * CopyOnWriteArrayList 比Vector的优势在哪里
         * Vector使用的是synchronized
         * CopyOnWriteArrayList使用的是Lock
         */
        List<String> list = new CopyOnWriteArrayList();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
