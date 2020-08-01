package com.unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class MapTest {
    public static void main(String[] args) {
        /**
         * map是怎么使用的？
         *  不是 工作中不用HashMap
         * map默认等价于什么？
         *  new HashMap(16,0.75F);
         * map 加载因子，初始容量
         *
         * 解决方案：
         *   Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
         *   Map<String,String> map = new ConcurrentHashMap<>();
         */
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
