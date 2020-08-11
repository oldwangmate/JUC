package com.single;

/**
 * 静态内部类实现
 */
public class Holder {
    private Holder(){}
    public static class Inner{
        private static final Holder HOLDER = new Holder();
    }

    public static Holder getInstance(){
        return Inner.HOLDER;
    }

}
