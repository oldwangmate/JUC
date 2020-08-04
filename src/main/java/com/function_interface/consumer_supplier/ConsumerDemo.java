package com.function_interface.consumer_supplier;

import java.util.function.Consumer;

/**
 * Consumer 消费接口 只要输入没有返回值
 */
public class ConsumerDemo {

    public static void main(String[] args) {
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };
       consumer.accept(1);

        Consumer<Integer> consumer1 = (integer -> {
            System.out.println(integer);
        });
        consumer1.accept(1);
    }


}
