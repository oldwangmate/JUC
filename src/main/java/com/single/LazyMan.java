package com.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LazyMan {

    private LazyMan(){
        synchronized (LazyMan.class){
            if(lazyMan != null){
                throw new RuntimeException("你还想用反射！！！");
            }
        }
        System.out.println(Thread.currentThread().getName()+ ":ok");
    }

    private volatile static LazyMan lazyMan;


    //双重检查  检查DCL
    public static LazyMan getInstance(){
        if(lazyMan == null){
            synchronized (LazyMan.class){
                if(lazyMan == null){
                    lazyMan = new LazyMan(); //不是原子性操作
                    /**
                     * 分配内存空间
                     * 执行构造方法 初始化对象
                     * 把这个对象指向这个空间
                     *
                     */
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> constructor = LazyMan.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazyMan lazyMan = constructor.newInstance();
        System.out.println(lazyMan == LazyMan.getInstance());
    }
}
