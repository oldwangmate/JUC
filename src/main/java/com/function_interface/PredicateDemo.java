package com.function_interface;

import java.util.function.Predicate;

/**
 * 断定性接口 返回值是一个boolean值
 */
public class PredicateDemo {

    public static void main(String[] args) {
        Predicate<Integer> integerPredicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if(integer > 1){
                    return true;
                }else {
                    return false;
                }
            }
        };
        System.out.println(integerPredicate.test(2));

        Predicate<Integer> predicate = (param) -> {
            if(param > 1){
                return true;
            }else {
                return false;
            }
        };
        System.out.println(predicate.test(2));
    }
}
