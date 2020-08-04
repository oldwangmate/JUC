package com.function_interface;

import com.TestScala;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 函数式接口
 * @FunctionalInterface 简化编程，在新版本的框架底层大量使用
 *
 * Function 有一个输入参数 有一个输入 只要是函数式接口都可以用lambda表达式简化
 */

public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        /**
         * Function 函数型接口
         */
        Function<Integer, Integer> function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 1;
            }
        };
        System.out.println(function.apply(1));

          Function<Integer,Integer> function1 = (a) -> {
              return a + 1 ;
          };

        System.out.println(function1.apply(1));
        //调用Scala中的方法
        TestScala testScala = new TestScala();
        System.out.println(testScala.add(1, 2));


    }

}
