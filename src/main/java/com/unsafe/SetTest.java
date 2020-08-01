package com.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *  java.util.ConcurrentModificationException 并发修改异常
 */
public class SetTest {
    public static void main(String[] args) {
        /**
         * 解决方案：
         * 1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
         * 2.Set<String> set = new CopyOnWriteArraySet<>();
         * HashSet底层
         * 就是一个HashMap  set的本事就是map  因为map的key是不能重复的
         */
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
