package com.single;

/**
 * 饿汉式单例
 *
 * 可能会浪费空间
 */
public class Hungry {

    private Hungry(){}

    private final static Hungry HUNGRY = new Hungry();

    public static  Hungry getinstance(){
        return HUNGRY;
    }
}
