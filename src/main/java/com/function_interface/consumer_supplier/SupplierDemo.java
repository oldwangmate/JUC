package com.function_interface.consumer_supplier;

import java.util.function.Supplier;

/**
 * 供给型接口  没有参数 只有返回值
 */
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1;
            }
        };
        System.out.println(supplier.get());

        Supplier<Integer> supplier1 = () -> {
            System.out.println("aaa");
            return 1;
        };
        System.out.println(supplier1.get());
    }
}
